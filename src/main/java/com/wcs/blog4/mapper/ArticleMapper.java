package com.wcs.blog4.mapper;

import com.wcs.blog4.dto.ArticleDTO;
import com.wcs.blog4.dto.AuthorDTO;
import com.wcs.blog4.model.*;
import com.wcs.blog4.repository.CategoryRepository;
import com.wcs.blog4.repository.ImageRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ArticleMapper {
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;

    public ArticleMapper(CategoryRepository categoryRepository, ImageRepository imageRepository) {
        this.categoryRepository = categoryRepository;
        this.imageRepository = imageRepository;
    }

    public ArticleDTO convertToDTO(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setContent(article.getContent());
        articleDTO.setUpdatedAt(article.getUpdatedAt());
        if (article.getCategory() != null) {
            articleDTO.setCategoryName(article.getCategory().getName());
        }
        if (article.getImages() != null) {
            articleDTO.setImageUrls(article.getImages().stream().map(Image::getUrl).collect(Collectors.toList()));
        }
        if (article.getArticleAuthors() != null) {
            articleDTO.setAuthors(article.getArticleAuthors().stream()
                    .filter(articleAuthor -> articleAuthor.getAuthor() != null)
                    .map(articleAuthor -> {
                        AuthorDTO authorDTO = new AuthorDTO();
                        authorDTO.setId(articleAuthor.getAuthor().getId());
                        authorDTO.setFirstname(articleAuthor.getAuthor().getFirstname());
                        authorDTO.setLastname(articleAuthor.getAuthor().getLastname());
                        return authorDTO;
                    })
                    .collect(Collectors.toList()));
        }
        return articleDTO;
    }
}
