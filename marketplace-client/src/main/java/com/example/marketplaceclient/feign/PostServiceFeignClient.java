package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.Post;
import com.example.marketplaceclient.model.dto.CreatePost;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(url = "https://houlala.herokuapp.com/api/posts", name = "posts")
public interface PostServiceFeignClient {

    @GetMapping("")
    List<Post> getAllPosts() throws MarketplaceException;

    @GetMapping("/getRandomPosts")
    List<Post> getRandomPosts(@RequestParam int size) throws MarketplaceException;

    @GetMapping("/filterPostByPageId")
    List<Post> getPostByLocationId(@RequestParam String locationId) throws MarketplaceException;

    @DeleteMapping("/{id}")
    void deletePost(@PathVariable String id) throws MarketplaceException;

    @GetMapping("/getRandomPostsByPageId")
    List<Post> getRandomPostsByLocationId(@RequestParam int size, @RequestParam String locationId) throws MarketplaceException;

    @PostMapping("")
    Post createPost(@RequestBody CreatePost newPost) throws MarketplaceException;

    @PutMapping("/{id}")
    void editPost(@PathVariable String id, @RequestBody CreatePost editPost) throws MarketplaceException;

    @PostMapping("/postWithImage")
    Post createPostWithImage(@RequestBody CreatePost newPost, @RequestPart(name = "image") MultipartFile file) throws MarketplaceException;

    @GetMapping("/{id}")
    Post getPostById(@PathVariable String id, @RequestParam String userId) throws MarketplaceException;

    @PutMapping("/likePost/{id}")
    void likePost(@PathVariable String id, @RequestParam String userId) throws MarketplaceException;

}
