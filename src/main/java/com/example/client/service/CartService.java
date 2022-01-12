package com.example.client.service;
import java.util.List;

import com.example.client.repository.CartRepository;

import com.example.client.model.Cart;

import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CartService {
    @Autowired
	private CartRepository repo;
	
	
	public List<Cart> listAll() {
		return repo.findAll();
	}

    
	public List listAll2(String param) {
		return repo.findAll2(param);
	}

	public Cart get(long cart_id) {
		return repo.findById(cart_id).get();
	}

	public void save(Cart cart,long cart_id) {
		if (cart_id != 0){
			cart.setCart_id(cart_id);
			repo.save(cart);
		}else{
			repo.save(cart);
		}	
	}

	public void delete(long cart_id) {
		repo.deleteById(cart_id);
	}

	
}