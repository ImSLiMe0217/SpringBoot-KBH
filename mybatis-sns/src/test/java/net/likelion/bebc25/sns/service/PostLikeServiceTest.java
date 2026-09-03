package net.likelion.bebc25.sns.service;

import net.likelion.bebc25.sns.dto.LikeToggleResponseDto;
import net.likelion.bebc25.sns.dto.PostResponseDto;
import net.likelion.bebc25.sns.mapper.PostLikeMapper;
import net.likelion.bebc25.sns.mapper.PostMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
@Transactional
public class PostLikeServiceTest {
    @Autowired
    private PostLikeService postLikeService;

    // 실제 PostMapper의 동작은 유지되지만 필요한 경우 특정 메서드만 모킹하여 강제로 예외를 발생 시킬 수 있음
    @MockitoSpyBean
    private PostMapper postMapper;

    @Autowired
    private PostLikeMapper postLikeMapper;

    @Test
    @DisplayName("좋아요 토글, 카운트 수 변경 테스트")
    void toggleLikeAddTest() {
        // given: 1번 회원이 2번 게시글에 좋아요 시도
        Long memberId = 1L;
        Long postId = 2L;

        PostResponseDto beforePost = postMapper.findById(postId);
        int beforeLikeCount = beforePost.likeCount();
        boolean beforeLiked = postLikeMapper.countLike(memberId, postId) > 0;

        // when: 좋아요 토글
        LikeToggleResponseDto result = postLikeService.toggleLike(memberId, postId);
        PostResponseDto afterPost = postMapper.findById(postId);
        boolean afterLiked = postLikeMapper.countLike(memberId, postId) > 0;
        // then: result의 liked가 true
        if (beforeLiked) {
            assertThat(result.liked()).isFalse();
            assertThat(result.likeCount()).isEqualTo(beforeLikeCount - 1);
            assertThat(afterLiked).isFalse();
            assertThat(afterPost.likeCount()).isEqualTo(beforeLikeCount - 1);
        }
        else {
            assertThat(result.liked()).isTrue();
            assertThat(result.likeCount()).isEqualTo(beforeLikeCount + 1);
            assertThat(afterLiked).isTrue();
            assertThat(afterPost.likeCount()).isEqualTo(beforeLikeCount + 1);
        }
    }

    @Test
    @DisplayName("좋아요 토글, 카운트 변경 중 예외 발생 테스트")
    void toggleLikeRollbackTest() {
        // given: 1번 회원이 2번 게시글에 좋아요 시도
        Long memberId = 1L;
        Long postId = 2L;

        doThrow();
        // when: 좋아요 토글
        LikeToggleResponseDto result = postLikeService.toggleLike(memberId, postId);
    }
}
