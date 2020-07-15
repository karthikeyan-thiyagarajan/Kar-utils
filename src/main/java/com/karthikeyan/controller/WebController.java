package com.karthikeyan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Karthikeyan on 01/06/20
 */

@Controller
public class WebController {

    @GetMapping("/")
    public String getLogin() {
        return "redirect:/login";
    }

    @GetMapping("/dash")
    public String getHomePage() {
        return "Dashboard";
    }

    @GetMapping(value = "/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "index";
    }

}
