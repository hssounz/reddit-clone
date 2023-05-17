package org.hssounz.redditclonebackend.controller;

import lombok.RequiredArgsConstructor;
import org.hssounz.redditclonebackend.dto.CommentRequestDTO;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;
import org.hssounz.redditclonebackend.model.Comment;
import org.hssounz.redditclonebackend.model.Response;
import org.hssounz.redditclonebackend.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController @RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping
    ResponseEntity<Response> createComment(@RequestBody CommentRequestDTO commentRequestDTO){
        try {
            return ResponseEntity.ok(
                    Response.builder()
                            .status(HttpStatus.CREATED)
                            .statusCode(HttpStatus.CREATED.value())
                            .message("Comment created successfully")
                            .data(Map.of("Comment", commentService.save(commentRequestDTO)))
                            .build()
            );
        } catch (SpringRedditException e) {
            e.printStackTrace();
            return ResponseEntity.ok(
                    Response.builder()
                            .status(HttpStatus.NOT_ACCEPTABLE)
                            .statusCode(HttpStatus.NOT_ACCEPTABLE.value())
                            .message(e.getMessage())
                            .developerMessage(e.toString())
                            .build()
            );
        }
    }
    @GetMapping("/{postId}")
    ResponseEntity<Response> getPostComments(@PathVariable String postId) {
       try {
           return ResponseEntity.ok(
                   Response.builder()
                           .message(String.format("Comments for post: %s retrieved successfully.", postId))
                           .status(HttpStatus.OK)
                           .statusCode(HttpStatus.OK.value())
                           .data(Map.of("Comments", commentService.getPostComments(postId)))
                           .build()
           );
       } catch (Error | Exception e) {
           e.printStackTrace();
           return ResponseEntity.ok(
                   Response.builder()
                           .message(e.getMessage())
                           .status(HttpStatus.INTERNAL_SERVER_ERROR)
                           .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                           .build()
           );
       }
    }
    @GetMapping("/{username}")
    ResponseEntity<Response> getUserComments(@PathVariable String username) {
        try {
            return ResponseEntity.ok(
                    Response.builder()
                            .message(String.format("Comments for user: %s retrieved successfully.", username))
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .data(Map.of("Comments", commentService.getUserComments(username)))
                            .build()
            );
        } catch (Error | Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(
                    Response.builder()
                            .message(e.getMessage())
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .build()
            );
        }
    }
}
