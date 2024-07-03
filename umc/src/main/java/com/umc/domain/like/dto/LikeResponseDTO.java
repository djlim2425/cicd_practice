package com.umc.domain.like.dto;

import lombok.Data;

@Data
public class LikeResponseDTO {
    private Long member_id;
    private Long post_id;
    private boolean liked;
}
