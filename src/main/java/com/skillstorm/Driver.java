package com.skillstorm;

import java.util.List;

import com.skillstorm.daos.PopDao;
import com.skillstorm.daos.PopDaoImpl;
import com.skillstorm.models.Pop;

public class Driver {

	public static void main(String[] args) {
		
		PopDao dao = new PopDaoImpl();
		List<Pop> pops = dao.findAll();
		System.out.println(pops);
		
		System.out.println(dao.findById(1));
		System.out.println(dao.findById(45));
		
		System.out.println(dao.findByName("'' OR '' = ''; --"));

	}

}
