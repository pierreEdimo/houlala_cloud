package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.Comment;
import com.example.marketplaceclient.model.dto.CreateCommentDto;
import com.example.marketplaceclient.model.dto.EditCommentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url ="http://houlala_marketplace:3000/api/posts", name = "comments")
public interface CommentServiceFeignClient {

    @GetMapping("/comment/filter")
    List<Comment> getCommentFromPostId(@RequestParam String postId) throws MarketplaceException;

    @PostMapping("/comment/{id}")
    void addComment(@PathVariable(name = "id") String postId, @RequestBody CreateCommentDto newComment) throws MarketplaceException;

    @PutMapping("/comment/{id}")
    Comment editComment(@PathVariable(name = "id") String postId, @RequestParam String commentId, @RequestBody EditCommentDto newComment) throws MarketplaceException;

    @DeleteMapping("/comment/{id}")
    void deleteComment(@PathVariable(name ="id") String postId, @RequestParam String commentId) throws MarketplaceException;

}
