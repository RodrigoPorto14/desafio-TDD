package com.devsuperior.bds02.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.exceptions.DataBaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@Service
public class CityService {
	
	@Autowired
	CityRepository cityRep;
	
	@Transactional(readOnly=true)
	public List<CityDTO> findAll()
	{
		return cityRep.findAll(Sort.by("name")).stream().map(x -> new CityDTO(x)).toList();
	}
	
	@Transactional
	public CityDTO insert(CityDTO dto)
	{
		City entity = new City();
		entity.setName(dto.getName());
		return new CityDTO(cityRep.save(entity));
	}
	
	public void delete(Long id)
	{
		try { cityRep.deleteById(id); }
		catch(EmptyResultDataAccessException e){throw new ResourceNotFoundException("Id not found " +id);}
		catch(DataIntegrityViolationException e) {throw new DataBaseException("Integrity violation");}
	}

	
}
