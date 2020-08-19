package me.soob.springbootwebservice.web.controller;

import lombok.RequiredArgsConstructor;
import me.soob.springbootwebservice.service.MemberService;
import me.soob.springbootwebservice.web.dto.member.MemberJoinRequestDto;
import me.soob.springbootwebservice.web.dto.member.MemberResponseDto;
import me.soob.springbootwebservice.web.dto.member.MemberUpdateReqeustDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/members")
@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    // 회원 가입 GET
    @GetMapping("/join")
    public String memberJoin(Model model) {
        model.addAttribute("memberJoinRequestDto", new MemberJoinRequestDto());

        return "members/join";
    }

    // 회원 가입 POST
    @PostMapping("/join")
    public String memberJoin(MemberJoinRequestDto memberJoinRequestDto) {
        memberService.join(memberJoinRequestDto);

        return "redirect:/";
    }

    // 내 정보 GET
    @GetMapping("/myinfo/{id}")
    public String memberGet(@PathVariable Long id, Model model) {
        MemberResponseDto memberResponseDto = memberService.findOne(id);
        model.addAttribute("memberResponseDto", memberResponseDto);

        return "/members/myinfo";
    }

    // 내 정보 수정 GET
    @GetMapping("/myinfo/update/{id}")
    public String memberUpdate(@PathVariable Long id, Model model) {
        MemberResponseDto memberResponseDto = memberService.findOne(id);
        model.addAttribute("memberResponseDto", memberResponseDto);
        model.addAttribute("memberUpdateRequestDto",new MemberUpdateReqeustDto());

        return "/members/update";
    }

    // 내 정보 수정 PUT
//    @RequestMapping(value = "/myinfo/update/{id}", method = RequestMethod.PUT)
    @PutMapping("/myinfo/update/{id}")
    public String memberUpdate(@PathVariable Long id, MemberUpdateReqeustDto memberUpdateReqeustDto) {
        memberService.update(id, memberUpdateReqeustDto);

        return "/members/myinfo/" + id;
    }

    // 회원 탈퇴 DELETE
    @DeleteMapping("/myinfo/update/{id}")
    public String memberDelete(@PathVariable Long id) {
        memberService.delete(id);

        return "redirect:/";
    }

}
