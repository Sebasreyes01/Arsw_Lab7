package com.edu.eci.arsw.bd_nosql.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Stock {

    @Id
    public String id;

    public LocalDateTime date;
    public String function;
    public String symbol;
    public String interval;
    public String info;

    public Stock() {}

    public Stock(LocalDateTime date, String function, String symbol, String interval, String info) {
        this.date = date;
        this.function = function;
        this.symbol = symbol;
        this.interval = interval;
        this.info = info;
    }

    @Override
    public String toString() {
        return String.format("Stock[id=%s, date='%s', function='%s', symbol='%s', interval='%s', info='%s']", id, date, function, symbol, interval, info);
    }

}
