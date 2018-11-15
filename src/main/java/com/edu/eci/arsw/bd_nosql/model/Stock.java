package com.edu.eci.arsw.bd_nosql.model;

import org.springframework.data.annotation.Id;

public class Stock {

    @Id
    public String id;

    public String function;
    public String symbol;
    public String interval;
    public String info;

    public Stock() {}

    public Stock(String function, String symbol, String interval, String info) {
        this.function = function;
        this.symbol = symbol;
        this.interval = interval;
        this.info = info;
    }

    @Override
    public String toString() {
        return String.format("Stock[id=%s, function='%s', symbol='%s', interval='%s', info='%s']", id, function, symbol, interval, info);
    }

}
