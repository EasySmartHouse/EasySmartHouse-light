package net.easysmarthouse.service.repository;

import net.easysmarthouse.shared.domain.user.VerificationToken;

public interface VerificationTokenRepository {

    public VerificationToken save(VerificationToken verificationToken);

    public VerificationToken update(VerificationToken verificationToken);

    public VerificationToken getToken(String token);

}
