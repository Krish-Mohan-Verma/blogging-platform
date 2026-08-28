package com.anon.blogging.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anon.blogging.Entity.Post;
import com.anon.blogging.Entity.User;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findAllByOrderByIdDesc();
}
