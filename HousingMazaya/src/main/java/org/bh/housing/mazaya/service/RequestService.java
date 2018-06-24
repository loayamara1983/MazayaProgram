package org.bh.housing.mazaya.service;

import javax.annotation.PostConstruct;

import org.bh.housing.mazaya.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {
	
	@Autowired
	private RequestRepository repo;
	
	@PostConstruct
	public void init() {
		System.out.println(repo.count());
	}
	
}
