package com.example.client.service;
import java.util.List;

import com.example.client.repository.BarangRepository;

import com.example.client.model.Barang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
@Service
@Transactional
public class BarangService {
    @Autowired
	private BarangRepository repo;
	
	
	public List listAll() {
		return repo.findAll();
	}

	public List<Barang> listAll2(String param) {
		return repo.findAll2(param);
	}

	public Barang get(long barang_id) {
		return repo.findById(barang_id).get();
	}

	
	public void save(Barang barang,long barang_id) {
		if (barang_id != 0){
			barang.setBarang_id(barang_id);
			repo.save(barang);
		}else{
			repo.save(barang);
		}	
	}
}