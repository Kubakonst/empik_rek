package com.example.empik.service;

import com.example.empik.dto.GithubUserDTO;
import com.example.empik.dto.GithubUserEntity;
import com.example.empik.model.GithubUserResponse;
import com.example.empik.repository.GithubUserEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import static java.util.Objects.requireNonNull;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class GithubUserService {

  private final RestTemplate restTemplate = new RestTemplate();
  private final GithubUserEntityRepository githubUserEntityRepository;

  public GithubUserDTO findGithubUser(String login) {
    GithubUserDTO githubUserDTO = mapToDTOFromResponse(getGithubUserData(login));
    saveRequestCount(githubUserDTO);
    return githubUserDTO;
  }

  GithubUserResponse getGithubUserData(String login) {

    String url = "https://api.github.com/users/" + login;

    ResponseEntity<GithubUserResponse> response =
        restTemplate.exchange(
            url, HttpMethod.GET, new HttpEntity(new HttpHeaders()), GithubUserResponse.class);
    return response.getBody();
  }

  private void saveRequestCount(GithubUserDTO githubUserDTO) {
    GithubUserEntity githubUserEntity =
        githubUserEntityRepository.findByLogin(githubUserDTO.getLogin());
    githubUserEntity.setRequestCount(githubUserEntity.getRequestCount() + 1);
    githubUserEntityRepository.save(githubUserEntity);
  }

  private GithubUserDTO mapToDTOFromResponse(GithubUserResponse githubUserResponse) {
    requireNonNull(githubUserResponse);
    return GithubUserDTO.builder()
        .id(githubUserResponse.getId())
        .login(githubUserResponse.getLogin())
        .name(githubUserResponse.getName())
        .type(githubUserResponse.getType())
        .avatarUrl(githubUserResponse.getAvatarUrl())
        .createdAt(githubUserResponse.getCreatedAt())
        .calculations(
            6 / githubUserResponse.getFollowers() * (2 + githubUserResponse.getPublicRepos()))
        .build();
  }
}
