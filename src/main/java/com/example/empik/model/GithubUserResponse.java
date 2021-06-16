package com.example.empik.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class GithubUserResponse {

  long id;
  String login;
  String name;
  String type;

  @JsonProperty("avatar_url")
  String avatarUrl;

  @JsonProperty("created_at")
  Instant createdAt;

  Integer followers;

  @JsonProperty("public_repos")
  Integer publicRepos;


}
