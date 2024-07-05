package dai_ichi_customer_portal.shared_modules.exception.aabstract;

import lombok.Getter;

@Getter
public class AbstractServiceUnavailableException extends RuntimeException {

    private final String message;

    public AbstractServiceUnavailableException(final String message) {
        super(message);
        this.message = message;
    }
}