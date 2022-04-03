package com.test.task.sql.controller;

import com.test.task.sql.model.PriceHistory;
import com.test.task.sql.model.dto.response.MaxPriceResponseDto;
import com.test.task.sql.model.dto.response.MinPriceResponseDto;
import com.test.task.sql.model.dto.response.SortedPageResponseDto;
import com.test.task.sql.service.CexIoClient;
import com.test.task.sql.service.PriceHistoryService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cryptocurrencies")
public class PriceHistoryController {
    private static final List<String> CURRENCY_PAIR_LIST =
            List.of("BTC/USD", "XRP/USD", "ETH/USD");
    private final CexIoClient client;
    private final PriceHistoryService service;

    public PriceHistoryController(CexIoClient client, PriceHistoryService service) {
        this.client = client;
        this.service = service;
    }

    @GetMapping("/inject")
    public void getTradeHistory() {
        for (String currencyPair : CURRENCY_PAIR_LIST) {
            client.getTradeHistory(currencyPair);
        }
    }

    @GetMapping("/minprice")
    public MinPriceResponseDto getMinPrice(@RequestParam String name) {
        List<PriceHistory> priceHistoryList = service.findAllByCurrencyPair(name);
        MinPriceResponseDto responseDto = new MinPriceResponseDto();
        String minPrice = priceHistoryList.stream()
                .map(PriceHistory::getPrice)
                .min(Comparator.naturalOrder())
                .orElseThrow(() -> {
                    throw new RuntimeException("Can't get the min price of the currency pair: "
                            + name + " in DB!");
                });
        responseDto.setCurrencyPair(name);
        responseDto.setMinPrice(minPrice);
        return responseDto;
    }

    @GetMapping("/maxprice")
    public MaxPriceResponseDto getMaxPrice(@RequestParam String name) {
        MaxPriceResponseDto responseDto = new MaxPriceResponseDto();
        String maxPrice = service.findAllByCurrencyPair(name).stream()
                .map(PriceHistory::getPrice)
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> {
                    throw new RuntimeException("Can't get the max price of the currency pair: "
                            + name + " in DB!");
                });
        responseDto.setCurrencyPair(name);
        responseDto.setMaxPrice(maxPrice);
        return responseDto;
    }

    @GetMapping()
    public SortedPageResponseDto getSortedPage(@RequestParam String name,
                                               @RequestParam Optional<Integer> page,
                                               @RequestParam Optional<Integer> size) {
        SortedPageResponseDto responseDto = new SortedPageResponseDto();
        List<String> priceList = service.findAllByCurrencyPair(name).stream()
                .map(PriceHistory::getPrice)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        int pageNumber = page.orElse(0);
        int sizeNumber = size.orElse(10);
        responseDto.setSortedPricesList(priceList
                .subList(pageNumber * sizeNumber, pageNumber * sizeNumber + sizeNumber));
        responseDto.setCurrencyPair(name);
        responseDto.setPageNumber(pageNumber);
        return responseDto;
    }

    @GetMapping("/csv")
    public void getCsv() {
        List<String[]> csvList = new ArrayList<>();
        csvList.add(new String[]{"Cryptocurrency Name", "Min Price", "Max Price"});
        for (String currencyPair: CURRENCY_PAIR_LIST) {
            List<PriceHistory> priceHistoryList = service.findAllByCurrencyPair(currencyPair);
            String min = priceHistoryList.stream()
                    .map(PriceHistory::getPrice)
                    .min(Comparator.naturalOrder())
                    .orElseThrow(() -> {
                        throw new RuntimeException("Can't get the minimum price of "
                                + "the currency pair: " + currencyPair + " in DB!");
                    });
            String max = priceHistoryList.stream()
                    .map(PriceHistory::getPrice)
                    .min(Comparator.naturalOrder())
                    .orElseThrow(() -> {
                        throw new RuntimeException("Can't get the maximum price of "
                                + "the currency pair: " + currencyPair + " in DB!");
                    });
            csvList.add(new String[]{currencyPair, min, max});
        }
        service.writeToCsv(csvList);
    }
}
