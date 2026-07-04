package com.anon.blogging.Service;

import com.anon.blogging.Entity.User;

public interface UserService {
    void registerUser(String username, String password);

    // @Override
    public User findByUsername(String username);
}
