package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * The interface Bid list repository.
 */
@Repository
public interface BidListRepository extends JpaRepository<BidList, Integer> {

    /**
     * Find bid list by account list.
     *
     * @param account the account
     * @return the list
     */
    List<BidList> findBidListByAccount(String account);

}
