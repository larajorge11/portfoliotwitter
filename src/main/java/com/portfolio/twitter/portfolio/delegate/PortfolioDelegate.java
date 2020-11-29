package com.portfolio.twitter.portfolio.delegate;

import com.portfolio.twitter.portfolio.domain.ProfileInformation;
import com.portfolio.twitter.portfolio.domain.ProfileResponse;
import com.portfolio.twitter.portfolio.domain.TwitterInformation;
import com.portfolio.twitter.portfolio.mapper.PortfolioMapper;
import com.portfolio.twitter.portfolio.mapper.TwitterMapper;
import com.portfolio.twitter.portfolio.service.PortfolioService;
import com.portfolio.twitter.portfolio.service.twitter.TwitterService;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.portfolio.twitter.portfolio.utils.Constants.TWEETS_LIMIT;

@Component
public class PortfolioDelegate {

    private final PortfolioService portfolioService;
    private final TwitterService twitterService;
    private final PortfolioMapper portfolioMapper;
    private final TwitterMapper twitterMapper;

    public PortfolioDelegate(PortfolioService portfolioService, TwitterService twitterService,
                             PortfolioMapper portfolioMapper, TwitterMapper twitterMapper) {
        this.portfolioService = portfolioService;
        this.twitterService = twitterService;
        this.portfolioMapper = portfolioMapper;
        this.twitterMapper = twitterMapper;
    }

    public ProfileResponse getPortfolioById(Integer id) {
        return getTwitterInformationService(Optional.of(portfolioService.findById(id))
                .map(portfolioMapper::mapProfile)
                .orElse(null));
    }

    private ProfileResponse getTwitterInformationService(ProfileInformation profileInformation) {

        List<TwitterInformation> twitterInformationList =
                twitterService.getTweetList(profileInformation.getTwitterUsername())
                .stream()
                .map(twitterMapper::mapTweet)
                .sorted(Comparator.comparing(TwitterInformation::getCreatedAt).reversed())
                .limit(TWEETS_LIMIT)
                .collect(Collectors.toList());

        return ProfileResponse.builder()
                .profileInfo(profileInformation)
                .tweets(twitterInformationList)
                .build();
    }
}
