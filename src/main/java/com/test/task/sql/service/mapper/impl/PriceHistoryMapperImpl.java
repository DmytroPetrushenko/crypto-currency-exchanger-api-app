package com.test.task.sql.service.mapper.impl;

import com.test.task.sql.model.PriceHistory;
import com.test.task.sql.model.dto.request.PriceHistoryRequestDto;
import com.test.task.sql.service.mapper.PriceHistoryMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PriceHistoryMapperImpl implements PriceHistoryMapper {
    @Override
    public PriceHistory toEntity(PriceHistoryRequestDto requestDto) {
        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setTimeStampUnix(requestDto.getTmsp());
        priceHistory.setPrice(requestDto.getPrice());
        priceHistory.setCrateAt(LocalDateTime.now().toString());
        priceHistory.setCurrencyPair(requestDto.getCurrencyPair());
        return priceHistory;
    }
}
