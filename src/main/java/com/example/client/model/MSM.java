package com.example.client.model;
import java.sql.Clob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MSM {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)

    private Long msm_id;
    private String msm_nama;
    private String msm_email;
    private String msm_bod;
    private String msm_qiudao;
    private String msm_alamat;
    private String msm_bakat;
    private Clob msm_foto;
    private String msm_status;
    private Long user_id;
    
    public MSM() {
	}

	protected MSM(String msm_nama,String msm_email,String msm_bod,String msm_qiudao,String msm_alamat,String msm_bakat, Clob msm_foto, String msm_status, Long user_id) {
		super();
		this.msm_nama = msm_nama;
		this.msm_email = msm_email;
        this.msm_bod = msm_bod;
        this.msm_qiudao = msm_qiudao;
        this.msm_alamat = msm_alamat;
        this.msm_bakat = msm_bakat;
        this.msm_foto = msm_foto;
        this.msm_status = msm_status;
        this.user_id = user_id;
    }
    

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getMsm_id() {
		return msm_id;
	}
	public void setMsm_id(Long msm_id) {
		this.msm_id = msm_id;
    }




    public String getMsm_nama() {
		return msm_nama;
	}
	public void setMsm_nama(String msm_nama) {
		this.msm_nama = msm_nama;
    }



    public String getMsm_email() {
		return msm_email;
	}
	public void setMsm_email(String msm_email) {
		this.msm_email = msm_email;
    }



    public String getMsm_bod() {
		return msm_bod;
	}
	public void setMsm_bod(String msm_bod) {
		this.msm_bod = msm_bod;
    }
    

    public String getMsm_alamat() {
		return msm_alamat;
	}
	public void setMsm_alamat(String msm_alamat) {
		this.msm_alamat = msm_alamat;
    }




    public String getMsm_qiudao() {
		return msm_qiudao;
	}
	public void setMsm_qiudao(String msm_qiudao) {
		this.msm_qiudao = msm_qiudao;
    }


    public String getMsm_bakat() {
		return msm_bakat;
	}
	public void setMsm_bakat(String msm_bakat) {
		this.msm_bakat = msm_bakat;
    }


    public Clob getMsm_foto() {
		return msm_foto;
	}
	public void setMsm_foto(Clob msm_foto) {
		this.msm_foto = msm_foto;
    }


    public String getMsm_status() {
		return msm_status;
	}
	public void setMsm_status(String msm_status) {
		this.msm_status = msm_status;
    }


    
    public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
    }

}
