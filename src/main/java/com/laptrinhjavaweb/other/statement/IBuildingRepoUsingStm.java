package com.laptrinhjavaweb.other.statement;

import java.util.List;

import com.laptrinhjavaweb.entity.BuildingEntity;

public interface IBuildingRepoUsingStm {
	List<BuildingEntity> findByIdUsingStm(int id);

	List<BuildingEntity> findByIdUsingPrepardStm(int id);
}
