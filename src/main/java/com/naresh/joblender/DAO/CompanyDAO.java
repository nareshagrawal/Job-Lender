package com.naresh.joblender.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.naresh.joblender.Exception.CompanyException;
import com.naresh.joblender.Exception.JobsException;
import com.naresh.joblender.Exception.UserException;
import com.naresh.joblender.POJO.Company;




public class CompanyDAO  extends DAO {
	
public CompanyDAO(){
		
	}
	
	public List<Company> list() throws CompanyException {
        try {
            begin();
            Query q = getSession().createQuery("from Company");
            List<Company> list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new CompanyException("Could not list the companies", e);
        } finally {
            close();
        }
    }
	
	
	public Company register(Company c) throws CompanyException{
			
		try {
            begin();
            Company comp = new Company(c.getCompanyName(),c.getCompanyAddress(),c.getCompanyDescription());
            getSession().save(comp);
            commit();
            return comp;
        } catch (HibernateException e) {
            rollback();
            throw new CompanyException("Exception while creating compnay: " + e.getMessage());
        }finally {
            close();
        }
	}
	
	public Company get(String name) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Company where companyName= :companyName");
			q.setString("companyName", name);		
			Company comp = (Company) q.uniqueResult();
			commit();
			return comp;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get company " + name, e);
		}finally {
            close();
        }
	}
	
	public Company getById(int id) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Company where companyId= :compId");
			q.setInteger("compId", id);		
			Company comp = (Company) q.uniqueResult();
			commit();
			return comp;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get company " + id, e);
		}finally {
            close();
        }
	}
	
	public void delete(Company comp) throws CompanyException{
		try {
			begin();
			getSession().delete(comp);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new CompanyException("Could not delete company " + comp.getCompanyId(), e);
		}finally {
            close();
        }
	}
	
	public void update(Company comp) throws JobsException {
	    try {
	        begin();
	        getSession().update(comp);
	        commit();
	     
	    } catch (HibernateException e) {
	        rollback();
	        throw new JobsException("Could not update the company", e);
	    }finally {
            close();
        }
	}


}
