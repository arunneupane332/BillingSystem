package com.namus.billingSystem.BillingSystem.Security.Services;


import com.namus.billingSystem.BillingSystem.Security.Entity.Role;
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

    @Override
    public void saveUserInfo(User userInfo) {
        String password = userInfo.getPassword();
        userInfo.setPassword(bCryptPasswordEncoder.encode(password));
        Set<Role> role=userInfo.getRoles();
        userInfo.setRoles(role);
        userInfoRepository.save(userInfo);
    }
}
