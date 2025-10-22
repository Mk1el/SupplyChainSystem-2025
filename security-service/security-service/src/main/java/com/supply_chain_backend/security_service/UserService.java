package com.supply_chain_backend.security_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserProfileDto getUserProfile(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProfileDto profile = new UserProfileDto();
        profile.setUsername(user.getUsername());
        profile.setEmail(user.getEmail());
        profile.setFullName(user.getFullName());
        profile.setRole(user.getRoles().stream().findFirst().get().getName().name());
        return profile;
    }
}
