package me.soob.springbootwebservice.web.controller;

import lombok.RequiredArgsConstructor;
import me.soob.springbootwebservice.service.PostsService;
import me.soob.springbootwebservice.web.dto.posts.PostsResponseDto;
import me.soob.springbootwebservice.web.dto.posts.PostsUpdateRequestDto;
import me.soob.springbootwebservice.web.dto.posts.PostsWriteRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/posts")
@RequiredArgsConstructor
@Controller
public class PostsController {

    private final PostsService postsService;

    // 게시글 저장 GET
    @GetMapping("/save")
    public String postSave(Model model) {
        model.addAttribute("postsWriteRequestDto", new PostsWriteRequestDto());

        return "/posts/save";
    }

    // 게시글 저장 POST
    @PostMapping("/save")
    public String postSave(PostsWriteRequestDto postsWriteRequestDto) {
        Long id = postsService.postsWrite(postsWriteRequestDto);

        return "/posts/list/" + id;
    }

    // 게시글 전체 조회
    @GetMapping("/list")
    public String postsList(Model model) {
        List<PostsResponseDto> postsList = postsService.findAllDesc();
        model.addAttribute("postsList", postsList);

        return "/posts/list";
    }

    // 게시글 조회 GET
    @GetMapping("/list/{id}")
    public String postView(@PathVariable Long id, Model model) {
        PostsResponseDto postsResponseDto = postsService.findOne(id);
        model.addAttribute("postsResponseDto", postsResponseDto);

        return "/posts/view";
    }

    // 게시글 수정 GET
    @GetMapping("/list/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto postsResponseDto = postsService.findOne(id);
        model.addAttribute("postsResponseDto", postsResponseDto);

        return "/posts/update";
    }

    // 게시글 수정 PUT
    @PutMapping("/list/update/{id}")
    public String postsUpdate(@PathVariable Long id, PostsUpdateRequestDto postsUpdateRequestDto) {
        postsService.postUpdate(id, postsUpdateRequestDto);

        return "/posts/list/" + id;
    }

    // 게시글 삭제 DELETE
    @DeleteMapping("/list/update/{id}")
    public String postsDelete(@PathVariable Long id) {
        postsService.postsDelete(id);

        return "redirect:/";
    }


}
