package org.xpup.hafmis.syscollection.peoplebank.documentstop.bsinterface;

import java.util.List;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IDocumentstopBS {

  //��������
  public void insertBankInfo(List list, SecurityInfo securityInfo) throws Exception;
  
  //���BANKINFO ��¼����
  public int countBankInfo() throws Exception;
  
  //��ѯBANKINFO ��Ϣ
  public List queryBankInfo() throws Exception;
  
  //�޸�BANKINFO ��Ϣ
  public void updateBankInfo(List list ,SecurityInfo securityInfo) throws Exception;
}
