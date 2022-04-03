package com.test.task.sql.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.task.sql.model.dto.request.ParametersForClientRequestDto;
import com.test.task.sql.model.dto.request.PriceHistoryRequestDto;
import com.test.task.sql.service.mapper.PriceHistoryMapper;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CexIoClient {
    private static final Integer LAST_HOURS = 24;
    private static final Integer NUMBER_PRICES = 100;
    private static final String URL = "https://cex.io/api/price_stats/";
    private final PriceHistoryMapper mapper;
    private final PriceHistoryService service;

    public CexIoClient(PriceHistoryMapper mapper, PriceHistoryService service) {
        this.mapper = mapper;
        this.service = service;
    }

    public void getTradeHistory(String currencyPair) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<ParametersForClientRequestDto> params =
                new HttpEntity<>(new ParametersForClientRequestDto(LAST_HOURS, NUMBER_PRICES));
        String object = restTemplate
                .postForObject(URL + currencyPair, params, String.class);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        PriceHistoryRequestDto[] histories = gson
                .fromJson(object, PriceHistoryRequestDto[].class);
        for (PriceHistoryRequestDto element: histories) {
            element.setCurrencyPair(currencyPair);
            service.save(mapper.toEntity(element));
        }
    }

}
