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


import com.example.client.repository.AdminNotifRepository;
import com.example.client.service.AdminNotifService;
import com.example.client.model.Notif;

import com.example.client.model.SMP;
import com.example.client.service.AdminSMPService;

@Controller
public class AdminSMPController {
    @Autowired
	private AdminSMPService service; 
	
@Autowired
private AdminNotifService notifservice;
@Autowired
private AdminNotifRepository notifrepo;

	@RequestMapping(value = "/adminsave_smp", method = RequestMethod.POST)
	public String saveSMP(@ModelAttribute("smp") SMP smp) {
		service.save(smp);
		
		List lc = notifrepo.notifmembersm();
		for (int i=0;i<lc.size();i++)
        {
			Object[] cr=(Object[])lc.get(i);
			Notif notif = new Notif();
            
			notif.setNotif_isi("Pengumuman baru telah diUpload");
			notif.setNotif_flag("0");
			notif.setNotif_tanggal(java.time.LocalDate.now()+"");
			notif.setUser_id(Long.valueOf(cr[9]+""));
			notifservice.save(notif);
		}

		return "redirect:/admindaftar_pengumuman_sm.html";
	}

	@RequestMapping("/admindaftar_pengumuman_sm.html")
	public String viewDaftarSMP(Model model) {
		List listsmp = service.listAll();
		model.addAttribute("listsmp", listsmp);
		
		return "admindaftar_pengumuman_sm.html";
	}

    @RequestMapping("/adminpengumuman_sm.html")
	public String showNewSMPPage(Model model) {
		SMP smp = new SMP();
		model.addAttribute("smp", smp);
		
		return "adminpengumuman_sm";
    }
    
	@RequestMapping("/adminedit_smp.html/{smp_id}")
	public ModelAndView showEditSMPPage(@PathVariable(name = "smp_id") int smp_id) {
		ModelAndView mav = new ModelAndView("adminedit_smp.html");
		SMP smp = service.get(smp_id);
		mav.addObject("smp", smp);
		return mav;
	}

	@RequestMapping("/delete_smp.html/{smp_id}")
	public String deleteProduct(@PathVariable(name = "smp_id") int smp_id) {
		service.delete(smp_id);
		return "redirect:/admindaftar_pengumuman_sm.html?success";		
	}

}