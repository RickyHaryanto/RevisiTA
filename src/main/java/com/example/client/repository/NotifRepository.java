package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.Notif;

public interface NotifRepository extends JpaRepository<Notif, Long> {
    @Query(
        value = "select notif_id,notif_flag,notif_isi,date_format(str_to_date(notif_tanggal,'%Y-%m-%d'),'%d-%M-%Y'),user_id from notif where user_id= ? and notif_flag='0' order by notif_id desc limit 10", 
        nativeQuery = true)
        List select(String param);
    

    //Query untuk notif ke admin
    @Query(
        value = "select u.* from user u, role r, users_roles ur where u.user_id = ur.user_id and r.id = ur.role_id and r.name='Admin Umum' limit 1", 
        nativeQuery = true)
        List notifchat();


    @Query(
        value = "select u.* from user u, role r, users_roles ur where u.user_id = ur.user_id and r.id = ur.role_id and r.name='Admin Event' limit 1", 
        nativeQuery = true)
        List notifdaftradiklat();

        @Query(
            value = "select u.* from user u, role r, users_roles ur where u.user_id = ur.user_id and r.id = ur.role_id and r.name='Admin Sekolah Minggu' limit 1", 
            nativeQuery = true)
            List notifanggotasm();
    
}