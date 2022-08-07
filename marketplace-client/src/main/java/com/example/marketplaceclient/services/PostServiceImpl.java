package com.example.marketplaceclient.services;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.feign.LocationServiceFeignClient;
import com.example.marketplaceclient.feign.PostServiceFeignClient;
import com.example.marketplaceclient.model.Location;
import com.example.marketplaceclient.model.Post;
import com.example.marketplaceclient.model.dto.CreatePost;
import com.example.marketplaceclient.model.dto.PostDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostServiceFeignClient feignClient;

    private final LocationServiceFeignClient serviceFeignClient;

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> postList;
        List<PostDto> postDtoList = new ArrayList<>();

        try {
            postList = this.feignClient.getAllPosts();
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        postList.forEach(post -> postDtoList.add(this.toPostDto(post)));

        return postDtoList;
    }

    @Override
    public List<PostDto> getRandomPosts(int size) {
        List<Post> postList;
        List<PostDto> postDtoList = new ArrayList<>();

        try {
            postList = this.feignClient.getRandomPosts(size);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        postList.forEach(post -> postDtoList.add(this.toPostDto(post)));

        return postDtoList;
    }

    @Override
    public List<PostDto> getPostsByLocationId(String locationId) {
        List<Post> postList;
        List<PostDto> postDtoList = new ArrayList<>();

        try {
            postList = this.feignClient.getPostByLocationId(locationId);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        postList.forEach(post -> postDtoList.add(this.toPostDto(post)));

        return postDtoList;
    }

    @Override
    public void deletePost(String id) {
        try {
            this.feignClient.deletePost(id);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public List<PostDto> getRandomPostsByLocationId(int size, String locationId) {
        List<Post> postList;
        List<PostDto> postDtoList = new ArrayList<>();

        try {
            postList = this.feignClient.getRandomPostsByLocationId(size, locationId);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        postList.forEach(post -> postDtoList.add(this.toPostDto(post)));

        return postDtoList;
    }

    @Override
    public PostDto createPost(CreatePost newPost) {
        Post createdPost;

        try {
            createdPost = this.feignClient.createPost(newPost);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        return this.toPostDto(createdPost);
    }

    @Override
    public void editPost(String id, CreatePost newPost) {
        try {
            this.feignClient.editPost(id, newPost);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public PostDto createPostWithImage(String post, MultipartFile file) {
        CreatePost newPost;
        Post createdPost;

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            newPost = objectMapper.readValue(post, CreatePost.class);
            createdPost = this.feignClient.createPostWithImage(newPost, file);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return this.toPostDto(createdPost);
    }

    @Override
    public PostDto getSinglePost(String id, String userId) {
        Post post;

        try {
            post = this.feignClient.getPostById(id, userId);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        return this.toPostDto(post);
    }

    @Override
    public void likePost(String id, String userId) {
        try {
            this.feignClient.likePost(id, userId);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public List<PostDto> filterPost(String word) {
        List<Post> posts;
        List<PostDto> postDtoList = new ArrayList<>();

        try {
            posts = this.feignClient.getAllPosts().stream().filter(post -> post.getContent()
                    .toLowerCase().contains(word.toLowerCase())).toList();
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        posts.forEach(post -> postDtoList.add(this.toPostDto(post)));
        return postDtoList;
    }


    private PostDto toPostDto(Post post) {
        Location location;

        try {
            location = this.serviceFeignClient.getALocation(post.getLocationId());
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        return new PostDto(
                post.get_id(),
                post.getContent(),
                post.getImageUrl(),
                location,
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getCommentCount(),
                post.getLikeCount(),
                post.isLiked()
        );

    }
}
