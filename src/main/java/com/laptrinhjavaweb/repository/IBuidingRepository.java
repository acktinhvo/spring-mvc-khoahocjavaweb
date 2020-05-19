package com.laptrinhjavaweb.repository;

import java.util.List;

import com.laptrinhjavaweb.entity.BuildingEntity;

public interface IBuidingRepository {
	List<BuildingEntity> findAll();
}
