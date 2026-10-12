package net.likelion.bebc25.jpasns.domain;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberDetailTest {
    @Autowired
    private EntityManager em;

    private Long newMemberId;

    @BeforeEach
    void setup() {
        // 회원 등록
        Member member = new Member("test1@test.com", "1234", "테스터1");
        em.persist(member); // insert 쿼리문 생성 (실행은 안함)

        newMemberId = member.getId();

        em.flush(); // 생성해 둔 쿼리문 실행
        em.clear(); // 캐시 삭제
    }

    @Test
    @DisplayName("회원 세부정보 조회")
    void findMemberDetail() {
        MemberDetail memberDetail = new MemberDetail(newMemberId, new Address("01644", "서울특별시 노원구 상계3.4동 덕릉로 124가길", "23 201호"), "안녕하세요", "N");

        em.persist(memberDetail);

        em.flush();
        em.clear();

        MemberDetail foundMemberDetail = em.find(MemberDetail.class, newMemberId);

        assertThat(foundMemberDetail).isNotNull();
        assertThat(foundMemberDetail.getAddress().getZipcode()).isEqualTo(memberDetail.getAddress().getZipcode());
        assertThat(foundMemberDetail.getAddress().getRoadAddress()).isEqualTo(memberDetail.getAddress().getRoadAddress());
        assertThat(foundMemberDetail.getAddress().getDetailAddress()).isEqualTo(memberDetail.getAddress().getDetailAddress());
    }
}
