package me.soob.springbootwebservice.web.controller;

import lombok.RequiredArgsConstructor;
import me.soob.springbootwebservice.service.MemberService;
import me.soob.springbootwebservice.web.dto.member.MemberJoinRequestDto;
import me.soob.springbootwebservice.web.dto.member.MemberResponseDto;
import me.soob.springbootwebservice.web.dto.member.MemberUpdateReqeustDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    // 회원 가입 GET
    @GetMapping("/members/join")
    public String memberJoin(Model model) {
        model.addAttribute("memberJoinRequestDto", new MemberJoinRequestDto());

        return "/members/join";
    }

    // 회원 가입 POST
    @PostMapping("/members/join")
    public Long memberJoin(@RequestBody MemberJoinRequestDto memberJoinRequestDto) {
        return memberService.join(memberJoinRequestDto);
    }

    // 내 정보 GET
    @GetMapping("/members/myinfo/{id}")
    public String memberGet(@PathVariable Long id, Model model) {
        MemberResponseDto memberResponseDto = memberService.findOne(id);
        model.addAttribute("memberResponseDto", memberResponseDto);

        return "/members/myinfo";
    }

    // 내 정보 수정 PUT
    @PutMapping("/members/myinfo/{id}")
    public Long memberUpdate(@PathVariable Long id, @RequestBody MemberUpdateReqeustDto memberUpdateReqeustDto) {
        return memberService.update(id, memberUpdateReqeustDto);
    }

    // 회원 탈퇴 DELETE
    @DeleteMapping("/members/myinfo/{id}")
    public Long memberDelete(@PathVariable Long id) {
        memberService.delete(id);

        return id;
    }

}
