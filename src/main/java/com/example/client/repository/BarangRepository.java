package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.Barang;

public interface BarangRepository extends JpaRepository<Barang, Long> {
    @Query(
        value = "SELECT * FROM barang WHERE barang_id = ?", 
       nativeQuery = true)
       List<Barang> findAll2(String param);

       @Query(
        value = "SELECT barang_id,barang_foto,FORMAT(barang_harga,0),barang_keterangan,barang_nama FROM barang order by barang_id desc", 
       nativeQuery = true)
       List findAll();

       @Query(
        value = "SELECT barang_id,barang_foto,FORMAT(barang_harga,0),barang_keterangan,barang_nama FROM barang where barang_nama like %?%", 
       nativeQuery = true)
       List searchbarang(String param);

       @Query(
        value = "SELECT barang_id,barang_foto,FORMAT(barang_harga,0),barang_keterangan,barang_nama FROM barang where barang_id=?", 
       nativeQuery = true)
       List detailbarang(int param);
}