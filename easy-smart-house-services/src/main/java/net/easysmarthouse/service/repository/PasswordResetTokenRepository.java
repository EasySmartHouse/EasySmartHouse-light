package net.easysmarthouse.service.repository;

import net.easysmarthouse.shared.domain.user.PasswordResetToken;

import java.util.Date;

public interface PasswordResetTokenRepository {

    public PasswordResetToken save(PasswordResetToken token);

    public PasswordResetToken update(PasswordResetToken token);

    public PasswordResetToken getToken(String token);

    public void deleteExpiredSince(Date date);

}
