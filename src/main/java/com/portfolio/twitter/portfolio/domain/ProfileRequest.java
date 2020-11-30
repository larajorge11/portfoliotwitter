package com.portfolio.twitter.portfolio.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProfileRequest {
    private String description;
    private String imageUrl;
    private String twitterUsername;
    private String title;
    private String tittle;
}
