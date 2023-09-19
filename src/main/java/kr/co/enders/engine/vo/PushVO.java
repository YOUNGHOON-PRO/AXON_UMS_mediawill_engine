package kr.co.enders.engine.vo;

public class PushVO {
	private int pushmessageId;		//PUSH관리번호
	private String userId;			//사용자ID
	private int deptNo;				//사용자그룹
	private String regId;			//등록자ID
	private String regDt;			//등록일시
	private String upId;			//수정자ID 
	private String upDt;			//수정일시
	private int segNo;				//세그먼트등록번호
	private String sendTelno;		//발신자전화번호
	private int campNo;				//캠페인번호
	private String pushName;		//PUSH 명
	private String pushMessage;		//PUSH 내용
	private String pushGubun;		//PUSH 유형:C124
	private String filePath;		//첨부파일1
	private String callUri;			//ANDROID LINK URL
	private String callUriIos;		//IOS LINK URL
	private String osGubun;			//OS구분
	private String pushTitle;		//PUSH제목
	private int retryCnt;			//재시도횟수
	private int pushAlarmday;		//알람일수
	private String smsYn;			//SMS전송여부
	private String legalYn;			//광고여부 Y/N
	private String legalCf;			//광고안내 메시지
	private String sendRepeat;		//SEND_REPEAT
	private String sendTermLoop;	//SEND_TERM_LOOP
	private String sendTermEndDt;	//SEND_TERM_END_DT
	private String sendTermLoopTy;	//SEND_TERM_LOOP_TY
	private String sendDt;			//발송일시
	private String sendEndEt;		//발송종료일시
	private String workStatus;		//발송상태
	private String status;			//상태
	private String callUrlTyp;		//URL LINK 유형:C123
	private String sendTyp;			//발송유형:C120
	private String fileNm;			//첨부파일명
	private int fileSize;			//첨부파일사이즈
	
	public int getPushmessageId() {
		return pushmessageId;
	}
	public void setPushmessageId(int pushmessageId) {
		this.pushmessageId = pushmessageId;
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
	public int getSegNo() {
		return segNo;
	}
	public void setSegNo(int segNo) {
		this.segNo = segNo;
	}
	public String getSendTelno() {
		return sendTelno;
	}
	public void setSendTelno(String sendTelno) {
		this.sendTelno = sendTelno;
	}
	public int getCampNo() {
		return campNo;
	}
	public void setCampNo(int campNo) {
		this.campNo = campNo;
	}
	public String getPushName() {
		return pushName;
	}
	public void setPushName(String pushName) {
		this.pushName = pushName;
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
	public String getCallUri() {
		return callUri;
	}
	public void setCallUri(String callUri) {
		this.callUri = callUri;
	}
	public String getCallUriIos() {
		return callUriIos;
	}
	public void setCallUriIos(String callUriIos) {
		this.callUriIos = callUriIos;
	}
	public String getOsGubun() {
		return osGubun;
	}
	public void setOsGubun(String osGubun) {
		this.osGubun = osGubun;
	}
	public String getPushTitle() {
		return pushTitle;
	}
	public void setPushTitle(String pushTitle) {
		this.pushTitle = pushTitle;
	}
	public int getRetryCnt() {
		return retryCnt;
	}
	public void setRetryCnt(int retryCnt) {
		this.retryCnt = retryCnt;
	}
	public int getPushAlarmday() {
		return pushAlarmday;
	}
	public void setPushAlarmday(int pushAlarmday) {
		this.pushAlarmday = pushAlarmday;
	}
	public String getSmsYn() {
		return smsYn;
	}
	public void setSmsYn(String smsYn) {
		this.smsYn = smsYn;
	}
	public String getLegalYn() {
		return legalYn;
	}
	public void setLegalYn(String legalYn) {
		this.legalYn = legalYn;
	}
	public String getLegalCf() {
		return legalCf;
	}
	public void setLegalCf(String legalCf) {
		this.legalCf = legalCf;
	}
	public String getSendRepeat() {
		return sendRepeat;
	}
	public void setSendRepeat(String sendRepeat) {
		this.sendRepeat = sendRepeat;
	}
	public String getSendTermLoop() {
		return sendTermLoop;
	}
	public void setSendTermLoop(String sendTermLoop) {
		this.sendTermLoop = sendTermLoop;
	}
	public String getSendTermEndDt() {
		return sendTermEndDt;
	}
	public void setSendTermEndDt(String sendTermEndDt) {
		this.sendTermEndDt = sendTermEndDt;
	}
	public String getSendTermLoopTy() {
		return sendTermLoopTy;
	}
	public void setSendTermLoopTy(String sendTermLoopTy) {
		this.sendTermLoopTy = sendTermLoopTy;
	}
	public String getSendDt() {
		return sendDt;
	}
	public void setSendDt(String sendDt) {
		this.sendDt = sendDt;
	}
	public String getSendEndEt() {
		return sendEndEt;
	}
	public void setSendEndEt(String sendEndEt) {
		this.sendEndEt = sendEndEt;
	}
	public String getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCallUrlTyp() {
		return callUrlTyp;
	}
	public void setCallUrlTyp(String callUrlTyp) {
		this.callUrlTyp = callUrlTyp;
	}
	public String getSendTyp() {
		return sendTyp;
	}
	public void setSendTyp(String sendTyp) {
		this.sendTyp = sendTyp;
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
}
