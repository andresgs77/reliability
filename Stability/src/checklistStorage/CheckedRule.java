package checklistStorage;

public class CheckedRule {
	
	private String item;
	private String check;
	private String type;
	
	public CheckedRule(String i, String c, String t){
		item=i;
		check=c;
		type=t;
	}
	
	public CheckedRule(String i, String c){
		item=i;
		check=c;
	}

	public String getItem() {
		return item;
	}
	
	public String getCheck() {
		return check;
	}
	
	public boolean isChecked(){
		if (check.equals("true")) return true;
		return false;
	}

	public String getType() {
		return type;
	}

}
