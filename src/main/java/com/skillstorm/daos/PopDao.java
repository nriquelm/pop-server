package com.skillstorm.daos;

import java.util.List;

import com.skillstorm.models.Pop;

public interface PopDao {
	
	public List<Pop> findAll();
	public Pop findById(int id);
	public Pop findByName(String name);
	
	public Pop save(Pop pop);
	public void updatePrice(Pop pop);
	public void delete(Pop pop);
	public void delete(int id);
	public void deleteMany(int[] ids);

}
