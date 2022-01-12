package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Hashtable;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.Dorder;

public interface DorderRepository extends JpaRepository<Dorder, Long> {
    @Query(
        value = "SELECT d.dorder_id,d.barang_id,d.dorder_jumlah,d.horder_id,FORMAT(d.dorder_harga,0),FORMAT(d.dorder_subtotal,0),b.barang_nama FROM dorder d INNER JOIN barang b ON (d.barang_id=b.barang_id) WHERE horder_id = ?", 
        nativeQuery = true)
        List findAllMember(String param);



        @Query(
            value = "SELECT c.* FROM cart c, user u WHERE c.user_id = u.user_id and c.user_id = ?", 
            nativeQuery = true)
            List findAllMember2(String param2);
}
