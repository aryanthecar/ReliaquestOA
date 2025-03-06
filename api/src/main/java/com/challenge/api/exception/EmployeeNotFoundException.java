package com.challenge.api.exception;

import java.util.UUID;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(UUID uuid) {
        super("Employee with UUID " + uuid + " not found");
    }
}
