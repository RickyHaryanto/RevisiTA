package com.example.client.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import com.example.client.repository.AdminNotifRepository;

import com.example.client.model.User;
import com.example.client.service.AdminUserService;

@Controller
public class AdminMainController {
    @Autowired
	private AdminUserService service2;
    @Autowired
    private AdminNotifRepository reponotif;
    

    @GetMapping("/adminindex.html")
    public String index(Model model) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
		model.addAttribute("haloindex",usr.getNama());
        return "adminindex";
    }


    @GetMapping("/adminindex_market.html")
    public String indexMarket(Model model) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
		model.addAttribute("haloindexmarket",usr.getNama());
        return "adminindex_market";
    }

    @GetMapping("/adminindex_sm.html")
    public String indexSm(Model model) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
		model.addAttribute("haloindexsm",usr.getNama());
        return "adminindex_sm";
    }

    @GetMapping("/adminindex_event.html")
    public String indexEvent(Model model) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
		model.addAttribute("haloindexevent",usr.getNama());
        return "adminindex_event";
    }

    /*@GetMapping("/admin/login")
    public String login(Model model) {
        return "/admin/login";
    }*/

    /*@GetMapping("/logout.html")
    public String logout(Model model,HttpSession session) {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "login";
    }*/


    @GetMapping("/serviceadminumum")
    @ResponseBody
    public java.util.List serviceadminumum() {
        java.util.List lc = reponotif.selectindex();
        return lc;
    }

    @GetMapping("/serviceadminsm")
    @ResponseBody
    public java.util.List serviceadminsm() {
        java.util.List lc = reponotif.selectindexsm();
        return lc;
    }


    @GetMapping("/serviceadminevent")
    @ResponseBody
    public java.util.List serviceadminevent() {
        java.util.List lc = reponotif.selectindexevent();
        return lc;
    }


    @GetMapping("/serviceadminmarket")
    @ResponseBody
    public java.util.List serviceadminumummarket() {
        java.util.List lc = reponotif.selectindexmarket();
        return lc;
    }

    @GetMapping("/adminlaporan.html")
    public String laporan(Model model) {
        return "adminlaporan";
    }

}
