package kr.co.enders.engine.vo;

public class SmsPhoneVO  {
	private String msgid;			//메시지아이디
	private String keygen;			//메시지키
	private String phone;			//핸드폰번호
	private String requestkey;
  
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	public String getKeygen() {
		return keygen;
	}
	public void setKeygen(String keygen) {
		this.keygen = keygen;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRequestkey() {
		return requestkey;
	}
	public void setRequestkey(String requestkey) {
		this.requestkey = requestkey;
	}
	
}
