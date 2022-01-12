package com.example.client.service;
import java.util.List;

import com.example.client.repository.MSMRepository;
import com.example.client.model.MSM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MSMService {
    @Autowired
	private MSMRepository repo;
	
	public List<MSM> listAll() {
		return repo.findAll();
	}

	public MSM get(long msm_id) {
		return repo.findById(msm_id).get();
	}

	public void save(MSM msm, long msm_id) {
		if (msm_id != 0){
			msm.setMsm_id(msm_id);
			repo.save(msm);
		}else{
			repo.save(msm);
		}	
		
	}
}