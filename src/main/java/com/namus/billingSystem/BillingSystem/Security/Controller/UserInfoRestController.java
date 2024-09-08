package com.namus.billingSystem.BillingSystem.Security.Controller;

import com.namus.billingSystem.BillingSystem.Common.Response.GlobalApiResponse;
import com.namus.billingSystem.BillingSystem.Security.Entity.User;
import com.namus.billingSystem.BillingSystem.Security.Services.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class UserInfoRestController {

    private final UserInfoService userInfoService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/saveUser")
    public ResponseEntity<GlobalApiResponse> saveRestUserInfo(@RequestBody User userInfo) {

        // Save the user using the service
        userInfoService.saveUserInfo(userInfo);

        // Return a response with status 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(
                GlobalApiResponse.builder()
                        .code(HttpStatus.CREATED.value())
                        .data(null)
                        .message("User Created Successfully")
                        .status(true)
                        .build()
        );
    }
}
