package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.stereotype.Service;

@Service
public class TradeService {

    private final TradeRepository tradeRepository;

    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public void saveTrade(Trade trade, String creationName) {
        trade.setCreationName(creationName);
        trade.setCreationDate(new java.sql.Timestamp(System.currentTimeMillis()));
        tradeRepository.save(trade);
    }

    public void updateTrade(Trade trade, String revisionName) {
        trade.setRevisionName(revisionName);
        trade.setRevisionDate(new java.sql.Timestamp(System.currentTimeMillis()));
        tradeRepository.save(trade);
    }

    public Trade findTradeById(Integer id) {
        return tradeRepository.findById(id).orElse(null);
    }

    public Iterable<Trade> findAllTrades() {
        return tradeRepository.findAll();
    }

    public void deleteTradeById(Integer id) {
        tradeRepository.deleteById(id);
    }

}