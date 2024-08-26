package com.wcs.blog4.mapper;

import com.wcs.blog4.dto.AuthorDTO;
import com.wcs.blog4.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorDTO toDTO(Author author) {
        if (author == null) {
            return null;
        }
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setFirstname(author.getFirstname());
        authorDTO.setLastname(author.getLastname());
        return authorDTO;
    }

    public Author toEntity(AuthorDTO authorDTO) {
        if (authorDTO == null) {
            return null;
        }
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setFirstname(authorDTO.getFirstname());
        author.setLastname(authorDTO.getLastname());
        return author;
    }
}
