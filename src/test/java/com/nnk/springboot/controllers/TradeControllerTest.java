package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.security.Principal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TradeControllerTest {
    @Mock
    TradeService tradeService;

    @Mock
    Model model;

    @Mock
    BindingResult bindingResult;

    @Mock
    Principal principal;

    @InjectMocks
    TradeController tradeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void home() {
        when(tradeService.findAllTrades()).thenReturn(Collections.emptyList());
        String result = tradeController.home(model);
        assertEquals("trade/list", result);
        verify(tradeService, times(1)).findAllTrades();
    }

    @Test
    void addTradeForm() {
        Trade trade = new Trade();
        String result = tradeController.addUser(trade);
        assertEquals("trade/add", result);
    }

    @Test
    void validate() {
        Trade trade = new Trade();
        when(bindingResult.hasErrors()).thenReturn(false);
        String result = tradeController.validate(trade, bindingResult, model, principal);
        assertEquals("trade/list", result);
    }

    @Test
    void showUpdateForm() {
        Trade trade = new Trade();
        when(tradeService.findTradeById(any(Integer.class))).thenReturn(trade);
        String result = tradeController.showUpdateForm(1, model);
        assertEquals("trade/update", result);
        verify(tradeService, times(1)).findTradeById(any(Integer.class));
    }

    @Test
    void updateTrade() {
        Trade trade = new Trade();
        when(bindingResult.hasErrors()).thenReturn(false);
        String result = tradeController.updateTrade(1, trade, bindingResult, model, principal);
        assertEquals("redirect:/trade/list", result);
    }

    @Test
    void deleteTrade() {
        String result = tradeController.deleteTrade(1, model);
        assertEquals("redirect:/trade/list", result);
        verify(tradeService, times(1)).deleteTradeById(any(Integer.class));
    }

}