package org.hssounz.redditclonebackend.service;

import org.hssounz.redditclonebackend.dto.PostRequestDTO;
import org.hssounz.redditclonebackend.dto.PostResponseDTO;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;

import java.util.List;

public interface PostService {
    PostResponseDTO save(PostRequestDTO postRequestDTO) throws SpringRedditException;
    PostResponseDTO getPost(String id) throws SpringRedditException;
    List<PostResponseDTO> getAllPosts();

}
