package org.xpup.hafmis.sysloan.dataready.loanpara.business;

import org.xpup.hafmis.syscollection.common.dao.CollParaDAO;
import org.xpup.hafmis.sysloan.dataready.loanpara.bsinterface.ILoanDocNumDesignBS;
/**
 * 
 * @author ���
 * 2008-5-21  
 */

public class LoanDocNumDesignBS implements ILoanDocNumDesignBS{

  private CollParaDAO collParaDAO=null;
  public CollParaDAO getCollParaDAO() {
    return collParaDAO;
  }

  public void setCollParaDAO(CollParaDAO collParaDAO) {
    this.collParaDAO = collParaDAO;
  }
  /**
   * ���ƾ֤�����ɷ�ʽ�����Ļ�������
   * @author ���
   */
  public String getLoanDocNumDesignPara() throws Exception {
    String loanDocNumDocument="";
    loanDocNumDocument=collParaDAO.getLoanDocNumDesignPara();
    return loanDocNumDocument;
  }
  
  /**
   * ����ƾ֤�����ɷ�ʽ�����Ļ�������
   * @author 
   */
  public void updateLoanDocNumDesignPara(String loanDocNumDocument) throws Exception {
    collParaDAO.updateLoanDocNumDesignPara(loanDocNumDocument);
  }
  public String getNamePara() throws Exception {
    String name="";
    name=collParaDAO.getLoanNamePara();
    return name;
  }
  public void updateNamePara(String name) throws Exception {
    collParaDAO.updateLoanNamePara(name);
  }
  
}
