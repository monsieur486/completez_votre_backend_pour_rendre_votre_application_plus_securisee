package com.nnk.springboot.fixture;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * The type Install bid list fixture.
 */
@Component
@Slf4j
public class InstallBidListFixture {
    private final BidListService bidListService;

    /**
     * Instantiates a new Install bid list fixture.
     *
     * @param bidListService the bid list service
     */
    public InstallBidListFixture(BidListService bidListService) {
        this.bidListService = bidListService;
    }


    /**
     * Execute.
     */
    public void execute() {
        log.warn("Creating fixtures for BidList");
        BidList bidList = new BidList("Account Test", "Type Test", 10);
        bidListService.saveBidList(bidList, "admin");
        BidList bidList2 = new BidList("Account Test 2", "Type Test 2", 20);
        bidListService.saveBidList(bidList2, "admin");
    }

}
