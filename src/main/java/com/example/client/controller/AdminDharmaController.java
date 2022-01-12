package com.example.client.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.ModelMap;
import com.example.client.repository.AdminNotifRepository;
import com.example.client.service.AdminNotifService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.client.model.Notif;

import java.sql.Clob;

import com.example.client.model.Dharma;
import com.example.client.service.AdminDharmaService;

@Controller
public class AdminDharmaController {
	int currentID = 0;
	
    @Autowired
	private AdminDharmaService service; 
	@Autowired
	private AdminNotifService notifservice;
	@Autowired
	private AdminNotifRepository notifrepo;

	

	
	@RequestMapping(value = "/adminsave_dharma", method = RequestMethod.POST)
	public String saveDharma(RedirectAttributes redirectAttributes, 
	@RequestParam("dharma_media") MultipartFile dharma_media, ModelMap modelMap, 
	@RequestParam("dharma_isi") String dharma_isi, 
	@RequestParam("dharma_judul") String dharma_judul, 
	@RequestParam("dharma_tanggal") String dharma_tanggal) {	
	
		Dharma dharma = new Dharma();

		dharma.setDharma_isi(dharma_isi);
		dharma.setDharma_judul(dharma_judul);
		dharma.setDharma_tanggal(dharma_tanggal);
		
		try {
			String tempbase = Base64.getEncoder().encodeToString(dharma_media.getBytes());
			Clob myclob = new javax.sql.rowset.serial.SerialClob(tempbase.toCharArray());
		dharma.setDharma_media(myclob);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		service.save(dharma,currentID);




		//Notif
		List lc = notifrepo.notifdharma();
		for (int i=0;i<lc.size();i++)
        {
			Object[] cr=(Object[])lc.get(i);
			Notif notif = new Notif();
            
			notif.setNotif_isi("Rekap Dharma baru telah diUpload");
			notif.setNotif_flag("0");
			notif.setNotif_tanggal(java.time.LocalDate.now()+"");
			notif.setUser_id(Long.valueOf(cr[0]+""));
			notifservice.save(notif);
		}
		return "redirect:/admindaftar_dharma.html?success";
	}

	@RequestMapping("/admindaftar_dharma.html")
	public String viewDaftarDharma(Model model) {
		List listdharma = service.listAll();
		model.addAttribute("listdharma", listdharma);
		
		return "admindaftar_dharma";
	}

    
	@RequestMapping("/adminedit_dharma.html/{dharma_id}")
	public ModelAndView showEditDharmaPage(@PathVariable(name = "dharma_id") int dharma_id) {
		ModelAndView mav = new ModelAndView("adminedit_dharma.html");
		Dharma dharma = service.get(dharma_id);
		currentID = dharma_id;
		mav.addObject("dharma", dharma);
		return mav;
    }   
    
    @RequestMapping("/adminupload_dharma.html")
	public String showNewDharmaPage(Model model) {
		Dharma dharma = new Dharma();
		model.addAttribute("dharma", dharma);
		currentID=0;
		return "adminupload_dharma";
    }
	
	
	@RequestMapping("/delete_dharma.html/{dharma_id}")
	public String deleteProduct(@PathVariable(name = "dharma_id") int dharma_id) {
		service.delete(dharma_id);
		return "redirect:/admindaftar_dharma.html";		
	}
}