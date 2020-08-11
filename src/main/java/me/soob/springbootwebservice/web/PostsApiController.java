package me.soob.springbootwebservice.web;

import lombok.RequiredArgsConstructor;
import me.soob.springbootwebservice.service.PostsService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

//    @PostMapping("/api/v1/posts")
//    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
//
//        return postsService.save(requestDto);
//    }

//    @GetMapping("/api/v1/posts/{id}")
//    public PostsResponseDto findById(@PathVariable Long id) {
//        return postsService.findById(id);
//    }

}
