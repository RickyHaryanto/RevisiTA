package com.example.client.service;
import java.util.List;

import com.example.client.repository.DonasiRepository;
import com.example.client.model.Donasi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DonasiService {
    @Autowired
	private DonasiRepository repo;
	
	public List listAll(String param) {
		return repo.findAll2(param);
	}

	public List<Donasi> listAll2(String param) {
		return repo.cetakpdf(param);
	}

	public Donasi get(long donasi_id) {
		return repo.findById(donasi_id).get();
	}

	public void save(Donasi donasi) {
		repo.save(donasi);
	}
}