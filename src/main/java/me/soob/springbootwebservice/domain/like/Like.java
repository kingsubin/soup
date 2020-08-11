package me.soob.springbootwebservice.domain.like;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.soob.springbootwebservice.domain.comment.Comment;
import me.soob.springbootwebservice.domain.member.Member;
import me.soob.springbootwebservice.domain.posts.Posts;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "likes")
public class Like {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Enumerated(EnumType.STRING)
    private LikeType likeType;
}
