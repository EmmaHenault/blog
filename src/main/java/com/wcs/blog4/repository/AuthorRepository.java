package com.wcs.blog4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wcs.blog4.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
