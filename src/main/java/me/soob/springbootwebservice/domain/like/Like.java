package me.soob.springbootwebservice.domain.like;

import lombok.AccessLevel;
import lombok.Builder;
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

    // 빌더
    @Builder
    public Like(Member member, Posts posts, Comment comment) {
        this.member = member;
        this.posts = posts;
        this.comment = comment;
    }
}
