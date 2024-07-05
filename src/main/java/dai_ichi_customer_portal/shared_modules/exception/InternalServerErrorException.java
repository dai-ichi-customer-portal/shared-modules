package dai_ichi_customer_portal.shared_modules.exception;

import dai_ichi_customer_portal.shared_modules.exception.aabstract.AbstractInternalServerErrorException;

public class InternalServerErrorException extends AbstractInternalServerErrorException {

    public InternalServerErrorException() {
        super("Internal Server Error.");
    }
}