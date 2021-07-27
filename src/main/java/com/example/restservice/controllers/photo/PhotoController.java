package com.example.restservice.controllers.photo;

import com.example.restservice.models.photo.Photo;
import com.example.restservice.repository.PhotoRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PhotoController {
    @Autowired
    private PhotoRepository photoRepository;

    @PostMapping("/photos")
    public ResponseEntity<Photo> addPhoto(@RequestParam("title") String title, @RequestParam("image") MultipartFile image, Model model) throws IOException {
        try {
            Photo photo = new Photo(title);
            photo.setImage(
                    new Binary(BsonBinarySubType.BINARY, image.getBytes()));
            photo = photoRepository.insert(photo);
            //String id = photo.getId();
            //return "redirect:/api/v1/photos/" + id;
            return new ResponseEntity<>(photo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/photos/upload")
    public String uploadPhoto(Model model) {
        model.addAttribute("message", "hello");
        return "uploadPhoto";
    }

    @GetMapping("/photos")
    public ResponseEntity<List<Photo>> getAllPhotos(){
        try {
            List<Photo> photos = new ArrayList<>();
            photoRepository.findAll().forEach(photos::add);
            if (photos.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(photos, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/photos/{id}")
    public ResponseEntity<Photo> getPhoto(@PathVariable String id, Model model) {
        try {
            Photo photo = photoRepository.findById(id).get();
            model.addAttribute("title", photo.getTitle());
            model.addAttribute("image",
                    Base64.getEncoder().encodeToString(photo.getImage().getData()));
            //return photo;//"photos";
            return new ResponseEntity<>(photo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/photos/{id}")
    public ResponseEntity<HttpStatus> deletePhoto(@PathVariable String id){
        try {
            photoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/photos")
    public ResponseEntity<HttpStatus> deleteAll(){
        try {
            photoRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
