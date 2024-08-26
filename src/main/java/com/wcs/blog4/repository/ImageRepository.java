package com.wcs.blog4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wcs.blog4.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
