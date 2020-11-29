package com.portfolio.twitter.portfolio.controller;

import com.portfolio.twitter.portfolio.delegate.PortfolioDelegate;
import com.portfolio.twitter.portfolio.domain.ProfileInformation;
import com.portfolio.twitter.portfolio.domain.ProfileResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class PortfolioController {

    private final PortfolioDelegate portfolioDelegate;

    public PortfolioController(PortfolioDelegate portfolioDelegate) {
        this.portfolioDelegate = portfolioDelegate;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> getInformation(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(portfolioDelegate.getPortfolioById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modifyUserInfo(@PathVariable("id") Integer id,
                                                          @RequestBody String request) {
        return null;
    }
}
