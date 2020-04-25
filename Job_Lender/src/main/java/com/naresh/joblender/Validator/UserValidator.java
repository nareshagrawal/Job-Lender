package com.naresh.joblender.Validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.naresh.joblender.DAO.UserDAO;
import com.naresh.joblender.Exception.UserException;
import com.naresh.joblender.POJO.User;


public class UserValidator implements Validator{
	
	private final Pattern pattern=Pattern.compile("[a-zA-Z ]+");
	private final Pattern patternPassword=Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{4,8}$");
	private final Pattern patternUserName=Pattern.compile("[a-zA-Z0-9 ]+");
    private final Pattern patternemail=Pattern.compile("[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
	
	public boolean supports(Class<?> aClass) {
		return User.class.isAssignableFrom(aClass);
	}

	
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.user", "First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.user", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.invalid.user", "User Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.invalid.email","Email Required");
		
		System.out.println("The Uname is  : " +user.getUsername());
		String name = user.getUsername();
		String email = user.getEmail();
		UserDAO userdao = new UserDAO();
		

		try {
			
			if(!pattern.matcher(user.getFirstName()).matches()) {
				errors.rejectValue("firstName","error.invalid.user","Name contains only Alphabet");
				}
			if(!pattern.matcher(user.getLastName()).matches()) {
				errors.rejectValue("lastName","error.invalid.user","Name contains only Alphabet");
				}
			if(!patternemail.matcher(user.getEmail()).matches()) {
				errors.rejectValue("email","error.invalid.employee","Invalid Email Format");
				}
			if(!patternUserName.matcher(user.getUsername()).matches()) {
				errors.rejectValue("username","error.invalid.user","User name contains only alphanumeric character");
				}
			if(!patternPassword.matcher(user.getPassword()).matches()) {
				errors.rejectValue("password","error.invalid.user","Password must be between 4-8 characters,\n must include at least one upper case letter, one lower case letter and one numeric digit");
				}
			
			System.out.println("The Uname inside try is  : " +user.getUsername());
			User u = userdao.getUserName(name);
			if (u != null){
				errors.rejectValue("username", "error.invalid.user", "username already taken");
			}
			
			User e = userdao.getByEmail(email);
			if (e !=null){
				errors.rejectValue("email", "error.invalid.email", "email address already exists");
			}
			
			
			
		} catch (UserException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception: " + e.getMessage());
			}
		
	}

}
