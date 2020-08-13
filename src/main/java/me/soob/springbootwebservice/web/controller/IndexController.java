package me.soob.springbootwebservice.web.controller;

import lombok.RequiredArgsConstructor;
import me.soob.springbootwebservice.service.MemberService;
import me.soob.springbootwebservice.service.PostsService;
import me.soob.springbootwebservice.web.dto.member.MemberResponseDto;
import me.soob.springbootwebservice.web.dto.posts.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final MemberService memberService;
    private final PostsService postsService;

    /**
     * 회원 가입
     * 로그인
     *
     * 게시글 작성
     * 게시글 목록
     * 게시글 수정
     */

    @GetMapping("/")
    public String index() {
        return "index";
    }

    // 회원 가입
    @GetMapping("/members/join")
    public String memberSave() {
        return "/members/join";
    }

    // 회원 로그인
    @GetMapping("/members/login")
    public String memberLogin() {
        return "/members/login";
    }

    // 회원 정보 수정
    @GetMapping("/members/update/{id}")
    public String memberUpdate(@PathVariable Long id, Model model) {
        MemberResponseDto dto = memberService.findOne(id);
        model.addAttribute("member", dto);

        return "/members/update";
    }

    // 회원 목록
    @GetMapping("/members")
    public String members() {
        return "/members/list";
    }

    // ================================================================================================

    // 게시글 저장
    @GetMapping("/posts/save")
    public String postSave() {
        return "/posts/save";
    }

    // 게시글 수정 
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findOne(id);
        model.addAttribute("post", dto);

        return "/posts/update";
    }

}
