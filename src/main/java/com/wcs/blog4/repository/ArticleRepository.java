package com.wcs.blog4.repository;

import com.wcs.blog4.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDateTime;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByTitle(String title);
    List<Article> findByContentContaining(String terms);
    List<Article> findByCreatedAtAfter(LocalDateTime date);
    List<Article> findTop5ByOrderByCreatedAtDesc();
}