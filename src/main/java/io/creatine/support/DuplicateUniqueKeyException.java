package io.creatine.support;

public class DuplicateUniqueKeyException extends RuntimeException {

    public DuplicateUniqueKeyException() {
        super("Duplicated Unique Key");
    }

    public DuplicateUniqueKeyException(String message) {
        super(message);
    }

}
