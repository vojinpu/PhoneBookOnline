package com.full.helloappengine;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import PhoneBook.Contact;
import PhoneBook.PhoneBook;

@SuppressWarnings("serial")
public class ShowContacts extends HttpServlet {


	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		PhoneBook phoneBook = PhoneBook.getPhoneBook();
		ArrayList<Contact> contacts = phoneBook.getAllContacts();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String strJson = gson.toJson(contacts);
		
		resp.setContentType("text/html");
		resp.getWriter().println(strJson);
		System.out.println(strJson);
		

	}
	
}
