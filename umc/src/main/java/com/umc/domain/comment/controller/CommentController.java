package com.umc.domain.comment.controller;

import com.umc.common.response.ApiResponse;
import com.umc.domain.comment.dto.CommentRequestDTO;
import com.umc.domain.comment.dto.CommentResponseDTO;
import com.umc.domain.comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/comment")
@RestController
public class CommentController {

    private final CommentService commentService;

    @CrossOrigin
    @Operation(summary = "댓글 작성 API")
    @PostMapping("/")
    public ApiResponse<CommentResponseDTO> addComment(
            @RequestBody CommentRequestDTO commentRequestDTO,
            @RequestParam Long postId,
            @RequestParam Long memberId) {
        CommentResponseDTO createdComment = commentService.addComment(commentRequestDTO, postId, memberId);
        return ApiResponse.onSuccess(createdComment);
    }

    @CrossOrigin
    @Operation(summary = "댓글 수정 API")
    @PostMapping("/{comment_id}")
    public ApiResponse<CommentResponseDTO> modifyComment(
            @PathVariable("comment_id") Long commentId,
            @RequestBody CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO updatedComment = commentService.modifyComment(commentId, commentRequestDTO);
        return ApiResponse.onSuccess(updatedComment);
    }

    @CrossOrigin
    @Operation(summary = "특정 게시물 댓글 읽기 API")
    @GetMapping("/{post_id}")
    public ApiResponse<List<CommentResponseDTO>> getComments(@PathVariable("post_id") Long postId) {
        List<CommentResponseDTO> comments = commentService.getCommentsByPostId(postId);
        return ApiResponse.onSuccess(comments);
    }
}
