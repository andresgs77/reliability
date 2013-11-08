package checklistStorage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class FileStorage {
	
	public FileStorage (File file, String eval, String ro){
		String result= "{\"liveRO\":\""+ro+"\",\"eval\":[";
		result=result+eval+"]}";
		try {
			Writer output = null;
			output = new BufferedWriter(new FileWriter(file));
			output.write(result);
			output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	}

	public FileStorage() {
	}

	public void add(File f, String eval) {
		String content="";
		try {
			String output = new Scanner(f).useDelimiter("\\Z").next();
			content=output;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int pos=content.lastIndexOf("]}");
		content=content.substring(0, pos)+","+eval+"]}";
		
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
