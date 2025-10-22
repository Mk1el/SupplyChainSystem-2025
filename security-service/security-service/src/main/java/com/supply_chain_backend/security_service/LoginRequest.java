package com.supply_chain_backend.security_service;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
