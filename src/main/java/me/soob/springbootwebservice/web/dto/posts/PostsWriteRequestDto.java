package me.soob.springbootwebservice.web.dto.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.soob.springbootwebservice.domain.member.Member;
import me.soob.springbootwebservice.domain.posts.PostType;
import me.soob.springbootwebservice.domain.posts.Posts;

@NoArgsConstructor
@Getter
public class PostsWriteRequestDto {

    private String title;
    private String content;
    private String file;
    private PostType postType;
    private Member member;

    @Builder
    public PostsWriteRequestDto(String title, String content, String file, PostType postType, Member member) {
        this.title = title;
        this.content = content;
        this.file = file;
        this.postType = postType;
        this.member = member;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .file(file)
                .postType(postType)
                .member(member)
                .build();
    }
}
