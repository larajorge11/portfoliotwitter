package com.portfolio.twitter.portfolio.mapper;

import com.portfolio.twitter.portfolio.domain.TwitterInformation;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Component;

@Component
public class TwitterMapper {

    public TwitterInformation mapTweet(Tweet tweet) {
        return TwitterInformation.builder()
                .createdAt(tweet.getCreatedAt())
                .fromUser(tweet.getFromUser())
                .fromUserId(tweet.getFromUserId())
                .id(tweet.getId())
                .inReplyToScreenName(tweet.getInReplyToScreenName())
                .inReplyToStatusId(tweet.getInReplyToStatusId())
                .inReplyToUserId(tweet.getInReplyToUserId())
                .languageCode(tweet.getLanguageCode())
                .profileImageUrl(tweet.getProfileImageUrl())
                .retweetCount(tweet.getRetweetCount())
                .source(tweet.getSource())
                .text(tweet.getText())
                .toUserId(tweet.getToUserId())
                .build();
    }
}
