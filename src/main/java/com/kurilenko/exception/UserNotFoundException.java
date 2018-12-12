package com.kurilenko.exception;

import java.util.Collections;
import java.util.Map;

public class UserNotFoundException extends ResourceNotFoundException {
    private final String username;

    public UserNotFoundException(String username){
        super(String.format("User with USERNAME = %s has not been found", username));
        this.username = username;
    }

    @Override
    public Map<String, String> getMessageParams() {
        return Collections.singletonMap("username", username);
    }
}
