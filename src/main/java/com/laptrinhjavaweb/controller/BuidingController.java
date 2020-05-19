package com.laptrinhjavaweb.controller;

import java.util.List;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.impl.BuildingService;

public class BuidingController {

	public static void main(String[] args) {
		IBuildingService buildingService = new BuildingService();
		List<BuildingDTO> result = buildingService.findAll();
		for (BuildingDTO item : result) {
			System.out.println(item.getName());
			System.out.println(item.getWard());
		}
	}
}
