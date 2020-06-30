package com.karthikeyan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Karthikeyan on 01/06/20
 */

@Controller
public class WebController {

    @GetMapping("/")
    public String getHomePage() {
        return "Hello";
    }
}
