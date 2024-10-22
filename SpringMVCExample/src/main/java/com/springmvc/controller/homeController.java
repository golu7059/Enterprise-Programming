package com.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springmvc.dao.customerDAO;
import com.springmvc.model.Customer;

import jakarta.transaction.Transactional;

@Controller
public class homeController {

	@Autowired
	customerDAO dao;
	
	@GetMapping("/")
	public String landing(Model model) {
		model.addAttribute("allcustomers", dao.getCustomers());
		System.out.println("This is / request mapping");
		return "index";
	}
	
	@GetMapping("getall")
	public String getall(Model model) {
		model.addAttribute("allcustomers", dao.getCustomers());
		return "index";
	}	

	@GetMapping("addcustomer")
	public String getcustomer() {
		return "newcustomer";
	}
	@RequestMapping("saveCustomer")
	public String addCustomer(@ModelAttribute("customer") Customer customer, Model model) {
		dao.saveCustomer(customer);
		model.addAttribute("allcustomers", dao.getCustomers());
		return "index";
	}
	
	@RequestMapping("delCustomer")
	public String delCustomer(@RequestParam("id") int id, Model model) {
		dao.delCustomer(id);
		model.addAttribute("allcustomers", dao.getCustomers());
		return "index";
	}

	@RequestMapping("editCustomer")
	public String editCustomer(@RequestParam("id") int id, Model model) {
		Customer customer = dao.getCustomerByID(id);
		model.addAttribute("customer", customer);
		return "newcustomer";
	}
}
