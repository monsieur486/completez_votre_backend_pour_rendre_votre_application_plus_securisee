package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.stereotype.Service;

@Service
public class CurvePointService {

    private final CurvePointRepository curvePointRepository;

    public CurvePointService(CurvePointRepository curvePointRepository) {
        this.curvePointRepository = curvePointRepository;
    }

    public void saveCurvePoint(CurvePoint curvePoint) {
        curvePointRepository.save(curvePoint);
    }

    public CurvePoint findCurvePointById(Integer id) {
        return curvePointRepository.findById(id).orElse(null);
    }

    public Iterable<CurvePoint> findAllCurvePoints() {
        return curvePointRepository.findAll();
    }

    public void deleteCurvePointById(Integer id) {
        curvePointRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return curvePointRepository.existsById(id);
    }

    public void updateCurvePoint(CurvePoint curvePoint) {
        curvePointRepository.save(curvePoint);
    }
}
