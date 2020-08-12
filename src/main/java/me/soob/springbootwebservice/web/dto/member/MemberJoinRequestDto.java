package me.soob.springbootwebservice.web.dto.member;

import lombok.Builder;
import lombok.Getter;
import me.soob.springbootwebservice.domain.member.Member;

@Getter
public class MemberJoinRequestDto {

    private String email;
    private String password;
    private String name;
    private String picture;

    @Builder
    public MemberJoinRequestDto(String email, String password, String name, String picture) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.picture = picture;
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .picture(picture)
                .build();
    }

}
