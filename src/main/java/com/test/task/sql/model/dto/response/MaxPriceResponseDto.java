package com.test.task.sql.model.dto.response;

import lombok.Data;

@Data
public class MaxPriceResponseDto {
    private String currencyPair;
    private String maxPrice;
}
