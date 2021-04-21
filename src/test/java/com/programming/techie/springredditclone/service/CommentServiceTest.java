package com.programming.techie.springredditclone.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.programming.techie.springredditclone.exceptions.SpringRedditException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommentServiceTest {

  @Test
  @DisplayName("Test should pass when comment doesn't contain swear words")
  void shouldNotContainSwearWordsInsideComment() {
    CommentService commentService = new CommentService(null, null, null, null, null, null, null);
    Assertions.assertFalse(commentService.containsSwearWords("This is a clean comment"));
  }

  @Test
  @DisplayName("Test should pass when comment doesn't contain swear words")
  void assertJShouldNotContainSwearWordsInsideComment() {
    CommentService commentService = new CommentService(null, null, null, null, null, null, null);
    assertThat(commentService.containsSwearWords("This is a clean comment")).isFalse();
  }

  @Test
  @DisplayName("Test should pass when comment contains swear words and throw an exception")
  void shouldThrowExceptionWhenCommentContainsSwearWordsInsideComment() {
    CommentService commentService = new CommentService(null, null, null, null, null, null, null);
    SpringRedditException exception = Assertions.assertThrows(SpringRedditException.class, () ->
        commentService.containsSwearWords("This is a clean as shit comment"));
    Assertions.assertTrue(exception.getMessage().contains("Comments contains unacceptable language"));
  }

  @Test
  @DisplayName("Test should pass when comment contains swear words and throw an exception")
  void assertJShouldThrowExceptionWhenCommentContainsSwearWordsInsideComment() {
    CommentService commentService = new CommentService(null, null, null, null, null, null, null);
    assertThatThrownBy(() ->
        commentService.containsSwearWords("This is a clean as shit comment")
    ).isInstanceOf(SpringRedditException.class)
        .hasMessage("Comments contains unacceptable language");
  }
}