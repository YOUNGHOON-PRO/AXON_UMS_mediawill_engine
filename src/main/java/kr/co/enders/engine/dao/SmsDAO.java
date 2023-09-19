package kr.co.enders.engine.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.enders.engine.vo.DbConnVO;
import kr.co.enders.engine.vo.SegmentVO;
import kr.co.enders.engine.vo.SmsAttachVO;
import kr.co.enders.engine.vo.SmsPhoneVO;
import kr.co.enders.engine.vo.SmsQueueVO;
import kr.co.enders.engine.vo.SmsSendLogVO;
import kr.co.enders.engine.vo.SmsVO;

@Repository
public class SmsDAO {
	@Autowired
	private SqlSessionTemplate engineSeesion;
	
	public List<SmsVO> getSmsListTarget(SmsVO vo) throws Exception {
		return engineSeesion.selectList("SmsDAO.getSmsListTarget", vo);
	}
	
	public int updateSmsStatus(SmsVO vo) throws Exception {
		return engineSeesion.update("SmsDAO.updateSmsStatus", vo);
	}
	
	public List<SmsPhoneVO> getSmsPhoneList(SmsVO vo) throws Exception {
		return engineSeesion.selectList("SmsDAO.getSmsPhoneList", vo);
	}
	
	public int insertSmsQueue(List<SmsQueueVO> vo) throws Exception {
		return engineSeesion.insert("SmsDAO.insertSmsQueue", vo);
	}
	
	public int insertSmsLog(SmsSendLogVO vo) throws Exception {
		return engineSeesion.insert("SmsDAO.insertSmsLog", vo);
	}
	
	public SegmentVO getSegment(int segNo) throws Exception {
		return (SegmentVO) engineSeesion.selectOne("SmsDAO.getSegment", segNo);
	}

	public DbConnVO getDbConnInfo(int dbConnNo) throws Exception {
		return (DbConnVO) engineSeesion.selectOne("SmsDAO.getDbConnInfo", dbConnNo);
	}
	
	public List<SmsVO> getKakaoTemplateMergeList(SmsVO smsVO) throws Exception {
		return engineSeesion.selectList("SmsDAO.getKakaoTemplateMergeList", smsVO);
	}
	
	public List<SmsAttachVO> getSmsAttachList(SmsVO smsVO) throws Exception {
		return engineSeesion.selectList("SmsDAO.getSmsAttachList", smsVO);
	}

}
