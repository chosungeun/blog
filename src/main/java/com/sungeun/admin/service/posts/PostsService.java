package com.sungeun.admin.service.posts;

import com.sungeun.admin.domain.posts.PostsRepository;
import com.sungeun.admin.domain.user.web.dto.PostsSaveRequestDto;
import com.sungeun.admin.domain.user.web.dto.PostsListResponseDto;
import com.sungeun.admin.domain.user.web.dto.PostsResponseDto;
import com.sungeun.admin.domain.user.web.dto.PostsUpdateRequestDto;
import com.sungeun.admin.domain.posts.Posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    /*
    * @Transactional(readOnly = true)
    * 트랜잭션 범위는 유지하된, 조회 속도가 개선됩니다. (등록, 수정, 삭제 기능은 없습니다)
    * .map(PostsListResponseDto::new)
    * 는 .map(posts -> new PostsListResponseDto(posts)) 와 같습니다.
    * */
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 게시글이 없습니다. id=" +id));

        postsRepository.delete(posts);
    }
}
