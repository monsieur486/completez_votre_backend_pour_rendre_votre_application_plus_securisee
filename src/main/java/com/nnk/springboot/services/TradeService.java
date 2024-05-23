package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.stereotype.Service;

/**
 * The type Trade service.
 */
@Service
public class TradeService {

    private final TradeRepository tradeRepository;

    /**
     * Instantiates a new Trade service.
     *
     * @param tradeRepository the trade repository
     */
    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    /**
     * Save trade.
     *
     * @param trade        the trade
     * @param creationName the creation name
     */
    public void saveTrade(Trade trade, String creationName) {
        trade.setCreationName(creationName);
        trade.setCreationDate(new java.sql.Timestamp(System.currentTimeMillis()));
        tradeRepository.save(trade);
    }

    /**
     * Update trade.
     *
     * @param trade        the trade
     * @param revisionName the revision name
     */
    public void updateTrade(Trade trade, String revisionName) {
        trade.setRevisionName(revisionName);
        trade.setRevisionDate(new java.sql.Timestamp(System.currentTimeMillis()));
        tradeRepository.save(trade);
    }

    /**
     * Find trade by id trade.
     *
     * @param id the id
     * @return the trade
     */
    public Trade findTradeById(Integer id) {
        return tradeRepository.findById(id).orElse(null);
    }

    /**
     * Find all trades iterable.
     *
     * @return the iterable
     */
    public Iterable<Trade> findAllTrades() {
        return tradeRepository.findAll();
    }

    /**
     * Delete trade by id.
     *
     * @param id the id
     */
    public void deleteTradeById(Integer id) {
        tradeRepository.deleteById(id);
    }

}
