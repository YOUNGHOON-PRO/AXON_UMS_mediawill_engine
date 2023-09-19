package kr.co.enders.engine.svc;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.enders.engine.vo.DbConnVO;
import kr.co.enders.engine.vo.SegmentVO;
import kr.co.enders.engine.vo.SmsAttachVO;
import kr.co.enders.engine.vo.SmsLogVO;
import kr.co.enders.engine.vo.SmsPhoneVO;
import kr.co.enders.engine.vo.SmsQueueVO;
import kr.co.enders.engine.vo.SmsSendLogVO;
import kr.co.enders.engine.vo.SmsVO;

@Service
public interface SmsLogService {	

	SmsLogVO getSmsLogTable(SmsLogVO smslogVO) throws Exception;

	int transferSmsLog(SmsLogVO smslogVO) throws Exception;
	
	public List<SmsLogVO> getSmsLogData(SmsLogVO smslogVO) throws Exception;
	
	@Transactional(value="transactionManager")
	int insertSmsLogData(SmsLogVO smslogVO) throws Exception;
	
	@Transactional(value="transactionManager")
	int insertNeosmsData(SmsLogVO smslogVO) throws Exception;
	
	
}
