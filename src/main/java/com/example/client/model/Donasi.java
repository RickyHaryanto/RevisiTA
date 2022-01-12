package com.example.client.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Donasi {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)

    private Long donasi_id;
    private String donasi_tanggal;
    private String donasi_atasnama;
    private String donasi_namabarang;
	private String donasi_jumlahtotal;
	private String donasi_keterangan;
	private Integer donasi_hargatotal;
	private Long user_id;

    public Donasi() {
	}

	protected Donasi(String donasi_tanggal,String donasi_atasnama, String donasi_namabarang, String donasi_jumlahtotal,String donasi_keterangan, Integer donasi_hargatotal, Long user_id ) {
		super();
		this.donasi_tanggal = donasi_tanggal;
		this.donasi_atasnama = donasi_atasnama;
		this.donasi_namabarang = donasi_namabarang;
		this.donasi_jumlahtotal = donasi_jumlahtotal;
		this.donasi_keterangan = donasi_keterangan;
		this.donasi_hargatotal = donasi_hargatotal;
		this.user_id = user_id;
    }

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getDonasi_id() {
		return donasi_id;
	}

	public void setDonasi_id(Long donasi_id) {
		this.donasi_id = donasi_id;
    }

    public String getDonasi_tanggal() {
		return donasi_tanggal;
	}

	public void setDonasi_tanggal(String donasi_tanggal) {
		this.donasi_tanggal = donasi_tanggal;
    }

    public String getDonasi_namabarang() {
		return donasi_namabarang;
	}

	public void setDonasi_namabarang(String donasi_namabarang) {
		this.donasi_namabarang = donasi_namabarang;
	}
	

	public String getDonasi_jumlahtotal() {
		return donasi_jumlahtotal;
	}

	public void setDonasi_jumlahtotal(String donasi_jumlahtotal) {
		this.donasi_jumlahtotal = donasi_jumlahtotal;
	}

	public Integer getDonasi_hargatotal() {
		return donasi_hargatotal;
	}

	public void setDonasi_hargatotal(Integer donasi_hargatotal) {
		this.donasi_hargatotal = donasi_hargatotal;
	}

	public String getDonasi_atasnama() {
		return donasi_atasnama;
	}

	public void setDonasi_atasnama(String donasi_atasnama) {
		this.donasi_atasnama = donasi_atasnama;
	}

	public String getDonasi_keterangan() {
		return donasi_keterangan;
	}

	public void setDonasi_keterangan(String donasi_keterangan) {
		this.donasi_keterangan = donasi_keterangan;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
    }
}