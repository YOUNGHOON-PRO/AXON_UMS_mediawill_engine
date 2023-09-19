package kr.co.enders.engine.svc;

import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.enders.engine.svc.SmsService;
import kr.co.enders.engine.vo.DbConnVO;
import kr.co.enders.engine.vo.SegmentVO;
import kr.co.enders.engine.vo.SmsAttachVO;
import kr.co.enders.engine.vo.SmsLogVO;
import kr.co.enders.engine.vo.SmsPhoneVO;
import kr.co.enders.engine.vo.SmsQueueVO;
import kr.co.enders.engine.vo.SmsSendLogVO;
import kr.co.enders.engine.vo.SmsVO;

import kr.co.enders.engine.dao.SmsDAO;
import kr.co.enders.engine.dao.SmsLogDAO;

@Service("smsLogService") 
public class SmsLogServiceImpl  implements SmsLogService {

	@Autowired
	private SmsLogDAO smslogDao;

	@Override
	public SmsLogVO getSmsLogTable(SmsLogVO smslogVO) throws Exception {
		return smslogDao.getSmsLogTable(smslogVO);
	}
	
	@Override
	public int transferSmsLog(SmsLogVO smslogVO) throws Exception {
		return smslogDao.transferSmsLog(smslogVO);
	}

	@Override
	public List<SmsLogVO> getSmsLogData(SmsLogVO smslogVO) throws Exception {
		return smslogDao.getSmsLogData(smslogVO);
	}

	@Override
	public int insertSmsLogData(SmsLogVO smslogVO) throws Exception {
		return smslogDao.insertSmsLogData(smslogVO);
	}

	@Override
	public int insertNeosmsData(SmsLogVO smslogVO) throws Exception {
		return smslogDao.insertNeosmsData(smslogVO);
	}
	
}
