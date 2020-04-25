package com.naresh.joblender.DAO;


import org.hibernate.HibernateException;
import org.hibernate.Query;


import com.naresh.joblender.Exception.EmployeeException;
import com.naresh.joblender.POJO.Employee;

public class EmployeeDAO extends DAO{

	
public EmployeeDAO(){
		
	}
  
public Employee registerEmployee(Employee e)throws EmployeeException {
		
				Employee employee = new Employee();
		try {
			
			begin();
		    employee.setFirstName(e.getFirstName());
		    employee.setLastName(e.getLastName());
		    employee.setPhoneNo(e.getPhoneNo());
		    employee.setEmail(e.getEmail());
		    employee.setSchool(e.getSchool());
		    employee.setDegree(e.getDegree());
		    employee.setMajor(e.getMajor());
		    employee.setGPA(e.getGPA());
		    employee.setUser(e.getUser());
		    employee.setResumeName(e.getResumeName());
		    employee.setCountry(e.getCountry());
		    employee.setAddress(e.getAddress());
		    employee.setCity(e.getCity());
		    employee.setZip(e.getZip());
		    employee.setLinkedin(e.getLinkedin());
		    employee.setSkills(e.getSkills());
		    employee.setAbout(e.getAbout());
		    getSession().save(employee);
		    commit();
		
			} catch (HibernateException error) {
					rollback();
					throw new EmployeeException("Exception while creating profile: " + error.getMessage());
			} finally {
					close();
			}
		return employee;
	}
	
public Employee getEmployee(long uID) throws EmployeeException {
		
		Employee employee = new Employee();
		
		try {
			begin();
			Query q = getSession().createQuery("from Employee where user = :uID");
			q.setLong("uID", uID);
			employee=(Employee) q.uniqueResult();
			commit();
		}catch (HibernateException error) {
			rollback();
			throw new EmployeeException("Exception while creating profile: " + error.getMessage());
		} finally {
	        close();
	    }
		
		return employee;
	}
	
public Employee updateEmployee(Employee employeeExit, Employee employeeNew, String fileName ) throws EmployeeException {
		
	
		try {
			begin();
			employeeExit.setPhoneNo(employeeNew.getPhoneNo());
			employeeExit.setSchool(employeeNew.getSchool());
			employeeExit.setDegree(employeeNew.getDegree());
			employeeExit.setMajor(employeeNew.getMajor());
			employeeExit.setGPA(employeeNew.getGPA());
			employeeExit.setResumeName(fileName);
			employeeExit.setCountry(employeeNew.getCountry());
			employeeExit.setAddress(employeeNew.getAddress());
			employeeExit.setCity(employeeNew.getCity());
			employeeExit.setZip(employeeNew.getZip());
			employeeExit.setLinkedin(employeeNew.getLinkedin());
			employeeExit.setSkills(employeeNew.getSkills());
			employeeExit.setAbout(employeeNew.getAbout());
			getSession().update(employeeExit);
			commit();
			
		}catch (HibernateException error) {
			rollback();
			throw new EmployeeException("Exception while creating profile: " + error.getMessage());
		} finally {
	        close();
	    }
		
		return employeeExit;
	}

	public void deleteEmployee(long uID) throws EmployeeException {
		
		try {
			Employee employee = new Employee();
			begin();
			Query q = getSession().createQuery("from Employee where user = :uID");
			q.setLong("uID", uID);
			employee=(Employee) q.uniqueResult();
			getSession().delete(employee);
			commit();
		}catch (HibernateException error) {
			rollback();
			throw new EmployeeException("Exception while deleting account: " + error.getMessage());
		} finally {
	        close();
	    }
		
		
	}
		
}

