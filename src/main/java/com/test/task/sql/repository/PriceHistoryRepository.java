package com.test.task.sql.repository;

import com.test.task.sql.model.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Integer> {

    List<PriceHistory> findAllByCurrencyPair(String pair);
}
