package com.sungeun.admin.web.domain.posts;

import com.sungeun.admin.domain.posts.Posts;
import com.sungeun.admin.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 별다른 설정 없이 @SpringBootTest 를 사용할 경우, H2 데이터베이스를 자동으로 실행해 줍니다.
 *
 * 이 테스트 역시 실행할 경우, H2가 자동을 실행 됩니다.
 *
 * */


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("sungeun")
                .build());

        /**
         * 1. postsRepository.save()
         * 테이블 posts 에 insert/update 쿼리를 실행합니다.
         * id 값이 있다면 update 가, 없다면 insert 쿼리가 실행됩니다.
         *
         * 2. postsRepository.findAll()
         * 테이블 posts 에 있는 모든 데이터를 조회해오는 메소드 입니다.
         * */

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        // console 찍지
        System.out.println(">>>>>>>>> createDate=" + posts.getCreatedDate() + ", modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }

}
