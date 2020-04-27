package com.naresh.joblender.controller;

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
import com.naresh.joblender.Exception.UserException;
import com.naresh.joblender.POJO.Company;
import com.naresh.joblender.POJO.User;
import com.naresh.joblender.Validator.UserValidator;



@Controller
@RequestMapping("/user/*")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	
	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;
	
	
	@RequestMapping(value = "/user/login.htm", method = RequestMethod.GET)
	protected String goToUserHome(HttpServletRequest request) throws Exception {
		
		return "login";
	}

	@RequestMapping(value = "/user/login.htm", method = RequestMethod.POST)
	protected ModelAndView loginUser(HttpServletRequest request, UserDAO userDao, JobsDAO jobsDao, CompanyDAO companyDao) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = (HttpSession) request.getSession();
		
		try {

			System.out.print("loginUser");

			User u = userDao.get(request.getParameter("username"), request.getParameter("password"));
			
			if(u == null){
				System.out.println("UserName/Password does not exist");
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				mv.setViewName("error");
				return mv;
			}
			if(u.getStatus().equals("inactive"))
			{
				System.out.println("User Profile inactive");
				session.setAttribute("errorMessage", "Your profile deleted");
				mv.setViewName("recover");
				return mv;
			}
			session.setAttribute("user", u);
			String role = u.getRole().trim();
			
			if (role.equals("employee")){
			System.out.println("Login as Employee");
			logger.info("Login as Employee");
				mv.addObject("jobs", jobsDao.list());
				mv.setViewName("employee-home");
				return mv ;
			}
			
			else if (role.equals("employer")){
				System.out.println("Login as Employer ");
				logger.info("Login as Employer");
				mv.addObject("jobs", jobsDao.listCompany(u.getCompany()));
				mv.setViewName("employer-home");
				return mv;
			}
			
			else if(role.equals("admin")){
				System.out.println("Login as Admin");
				logger.info("Login as admin");
				mv.addObject("companies", companyDao.list());
				mv.setViewName("admin-home");
				return mv;	
				}
			
			else
				return null;
	
		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			logger.error("Exception in login");
			session.setAttribute("errorMessage", "error while login");
			mv.setViewName("error");
			return mv;
		}

  }
	
	@RequestMapping(value = "/user/register.htm", method = RequestMethod.GET)
	protected ModelAndView registerUser(CompanyDAO companyDao) throws Exception {
		System.out.println("register User page ");
		ModelAndView mv = new ModelAndView();
		mv.addObject("companies", companyDao.list());
		mv.addObject("user",new User());
		mv.setViewName("register-user");
		return mv;

	}
	
	@RequestMapping(value = "/user/employee.htm", method = RequestMethod.GET)
	protected ModelAndView employeeHome(HttpServletRequest request,JobsDAO jobsDao) throws Exception {
		//checking session
		HttpSession sessionExpired = (HttpSession) request.getSession(false);
		if(sessionExpired==null)
			return new ModelAndView("login");
		
		System.out.println("Employee Home");
		ModelAndView mv = new ModelAndView();
		mv.addObject("jobs", jobsDao.list());
		mv.setViewName("employee-home");
        return mv;

	}
	
	@RequestMapping(value = "/user/employer.htm", method = RequestMethod.GET)
	protected ModelAndView employerHome(HttpServletRequest request,JobsDAO jobsDao) throws Exception {
		//checking session
		HttpSession sessionExpired = (HttpSession) request.getSession(false);
		if(sessionExpired==null)
			return new ModelAndView("login");
		
		System.out.println("Employeer Home");
		HttpSession session = (HttpSession) request.getSession();
		
		User u = (User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView();
		mv.addObject("jobs", jobsDao.listCompany(u.getCompany()));
		mv.setViewName("employer-home");
		return mv;

		

	}
	
	@RequestMapping(value = "/user/admin.htm", method = RequestMethod.GET)
	protected ModelAndView adminHome(HttpServletRequest request,CompanyDAO companyDao) throws Exception {
		//checking session
		HttpSession sessionExpired = (HttpSession) request.getSession(false);
		if(sessionExpired==null)
			return new ModelAndView("login");
		
		System.out.print("Admin Home");
		ModelAndView mv = new ModelAndView();
		mv.addObject("companies", companyDao.list());
		mv.setViewName("admin-home");
        return mv;

	}
	
	@RequestMapping(value = "/user/register.htm", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request,  @ModelAttribute("user") User user, BindingResult result,UserDAO userDao,CompanyDAO companyDao) throws Exception {

		validator.validate(user, result);

		if (result.hasErrors()) {

			ModelAndView mv = new ModelAndView();
			mv.addObject("companies", companyDao.list());
			mv.addObject("user", user);
			mv.setViewName("register-user");
			return mv;
		}

		try {

			System.out.print("registerNewUser");
			String role = request.getParameter("myRole");
			user.setRole(role);
			user.setStatus("active");
			
			if(role.equalsIgnoreCase("employer")) {
				
				String company = request.getParameter("myCompany");
				System.out.println("The Company is :" +company);
				Company c = companyDao.get(company);
				System.out.println("The company ID : " +c.getCompanyId());
                user.setCompany(c);
			
			}else {
				 Company c = companyDao.get(null);
				 user.setCompany(c);
			}
			
			User u = userDao.register(user);
			request.getSession().setAttribute("user", u);
			logger.info("registering new user");
			return new ModelAndView("userAdd-Successful", "user", u);
			
		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			logger.error("Exception in registering new user");
			return new ModelAndView("error", "errorMessage", "error while creating new user");
		}
	}
	
	@RequestMapping(value = "/user/reactive.htm", method = RequestMethod.POST)
	protected ModelAndView reactiveAccount(HttpServletRequest request, UserDAO userDao,JobsDAO jobsDao) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = (HttpSession) request.getSession();
		User u = userDao.get(request.getParameter("username"), request.getParameter("password"));
		
		if(u == null|| !(u.getEmail().equalsIgnoreCase(request.getParameter("email")))){
			System.out.println("UserName/Password does not exist");
			session.setAttribute("errorMessage", "UserName/Password does not exist");
			mv.setViewName("error");
			return mv;
		}
		
		 userDao.updateStatus(u, "active");
		 session.setAttribute("user", u);
		 mv.addObject("jobs", jobsDao.list());
		 mv.setViewName("employee-home");
		 return mv;
	}

}
