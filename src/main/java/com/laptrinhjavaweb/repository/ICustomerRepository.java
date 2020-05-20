package com.laptrinhjavaweb.repository;

import java.util.List;

import com.laptrinhjavaweb.entity.CustomerEntity;

public interface ICustomerRepository {
	List<CustomerEntity> findAll();
}
