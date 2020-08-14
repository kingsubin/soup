package me.soob.springbootwebservice.domain.posts;

import lombok.Getter;

@Getter
public enum PostType {
    // 정보
    INFO_STUDY, INFO_JOB,

    // 커뮤니티
    TALK_FREE, TALK_SUTDY,

    // 후기
    REVIEW_FOOD, REVIEW_JOB,

    // 질문
    QNA_CHARGE, QNA_FREE,

    // 플리마켓
    FLEA_SELL, FLEA_BUY,

    // 상점
    SHOP_CP,

    // 운영관리
    MANAGE_NOTICE, MANAGE_EVENT
}
