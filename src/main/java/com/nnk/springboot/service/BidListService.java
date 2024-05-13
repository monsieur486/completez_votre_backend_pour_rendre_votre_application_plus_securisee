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


    public List<BidList> findBidListByAccount(String account) {
        return bidListRepository.findBidListByAccount(account);
    }

    public BidList findBidListById(Integer id) {
        return bidListRepository.findById(id).orElse(null);
    }

    public BidList addBidList(BidList bidList, String username) {
        bidList.setCreationName(username);
        return bidListRepository.save(bidList);
    }

    public BidList updateBidList(BidList bidList, String username) {
        bidList.setRevisionName(username);
        return bidListRepository.save(bidList);
    }

    public void deleteBidList(Integer id) {
        bidListRepository.deleteById(id);
    }

    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }
}
