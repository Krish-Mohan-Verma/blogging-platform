package com.anon.blogging.Controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anon.blogging.DTO.PostDTO;
import com.anon.blogging.Entity.Post;
import com.anon.blogging.Entity.User;
import com.anon.blogging.Service.PostService;
import com.anon.blogging.Service.UserService;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    // Public — anyone can view all blogs
    @GetMapping("/api/posts")
    public List<PostDTO> listPosts() {
        return postService.getAllPosts().stream().map(PostDTO::new).collect(Collectors.toList());
    }

    // Public — anyone can view a single blog
    @GetMapping("/api/posts/{id}")
    public ResponseEntity<?> viewPost(@PathVariable int id) {
        try {
            Post post = postService.getPostById(id);
            return ResponseEntity.ok(new PostDTO(post));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Post not found"));
        }
    }

    // Requires login — create a new blog post
    @PostMapping("/api/posts")
    public ResponseEntity<?> createPost(@RequestBody Map<String, String> body, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Login required"));
        }
        String title = body.get("title");
        String content = body.get("content");
        if (title == null || title.isBlank() || content == null || content.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Title and content are required"));
        }
        User user = userService.findByUsername(principal.getName());
        postService.createPost(title, content, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Post created"));
    }
}
