package me.soob.springbootwebservice.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.soob.springbootwebservice.domain.BaseTimeEntity;
import me.soob.springbootwebservice.domain.posts.Posts;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<Posts> Posts = new ArrayList<Posts>();

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Column(length = 30, nullable = false)
    private String email;

    @Column(length = 30, nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String name;

    private int cp;
    private int lp;
    private int level;

    @Builder
    public Member(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

}
