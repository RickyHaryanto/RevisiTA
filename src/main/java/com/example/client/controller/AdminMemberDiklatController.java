package com.example.client.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigInteger;

import com.example.client.model.MemberDiklat;


import com.example.client.service.AdminMemberDiklatService;

@Controller
public class AdminMemberDiklatController {
    @Autowired
	private AdminMemberDiklatService service; 
	

	@RequestMapping(value = "/adminsave_memberdiklat", method = RequestMethod.POST)
	public String saveMemberdiklat(@ModelAttribute("memberdiklat") MemberDiklat memberdiklat) {
		
		service.save(memberdiklat);
		
		return "redirect:/adminmemberdiklat.html";
	}

	@RequestMapping("/adminmemberdiklat.html")
	public String viewDaftarMemberdiklat(Model model) {
		List listmemberdiklat = service.listAll();
		BigInteger jumlah = service.listAllMember();
		model.addAttribute("listmemberdiklat", listmemberdiklat);
		model.addAttribute("jumlah", jumlah);
		return "adminmemberdiklat";
	}


    
	@RequestMapping("/adminedit_memberdiklat.html/{memberdiklat_id}")
	public ModelAndView showEditMemberdiklatPage(@PathVariable(name = "memberdiklat_id") int memberdiklat_id) {
		ModelAndView mav = new ModelAndView("adminedit_memberdiklat.html");
		MemberDiklat memberdiklat = service.get(memberdiklat_id);
		mav.addObject("memberdiklat", memberdiklat);
		return mav;
    }   
}