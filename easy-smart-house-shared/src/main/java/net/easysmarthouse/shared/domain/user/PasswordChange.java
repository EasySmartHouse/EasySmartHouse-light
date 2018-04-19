package net.easysmarthouse.shared.domain.user;

import net.easysmarthouse.shared.validation.ValidPassword;

import java.io.Serializable;

public class PasswordChange implements Serializable {

    private String oldPassword;

    @ValidPassword
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
