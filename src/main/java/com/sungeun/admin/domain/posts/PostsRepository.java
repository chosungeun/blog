package com.sungeun.admin.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/***
 * 보통 ibatis 나 MyBatis 등에서 Dao 라고 불리는 DB Layer 접근자 입니다.
 * JPA 에선 Repository 라고 부르며 인터페이스로 생성합니다.
 * 단순히 인터페이스를 생성 후, JpaRepository<Entity, PK타입> 를 상속하면
 * 기본적인 CRUD 메소드가 자동으로 생성됩니다.
 */

public interface PostsRepository extends JpaRepository<Posts, Long>{
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}

