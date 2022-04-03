package com.test.task.sql.repository;

import com.test.task.sql.model.PriceHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Integer> {

    List<PriceHistory> findAllByCurrencyPair(String pair);
}
