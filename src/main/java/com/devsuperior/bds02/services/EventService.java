package com.devsuperior.bds02.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@Service
public class EventService {

	@Autowired
	EventRepository eventRep;
	
	@Autowired
	CityRepository cityRep;
	
	@Transactional(readOnly=true)
	public Page<EventDTO> findAll(Pageable pageable)
	{
		return eventRep.findAll(pageable).map(x -> new EventDTO(x));
	}
	
	@Transactional
	public EventDTO insert(EventDTO dto)
	{
		Event entity = new Event(dto);
		entity.setCity(cityRep.getOne(dto.getCityId()));
		return new EventDTO(eventRep.save(entity));
	}
	
	@Transactional
	public EventDTO update(Long id, EventDTO dto)
	{
		try
		{
			Event entity = eventRep.getOne(id);
			entity.setName(dto.getName());
			entity.setDate(dto.getDate());
			entity.setUrl(dto.getUrl());
			entity.setCity(cityRep.getOne(dto.getCityId()));
			return new EventDTO(eventRep.save(entity));
		}
		catch(EntityNotFoundException e){ throw new ResourceNotFoundException("Id not found "+id); }
	}
	
}
