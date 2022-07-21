package com.skillstorm.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.daos.PopDao;
import com.skillstorm.daos.PopDaoImpl;
import com.skillstorm.models.Pop;


@WebServlet(name = "PopServlet", urlPatterns = "/pops/*")
public class PopServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		System.out.println("PopServlet Created");
		super.init();
	}

	@Override
	public void destroy() {
		System.out.println("PopServlet Destroyed");
		super.destroy();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Servicing");
		super.service(req, resp);
	}

	private static final long serialVersionUID = 6545584803189140545L;
	PopDao dao = new PopDaoImpl();
	ObjectMapper mapper = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String path = req.getPathInfo();
			String[] splitString = path.split("/");
			String input = splitString[1];

			int id = Integer.parseInt(input);
			Pop pop = dao.findById(id);

			if (pop != null) {
				resp.setContentType("application/json");
				resp.getWriter().print(mapper.writeValueAsString(pop));
			} else {
				resp.setContentType("application/json");
				resp.getWriter().print(mapper.writeValueAsString("No pop found at this ID"));
			}

		} catch (NumberFormatException e) {
			String path = req.getPathInfo();
			String[] splitString = path.split("/");
			String input = splitString[1];

			Pop pop = dao.findByName(input);
			resp.setContentType("application/json");
			resp.getWriter().print(mapper.writeValueAsString(pop));

		} catch (ArrayIndexOutOfBoundsException e) {

			List<Pop> pops = dao.findAll();
			resp.setContentType("application/json");
			resp.getWriter().print(mapper.writeValueAsString(pops));

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		InputStream reqBody = req.getInputStream();
		Pop newPop = mapper.readValue(reqBody, Pop.class);
		dao.save(newPop);
		System.out.println("Pop has been added");

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			InputStream reqBody = req.getInputStream();
			Pop pop = mapper.readValue(reqBody, Pop.class);
			dao.updatePrice(pop.getCost(), pop.getId());
			resp.setContentType("application/json");
			resp.getWriter().print(mapper.writeValueAsString("Pop has been updated"));
			resp.setStatus(201);
			
		} catch (Exception e) {
			
			resp.getWriter().print(mapper.writeValueAsString("Pop could not be updated"));
			
		}

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getPathInfo();
		String[] splitString = path.split("/");
		String input = splitString[1];
		
		try {
			int id = Integer.parseInt(input);
			dao.delete(id);
			
		} catch (NumberFormatException e) {
			
			// resp.getWriter().print(mapper.writeValueAsString("Pop Not Found"));
			dao.deleteByName(input);
			
		}
	}

}
