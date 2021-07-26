package com.example.restservice.controllers;

import com.example.restservice.models.photo.Photo;
import com.example.restservice.repository.PhotoRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api/v1")
public class PhotoController {
    @Autowired
    private PhotoRepository photoRepo;

    @PostMapping("/photos")
    public String addPhoto(@RequestParam("title") String title, @RequestParam("image") MultipartFile image, Model model) throws IOException {
        Photo photo = new Photo(title);
        photo.setImage(
                new Binary(BsonBinarySubType.BINARY, image.getBytes()));
        photo = photoRepo.insert(photo);
        String id = photo.getId();
        return "redirect:/api/v1/photos/" + id;
    }

    @GetMapping("/photos/upload")
    public String uploadPhoto(Model model) {
        model.addAttribute("message", "hello");
        return "uploadPhoto";
    }

    @GetMapping("/photos/{id}")
    public Photo getPhoto(@PathVariable String id, Model model) {
        Photo photo = photoRepo.findById(id).get();
        model.addAttribute("title", photo.getTitle());
        model.addAttribute("image",
                Base64.getEncoder().encodeToString(photo.getImage().getData()));
        return photo;//"photos";
    }



}
