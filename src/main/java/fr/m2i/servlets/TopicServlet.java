package fr.m2i.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2i.models.Message;
import fr.m2i.models.Topic;

@WebServlet("/topic")
public class TopicServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String PAGE = "/WEB-INF/pages/topic.jsp";
	private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm";
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);
       
    public TopicServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int topicId = Integer.parseInt(request.getParameter("topicId"));
		
		request.setAttribute("topic", this.getTopic(topicId));
		
		this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("f");
		
		switch (type) {
			case "ajouter":
				ajouterMessage(request);
				break;
			case "supprimer":
				supprimerMessage(request);
				break;
			case "modifier":
				modifierMessage(request);
				break;
		}
		
		doGet(request, response);
	}
	
	private Topic getTopic(int topicId) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		Topic topic = em.find(Topic.class, topicId);
		
		em.close();
		factory.close();
		
		return topic;
	}
	
	private void ajouterMessage(HttpServletRequest request) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		String stringMessage = request.getParameter("message");		
		int topicId = Integer.parseInt(request.getParameter("topicId"));		
		int userId = (Integer) request.getSession().getAttribute("userId");
		
		String stringDate = DATE_FORMAT.format(new Date());
		
		boolean success;
		
		try {
			EntityTransaction entityTransaction = em.getTransaction();
			entityTransaction.begin();
			
			success = false;
			
			try {				
				em.createNativeQuery("insert into message_table (id_topic, id_user, message, message_date) VALUES(?, ?, ?, ?)")
				.setParameter(1, topicId)
				.setParameter(2, userId)
				.setParameter(3, stringMessage)
				.setParameter(4, stringDate)
				.executeUpdate();
				
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
	
	private void supprimerMessage(HttpServletRequest request) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		int messageId = Integer.parseInt(request.getParameter("messageId"));
		
		boolean success;
		
		try {
			EntityTransaction entityTransaction = em.getTransaction();
			entityTransaction.begin();
			
			success = false;
			
			try {				
				Message message = em.find(Message.class, messageId);
				em.remove(message);		
				
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
	
	private void modifierMessage(HttpServletRequest request) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		int messageId = Integer.parseInt(request.getParameter("messageId"));
		String stringMessage = request.getParameter("message");
		
		boolean success;
		
		try {
			EntityTransaction entityTransaction = em.getTransaction();
			entityTransaction.begin();
			
			success = false;
			
			try {				
				Message message = em.find(Message.class, messageId);
				message.setMessage(stringMessage);		
				
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
}
