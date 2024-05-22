package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidListService {

    private final BidListRepository bidListRepository;

    public BidListService(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    public BidList findBidListById(Integer id) {
        return bidListRepository.findById(id).orElse(null);
    }

    public void updateBidList(BidList bidList, String revisionName) {
        bidList.setRevisionName(revisionName);
        bidList.setRevisionDate(new java.sql.Timestamp(System.currentTimeMillis()));
        bidListRepository.save(bidList);
    }

    public void deleteBidList(Integer id) {
        bidListRepository.deleteById(id);
    }

    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    public void saveBidList(BidList bidList, String creationName) {
        bidList.setCreationName(creationName);
        bidList.setCreationDate(new java.sql.Timestamp(System.currentTimeMillis()));
        bidListRepository.save(bidList);
    }
}
