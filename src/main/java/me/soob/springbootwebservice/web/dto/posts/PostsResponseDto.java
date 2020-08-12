package me.soob.springbootwebservice.web.dto.posts;

import lombok.Getter;
import me.soob.springbootwebservice.domain.comment.Comment;
import me.soob.springbootwebservice.domain.like.Like;
import me.soob.springbootwebservice.domain.member.Member;
import me.soob.springbootwebservice.domain.posts.PostType;
import me.soob.springbootwebservice.domain.posts.Posts;

import java.util.List;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private Member member;
    private String content;
    private String file;
    private PostType postType;
    private int cashPoint;
    private int readCount;
    private List<Like> likes;
    private List<Comment> comments;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.member = entity.getMember();
        this.content = entity.getContent();
        this.file = entity.getFile();
        this.postType = entity.getPostType();
        this.cashPoint = entity.getCashPoint();
        this.readCount = entity.getReadCount();
        this.likes = entity.getLikes();
        this.comments = entity.getComments();
    }
}
