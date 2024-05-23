package com.nnk.springboot.fixture;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * The type Install trade fixture.
 */
@Component
@Slf4j
public class InstallTradeFixture {
    private final TradeService tradeService;

    /**
     * Instantiates a new Install trade fixture.
     *
     * @param tradeService the trade service
     */
    public InstallTradeFixture(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    /**
     * Execute.
     */
    public void execute() {
        log.warn("Creating fixtures for Trade");
        tradeService.saveTrade(new Trade("Trade Account Test", "Type Test", 10.0), "admin");
        tradeService.saveTrade(new Trade("Trade Account Test 2", "Type Test 2", 20.0), "admin");
    }

}
