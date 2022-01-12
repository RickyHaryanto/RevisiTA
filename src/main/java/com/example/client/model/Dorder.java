package com.example.client.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Dorder {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long dorder_id;
	private Long horder_id;
    private Long dorder_jumlah;
    private Long barang_id;
	private Integer dorder_subtotal;
	private Integer dorder_harga;
	

    public Dorder() {
	}

	protected Dorder(Long horder_id, Long dorder_jumlah,Long barang_id,Integer dorder_subtotal,Integer dorder_harga) {
		super();
		this.horder_id = horder_id;
		this.dorder_jumlah = dorder_jumlah;
	
		this.barang_id = barang_id;
		this.dorder_subtotal = dorder_subtotal;
		this.dorder_harga = dorder_harga;
    }


    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getDorder_id() {
		return dorder_id;
	}
	public void setDorder_id(Long dorder_id) {
		this.dorder_id = dorder_id;
    }

	public Long getHorder_id() {
		return horder_id;
	}
	public void setHorder_id(Long horder_id) {
		this.horder_id = horder_id;
	}
	

    public Long getDorder_jumlah() {
		return dorder_jumlah;
	}
	public void setDorder_jumlah(Long dorder_jumlah) {
		this.dorder_jumlah = dorder_jumlah;
    }


  

    public Long getBarang_id() {
		return barang_id;
	}
	public void setBarang_id(Long barang_id) {
		this.barang_id = barang_id;
	}
	
	public Integer getDorder_subtotal() {
		return dorder_subtotal;
	}
	public void setDorder_subtotal(Integer dorder_subtotal) {
		this.dorder_subtotal = dorder_subtotal;
	}

	public Integer getDorder_harga() {
		return dorder_harga;
	}
	public void setDorder_harga(Integer dorder_harga) {
		this.dorder_harga = dorder_harga;
	}
	
}