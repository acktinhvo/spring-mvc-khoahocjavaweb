package com.laptrinhjavaweb.repository;

import java.util.List;

public interface IJpaRepository<T> {
	List<T> findAll();

	T findById(int id);
}
