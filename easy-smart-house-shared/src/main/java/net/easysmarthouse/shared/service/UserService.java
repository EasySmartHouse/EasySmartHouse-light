package net.easysmarthouse.shared.service;

import net.easysmarthouse.shared.domain.user.User;
import net.easysmarthouse.shared.domain.user.VerificationToken;
import net.easysmarthouse.shared.validation.EmailExistsException;

public interface UserService {

    public User save(User user);

    public User findByUsername(String username);

    public User findByEmail(String email) throws EmailExistsException;

    public User findById(Long id);

    public User update(User user);

    public void createVerificationToken(User user, String token);

    public VerificationToken getVerificationToken(String token);

}
