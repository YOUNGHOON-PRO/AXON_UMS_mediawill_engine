package kr.co.enders.engine.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.enders.engine.vo.DbConnVO;
import kr.co.enders.engine.vo.EmailApiVO;
import kr.co.enders.engine.vo.PushApiVO;
import kr.co.enders.engine.vo.SegmentVO;
import kr.co.enders.engine.vo.SmsApiVO;
import kr.co.enders.engine.vo.SmsAttachVO;
import kr.co.enders.engine.vo.SmsPhoneVO;
import kr.co.enders.engine.vo.SmsQueueVO;
import kr.co.enders.engine.vo.SmsSendLogVO;
import kr.co.enders.engine.vo.SmsVO;

@Repository
public class PushApiDAO {
	@Autowired
	private SqlSessionTemplate engineSeesion;
	
	public int insertPushApiStg(List<PushApiVO> vo) throws Exception {
		return engineSeesion.insert("PushApiDAO.insertPushApiStg", vo);
	}
	
	/*
	
	public List<SmsApiVO> getSmsListTarget(SmsApiVO vo) throws Exception {
		return engineSeesion.selectList("SmsApiDAO.getSmsListTarget", vo);
	}
	
	public int updateSmsStatus(SmsApiVO vo) throws Exception {
		return engineSeesion.update("SmsApiDAO.updateSmsStatus", vo);
	}
	
	public List<SmsPhoneVO> getSmsPhoneList(SmsApiVO vo) throws Exception {
		return engineSeesion.selectList("SmsApiDAO.getSmsPhoneList", vo);
	}
	
	public int insertSmsQueue(List<SmsQueueVO> vo) throws Exception {
		return engineSeesion.insert("SmsApiDAO.insertSmsQueue", vo);
	}
	
	public int insertSmsLog(SmsSendLogVO vo) throws Exception {
		return engineSeesion.insert("SmsApiDAO.insertSmsLog", vo);
	}
	
	public SegmentVO getSegment(int segNo) throws Exception {
		return (SegmentVO) engineSeesion.selectOne("SmsApiDAO.getSegment", segNo);
	}

	public DbConnVO getDbConnInfo(int dbConnNo) throws Exception {
		return (DbConnVO) engineSeesion.selectOne("SmsApiDAO.getDbConnInfo", dbConnNo);
	}
	
	public List<SmsApiVO> getKakaoTemplateMergeList(SmsApiVO smsApiVO) throws Exception {
		return engineSeesion.selectList("SmsApiDAO.getKakaoTemplateMergeList", smsApiVO);
	}
	
	public List<SmsAttachVO> getSmsAttachList(SmsApiVO smsApiVO) throws Exception {
		return engineSeesion.selectList("SmsApiDAO.getSmsAttachList", smsApiVO);
	}
	
	public int insertSmsApiStg(List<SmsApiVO> vo) throws Exception {
		return engineSeesion.insert("SmsApiDAO.insertSmsApiStg", vo);
	}
	
	public int insertNeoSms(SmsApiVO vo) throws Exception {
		return engineSeesion.insert("SmsApiDAO.insertNeoSms", vo);
	}
	
	public int deleteApiDel() throws Exception {
		return engineSeesion.delete("SmsApiDAO.deleteApiDel");
	}
	
	public int insertNeoSmsKko(SmsApiVO vo) throws Exception {
		return engineSeesion.insert("SmsApiDAO.insertNeoSmsKko", vo);
	}

	public int insertKkoApiStg(List<SmsApiVO> vo) throws Exception {
		return engineSeesion.insert("SmsApiDAO.insertKkoApiStg", vo);
	}

	public int deleteKkoApiDel() {
		return engineSeesion.delete("SmsApiDAO.deleteKkoApiDel");
	}
	
	public int insertSmsWeblinkQueue(List<SmsQueueVO> vo) throws Exception {
		return engineSeesion.insert("SmsApiDAO.insertSmsWeblinkQueue", vo);
	}
	
	public List<SmsApiVO> getKkoSmsListTarget(SmsApiVO vo) throws Exception {
		return engineSeesion.selectList("SmsApiDAO.getKkoSmsListTarget", vo);
	}
*/
}
