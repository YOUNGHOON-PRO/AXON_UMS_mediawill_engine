package kr.co.enders.engine.vo;

public class SmsSendLogVO {
	private String msgid;		//메시지아이디
	private String keygen;		//메시지키
	private String phone;		//핸드폰번호
	private String sendDt;		//발송일시
	private int retryCnt;		//재시도 횟수
	private String id;			//고객ID
	private String name;		//고객명
	private String email;		//고객이메일
	private String enckey;		//보안키
	private String bizkey;		//EAI연계KEY
	private String userId;		//사용자ID
	private int deptNo;			//사용자그룹
	private String sendTyp;		//메세지발송유형:C120
	private String msgTyp;		//메세지유형:C115
	private String regDt;		//등록일시:currentTimeMilis
	private String cmid;		//DAOU PK
	private String umid;		//MESSAGE_ID
	private String sendTelno;	//발신자번호
	private String sendNm;		//발신자명
	private String prtDt;		//단말기수신일시
	private String rsltCd;		//발송결과:0-실패, 1-성공
	private String rcode;		//상세코드
	private String reType;		//대체발송 메시지 타입
	private String reType01;	//1차 대체발송 메시지 타입
	private String reType02;	//2차 대체발송 메시지 타입
	private String reType03;	//3차 대체발송 메시지 타입
	
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
	public String getSendTyp() {
		return sendTyp;
	}
	public void setSendTyp(String sendTyp) {
		this.sendTyp = sendTyp;
	}
	public String getMsgTyp() {
		return msgTyp;
	}
	public void setMsgTyp(String msgTyp) {
		this.msgTyp = msgTyp;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getCmid() {
		return cmid;
	}
	public void setCmid(String cmid) {
		this.cmid = cmid;
	}
	public String getUmid() {
		return umid;
	}
	public void setUmid(String umid) {
		this.umid = umid;
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
}
