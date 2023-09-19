package kr.co.enders.engine.svc;

import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 

import kr.co.enders.engine.svc.PushService;

import kr.co.enders.engine.vo.SegmentVO;
import kr.co.enders.engine.vo.DbConnVO;
import kr.co.enders.engine.vo.PushQueueVO;
import kr.co.enders.engine.vo.PushSendLogVO;
import kr.co.enders.engine.vo.PushVO;

import kr.co.enders.engine.dao.PushDAO;

@Service("pushService") 
public class PushServiceImpl  implements PushService{

	@Autowired
	private PushDAO pushDao;

	@Override
	public void updatePush(PushVO vo) throws Exception {
		pushDao.updatePush(vo);
	}

	@Override
	public PushVO getPush(PushVO vo) throws Exception {
		return pushDao.getPush(vo);
	}
	
	@Override
	public List<PushVO> getPushListTarget(PushVO vo) throws Exception {
		return pushDao.getPushListTarget(vo);
	}
	
	@Override
	public int updatePushStatus(PushVO vo) throws Exception {
		return pushDao.updatePushStatus(vo);
	}
	
	@Override
	public int insertPushQueue(PushQueueVO vo) throws Exception {
		return pushDao.insertPushQueue(vo);
	}
	
	@Override
	public int insertPushLog(PushSendLogVO vo) throws Exception {
		return pushDao.insertPushLog(vo);
	}
	
	@Override
	public SegmentVO getSegment(int segNo) throws Exception {
		return pushDao.getSegment(segNo);
	}
	
	@Override
	public DbConnVO getDbConnInfo(int dbConnNo) throws Exception {
		return pushDao.getDbConnInfo(dbConnNo);
	}
}
