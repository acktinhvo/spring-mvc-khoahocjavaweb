package com.laptrinhjavaweb.repository;

import java.util.List;

public interface IJpaRepository<T> {
	List<T> findAll();

	List<T> findById(int id);
}
