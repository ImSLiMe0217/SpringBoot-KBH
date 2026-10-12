package net.likelion.bebc25.jpasns.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.likelion.bebc25.jpasns.common.entity.BaseTimeEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    // Lazy     (지연 로딩): "권장됨", 조회 시점에서 Post 정보만 조회, Member객체는 Proxy 객체로 만들어 두고 getMember 메서드 실행 시 Member 정보를 조회해 옴
    // Eager    (즉시 로딩): "기본값", 조회 시점에서 Member의 정보도 Join해서 가져옴, 필요 없을 때도 매번 조회해와서 성능이 느려질 수 있음
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String content;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    private int likeCount;

    // 이미지 첨부 안했을 경우
    public Post(Member member, String content) {
        this(member, content, null);
    }

    // 이미지 첨부 했을 경우
    public Post(Member member, String content, String imageUrl) {
        this.member = member;
        this.content = content;
        this.imageUrl = imageUrl;
        this.likeCount = 0;
    }
}
