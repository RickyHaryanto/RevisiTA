package com.example.client.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MemberDiklat {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)

    private Long memberdiklat_id;
    private String memberdiklat_tanggal;
	private String memberdiklat_status;
	private String memberdiklat_nama;
	private String memberdiklat_email;
    private Long user_id;
    private Long diklat_id;

    public MemberDiklat() {
	}

	protected MemberDiklat(String memberdiklat_tanggal,String memberdiklat_status,String memberdiklat_nama, String memberdiklat_email, Long user_id, Long diklat_id) {
		super();
		this.memberdiklat_tanggal = memberdiklat_tanggal;
		this.memberdiklat_status = memberdiklat_status;
        this.user_id = user_id;
		this.diklat_id = diklat_id;
		this.memberdiklat_nama = memberdiklat_nama;
		this.memberdiklat_email = memberdiklat_email;
    }

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getMemberdiklat_id() {
		return memberdiklat_id;
	}

	public void setMemberdiklat_id(Long memberdiklat_id) {
		this.memberdiklat_id = memberdiklat_id;
    }

    public String getMemberdiklat_tanggal() {
		return memberdiklat_tanggal;
	}

	public void setMemberdiklat_tanggal(String memberdiklat_tanggal) {
		this.memberdiklat_tanggal = memberdiklat_tanggal;
    }

    public String getMemberdiklat_status() {
		return memberdiklat_status;
	}

	public void setMemberdiklat_status(String memberdiklat_status) {
		this.memberdiklat_status = memberdiklat_status;
	}
	
	public String getMemberdiklat_nama() {
		return memberdiklat_nama;
	}

	public void setMemberdiklat_nama(String memberdiklat_nama) {
		this.memberdiklat_nama = memberdiklat_nama;
	}
	
	public String getMemberdiklat_email() {
		return memberdiklat_email;
	}

	public void setMemberdiklat_email(String memberdiklat_email) {
		this.memberdiklat_email = memberdiklat_email;
    }

    public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
    }

    public Long getDiklat_id() {
		return diklat_id;
	}

	public void setDiklat_id(Long diklat_id) {
		this.diklat_id = diklat_id;
    }
}