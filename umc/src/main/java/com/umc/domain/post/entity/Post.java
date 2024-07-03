package com.umc.domain.post.entity;

import com.umc.common.entity.BaseTimeEntity;
import com.umc.domain.board.entity.Board;
import com.umc.domain.comment.entity.Comment;
import com.umc.domain.user.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post  extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "title", length = 20, nullable = false)
    private String title;

    @Column(name = "body", length = 50, nullable = false)
    private String body;

    @OneToMany(mappedBy ="post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
}
