package com.naresh.joblender.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.naresh.joblender.DAO.JobsDAO;
import com.naresh.joblender.POJO.JobList;




@RunWith(MockitoJUnitRunner.class)
public class SearchJobTest {
	
	@Mock
    JobsDAO dao;
	
	 @Before
	    public void init() {
	        MockitoAnnotations.initMocks(this);
	    }
	 
	 @Test
	 public void searchByID() {
		  JobList job =new JobList("test", "testdec");
		    int id=1;
		
		try {	
		       
	        when(dao.getJob(id)).thenReturn(job);
	         
	        JobList result =(dao.getJob(id));
	       
	        assertEquals( job ,result);
	       
	        verify(dao, times(1)).getJob(id);
	        
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
		 
	 }


	 
	 @Test
	 public void searchByName() {
		 
		String jobName ="software";
		
		try {	
		       
	        when(dao.listJobname(jobName)).thenReturn(null);
	         
	        List<JobList> result =(dao.listJobname(jobName));
	       
	        assertEquals(null ,result);
	       
	        verify(dao, times(1)).listJobname(jobName);
	        
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
		 
	 }
	 
	
}
