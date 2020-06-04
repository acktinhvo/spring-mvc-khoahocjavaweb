package com.laptrinhjavaweb.repository.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.annotation.Table;
import com.laptrinhjavaweb.connection.ConnectionDTB;
import com.laptrinhjavaweb.repository.IJpaRepository;

public class SimpleJpaRepository<T> implements IJpaRepository<T> {

	private ConnectionDTB connectionDTB = new ConnectionDTB();

	private ConvertData<T> converData = new ConvertData<T>();

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		List<T> result = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Class<T> zClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		try {
			conn = connectionDTB.checkConnection(conn);

			if (conn == null) {
				return result;
			}

			stmt = conn.createStatement();
			String tableName = "";
			if (zClass.isAnnotationPresent(Table.class)) {
				Table table = zClass.getAnnotation(Table.class);
				tableName = table.name();
			}
			String sql = "SELECT * FROM " + tableName;
			rs = stmt.executeQuery(sql);
			result = converData.convertDataToList(rs, result, zClass);
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

	@Override
	public T findById(int id) {
		T result = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Class<T> zClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		try {
			result = zClass.newInstance();
			conn = connectionDTB.checkConnection(conn);

			if (conn == null) {
				return result;
			}
			stmt = conn.createStatement();
			String tableName = "";
			if (zClass.isAnnotationPresent(Table.class)) {
				Table table = zClass.getAnnotation(Table.class);
				tableName = table.name();
			}
			String sql = "SELECT * FROM " + tableName + " WHERE id = " + id;
			rs = stmt.executeQuery(sql);
			result = converData.converDataToEntity(rs, result, zClass);
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
