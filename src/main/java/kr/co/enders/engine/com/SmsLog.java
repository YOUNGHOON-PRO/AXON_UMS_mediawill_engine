package kr.co.enders.engine.com;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import kr.co.enders.engine.svc.SmsLogService;
import kr.co.enders.engine.util.BeanUtils;
import kr.co.enders.engine.util.EncryptUtil;
import kr.co.enders.engine.vo.SmsLogVO;

@Component("smsLog")
public class SmsLog { 
	
	private static Logger logger = LoggerFactory.getLogger(SmsLog.class);
	
	@Value("${JASYPT.ALGORITHM}")
	private String algorithm;
	
	@Value("${JASYPT.KEYSTRING}")
	private String keystring;
	
	private SmsLogService smsLogService;
	
	
	public void transferLog() {
		
		String destPhone ="";
		String sendPhone ="";
		int result = 0;
		int result2 = 0;
		
		
		smsLogService =  (SmsLogService) BeanUtils.getBean("smsLogService");
		
		// 1. 테이블 정보 가져오기
		String smsLogTable = getSmsLogTable();
		
		// 1-1. 테이블이 없으면
		if ("0".equals(smsLogTable)){
			//logger.debug("Sms Log Table Not Exist !!!!!!!!!!!!");
			return;
	
		// 1.2 테이블이 있으면
		} else {
			SmsLogVO smsLogVO = new SmsLogVO();
			smsLogVO.setSmsLogTable(smsLogTable);
			
			// 2. 데이터 가져오기
			List<SmsLogVO> smsLogData = getSmsLogData(smsLogVO);
			//logger.debug(" SmsLogData.size() " +  smsLogData.size());
			
			if(smsLogData !=null && smsLogData.size() > 0) {
				
				for (int i= 0; i < smsLogData.size(); i ++) {
					
					if (smsLogData.get(i) != null) {
						smsLogData.get(i).setSmsLogTable(smsLogTable);
						
						//destPhone = EncryptUtil.getJasyptEncryptedString(algorithm, keystring, smsLogData.get(i).getDestPhone());
						//smsLogData.get(i).setDestPhone(destPhone);
						
						//sendPhone = EncryptUtil.getJasyptEncryptedString(algorithm, keystring, smsLogData.get(i).getSendPhone());
						//smsLogData.get(i).setSendPhone(sendPhone);
						
						try {
							// 3. neo_sms 데이터 만들기
							
							if(smsLogData.get(i).getMsgType() == 0) {
								smsLogData.get(i).setGubun("000");
							}else if(smsLogData.get(i).getMsgType() == 5){
								smsLogData.get(i).setGubun("001");
							}
							result = smsLogService.insertNeosmsData(smsLogData.get(i));
							if(result==1) {
								//logger.debug("Neo_sms 데이터 생성 " + result);	
							}else {
								//logger.debug("Neo_sms 데이터 존재 " + result);
							}
							
							// 4. 데이터 이관하기
							
							String k = Integer.toString(smsLogData.get(i).getMsgType());
							
							if("6".equals(k)) {
								smsLogData.get(i).setSmsSendYn("N");
							} 
							else if("0".equals(k) || "5".equals(k)){
								smsLogData.get(i).setSmsSendYn("Y");
							}
							else {
								smsLogData.get(i).setSmsSendYn("N");
							}
							
							result2 = smsLogService.insertSmsLogData(smsLogData.get(i));
							
							if(result==1) {
								//logger.debug("로그 이관 성공 "+ result2);	
							}else {
								//logger.debug("로그 이관 실패 " +result2);
							}
							
							result = 0;
							result2 = 0;
						} catch (Exception e) {
							logger.error("smsLogService.insertSmsLogData error = " + e);
						}
					}
				}
				logger.debug("로그이관 : " +smsLogData.size() +"건");
			}else {
				logger.debug("로그이관 : 0건");
			}
		}
		
	}
		
	
	/**********************************************
	 * SMS 로그테이블명 가져오기
	 *********************************************/
	private String getSmsLogTable() {
		 
		SmsLogVO smsLogVO = null;
		String str ="";
		
		try {
			smsLogVO = smsLogService.getSmsLogTable(smsLogVO);
			str = smsLogVO.getSmsLogTable();
			
		} catch(Exception e) {
			logger.error("smsLogService.getSmsLogTable error = " + e);
		}
		return str;
	}
	
	/**********************************************
	 * SMS 로그 데이터 가져오기
	 *********************************************/
	private List<SmsLogVO> getSmsLogData(SmsLogVO vo) {
		 
		List<SmsLogVO>  smsLogVO = null;
		
		try {
			smsLogVO =  smsLogService.getSmsLogData(vo);
			//logger.debug("Sms Log getSmsLogData : " + smsLogVO);
			
		} catch(Exception e) {
			logger.error("smsLogService.getSmsLogData error = " + e);
		}
		return smsLogVO;
	}
	
	/**********************************************
	 * SMS 로그데이터 이관
	 *********************************************/
	private int insertSmsLogData(List<SmsLogVO> vo) {
		 
		int result=0;
		
		try {
			result = smsLogService.insertSmsLogData((SmsLogVO) vo);
			logger.debug("Sms Log setTransferSmsLog : " +result);
			if(result==1) {
				logger.debug("성공");	
			}else {
				logger.debug("실패");
			}
			
		} catch(Exception e) {
			logger.error("smsLogService.setTransferSmsLog error = " + e);
		}
		return result;
	}
	
	
}
