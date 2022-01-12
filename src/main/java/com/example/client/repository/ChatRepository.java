package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {
  

       @Query(
        value = "SELECT chat_id, chat_pertanyaan, chat_jawaban, date_format(str_to_date(chat_date,'%Y-%m-%d'),'%d-%m-%Y'), chat_time, date_format(str_to_date(chat_dateadmin,'%Y-%m-%d'),'%d-%m-%Y'), chat_timeadmin from chat WHERE user_id = ? order by chat_id desc", 
       nativeQuery = true)
       List findAll2(String param);
}