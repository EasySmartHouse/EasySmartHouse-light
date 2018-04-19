package net.easysmarthouse.service.impl;

import net.easysmarthouse.service.repository.PasswordResetTokenRepository;
import net.easysmarthouse.service.repository.UserRepository;
import net.easysmarthouse.service.repository.VerificationTokenRepository;
import net.easysmarthouse.shared.domain.user.PasswordResetToken;
import net.easysmarthouse.shared.domain.user.User;
import net.easysmarthouse.shared.domain.user.VerificationToken;
import net.easysmarthouse.shared.service.UserService;
import net.easysmarthouse.shared.validation.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

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
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        final VerificationToken verificationToken = new VerificationToken(token);
        verificationToken.setUserId(user.getId());
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public VerificationToken getVerificationToken(String token, boolean userInclude) {
        final VerificationToken verificationToken = verificationTokenRepository.getToken(token);

        Optional.ofNullable(verificationToken)
                .ifPresent((verifyToken) -> {
                    if (userInclude) {
                        verificationToken.setUser(
                                userRepository.findById(verificationToken.getUserId()));
                    }
                });

        return verificationToken;
    }

    @Override
    @Transactional
    public VerificationToken generateNewVerificationToken(String existingVerificationToken, boolean userInclude) {
        final VerificationToken verificationToken = verificationTokenRepository.getToken(existingVerificationToken);

        Optional.ofNullable(verificationToken)
                .ifPresent((verifyToken) -> {
                    if (userInclude) {
                        verificationToken.setUser(
                                userRepository.findById(verificationToken.getUserId()));
                    }
                });

        verificationToken.updateToken(UUID.randomUUID().toString());
        verificationTokenRepository.update(verificationToken);
        return verificationToken;
    }

    @Override
    @Transactional
    public void createPasswordResetTokenForUser(final User user, final String token) {
        final PasswordResetToken resetToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(resetToken);
    }

    @Override
    public PasswordResetToken findPasswordResetToken(String token, boolean userInclude) {
        PasswordResetToken resetToken = passwordResetTokenRepository.getToken(token);

        if (resetToken != null && userInclude) {
            resetToken.setUser(
                    userRepository.findById(resetToken.getUserId()));
        }

        return passwordResetTokenRepository.getToken(token);
    }

    @Override
    public void changeUserPassword(User user) {
        userRepository.changePassword(user);
    }

    @Override
    public void deleteExpiredTokens(Date date) {
        verificationTokenRepository.deleteExpiredSince(date);
        passwordResetTokenRepository.deleteExpiredSince(date);
    }
}
