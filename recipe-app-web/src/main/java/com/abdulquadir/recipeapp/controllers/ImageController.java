package com.abdulquadir.recipeapp.controllers;


import com.abdulquadir.recipeapp.commands.RecipeCommand;
import com.abdulquadir.recipeapp.services.ImageService;
import com.abdulquadir.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


@Slf4j
@Controller
@RequestMapping("/recipe")
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }


    @GetMapping("/{recipeId}/image")
    public String uploadImage(@PathVariable String recipeId, Model model){

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/imageUploadForm";
    }


    @PostMapping("/{recipeId}/image")
    public String handleImageUpload(@PathVariable String recipeId, @RequestParam("imagefile") MultipartFile imgFile){

        imageService.saveImage(Long.valueOf(recipeId), imgFile);

        return "redirect:/recipe/"+recipeId+"/show";
    }

    @GetMapping("/{recipeId}/recipeimage")
    public void renderImageFromDB(@PathVariable String recipeId, HttpServletResponse response) throws IOException{

        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));

        byte[] byteArray = new byte[recipeCommand.getImage().length];
        int i = 0;
        for(Byte wrappedByte : recipeCommand.getImage()){
            byteArray[i++] = wrappedByte;
        }

        response.setContentType("image/jpeg");
        InputStream inputStream = new ByteArrayInputStream(byteArray);
        IOUtils.copy(inputStream, response.getOutputStream());

    }
}
