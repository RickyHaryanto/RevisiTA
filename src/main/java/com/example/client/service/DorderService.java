package com.example.client.service;



import java.util.Hashtable;
import java.util.List;

import com.example.client.repository.DorderRepository;
import com.example.client.model.Dorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class DorderService {
    @Autowired
	private DorderRepository repo;
	
	public void save(Dorder dorder) {
		repo.save(dorder);
	}

	public Dorder get(long horder_id) {
		return repo.findById(horder_id).get();
	}

	public List listAll(String param) {
		return repo.findAllMember(param);
	}

	public List listAll2(String param2) {
		return repo.findAllMember2(param2);
	}

}