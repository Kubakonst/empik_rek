package com.example.empik.service;

import com.example.empik.dto.GithubUserDTO;
import com.example.empik.repository.InMemoryGithubUserEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GithubUserServiceTest {

  private GithubUserService githubUserService;
  private InMemoryGithubUserEntityRepository inMemoryGithubUserEntityRepository =
      new InMemoryGithubUserEntityRepository();

  @BeforeEach
  void setup() {
    githubUserService = new GithubUserService(inMemoryGithubUserEntityRepository);
  }

  @Test
  void shouldNotThrowAnyExceptions() {

    assertDoesNotThrow(
        () -> {
          githubUserService.findGithubUser("Kubakonst");
        });
  }

  @Test
  void shouldSaveInRepo() {

    githubUserService.findGithubUser("Kubakonst");
    assertEquals(1, inMemoryGithubUserEntityRepository.findByLogin("Kubakonst").getRequestCount());
  }

  @Test
  void shouldCountRequests() {

    githubUserService.findGithubUser("Kubakonst");
    githubUserService.findGithubUser("Kubakonst");
    githubUserService.findGithubUser("Kubakonst");
    assertEquals(3, inMemoryGithubUserEntityRepository.findByLogin("Kubakonst").getRequestCount());
  }

  @Test
  void shouldCalculateCorrect() {
    GithubUserDTO result = githubUserService.findGithubUser("Kubakonst");
    assertEquals(42, result.getCalculations());
  }
}
