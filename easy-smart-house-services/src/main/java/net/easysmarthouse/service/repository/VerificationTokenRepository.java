package net.easysmarthouse.service.repository;

import net.easysmarthouse.shared.domain.user.VerificationToken;

import java.util.Date;

public interface VerificationTokenRepository {

    public VerificationToken save(VerificationToken verificationToken);

    public VerificationToken update(VerificationToken verificationToken);

    public VerificationToken getToken(String token);

    public void deleteExpiredSince(Date date);

}
