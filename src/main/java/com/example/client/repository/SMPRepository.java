package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.SMP;

public interface SMPRepository extends JpaRepository<SMP, Long> {
    @Query(
        value = "SELECT smp_id, smp_isi, smp_judul, date_format(str_to_date(smp_tanggal,'%Y-%m-%d'),'%d-%m-%Y')from smp order by smp_id desc", 
        nativeQuery = true)
        List findAll();

        @Query(
            value = "SELECT smp_id, smp_isi, smp_judul, date_format(str_to_date(smp_tanggal,'%Y-%m-%d'),'%d-%m-%Y')from smp where smp_tanggal like %?%", 
            nativeQuery = true)
            List searchsmp(String param);
}