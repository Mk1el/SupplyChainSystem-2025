package com.supply_chain_backend.security_service;

import lombok.Data;

@Data
public class UserProfileDto {
    private String username;
    private String email;
    private String fullName;
    private String role;
}
