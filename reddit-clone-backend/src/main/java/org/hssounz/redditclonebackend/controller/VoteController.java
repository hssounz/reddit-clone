package org.hssounz.redditclonebackend.controller;

import lombok.RequiredArgsConstructor;
import org.hssounz.redditclonebackend.dto.VoteRequestDTO;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;
import org.hssounz.redditclonebackend.model.Response;
import org.hssounz.redditclonebackend.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController @RequestMapping("/api/votes") @RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<Response> vote(@RequestBody VoteRequestDTO voteRequestDTO) {
        try {
            return ResponseEntity.ok(
                    Response.builder()
                            .statusCode(HttpStatus.CREATED.value())
                            .status(HttpStatus.CREATED)
                            .message("Vote created successfully")
                            .data(Map.of("Vote", voteService.vote(voteRequestDTO)))
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
}
