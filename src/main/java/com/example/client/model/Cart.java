package com.example.client.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cart {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)

    private Long cart_id;
    private Long barang_id;
    private Long user_id;
    private Integer cart_jumlah;
	private Integer cart_subtotal;
	private Integer cart_harga;
	
    

    public Cart() {
	}

	protected Cart(Long barang_id,Long user_id,Integer cart_jumlah,Integer cart_subtotal,Integer cart_harga) {
		super();
		this.barang_id = barang_id;
		this.user_id = user_id;
       
        this.cart_jumlah = cart_jumlah;
		this.cart_subtotal = cart_subtotal;
		this.cart_harga = cart_harga;
    }

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCart_id() {
		return cart_id;
	}

	public void setCart_id(Long cart_id) {
		this.cart_id = cart_id;
    }


    public Integer getCart_subtotal() {
		return cart_subtotal;
	}

	public void setCart_subtotal(Integer cart_subtotal) {
		this.cart_subtotal = cart_subtotal;
	}
	

	public Integer getCart_jumlah() {
		return cart_jumlah;
	}

	public void setCart_jumlah(Integer cart_jumlah) {
		this.cart_jumlah = cart_jumlah;
	}
	
	public Integer getCart_harga() {
		return cart_harga;
	}

	public void setCart_harga(Integer cart_harga) {
		this.cart_harga = cart_harga;
    }

    public Long getBarang_id() {
		return barang_id;
	}

	public void setBarang_id(Long barang_id) {
		this.barang_id = barang_id;
    }

    public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
    }
}