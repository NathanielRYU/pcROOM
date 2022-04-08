package bean;

public class MembersInfo {
	private String memName;
	private String nickName;
	private String phoneNum;
	private String passWord;
	private String accessTime;
	private int state;
	private String memPcCode;
	private String memPcRoomName;
	
	public String getMemPcRoomName() {
		return memPcRoomName;
	}

	public void setMemPcRoomName(String memPcRoomName) {
		this.memPcRoomName = memPcRoomName;
	}

	// ============================================================
	public String getMemPcCode() {
		return memPcCode;
	}

	public void setMemPcCode(String memPcCode) {
		this.memPcCode = memPcCode;
	}

	
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(String accessTime) {
		this.accessTime = accessTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	
	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
