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
import kr.co.enders.engine.svc.NeoEmailApiService;
import kr.co.enders.engine.svc.SmsApiService;
import kr.co.enders.engine.svc.SmsService;
import kr.co.enders.engine.svc.TsEmailApiService;
import kr.co.enders.engine.util.BeanUtils;
import kr.co.enders.engine.util.DBUtil;
import kr.co.enders.engine.util.EncryptUtil;
import kr.co.enders.engine.util.FileUtil;
import kr.co.enders.engine.util.StringUtil;
import kr.co.enders.engine.vo.DbConnVO;
import kr.co.enders.engine.vo.EmailApiVO;
import kr.co.enders.engine.vo.SegmentMemberVO;
import kr.co.enders.engine.vo.SegmentVO;
import kr.co.enders.engine.vo.SmsAttachVO;
import kr.co.enders.engine.vo.SmsPhoneVO;
import kr.co.enders.engine.vo.SmsQueueVO;
import kr.co.enders.engine.vo.SmsSendLogVO;
import kr.co.enders.engine.vo.SmsVO;

@Component("tsEmailApi")
public class TsEmailApiSend { 
	
	private static Logger logger = LoggerFactory.getLogger(TsEmailApiSend.class);
	
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
	
	@Value("${BIZCLIENT.KKO.SENDER_KEY}")
	private String kkoSenderKey;
	
	@Value("${BIZCLIENT.TIMEOUT}")
	private int timeout;
	
	@Value("${SMS.API.UPLOAD}")
	private String smsApiUpload;
	
	@Value("${KKO.API.UPLOAD}")
	private String kkoApiUpload;
	
	@Value("${NEO.EMAIL.API.UPLOAD}")
	private String neoEmailApiUpload;
	
	@Value("${TS.EMAIL.API.UPLOAD}")
	private String tsEmailApiUpload;
	
	private TsEmailApiService tsEmailApiService;

	public String currentDate() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter sendFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String sendDate = now.format(sendFormatter);
		
		return sendDate;
	}
	
	public void sendEmail() {
		String senderTel = "";
		
		tsEmailApiService =  (TsEmailApiService) BeanUtils.getBean("tsEmailApiService");
		
		int result = 0 ;
		try {
			
			/*
			 * API 파일 방식
			 * mergecols   : [PHONE, RECEIVEPHONENUMBER, STORENAME, MEMBERNO, MEMBERNAME, WITHDRAWALDATECONTEXT, ORDERDATECONTEXT, LICENSEEXPIREDDATECONTEXT]
			 * member	   : {MEMBERNAME=홍길일, STORENAME=, WITHDRAWALDATECONTEXT=07월01일(월), PHONE=01012345678, ORDERDATECONTEXT=07월01일(월) 13:00, LICENSEEXPIREDDATECONTEXT=08월01일(월) 13:00, MEMBERNO=test, RECEIVEPHONENUMBER=01012345678}
			 */
			SegmentMemberVO memberVO = new SegmentMemberVO();
			List<HashMap<String,String>> memberList = new ArrayList<HashMap<String, String>>();
			List<EmailApiVO> emailApiList =  new ArrayList<EmailApiVO>();
			
			
			HashMap jsonTohash  = new HashMap();
 			HashMap mainHash  = new HashMap();
			HashMap listHash = new HashMap();
			ArrayList listArray  = new ArrayList();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = null ;
			ObjectMapper objectMapper = new ObjectMapper();
			
			EmailApiVO emailApiVO = new EmailApiVO();

			File dir = new File(tsEmailApiUpload);
			String[] filenames = dir.list((f,name)->name.endsWith(".txt"));
			
			String requestFile ="";
			String requestFile2 ="";
			int strartFile = 0;
			int endFile = 0;
			int index =0;
			String fileName ="";
			ArrayList lastFile = new ArrayList();
			
			//전체파일 검색
			for (int i = 0; i < filenames.length; i++) {
				
				index=0;
				lastFile.clear();
				
				int Idx = filenames[i].lastIndexOf(".");
				String _fileName = filenames[i].substring(0,Idx);
				
				String[] gubun = _fileName.split("_");
				ArrayList gubunList = new ArrayList();
				for(int b=0; b<gubun.length; b++) {
					
					if(b==0) {
						requestFile = gubun[0];
					}
				}
				
				//파일 건수 비교 데이터 생성
				for(int q=0;q<filenames.length;q++) {
					

					int Idx2 = filenames[q].lastIndexOf(".");
					String _fileName2 = filenames[q].substring(0,Idx2);
					
					String[] gubun2 = _fileName.split("_");
					ArrayList gubunList2 = new ArrayList();
					for(int b=0; b<gubun2.length; b++) {
						gubunList.add(gubun2[b]);
						
						if(b==0) {
							requestFile2 = gubun[b];
						}
						if(b==1) {
							strartFile = Integer.parseInt(gubun[b]);
						}else if(b==2){
							endFile = Integer.parseInt(gubun[b]);
						}
					}
					
					
					if(requestFile2.equals(requestFile)) {  
						if(lastFile.size() != endFile){
							index = index+1;
						}
						
						lastFile.add(filenames[q]);
					}

					//두 개의 파일명이 같은가 
					if(requestFile.equals(requestFile2)) {
						
						//같은 파일명의 총건수와 받은 건수가 같은가
						if(index == endFile) {
							
							for(int p=0;p<lastFile.size();p++) {
								
								if(new File(tsEmailApiUpload+"/"+lastFile.get(p)).exists()) {
								
									jsonNode = objectMapper.readTree(new File(tsEmailApiUpload+"/"+lastFile.get(p)));
								    String target = ((String) lastFile.get(p)).substring(0, ((String) lastFile.get(p)).lastIndexOf('.')) + ".ING";
								    
								    File baseFile = new File(tsEmailApiUpload+"/"+lastFile.get(p));
								    File newFile = new File(tsEmailApiUpload+"/"+target);
								    File sucessFile = new File(tsEmailApiUpload+"/bak/"+lastFile.get(p)+"_"+currentDate());
								    
								    //파일 이름 변경
								    if(baseFile.exists()) {
								    	 boolean result2 = baseFile.renameTo(newFile);
								    }
								    
								    if(jsonNode.get("requestoption") != null){
										mainHash.put("requestoption", jsonNode.get("requestoption").asText());	
										emailApiVO.setRequestoption(jsonNode.get("requestoption").asText());
									}else{
										mainHash.put("requestoption", "");
										emailApiVO.setRequestoption("");
									}
									if(jsonNode.get("messagename") != null){
										mainHash.put("messagename", jsonNode.get("messagename").asText());
										emailApiVO.setMessagename(jsonNode.get("messagename").asText());
									}else{
										mainHash.put("messagename", "");
										emailApiVO.setMessagename("");
									}
									if(jsonNode.get("requestkey") != null){
										mainHash.put("requestkey", jsonNode.get("requestkey").asText());
										emailApiVO.setRequestKey(jsonNode.get("requestkey").asText());
									}else{
										mainHash.put("requestkey", "");
										emailApiVO.setRequestKey("");
									}
									if(jsonNode.get("pagesize") != null){
										mainHash.put("pagesize", jsonNode.get("pagesize").asText());
										emailApiVO.setPagesize(jsonNode.get("pagesize").asText());
									}else{
										mainHash.put("pagesize", "");
										emailApiVO.setPagesize("");
									}
									if(jsonNode.get("totalpagenumber") != null){
										mainHash.put("totalpagenumber", jsonNode.get("totalpagenumber").asText());
										emailApiVO.setTotalpagenumber(jsonNode.get("totalpagenumber").asText());
									}else{
										mainHash.put("totalpagenumber", "");
										emailApiVO.setTotalpagenumber("");
									}
									if(jsonNode.get("templatecode") != null){
										mainHash.put("templatecode", jsonNode.get("templatecode").asText());
										emailApiVO.setTemplatecode(jsonNode.get("templatecode").asText());
									}else{
										mainHash.put("templatecode", "");
										emailApiVO.setTemplatecode("");
									}
									if(jsonNode.get("pagenumber") != null){
										mainHash.put("pagenumber", jsonNode.get("pagenumber").asText());
										emailApiVO.setPagenumber(jsonNode.get("pagenumber").asText());
									}else{
										mainHash.put("pagenumber", "");
										emailApiVO.setPagenumber("");
									}
									if(jsonNode.get("campaigncode") != null){
										mainHash.put("campaigncode", jsonNode.get("campaigncode").asText());
										emailApiVO.setCampaigncode(jsonNode.get("campaigncode").asText());
									}else{
										mainHash.put("campaigncode", "");
										emailApiVO.setCampaigncode("");
									}
									if(jsonNode.get("campaignno") != null){
										mainHash.put("campaignno", jsonNode.get("campaignno").asText());
										emailApiVO.setCampaignno(jsonNode.get("campaignno").asText());
									}else{
										mainHash.put("campaignno", "");
										emailApiVO.setCampaignno("");
									}
									if(jsonNode.get("senderphonenumber") != null){
										mainHash.put("senderphonenumber", jsonNode.get("senderphonenumber").asText());
										emailApiVO.setSenderphonenumber(jsonNode.get("senderphonenumber").asText());
									}else{
										mainHash.put("senderphonenumber", "");
										emailApiVO.setSenderphonenumber("");
									}
									if(jsonNode.get("senderemail") != null){
										mainHash.put("senderemail", jsonNode.get("senderemail").asText());
										emailApiVO.setSenderemail(jsonNode.get("senderemail").asText());
									}else{
										mainHash.put("senderemail", "");
										emailApiVO.setSenderemail("");
									}
									if(jsonNode.get("sendduedatatime") != null){
										mainHash.put("sendduedatatime", jsonNode.get("sendduedatatime").asText());
										emailApiVO.setSendduedatatime(jsonNode.get("sendduedatatime").asText());
									}else{
										mainHash.put("sendduedatatime", "");
										emailApiVO.setSendduedatatime("");
									}
									
									ArrayNode jsonList =(ArrayNode) jsonNode.get("data");

									if( jsonList != null){
										for(int z=0;z< jsonList.size(); z++ ){
											JsonNode jsonNode2 = (JsonNode) jsonList.get(z);
											
											
											if(jsonNode2.get("receivedeviceno") != null){
												listHash.put("receivedeviceno", jsonNode2.get("receivedeviceno").asText());	
												emailApiVO.setReceivedeviceno(jsonNode2.get("receivedeviceno").asText());
											}else{
												listHash.put("receivedeviceno", "");
												emailApiVO.setReceivedeviceno("");
											}
											if(jsonNode2.get("distributorname") != null){
												listHash.put("distributorname", jsonNode2.get("distributorname").asText());
												emailApiVO.setDistributorname(jsonNode2.get("distributorname").asText());
											}else{
												listHash.put("distributorname", "");
												emailApiVO.setDistributorname("");
											}
											if(jsonNode2.get("orderno") != null){
												listHash.put("orderno", jsonNode2.get("orderno").asText());	
												emailApiVO.setOrderno(jsonNode2.get("orderno").asText());
											}else{
												listHash.put("orderno", "");
												emailApiVO.setOrderno("");
											}
											if(jsonNode2.get("withdrawaldatecontext") != null){
												listHash.put("withdrawaldatecontext", jsonNode2.get("withdrawaldatecontext").asText());
												emailApiVO.setWithdrawaldatecontext(jsonNode2.get("withdrawaldatecontext").asText());
											}else{
												listHash.put("withdrawaldatecontext", "");
												emailApiVO.setWithdrawaldatecontext("");
											}
											if(jsonNode2.get("subject") != null){
												listHash.put("subject", jsonNode2.get("subject").asText());	
												emailApiVO.setSubject(jsonNode2.get("subject").asText());
											}else{
												listHash.put("subject", "");
												emailApiVO.setSubject("");
											}
											if(jsonNode2.get("bankaccount") != null){
												listHash.put("bankaccount", jsonNode2.get("bankaccount").asText());
												emailApiVO.setBankaccount(jsonNode2.get("bankaccount").asText());
											}else{
												listHash.put("bankaccount", "");
												emailApiVO.setBankaccount("");
											}
											if(jsonNode2.get("storename") != null){
												listHash.put("storename", jsonNode2.get("storename").asText());
												emailApiVO.setStorename(jsonNode2.get("storename").asText());
											}else{
												listHash.put("storename", "");
												emailApiVO.setStorename("");
											}
											if(jsonNode2.get("receiveemail") != null){
												listHash.put("receiveemail", jsonNode2.get("receiveemail").asText());
												emailApiVO.setReceiveemail(jsonNode2.get("receiveemail").asText());
											}else{
												listHash.put("receiveemail", "");
												emailApiVO.setReceiveemail("");
											}
											if(jsonNode2.get("ordermonth") != null){
												listHash.put("ordermonth", jsonNode2.get("ordermonth").asText());
												emailApiVO.setOrdermonth(jsonNode2.get("ordermonth").asText());
											}else{
												listHash.put("ordermonth", "");
												emailApiVO.setOrdermonth("");
											}
											if(jsonNode2.get("encryptionkey") != null){
												listHash.put("encryptionkey", jsonNode2.get("encryptionkey").asText());
												emailApiVO.setEncryptionkey(jsonNode2.get("encryptionkey").asText());
											}else{
												listHash.put("encryptionkey", "");
												emailApiVO.setEncryptionkey("");
											}
											if(jsonNode2.get("orderdatecontext") != null){
												listHash.put("orderdatecontext", jsonNode2.get("orderdatecontext").asText());
												emailApiVO.setOrderdatecontext(jsonNode2.get("orderdatecontext").asText());
											}else{
												listHash.put("orderdatecontext", "");
												emailApiVO.setOrderdatecontext("");
											}
											if(jsonNode2.get("temporarypassword") != null){
												listHash.put("temporarypassword", jsonNode2.get("temporarypassword").asText());
												emailApiVO.setTemporarypassword(jsonNode2.get("temporarypassword").asText());
											}else{
												listHash.put("temporarypassword", "");
												emailApiVO.setTemporarypassword("");
											}
											if(jsonNode2.get("memberno") != null){
												listHash.put("memberno", jsonNode2.get("memberno").asText());
												emailApiVO.setMemberno(jsonNode2.get("memberno").asText());
											}else{
												listHash.put("memberno", "");
												emailApiVO.setMemberno("");
											}
											if(jsonNode2.get("licenseexpireddatecontext") != null){
												listHash.put("licenseexpireddatecontext", jsonNode2.get("licenseexpireddatecontext").asText());
												emailApiVO.setLicenseexpireddatecontext(jsonNode2.get("licenseexpireddatecontext").asText());
											}else{
												listHash.put("licenseexpireddatecontext", "");
												emailApiVO.setLicenseexpireddatecontext("");
											}
											if(jsonNode2.get("depositduedatetime") != null){
												listHash.put("depositduedatetime", jsonNode2.get("depositduedatetime").asText());
												emailApiVO.setDepositduedatetime(jsonNode2.get("depositduedatetime").asText());
											}else{
												listHash.put("depositduedatetime", "");
												emailApiVO.setDepositduedatetime("");
											}
											if(jsonNode2.get("receivephonenumber") != null){
												listHash.put("receivephonenumber", jsonNode2.get("receivephonenumber").asText());
												emailApiVO.setReceivephonenumber(jsonNode2.get("receivephonenumber").asText());
											}else{
												listHash.put("receivephonenumber", "");
												emailApiVO.setReceivephonenumber("");
											}
											if(jsonNode2.get("storeownerusername") != null){
												listHash.put("storeownerusername", jsonNode2.get("storeownerusername").asText());
												emailApiVO.setStoreownerusername(jsonNode2.get("storeownerusername").asText());
											}else{
												listHash.put("storeownerusername", "");
												emailApiVO.setStoreownerusername("");
											}
											if(jsonNode2.get("depositorname") != null){
												listHash.put("depositorname", jsonNode2.get("depositorname").asText());
												emailApiVO.setDepositorname(jsonNode2.get("depositorname").asText());
											}else{
												listHash.put("depositorname", "");
												emailApiVO.setDepositorname("");
											}
											if(jsonNode2.get("businesskey") != null){
												listHash.put("businesskey", jsonNode2.get("businesskey").asText());
												emailApiVO.setBusinesskey(jsonNode2.get("businesskey").asText());
											}else{
												listHash.put("businesskey", "");
												emailApiVO.setBusinesskey("");
											}
											if(jsonNode2.get("context") != null){
												listHash.put("context", jsonNode2.get("context").asText());
												emailApiVO.setContext(jsonNode2.get("context").asText());
											}else{
												listHash.put("context", "");
												emailApiVO.setContext("");
											}
											if(jsonNode2.get("moneyamount") != null){
												listHash.put("moneyamount", jsonNode2.get("moneyamount").asText());
												emailApiVO.setMoneyamount(jsonNode2.get("moneyamount").asText());
											}else{
												listHash.put("moneyamount", "");
												emailApiVO.setMoneyamount("");
											}
											if(jsonNode2.get("bankname") != null){
												listHash.put("bankname", jsonNode2.get("bankname").asText());
												emailApiVO.setBankname(jsonNode2.get("bankname").asText());
											}else{
												listHash.put("bankname", "");
												emailApiVO.setBankname("");
											}
											if(jsonNode2.get("verificationcode") != null){
												listHash.put("verificationcode", jsonNode2.get("verificationcode").asText());
												emailApiVO.setVerificationcode(jsonNode2.get("verificationcode").asText());
											}else{
												listHash.put("verificationcode", "");
												emailApiVO.setVerificationcode("");
											}
											if(jsonNode2.get("membername") != null){
												listHash.put("membername", jsonNode2.get("membername").asText());	
												emailApiVO.setMembername(jsonNode2.get("membername").asText());
											}else{
												listHash.put("membername", "");
												emailApiVO.setMembername("");
											}
											if(jsonNode2.get("centerphonenumber") != null){
												listHash.put("centerphonenumber", jsonNode2.get("centerphonenumber").asText());	
												emailApiVO.setCenterphonenumber(jsonNode2.get("centerphonenumber").asText());
											}else{
												listHash.put("centerphonenumber", "");
												emailApiVO.setCenterphonenumber("");
											}
											if(jsonNode2.get("linkurlmyoffice") != null){
												listHash.put("linkurlmyoffice", jsonNode2.get("linkurlmyoffice").asText());	
												emailApiVO.setLinkurlmyoffice(jsonNode2.get("linkurlmyoffice").asText());
											}else{
												listHash.put("linkurlmyoffice", "");
												emailApiVO.setLinkurlmyoffice("");
											}
											if(jsonNode2.get("membernumber") != null){
												listHash.put("membernumber", jsonNode2.get("membernumber").asText());	
												emailApiVO.setMembernumber(jsonNode2.get("membernumber").asText());
											}else{
												listHash.put("membernumber", "");
												emailApiVO.setMembernumber("");
											}
											if(jsonNode2.get("map1") != null){
												listHash.put("map1", jsonNode2.get("map1").asText());	
												emailApiVO.setMap1(jsonNode2.get("map1").asText());
											}else{
												listHash.put("map1", "");
												emailApiVO.setMap1("");
											}
											if(jsonNode2.get("map2") != null){
												listHash.put("map2", jsonNode2.get("map2").asText());	
												emailApiVO.setMap2(jsonNode2.get("map2").asText());
											}else{
												listHash.put("map2", "");
												emailApiVO.setMap1("");
											}
											if(jsonNode2.get("map3") != null){
												listHash.put("map3", jsonNode2.get("map3").asText());	
												emailApiVO.setMap3(jsonNode2.get("map3").asText());
											}else{
												listHash.put("map3", "");
												emailApiVO.setMap3("");
											}
											if(jsonNode2.get("map4") != null){
												listHash.put("map4", jsonNode2.get("map4").asText());	
												emailApiVO.setMap4(jsonNode2.get("map4").asText());
											}else{
												listHash.put("map4", "");
												emailApiVO.setMap4("");
											}
											if(jsonNode2.get("map5") != null){
												listHash.put("map5", jsonNode2.get("map5").asText());	
												emailApiVO.setMap5(jsonNode2.get("map5").asText());
											}else{
												listHash.put("map5", "");
												emailApiVO.setMap5("");
											}
											if(jsonNode2.get("map6") != null){
												listHash.put("map6", jsonNode2.get("map6").asText());	
												emailApiVO.setMap6(jsonNode2.get("map6").asText());
											}else{
												listHash.put("map6", "");
												emailApiVO.setMap6("");
											}
											if(jsonNode2.get("map7") != null){
												listHash.put("map7", jsonNode2.get("map7").asText());	
												emailApiVO.setMap7(jsonNode2.get("map7").asText());
											}else{
												listHash.put("map7", "");
												emailApiVO.setMap7("");
											}
											if(jsonNode2.get("map8") != null){
												listHash.put("map8", jsonNode2.get("map8").asText());	
												emailApiVO.setMap8(jsonNode2.get("map8").asText());
											}else{
												listHash.put("map8", "");
												emailApiVO.setMap8("");
											}
											if(jsonNode2.get("map9") != null){
												listHash.put("map9", jsonNode2.get("map9").asText());	
												emailApiVO.setMap9(jsonNode2.get("map9").asText());
											}else{
												listHash.put("map9", "");
												emailApiVO.setMap9("");
											}
											if(jsonNode2.get("map10") != null){
												listHash.put("map10", jsonNode2.get("map10").asText());	
												emailApiVO.setMap10(jsonNode2.get("map10").asText());
											}else{
												listHash.put("map10", "");
												emailApiVO.setMap10("");
											}
											if(jsonNode2.get("map11") != null){
												listHash.put("map11", jsonNode2.get("map11").asText());	
												emailApiVO.setMap11(jsonNode2.get("map11").asText());
											}else{
												listHash.put("map11", "");
												emailApiVO.setMap11("");
											}
											if(jsonNode2.get("map12") != null){
												listHash.put("map12", jsonNode2.get("map12").asText());	
												emailApiVO.setMap12(jsonNode2.get("map12").asText());
											}else{
												listHash.put("map12", "");
												emailApiVO.setMap12("");
											}
											if(jsonNode2.get("map13") != null){
												listHash.put("map13", jsonNode2.get("map13").asText());	
												emailApiVO.setMap13(jsonNode2.get("map13").asText());
											}else{
												listHash.put("map13", "");
												emailApiVO.setMap13("");
											}
											if(jsonNode2.get("map14") != null){
												listHash.put("map14", jsonNode2.get("map14").asText());	
												emailApiVO.setMap14(jsonNode2.get("map14").asText());
											}else{
												listHash.put("map14", "");
												emailApiVO.setMap14("");
											}
											if(jsonNode2.get("map15") != null){
												listHash.put("map15", jsonNode2.get("map15").asText());	
												emailApiVO.setMap15(jsonNode2.get("map15").asText());
											}else{
												listHash.put("map15", "");
												emailApiVO.setMap15("");
											}
											
											//memberList.add(listHash);
											emailApiList.add(emailApiVO);
											//smsApiService.insertSmsApiStg(SmsApiList);
											tsEmailApiService.insertTsEmailApiStg(emailApiList);
											emailApiList.clear();											
										}
										
										//memberVO.setMemberList(memberList);
										
										 //파일 이동
										try {
											 if (newFile.exists())
											  {
												 Files.move(newFile.toPath(), sucessFile.toPath(), StandardCopyOption.ATOMIC_MOVE);
											  }

										} catch (Exception e) {
										   e.printStackTrace();
										}
									}
									//logger.debug("총 파일 건 수 : " + filenames.length+"건");
									//logger.debug("파일 건수 : " + p);
									//logger.debug("받은파일 건 수 : " + index);
									//logger.debug("전체파일 건 수 : " + endFile);
									logger.debug("파일 명 : " + lastFile.get(p));
									
									//emailApiVO.getSenderphonenumber();
									//emailApiVO.setPlanUserId("ADMIN");
									//emailApiVO.setUserId("ADMIN");
									//emailApiVO.setDeptNo(1);
									//emailApiVO.setSendYm((emailApiVO.getSendduedatatime().toString()).substring(0,8));
									//emailApiVO.setDeptNo(1);
									//emailApiVO.setSmsStatus("000");
									//emailApiVO.setSendTyp("001");
									//emailApiVO.setSendNm("슈퍼관리자");
									//emailApiVO.setRegId("ADMIN");
									//emailApiVO.setExeUserId("ADMIN");
									//emailApiVO.setGubun("004");
									//emailApiVO.setSenderphonenumber(EncryptUtil.getJasyptEncryptedString(algorithm, keystring, emailApiVO.getSenderphonenumber()));
									//emailApiVO.setSendStartDt(currentDate());
									//smsApiService.insertNeoSmsKko(emailApiVO);
									}
							}
						}
					}
				}
			}
			
		} catch(Exception e) {
			logger.error("kkoService.sendSms error = " + e);
			
		}
	}
	
/*	
	public void insertMsg() {
		String senderTel = "";
		int result =0;
		
					List<emailApiVO> smsApiTargetList = getTargetList(currentDate());
					if (smsApiTargetList == null || smsApiTargetList.size() < 1 ){
						//logger.debug("KKO Api Send Target Not Exist");
						return;
					}
					
					
					//APi DB 방식
					for (int i= 0; i < smsApiTargetList.size(); i ++) {
						
						SegmentVO segmentVO =  getSegment(smsApiTargetList.get(i).getSegNo());
						SegmentMemberVO segmentMemberVO = new SegmentMemberVO();
						
						if(segmentVO != null && !"".equals(segmentVO.getCreateTy())) {
							if("000".equals(segmentVO.getCreateTy())) { //추출조건
								segmentMemberVO = ReadSegmentQuery(segmentVO);
							} else if("002".equals(segmentVO.getCreateTy())) { //직접SQL
								segmentVO.setRequestKey(smsApiTargetList.get(0).getRequestKey());
								segmentMemberVO = ReadSegmentQuery(segmentVO);
							} else if ("003".equals(segmentVO.getCreateTy())) { //파일연동
								segmentMemberVO = ReadSegmentFile(segmentVO);
							}
							if (segmentMemberVO != null) {
								senderTel = EncryptUtil.getJasyptDecryptedString(algorithm, keystring, smsApiTargetList.get(i).getSendTelno());
								smsApiTargetList.get(i).setSendTelno(senderTel);
								result = insertMessageQueue(smsApiTargetList.get(i),segmentVO, segmentMemberVO, currentDate());
							}
						} else {
							result = -1; 
						}
						
						if (result < 0 ) { 
							setTargetStatus(smsApiTargetList,  "004");
							logger.debug("발송실패");
							logger.debug("RequestKey : " + smsApiTargetList.get(i).getRequestKey());
							logger.debug("CampusNo : " + smsApiTargetList.get(i).getCampusNo());
							logger.debug("SegNo : " + smsApiTargetList.get(i).getSegNo());
							logger.debug("Gubun : " + smsApiTargetList.get(i).getGubun());
							logger.debug("Templatecode : " + smsApiTargetList.get(i).getTemplatecode());
							
						} else {
							setTargetStatus(smsApiTargetList,  "003");
							logger.debug("발송완료");
						}
					}
	}
*/	

	
/*	
	private List<emailApiVO> getTargetList(String sendDate){
		emailApiVO emailApiVO = new emailApiVO();
		emailApiVO.setSendDate(sendDate); 
		List<emailApiVO> smsTargetList = null;
		
		try {
			smsTargetList = smsApiService.getKkoSmsListTarget(emailApiVO);
			if(smsTargetList!=null && smsTargetList.size() > 0 ) {
				logger.debug("발송요청 : " + Integer.toString(smsTargetList.size())+ "건");
				
				try {
					for (int i= 0; i < smsTargetList.size(); i ++) {
						emailApiVO.setMsgid(smsTargetList.get(i).getMsgid());
						emailApiVO.setKeygen(smsTargetList.get(i).getKeygen());
						emailApiVO.setSendStartDt(sendDate);
						emailApiVO.setUpDt(currentDate());
						emailApiVO.setStatus("002");
						emailApiVO.setRequestKey(smsTargetList.get(i).getRequestKey());
						emailApiVO.setWeblinkJson(smsTargetList.get(i).getTempCd()+".json");
						smsApiService.updateSmsStatus(emailApiVO);
						smsTargetList.get(i).setSendTelno(EncryptUtil.getJasyptDecryptedString(algorithm, keystring,smsTargetList.get(i).getSendTelno()));
					}
				} catch(Exception e) {
					//logger.error("KKO smsService.updateSmsStatus error = " + e);
				}
			} else {
				//logger.debug("KKO SMS Send Target Data is Nothing");
			}
		} catch(Exception e) {
			logger.error("KKO smsService.getSmsListTarget error = " + e);
		}
		return smsTargetList;
	}
	
	private List<SmsAttachVO> getFileAttachList(emailApiVO emailApiVO){
 
		List<SmsAttachVO> smsAttachList = null;
		
		try {
			smsAttachList = smsApiService.getSmsAttachList(emailApiVO);
		} catch(Exception e) {
			logger.error("KKO smsService.getSmsAttachList error = " + e);
		}
		return smsAttachList;
	}
	
	private void setTargetStatus( List<emailApiVO> smsTargetList,  String status){
		try {
			 
			if(smsTargetList!=null && smsTargetList.size() > 0 ) {
				try {
					for (int i= 0; i < smsTargetList.size(); i ++) {
						emailApiVO emailApiVO = new emailApiVO(); 
						emailApiVO.setMsgid(smsTargetList.get(i).getMsgid());
						emailApiVO.setKeygen(smsTargetList.get(i).getKeygen());
						emailApiVO.setStatus(status);
						emailApiVO.setUserId(smsTargetList.get(i).getUserId());
						emailApiVO.setRequestKey(smsTargetList.get(i).getRequestKey());
						emailApiVO.setUpDt(currentDate());
						emailApiVO.setSendEndDt(currentDate());
						smsApiService.updateSmsStatus(emailApiVO);
					}
				} catch(Exception e) {
					logger.error("KKO smsService.updateSmsStatus error = " + e);
				}
			}  else {
				logger.debug("KKO SMS Set Target Status Data is Nothing");
			}
		} catch(Exception e) {
			logger.error("KKO smsService.setTargetStatus error = " + e);
		}
	}
	
	private SegmentVO getSegment(int segNo) {
		SegmentVO segmentVO = null;
		try {
			segmentVO = smsApiService.getSegment(segNo);
		} catch (Exception e) {
			logger.error("KKO smsService.getSegment error = " + e);
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
			dbConnInfo = smsApiService.getDbConnInfo(segmentVO.getDbConnNo());
		} catch(Exception e) {
			logger.debug("KKO smsService.getDbConnInfo error = " + e);
		}
		
		SegmentMemberVO memberVO = null;
		if(dbConnInfo != null) {
			DBUtil dbUtil = new DBUtil();
			String dbDriver = dbConnInfo.getDbDriver();
			String dbUrl = dbConnInfo.getDbUrl();
			String loginId = dbConnInfo.getLoginId();
			String loginPwd = EncryptUtil.getJasyptDecryptedString(algorithm, keystring, dbConnInfo.getLoginPwd());
			
			memberVO = dbUtil.getMemberList(dbDriver, dbUrl, loginId, loginPwd, segmentVO);
		}
		return memberVO;
	}
	
	private int insertMessageQueue(emailApiVO emailApiVO, SegmentVO segmentVO, SegmentMemberVO segmentMemberVO, String sendDate) {
		int result = 0;

		String[] mergeCols = null;
		
		String title =emailApiVO.getSmsName();
		String message =emailApiVO.getSmsMessage();
		
		if (!"".equals(segmentVO.getMergeCol())) {
			mergeCols = segmentVO.getMergeCol().split(",");
		}
		
		List<SmsAttachVO> smsAttachList= null;
		try {
			smsAttachList = getFileAttachList(emailApiVO);
		} catch (Exception e) {
			logger.error("KKO SmsSend getFileAttachList error = " + e);
		}
		
		List<HashMap<String,String>> memberList = segmentMemberVO.getMemberList();
		
		logger.debug("대상자수 : " + memberList.size()+"명" );
		
		List<SmsQueueVO> listReceiver = new ArrayList<SmsQueueVO>();
		
		if(memberList != null && memberList.size() > 0) {
			for(int i=0;i<memberList.size();i++) {
				HashMap<String,String> member = (HashMap<String,String>)memberList.get(i);
				
				SmsQueueVO smsQueueVO = setMergeData( mergeCols, member, message, title);
				if(smsAttachList !=null && smsAttachList.size() > 0 ) {
					smsQueueVO.setAttachedFile(smsAttachList.get(0).getAttFlPath());
				}
				insertQueue(emailApiVO, smsQueueVO, sendDate, listReceiver);
			}
		}
		
		try {
			if(listReceiver != null && listReceiver.size() > 0) {
				if("Y".equals(emailApiVO.getWeblinkYn())) {
					logger.debug("웹링크 : " + emailApiVO.getWeblinkYn());
					result = smsApiService.insertSmsWeblinkQueue(listReceiver);
				}else {
					logger.debug("웹링크 아님 : "+ emailApiVO.getWeblinkYn());
					result = smsApiService.insertSmsQueue(listReceiver);	
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	private List<SmsQueueVO> insertQueue(emailApiVO emailApiVO,SmsQueueVO smsQueueVO, String sendDate, List<SmsQueueVO> listReceiver) {
		
		int smsGubun = 0;
		int result = 0;
		switch(emailApiVO.getGubun()) {
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
			smsQueueVO.setTemplateCode(emailApiVO.getTempCd());
			smsQueueVO.setReType("MMS");
			break;
		default :
			smsGubun = 0;
		}
		
		smsQueueVO.setMsgid(emailApiVO.getMsgid());
		smsQueueVO.setKeygen(emailApiVO.getKeygen());
		smsQueueVO.setUmid(umid);
		smsQueueVO.setMsgType(smsGubun);
		smsQueueVO.setStatus(0);
		smsQueueVO.setSendPhone(getDecryptedString("SEND_TELNO",emailApiVO.getSendTelno()));
		smsQueueVO.setSendName(emailApiVO.getSendNm());
		smsQueueVO.setNationCode("82");
		smsQueueVO.setSenderKey(kkoSenderKey);
		smsQueueVO.setTimeout(timeout);
		smsQueueVO.setRePart("C"); 
		smsQueueVO.setAdFlag(emailApiVO.getLegalYn());
		smsQueueVO.setSendTime(sendDate);
		smsQueueVO.setUserId(emailApiVO.getUserId());
		smsQueueVO.setDeptNo(emailApiVO.getDeptNo());
		smsQueueVO.setRegDt(sendDate);
		smsQueueVO.setMsgTyp(emailApiVO.getGubun());
		smsQueueVO.setSendTyp(emailApiVO.getSendTyp());
		smsQueueVO.setSmsStatus("002");
		smsQueueVO.setWeblinkYn(emailApiVO.getWeblinkYn());
		smsQueueVO.setWeblinkJson(emailApiVO.getTempCd()+".json");
		listReceiver.add(smsQueueVO);
		
		
		try {
			if("002".equals(smsQueueVO.getValidStatus())) {
				//result = smsApiService.insertSmsQueue(smsQueueVO);
				
			} else if("009".equals(smsQueueVO.getValidStatus())) { //PHONE번호없으므로 LOGFILE에 저장됨 
				logger.error("KKO SmsSend insertMessageQueue Phone Number Not Exist  : " + "");
			} else {
				//result = insertSendLog(smsQueueVO);
			}
		} catch(Exception e) {
			logger.error("KKO SmsSend insertMessageQueue error = " + e);
			e.printStackTrace();
			result = -9;
		}
		return listReceiver;
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
			result = smsApiService.insertSmsLog(smsSendLogVO);
		} catch(Exception e) {
			logger.error("KKO SmsSend insertSendLog error = " + e);
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
					result = EncryptUtil.getJasyptDecryptedString(algorithm, keystring, data);
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
	
	
*/	
}
