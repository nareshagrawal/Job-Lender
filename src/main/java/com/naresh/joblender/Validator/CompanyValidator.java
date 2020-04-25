package com.naresh.joblender.Validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.naresh.joblender.DAO.CompanyDAO;
import com.naresh.joblender.POJO.Company;


public class CompanyValidator implements Validator {

	private final Pattern pattern=Pattern.compile("[a-zA-Z0-9 ]+");
	
	public boolean supports(Class<?> aClass) {
		
		return Company.class.isAssignableFrom(aClass);
	}

	public void validate(Object obj, Errors errors) {
		Company comp = (Company) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyName", "error.invalid.comp", "Company Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyAddress", "error.invalid.comp", "Company Address Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyDescription", "error.invalid.comp", "Company Description Required");
		
		try {
			
			if(!pattern.matcher(comp.getCompanyName()).matches()) {
				errors.rejectValue("companyName","error.invalid.comp","Company name contains only alphanumeric Character");
				}
			
			if(!pattern.matcher(comp.getCompanyAddress()).matches()) {
				errors.rejectValue("companyAddress","error.invalid.comp","Company address contains only alphanumeric Character");
				}
			
			if(!pattern.matcher(comp.getCompanyDescription()).matches()) {
				errors.rejectValue("companyDescription","error.invalid.comp","Company description contains only alphanumeric Character");
				}
			CompanyDAO compDao = new CompanyDAO();
			String name = comp.getCompanyName();
			Company c= compDao.get(name);
			if (c != null){
				errors.rejectValue("companyName", "error.invalid.comp", "Company with name: "+name+" already registered");
			}
			
		}catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
}
