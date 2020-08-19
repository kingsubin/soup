package me.soob.springbootwebservice.web.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.soob.springbootwebservice.domain.member.Member;

@NoArgsConstructor
@Getter @Setter
public class MemberResponseDto {

    private Long id;
    private String email;
    private String name;
    private String password;
    private int levelPoint;
    private int cashPoint;
    private int level;

    public MemberResponseDto(Member entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.password = entity.getPassword();
        this.levelPoint = entity.getLevelPoint();
        this.cashPoint = entity.getCashPoint();
        this.level = entity.getLevel();
    }

}
