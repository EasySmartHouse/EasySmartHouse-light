package net.easysmarthouse.service;

public interface UserSecurityService {

    public String validatePasswordResetToken(long id, String token);

}
