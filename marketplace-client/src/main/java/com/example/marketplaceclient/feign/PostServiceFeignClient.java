package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.Post;
import com.example.marketplaceclient.model.dto.CreatePost;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(url = "http://houlala_marketplace:3000/api/posts", name = "posts")
public interface PostServiceFeignClient {

    @GetMapping("")
    List<Post> getAllPosts() throws MarketplaceException;

    @GetMapping("/random")
    List<Post> getRandomPosts(@RequestParam int size) throws MarketplaceException;

    @GetMapping("/location/{id}")
    List<Post> getPostByLocationId(@PathVariable("id") String locationId) throws MarketplaceException;

    @DeleteMapping("/{id}")
    void deletePost(@PathVariable String id) throws MarketplaceException;

    @GetMapping("/random/location/{locationId}")
    List<Post> getRandomPostsByLocationId(@RequestParam int size, @PathVariable String locationId) throws MarketplaceException;

    @PostMapping("")
    Post createPost(@RequestBody CreatePost newPost) throws MarketplaceException;

    @PutMapping("/{id}")
    void editPost(@PathVariable String id, @RequestBody CreatePost editPost) throws MarketplaceException;

    @PostMapping("/image")
    Post createPostWithImage(@RequestBody CreatePost newPost, @RequestPart(name = "image") MultipartFile file) throws MarketplaceException;

    @GetMapping("/{id}")
    Post getPostById(@PathVariable String id, @RequestParam String userId) throws MarketplaceException;

    @PutMapping("/like/{id}")
    void likePost(@PathVariable String id, @RequestParam String userId) throws MarketplaceException;

}
