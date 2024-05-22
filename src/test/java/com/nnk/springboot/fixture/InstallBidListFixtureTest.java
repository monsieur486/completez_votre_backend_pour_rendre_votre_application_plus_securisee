package com.nnk.springboot.fixture;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class InstallBidListFixtureTest {

    @Mock
    BidListService bidListService;

    @InjectMocks
    InstallBidListFixture installBidListFixture;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute() {
        installBidListFixture.execute();

        verify(bidListService, times(2)).saveBidList(any(BidList.class), eq("admin"));

    }
}
