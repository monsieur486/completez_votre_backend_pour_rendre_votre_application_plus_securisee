package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
public class BidListController {
    private final BidListService bidListService;

    public BidListController(BidListService bidListService) {
        this.bidListService = bidListService;
    }

    @RequestMapping("/bidList/list")
    public String home(Model model) {
        model.addAttribute("bidLists", bidListService.findAll());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bidlist,
                           BindingResult result,
                           Model model,
                           Principal principal) {

        if (bidlist.getBidQuantity() == null || bidlist.getBidQuantity() <= 1) {
            result.rejectValue("bidQuantity", "", "Bid Quantity must be greater than 1");
        }

        if (result.hasErrors()) {
            return "bidList/add";
        }

        bidListService.saveBidList(bidlist, principal.getName());

        model.addAttribute("bidLists", bidListService.findAll());
        return "bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("bidList", bidListService.findBidListById(id));
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id,
                            @Valid BidList bidList,
                            BindingResult result,
                            Model model,
                            Principal principal) {

        if (bidList.getBidQuantity() == null || bidList.getBidQuantity() <= 1) {
            result.rejectValue("bidQuantity", "", "Bid Quantity must be greater than 1");
        }


        if (result.hasErrors()) {
            return "bidList/update";
        }

        bidListService.updateBidList(bidList, principal.getName());
        model.addAttribute("bidLists", bidListService.findAll());
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        bidListService.deleteBidList(id);
        model.addAttribute("bidLists", bidListService.findAll());
        return "redirect:/bidList/list";
    }
}
