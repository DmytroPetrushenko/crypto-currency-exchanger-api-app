package com.test.task.sql.service.impl;

import com.test.task.sql.model.PriceHistory;
import com.test.task.sql.repository.PriceHistoryRepository;
import com.test.task.sql.service.PriceHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceHistoryServiceImpl implements PriceHistoryService {
    private final PriceHistoryRepository repository;

    public PriceHistoryServiceImpl(PriceHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(PriceHistory element) {
        repository.save(element);
    }

    @Override
    public List<PriceHistory> findAllByCurrencyPair(String pair) {
        return repository.findAllByCurrencyPair(pair);
    }
}
