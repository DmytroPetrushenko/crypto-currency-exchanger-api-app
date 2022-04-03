package com.test.task.sql.service;

import com.test.task.sql.model.PriceHistory;

import java.util.List;

public interface PriceHistoryService {
    void save(PriceHistory element);

    List<PriceHistory> findAllByCurrencyPair(String pair);

    void writeToCsv(List<String[]> csvList);
}
