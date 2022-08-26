package fr.m2i.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="topic_table")
public class Topic {
	
	@Id
	@Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Basic
	@Column(name="title")
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;
	
	@OneToMany(targetEntity = Message.class, mappedBy="topic", fetch = FetchType.EAGER)
	private List<Message> messages = new ArrayList<>();
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date topic_date;	
	
	public Topic() {
		super();
	}

	public Topic(int id, String title, User user, List<Message> messages, Date topic_date) {
		super();
		this.id = id;
		this.title = title;
		this.user = user;
		this.messages = messages;
		this.topic_date = topic_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Date getTopic_date() {
		return topic_date;
	}

	public void setTopic_date(Date topic_date) {
		this.topic_date = topic_date;
	}
}
