package io.creatine.support;


import java.net.URI;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Represents a standardized error response structure for API endpoints. This record encapsulates comprehensive error
 * information including a general message, the resource that caused the error, timestamp of occurrence, and detailed
 * field-level errors.
 *
 * <p>This immutable data structure is typically used in REST API responses to provide
 * consistent error formatting across different endpoints and services.</p>
 *
 * @param message   A human-readable description of the error that occurred
 * @param resource  The URI of the resource that was being accessed when the error occurred
 * @param timestamp The exact date and time when the error occurred, with timezone information
 * @param errors    A list of detailed field-specific errors, useful for validation failures
 * @param isSuccess Indicate whether the action resulted in success or failure. Always returns false in an error reponse.
 * @author ThanhKien00
 * @since 1.0.0
 */
public record ErrorResponse(
        String message,
        URI resource,
        ZonedDateTime timestamp,
        List<ErrorDetail> errors,
        boolean isSuccess) {

    public ErrorResponse(String message, URI resource, ZonedDateTime timestamp, List<ErrorDetail> errors) {
        this(message, resource, timestamp, errors, false);
    }

    public ErrorResponse(String message, URI resource, ZonedDateTime timestamp) {
        this(message, resource, timestamp, List.of(), false);
    }

    /**
     * Represents a specific field-level error within an error response. This nested record provides granular error
     * information for individual fields, commonly used in form validation or request body validation scenarios.
     *
     * <p>Each ErrorDetail associates a field name with its specific error message,
     * allowing clients to display targeted error feedback to users.</p>
     *
     * @param field   The name of the field that contains the error (e.g., "email", "password")
     * @param message A descriptive error message explaining what went wrong with this specific field
     */
    public record ErrorDetail(
            String field,
            String message) {}


}
