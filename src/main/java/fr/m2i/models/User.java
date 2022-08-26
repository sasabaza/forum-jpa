package fr.m2i.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user_table")
public class User {
	
	@Id
	@Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Basic
	@Column(name="username")
	private String username;
	
	@Basic
	@Column(name="password")
	private String password;
	
	@Basic
	@Column(name="role")
	private String role;	
	
	@OneToMany(targetEntity = Topic.class, mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Topic> topics = new ArrayList<>();
	
	@OneToMany(targetEntity = Message.class, mappedBy="user", cascade = CascadeType.ALL)
	private List<Message> messages = new ArrayList<>();	

	public User() {
		super();
	}

	public User(int id, String username, String password, String role, List<Topic> topics, List<Message> messages) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.topics = topics;
		this.messages = messages;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}	
	
    public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public String toString() {
        return this.username;
    } 
}
