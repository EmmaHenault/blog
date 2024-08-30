package com.wcs.blog4.dto;

import org.hibernate.validator.constraints.URL;

import java.util.List;

public class ImageDTO {
    private Long id;
    @URL(message = "L'URL de l'image doit être valide")
    private String url;
    private List<Long> articleIds;

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @URL(message = "L'URL de l'image doit être valide") String getUrl() {
        return url;
    }

    public void setUrl(@URL(message = "L'URL de l'image doit être valide") String url) {
        this.url = url;
    }

    public List<Long> getArticleIds() {
        return articleIds;
    }

    public void setArticleIds(List<Long> articleIds) {
        this.articleIds = articleIds;
    }
}
