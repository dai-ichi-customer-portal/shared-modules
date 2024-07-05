package dai_ichi_customer_portal.shared_modules.exception.aabstract;

import lombok.Getter;

@Getter
public class AbstractGeneralDomainRuleException extends RuntimeException {

    private final String message;

    public AbstractGeneralDomainRuleException(final String message) {
        super(message);
        this.message = message;
    }
}