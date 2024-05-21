package com.nnk.springboot.configuration.fixture;

import com.nnk.springboot.domain.*;
import com.nnk.springboot.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InstallCurvePointFixture {
    private final CurvePointService curvePointService;

    public InstallCurvePointFixture(CurvePointService curvePointService) {
        this.curvePointService = curvePointService;
    }

    public void execute() {
        log.warn("Creating fixtures for CurevePoint");
        curvePointService.saveCurvePoint(new CurvePoint(10, 10d, 30d));
        curvePointService.saveCurvePoint(new CurvePoint(20, 20d, 40d));
    }
}
