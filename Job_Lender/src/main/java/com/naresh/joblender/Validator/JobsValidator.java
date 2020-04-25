package com.naresh.joblender.Validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.naresh.joblender.POJO.JobList;



public class JobsValidator implements Validator {
	
	private final Pattern pattern=Pattern.compile("[a-zA-Z0-9 ]+");
	private final Pattern patternSkill=Pattern.compile("[a-zA-Z0-9 ,#+]+");
	
	public boolean supports(Class<?> aClass) {
		return JobList.class.isAssignableFrom(aClass);
	}

	public void validate(Object obj, Errors errors) {
		JobList job = (JobList) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jobName", "error.invalid.job", "Job Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jobDesc", "error.invalid.job", "Job Description Required");
	
	
		try {
		
			if(!pattern.matcher(job.getJobName()).matches()) {
				errors.rejectValue("jobName","error.invalid.job","Job name contains only alphanumeric Character");
				}
			
			if(!pattern.matcher(job.getJobDesc()).matches()) {
				errors.rejectValue("jobDesc","error.invalid.job","Job description contains only alphanumeric Character");
				}
			
			if(!patternSkill.matcher(job.getReqSkill()).matches()) {
				errors.rejectValue("reqSkill","error.invalid.job","Skills must be in format X,Y,Z");
				}
			
		}catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}	
	
	}

}
