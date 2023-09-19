package kr.co.enders.engine.vo;

public class SmsQueueVO {

	private String cmid;			// 데이터 ID
	private String umid;			// MESSAGE ID
	private int msgType;			// 데이터 타입 (SMS 0/FAX 2/PHONE 3/MMS 5/AT 6/FT 7/RCS 8/BI 11/BW 12)
	private int status;				// 데이터 발송 상태 (대기 0/발송중1/발송완료 2)
	private String callStatus;		// 발송결과 리포트
	private String requestTime;		// 데이터 등록 시간
	private String sendTime;		// 발송 기준 시간
	private String reportTime;		// 단말기 수신 시간
	private String destPhone;		// 수신번호
	private String sendPhone;		// 발신자번호
	private String destName;		// 수신자명
	private String sendName;		// 발신자명
	private String subject;			// 제목
	private String msgBody;			// 메시지 내용
	private String nationCode;		// 국가코드
	private String senderKey;		// 발신프로필 키
	private String templateCode;	// 템플릿 코드
	private String responseMethod;	// 발송 방식 (PUSH)
	private int timeout;			// 대체발송을 위한 타임아웃 시간설정
	private String reType;			// 대체발송 메시지 타입
	private String reBody;			// 대체발송 메시지 내용
	private String rePart;			// 대체발송 처리 주체 (C:CLIENT, S:Server)
	private int coverFlag;			// 표지 발송 옵션
	private int smsFlag;			// 실패 시 문자 전송 옵션
	private int replyFlag;			// 시나리오 답변기능 여부(Y:1, N:0)
	private int retryCnt; 			// 재시도회수
	private String attachedFile;	// 첨부파일 전송 시 파일명
	private String vxmlFile;		// 음성 시나리오 파일 이름
	private int usePage;			// 발송 페이지 수
	private int useTime;			// 발송 소요 시간
	private int snResult;			// 설문 조사 응답 값
	private String telInfo;			// 통신사 정보
	private String cinfo;			// Client Indexed Info
	private String userKey;			// 옐로아이디 봇을 이용해 받은 옐로아이디 사용자 식별키
	private String adFlag;			// 광고성 메시지 필수 표기 사항을 노출(노출여부 Y/N, 기본값 Y)
	private String rcsRefkey;		// RCS 테이블 KEY

	private String msgid;			// NEO_SMS.MSGID
	private String keygen;			// NEO_SMS.KEYGEN

	// valid 여부
	private String validStatus; 	// C035 코드 정보

	// Log 저장용
	private String id; 				// 고객ID
	private String email; 			// 고객이메일
	private String userId;			// 사용자ID
	private int deptNo; 			// 사용자그룹
	private String sendTyp;			// 메세지발송유형:C120
	private String msgTyp; 			// 메세지유형:C115
	private String regDt;			// 등록일시:currentTimeMilis
	
	private String smsStatus;
	private String weblinkYn;
	private String weblinkJson;

	
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
	public int getMsgType() {
		return msgType;
	}
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCallStatus() {
		return callStatus;
	}
	public void setCallStatus(String callStatus) {
		this.callStatus = callStatus;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getReportTime() {
		return reportTime;
	}
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
	public String getDestPhone() {
		return destPhone;
	}
	public void setDestPhone(String destPhone) {
		this.destPhone = destPhone;
	}
	public String getSendPhone() {
		return sendPhone;
	}
	public void setSendPhone(String sendPhone) {
		this.sendPhone = sendPhone;
	}
	public String getDestName() {
		return destName;
	}
	public void setDestName(String destName) {
		this.destName = destName;
	}
	public String getSendName() {
		return sendName;
	}
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMsgBody() {
		return msgBody;
	}
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	public String getNationCode() {
		return nationCode;
	}
	public void setNationCode(String nationCode) {
		this.nationCode = nationCode;
	}
	public String getSenderKey() {
		return senderKey;
	}
	public void setSenderKey(String senderKey) {
		this.senderKey = senderKey;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getResponseMethod() {
		return responseMethod;
	}
	public void setResponseMethod(String responseMethod) {
		this.responseMethod = responseMethod;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public String getReType() {
		return reType;
	}
	public void setReType(String reType) {
		this.reType = reType;
	}
	public String getReBody() {
		return reBody;
	}
	public void setReBody(String reBody) {
		this.reBody = reBody;
	}
	public String getRePart() {
		return rePart;
	}
	public void setRePart(String rePart) {
		this.rePart = rePart;
	}
	public int getCoverFlag() {
		return coverFlag;
	}
	public void setCoverFlag(int coverFlag) {
		this.coverFlag = coverFlag;
	}
	public int getSmsFlag() {
		return smsFlag;
	}
	public void setSmsFlag(int smsFlag) {
		this.smsFlag = smsFlag;
	}
	public int getReplyFlag() {
		return replyFlag;
	}
	public void setReplyFlag(int replyFlag) {
		this.replyFlag = replyFlag;
	}
	public int getRetryCnt() {
		return retryCnt;
	}
	public void setRetryCnt(int retryCnt) {
		this.retryCnt = retryCnt;
	}
	public String getAttachedFile() {
		return attachedFile;
	}
	public void setAttachedFile(String attachedFile) {
		this.attachedFile = attachedFile;
	}
	public String getVxmlFile() {
		return vxmlFile;
	}
	public void setVxmlFile(String vxmlFile) {
		this.vxmlFile = vxmlFile;
	}
	public int getUsePage() {
		return usePage;
	}
	public void setUsePage(int usePage) {
		this.usePage = usePage;
	}
	public int getUseTime() {
		return useTime;
	}
	public void setUseTime(int useTime) {
		this.useTime = useTime;
	}
	public int getSnResult() {
		return snResult;
	}
	public void setSnResult(int snResult) {
		this.snResult = snResult;
	}
	public String getTelInfo() {
		return telInfo;
	}
	public void setTelInfo(String telInfo) {
		this.telInfo = telInfo;
	}
	public String getCinfo() {
		return cinfo;
	}
	public void setCinfo(String cinfo) {
		this.cinfo = cinfo;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getAdFlag() {
		return adFlag;
	}
	public void setAdFlag(String adFlag) {
		this.adFlag = adFlag;
	}
	public String getRcsRefkey() {
		return rcsRefkey;
	}
	public void setRcsRefkey(String rcsRefkey) {
		this.rcsRefkey = rcsRefkey;
	}
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
	public String getValidStatus() {
		return validStatus;
	}
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getSmsStatus() {
		return smsStatus;
	}
	public void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}
	public String getWeblinkYn() {
		return weblinkYn;
	}
	public void setWeblinkYn(String weblinkYn) {
		this.weblinkYn = weblinkYn;
	}
	public String getWeblinkJson() {
		return weblinkJson;
	}
	public void setWeblinkJson(String weblinkJson) {
		this.weblinkJson = weblinkJson;
	}
	
	
}
