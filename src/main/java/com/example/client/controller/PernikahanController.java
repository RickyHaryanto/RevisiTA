package com.example.client.controller;

import java.util.Base64;
import java.util.List;
import com.example.client.model.User;
import com.example.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.ModelMap;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.client.model.Notif;
import com.example.client.repository.NotifRepository;
import com.example.client.service.NotifService;
import java.sql.Clob;

import com.example.client.model.Pernikahan;
import com.example.client.service.PernikahanService;

@Controller
public class PernikahanController {
	
	int currentID=0;
	@Autowired
	private NotifService notifservice;
	@Autowired
	private NotifRepository notifrepo;
	@Autowired
	private PernikahanService service; 
	@Autowired
	private UserService service2;

	@RequestMapping(value = "/save_pernikahan", method = RequestMethod.POST)
	public String savePernikahan(RedirectAttributes redirectAttributes, 
	@RequestParam("pernikahan_fotopria") MultipartFile pernikahan_fotopria, 
	@RequestParam("pernikahan_fotowanita") MultipartFile pernikahan_fotowanita, 
	ModelMap modelMap, 
	@RequestParam("pernikahan_namapria") String pernikahan_namapria, 
	@RequestParam("pernikahan_namawanita") String pernikahan_namawanita, 
	@RequestParam("pernikahan_tanggal") String pernikahan_tanggal,
	@RequestParam("pernikahan_waktu") String pernikahan_waktu, 
	@RequestParam("pernikahan_status") String pernikahan_status, 
	@RequestParam("pernikahan_keterangan") String pernikahan_keterangan
	)  {
		Pernikahan pernikahan = new Pernikahan();

		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
		
		modelMap.addAttribute("pernikahan_fotopria", pernikahan_fotopria);
		modelMap.addAttribute("pernikahan_fotowanita", pernikahan_fotowanita);
		modelMap.addAttribute("pernikahan_namapria", pernikahan_namapria);
		modelMap.addAttribute("pernikahan_namawanita", pernikahan_namawanita);
		modelMap.addAttribute("pernikahan_tanggal", pernikahan_tanggal);
		modelMap.addAttribute("pernikahan_waktu", pernikahan_waktu);
		modelMap.addAttribute("pernikahan_status", pernikahan_status);
		modelMap.addAttribute("pernikahan_keterangan", pernikahan_keterangan);
		
	
		pernikahan.setPernikahan_namapria(pernikahan_namapria);
		pernikahan.setPernikahan_namawanita(pernikahan_namawanita);
		try {
			String tempbase = Base64.getEncoder().encodeToString(pernikahan_fotopria.getBytes());
			Clob myclob = new javax.sql.rowset.serial.SerialClob(tempbase.toCharArray());
			pernikahan.setPernikahan_fotopria(myclob);
			
			String tempbase2 = Base64.getEncoder().encodeToString(pernikahan_fotowanita.getBytes());
			Clob myclob2 = new javax.sql.rowset.serial.SerialClob(tempbase2.toCharArray());
			
			pernikahan.setPernikahan_fotowanita(myclob2);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		pernikahan.setPernikahan_tanggal(pernikahan_tanggal);
		pernikahan.setPernikahan_waktu(pernikahan_waktu);
		pernikahan.setPernikahan_keterangan(pernikahan_keterangan);
		pernikahan.setUser_id(usr.getUser_id());
		pernikahan.setPernikahan_status("Belum Diterima");


		service.save(pernikahan,currentID);
		

		//Notif
		List lc = notifrepo.notifdaftradiklat();
		for (int i=0;i<lc.size();i++)
        {
			Object[] cr=(Object[])lc.get(i);
			Notif notif = new Notif();
            
			notif.setNotif_isi("Pendaftaran Pemberkatan diterima");
			notif.setNotif_flag("0");
			notif.setNotif_tanggal(java.time.LocalDate.now()+"");
			notif.setUser_id(Long.valueOf(cr[0]+""));
			notifservice.save(notif);
		}
		return "redirect:/pernikahan.html?success";
	}

	@RequestMapping("/pernikahan.html")
	public String showNewPernikahanPage(Model model) {
		Pernikahan pernikahan = new Pernikahan();
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
		
		currentID=0;
		model.addAttribute("test",usr.getUser_id());
		model.addAttribute("pernikahan", pernikahan);
		return "pernikahan";
    }

    
	@RequestMapping("/edit_pernikahan.html/{pernikahan_id}")
	public ModelAndView showEditPernikahanPage(@PathVariable(name = "pernikahan_id") int pernikahan_id) {
		ModelAndView mav = new ModelAndView("edit_pernikahan.html");
		Pernikahan pernikahan = service.get(pernikahan_id);
		mav.addObject("pernikahan", pernikahan);
		return mav;
    }
}