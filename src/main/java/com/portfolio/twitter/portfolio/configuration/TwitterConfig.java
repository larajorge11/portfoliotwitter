package com.portfolio.twitter.portfolio.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@Configuration
public class TwitterConfig {
    @Value("${twitter.apiKey}")
    private String consumerKey;

    @Value("${twitter.apiSecretKey}")
    private String consumerSecret;

    @Value("${twitter.accessToken}")
    private String accessToken;

    @Value("${twitter.accessTokenSecret}")
    private String accessTokenSecret;

    @Bean
    TwitterTemplate getTwtTemplate(){
        return new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
    }
}
