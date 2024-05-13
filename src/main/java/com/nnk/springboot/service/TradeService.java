package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.stereotype.Service;

@Service
public class TradeService {

    private final TradeRepository tradeRepository;

    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public void saveTrade(Trade trade) {
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

    public boolean existsById(Integer id) {
        return tradeRepository.existsById(id);
    }
}
