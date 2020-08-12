package me.soob.springbootwebservice.web.dto.comment;

import lombok.Getter;
import me.soob.springbootwebservice.domain.comment.Comment;
import me.soob.springbootwebservice.domain.like.Like;
import me.soob.springbootwebservice.domain.member.Member;
import me.soob.springbootwebservice.domain.posts.Posts;

import java.util.List;

@Getter
public class CommentResponseDto {

    private Long id;
    private Posts posts;
    private Member member;
    private String content;
    private List<Like> likes;

    public CommentResponseDto(Comment entity) {
        this.id = entity.getId();
        this.posts = entity.getPosts();
        this.member = entity.getMember();
        this.content = entity.getComment();
        this.likes = entity.getLikes();
    }
}
