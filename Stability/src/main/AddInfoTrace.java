package main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import checklistStorage.UpdateFiles;

@Path("/addInfo")
public class AddInfoTrace {

	// This method is called if TEXT_PLAIN is request
		@GET
		@Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		public String sayPlainText(@QueryParam("RO") String ro) {
			String response="Please specify the RO: /addInfo{?RO}";
			if (ro!=null){
				response="The trace for " + ro + " has been created.";
				UpdateFiles up=new UpdateFiles(ro);
			}
		return response;
		}
}
