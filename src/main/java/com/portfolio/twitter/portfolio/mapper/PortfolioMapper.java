package com.portfolio.twitter.portfolio.mapper;

import com.portfolio.twitter.portfolio.domain.ProfileInformation;
import com.portfolio.twitter.portfolio.model.Portfolio;
import org.springframework.stereotype.Component;

@Component
public class PortfolioMapper {

    public ProfileInformation mapProfile(Portfolio portfolio) {
        return ProfileInformation.builder()
                .idPortfolio(portfolio.getId())
                .description(portfolio.getDescription())
                .twitterUsername(portfolio.getTwitterUserName())
                .title(portfolio.getTitle())
                .imageUrl(portfolio.getImageUrl())
                .build();
    }
}
