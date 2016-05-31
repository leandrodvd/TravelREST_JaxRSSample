package com.leandrodavid.travel;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("/travels")
public class TravelResource {

	
	@GET
	@Produces("application/json")
	public String getTravels(){
		String travelListJson = getGson().toJson(TravelDAO.getInstance().getTravelList());
		
		return travelListJson;
	}
	
	
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public String postTravel(String req){
		Travel travel = getGson().fromJson(req, Travel.class);
		TravelDAO.getInstance().addTravel(travel);
		return "{'success':'true'}";
	}
	
	@Path("uniquetravel/{id}")
	@GET
	@Produces("application/json")
	public String getTravel(@PathParam(value="id") int travelId){
		Travel travel= TravelDAO.getInstance().getTravel(travelId);
		String travelJson = getGson().toJson(travel);
		return travelJson;
	}
	
	@Path("{id}")
	@PUT
	@Produces("application/json")
	public Response putTravel(String req,@PathParam(value="id") int travelId){
		if( TravelDAO.getInstance().travelExists(travelId)){
			Travel travel = getGson().fromJson(req, Travel.class);
			TravelDAO.getInstance().updateTravel(travelId, travel);
			return Response.ok().build();
		}
		else{
			return Response.status(404).build();
		}
	}
	
	@Path("{id}")
	@DELETE
	@Produces("application/json")
	public Response deleteTravel(@PathParam(value="id") int travelId){
		
		if( TravelDAO.getInstance().travelExists(travelId)){
			TravelDAO.getInstance().deleteTravel(travelId);
			return Response.ok().build();
		}
		else{
			return Response.status(404).build();
		}
	}

	
	///UTILS
	private static Gson gson;
	public Gson getGson(){
		if (gson==null){
			gson = new Gson();
		}
		return gson;
	}
		
	
	
}
