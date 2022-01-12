package com.example.client.model;

import java.sql.Clob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Berita {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    
    private Long berita_id;
    private String berita_judul;
    private String berita_isi;
	private String berita_tanggal;
    private Clob berita_file;

    public Berita() {
	}

	protected Berita(String berita_judul,String berita_isi,String berita_tanggal,Clob berita_file) {
		super();
		this.berita_judul = berita_judul;
		this.berita_isi = berita_isi;
		this.berita_tanggal = berita_tanggal;
		this.berita_file = berita_file;
    }
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getBerita_id() {
		return berita_id;
	}

	public void setBerita_id(Long berita_id) {
		this.berita_id = berita_id;
    }
    
    public String getBerita_isi() {
		return berita_isi;
	}

	public void setBerita_isi(String berita_isi) {
		this.berita_isi = berita_isi;
    }

    public String getBerita_judul() {
		return berita_judul;
	}

	public void setBerita_judul(String berita_judul) {
		this.berita_judul = berita_judul;
    }

    public Clob getBerita_file() {
		return berita_file;
	}

	public void setBerita_file(Clob berita_file) {
		this.berita_file = berita_file;
    }

    public String getBerita_tanggal() {
		return berita_tanggal;
	}

	public void setBerita_tanggal(String berita_tanggal) {
		this.berita_tanggal = berita_tanggal;
    }
}