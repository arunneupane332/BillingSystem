package com.namus.billingSystem.BillingSystem.Security.Repo;

import com.namus.billingSystem.BillingSystem.Security.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
