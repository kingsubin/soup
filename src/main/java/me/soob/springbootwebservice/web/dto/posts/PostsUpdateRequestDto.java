package me.soob.springbootwebservice.web.dto.posts;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostsUpdateRequestDto {

    private String title;
    private String content;
    private String file;

    @Builder
    public PostsUpdateRequestDto(String title, String content, String file) {
        this.title = title;
        this.content = content;
        this.file = file;
    }
}
