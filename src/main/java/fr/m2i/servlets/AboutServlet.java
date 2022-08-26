package fr.m2i.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/a-propos")
public class AboutServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String PAGE = "/WEB-INF/pages/about.jsp";	
       
    public AboutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
