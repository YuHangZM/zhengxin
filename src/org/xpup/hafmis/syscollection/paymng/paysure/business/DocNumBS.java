package org.xpup.hafmis.syscollection.paymng.paysure.business;


import java.util.Map;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.CollParaDAO;
import org.xpup.hafmis.syscollection.common.dao.DocNumCancelDAO;
import org.xpup.hafmis.syscollection.common.dao.DocNumMaintainDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.DocNumCancel;
import org.xpup.hafmis.syscollection.common.domain.entity.DocNumMaintain;
import org.xpup.hafmis.syscollection.paymng.paysure.bsinterface.IDocNumBS;
/**
 * 
 * @author �����
 * 2007-6-26  
 */

public class DocNumBS implements IDocNumBS{
  
  private CollParaDAO collParaDAO=null;
  private DocNumMaintainDAO docNumMaintainDAO = null;
  private DocNumCancelDAO docNumCancelDAO=null;
  public void setDocNumCancelDAO(DocNumCancelDAO docNumCancelDAO) {
    this.docNumCancelDAO = docNumCancelDAO;
  }
  public void setDocNumMaintainDAO(DocNumMaintainDAO docNumMaintainDAO) {
    this.docNumMaintainDAO = docNumMaintainDAO;
  }
  public void insertDocNumCancel(String docNum, String officeCode, String bizYearmonth) throws Exception, BusinessException {
    try{
      if(officeCode!=null ||bizYearmonth!=null ||docNum!=null){
      docNumCancelDAO.insertDocNumCancel(docNum, officeCode, bizYearmonth);
      }
      }catch(Exception e){
        throw new BusinessException("���ƾ֤��ʧ��!");
      }
  }
  public String getDocNumdocNum(String officeCode, String bizYearmonth,SecurityInfo securityInfo) throws Exception, BusinessException {
    String docNum=null;
    try{
       docNum=docNumMaintainDAO.getDocNumdocNum(officeCode, bizYearmonth);
       Map office = securityInfo.getOfficeInnerCodeMap();
       String officecode = office.get(officeCode).toString();
       if (officecode.length() == 1) {
         officecode = "0" + officecode;
       }
       docNum=officecode+bizYearmonth+docNum;
      }catch(Exception e){
        throw new BusinessException("����ƾ֤��ʧ��!");
      }
    return docNum;
  }
  /**
   * ���ƾ֤�����ɷ�ʽ�����Ļ�������
   * @author ���
   */
  public String getDocNumDesignPara() throws Exception {
    String docNumDocument="";
    docNumDocument=collParaDAO.getDocNumDesignPara();
    return docNumDocument;
  }
  public void setCollParaDAO(CollParaDAO collParaDAO) {
    this.collParaDAO = collParaDAO;
  }
}
