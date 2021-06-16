package com.example.empik.repository;

import com.example.empik.dto.GithubUserEntity;
import java.util.HashMap;
import static java.util.Objects.requireNonNull;

public class InMemoryGithubUserEntityRepository implements GithubUserEntityRepository {

  private HashMap<String, GithubUserEntity> map = new HashMap<>();

  public GithubUserEntity save(GithubUserEntity githubUserEntity) {
    requireNonNull(githubUserEntity);
    map.put(githubUserEntity.getLogin(), githubUserEntity);
    return githubUserEntity;

  }

  public GithubUserEntity findByLogin(String loanNumber) {
    GithubUserEntity loanRequestDataDto = map.get(loanNumber);
    if (loanRequestDataDto == null) {
      return new GithubUserEntity(loanNumber, 0);
    }
    return loanRequestDataDto;
  }
}
