package com.wcs.blog4.service;

import com.wcs.blog4.dto.ImageDTO;
import com.wcs.blog4.mapper.ImageMapper;
import com.wcs.blog4.model.Image;
import com.wcs.blog4.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    public ImageService(ImageRepository imageRepository, ImageMapper imageMapper) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
    }

    public List<ImageDTO> getAllImages() {
        List<Image> images = imageRepository.findAll();
        return images.stream().map(imageMapper::toDTO).collect(Collectors.toList());
    }

    public Optional<ImageDTO> getImageById(Long id) {
        return imageRepository.findById(id).map(imageMapper::toDTO);
    }

    public ImageDTO createImage(ImageDTO imageDTO) {
        Image image = imageMapper.toEntity(imageDTO);
        Image savedImage = imageRepository.save(image);
        return imageMapper.toDTO(savedImage);
    }

    public Optional<ImageDTO> updateImage(Long id, ImageDTO imageDTO) {
        return imageRepository.findById(id).map(image -> {
            image.setUrl(imageDTO.getUrl());
            Image updatedImage = imageRepository.save(image);
            return imageMapper.toDTO(updatedImage);
        });
    }

    public boolean deleteImage(Long id) {
        return imageRepository.findById(id).map(image -> {
            imageRepository.delete(image);
            return true;
        }).orElse(false);
    }
}
