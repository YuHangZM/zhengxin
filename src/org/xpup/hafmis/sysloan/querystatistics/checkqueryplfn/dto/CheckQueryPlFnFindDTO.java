package org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.dto;

public class CheckQueryPlFnFindDTO {
 private String contractid="";//����˺�ͬ���
 private String borrowername="";//���������
 private String loankouacc="";//�����˺�
 private String cardnum="";//֤������
 private String loanstartdateSt="";//��������ڿ�ʼ
 private String loanstartdateEnd="";//��������ڽ���
public String getBorrowername() {
  return borrowername;
}
public void setBorrowername(String borrowername) {
  this.borrowername = borrowername;
}
public String getCardnum() {
  return cardnum;
}
public void setCardnum(String cardnum) {
  this.cardnum = cardnum;
}
public String getContractid() {
  return contractid;
}
public void setContractid(String contractid) {
  this.contractid = contractid;
}
public String getLoankouacc() {
  return loankouacc;
}
public void setLoankouacc(String loankouacc) {
  this.loankouacc = loankouacc;
}
public String getLoanstartdateEnd() {
  return loanstartdateEnd;
}
public void setLoanstartdateEnd(String loanstartdateEnd) {
  this.loanstartdateEnd = loanstartdateEnd;
}
public String getLoanstartdateSt() {
  return loanstartdateSt;
}
public void setLoanstartdateSt(String loanstartdateSt) {
  this.loanstartdateSt = loanstartdateSt;
}
}
