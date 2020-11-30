package com.portfolio.twitter.portfolio.service;

import com.portfolio.twitter.portfolio.service.twitter.TwitterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.social.twitter.api.TimelineOperations;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TwitterServiceTest {

    private final String    TWITTER_USERNAME    = "GoT_Tyrion";

    @Mock
    Twitter twitter;

    @Mock
    private TimelineOperations timelineOperations;

    @InjectMocks
    private TwitterService twitterService;

    @Test
    public void getTweetList() {
        when(twitter.timelineOperations()).thenReturn(timelineOperations);
        when(timelineOperations.getUserTimeline(anyString())).thenReturn(buildTweetList());
        List<Tweet> tweetList = twitterService.getTweetList(TWITTER_USERNAME);
        assertThat(tweetList.size(), is(5));
        assertThat(tweetList.get(0).getId(), is(123456L));
        assertThat(tweetList.get(0).getText(), is("Who did this?"));
        assertThat(tweetList.get(0).getFromUser(), is("GoT_Tyrion"));
        assertThat(tweetList.get(0).getProfileImageUrl(), is("http://pbs.twimg.com/profile_images/668279339838935040/8sUE9d4C_normal.jpg"));
        assertThat(tweetList.get(0).getSource(), is("<a href=\\\"http://twitter.com/download/iphone\\\" rel=\\\"nofollow\\\">Twitter for iPhone</a>"));
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
}
