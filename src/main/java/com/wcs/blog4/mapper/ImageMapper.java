package com.wcs.blog4.mapper;

import com.wcs.blog4.dto.ImageDTO;
import com.wcs.blog4.model.Article;
import com.wcs.blog4.model.Image;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ImageMapper {

    public ImageDTO toDTO(Image image) {
        if (image == null) {
            return null;
        }
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(image.getId());
        imageDTO.setUrl(image.getUrl());

        if (image.getArticles() != null) {
            imageDTO.setArticleIds(image.getArticles().stream().map(Article::getId).collect(Collectors.toList()));
        }

        return imageDTO;
    }

    public Image toEntity(ImageDTO imageDTO) {
        if (imageDTO == null) {
            return null;
        }
        Image image = new Image();
        image.setId(imageDTO.getId());
        image.setUrl(imageDTO.getUrl());
        // Les associations avec les articles peuvent être gérées séparément pour éviter les problèmes de boucles infinies
        return image;
    }
}
