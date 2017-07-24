package com.full.helloappengine;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import PhoneBook.Contact;
import PhoneBook.PhoneBook;

public class DeleteContact extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
		// TODO Auto-generated method stub

		
		String contactString = req.getParameter("deleteContact");

		
		
		int num = Integer.parseInt(contactString);
		
		System.out.println("Obrisan j e " + contactString);
		
		
		PhoneBook ph = PhoneBook.getPhoneBook();
		
		ph.deleteContact(num);
		
		
		
	
	}
	
	

}
