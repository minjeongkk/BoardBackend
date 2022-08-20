package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //JPA와 연동한 테스트
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /* Case 1: 4번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 4L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상하기
            Article article = new Article(4L, "movie", "comment!");
            Comment a = new Comment(1L, article, "Park", "spider man");
            Comment b = new Comment(2L, article, "Kim", "conan");
            Comment c = new Comment(3L, article, "Choi", "iron man");
            List<Comment> expected = Arrays.asList(a,b,c);

            // 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력");
        }

        /* Case 2: 1번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 1L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상하기
            Article article = new Article(1L, "KIM", "1111");
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없음");
        }

        /* Case 3 : 9번 게시글의 모든 댓글을 조회 */
        /* Case 4 : 9999번 게시글의 모든 댓글을 조회 */
        /* Case 5 : -1번 게시글의 모든 댓글을 조회 */

    }


    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        /* Case 1 : Park의 모든 댓글을 조회 */
        {
            // 입력 데이터를 준비
            String nickname ="Park";

            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 예상하기
            Comment a = new Comment(1L, new Article(4L, "movie", "comment!"), "Park", "spider man");
            Comment b = new Comment(4L, new Article(5L, "food", "comment plz"), "Park", "chicken");
            Comment c = new Comment(7L, new Article(6L, "hobby", "comment gogo"), "Park", "book");
            List<Comment> excepted = Arrays.asList(a, b, c);

            // 검증
            assertEquals(excepted.toString(), comments.toString(), "Park의 모든 댓글을 출력");
        }
        /* Case 2 : "Kim"의 모든 댓글을 조회 */
        /* Case 3 : null의 모든 댓글을 조회 */
        /* Case 4 : ""의 모든 댓글을 조회 */
        /* Case 5 : "i"의 모든 댓글을 조회 */
    }
}