package me.soob.springbootwebservice.service;

import lombok.RequiredArgsConstructor;
import me.soob.springbootwebservice.domain.comment.Comment;
import me.soob.springbootwebservice.domain.comment.CommentRepository;
import me.soob.springbootwebservice.web.dto.comment.CommentResponseDto;
import me.soob.springbootwebservice.web.dto.comment.CommentWriteRequestDto;
import me.soob.springbootwebservice.web.dto.posts.PostsResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    /**
     * 댓글 1개 조회
     */
    public CommentResponseDto findOne(Long commentId) {
        Comment findComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id =" + commentId));
        return new CommentResponseDto(findComment);
    }


    /**
     * 댓글 전체 조회
     */
    public List<CommentResponseDto> findAllDesc() {
        return commentRepository.findAllDesc().stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 댓글 작성
     * 모든 게시판에 댓글 작성시 LP +10
     */
    @Transactional
    public Long commentWrite(CommentWriteRequestDto commentDto) {
        commentDto.getMember().plusCommentLevelPoint();
        return commentRepository.save(commentDto.toEntity()).getId();
    }

    /**
     * 댓글 수정
     */
    @Transactional
    public Long commentUpdate(Long commentId, String comment) {
        Comment findComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id =" + commentId));
        findComment.update(comment);

        return commentId;
    }

    /**
     * 댓글 삭제
     */
    @Transactional
    public void commentDelete(Long commentId) {
        Comment findComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id =" + commentId));
        findComment.getMember().minusCommentLevelPoint();

        commentRepository.delete(findComment);
    }

}
