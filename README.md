# blog Project
개인공부를 위한 blog 프로젝트 입니다.

- Spring-boot : 2.1.9
- Gradle : 7.4

# 프로젝트를 진행하며, 기록이 필요한 부분을 작성 했습니다.
1. Gradl 의 Compile 과 implementation
  - compile 은 implementation 보다 더 많은 라이브러리를 빌드합니다.
  - 엔터프라이즈 프로젝트 시, compile 보다는 implementation 을 사용하는 것이 더욱 적절 합니다. 
  - 필요하고, 사용하는 부분만 빌드하기 때문입니다. 
  - 무엇보다, compile 는 deprecated 되었습니다. (6.X 버젼)

2. Gradle 버젼에 따른 Lombok 의존성 패턴
- Variable not initialized in the default constructor
- 생성자를 롬복을 이용하여 만들었는데, 막상 variable not initialized in the default constructor라는 에러가 발생했다면, 롬복이 정상적으로 동작하지 않는다는 뜻이다.

1) Gradle 버전 확인
- Gradle을 통해서 롬복 의존성 라이브러리를 추가해주었지만 동작하지 않는다면 자신의 Gradle 버전이 5.x 이상인지 확인해야 한다.
- 그 이유는 Gradle 버전이 올라가면서 Lombok 의존성을 추가하는 방법이 바뀌었기 때문입니다.

- Gradle 5.x 미만
dependencies {
  implementation 'org.projectlombok:lombok'
}

- Gradle 5.x 이상
dependencies {
  compileOnly 'org.projectlombok:lombok'
  annotationProcessor 'org.projectlombok:lombok'
}
