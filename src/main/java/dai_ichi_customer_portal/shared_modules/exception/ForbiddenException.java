package dai_ichi_customer_portal.shared_modules.exception;

import dai_ichi_customer_portal.shared_modules.exception.aabstract.AbstractForbiddenException;

public class ForbiddenException extends AbstractForbiddenException {

    public ForbiddenException() {
        super("Access Denied: You do not have permission to access this resource.");
    }
}