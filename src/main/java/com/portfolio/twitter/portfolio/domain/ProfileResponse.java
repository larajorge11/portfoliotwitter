package com.portfolio.twitter.portfolio.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ProfileResponse {
    private ProfileInformation profileInfo;
    private List<TwitterInformation> tweets;
}
