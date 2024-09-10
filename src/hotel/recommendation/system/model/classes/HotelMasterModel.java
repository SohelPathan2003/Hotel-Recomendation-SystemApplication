package hotel.recommendation.system.model.classes;

public class HotelMasterModel extends AreaMasterModel{
	private int hotelid;
	private String hotelname;
	private String hoteladdress;
	private int priceFrom;
	private int numberOfClient;
	public int getNumberOfClient() {
		return numberOfClient;
	}
	public void setNumberOfClient(int numberOfClient) {
		this.numberOfClient = numberOfClient;
	}
	public int getHotelid() {
		return hotelid;
	}
	public void setHotelid(int hotelid) {
		this.hotelid = hotelid;
	}
	public String getHotelname() {
		return hotelname;
	}
	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	public String getHoteladdress() {
		return hoteladdress;
	}
	public void setHoteladdress(String hoteladdress) {
		this.hoteladdress = hoteladdress;
	}
	public int getPriceFrom() {
		return priceFrom;
	}
	public void setPriceFrom(int priceFrom) {
		this.priceFrom = priceFrom;
	}
	public int getPriceTo() {
		return priceTo;
	}
	public void setPriceTo(int priceTo) {
		this.priceTo = priceTo;
	}
	private int priceTo;
    private float hotelRating;
	public float getHotelRating() {
		return hotelRating;
	}
	public void setHotelRating(float hotelRating) {
		this.hotelRating = hotelRating;
	}
    
}
