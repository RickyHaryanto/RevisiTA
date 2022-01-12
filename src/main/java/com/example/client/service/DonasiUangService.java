package com.example.client.service;
import java.util.List;

import com.example.client.repository.DonasiUangRepository;
import com.example.client.model.DonasiUang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DonasiUangService {
    @Autowired
	private DonasiUangRepository repo;
	
	public List listAll(String param) {
		return repo.findAll2(param);
	}

	public void save(DonasiUang donasiuang) {
		repo.save(donasiuang);
	}
}