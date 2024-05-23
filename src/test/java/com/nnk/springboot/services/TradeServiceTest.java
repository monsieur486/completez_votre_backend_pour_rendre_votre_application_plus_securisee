package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TradeServiceTest {

    @Mock
    TradeRepository tradeRepository;

    @InjectMocks
    TradeService tradeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveTrade() {
        Trade trade = new Trade();
        tradeService.saveTrade(trade, "test");
        verify(tradeRepository, times(1)).save(any(Trade.class));
    }

    @Test
    void findTradeById() {
        Trade trade = new Trade();
        trade.setTradeId(1);
        when(tradeRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(trade));

        Trade result = tradeService.findTradeById(1);

        assertEquals(1, result.getTradeId());
        verify(tradeRepository, times(1)).findById(any(Integer.class));
    }

    @Test
    void findAllTrades() {
        when(tradeRepository.findAll()).thenReturn(Collections.emptyList());

        Iterable<Trade> result = tradeService.findAllTrades();

        assertEquals(0, ((List<Trade>) result).size());
        verify(tradeRepository, times(1)).findAll();
    }

    @Test
    void deleteTradeById() {
        tradeService.deleteTradeById(1);
        verify(tradeRepository, times(1)).deleteById(any(Integer.class));
    }

    @Test
    void updateTrade() {
        Trade trade = new Trade();
        tradeService.updateTrade(trade, "test");
        verify(tradeRepository, times(1)).save(any(Trade.class));
    }
}