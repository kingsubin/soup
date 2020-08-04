package me.soob.springbootwebservice.domain.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.soob.springbootwebservice.domain.BaseTimeEntity;
import me.soob.springbootwebservice.domain.posts.Posts;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSTS_ID")
    private Posts posts;

    private String name;
    private String comment;

}
