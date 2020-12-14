package com.example.test.service.login;


import com.example.test.model.Admin;
import com.example.test.repository.admin.AdminRepository;
import com.example.test.repository.admin.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
    private AdminRepository adminRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
private PasswordEncoder passwordEncoder;
    @Override
    public void save(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRoles(new HashSet<>(roleRepository.findAll()));
        adminRepository.save(admin);
    }

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
	
}
