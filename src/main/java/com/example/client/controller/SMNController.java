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
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.client.model.User;
import com.example.client.repository.SMNRepository;
import com.example.client.service.UserService;

import com.example.client.model.SMN;
import com.example.client.service.SMNService;

@Controller
public class SMNController {
    @Autowired
	private SMNService service; 
	@Autowired
	private SMNRepository repo; 
	
	String searchtemp;
	@Autowired
	private UserService service2;

	@RequestMapping(value = "/save_smn", method = RequestMethod.POST)
	public String saveSMN(@ModelAttribute("smn") SMN smn) {
		service.save(smn);
		
		return "redirect:/list_nilai.html";
	}

	@RequestMapping("/list_nilai.html")
	public String viewDaftarNilai(Model model) {
		
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());

		SMN smn = new SMN();
		model.addAttribute("smn", smn);
				
		List listsmn = service.listAll(usr.getUser_id()+"");
		model.addAttribute("listsmn", listsmn);
		model.addAttribute("test",usr.getUser_id());
		return "list_nilai";
	}

    @RequestMapping("/add_SMN.html")
	public String showNewSMNPage(Model model) {
		SMN smn = new SMN();
		model.addAttribute("smn", smn);
		
		return "add_SMN";
    }
    
	@RequestMapping("/edit_smn.html/{smn_id}")
	public ModelAndView showEditSMNPage(@PathVariable(name = "smn_id") int smn_id) {
		ModelAndView mav = new ModelAndView("edit_smn.html");
		SMN smn = service.get(smn_id);
		mav.addObject("smn", smn);
		return mav;
	}

	@RequestMapping(value = "/search_smn", method = RequestMethod.POST)
	public String searchsmn(RedirectAttributes redirectAttributes, 
	Model model, 
	@RequestParam("smn_tanggal") String smn_tanggal
	) {
		
		searchtemp = smn_tanggal;
		System.out.println(smn_tanggal);
		
		return "redirect:/search_smn.html";
	}


	@RequestMapping("/search_smn.html")
	public String searchdharma(Model model) {
		SMN smn = new SMN();
		model.addAttribute("smn", smn);


		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());

		List listsmnsearch = repo.searchsmn(usr.getUser_id()+"",searchtemp);
		model.addAttribute("listsmnsearch", listsmnsearch);
		
		searchtemp = "";
		return "search_smn";
	}
}