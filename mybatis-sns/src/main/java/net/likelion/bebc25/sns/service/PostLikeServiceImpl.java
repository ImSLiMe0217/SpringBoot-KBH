package net.likelion.bebc25.sns.service;

import lombok.AllArgsConstructor;
import net.likelion.bebc25.sns.dto.LikeToggleResponseDto;
import net.likelion.bebc25.sns.dto.PostResponseDto;
import net.likelion.bebc25.sns.mapper.PostLikeMapper;
import net.likelion.bebc25.sns.mapper.PostMapper;
import org.springframework.stereotype.Service;

@Service
public class PostLikeServiceImpl implements PostLikeService {

    private final PostMapper postMapper;
    private final PostLikeMapper postLikeMapper;

    public PostLikeServiceImpl(PostMapper postMapper, PostLikeMapper postLikeMapper) {
        this.postMapper = postMapper;
        this.postLikeMapper = postLikeMapper;
    }

    @Override
    public LikeToggleResponseDto toggleLike(Long memberId, Long postId) {

        // 1. 대상 게시글의 존재 여부 확인
        PostResponseDto post = postMapper.findById(postId);
        if (post == null) {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + postId);
        }

        // 2. 현재 사용자의 좋아요 등록 여부 확인
        boolean isLiked = postLikeMapper.countLike(memberId, postId) > 0;

        if (isLiked) {
            // 3-1. 이미 등록되어 있을 경우 등록 취소 처리
            // 좋아요 제거 -> 좋아요 수 1 감소
            postLikeMapper.deleteLike(memberId, postId);
            postMapper.decreaseLikeCount(postId);
            return new LikeToggleResponseDto(false, post.likeCount() - 1);
        }
        else {
            // 3-2. 등록되어 있지 않을 경우 등록 처리
            // 좋아요 추가 -> 좋아요 수 1 증가
            postLikeMapper.insertLike(memberId, postId);
            postMapper.increaseLikeCount(postId);
            return new LikeToggleResponseDto(true, post.likeCount() + 1);
        }
    }
}
