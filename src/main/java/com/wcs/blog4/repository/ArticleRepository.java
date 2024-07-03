package com.wcs.blog4.repository;

import com.wcs.blog4.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
