package kr.co.enders.engine.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 

import kr.co.enders.engine.com.SmsApiSend;
import kr.co.enders.engine.vo.SegmentMemberVO;
import kr.co.enders.engine.vo.SegmentVO; 

public class DBUtil {
	
	private static Logger logger = LoggerFactory.getLogger(DBUtil.class);

	public Connection getConnection(String dbDriver, String dbUrl, String loginId, String loginPwd) throws Exception {
		Connection conn = null;
		
		Class.forName(dbDriver);
		conn = DriverManager.getConnection(dbUrl, loginId, loginPwd);
		
		return conn;
	}
	
	public Connection getConnection(String dataSourceName) throws Exception {
		Connection conn = null;
		
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup(dataSourceName);
		conn = ds.getConnection();
		
		return conn;
	}
	
	public SegmentMemberVO getMemberList(String dbDriver, String dbUrl, String loginId, String loginPwd, SegmentVO segmentInfo) {
		
		String query   = StringUtil.setUpperString(segmentInfo.getQuery());
		String selectSql = StringUtil.setUpperString(segmentInfo.getSelectSql());
		String fromSql  = StringUtil.setUpperString(segmentInfo.getFromSql());
		String whereSql  = StringUtil.setUpperString(segmentInfo.getWhereSql());
		String orderbySql = StringUtil.setUpperString(segmentInfo.getOrderbySql());
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rss = null;
		
		SegmentMemberVO memberVO = new SegmentMemberVO();
		List<HashMap<String,String>> memberList = new ArrayList<HashMap<String, String>>();
		
		String cSql = "";
		String sql = "";
		
		if("002".equals(segmentInfo.getCreateTy())) {		//직접 SQL 입력
			sql = query;
			cSql = "SELECT COUNT(*) " + query.substring(query.indexOf("FROM"), query.length());
		} else {
			sql = "SELECT " + selectSql + " ";
			sql += "FROM " + fromSql + " ";
			if(whereSql != null && !"".equals(whereSql)) {
				if(segmentInfo.getValue() != null && !"".equals(segmentInfo.getValue())) {
					sql += "WHERE " + whereSql + " AND " + segmentInfo.getSearch() + " LIKE '%" + segmentInfo.getValue() + "%' ";
				} else {
					sql += "WHERE " + whereSql + " ";
				}
			} else {
				if(segmentInfo.getValue() != null && !"".equals(segmentInfo.getValue())) {
					sql += "WHERE " + segmentInfo.getSearch() + " LIKE '%" + segmentInfo.getValue() + "%' ";
				}
			}
			
			if(orderbySql != null && !"".equals(orderbySql.trim())) {
				sql += "ORDER BY " + orderbySql;
			}
			
			String fromUnder = "FROM " + fromSql + " ";
			if(whereSql != null && !"".equals(whereSql)) {
				if(segmentInfo.getValue() != null && !"".equals(segmentInfo.getValue())) {
					fromUnder += "WHERE " + whereSql + " AND " + segmentInfo.getSearch() + " LIKE '%" + segmentInfo.getValue() + "%' ";
				} else {
					fromUnder += "WHERE " + whereSql + " ";
				}
			} else {
				if(segmentInfo.getValue() != null && !"".equals(segmentInfo.getValue())) {
					fromUnder += "WHERE " + segmentInfo.getSearch() + " LIKE '%" + segmentInfo.getValue() + "%' ";
				}
			}
			
			if(selectSql.toUpperCase().indexOf("DISTINCT") != -1) {
				cSql = "SELECT COUNT(DISTINCT *) " + fromUnder;
			} else {
				cSql = "SELECT COUNT(*) " + fromUnder.trim();
			}
		}
		
		
		try {
			conn = getConnection(dbDriver, dbUrl, loginId, loginPwd);
			sql = sql.replaceAll("@REQUESTKEY", "'"+segmentInfo.getRequestKey().toString()+"'");
			
			
			// 데이터 상세 조회
			pstm = conn.prepareStatement(sql);
			rss = pstm.executeQuery();
			while(rss.next()) {
				int idx = 1;
				HashMap<String,String> data = new HashMap<String,String>();
				StringTokenizer mkey = new StringTokenizer(segmentInfo.getMergeKey(), ",");
				while(mkey.hasMoreTokens()) {
					String tmpMkey = mkey.nextToken();
					data.put(tmpMkey, rss.getString(idx));
					idx++;
				}
				memberList.add(data);
			}
			
			memberVO.setMemberList(memberList);
			
		} catch(Exception e) {
			logger.error("getMemberList error = " + e);
		} finally {
			if(rss != null) try { rss.close(); } catch(Exception ex) {}
			if(pstm != null) try { pstm.close(); } catch(Exception ex) {}
			if(conn != null) try { conn.close(); } catch(Exception ex) {}
		}
		
		return memberVO;
	}
}
