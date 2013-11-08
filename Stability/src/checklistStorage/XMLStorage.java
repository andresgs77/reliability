package checklistStorage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class XMLStorage {

	public XMLStorage(File file, String eval, String ro) {
		String result = "<trace>\n";
		result = result + "<ro>" + ro + "</ro>\n";
		result = result + "<evaluations>\n" + eval + "</evaluations>\n</trace>";
		try {
			Writer output = null;
			output = new BufferedWriter(new FileWriter(file));
			output.write(result);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public XMLStorage() {
		// TODO Auto-generated constructor stub
	}

	public void add(File f, String xml) {
		String content = "";
		try {
			String output = new Scanner(f).useDelimiter("\\Z").next();
			content = output;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String lastRules = content.substring(
				content.lastIndexOf("<rules>") + 1,
				content.lastIndexOf("</rules>"));
		String newRules = xml.substring(xml.lastIndexOf("<rules>") + 1,
				xml.lastIndexOf("</rules>"));

		if (!lastRules.equals(newRules)) {
			int pos = content.lastIndexOf("</evaluations>");
			content = content.substring(0, pos) + xml
					+ "</evaluations>\n</trace>";

			try {
				Writer output = null;
				output = new BufferedWriter(new FileWriter(f));
				output.write(content);
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
