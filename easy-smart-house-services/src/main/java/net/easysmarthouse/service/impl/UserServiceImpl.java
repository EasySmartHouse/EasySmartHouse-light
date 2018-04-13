package net.easysmarthouse.service.impl;

import net.easysmarthouse.service.repository.UserRepository;
import net.easysmarthouse.shared.domain.user.User;
import net.easysmarthouse.shared.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
