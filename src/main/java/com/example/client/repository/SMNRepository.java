package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.SMN;

public interface SMNRepository extends JpaRepository<SMN, Long> {
    @Query(
        value = "SELECT smn_id,smn_dharma,smn_etika,smn_keterangan,smn_ritual,date_format(str_to_date(smn_tanggal,'%Y-%m-%d'),'%d-%m-%Y'),user_id FROM smn WHERE user_id = ? order by smn_id desc", 
       nativeQuery = true)
       List findAll2(String param);

       @Query(
        value = "SELECT smn_id,smn_dharma,smn_etika,smn_keterangan,smn_ritual,date_format(str_to_date(smn_tanggal,'%Y-%m-%d'),'%d-%m-%Y'),user_id FROM smn WHERE user_id = ? and smn_tanggal like %?%", 
       nativeQuery = true)
       List searchsmn(String param, String param2);
}