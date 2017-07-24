package PhoneBook;
import java.util.ArrayList;




public class Contact {

	private String name;
	private ArrayList <String> phones;
	private ArrayList <String> emails;
	private int key;
	
	public Contact(String name){
		
		this.name = name;
		phones = new ArrayList<>();
		emails = new ArrayList<>();
		
	}
	
	public void addPhone(String phone){
		
		phones.add(phone);
		
	}
	
	public void addEmail(String mail){
		
		emails.add(mail);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public boolean Contains(String query){

		
		if(name.toUpperCase().contains(query.toUpperCase()))
			return true;
		
		for(String num : phones)
			if(num.contains(query))
				return true;
		
		for(String help : emails)
			if(help.toUpperCase().contains(query.toUpperCase()))
				return true;
		
		
		
		
		
		return false;
	}

	public String getName() {
		return name;
	}

	public ArrayList<String> getPhones() {
		return phones;
	}

	public ArrayList<String> getEmails() {
		return emails;
	}

	public void editName(String str) {
		
		name = str;
		
	}

	public void deletePhone(int i) {
		
		phones.remove(i);
		
	}
	
	public void deleteEmails(int i) {
		
		emails.remove(i);
		
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	
	
	
}
