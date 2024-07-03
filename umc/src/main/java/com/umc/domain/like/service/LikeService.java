package com.umc.domain.like.service;

import com.umc.domain.like.entity.Like;
import com.umc.domain.like.repository.LikeRepository;
import com.umc.domain.post.entity.Post;
import com.umc.domain.post.repository.PostsRepository;
import com.umc.domain.user.entity.Member;
import com.umc.domain.user.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostsRepository postsRepository;

    public long countLikesByPostId(Long postId) {
        return likeRepository.countByPostId(postId);
    }

    public boolean isPostLikedByUser(Long memberId, Long postId) {//레코드생성
        return likeRepository.existsByMemberIdAndPostId(memberId, postId);
    }


    public boolean toggleLike(Long memberId, Long postId) {//레코드수정
        Like like = likeRepository.findByMemberIdAndPostId(memberId, postId);
        if (like == null) {
            Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));
            Post post = postsRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));
            like = Like.builder()
                    .member(member)
                    .post(post)
                    .liked(true)
                    .build();
            likeRepository.save(like);
            return true; //좋아요 눌림
        } else {
            if (like.isLiked()) {
                likeRepository.deleteByMemberIdAndPostId(memberId, postId); // 좋아요 취소: 레코드 삭제
                return false;
            } else {
                like.setLiked(true); //좋아요 눌림: 플래그 업데이트
                likeRepository.save(like);
                return true;
            }
        }
    }

}