package org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto;

import java.math.BigDecimal;

public class BizCheckTotalDTO {
  
  private BigDecimal occurMoneyTotle= new BigDecimal(0.00);// ���Ž��-�ܶ�

  private BigDecimal reclaimCorpusTotle= new BigDecimal(0.00);// ���ձ���-�ܶ�

  private BigDecimal reclaimAccrualTotle= new BigDecimal(0.00);// ������Ϣ-�ܶ��ܶ�

  private BigDecimal realPunishInterestTotle= new BigDecimal(0.00);// ���շ�Ϣ-�ܶ�
  
  private BigDecimal badDebtTotle= new BigDecimal(0.00);// ���˺������-�ܶ�

  private BigDecimal putUpMoneyTotle= new BigDecimal(0.00);// ���˽��-�ܶ�

  private BigDecimal bailTotle= new BigDecimal(0.00);// ��֤��-�ܶ�

  private BigDecimal bailAccrualTotle= new BigDecimal(0.00);// ��֤����Ϣ-�ܶ�
  
  private int affirmbizSt = 0;// ȷ��״̬����

  private int checkbizSt = 0;// ����״̬����

  private int count = 0;
  
  private BigDecimal reclaimtotle = new BigDecimal(0.00);// ����Ӧ�����

  private BigDecimal reclaimbacktotle = new BigDecimal(0.00);// ����ʵ�����

  public int getAffirmbizSt() {
    return affirmbizSt;
  }

  public void setAffirmbizSt(int affirmbizSt) {
    this.affirmbizSt = affirmbizSt;
  }

  public BigDecimal getBadDebtTotle() {
    return badDebtTotle;
  }

  public void setBadDebtTotle(BigDecimal badDebtTotle) {
    this.badDebtTotle = badDebtTotle;
  }

  public BigDecimal getBailAccrualTotle() {
    return bailAccrualTotle;
  }

  public void setBailAccrualTotle(BigDecimal bailAccrualTotle) {
    this.bailAccrualTotle = bailAccrualTotle;
  }

  public BigDecimal getBailTotle() {
    return bailTotle;
  }

  public void setBailTotle(BigDecimal bailTotle) {
    this.bailTotle = bailTotle;
  }

  public int getCheckbizSt() {
    return checkbizSt;
  }

  public void setCheckbizSt(int checkbizSt) {
    this.checkbizSt = checkbizSt;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public BigDecimal getOccurMoneyTotle() {
    return occurMoneyTotle;
  }

  public void setOccurMoneyTotle(BigDecimal occurMoneyTotle) {
    this.occurMoneyTotle = occurMoneyTotle;
  }

  public BigDecimal getPutUpMoneyTotle() {
    return putUpMoneyTotle;
  }

  public void setPutUpMoneyTotle(BigDecimal putUpMoneyTotle) {
    this.putUpMoneyTotle = putUpMoneyTotle;
  }

  public BigDecimal getRealPunishInterestTotle() {
    return realPunishInterestTotle;
  }

  public void setRealPunishInterestTotle(BigDecimal realPunishInterestTotle) {
    this.realPunishInterestTotle = realPunishInterestTotle;
  }

  public BigDecimal getReclaimAccrualTotle() {
    return reclaimAccrualTotle;
  }

  public void setReclaimAccrualTotle(BigDecimal reclaimAccrualTotle) {
    this.reclaimAccrualTotle = reclaimAccrualTotle;
  }

  public BigDecimal getReclaimCorpusTotle() {
    return reclaimCorpusTotle;
  }

  public void setReclaimCorpusTotle(BigDecimal reclaimCorpusTotle) {
    this.reclaimCorpusTotle = reclaimCorpusTotle;
  }

  public BigDecimal getReclaimbacktotle() {
    return reclaimbacktotle;
  }

  public void setReclaimbacktotle(BigDecimal reclaimbacktotle) {
    this.reclaimbacktotle = reclaimbacktotle;
  }

  public BigDecimal getReclaimtotle() {
    return reclaimtotle;
  }

  public void setReclaimtotle(BigDecimal reclaimtotle) {
    this.reclaimtotle = reclaimtotle;
  }


}
