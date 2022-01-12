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

import com.example.client.model.Diklat;
import com.example.client.model.User;
import com.example.client.repository.DiklatRepository;
import com.example.client.service.UserService;

import com.example.client.service.DiklatService;

import org.springframework.security.core.context.SecurityContextHolder;
@Controller
public class DiklatController {
	int currentID = 0;
	
	@Autowired
	private DiklatService service; 
	@Autowired
	private DiklatRepository repo;
	@Autowired
	private UserService service2;
	String searchtemp;

	@RequestMapping("/daftar_diklat.html")
		public String viewDaftarDiklat(Model model) {
		List listDiklat = service.listAll();
		model.addAttribute("listDiklat", listDiklat);
		
		Diklat diklat = new Diklat();
		model.addAttribute("diklat", diklat);
		
		//show active diklat
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
			
		List listDiklat2 = service.listAll2(usr.getUser_id());
		model.addAttribute("listDiklat2", listDiklat2);
		
		return "daftar_diklat";
	}

	@RequestMapping("/daftardiklat_user.html")
		public String viewDaftarDiklatUser(Model model) {
		List listDiklat = service.listAll();
		model.addAttribute("listDiklat", listDiklat);
		
		Diklat diklat = new Diklat();
		model.addAttribute("diklat", diklat);
		
		
		return "daftardiklat_user";
	}

	@RequestMapping("/detaildiklat.html/{diklat_id}")
	public String viewDetail(Model model,@PathVariable(name = "diklat_id") int diklat_id) {
		List listdiklatdetail = repo.detaildiklat(diklat_id);
		model.addAttribute("listdiklatdetail", listdiklatdetail);
		
		return "detaildiklat";
	}
	
	@RequestMapping(value = "/search_diklat", method = RequestMethod.POST)
	public String searchdiklat(RedirectAttributes redirectAttributes, 
	Model model, 
	@RequestParam("diklat_nama") String diklat_nama
	) {
		
		searchtemp = diklat_nama;
		System.out.println(diklat_nama);
		
		return "redirect:/search_diklat.html";
	}

	@RequestMapping("/search_diklat.html")
	public String searchdiklat(Model model) {
		Diklat diklat = new Diklat();
		model.addAttribute("diklat", diklat);
		

		List listdiklatsearch = repo.searchdiklat(searchtemp);
		model.addAttribute("listdiklatsearch", listdiklatsearch);
		
		searchtemp = "";
		return "search_diklat";
	}

}
