package dai_ichi_customer_portal.shared_modules.log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Component
public class LoggerFilter extends OncePerRequestFilter {

    private static final AntPathMatcher MATCHER = new AntPathMatcher();
    private static final List<String> SHOULD_NOT_FILTER_PATHS = List.of(
            "/api/v1/actuator/**"
    );

    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
        try {
            return new String(contentAsByteArray, characterEncoding);
        } catch (UnsupportedEncodingException e) {
            return new String(contentAsByteArray, StandardCharsets.UTF_8);
        }
    }

    private boolean isSensitiveField(String fieldName) {
        return fieldName.contains("password")
                || fieldName.contains("phoneNumber")
                || fieldName.contains("username")
                || fieldName.contains("userCode")
                || fieldName.contains("otp");
    }

    @Override
    protected boolean shouldNotFilter(@NonNull final HttpServletRequest request) {
        return SHOULD_NOT_FILTER_PATHS.stream().anyMatch(path -> MATCHER.match(path, request.getRequestURI()));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Wrap the request and response for caching
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        String responseBody = getStringValue(responseWrapper.getContentAsByteArray(), response.getCharacterEncoding());

        // Record start time
        final long startTime = System.currentTimeMillis();

        // Proceed with the filter chain
        filterChain.doFilter(requestWrapper, responseWrapper);

        // Calculate time taken
        final long timeTaken = System.currentTimeMillis() - startTime;

        // Extract request and response bodies
        String requestBody = getStringValue(requestWrapper.getContentAsByteArray(), request.getCharacterEncoding());

        // Filter sensitive fields in the request payload
        requestBody = getWithoutSensitiveField(requestBody);


        // Log the request method, URI, filtered request payload, response status code, and time taken
        log.info("Finish Processing: IP={}; Method={}; RequestURI={}; Request Payload={}; HttpStatus={}; Time Taken={}",
                request.getRemoteHost(), request.getMethod(), request.getRequestURI(), requestBody, response.getStatus(), timeTaken);

        // Copy the cached response body to the original response object
        responseWrapper.copyBodyToResponse();
    }

    private String getWithoutSensitiveField(String payload) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode payloadJson = objectMapper.readTree(payload);

            payloadJson.fields().forEachRemaining(entry -> {
                String fieldName = entry.getKey();
                if (isSensitiveField(fieldName)) {
                    ((ObjectNode) payloadJson).put(fieldName, "****");
                }
            });

            return payloadJson.toString();
        } catch (IOException e) {
            return "";
        }
    }
}
