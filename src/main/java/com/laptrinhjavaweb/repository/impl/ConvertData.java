package com.laptrinhjavaweb.repository.impl;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Entity;
import com.laptrinhjavaweb.repository.IConvertData;

public class ConvertData<T> implements IConvertData<T> {

	/**
	 * Convert Data from ResultSet to Entity, return list Entity
	 *
	 * @param Class<T>,ResultSet,List<T>
	 * @return List<T>
	 */
	@Override
	public List<T> convertDataToList(ResultSet rs, List<T> result, Class<T> zClass) {
		try {
			if (zClass.isAnnotationPresent(Entity.class)) {
				ResultSetMetaData resultSetMetaData = rs.getMetaData();
				Field[] fields = zClass.getDeclaredFields();
				while (rs.next()) {
					T object = zClass.newInstance();
					for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
						String columnInName = resultSetMetaData.getColumnName(i + 1);
						Object columnValue = rs.getObject(i + 1);
						for (Field field : fields) {
							Column column = field.getAnnotation(Column.class);
							if (column.name().equals(columnInName) && columnValue != null) {
								BeanUtils.setProperty(object, field.getName(), columnValue);
							}
						}
					}
					result.add(object);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	/**
	 * Convert Data from ResultSet to Entity, return Entity
	 * 
	 * @param Class<T>,ResultSet,List<T>
	 * @return T
	 */
	@Override
	public T converDataToEntity(ResultSet rs, T result, Class<T> zClass) {
		try {
			if (zClass.isAnnotationPresent(Entity.class)) {
				ResultSetMetaData resultSetMetaData = rs.getMetaData();
				Field[] fields = zClass.getDeclaredFields();
				while (rs.next()) {
					T object = zClass.newInstance();
					for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
						String columnInName = resultSetMetaData.getColumnName(i + 1);
						Object columnValue = rs.getObject(i + 1);
						for (Field field : fields) {
							Column column = field.getAnnotation(Column.class);
							if (column.name().equals(columnInName) && columnValue != null) {
								BeanUtils.setProperty(object, field.getName(), columnValue);
							}
						}
					}
					result = object;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
}
