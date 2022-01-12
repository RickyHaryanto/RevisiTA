package com.example.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.ModelMap;
import com.example.client.service.UserService;
import com.example.client.model.User;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.example.client.model.Dorder;
import com.example.client.model.Horder;
import com.example.client.service.DorderService;
import com.example.client.service.HorderService;
import com.example.client.service.CartService;
import com.example.client.repository.CartRepository;
import com.example.client.repository.HorderRepository;

import org.springframework.security.core.context.SecurityContextHolder;
@Controller
public class HorderController {
    @Autowired
    private HorderService service; 
    @Autowired
    private UserService service2;
    @Autowired
    private CartRepository selectcart;
    @Autowired
    private DorderService dorderservice;
    @Autowired
    private HorderRepository servicetotal;
    @Autowired
    private CartService cartservice;
    
    Horder horder = new Horder();

    Dorder dorder = new Dorder();



    @RequestMapping(value = "/save_checkout", method = RequestMethod.POST)
	public String savecheckout(
	RedirectAttributes redirectAttributes, @RequestParam("horder_atasnama") String horder_atasnama,
    ModelMap modelMap, Model model)  
    {
			
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
        
        horder=new Horder();


        //get grandtotal
        Integer showgrandtotal = servicetotal.findgrandtotal(usr.getUser_id());


        horder.setHorder_atasnama(horder_atasnama);
		horder.setHorder_status("Belum Selesai");
        horder.setHorder_tanggal(java.time.LocalDate.now()+"");
        horder.setUser_id(usr.getUser_id());
        horder.setUser_id(usr.getUser_id());
		horder.setHorder_totalharga(showgrandtotal);
		service.save(horder);
        
        showgrandtotal =0;


        //insert to Dorder
        long horderID=horder.getHorder_id();
        
        List lc = selectcart.findAll2(Long.toString(usr.getUser_id()));
        for (int i=0;i<lc.size();i++)
        {
            Object[] cr=(Object[])lc.get(i);
            
            dorder=new Dorder();
            
            dorder.setHorder_id(horderID);
            dorder.setDorder_jumlah(Long.valueOf(cr[3]+""));
            dorder.setBarang_id(Long.valueOf(cr[1]+""));
            Integer subtotal=Integer.parseInt(cr[4]+"");
            dorder.setDorder_subtotal(subtotal);
            Integer harga=Integer.parseInt(cr[5]+"");
            dorder.setDorder_harga(harga);

            dorderservice.save(dorder);
            subtotal=0;
            harga=0;
            //deletecat
            cartservice.delete(Long.valueOf(cr[0]+""));
          
        }

        //delete cart
        //selectcart.deletecart(usr.getUser_id());
        
        
        
        return "redirect:/list_horder.html?success";
		
    }


    @RequestMapping("/list_horder.html")
	public String viewDaftarHorder(Model model) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User usr=service2.findByEmail(user.getUsername());
        
		List listhorder = servicetotal.findAllMember(usr.getUser_id()+"");
		model.addAttribute("listhorder", listhorder);
		
		return "list_horder";
    }
    

   
}