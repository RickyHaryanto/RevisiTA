package com.example.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.client.model.Donasi;
import com.example.client.service.AdminDonasiService;
import com.example.client.service.AdminDonasiUangService;

@Controller
public class AdminDonasiController {
    @Autowired
	private AdminDonasiService service; 

	@Autowired
	private AdminDonasiUangService service2; 

	
	@RequestMapping(value = "/adminsave_donasi", method = RequestMethod.POST)
	public String saveDonasi(@ModelAttribute("donasi") Donasi donasi) {
		service.save(donasi);
		
		return "redirect:/adminlist_donasi.html";
	}

	@RequestMapping("/adminlist_donasi.html")
	public String viewDaftarDonasi(Model model) {
		List listdonasi = service.listAll();
		model.addAttribute("listdonasi", listdonasi);


		List listdonasiuang = service2.listAll();
		model.addAttribute("listdonasiuang", listdonasiuang);
		
		return "adminlist_donasi";
	}

    @RequestMapping("/admindonasi.html")
	public String showNewDonasiPage(Model model) {
		Donasi donasi = new Donasi();
		model.addAttribute("donasi", donasi);

		List daftarumat = service.daftar();
		model.addAttribute("daftarumat", daftarumat);

		return "admindonasi";
    }
    
	
}