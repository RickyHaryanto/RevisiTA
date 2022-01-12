package com.example.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.client.model.SMP;
import com.example.client.repository.SMPRepository;
import com.example.client.service.SMPService;

@Controller
public class SMPController {
    @Autowired
	private SMPService service; 
	@Autowired
	private SMPRepository repo; 
	String searchtemp;
	
	@RequestMapping(value = "/save_smp", method = RequestMethod.POST)
	public String saveSMP(@ModelAttribute("smp") SMP smp) {
		service.save(smp);
		
		return "redirect:/daftar_pengumuman_sm.html";
	}

	@RequestMapping("/daftar_pengumuman_sm.html")
	public String viewDaftarSMP(Model model) {
		List listsmp = service.listAll();
		model.addAttribute("listsmp", listsmp);
		SMP smp = new SMP();
		model.addAttribute("smp", smp);
		return "daftar_pengumuman_sm";
	}

    @RequestMapping("/pengumuman_sm.html")
	public String showNewSMPPage(Model model) {
		SMP smp = new SMP();
		model.addAttribute("smp", smp);
		
		return "pengumuman_sm";
    }
    
	@RequestMapping("/edit_smp.html/{smp_id}")
	public ModelAndView showEditSMPPage(@PathVariable(name = "smp_id") int smp_id) {
		ModelAndView mav = new ModelAndView("edit_smp.html");
		SMP smp = service.get(smp_id);
		mav.addObject("smp", smp);
		return mav;
	}

	@RequestMapping(value = "/search_smp", method = RequestMethod.POST)
	public String searchsmp(RedirectAttributes redirectAttributes, 
	Model model, 
	@RequestParam("smp_tanggal") String smp_tanggal
	) {
		
		searchtemp = smp_tanggal;
		System.out.println(smp_tanggal);
		
		return "redirect:/search_smp.html";
	}


	@RequestMapping("/search_smp.html")
	public String searchsmp(Model model) {
		SMP smp = new SMP();
		model.addAttribute("smp", smp);

		List listsmpsearch = repo.searchsmp(searchtemp);
		model.addAttribute("listsmpsearch", listsmpsearch);
		
		searchtemp = "";
		return "search_smp";
	}

}