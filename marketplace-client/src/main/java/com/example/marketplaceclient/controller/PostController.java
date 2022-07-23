package com.example.marketplaceclient.controller;

import com.example.marketplaceclient.model.dto.CreatePost;
import com.example.marketplaceclient.model.dto.PostDto;
import com.example.marketplaceclient.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {


    private final PostService service;

    @GetMapping("")
    public List<PostDto> getAllPosts() {
        return this.service.getAllPosts();
    }

    @GetMapping("/getRandomPosts")
    public List<PostDto> getRandomPosts(@RequestParam int size) {
        return this.service.getRandomPosts(size);
    }

    @GetMapping("/location/{id}")
    public List<PostDto> getPostByLocationId(@PathVariable(name = "id") String locationId) {
        return this.service.getPostsByLocationId(locationId);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable String id) {
        this.service.deletePost(id);
    }

    @PostMapping("")
    public PostDto createPost(@RequestBody CreatePost newPost) {
        return this.service.createPost(newPost);
    }

    @PatchMapping("/{id}")
    public void editPost(@PathVariable String id, @RequestBody CreatePost newPost) {
        this.service.editPost(id, newPost);
    }

    @GetMapping("/random/location/{id}")
    public List<PostDto> getPostByLocationId(@RequestParam int size, @PathVariable(name = "id") String locationId) {
        return this.service.getRandomPostsByLocationId(size, locationId);
    }

    @PostMapping("/postWithImage")
    public PostDto createPostWithImage(@RequestPart(name = "post") String post, @RequestPart(name = "image") MultipartFile file) {
        return this.service.createPostWithImage(post, file);
    }

    @GetMapping("/{id}")
    public PostDto getAsinglePost(@PathVariable String id, @RequestParam(required = false) String userId) {
        return this.service.getSinglePost(id, userId);
    }

    @PatchMapping("/likePost/{id}")
    public void likePost(@PathVariable String id, @RequestParam String userId){
        this.service.likePost(id, userId);
    }

}
