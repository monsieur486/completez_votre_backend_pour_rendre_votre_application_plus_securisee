package com.nnk.springboot.fixture;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class InstallCurvePointFixtureTest {

    @Mock
    CurvePointService curvePointService;

    @InjectMocks
    InstallCurvePointFixture installCurvePointFixture;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute() {
        installCurvePointFixture.execute();
        verify(curvePointService, times(2)).saveCurvePoint(any(CurvePoint.class));
    }
}