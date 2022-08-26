package fr.m2i.servlets;

import java.io.IOException;

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

@WebServlet("/creer-compte")
public class CreerCompteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String PAGE="/WEB-INF/pages/register.jsp";	
       
    public CreerCompteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if ((request.getSession().getAttribute("access") != null && (boolean) request.getSession().getAttribute("access") == true)) {
			
			response.sendRedirect(request.getContextPath() + "/account");
		} else {
			this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (username != null && password != null) {
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
			EntityManager em = factory.createEntityManager();
			
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setRole("utilisateur");
			
			boolean success;
			
			try {
				EntityTransaction entityTransaction = em.getTransaction();
				entityTransaction.begin();
				
				success = false;
				
				try {				
					em.persist(user);
					
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
			
			request.getSession().setAttribute("access", true);
			request.getSession().setAttribute("userId", user.getId());
			request.getSession().setAttribute("userRole", user.getRole());
		}
		
		doGet(request, response);
	}
}
