package com.example.client.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DonasiUang {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)

    private Long donasiuang_id;
    private String donasiuang_tanggal;
	private String donasiuang_nomornota;
	private String donasiuang_nama;
	private String donasiuang_email;
    private Integer donasiuang_nominal;
	private Long user_id;

    public DonasiUang() {
	}

	protected DonasiUang(String donasiuang_tanggal,String donasiuang_nomornota,String donasiuang_nama, String donasiuang_email, Integer donasiuang_nominal, Long user_id ) {
		super();
		this.donasiuang_tanggal = donasiuang_tanggal;
		this.donasiuang_nomornota = donasiuang_nomornota;
		this.donasiuang_nama = donasiuang_nama;
		this.donasiuang_email = donasiuang_email;
		this.donasiuang_nominal = donasiuang_nominal;
		this.user_id = user_id;
    }


    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getDonasiuang_id() {
		return donasiuang_id;
	}

	public void setDonasiuang_id(Long donasiuang_id) {
		this.donasiuang_id = donasiuang_id;
    }

    public String getDonasiuang_tanggal() {
		return donasiuang_tanggal;
	}

	public void setDonasiuang_tanggal(String donasiuang_tanggal) {
		this.donasiuang_tanggal = donasiuang_tanggal;
    }

    public String getDonasiuang_nama() {
		return donasiuang_nama;
	}

	public void setDonasiuang_nama(String donasiuang_nama) {
		this.donasiuang_nama = donasiuang_nama;
	}

	public String getDonasiuang_email() {
		return donasiuang_email;
	}

	public void setDonasiuang_email(String donasiuang_email) {
		this.donasiuang_email = donasiuang_email;
	}


	public String getDonasiuang_nomornota() {
		return donasiuang_nomornota;
	}

	public void setDonasiuang_nomornota(String donasiuang_nomornota) {
		this.donasiuang_nomornota = donasiuang_nomornota;
	}
	

	public Integer getDonasiuang_nominal() {
		return donasiuang_nominal;
	}

	public void setDonasiuang_nominal(Integer donasiuang_nominal) {
		this.donasiuang_nominal = donasiuang_nominal;
    }
    

    public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
    }

}