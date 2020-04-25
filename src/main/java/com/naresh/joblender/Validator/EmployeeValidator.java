package com.naresh.joblender.Validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.naresh.joblender.POJO.Employee;



public class EmployeeValidator implements Validator {
	
	private final Pattern pattern=Pattern.compile("[a-zA-Z ]+");
	private final Pattern patternPhone=Pattern.compile("[1-9][0-9]{9}");
	private final Pattern patternZip=Pattern.compile("[0-9]{5}");
	private final Pattern patternemail=Pattern.compile("[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
	private final Pattern patternGPA=Pattern.compile("[0-9][.][0-9]");
	private final Pattern patternAddress=Pattern.compile("[a-zA-Z0-9 ,]+");
	private final Pattern patternSkills=Pattern.compile("[a-zA-Z0-9 ,+]+");
	private final Pattern patternLink=Pattern.compile("[a-zA-Z0-9 \\:\\.\\-\\_\\/\\?]+");
	
	
	
	public boolean supports(Class<?> aClass) {
		return Employee.class.isAssignableFrom(aClass);
		
	}

	public void validate(Object obj, Errors errors) {
		
		Employee employee = (Employee) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.employee", "First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.employee", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNo", "error.invalid.employee", "Phone number Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.invalid.employee", "Email Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "school", "error.invalid.employee", "School Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "degree", "error.invalid.employee", "Degree Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "major", "error.invalid.employee", "Major Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "GPA", "error.invalid.employee", "GPA Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "error.invalid.employee", "Country Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "error.invalid.employee", "Addresss Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "error.invalid.employee", "City Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip", "error.invalid.employee", "Zip Code Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "linkedin", "error.invalid.employee", "Linkedin Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "skills", "error.invalid.employee", "Skills Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "about", "error.invalid.employee", "About yourself Required");
		try {
	
	if(!pattern.matcher(employee.getFirstName()).matches()) {
		errors.rejectValue("firstName","error.invalid.employee","Name contains only Alphabet");
		}
	if(!pattern.matcher(employee.getLastName()).matches()) {
		errors.rejectValue("lastName","error.invalid.employee","Name contains only Alphabet");
		}
	if(!patternPhone.matcher(employee.getPhoneNo()).matches()) {
		errors.rejectValue("phoneNo","error.invalid.employee","Phone number must contain 10 digit");
		}
	
	if(!patternemail.matcher(employee.getEmail()).matches()) {
		errors.rejectValue("email","error.invalid.employee","Invalid Email Format");
		}
	if(!pattern.matcher(employee.getSchool()).matches()) {
		errors.rejectValue("school","error.invalid.employee","School Name contains ony Alphabet");
		}
	if(!pattern.matcher(employee.getMajor()).matches()) {
		errors.rejectValue("major","error.invalid.employee"," Major contains only Alphabet");
		}
	if(!pattern.matcher(employee.getDegree()).matches()) {
		errors.rejectValue("degree","error.invalid.employee","Degree contains only Alphabet");
		}
	if(!patternGPA.matcher(employee.getGPA()).matches()) {
		errors.rejectValue("GPA","error.invalid.employee","GPA must in X.Y format");
		}
	if(!pattern.matcher(employee.getCountry()).matches()) {
		errors.rejectValue("country","error.invalid.employee","Country Name contains only Alphabet");
		}
	if(!patternAddress.matcher(employee.getAddress()).matches()) {
		errors.rejectValue("address","error.invalid.employee","Address contains only Alphabet & ','");
		}
	if(!pattern.matcher(employee.getCity()).matches()) {
		errors.rejectValue("city","error.invalid.employee","City Name contains only Alphabet");
		}
	
	if(!patternZip.matcher(employee.getZip()).matches()) {
		errors.rejectValue("zip","error.invalid.employee","Zip Code contains must be of 5 digit");
		}
	if(!patternLink.matcher(employee.getLinkedin()).matches()) {
		errors.rejectValue("linkedin","error.invalid.employee","Invalid format");
		}
	if(!patternSkills.matcher(employee.getSkills()).matches()) {
		errors.rejectValue("skills","error.invalid.employee","Skills must be in format X,Y,Z");
		}
	if(!patternAddress.matcher(employee.getAbout()).matches()) {
		errors.rejectValue("about","error.invalid.employee","About Yourself contains only alphanumeric character");
		}
	
	}catch (Exception e) {
		System.out.println("Exception: " + e.getMessage());
	}
}

}
