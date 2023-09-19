package kr.co.enders.engine.svc;

import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.enders.engine.svc.SmsService;
import kr.co.enders.engine.vo.DbConnVO;
import kr.co.enders.engine.vo.SegmentVO;
import kr.co.enders.engine.vo.SmsAttachVO;
import kr.co.enders.engine.vo.SmsPhoneVO;
import kr.co.enders.engine.vo.SmsQueueVO;
import kr.co.enders.engine.vo.SmsSendLogVO;
import kr.co.enders.engine.vo.SmsVO;

import kr.co.enders.engine.dao.SmsDAO;

@Service("smsService") 
public class SmsServiceImpl  implements SmsService {

	@Autowired
	private SmsDAO smsDao;

	@Override
	public List<SmsVO> getSmsListTarget(SmsVO vo) throws Exception {
		return smsDao.getSmsListTarget(vo);
	}
	
	@Override
	public int updateSmsStatus(SmsVO vo) throws Exception {
		return smsDao.updateSmsStatus(vo);
	}
	
	@Override
	public int insertSmsQueue(List<SmsQueueVO> vo) throws Exception {
		return smsDao.insertSmsQueue(vo);
	}
	
	@Override
	public int insertSmsLog(SmsSendLogVO vo) throws Exception {
		return smsDao.insertSmsLog(vo);
	}
	
	@Override
	public SegmentVO getSegment(int segNo) throws Exception {
		return smsDao.getSegment(segNo);
	}
	
	@Override
	public DbConnVO getDbConnInfo(int dbConnNo) throws Exception {
		return smsDao.getDbConnInfo(dbConnNo);
	}
	
	@Override
	public List<SmsVO> getKakaoTemplateMergeList(SmsVO smsVO) throws Exception {
		return smsDao.getKakaoTemplateMergeList(smsVO);
	}
	
	
	@Override
	public List<SmsAttachVO> getSmsAttachList(SmsVO smsVO) throws Exception {
		return smsDao.getSmsAttachList(smsVO);
	}
	
	@Override
	public List<SmsPhoneVO> getSmsPhoneList(SmsVO smsVO) throws Exception {
		return smsDao.getSmsPhoneList(smsVO);
	}
}
