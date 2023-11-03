package com.besp.likebesp1.mainpage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainpageController {

    @GetMapping("/")
    public String mainpage(){
        return "/mainpage";
    }
}
