package com.purplemagic.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
	     binder.registerCustomEditor(Date.class,     
	                         new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), true, 10));   
	}

	@RequestMapping(value = "/")
	public String showHome(Model model) {
		List<Customer> customers = customerService.findAll();
		model.addAttribute("customers", customers);
		return "home";
	}

	// Delete Customer [.../remove?id=7]
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public @ResponseBody Long removeCustomer(@RequestParam("id") String stringId) {

		if (stringId != null) {
			Long id = Long.parseLong(stringId);
			customerService.delete(id);
			return id;
		} else {
			System.out.println("Javila se greska prilikom brisanja kontakta iz baze");
			return new Long(777);
		}
	}

/*	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public @ResponseBody Customer addCustomer(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("email") String email, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("dob") String dob) {
		
		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmail(email);
		customer.setPhoneNumber(phoneNumber);
		customer.setDob(null);

		
		Customer rCustomer = customerService.add(customer);
		return rCustomer;
	}*/
	
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public @ResponseBody Customer addCustomer(@ModelAttribute("customer") Customer customer, BindingResult result) {
		
		if(result.hasErrors()) {
			System.out.println("BINDING RESULT ERROR!!");
		}
		
		Customer rCustomer = customerService.add(customer);
		return rCustomer;
	}


}
