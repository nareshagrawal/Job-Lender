package com.naresh.joblender.POJO;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.persistence.Table;



@Entity
@Table(name = "User_table")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "userID", nullable = false)
	 private Long userID;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name ="lastName")
	private String lastName;
	
	@Column(name ="email")
	private String email;
	
	@Column(name = "userName")
	private String username;

	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private String role;
	
	@ManyToOne
	private Company company; 
	
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable (
       name="user_job_table",
       joinColumns = {@JoinColumn(name="userID", nullable = false )},
       inverseJoinColumns = {@JoinColumn(name="jobID" )}
       )
	
	 private Set<JobList> joblists = new LinkedHashSet<JobList>();
	
	

	public User(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
		
	}
	
	public User() {
		
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Set<JobList> getJoblists() {
		return joblists;
	}

	public void setJoblists(Set<JobList> joblists) {
		this.joblists = joblists;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
