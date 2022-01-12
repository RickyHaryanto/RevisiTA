package com.example.client.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class SMP {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)

    private Long smp_id;
    private String smp_judul;
    private String smp_isi;
    private String smp_tanggal;
    
    public SMP() {
	}

	protected SMP(String smp_judul,String smp_isi,String smp_tanggal) {
		super();
		this.smp_judul = smp_judul;
		this.smp_isi = smp_isi;
		this.smp_tanggal = smp_tanggal;
    }

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getSmp_id() {
		return smp_id;
	}

	public void setSmp_id(Long smp_id) {
		this.smp_id = smp_id;
    }

    public String getSmp_judul() {
		return smp_judul;
	}

	public void setSmp_judul(String smp_judul) {
		this.smp_judul = smp_judul;
    }

    public String getSmp_isi() {
		return smp_isi;
	}

	public void setSmp_isi(String smp_isi) {
		this.smp_isi = smp_isi;
    }

    public String getSmp_tanggal() {
		return smp_tanggal;
	}

	public void setSmp_tanggal(String smp_tanggal) {
		this.smp_tanggal = smp_tanggal;
    }
    
}