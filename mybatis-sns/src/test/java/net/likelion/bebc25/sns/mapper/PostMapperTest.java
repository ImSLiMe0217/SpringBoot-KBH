package net.likelion.bebc25.sns.mapper;

import net.likelion.bebc25.sns.dto.PostResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class PostMapperTest {
    @Autowired
    private PostMapper postMapper;
    
    @Test
    @DisplayName("게시글 한 건 조회 테스트")
    void findByIdTest() {
        // 존재하는 1번 게시글 조회
        PostResponseDto post = postMapper.findById(1L);
        assertThat(post).isNotNull();
        assertThat(post.id()).isEqualTo(1L);

        // 존재하지 않는 게시글 조회
        PostResponseDto notFoundPost = postMapper.findById(999999999999L);
        assertThat(notFoundPost).isNull();
    }
}
