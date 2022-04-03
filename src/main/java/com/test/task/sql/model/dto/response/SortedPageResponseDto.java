package com.test.task.sql.model.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class SortedPageResponseDto {
    private String currencyPair;
    private Integer pageNumber;
    private List<String> sortedPricesList;
}
