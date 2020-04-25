package com.naresh.joblender.POJO;

import java.util.Date;
import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;





@Entity
@Table(name = "Job_table")
public class JobList {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "jobID", unique=true, nullable = false)
	private int jobID;
	
	@Column(name = "jobName")
	private String jobName;
	
	
	@Column(name = "jobDesc")
	@Lob
	private String jobDesc;
	
	
	@Column(name = "postedName")
	private String postedName;
	
	
	@Column(name = "posteduserName")
	private String postedID;
	
	@Column(name = "posteddate")
	private Date postedDate;
	
	@Column(name = "reqSkill")
	@Lob
	private String reqSkill;
	
	@ManyToOne
	@JoinColumn(name= "companyId")
	private Company company;
	
	@ManyToMany(mappedBy="joblists",fetch = FetchType.EAGER)
	private Set<User> users;

	public JobList(){
		
	}
	
	public JobList(String jobname, String jobdesc){
		this.jobName = jobname;
		this.jobDesc = jobdesc;
		this.postedDate = new Date();
		
	}
	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}
	
	
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getPostedName() {
		return postedName;
	}

	public void setPostedName(String postedName) {
		this.postedName = postedName;
	}

	public String getPostedID() {
		return postedID;
	}

	public void setPostedID(String postedID) {
		this.postedID = postedID;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public String getReqSkill() {
		return reqSkill;
	}

	public void setReqSkill(String reqSkill) {
		this.reqSkill = reqSkill;
	}	
	
	
}
