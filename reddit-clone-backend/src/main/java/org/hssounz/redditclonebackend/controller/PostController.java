package org.hssounz.redditclonebackend.controller;

import lombok.RequiredArgsConstructor;
import org.hssounz.redditclonebackend.dto.PostRequestDTO;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;
import org.hssounz.redditclonebackend.model.Response;
import org.hssounz.redditclonebackend.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController @RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Response> save(@RequestBody PostRequestDTO postRequestDTO){
        try {
            return ResponseEntity.ok(
                    Response.builder()
                            .statusCode(HttpStatus.CREATED.value())
                            .status(HttpStatus.CREATED)
                            .message("New post added successfully to subreddit: " + postRequestDTO.getSubredditName())
                            .data(Map.of("Post", postService.save(postRequestDTO)))
                            .build()
            );
        } catch (SpringRedditException e) {
            e.printStackTrace();
            return ResponseEntity.ok(
                    Response.builder()
                            .statusCode(HttpStatus.NOT_ACCEPTABLE.value())
                            .status(HttpStatus.NOT_ACCEPTABLE)
                            .message(e.getMessage())
                            .developerMessage(e.toString())
                            .build()
            );
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable String id){
        try {
            return ResponseEntity.ok(
                    Response.builder()
                            .message(String.format("Post: %s retrieved successfully", id))
                            .statusCode(HttpStatus.OK.value())
                            .status(HttpStatus.OK)
                            .data(Map.of("Post", postService.getPost(id)))
                            .build()
            );
        } catch (SpringRedditException e) {
            e.printStackTrace();
            return ResponseEntity.ok(
                    Response.builder()
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .developerMessage(e.toString())
                            .status(HttpStatus.NOT_FOUND)
                            .message(e.getMessage())
                            .build()
            );
        }
    }
    @GetMapping("/all")
    public ResponseEntity<Response> getAllPosts(){
        return ResponseEntity.ok(
                Response.builder()
                        .message("All posts retrieved successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(Map.of("Posts", postService.getAllPosts()))
                        .build()
        );
    }
}
