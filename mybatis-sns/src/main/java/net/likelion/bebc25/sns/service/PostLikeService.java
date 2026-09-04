package net.likelion.bebc25.sns.service;

import net.likelion.bebc25.sns.dto.LikeToggleResponseDto;

public interface PostLikeService {

    // 좋아요 상태 토글 (미등록 시 등록, 기등록 시 취소)
    LikeToggleResponseDto toggleLike(Long memberId, Long postId);
}
