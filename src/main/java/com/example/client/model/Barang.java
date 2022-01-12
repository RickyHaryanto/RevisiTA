package com.example.client.model;

import java.sql.Clob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Barang {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)

    private Long barang_id;
	private String barang_nama;
	
    private Clob barang_foto;
    private Clob barang_keterangan;
    private Integer barang_harga;

    public Barang() {
	}

	protected Barang(String barang_nama,Clob barang_foto,Clob barang_keterangan, Integer barang_harga) {
		super();
		this.barang_nama = barang_nama;
		this.barang_foto = barang_foto;
        this.barang_keterangan = barang_keterangan;
        this.barang_harga = barang_harga;
    }

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getBarang_id() {
		return barang_id;
	}

	public void setBarang_id(Long barang_id) {
		this.barang_id = barang_id;
    }

    public String getBarang_nama() {
		return barang_nama;
	}

	public void setBarang_nama(String barang_nama) {
		this.barang_nama = barang_nama;
    }

    public Clob getBarang_foto() {
		return barang_foto;
	}

	public void setBarang_foto(Clob barang_foto) {
		this.barang_foto = barang_foto;
    }

    public Clob getBarang_keterangan() {
		return barang_keterangan;
	}

	public void setBarang_keterangan(Clob barang_keterangan) {
		this.barang_keterangan = barang_keterangan;
    }

    public Integer getBarang_harga() {
		return barang_harga;
	}

	public void setBarang_harga(Integer barang_harga) {
		this.barang_harga = barang_harga;
    }
}