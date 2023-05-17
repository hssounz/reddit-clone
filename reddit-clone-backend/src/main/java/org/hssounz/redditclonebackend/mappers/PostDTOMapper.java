package org.hssounz.redditclonebackend.mappers;

import org.hssounz.redditclonebackend.dto.PostRequestDTO;
import org.hssounz.redditclonebackend.dto.PostResponseDTO;
import org.hssounz.redditclonebackend.exceptions.SpringRedditException;
import org.hssounz.redditclonebackend.model.Post;

public interface PostDTOMapper {
    Post fromPostRequest(PostRequestDTO postRequestDTO) throws SpringRedditException;
    PostResponseDTO fromPost(Post post);
}
