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

import com.example.client.model.Barang;
import com.example.client.service.AdminBarangService;

@Controller
public class AdminBarangController {
	int currentID = 0;
	@Autowired
	private AdminBarangService service; 
	

	
	 
	@RequestMapping(value = "/adminsave_barang", method = RequestMethod.POST)
	public String saveBarang(RedirectAttributes redirectAttributes, 
	@RequestParam("barang_foto") MultipartFile barang_foto, ModelMap modelMap, 
	@RequestParam("barang_nama") String barang_nama, 
	@RequestParam("barang_keterangan") String barang_keterangan, 
	@RequestParam("barang_harga") Integer barang_harga) {

		Barang barang = new Barang();

		modelMap.addAttribute("barang_foto", barang_foto);
		modelMap.addAttribute("barang_nama", barang_nama);
		modelMap.addAttribute("barang_keterangan", barang_keterangan);
		modelMap.addAttribute("barang_harga", barang_harga);
		
		try{
			Clob myclob2 = new javax.sql.rowset.serial.SerialClob(barang_keterangan.toCharArray());
			barang.setBarang_keterangan(myclob2);
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
			
		barang.setBarang_nama(barang_nama);
		barang.setBarang_harga(barang_harga);

		try {
			String tempbase = Base64.getEncoder().encodeToString(barang_foto.getBytes());
			Clob myclob = new javax.sql.rowset.serial.SerialClob(tempbase.toCharArray());
			barang.setBarang_foto(myclob);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		service.save(barang,currentID);
		return "redirect:/adminlistbarang.html?success";
	}

	@RequestMapping("/adminlistbarang.html")
	public String viewDaftarBarang(Model model) {
		List listbarang = service.listAll();
		model.addAttribute("listbarang", listbarang);
		
		return "adminlistbarang";
	}

    @RequestMapping("/adminadd_product_market.html")
	public String showNewBarangPage(Model model) {
		Barang barang = new Barang();
		model.addAttribute("barang", barang);
		currentID=0;
		return "adminadd_product_market";
    }
	
	

	@RequestMapping("/adminedit_barang.html/{barang_id}")
	public ModelAndView showEditBarangPage(@PathVariable(name = "barang_id") int barang_id) {
		ModelAndView mav = new ModelAndView("adminedit_barang.html");
		Barang barang = service.get(barang_id);
		currentID = barang_id;
		mav.addObject("barang", barang);
		return mav;
	}

	
	@RequestMapping("/delete_barang.html/{barang_id}")
	public String deleteProduct(@PathVariable(name = "barang_id") int barang_id) {
		service.delete(barang_id);
		return "redirect:/adminlistbarang.html";		
	}
}