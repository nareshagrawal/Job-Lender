package com.naresh.joblender.PDFview;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;

import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import com.naresh.joblender.POJO.Employee;

public class myPdfview extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	
				Employee employee = (Employee) model.get("viewProfile");
				
				Paragraph p = new Paragraph("Candidate Profile",FontFactory.getFont(FontFactory.HELVETICA, 25, Font.BOLDITALIC));
				p.setAlignment("center");
				p.setSpacingAfter(3);
				
				LineSeparator lineBreak = new LineSeparator();
				
				Paragraph p1 = new Paragraph("Personal Details",FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLDITALIC));
				p1.setAlignment("left");
				p1.setSpacingBefore(5);
				//p1.setSpacingAfter(2);
					Table table = new Table(2); 
					table.setAlignment("center");
					table.setPadding(5);
					table.setSpacing(2);
						 table.addCell("First Name");
						 table.addCell(employee.getFirstName());
						 table.addCell("Last Name");
						 table.addCell(employee.getLastName());
						 table.addCell("Email ID");
						 table.addCell(employee.getEmail());
						 table.addCell("About Candidate");
						 table.addCell(employee.getAbout());

				Paragraph p2 = new Paragraph("Academic Details",FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLDITALIC));
				p2.setAlignment("left");
				p2.setSpacingBefore(5);
				//p2.setSpacingAfter(2);
					Table table1 = new Table(2); 
					table1.setAlignment("center");
					table1.setPadding(5);
					table1.setSpacing(2);
						 table1.addCell("School");
						 table1.addCell(employee.getSchool());
						 table1.addCell("Degree");
						 table1.addCell(employee.getDegree());
						 table1.addCell("Major");
						 table1.addCell(employee.getMajor());
						 table1.addCell("GPA");
						 table1.addCell(employee.getGPA());
						 table1.addCell("Skills");
						 table1.addCell(employee.getSkills());
						 table1.addCell("Linkedin");
						 table1.addCell(employee.getLinkedin());
						 
				 
				Paragraph p3 = new Paragraph("Contact Details",FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLDITALIC));
				p3.setAlignment("left");
				p3.setSpacingBefore(5);
				//p3.setSpacingAfter(2);
					Table table2 = new Table(2); 
					table2.setAlignment("center");
					table2.setPadding(5);
					table2.setSpacing(2);
						 table2.addCell("Phone Number");
						 table2.addCell(employee.getPhoneNo());
						 table2.addCell("Address");
						 table2.addCell(employee.getAddress());
						 table2.addCell("City");
						 table2.addCell(employee.getCity());
						 table2.addCell("Zip Code");
						 table2.addCell(employee.getZip());
						 table2.addCell("Country");
						 table2.addCell(employee.getCountry());
						 
				 
				 document.add(p);
				 document.add(lineBreak);
				 document.add(p1);
				 document.add(table);
				 document.add(p2);
				 document.add(table1);
				 document.add(p3);
				 document.add(table2);
				 document.close();
				
		
	}

}
