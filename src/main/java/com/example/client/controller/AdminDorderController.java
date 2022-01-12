package com.example.client.controller;

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


import com.example.client.model.Dorder;
import com.example.client.model.Horder;
import com.example.client.service.AdminDorderService;
import com.example.client.service.AdminHorderService;
import com.example.client.repository.AdminDorderRepository;

@Controller
public class AdminDorderController {
    @Autowired
	private AdminDorderService service; 
	@Autowired
	private AdminHorderService serviceh; 
	@Autowired
	private AdminDorderRepository repo; 
	

	@RequestMapping("/adminlist_dorder.html/{horder_id}")
	public String viewDaftarDorder(Model model, @PathVariable(name = "horder_id") int horder_id) {
		List listdorder = service.listAll(horder_id+"");
		
		model.addAttribute("listdorder", listdorder);
		
		return "adminlist_dorder";
	}



	@RequestMapping("/adminedit_dorder.html/{dorder_id}")
	public ModelAndView editdorder(@PathVariable(name = "dorder_id") int dorder_id) {
		ModelAndView mav = new ModelAndView("adminedit_dorder.html");
		Dorder dorder = service.get(dorder_id);
		currentID = dorder_id;
		mav.addObject("dorder", dorder);
		
		return mav;
	}



	int currentID = 0;
	
	@RequestMapping(value = "/adminsave_dorder/{dorder_id}", method = RequestMethod.POST) 
	public String savedorder(@PathVariable(name = "dorder_id") Long dorder_id,
	RedirectAttributes redirectAttributes, 
	@RequestParam("dorder_jumlah") Long dorder_jumlah,
	@RequestParam("horder_id") Long horder_id,
	
    ModelMap modelMap
    ){
		Dorder dorder = new Dorder();
		Horder horder = new Horder();
		
        modelMap.addAttribute("dorder_jumlah", dorder_jumlah);

		horder = serviceh.get(horder_id);
		dorder=service.get(currentID);
		
		//getHarga
		Integer harga = repo.findharga(dorder_id);
		//getjumlah
		Integer jumlah = dorder_jumlah.intValue();
		//subtotal
		Integer newsubtotal = harga*jumlah;
		

		dorder.setDorder_jumlah(dorder_jumlah);
		dorder.setDorder_subtotal(newsubtotal);
        
		service.save2(dorder,currentID);
		newsubtotal=0;
		//get grandtotal
		Integer showgrandtotal = repo.findgrandtotal(horder_id);
		horder.setHorder_totalharga(showgrandtotal);
		serviceh.save(horder);

		showgrandtotal =0;
        return "redirect:/adminlist_horder.html";
    }


}