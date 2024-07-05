package dai_ichi_customer_portal.shared_modules.exception.aabstract;

import lombok.Getter;

@Getter
public class AbstractInternalServerErrorException extends RuntimeException {

    private final String message;

    public AbstractInternalServerErrorException(final String message) {
        super(message);
        this.message = message;
    }
}