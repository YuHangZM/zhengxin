package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto;

import java.math.BigDecimal;

/**
 * Copy Right Information : ��װ���Զ������б����ݵ�DTO
 * Goldsoft Project : CredenceFillinTbListDTO
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.10.17
 */
public class CredenceFillinTbListDTO {

  /** ��ʾ��ҳ���ҵ������ */
  private String bizType = "";
  
  /** �����ص�ҵ������num */
  private String numBizType = "";

  /** �������� */
  private String settDate = "";

  /** ����� */
  private String settNum = "";

  /** �����ܽ�� */
  private BigDecimal sumOccurMoney = new BigDecimal(0.00);

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getSettDate() {
    return settDate;
  }

  public void setSettDate(String settDate) {
    this.settDate = settDate;
  }

  public String getSettNum() {
    return settNum;
  }

  public void setSettNum(String settNum) {
    this.settNum = settNum;
  }

  public BigDecimal getSumOccurMoney() {
    return sumOccurMoney;
  }

  public void setSumOccurMoney(BigDecimal sumOccurMoney) {
    this.sumOccurMoney = sumOccurMoney;
  }

  public String getNumBizType() {
    return numBizType;
  }

  public void setNumBizType(String numBizType) {
    this.numBizType = numBizType;
  }
}
