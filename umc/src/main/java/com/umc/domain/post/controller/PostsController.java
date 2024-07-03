package com.umc.domain.post.controller;

import com.umc.common.response.ApiResponse;
import com.umc.domain.post.dto.PostRequestDTO;
import com.umc.domain.post.dto.PostResponseDTO;
import com.umc.domain.post.dto.PostResponseListDTO;
import com.umc.domain.post.entity.Post;
import com.umc.domain.post.service.PostsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostsController {

    private final PostsService postsService;

    @CrossOrigin
    @Operation(summary = "게시글 작성 API")
    @PostMapping("/")
    public ApiResponse<PostResponseDTO> addPosts(@RequestBody PostRequestDTO postRequestDTO) {
        PostResponseDTO createdPost = postsService.addPost(postRequestDTO);
        return ApiResponse.onSuccess(createdPost);
    }

    @CrossOrigin
    @Operation(summary = "게시글 수정 API")
    @PostMapping("/{post_id}")
    public ApiResponse<PostResponseDTO> modifyPosts(@PathVariable("post_id") Long postId, @RequestBody PostRequestDTO postRequestDTO) {
        PostResponseDTO updatedPost = postsService.modifyPost(postId, postRequestDTO);
        return ApiResponse.onSuccess(updatedPost);
    }

    @CrossOrigin
    @Operation(summary = "게시글 목록 읽기 API")
    @GetMapping("/")
    public ApiResponse<List<PostResponseDTO>> getAllPosts() {
        List<PostResponseDTO> posts = postsService.getAllPosts();
        return ApiResponse.onSuccess(posts);
    }

    @CrossOrigin
    @Operation(summary = "게시글 세부내용 API")
    @GetMapping("/{post_id}")
    public ApiResponse<PostResponseDTO> getPosts(@PathVariable("post_id") Long postId) {
        PostResponseDTO post = postsService.getPost(postId);
        return ApiResponse.onSuccess(post);
    }

    //paging
    @CrossOrigin
    @Operation(summary = "특정 게시판의 게시물 목록 조회 API", description = "특정 게시판의 게시물 목록 조회 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @GetMapping("/{board_id}/posts")
    @Parameter(name = "board_id", description = "게시판 id, path variable 입니다")
    public ApiResponse<PostResponseListDTO> getBoardPosts(@PathVariable(name = "board_id") Long boardId,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Optional<Page<Post>> postPageOptional = postsService.getPostsByBoardId(boardId, pageable);


        if (postPageOptional.isPresent()) {
            Page<Post> postPage = postPageOptional.get();
            PostResponseListDTO responseListDTO = new PostResponseListDTO(postPage.getContent(), postPage.getNumber(), postPage.getTotalPages(), postPage.getTotalElements(), postPage.isFirst(), postPage.isLast());
            return ApiResponse.onSuccess(responseListDTO);
        } else {
            return ApiResponse.onFailure("NOT_FOUND", "Board not found", null);
        }
    }

    @CrossOrigin
    @Operation(summary = "게시글 삭제 API")
    @DeleteMapping("/{post_id}")
    public ApiResponse<Void> deletePosts(@PathVariable("post_id") Long postId) {
        postsService.deletePost(postId);
        return ApiResponse.onSuccess(null);
    }
}
