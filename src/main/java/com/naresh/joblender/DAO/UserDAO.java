package com.naresh.joblender.DAO;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.naresh.joblender.Exception.UserException;
import com.naresh.joblender.POJO.JobList;
import com.naresh.joblender.POJO.User;




public class UserDAO extends DAO {
	
	public User get(String username, String password) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);			
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + username, e);
		} finally {
            close();
        }
	}
	
	public User getUserName(String username) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where username = :username");
			q.setString("username", username);
				
			User user = (User) q.uniqueResult();
			commit();
			return user;
			
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + username, e);
		} finally {
            close();
        }
	}
	
	public User getUserID(long id) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where userID = :id");
			q.setLong("id",id);
				
			User user = (User) q.uniqueResult();
			commit();
			return user;
			
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get userID " + id, e);
		} finally {
            close();
        }
	}
	
	public User getByEmail(String email) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where email = :email");
			q.setString("email", email);
				
			User e = (User) q.uniqueResult();
			commit();
			return e;
			
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get email " + email, e);
		}finally {
            close();
        }
	}


	public User register(User u)
			throws UserException {
		try {
			begin();
			System.out.println("inside UserDAO");
             
			User user = new User(u.getUsername(),u.getPassword(),u.getRole());

			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			user.setEmail(u.getEmail());
			user.setRole(u.getRole());
			user.setCompany(u.getCompany());
			user.setStatus(u.getStatus());
		    getSession().save(user);
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		} finally {
            close();
        }
	}
	
	public List<User>getUsersComp(int id) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where company = :id");
			q.setInteger("id",id);
			List<User> user = q.list();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not delete user ", e);
		} finally {
            close();
        }
	}

	public void delete(long id) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where userID = :id");
			q.setLong("id",id);
			User u = (User) q.uniqueResult();
			getSession().delete(u);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not delete user ", e);
		} finally {
            close();
        }
	}
	
	public void update(User user, JobList job) throws UserException {
        try {

            begin();
            user.getJoblists().add(job);
            getSession().update(user);
    //        getSession().merge(user);
            commit();
            
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Could not update the user", e);
        } finally {
            close();
        }
    }
	
	public void remove (User user, JobList job) throws UserException {
        try {

            begin();
            user.getJoblists().remove(job);
            getSession().update(user);
//          getSession().merge(user);
            commit();
          
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Could not update the user", e);
        } finally {
            close();
        }
    }
	
	public void updateStatus(User user, String status) throws UserException {
        try {

            begin();
            user.setStatus(status);
            getSession().update(user);
    //        getSession().merge(user);
            commit();
            
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Could not update the user", e);
        } finally {
            close();
        }
    }

}
