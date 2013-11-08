package main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import rdfReliability.ItemReliability;
import storageEvaluation.LoadStorageATOMFull;

@Path("/getReliability")
public class ReliabilityRest {
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getStability(@QueryParam("RO") String ro) {
		ItemReliability item=new ItemReliability();
		//LoadStorage ls=new LoadStorage();
		//LoadStoragePAPER ls=new LoadStoragePAPER();
		LoadStorageATOMFull ls=new LoadStorageATOMFull();
		item.fillInfo(ls.LoadStoredXML(ro),ls.getRoName());
		return Response.ok(item).build();
	}

}
