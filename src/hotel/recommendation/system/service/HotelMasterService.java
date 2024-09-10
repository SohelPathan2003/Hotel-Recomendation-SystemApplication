package hotel.recommendation.system.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import hotel.recommendation.system.model.classes.ClientMasterModel;
import hotel.recommendation.system.model.classes.ClientRegisterModel;
import hotel.recommendation.system.model.classes.HotelMasterModel;
import hotel.recommendation.system.repository.HotelMasterRepository;

public class HotelMasterService { 
	
	HotelMasterRepository hotelmasterrepository=new HotelMasterRepository();
	
	public boolean isAddHotel(HotelMasterModel hotelmastermodel) throws SQLException {
		return hotelmasterrepository.isAddHotel(hotelmastermodel);
	}
	
	public ArrayList<HotelMasterModel> getAllHotelsAreaWise(String areaname,String cityname){
	
		return hotelmasterrepository.getAllHotelsAreaWise(areaname,cityname);
	}
	
	public ArrayList getHotelRatingWise(String areaname) {
		return hotelmasterrepository.getHotelRatingWise(areaname);
	}
	
	public boolean isAddClientDetails(ClientMasterModel clientdetails) throws SQLException {
		
		return hotelmasterrepository.isAddClientDetails(clientdetails);
	}
	public int getHotelIdByHotelName(String cityname,String areaname,String hotelname) throws SQLException {
		return hotelmasterrepository.getHotelIdByHotelName(cityname,areaname,hotelname);
	}
	
	public boolean updateRatingInHotelMaster(int hotelid) {
		return hotelmasterrepository.updateRatingInHotelMaster(hotelid);
		
		
	}
	
	public HashMap<String,String> getClientFeedbackForHotel(String areaname,String hotelname) {
		return hotelmasterrepository.getClientFeedbackForHotel(areaname,hotelname);
	}
	
	public 	ArrayList <HotelMasterModel> getAllHotelRatingWise(String cityname,String areaname) {
		return hotelmasterrepository.getAllHotelRatingWise(cityname,areaname);
	}
	
	public boolean isClientRegister(ClientRegisterModel clientregistermodel) {
		return hotelmasterrepository.isClientRegister(clientregistermodel);
	}
	
	public boolean isClinetValid(String username,String clientpassowrd) {
		return hotelmasterrepository.isClinetValid(username,clientpassowrd);
	}
	
	public int getClientIdByUserNameAndPassword(String username,String clientpassowrd) {
		return hotelmasterrepository.getClientIdByUserNameAndPassword(username,clientpassowrd);
	}
	
	
	public ArrayList getAllHotelNumberOfClientgWise(String cityname,String areaname) {
		return hotelmasterrepository.getAllHotelNumberOfClientgWise(cityname,areaname);
	}
}




