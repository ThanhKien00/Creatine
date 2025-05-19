package io.creatine.support;


import java.net.URI;
import java.time.ZonedDateTime;
import java.util.List;

public record ErrorResponse(
        String message,
        URI resource,
        ZonedDateTime timestamp,
        List<ErrorDetail> errors) {

    public record ErrorDetail(
            String field,
            String message) {}

}
