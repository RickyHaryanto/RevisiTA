package com.example.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.client.service.DorderService;

@Controller
public class DorderController {
    @Autowired
	private DorderService service; 
    
    
	@RequestMapping("/list_dorder.html/{horder_id}")
	public String viewDaftarDorder(Model model, @PathVariable(name = "horder_id") int horder_id) {
		List listdorder = service.listAll(horder_id+"");
		
		model.addAttribute("listdorder", listdorder);
		
		return "list_dorder";
	}
}