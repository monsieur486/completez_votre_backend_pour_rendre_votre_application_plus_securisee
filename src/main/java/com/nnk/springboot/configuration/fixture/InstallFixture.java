package com.nnk.springboot.configuration.fixture;

import com.nnk.springboot.domain.*;
import com.nnk.springboot.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InstallFixture {
    private final BidListService bidListService;
    private final CurvePointService curvePointService;
    private final RatingService ratingService;
    private final RuleNameService ruleNameService;
    private final TradeService tradeService;

    public InstallFixture(BidListService bidListService, CurvePointService curvePointService, RatingService ratingService, RuleNameService ruleNameService, TradeService tradeService) {
        this.bidListService = bidListService;
        this.curvePointService = curvePointService;
        this.ratingService = ratingService;
        this.ruleNameService = ruleNameService;
        this.tradeService = tradeService;
    }

    public void installBidListFixture() {
        log.warn("Creating fixtures for BidList");
        BidList bidList = new BidList("Account Test", "Type Test", 10);
        bidListService.saveBidList(bidList, "ADMIN");
        BidList bidList2 = new BidList("Account Test 2", "Type Test 2", 20);
        bidListService.saveBidList(bidList2, "ADMIN");
    }

    public void installCurvePointFixture() {
        log.warn("Creating fixtures for CurevePoint");
        curvePointService.saveCurvePoint(new CurvePoint(10, 10d, 30d));
        curvePointService.saveCurvePoint(new CurvePoint(20, 20d, 40d));
    }

    public void installRatingFixture() {
        log.warn("Creating fixtures for Rating");
        ratingService.saveRating(new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10));
        ratingService.saveRating(new Rating("Moodys Rating 2", "Sand PRating 2", "Fitch Rating 2", 20));
    }

    public void installRuleNameFixture() {
        log.warn("Creating fixtures for RuleName");
        ruleNameService.saveRuleName(new RuleName("Rule Name Test", "Description Test", "Json Test", "Template Test", "SQL Test", "SQL Part Test"));
        ruleNameService.saveRuleName(new RuleName("Rule Name Test 2", "Description Test 2", "Json Test 2", "Template Test 2", "SQL Test 2", "SQL Part Test 2"));
    }

    public void installTradeFixture() {
        log.warn("Creating fixtures for Trade");
        tradeService.saveTrade(new Trade("Trade Account Test", "Type Test"));
        tradeService.saveTrade(new Trade("Trade Account Test 2", "Type Test 2"));
    }

}
