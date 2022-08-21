package com.sungeun.admin.domain.user.web;

import com.sungeun.admin.domain.user.web.dto.PostsResponseDto;
import com.sungeun.admin.domain.user.web.dto.PostsSaveRequestDto;
import com.sungeun.admin.domain.user.web.dto.PostsUpdateRequestDto;
import com.sungeun.admin.service.posts.PostsService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private  final PostsService postsService;

    /***
     * 게시글 등록
     * @param requestDto
     * @return
     */
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
