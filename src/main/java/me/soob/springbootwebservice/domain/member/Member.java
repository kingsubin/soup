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

    //== 빌더 ==
    @Builder
    public Member(String email, String password, String name, String picture) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.picture = picture;
    }

    //== 비즈니스 로직 ==
    // 회원 정보 수정
    public void update(String name, String picture, String password) {
        this.name = name;
        this.picture = picture;
        this.password = password;
    }

    // 게시글 작성시 levelPoint 증가
    public void plusPostsLevelPoint(int levelPoint) {
        this.levelPoint += levelPoint;
    }

    // 게시글 삭제시 levelPoint 감소
    public void minusPostsLevelPoint(int levelPoint) {
        this.levelPoint -= levelPoint;
    }

    // 댓글 작성시 levelPoint 10 고정 증가
    public void plusCommentLevelPoint() {
        this.levelPoint += 10;
    }

    // 댓글 삭제 levelPoint 10 감소
    public void minusCommentLevelPoint() {
        this.levelPoint -= 10;
    }

}
