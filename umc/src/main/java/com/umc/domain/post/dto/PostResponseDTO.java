package com.umc.domain.post.dto;

import com.umc.domain.comment.dto.CommentResponseDTO;
import com.umc.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDTO {
    private Long post_id;
    private Long board_id;
    private Long member_id;
    private String title;
    private String body;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDTO> comments;

    public PostResponseDTO(Post post) {
        this.post_id = post.getId();
        this.board_id = post.getBoard().getId();
        this.member_id = post.getMember().getId();
        this.title = post.getTitle();
        this.body = post.getBody();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }

}

