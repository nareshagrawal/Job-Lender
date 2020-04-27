package com.naresh.joblender.controller;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.naresh.joblender.DAO.CompanyDAO;
import com.naresh.joblender.DAO.EmployeeDAO;
import com.naresh.joblender.DAO.JobsDAO;
import com.naresh.joblender.DAO.UserDAO;
import com.naresh.joblender.Exception.JobsException;
import com.naresh.joblender.PDFview.myPdfview;
import com.naresh.joblender.POJO.Company;
import com.naresh.joblender.POJO.Employee;
import com.naresh.joblender.POJO.JobList;
import com.naresh.joblender.POJO.User;
import com.naresh.joblender.Validator.EmployeeValidator;




@Controller
@RequestMapping("/employee/**")
public class EmployeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	@Qualifier("employeeValidator")
	EmployeeValidator validator;
	

	@RequestMapping(value = "/employee/profile.htm", method = RequestMethod.GET)
	protected String employeeProfile(HttpServletRequest request,ModelMap model, Employee profile,EmployeeDAO employeeDao) throws Exception {
		 // Checking Session
		HttpSession sessionExpired = (HttpSession) request.getSession(false);
		if(sessionExpired==null )
			return "login";
		// Checking User
		HttpSession sessionUser = (HttpSession) request.getSession();
		User uTest = (User) sessionUser.getAttribute("user");
		if(!uTest.getRole().equals("employee"))
			return "login";
		
		HttpSession session = (HttpSession) request.getSession();
		User u = (User) session.getAttribute("user");
		profile.setFirstName(u.getFirstName());
		profile.setLastName(u.getLastName());
		profile.setEmail(u.getEmail());
		Employee employeeExist= employeeDao.getEmployee(u.getUserID());
		if(employeeExist!=null) {
			profile.setPhoneNo(employeeExist.getPhoneNo());
			profile.setSchool(employeeExist.getSchool());
			profile.setDegree(employeeExist.getDegree());
			profile.setMajor(employeeExist.getMajor());
			profile.setGPA(employeeExist.getGPA());
			profile.setCountry(employeeExist.getCountry());
			profile.setAddress(employeeExist.getAddress());
			profile.setCity(employeeExist.getCity());
			profile.setZip(employeeExist.getZip());
			profile.setLinkedin(employeeExist.getLinkedin());
			profile.setAbout(employeeExist.getAbout());
			profile.setSkills(employeeExist.getSkills());
		}
	    model.addAttribute("profile",profile);
		return "employee-profile";
	}
	
	
	@RequestMapping(value = "/employee/profile.htm", method = RequestMethod.POST)
	protected ModelAndView createemployeeProfile(HttpServletRequest request,@ModelAttribute("profile") Employee profile, BindingResult result,EmployeeDAO employeeDao) throws Exception {
		
		  // Checking Session
		HttpSession sessionExpired = (HttpSession) request.getSession(false);
		if(sessionExpired==null )
			return new ModelAndView("login");
		// Checking User
		HttpSession sessionUser = (HttpSession) request.getSession();
		User uTest = (User) sessionUser.getAttribute("user");
		if(!uTest.getRole().equals("employee"))
			return new ModelAndView("login");
		
		validator.validate(profile, result);
		ModelAndView mv = new ModelAndView();
		
		if (result.hasErrors()) {
			HttpSession session = (HttpSession) request.getSession();
			User u = (User) session.getAttribute("user");
			profile.setFirstName(u.getFirstName());
			profile.setLastName(u.getLastName());
			profile.setEmail(u.getEmail());
			return new ModelAndView("employee-profile", "profile", profile);
		}
		
		try {

			
			HttpSession session = (HttpSession) request.getSession();
			User u = (User) session.getAttribute("user");
			CommonsMultipartFile resume = profile.getResume();
			String fileName = u.getUserID()+".pdf";
			File file = new File ("C:\\Users\\nares\\OneDrive\\Desktop\\Job_lender_Resume",fileName);
			resume.transferTo(file);
			logger.info("resume uploaded");
			Employee employeeExist= employeeDao.getEmployee(u.getUserID());
			if(employeeExist!=null) {
				mv.addObject("profile",employeeDao.updateEmployee(employeeExist,profile,fileName));
				logger.info("profile updated");
			}else {
			profile.setUser(u);
			profile.setResumeName(fileName);
			mv.addObject("profile",employeeDao.registerEmployee(profile) );
			System.out.println("creating new profile");
			logger.info("creating new profile");
			}
			mv.addObject("employeeJobStatus", "Profile submitted successfully");
			mv.setViewName("employeeJob-Status");
			return mv;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			logger.error("Exception in creating profile");
			return new ModelAndView("error", "errorMessage", "error while creating profile");
		}
	}
	
	
	
	@RequestMapping(value = "/employee/profile.pdf", method = RequestMethod.GET)
	protected ModelAndView viewProfile(HttpServletRequest request,EmployeeDAO employeeDao) throws Exception {
		
		 // Checking Session
		HttpSession sessionExpired = (HttpSession) request.getSession(false);
		if(sessionExpired==null )
			return new ModelAndView("login");
		// Checking User
		HttpSession sessionUser = (HttpSession) request.getSession();
		User uTest = (User) sessionUser.getAttribute("user");
		if(!uTest.getRole().equals("employer"))
			return new ModelAndView("login");
		
		
		int id = Integer.parseInt(request.getParameter("id"));
		View viewProfile = new myPdfview();
		Employee employeePro = employeeDao.getEmployee(id);
		if(employeePro!=null)
			return new ModelAndView(viewProfile,"viewProfile",employeePro);
		else 
			return new ModelAndView("notFoundEmployee","notfound","Candidate Profile is not registered");
		
	}
	
	@RequestMapping(value = "/employee/resume.pdf", method = RequestMethod.GET)
	protected ModelAndView viewResume(HttpServletRequest request,EmployeeDAO employeeDao) throws Exception {
		 // Checking Session
		HttpSession sessionExpired = (HttpSession) request.getSession(false);
		if(sessionExpired==null )
			return new ModelAndView("login");
		// Checking User
		HttpSession sessionUser = (HttpSession) request.getSession();
		User uTest = (User) sessionUser.getAttribute("user");
		if(!uTest.getRole().equals("employer"))
			return new ModelAndView("login");
		
		
		int id = Integer.parseInt(request.getParameter("id"));
		Employee employeePro = employeeDao.getEmployee(id);
		if(employeePro!=null)
			return new ModelAndView("viewResume","emp",employeePro);
		else 
			return new ModelAndView("notFoundEmployee","notfound","Candidate resume not found");
		
		
	}
	
	@RequestMapping(value = "/employee/deleteaccount", method = RequestMethod.GET)
	protected ModelAndView deleteAccount(HttpServletRequest request,JobsDAO jobsDao,UserDAO userDao,EmployeeDAO employeeDao) throws Exception {
		 // Checking Session
		HttpSession sessionExpired = (HttpSession) request.getSession(false);
		if(sessionExpired==null )
			return new ModelAndView("login");
		// Checking User
		HttpSession sessionUser = (HttpSession) request.getSession();
		User uTest = (User) sessionUser.getAttribute("user");
		if(!uTest.getRole().equals("employee"))
			return new ModelAndView("login");
		
		
		 ModelAndView mv = new ModelAndView();
		 HttpSession session = (HttpSession) request.getSession();
		 User u = (User) session.getAttribute("user");
		
	     Set<JobList> list = u.getJoblists();
	     if(list.size()!=0) {
	     Iterator<JobList> i=list.iterator();  
			while(i.hasNext()) {
				JobList temp=i.next();
				jobsDao.deleteApplied(u.getUserID(),temp.getJobID());
			}
	     }
	     Employee employee = employeeDao.getEmployee(u.getUserID());
			if(employee!=null) {
			employeeDao.deleteEmployee(u.getUserID());
			}
			userDao.updateStatus(u,"inactive");
			//userDao.delete(u.getUserID());
			session.invalidate();
			logger.info("account deleted");
		    mv.setViewName("account-delete");

		return mv;
	}
	
	@RequestMapping(value = "/employee/contact.htm", method = RequestMethod.GET)
	protected ModelAndView contact(HttpServletRequest request,UserDAO userDao) throws Exception {
		 // Checking Session
		HttpSession sessionExpired = (HttpSession) request.getSession(false);
		if(sessionExpired==null )
			return new ModelAndView("login");
		// Checking User
		HttpSession sessionUser = (HttpSession) request.getSession();
		User uTest = (User) sessionUser.getAttribute("user");
		if(!uTest.getRole().equals("employer"))
			return new ModelAndView("login");
		
		
		 long id = Long.parseLong(request.getParameter("id"));
		 User candidate =userDao.getUserID(id);
		 ModelAndView mv = new ModelAndView();
		 HttpSession session = (HttpSession) request.getSession();
		 User u = (User) session.getAttribute("user");
		 String form = u.getEmail();
		 mv.addObject("name",candidate.getFirstName());
		 mv.addObject("From", form);
		 mv.addObject("To", candidate.getEmail());
		 mv.setViewName("contact");
		 return mv;
	}
	
	
	@RequestMapping(value = "/employee/contact.htm", method = RequestMethod.POST)
	protected ModelAndView sendMail(HttpServletRequest request,UserDAO userDao) throws Exception {
		 // Checking Session
		HttpSession sessionExpired = (HttpSession) request.getSession(false);
		if(sessionExpired==null )
			return new ModelAndView("login");
		// Checking User
		HttpSession sessionUser = (HttpSession) request.getSession();
		User uTest = (User) sessionUser.getAttribute("user");
		if(!uTest.getRole().equals("employer"))
			return new ModelAndView("login");
		
		
		String to = (request.getParameter("to"));
		//String from = (request.getParameter("from"));
		String subject = (request.getParameter("subject"));
		String message = (request.getParameter("body"));
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("joblender31@gmail.com", "12345678Na"));
		email.setSSLOnConnect(true);
		email.setFrom("joblender31@gmail.com");
		email.setSubject(subject);
		email.setMsg(message);
		email.addTo(to);
		email.send();
		
		 return new ModelAndView("mailsent","name",userDao.getByEmail(to).getFirstName());
		
		
	}
	
	@RequestMapping(value = "/employee/jobs/unapply.htm", method = RequestMethod.GET)
	protected ModelAndView unapplyjobs(HttpServletRequest request,JobsDAO jobsDao,UserDAO userDao) throws Exception {
		 // Checking Session
		HttpSession sessionExpired = (HttpSession) request.getSession(false);
		if(sessionExpired==null )
			return new ModelAndView("login");
		// Checking User
		HttpSession sessionUser = (HttpSession) request.getSession();
		User uTest = (User) sessionUser.getAttribute("user");
		if(!uTest.getRole().equals("employee"))
			return new ModelAndView("login");
		
		
		ModelAndView mv = new ModelAndView();
		 int job_id = Integer.parseInt(request.getParameter("id"));
		 boolean flag = false;
		 HttpSession session = (HttpSession) request.getSession();
		 User u = (User) session.getAttribute("user");
		 JobList job = jobsDao.getJob(job_id);
		
		     for (JobList job1 : u.getJoblists()) {
				if (job1.getJobID() == job_id) {
					flag = true;
				    }
		     }
		     if(flag) {
		    	    
		    	    userDao.remove(u, job);
					job.getUsers().remove(u);
					jobsDao.update(job);
					jobsDao.deleteApplied(u.getUserID(),job_id);
					User uNew = userDao.getUserName(u.getUsername());
					session.setAttribute("user", uNew);
					
		    	   mv.addObject("employeeJobStatus","Application for Job ID:"+ job_id +" is  successfully withdrawn" );
		    	   logger.info("Application withdrawn");
		    	   mv.setViewName("employeeJob-Status");
		     }else {
		    	   mv.addObject("employeeJobStatus","Please apply job first" );
		    	   mv.setViewName("employeeJob-Status");
		     }
	     return mv;
	}
	
	@RequestMapping(value = "/employee/jobs/add.htm", method = RequestMethod.POST)
	protected ModelAndView addJob(HttpServletRequest request,UserDAO userDao,JobsDAO jobsDao) throws Exception {
		 // Checking Session
		HttpSession sessionExpired = (HttpSession) request.getSession(false);
		if(sessionExpired==null )
			return new ModelAndView("login");
		// Checking User
		HttpSession sessionUser = (HttpSession) request.getSession();
		User uTest = (User) sessionUser.getAttribute("user");
		if(!uTest.getRole().equals("employee"))
			return new ModelAndView("login");
		
		try {
			boolean flag = false;
			ModelAndView mv = new ModelAndView();
			HttpSession session = (HttpSession) request.getSession();
			User u = (User) session.getAttribute("user");
			int job_id = Integer.parseInt(request.getParameter("jobID"));
			for (JobList job1 : u.getJoblists()) {
				if (job1.getJobID() == job_id) {
					flag = true;
				}
			}
			
			if (!flag) {
				System.out.println("Add New Job");
				JobList job = jobsDao.getJob(job_id);
				userDao.update(u, job);
				job.getUsers().add(u);
				jobsDao.update(job);
				String address = u.getEmail();
				String subject = "Job Applied Notification for " +job.getJobName();
				String message = "Dear "+u.getFirstName()+","+"\n"+"\nThank you for submitting your application through Job Lender. You've taken the first step towards exploring your career with world-leading companies.We wanted to let you know we received your application.\nYou Applied for the position : "+job.getJobName()+"\n"+"Job Description : " +job.getJobDesc() 
				+"\nDate : " +new Date() +"\n"+"\nTalent Acquisition Team team will review your documents and let you know if we require additional information . We will keep you updated along the journey."+"\n"+"\nRegards,"+"\nJob Lender Talent Acquisition";
				
				Email email = new SimpleEmail();
				email.setHostName("smtp.googlemail.com");
				email.setSmtpPort(465);
				email.setAuthenticator(new DefaultAuthenticator("joblender31@gmail.com", "12345678Na"));
				email.setSSLOnConnect(true);
				email.setFrom("joblender31@gmail.com");
				email.setSubject(subject);
				email.setMsg(message);
				email.addTo(address);
				email.send();
				logger.info("mail sent");
				System.out.println("mail sent");
				//mv.addObject("jobs", jobsDao.listCompany(u.getCompany()));
				mv.addObject("joblist", job);
				logger.info("jobApplied-Successfull");
				mv.addObject("employeeJobStatus", "You have successfully applied to the job");
				mv.setViewName("employeeJob-Status");
				flag = false;
				return mv;
			} else {
				System.out.println("job is already applied");
				mv.addObject("jobs", null);
				mv.addObject("employeeJobStatus", "You have already applied for this job");
				mv.setViewName("employeeJob-Status");
				logger.warn("Job already applied");
				return mv;
			}
		} catch (JobsException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while adding job");
		}
	}

	@RequestMapping(value = "/employee/jobs/search.htm", method = RequestMethod.GET)
	protected ModelAndView searchjob(HttpServletRequest request) throws Exception {
		 // Checking Session
		HttpSession sessionExpired = (HttpSession) request.getSession(false);
		if(sessionExpired==null )
			return new ModelAndView("login");
		// Checking User
		HttpSession sessionUser = (HttpSession) request.getSession();
		User uTest = (User) sessionUser.getAttribute("user");
		if(!uTest.getRole().equals("employee"))
			return new ModelAndView("login");
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("search-job");
		return mv;
	}

	@RequestMapping(value = "/employee/jobs/search.htm", method = RequestMethod.POST)
	protected ModelAndView searchjobs(HttpServletRequest request,JobsDAO jobsDao,CompanyDAO companyDao) throws Exception {
		
		 // Checking Session
		HttpSession sessionExpired = (HttpSession) request.getSession(false);
		if(sessionExpired==null )
			return new ModelAndView("login");
		// Checking User
		HttpSession sessionUser = (HttpSession) request.getSession();
		User uTest = (User) sessionUser.getAttribute("user");
		if(!uTest.getRole().equals("employee"))
			return new ModelAndView("login");
		
		
		ModelAndView mv = new ModelAndView();
		//HttpSession session = (HttpSession) request.getSession();
		//User u = (User) session.getAttribute("user");
		String name = (String)request.getAttribute("searchT");
		String search = request.getParameter("searchkey");

		if (search.equalsIgnoreCase("jobname")) {
			List<JobList> jobs = jobsDao.listJobname(name);
			if (jobs.isEmpty()) {
				System.out.println("The returned list is empty");
				return new ModelAndView("search-fail","searchResult","Job with job name:" + name+" not found");
					}else {
						    mv.addObject("jobs", jobs);
							mv.setViewName("search-success");
						}
		} else if (search.equalsIgnoreCase("company")) {
			    Company c = companyDao.get(name);
                 if (c == null) {
						System.out.println("company not found");
						return new ModelAndView("search-fail","searchResult","Company with company name:" +name +" not found");
				 }else {
							List<JobList> jobs = jobsDao.listCompany(c);
			                if (jobs.isEmpty()) {
								System.out.println("The returned list is empty");
								return new ModelAndView("search-fail","searchResult","Company with company name:" + name+" not posted job yet");
							    }
					        mv.addObject("jobs", jobs);
					        mv.setViewName("search-success");
					}
		  }else if (search.equalsIgnoreCase("jobid")) {
			    JobList job = jobsDao.getJob(Integer.parseInt(name));
				if (job==null) {
					System.out.println("job is not present");
					return new ModelAndView("search-fail","searchResult","Job with job ID:" + name+" not found");
						}else {
							    mv.addObject("job", job);
								mv.setViewName("searchID-success");
							}
			  
		  }
		
		return mv;
 }

	@RequestMapping(value = "/employee/jobs/applied.htm", method = RequestMethod.GET)
	protected ModelAndView appliedjob(HttpServletRequest request,JobsDAO jobsDao) throws Exception {
		 // Checking Session
		HttpSession sessionExpired = (HttpSession) request.getSession(false);
		if(sessionExpired==null )
			return new ModelAndView("login");
		// Checking User
		HttpSession sessionUser = (HttpSession) request.getSession();
		User uTest = (User) sessionUser.getAttribute("user");
		if(!uTest.getRole().equals("employee"))
			return new ModelAndView("login");
		
		 
		ModelAndView mv = new ModelAndView();
		 HttpSession session = (HttpSession) request.getSession();
		 User u = (User) session.getAttribute("user");
		
		 mv.addObject("appliedJob",u.getJoblists());
		 mv.setViewName("jobs-applied");

		return mv;
	}
	
	
	
	@RequestMapping(value = "/employee/jobs/recommended.htm", method = RequestMethod.GET)
	protected ModelAndView recommendedJob(HttpServletRequest request,JobsDAO jobsDao,EmployeeDAO employeeDao) throws Exception {
		 // Checking Session
		HttpSession sessionExpired = (HttpSession) request.getSession(false);
		if(sessionExpired==null )
			return new ModelAndView("login");
		// Checking User
		HttpSession sessionUser = (HttpSession) request.getSession();
		User uTest = (User) sessionUser.getAttribute("user");
		if(!uTest.getRole().equals("employee"))
			return new ModelAndView("login");
		
		
		ModelAndView mv = new ModelAndView();
		Set<JobList> recom = new LinkedHashSet<JobList>();
		
		HttpSession session = (HttpSession) request.getSession();
		User u = (User) session.getAttribute("user");
		Employee employee = employeeDao.getEmployee(u.getUserID());
		if(employee!=null) {
		String skill[] = employee.getSkills().split(",");
		int count =0;
		
		boolean flag = false;
		
		List <JobList> jobList = jobsDao.list();
		Iterator<JobList> i=jobList.iterator();  
		while(i.hasNext())  
			 {
				JobList temp=i.next();
				
				int job_id = temp.getJobID();
				for (JobList job1 : u.getJoblists()) {
					if (job1.getJobID() == job_id) {
						flag = true;
					}
				}
				
				if(!flag) {
				String reqSkill[] = temp.getReqSkill().split(",");
				for(int j =0;j<reqSkill.length;j++) {
						for(int k=0;k<skill.length;k++) {
							if(skill[k].equalsIgnoreCase(reqSkill[j]))		
							count++;
						}
					}
				
				if(count>=2) {
					recom.add(temp);
					//count=0;
				}
			
			 }
				flag=false;
				count=0;
		}
		mv.addObject("recom", recom);
		mv.setViewName("recommendation");
        logger.info("Jobs recommendation");
	}else {
		
		mv.addObject("employeeJobStatus", "Please complete profile to provide better assistance");
		mv.setViewName("employeeJob-Status");
	}
		return mv;
	}

}
