package com.example.test.service.login;


import com.example.test.model.Admin;
import com.example.test.model.Role;
import com.example.test.repository.admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

public class AdminDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AdminRepository adminrepository;
	
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		
		Admin user = adminrepository.findByUsername(account);
		
		 Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
	        for (Role role : user.getRoles()){
	            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
	     }
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}

}
