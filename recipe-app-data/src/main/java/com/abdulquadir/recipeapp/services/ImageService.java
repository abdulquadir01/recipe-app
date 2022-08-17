package com.abdulquadir.recipeapp.services;


import org.springframework.web.multipart.MultipartFile;



public interface ImageService {
    public void saveImage(Long recipeId, MultipartFile file);
}