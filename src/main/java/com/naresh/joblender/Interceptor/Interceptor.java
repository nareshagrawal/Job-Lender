package com.naresh.joblender.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class Interceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(Interceptor.class);

	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		 
		logger.info("In interceptor: prehandle");
		
		 String options = request.getParameter("opt") ==null ? "" : request.getParameter("opt");
		 if(options!=null || !(options.equals(""))){
			
			 if(options.equalsIgnoreCase("updatecomp")){
				
				 logger.info("In interceptor:Update Company");
				 String comName = request.getParameter("CompanyName");
				 String comDes =  request.getParameter("companyDesc");
				 String comAdd =  request.getParameter("CompanyAddress");
				 
				 String [] badCharacters = {"<",">","script","/",";","#","|"};
				 
				 for(int i=0;i<badCharacters.length;i++){
					 comName= comName.replaceAll(badCharacters[i], "");
					 comName = comName.trim();
					 
					 comDes= comDes.replaceAll(badCharacters[i], "");
					 comDes = comDes.trim();
					 
					 comAdd= comAdd.replaceAll(badCharacters[i], "");
					 comAdd = comAdd.trim();
				 }
				 
				 request.setAttribute("comName",comName );
				 request.setAttribute("comDes",comDes );
				 request.setAttribute("comAdd",comAdd );
				 
			 }
			
          if(options.equalsIgnoreCase("updatejob")){
        	 
        	     logger.info("In interceptor:Update Job");
        	     String jobName = request.getParameter("jobName");
				 String jobDes =  request.getParameter("jobDesc");
				 String reqSkill = request.getParameter("reqSkill");
				 String [] badCharacters = {"<",">","script","/",";","#","|"};
				 
				 for(int i=0;i<badCharacters.length;i++){
					 jobName= jobName.replaceAll(badCharacters[i], "");
					 jobName = jobName.trim();
					 
					 jobDes= jobDes.replaceAll(badCharacters[i], "");
					 jobDes = jobDes.trim();
					 
					 reqSkill= reqSkill.replaceAll(badCharacters[i], "");
					  
					 			 
				 }
				 
				 request.setAttribute("jobName",jobName );
				 request.setAttribute("jobDes",jobDes );
				 request.setAttribute("reqSkill", reqSkill);
					 
			 }
          
          if(options.equalsIgnoreCase("searchjob")){
        	
        	     logger.info("In interceptor: Search");
        	     String searchT = request.getParameter("inputtext");
				 
				 String [] badCharacters = {"<",">","script","/",";","#","|"};
				 
				 for(int i=0;i<badCharacters.length;i++){
					 searchT= searchT.replaceAll(badCharacters[i], "");
					 searchT = searchT.trim();
					 
				 }
				 
				 request.setAttribute("searchT",searchT );
				 
			 }
		 }
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
