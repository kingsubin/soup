package me.soob.springbootwebservice.service;

import lombok.RequiredArgsConstructor;
import me.soob.springbootwebservice.domain.posts.PostsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostsService {

    private final PostsRepository postsRepository;

//    public PostsResponseDto findById(Long id) {
//        Posts entity = postsRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
//
//        return new PostsResponseDto(entity);
//    }

}
