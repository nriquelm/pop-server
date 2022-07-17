package com.skillstorm.servlets;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.models.Pop;

@WebServlet(urlPatterns = "/pop")
public class PopServlet extends HttpServlet {

	private static final long serialVersionUID = 6545584803189140545L;
	
	private CopyOnWriteArrayList<Pop> popList = new CopyOnWriteArrayList<>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(popList);
		resp.getWriter().print(json);
		resp.setContentType("");
		resp.setStatus(201);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Pop pop = mapper.readValue(req.getInputStream(), Pop.class);
		popList.add(pop);
		resp.setStatus(201);
		System.out.println("CREATED POP!");
		 
	}

}
