package net.easysmarthouse.shared.service;

import net.easysmarthouse.shared.domain.user.User;

public interface UserService {

    public User save(User user);

    public User findByUsername(String username);

}
