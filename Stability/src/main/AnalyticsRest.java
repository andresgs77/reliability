package main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import rdf.ItemAnalytics;
import controlAnalytics.GenerateAnalytics;

@Path("/getAnalytics")
public class AnalyticsRest {

	// This method is called if TEXT_PLAIN is request
		@GET
		@Produces(MediaType.TEXT_HTML)
		public String sayPlainText() {
			String response="<?xml version=\"1.0\"?>"+
		"<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" "+
	    " xmlns:roe=\"http://purl.org/ro/service/evaluate/stability\">"+
	    "<rdf:Description rdf:about=\"\">"+
	    "<roe:stability>/rest/getAnalytics{?RO}</roe:stability>"+
	    "</rdf:Description></rdf:RDF> ";
		return response;
		}
		
		@GET
		@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
		public Response getStability(@QueryParam("RO") String ro) {
			ItemAnalytics item=new ItemAnalytics();
			GenerateAnalytics gA=new GenerateAnalytics(ro);
			item.fillLiveROInfo(gA);
			item.fillSnapshotsInfo(gA);
			item.fillUsersInfo(gA);
			return Response.ok(item).build();
		}
}
