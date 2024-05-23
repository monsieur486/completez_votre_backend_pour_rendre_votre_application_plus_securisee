package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Curve point service.
 */
@Service
public class CurvePointService {

    private final CurvePointRepository curvePointRepository;

    /**
     * Instantiates a new Curve point service.
     *
     * @param curvePointRepository the curve point repository
     */
    public CurvePointService(CurvePointRepository curvePointRepository) {
        this.curvePointRepository = curvePointRepository;
    }

    /**
     * Save curve point.
     *
     * @param curvePoint the curve point
     */
    public void saveCurvePoint(CurvePoint curvePoint) {
        curvePointRepository.save(curvePoint);
    }

    /**
     * Find curve point by id curve point.
     *
     * @param id the id
     * @return the curve point
     */
    public CurvePoint findCurvePointById(Integer id) {
        return curvePointRepository.findById(id).orElse(null);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<CurvePoint> findAll() {
        return curvePointRepository.findAll();
    }

    /**
     * Delete curve point by id.
     *
     * @param id the id
     */
    public void deleteCurvePointById(Integer id) {
        curvePointRepository.deleteById(id);
    }

    /**
     * Exists by id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean existsById(Integer id) {
        return curvePointRepository.existsById(id);
    }

    /**
     * Update curve point.
     *
     * @param curvePoint the curve point
     */
    public void updateCurvePoint(CurvePoint curvePoint) {
        curvePointRepository.save(curvePoint);
    }
}
