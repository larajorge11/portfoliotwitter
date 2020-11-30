package com.portfolio.twitter.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping({"", "/", "index", "index.html"})
    public String index() {
        return "index";
    }

    @RequestMapping({"profile", "profile.html"})
    public String profile() {
        return "profile";
    }
}
