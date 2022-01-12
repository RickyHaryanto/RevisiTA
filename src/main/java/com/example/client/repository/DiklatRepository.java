package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.Diklat;

public interface DiklatRepository extends JpaRepository<Diklat, Long> {
    @Query(
        value = "SELECT diklat_id,diklat_nama,date_format(str_to_date(diklat_start,'%Y-%m-%d'),'%d-%m-%Y'),date_format(str_to_date(diklat_end,'%Y-%m-%d'),'%d-%m-%Y'),diklat_foto,diklat_keterangan,diklat_status,date_format(str_to_date(diklat_bataswaktu,'%Y-%m-%d'),'%d-%m-%Y') FROM diklat WHERE diklat_status = 'Open' order by diklat_id desc", 
       nativeQuery = true)
       List findAll();

      

       @Query(
        value = "SELECT diklat_id,diklat_nama,date_format(str_to_date(diklat_start,'%Y-%m-%d'),'%d-%m-%Y'),date_format(str_to_date(diklat_end,'%Y-%m-%d'),'%d-%m-%Y'),diklat_foto,diklat_keterangan,diklat_status,date_format(str_to_date(diklat_bataswaktu,'%Y-%m-%d'),'%d-%m-%Y') FROM diklat WHERE diklat_status = 'Open' and diklat_id=?", 
       nativeQuery = true)
       List detaildiklat(int param);

       @Query(
        value = "SELECT d.diklat_id,d.diklat_nama,date_format(str_to_date(d.diklat_start,'%Y-%m-%d'),'%d-%m-%Y'),date_format(str_to_date(d.diklat_end,'%Y-%m-%d'),'%d-%m-%Y'),diklat_foto,d.diklat_keterangan,d.diklat_status,date_format(str_to_date(d.diklat_bataswaktu,'%Y-%m-%d'),'%d-%m-%Y') FROM diklat d, user u, member_diklat md WHERE md.user_id = u.user_id and md.diklat_id = d.diklat_id and d.diklat_status = 'Open' and md.memberdiklat_status = 'Diterima' and md.user_id = ?", 
       nativeQuery = true)
       List findActiveMember(Long param);

       @Query(
        value = "SELECT diklat_id,diklat_nama,date_format(str_to_date(diklat_start,'%Y-%m-%d'),'%d-%m-%Y'),date_format(str_to_date(diklat_end,'%Y-%m-%d'),'%d-%m-%Y'),diklat_foto,diklat_keterangan,diklat_status,date_format(str_to_date(diklat_bataswaktu,'%Y-%m-%d'),'%d-%m-%Y') FROM diklat WHERE diklat_status = 'Open' and diklat_nama like %?%", 
       nativeQuery = true)
       List searchdiklat(String param);
}