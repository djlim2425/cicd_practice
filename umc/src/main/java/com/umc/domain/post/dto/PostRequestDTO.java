package com.umc.domain.post.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostRequestDTO {
    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력 값입니다.")
    private String body;
}
