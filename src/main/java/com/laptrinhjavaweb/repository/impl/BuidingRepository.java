package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.IBuidingRepository;

public class BuidingRepository implements IBuidingRepository {

	final String DB_URL = "jdbc:mysql://localhost:3306/khoahoclaptrinhjavaweb1";
	final String USER = "root";
	final String PASS = "Abc12345";

	@Override
	public List<BuildingEntity> findAll() {
		List<BuildingEntity> result = new ArrayList<>();
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
			while (rs.next()) {
				BuildingEntity buidingEntity = new BuildingEntity();
				buidingEntity.setName(rs.getString("name"));
				buidingEntity.setWard(rs.getString("ward"));
				result.add(buidingEntity);
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
