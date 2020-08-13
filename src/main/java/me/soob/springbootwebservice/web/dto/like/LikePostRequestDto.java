package me.soob.springbootwebservice.web.dto.like;

import lombok.Builder;
import lombok.Getter;
import me.soob.springbootwebservice.domain.like.Like;
import me.soob.springbootwebservice.domain.member.Member;
import me.soob.springbootwebservice.domain.posts.Posts;

@Getter
public class LikePostRequestDto {

    private Member member;
    private Posts posts;

    @Builder
    public LikePostRequestDto(Member member, Posts posts) {
        this.member = member;
        this.posts = posts;
    }

    public Like toEntity() {
        return Like.builder()
                .member(member)
                .posts(posts)
                .build();
    }
}
