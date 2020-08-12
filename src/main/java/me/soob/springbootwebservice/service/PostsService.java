package me.soob.springbootwebservice.service;

import lombok.RequiredArgsConstructor;
import me.soob.springbootwebservice.domain.posts.PostType;
import me.soob.springbootwebservice.domain.posts.Posts;
import me.soob.springbootwebservice.domain.posts.PostsRepository;
import me.soob.springbootwebservice.web.dto.posts.PostsResponseDto;
import me.soob.springbootwebservice.web.dto.posts.PostsUpdateRequestDto;
import me.soob.springbootwebservice.web.dto.posts.PostsWriteRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    /**
     * 게시글 1개 조회
     */
    public PostsResponseDto findOne(Long postsId) {
        Posts entity = postsRepository.findById(postsId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + postsId));

        return new PostsResponseDto(entity);
    }

    /**
     * 모든 게시글 조회
     * PostType에 따른 게시글 조회도 따로 생성해야함 (쿼리문 새로 작성 ? )
     */
    public List<PostsResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 게시글 작성
      */
    @Transactional
    public Long postsWrite(PostsWriteRequestDto postsDto) {
        int levelPoint = validatePostType(postsDto.getPostType());
        postsDto.getMember().plusPostsLevelPoint(levelPoint);

        return postsRepository.save(postsDto.toEntity()).getId();
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public Long postUpdate(Long postsId, PostsUpdateRequestDto postsDto) {
        Posts findPosts = postsRepository.findById(postsId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + postsId));
        findPosts.update(postsDto.getTitle(), postsDto.getContent(), postsDto.getFile());

        return postsId;
    }

    /**
     * 게시글 삭제
     * 게시글 작성시 증가했던 LP 만큼 감소
     */
    @Transactional
    public void postsDelete(Long postsId) {
        Posts findPosts = postsRepository.findById(postsId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + postsId));
        int levelPoint = validatePostType(findPosts.getPostType());
        findPosts.getMember().minusPostsLevelPoint(levelPoint);

        postsRepository.delete(findPosts);
    }

    /**
     * PostType 검증 메소드 , PostType에 따라 LP 변동량이 다르다
     * INFO_ +30, TALK_ +10, REVIEW_ +30, QNA_ +10, FLEA_ +10, SHOP_,MANAGE_ +0름
     */
    public int validatePostType(PostType postType) {
        int levelPoint = 0;
        switch (postType) {
            case REVIEW_FOOD: case REVIEW_JOB: case INFO_JOB: case INFO_STUDY:
                levelPoint = 30;
                break;
            case TALK_FREE: case TALK_SUTDY: case QNA_CHARGE: case QNA_FREE:
            case FLEA_BUY: case FLEA_SELL:
                levelPoint = 10;
                break;
        }
        return levelPoint;
    }
}
