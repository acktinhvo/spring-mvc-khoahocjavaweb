package com.laptrinhjavaweb.other.statement.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.connection.ConnectionDTB;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.other.statement.IBuildingRepoUsingStm;

public class BuidingRepositoryUsingStm implements IBuildingRepoUsingStm {

	private ConnectionDTB connectionDTB = new ConnectionDTB();
	List<BuildingEntity> result = new ArrayList<>();
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	@Override
	public List<BuildingEntity> findByIdUsingStm(int id) {

		try {
			conn = connectionDTB.checkConnection(conn);

			if (conn == null) {
				return result;
			}

			// Statement
			stmt = conn.createStatement();
			String sql = "SELECT * FROM Building Where id = " + id;

			// execute
			rs = stmt.executeQuery(sql);

			// set data into List
			this.fillDataIntoLst(rs);
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	@Override
	public List<BuildingEntity> findByIdUsingPrepardStm(int id) {
		try {
			conn = connectionDTB.checkConnection(conn);

			if (conn == null) {
				return result;
			}

			// PrepardStatement
			PreparedStatement findByid = conn.prepareStatement("SELECT * FROM Building Where id = ?");
			findByid.setInt(1, id);

			// execute
			rs = findByid.executeQuery();

			// set data into List
			this.fillDataIntoLst(rs);
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	public void fillDataIntoLst(ResultSet rs) {
		try {
			while (rs.next()) {
				BuildingEntity buildingEntity = new BuildingEntity();
				buildingEntity.setName(rs.getString("name"));
				buildingEntity.setWard(rs.getString("ward"));
				result.add(buildingEntity);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
