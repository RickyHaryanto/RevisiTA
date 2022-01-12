package com.example.client.service;
import java.util.List;

import com.example.client.repository.ChatRepository;
import com.example.client.model.Chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChatService {
    @Autowired
	private ChatRepository repo;
	
	public List<Chat> listAll(String param) {
		return repo.findAll2(param);
	}

	public Chat get(long chat_id) {
		return repo.findById(chat_id).get();
	}

	public void save(Chat chat) {
		repo.save(chat);
	}

	public void delete(long chat_id) {
		repo.deleteById(chat_id);
	}
}