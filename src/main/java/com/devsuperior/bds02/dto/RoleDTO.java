package com.devsuperior.bds02.dto;

import java.io.Serializable;

import com.devsuperior.bds02.entities.Role;

public class RoleDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String authority;
	
	public RoleDTO() {}
	
	public RoleDTO(Role entity)
	{
		id = entity.getId();
		authority = entity.getAuthority();
	}

	public Long getId() {
		return id;
	}

	public String getAuthority() {
		return authority;
	}
	
}
