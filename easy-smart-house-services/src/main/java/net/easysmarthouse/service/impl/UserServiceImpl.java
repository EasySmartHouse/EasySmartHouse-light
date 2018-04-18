package net.easysmarthouse.service.impl;

import net.easysmarthouse.service.repository.UserRepository;
import net.easysmarthouse.service.repository.VerificationTokenRepository;
import net.easysmarthouse.shared.domain.user.User;
import net.easysmarthouse.shared.domain.user.VerificationToken;
import net.easysmarthouse.shared.service.UserService;
import net.easysmarthouse.shared.validation.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User update(User user) {
        return userRepository.update(user);
    }

    @Override
    public User findByEmail(String email) throws EmailExistsException {
        final User user = userRepository.findByEmail(email);
        if (user != null) {
            throw new EmailExistsException();
        }
        return user;
    }

    @Override
    public void createVerificationToken(User user, String token) {
        final VerificationToken verificationToken = new VerificationToken(token);
        verificationToken.setUserId(user.getId());
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
    }
}
