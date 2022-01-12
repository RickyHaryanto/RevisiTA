package com.example.client.controller;

import java.util.Formatter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.client.model.User;
import java.security.MessageDigest;

import com.example.client.model.DonasiUang;
import com.example.client.service.DonasiUangService;

import com.example.client.repository.DonasiUangRepository;
import com.example.client.service.UserService;

@Controller
public class DonasiUangController {
    @Autowired
	private DonasiUangService service; 
    
    @Autowired
	private DonasiUangRepository repo; 
    
    
	@Autowired
    private UserService service2;
    

    

    @RequestMapping("/donasi.html")
	public String showdonasi(Model model) {
		DonasiUang donasiuang = new DonasiUang();
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
        model.addAttribute("test",usr.getUser_id());

		model.addAttribute("donasiuang", donasiuang);
		
		return "donasi";
    }
    

    @RequestMapping(value = "/save_doku", method = RequestMethod.POST)
	public String savedonasiuang(RedirectAttributes redirectAttributes, 
	ModelMap modelMap, 
	@RequestParam("donasiuang_nama") String donasiuang_nama,
    @RequestParam("donasiuang_email") String donasiuang_email,
    @RequestParam("donasiuang_tanggal") String donasiuang_tanggal,
    @RequestParam("donasiuang_nominal") Integer donasiuang_nominal
	) {

		DonasiUang donasiuang = new DonasiUang();
        
        
        //nomor nota
        int min =1;
        int max=9999999;
        Integer random = (int)(Math.random() * (max-min+1)+min);
        String part1 = "INV";
        String donasi_nomornota = part1+random.toString();

		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
		
        donasiuang.setDonasiuang_nama(donasiuang_nama);
        donasiuang.setDonasiuang_email(donasiuang_email);
        donasiuang.setDonasiuang_nominal(donasiuang_nominal);
        donasiuang.setDonasiuang_tanggal(donasiuang_tanggal);
        donasiuang.setDonasiuang_nomornota(donasi_nomornota);
        donasiuang.setUser_id(usr.getUser_id());
        service.save(donasiuang);


		return "redirect:/doku.html";
    }
    




    private static String byteToHex(final byte[] hash)
    {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    @RequestMapping("/doku.html")
	public String doku(Model model) {
		DonasiUang donasiuang = new DonasiUang();
		model.addAttribute("donasiuang", donasiuang);
        

        //get data
        String namadonatur = repo.namadonatur();
        String emaildonatur = repo.emaildonatur();
        String nomornota = repo.nomornotadonatur();
        String tanggaldonatur = repo.tanggaldonatur();
        int jumlah = repo.jumlahdonasi();
        //doku
        String word="";
        String mallID="11327973";
        String sharedKey="u8A6k2E4v9l9";
        String transID= nomornota;
        int amount=jumlah;
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            String pw=amount+".00"+mallID+sharedKey+transID;
            md.reset();
            md.update(pw.getBytes("UTF-8"));
            word = byteToHex(md.digest());
        }
        catch (Exception ex)
        {

        }
        model.addAttribute("MALLID",mallID);
        
        model.addAttribute("TRANSID",transID);
        model.addAttribute("Sharedkey",sharedKey);
        model.addAttribute("kodeA",word);
        model.addAttribute("amount",amount);
        model.addAttribute("nama",namadonatur);
        model.addAttribute("email",emaildonatur);
        model.addAttribute("tanggal","20201211100202");

		return "doku";
    }
    


}