package com.example.client.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Horder {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)

    private Long horder_id;
    private String horder_atasnama;
    private String horder_tanggal;
    private String horder_status;
    private Long user_id;
	private Integer horder_totalharga;

    public Horder() {
	}

	protected Horder(String horder_atasnama,String horder_tanggal,String horder_status,Long user_id,Integer horder_totalharga) {
		super();
		this.horder_atasnama = horder_atasnama;
		this.horder_tanggal = horder_tanggal;
        this.horder_status = horder_status;
		this.user_id = user_id;
		this.horder_totalharga = horder_totalharga;
    }

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getHorder_id() {
		return horder_id;
	}
	public void setHorder_id(Long horder_id) {
		this.horder_id = horder_id;
    }



    
    public String getHorder_atasnama() {
		return horder_atasnama;
	}
	public void setHorder_atasnama(String horder_atasnama) {
		this.horder_atasnama = horder_atasnama;
    }


    public String getHorder_tanggal() {
		return horder_tanggal;
	}
	public void setHorder_tanggal(String horder_tanggal) {
		this.horder_tanggal = horder_tanggal;
    }


    public String getHorder_status() {
		return horder_status;
	}
	public void setHorder_status(String horder_status) {
		this.horder_status = horder_status;
    }


    
    public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public Integer getHorder_totalharga() {
		return horder_totalharga;
	}
	public void setHorder_totalharga(Integer horder_totalharga) {
		this.horder_totalharga = horder_totalharga;
    }
}