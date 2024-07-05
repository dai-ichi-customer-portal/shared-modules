package dai_ichi_customer_portal.shared_modules.exception;

import dai_ichi_customer_portal.shared_modules.exception.aabstract.AbstractGeneralDomainRuleException;

public class GeneralDomainRuleException extends AbstractGeneralDomainRuleException {

    public GeneralDomainRuleException(final String message) {
        super(message);
    }
}