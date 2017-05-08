package com.purplemagic.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public @ResponseBody Customer addCustomer(@ModelAttribute("customer") Customer customer, BindingResult result) {
		
		if(result.hasErrors()) {
			System.out.println("BINDING RESULT ERROR!!");
		}
		
		Customer rCustomer = customerService.add(customer);
		return rCustomer;
	}
	
	@RequestMapping (value="/search/{term}", method=RequestMethod.GET)
	public ModelAndView searchCustomers(@PathVariable("term") String term, ModelMap model) {
		List<Customer> customers = customerService.findCustomersByName(term);
		model.put("customers", customers);		
		return new ModelAndView("search", model);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public @ResponseBody Customer updateCustomer(@RequestParam("id") Long id, @ModelAttribute("customer") Customer customer) {
		
		Customer rCustomer = customerService.update(customer);
		
		return rCustomer;
	}


}
