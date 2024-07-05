package dai_ichi_customer_portal.shared_modules.exception;

import dai_ichi_customer_portal.shared_modules.exception.aabstract.AbstractResourceNotFoundException;

public class ResourceNotFoundException extends AbstractResourceNotFoundException {

    public ResourceNotFoundException(final String resourceName, final long identifier) {
        super(String.format("Resource [%s] with identifier [%s] not found", resourceName, identifier));
    }

    public ResourceNotFoundException(final String message) {
        super(message);
    }
}