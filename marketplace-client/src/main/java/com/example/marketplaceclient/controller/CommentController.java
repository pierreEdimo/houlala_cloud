package com.example.marketplaceclient.controller;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.feign.CommentServiceFeignClient;
import com.example.marketplaceclient.feign.LoginFeignClient;
import com.example.marketplaceclient.model.Comment;
import com.example.marketplaceclient.model.User;
import com.example.marketplaceclient.model.dto.CommentDto;
import com.example.marketplaceclient.model.dto.CreateCommentDto;
import com.example.marketplaceclient.model.dto.EditCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentServiceFeignClient feignClient;

    private final LoginFeignClient loginFeignClient;

    @GetMapping("")
    public List<CommentDto> getCommentFromPost(@RequestParam String postId) {
        List<Comment> commentList;
        List<CommentDto> commentDtoList = new ArrayList<>();

        try {
            commentList = this.feignClient.getCommentFromPostId(postId);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        commentList.forEach(comment -> commentDtoList.add(this.toCommentDto(comment)));

        return commentDtoList;
    }

    @PostMapping("/{id}")
    public void addCommentToPost(@PathVariable(name = "id") String postId, @RequestBody CreateCommentDto newComment) {
        try {
            this.feignClient.addComment(postId, newComment);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CommentDto editComment(@PathVariable(name = "id") String id, @RequestParam String commentId, @RequestBody EditCommentDto newComment) {
        Comment comment;

        try {
            comment = this.feignClient.editComment(id, commentId, newComment);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }

        return this.toCommentDto(comment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable(name = "id") String postId, @RequestParam String commentId) {
        try {
            this.feignClient.deleteComment(postId, commentId);
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    private CommentDto toCommentDto(Comment comment) {
        User author;
        try {
            author = this.loginFeignClient.getSingleUserByEmail(comment.getUserId());
        } catch (MarketplaceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
        return new CommentDto(
                comment.getContent(),
                comment.get_id(),
                comment.getCreatedAt(),
                comment.getUpdatedAt(),
                comment.getPostId(),
                author
        );
    }
}
