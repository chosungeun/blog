package com.sungeun.admin.web;

import com.sungeun.admin.config.auth.LoginUser;
import com.sungeun.admin.config.auth.dto.SessionUser;
import com.sungeun.admin.service.posts.PostsService;
import com.sungeun.admin.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        // 로그인 성송 시 세션에 SessionUser 를 저장하도록 구성했습니다.
        // 즉, 로그인 성공 시 httpSession.getAttribute("user") 에서 값을 가져올 수 있습니다.
        // SessionUser user = (SessionUser) httpSession.getAttribute("user");

        // 기존에 (User) httpSession.getAttribute("user") 로 가져오던 세션 정보 값이 개선 됨
        // 이제는 어느 컨트롤러 든지 @LoginUser 만 사용하면, 세션정보를 가져올 수 있게 되었습니다.
        model.addAttribute("posts", postsService.findAllDesc());

        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
