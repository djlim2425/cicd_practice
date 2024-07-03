package com.umc.domain.like.repository;

import com.umc.domain.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    long countByPostId(Long postId);
    boolean existsByMemberIdAndPostId(Long memberId, Long postId);
    Like findByMemberIdAndPostId(Long memberId, Long postId);
    void deleteByMemberIdAndPostId(Long memberId, Long postId);
}
