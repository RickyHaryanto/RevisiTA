package com.example.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.client.service.UserService;

import java.util.List;

import com.example.client.model.Berita;
import com.example.client.repository.BeritaRepository;
import com.example.client.service.BeritaService;

@Controller
public class BeritaController {
	
	@Autowired
	private BeritaService service; 
	@Autowired
	private BeritaRepository repo; 
	
	@Autowired
	private UserService service2;
	String searchtemp;


	@RequestMapping(value = "/search_berita", method = RequestMethod.POST)
	public String searchberita(RedirectAttributes redirectAttributes, 
	Model model, 
	@RequestParam("berita_judul") String berita_judul
	) {
		
		searchtemp = berita_judul;
		System.out.println(berita_judul);
		
		return "redirect:/search_berita.html";
	}


@RequestMapping("/search_berita.html")
	public String searchberita(Model model) {
		Berita berita = new Berita();
		model.addAttribute("berita", berita);

		List listberitasearch = repo.searchberita(searchtemp);
		model.addAttribute("listberitasearch", listberitasearch);
		
		searchtemp = "";
		return "search_berita";
	}

/*
	@RequestMapping("/listberita.html")
	public String viewDaftarBerita(Model model) {
		
			List<Berita> listberita = service.listAll();
			model.addAttribute("listberita", listberita);
			return "listberita";

	}
*/
	
	
}