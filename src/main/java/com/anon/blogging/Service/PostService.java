package com.anon.blogging.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anon.blogging.Entity.Post;
import com.anon.blogging.Entity.User;
import com.anon.blogging.Repository.PostRepository;

@Service
public class PostService {
    
    @Autowired
    private PostRepository postRepository;

    public void createPost(String title, String content, User user){
        Post post = new Post(title, content, user);
        postRepository.save(post);
    }

    public List<Post> getPostsByUser(User user){
        return postRepository.findByUser(user);
    }

    public List<Post> getAllPosts(){
        return postRepository.findAllByOrderByIdDesc();
    }

    public Post getPostById(int id){
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }
}
