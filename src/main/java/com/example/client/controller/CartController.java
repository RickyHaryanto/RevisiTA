package com.example.client.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.ModelMap;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.client.model.Barang;
import com.example.client.model.Cart;
import com.example.client.service.CartService;
import com.example.client.service.UserService;
import com.example.client.model.User;
import com.example.client.repository.CartRepository;
import com.example.client.model.Horder;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class CartController {
    @Autowired
    private CartService service;
    @Autowired
	private UserService service2;
	@Autowired
	private CartRepository serviceharga;
	

    int currentID = 0;

   
    @RequestMapping(value = "/save_cart/{barang_id}", method = RequestMethod.POST)
	public String saveMemberdiklat(@PathVariable(name = "barang_id") String barang_id,
	RedirectAttributes redirectAttributes, 
    ModelMap modelMap, @RequestParam("cart_jumlah") Integer cart_jumlah)  
    {
		Cart cart = new Cart();
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
		
		
		modelMap.addAttribute("cart_jumlah", cart_jumlah);
		modelMap.addAttribute("barang_id", barang_id);
		
		//get harga
		Integer harga = serviceharga.findharga(barang_id);
		//get subtotal
		Integer subtotal = harga*cart_jumlah;
		System.out.println(subtotal);

		
		//cek apakah item kembar
		Long kesamaan = serviceharga.cekitem(barang_id);
		if (kesamaan == null){
			cart.setCart_jumlah(cart_jumlah);
			cart.setUser_id(usr.getUser_id());
			cart.setBarang_id(Long.parseLong(barang_id));
			cart.setCart_subtotal(subtotal);
			cart.setCart_harga(harga);
			service.save(cart,currentID);
			System.out.println("barang baru");
		}else{
			//UPDATE
			cart=service.get(kesamaan);
			//get jumlah item dari cart
			Long jumlahitem = serviceharga.jumlah(kesamaan);
			//tambahkan dengan jumlah baru
			Long jumlahbaru = jumlahitem+cart_jumlah;
			//get harga
			Long hargaitem = serviceharga.harga(kesamaan);
			//subtotal
			Long subtotalitem = jumlahbaru*hargaitem;
			
			cart.setCart_jumlah(jumlahbaru.intValue());
			cart.setCart_subtotal(subtotalitem.intValue());
			service.save(cart,currentID);
			System.out.println("barang sudah ada");
		}
		


	
		
		subtotal =0;
		harga=0;
		return "redirect:/listbarang.html?success";
    }
    

    @RequestMapping("/cart.html")
	public String viewcart(Model model,HttpSession session) {
		Horder horder = new Horder();
		model.addAttribute("horder", horder);

		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
        
        List listcart = service.listAll2(usr.getUser_id()+"");
		model.addAttribute("listcart", listcart);
		currentID = 0;
		return "cart";
	}
    

    @RequestMapping("/delete_cart.html/{cart_id}")
	public String deleteProduct(@PathVariable(name = "cart_id") int cart_id) {
		service.delete(cart_id);
		return "redirect:/cart.html?success";		
	}

	@RequestMapping("/edit_cart.html/{cart_id}")
	public ModelAndView editdorder(Model model,@PathVariable(name = "cart_id") int cart_id) {
	/*	Barang barang = new Barang();
		model.addAttribute("barang", barang);
		List listbarang = service.listAll();
		model.addAttribute("listbarang", listbarang);*/
		Barang barang = new Barang();
		model.addAttribute("barang", barang);
		
		Integer idbarang = serviceharga.getbarangid(cart_id);
		List detailbarang = serviceharga.getbarang(idbarang);
		model.addAttribute("detailbarang", detailbarang);
		
		
		ModelAndView mav = new ModelAndView("edit_cart.html");
		Cart cart = service.get(cart_id);
		currentID = cart_id;
		mav.addObject("cart", cart);
		
		return mav;
	}




	Cart cart = new Cart();
	@RequestMapping(value = "/save_cart2/{cart_id}", method = RequestMethod.POST) 
	public String savedorder(@PathVariable(name = "cart_id") Long cart_id,
	RedirectAttributes redirectAttributes, 
	@RequestParam("cart_jumlah") Long cart_jumlah,
    ModelMap modelMap
    ){

        modelMap.addAttribute("cart_jumlah", cart_jumlah);


		cart=service.get(currentID);
		
		//getHarga
		Long harga = serviceharga.selecthargaedit(cart_id);
		//getjumlah
		Long jumlah = cart_jumlah;
		//subtotal
		Long newsubtotal = harga*jumlah;

		cart.setCart_jumlah(cart_jumlah.intValue());
		cart.setCart_subtotal(newsubtotal.intValue());
        
        
		service.save(cart,currentID);
		return "redirect:/cart.html";
    }
    
}