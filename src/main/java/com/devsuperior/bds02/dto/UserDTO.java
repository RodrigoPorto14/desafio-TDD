package com.devsuperior.bds02.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.devsuperior.bds02.entities.User;

public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@Email(message = "Favor entrar um email válido")
	private String email;
	
	Set<RoleDTO> roles = new HashSet<>();
	
	public UserDTO() {}
	
	public UserDTO(User entity)
	{
		id = entity.getId();
		email = entity.getEmail();
		entity.getRoles().forEach((role) -> roles.add(new RoleDTO(role)));
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}
	
	public Set<RoleDTO> getRoles(){
		return roles;
	}
}
