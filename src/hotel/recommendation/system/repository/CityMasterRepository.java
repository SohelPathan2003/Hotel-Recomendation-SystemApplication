package hotel.recommendation.system.repository;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import hotel.recommendation.system.databaseconfigration.getDBConnection;
import hotel.recommendation.system.model.classes.*;
public class CityMasterRepository extends getDBConnection {
	private int cityid=0;
	private int areaid=0;
	private ArrayList<String> listofcities=null;
	private ArrayList<String> listofareas=null;
	private int answer=0;
	private boolean b;

	public boolean isAddCity(CityMasterModel citymastermodel) {
		cityid=this.getAutoIdForCity();
		try {
		pstmt=conn.prepareStatement("insert into citymaster values(?,?)");
		pstmt.setInt(1,cityid);
		pstmt.setString(2,citymastermodel.getCityName());
		answer =pstmt.executeUpdate();
		System.out.println(answer);
		
		}catch(SQLException ex) {
			System.out.println(ex);
		}
		
		return answer>0?true:false;
	}
	
	public int getAutoIdForCity()  {
		this.cityid=0;
			try {	
		pstmt=conn.prepareStatement("select cityid from citymaster");
		rs=pstmt.executeQuery();
		while(rs.next()) {
			cityid=rs.getInt(1);
		}
			}catch(Exception ex) {
				System.out.println(ex);
			}
		return ++cityid;
	}
	
	
	
	public int getCityIdByCityName(String cityname)throws SQLException,IOException {
		pstmt=conn.prepareStatement("select cityid from citymaster where cityname=?");
		pstmt.setString(1,cityname);
		rs=pstmt.executeQuery();
		if(rs.next())
			return rs.getInt(1);
		else
			return -1;
	}

	
	
	public ArrayList <String>getAllCities() throws SQLException{
	 listofcities=new ArrayList<String>();
		
		pstmt=conn.prepareStatement("select cityname from citymaster");
		rs=pstmt.executeQuery();
		while(rs.next()) {
			listofcities.add(rs.getString(1));
		}
		
		if(listofcities.size()>0)
			return listofcities;
		else
			return null;
		
	}
	
	public ArrayList<String> getAreasCityWise(String cityname)throws SQLException{
		listofareas=new ArrayList<String>();
		pstmt=conn.prepareStatement("select a.areaname from areamaster a inner join cityareajoin cs on cs.areaid=a.areaid inner join citymaster c on c.cityid=cs.cityid where c.cityname=?");
		pstmt.setString(1,cityname);
		rs=pstmt.executeQuery();
		while(rs.next()) {
			listofareas.add(rs.getString(1));
		}
		
		if(listofareas.size()>0)
			return listofareas;
		else
			return null;
	}
	
	public boolean isaAddArea(AreaMasterModel areamastermodel) {
		areaid=this.generateAreaIdAuto();
		
		try {
//			pstmt=conn.prepareStatement("insert into areamaster values('0',?)");
//			pstmt.setString(1,areamastermodel.getAreaname());
//			answer=pstmt.executeUpdate();
//			
//			
//		   pstmt=conn.prepareStatement("insert into cityareajoin values(?,?)");
//		   pstmt.setInt(1,areamastermodel.getCityid());
//		   pstmt.setInt(2,areamastermodel.getAreaid());
			
			
			//  see we write two prepare statement instead of that we create one procedure.
			
			cstmt=conn.prepareCall("call saveDataAreamasterAndCityareajoin(?,?,?)");
			cstmt.setInt(1,areaid);
			cstmt.setString(2,areamastermodel.getAreaname());
			cstmt.setInt(3,areamastermodel.getCityId());
			b=cstmt.execute();    // it will return the false if execute successfully.........
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		if(!b)
			return true;
		else
			return false;
		
	}
	
	
	
	
	public int generateAreaIdAuto()  {
		this.areaid=0;
			try {	
		pstmt=conn.prepareStatement("select max(areaid) from areamaster");
		rs=pstmt.executeQuery();
		if(rs.next()) {
			cityid=rs.getInt(1);
			return ++cityid;
		}
			
			}catch(Exception ex) {
				System.out.println(ex);
				return -1;
			}
		return ++cityid;
	}
	
	public int getAreaIdByAreaName(String areaname) throws SQLException {
		pstmt=conn.prepareStatement("select areaid from areamaster where areaname=?");
		pstmt.setString(1,areaname);
		rs=pstmt.executeQuery();
		if(rs.next())
			return rs.getInt(1);
		else
		
			return -1;
	}
	
}
