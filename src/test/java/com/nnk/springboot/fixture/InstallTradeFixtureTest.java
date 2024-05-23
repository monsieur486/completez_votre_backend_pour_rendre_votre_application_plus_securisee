package com.nnk.springboot.fixture;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class InstallTradeFixtureTest {

    @Mock
    TradeService tradeService;

    @InjectMocks
    InstallTradeFixture installTradeFixture;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute() {
        installTradeFixture.execute();
        verify(tradeService, times(2)).saveTrade(any(Trade.class), eq("admin"));
    }
}