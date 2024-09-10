package hotel.recommendation.system.model.classes;

public class ClientRegisterModel {

	private int clientid;
	private String clientName;
	private Long clientnumber;
	private String clientpassword;
	public int getClientid() {
		return clientid;
	}
	public void setClientid(int clientid) {
		this.clientid = clientid;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public Long getClientnumber() {
		return clientnumber;
	}
	public void setClientnumber(Long clientnumber) {
		this.clientnumber = clientnumber;
	}
	public String getClientpassword() {
		return clientpassword;
	}
	public void setClientpassword(String clientpassword) {
		this.clientpassword = clientpassword;
	}
	
}
