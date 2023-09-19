package kr.co.enders.engine.com;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import org.springframework.beans.factory.annotation.Value; 
import org.springframework.stereotype.Component;

import kr.co.enders.engine.svc.SmsService;
import kr.co.enders.engine.util.BeanUtils;
import kr.co.enders.engine.util.DBUtil;
import kr.co.enders.engine.util.EncryptUtil;
import kr.co.enders.engine.util.FileUtil;
import kr.co.enders.engine.util.StringUtil;
import kr.co.enders.engine.vo.DbConnVO;
import kr.co.enders.engine.vo.SegmentMemberVO;
import kr.co.enders.engine.vo.SegmentVO;
import kr.co.enders.engine.vo.SmsAttachVO;
import kr.co.enders.engine.vo.SmsPhoneVO;
import kr.co.enders.engine.vo.SmsQueueVO;
import kr.co.enders.engine.vo.SmsSendLogVO;
import kr.co.enders.engine.vo.SmsVO;

@Component("sms")
public class SmsSend { 
	
	private static Logger logger = LoggerFactory.getLogger(SmsSend.class);
	
	@Value("${JASYPT.ALGORITHM}")
	private String algorithm;
	
	@Value("${JASYPT.KEYSTRING}")
	private String keystring;
	
	@Value("${FILE.UPLOAD_PATH}")
	private String fileUploadPath;
	
	@Value("${ENC.COLUMN}")
	private String encColumn;
	
	@Value("${BIZCLIENT.UMID}")
	private String umid;
	
	@Value("${BIZCLIENT.SENDER_KEY}")
	private String senderKey;
	
	@Value("${BIZCLIENT.TIMEOUT}")
	private int timeout;
	
	private SmsService smsService;

	public void sendSms() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter sendFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String sendDate = now.format(sendFormatter);
		String senderTel = "";
		
		smsService =  (SmsService) BeanUtils.getBean("smsService");
		List<SmsVO> smsTargetList = getTargetList(sendDate);

		if (smsTargetList == null || smsTargetList.size() < 1 ){
			//logger.debug("Sms Send Target Not Exist");
			return;
		}
		
		int result = 0 ;
		try {
			for (int i= 0; i < smsTargetList.size(); i ++) {
				SegmentVO segmentVO =  getSegment(smsTargetList.get(i).getSegNo());
				SegmentMemberVO segmentMemberVO = new SegmentMemberVO();
				
				if(segmentVO != null && !"".equals(segmentVO.getCreateTy())) {
					if("000".equals(segmentVO.getCreateTy())) { //추출조건 
						segmentMemberVO = ReadSegmentQuery(segmentVO);
					} else if("002".equals(segmentVO.getCreateTy())) { //직접SQL 
						segmentMemberVO = ReadSegmentQuery(segmentVO);
					} else if ("003".equals(segmentVO.getCreateTy())) { //파일연동 
						segmentMemberVO = ReadSegmentFile(segmentVO);
					}
					
					if (segmentMemberVO != null) {
						//senderTel = EncryptUtil.getJasyptDecryptedString(algorithm, keystring, smsTargetList.get(i).getSendTelno());
						//smsTargetList.get(i).setSendTelno(senderTel);
						result = insertMessageQueue(smsTargetList.get(i),segmentVO, segmentMemberVO,sendDate);
					}
				} else {
					result = -1; 
				}
				
				if (result < 0 ) { 
					setTargetStatus(null, smsTargetList.get(i), "004");
				} else {
					setTargetStatus(null, smsTargetList.get(i), "003");
				}
			}
		} catch(Exception e) {
			logger.error("smsService.sendSms error = " + e);
			if(smsTargetList != null) {
				setTargetStatus(smsTargetList, null, "004");
			}
		}
	}
	
	private List<SmsVO> getTargetList(String sendDate){
		SmsVO smsVO = new SmsVO();
		smsVO.setStatus("001");
		smsVO.setSmsStatus("000");
		smsVO.setSendDate(sendDate); 
		List<SmsVO> smsTargetList = null;
		
		try {
			smsTargetList = smsService.getSmsListTarget(smsVO);
			if(smsTargetList!=null && smsTargetList.size() > 0 ) {
				System.out.println("SmsListTarget " + Integer.toString(smsTargetList.size()));
				try {
					for (int i= 0; i < smsTargetList.size(); i ++) {
						smsVO.setMsgid(smsTargetList.get(i).getMsgid());
						smsVO.setKeygen(smsTargetList.get(i).getKeygen());
						smsVO.setSendStartDt(sendDate);
						smsVO.setStatus("002");
						smsService.updateSmsStatus(smsVO);
						//smsTargetList.get(i).setSendTelno(EncryptUtil.getJasyptDecryptedString(algorithm, keystring,smsTargetList.get(i).getSendTelno()));
					}
				} catch(Exception e) {
					logger.error("smsService.updateSmsStatus error = " + e);
				}
			} else {
				//System.out.println("SMS Send Target Data is Nothing");
			}
		} catch(Exception e) {
			logger.error("smsService.getSmsListTarget error = " + e);
		}
		return smsTargetList;
	}
	
	private List<SmsAttachVO> getFileAttachList(SmsVO smsVO){
 
		List<SmsAttachVO> smsAttachList = null;
		
		try {
			smsAttachList = smsService.getSmsAttachList(smsVO);
		} catch(Exception e) {
			logger.error("smsService.getSmsAttachList error = " + e);
		}
		return smsAttachList;
	}
	
	private void setTargetStatus( List<SmsVO> smsTargetList, SmsVO smsTarget, String status){
		try {
			 
			if(smsTargetList!=null && smsTargetList.size() > 0 ) {
				try {
					for (int i= 0; i < smsTargetList.size(); i ++) {
						SmsVO smsVO = new SmsVO(); 
						smsVO.setMsgid(smsTargetList.get(i).getMsgid());
						smsVO.setKeygen(smsTargetList.get(i).getKeygen());
						smsVO.setStatus(status);
						smsService.updateSmsStatus(smsVO);
					}
				} catch(Exception e) {
					logger.error("smsService.updateSmsStatus error = " + e);
				}
			} else if (smsTarget != null) {
				smsTarget.setStatus(status);
				smsService.updateSmsStatus(smsTarget);
			} else {
				System.out.println("SMS Set Target Status Data is Nothing");
			}
		} catch(Exception e) {
			logger.error("smsService.setTargetStatus error = " + e);
		}
	}
	
	private SegmentVO getSegment(int segNo) {
		SegmentVO segmentVO = null;
		try {
			segmentVO = smsService.getSegment(segNo);
		} catch (Exception e) {
			logger.error("smsService.getSegment error = " + e);
			segmentVO = null; 
		}
		return segmentVO;
	}
	
	private SegmentMemberVO ReadSegmentFile(SegmentVO segmentVO){
		SegmentMemberVO memberVO = null;
		String filePath = fileUploadPath + "/" + segmentVO.getSegFlPath();
		try {
			memberVO = FileUtil.getMemberList(filePath);
			
		} catch(Exception e) {
			logger.error("PushSend ReadSegmentFile error = " + e);
			e.printStackTrace();
		}
		
		return memberVO;
	}
	
	private SegmentMemberVO ReadSegmentQuery(SegmentVO segmentVO){
		DbConnVO dbConnInfo = null;
		try {
			dbConnInfo = smsService.getDbConnInfo(segmentVO.getDbConnNo());
		} catch(Exception e) {
			logger.error("smsService.getDbConnInfo error = " + e);
		}
		
		SegmentMemberVO memberVO = null;
		
		if(dbConnInfo != null) {
			DBUtil dbUtil = new DBUtil();
			String dbDriver = dbConnInfo.getDbDriver();
			String dbUrl = dbConnInfo.getDbUrl();
			String loginId = dbConnInfo.getLoginId();
			String loginPwd = EncryptUtil.getJasyptDecryptedString(algorithm, keystring, dbConnInfo.getLoginPwd());
			//String loginPwd = dbConnInfo.getLoginPwd();
			
			memberVO = dbUtil.getMemberList(dbDriver, dbUrl, loginId, loginPwd, segmentVO);
		}
		return memberVO;
	}
	
	private int insertMessageQueue(SmsVO smsVO, SegmentVO segmentVO, SegmentMemberVO segmentMemberVO, String sendDate) {
		int result = 0;
		
		String[] mergeCols = null;
		
		List<SmsQueueVO> listReceiver = new ArrayList<SmsQueueVO>();
		
		String title =smsVO.getSmsName();
		String message =smsVO.getSmsMessage();
		
		if (!"".equals(segmentVO.getMergeCol())) {
			mergeCols = segmentVO.getMergeCol().split(",");
		}
		
		List<SmsAttachVO> smsAttachList= null;
		try {
			smsAttachList = getFileAttachList(smsVO);
		} catch (Exception e) {
			logger.error("SmsSend getFileAttachList error = " + e);
		}
		
		List<HashMap<String,String>> memberList = segmentMemberVO.getMemberList();
		SmsQueueVO smsQueueVO = new SmsQueueVO();
		
		if(memberList != null && memberList.size() > 0) {
			for(int i=0;i<memberList.size();i++) {
				HashMap<String,String> member = (HashMap<String,String>)memberList.get(i);
				
				smsQueueVO = setMergeData( mergeCols, member, message, title);
				if(smsAttachList !=null && smsAttachList.size() > 0 ) {
					smsQueueVO.setAttachedFile(smsAttachList.get(0).getAttFlPath());
				}
				insertQueue(smsVO, smsQueueVO, sendDate, listReceiver);
			}
			
			try {
				if("000".equals(smsQueueVO.getValidStatus())) {
					logger.debug("대상자수 : "+listReceiver.size());
					result = smsService.insertSmsQueue(listReceiver);
					logger.debug("발송완료");
				} else if("009".equals(smsQueueVO.getValidStatus())) { //PHONE번호없으므로 LOGFILE에 저장됨 
					logger.error("SmsSend insertMessageQueue Phone Number Not Exist  : " + "");
				} else {
					result = insertSendLog(smsQueueVO);
				}
			} catch(Exception e) {
				logger.error("SmsSend insertMessageQueue error = " + e);
				e.printStackTrace();
				result = -9;
			}
		}
		
		try {
			List<SmsPhoneVO> smsPhoneList = smsService.getSmsPhoneList(smsVO);
			
			
			
			if(smsPhoneList != null && smsPhoneList.size() > 0) {
				
				String noMergeBody = StringUtil.repalcePatternCharAlias(smsVO.getSmsMessage());
				String noMergeTitle = StringUtil.repalcePatternCharAlias(smsVO.getSmsName());
				for (int m = 0; m < smsPhoneList.size(); m++) {
					
					smsQueueVO.setSubject(noMergeTitle);
					smsQueueVO.setMsgBody(noMergeBody);
					smsQueueVO.setReBody(noMergeBody);
					smsQueueVO.setValidStatus("000");
					smsQueueVO.setDestPhone(getDecryptedString("PHONE", (String)smsPhoneList.get(m).getPhone()));
					
					if(smsAttachList !=null && smsAttachList.size() > 0 ) {
						smsQueueVO.setAttachedFile(smsAttachList.get(0).getAttFlPath());
					}
					insertQueue(smsVO, smsQueueVO, sendDate, listReceiver);
				}
				
				try {
					if("000".equals(smsQueueVO.getValidStatus())) {
						logger.debug("대상자수 : "+listReceiver.size());
						result = smsService.insertSmsQueue(listReceiver);
						logger.debug("발송완료");
					} else if("009".equals(smsQueueVO.getValidStatus())) { //PHONE번호없으므로 LOGFILE에 저장됨 
						logger.error("SmsSend insertMessageQueue Phone Number Not Exist  : " + "");
					} else {
						result = insertSendLog(smsQueueVO);
					}
				} catch(Exception e) {
					logger.error("SmsSend insertMessageQueue error = " + e);
					e.printStackTrace();
					result = -9;
				}
			}
		} catch (Exception e) {
			logger.error("SmsSend insertMessageQueue AddPhone error = " + e);
		}
		return result;
	}
	
	private void insertQueue(SmsVO smsVO,SmsQueueVO smsQueueVO, String sendDate, List<SmsQueueVO> listReceiver) {
		
		int smsGubun = 0;
		int result = 0;
		switch(smsVO.getGubun()) {
		case "000": //SMS 
			smsGubun = 0;
			break;
		case "001": //LMS
			smsGubun = 5;
			break;
		case "002": //MMS
			smsGubun = 5;
			break;
		case "004": //KAKAO(알림톡)
			smsGubun = 6;
			smsQueueVO.setTemplateCode(smsVO.getTempCd());
			smsQueueVO.setReType("MMS");
			break;
		default :
			smsGubun = 0;
		}
		
		smsQueueVO.setMsgid(smsVO.getMsgid());
		smsQueueVO.setKeygen(smsVO.getKeygen());
		smsQueueVO.setUmid(umid);
		smsQueueVO.setMsgType(smsGubun);
		smsQueueVO.setStatus(0);
		smsQueueVO.setSendPhone(getDecryptedString("SEND_TELNO",smsVO.getSendTelno()));
		smsQueueVO.setSendName(smsVO.getSendNm());
		smsQueueVO.setNationCode("82");
		smsQueueVO.setSenderKey(senderKey);
		smsQueueVO.setTimeout(timeout);
		smsQueueVO.setRePart("C"); 
		smsQueueVO.setAdFlag(smsVO.getLegalYn());
		smsQueueVO.setSendTime(sendDate);
		smsQueueVO.setUserId(smsVO.getUserId());
		smsQueueVO.setDeptNo(smsVO.getDeptNo());
		smsQueueVO.setRegDt(sendDate);
		smsQueueVO.setMsgTyp(smsVO.getGubun());
		smsQueueVO.setSendTyp(smsVO.getSendTyp());
		
//		System.out.println("smsQueueVO     umid         : " + smsQueueVO.getUmid());
//		System.out.println("smsQueueVO     msgType      : " + smsQueueVO.getMsgType());
//		System.out.println("smsQueueVO     status       : " + smsQueueVO.getStatus());
//		System.out.println("smsQueueVO     DestPhone    : " + smsQueueVO.getDestPhone());
//		System.out.println("smsQueueVO     SendPhone    : " + smsQueueVO.getSendPhone());
//		System.out.println("smsQueueVO     DestName     : " + smsQueueVO.getDestName());
//		System.out.println("smsQueueVO     SendName     : " + smsQueueVO.getSendName());
//		System.out.println("smsQueueVO     Subject      : " + smsQueueVO.getSubject());
//		System.out.println("smsQueueVO     MsgBody      : " + smsQueueVO.getMsgBody());
//		System.out.println("smsQueueVO     NationCode   : " + smsQueueVO.getNationCode());
//		System.out.println("smsQueueVO     SenderKey    : " + smsQueueVO.getSenderKey());
//		System.out.println("smsQueueVO     TemplateCode : " + smsQueueVO.getTemplateCode());
//		System.out.println("smsQueueVO     Timeout      : " + smsQueueVO.getTimeout());
//		System.out.println("smsQueueVO     ReType       : " + smsQueueVO.getReType());
//		System.out.println("smsQueueVO     ReBody       : " + smsQueueVO.getReBody());
//		System.out.println("smsQueueVO     RePart       : " + smsQueueVO.getRePart());
//		System.out.println("smsQueueVO     AdFlag       : " + smsQueueVO.getAdFlag());
//		System.out.println("smsQueueVO     Msgid        : " + smsQueueVO.getMsgid());
//		System.out.println("smsQueueVO     Keygen       : " + smsQueueVO.getKeygen()); 
		listReceiver.add(smsQueueVO);
		
	}
	private int insertSendLog(SmsQueueVO smsQueueVO) {
		int result = 0; 
		SmsSendLogVO smsSendLogVO = new SmsSendLogVO();
		
		smsSendLogVO.setMsgid(smsQueueVO.getMsgid());
		smsSendLogVO.setKeygen(smsQueueVO.getKeygen());
		smsSendLogVO.setPhone(smsQueueVO.getDestPhone());
		smsSendLogVO.setSendDt(smsQueueVO.getSendTime());
		smsSendLogVO.setId(smsQueueVO.getId());
		smsSendLogVO.setName(smsQueueVO.getDestName());
		smsSendLogVO.setEmail(smsQueueVO.getEmail());
		smsSendLogVO.setUserId(smsQueueVO.getUserId());
		smsSendLogVO.setDeptNo(smsQueueVO.getDeptNo());
		smsSendLogVO.setSendTyp(smsQueueVO.getSendTyp());
		smsSendLogVO.setMsgTyp(smsQueueVO.getMsgTyp());
		smsSendLogVO.setCmid(smsQueueVO.getCmid());
		smsSendLogVO.setUmid(smsQueueVO.getUmid());
		smsSendLogVO.setSendTelno(smsQueueVO.getSendPhone());
		smsSendLogVO.setSendNm(smsQueueVO.getSendName());
		smsSendLogVO.setReType(smsQueueVO.getReType());
		smsSendLogVO.setSendTyp(smsQueueVO.getMsgTyp());
		smsSendLogVO.setRsltCd("0");
		smsSendLogVO.setRcode("001");
		smsSendLogVO.setRetryCnt(0);
		smsSendLogVO.setRegDt(smsQueueVO.getRegDt());
		
		try {
			result = smsService.insertSmsLog(smsSendLogVO);
		} catch(Exception e) {
			logger.error("SmsSend insertSendLog error = " + e);
			e.printStackTrace();
			result = -9;
		}
		return result;
		
	}
	
	private SmsQueueVO setMergeData(String[] mergeCols, HashMap<String,String> segmenmtCols, String message, String title) {
		SmsQueueVO smsQueueVO = new SmsQueueVO();
		
		String validStatus ="000";
		String decStr = "";
		String reBody = "";
		
		for(int i = 0; i < mergeCols.length; i++ ) {
			
			decStr = getDecryptedString(mergeCols[i], (String)segmenmtCols.get(mergeCols[i]) );
			
			if (segmenmtCols.get(mergeCols[i]) != null) {
				message = message.replace("$:" + mergeCols[i] +":$", decStr);
				title = title.replace("$:" + mergeCols[i] +":$", decStr);
				reBody = StringUtil.repalcePatternCharAlias(message);
			}
			if(mergeCols[i].equalsIgnoreCase("ID")){
				smsQueueVO.setId(decStr);
			}
			if(mergeCols[i].equalsIgnoreCase("EMAIL")){
				if(decStr.equals((String)segmenmtCols.get(mergeCols[i]))){
					validStatus = "002";
				}
				smsQueueVO.setEmail(decStr);
			}
			if(mergeCols[i].equalsIgnoreCase("NAME")){
				smsQueueVO.setDestName(decStr);
			}
			if(mergeCols[i].equalsIgnoreCase("PHONE")){
				if(decStr.equals((String)segmenmtCols.get(mergeCols[i]))){
					validStatus = "002"; 
				}
				smsQueueVO.setDestPhone(decStr);
			}
		}
		
		if(smsQueueVO.getDestPhone() == null ||  "".equals(smsQueueVO.getDestPhone())) {
			validStatus = "009";
		}
		
		smsQueueVO.setSubject(title);
		smsQueueVO.setMsgBody(message);
		smsQueueVO.setReBody(reBody);
		smsQueueVO.setValidStatus(validStatus);
		
		return smsQueueVO;
	}

	public String getDecryptedString(String colNm,String data) {
		try {
			String result = "";
			String[] cols = encColumn.split("\\;");
			
			Loop1:
			for(int j=0;j<cols.length;j++) {
				if(cols[j].equals(colNm)) {
					//result = EncryptUtil.getJasyptDecryptedString(algorithm, keystring, data);
					result = data;
					break Loop1;
				} else {
					result = data;
				}
			}
			return result;
		} catch(Exception e) {
			logger.error("getDecryptedString orgStr [" + data + "] error = " + e);
			return data;
		}
	}	
}
