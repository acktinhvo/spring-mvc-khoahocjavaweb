package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.ICustomerRepository;

public class CustomerRepository extends SimpleJpaRepository<CustomerEntity> implements ICustomerRepository {

	final String DB_URL = "jdbc:mysql://localhost:3306/khoahoclaptrinhjavaweb1";
	final String USER = "root";
	final String PASS = "Abc12345";

	@Override
	public List<CustomerEntity> findAll() {
		List<CustomerEntity> result = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			String sql = "SELECT * FROM Building";
			rs = stmt.executeQuery(sql);
			/*
			 * while (rs.next()) { CustomerEntity customerEntity = new CustomerEntity();
			 * customerEntity.setFullname(rs.getString("fullname"));
			 * customerEntity.setPhone(rs.getString("phone")); result.add(customerEntity); }
			 */
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
