package com.mediscreen.client.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(Model model){
        log.info("load home");
        return "home";
    }
}
