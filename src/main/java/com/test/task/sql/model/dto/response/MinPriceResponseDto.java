package com.test.task.sql.model.dto.response;

import lombok.Data;

@Data
public class MinPriceResponseDto {
    private String currencyPair;
    private String minPrice;
}
