package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Home controller.
 */
@Controller
public class HomeController {
    /**
     * Home string.
     *
     * @return the string
     */
    @RequestMapping("/")
    public String home() {
        return "home";
    }

    /**
     * Admin home string.
     *
     * @return the string
     */
    @RequestMapping("/admin/home")
    public String adminHome() {
        return "redirect:/bidList/list";
    }


}
