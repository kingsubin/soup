package me.soob.springbootwebservice.web.dto.comment;

import lombok.Builder;
import lombok.Getter;
import me.soob.springbootwebservice.domain.comment.Comment;
import me.soob.springbootwebservice.domain.member.Member;
import me.soob.springbootwebservice.domain.posts.Posts;

@Getter
public class CommentWriteRequestDto {

    private String comment;
    private Member member;
    private Posts posts;

    @Builder
    public CommentWriteRequestDto(String comment, Member member, Posts posts) {
        this.comment = comment;
        this.member = member;
        this.posts = posts;
    }

    public Comment toEntity() {
        return Comment.builder()
                .comment(comment)
                .member(member)
                .posts(posts)
                .build();
    }
}
