package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CurveControllerTest {
    @Mock
    CurvePointService curvePointService;

    @Mock
    Model model;

    @Mock
    BindingResult bindingResult;

    @InjectMocks
    CurveController curveController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void home() {
        when(curvePointService.findAll()).thenReturn(Collections.emptyList());
        String result = curveController.home(model);
        assertEquals("curvePoint/list", result);
        verify(curvePointService, times(1)).findAll();
    }

    @Test
    void addBidForm() {
        CurvePoint curvePoint = new CurvePoint();
        String result = curveController.addBidForm(curvePoint);
        assertEquals("curvePoint/add", result);
    }

    @Test
    void validate() {
        CurvePoint curvePoint = new CurvePoint();
        when(bindingResult.hasErrors()).thenReturn(false);
        String result = curveController.validate(curvePoint, bindingResult, model);
        assertEquals("curvePoint/list", result);
    }

    @Test
    void validate_withErrors() {
        CurvePoint curvePoint = new CurvePoint();
        when(bindingResult.hasErrors()).thenReturn(true);
        String result = curveController.validate(curvePoint, bindingResult, model);
        assertEquals("curvePoint/add", result);
    }

    @Test
    void showUpdateForm() {
        CurvePoint curvePoint = new CurvePoint();
        when(curvePointService.findCurvePointById(anyInt())).thenReturn(curvePoint);
        String result = curveController.showUpdateForm(1, model);
        assertEquals("curvePoint/update", result);
        verify(curvePointService, times(1)).findCurvePointById(1);
    }

    @Test
    void updateBid() {
        CurvePoint curvePoint = new CurvePoint();
        when(bindingResult.hasErrors()).thenReturn(false);
        String result = curveController.updateBid(1, curvePoint, bindingResult, model);
        assertEquals("redirect:/curvePoint/list", result);
        verify(curvePointService, times(1)).updateCurvePoint(curvePoint);
    }

    @Test
    void deleteBid() {
        String result = curveController.deleteBid(1, model);
        assertEquals("redirect:/curvePoint/list", result);
        verify(curvePointService, times(1)).deleteCurvePointById(1);
    }

    @Test
    void deleteBid_notFound() {
        String result = curveController.deleteBid(1, model);
        assertEquals("redirect:/curvePoint/list", result);
        verify(curvePointService, times(1)).deleteCurvePointById(1);
    }

}