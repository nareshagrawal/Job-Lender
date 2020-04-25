package com.naresh.joblender.controller;

import java.util.Set;

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

import com.naresh.joblender.DAO.JobsDAO;
import com.naresh.joblender.DAO.UserDAO;
import com.naresh.joblender.Exception.JobsException;
import com.naresh.joblender.POJO.JobList;
import com.naresh.joblender.POJO.User;
import com.naresh.joblender.Validator.JobsValidator;

@Controller
@RequestMapping("/employer/**")
public class EmployerController {

private static final Logger logger = LoggerFactory.getLogger(EmployerController.class);
	
	@Autowired
	@Qualifier("jobsValidator")
	JobsValidator validator;
	
	
	@RequestMapping(value = "/employer/job/register.htm", method = RequestMethod.GET)
	protected ModelAndView addjobs(HttpServletRequest request) throws Exception {
		        // Checking Session
				HttpSession sessionExpired = (HttpSession) request.getSession(false);
				if(sessionExpired==null )
					return new ModelAndView("login");
				// Checking User
				HttpSession sessionUser = (HttpSession) request.getSession();
				User uTest = (User) sessionUser.getAttribute("user");
				if(!uTest.getRole().equals("employer"))
					return new ModelAndView("login");
		
		
		ModelAndView mv = new ModelAndView();
		HttpSession session = (HttpSession) request.getSession();
		User u = (User) session.getAttribute("user");
		mv.addObject("companyName", u.getCompany().getCompanyName());
		mv.addObject("joblist", new JobList());
		mv.setViewName("job-add");

		return mv;
	}

	@RequestMapping(value = "/employer/job/register.htm", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request, @ModelAttribute("joblist") JobList joblist,
			BindingResult result,JobsDAO jobsDao) throws Exception {
		       // Checking Session
				HttpSession sessionExpired = (HttpSession) request.getSession(false);
				if(sessionExpired==null )
					return new ModelAndView("login");
				// Checking User
				HttpSession sessionUser = (HttpSession) request.getSession();
				User uTest = (User) sessionUser.getAttribute("user");
				if(!uTest.getRole().equals("employer"))
					return new ModelAndView("login");
		
		validator.validate(joblist, result);
		if (result.hasErrors()) {
			return new ModelAndView("job-add", "joblist", joblist);
		}

		try {
			ModelAndView mv = new ModelAndView();
			HttpSession session = (HttpSession) request.getSession();
			User u = (User) session.getAttribute("user");
			System.out.println("Add New Job");
			joblist.setCompany(u.getCompany());
			joblist.setPostedName(u.getFirstName());
			joblist.setPostedID(u.getUsername());
			JobList job = jobsDao.register(joblist);
			mv.addObject("jobs", jobsDao.listCompany(u.getCompany()));
			mv.addObject("joblist", job);
			logger.info("Adding new job ");
			mv.addObject("jobMessage","The Job is added successfully");
			mv.setViewName("jobStatus");
			return mv;

		} catch (JobsException e) {
			System.out.println("Exception: " + e.getMessage());
			logger.error("Exception in adding job");
			return new ModelAndView("error", "errorMessage", "error while adding job");
		}
	}

	
	@RequestMapping(value = "/employer/jobs/update.htm", method = RequestMethod.GET)
	protected ModelAndView updatejobs( HttpServletRequest request,JobsDAO jobsDao) throws Exception {
		       // Checking Session
				HttpSession sessionExpired = (HttpSession) request.getSession(false);
				if(sessionExpired==null )
					return new ModelAndView("login");
				// Checking User
				HttpSession sessionUser = (HttpSession) request.getSession();
				User uTest = (User) sessionUser.getAttribute("user");
				if(!uTest.getRole().equals("employer"))
					return new ModelAndView("login");
				
		ModelAndView mv = new ModelAndView();
		HttpSession session = (HttpSession) request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		JobList job = jobsDao.getJob(id);
		User u = (User) session.getAttribute("user");
		mv.addObject("companyName", u.getCompany().getCompanyName());
		mv.addObject("jobList", job);
		mv.setViewName("job-update");

		return mv;
	}

	@RequestMapping(value = "/employer/jobs/update.htm", method = RequestMethod.POST)
	protected ModelAndView updatejob(HttpServletRequest request,JobsDAO jobsDao) throws Exception {
		        // Checking Session
				HttpSession sessionExpired = (HttpSession) request.getSession(false);
				if(sessionExpired==null )
					return new ModelAndView("login");
				// Checking User
				HttpSession sessionUser = (HttpSession) request.getSession();
				User uTest = (User) sessionUser.getAttribute("user");
				if(!uTest.getRole().equals("employer"))
					return new ModelAndView("login");
				
		
		ModelAndView mv = new ModelAndView();
		int id = Integer.parseInt(request.getParameter("jobId"));
		JobList job = jobsDao.getJob(id);
		job.setJobName((String)request.getAttribute("jobName"));
		job.setJobDesc((String)request.getAttribute("jobDes"));
		job.setReqSkill((String)request.getAttribute("reqSkill"));
		System.out.println("job id" + id+"Job updated");
		jobsDao.update(job);
		logger.info("updating job");
		mv.addObject("jobList", job);
		mv.addObject("jobMessage", "The Job is updated successfully");
		mv.setViewName("jobStatus");

		return mv;
	}
	
	@RequestMapping(value = "/employer/jobs/delete.htm", method = RequestMethod.GET)
	protected ModelAndView deletejobs(HttpServletRequest request,JobsDAO jobsDao,UserDAO userDao) throws Exception {
		       // Checking Session
				HttpSession sessionExpired = (HttpSession) request.getSession(false);
				if(sessionExpired==null )
					return new ModelAndView("login");
				// Checking User
				HttpSession sessionUser = (HttpSession) request.getSession();
				User uTest = (User) sessionUser.getAttribute("user");
				if(!uTest.getRole().equals("employer"))
					return new ModelAndView("login");
				
		
		ModelAndView mv = new ModelAndView();
		  int id = Integer.parseInt(request.getParameter("id"));
		 jobsDao.deleteUser(id);
	     int result= jobsDao.deleteJob(id);
	     if(result==1) {
	    	 mv.addObject("jobMessage","Job with Job ID:"+ id +" deleted" );
	         logger.info("job deleted");
	     }
	     else {
	    	 mv.addObject("jobMessage","Job with Job ID:"+ id +" is not deleted" );
	    	 logger.warn("job  not deleted");
	    }
	     mv.setViewName("jobStatus");
	     return mv;
	}
	
	@RequestMapping(value = "/employer/jobs/viewalljobs.htm", method = RequestMethod.GET)
	protected ModelAndView viewAllJob(HttpServletRequest request,JobsDAO jobsDao) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("joblist1", jobsDao.list());
		mv.setViewName("viewalljobs");
        logger.info("View all jobs");
		return mv;
	}
	
	@RequestMapping(value = "/employer/appliedlist.htm", method = RequestMethod.GET)
	protected ModelAndView allAppliedCandidate(HttpServletRequest request,JobsDAO jobsDao) throws Exception {
		        // Checking Session
				HttpSession sessionExpired = (HttpSession) request.getSession(false);
				if(sessionExpired==null )
					return new ModelAndView("login");
				// Checking User
				HttpSession sessionUser = (HttpSession) request.getSession();
				User uTest = (User) sessionUser.getAttribute("user");
				if(!uTest.getRole().equals("employer"))
					return new ModelAndView("login");
		
		ModelAndView mv = new ModelAndView();
		int id = Integer.parseInt(request.getParameter("id"));
		 JobList job = jobsDao.getJob(id);
		 Set<User> list = job.getUsers();
		 mv.addObject("list", list);
		 mv.addObject("JOBID",id);
		 mv.setViewName("applied-Candidate");

		return mv;
	}
	

}
