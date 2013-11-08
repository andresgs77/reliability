package checklistStorage;

import java.io.File;
import java.util.ArrayList;

public class PruebaLista {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ListROs l=new ListROs();
		ArrayList<String> listRos=l.getListROs();

		
		// tipos
		//http://purl.org/wf4ever/ro#ResearchObject
		//http://purl.org/wf4ever/roevo#LiveRO
		//http://purl.org/wf4ever/roevo#SnapshotRO
		//http://purl.org/wf4ever/roevo#ArchivedRO
		
		
		// Lista de ROs - archivos

		/*ArrayList<String> listRos = new ArrayList<String>();
		listRos.add("http://sandbox.wf4ever-project.org/rodl/ROs/Pack27/");
		listRos.add("http://sandbox.wf4ever-project.org/rodl/ROs/Pack388-release/");
		listRos.add("http://sandbox.wf4ever-project.org/rodl/ROs/Kegg-workflow-evaluation/");
		listRos.add("http://sandbox.wf4ever-project.org/rodl/ROs/Pack387/");
		listRos.add("http://sandbox.wf4ever-project.org/rodl/ROs/helloworld/");*/

		int min = 0;
		int max = 1;
		int i = min;
		while (i < max) {
			int numRO=0;
			for (String ro : listRos) {
				String[] aux = ro.split("/");
				String fileName = aux[aux.length - 1];

				File f = new File("WebContent/Traces/"+fileName+".txt");

				try {
					ChecklistParser p = new ChecklistParser(ro, null, "");
					if (f.exists()) {
						FileStorage fs = new FileStorage();
						fs.add(f, p.getEval());
					} else {
						FileStorage fs = new FileStorage(f, p.getEval(), ro);
					}

					System.out.println(numRO);
					numRO++;
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			 /*try {
				Thread.sleep(61 * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			 i++;
		}

	}

}
