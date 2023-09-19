package kr.co.enders.engine.vo;

public class EmailApiVO {
	
	/*
	 * TS_MAILQUEUE
	 * TS_RECIPEINTINFO 
	 * TS_ATTACH
	 * TS_SERVICETYP  
	 */
	
	//TS_MAILQUEUE
	private int tsMid;
	private int tsSubid;
	private int tsTid;
	private String tsSpos;
	private String tsSname;
	private String tsSmail;
	private String tsSid;
	private String tsRpos;
	private String tsQuery;
	private String tsCtnpos;
	private String tsSubject;
	private String tsContents;
	private String tsCdate;
	private String tsSdate;
	private String tsStatus;
	private String tsSbcode;
	private int tsrefmid;
	private int tsCharset;
	private String tsAttachfile01;
	private String tsAttachfile02;
	private String tsAttachfile03;
	private String tsAttachfile04;
	private String tsAttachfile05;
	private int tsRtyMid;
	private int tsRtySubid;
	private String tsRtyTyp;
	private int tsDeptNo;
	
	//ts_recipeintinfo 
	private String tsRid;
	private String tsRname;
	private String tsRmail;
	private String tsEnckey;
	private String tsBizkey;
							
	//TS_SERVICETYP  
	private Integer tsSvcTid;
	private String tsSvcTnm;
	private String tsSvcTdesc;
	private String tsSvcSname;
	private String tsSvcSmail;
	private String tsSvcSid;
	private String tsSvcEmailSubject;
	private String tsSvcContentsPath;
	private String tsSvcContentsTyp;
	private int tsSvcAttchCnt;
	private String tsSvcRecvChkYn;
	private String tsSvcUserId;
	private Integer tsSvcDeptNo;
	private String tsSvcUseYn;
	private String tsSvcStatus;
	private String tsSvcEaiCampNo;
	private String tsSvcUpId;
	private String tsSvcUpDt;
	private String tsSvcRegId;
	private String tsSvcRegDt;
	private String tsSvcWorkStatus;
	private String tsSvcProhibiChkTyp;
	private Integer tsSvcCampNo;
	private Integer tsSvcSegNo;
	private String tsSvcMailMktGb;
	
	//add..
	private String regId;
	private String regDt;
	private String map1;
	private String map2;
	private String map3;
	private String map4;
	private String map5;
	private String map6;
	private String map7;
	private String map8;
	private String map9;
	private String map10;
	private String map11;
	private String map12;
	private String map13;
	private String map14;
	private String map15;
	private String map16;
	private String map17;
	private String map18;
	private String map19;
	private String map20;
	private String map21;
	private String map22;
	private String map23;
	private String map24;
	private String map25;
	private String map26;
	private String map27;
	private String map28;
	private String map29;
	private String map30;
	private String requestKey;
	private String requestoption;
	private String messagename;
	private String templatecode;
	private String campaigncode;
	private String campaignno;
	private String pagenumber;
	private String totalpagenumber;
	private String pagesize;
	private String memberno;
	private String membername;
	private String receivephonenumber;
	private String receiveemail;
	private String receivedeviceno;
	private String encryptionkey;
	private String businesskey;
	private String bankaccount;
	private String bankname;
	private String subject;
	private String context;
	private String depositduedatetime;
	private String depositorname;
	private String distributorname;
	private String licenseexpireddatecontext;
	private String moneyamount;
	private String orderdatecontext;
	private String ordermonth;
	private String orderno;
	private String storename;
	private String storeownerusername;
	private String temporarypassword;
	private String verificationcode;
	private String withdrawaldatecontext;
	private String status;
	private String phone;
	private String senderphonenumber;
	private String senderemail;
	private String sendduedatatime;
	private String centerphonenumber;
	private String linkurlmyoffice;
	private String membernumber;
	private String tempMappContent;
	private String weblinkYn;
	private String weblinkJson;
	
	
	
	public String getCampaignno() {
		return campaignno;
	}
	public void setCampaignno(String campaignno) {
		this.campaignno = campaignno;
	}
	public String getSenderemail() {
		return senderemail;
	}
	public void setSenderemail(String senderemail) {
		this.senderemail = senderemail;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public int getTsMid() {
		return tsMid;
	}
	public void setTsMid(int tsMid) {
		this.tsMid = tsMid;
	}
	public int getTsSubid() {
		return tsSubid;
	}
	public void setTsSubid(int tsSubid) {
		this.tsSubid = tsSubid;
	}
	public int getTsTid() {
		return tsTid;
	}
	public void setTsTid(int tsTid) {
		this.tsTid = tsTid;
	}
	public String getTsSpos() {
		return tsSpos;
	}
	public void setTsSpos(String tsSpos) {
		this.tsSpos = tsSpos;
	}
	public String getTsSname() {
		return tsSname;
	}
	public void setTsSname(String tsSname) {
		this.tsSname = tsSname;
	}
	public String getTsSmail() {
		return tsSmail;
	}
	public void setTsSmail(String tsSmail) {
		this.tsSmail = tsSmail;
	}
	public String getTsSid() {
		return tsSid;
	}
	public void setTsSid(String tsSid) {
		this.tsSid = tsSid;
	}
	public String getTsRpos() {
		return tsRpos;
	}
	public void setTsRpos(String tsRpos) {
		this.tsRpos = tsRpos;
	}
	public String getTsQuery() {
		return tsQuery;
	}
	public void setTsQuery(String tsQuery) {
		this.tsQuery = tsQuery;
	}
	public String getTsCtnpos() {
		return tsCtnpos;
	}
	public void setTsCtnpos(String tsCtnpos) {
		this.tsCtnpos = tsCtnpos;
	}
	public String getTsSubject() {
		return tsSubject;
	}
	public void setTsSubject(String tsSubject) {
		this.tsSubject = tsSubject;
	}
	public String getTsContents() {
		return tsContents;
	}
	public void setTsContents(String tsContents) {
		this.tsContents = tsContents;
	}
	public String getTsCdate() {
		return tsCdate;
	}
	public void setTsCdate(String tsCdate) {
		this.tsCdate = tsCdate;
	}
	public String getTsSdate() {
		return tsSdate;
	}
	public void setTsSdate(String tsSdate) {
		this.tsSdate = tsSdate;
	}
	public String getTsStatus() {
		return tsStatus;
	}
	public void setTsStatus(String tsStatus) {
		this.tsStatus = tsStatus;
	}
	public String getTsSbcode() {
		return tsSbcode;
	}
	public void setTsSbcode(String tsSbcode) {
		this.tsSbcode = tsSbcode;
	}
	public int getTsrefmid() {
		return tsrefmid;
	}
	public void setTsrefmid(int tsrefmid) {
		this.tsrefmid = tsrefmid;
	}
	public int getTsCharset() {
		return tsCharset;
	}
	public void setTsCharset(int tsCharset) {
		this.tsCharset = tsCharset;
	}
	public String getTsAttachfile01() {
		return tsAttachfile01;
	}
	public void setTsAttachfile01(String tsAttachfile01) {
		this.tsAttachfile01 = tsAttachfile01;
	}
	public String getTsAttachfile02() {
		return tsAttachfile02;
	}
	public void setTsAttachfile02(String tsAttachfile02) {
		this.tsAttachfile02 = tsAttachfile02;
	}
	public String getTsAttachfile03() {
		return tsAttachfile03;
	}
	public void setTsAttachfile03(String tsAttachfile03) {
		this.tsAttachfile03 = tsAttachfile03;
	}
	public String getTsAttachfile04() {
		return tsAttachfile04;
	}
	public void setTsAttachfile04(String tsAttachfile04) {
		this.tsAttachfile04 = tsAttachfile04;
	}
	public String getTsAttachfile05() {
		return tsAttachfile05;
	}
	public void setTsAttachfile05(String tsAttachfile05) {
		this.tsAttachfile05 = tsAttachfile05;
	}
	public int getTsRtyMid() {
		return tsRtyMid;
	}
	public void setTsRtyMid(int tsRtyMid) {
		this.tsRtyMid = tsRtyMid;
	}
	public int getTsRtySubid() {
		return tsRtySubid;
	}
	public void setTsRtySubid(int tsRtySubid) {
		this.tsRtySubid = tsRtySubid;
	}
	public String getTsRtyTyp() {
		return tsRtyTyp;
	}
	public void setTsRtyTyp(String tsRtyTyp) {
		this.tsRtyTyp = tsRtyTyp;
	}
	public int getTsDeptNo() {
		return tsDeptNo;
	}
	public void setTsDeptNo(int tsDeptNo) {
		this.tsDeptNo = tsDeptNo;
	}
	public String getTsRid() {
		return tsRid;
	}
	public void setTsRid(String tsRid) {
		this.tsRid = tsRid;
	}
	public String getTsRname() {
		return tsRname;
	}
	public void setTsRname(String tsRname) {
		this.tsRname = tsRname;
	}
	public String getTsRmail() {
		return tsRmail;
	}
	public void setTsRmail(String tsRmail) {
		this.tsRmail = tsRmail;
	}
	public String getTsEnckey() {
		return tsEnckey;
	}
	public void setTsEnckey(String tsEnckey) {
		this.tsEnckey = tsEnckey;
	}
	public String getTsBizkey() {
		return tsBizkey;
	}
	public void setTsBizkey(String tsBizkey) {
		this.tsBizkey = tsBizkey;
	}
	public Integer getTsSvcTid() {
		return tsSvcTid;
	}
	public void setTsSvcTid(Integer tsSvcTid) {
		this.tsSvcTid = tsSvcTid;
	}
	public String getTsSvcTnm() {
		return tsSvcTnm;
	}
	public void setTsSvcTnm(String tsSvcTnm) {
		this.tsSvcTnm = tsSvcTnm;
	}
	public String getTsSvcTdesc() {
		return tsSvcTdesc;
	}
	public void setTsSvcTdesc(String tsSvcTdesc) {
		this.tsSvcTdesc = tsSvcTdesc;
	}
	public String getTsSvcSname() {
		return tsSvcSname;
	}
	public void setTsSvcSname(String tsSvcSname) {
		this.tsSvcSname = tsSvcSname;
	}
	public String getTsSvcSmail() {
		return tsSvcSmail;
	}
	public void setTsSvcSmail(String tsSvcSmail) {
		this.tsSvcSmail = tsSvcSmail;
	}
	public String getTsSvcSid() {
		return tsSvcSid;
	}
	public void setTsSvcSid(String tsSvcSid) {
		this.tsSvcSid = tsSvcSid;
	}
	public String getTsSvcEmailSubject() {
		return tsSvcEmailSubject;
	}
	public void setTsSvcEmailSubject(String tsSvcEmailSubject) {
		this.tsSvcEmailSubject = tsSvcEmailSubject;
	}
	public String getTsSvcContentsPath() {
		return tsSvcContentsPath;
	}
	public void setTsSvcContentsPath(String tsSvcContentsPath) {
		this.tsSvcContentsPath = tsSvcContentsPath;
	}
	public String getTsSvcContentsTyp() {
		return tsSvcContentsTyp;
	}
	public void setTsSvcContentsTyp(String tsSvcContentsTyp) {
		this.tsSvcContentsTyp = tsSvcContentsTyp;
	}
	public int getTsSvcAttchCnt() {
		return tsSvcAttchCnt;
	}
	public void setTsSvcAttchCnt(int tsSvcAttchCnt) {
		this.tsSvcAttchCnt = tsSvcAttchCnt;
	}
	public String getTsSvcRecvChkYn() {
		return tsSvcRecvChkYn;
	}
	public void setTsSvcRecvChkYn(String tsSvcRecvChkYn) {
		this.tsSvcRecvChkYn = tsSvcRecvChkYn;
	}
	public String getTsSvcUserId() {
		return tsSvcUserId;
	}
	public void setTsSvcUserId(String tsSvcUserId) {
		this.tsSvcUserId = tsSvcUserId;
	}
	public Integer getTsSvcDeptNo() {
		return tsSvcDeptNo;
	}
	public void setTsSvcDeptNo(Integer tsSvcDeptNo) {
		this.tsSvcDeptNo = tsSvcDeptNo;
	}
	public String getTsSvcUseYn() {
		return tsSvcUseYn;
	}
	public void setTsSvcUseYn(String tsSvcUseYn) {
		this.tsSvcUseYn = tsSvcUseYn;
	}
	public String getTsSvcStatus() {
		return tsSvcStatus;
	}
	public void setTsSvcStatus(String tsSvcStatus) {
		this.tsSvcStatus = tsSvcStatus;
	}
	public String getTsSvcEaiCampNo() {
		return tsSvcEaiCampNo;
	}
	public void setTsSvcEaiCampNo(String tsSvcEaiCampNo) {
		this.tsSvcEaiCampNo = tsSvcEaiCampNo;
	}
	public String getTsSvcUpId() {
		return tsSvcUpId;
	}
	public void setTsSvcUpId(String tsSvcUpId) {
		this.tsSvcUpId = tsSvcUpId;
	}
	public String getTsSvcUpDt() {
		return tsSvcUpDt;
	}
	public void setTsSvcUpDt(String tsSvcUpDt) {
		this.tsSvcUpDt = tsSvcUpDt;
	}
	public String getTsSvcRegId() {
		return tsSvcRegId;
	}
	public void setTsSvcRegId(String tsSvcRegId) {
		this.tsSvcRegId = tsSvcRegId;
	}
	public String getTsSvcRegDt() {
		return tsSvcRegDt;
	}
	public void setTsSvcRegDt(String tsSvcRegDt) {
		this.tsSvcRegDt = tsSvcRegDt;
	}
	public String getTsSvcWorkStatus() {
		return tsSvcWorkStatus;
	}
	public void setTsSvcWorkStatus(String tsSvcWorkStatus) {
		this.tsSvcWorkStatus = tsSvcWorkStatus;
	}
	public String getTsSvcProhibiChkTyp() {
		return tsSvcProhibiChkTyp;
	}
	public void setTsSvcProhibiChkTyp(String tsSvcProhibiChkTyp) {
		this.tsSvcProhibiChkTyp = tsSvcProhibiChkTyp;
	}
	public Integer getTsSvcCampNo() {
		return tsSvcCampNo;
	}
	public void setTsSvcCampNo(Integer tsSvcCampNo) {
		this.tsSvcCampNo = tsSvcCampNo;
	}
	public Integer getTsSvcSegNo() {
		return tsSvcSegNo;
	}
	public void setTsSvcSegNo(Integer tsSvcSegNo) {
		this.tsSvcSegNo = tsSvcSegNo;
	}
	public String getTsSvcMailMktGb() {
		return tsSvcMailMktGb;
	}
	public void setTsSvcMailMktGb(String tsSvcMailMktGb) {
		this.tsSvcMailMktGb = tsSvcMailMktGb;
	}
	public String getMap1() {
		return map1;
	}
	public void setMap1(String map1) {
		this.map1 = map1;
	}
	public String getMap2() {
		return map2;
	}
	public void setMap2(String map2) {
		this.map2 = map2;
	}
	public String getMap3() {
		return map3;
	}
	public void setMap3(String map3) {
		this.map3 = map3;
	}
	public String getMap4() {
		return map4;
	}
	public void setMap4(String map4) {
		this.map4 = map4;
	}
	public String getMap5() {
		return map5;
	}
	public void setMap5(String map5) {
		this.map5 = map5;
	}
	public String getMap6() {
		return map6;
	}
	public void setMap6(String map6) {
		this.map6 = map6;
	}
	public String getMap7() {
		return map7;
	}
	public void setMap7(String map7) {
		this.map7 = map7;
	}
	public String getMap8() {
		return map8;
	}
	public void setMap8(String map8) {
		this.map8 = map8;
	}
	public String getMap9() {
		return map9;
	}
	public void setMap9(String map9) {
		this.map9 = map9;
	}
	public String getMap10() {
		return map10;
	}
	public void setMap10(String map10) {
		this.map10 = map10;
	}
	public String getMap11() {
		return map11;
	}
	public void setMap11(String map11) {
		this.map11 = map11;
	}
	public String getMap12() {
		return map12;
	}
	public void setMap12(String map12) {
		this.map12 = map12;
	}
	public String getMap13() {
		return map13;
	}
	public void setMap13(String map13) {
		this.map13 = map13;
	}
	public String getMap14() {
		return map14;
	}
	public void setMap14(String map14) {
		this.map14 = map14;
	}
	public String getMap15() {
		return map15;
	}
	public void setMap15(String map15) {
		this.map15 = map15;
	}
	public String getMap16() {
		return map16;
	}
	public void setMap16(String map16) {
		this.map16 = map16;
	}
	public String getMap17() {
		return map17;
	}
	public void setMap17(String map17) {
		this.map17 = map17;
	}
	public String getMap18() {
		return map18;
	}
	public void setMap18(String map18) {
		this.map18 = map18;
	}
	public String getMap19() {
		return map19;
	}
	public void setMap19(String map19) {
		this.map19 = map19;
	}
	public String getMap20() {
		return map20;
	}
	public void setMap20(String map20) {
		this.map20 = map20;
	}
	public String getMap21() {
		return map21;
	}
	public void setMap21(String map21) {
		this.map21 = map21;
	}
	public String getMap22() {
		return map22;
	}
	public void setMap22(String map22) {
		this.map22 = map22;
	}
	public String getMap23() {
		return map23;
	}
	public void setMap23(String map23) {
		this.map23 = map23;
	}
	public String getMap24() {
		return map24;
	}
	public void setMap24(String map24) {
		this.map24 = map24;
	}
	public String getMap25() {
		return map25;
	}
	public void setMap25(String map25) {
		this.map25 = map25;
	}
	public String getMap26() {
		return map26;
	}
	public void setMap26(String map26) {
		this.map26 = map26;
	}
	public String getMap27() {
		return map27;
	}
	public void setMap27(String map27) {
		this.map27 = map27;
	}
	public String getMap28() {
		return map28;
	}
	public void setMap28(String map28) {
		this.map28 = map28;
	}
	public String getMap29() {
		return map29;
	}
	public void setMap29(String map29) {
		this.map29 = map29;
	}
	public String getMap30() {
		return map30;
	}
	public void setMap30(String map30) {
		this.map30 = map30;
	}
	public String getRequestKey() {
		return requestKey;
	}
	public void setRequestKey(String requestKey) {
		this.requestKey = requestKey;
	}
	public String getRequestoption() {
		return requestoption;
	}
	public void setRequestoption(String requestoption) {
		this.requestoption = requestoption;
	}
	public String getMessagename() {
		return messagename;
	}
	public void setMessagename(String messagename) {
		this.messagename = messagename;
	}
	public String getTemplatecode() {
		return templatecode;
	}
	public void setTemplatecode(String templatecode) {
		this.templatecode = templatecode;
	}
	public String getCampaigncode() {
		return campaigncode;
	}
	public void setCampaigncode(String campaigncode) {
		this.campaigncode = campaigncode;
	}
	public String getPagenumber() {
		return pagenumber;
	}
	public void setPagenumber(String pagenumber) {
		this.pagenumber = pagenumber;
	}
	public String getTotalpagenumber() {
		return totalpagenumber;
	}
	public void setTotalpagenumber(String totalpagenumber) {
		this.totalpagenumber = totalpagenumber;
	}
	public String getPagesize() {
		return pagesize;
	}
	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}
	public String getMemberno() {
		return memberno;
	}
	public void setMemberno(String memberno) {
		this.memberno = memberno;
	}
	public String getMembername() {
		return membername;
	}
	public void setMembername(String membername) {
		this.membername = membername;
	}
	public String getReceivephonenumber() {
		return receivephonenumber;
	}
	public void setReceivephonenumber(String receivephonenumber) {
		this.receivephonenumber = receivephonenumber;
	}
	public String getReceiveemail() {
		return receiveemail;
	}
	public void setReceiveemail(String receiveemail) {
		this.receiveemail = receiveemail;
	}
	public String getReceivedeviceno() {
		return receivedeviceno;
	}
	public void setReceivedeviceno(String receivedeviceno) {
		this.receivedeviceno = receivedeviceno;
	}
	public String getEncryptionkey() {
		return encryptionkey;
	}
	public void setEncryptionkey(String encryptionkey) {
		this.encryptionkey = encryptionkey;
	}
	public String getBusinesskey() {
		return businesskey;
	}
	public void setBusinesskey(String businesskey) {
		this.businesskey = businesskey;
	}
	public String getBankaccount() {
		return bankaccount;
	}
	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getDepositduedatetime() {
		return depositduedatetime;
	}
	public void setDepositduedatetime(String depositduedatetime) {
		this.depositduedatetime = depositduedatetime;
	}
	public String getDepositorname() {
		return depositorname;
	}
	public void setDepositorname(String depositorname) {
		this.depositorname = depositorname;
	}
	public String getDistributorname() {
		return distributorname;
	}
	public void setDistributorname(String distributorname) {
		this.distributorname = distributorname;
	}
	public String getLicenseexpireddatecontext() {
		return licenseexpireddatecontext;
	}
	public void setLicenseexpireddatecontext(String licenseexpireddatecontext) {
		this.licenseexpireddatecontext = licenseexpireddatecontext;
	}
	public String getMoneyamount() {
		return moneyamount;
	}
	public void setMoneyamount(String moneyamount) {
		this.moneyamount = moneyamount;
	}
	public String getOrderdatecontext() {
		return orderdatecontext;
	}
	public void setOrderdatecontext(String orderdatecontext) {
		this.orderdatecontext = orderdatecontext;
	}
	public String getOrdermonth() {
		return ordermonth;
	}
	public void setOrdermonth(String ordermonth) {
		this.ordermonth = ordermonth;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public String getStoreownerusername() {
		return storeownerusername;
	}
	public void setStoreownerusername(String storeownerusername) {
		this.storeownerusername = storeownerusername;
	}
	public String getTemporarypassword() {
		return temporarypassword;
	}
	public void setTemporarypassword(String temporarypassword) {
		this.temporarypassword = temporarypassword;
	}
	public String getVerificationcode() {
		return verificationcode;
	}
	public void setVerificationcode(String verificationcode) {
		this.verificationcode = verificationcode;
	}
	public String getWithdrawaldatecontext() {
		return withdrawaldatecontext;
	}
	public void setWithdrawaldatecontext(String withdrawaldatecontext) {
		this.withdrawaldatecontext = withdrawaldatecontext;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSenderphonenumber() {
		return senderphonenumber;
	}
	public void setSenderphonenumber(String senderphonenumber) {
		this.senderphonenumber = senderphonenumber;
	}
	public String getSendduedatatime() {
		return sendduedatatime;
	}
	public void setSendduedatatime(String sendduedatatime) {
		this.sendduedatatime = sendduedatatime;
	}
	public String getCenterphonenumber() {
		return centerphonenumber;
	}
	public void setCenterphonenumber(String centerphonenumber) {
		this.centerphonenumber = centerphonenumber;
	}
	public String getLinkurlmyoffice() {
		return linkurlmyoffice;
	}
	public void setLinkurlmyoffice(String linkurlmyoffice) {
		this.linkurlmyoffice = linkurlmyoffice;
	}
	public String getMembernumber() {
		return membernumber;
	}
	public void setMembernumber(String membernumber) {
		this.membernumber = membernumber;
	}
	public String getTempMappContent() {
		return tempMappContent;
	}
	public void setTempMappContent(String tempMappContent) {
		this.tempMappContent = tempMappContent;
	}
	public String getWeblinkYn() {
		return weblinkYn;
	}
	public void setWeblinkYn(String weblinkYn) {
		this.weblinkYn = weblinkYn;
	}
	public String getWeblinkJson() {
		return weblinkJson;
	}
	public void setWeblinkJson(String weblinkJson) {
		this.weblinkJson = weblinkJson;
	}
	
		
}