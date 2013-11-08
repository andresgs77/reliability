package control;


public class SnapshotEval {
	
	private String uri;
	private double value;
	private String date;
	private String user;
	
	public SnapshotEval(String uri){
		this.uri=uri;
	}

	public String getUri() {
		return uri;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setUser(String author) {
		this.user=author;
		
	}
	
	public String getUser() {
		return user;	
	}

}
