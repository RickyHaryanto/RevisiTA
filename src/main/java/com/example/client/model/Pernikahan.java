package com.example.client.model;

import java.sql.Clob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;


@Entity
public class Pernikahan {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)

    private Long pernikahan_id;
    private String pernikahan_tanggal;
    private String pernikahan_namapria;
	private String pernikahan_namawanita;

	private Clob pernikahan_fotopria;

    private Clob pernikahan_fotowanita;
    private String pernikahan_keterangan;
	private String pernikahan_status;
	private String pernikahan_waktu;
    private Long user_id;

    public Pernikahan() {
	}

	protected Pernikahan(String pernikahan_tanggal,String pernikahan_namapria,String pernikahan_namawanita,Clob pernikahan_fotopria,Clob pernikahan_fotowanita, String pernikahan_keterangan, String pernikahan_status,String pernikahan_waktu, Long user_id) {
		super();
		this.pernikahan_tanggal = pernikahan_tanggal;
		this.pernikahan_namapria = pernikahan_namapria;
        this.pernikahan_namawanita = pernikahan_namawanita;
        this.pernikahan_fotopria = pernikahan_fotopria;
        this.pernikahan_fotowanita = pernikahan_fotowanita;
        this.pernikahan_keterangan = pernikahan_keterangan;
		this.pernikahan_status = pernikahan_status;
		this.pernikahan_waktu = pernikahan_waktu;
        this.user_id = user_id;
    }
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getPernikahan_id() {
		return pernikahan_id;
	}
	public void setPernikahan_id(Long pernikahan_id) {
		this.pernikahan_id = pernikahan_id;
    }



    public String getPernikahan_tanggal() {
		return pernikahan_tanggal;
	}
	public void setPernikahan_tanggal(String pernikahan_tanggal) {
		this.pernikahan_tanggal = pernikahan_tanggal;
    }



    public String getPernikahan_namapria() {
		return pernikahan_namapria;
	}
	public void setPernikahan_namapria(String pernikahan_namapria) {
		this.pernikahan_namapria = pernikahan_namapria;
    }


    public String getPernikahan_namawanita() {
		return pernikahan_namawanita;
	}
	public void setPernikahan_namawanita(String pernikahan_namawanita) {
		this.pernikahan_namawanita = pernikahan_namawanita;
    }


    public Clob getPernikahan_fotopria() {
		return pernikahan_fotopria;
	}
	public void setPernikahan_fotopria(Clob pernikahan_fotopria) {
		this.pernikahan_fotopria = pernikahan_fotopria;
    }



    public Clob getPernikahan_fotowanita() {
		return pernikahan_fotowanita;
	}
	public void setPernikahan_fotowanita(Clob pernikahan_fotowanita) {
		this.pernikahan_fotowanita = pernikahan_fotowanita;
    }



    
    public String getPernikahan_keterangan() {
		return pernikahan_keterangan;
	}
	public void setPernikahan_keterangan(String pernikahan_keterangan) {
		this.pernikahan_keterangan = pernikahan_keterangan;
    }




    public String getPernikahan_status() {
		return pernikahan_status;
	}
	public void setPernikahan_status(String pernikahan_status) {
		this.pernikahan_status = pernikahan_status;
	}
	

	public String getPernikahan_waktu() {
		return pernikahan_waktu;
	}
	public void setPernikahan_waktu(String pernikahan_waktu) {
		this.pernikahan_waktu = pernikahan_waktu;
    }



    public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
    }
}