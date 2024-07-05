package dai_ichi_customer_portal.shared_modules.exception;

import dai_ichi_customer_portal.shared_modules.exception.aabstract.AbstractUnauthorizedException;

public class UnauthorizedException extends AbstractUnauthorizedException {

    public UnauthorizedException() {
        super("Unauthorized: You need to log in to access this resource.");
    }

    public UnauthorizedException(final String message) {
        super(message);
    }
}