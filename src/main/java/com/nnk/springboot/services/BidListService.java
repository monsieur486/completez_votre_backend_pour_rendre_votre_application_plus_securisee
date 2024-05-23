package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Bid list service.
 */
@Service
public class BidListService {

    private final BidListRepository bidListRepository;

    /**
     * Instantiates a new Bid list service.
     *
     * @param bidListRepository the bid list repository
     */
    public BidListService(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    /**
     * Find bid list by id bid list.
     *
     * @param id the id
     * @return the bid list
     */
    public BidList findBidListById(Integer id) {
        return bidListRepository.findById(id).orElse(null);
    }

    /**
     * Update bid list.
     *
     * @param bidList      the bid list
     * @param revisionName the revision name
     */
    public void updateBidList(BidList bidList, String revisionName) {
        bidList.setRevisionName(revisionName);
        bidList.setRevisionDate(new java.sql.Timestamp(System.currentTimeMillis()));
        bidListRepository.save(bidList);
    }

    /**
     * Delete bid list.
     *
     * @param id the id
     */
    public void deleteBidList(Integer id) {
        bidListRepository.deleteById(id);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    /**
     * Save bid list.
     *
     * @param bidList      the bid list
     * @param creationName the creation name
     */
    public void saveBidList(BidList bidList, String creationName) {
        bidList.setCreationName(creationName);
        bidList.setCreationDate(new java.sql.Timestamp(System.currentTimeMillis()));
        bidListRepository.save(bidList);
    }
}
