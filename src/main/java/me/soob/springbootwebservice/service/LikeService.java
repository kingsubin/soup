package me.soob.springbootwebservice.service;

import lombok.RequiredArgsConstructor;
import me.soob.springbootwebservice.domain.like.LikeRepository;
import me.soob.springbootwebservice.web.dto.like.LikePostRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LikeService {

    private final LikeRepository likeRepository;

    /**
     * 게시글 추천
     * 자신의 글에 자신이 추천 불가능
     * 추천 중복 여부 확인후 추천 받은 게시글의 작성자 LP 증가
     */
    @Transactional
    public Long LikePosts(LikePostRequestDto likeDto) {

        likeDto.getPosts().getMember().plusLikePostsLevelPoint(); // 추천 받은 게시글의 작성자 LP 증가

        return likeRepository.save(likeDto.toEntity()).getId();
    }

    /**
     * 게시글 추천 취소
     */
    @Transactional
    public void LikeCancelPosts() {
        
    }

    /**
     * 댓글 추천
     */
//    @Transactional
//    public Long LikeComment() {
//
//    }

    /**
     * 댓글 추천 취소
     */
    @Transactional
    public void LikeCancelComment() {

    }

    /**
     * 추천 중복 여부 검사 메소드
     * 추천한 게시글의 추천 List에서 member 검색후 존재시 false, 없을시 true
     * 추천한 게시글의 likes 에서 like.member가 존재하는지 검사
     *
     */
//    public boolean validateDuplicateLike(LikePostRequestDto likeDto) {
//        likeDto.getMember()
//    }


}
