package com.example.client.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notif {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long notif_id;
	private String notif_isi;
	private String notif_tanggal;
    private String notif_flag;
    private Long user_id;
	
	
	public Notif() {
	}

	protected Notif(String notif_isi,String notif_tanggal,String notif_flag,Long user_id) {
		super();
		this.notif_isi = notif_isi;
		this.notif_tanggal = notif_tanggal;
		this.notif_flag = notif_flag;
		this.user_id = user_id;
    }
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getNotif_id() {
		return notif_id;
	}

	public void setNotif_id(Long notif_id) {
		this.notif_id = notif_id;
	}

	public String getNotif_isi() {
		return notif_isi;
    }
    
    public void setNotif_isi(String notif_isi) {
		this.notif_isi = notif_isi;
    }
    

    public String getNotif_tanggal() {
		return notif_tanggal;
    }
    
    public void setNotif_tanggal(String notif_tanggal) {
		this.notif_tanggal = notif_tanggal;
    }
    
    public String getNotif_flag() {
		return notif_flag;
    }
    
    public void setNotif_flag(String notif_flag) {
		this.notif_flag = notif_flag;
    }
    
    public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
}