package kr.co.enders.engine.svc;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.enders.engine.vo.DbConnVO;
import kr.co.enders.engine.vo.SegmentVO;
import kr.co.enders.engine.vo.SmsAttachVO;
import kr.co.enders.engine.vo.SmsPhoneVO;
import kr.co.enders.engine.vo.SmsQueueVO;
import kr.co.enders.engine.vo.SmsSendLogVO;
import kr.co.enders.engine.vo.SmsVO;

@Service
public interface SmsService {	
	List<SmsVO> getSmsListTarget(SmsVO vo) throws Exception;
	
	@Transactional(value="transactionManager")
	int updateSmsStatus(SmsVO vo) throws Exception;
	
	@Transactional(value="transactionManager")
	int insertSmsQueue(List<SmsQueueVO> vo) throws Exception;
	
	@Transactional(value="transactionManager")
	int insertSmsLog(SmsSendLogVO vo) throws Exception;
	
	SegmentVO getSegment(int segNo) throws Exception;
	
	public DbConnVO getDbConnInfo(int dbConnNo) throws Exception;
	
	public List<SmsVO> getKakaoTemplateMergeList(SmsVO smsVO) throws Exception;	 
	
	public List<SmsAttachVO> getSmsAttachList(SmsVO smsVO) throws Exception;
	
	public List<SmsPhoneVO> getSmsPhoneList(SmsVO smsVO) throws Exception;
}
