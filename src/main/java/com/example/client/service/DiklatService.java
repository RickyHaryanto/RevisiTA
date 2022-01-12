
package com.example.client.service;
import java.util.List;

import com.example.client.repository.DiklatRepository;
import com.example.client.model.Diklat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class DiklatService {

	@Autowired
	private DiklatRepository repo;
	
	public List listAll() {
		return repo.findAll();
	}

	public List listAll2(Long param) {
		return repo.findActiveMember(param);
	}

	public Diklat get(long diklat_id) {
		return repo.findById(diklat_id).get();
	}

	public void save(Diklat diklat) {
			repo.save(diklat);
	}
	
}
