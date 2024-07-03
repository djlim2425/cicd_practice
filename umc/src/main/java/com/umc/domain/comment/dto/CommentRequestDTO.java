package com.umc.domain.comment.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
public class CommentRequestDTO {

    @Pattern(regexp = "^[a-zA-z0-9]{1,50}$", message = "1 ~ 50자로 작성해 주십시오.")
    private String body;
}
