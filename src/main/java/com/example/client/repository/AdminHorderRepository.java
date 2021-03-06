package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.Horder;

public interface AdminHorderRepository extends JpaRepository<Horder, Long> {
    @Query(
        value = "SELECT h.horder_id,h.horder_atasnama,h.horder_status,date_format(str_to_date(h.horder_tanggal,'%Y-%m-%d'),'%d-%M-%Y'),h.user_id,h.horder_totalharga,u.email FROM horder h INNER JOIN user u ON (h.user_id=u.user_id)", 
        nativeQuery = true)
        List findAllMember();


        @Query(
            value = "SELECT h.horder_id,h.horder_atasnama,h.horder_status,date_format(str_to_date(h.horder_tanggal,'%Y-%m-%d'),'%d-%M-%Y'),h.user_id,h.horder_totalharga,u.email FROM horder h INNER JOIN user u ON (h.user_id=u.user_id) where h.horder_status='Selesai' and h.horder_tanggal between ? and ?", 
            nativeQuery = true)
            List laporan(String param1, String param2);
    
        @Query(
        value = "SELECT b.barang_nama, d.dorder_harga, d.dorder_jumlah, d.dorder_subtotal FROM horder h, dorder d, barang b WHERE h.horder_id = d.horder_id and b.barang_id = d.barang_id and h.horder_id = ?", 
       nativeQuery = true)
       List listprint(String param);

       @Query(
        value = "SELECT DISTINCT h.horder_atasnama,date_format(str_to_date(h.horder_tanggal,'%Y-%m-%d'),'%d-%M-%Y'), h.horder_totalharga FROM horder h, dorder d, barang b WHERE h.horder_id = d.horder_id and b.barang_id = d.barang_id and h.horder_id = ?", 
       nativeQuery = true)
       List listprintheader(String param);

}