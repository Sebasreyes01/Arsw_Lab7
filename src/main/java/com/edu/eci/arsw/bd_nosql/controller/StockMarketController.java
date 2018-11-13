package com.edu.eci.arsw.bd_nosql.controller;

import com.edu.eci.arsw.bd_nosql.services.StockMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/stockmarket")
public class StockMarketController {

    @Autowired
    StockMarketService sms;

    @RequestMapping(method = RequestMethod.GET, path = "/intraday/{symbol}/{interval}")
    public ResponseEntity<?> handlerGetResourceStockMarketintraday(@PathVariable String symbol, @PathVariable String interval) {
        try {
            return new ResponseEntity<>(sms.stockInfo("TIME_SERIES_INTRADAY", symbol, interval), HttpStatus.ACCEPTED);
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
            return new ResponseEntity<>(sms.stockInfo(f, symbol, ""), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            Logger.getLogger(StockMarketController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
    }

}
