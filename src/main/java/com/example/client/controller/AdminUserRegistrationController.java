package com.example.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.example.client.model.User;
import com.example.client.repository.UserRepository;
import com.example.client.service.AdminUserService;
import com.example.client.service.AdminUserServiceImpl;
import com.example.client.dto.UserRegistrationDto;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;


@Controller

public class AdminUserRegistrationController {

  
    @Autowired
    private AdminUserService userService;
    @Autowired
    private UserRepository repoemail;
    @Autowired
    private AdminUserServiceImpl userServiceimpl;

    private UserRegistrationDto userDto = new UserRegistrationDto();

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @RequestMapping(value = "/adminregistration.html") 
    public String showRegistrationForm(Model model) {
        return "adminregistration";
    }

    
    @RequestMapping(value = "/adminregistrationUpload", method = RequestMethod.POST) 
    public String registerUserAccount(RedirectAttributes redirectAttributes, 
    @RequestParam("nama") String nama,@RequestParam("bod") String bod, @RequestParam("alamat") String alamat, @RequestParam("email") String email,
    @RequestParam("qiudao") String qiudao, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword,
    @RequestParam("jabatan") String jabatan, @RequestParam("kota") String kota, ModelMap modelMap
    ){
        if(password.equals(confirmPassword)){

        }else{
            return "redirect:/adminregistration.html?errorpass";
        }

        String emailcek = repoemail.cekemail(email);
        if(emailcek == null){

        }else{
            return "redirect:/adminregistration.html?erroremail";
        }

        modelMap.addAttribute("nama", nama);
        modelMap.addAttribute("bod", bod);
        modelMap.addAttribute("alamat", alamat);
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("qiudao", qiudao);
        modelMap.addAttribute("password", password);
        modelMap.addAttribute("confirmPassword", confirmPassword);
        modelMap.addAttribute("jabatan", jabatan);
        modelMap.addAttribute("kota", kota);
       

        User existing = userService.findByEmail(email);
        if (existing != null){
            // result.rejectValue("email", null, "There is already an account registered with that email");
            return "/adminregistration";
        }

        userDto.setNama(nama);
        userDto.setAlamat(alamat);
        userDto.setBod(bod);
        userDto.setEmail(email);
        userDto.setQiudao(qiudao);
        userDto.setPassword(password);
        userDto.setConfirmPassword(confirmPassword);
        userDto.setJabatan(jabatan);
        userDto.setKota(kota);
        userService.save(userDto);
        return "redirect:/adminregistration.html?success";
    }


    @RequestMapping("/adminumat.html")
    public String viewUmatPage(Model model) {
        List listumat = userServiceimpl.showAll();
        model.addAttribute("listumat", listumat);
        
        return "adminumat";
    }

    @RequestMapping("/adminedit_umat.html/{user_id}")
	public ModelAndView showEditUmatPage(@PathVariable(name = "user_id") int user_id) {
		ModelAndView mav = new ModelAndView("adminedit_umat.html");
        User user = userServiceimpl.get(user_id);
        currentID = user_id;
		mav.addObject("user", user);
		return mav;
    }
    
    int currentID = 0;
    public User user = new User();
    @RequestMapping(value = "/adminregistrationUpload2", method = RequestMethod.POST) 
    public String registerUserAccount2(RedirectAttributes redirectAttributes, 
    @RequestParam("status") String status,
    ModelMap modelMap
    ){

        modelMap.addAttribute("status", status);
        
        user=userServiceimpl.get(currentID);
        user.setStatus(status);
        
        
        userServiceimpl.save2(user,currentID);
        return "redirect:/adminumat.html";
    }

}
