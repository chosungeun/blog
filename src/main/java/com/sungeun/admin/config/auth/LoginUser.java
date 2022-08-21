package com.sungeun.admin.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 중복되는 코드 어노테이션 만들기
 * @Target(ElementType.PARAMETER)
 * 이 어노테이션이 생성될 수 있는 위치를 지정합니다.
 * PARAMETER 로 지정했으니, 메소드의 파라미터로 선언된 객체에서만 사용할 수 있습니다.
 *
 * @interface
 * 이 파일은 어노테이션 클래스로 지정합니다.
 * LoginUser 라는 이름을 가진 어노테이션이 생성되었다고 보면 됩니다.
 * */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}