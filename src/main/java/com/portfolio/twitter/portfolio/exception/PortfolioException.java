package com.portfolio.twitter.portfolio.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PortfolioException extends Exception {
    public PortfolioException(String message) {
        super(message);
    }
}
