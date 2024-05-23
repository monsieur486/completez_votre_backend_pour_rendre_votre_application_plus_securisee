package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
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
 * The type Trade controller.
 */
@Controller
public class TradeController {
    private final TradeService tradeService;

    /**
     * Instantiates a new Trade controller.
     *
     * @param tradeService the trade service
     */
    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    /**
     * Home string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/trade/list")
    public String home(Model model) {
        model.addAttribute("trades", tradeService.findAllTrades());
        return "trade/list";
    }

    /**
     * Add user string.
     *
     * @param bid the bid
     * @return the string
     */
    @GetMapping("/trade/add")
    @PreAuthorize("hasAuthority('ADD_PRIVILEGE')")
    public String addUser(Trade bid) {
        return "trade/add";
    }

    /**
     * Validate string.
     *
     * @param trade     the trade
     * @param result    the result
     * @param model     the model
     * @param principal the principal
     * @return the string
     */
    @PostMapping("/trade/validate")
    @PreAuthorize("hasAuthority('ADD_PRIVILEGE')")
    public String validate(@Valid Trade trade, BindingResult result, Model model, Principal principal) {

        if (result.hasErrors()) {
            return "trade/add";
        }

        tradeService.saveTrade(trade, principal.getName());
        model.addAttribute("trades", tradeService.findAllTrades());

        return "trade/list";
    }

    /**
     * Show update form string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/trade/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.findTradeById(id);
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    /**
     * Update trade string.
     *
     * @param id        the id
     * @param trade     the trade
     * @param result    the result
     * @param model     the model
     * @param principal the principal
     * @return the string
     */
    @PostMapping("/trade/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                              BindingResult result, Model model, Principal principal) {

        if (result.hasErrors()) {
            return "trade/update";
        }

        trade.setTradeId(id);
        tradeService.updateTrade(trade, principal.getName());
        model.addAttribute("trades", tradeService.findAllTrades());

        return "redirect:/trade/list";
    }

    /**
     * Delete trade string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/trade/delete/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        tradeService.deleteTradeById(id);
        return "redirect:/trade/list";
    }
}
