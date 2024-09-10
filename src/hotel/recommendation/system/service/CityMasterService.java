package hotel.recommendation.system.service;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import hotel.recommendation.system.model.classes.*;
import hotel.recommendation.system.repository.*;
public class CityMasterService {
	
	CityMasterRepository citymasterrepository=new CityMasterRepository();
	
	public boolean isAddCity(CityMasterModel citymastermodel) {
		return citymasterrepository.isAddCity(citymastermodel);
	}

	
	public int getCityIdByCityName(String cityname) throws SQLException, IOException {
		return citymasterrepository.getCityIdByCityName(cityname);
	}
	
	public ArrayList<String> getAllCities() throws SQLException{
		return citymasterrepository.getAllCities();
	}
	public ArrayList<String> getAreasCityWise(String cityname)throws SQLException{
		return citymasterrepository.getAreasCityWise(cityname);
		
		
	}
	
	
	public boolean isAddArea(AreaMasterModel areamastermodel) {
		return citymasterrepository.isaAddArea(areamastermodel);
	}
	
	public int getAreaIdByAreaName(String areaname) throws SQLException {
		
		return citymasterrepository.getAreaIdByAreaName(areaname);
		
	}
}
