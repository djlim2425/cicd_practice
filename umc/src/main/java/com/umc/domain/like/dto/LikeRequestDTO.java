package com.umc.domain.like.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LikeRequestDTO {
    private Long member_id;
    private Long post_id;

}
