package com.test.task.sql.service.impl;

import com.opencsv.CSVWriter;
import com.test.task.sql.model.PriceHistory;
import com.test.task.sql.repository.PriceHistoryRepository;
import com.test.task.sql.service.PriceHistoryService;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;

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

    @Override
    public void writeToCsv(List<String[]> csvList) {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter("C:\\IdeaProjects\\Oril"
                + "\\new_decision\\src\\main\\resources\\csv\\report.csv"))) {
            csvWriter.writeAll(csvList);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to a csv file!", e);
        }
    }
}
