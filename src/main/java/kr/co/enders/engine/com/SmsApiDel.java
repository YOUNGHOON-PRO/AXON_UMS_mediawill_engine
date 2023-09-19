package kr.co.enders.engine.com;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import org.springframework.beans.factory.annotation.Value; 
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import ch.qos.logback.core.joran.spi.Interpreter;
import kr.co.enders.engine.svc.SmsApiDelService;
import kr.co.enders.engine.svc.SmsApiService;
import kr.co.enders.engine.svc.SmsService;
import kr.co.enders.engine.util.BeanUtils;
import kr.co.enders.engine.util.DBUtil;
import kr.co.enders.engine.util.EncryptUtil;
import kr.co.enders.engine.util.FileUtil;
import kr.co.enders.engine.util.StringUtil;
import kr.co.enders.engine.vo.DbConnVO;
import kr.co.enders.engine.vo.SegmentMemberVO;
import kr.co.enders.engine.vo.SegmentVO;
import kr.co.enders.engine.vo.SmsApiVO;
import kr.co.enders.engine.vo.SmsAttachVO;
import kr.co.enders.engine.vo.SmsPhoneVO;
import kr.co.enders.engine.vo.SmsQueueVO;
import kr.co.enders.engine.vo.SmsSendLogVO;
import kr.co.enders.engine.vo.SmsVO;

@Component("smsApiDel")
public class SmsApiDel { 
	
	private static Logger logger = LoggerFactory.getLogger(SmsApiDel.class);
	
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
	
	@Value("${SMS.API.UPLOAD}")
	private String apiUpload;
	
	private SmsApiDelService smsApiDelService;

	
	public void apiDel() {
		
		smsApiDelService =  (SmsApiDelService) BeanUtils.getBean("smsApiDelService");
		
		try {
				 int rst = smsApiDelService.deleteApiDel();
				 logger.debug("SMS "+rst +" 건 삭제");
				
		} catch(Exception e) {
			logger.error("SmsApiDelService.apiDel error = " + e);
			
		}
	}
	
	public void kkoApiDel() {
		
		smsApiDelService =  (SmsApiDelService) BeanUtils.getBean("smsApiDelService");
		
		try {
				 int rst = smsApiDelService.deleteKkoApiDel();
				 logger.debug("KKO "+rst +" 건 삭제");
				
		} catch(Exception e) {
			logger.error("SmsApiDelService.apiDel error = " + e);
			
		}
	}
}
