package com.wcs.blog4.repository;

import com.wcs.blog4.model.Article;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByTitle(String title);

    List<Article> findByContent(String content);

    List<Article> findByCreatedAtAfter(LocalDateTime date);

    List<Article> findTop5ByOrderByCreatedAtDesc();
}