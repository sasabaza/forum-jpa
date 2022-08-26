package fr.m2i.servlets;

import java.io.IOException;
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

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String PAGE = "/WEB-INF/pages/account.jsp";

	public AccountServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if ((request.getSession().getAttribute("access") != null
				&& (boolean) request.getSession().getAttribute("access") == true)) {

			String userRole = (String) request.getSession().getAttribute("userRole");

			if (userRole.equals("administrateur")) {
				request.setAttribute("listusers", this.getUsers());
			}

			this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userid");

		deleteUser(userId);

		doGet(request, response);
	}

	private void deleteUser(String userId) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		boolean success;

		User user;

		try {
			EntityTransaction entityTransaction = em.getTransaction();
			entityTransaction.begin();

			success = false;

			try {
				user = (User) em.createNativeQuery("select * from user_table where id = :id", User.class)
						.setParameter("id", userId)
						.getSingleResult();

				if (user != null) {
					em.remove(user);
				}

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
	}

	@SuppressWarnings("unchecked")
	protected List<User> getUsers() {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		List<User> listUsers;
		boolean success;

		try {
			EntityTransaction entityTransaction = em.getTransaction();
			entityTransaction.begin();

			success = false;

			try {
				listUsers = em.createNativeQuery("select * from user_table where not user_table.id ='1'", User.class)
						.getResultList();
				
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

		return listUsers;
	}
}
