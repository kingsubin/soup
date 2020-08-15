package me.soob.springbootwebservice.web.dto.member;

import lombok.Builder;
import lombok.Getter;
import me.soob.springbootwebservice.domain.member.Member;

@Getter
public class MemberJoinRequestDto {

    private String email;
    private String password;
    private String name;

    @Builder
    public MemberJoinRequestDto(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
    }

    // password encode를 위한 set method
    public void encodePassword(String password) {
        this.password = password;
    }

}
