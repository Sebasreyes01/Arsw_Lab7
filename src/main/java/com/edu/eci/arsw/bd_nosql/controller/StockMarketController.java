package com.edu.eci.arsw.bd_nosql.controller;

import com.edu.eci.arsw.bd_nosql.model.Stock;
import com.edu.eci.arsw.bd_nosql.model.StockRepository;
import com.edu.eci.arsw.bd_nosql.services.StockMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/stockmarket")
public class StockMarketController {

    @Autowired
    private StockMarketService sms;

    @Autowired
    private StockRepository repository;

    @RequestMapping(method = RequestMethod.GET, path = "/intraday/{symbol}/{interval}")
    public ResponseEntity<?> handlerGetResourceStockMarketintraday(@PathVariable String symbol, @PathVariable String interval) {
        try {
            LocalDateTime now = LocalDateTime.now();
            int day = now.getDayOfYear();
            int year = now.getYear();
            Stock stockDB = repository.findByFunctionAndSymbolAndInterval("intraday", symbol, interval);

            String ans;
            if(stockDB != null) {
                if(stockDB.date.getDayOfYear() < day || stockDB.date.getYear() < year) {
                    repository.deleteById(stockDB.id);
                    ans = sms.stockInfo("TIME_SERIES_INTRADAY", symbol, interval);
                    repository.save(new Stock(now,"intraday", symbol, interval, ans));
                } else {
                    ans = stockDB.info;
                }
            } else {
                ans = sms.stockInfo("TIME_SERIES_INTRADAY", symbol, interval);
                repository.save(new Stock(now,"intraday", symbol, interval, ans));
            }

//            if(stockDB != null && (stockDB.date.getDayOfYear() < day || stockDB.date.getYear() < year)) {
//                repository.deleteById(stockDB.id);
//            }
//
//            if(repository.findByFunctionAndSymbolAndInterval("intraday", symbol, interval) == null) {
//                ans = sms.stockInfo("TIME_SERIES_INTRADAY", symbol, interval);
//                repository.save(new Stock("intraday", symbol, interval, ans));
////                System.out.println("llamo API externo");
//            } else {
//                ans = repository.findByFunctionAndSymbolAndInterval("intraday", symbol, interval).info;
////                System.out.println("llamo DB externa");
//            }
            return new ResponseEntity<>(ans, HttpStatus.ACCEPTED);
//            return new ResponseEntity<>(sms.stockInfo("TIME_SERIES_INTRADAY", symbol, interval), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            Logger.getLogger(StockMarketController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{function}/{symbol}")
    public ResponseEntity<?> handlerGetResourceStockMarket(@PathVariable String function, @PathVariable String symbol) {
        try {
            String f;
            if(function.equalsIgnoreCase("daily")) {
                f = "TIME_SERIES_DAILY";
            } else if(function.equalsIgnoreCase("weekly")) {
                f = "TIME_SERIES_WEEKLY";
            } else if(function.equalsIgnoreCase("monthly")) {
                f = "TIME_SERIES_MONTHLY";
            } else {
                f = function;
            }
            LocalDateTime now = LocalDateTime.now();
            int day = now.getDayOfYear();
            int year = now.getYear();
            Stock stockDB = repository.findByFunctionAndSymbolAndInterval(function, symbol, "");
            String ans;
            if(stockDB != null) {
                if(stockDB.date.getDayOfYear() < day || stockDB.date.getYear() < year) {
                    repository.deleteById(stockDB.id);
                    ans = sms.stockInfo(f, symbol, "");
                    repository.save(new Stock(now, function, symbol, "", ans));
                } else {
                    ans = stockDB.info;
                }
            } else {
                ans = sms.stockInfo(f, symbol, "");
                repository.save(new Stock(now, function, symbol, "", ans));
            }

//            if(repository.findByFunctionAndSymbolAndInterval(function, symbol, "") == null) {
//                ans = sms.stockInfo(f, symbol, "");
//                repository.save(new Stock(function, symbol, "", ans));
////                System.out.println("llamo API externo");
//            } else {
//                ans = repository.findByFunctionAndSymbolAndInterval(function, symbol, "").info;
////                System.out.println("llamo DB externa");
//            }
            return new ResponseEntity<>(ans, HttpStatus.ACCEPTED);
//            return new ResponseEntity<>(sms.stockInfo(f, symbol, ""), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            Logger.getLogger(StockMarketController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
    }

}
