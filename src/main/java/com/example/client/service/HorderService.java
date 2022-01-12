package com.example.client.service;


import java.util.List;

import com.example.client.repository.HorderRepository;
import com.example.client.model.Horder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class HorderService {
    @Autowired
	private HorderRepository repo;
	
	public void save(Horder horder) {
		repo.save(horder);
	}

	public Horder get(long horder_id) {
		return repo.findById(horder_id).get();
	}

	
}