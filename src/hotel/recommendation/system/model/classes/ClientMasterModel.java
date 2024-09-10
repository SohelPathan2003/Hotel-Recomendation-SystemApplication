package hotel.recommendation.system.model.classes;

public class ClientMasterModel{
	private String clientName;
	private int clientid;
	private long clientNumber;
	private int hotelId;
	public String getClientName() {
		return clientName;
	}
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public int getClientid() {
		return clientid;
	}
	public void setClientid(int clientid) {
		this.clientid = clientid;
	}
	public long getClientNumber() {
		return clientNumber;
	}
	public void setClientNumber(long clientnumber) {
		this.clientNumber = clientnumber;
	}
	public int getHotelRating() {
		return hotelRating;
	}
	public void setHotelRating(int hotelRating) {
		this.hotelRating = hotelRating;
	}
	public String getTextReview() {
		return textReview;
	}
	public void setTextReview(String textReview) {
		this.textReview = textReview;
	}
	private int hotelRating;
	private String textReview;
	

}
