package kr.co.enders.engine.com;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import kr.co.enders.engine.svc.PushService;
import kr.co.enders.engine.util.BeanUtils;
import kr.co.enders.engine.util.DBUtil;
import kr.co.enders.engine.util.EncryptUtil;
import kr.co.enders.engine.util.FileUtil;
import kr.co.enders.engine.vo.SegmentVO;
import kr.co.enders.engine.vo.DbConnVO;
import kr.co.enders.engine.vo.PushQueueVO;
import kr.co.enders.engine.vo.PushSendLogVO;
import kr.co.enders.engine.vo.PushVO;
import kr.co.enders.engine.vo.SegmentMemberVO;

@Component("push")
public class PushSend { 
	
	private static Logger logger = LoggerFactory.getLogger(PushSend.class);
	
	@Value("${JASYPT.ALGORITHM}")
	private String algorithm;
	
	@Value("${JASYPT.KEYSTRING}")
	private String keystring;
	
	@Value("${FILE.UPLOAD_PATH}")
	private String fileUploadPath;
	
	@Value("${ENC.COLUMN}")
	private String encColumn;
	
	private PushService pushService;
	
	public void sendPush() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter sendFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String sendDate = now.format(sendFormatter);
		pushService =  (PushService) BeanUtils.getBean("pushService");
		List<PushVO> pushTargetList = getTargetList(sendDate);
		 
		if (pushTargetList == null || pushTargetList.size() < 1 ){
			//logger.debug("Push Send Target Not Exist");
			return;
		}
		int result = 0 ;
		try {
			for (int i= 0; i < pushTargetList.size(); i ++) {
				SegmentVO segmentVO =  getSegment(pushTargetList.get(i).getSegNo());
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
						result = insertMessageQueue(pushTargetList.get(i),segmentVO, segmentMemberVO,sendDate);
					}
				} else {
					result = -1; 
				}
				
				if (result < 0 ) { 
					setTargetStatus(null, pushTargetList.get(i), "004");
				} else {
					setTargetStatus(null, pushTargetList.get(i), "003");
				}
			}
		} catch(Exception e) {
			logger.error("pushService.sendPush error = " + e);
			if(pushTargetList != null) {
				setTargetStatus(pushTargetList, null, "004");
			}
		}
	}
	
	private List<PushVO> getTargetList(String sendDate){
		PushVO pushVO = new PushVO();
		pushVO.setWorkStatus("001");
		pushVO.setStatus("000");
		pushVO.setSendDt(sendDate); 
		List<PushVO> pushTargetList = null;
		
		try {
			pushTargetList = pushService.getPushListTarget(pushVO);
			if(pushTargetList!=null && pushTargetList.size() > 0 ) {
				System.out.println("PushListTarget " + Integer.toString(pushTargetList.size()));
				try {
					for (int i= 0; i < pushTargetList.size(); i ++) {
						pushVO.setPushmessageId(pushTargetList.get(i).getPushmessageId());
						pushVO.setSendDt(sendDate);
						pushVO.setWorkStatus("002");
						pushService.updatePushStatus(pushVO);
					}
				} catch(Exception e) {
					logger.error("pushService.updatePushStatus error = " + e);
				}
			} else {
				//System.out.println("Push Send Target Data is Nothing");
			}
		} catch(Exception e) {
			logger.error("pushService.getPushListTarget error = " + e);
		}
		return pushTargetList;
	}
	
	private void setTargetStatus( List<PushVO> pushTargetList, PushVO pushTarget, String workStatus){
		try {
			 
			if(pushTargetList!=null && pushTargetList.size() > 0 ) {
				try {
					for (int i= 0; i < pushTargetList.size(); i ++) {
						PushVO pushVO = new PushVO(); 
						pushVO.setPushmessageId(pushTargetList.get(i).getPushmessageId());
						pushVO.setWorkStatus(workStatus);
						pushService.updatePushStatus(pushVO);
					}
				} catch(Exception e) {
					logger.error("pushService.updatePushStatus error = " + e);
				}
			} else if (pushTarget != null) {
				pushTarget.setWorkStatus(workStatus);
				pushService.updatePushStatus(pushTarget);
			} else {
				System.out.println("Push Set Target Status Data is Nothing");
			}
		} catch(Exception e) {
			logger.error("pushService.setTargetStatus error = " + e);
		}
	}
	
	private SegmentVO getSegment(int segNo) {
		SegmentVO segmentVO = null;
		try {
			segmentVO = pushService.getSegment(segNo);
		} catch (Exception e) {
			logger.error("pushService.getSegment error = " + e);
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
			dbConnInfo = pushService.getDbConnInfo(segmentVO.getDbConnNo());
		} catch(Exception e) {
			logger.error("pushService.getDbConnInfo error = " + e);
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
	
	private int insertMessageQueue(PushVO pushVO, SegmentVO segmentVO, SegmentMemberVO segmentMemberVO, String sendDate) {
		int result = 0;
		
		String[] mergeCols = null;
		
		String title =pushVO.getPushTitle();
		String message =pushVO.getPushMessage();
		
		if (!"".equals(segmentVO.getMergeCol())) {
			mergeCols = segmentVO.getMergeCol().split(",");
		}
		
		List<HashMap<String,String>> memberList = segmentMemberVO.getMemberList();
		if(memberList != null && memberList.size() > 0) {
			for(int i=0;i<memberList.size();i++) {
				HashMap<String,String> member = (HashMap<String,String>)memberList.get(i);
				
				PushQueueVO pushQueueVO  = setMergeData( mergeCols, member, message, title);
				pushQueueVO.setPushmessageId(pushVO.getPushmessageId());
				pushQueueVO.setSendDt(sendDate);
				pushQueueVO.setWorkStatus("000");
				pushQueueVO.setLogClsYn("0");
				pushQueueVO.setUserId(pushVO.getUserId());
				pushQueueVO.setDeptNo(pushVO.getDeptNo());
				pushQueueVO.setPushGubun(pushVO.getPushGubun());
				pushQueueVO.setFilePath(pushVO.getFilePath());
				pushQueueVO.setFileNm(pushVO.getFileNm());
				pushQueueVO.setFileSize(pushVO.getFileSize());
				pushQueueVO.setCallUrlTyp(pushVO.getCallUrlTyp());
				pushQueueVO.setSendTyp(pushVO.getSendTyp());
				pushQueueVO.setSmsYn(pushVO.getSmsYn());
				
				if (pushQueueVO.getOsGubun().equals("001")){
					pushQueueVO.setCallUri(pushVO.getCallUri());
				} else {
					pushQueueVO.setCallUri(pushVO.getCallUriIos());
				}
				pushQueueVO.setRegDt(sendDate);
				pushQueueVO.setRegId("ADMIN");
				
				System.out.println("pushQueueVO       pushmessageId : " + pushQueueVO.getPushmessageId());
				System.out.println("pushQueueVO       deviceIdentiNo: " + pushQueueVO.getDeviceIdentiNo());
				System.out.println("pushQueueVO       id : " + pushQueueVO.getId());
				System.out.println("pushQueueVO       name : " + pushQueueVO.getName());
				System.out.println("pushQueueVO       userId : " + pushQueueVO.getUserId());
				System.out.println("pushQueueVO       deptNo : " + pushQueueVO.getDeptNo());
				System.out.println("pushQueueVO       sendDt : " + pushQueueVO.getSendDt());
				System.out.println("pushQueueVO       sendStrDt : " + pushQueueVO.getSendStrDt());
				System.out.println("pushQueueVO       sendEndDt : " + pushQueueVO.getSendEndDt());
				System.out.println("pushQueueVO       pushTitle : " + pushQueueVO.getPushTitle());
				System.out.println("pushQueueVO       pushMessage : " + pushQueueVO.getPushMessage());
				System.out.println("pushQueueVO       pushGubun : " + pushQueueVO.getPushGubun());
				System.out.println("pushQueueVO       filePath : " + pushQueueVO.getFilePath());
				System.out.println("pushQueueVO       fileNm : " + pushQueueVO.getFileNm());
				System.out.println("pushQueueVO       fileSize : " + pushQueueVO.getFileSize());
				System.out.println("pushQueueVO       sendTyp : " + pushQueueVO.getSendTyp());
				System.out.println("pushQueueVO       osGubun : " + pushQueueVO.getOsGubun());
				System.out.println("pushQueueVO       callUrlTyp : " + pushQueueVO.getCallUrlTyp());
				System.out.println("pushQueueVO       callUri : " + pushQueueVO.getCallUri());
				System.out.println("pushQueueVO       smsYn : " + pushQueueVO.getSmsYn());
				System.out.println("pushQueueVO       workStatus : " + pushQueueVO.getWorkStatus());
				System.out.println("pushQueueVO       logClsYn : " + pushQueueVO.getLogClsYn());
				System.out.println("pushQueueVO       regId : " + pushQueueVO.getRegId());
				System.out.println("pushQueueVO       regDt : " + pushQueueVO.getRegDt());
				
				try {
					if("000".equals(pushQueueVO.getValidStatus())) {
						result = pushService.insertPushQueue(pushQueueVO);
					} else {
						result = insertSendLog(pushQueueVO);
					}
				} catch(Exception e) {
					logger.error("PushSend insertMessageQueue error = " + e);
					e.printStackTrace();
					result = -9;
				}
			}
		}
		return result;
	}
	
	private int insertSendLog(PushQueueVO pushQueueVO) {
		int result = 0; 
		PushSendLogVO pushSendLogVO = new PushSendLogVO();
		
		pushSendLogVO.setPushmessageId(pushQueueVO.getPushmessageId());	
		pushSendLogVO.setDeviceIdentiNo(pushQueueVO.getDeviceIdentiNo());
		pushSendLogVO.setId	(pushQueueVO.getId());
		pushSendLogVO.setName(pushQueueVO.getName());
		pushSendLogVO.setEmail(pushQueueVO.getEmail());
		pushSendLogVO.setPhone(pushQueueVO.getPhone());
		pushSendLogVO.setEnckey(pushQueueVO.getEnckey());
		pushSendLogVO.setBizkey(pushQueueVO.getBizkey());
		pushSendLogVO.setUserId(pushQueueVO.getUserId());
		pushSendLogVO.setDeptNo	(pushQueueVO.getDeptNo());
		pushSendLogVO.setSendDt(pushQueueVO.getSendDt());
		pushSendLogVO.setPushGubun(pushQueueVO.getPushGubun());	
		pushSendLogVO.setOsGubun(pushQueueVO.getOsGubun());
		pushSendLogVO.setSmsYn(pushQueueVO.getSmsYn());
		pushSendLogVO.setRsltCd("0");
		pushSendLogVO.setRcode("001");
		pushSendLogVO.setRetryCnt(0);
		pushSendLogVO.setRegDt(pushQueueVO.getRegDt());
		
		try {
			result = pushService.insertPushLog(pushSendLogVO);
		} catch(Exception e) {
			logger.error("PushSend insertSendLog error = " + e);
			e.printStackTrace();
			result = -9;
		}
		return result;
		
	}
	
	private PushQueueVO setMergeData(String[] mergeCols, HashMap<String,String> segmenmtCols, String message, String title) {
		PushQueueVO pushQueueVO = new PushQueueVO();
		
		String validStatus ="000";
		String decStr = "";
		
		for(int i = 0; i < mergeCols.length; i++ ) {
			
			decStr = getDecryptedString(mergeCols[i], (String)segmenmtCols.get(mergeCols[i]) );
			
			if (segmenmtCols.get(mergeCols[i]) != null) {
				message = message.replace("$:" + mergeCols[i] +":$", decStr);
				title = title.replace("$:" + mergeCols[i] +":$", decStr);
			}
			if(mergeCols[i].equalsIgnoreCase("ID")){
				pushQueueVO.setId(decStr);
			}
			if(mergeCols[i].equalsIgnoreCase("NAME")){
				pushQueueVO.setName(decStr);
			}
			if(mergeCols[i].equalsIgnoreCase("DEVICE_IDENTI_NO")){
				if(decStr.equals((String)segmenmtCols.get(mergeCols[i]))){
					validStatus = "002"; 
				}
				pushQueueVO.setDeviceIdentiNo(decStr);
			}
			if(mergeCols[i].equalsIgnoreCase("OS_GUBUN")){
				pushQueueVO.setOsGubun(decStr);
			}
			
			if(mergeCols[i].equalsIgnoreCase("PHONE")){
				if(decStr.equals((String)segmenmtCols.get(mergeCols[i]))){
					validStatus = "002"; 
				}
				pushQueueVO.setPhone(decStr);
			}
			if(mergeCols[i].equalsIgnoreCase("EMAIL")){
				if(decStr.equals((String)segmenmtCols.get(mergeCols[i]))){
					validStatus = "002"; 
				}
				pushQueueVO.setEmail(decStr);
			}
		}
		
		if("".equals(pushQueueVO.getId()) || "".equals(pushQueueVO.getName())) {
			validStatus = "009";
		}
		
		pushQueueVO.setPushMessage(message);
		pushQueueVO.setPushTitle(title);
		pushQueueVO.setValidStatus(validStatus);
		
		return pushQueueVO;
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
