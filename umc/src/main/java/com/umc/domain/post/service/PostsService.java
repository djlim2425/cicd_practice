package com.umc.domain.post.service;

import com.umc.domain.board.entity.Board;
import com.umc.domain.board.repository.BoardRepository;
import com.umc.domain.post.dto.PostRequestDTO;
import com.umc.domain.post.dto.PostResponseDTO;
import com.umc.domain.post.dto.PostResponseListDTO;
import com.umc.domain.post.entity.Post;
import com.umc.domain.post.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;
    private final BoardRepository boardRepository;

    public PostResponseDTO addPost(PostRequestDTO postRequestDTO) {
        Post post = new Post();
        post.setTitle(postRequestDTO.getTitle());
        post.setBody(postRequestDTO.getBody());
        post.setCreatedAt(LocalDateTime.now());
        Post savedPost = postsRepository.save(post);
        return convertToResponseDto(savedPost);
    }

    public PostResponseDTO modifyPost(Long postId, PostRequestDTO postRequestDTO) {
        Optional<Post> existingPost = postsRepository.findById(postId);
        if (existingPost.isPresent()) {
            Post updatedPost = existingPost.get();
            updatedPost.setTitle(postRequestDTO.getTitle());
            updatedPost.setBody(postRequestDTO.getBody());
            updatedPost.setModifiedAt(LocalDateTime.now());
            Post savedPost = postsRepository.save(updatedPost);
            return convertToResponseDto(savedPost);
        } else {
            throw new RuntimeException("Post not found with id: " + postId);
        }
    }

    public List<PostResponseDTO> getAllPosts() {
        List<Post> posts = postsRepository.findAll();
        return posts.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public Optional<Page<Post>> getPostsByBoardId(Long boardId, Pageable pageable){
        Optional<Board> board = boardRepository.findById(boardId);
        return Optional.of(postsRepository.findByBoard_Id(boardId, pageable));
    }

    public PostResponseDTO getPost(Long postId) {
        Post post = postsRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
        return convertToResponseDto(post);
    }

    public void deletePost(Long postId) {
        postsRepository.deleteById(postId);
    }

    private PostResponseDTO convertToResponseDto(Post post) {
        PostResponseDTO postResponseDto = new PostResponseDTO();
        postResponseDto.setPost_id(post.getId());
        postResponseDto.setTitle(post.getTitle());
        postResponseDto.setCreatedAt(post.getCreatedAt());
        postResponseDto.setModifiedAt(post.getModifiedAt());
        return postResponseDto;
    }
}
