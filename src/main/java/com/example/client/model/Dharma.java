package com.example.client.model;

import java.sql.Clob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dharma {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    
    private Long dharma_id;
    private String dharma_judul;
    private String dharma_isi;
	private String dharma_tanggal;
    private Clob dharma_media;

    public Dharma() {
	}

	protected Dharma(String dharma_judul,String dharma_isi,String dharma_tanggal,Clob dharma_media) {
		super();
		this.dharma_judul = dharma_judul;
		this.dharma_isi = dharma_isi;
		this.dharma_tanggal = dharma_tanggal;
		this.dharma_media = dharma_media;
    }
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getDharma_id() {
		return dharma_id;
	}

	public void setDharma_id(Long dharma_id) {
		this.dharma_id = dharma_id;
    }
    
    public String getDharma_isi() {
		return dharma_isi;
	}

	public void setDharma_isi(String dharma_isi) {
		this.dharma_isi = dharma_isi;
    }

    public String getDharma_judul() {
		return dharma_judul;
	}

	public void setDharma_judul(String dharma_judul) {
		this.dharma_judul = dharma_judul;
    }

    public Clob getDharma_media() {
		return dharma_media;
	}

	public void setDharma_media(Clob dharma_media) {
		this.dharma_media = dharma_media;
    }

    public String getDharma_tanggal() {
		return dharma_tanggal;
	}

	public void setDharma_tanggal(String dharma_tanggal) {
		this.dharma_tanggal = dharma_tanggal;
    }

}