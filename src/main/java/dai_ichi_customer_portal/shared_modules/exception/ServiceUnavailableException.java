package dai_ichi_customer_portal.shared_modules.exception;

import dai_ichi_customer_portal.shared_modules.exception.aabstract.AbstractServiceUnavailableException;

public class ServiceUnavailableException extends AbstractServiceUnavailableException {

    public ServiceUnavailableException() {
        super("Service Unavailable: Service is not available");
    }

    public ServiceUnavailableException(final String message) {
        super(message);
    }
}