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
public class CommentTest {
    @Autowired
    private EntityManager em;

    private Long newMemberId;
    private Long newPostId;
    private Long newCommentId;

    @BeforeEach
    void setUp() {
        // 회원 등록
        Member member = new Member("user1@test.com", "1234", "테스터1");
        em.persist(member);
        this.newMemberId = member.getId();

        // 2. 게시글 등록
        Post post = new Post(member, "게시글 등록 테스트 중");
        em.persist(post);
        this.newPostId = post.getId();

        // 3. 댓글 등록
        Comment comment = new Comment(newMemberId, newPostId, "댓글 등록 테스트 중");
        em.persist(comment);
        this.newCommentId = comment.getId();

        em.clear();
    }

    @Test
    @DisplayName("댓글 등록")
//    @Commit   // 테스트 종료 후 저장된 데이터를 롤백시키지 않고 유지
    void create() {
        Comment comment = new Comment(newMemberId, newPostId, "댓글 등록 테스트 2");
        em.persist(comment);
        Long commentId = comment.getId();

        em.clear();

        Comment commentFound = em.find(Comment.class, commentId);
        assertThat(commentFound).isNotNull();
        assertThat(commentFound.getMemberId()).isEqualTo(comment.getMemberId());
        assertThat(commentFound.getPostId()).isEqualTo(comment.getPostId());
    }

    @Test
    @DisplayName("댓글 조회")
    void read() {
        Comment comment = em.find(Comment.class, newCommentId);
        assertThat(comment).isNotNull();
        assertThat(comment.getMemberId()).isEqualTo(newMemberId);
        assertThat(comment.getPostId()).isEqualTo(newPostId);
    }

    @Test
    @DisplayName("댓글 수정")
    void update() {
        Comment targetComment = em.find(Comment.class, newCommentId);

        // JPA에서는 find로 꺼내온 데이터의 속성을 수정하면 자동으로 Update쿼리문이 생성됨
        // 자동으로 실행되진 않고 버퍼에 저장됨 -> "쓰기 지연"
        targetComment.changeContent("댓글 수정 테스트");
        em.flush();
        em.clear();

        Comment updatedComment = em.find(Comment.class, newCommentId);
        assertThat(updatedComment.getContent()).isEqualTo("댓글 수정 테스트");
    }

    @Test
    @DisplayName("댓글 삭제")
    void delete() {
        Comment targetComment = em.find(Comment.class, newCommentId);
        em.remove(targetComment);   // delete 쿼리문 생성, targetComment의 PK값(@Id 어노테이션이 지정된 컬럼의 값을 1차캐시 영역에서 지움
        em.flush();
        em.clear();

        Comment deletedComment = em.find(Comment.class, newCommentId);
        assertThat(deletedComment).isNull();
    }
}
