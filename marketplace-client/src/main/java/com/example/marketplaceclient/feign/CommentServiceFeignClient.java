package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.Comment;
import com.example.marketplaceclient.model.dto.CreateCommentDto;
import com.example.marketplaceclient.model.dto.EditCommentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url ="https://houlala.herokuapp.com/api/posts", name = "comments")
public interface CommentServiceFeignClient {

    @GetMapping("/getCommentFromPost")
    List<Comment> getCommentFromPostId(@RequestParam String postId) throws MarketplaceException;

    @PutMapping("/addComment/{id}")
    Comment addComment(@PathVariable(name = "id") String postId, @RequestBody CreateCommentDto newComment) throws MarketplaceException;

    @PutMapping("/editCommentFromPost/{id}")
    Comment editComment(@PathVariable(name = "id") String postId, @RequestParam String commentId, @RequestBody EditCommentDto newComment) throws MarketplaceException;

    @PutMapping("/deleteComment/{id}")
    void deleteComment(@PathVariable(name ="id") String postId, @RequestParam String commentId) throws MarketplaceException;

}
