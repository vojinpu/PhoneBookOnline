package PhoneBook;
public enum Controls {

	ADD(1), 
	SEARCH(3), 
	DELETE(4), 
	SHOWS(5),
	EXIT(0),
	NEUTRAL(-1);
	
	public static final Controls fromString(String action){
		return Enum.valueOf(Controls.class, action.toUpperCase());
	}
	
	private int value;
	
	Controls(int num){
		value = num;
	}
	
	int getValue(){
		return value;
	
	}
	

//	public static Controls getEnum(String num) {
//
//		if (num.equals("1"))
//			return Controls.ADD;
//
//		if (num.equals("2"))
//			return Controls.UPDATE;
//
//		if (num.equals("3"))
//			return Controls.SEARCH;
//
//		if (num.equals("4"))
//			return Controls.DELETE;
//
//		if (num.equals("5"))
//			return Controls.SHOWS;
//
//		return Controls.NEUTRAL;
//
//	}
}
