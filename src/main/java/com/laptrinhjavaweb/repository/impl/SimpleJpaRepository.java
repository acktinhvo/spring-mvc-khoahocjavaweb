package com.laptrinhjavaweb.repository.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Entity;
import com.laptrinhjavaweb.annotation.Table;
import com.laptrinhjavaweb.repository.IJpaRepository;

public class SimpleJpaRepository<T> implements IJpaRepository<T> {

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		List<T> result = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Class<T> zClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/khoahoclaptrinhjavaweb1", "root",
					"Abc12345");
			stmt = conn.createStatement();
			String tableName = "";
			if (zClass.isAnnotationPresent(Table.class)) {
				Table table = zClass.getAnnotation(Table.class);
				tableName = table.name();
			}
			String sql = "SELECT * FROM" + tableName;
			rs = stmt.executeQuery(sql);
			/*
			 * while (rs.next()) { CustomerEntity customerEntity = new CustomerEntity();
			 * customerEntity.setFullname(rs.getString("fullname"));
			 * customerEntity.setPhone(rs.getString("phone")); result.add(customerEntity); }
			 */
			if (zClass.isAnnotationPresent(Entity.class)) {
				ResultSetMetaData resultSetMetaData = rs.getMetaData();
				Field[] fields = zClass.getDeclaredFields();
				while (rs.next()) {
					T object = zClass.newInstance();
					for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
						String columnInName = resultSetMetaData.getColumnClassName(i + 1);
						Object columnValue = rs.getObject(i + 1);
						for (java.lang.reflect.Field field : fields) {
							Column column = field.getAnnotation(Column.class);
							if (column.name().equals(columnInName) && columnValue != null) {
								BeanUtils.setProperty(object, field.getName(), columnValue);
							}
						}
					}
					result.add(object);
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (conn != null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return result;
	}
}
