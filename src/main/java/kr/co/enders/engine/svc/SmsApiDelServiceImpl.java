package kr.co.enders.engine.svc;

import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.enders.engine.svc.SmsService;
import kr.co.enders.engine.vo.DbConnVO;
import kr.co.enders.engine.vo.SegmentVO;
import kr.co.enders.engine.vo.SmsApiVO;
import kr.co.enders.engine.vo.SmsAttachVO;
import kr.co.enders.engine.vo.SmsPhoneVO;
import kr.co.enders.engine.vo.SmsQueueVO;
import kr.co.enders.engine.vo.SmsSendLogVO;
import kr.co.enders.engine.vo.SmsVO;
import kr.co.enders.engine.dao.SmsApiDAO;
import kr.co.enders.engine.dao.SmsDAO;

@Service("smsApiDelService") 
public class SmsApiDelServiceImpl  implements SmsApiDelService {

	@Autowired
	private SmsApiDAO smsApiDao;


	@Override
	public int deleteApiDel() throws Exception {
		return smsApiDao.deleteApiDel();
	}


	@Override
	public int deleteKkoApiDel() throws Exception {
		return smsApiDao.deleteKkoApiDel();
	}
}
