package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.stereotype.Service;

@Service
public class BidListService {

    private final BidListRepository bidListRepository;


    public BidListService(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    public BidList findBidListById(Integer id) {
        return bidListRepository.findById(id).orElse(null);
    }

    public BidList addBidList(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    public BidList updateBidList(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    public void deleteBidList(Integer id) {
        bidListRepository.deleteById(id);
    }
}
