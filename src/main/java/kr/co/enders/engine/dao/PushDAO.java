package kr.co.enders.engine.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.enders.engine.vo.SegmentVO;
import kr.co.enders.engine.vo.DbConnVO;
import kr.co.enders.engine.vo.PushQueueVO;
import kr.co.enders.engine.vo.PushSendLogVO;
import kr.co.enders.engine.vo.PushVO;

@Repository
public class PushDAO {
	@Autowired
	private SqlSessionTemplate engineSeesion;
	
	public void updatePush(PushVO vo) throws Exception {
		engineSeesion.update("PushDAO.updatePush", vo);
	}

	public PushVO getPush(PushVO vo) throws Exception {
		return (PushVO) engineSeesion.selectOne("PushDAO.getPush", vo);
	}
	
	public List<PushVO> getPushListTarget(PushVO vo) throws Exception {
		return engineSeesion.selectList("PushDAO.getPushListTarget", vo);
	}
	
	public int updatePushStatus(PushVO vo) throws Exception {
		return engineSeesion.update("PushDAO.updatePushStatus", vo);
	}
	
	public int insertPushQueue(PushQueueVO vo) throws Exception {
		return engineSeesion.insert("PushDAO.insertPushQueue", vo);
	}
	
	public int insertPushLog(PushSendLogVO vo) throws Exception {
		return engineSeesion.insert("PushDAO.insertPushLog", vo);
	}
	
	public SegmentVO getSegment(int segNo) throws Exception {
		return (SegmentVO) engineSeesion.selectOne("PushDAO.getSegment", segNo);
	}

	public DbConnVO getDbConnInfo(int dbConnNo) throws Exception {
		return (DbConnVO) engineSeesion.selectOne("PushDAO.getDbConnInfo", dbConnNo);
		
	}

}
