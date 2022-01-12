package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.Dharma;

public interface DharmaRepository extends JpaRepository<Dharma, Long> {
    @Query(
        value = "SELECT dharma_id,dharma_isi,dharma_judul,dharma_media,date_format(str_to_date(dharma_tanggal,'%Y-%m-%d'),'%d-%m-%Y')from dharma order by dharma_id desc", 
        nativeQuery = true)
        List findAll();

        @Query(
            value = "SELECT dharma_id,dharma_isi,dharma_judul,dharma_media,date_format(str_to_date(dharma_tanggal,'%Y-%m-%d'),'%d-%m-%Y')from dharma where dharma_tanggal like %?%", 
            nativeQuery = true)
            List searchdharma(String param);
}