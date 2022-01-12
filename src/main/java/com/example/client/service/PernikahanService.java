package com.example.client.service;
import java.util.List;

import com.example.client.repository.PernikahanRepository;
import com.example.client.model.Pernikahan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PernikahanService {
    @Autowired
	private PernikahanRepository repo;
	
	public List<Pernikahan> listAll() {
		return repo.findAll();
	}

	public Pernikahan get(long pernikahan_id) {
		return repo.findById(pernikahan_id).get();
	}

	public void save(Pernikahan pernikahan, long pernikahan_id) {
		if (pernikahan_id != 0){
			pernikahan.setPernikahan_id(pernikahan_id);
			repo.save(pernikahan);
		}else{
			repo.save(pernikahan);
		}	
		
		
	}
}