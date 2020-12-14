package com.example.test.service.login;

import com.example.test.model.Admin;


public interface AdminService {

	void save(Admin admin);

	Admin findByUsername(String username);

}
