package io.creatine.support;

import lombok.Builder;

@Builder
public record ApiResponse<T> (
        T data,
        String message,
        int statusCode,
        boolean isSuccess
) {
}
