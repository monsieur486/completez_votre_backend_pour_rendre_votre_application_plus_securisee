package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
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

class BidListControllerTest {
    @Mock
    BidListService bidListService;

    @Mock
    Model model;

    @Mock
    BindingResult bindingResult;

    @Mock
    Principal principal;

    @InjectMocks
    BidListController bidListController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void home() {
        when(bidListService.findAll()).thenReturn(Collections.emptyList());
        String result = bidListController.home(model);
        assertEquals("bidList/list", result);
        verify(bidListService, times(1)).findAll();
    }

    @Test
    void addBidForm() {
        BidList bid = new BidList();
        String result = bidListController.addBidForm(bid);
        assertEquals("bidList/add", result);
    }

    @Test
    void validate() {
        BidList bidList = new BidList();
        when(bindingResult.hasErrors()).thenReturn(false);
        String result = bidListController.validate(bidList, bindingResult, model, principal);
        assertEquals("bidList/list", result);
    }

    @Test
    void showUpdateForm() {
        BidList bidList = new BidList();
        when(bidListService.findBidListById(any(Integer.class))).thenReturn(bidList);
        String result = bidListController.showUpdateForm(1, model);
        assertEquals("bidList/update", result);
        verify(bidListService, times(1)).findBidListById(any(Integer.class));
    }

    @Test
    void updateBid() {
        BidList bidList = new BidList();
        when(bindingResult.hasErrors()).thenReturn(false);
        String result = bidListController.updateBid(1, bidList, bindingResult, model, principal);
        assertEquals("redirect:/bidList/list", result);
    }

    @Test
    void deleteBid() {
        String result = bidListController.deleteBid(1, model);
        assertEquals("redirect:/bidList/list", result);
        verify(bidListService, times(1)).deleteBidList(any(Integer.class));
    }

}