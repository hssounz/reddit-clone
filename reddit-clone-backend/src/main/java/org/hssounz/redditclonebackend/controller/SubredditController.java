package org.hssounz.redditclonebackend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hssounz.redditclonebackend.dto.SubredditRequestDTO;
import org.hssounz.redditclonebackend.dto.SubredditResponseDTO;
import org.hssounz.redditclonebackend.model.Response;
import org.hssounz.redditclonebackend.service.SubredditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController @RequestMapping("/api/subreddit")
@AllArgsConstructor @Slf4j
public class SubredditController {
    private final SubredditService subredditService;

    @PostMapping
    public ResponseEntity<Response> createSubreddit(@RequestBody SubredditRequestDTO subredditRequestDTO) {
        return ResponseEntity.ok(
                Response.builder()
                        .message("Subreddit created successfully")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .data(Map.of("Subreddit: ", subredditService.save(subredditRequestDTO)))
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<Response> getAllSubreddits(){
        return ResponseEntity.ok(
                Response.builder()
                        .message("List of subreddits retrieved")
                        .statusCode(HttpStatus.OK.value())
                        .status(HttpStatus.OK)
                        .data(Map.of("subreddits", subredditService.getAll()))
                        .build()
        );
    }
}
