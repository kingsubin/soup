package me.soob.springbootwebservice.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.soob.springbootwebservice.domain.member.Member;

@Getter
@NoArgsConstructor
public class MemberDto {

    private String email;
    private String password;
    private String name;
    private String picture;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .picture(picture)
                .build();
    }

    @Builder
    public MemberDto(String email, String password, String name, String picture) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.picture = picture;
    }

    public MemberDto(Member entity) {
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.name = entity.getName();
        this.picture = entity.getPicture();
    }

}
