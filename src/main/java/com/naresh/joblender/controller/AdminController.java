package com.naresh.joblender.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.naresh.joblender.DAO.CompanyDAO;
import com.naresh.joblender.DAO.JobsDAO;
import com.naresh.joblender.DAO.UserDAO;
import com.naresh.joblender.Exception.CompanyException;
import com.naresh.joblender.POJO.Company;
import com.naresh.joblender.POJO.JobList;
import com.naresh.joblender.POJO.User;
import com.naresh.joblender.Validator.CompanyValidator;


@Controller
@RequestMapping("/admin/**")
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	@Qualifier("companyValidator")
	CompanyValidator validator;
	
	
	@RequestMapping(value = "/admin/company/register.htm", method = RequestMethod.GET)
	protected ModelAndView registerUser(HttpServletRequest request) throws Exception {
		// Checking Session
		HttpSession sessionExpired = (HttpSession) request.getSession(false);
		if(sessionExpired==null )
			return new ModelAndView("login");
		// Checking User
		HttpSession sessionUser = (HttpSession) request.getSession();
		User uTest = (User) sessionUser.getAttribute("user");
		if(!uTest.getRole().equals("admin"))
			return new ModelAndView("login");
		
		return new ModelAndView("register-company", "company", new Company());
	}
	
	
	@RequestMapping(value = "/admin/company/register.htm", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request,  @ModelAttribute("company") Company company, BindingResult result,CompanyDAO companyDao) throws Exception {
		
		        // Checking Session
				HttpSession sessionExpired = (HttpSession) request.getSession(false);
				if(sessionExpired==null )
					return new ModelAndView("login");
				// Checking User
				HttpSession sessionUser = (HttpSession) request.getSession();
				User uTest = (User) sessionUser.getAttribute("user");
				if(!uTest.getRole().equals("admin"))
					return new ModelAndView("login");
				
		
		validator.validate(company, result);
		if (result.hasErrors()) {
			return new ModelAndView("register-company", "company", company);
		}

		try {

			System.out.println("registering New  Company");
			Company c = companyDao.register(company);
			logger.info("registering new company");
			return new ModelAndView("companyStatus", "companyStatus", "The Company has been added Successfully");

		} catch (CompanyException e) {
			System.out.println("Exception: " + e.getMessage());
			logger.error("Exception in registering company");
			return new ModelAndView("error", "errorMessage", "error while registering company");
		}
	}

	@RequestMapping(value = "/admin/company/update.htm", method = RequestMethod.GET)
	protected ModelAndView updateComp(HttpServletRequest request,CompanyDAO companyDao) throws Exception {
		
		        // Checking Session
				HttpSession sessionExpired = (HttpSession) request.getSession(false);
				if(sessionExpired==null )
					return new ModelAndView("login");
				// Checking User
				HttpSession sessionUser = (HttpSession) request.getSession();
				User uTest = (User) sessionUser.getAttribute("user");
				if(!uTest.getRole().equals("admin"))
					return new ModelAndView("login");
				
		
		ModelAndView mv = new ModelAndView();
		int id = Integer.parseInt(request.getParameter("id"));
		Company comp = companyDao.getById(id);
		mv.addObject("comp", comp);
		mv.addObject("companyName", comp.getCompanyName());
		mv.setViewName("company-update");
		return mv;
	}
	
	@RequestMapping(value = "/admin/company/update.htm", method = RequestMethod.POST)
	protected ModelAndView updateCompany(HttpServletRequest request,CompanyDAO companyDao) throws Exception {
		
		        // Checking Session
				HttpSession sessionExpired = (HttpSession) request.getSession(false);
				if(sessionExpired==null )
					return new ModelAndView("login");
				// Checking User
				HttpSession sessionUser = (HttpSession) request.getSession();
				User uTest = (User) sessionUser.getAttribute("user");
				if(!uTest.getRole().equals("admin"))
					return new ModelAndView("login");
				
		
		ModelAndView mv = new ModelAndView();
		
		int id = Integer.parseInt(request.getParameter("companyId"));
		Company comp = companyDao.getById(id);
		
		comp.setCompanyName((String)request.getAttribute("comName"));
		comp.setCompanyAddress((String)request.getAttribute("comAdd"));
		comp.setCompanyDescription((String)request.getAttribute("comDes"));
		
		companyDao.update(comp);
		logger.info("updating company");
		mv.addObject("companies", comp);
		mv.addObject("companyStatus","Company is updated successfully");
		mv.setViewName("companyStatus");
		return mv;
	}
	
	@RequestMapping(value = "/admin/company/delete.htm", method = RequestMethod.GET)
	protected ModelAndView deleteCompany(HttpServletRequest request,CompanyDAO companyDao,UserDAO userDao,JobsDAO jobsDao) throws Exception {
		        // Checking Session
				HttpSession sessionExpired = (HttpSession) request.getSession(false);
				if(sessionExpired==null )
					return new ModelAndView("login");
				// Checking User
				HttpSession sessionUser = (HttpSession) request.getSession();
				User uTest = (User) sessionUser.getAttribute("user");
				if(!uTest.getRole().equals("admin"))
					return new ModelAndView("login");
				
		
		ModelAndView mv = new ModelAndView();
		
		int id = Integer.parseInt(request.getParameter("id"));
	
		List <JobList> jobList = jobsDao.listCompany(companyDao.getById(id));
		if(jobList.size()!=0) {
			Iterator<JobList> i=jobList.iterator();  
			while(i.hasNext())  
				 {
				    JobList temp=i.next();
					jobsDao.deleteUser(temp.getJobID());
					int result1 = jobsDao.deleteJob(temp.getJobID());
				 }
			  
			 
			}
		List <User> userlist = userDao.getUsersComp(id);
		if(userlist.size()!=0) {
			Iterator<User> i=userlist.iterator();  
			while(i.hasNext())  
				 {
				  User temp=i.next();
				  userDao.delete(temp.getUserID());
				 }
		}
		companyDao.delete(companyDao.getById(id));
		logger.info("delete company");
		mv.addObject("companyStatus", "Company deleted successfully");
		mv.setViewName("companyStatus");
		return mv;
	}
	
	@RequestMapping(value = "/admin/company/jobposted.htm", method = RequestMethod.GET)
	protected ModelAndView jobPosted(HttpServletRequest request,JobsDAO jobsDao,CompanyDAO companyDao) throws Exception {
		        // Checking Session
				HttpSession sessionExpired = (HttpSession) request.getSession(false);
				if(sessionExpired==null )
					return new ModelAndView("login");
				// Checking User
				HttpSession sessionUser = (HttpSession) request.getSession();
				User uTest = (User) sessionUser.getAttribute("user");
				if(!uTest.getRole().equals("admin"))
					return new ModelAndView("login");
				
		
		ModelAndView mv = new ModelAndView();
		int id = Integer.parseInt(request.getParameter("id"));
		Company comp1 = companyDao.getById(id);
		List<JobList> jobs = jobsDao.listCompany(comp1);
		mv.addObject("CompanyJobs", jobs);
	    mv.addObject("companyName1", comp1.getCompanyName());
		mv.setViewName("company-Jobs");
		return mv;
	}
}
