package com.test.task.sql.service.mapper;

import com.test.task.sql.model.PriceHistory;
import com.test.task.sql.model.dto.request.PriceHistoryRequestDto;

public interface PriceHistoryMapper {

    PriceHistory toEntity(PriceHistoryRequestDto requestDto);

}
