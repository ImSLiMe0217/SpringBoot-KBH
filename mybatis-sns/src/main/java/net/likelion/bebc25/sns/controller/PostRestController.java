package net.likelion.bebc25.sns.controller;

import net.likelion.bebc25.sns.dto.PostCreateRequest;
import net.likelion.bebc25.sns.dto.PostResponse;
import net.likelion.bebc25.sns.dto.PostSearchRequest;
import net.likelion.bebc25.sns.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostRestController {
    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 목록 조회
    @GetMapping
    public ResponseEntity<List<PostResponse>> getPostList(
            @ModelAttribute PostSearchRequest searchRequest
    ) {
        // 검색어에 해당하는 게시글 목록 조회
        List<PostResponse> posts = postService.searchPosts(searchRequest);
        return ResponseEntity.ok(posts);
    }

    // 게시글 등록
    @PostMapping
    public ResponseEntity<PostResponse> createPost(
            @RequestHeader("X-Member-Id") Long memberId,
            @RequestBody PostCreateRequest request
    ) {
        request.setMemberId(memberId);
        PostResponse createdPost = postService.createPost(request);
        URI location = URI.create("/api/v1/posts" + createdPost.id());
        return ResponseEntity.created(location).body(createdPost);
    }
}
