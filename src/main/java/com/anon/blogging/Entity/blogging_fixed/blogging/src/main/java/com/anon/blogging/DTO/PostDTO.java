package com.anon.blogging.DTO;

import com.anon.blogging.Entity.Post;

public class PostDTO {
    private int id;
    private String title;
    private String content;
    private String author;

    public PostDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getUser() != null ? post.getUser().getUsername() : "unknown";
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getAuthor() { return author; }
}
