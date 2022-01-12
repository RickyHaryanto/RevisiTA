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

import com.example.client.model.Event;
import com.example.client.service.AdminEventService;

@Controller
public class AdminEventController {
    @Autowired
    private AdminEventService service; 
	int currentID = 0;
	


    @RequestMapping(value = "/adminsave_event", method = RequestMethod.POST)
	public String saveEvent(RedirectAttributes redirectAttributes, 
	@RequestParam("event_foto") MultipartFile event_foto, ModelMap modelMap, 
	@RequestParam("event_judul") String event_judul, 
	@RequestParam("event_keterangan") String event_keterangan,
	@RequestParam("event_tanggal") String event_tanggal,
	@RequestParam("event_waktu") String event_waktu) {

		Event event = new Event();

		try {
			String tempbase = Base64.getEncoder().encodeToString(event_foto.getBytes());
			Clob myclob = new javax.sql.rowset.serial.SerialClob(tempbase.toCharArray());
			event.setEvent_foto(myclob);
	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("www");
		}
		
		event.setEvent_judul(event_judul);
		event.setEvent_keterangan(event_keterangan);
		event.setEvent_tanggal(event_tanggal);
		event.setEvent_waktu(event_waktu);
		
	
		
		service.save(event,currentID);
		
		return "redirect:/adminlist_event.html?success";
	}

	@RequestMapping("/adminlist_event.html")
	public String viewDaftarEvent(Model model) {
		List listevent = service.listAll();
		model.addAttribute("listevent", listevent);
		
		return "adminlist_event";
	}

    @RequestMapping("/adminadd_event.html")
	public String showNewEventPage(Model model) {
		Event event = new Event();
		model.addAttribute("event", event);
		currentID=0;
		return "adminadd_event.html";
    }
    
	@RequestMapping("/adminedit_event.html/{event_id}")
	public ModelAndView showEditEventPage(@PathVariable(name = "event_id") int event_id) {
		ModelAndView mav = new ModelAndView("adminedit_event.html");
		Event event = service.get(event_id);
		currentID = event_id;
		mav.addObject("event", event);
		return mav;
	}


	@RequestMapping("/admingooglecalendar.html")
	public String viewgc(Model model) {
		
		
		return "admingooglecalendar";
	}

	@RequestMapping("/delete_event.html/{event_id}")
	public String deleteProduct(@PathVariable(name = "event_id") int event_id) {
		service.delete(event_id);
		return "redirect:/adminlist_event.html";		
	}

}