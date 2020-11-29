package com.portfolio.twitter.portfolio.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequest {
    private Integer idPortfolio;
    private String description;
    private String imageUrl;
    private String twitterUsername;
    private String title;
}
