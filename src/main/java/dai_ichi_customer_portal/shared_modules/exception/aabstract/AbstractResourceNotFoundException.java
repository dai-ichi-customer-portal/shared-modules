package dai_ichi_customer_portal.shared_modules.exception.aabstract;

import lombok.Getter;

@Getter
public class AbstractResourceNotFoundException extends RuntimeException {

    private final String message;

    public AbstractResourceNotFoundException(final String message) {
        super(message);
        this.message = message;
    }
}