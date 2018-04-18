package net.easysmarthouse.shared.validation;

public class EmailExistsException extends Throwable {

    public EmailExistsException() {
    }

    public EmailExistsException(final String message) {
        super(message);
    }

}
