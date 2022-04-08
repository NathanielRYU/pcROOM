package bean;

public class Friends {
	private String userId;
	private String reqUserId;
	private String resUserId;
	public String getReqUserId() {
		return reqUserId;
	}
	public void setReqUserId(String reqUserId) {
		this.reqUserId = reqUserId;
	}
	public String getResUserId() {
		return resUserId;
	}
	public void setResUserId(String resUserId) {
		this.resUserId = resUserId;
	}
	private int stCode;
	private String accessTime;
	private String pcRoomName;
	private String stName;
	
	public String getStName() {
		return stName;
	}
	public void setStName(String stName) {
		this.stName = stName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getStCode() {
		return stCode;
	}
	public void setStCode(int stCode) {
		this.stCode = stCode;
	}
	public String getAccessTime() {
		return accessTime;
	}
	public void setAccessTime(String accessTime) {
		this.accessTime = accessTime;
	}
	public String getPcRoomName() {
		return pcRoomName;
	}
	public void setPcRoomName(String pcRoomName) {
		this.pcRoomName = pcRoomName;
	}
	
  
}
