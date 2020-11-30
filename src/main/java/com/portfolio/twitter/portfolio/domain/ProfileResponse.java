package com.portfolio.twitter.portfolio.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ProfileResponse extends Response {
    private ProfileInformation profileInfo;
    private List<TwitterInformation> tweets;
}
