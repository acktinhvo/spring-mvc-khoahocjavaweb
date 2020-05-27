package com.laptrinhjavaweb.controller;

import java.util.List;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.impl.CustomerService;

public class CustomerController {
	public static void main(String[] args) {
		ICustomerService customerService = new CustomerService();
		List<CustomerDTO> result = customerService.findAll();
		for (CustomerDTO item : result) {
			System.out.println(item.getFullname());
			System.out.println(item.getPhone());
		}
	}
}
