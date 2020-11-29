package com.portfolio.twitter.portfolio.service.twitter;

import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwitterService {

    private final Twitter twitter;

    public TwitterService (Twitter twitter) {
        this.twitter = twitter;
    }

    public List<Tweet> getTweetList(String userTwitter) {
        return twitter.timelineOperations().getUserTimeline(userTwitter);
    }
}
