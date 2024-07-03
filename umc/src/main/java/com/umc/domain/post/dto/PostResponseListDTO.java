package com.umc.domain.post.dto;

import com.umc.domain.post.entity.Post;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseListDTO {
    List<PostResponseDTO> postList;
    Integer listSize;
    Integer totalPage;
    Long totalElements;
    Boolean isFirst;
    Boolean isLast;

    public PostResponseListDTO(List<Post> content, int number, int totalPages, long totalElements, boolean isFirst, boolean isLast) {
        this.postList = content.stream()
                .map(PostResponseDTO::new)
                .collect(Collectors.toList());
        this.listSize = content.size();
        this.totalPage = totalPages;
        this.totalElements = totalElements;
        this.isFirst = isFirst;
        this.isLast = isLast;
    }

}
