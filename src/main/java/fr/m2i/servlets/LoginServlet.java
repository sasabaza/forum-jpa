package fr.m2i.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2i.models.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String PAGE = "/WEB-INF/pages/login.jsp";	
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String isLogoff = request.getParameter("logoff");
		if(isLogoff != null && !isLogoff.isEmpty() && isLogoff.equals("true")) {
			
			String userRole = (String) request.getSession().getAttribute("userRole");
			
			if (userRole.equals("administrateur")) {
				request.removeAttribute("listusers");
			}
			
			request.getSession().removeAttribute("userId");
			request.getSession().removeAttribute("userRole");
			request.getSession().setAttribute("access", false);
		}
		
		if ((request.getSession().getAttribute("access") != null && (boolean) request.getSession().getAttribute("access") == true)) {
			
			response.sendRedirect(request.getContextPath() + "/account");
		} else {
			this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
		}
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (username != null && password != null) {
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
			EntityManager em = factory.createEntityManager();
			
			List<User> listUsers = new ArrayList<>();
			
			boolean success;
			
			try {
				EntityTransaction entityTransaction = em.getTransaction();
				entityTransaction.begin();
				
				success = false;
				
				try {
					listUsers = em.createNativeQuery("select * from user_table", User.class).getResultList();
					success = true;
				} finally {
					if (success) {
						entityTransaction.commit();
					} else {
						entityTransaction.rollback();
					}
				}		
				
			} finally {
				em.close();
				factory.close();
			}
			
			for (User user: listUsers) {
				if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
					request.getSession().setAttribute("access", true);
					request.getSession().setAttribute("userId", user.getId());
					request.getSession().setAttribute("userRole", user.getRole());
				}
			}
		}

		doGet(request, response);
	}
}
