package atom;

import java.util.Calendar;



public class AtomFeedTitileBuilder {
	
	public static String buildTitle(String roUri, Calendar fromDate, Calendar toDate) {
        String result = "Notifications for ";
        if (roUri == null) {
            result += "completeness and reliability in ROs";
        } else {
            result += roUri.toString();
        }
        if (fromDate != null || toDate != null) {
            result += "\nRange:\n";
            if (fromDate != null) {
                result += "\nfrom: " + fromDate.getTime().toString();
            }
            if (toDate != null) {
                result += "\nto: " + toDate.getTime().toString();
            }
        }
        return result;
    }

}
