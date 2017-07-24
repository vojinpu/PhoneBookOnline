package PhoneBook;
import java.io.Console;
import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.rmi.CORBA.Util;


public class PhoneBook {

	private HashMap<Integer, Contact> contacts;
	private Scanner sc;
	
	
	private static PhoneBook phoneBook;
	
	public static PhoneBook getPhoneBook() throws IOException{
		
		if(phoneBook == null)
			phoneBook = new PhoneBook();
			
		
		return phoneBook;
		
		
	}

	private PhoneBook() throws IOException {

		String badValue = "incorrect value";

		sc = new Scanner(System.in);
		String num;
		String str = "";

		contacts = new HashMap<>();
		
		
		
		/////TEST
		
		
		Contact contact1 = new Contact("Vojin");
		contact1.addPhone("0633625817");
		contact1.addEmail("vojin96bg@gmail.com");
		
		Contact contact2 = new Contact("Vanja");
		contact2.addPhone("0661829294");
		contact2.addEmail("vanjaaaaa@gmail.com");
		contact2.addEmail("123@prc.com");
		
		
		int br;
		contacts.put(br = contact1.hashCode() % 99 + 1, contact1);
		contact1.setKey(br);
		contacts.put(br = contact2.hashCode() % 99 + 1, contact2);
		contact2.setKey(br);
		
		
		
		
		
		///////
//
//		Enum state = Controls.NEUTRAL;
//
//		num = "-1";
//
//		while (true) {
//
//			System.out.println("Press a command:");
//			System.out.println("1. Create contact");
//			System.out.println("3. Search contacts");
//			System.out.println("4. Delete contact");
//			System.out.println("5. Show All contacts");
//			System.out.println();
//			System.out.println("0. Exit ");
//
//			num = sc.nextLine();
//			
////			int numInt;
//			Controls option = Controls.values()[getEnumPosition(Integer.parseInt(num))];
//			System.out.println(option);
//
//			clearScreen();
//			
//			switch (option) {
//            case  ADD : addContact();
//                  break;
//            case SEARCH: searchContact();
//            	break;
//            case DELETE: deleteContact();
//            	break;
//            case SHOWS: showAllContacts();
//            	break;
//            case EXIT: return;
//            
//            case NEUTRAL: System.out.println("Wrong input");
//            	break;
//            	
//			}
//
//
//			state = Controls.NEUTRAL;
//
//		}
	}

	public int getEnumPosition(int num){
		
		int j = 0;
		for(Controls i : Controls.values()){
			
			if(i.getValue() == num)
				return j;
		
			j++;
		}
		
		return 5;
		
		
	}
	
	private void deleteContact() {

		while (true) {
			showAllContacts();
			System.out.println("Type the number of contact that you want to delete.(only number)");
			System.out.println("-1 for every");
			System.out.println();
			System.out.println("0 For Cancel");

			if (sc.hasNextInt() == false) {

				clearScreen();
				sc.nextLine();
				System.out.println("Contact with that number doesn't exists.");
				System.out.println();
				showAllContacts();
				continue;

			}

			int numPom;
			numPom = sc.nextInt();

			if (numPom == -1) {

				contacts = new HashMap<>();
				break;

			}

			if (numPom == 0)
				break;

			else {

				if (contacts.get(numPom) != null) {
					contacts.remove(numPom);
					clearScreen();
				} else {
					clearScreen();
					System.out.println("Contact with that number doesn't exists.");
					continue;

				}

			}

		}

	}

	private void searchContact() {

		System.out.println("Write the number or name which you want to search");
		System.out.println();
		System.out.println("0 For Cancel");

		String query = sc.nextLine();

		clearScreen();

		showQueryRezults(query);

		while (true) {

			System.out.println("0 For Cancel");
			System.out.println("1 for search again");
			System.out.println("Enter the number of contact to see it");

			String numHelp;

			numHelp = sc.nextLine();

			if (numHelp.equals("0"))
				break;

			if (numHelp.equals("1")) {

				query = sc.nextLine();

				clearScreen();

				showQueryRezults(query);

			}

			else
				showContact(numHelp);

		}

	}

	private void addContact() {

		String str;

		System.out.println("Please insert the name of the contact (no more than 30 characters)");
		System.out.println();
		System.out.println("0 for Cancel");
		str = sc.nextLine();
		clearScreen();

		// Exit Insert
		if (str.equals("0"))
			return;

		// Insert Again
		if (str.length() < 0 || str.length() > 35) {
			System.out.println("Incorect value");
			addContact();

		}

		Contact contact = new Contact(str);

		String numPom = "-1";

		while (true) {

			System.out.println("1 For Adding Numer");
			System.out.println("2 For Adding e-mail");
			System.out.println("3 for Save in Contacts");
			System.out.println();
			System.out.println("0 For Cancel");

			numPom = sc.nextLine();
			clearScreen();

			if (numPom.equals("3")) {
				System.out.println(contact.toString());
				// 0 is reserved for Exit
				contacts.put(contact.hashCode() % 99 + 1, contact);
				clearScreen();
				System.out.println("Contact successfully added");
				System.out.println();
			}

			// saved or leaving
			if (numPom.equals("0") || numPom.equals("3"))
				break;

			// something should be inserted
			System.out.println("Please inser it(no more than 30 characters)");

			clearScreen();

			if (numPom.equals("1"))
				addNumber(contact);

			if (numPom.equals("2"))
				addEmail(contact);

		}

	}

	private void showContact(String numHelp) {

		int key = Integer.parseInt(numHelp);

		Contact contact = null;

		if (contacts.get(key) == null) {

			clearScreen();
			System.out.println("Contact with that number doesn't exist.");
			System.out.println();
			return;
		}

		else
			contact = contacts.get(key);

		// Print Contact
		clearScreen();

		System.out.println();
		System.out.println("Name: " + contact.getName());

		System.out.println("Phones: ");
		if (contact.getPhones().size() == 0)
			System.out.println("NONE");

		else
			for (String str : contact.getPhones())
				System.out.println(str);

		System.out.println("E-Mails: ");
		if (contact.getEmails().size() == 0)
			System.out.println("NONE");

		else
			for (String str : contact.getEmails())
				System.out.println(str);

		System.out.println();

		System.out.println("0 for Back");
		System.out.println("1 for Edit");

		sc = new Scanner(System.in);
		String str = sc.nextLine();
		clearScreen();

		if (str.equals("0"))
			return;

		if (str.equals("1"))
			editContact(contact);

		
			showContact(numHelp);

	}

	private void editContact(Contact contact) {

		System.out.println("1 for Name");
		System.out.println("2 Add Number");
		System.out.println("3 Delete Number");
		System.out.println("4 Add E-Mail");
		System.out.println("5 Delete E-Mail");
		System.out.println();
		System.out.println("0 for Exit");

		sc = new Scanner(System.in);
		String str = sc.nextLine();
		clearScreen();

		if (str.equals("0"))
			return;

		if (str.equals("1")) {
			clearScreen();
			System.out.println("Please insert new Name");

			str = sc.nextLine();

			contact.editName(str);

		}

		if (str.equals("2")) {

			addNumber(contact);

		}

		if (str.equals("3")) {

			deleteNumber(contact);

		}

		if (str.equals("4")) {

			addEmail(contact);

		}

		if (str.equals("5")) {

			deleteEmail(contact);

		}

		if (str.equals("0"))
			return;
		
		clearScreen();


	}

	private void deleteNumber(Contact contact) {

		clearScreen();
		int counter = 1;

		System.out.println("Phones: ");
		if (contact.getPhones().size() == 0)
			System.out.println("This person doesn't have any number");

		else
			for (String str : contact.getPhones())
				System.out.println((counter++) + ". " + str);

		System.out.println("Insert the serial number to be deleted");
		String sn = sc.nextLine();

		// ADD CHECKING IS IT A NUMBER

		int num = Integer.parseInt(sn);

		if (num < 0 || num > contact.getPhones().size())
			deleteNumber(contact);

		else
			contact.deletePhone(num - 1);

	}

	private void deleteEmail(Contact contact) {

		clearScreen();
		int counter = 1;

		System.out.println("Emails: ");
		if (contact.getEmails().size() == 0)
			System.out.println("This person doesn't have any mail");

		else
			for (String str : contact.getEmails())
				System.out.println((counter++) + ". " + str);

		System.out.println("Insert the serial number to be deleted");
		String sn = sc.nextLine();

		// ADD CHECKING IS IT A NUMBER

		int num = Integer.parseInt(sn);

		if (num < 0 || num > contact.getEmails().size())
			deleteEmail(contact);

		else
			contact.deleteEmails(num - 1);

	}

	private void addEmail(Contact contact) {

		clearScreen();
		System.out.println("Please insert the E-mail");
		String email = sc.nextLine();

		if (email.length() == 0 || email.length() > 35 || !isValidEmail(email))
			addEmail(contact);

		else {
			contact.addEmail(email);
			clearScreen();
		}

	}

	private void addNumber(Contact contact) {

		clearScreen();
		System.out.println("Please insert the number");
		String phone = sc.nextLine();

		if (phone.length() < 3 || phone.length() > 35 || !isValidPhoneNumber(phone))
			addNumber(contact);

		else {
			contact.addPhone(phone);
			clearScreen();
		}

	}

	public boolean isPhoneNumber(String phone) {

		for (int i = 0; i < phone.length(); i++) {
			char c = phone.charAt(i);

			if ((c < '0' || c > '9') && c != '+')
				return false;

		}

		return true;
	}

	private void showQueryRezults(String query) {

		boolean empty = true;
		for (int key : contacts.keySet()) {

			if (contacts.get(key).Contains(query)) {
				System.out.println(key + ". " + contacts.get(key).toString());
				empty = false;
			}

		}

		if (empty)
			System.out.println("Sorry, but you don't have contact with that parameters.");

		System.out.println();

	}

	private void clearScreen() {

		for (int i = 0; i < 50; i++)
			System.out.println();

	}

	private void showAllContacts() {

		if (contacts.keySet().size() == 0) {

			System.out.println("You don't have any contact inserted yet.");
			System.out.println();
			return;

		}

		System.out.println("Contacts: ");

		for (int key : contacts.keySet()) {

			System.out.println(key + ". " + contacts.get(key).toString());

		}

		System.out.println();

	}
	
	public ArrayList<Contact> getAllContacts(){
		
		ArrayList<Contact> cont = new ArrayList<>();
		
		for (int key : contacts.keySet()) {

		cont.add(contacts.get(key));

		}
		
		return cont;
		
	}

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public static boolean isValidEmail(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

	private static boolean isValidPhoneNumber(String phoneNo) {
		// validate phone numbers of format "1234567890"
		if (phoneNo.matches("\\d{10}"))
			return true;
		// validating phone number with -, . or spaces
		else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
			return true;
		// validating phone number with extension length from 3 to 5
		else if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
			return true;
		// validating phone number where area code is in braces ()
		else if (phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))
			return true;
		// return false if nothing matches the input
		else
			return false;

	}
	
	
	public void addContact(Contact ct){
		
		int num;
		contacts.put(num = ct.hashCode()%99+1, ct);
		ct.setKey(num);
		
	}
	
	public ArrayList<Contact> searchContacts(String query){
		
			ArrayList<Contact> contactsRez = new ArrayList<>();

			boolean empty = true;
			for (int key : contacts.keySet()) {

				if (contacts.get(key).Contains(query)) {
					contactsRez.add(contacts.get(key));
				}

			}



			return contactsRez;

		
	}
	
	public void deleteContact(int key){
		
		
		contacts.remove(key);
		
	}
	
	
	public static void main(String[] args) throws IOException {
		
		new PhoneBook();
		
		
		
		
		
	}

	

}
