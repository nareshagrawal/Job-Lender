package com.naresh.joblender.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;

import com.naresh.joblender.Exception.JobsException;
import com.naresh.joblender.POJO.Company;
import com.naresh.joblender.POJO.JobList;




public class JobsDAO extends DAO {
	
public JobsDAO(){
		
	}
		
		
	public JobList register(JobList j) throws JobsException{
			
			try {
	            begin();
	            JobList job = new JobList(j.getJobName(),j.getJobDesc());
	            job.setReqSkill(j.getReqSkill());
	            job.setCompany(j.getCompany());
	            job.setPostedName(j.getPostedName());
	            job.setPostedID(j.getPostedID());
	            getSession().save(job);
	            commit();
	            return job;
	        } catch (HibernateException e) {
	            rollback();
	            throw new JobsException("Exception while creating compnay: " + e.getMessage());
	        } finally {
	            close();
	        }
		}

	public List<JobList> list() throws JobsException {
	    try {
	        begin();
	        Query q = getSession().createQuery("from JobList");
	        
	        List<JobList> list = q.list();
	        commit();
	        return list;
	    } catch (HibernateException e) {
	        rollback();
	        throw new JobsException("Could not list the Jobs", e);
	    }finally {
            close();
        }
	}

	public List<JobList> listCompany(Company company) throws JobsException {
	    try {
	    	begin();
	    	begin();
	        Query q = getSession().createQuery("from JobList where company= :comp");
	        q.setInteger("comp", company.getCompanyId());
	        List<JobList> list = q.list();
	        commit();
	        return list;
	    } catch (HibernateException e) {
	        rollback();
	        throw new JobsException("Could not list the Jobs", e);
	    }finally {
            close();
        }
	}
	
	
	public List<JobList> listJobname(String name) throws JobsException {
	    try {
	        begin();
	        Query q = getSession().createQuery("from JobList where lower(jobName) LIKE lower(:jobname)");
	        q.setString("jobname", "%"+name+"%");
	        List<JobList> list = q.list();
	        commit();
	        return list;
	    } catch (HibernateException e) {
	        rollback();
	        throw new JobsException("Could not list the Jobs with job name", e);
	    }finally {
            close();
        }
	}
	
	
	public JobList getJob(int id) throws JobsException{
		try{
			begin();
			//Query q = getSession().createQuery("from JobList where jobID= :id");
			//q.setInteger("id", id);
			//JobList job = (JobList)q.uniqueResult();
			Criteria crit = getSession().createCriteria(JobList.class);
			crit.add(Restrictions.eq("jobID", id));
			JobList job = (JobList) crit.uniqueResult();
			commit();
			return job;
		} catch(HibernateException e) {
			rollback();
			throw new JobsException("could not find the job",e);
		}finally {
            close();
        }
	}
	
	
	public void deleteUser(int jobId) throws JobsException {
		
		try {
			begin();
			System.out.println("Delete jobID:"+jobId);
			SQLQuery q = getSession().createSQLQuery("delete from user_job_table where jobID =:id");
			q.setInteger("id",jobId);
			q.executeUpdate();
			 commit();
		  } catch (HibernateException e) {
			rollback();
			System.out.println(e);
			throw new JobsException("cannot delete user for job", e.getCause());
		}finally {
            close();
        }
	
	 }
	
public void deleteApplied(long userId,int jobId) throws JobsException {
		
		try {
			begin();
			System.out.println("Delete jobID:"+jobId);
			SQLQuery q = getSession().createSQLQuery("delete from user_job_table where jobID =:jobid and userID=:userid");
			q.setInteger("jobid",jobId);
			q.setLong("userid", userId);
			q.executeUpdate();
			 commit();
		  } catch (HibernateException e) {
			rollback();
			System.out.println(e);
			throw new JobsException("cannot delete user for the job", e.getCause());
		}finally {
            close();
        }
	
	 }
	


	public int deleteJob(int jobID) throws JobsException {
		int result=0;
		try {
			begin();
			Query q = getSession().createQuery("from JobList where jobID=:id");
			q.setInteger("id", jobID);
			JobList job1 = (JobList)q.uniqueResult();
			getSession().delete(job1);
			commit();
			result=1;
		} catch (HibernateException e) {
			rollback();
			
			throw new JobsException("cannot delete job", e.getCause());
		}finally {
            close();
        }
		return result;
	 }

	public void update(JobList job) throws JobsException {
	    try {
	        begin();
	        getSession().update(job);
	        commit();
	        System.out.println("Updated the job");
	    } catch (HibernateException e) {
	        rollback();
	        throw new JobsException("Could not update the job", e);
	    }finally {
            close();
        }
	}


}
