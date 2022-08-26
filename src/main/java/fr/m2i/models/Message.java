package fr.m2i.models;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="message_table")
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_topic")
	private Topic topic;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;
	
	@Basic
	@Column(name="message")
	private String message;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date message_date;	

	public Message() {
		super();
	}

	public Message(int id, Topic topic, User user, String message, Date message_date) {
		super();
		this.id = id;
		this.topic = topic;
		this.user = user;
		this.message = message;
		this.message_date = message_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getMessage_date() {
		return message_date;
	}

	public void setMessage_date(Date message_date) {
		this.message_date = message_date;
	}
}
