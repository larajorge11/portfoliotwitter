package com.portfolio.twitter.portfolio.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProfileInformation {
    private Integer idPortfolio;
    private String description;
    private String imageUrl;
    private String twitterUsername;
    private String title;
}
