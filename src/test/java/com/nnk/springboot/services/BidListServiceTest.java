package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class BidListServiceTest {

    @Mock
    BidListRepository bidListRepository;

    @InjectMocks
    BidListService bidListService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findBidListById() {
        BidList bidList = new BidList();
        bidList.setBidListId(1);
        when(bidListRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(bidList));

        BidList result = bidListService.findBidListById(1);

        assertEquals(1, result.getBidListId());
        verify(bidListRepository, times(1)).findById(any(Integer.class));
    }

    @Test
    void updateBidList() {
        BidList bidList = new BidList();
        bidListService.updateBidList(bidList, "revisionName");
        verify(bidListRepository, times(1)).save(any(BidList.class));
    }

    @Test
    void deleteBidList() {
        bidListService.deleteBidList(1);
        verify(bidListRepository, times(1)).deleteById(any(Integer.class));
    }

    @Test
    void findAll() {
        when(bidListRepository.findAll()).thenReturn(Collections.emptyList());

        List<BidList> result = bidListService.findAll();

        assertEquals(0, result.size());
        verify(bidListRepository, times(1)).findAll();
    }

    @Test
    void saveBidList() {
        BidList bidList = new BidList();
        bidListService.saveBidList(bidList, "creationName");
        verify(bidListRepository, times(1)).save(any(BidList.class));
    }
}