package com.full.helloappengine;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import PhoneBook.Contact;
import PhoneBook.PhoneBook;

@SuppressWarnings("serial")
public class CreateContact extends HttpServlet{
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
//		InputStream is = req.getInputStream();
		
		
		
		String contactString = req.getReader().readLine();
		
//		JsonObject jsonObject = (new JsonParser()).parse(contactString).getAsJsonObject();
		
		
		com.google.appengine.repackaged.org.json.JSONObject obj;
		try {
			obj = new com.google.appengine.repackaged.org.json.JSONObject(contactString);
	
		
		
			String name =  obj.getString("nameText");
			com.google.appengine.repackaged.org.json.JSONArray arrPhone = obj.getJSONArray("phonesText");
			com.google.appengine.repackaged.org.json.JSONArray arrMail = obj.getJSONArray("mailsText");
			
			
			Contact contact = new Contact(name);
			for(int i = 0; i < arrPhone.length(); i++)
				contact.addPhone(arrPhone.get(i).toString());
			for(int i = 0; i < arrMail.length(); i++)
				contact.addPhone(arrMail.get(i).toString());
			
			
			
				
			
			
			PhoneBook ph = PhoneBook.getPhoneBook();
			
			ph.addContact(contact);

			System.out.println("Contact: " + contact.toString());
			System.out.println("Contact: " + contact.getPhones().toString());
			System.out.println("Contact: " + contact.getEmails().toString());
			
			
			String bla  = req.getQueryString();
			
//			String lele = req.get
			
			System.out.println("bla:......." + name);
			
		
		
		
		
		
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	
	
	
	
	}

	
	
	
	
}
