package net.easysmarthouse.shared.service;

import net.easysmarthouse.shared.domain.user.PasswordResetToken;
import net.easysmarthouse.shared.domain.user.User;
import net.easysmarthouse.shared.domain.user.VerificationToken;
import net.easysmarthouse.shared.validation.EmailExistsException;

import java.util.Date;

public interface UserService {

    public User save(User user);

    public User findByUsername(String username);

    public User findByEmail(String email);

    public User findById(Long id);

    public User update(User user);

    public void createVerificationToken(User user, String token);

    public VerificationToken getVerificationToken(String token, boolean userInclude);

    public VerificationToken generateNewVerificationToken(final String existingVerificationToken, boolean userInclude);

    public void createPasswordResetTokenForUser(final User user, final String token);

    public PasswordResetToken findPasswordResetToken(String token, boolean userInclude);

    public void changeUserPassword(User user);

    public void deleteExpiredTokens(Date date);

}
