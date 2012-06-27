package org.xpup.hafmis.syscollection.peoplebank.documentstop.business;

import java.util.Date;
import java.util.List;

import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.BankInfoDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.BankInfo;
import org.xpup.hafmis.syscollection.peoplebank.documentstop.bsinterface.IDocumentstopBS;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;

public class DocumentstopBS implements IDocumentstopBS {

  private BankInfoDAO bankInfoDAO = null;
  protected HafOperateLogDAO hafOperateLogDAO = null;

  public BankInfoDAO getBankInfoDAO() {
    return bankInfoDAO;
  }

  public void setBankInfoDAO(BankInfoDAO bankInfoDAO) {
    this.bankInfoDAO = bankInfoDAO;
  }

  /**
   * ��������
   * @param documentstopAF
   * @throws Exception
   */
  public void insertBankInfo(List list ,SecurityInfo securityInfo) throws Exception{
    try {
      BankInfo bankInfo = new BankInfo();
      bankInfo.setNum("1");
      bankInfo.setDescrip("���ݸ�ʽ�汾��");
      bankInfo.setValue(list.get(0).toString());
      bankInfoDAO.insert(bankInfo);
      
      BankInfo bankInfo2 = new BankInfo();
      bankInfo2.setNum("2");
      bankInfo2.setDescrip("�����ϱ�����");
      bankInfo2.setValue(list.get(1).toString());
      bankInfoDAO.insert(bankInfo2);
      
      BankInfo bankInfo3 = new BankInfo();
      bankInfo3.setNum("3");
      bankInfo3.setDescrip("��ϵ��");
      bankInfo3.setValue(list.get(2).toString());
      bankInfoDAO.insert(bankInfo3);
      
      BankInfo bankInfo4 = new BankInfo();
      bankInfo4.setNum("4");
      bankInfo4.setDescrip("��ϵ�绰");
      bankInfo4.setValue(list.get(3).toString());
      bankInfoDAO.insert(bankInfo4);
      
      BankInfo bankInfo5 = new BankInfo();
      bankInfo5.setNum("5");
      bankInfo5.setDescrip("�����ص�");
      bankInfo5.setValue(list.get(4).toString());
      bankInfoDAO.insert(bankInfo5);
      
      BankInfo bankInfo6 = new BankInfo();
      bankInfo6.setNum("6");
      bankInfo6.setDescrip("���ݷ�������");
      bankInfo6.setValue(list.get(5).toString());
      bankInfoDAO.insert(bankInfo6);
      
      //  ����BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_BANKDATAEXP_RECORDHEADSETTING);
      hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_ADD);
      hafOperateLog.setOpIp(securityInfo.getUserIp());
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOperator(securityInfo.getUserName());
      hafOperateLogDAO.insert(hafOperateLog);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * ���BANKINFO ��¼����
   * @return
   * @throws Exception
   */
  public int countBankInfo() throws Exception{
    try {
      return bankInfoDAO.countBankInfo();
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }
  
  /**
   * ��ѯBANKINFO ��Ϣ
   * @return
   * @throws Exception
   */
  public List queryBankInfo() throws Exception{
    try {
      return bankInfoDAO.queryBankInfo();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
  /**
   * �޸�BANKINFO ��Ϣ
   */
  public void updateBankInfo(List list ,SecurityInfo securityInfo) throws Exception{
    try {
      bankInfoDAO.updateBankInfo(list);
      //  ����BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_BANKDATAEXP_RECORDHEADSETTING);
      hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_MODIFY);
      hafOperateLog.setOpIp(securityInfo.getUserIp());
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOperator(securityInfo.getUserName());
      hafOperateLogDAO.insert(hafOperateLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public HafOperateLogDAO getHafOperateLogDAO() {
    return hafOperateLogDAO;
  }

  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }

}
