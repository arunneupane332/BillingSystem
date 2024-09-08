package com.namus.billingSystem.BillingSystem.Security.Services;


import com.namus.billingSystem.BillingSystem.Security.Entity.User;
import com.namus.billingSystem.BillingSystem.Security.Repo.RoleRepository;
import com.namus.billingSystem.BillingSystem.Security.Repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final UserRepository userInfoRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public void saveUserInfo(User userInfo) {
        // Encrypt the user's password
        String password = userInfo.getPassword();
        userInfo.setPassword(bCryptPasswordEncoder.encode(password));

        // Handle roles (converting role names (String) to Role entities)
        Set<Role> roles = new HashSet<>();
        if (userInfo.getRoles() != null) {
            userInfo.getRoles().forEach(roleName -> {
                // Fetch role by name, or throw an exception if not found
                Role role = roleRepository.findByName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
                roles.add(role); // Add the role to the user's role set
            });
            userInfo.setRoles(roles); // Set the roles in the user entity
        }

        // Save the user with roles and encoded password
        userInfoRepository.save(userInfo);
    }
}
