package me.soob.springbootwebservice.domain.posts;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.soob.springbootwebservice.domain.BaseTimeEntity;
import me.soob.springbootwebservice.domain.comment.Comment;
import me.soob.springbootwebservice.domain.like.Like;
import me.soob.springbootwebservice.domain.member.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posts_id")
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String title;

    private String file;
    private int readCount;
    private int cashPoint;

    @Enumerated(EnumType.STRING)
    private PostType postType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    // 빌더
    @Builder
    public Posts(String title, String content, Member member, String file, PostType postType) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.file = file;
        this.postType = postType;
    }

    // == 연관관계 편의 ==

    // == 비즈니스 로직 ==
    // 게시글 수정
    public void update(String title, String content, String file) {
        this.title = title;
        this.content = content;
        this.file = file;
    }

}
