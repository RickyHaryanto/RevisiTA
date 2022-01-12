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

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Clob;

import com.example.client.repository.AdminNotifRepository;
import com.example.client.service.AdminNotifService;
import com.example.client.model.Notif;
import com.example.client.model.Berita;
import com.example.client.service.AdminBeritaService;

@Controller
public class AdminBeritaController {
	int currentID = 0;

	
	@Autowired
	private AdminBeritaService service; 
	@Autowired
	private AdminNotifService notifservice;
	@Autowired
	private AdminNotifRepository notifrepo;


	@RequestMapping(value = "/adminsave_berita", method = RequestMethod.POST)
	public String saveBerita(RedirectAttributes redirectAttributes, 
	@RequestParam("berita_file") MultipartFile berita_file, ModelMap modelMap, 
	@RequestParam("berita_judul") String berita_judul, 
	@RequestParam("berita_isi") String berita_isi, 
	@RequestParam("berita_tanggal") String berita_tanggal) {	
	
		Berita berita = new Berita();

		modelMap.addAttribute("berita_file", berita_file);
		modelMap.addAttribute("berita_judul", berita_judul);
		modelMap.addAttribute("berita_isi", berita_isi);
		modelMap.addAttribute("berita_tanggal", berita_tanggal);
		
		
		berita.setBerita_tanggal(berita_tanggal);
		berita.setBerita_judul(berita_judul);
		berita.setBerita_isi(berita_isi);
		berita.setBerita_tanggal(berita_tanggal);
		
		try {
			String tempbase = Base64.getEncoder().encodeToString(berita_file.getBytes());
			Clob myclob = new javax.sql.rowset.serial.SerialClob(tempbase.toCharArray());
			berita.setBerita_file(myclob);
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		service.save(berita,currentID);
		
		List lc = notifrepo.notifberita();
		for (int i=0;i<lc.size();i++)
        {
			Object[] cr=(Object[])lc.get(i);
			Notif notif = new Notif();
            
			notif.setNotif_isi("Berita baru telah diUpload");
			notif.setNotif_flag("0");
			notif.setNotif_tanggal(java.time.LocalDate.now()+"");
			notif.setUser_id(Long.valueOf(cr[0]+""));
			notifservice.save(notif);
		}


		return "redirect:/adminlistberita.html?success";
	}

	@RequestMapping("/adminlistberita.html")
	public String viewDaftarBerita(Model model) {
		List listberita = service.listAll();
		model.addAttribute("listberita", listberita);
		
		return "adminlistberita";
	}

	@RequestMapping("/adminupload_berita.html")
	public String showNewBeritaPage(Model model) {
		Berita berita = new Berita();
		model.addAttribute("berita", berita);
		currentID=0;
		return "adminupload_berita";
    }
    
	@RequestMapping("/adminedit_berita.html/{berita_id}")
	public ModelAndView showEditBeritaPage(@PathVariable(name = "berita_id") int berita_id) {
		ModelAndView mav = new ModelAndView("adminedit_berita.html");
		Berita berita = service.get(berita_id);
		currentID = berita_id;
		mav.addObject("berita", berita);
		return mav;
	}

	@RequestMapping("/delete_berita.html/{berita_id}")
	public String deleteProduct(@PathVariable(name = "berita_id") int berita_id) {
		service.delete(berita_id);
		return "redirect:/adminlistberita.html";		
	}

}