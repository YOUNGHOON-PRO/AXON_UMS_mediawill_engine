package kr.co.enders.engine.svc;

import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.enders.engine.svc.SmsService;
import kr.co.enders.engine.vo.DbConnVO;
import kr.co.enders.engine.vo.EmailApiVO;
import kr.co.enders.engine.vo.SegmentVO;
import kr.co.enders.engine.vo.SmsApiVO;
import kr.co.enders.engine.vo.SmsAttachVO;
import kr.co.enders.engine.vo.SmsPhoneVO;
import kr.co.enders.engine.vo.SmsQueueVO;
import kr.co.enders.engine.vo.SmsSendLogVO;
import kr.co.enders.engine.vo.SmsVO;
import kr.co.enders.engine.dao.NeoEmailApiDAO;
import kr.co.enders.engine.dao.SmsApiDAO;
import kr.co.enders.engine.dao.SmsDAO;

@Service("neoEmailApiService") 
public class NeoEmailApiServiceImpl  implements NeoEmailApiService {

	@Autowired
	private NeoEmailApiDAO neoEmailApiDAO;

	
	@Override
	public int insertNeoEmailApiStg(List<EmailApiVO> vo) throws Exception {
		return neoEmailApiDAO.insertNeoEmailApiStg(vo);
	}

	
/*	
	@Override
	public List<SmsApiVO> getSmsListTarget(SmsApiVO vo) throws Exception {
		return smsApiDao.getSmsListTarget(vo);
	}
	
	@Override
	public int updateSmsStatus(SmsApiVO vo) throws Exception {
		return smsApiDao.updateSmsStatus(vo);
	}
	
	@Override
	public int insertSmsQueue(List<SmsQueueVO> vo) throws Exception {
		return smsApiDao.insertSmsQueue(vo);
	}
	
	@Override
	public int insertSmsLog(SmsSendLogVO vo) throws Exception {
		return smsApiDao.insertSmsLog(vo);
	}
	
	@Override
	public SegmentVO getSegment(int segNo) throws Exception {
		return smsApiDao.getSegment(segNo);
	}
	
	@Override
	public DbConnVO getDbConnInfo(int dbConnNo) throws Exception {
		return smsApiDao.getDbConnInfo(dbConnNo);
	}
	
	@Override
	public List<SmsApiVO> getKakaoTemplateMergeList(SmsApiVO smsApiVO) throws Exception {
		return smsApiDao.getKakaoTemplateMergeList(smsApiVO);
	}
	
	
	@Override
	public List<SmsAttachVO> getSmsAttachList(SmsApiVO smsApiVO) throws Exception {
		return smsApiDao.getSmsAttachList(smsApiVO);
	}
	
	@Override
	public List<SmsPhoneVO> getSmsPhoneList(SmsApiVO smsApiVO) throws Exception {
		return smsApiDao.getSmsPhoneList(smsApiVO);
	}

	@Override
	public int insertSmsApiStg(List<SmsApiVO> vo) throws Exception {
		return smsApiDao.insertSmsApiStg(vo);
	}

	@Override
	public int insertNeoSms(SmsApiVO vo) throws Exception {
		return smsApiDao.insertNeoSms(vo);
	}

	@Override
	public int insertNeoSmsKko(SmsApiVO vo) throws Exception {
		return smsApiDao.insertNeoSmsKko(vo);
	}

	
	@Override
	public int insertSmsWeblinkQueue(List<SmsQueueVO> vo) throws Exception {
		return smsApiDao.insertSmsWeblinkQueue(vo);
	}
	
	@Override
	public List<SmsApiVO> getKkoSmsListTarget(SmsApiVO vo) throws Exception {
		return smsApiDao.getKkoSmsListTarget(vo);
	}
*/


	
}
