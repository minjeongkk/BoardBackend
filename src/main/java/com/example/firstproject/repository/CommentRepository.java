package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //특정 게시글의 모든 댓글 조회
    // 1. Query 어노테이션으로 작성
    @Query(value =
            "SELECT * "+
            "FROM comment "+
            "WHERE article_id = :articleId",
            nativeQuery = true)
                                //:articleId와 같아야 함
    List<Comment> findByArticleId(Long articleId);

    // 특정 닉네임의 모든 댓글 조회
    // 2. 쿼리를 xml로 작성 -> META-INF에 orm.xml
    List<Comment> findByNickname(String nickname);

}
