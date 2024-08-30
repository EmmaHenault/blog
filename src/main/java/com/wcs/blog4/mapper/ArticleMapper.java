package com.wcs.blog4.mapper;

import com.wcs.blog4.dto.ArticleCreateDTO;
import com.wcs.blog4.dto.ArticleDTO;
import com.wcs.blog4.dto.AuthorDTO;
import com.wcs.blog4.model.*;
import com.wcs.blog4.repository.CategoryRepository;
import com.wcs.blog4.repository.ImageRepository;
import org.springframework.stereotype.Component;

import java.util.List;
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

    public Article convertToEntity(ArticleCreateDTO articleDTO) {
        Article article = new Article();
        article.setId(articleDTO.getId());
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setUpdatedAt(articleDTO.getUpdatedAt());

        // Gestion de la catégorie
        if (articleDTO.getCategoryName() != null) {
            List<Category> categories = categoryRepository.findAll();
            for (Category category : categories) {
                if (category.getName().equals(articleDTO.getCategoryName())) {
                    article.setCategory(category);
                    break;
                }
            }
        }

        // Gestion des images
        if (articleDTO.getImageUrls() != null) {
            // Ici, vous devrez soit chercher les images dans votre base de données
            // par leur URL d'une autre manière, soit créer des instances d'Image.
            // Exemple: supposons que vous ayez une méthode pour obtenir des images par leur URL.

            List<Image> images = articleDTO.getImageUrls().stream()
                    .map(url -> {
                        // Remplacez cette ligne par la méthode réelle pour récupérer ou créer l'image
                        Image image = new Image();
                        image.setUrl(url);
                        return image;
                    })
                    .collect(Collectors.toList());

            article.setImages(images);
        }

        // Gestion des auteurs
        if (articleDTO.getAuthors() != null) {
            article.setArticleAuthors(articleDTO.getAuthors().stream()
                    .map(authorDTO -> {
                        Author author = new Author();
                        author.setId(authorDTO.getId());
                        author.setFirstname(authorDTO.getFirstname());
                        author.setLastname(authorDTO.getLastname());

                        ArticleAuthor articleAuthor = new ArticleAuthor();
                        articleAuthor.setArticle(article);
                        articleAuthor.setAuthor(author);
                        return articleAuthor;
                    })
                    .collect(Collectors.toList()));
        }

        return article;
    }
}
