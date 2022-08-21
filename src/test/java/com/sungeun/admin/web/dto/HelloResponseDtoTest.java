package com.sungeun.admin.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);

        /*
        * assertThat
        * - assertj 라는 테스트 검증 라이브러리의 검증 메소드 입니다.
        * - 검증하고 싶은 대상을 메소드 인자로 받습니다.
        * - 메소드 체이닝이 지원된거 isEqualTo 와 같이 메소드를 이어서 사용 할 수 있습니다.
        *
        * isEqualTo
        * - assertj의 동등 비교 메소드립니다.
        * - assertThat 에 있는 값과 isEqualTo의 값을 비교해서 같을 때만 성공입니다.
        *
        * */

    }
}
