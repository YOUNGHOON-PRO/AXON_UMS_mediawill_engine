package kr.co.enders.engine.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.enders.engine.vo.DbConnVO;
import kr.co.enders.engine.vo.SegmentVO;
import kr.co.enders.engine.vo.SmsApiVO;
import kr.co.enders.engine.vo.SmsAttachVO;
import kr.co.enders.engine.vo.SmsPhoneVO;
import kr.co.enders.engine.vo.SmsQueueVO;
import kr.co.enders.engine.vo.SmsSendLogVO;
import kr.co.enders.engine.vo.SmsVO;

@Repository
public class SmsApiDelDAO {
	@Autowired
	private SqlSessionTemplate engineSeesion;
	
	
	public int deleteApiDel(List<SmsApiVO> vo) throws Exception {
		return engineSeesion.delete("SmsApiDelDAO.deleteApiDel", vo);
	}
	

}
