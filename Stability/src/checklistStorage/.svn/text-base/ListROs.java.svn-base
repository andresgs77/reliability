package checklistStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class ListROs {

	public ArrayList<String> getListROs() {
		String requestURL = "http://calatola.man.poznan.pl/rodl/sparql?query=Select%20?ro%20where%20%7B%0A?ro%20a%20%3Chttp://purl.org/wf4ever/ro%23ResearchObject%3E%0A%7D";
		System.out.println(requestURL);

		String response = "";
		BufferedReader rd = null;
		try {
			URL url = new URL(requestURL);
			URLConnection conn2 = url.openConnection();
			conn2.setRequestProperty("Accept", "application/rdf+xml");
			rd = new BufferedReader(new InputStreamReader(
					conn2.getInputStream()));

			String line;
			while ((line = rd.readLine()) != null) {
				// Process line...
				response = response + line + "\n";
			}
		} catch (Exception e) {
			System.out.println("Web request failed: "+requestURL);
			// Web request failed
		} finally {
			if (rd != null) {
				try {
					rd.close();
				} catch (IOException ex) {
					System.out.println("Problema al cerrar el objeto lector");
				}
			}
		}

		return (extractROs(response));
	}

	private ArrayList<String> extractROs(String response) {
		ArrayList<String> ros = new ArrayList<String>();
		
		String ro=null;
		if (response != null) {
			Scanner scanner = new Scanner(response);
			String line = null;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if (line.contains("<uri>")){
					ro=line.substring(line.indexOf("<uri>")+5, line.indexOf("</uri>"));
					ros.add(ro);
				}
				
			}
		}

		return ros;
	}

}
