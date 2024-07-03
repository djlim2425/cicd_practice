package com.umc.domain.comment.service;

import com.umc.domain.comment.dto.CommentRequestDTO;
import com.umc.domain.comment.dto.CommentResponseDTO;
import com.umc.domain.comment.entity.Comment;
import com.umc.domain.comment.repository.CommentRepository;
import com.umc.domain.post.entity.Post;
import com.umc.domain.user.entity.Member;
import com.umc.domain.post.repository.PostsRepository;
import com.umc.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostsRepository postRepository;
    private final MemberRepository memberRepository;

    public CommentResponseDTO addComment(CommentRequestDTO commentRequestDTO, Long postId, Long memberId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + memberId));

        Comment comment = Comment.builder()
                .post(post)
                .member(member)
                .body(commentRequestDTO.getBody())
                .build();

        Comment savedComment = commentRepository.save(comment);
        return convertToResponseDto(savedComment);
    }

    public CommentResponseDTO modifyComment(Long commentId, CommentRequestDTO commentRequestDTO) {
        Optional<Comment> existingComment = commentRepository.findById(commentId);
        if (existingComment.isPresent()) {
            Comment updatedComment = existingComment.get();
            updatedComment.setBody(commentRequestDTO.getBody());
            Comment savedComment = commentRepository.save(updatedComment);
            return convertToResponseDto(savedComment);
        } else {
            throw new RuntimeException("Comment not found with id: " + commentId);
        }
    }

    public List<CommentResponseDTO> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    private CommentResponseDTO convertToResponseDto(Comment comment) {
        return CommentResponseDTO.builder()
                .comment_id(comment.getId())
                .post_id(comment.getPost().getId())
                .member_id(comment.getMember().getId())
                .nickname(comment.getMember().getNickname())
                .body(comment.getBody())
                .createAt(comment.getCreatedAt())
                .updateAt(comment.getModifiedAt())
                .build();
    }
}
