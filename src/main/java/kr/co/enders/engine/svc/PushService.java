package kr.co.enders.engine.svc;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.enders.engine.vo.SegmentVO;
import kr.co.enders.engine.vo.DbConnVO;
import kr.co.enders.engine.vo.PushQueueVO;
import kr.co.enders.engine.vo.PushSendLogVO;
import kr.co.enders.engine.vo.PushVO;

@Service
public interface PushService {

	@Transactional(value="transactionManager")
	void updatePush(PushVO vo) throws Exception;
	
	PushVO getPush(PushVO vo) throws Exception;
	
	List<PushVO> getPushListTarget(PushVO vo) throws Exception;
	
	@Transactional(value="transactionManager")
	int updatePushStatus(PushVO vo) throws Exception;
	
	@Transactional(value="transactionManager")
	int insertPushQueue(PushQueueVO pushQueue) throws Exception;
	
	@Transactional(value="transactionManager")
	int insertPushLog(PushSendLogVO pushSendLogVO) throws Exception;
	
	SegmentVO getSegment(int segNo) throws Exception;
	
	public DbConnVO getDbConnInfo(int dbConnNo) throws Exception;
}
