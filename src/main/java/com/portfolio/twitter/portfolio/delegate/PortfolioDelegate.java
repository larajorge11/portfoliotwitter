package com.portfolio.twitter.portfolio.delegate;

import com.portfolio.twitter.portfolio.domain.*;
import com.portfolio.twitter.portfolio.mapper.PortfolioMapper;
import com.portfolio.twitter.portfolio.mapper.TwitterMapper;
import com.portfolio.twitter.portfolio.service.PortfolioService;
import com.portfolio.twitter.portfolio.service.twitter.TwitterService;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.portfolio.twitter.portfolio.utils.Constants.*;

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

    public Response getPortfolioById(Integer id) {
        return getTwitterInformationService(Optional.ofNullable(portfolioService.findById(id))
                .map(portfolioMapper::mapProfile)
                .orElse(null));
    }

    public Response getPortfolios() {
        List<ProfileInformation> profiles = portfolioService.findAll()
                .stream()
                .map(portfolioMapper::mapProfile)
                .collect(Collectors.toList());

        return new UserInfoResponse(profiles);
    }

    public String updatePortfolio(Integer id, ProfileRequest profileRequest) {
        return Optional.ofNullable(portfolioService.findById(id))
                .map(x -> portfolioMapper.mapProfileEntity(x, profileRequest))
                .map(x -> portfolioService.save(x))
                .map(x -> MESSAGE_SUCCESS)
                .orElse(PROFILE_NOT_FOUND);
    }

    private ProfileResponse getTwitterInformationService(ProfileInformation profileInformation) {
        ProfileResponse profileResponse = null;
        Optional<ProfileInformation> optionalProfileInformation = Optional.ofNullable(profileInformation);
        if (optionalProfileInformation.isPresent()) {
            List<TwitterInformation> twitterInformationList =
                    twitterService.getTweetList(profileInformation.getTwitterUsername())
                            .stream()
                            .map(twitterMapper::mapTweet)
                            .sorted(Comparator.comparing(TwitterInformation::getCreatedAt).reversed())
                            .limit(TWEETS_LIMIT)
                            .collect(Collectors.toList());

            profileResponse = ProfileResponse.builder()
                    .profileInfo(profileInformation)
                    .tweets(twitterInformationList)
                    .build();
        }
        return profileResponse;
    }
}
