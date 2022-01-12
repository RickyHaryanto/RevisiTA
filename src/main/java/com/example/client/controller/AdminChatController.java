package com.example.client.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.client.service.AdminUserService;

import com.example.client.repository.AdminNotifRepository;
import com.example.client.service.AdminNotifService;
import com.example.client.model.Notif;
import com.example.client.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.client.model.Chat;
import com.example.client.service.AdminChatService;

@Controller
public class AdminChatController {
    @Autowired
	private AdminChatService service; 
	
	@Autowired
	private AdminNotifService notifservice;
	@Autowired
	private AdminNotifRepository notifrepo;
	@Autowired
	private AdminUserService service2;


	int currentID = 0;

	@RequestMapping("/admindaftar_chat.html")
	public String viewDaftarChat(Model model) {
		List listchat = service.listAll();
		model.addAttribute("listchat", listchat);
		currentID=0;
		return "admindaftar_chat";
	}

	@RequestMapping("/adminedit_chat.html/{chat_id}")
	public ModelAndView showEditChatPage(@PathVariable(name = "chat_id") int chat_id) {
		ModelAndView mav = new ModelAndView("adminedit_chat.html");
		Chat chat = service.get(chat_id);
		currentID = chat_id;
		mav.addObject("chat", chat);
		
		return mav;
    }
	
	Chat chat = new Chat();

    @RequestMapping(value = "/adminsave_chat", method = RequestMethod.POST)
	public String saveChat(RedirectAttributes redirectAttributes, 
	ModelMap modelMap, 
	@RequestParam("user_id") Long user_id, 
	@RequestParam("chat_pertanyaan") String chat_pertanyaan, 
	@RequestParam("chat_jawaban") String chat_jawaban,
	@RequestParam("chat_date") String chat_date,
	@RequestParam("chat_time") String chat_time
	
	) {
		
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());


		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();
		String waktu = localTime+"";
		String hasilwaktu = waktu.substring(0,8);

		chat.setChat_jawaban(chat_jawaban);
		chat.setChat_pertanyaan(chat_pertanyaan);
		chat.setChat_date(chat_date);
		chat.setChat_time(chat_time);
		chat.setChat_dateadmin(localDate+"");
		chat.setChat_timeadmin(hasilwaktu);
		
		chat.setUser_id(user_id);
		service.save(chat,currentID);
		

		List lc = notifrepo.notifchat(user_id);
		for (int i=0;i<lc.size();i++)
        {
			Object[] cr=(Object[])lc.get(i);
			Notif notif = new Notif();
            
			notif.setNotif_isi("Pertanyaan anda telah dijawab");
			notif.setNotif_flag("0");
			notif.setNotif_tanggal(java.time.LocalDate.now()+"");
			notif.setUser_id(Long.valueOf(cr[0]+""));
			notifservice.save(notif);
		}


		return "redirect:/admindaftar_chat.html?success";
	}
	
	

}