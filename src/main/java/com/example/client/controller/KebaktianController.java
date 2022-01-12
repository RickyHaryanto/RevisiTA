package com.example.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.client.model.Kebaktian;
import com.example.client.repository.KebaktianRepository;
import com.example.client.service.KebaktianService;

@Controller
public class KebaktianController {
	
	@Autowired
	private KebaktianService service; 
	@Autowired
	private KebaktianRepository repo;
	String searchtemp;
	
	@RequestMapping(value = "/save_kebaktian", method = RequestMethod.POST)
	public String saveKebaktian(@ModelAttribute("kebaktian") Kebaktian kebaktian) {
		service.save(kebaktian);
		
		return "redirect:/kebaktian.html";
	}

	@RequestMapping("/kebaktian.html")
	public String viewDaftarKebaktian(Model model) {
		List listkebaktian = service.listAll();
		model.addAttribute("listkebaktian", listkebaktian);
		Kebaktian kebaktian = new Kebaktian();
		model.addAttribute("kebaktian", kebaktian);
		return "kebaktian";
	}

	@RequestMapping("/kebaktian_user.html")
	public String viewDaftarKebaktianUser(Model model) {
		List listkebaktian = service.listAll();
		model.addAttribute("listkebaktian", listkebaktian);
		
		return "kebaktian_user";
	}

	@RequestMapping("/edit_kebaktian.html/{kebaktian_id}")
	public ModelAndView showEditKebaktianPage(@PathVariable(name = "kebaktian_id") int kebaktian_id) {
		ModelAndView mav = new ModelAndView("edit_kebaktian.html");
		Kebaktian kebaktian = service.get(kebaktian_id);
		mav.addObject("kebaktian", kebaktian);
		return mav;
	}


	@RequestMapping(value = "/search_kebaktian", method = RequestMethod.POST)
	public String searchkebaktian(RedirectAttributes redirectAttributes, 
	Model model, 
	@RequestParam("kebaktian_tanggal") String kebaktian_tanggal
	) {
		
		searchtemp = kebaktian_tanggal;
		System.out.println(kebaktian_tanggal);
		
		return "redirect:/search_kebaktian.html";
	}


	@RequestMapping("/search_kebaktian.html")
	public String searchkebaktian(Model model) {
		Kebaktian kebaktian = new Kebaktian();
		model.addAttribute("kebaktian", kebaktian);

		List listkebaktiansearch = repo.searchkebaktian(searchtemp);
		model.addAttribute("listkebaktiansearch", listkebaktiansearch);
		
		searchtemp = "";
		return "search_kebaktian";
	}


}
