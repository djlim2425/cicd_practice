package com.umc.domain.like.controller;

import com.umc.common.response.ApiResponse;
import com.umc.domain.like.dto.LikeRequestDTO;
import com.umc.domain.like.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {


    @Autowired
    private LikeService likeService;


    @GetMapping("/count/{postId}")
    public ApiResponse<Long> countLikesByPostId(@PathVariable Long postId) {
        long likeCount = likeService.countLikesByPostId(postId);
        return ApiResponse.onSuccess(likeCount);
    }


    @GetMapping("/isLiked")
    public ApiResponse<Boolean> isPostLikedByUser(@RequestParam Long memberId, @RequestParam Long postId) {
        boolean isLiked = likeService.isPostLikedByUser(memberId, postId);
        return ApiResponse.onSuccess(isLiked);
    }


    @PostMapping("/toggle")
    public ApiResponse<Boolean> toggleLike(@RequestBody LikeRequestDTO likeDTO) {
        boolean isLiked = likeService.toggleLike(likeDTO.getMember_id(), likeDTO.getPost_id());
        String message = isLiked ? "Post liked successfully" : "Post unliked successfully";
        return ApiResponse.onSuccess(isLiked);
    }
}

