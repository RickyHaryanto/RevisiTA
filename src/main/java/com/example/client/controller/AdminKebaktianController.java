package com.example.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;


import com.example.client.model.Kebaktian;
import com.example.client.service.AdminKebaktianService;

@Controller
public class AdminKebaktianController {
	int currentID = 0;
	@Autowired
	private AdminKebaktianService service; 
	
	@RequestMapping(value = "/adminsave_kebaktian", method = RequestMethod.POST)
	public String saveKebaktian(@ModelAttribute("kebaktian") Kebaktian kebaktian) {
		service.save(kebaktian,Long.valueOf(currentID));
		
		return "redirect:/adminkebaktian.html?w=1";
	}

	@RequestMapping("/adminkebaktian.html")
	public String viewDaftarKebaktian(Model model) {
		List listkebaktian = service.listAll();
		model.addAttribute("listkebaktian", listkebaktian);
		
		return "adminkebaktian";
	}

	@RequestMapping("/adminedit_kebaktian.html/{kebaktian_id}")
	public ModelAndView showEditKebaktianPage(@PathVariable(name = "kebaktian_id") int kebaktian_id) {
		ModelAndView mav = new ModelAndView("adminedit_kebaktian.html");
		Kebaktian kebaktian = service.get(kebaktian_id);
		currentID = kebaktian_id;
		mav.addObject("kebaktian", kebaktian);

		return mav;
	}

	@RequestMapping("/adminadd_kebaktian.html")
	public String addkebaktian(Model model) {
		Kebaktian kebaktian = new Kebaktian();
		model.addAttribute("kebaktian", kebaktian);
		currentID=0;
		return "adminadd_kebaktian";
	}
	
	@RequestMapping("/delete_kebaktian.html/{kebaktian_id}")
	public String deleteProduct(@PathVariable(name = "kebaktian_id") int kebaktian_id) {
		service.delete(kebaktian_id);
		return "redirect:/adminkebaktian.html?success";		
	}

}
