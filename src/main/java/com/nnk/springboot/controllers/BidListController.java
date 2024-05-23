package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


/**
 * The type Bid list controller.
 */
@Controller
public class BidListController {
    private final BidListService bidListService;

    /**
     * Instantiates a new Bid list controller.
     *
     * @param bidListService the bid list service
     */
    public BidListController(BidListService bidListService) {
        this.bidListService = bidListService;
    }

    /**
     * Home string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/bidList/list")
    public String home(Model model) {
        model.addAttribute("bidLists", bidListService.findAll());
        return "bidList/list";
    }

    /**
     * Add bid form string.
     *
     * @param bid the bid
     * @return the string
     */
    @GetMapping("/bidList/add")
    @PreAuthorize("hasAuthority('ADD_PRIVILEGE')")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    /**
     * Validate string.
     *
     * @param bidlist   the bidlist
     * @param result    the result
     * @param model     the model
     * @param principal the principal
     * @return the string
     */
    @PostMapping("/bidList/validate")
    @PreAuthorize("hasAuthority('ADD_PRIVILEGE')")
    public String validate(@Valid BidList bidlist,
                           BindingResult result,
                           Model model,
                           Principal principal) {

        if (result.hasErrors()) {
            return "bidList/add";
        }

        bidListService.saveBidList(bidlist, principal.getName());

        model.addAttribute("bidLists", bidListService.findAll());
        return "bidList/list";
    }

    /**
     * Show update form string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/bidList/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("bidList", bidListService.findBidListById(id));
        return "bidList/update";
    }

    /**
     * Update bid string.
     *
     * @param id        the id
     * @param bidList   the bid list
     * @param result    the result
     * @param model     the model
     * @param principal the principal
     * @return the string
     */
    @PostMapping("/bidList/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
    public String updateBid(@PathVariable("id") Integer id,
                            @Valid BidList bidList,
                            BindingResult result,
                            Model model,
                            Principal principal) {

        if (result.hasErrors()) {
            return "bidList/update";
        }

        bidListService.updateBidList(bidList, principal.getName());
        model.addAttribute("bidLists", bidListService.findAll());
        return "redirect:/bidList/list";
    }

    /**
     * Delete bid string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/bidList/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE_PRIVILEGE')")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        bidListService.deleteBidList(id);
        model.addAttribute("bidLists", bidListService.findAll());
        return "redirect:/bidList/list";
    }
}
