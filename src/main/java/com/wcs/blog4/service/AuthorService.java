package com.wcs.blog4.service;

import com.wcs.blog4.dto.AuthorDTO;
import com.wcs.blog4.mapper.AuthorMapper;
import com.wcs.blog4.model.Author;
import com.wcs.blog4.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(authorMapper::toDTO).collect(Collectors.toList());
    }

    public Optional<AuthorDTO> getAuthorById(Long id) {
        return authorRepository.findById(id).map(authorMapper::toDTO);
    }

    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = authorMapper.toEntity(authorDTO);
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.toDTO(savedAuthor);
    }

    public Optional<AuthorDTO> updateAuthor(Long id, AuthorDTO authorDTO) {
        return authorRepository.findById(id).map(author -> {
            author.setFirstname(authorDTO.getFirstname());
            author.setLastname(authorDTO.getLastname());
            Author updatedAuthor = authorRepository.save(author);
            return authorMapper.toDTO(updatedAuthor);
        });
    }

    public boolean deleteAuthor(Long id) {
        return authorRepository.findById(id).map(author -> {
            authorRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
