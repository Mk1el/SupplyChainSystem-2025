package com.supply_chain_backend.security_service;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String role; // ADMIN, SUPPLIER, CUSTOMER, etc.
}
