package me.soob.springbootwebservice.web.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class MemberUpdateReqeustDto {

    private String name;
    private String password;

    @Builder
    public MemberUpdateReqeustDto(String name, String password) {
        this.name = name;
        this.password = password;
    }

}
