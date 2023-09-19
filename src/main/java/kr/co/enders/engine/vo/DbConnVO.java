/**
 * 작성자 : 김상진
 * 작성일시 : 2021.07.12
 * 설명 : DB Connection VO
 * 수정자 : 김준희
 * 수정일시 : 2021.08.10
 * 수정내역 : sys.vo -->sys.dbc.vo 
 */
package kr.co.enders.engine.vo;

public class DbConnVO  {
	private int dbConnNo;			// DB Connection번호
	private String dbTy;			// DBMS 유형
	private String dbTyNm;			// DBMS 유형명
	private String dbDriver;		// DB Driver
	private String dbUrl;			// DB 연결 URL
	private String loginId;			// 로그인 아이디
	private String loginPwd;		// 비밀번호
	private String dbCharSet;		// 문자셋
	private String dbCharSetNm;		// 문자셋명
	public int getDbConnNo() {
		return dbConnNo;
	}
	public void setDbConnNo(int dbConnNo) {
		this.dbConnNo = dbConnNo;
	}
	public String getDbTy() {
		return dbTy;
	}
	public void setDbTy(String dbTy) {
		this.dbTy = dbTy;
	}
	public String getDbTyNm() {
		return dbTyNm;
	}
	public void setDbTyNm(String dbTyNm) {
		this.dbTyNm = dbTyNm;
	}
	public String getDbDriver() {
		return dbDriver;
	}
	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}
	public String getDbUrl() {
		return dbUrl;
	}
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getDbCharSet() {
		return dbCharSet;
	}
	public void setDbCharSet(String dbCharSet) {
		this.dbCharSet = dbCharSet;
	}
	public String getDbCharSetNm() {
		return dbCharSetNm;
	}
	public void setDbCharSetNm(String dbCharSetNm) {
		this.dbCharSetNm = dbCharSetNm;
	}
}
