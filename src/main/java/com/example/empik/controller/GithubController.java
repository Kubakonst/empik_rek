package com.example.empik.controller;

import com.example.empik.dto.GithubUserDTO;
import com.example.empik.service.GithubUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class GithubController {

  @Autowired
  private GithubUserService githubUserService;

  @GetMapping("/{login}")
  public GithubUserDTO getAllAdministrators(
      @PathVariable(value = "login") String login) {
    return githubUserService.findGithubUser(login);
  }
}
