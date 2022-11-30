package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.Post;
import com.example.marketplaceclient.model.dto.CreatePost;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "http://houlala_marketplace:3000/api/posts", name = "posts")
public interface PostServiceFeignClient {

    @GetMapping("")
    List<Post> getAllPosts() throws MarketplaceException;

    @GetMapping("/random")
    List<Post> getRandomPosts(@RequestParam(value = "size") int size) throws MarketplaceException;

    @GetMapping("/location/{id}")
    List<Post> getPostByLocationId(@PathVariable(value = "id") String locationId) throws MarketplaceException;

    @DeleteMapping("/{id}")
    void deletePost(@PathVariable(value = "id") String id) throws MarketplaceException;

    @GetMapping("/random/location/{locationId}")
    List<Post> getRandomPostsByLocationId(@RequestParam(value = "size") int size, @PathVariable(value = "locationId") String locationId) throws MarketplaceException;

    @PostMapping("")
    Post createPost(@RequestBody CreatePost newPost) throws MarketplaceException;

    @PutMapping("/{id}")
    void editPost(@PathVariable(value = "id") String id, @RequestBody CreatePost editPost) throws MarketplaceException;

    @GetMapping("/{id}")
    Post getPostById(@PathVariable(value = "id") String id, @RequestParam(value = "userId") String userId) throws MarketplaceException;

    @PutMapping("/like/{id}")
    void likePost(@PathVariable(value = "id") String id, @RequestParam(value = "userId") String userId) throws MarketplaceException;

}
