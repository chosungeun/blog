package com.sungeun.admin.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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

    /**
     * 1. Param
     * API 테스트 할 때 사용될 요청 파라미터를 설정 합니다.
     * 단, 값은 String만 허용됩니다.
     * 그래서 숫자/날자 등의 데이터도 등록 할 때는 문자여로 변경해야 합니다.
     *
     * 2. jsonPath
     * JSON 응답갑승ㄹ 필드별로 검증할 수 있는 메소드 입니다.
     * $를 기준으로 필드명을 명시합니다.
     * 여기서는 name 와 amount 를 검증하니, $name, $amount 로 검증합니다.
     * */
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                    get("/hello/dto")
                            .param("name", name)
                            .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
