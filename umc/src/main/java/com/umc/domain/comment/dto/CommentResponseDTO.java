package com.umc.domain.comment.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@Data
public class CommentResponseDTO {
    private Long comment_id;
    private Long post_id;
    private Long member_id;
    private String nickname;
    private String body;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
