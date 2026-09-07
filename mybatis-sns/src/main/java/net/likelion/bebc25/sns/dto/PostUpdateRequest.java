package net.likelion.bebc25.sns.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record PostUpdateRequest(
        @NotBlank(message = "본문을 입력해주세요.")
        @Size(max = 1000, message = "본문은 최대 1000자 이하로만 입력해주세요")
        String content,

        String imageUrl
) {
    
}