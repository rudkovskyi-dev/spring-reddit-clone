package com.programming.techie.springredditclone.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.programming.techie.springredditclone.dto.PostResponse;
import com.programming.techie.springredditclone.security.JwtProvider;
import com.programming.techie.springredditclone.service.PostService;
import com.programming.techie.springredditclone.service.UserDetailsServiceImpl;
import java.util.Arrays;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = PostController.class)
public class PostControllerTest {

  @MockBean
  private PostService postService;
  @MockBean
  private UserDetailsServiceImpl userDetailsService;
  @MockBean
  private JwtProvider jwtProvider;
  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("Should list all posts on GET request to /api/posts/")
  public void shouldListAllPosts() throws Exception {

    PostResponse postRequest1 = new PostResponse(1L, "Post Name", "http://url.site", "Description",
        "User 1",
        "Subreddit Name", 0, 0, "1 day ago", false, false);
    PostResponse postRequest2 = new PostResponse(2L, "Post Name 2", "http://url2.site2",
        "Description2", "User 2",
        "Subreddit Name 2", 0, 0, "2 days ago", false, false);

    Mockito.when(postService.getAllPosts()).thenReturn(Arrays.asList(postRequest1, postRequest2));

    mockMvc.perform(MockMvcRequestBuilders.get("/api/posts/"))
        .andExpect(MockMvcResultMatchers.status().is(200))
        .andExpect(
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.size()", Matchers.is(2)))
        .andExpect(jsonPath("$[0].id", Matchers.is(1)))
        .andExpect(jsonPath("$[0].postName", Matchers.is("Post Name")))
        .andExpect(jsonPath("$[0].url", Matchers.is("http://url.site")))
        .andExpect(jsonPath("$[1].url", Matchers.is("http://url2.site2")))
        .andExpect(jsonPath("$[1].postName", Matchers.is("Post Name 2")))
        .andExpect(jsonPath("$[1].id", Matchers.is(2)));
  }
}