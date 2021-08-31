package com.example.purse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

import com.example.purse.models.entities.Purse;
import com.example.purse.repositories.IPurseRepository;
import com.example.purse.repositories.IRepository;

@Service
public class PurseService extends BaseService<Purse,String> implements IPurseService {

	private final IPurseRepository iPurseRepository;
	
	@Autowired
	public PurseService(IPurseRepository iPurseRepository) {
		this.iPurseRepository = iPurseRepository;
	}
	
	@Override
	protected IRepository<Purse, String> getRepository() {
		return iPurseRepository;
	}

}