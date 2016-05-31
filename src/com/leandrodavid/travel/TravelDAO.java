package com.leandrodavid.travel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class TravelDAO {

	private HashMap<Integer,Travel> travelList;
	private  int id ;
	
	private  static TravelDAO travelDAOInstance;
	
	private TravelDAO(){			
		travelList = new HashMap<Integer,Travel>();
		id= 0 ;
	}
	
	public static TravelDAO getInstance(){
		if (travelDAOInstance==null){
			travelDAOInstance= new TravelDAO();
		}
		
		return travelDAOInstance;
	}
	
	public Collection<Travel> getTravelList(){
		
		return (Collection<Travel>)travelList.values();
	}
	
	public int addTravel(Travel travel){
		int currentId=id;
		travel.setId(currentId);
		this.travelList.put(currentId, travel);
		id++;
		
		return currentId;
	}
	
	
	public Travel getTravel(Integer id){
		return travelList.get(id);
	}
	
	public void updateTravel(Integer id,Travel travel){
		travelList.replace(id,travel);
	}
	
	public void deleteTravel(Integer id){
		travelList.remove(id);
	}
	
	public boolean travelExists(Integer id){
		return travelList.containsKey(id);
	}
}
