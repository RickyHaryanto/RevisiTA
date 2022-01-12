package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.DonasiUang;

public interface DonasiUangRepository extends JpaRepository<DonasiUang, Long> {
    @Query(
        value = "SELECT donasiuang_nama FROM donasi_uang order by donasiuang_id desc limit 1", 
       nativeQuery = true)
       String namadonatur();


       @Query(
        value = "SELECT donasiuang_email FROM donasi_uang order by donasiuang_id desc limit 1", 
       nativeQuery = true)
       String emaildonatur();

       @Query(
        value = "SELECT donasiuang_nomornota FROM donasi_uang order by donasiuang_id desc limit 1", 
       nativeQuery = true)
       String nomornotadonatur();

       @Query(
        value = "SELECT donasiuang_tanggal FROM donasi_uang order by donasiuang_id desc limit 1", 
       nativeQuery = true)
       String tanggaldonatur();

       @Query(
        value = "SELECT donasiuang_nominal FROM donasi_uang order by donasiuang_id desc limit 1", 
       nativeQuery = true)
       int jumlahdonasi();

       @Query(
        value = "SELECT donasiuang_id,FORMAT(donasiuang_nominal,0),donasiuang_nomornota,date_format(str_to_date(donasiuang_tanggal,'%Y-%m-%d'),'%d-%M-%Y'),user_id,donasiuang_nama,donasiuang_email FROM donasi_uang WHERE user_id = ?", 
       nativeQuery = true)
       List findAll2(String param);
}