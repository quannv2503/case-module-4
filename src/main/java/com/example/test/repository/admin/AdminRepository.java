package com.example.test.repository.admin;

import com.example.test.model.Admin;
import com.example.test.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
}
