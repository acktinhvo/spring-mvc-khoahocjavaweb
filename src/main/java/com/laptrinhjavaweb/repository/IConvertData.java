package com.laptrinhjavaweb.repository;

import java.sql.ResultSet;
import java.util.List;

public interface IConvertData<T> {

	List<T> convertDataToList(ResultSet rs, List<T> result, Class<T> zClass);

	T converDataToEntity(ResultSet rs, T result, Class<T> zClass);
}
