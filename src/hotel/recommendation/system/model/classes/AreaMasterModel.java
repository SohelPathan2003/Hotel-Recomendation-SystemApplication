package hotel.recommendation.system.model.classes;

public class AreaMasterModel extends CityMasterModel{
	
	private int areaid;
	private String areaname;
	public int getAreaid() {
		return areaid;
	}
	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}
	public String getAreaname() {
		return areaname;
	}
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	
//	private int cityid;
//	public int getCityid() {
//		return cityid;
//	}
//	public void setCityid(int cityid) {
//		this.cityid = cityid;
//	}
	

}
