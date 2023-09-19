package kr.co.enders.engine;
 
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.co.enders.engine.com.SmsSend;
import kr.co.enders.engine.com.TsEmailApiSend;
import kr.co.enders.engine.com.KkoApiSend;
import kr.co.enders.engine.com.NeoEmailApiSend;
import kr.co.enders.engine.com.PushApiSend;
import kr.co.enders.engine.com.PushSend;
import kr.co.enders.engine.com.SmsApiDel;
import kr.co.enders.engine.com.SmsApiSend;
import kr.co.enders.engine.com.SmsLog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class TimerJob extends TimerTask {
    
	public void run() {
    	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String nowTime = sdf1.format(now);
        System.out.println(nowTime + " run..");
    }
}

public class EngineService {
	private static Logger logger = LoggerFactory.getLogger(EngineService.class);
	
	public static void main(String[] args) throws InterruptedException {
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter logFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String formatedNow = now.format(logFormatter);
		
		String targetGubun = "";
		if(args.length == 1 ) {
			targetGubun = args[0];
		} else {
			targetGubun = "sms";
		} 
		
		logger.debug("Engine Service Started ["+ targetGubun +"] ["+ formatedNow + "]========================>>");
		
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		try {
			
			
			if(targetGubun.equals("push")) {
				PushSend pushSend = (PushSend) container.getBean(targetGubun);
				
//				Timer timer = new Timer();
//				timer.schedule(new TimerJob(), 0, 1000*1*60);
				
				while(true) {
					pushSend.sendPush();
					Thread.sleep(2000);
				}
			} else if(targetGubun.equals("smsLog")){
				SmsLog smsLog = (SmsLog) container.getBean(targetGubun);

//				Timer timer = new Timer();
//				timer.schedule(new TimerJob(), 0, 1000*1*60);
				
				while(true) {
					smsLog.transferLog();
					Thread.sleep(1000*60*1);
				}
				
			} else if(targetGubun.equals("smsApi")){
				SmsApiSend smsApiSend = (SmsApiSend) container.getBean(targetGubun);
				
//				Timer timer = new Timer();
//				timer.schedule(new TimerJob(), 0, 1000*1*60);
				
				while(true) {
					smsApiSend.sendSms();
					smsApiSend.insertMsg();
					Thread.sleep(2000);
				}
			} else if(targetGubun.equals("smsApiDel")){
				SmsApiDel smsApiDel = (SmsApiDel) container.getBean(targetGubun);

//				Timer timer = new Timer();
//				timer.schedule(new TimerJob(), 0, 1000*1*60);
				
				while(true) {
					smsApiDel.apiDel();
					smsApiDel.kkoApiDel();
					Thread.sleep(1000*60*10);
				}
			} else if(targetGubun.equals("kkoApi")){
				KkoApiSend kkoApiSend = (KkoApiSend) container.getBean(targetGubun);
				
//				Timer timer = new Timer();
//				timer.schedule(new TimerJob(), 0, 1000*1*60);
				
				while(true) {
					kkoApiSend.sendSms();
					kkoApiSend.insertMsg();
					Thread.sleep(2000);
				}
			} else if(targetGubun.equals("neoEmailApi")){
				NeoEmailApiSend neoEmailApiSend = (NeoEmailApiSend) container.getBean(targetGubun);
				
//				Timer timer = new Timer();
//				timer.schedule(new TimerJob(), 0, 1000*1*60);
				
				while(true) {
					neoEmailApiSend.sendEmail();
					Thread.sleep(2000);
				}
			} else if(targetGubun.equals("tsEmailApi")){
				
				TsEmailApiSend tsEmailApiSend = (TsEmailApiSend) container.getBean(targetGubun);
				
//				Timer timer = new Timer();
//				timer.schedule(new TimerJob(), 0, 1000*1*60);
				
				while(true) {
					tsEmailApiSend.sendEmail();
					Thread.sleep(2000);
				}
			} else if(targetGubun.equals("pushApi")){
				
				PushApiSend pushApiSend = (PushApiSend) container.getBean(targetGubun);
				
//				Timer timer = new Timer();
//				timer.schedule(new TimerJob(), 0, 1000*1*60);
				
				while(true) {
					pushApiSend.sendPush();
					Thread.sleep(2000);
				}
			}else {
				SmsSend smsSend = (SmsSend) container.getBean(targetGubun);
				
//				Timer timer = new Timer();
//				timer.schedule(new TimerJob(), 0, 1000*1*60);
				
				while(true) {
					smsSend.sendSms();
					Thread.sleep(2000);
				}
			}
		} catch(Exception e) {
			now = LocalDateTime.now();
			formatedNow = now.format(logFormatter);
			logger.error("Engine Service Exception ["+ targetGubun +"] ["+ formatedNow + "]" + e.getMessage());
		} finally {
			now = LocalDateTime.now();
			formatedNow = now.format(logFormatter);
			
			logger.debug("<<========================Engine Service Finished ["+ targetGubun +"] ["+ formatedNow + "]");
			container.close();
		} 

	}
}

