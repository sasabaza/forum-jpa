package fr.m2i.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

import fr.m2i.models.Message;
import fr.m2i.models.Topic;
import fr.m2i.models.User;

@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;	
	private static final String PAGE = "/WEB-INF/pages/accueil.jsp";
       
    public AccueilServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("listTopics", this.getTopics());
		
		this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("f");
		
		switch (type) {
			case "ajouter":
				ajouterTopic(request);
				break;
			case "supprimer":
				supprimerTopic(request);
				break;			
			case "maj":
				majTopic(request);
				break;
		}		
		
		doGet(request, response);
	}
	
	@SuppressWarnings("unchecked")
	private List<Topic> getTopics() {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		List<Topic> listTopics;
		boolean success;
		
		try {
			EntityTransaction entityTransaction = em.getTransaction();
			entityTransaction.begin();
			
			success = false;
			
			try {
				listTopics = em.createNativeQuery("select * from topic_table order by topic_date desc", Topic.class).getResultList();
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
		
		return listTopics;
	}
	
	private void ajouterTopic(HttpServletRequest request) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		String title = request.getParameter("title");		
		int userId = (Integer) request.getSession().getAttribute("userId");
		User user = em.find(User.class, userId);
		
		boolean success;
		
		try {
			EntityTransaction entityTransaction = em.getTransaction();
			entityTransaction.begin();
			
			success = false;
			
			try {
				Topic topic = new Topic();
				topic.setMessages(new ArrayList<Message>());
				topic.setTitle(title);
				topic.setTopic_date(new Date());
				topic.setUser(user);
				
				em.persist(topic);
				
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
	
	private void supprimerTopic(HttpServletRequest request) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		int topicId = Integer.parseInt(request.getParameter("id"));
		int userId = (Integer) request.getSession().getAttribute("userId");
		
		boolean success;
		
		try {
			EntityTransaction entityTransaction = em.getTransaction();
			entityTransaction.begin();
			
			success = false;
			
			try {				
				em.createNativeQuery("delete from message_table where id_topic = ?")
				.setParameter(1, topicId)
				.executeUpdate();				
				
				em.createNativeQuery("delete from topic_table where id = ? and id_user = ?")
				.setParameter(1, topicId)
				.setParameter(2, userId)
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
	
	private void majTopic(HttpServletRequest request) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		String title = request.getParameter("title");
		int topicId = Integer.parseInt(request.getParameter("id"));
		
		boolean success;
		
		try {
			EntityTransaction entityTransaction = em.getTransaction();
			entityTransaction.begin();
			
			success = false;
			
			try {
				Topic topic = em.find(Topic.class, topicId);
				topic.setTitle(title);
				
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
