package vn.ohana.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.ohana.entities.StatusPost;
import vn.ohana.post.PostService;
import vn.ohana.post.dto.PostResult;

import java.util.List;

@RestController
//@SessionAttribute("cart")
@RequestMapping("/api/post")

public class PostRestController {

    @Autowired
    private PostService postService;

    @GetMapping("/newPost")
    public ResponseEntity<?> getPost() {
        List<PostResult> posts = postService.findAllByStatus(StatusPost.PUBLISHED);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/unapproved")
    public ResponseEntity<?> getUnapproved() {
        List<PostResult> unapproved = postService.findAllByStatus(StatusPost.PENDING_REVIEW);
        return new ResponseEntity<>(unapproved, HttpStatus.OK);
    }

    @PostMapping("/unapproved/{postId}")
    public ResponseEntity<?> doUnapproved(@PathVariable Long postId) {
        postService.unapproved(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/published/{postId}")
    public ResponseEntity<?> doPublished(@PathVariable Long postId) {
        postService.published(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
