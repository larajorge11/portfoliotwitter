package com.portfolio.twitter.portfolio.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserInfoResponse extends Response {
    private final List<ProfileInformation> profiles;
}
