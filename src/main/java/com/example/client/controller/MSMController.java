package com.example.client.controller;

import java.util.Base64;
import java.util.List;

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
import javax.servlet.http.HttpSession;

import java.sql.Clob;

import com.example.client.model.MSM;
import com.example.client.service.MSMService;
import com.example.client.model.User;
import com.example.client.service.UserService;


import com.example.client.model.Notif;
import com.example.client.repository.NotifRepository;
import com.example.client.service.NotifService;

@Controller
public class MSMController {
    @Autowired
	private MSMService service; 
	@Autowired
	private NotifService notifservice;
	@Autowired
	private NotifRepository notifrepo;
	@Autowired
	private UserService service2;
	
	int currentID=0;

	@RequestMapping(value = "/save_msm", method = RequestMethod.POST)
	public String saveMSM(RedirectAttributes redirectAttributes, 
	@RequestParam("msm_foto") MultipartFile msm_foto, ModelMap modelMap, 
	@RequestParam("msm_nama") String msm_nama, 
	@RequestParam("msm_email") String msm_email, 
	@RequestParam("msm_alamat") String msm_alamat,
	@RequestParam("msm_bod") String msm_bod, 
	@RequestParam("msm_qiudao") String msm_qiudao, 
	@RequestParam("msm_bakat") String msm_bakat,
	@RequestParam("msm_status") String msm_status
	
	)  {
		MSM msm = new MSM();
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
		

		modelMap.addAttribute("msm_foto", msm_foto);
		modelMap.addAttribute("msm_nama", msm_nama);
		modelMap.addAttribute("msm_email", msm_email);
		modelMap.addAttribute("msm_alamat", msm_alamat);
		modelMap.addAttribute("msm_bod", msm_bod);
		modelMap.addAttribute("msm_qiudao", msm_qiudao);
		modelMap.addAttribute("msm_bakat", msm_bakat);
		modelMap.addAttribute("msm_status", msm_status);
		
		
		


		msm.setMsm_nama(msm_nama);
		msm.setMsm_email(msm_email);
		msm.setMsm_alamat(msm_alamat);

		try {
			String tempbase = Base64.getEncoder().encodeToString(msm_foto.getBytes());
			Clob myclob = new javax.sql.rowset.serial.SerialClob(tempbase.toCharArray());
			msm.setMsm_foto(myclob);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		msm.setMsm_bakat(msm_bakat);
		msm.setMsm_bod(msm_bod);
		msm.setMsm_qiudao(msm_qiudao);
		msm.setUser_id(usr.getUser_id());
		msm.setMsm_status("Belum Diterima");


		service.save(msm,currentID);
		
			//Notif
			List lc = notifrepo.notifanggotasm();
			for (int i=0;i<lc.size();i++)
			{
				Object[] cr=(Object[])lc.get(i);
				Notif notif = new Notif();
				
				notif.setNotif_isi("Anggota SM Telah Mendaftar");
				notif.setNotif_flag("0");
				notif.setNotif_tanggal(java.time.LocalDate.now()+"");
				notif.setUser_id(Long.valueOf(cr[0]+""));
				notifservice.save(notif);
			}

		return "redirect:/pendaftaran_sm.html?success";
	}

	@RequestMapping("/anggota_sm.html")
	public String viewDaftarMSM(Model model) {
		List<MSM> listmsm = service.listAll();
		model.addAttribute("listmsm", listmsm);
		
		return "anggota_sm";
	}

	@RequestMapping("/pendaftaran_sm.html")
	public String showNewPendaftaranPage(Model model,HttpSession session) {
		MSM msm = new MSM();
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
		currentID=0;
		
		model.addAttribute("test",usr.getUser_id());
		model.addAttribute("msm", msm);
		
		return "pendaftaran_sm";
	}
	
	@RequestMapping("/edit_msm.html/{msm_id}")
	public ModelAndView showEditMSMPage(@PathVariable(name = "msm_id") int msm_id) {
		ModelAndView mav = new ModelAndView("edit_msm.html");
		MSM msm = service.get(msm_id);
		mav.addObject("msm", msm);
		return mav;
    }   
}