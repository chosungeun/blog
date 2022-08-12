package com.sungeun.admin;

import com.sungeun.admin.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
// import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
* @RunWith
* 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킵니다.
* 여기서는 SpringRunner 라는 스프링 실행자를 사용합니다.
* 즉, 스프링 부트 테스트와 Junit 사이에 연결자 역할을 합니다. (중요)
* */

/*
* 여러 스트링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션 입니다.
* 선언할 경우, @Controller, @ControllerAdvice 등은 사용할 수 없습니다.
* 단, @Service, @Component, @Repository 등은 사용할 수 없습니다.
* 여기서는 컨트롤러만 사용하기 때문에 선언합니다.
* */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers =  HelloController.class)
public class HelloControllerTest {

    @Autowired
    private  MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }
}
