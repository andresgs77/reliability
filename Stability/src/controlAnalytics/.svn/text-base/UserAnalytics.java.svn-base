package controlAnalytics;

public class UserAnalytics {
	
	private String user;
	private double totalChanges;
	private int totalAdditions;
	private int totalModifications;
	private int totalRemovals;
	
	private double relativeAdditions;
	private double relativeModifications;
	private double relativeRemovals;
	
	private double userChangesImpact;
	private double userSnapshots;
	private int totalSnapshots;
	private double userSnapshotsImpact;
	
	public UserAnalytics(String user, int snapshots){
		this.user=user;
		totalSnapshots=snapshots;
		userSnapshots=0;
	}
	
	private void updateAdditions(int num){
		totalAdditions=totalAdditions+num;
	}
	
	private void updateModifications(int num){
		totalModifications=totalModifications+num;
	}
	
	private void updateRemovals(int num){
		totalRemovals=totalRemovals+num;
	}
	

	public void update(IsolatedAnalytics s) {
		updateAdditions(s.getTotalAdditions());
		updateModifications(s.getTotalModifications());
		updateRemovals(s.getTotalRemovals());
		totalChanges=totalAdditions+totalModifications+totalRemovals;
		if (totalChanges!=0){
			relativeAdditions=totalAdditions/totalChanges;
			relativeModifications=totalModifications/totalChanges;
			relativeRemovals=totalRemovals/totalChanges;
		}
		userChangesImpact=userChangesImpact+s.getQuantityImpact();
		if (totalSnapshots!=0){
			userSnapshots++;
			userSnapshotsImpact=userSnapshots/totalSnapshots;
		}
	}
	
	/////// GETTERS //////////////
	public String getUser(){
		return user;
	}

	public double getTotalChanges() {
		return totalChanges;
	}

	public int getTotalAdditions() {
		return totalAdditions;
	}

	public int getTotalModifications() {
		return totalModifications;
	}

	public int getTotalRemovals() {
		return totalRemovals;
	}

	public double getRelativeAdditions() {
		return relativeAdditions;
	}

	public double getRelativeModifications() {
		return relativeModifications;
	}

	public double getRelativeRemovals() {
		return relativeRemovals;
	}

	public double getUserChangesImpact() {
		return userChangesImpact;
	}

	public double getUserSnapshotsImpact() {
		return userSnapshotsImpact;
	}
	////////////////////////////////

}
