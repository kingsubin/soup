package me.soob.springbootwebservice.web.dto.member;

import lombok.Getter;
import me.soob.springbootwebservice.domain.member.Member;

@Getter
public class MemberResponseDto {

    private Long id;
    private String email;
    private String name;
    private String picture;
    private String password;
    private int levelPoint;
    private int cashPoint;
    private int level;

    public MemberResponseDto(Member entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.picture = entity.getPicture();
        this.password = entity.getPassword();
        this.levelPoint = entity.getLevelPoint();
        this.cashPoint = entity.getCashPoint();
        this.level = entity.getLevel();
    }

}
