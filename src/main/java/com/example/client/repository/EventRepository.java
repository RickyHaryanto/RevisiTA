package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query(
        value = "SELECT event_id,event_judul,date_format(str_to_date(event_tanggal,'%Y-%m-%d'),'%d-%m-%Y'),event_foto,event_keterangan,event_waktu from event limit 4", 
        nativeQuery = true)
        List findAll();

        @Query(
            value = "SELECT event_id,event_judul,date_format(str_to_date(event_tanggal,'%Y-%m-%d'),'%d-%m-%Y'),event_foto,event_keterangan,event_waktu from event where event_id=?", 
            nativeQuery = true)
            List detailevent(int param);

        @Query(
            value = "SELECT event_id,event_judul,date_format(str_to_date(event_tanggal,'%Y-%m-%d'),'%d-%m-%Y'),event_foto,event_keterangan,event_waktu from event where event_judul=?", 
            nativeQuery = true)
            List searchevent(String param);
    
            @Query(
                value = "SELECT event_id,event_judul,date_format(str_to_date(event_tanggal,'%Y-%m-%d'),'%d-%m-%Y'),event_foto,event_keterangan,event_waktu from event where event_id>? limit 12", 
                nativeQuery = true)
                List laodmore(Integer param);
            
}