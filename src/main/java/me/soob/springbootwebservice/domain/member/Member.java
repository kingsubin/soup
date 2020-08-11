package me.soob.springbootwebservice.domain.member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.soob.springbootwebservice.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String name;

    private String picture;
    private int levelPoint;
    private int cashPoint;
    private int level;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // 빌더
    @Builder
    public Member(String email, String password, String name, String picture) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.picture = picture;
    }

    // 회원 정보 수정
    public void update(String picture, String name) {
        this.picture = picture;
        this.name = name;
    }

}
