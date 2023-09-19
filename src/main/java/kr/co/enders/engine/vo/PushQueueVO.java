package kr.co.enders.engine.vo;

public class PushQueueVO {
	private int pushmessageId;		//PUSH관리번호
	private String deviceIdentiNo;	//디바이스식별번호
	private String id;				//고객ID
	private String name;			//고객명
	private String email;			//고객이메일
	private String phone;			//고객폰번호
	private String enckey;			//보안키
	private String bizkey;			//EAI연계KEY
	private String userId;			//사용자ID
	private int deptNo;				//사용자그룹
	private String sendDt;			//발송일시: 발송예약일시
	private String sendStrDt;		//발송시작 일시 (밀리세컨드), 전체 시작 발송 시간
	private String sendEndDt;		//발송완료 일시 (밀리세컨드), 건별 발송 종료 시간
	private String pushTitle;		//PUSH제목
	private String pushMessage;	//PUSH내용
	private String pushGubun;		//PUSH유형:C124
	private String filePath;		//첨부파일경로
	private String fileNm;			//첨부파일명,
	private int fileSize;			//첨부파일사이즈
	private String sendTyp;			//발송유형:C120,
	private String osGubun;			//OS구분
	private String callUrlTyp;		//URLLINK유형:C123
	private String callUri;			//ANDROIDLINKURL
	private String smsYn;			//SMS전송여부, 옵션 존재시 적용
	private String workStatus;		//발송상태: 000-발송대기, 001-발송중, 002 - 발송완료
	private String rsltCd;			//발송결과:0실패,1성공
	private String rcode;			//상세코드 : 시스템 상세 코드
	private int retryCnt;			//재시도횟수
	private String logClsYn;		//0 - 미처리 , 1 - 처리중 , 9 - 로그 집계 완료 ( 7일 후 물리 삭제)
	private String regId;			//등록자ID
	private String regDt;			//등록일시
	private String upId;			//수정자ID
	private String upDt;			//수정일시
	
	//valid 여부 
	private String validStatus;		//C035 코드 정보 

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
	public String getSendDt() {
		return sendDt;
	}
	public void setSendDt(String sendDt) {
		this.sendDt = sendDt;
	}
	public String getSendStrDt() {
		return sendStrDt;
	}
	public void setSendStrDt(String sendStrDt) {
		this.sendStrDt = sendStrDt;
	}
	public String getSendEndDt() {
		return sendEndDt;
	}
	public void setSendEndDt(String sendEndDt) {
		this.sendEndDt = sendEndDt;
	}
	public String getPushTitle() {
		return pushTitle;
	}
	public void setPushTitle(String pushTitle) {
		this.pushTitle = pushTitle;
	}
	public String getPushMessage() {
		return pushMessage;
	}
	public void setPushMessage(String pushMessage) {
		this.pushMessage = pushMessage;
	}
	public String getPushGubun() {
		return pushGubun;
	}
	public void setPushGubun(String pushGubun) {
		this.pushGubun = pushGubun;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileNm() {
		return fileNm;
	}
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public String getSendTyp() {
		return sendTyp;
	}
	public void setSendTyp(String sendTyp) {
		this.sendTyp = sendTyp;
	}
	public String getOsGubun() {
		return osGubun;
	}
	public void setOsGubun(String osGubun) {
		this.osGubun = osGubun;
	}
	public String getCallUrlTyp() {
		return callUrlTyp;
	}
	public void setCallUrlTyp(String callUrlTyp) {
		this.callUrlTyp = callUrlTyp;
	}
	public String getCallUri() {
		return callUri;
	}
	public void setCallUri(String callUri) {
		this.callUri = callUri;
	}
	public String getSmsYn() {
		return smsYn;
	}
	public void setSmsYn(String smsYn) {
		this.smsYn = smsYn;
	}
	public String getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
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
	public int getRetryCnt() {
		return retryCnt;
	}
	public void setRetryCnt(int retryCnt) {
		this.retryCnt = retryCnt;
	}
	public String getLogClsYn() {
		return logClsYn;
	}
	public void setLogClsYn(String logClsYn) {
		this.logClsYn = logClsYn;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getUpId() {
		return upId;
	}
	public void setUpId(String upId) {
		this.upId = upId;
	}
	public String getUpDt() {
		return upDt;
	}
	public void setUpDt(String upDt) {
		this.upDt = upDt;
	}
	public String getValidStatus() {
		return validStatus;
	}
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
}
