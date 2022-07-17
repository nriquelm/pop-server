package com.skillstorm.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import java.sql.Statement;
import com.skillstorm.conf.PopWarehouseCreds;
import com.skillstorm.models.Pop;

public class PopDaoImpl implements PopDao{

	@Override
	public List<Pop> findAll() {
		String sql = "SELECT * FROM pop";
		
		try (Connection conn = PopWarehouseCreds.getInstance().getConnection()) {
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			LinkedList<Pop> pops = new LinkedList<>();
			
			while(rs.next()) {
				Pop pop = new Pop(rs.getInt("PopId"), rs.getString("Name"), rs.getInt("Quantity"), rs.getDouble("Cost"), rs.getString("Series"), rs.getString("Status"));
				pops.add(pop);
			}
			return pops;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public Pop findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pop findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pop save(Pop pop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePrice(Pop pop) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Pop pop) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMany(int[] ids) {
		// TODO Auto-generated method stub
		
	}

}
