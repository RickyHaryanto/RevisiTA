package com.example.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.client.model.Barang;
import com.example.client.model.Cart;
import com.example.client.service.BarangService;
import com.example.client.repository.BarangRepository;

@Controller
public class BarangController {
	int currentID = 0;
	
	@Autowired
	private BarangService service; 
	@Autowired
	private BarangRepository repo; 
	String searchtemp;

	
	private Barang barang = new Barang();

	

	@RequestMapping("/listbarang.html")
	public String viewDaftarBarang(Model model) {
		Barang barang = new Barang();
		model.addAttribute("barang", barang);
		List listbarang = service.listAll();
		model.addAttribute("listbarang", listbarang);
		
		return "listbarang";
	}

	@RequestMapping(value = "/search_barang", method = RequestMethod.POST)
	public String searchEvent(RedirectAttributes redirectAttributes, 
	Model model, 
	@RequestParam("barang_nama") String barang_nama
	) {
		
		searchtemp = barang_nama;
		System.out.println(barang_nama);
		
		return "redirect:/search_barang.html";
	}

	@RequestMapping("/search_barang.html")
	public String searchbarang(Model model) {
		Barang barang = new Barang();
		model.addAttribute("barang", barang);

		List listbarangsearch = repo.searchbarang(searchtemp);
		model.addAttribute("listbarangsearch", listbarangsearch);
		
		searchtemp = "";
		return "search_barang";
	}


	@RequestMapping("/detailbarang.html/{barang_id}")
	public String viewDetail(Model model,@PathVariable(name = "barang_id") int barang_id) {
		List listdetail = repo.detailbarang(barang_id);
		model.addAttribute("listdetail", listdetail);
		Cart cart = new Cart();
		model.addAttribute("cart", cart);
		return "detailbarang";
	}
	
	

}