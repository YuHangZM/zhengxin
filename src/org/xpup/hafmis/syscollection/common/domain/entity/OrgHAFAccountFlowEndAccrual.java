package org.xpup.hafmis.syscollection.common.domain.entity;
/**
 * ��λס������ҵ����ˮ-��Ϣ
 * 
 * @author���� 2007-6-20
 */
public class OrgHAFAccountFlowEndAccrual extends OrgHAFAccountFlow{
  
  private static final long serialVersionUID = -3745806964079944938L;

  /** persistent field */
  private String bizType;

  public String getBizType() {
    return "��Ϣ";
  }
  
}