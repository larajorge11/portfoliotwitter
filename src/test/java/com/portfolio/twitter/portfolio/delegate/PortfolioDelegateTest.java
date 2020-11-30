package com.portfolio.twitter.portfolio.delegate;

import com.portfolio.twitter.portfolio.domain.*;
import com.portfolio.twitter.portfolio.mapper.PortfolioMapper;
import com.portfolio.twitter.portfolio.mapper.TwitterMapper;
import com.portfolio.twitter.portfolio.model.Portfolio;
import com.portfolio.twitter.portfolio.service.PortfolioService;
import com.portfolio.twitter.portfolio.service.twitter.TwitterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.social.twitter.api.Tweet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PortfolioDelegateTest {

    private final Integer   IDENTIFIER          = 1;
    private final String    DESCRIPTION         = "This is a description";
    private final String    TWITTER_USERNAME    = "GoT_Tyrion";
    private final String    TITLE               = "Tyrion Lannister2";
    private final String    TITTLE              = "Tyrion Lannister2";
    private final String    IMAGE_URL           = "https://pbs.twimg.com/profile_images/668279339838935040/8sUE9d4C_400x400.jpg";

    @Mock
    PortfolioService portfolioService;

    @Mock
    PortfolioMapper portfolioMapper;

    @Mock
    TwitterService twitterService;

    @Spy
    TwitterMapper twitterMapper;

    @InjectMocks
    private PortfolioDelegate portfolioDelegate;

    @Test
    public void getPortfolioById(){
        Portfolio portfolio = buildPortfolio(TWITTER_USERNAME, TITLE, TITTLE,IMAGE_URL, IDENTIFIER, DESCRIPTION);
        ProfileInformation profileInformation = buildProfileInformation(IDENTIFIER, DESCRIPTION, TWITTER_USERNAME, TITLE, IMAGE_URL);
        List<Tweet> tweets = buildTweetList();

        when(portfolioService.findById(anyInt())).thenReturn(portfolio);
        when(portfolioMapper.mapProfile(any(Portfolio.class))).thenReturn(profileInformation);
        when(twitterService.getTweetList(anyString())).thenReturn(tweets);

        Response response = portfolioDelegate.getPortfolioById(IDENTIFIER);
        assertThat(((ProfileResponse)response).getProfileInfo().getIdPortfolio(), is(1));
        assertThat(((ProfileResponse)response).getProfileInfo().getDescription(), is("This is a description"));
        assertThat(((ProfileResponse)response).getProfileInfo().getImageUrl(), is("https://pbs.twimg.com/profile_images/668279339838935040/8sUE9d4C_400x400.jpg"));
        assertThat(((ProfileResponse)response).getProfileInfo().getTwitterUsername(), is("GoT_Tyrion"));
        assertThat(((ProfileResponse)response).getProfileInfo().getTitle(), is("Tyrion Lannister2"));
        assertThat(((ProfileResponse)response).getTweets().size(), is(5));
    }

    @Test
    public void getPortfolios() {
        List<Portfolio> portfolios = buildPortfolios();
        ProfileInformation profileInformation = buildProfileInformation(IDENTIFIER, DESCRIPTION, TWITTER_USERNAME, TITLE, IMAGE_URL);

        when(portfolioService.findAll()).thenReturn(portfolios);
        when(portfolioMapper.mapProfile(any(Portfolio.class))).thenReturn(profileInformation);

        Response response = portfolioDelegate.getPortfolios();

        assertThat(((UserInfoResponse)response).getProfiles().size(), is(5));
    }

    @Test
    public void updatePortfolio() {
        ProfileRequest profileRequest = buildProfileRequest(TWITTER_USERNAME, TITLE, TITTLE,IMAGE_URL, DESCRIPTION);
        Portfolio portfolio = buildPortfolio(TWITTER_USERNAME, TITLE, TITTLE,IMAGE_URL, IDENTIFIER, DESCRIPTION);

        when(portfolioService.findById(anyInt())).thenReturn(portfolio);
        when(portfolioMapper.mapProfileEntity(any(Portfolio.class), any(ProfileRequest.class))).thenReturn(portfolio);
        when(portfolioService.save(any(Portfolio.class))).thenReturn(portfolio);

        String response = portfolioDelegate.updatePortfolio(IDENTIFIER, profileRequest);

        assertThat(response, is("Profile updated succesfully!"));

    }

    private ProfileRequest buildProfileRequest(String twitterUsername, String tittle,
                                               String title, String imageUrl, String description) {
        return ProfileRequest.builder()
                .twitterUsername(twitterUsername)
                .description(description)
                .imageUrl(imageUrl)
                .tittle(tittle)
                .title(title)
                .build();
    }

    private List<Portfolio> buildPortfolios() {
        return new ArrayList<>(Arrays.asList(
                buildPortfolio(TWITTER_USERNAME, TITLE, TITTLE,IMAGE_URL, IDENTIFIER, DESCRIPTION),
                buildPortfolio(TWITTER_USERNAME, TITLE, TITTLE,IMAGE_URL, IDENTIFIER, DESCRIPTION),
                buildPortfolio(TWITTER_USERNAME, TITLE, TITTLE,IMAGE_URL, IDENTIFIER, DESCRIPTION),
                buildPortfolio(TWITTER_USERNAME, TITLE, TITTLE,IMAGE_URL, IDENTIFIER, DESCRIPTION),
                buildPortfolio(TWITTER_USERNAME, TITLE, TITTLE,IMAGE_URL, IDENTIFIER, DESCRIPTION)
        ));
    }

    private Portfolio buildPortfolio(String twitterUsername, String tittle,
                                     String title, String imageUrl, Integer id, String description) {
        Portfolio portfolio = new Portfolio();
        portfolio.setTwitterUserName(twitterUsername);
        portfolio.setTittle(tittle);
        portfolio.setTitle(title);
        portfolio.setImageUrl(imageUrl);
        portfolio.setId(id);
        portfolio.setDescription(description);

        return portfolio;
    }

    private ProfileInformation buildProfileInformation(Integer id, String description,
                                                       String twitterUserName, String title, String imageUrl) {
        return ProfileInformation.builder()
                .idPortfolio(id)
                .description(description)
                .twitterUsername(twitterUserName)
                .title(title)
                .imageUrl(imageUrl)
                .build();
    }

    private List<Tweet> buildTweetList() {
        return new ArrayList<>(Arrays.asList(
                buildTweet(),
                buildTweet(),
                buildTweet(),
                buildTweet(),
                buildTweet()
        ));
    }

    private Tweet buildTweet() {
        return new Tweet(123456, "Who did this?",
                new Date(), "GoT_Tyrion",
                "http://pbs.twimg.com/profile_images/668279339838935040/8sUE9d4C_normal.jpg",
                null, 987654, "en",
                "<a href=\\\"http://twitter.com/download/iphone\\\" rel=\\\"nofollow\\\">Twitter for iPhone</a>");
    }

    private TwitterInformation buildTwitterInformation() {
        return TwitterInformation.builder()
                .createdAt(new Date())
                .fromUser("GoT_Tyrion")
                .fromUserId(987654)
                .id(123456)
                .inReplyToScreenName(null)
                .inReplyToStatusId(null)
                .inReplyToUserId(null)
                .languageCode("en")
                .profileImageUrl("http://pbs.twimg.com/profile_images/668279339838935040/8sUE9d4C_normal.jpg")
                .retweetCount(560)
                .source("<a href=\\\"http://twitter.com/download/iphone\\\" rel=\\\"nofollow\\\">Twitter for iPhone</a>")
                .text("Who did this?")
                .toUserId(null)
                .build();
    }

}
