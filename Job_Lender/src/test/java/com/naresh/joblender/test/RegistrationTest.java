package com.naresh.joblender.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.naresh.joblender.DAO.CompanyDAO;
import com.naresh.joblender.DAO.JobsDAO;
import com.naresh.joblender.DAO.UserDAO;
import com.naresh.joblender.POJO.Company;
import com.naresh.joblender.POJO.JobList;
import com.naresh.joblender.POJO.User;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationTest {

	@Mock
    UserDAO userDao;
	
	@Mock
    CompanyDAO compDao;
	
	@Mock
    JobsDAO jobsDao;
	
	 @Before
	    public void init() {
	        MockitoAnnotations.initMocks(this);
	    }
	 
	 @Test
	 public void registerUser() {
		  
		 User user = new User("test","test123","employee");
		 
		 try {	
		       
		        when(userDao.register(user)).thenReturn(user);
		         
		        User result =(userDao.register(user));
		       
		        assertEquals(user ,result);
		       
		        verify(userDao, times(1)).register(user);
		        
		    	}catch(Exception e) {
		    		e.printStackTrace();
		    	}
		  }
	 
	 @Test
	 public void registerComp() {
		  
		 Company comp = new Company();
		 
		 try {	
		       
		        when(compDao.register(comp)).thenReturn(comp);
		         
		        Company result =(compDao.register(comp));
		       
		        assertEquals(comp ,result);
		       
		        verify(compDao, times(1)).register(comp);
		        
		    	}catch(Exception e) {
		    		e.printStackTrace();
		    	}
	      }
	 
	 @Test
	 public void registerJob() {
		  
		 JobList job = new JobList("test", "jobdesc");
		 
		 try {	
		       
		        when(jobsDao.register(job)).thenReturn(job);
		         
		        JobList result =(jobsDao.register(job));
		       
		        assertEquals(job ,result);
		       
		        verify(jobsDao, times(1)).register(job);
		        
		    	}catch(Exception e) {
		    		e.printStackTrace();
		    	}
	     }
	
	
	
}
