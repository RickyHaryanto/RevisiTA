package com.example.client.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.Horder;

public interface HorderRepository extends JpaRepository<Horder, Long> {
    @Query(
        value = "SELECT h.horder_id,h.horder_atasnama,h.horder_status,date_format(str_to_date(h.horder_tanggal,'%Y-%m-%d'),'%d-%M-%Y'),h.user_id,FORMAT(h.horder_totalharga,0),u.email FROM horder h INNER JOIN user u ON (h.user_id=u.user_id) where u.user_id=?", 
        nativeQuery = true)
        List findAllMember(String param);

        @Query(
            value = "SELECT sum(cart_subtotal) FROM cart where user_id = ?", 
            nativeQuery = true)
            Integer findgrandtotal(Long param);
}