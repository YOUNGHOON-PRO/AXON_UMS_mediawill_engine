package kr.co.enders.engine.svc;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.enders.engine.vo.DbConnVO;
import kr.co.enders.engine.vo.EmailApiVO;
import kr.co.enders.engine.vo.SegmentVO;
import kr.co.enders.engine.vo.SmsApiVO;
import kr.co.enders.engine.vo.SmsAttachVO;
import kr.co.enders.engine.vo.SmsPhoneVO;
import kr.co.enders.engine.vo.SmsQueueVO;
import kr.co.enders.engine.vo.SmsSendLogVO;
import kr.co.enders.engine.vo.SmsVO;

@Service
public interface TsEmailApiService {	
	
	@Transactional(value="transactionManager")
	int insertTsEmailApiStg(List<EmailApiVO> listReceiver) throws Exception;
	
/*	
	@Transactional(value="transactionManager")
	int updateSmsStatus(SmsApiVO vo) throws Exception;
	
	@Transactional(value="transactionManager")
	int insertSmsQueue(List<SmsQueueVO> listReceiver) throws Exception;
	
	@Transactional(value="transactionManager")
	int insertSmsLog(SmsSendLogVO vo) throws Exception;
	
	SegmentVO getSegment(int segNo) throws Exception;
	
	public DbConnVO getDbConnInfo(int dbConnNo) throws Exception;
	
	public List<SmsApiVO> getKakaoTemplateMergeList(SmsApiVO smsApiVO) throws Exception;	 
	
	public List<SmsAttachVO> getSmsAttachList(SmsApiVO smsApiVO) throws Exception;
	
	public List<SmsPhoneVO> getSmsPhoneList(SmsApiVO smsApiVO) throws Exception;
	
	@Transactional(value="transactionManager")
	int insertSmsApiStg(List<SmsApiVO> listReceiver) throws Exception;
	
	@Transactional(value="transactionManager")
	int insertNeoSms(SmsApiVO vo) throws Exception;

	@Transactional(value="transactionManager")
	int insertNeoSmsKko(SmsApiVO vo) throws Exception;

	@Transactional(value="transactionManager")
	int insertSmsWeblinkQueue(List<SmsQueueVO> listReceiver) throws Exception;

	List<SmsApiVO> getKkoSmsListTarget(SmsApiVO vo) throws Exception;
*/	
}
