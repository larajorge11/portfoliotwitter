package com.portfolio.twitter.portfolio.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class StatusResponse implements Response {
    private final String message;
}
