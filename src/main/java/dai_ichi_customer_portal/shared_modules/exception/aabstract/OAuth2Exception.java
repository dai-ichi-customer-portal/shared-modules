package dai_ichi_customer_portal.shared_modules.exception.aabstract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public abstract class OAuth2Exception extends RuntimeException {

    private final String error;

    @JsonProperty("error_description")
    private final String errorDescription;

    public OAuth2Exception(final String error, final String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public int getHttpErrorCode() {
        return 400;
    }
}