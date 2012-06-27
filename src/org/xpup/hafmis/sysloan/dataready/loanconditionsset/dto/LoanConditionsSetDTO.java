package org.xpup.hafmis.sysloan.dataready.loanconditionsset.dto;

import java.math.BigDecimal;

public class LoanConditionsSetDTO {
 private String office="";//���´�
 private String isRegular="";//1������������˻�״̬�Ƿ�����
 private String one="";//1���Ƿ�����
 private String chgbizMonth="";//2����������������������ڶ�����
 private String two="";//2���Ƿ�����
 private String accountOpenMonth="";//3����������ʱ����ڶ�����
 private String three="";//3���Ƿ�����
 private String maleAge="";//4��������ʵ������Ӵ������޲�����������(��)
 private String femaleAge="";//4��������ʵ������Ӵ������޲�����������(Ů)
 private String four="";//4���Ƿ�����
 private String loanLimitMin="";//5���������޲����ڶ�����
 private String loanLimitMax="";//5��������޲�����������
 private String five="";//5���Ƿ�����
 private String overTimeMax="";//6���������,�����ڲ�����������
 private String six="";//6���Ƿ�����
 private BigDecimal loanMoneyMax=new BigDecimal(0.00);//7������ӵ�й����������ܳ�������Ԫ
 private BigDecimal otherLoanMoneyMax=new BigDecimal(0.00);//7���и��������˵Ĵ�����ܳ�������Ԫ
 private String seven="";//7���Ƿ�����
 private BigDecimal merchandiseRateMax=new BigDecimal(0.00);//8��������ܳ�����Ʒ���۵İٷֱ�
 private String eight="";//8���Ƿ�����
 private BigDecimal secondhandRateMax=new BigDecimal(0.00);//9��������ܳ������ַ��۵İٷֱ�
 private String nine="";//9���Ƿ�����
 private BigDecimal merchandiseMoneyMax=new BigDecimal(0.00);//10����Ʒ��������߽���������Ԫ
 private String ten="";//10���Ƿ�����
 private BigDecimal secondhandMoneyMax=new BigDecimal(0.00);//11�����ַ�������߽���������Ԫ
 private String eleven="";//11���Ƿ�����
 
 //������ʼ-->
 private String qianJiaoMonth="";//2.2������Ƿ������
 private BigDecimal secondhandRateMax_1=new BigDecimal(0.00);//9.1��������ܳ������ַ��۵İٷֱȣ�5--10�꣩
 private BigDecimal secondhandRateMax_2=new BigDecimal(0.00);//9.2��������ܳ������ַ��۵İٷֱȣ�11�����ϣ�
 private BigDecimal beiShu=new BigDecimal(0.00);//12���ó��������ͥ��Ա����������������ס������������ 
 private String timeMax_1="";//13.1���������������Ʒ��
 private String timeMax_2="";//13.1������������޶��ַ�
 private String comNature_1="";//15.1��λ����һ
 private String comNature_2="";//16.1��λ���ʶ�
 private String comNature_3="";//17.1��λ������
 private String personCount_1="";//15.2��λ����һ������
 private String personCount_2="";//16.2��λ���ʶ�������
 private String personCount_3="";//17.2��λ������������
 private String monthCount_1="";//15.3��λ����һ������
 private String monthCount_2="";//16.3��λ���ʶ�������
 private String monthCount_3="";//17.3��λ������������
 private BigDecimal  salaryRate =new BigDecimal(0.00);//16������������뻹�����
 private String twive="";//12���Ƿ�����
 private String thirteen="";//13���Ƿ�����
 private String fourteen="";//14���Ƿ�����
 private String fifteen="";//15���Ƿ�����
 private String sixteen="";//16���Ƿ�����
 private String seventeen="";//17���Ƿ�����
 private String eighteen="";//18���Ƿ�����
 private String ninteen="";//19���Ƿ�����
 //<--��������
public String getAccountOpenMonth() {
  return accountOpenMonth;
}
public void setAccountOpenMonth(String accountOpenMonth) {
  this.accountOpenMonth = accountOpenMonth;
}
public String getChgbizMonth() {
  return chgbizMonth;
}
public void setChgbizMonth(String chgbizMonth) {
  this.chgbizMonth = chgbizMonth;
}
public String getEight() {
  return eight;
}
public void setEight(String eight) {
  this.eight = eight;
}
public String getEleven() {
  return eleven;
}
public void setEleven(String eleven) {
  this.eleven = eleven;
}
public String getFemaleAge() {
  return femaleAge;
}
public void setFemaleAge(String femaleAge) {
  this.femaleAge = femaleAge;
}
public String getFive() {
  return five;
}
public void setFive(String five) {
  this.five = five;
}
public String getFour() {
  return four;
}
public void setFour(String four) {
  this.four = four;
}
public String getIsRegular() {
  return isRegular;
}
public void setIsRegular(String isRegular) {
  this.isRegular = isRegular;
}
public String getLoanLimitMax() {
  return loanLimitMax;
}
public void setLoanLimitMax(String loanLimitMax) {
  this.loanLimitMax = loanLimitMax;
}
public String getLoanLimitMin() {
  return loanLimitMin;
}
public void setLoanLimitMin(String loanLimitMin) {
  this.loanLimitMin = loanLimitMin;
}
public BigDecimal getLoanMoneyMax() {
  return loanMoneyMax;
}
public void setLoanMoneyMax(BigDecimal loanMoneyMax) {
  this.loanMoneyMax = loanMoneyMax;
}
public String getMaleAge() {
  return maleAge;
}
public void setMaleAge(String maleAge) {
  this.maleAge = maleAge;
}
public BigDecimal getMerchandiseMoneyMax() {
  return merchandiseMoneyMax;
}
public void setMerchandiseMoneyMax(BigDecimal merchandiseMoneyMax) {
  this.merchandiseMoneyMax = merchandiseMoneyMax;
}
public BigDecimal getMerchandiseRateMax() {
  return merchandiseRateMax;
}
public void setMerchandiseRateMax(BigDecimal merchandiseRateMax) {
  this.merchandiseRateMax = merchandiseRateMax;
}
public String getNine() {
  return nine;
}
public void setNine(String nine) {
  this.nine = nine;
}
public String getOne() {
  return one;
}
public void setOne(String one) {
  this.one = one;
}
public BigDecimal getOtherLoanMoneyMax() {
  return otherLoanMoneyMax;
}
public void setOtherLoanMoneyMax(BigDecimal otherLoanMoneyMax) {
  this.otherLoanMoneyMax = otherLoanMoneyMax;
}
public String getOverTimeMax() {
  return overTimeMax;
}
public void setOverTimeMax(String overTimeMax) {
  this.overTimeMax = overTimeMax;
}
public BigDecimal getSecondhandMoneyMax() {
  return secondhandMoneyMax;
}
public void setSecondhandMoneyMax(BigDecimal secondhandMoneyMax) {
  this.secondhandMoneyMax = secondhandMoneyMax;
}
public BigDecimal getSecondhandRateMax() {
  return secondhandRateMax;
}
public void setSecondhandRateMax(BigDecimal secondhandRateMax) {
  this.secondhandRateMax = secondhandRateMax;
}
public String getSeven() {
  return seven;
}
public void setSeven(String seven) {
  this.seven = seven;
}
public String getSix() {
  return six;
}
public void setSix(String six) {
  this.six = six;
}
public String getTen() {
  return ten;
}
public void setTen(String ten) {
  this.ten = ten;
}
public String getThree() {
  return three;
}
public void setThree(String three) {
  this.three = three;
}
public String getTwo() {
  return two;
}
public void setTwo(String two) {
  this.two = two;
}
public String getOffice() {
  return office;
}
public void setOffice(String office) {
  this.office = office;
}
public BigDecimal getBeiShu() {
  return beiShu;
}
public void setBeiShu(BigDecimal beiShu) {
  this.beiShu = beiShu;
}
public String getFourteen() {
  return fourteen;
}
public void setFourteen(String fourteen) {
  this.fourteen = fourteen;
}
public String getQianJiaoMonth() {
  return qianJiaoMonth;
}
public void setQianJiaoMonth(String qianJiaoMonth) {
  this.qianJiaoMonth = qianJiaoMonth;
}
public BigDecimal getSalaryRate() {
  return salaryRate;
}
public void setSalaryRate(BigDecimal salaryRate) {
  this.salaryRate = salaryRate;
}
public BigDecimal getSecondhandRateMax_1() {
  return secondhandRateMax_1;
}
public void setSecondhandRateMax_1(BigDecimal secondhandRateMax_1) {
  this.secondhandRateMax_1 = secondhandRateMax_1;
}
public BigDecimal getSecondhandRateMax_2() {
  return secondhandRateMax_2;
}
public void setSecondhandRateMax_2(BigDecimal secondhandRateMax_2) {
  this.secondhandRateMax_2 = secondhandRateMax_2;
}
public String getThirteen() {
  return thirteen;
}
public void setThirteen(String thirteen) {
  this.thirteen = thirteen;
}
public String getTimeMax_1() {
  return timeMax_1;
}
public void setTimeMax_1(String timeMax_1) {
  this.timeMax_1 = timeMax_1;
}
public String getTimeMax_2() {
  return timeMax_2;
}
public void setTimeMax_2(String timeMax_2) {
  this.timeMax_2 = timeMax_2;
}
public String getTwive() {
  return twive;
}
public void setTwive(String twive) {
  this.twive = twive;
}
public String getFifteen() {
  return fifteen;
}
public void setFifteen(String fifteen) {
  this.fifteen = fifteen;
}
public String getSixteen() {
  return sixteen;
}
public void setSixteen(String sixteen) {
  this.sixteen = sixteen;
}
public String getComNature_1() {
  return comNature_1;
}
public void setComNature_1(String comNature_1) {
  this.comNature_1 = comNature_1;
}
public String getComNature_2() {
  return comNature_2;
}
public void setComNature_2(String comNature_2) {
  this.comNature_2 = comNature_2;
}
public String getComNature_3() {
  return comNature_3;
}
public void setComNature_3(String comNature_3) {
  this.comNature_3 = comNature_3;
}
public String getEighteen() {
  return eighteen;
}
public void setEighteen(String eighteen) {
  this.eighteen = eighteen;
}
public String getMonthCount_1() {
  return monthCount_1;
}
public void setMonthCount_1(String monthCount_1) {
  this.monthCount_1 = monthCount_1;
}
public String getMonthCount_2() {
  return monthCount_2;
}
public void setMonthCount_2(String monthCount_2) {
  this.monthCount_2 = monthCount_2;
}
public String getMonthCount_3() {
  return monthCount_3;
}
public void setMonthCount_3(String monthCount_3) {
  this.monthCount_3 = monthCount_3;
}
public String getNinteen() {
  return ninteen;
}
public void setNinteen(String ninteen) {
  this.ninteen = ninteen;
}
public String getPersonCount_1() {
  return personCount_1;
}
public void setPersonCount_1(String personCount_1) {
  this.personCount_1 = personCount_1;
}
public String getPersonCount_2() {
  return personCount_2;
}
public void setPersonCount_2(String personCount_2) {
  this.personCount_2 = personCount_2;
}
public String getPersonCount_3() {
  return personCount_3;
}
public void setPersonCount_3(String personCount_3) {
  this.personCount_3 = personCount_3;
}
public String getSeventeen() {
  return seventeen;
}
public void setSeventeen(String seventeen) {
  this.seventeen = seventeen;
}
}
