package com.example.client.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class SMN {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)

    private Long smn_id;
    private String smn_dharma;
    private String smn_etika;
    private String smn_ritual;
    private String smn_tanggal;
    private String smn_keterangan;
    private Long user_id;

    public SMN() {
	}

	protected SMN(String smn_dharma,String smn_etika,String smn_ritual,String smn_tanggal,String smn_keterangan,Long user_id) {
		super();
		this.smn_dharma = smn_dharma;
		this.smn_etika = smn_etika;
        this.smn_ritual = smn_ritual;
        this.smn_tanggal = smn_tanggal;
        this.smn_keterangan = smn_keterangan;
        this.user_id = user_id;
    }

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getSmn_id() {
		return smn_id;
	}
	public void setSmn_id(Long smn_id) {
		this.smn_id = smn_id;
    }


    public String getSmn_dharma() {
		return smn_dharma;
	}
	public void setSmn_dharma(String smn_dharma) {
		this.smn_dharma = smn_dharma;
    }


    public String getSmn_etika() {
		return smn_etika;
	}
	public void setSmn_etika(String smn_etika) {
		this.smn_etika = smn_etika;
    }


    public String getSmn_ritual() {
		return smn_ritual;
	}
	public void setSmn_ritual(String smn_ritual) {
		this.smn_ritual = smn_ritual;
    }


    public String getSmn_tanggal() {
		return smn_tanggal;
	}
	public void setSmn_tanggal(String smn_tanggal) {
		this.smn_tanggal = smn_tanggal;
    }


    public String getSmn_keterangan() {
		return smn_keterangan;
	}
	public void setSmn_keterangan(String smn_keterangan) {
		this.smn_keterangan = smn_keterangan;
    }


    public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
    }

}