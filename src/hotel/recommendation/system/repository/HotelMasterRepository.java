package hotel.recommendation.system.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import hotel.recommendation.system.databaseconfigration.getDBConnection;
import hotel.recommendation.system.model.classes.ClientMasterModel;
import hotel.recommendation.system.model.classes.ClientRegisterModel;
import hotel.recommendation.system.model.classes.HotelMasterModel;

public class HotelMasterRepository extends getDBConnection {
    boolean b;
    int hotelid=0;
    int clientid=0;
    LinkedHashMap linkedhashmap=null;
    HotelMasterModel hotelmastermodel;
    ArrayList al;
    int result=0;
	public boolean isAddHotel(HotelMasterModel hotelmastermodel) throws SQLException {
		
	hotelid=this.getHotelId();
		cstmt=conn.prepareCall("call saveDataHotelmasterAndareahoteljoin(?,?,?,?,?,?,?)");
		cstmt.setInt(1,hotelmastermodel.getAreaid());
		cstmt.setInt(2,hotelid);
		cstmt.setString(3,hotelmastermodel.getHotelname());
		cstmt.setString(4,hotelmastermodel.getHoteladdress());
		cstmt.setInt(5,hotelmastermodel.getPriceFrom());
		cstmt.setInt(6,hotelmastermodel.getPriceTo());
		cstmt.setInt(7,0);
		b=cstmt.execute();
		if(!b)
			return true;
		else
			return false;
		
	}
	
	public int getHotelId() throws SQLException {
		pstmt=conn.prepareStatement("select max(hotelid)from hotelmaster");
		rs=pstmt.executeQuery();
		if(rs.next())
			hotelid=rs.getInt(1);
		return ++hotelid;
	}
	
	public ArrayList getAllHotelsAreaWise(String areaname,String cityname){
		al=new ArrayList<HotelMasterModel>();
		try {
			pstmt=conn.prepareStatement(" select h.hotelid,h.hotelname,h.hoteladdress,h.price_range_from,h.price_range_to from hotelmaster h inner join areahoteljoin ah on ah.hotelid=h.hotelid inner join areamaster area on area.areaid=ah.areaid inner join cityareajoin ca on ca.areaid=area.areaid inner join citymaster c on c.cityid=ca.cityid where cityname=? and  area.areaname=?");
			pstmt.setString(1,cityname);
			pstmt.setString(2,areaname);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				hotelmastermodel=new HotelMasterModel();
				hotelmastermodel.setHotelid(rs.getInt(1));
				hotelmastermodel.setHotelname(rs.getString(2));
				hotelmastermodel.setHoteladdress(rs.getString(3));
				hotelmastermodel.setPriceFrom(rs.getInt(4));
				hotelmastermodel.setPriceTo(rs.getInt(5));
				al.add(hotelmastermodel);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al;
		
	}
	
	public ArrayList getHotelRatingWise(String areaname) {
		hotelmastermodel=new HotelMasterModel();
		al=new ArrayList<HotelMasterModel>();
		try {
			pstmt=conn.prepareStatement("select h.hotelid,h.hotelname,h.hoteladdress,h.price_range_from,h.price_range_to,h.hotelrating ,count(cf.hotelid) from hotelmaster h inner join areahoteljoin cs on cs.hotelid=h.hotelid inner join areamaster a on a.areaid=cs.areaid inner join clientfeedback cf on cf.hotelid=h.hotelid where a.areaname=? group by cf.hotelid order by count(cf.hotelid) desc");
			pstmt.setString(1,areaname);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				hotelmastermodel=new HotelMasterModel();
				hotelmastermodel.setHotelid(rs.getInt(1));
				hotelmastermodel.setHotelname(rs.getString(2));
				hotelmastermodel.setHoteladdress(rs.getString(3));
				hotelmastermodel.setPriceFrom(rs.getInt(4));
				hotelmastermodel.setPriceTo(rs.getInt(5));
				hotelmastermodel.setHotelRating(rs.getInt(6));
				hotelmastermodel.setNumberOfClient(rs.getInt(7));
				al.add(hotelmastermodel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return al;
		
	}
	
	public ArrayList getAllHotelRatingWise(String cityname,String areaname) {
		
		
		
		hotelmastermodel=new HotelMasterModel();
		al=new ArrayList<HotelMasterModel>();
		try {
			pstmt=conn.prepareStatement("select h.hotelid,h.hotelname,h.hoteladdress,h.price_range_from,h.price_range_to,h.hotelrating ,count(cf.hotelid) from hotelmaster h inner join areahoteljoin cs on cs.hotelid=h.hotelid inner join areamaster a on a.areaid=cs.areaid inner join clientfeedback cf on cf.hotelid=h.hotelid where a.areaname=? group by cf.hotelid order by h.hotelrating desc");
			pstmt.setString(1,areaname);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				hotelmastermodel=new HotelMasterModel();
				hotelmastermodel.setHotelid(rs.getInt(1));
				hotelmastermodel.setHotelname(rs.getString(2));
				hotelmastermodel.setHoteladdress(rs.getString(3));
				hotelmastermodel.setPriceFrom(rs.getInt(4));
				hotelmastermodel.setPriceTo(rs.getInt(5));
				hotelmastermodel.setHotelRating(rs.getFloat(6));
				hotelmastermodel.setNumberOfClient(rs.getInt(7));
				al.add(hotelmastermodel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pstmt=conn.prepareStatement("select h.hotelid,h.hotelname,h.hoteladdress,h.price_range_from,h.price_range_to,h.hotelrating from hotelmaster h inner join areahoteljoin ah on ah.hotelid=h.hotelid inner join areamaster a on a.areaid=ah.areaid inner join cityareajoin ca on ca.areaid=a.areaid inner join citymaster city on city.cityid=ca.cityid where cityname=? and areaname=? and hotelrating=0");
			pstmt.setString(1, cityname);
			pstmt.setString(2,areaname);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				hotelmastermodel=new HotelMasterModel();
				hotelmastermodel.setHotelid(rs.getInt(1));
				hotelmastermodel.setHotelname(rs.getString(2));
				hotelmastermodel.setHoteladdress(rs.getString(3));
				hotelmastermodel.setPriceFrom(rs.getInt(4));
				hotelmastermodel.setPriceTo(rs.getInt(5));
				hotelmastermodel.setHotelRating(rs.getInt(6));
				hotelmastermodel.setNumberOfClient(0);
				al.add(hotelmastermodel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return al;
	}
	
	
	
public ArrayList getAllHotelNumberOfClientgWise(String cityname,String areaname) {
		
		
		
		hotelmastermodel=new HotelMasterModel();
		al=new ArrayList<HotelMasterModel>();
		try {
			pstmt=conn.prepareStatement("select h.hotelid,h.hotelname,h.hoteladdress,h.price_range_from,h.price_range_to,h.hotelrating ,count(cf.hotelid) from hotelmaster h inner join areahoteljoin cs on cs.hotelid=h.hotelid inner join areamaster a on a.areaid=cs.areaid inner join clientfeedback cf on cf.hotelid=h.hotelid where a.areaname=? group by cf.hotelid order by count(h.hotelid) desc");
			pstmt.setString(1,areaname);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				hotelmastermodel=new HotelMasterModel();
				hotelmastermodel.setHotelid(rs.getInt(1));
				hotelmastermodel.setHotelname(rs.getString(2));
				hotelmastermodel.setHoteladdress(rs.getString(3));
				hotelmastermodel.setPriceFrom(rs.getInt(4));
				hotelmastermodel.setPriceTo(rs.getInt(5));
				hotelmastermodel.setHotelRating(rs.getFloat(6));
				hotelmastermodel.setNumberOfClient(rs.getInt(7));
				al.add(hotelmastermodel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pstmt=conn.prepareStatement("select h.hotelid,h.hotelname,h.hoteladdress,h.price_range_from,h.price_range_to,h.hotelrating from hotelmaster h inner join areahoteljoin ah on ah.hotelid=h.hotelid inner join areamaster a on a.areaid=ah.areaid inner join cityareajoin ca on ca.areaid=a.areaid inner join citymaster city on city.cityid=ca.cityid where cityname=? and areaname=? and hotelrating=0");
			pstmt.setString(1, cityname);
			pstmt.setString(2,areaname);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				hotelmastermodel=new HotelMasterModel();
				hotelmastermodel.setHotelid(rs.getInt(1));
				hotelmastermodel.setHotelname(rs.getString(2));
				hotelmastermodel.setHoteladdress(rs.getString(3));
				hotelmastermodel.setPriceFrom(rs.getInt(4));
				hotelmastermodel.setPriceTo(rs.getInt(5));
				hotelmastermodel.setHotelRating(rs.getFloat(6));
				hotelmastermodel.setNumberOfClient(0);
				al.add(hotelmastermodel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return al;
	}
	
	
	
	public int getHotelIdByHotelName(String cityname,String areaname,String hotelname) throws SQLException {
		pstmt=conn.prepareStatement("select h.hotelid from hotelmaster h inner join areahoteljoin ah on ah.hotelid=h.hotelid inner join areamaster a on a.areaid=ah.areaid inner join cityareajoin ca on ca.areaid=a.areaid inner join citymaster c on c.cityid=ca.cityid where c.cityname=? and a.areaname=? and h.hotelname=?;");
		pstmt.setString(1,cityname);
		pstmt.setString(2,areaname);
		pstmt.setString(3, hotelname);
		rs=pstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt(1);
		}else {
			return -1;
		}
	}
	
	
	public boolean isAddClientDetails(ClientMasterModel clientdetails) throws SQLException {
//		clientid=this.generateAutoClientId();
//	    cstmt=conn.prepareCall("call clientandfeedback('0',?,?,?,?,?,?)");
//	    cstmt.setInt(1,clientid);
//	    cstmt.setInt(2,clientdetails.getHotelId());
//	    cstmt.setString(3,clientdetails.getClientName());
//	    cstmt.setLong(4,clientdetails.getClientNumber());
//	    cstmt.setInt(5,clientdetails.getHotelRating());
//	    cstmt.setString(6,clientdetails.getTextReview());
		
		
		
		
		
		
		pstmt=conn.prepareStatement("insert into clientfeedback values('0',?,?,?,?)");
		pstmt.setInt(1,clientdetails.getClientid());
		pstmt.setInt(2, clientdetails.getHotelId());
		pstmt.setInt(3,clientdetails.getHotelRating());
		pstmt.setString(4, clientdetails.getTextReview());
		result=pstmt.executeUpdate();
		if(result>0)
			return true;
		else
			return false;
		
			
//	    b=cstmt.execute();
//		if(!b) {
//			System.out.println("present...........");
//
//			return true;
//		}
//		else {
//			return false;
//			
		//}
	}
	
	
	public int generateAutoClientId() throws SQLException {
		pstmt=conn.prepareStatement("select max(clientid) from clientmaster");
		rs=pstmt.executeQuery();
		if(rs.next()) {
			clientid=rs.getInt(1);
		}
		return ++clientid;
		
	}
	
	
	public boolean updateRatingInHotelMaster(int hotelid) {
		try {
			pstmt=conn.prepareStatement("select avg(cf.rating) from clientfeedback cf inner join hotelmaster h on h.hotelid=cf.hotelid where h.hotelid=?");
			pstmt.setInt(1, hotelid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				pstmt=conn.prepareStatement("update hotelmaster set hotelrating=? where hotelid=?");
				pstmt.setFloat(1, rs.getFloat(1));
				pstmt.setInt(2,hotelid);
				pstmt.executeUpdate();
				return true;
				
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public HashMap<String,String> getClientFeedbackForHotel(String areaname,String hotelname){
		HashMap<String,String> set=new HashMap<String,String>();
		try {
			pstmt=conn.prepareStatement("select client.clientname,cf.review from hotelmaster h inner join clientfeedback cf on h.hotelid=cf.hotelid inner join clientmaster client on client.clientid=cf.clientid where h.hotelname=?");
			pstmt.setString(1, hotelname);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				set.put(rs.getString(1), rs.getString(2));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return set;
	}
	
	
	public boolean isClientRegister(ClientRegisterModel clientregistermodel) {
		
		try {
			clientid=this.generateAutoClientId();
			pstmt=conn.prepareStatement("insert into clientmaster values(?,?,?,?)");
			pstmt.setInt(1,clientid);
			pstmt.setString(2,clientregistermodel.getClientName());
			pstmt.setLong(3,clientregistermodel.getClientnumber());
			pstmt.setString(4,clientregistermodel.getClientpassword());
			result=pstmt.executeUpdate();
			if(result>0) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  false;
		}
	}
	
	public boolean isClinetValid(String username,String clientpassowrd) {
		try {
			pstmt=conn.prepareStatement(" select clientid from clientmaster where clientname=? and password=?");
			pstmt.setString(1,username);
			pstmt.setString(2, clientpassowrd);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  false;
		}
		
	}
	
	public int getClientIdByUserNameAndPassword(String username,String clientpassowrd) {
		try {
			pstmt=conn.prepareStatement(" select clientid from clientmaster where clientname=? and password=?");
			pstmt.setString(1,username);
			pstmt.setString(2, clientpassowrd);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return -1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  -1;
		}
		
	}
	
}
