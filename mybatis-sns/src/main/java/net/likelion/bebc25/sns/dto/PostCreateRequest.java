package net.likelion.bebc25.sns.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PostCreateRequest {
    private Long id;

    @NotNull
    private Long memberId;

    @NotBlank(message = "본문을 입력해주세요.")
    @Size(max = 1000, message = "본문은 최대 1000자 이하로만 입력해주세요")
    private String content;

    private String imageUrl;

    public PostCreateRequest(Long memberId, String content, String imageUrl) {
        this.memberId = memberId;
        this.content = content;
        this.imageUrl = imageUrl;
    }
}
