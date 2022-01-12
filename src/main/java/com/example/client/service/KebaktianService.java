
package com.example.client.service;
import java.util.List;

import com.example.client.repository.KebaktianRepository;
import com.example.client.model.Kebaktian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class KebaktianService {

	@Autowired
	private KebaktianRepository repo;
	
	public void save(Kebaktian kebaktian) {
		repo.save(kebaktian);
	}

	public Kebaktian get(long kebaktian_id) {
		return repo.findById(kebaktian_id).get();
	}

	public List listAll() {
		return repo.findAll();
	}

}
