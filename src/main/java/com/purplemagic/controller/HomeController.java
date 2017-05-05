package com.purplemagic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.purplemagic.model.Customer;
import com.purplemagic.service.CustomerService;

@Controller
public class HomeController {

	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "/")
	public String showHome(Model model) {
		List<Customer> customers = customerService.findAll();
		model.addAttribute("customers", customers);
		return "home";
	}
	
	@RequestMapping(value="/customer", method=RequestMethod.POST)
	public @ResponseBody Long removeCustomer(@RequestParam(name="del", required=false) String del, @RequestParam("id") String stringId) {
		
		Long id = Long.parseLong(stringId);
		
		if(del!=null && del.equals("true")) {
			customerService.delete(id);
			return id;
		} else {
			System.out.println("Javila se greska prilikom brisanja kontakta iz baze");
			return new Long(777);
		}
		
	
	}

}
