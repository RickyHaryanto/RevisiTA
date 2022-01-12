package com.example.client.service;
import java.util.List;

import com.example.client.repository.SMNRepository;
import com.example.client.model.SMN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SMNService {
    @Autowired
	private SMNRepository repo;
	
	public List listAll(String param) {
		return repo.findAll2(param);
	}

	public SMN get(long smn_id) {
		return repo.findById(smn_id).get();
	}

	public void save(SMN smn) {
		repo.save(smn);
	}

	
}