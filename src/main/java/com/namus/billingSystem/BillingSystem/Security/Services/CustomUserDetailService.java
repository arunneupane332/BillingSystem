package com.namus.billingSystem.BillingSystem.Security.Services;


import com.namus.billingSystem.BillingSystem.Security.Entity.Role;
import com.namus.billingSystem.BillingSystem.Security.Repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.namus.billingSystem.BillingSystem.Security.Entity.User user = userInfoRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        Set<Role> roles = user.getRoles();
        List<? extends SimpleGrantedAuthority> mylist = roles.stream()
                .map(data -> new SimpleGrantedAuthority("ROLE_" + data.getRole())).collect(Collectors.toList());
        return User.withUsername(user.getUsername()).password(user.getPassword())
                .authorities(mylist).build();

    }
}
