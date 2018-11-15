package com.edu.eci.arsw.bd_nosql.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StockRepository extends MongoRepository<Stock, String> {

    public Stock findByFunctionAndSymbolAndInterval(String function, String symbol, String interval);
    public List<Stock> findBySymbol(String symbol);

}
