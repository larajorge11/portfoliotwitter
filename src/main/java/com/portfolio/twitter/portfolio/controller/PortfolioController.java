package com.portfolio.twitter.portfolio.controller;

import com.portfolio.twitter.portfolio.delegate.PortfolioDelegate;
import com.portfolio.twitter.portfolio.domain.ProfileRequest;
import com.portfolio.twitter.portfolio.domain.Response;
import com.portfolio.twitter.portfolio.domain.StatusResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.portfolio.twitter.portfolio.utils.Constants.PROFILE_NOT_FOUND;

@Controller
@RequestMapping("/profile")
public class PortfolioController {

    private final PortfolioDelegate portfolioDelegate;

    public PortfolioController(PortfolioDelegate portfolioDelegate) {
        this.portfolioDelegate = portfolioDelegate;
    }

    @GetMapping
    public ResponseEntity<Response> getAllProfiles() {
        return ResponseEntity.ok(portfolioDelegate.getPortfolios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getProfile(@PathVariable("id") Integer id) {
        return Optional.ofNullable(portfolioDelegate.getPortfolioById(id))
                .map(x -> ResponseEntity.ok(x))
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new StatusResponse(PROFILE_NOT_FOUND)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusResponse> updateProfileInformation(@PathVariable("id") Integer id,
                                                          @RequestBody ProfileRequest request) {
        return Optional.of(portfolioDelegate.updatePortfolio(id, request))
                .map(x -> ResponseEntity.ok(new StatusResponse(x)))
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new StatusResponse(PROFILE_NOT_FOUND)));
    }
}
