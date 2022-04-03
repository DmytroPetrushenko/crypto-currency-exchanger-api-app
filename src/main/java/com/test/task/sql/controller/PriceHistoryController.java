package com.test.task.sql.controller;

import com.test.task.sql.model.PriceHistory;
import com.test.task.sql.model.dto.response.MaxPriceResponseDto;
import com.test.task.sql.model.dto.response.MinPriceResponseDto;
import com.test.task.sql.service.CexIoClient;
import com.test.task.sql.service.PriceHistoryService;
import java.util.Comparator;
import java.util.List;
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

    //GET /cryptocurrencies/minprice?name=[currency_name] - should return record with the lowest price of selected
    // cryptocurrency
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
    //GET /cryptocurrencies/maxprice?name=[currency_name] - should return record with the highest price of selected
    // cryptocurrency [currency_name] possible values: BTC, ETH or XRP. If some other value is provided then appropriate
    // error message should be thrown.
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

    //GET /cryptocurrencies?name=[currency_name]&page=[page_number]&size=[page_size] - should return a selected page
    // with selected number of elements and default sorting should be by price from lowest to highest. For example, if
    // page=0&size=10, then you should return first 10 elements from database, sorted by price from lowest to highest.
    // [page_number] and [page_size] request parameters should be optional, so if they are missing then you should set
    // them default values page=0, size=10.


    //Generate a CSV report.
    //You need to create an endpoint that will generate a CSV report saved into file.
    //GET /cryptocurrencies/csv Report should contain the following fields: Cryptocurrency Name, Min Price, Max Price.
    // So there should be only three records in that report, because we have three different cryptocurrencies. Feel free
    // to use any available library for generating csv files.
}
