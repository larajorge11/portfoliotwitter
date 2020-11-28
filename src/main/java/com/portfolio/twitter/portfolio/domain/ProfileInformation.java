package com.portfolio.twitter.portfolio.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileInformation {
    private String description;
    private String imageUrl;
    private String twitterUsername;
    private String title;
}
