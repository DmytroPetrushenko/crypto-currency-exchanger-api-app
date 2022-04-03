package com.test.task.sql.model.dto.request;

import lombok.Data;

@Data
public class PriceHistoryRequestDto {
    private Integer tmsp;
    private String price;
    private String currencyPair;
}
