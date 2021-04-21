package com.programming.techie.springredditclone.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.programming.techie.springredditclone.model.User;
import java.time.Instant;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTestEmbedded {

  @Autowired
  private UserRepository userRepository;

  @Test
  public void shouldSaveUser() {
    User user = new User(null, "test user", "password", "user@email.com", Instant.now(), true);
    User savedUser = userRepository.save(user);
    assertThat(savedUser).usingRecursiveComparison().ignoringFields("userId").isEqualTo(user);
  }

  @Test
  @Sql("classpath:test-data.sql")
  public void shouldSaveUserThroughSqlFile() {
    Optional<User> test = userRepository.findByUsername("testuser_sql");
    assertThat(test).isNotEmpty();
  }

}
