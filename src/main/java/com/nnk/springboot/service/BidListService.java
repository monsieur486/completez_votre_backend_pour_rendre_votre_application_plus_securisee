package com.nnk.springboot.service;

import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;

@Service
public class BidListService {

    private BidListRepository bidListRepository;


    public BidListService(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    public BidList findBidListById(Integer id){
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
