package org.generation.italy.collectionarchive.models.exceptions;

public class SenderNotFoundException extends RuntimeException {
    public SenderNotFoundException(String message) {
        super(message);
    }
}
