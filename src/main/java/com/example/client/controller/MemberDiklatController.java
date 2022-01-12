package com.example.client.controller;
import java.util.List;
import com.example.client.model.User;
import com.example.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.ModelMap;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.client.model.Notif;
import com.example.client.repository.NotifRepository;
import com.example.client.service.NotifService;


import com.example.client.model.MemberDiklat;
import com.example.client.service.MemberDiklatService;

@Controller
public class MemberDiklatController {
    @Autowired
	private MemberDiklatService service; 
	@Autowired
	private UserService service2;
	
	@Autowired
	private NotifService notifservice;
	@Autowired
	private NotifRepository notifrepo;
	int currentID = 0;

	 

	@RequestMapping(value = "/save_memberdiklat/{diklat_id}", method = RequestMethod.POST)
	public String saveMemberdiklat(@PathVariable(name = "diklat_id") String diklat_id,
	RedirectAttributes redirectAttributes, 
	ModelMap modelMap, 
	@RequestParam("memberdiklat_nama") String memberdiklat_nama, 
	@RequestParam("memberdiklat_tanggal") String memberdiklat_tanggal, 
	@RequestParam("memberdiklat_email") String memberdiklat_email,
	@RequestParam("memberdiklat_status") String memberdiklat_status
	)  {
		MemberDiklat memberdiklat = new MemberDiklat();

		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
		
		modelMap.addAttribute("memberdiklat_nama", memberdiklat_nama);
		modelMap.addAttribute("memberdiklat_tanggal", memberdiklat_tanggal);
		modelMap.addAttribute("memberdiklat_email", memberdiklat_email);
		modelMap.addAttribute("memberdiklat_status", memberdiklat_status);
		modelMap.addAttribute("diklat_id", diklat_id);
		
		
		memberdiklat.setMemberdiklat_nama(memberdiklat_nama);
		memberdiklat.setMemberdiklat_email(memberdiklat_email);
		memberdiklat.setMemberdiklat_tanggal(memberdiklat_tanggal);
		memberdiklat.setMemberdiklat_status("Belum Diterima");
		memberdiklat.setUser_id(usr.getUser_id());
		memberdiklat.setDiklat_id(Long.parseLong(diklat_id));
		


		service.save(memberdiklat,currentID);
		
		//Notif
		List lc = notifrepo.notifdaftradiklat();
		for (int i=0;i<lc.size();i++)
        {
			Object[] cr=(Object[])lc.get(i);
			Notif notif = new Notif();
            
			notif.setNotif_isi("Anggota Diklat Mendaftar");
			notif.setNotif_flag("0");
			notif.setNotif_tanggal(java.time.LocalDate.now()+"");
			notif.setUser_id(Long.valueOf(cr[0]+""));
			notifservice.save(notif);
		}
		return "redirect:/daftar_diklat.html?success";
	}


	@RequestMapping("/pendaftaran_diklat.html/{diklat_id}")
	public String showEditDiklatPage(Model model, @PathVariable(name = "diklat_id") int diklat_id) {
		MemberDiklat memberdiklat = new MemberDiklat();
		model.addAttribute("memberdiklat", memberdiklat);
		model.addAttribute("showid", diklat_id);
		currentID=0;
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
		model.addAttribute("test",usr.getUser_id());

		return "pendaftaran_diklat";
	}
	   
}