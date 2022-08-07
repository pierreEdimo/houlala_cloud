package com.example.marketplaceclient.services;

import com.example.marketplaceclient.model.dto.CreatePost;
import com.example.marketplaceclient.model.dto.PostDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

    List<PostDto> getAllPosts();

    List<PostDto> getRandomPosts(int size);

    List<PostDto> getPostsByLocationId(String locationId);

    void deletePost(String id);

    List<PostDto> getRandomPostsByLocationId(int size, String locationId);

    PostDto createPost(CreatePost newPost);

    void editPost(String id, CreatePost newPost);

    PostDto createPostWithImage(String post, MultipartFile file);

    PostDto getSinglePost(String id , String userId);

    void likePost(String id, String userId);

    List<PostDto> filterPost(String word);

}
