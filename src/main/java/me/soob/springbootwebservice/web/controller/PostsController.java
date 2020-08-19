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

@RequiredArgsConstructor
@Controller
public class PostsController {

    private final PostsService postsService;

    // 게시글 저장 GET
    @GetMapping("/posts/save")
    public String postSave(Model model) {
        model.addAttribute("postsWriteRequestDto", new PostsWriteRequestDto());

        return "/posts/save";
    }

    // 게시글 저장 POST
    @PostMapping("/posts/save")
    public Long postSave(@RequestBody PostsWriteRequestDto postsWriteRequestDto) {
        return postsService.postsWrite(postsWriteRequestDto);
    }

    // 게시글 전체 조회
    @GetMapping("/posts/list")
    public String postsList(Model model) {
        List<PostsResponseDto> postsList = postsService.findAllDesc();
        model.addAttribute("postsList", postsList);

        return "/posts/list";
    }

    // 게시글 조회 GET
    @GetMapping("/posts/list/{id}")
    public String postView(@PathVariable Long id, Model model) {
        PostsResponseDto postsResponseDto = postsService.findOne(id);
        model.addAttribute("postsResponseDto", postsResponseDto);

        return "/posts/view";
    }

    // 게시글 수정 GET
    @GetMapping("/posts/list/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findOne(id);
        model.addAttribute("post", dto);

        return "/posts/update";
    }

    // 게시글 수정 PUT
    @PutMapping("/posts/list/update/{id}")
    public Long postsUpdate(@PathVariable Long id, @RequestBody PostsUpdateRequestDto postsUpdateRequestDto) {
        return postsService.postUpdate(id, postsUpdateRequestDto);
    }

    // 게시글 삭제 DELETE
    @DeleteMapping("/posts/list/update/{id}")
    public Long postsDelete(@PathVariable Long id) {
        postsService.postsDelete(id);

        return id;
    }


}
