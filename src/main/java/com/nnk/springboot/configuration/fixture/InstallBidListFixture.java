package com.nnk.springboot.configuration.fixture;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InstallBidListFixture {
    private final BidListService bidListService;

    public InstallBidListFixture(BidListService bidListService) {
        this.bidListService = bidListService;
    }


    public void execute() {
        log.warn("Creating fixtures for BidList");
        BidList bidList = new BidList("Account Test", "Type Test", 10);
        bidListService.saveBidList(bidList, "admin");
        BidList bidList2 = new BidList("Account Test 2", "Type Test 2", 20);
        bidListService.saveBidList(bidList2, "admin");
    }

}
