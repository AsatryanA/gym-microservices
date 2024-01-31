package com.epam.gym.exception;


public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Class<?> clazz, Long id) {
        super(String.format("%s with id %s not found", clazz.getSimpleName(), id));
    }

    public ResourceNotFoundException(Class<?> clazz, String username) {
        super(String.format("%s with username %s not found", clazz.getSimpleName(), username));
    }
}
