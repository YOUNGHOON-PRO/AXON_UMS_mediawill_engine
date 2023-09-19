package kr.co.enders.engine.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.enders.engine.vo.DbConnVO;
import kr.co.enders.engine.vo.PushVO;
import kr.co.enders.engine.vo.SegmentVO;
import kr.co.enders.engine.vo.SmsAttachVO;
import kr.co.enders.engine.vo.SmsLogVO;
import kr.co.enders.engine.vo.SmsPhoneVO;
import kr.co.enders.engine.vo.SmsQueueVO;
import kr.co.enders.engine.vo.SmsSendLogVO;
import kr.co.enders.engine.vo.SmsVO;

@Repository
public class SmsLogDAO {
	@Autowired
	private SqlSessionTemplate engineSeesion;

	public SmsLogVO getSmsLogTable(SmsLogVO smsLogVO) throws Exception {
		return engineSeesion.selectOne("SmsLogDAO.getSmsLogTable",smsLogVO);
	}
	
	public int transferSmsLog(SmsLogVO smsLogVO) throws Exception {
		return engineSeesion.insert("SmsLogDAO.transferSmsLog",smsLogVO);
	}
	
	public List<SmsLogVO> getSmsLogData(SmsLogVO smslogVO) throws Exception {
		return engineSeesion.selectList("SmsLogDAO.getSmsLogData", smslogVO);
	}
	
	public int insertSmsLogData(SmsLogVO smslogVO) throws Exception {
		return engineSeesion.insert("SmsLogDAO.insertSmsLogData", smslogVO);
	}

	public int insertNeosmsData(SmsLogVO smslogVO) {
		return engineSeesion.insert("SmsLogDAO.insertNeosmsData", smslogVO);
	}
	
}
