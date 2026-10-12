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
public class MemberPostTest {
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
    @DisplayName("회원 조회")
    void memberFine() {
        Member findMember = em.find(Member.class, newMemberId);

        assertThat(findMember).isNotNull();
        assertThat(findMember.getEmail()).isEqualTo("test1@test.com");
        assertThat(findMember.getPassword()).isEqualTo("1234");
        assertThat(findMember.getNickname()).isEqualTo("테스터1");
        assertThat(findMember.getRole()).isEqualTo(Role.USER);
    }

    @Test
    @DisplayName("게시글 등록 및 조회")
    void postSaveAndFine() {
        Member member = em.find(Member.class, newMemberId);
        // 게시글 등록
        Post post = new Post(member, "첫번째 게시글");
        em.persist(post);
        em.flush();
        em.clear();

        // 등록한 게시글 조회
        Post findPost = em.find(Post.class, post.getId());

        assertThat(findPost).isNotNull();
        assertThat(findPost.getContent()).isEqualTo(post.getContent());
        assertThat(findPost.getMember().getId()).isEqualTo(newMemberId);
    }

    @Test
    @DisplayName("BaseTimeEntity 상속 동작 확인")
    void baseTimeAuditing() {
        Member findMember = em.find(Member.class, newMemberId);
        assertThat(findMember.getCreatedAt()).isNotNull();
        assertThat(findMember.getUpdatedAt()).isNotNull();
        assertThat(findMember.getCreatedAt()).isEqualTo(findMember.getUpdatedAt());

        findMember.changeNickname("테스터2");
        em.flush();
        em.clear();

        Member changedMember = em.find(Member.class, newMemberId);
        assertThat(changedMember.getNickname()).isEqualTo("테스터2");
        assertThat(changedMember.getUpdatedAt()).isNotEqualTo(findMember.getUpdatedAt());
        assertThat(changedMember.getUpdatedAt()).isNotEqualTo(changedMember.getCreatedAt());
    }
}
