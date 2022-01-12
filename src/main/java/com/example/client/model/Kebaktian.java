package com.example.client.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Kebaktian {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long kebaktian_id;
	private String kebaktian_tanggal;
	private String kebaktian_hari;
	private String kebaktian_waktu;
	private String kebaktian_pembicara;
	
	
	public Kebaktian() {
	}

	protected Kebaktian(String kebaktian_tanggal,String kebaktian_hari,String kebaktian_waktu,String kebaktian_pembicara) {
		super();
		this.kebaktian_tanggal = kebaktian_tanggal;
		this.kebaktian_hari = kebaktian_hari;
		this.kebaktian_waktu = kebaktian_waktu;
		this.kebaktian_pembicara = kebaktian_pembicara;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getKebaktian_id() {
		return kebaktian_id;
	}

	public void setKebaktian_id(Long kebaktian_id) {
		this.kebaktian_id = kebaktian_id;
	}

	public String getKebaktian_tanggal() {
		return kebaktian_tanggal;
	}

	public void setKebaktian_tanggal(String kebaktian_tanggal) {
		this.kebaktian_tanggal = kebaktian_tanggal;
	}
    
    public String getKebaktian_hari() {
		return kebaktian_hari;
	}

	public void setKebaktian_hari(String kebaktian_hari) {
		this.kebaktian_hari = kebaktian_hari;
	}
	public String getKebaktian_waktu() {
		return kebaktian_waktu;
	}

	public void setKebaktian_waktu(String kebaktian_waktu) {
		this.kebaktian_waktu = kebaktian_waktu;
	}
	public String getKebaktian_pembicara() {
		return kebaktian_pembicara;
	}

	public void setKebaktian_pembicara(String kebaktian_pembicara) {
		this.kebaktian_pembicara = kebaktian_pembicara;
	}
}
