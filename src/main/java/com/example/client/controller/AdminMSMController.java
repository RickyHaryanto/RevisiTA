package com.example.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.client.model.MSM;
import com.example.client.service.AdminMSMService;

@Controller
public class AdminMSMController {
    @Autowired
	private AdminMSMService service; 
	int currentID = 0;

	@RequestMapping(value = "/adminsave_msm", method = RequestMethod.POST)
	public String saveMSM(RedirectAttributes redirectAttributes, 
	@RequestParam("msm_status") String msm_status, 
    ModelMap modelMap) {
		
		MSM msm = new MSM();

		msm=service.get(currentID);

		msm.setMsm_status(msm_status);
		
		service.save(msm,currentID);
		
		return "redirect:/adminanggota_sm.html";
	}

	@RequestMapping("/adminanggota_sm.html")
	public String viewDaftarMSM(Model model) {
		List listmsm = service.listAll();
		model.addAttribute("listmsm", listmsm);
		currentID=0;
		return "adminanggota_sm";
	}

    
	@RequestMapping("/adminedit_msm.html/{msm_id}")
	public ModelAndView showEditMSMPage(@PathVariable(name = "msm_id") int msm_id) {
		ModelAndView mav = new ModelAndView("adminedit_msm.html");
		MSM msm = service.get(msm_id);
		currentID = msm_id;
		mav.addObject("msm", msm);
		return mav;
    }   
}