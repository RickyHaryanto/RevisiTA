package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query(
        value = "SELECT c.cart_id,c.barang_id,c.user_id,c.cart_jumlah,c.cart_subtotal,c.cart_harga,b.barang_nama,b.barang_harga,b.barang_foto FROM cart c, barang b WHERE c.barang_id = b.barang_id and user_id = ?", 
       nativeQuery = true)
       List findAll2(String param);

       @Query(
         value = "select barang_harga from barang where barang_id = ?", 
         nativeQuery = true)
         Integer findharga(String param);

         @Query(
          value = "delete from cart where user_id = ?", 
          nativeQuery = true)
          Long deletecart(Long param);


          //Untuk edit cart
          @Query(
          value = "select cart_harga from cart where cart_id = ?", 
          nativeQuery = true)
          Long selecthargaedit(Long param);

          @Query(
          value = "select barang_id from cart where cart_id = ?", 
          nativeQuery = true)
          Integer getbarangid(Integer param);

          @Query(
            value = "SELECT barang_id,barang_foto,FORMAT(barang_harga,0),barang_keterangan,barang_nama FROM barang where barang_id=?", 
           nativeQuery = true)
           List getbarang(int param);


          //cek kesamaan barang
          @Query(
            value = "select cart_id from cart where barang_id = ?", 
            nativeQuery = true)
            Long cekitem(String param);

            @Query(
              value = "select cart_jumlah from cart where cart_id = ?", 
              nativeQuery = true)
              Long jumlah(Long param);

              @Query(
                value = "select cart_harga from cart where cart_id = ?", 
                nativeQuery = true)
                Long harga(Long param);
 }
