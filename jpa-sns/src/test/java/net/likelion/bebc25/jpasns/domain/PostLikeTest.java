package net.likelion.bebc25.jpasns.domain;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class PostLikeTest {
    @Autowired
    private EntityManager em;

    private Long testMemberId;
    private Long testPostId;

    @BeforeEach
    void setUp() {
        // 회원 등록
        Member member = new Member("user1@test.com", "1234", "테스터1");
        em.persist(member);
        this.testMemberId = member.getId();

        // 2. 게시글 등록
        Post post = new Post(member, "게시글 등록 테스트 중");
        em.persist(post);
        this.testPostId = post.getId();

        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("좋아요 등록 및 조회")
    void postLikeSaveAndFind() {
        Member member = em.find(Member.class, testMemberId);
        Post post = em.find(Post.class, testPostId);

        PostLike postLike = new PostLike(post, member);
        em.flush();
        em.clear();

        PostLike foundPostLike = em.find(PostLike.class, testPostId);
    }
}
