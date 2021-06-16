package com.example.empik.repository;

import com.example.empik.dto.GithubUserEntity;
import org.springframework.data.repository.Repository;

public interface GithubUserEntityRepository extends Repository<GithubUserEntity, String> {

    GithubUserEntity save(GithubUserEntity githubUserEntity);
    GithubUserEntity findByLogin(String login);
}
