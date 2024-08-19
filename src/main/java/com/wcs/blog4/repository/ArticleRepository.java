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

    List<Article> findByContentContaining(String keyword);

    List<Article> findByCreatedAt(LocalDateTime createdAt);

    @Query("SELECT a FROM Article a ORDER BY a.createdAt DESC")
    List<Article> findTop5ByOrderByCreationDateDesc(Pageable pageable);
    default List<Article> findLatest5Articles() {
        return findTop5ByOrderByCreationDateDesc(PageRequest.of(0, 5));
    }
}
