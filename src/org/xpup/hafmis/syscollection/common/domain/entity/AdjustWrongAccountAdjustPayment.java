package org.xpup.hafmis.syscollection.common.domain.entity;

/**
 * ���˵���-���ɴ�K
 * 
 *@author ���
 *2007-6-25
 */
public class AdjustWrongAccountAdjustPayment extends AdjustWrongAccountHead{
  
  private static final long serialVersionUID = -3745806964079944938L;

  /** persistent field */
  private String bizType;

  public String getBizType() {
    return "���ɴ�";
  }
  
}