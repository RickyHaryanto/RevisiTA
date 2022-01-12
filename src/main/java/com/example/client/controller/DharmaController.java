package com.example.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.client.model.Dharma;
import com.example.client.repository.DharmaRepository;
import com.example.client.service.DharmaService;

@Controller
public class DharmaController {
	

    @Autowired
	private DharmaService service; 
	@Autowired
	private DharmaRepository repo;
	String searchtemp; 
	

	
	@RequestMapping("/daftar_dharma.html")
	public String viewDaftarDharma(Model model) {
		List listdharma = service.listAll();
		model.addAttribute("listdharma", listdharma);
		Dharma dharma = new Dharma();
		model.addAttribute("dharma", dharma);
		return "daftar_dharma";
	}

	@RequestMapping("/dharma_user.html")
	public String viewDaftarDharmaUser(Model model) {
		List listdharma = service.listAll();
		model.addAttribute("listdharma", listdharma);
		Dharma dharma = new Dharma();
		model.addAttribute("dharma", dharma);
		return "dharma_user";
	}

	@RequestMapping(value = "/search_dharma", method = RequestMethod.POST)
	public String searchEvent(RedirectAttributes redirectAttributes, 
	Model model, 
	@RequestParam("dharma_judul") String dharma_judul
	) {
		
		searchtemp = dharma_judul;
		System.out.println(dharma_judul);
		
		return "redirect:/search_dharma.html";
	}


@RequestMapping("/search_dharma.html")
	public String searchdharma(Model model) {
		Dharma dharma = new Dharma();
		model.addAttribute("dharma", dharma);

		List listdharmasearch = repo.searchdharma(searchtemp);
		model.addAttribute("listdharmasearch", listdharmasearch);
		
		searchtemp = "";
		return "search_dharma";
	}
    

    
    
}