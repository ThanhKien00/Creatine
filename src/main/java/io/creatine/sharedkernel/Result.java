package io.creatine.sharedkernel;

import lombok.Getter;

/**
 * Represents the outcome of an operation, encapsulating both success and failure states. A `Result` instance can
 * contain a payload, an error, and an optional metadata for additional context.
 *
 * @param <P> The type of the payload when the result is successful.
 */
@Getter
public class Result<P> {

    private final boolean isSuccess;
    private final boolean isFailure;
    private final P payload;
    private final Exception error;

    private Result(boolean isSuccess, P payload, Exception error) {
        this.isSuccess = isSuccess;
        this.isFailure = !isSuccess;
        this.payload = payload;
        this.error = error;
    }

    public static <P> Result<P> ok(P payload) {
        return new Result<>(true, payload, null);
    }

    public static <P> Result<P> fail(Exception error) {
        return new Result<>(false, null, error);
    }

}
