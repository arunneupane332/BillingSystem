package com.namus.billingSystem.BillingSystem.Security.Repo;

import com.namus.billingSystem.BillingSystem.Security.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
