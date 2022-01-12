package com.example.client.model;

import java.sql.Clob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Diklat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long diklat_id;
	private String diklat_nama;
	private String diklat_start;
	private String diklat_end;
	private Clob diklat_foto;
	private String diklat_keterangan;
	private String diklat_status;
	private String diklat_bataswaktu;
	
	
	
	public Diklat() {
	}

	protected Diklat(String diklat_nama,String diklat_start,String diklat_end,Clob diklat_foto,String diklat_keterangan,String diklat_status, String diklat_bataswaktu) {
		super();
		this.diklat_nama = diklat_nama;
		this.diklat_start = diklat_start;
		this.diklat_end = diklat_end;
		this.diklat_foto = diklat_foto;
		this.diklat_keterangan = diklat_keterangan;
		this.diklat_status = diklat_status;
		this.diklat_bataswaktu = diklat_bataswaktu;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getDiklat_id() {
		return diklat_id;
	}

	public void setDiklat_id(Long diklat_id) {
		this.diklat_id = diklat_id;
	}

	public String getDiklat_nama() {
		return diklat_nama;
	}

	public void setDiklat_nama(String diklat_nama) {
		this.diklat_nama = diklat_nama;
	}
	public String getDiklat_start() {
		return diklat_start;
	}

	public void setDiklat_start(String diklat_start) {
		this.diklat_start = diklat_start;
	}
	public String getDiklat_end() {
		return diklat_end;
	}

	public void setDiklat_end(String diklat_end) {
		this.diklat_end = diklat_end;
	}
	public Clob getDiklat_foto() {
		return diklat_foto;
	}

	public void setDiklat_foto(Clob diklat_foto) {
		this.diklat_foto = diklat_foto;
	}
	public String getDiklat_keterangan() {
		return diklat_keterangan;
	}

	public void setDiklat_keterangan(String diklat_keterangan) {
		this.diklat_keterangan = diklat_keterangan;
	}
	public String getDiklat_status() {
		return diklat_status;
	}

	public void setDiklat_status(String diklat_status) {
		this.diklat_status = diklat_status;
	}

	public String getDiklat_bataswaktu() {
		return diklat_bataswaktu;
	}

	public void setDiklat_bataswaktu(String diklat_bataswaktu) {
		this.diklat_bataswaktu = diklat_bataswaktu;
	}

}
