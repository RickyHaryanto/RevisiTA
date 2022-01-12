package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.Donasi;

public interface DonasiRepository extends JpaRepository<Donasi, Long> {
    @Query(
        value = "SELECT donasi_id,donasi_atasnama,donasi_jumlahtotal,donasi_namabarang,date_format(str_to_date(donasi_tanggal,'%Y-%m-%d'),'%d-%M-%Y'),user_id,donasi_keterangan FROM donasi WHERE user_id = ?", 
       nativeQuery = true)
       List findAll2(String param);

       @Query(
        value = "SELECT * FROM donasi WHERE donasi_id = ?", 
       nativeQuery = true)
       List<Donasi> cetakpdf(String param);
   
}