package com.example.client.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;

    private String nama;
    private String email;
    private String alamat;
    private String bod;
    private String qiudao;
    private String password;
    private String kota;
    private String statusberita;
    private String status;
   
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    public User() {
    }

    public User(String nama, String email, String alamat, String bod, String qiudao, String password, String kota, String status, String statusberita) {
        this.nama = nama;
        this.email = email;
        this.alamat = alamat;
        this.bod = bod;
        this.qiudao = qiudao;
        this.password = password;
        this.kota= kota;      
        this.statusberita = statusberita;
        this.status = status;
       
    }

    public User(String nama, String email, String alamat, String bod, String qiudao, String password, String kota, String status,String statusberita, Collection<Role> roles) {
        this.nama = nama;
        this.email = email;
        this.alamat = alamat;
        this.bod = bod;
        this.qiudao = qiudao;
        this.password = password;
        this.kota= kota; 
       this.statusberita = statusberita;
        this.status = status;
        this.roles = roles;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusBerita() {
        return statusberita;
    }

    public void setStatusBerita(String statusberita) {
        this.statusberita = statusberita;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getQiudao() {
        return qiudao;
    }

    public void setQiudao(String qiudao) {
        this.qiudao = qiudao;
    }



    public String getBod() {
        return bod;
    }

    public void setBod(String bod) {
        this.bod = bod;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + user_id +
                ", FullName='" + nama + '\'' +
                ", email='" + email + '\'' +
                ", password='" + "*********" + '\'' +
                ", roles=" + roles +
                '}';
    }
}
