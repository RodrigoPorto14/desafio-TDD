package com.devsuperior.bds02.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devsuperior.bds02.entities.User;
import com.devsuperior.bds02.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRep;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRep.findByEmail(username);
		if(user == null) 
		{
			logger.error("User not found: " + username);
			throw new UsernameNotFoundException("Email not found");
		}
		logger.info("User found: " + username);
		return user;
	}
}
