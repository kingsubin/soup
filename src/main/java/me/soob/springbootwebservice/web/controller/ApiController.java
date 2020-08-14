package me.soob.springbootwebservice.web.controller;

import lombok.RequiredArgsConstructor;
import me.soob.springbootwebservice.service.MemberService;
import me.soob.springbootwebservice.service.PostsService;
import me.soob.springbootwebservice.web.dto.member.MemberJoinRequestDto;
import me.soob.springbootwebservice.web.dto.posts.PostsWriteRequestDto;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ApiController {

    private final PostsService postsService;
    private final MemberService memberService;

    // 회원 가입
    @PostMapping("/api/members/join")
    public Long join(@RequestBody MemberJoinRequestDto requestDto) {
        return memberService.join(requestDto);
    }

    // 게시글 작성
    @PostMapping("/api/posts/save")
    public String save(@ModelAttribute PostsWriteRequestDto requestDto) {
        postsService.postsWrite(requestDto);
        return "redirect:/posts/list";
    }

//
//    // 게시글 수정
//    @PutMapping("/api/v1/posts/{id}")
//    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
//        return postsService.update(id, requestDto);
//    }
//
//    @GetMapping("/api/v1/posts/{id}")
//    public PostsResponseDto findById(@PathVariable Long id) {
//        return postsService.findById(id);
//    }
//
//    @DeleteMapping("/api/v1/posts/{id}")
//    public Long delete(@PathVariable Long id) {
//        postsService.delete(id);
//        return id;
//    }
}
