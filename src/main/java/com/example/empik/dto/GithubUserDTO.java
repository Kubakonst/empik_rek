package com.example.empik.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

@Value
@Builder
public class GithubUserDTO implements Serializable {

  long id;
  String login;
  String name;
  String type;
  String avatarUrl;
  Instant createdAt;
  int calculations;
}
