package kr.co.enders.engine.vo;
 
public class PushSendLogVO {
	private int pushmessageId;		//PUSH관리번호
	private String deviceIdentiNo;	//디바이스식별번호
	private String sendDt;			//발송일시 (밀리세컨드)
	private int retryCnt;			//재시도 횟수
	private String id;				//고객ID
	private String name;			//고객명
	private String email;			//고객이메일
	private String phone;			//폰번호
	private String enckey;			//보안키
	private String bizkey;			//EAI연계KEY
	private String userId;			//사용자ID
	private int deptNo;				//사용자그룹
	private String pushGubun;		//PUSH 유형:C124
	private String osGubun;			//OS 구분
	private String smsYn;			//SMS발송여부
	private String regDt;			//등록일시:currentTimeMilis
	private String sendTelno;		//발신자번호
	private String sendNm;			//발신자명
	private int campNo;				//캠페인번호
	private String prtDt;			//단말기수신일시
	private String rsltCd;			//발송결과:0 실패,1 성공
	private String rcode;			//상세코드
	private String reType;			//대체발송 메시지 타입 
	private String reType01;		//1차 대체발송 메시지 타입 
	private String reType02;		//2차 대체발송 메시지 타입
	private String reType03;		//3차 대체발송 메시지 타입
	private String rePart;			//대체발송주체
	private String telInfo;			//통신사 정보 (SKT/KTF/LGT/KKO)
	
	public int getPushmessageId() {
		return pushmessageId;
	}
	public void setPushmessageId(int pushmessageId) {
		this.pushmessageId = pushmessageId;
	}
	public String getDeviceIdentiNo() {
		return deviceIdentiNo;
	}
	public void setDeviceIdentiNo(String deviceIdentiNo) {
		this.deviceIdentiNo = deviceIdentiNo;
	}
	public String getSendDt() {
		return sendDt;
	}
	public void setSendDt(String sendDt) {
		this.sendDt = sendDt;
	}
	public int getRetryCnt() {
		return retryCnt;
	}
	public void setRetryCnt(int retryCnt) {
		this.retryCnt = retryCnt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEnckey() {
		return enckey;
	}
	public void setEnckey(String enckey) {
		this.enckey = enckey;
	}
	public String getBizkey() {
		return bizkey;
	}
	public void setBizkey(String bizkey) {
		this.bizkey = bizkey;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}
	public String getPushGubun() {
		return pushGubun;
	}
	public void setPushGubun(String pushGubun) {
		this.pushGubun = pushGubun;
	}
	public String getOsGubun() {
		return osGubun;
	}
	public void setOsGubun(String osGubun) {
		this.osGubun = osGubun;
	}
	public String getSmsYn() {
		return smsYn;
	}
	public void setSmsYn(String smsYn) {
		this.smsYn = smsYn;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getSendTelno() {
		return sendTelno;
	}
	public void setSendTelno(String sendTelno) {
		this.sendTelno = sendTelno;
	}
	public String getSendNm() {
		return sendNm;
	}
	public void setSendNm(String sendNm) {
		this.sendNm = sendNm;
	}
	public int getCampNo() {
		return campNo;
	}
	public void setCampNo(int campNo) {
		this.campNo = campNo;
	}
	public String getPrtDt() {
		return prtDt;
	}
	public void setPrtDt(String prtDt) {
		this.prtDt = prtDt;
	}
	public String getRsltCd() {
		return rsltCd;
	}
	public void setRsltCd(String rsltCd) {
		this.rsltCd = rsltCd;
	}
	public String getRcode() {
		return rcode;
	}
	public void setRcode(String rcode) {
		this.rcode = rcode;
	}
	public String getReType() {
		return reType;
	}
	public void setReType(String reType) {
		this.reType = reType;
	}
	public String getReType01() {
		return reType01;
	}
	public void setReType01(String reType01) {
		this.reType01 = reType01;
	}
	public String getReType02() {
		return reType02;
	}
	public void setReType02(String reType02) {
		this.reType02 = reType02;
	}
	public String getReType03() {
		return reType03;
	}
	public void setReType03(String reType03) {
		this.reType03 = reType03;
	}
	public String getRePart() {
		return rePart;
	}
	public void setRePart(String rePart) {
		this.rePart = rePart;
	}
	public String getTelInfo() {
		return telInfo;
	}
	public void setTelInfo(String telInfo) {
		this.telInfo = telInfo;
	}
}
