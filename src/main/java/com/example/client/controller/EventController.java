package com.example.client.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.client.model.Event;
import com.example.client.service.EventService;
import com.example.client.repository.EventRepository;

@Controller
public class EventController {
    @Autowired
	private EventService service; 
	
	@Autowired
    private EventRepository repo; 
	
	public String searchtemp;

    @RequestMapping(value = "/save_event", method = RequestMethod.POST)
	public String saveEvent(@ModelAttribute("event") Event event) {
		service.save(event);
		
		return "redirect:/list_event.html";
	}

	@RequestMapping(value = "/search_event", method = RequestMethod.POST)
	public String searchEvent(RedirectAttributes redirectAttributes, 
	Model model, 
	@RequestParam("event_judul") String event_judul
	) {
		
		searchtemp = event_judul;
		System.out.println(event_judul);
		
		return "redirect:/search_event.html";
	}

	@RequestMapping("/list_event.html")
	public String viewDaftarEvent(Model model) {
		Event event = new Event();
		model.addAttribute("event", event);
		searchtemp = "";
		List listevent = repo.findAll();
		model.addAttribute("listevent", listevent);
		
		return "list_event";
	}

	public Integer idsementara = 12;
	@RequestMapping(value="/requestdataevent", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> requestdataevent(@RequestParam String id) {
		//return id+"";
	 	Integer temp = idsementara;
		
		List listevent = repo.laodmore(temp);
		idsementara=idsementara+4;
		System.out.println(idsementara);
		
		

		Map hm=new HashMap();
		hm.put("event",listevent);
		hm.put("id",id);

		
		
	
		return ResponseEntity.status(HttpStatus.CREATED).body(
            hm);
	}

	@RequestMapping("/search_event.html")
	public String searchevent(Model model) {
		Event event = new Event();
		model.addAttribute("event", event);

		List listeventsearch = repo.searchevent(searchtemp);
		model.addAttribute("listeventsearch", listeventsearch);
		
		searchtemp = "";
		return "search_event";
	}

	

	@RequestMapping("/list_event_user.html")
	public String viewDaftarEventUser(Model model) {
		List listevent = repo.findAll();
		model.addAttribute("listevent", listevent);
		
		return "list_event_user";
	}

    @RequestMapping("/add_event.html")
	public String showNewEventPage(Model model) {
		Event event = new Event();
		model.addAttribute("event", event);
		
		return "add_event";
    }
    
	@RequestMapping("/edit_event.html/{event_id}")
	public ModelAndView showEditEventPage(@PathVariable(name = "event_id") int event_id) {
		ModelAndView mav = new ModelAndView("edit_event.html");
		Event event = service.get(event_id);
		mav.addObject("event", event);
		return mav;
	}



	@RequestMapping("/googlecalendar.html")
	public String viewgc(Model model) {
		
		
		return "googlecalendar";
	}

	@RequestMapping("/googlecalendar_user.html")
	public String viewgcuser(Model model) {
		
		
		return "googlecalendar";
	}

	@RequestMapping("/detailevent.html/{event_id}")
	public String viewDetail(Model model,@PathVariable(name = "event_id") int event_id) {
		List listeventdetail = repo.detailevent(event_id);
		model.addAttribute("listeventdetail", listeventdetail);
		
		return "detailevent";
	}
}