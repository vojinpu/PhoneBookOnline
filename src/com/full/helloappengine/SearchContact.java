package com.full.helloappengine;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import PhoneBook.Contact;
import PhoneBook.PhoneBook;

public class SearchContact extends HttpServlet{
	
	
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		
		String query = req.getParameter("searchContact");
		
		
		PhoneBook ph = PhoneBook.getPhoneBook();
		
		ArrayList<Contact> contacts = ph.searchContacts(query);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String strJson = gson.toJson(contacts);
		
		resp.setContentType("text/html");
		resp.getWriter().println(strJson);
		System.out.println(strJson);
		
		
		
	}
	
	
	

}
