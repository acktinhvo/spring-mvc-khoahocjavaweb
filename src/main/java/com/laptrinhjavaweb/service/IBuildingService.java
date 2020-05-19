package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.dto.BuildingDTO;

public interface IBuildingService {
	List<BuildingDTO> findAll();
}
