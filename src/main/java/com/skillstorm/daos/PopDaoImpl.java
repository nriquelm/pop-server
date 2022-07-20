package com.skillstorm.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import java.sql.Statement;
import com.skillstorm.conf.PopWarehouseCreds;
import com.skillstorm.models.Pop;

public class PopDaoImpl implements PopDao{
	
	public static PopWarehouseCreds creds = PopWarehouseCreds.getInstance();

	@Override
	public List<Pop> findAll() {
		
		String sql = "SELECT * FROM pop";
		
		try (Connection conn = creds.getConnection()) {
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			LinkedList<Pop> pops = new LinkedList<>();
			
			while(rs.next()) {
				Pop pop = mapRowToPop(rs);
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
		
		String sql = "SELECT * FROM pop WHERE pop_id = ?";
		
		try(Connection conn = creds.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return new Pop(rs.getInt("pop_id"), rs.getString("pop_name"), rs.getInt("quantity"), rs.getDouble("cost"), rs.getString("pop_series"), rs.getString("pop_status"));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return null;
	}

	@Override
	public Pop findByName(String name) {
		
		String sql = "SELECT * FROM pop WHERE pop_name = ?";
		
		try(Connection conn = creds.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return new Pop(rs.getInt("pop_id"), rs.getString("pop_name"), rs.getInt("quantity"), rs.getDouble("cost"), rs.getString("pop_series"), rs.getString("pop_status"));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return null;
	}

	@Override
	public Pop save(Pop pop) {
		
		String sql = "INSERT INTO pop (pop_id, pop_name, quantity, cost, pop_series, pop_status) VALUES (?, ?, ?, ?, ?, ?)";
		
		try(Connection conn = creds.getConnection()){
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, pop.getId());
			ps.setString(2, pop.getName());
			ps.setInt(3, pop.getQuantity());
			ps.setDouble(4, pop.getCost());
			ps.setString(5, pop.getSeries());
			ps.setString(6, pop.getStatus());
			
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected != 0) {
				conn.commit();
				return pop;
			}else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updatePrice(double cost, int id) {
		
		String sql = "UPDATE pop SET cost = ? WHERE pop_id = ?";
		
		try (Connection conn = creds.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, cost);
			ps.setInt(2, id);
			int numRowsUpdated = ps.executeUpdate();
			if(numRowsUpdated == 1) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		}catch (SQLException e){
			
			e.printStackTrace();
			
		}
		
		
	}

	@Override
	public void deleteByName(String name){
		
		String sql = "DELETE FROM pop WHERE pop_name = ?";
		
		try (Connection conn = creds.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			int numRowsDeleted = ps.executeUpdate();
			System.out.println(numRowsDeleted);
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
	}

	@Override
	public void delete(int id) {
		
		String sql = "DELETE FROM pop WHERE pop_id = ?";
		
		try (Connection conn = creds.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int numRowsDeleted = ps.executeUpdate();
			System.out.println(numRowsDeleted);
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}

	
	private static Pop mapRowToPop(ResultSet rs) {
		
		Pop pop = new Pop();
		
		try {
			
			pop.setId(rs.getInt("pop_id"));
			pop.setName(rs.getString("pop_name"));
			pop.setQuantity(rs.getInt("quantity"));
			pop.setCost(rs.getDouble("cost"));
			pop.setSeries(rs.getString("pop_series"));
			pop.setStatus(rs.getString("pop_status"));
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return pop;
		
	}

	

}
