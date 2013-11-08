package control;

public class UserBehaviour {
	
	private String user;
	private double numTotal;
	private double numGood;
	private double numBad;

	private double probGood;
	private double probBad;
	private double positiveImpact;
	private double negativeImpact;
	
	
	public UserBehaviour(String user){
		this.user=user;
		numGood=0;
		numBad=0;
		numTotal=0;
	}
	
	public void updateNums(double value){
		if (value<0)
			numGood++;
		else if (value>0)
			numBad++;
		
		numTotal++;
	}
	
	public void updateStatistics(double numSnapshots){
		//restamos uno porque sobra el primero de la lista.
		numSnapshots--;
		if (numTotal>0 && numSnapshots>0){
			probGood=numGood/numTotal;
			probBad=numBad/numTotal;
			positiveImpact=numGood/numSnapshots;
			negativeImpact=numBad/numSnapshots;
		}
	}

	public String getUser() {
		return user;
	}
	
	public double getProbGood() {
		return probGood;
	}

	public double getProbBad() {
		return probBad;
	}

	public double getPositiveImpact() {
		return positiveImpact;
	}

	public double getNegativeImpact() {
		return negativeImpact;
	}

}
