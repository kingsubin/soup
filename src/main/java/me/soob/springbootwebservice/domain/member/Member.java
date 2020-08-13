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

    private int levelPoint;
    private int cashPoint;
    private int level;

    //== 빌더 ==
    @Builder
    public Member(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    /**
     * ======================================== 비즈니스 로직 ========================================
     */

    // 회원 정보 수정
    public void update(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * level_point 를 level 에 반영
     * level, level_point 초기값 0
     * 100 -> 200 -> 400 -> 800 -> 1600
     */
    public void changeLevel() {
        int lp = this.levelPoint;
        if (lp >= 1600) {
            this.level = 5;
        } else if (lp >= 800) {
            this.level = 4;
        } else if (lp >= 400) {
            this.level = 3;
        } else if (lp >= 200) {
            this.level = 2;
        } else if (lp >= 100) {
            this.level = 1;
        }
    }

    // 게시글 작성시 levelPoint 증가
    public void plusPostsLevelPoint(int levelPoint) {
        this.levelPoint += levelPoint;
        changeLevel();
    }

    // 게시글 삭제시 levelPoint 감소
    public void minusPostsLevelPoint(int levelPoint) {
        this.levelPoint -= levelPoint;
        changeLevel();
    }

    // 댓글 작성시 levelPoint 10 증가
    public void plusCommentLevelPoint() {
        this.levelPoint += 10;
        changeLevel();
    }

    // 댓글 삭제 levelPoint 10 감소
    public void minusCommentLevelPoint() {
        this.levelPoint -= 10;
        changeLevel();
    }

    // 추천 게시글 levelPoint 20 증가
    public void plusLikePostsLevelPoint() {
        this.levelPoint += 20;
        changeLevel();
    }

    // 추천 삭제 게시글 levelPoint 20 감소
    public void minusLikePostsLevelPoint() {
        this.levelPoint -= 20;
        changeLevel();
    }

    // 추천 댓글 levelPoint 10 증가
    public void plusLikeCommentLevelPoint() {
        this.levelPoint += 10;
        changeLevel();
    }

    // 추천 삭제 댓글 levelPoint 10 감소
    public void minusLikeCommentLevelPoint() {
        this.levelPoint -= 10;
        changeLevel();
    }


}
