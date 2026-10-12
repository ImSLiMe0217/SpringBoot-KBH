package net.likelion.bebc25.jpasns.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDetail {
    @Id
    Long memberId;

    @Embedded
    Address address;

    @Column(columnDefinition = "TEXT")
    String Introduction;

    @Column(length = 1, nullable = false)
    String marketing_agreed;

    public MemberDetail(Long memberId, Address address, String introduction) {
        this(memberId, address, introduction, "N");
    }

    public MemberDetail(Long memberId, Address address, String introduction, String marketing_agreed) {
        this.memberId = memberId;
        this.address = address;
        Introduction = introduction;
        this.marketing_agreed = marketing_agreed;
    }
}
