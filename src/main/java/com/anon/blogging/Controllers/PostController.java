package com.anon.blogging.Controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anon.blogging.Entity.Post;
import com.anon.blogging.Entity.User;
import com.anon.blogging.Service.PostService;
import com.anon.blogging.Service.UserService;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/posts/new")
    public String showPostForm(){
        return "create-post";
    }

    @PostMapping("/posts/create")
    public String createPost(@RequestParam String title, @RequestParam String content, Principal principal){
        User user = userService.findByUsername(principal.getName());
        postService.createPost(title, content, user);
        return "redirect:/dashboard";
    }

    // View all blogs (public feed)
    @GetMapping("/posts")
    public String listPosts(Model model){
        model.addAttribute("posts", postService.getAllPosts());
        return "posts";
    }

    // View a single blog by id
    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable int id, Model model){
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "post-view";
    }
}
