package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
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

class CurvePointServiceTest {
    @Mock
    CurvePointRepository curvePointRepository;

    @InjectMocks
    CurvePointService curvePointService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCurvePoint() {
        CurvePoint curvePoint = new CurvePoint();
        curvePointService.saveCurvePoint(curvePoint);
        verify(curvePointRepository, times(1)).save(any(CurvePoint.class));
    }

    @Test
    void findCurvePointById() {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        when(curvePointRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(curvePoint));

        CurvePoint result = curvePointService.findCurvePointById(1);

        assertEquals(1, result.getId());
        verify(curvePointRepository, times(1)).findById(any(Integer.class));
    }

    @Test
    void findCurvePointById_notFound() {
        when(curvePointRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.empty());

        CurvePoint result = curvePointService.findCurvePointById(1);

        assertNull(result);
        verify(curvePointRepository, times(1)).findById(any(Integer.class));
    }

    @Test
    void findAllCurvePoints() {
        when(curvePointRepository.findAll()).thenReturn(Collections.emptyList());

        Iterable<CurvePoint> result = curvePointService.findAllCurvePoints();

        assertEquals(0, ((List<CurvePoint>) result).size());
        verify(curvePointRepository, times(1)).findAll();
    }

    @Test
    void deleteCurvePointById() {
        curvePointService.deleteCurvePointById(1);
        verify(curvePointRepository, times(1)).deleteById(any(Integer.class));
    }

    @Test
    void existsById() {
        when(curvePointRepository.existsById(any(Integer.class))).thenReturn(true);

        boolean result = curvePointService.existsById(1);

        assertTrue(result);
        verify(curvePointRepository, times(1)).existsById(any(Integer.class));
    }

    @Test
    void updateCurvePoint() {
        CurvePoint curvePoint = new CurvePoint();
        curvePointService.updateCurvePoint(curvePoint);
        verify(curvePointRepository, times(1)).save(any(CurvePoint.class));
    }

}