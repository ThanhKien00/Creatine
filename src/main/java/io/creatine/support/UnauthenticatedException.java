package io.creatine.support;

public class UnauthenticatedException extends RuntimeException {

    public UnauthenticatedException() {
        super("Unauthenticated request");
    }

    public UnauthenticatedException(String message) {
        super(message);
    }

}
