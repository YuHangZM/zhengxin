package org.xpup.hafmis.sysfinance.bookmng.credencemodle.business;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.credencemodle.bsinterface.ICredencemodleBS;
import org.xpup.hafmis.sysfinance.bookmng.credencemodle.dto.CredencemodleDTO;
import org.xpup.hafmis.sysfinance.bookmng.credencemodle.dto.CredencemodleListDTO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnOperateLogDAO;
import org.xpup.hafmis.sysfinance.common.dao.SubjectDAO;
import org.xpup.hafmis.sysfinance.common.dao.CredenceModleDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnOperateLog;
import org.xpup.hafmis.sysfinance.common.domain.entity.CredenceModle;

public class CredencemodleBS implements ICredencemodleBS {

  private FnOperateLogDAO fnOperateLogDAO = null;

  private SubjectDAO subjectDAO = null;

  private BookParameterDAO bookParameterDAO = null;
  
  private CredenceModleDAO credenceModleDAO=null;
  
  public void setFnOperateLog(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }

  public void setSubjectDAO(SubjectDAO subjectDAO) {
    this.subjectDAO = subjectDAO;
  }

  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }

  public void setFnOperateLogDAO(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }
 
  /**
   * author wsh ƾ֤ģʽ���� ��ȡҳ���ժҪ�б�
   * 
   * @param securityInfo Ȩ��
   * @2007-10-23
   * @return
   */
  public List findCredencemodleSummrayList(SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub      
    List summrayList=null;    
    try{
      summrayList=bookParameterDAO.getParamExplain("4","10", securityInfo);                 
    }catch(Exception e){
      e.printStackTrace();
    }
    return summrayList;
  }
  /**
   * author wsh ƾ֤ģʽ���� �жϿ�Ŀ�����Ƿ��Ǵ沢����һ����Ŀ����
   * 
   * @param subjectCode ��Ŀ����
   * @param securityInfo Ȩ��
   * @2007-10-24
   * @return
   */
  public void findCredencemodleExist(String subjectCode,SecurityInfo securityInfo)throws Exception, BusinessException {
    try {
      BusinessException be = null;
      int count = 0;
      count=subjectDAO.findSubjectrelationFirstCode_wsh(subjectCode,securityInfo).intValue();
      if (count == 0) {//����һ����Ŀ����
        be = new BusinessException("�ÿ�Ŀ���벻���ڻ������ϣ�");
        throw be;
      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  public void findSubjectRelationFirstCode(String subjectCode) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    
  }

  public Integer findSubjectrelationExist(String subjectCode) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  public void findSubjectrelationParentId(String subjectCode) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    
  }
  /**
   * author wsh ƾ֤ģʽ���� ��ѯ��Ŀ������fn110������
   * 
   * @param subjectCode ��Ŀ����
   * @param securityInfo Ȩ��
   * @2007-10-24
   * @return
   */
  public CredencemodleDTO findCredencemodleInfo(String subjectCode, SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    CredencemodleDTO credencemodleDTO = new CredencemodleDTO();
    try {
      credencemodleDTO = subjectDAO
          .findSubejectRelationTaInfo(subjectCode,securityInfo);      
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return credencemodleDTO;
  }
  /**
   * author wsh ƾ֤ģʽ���� ��ѯ��Ŀ�����ҵ������bizType���������moneyType��fn120�ļ�¼�Ƿ����
   * 
   * @param subjectCode ��Ŀ����
   * @param bizType ҵ������
   * @param moneyType �������
   * @param securityInfo Ȩ��
   * @2007-10-24
   * @return
   */
  public Integer findCredencemodleInfoExist(String subjectCode, String bizType, String moneyType, SecurityInfo securityInfo,String subjectDirection) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    Integer count = new Integer(0);
    try {
      count = credenceModleDAO.findCredencemodleInfoExist_wsh(subjectCode,bizType,moneyType,securityInfo,subjectDirection);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return count;
  }

  public void setCredenceModleDAO(CredenceModleDAO credenceModleDAO) {
    this.credenceModleDAO = credenceModleDAO;
  }
  /**
   * author wsh ƾ֤ģʽ���� �����¼��fn120
   * 
   * @param subjectCode ��Ŀ����
   * @param bizType ҵ������
   * @param moneyType �������
   * @param subjectDirection ��Ŀ����   
   * @param securityInfo Ȩ��
   * @2007-10-24
   * @return
   */
  public void saveCredencemodle(String subjectCode, String bizType, String moneyType, String subjectDirection, String summary, SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    try {
      CredenceModle credenceModle=new CredenceModle();
      if(subjectCode!=null&&!"".equals(subjectCode.trim())){
        credenceModle.setSubjectCode(subjectCode);
      }
      if(bizType!=null&&!"".equals(bizType.trim())){
        credenceModle.setBizType(bizType);
      }
      if(moneyType!=null&&!"".equals(moneyType.trim())){
        credenceModle.setMoneyType(moneyType);
      }
      if(subjectDirection!=null&&!"".equals(subjectDirection.trim())){
        credenceModle.setSubjectDirection(subjectDirection);
      }
      if(summary!=null&&!"".equals(summary.trim())){
        credenceModle.setSummray(summary);
      }
      credenceModle.setBookId(securityInfo.getBookId());
      credenceModleDAO.insert(credenceModle);
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpModel(String
          .valueOf(BusiLogConst.FN_OP_BOOKMNG_CREDENCEMODLE));
      fnOperateLog
          .setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
      fnOperateLog.setOpTime(new Date());
      fnOperateLogDAO.insert(fnOperateLog);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }    
  }
  /**
   * author wsh ƾ֤ģʽ���ø��¼�¼��fn120
   * 
   * @param subjectCode ��Ŀ����
   * @param bizType ҵ������
   * @param moneyType �������
   * @param subjectDirection ��Ŀ����   
   * @param securityInfo Ȩ��
   * @2007-10-24
   * @return
   */
  public void updateCredencemodle(String subjectCode, String bizType, String moneyType, String subjectDirection, String summary, SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    try {
      credenceModleDAO.updateCredencemodle_wsh(subjectCode,bizType,moneyType,subjectDirection, summary,securityInfo);
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpModel(String
          .valueOf(BusiLogConst.FN_OP_BOOKMNG_CREDENCEMODLE));
      fnOperateLog
          .setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
      fnOperateLog.setOpTime(new Date());
      fnOperateLogDAO.insert(fnOperateLog);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }    
  }
  /**
   * author wsh ƾ֤ģʽ���ò�ѯ�б���Ϣ
   * 
   * @param pagination  
   * @param securityInfo Ȩ��
   * @2007-10-24
   * @return
   */
  public List queryCredencemodleList(SecurityInfo securityInfo,
      Pagination pagination) {
    List list = new ArrayList();
    String subjectCode1 = "";
    String subjectName1 = "";
    String subjectDirection1 = "";
    String bizType1 = "";
    String bookId = "";
    try {
      if (pagination.getQueryCriterions().get("subjectCode1") != null) {
        subjectCode1 = (String) pagination.getQueryCriterions().get(
            "subjectCode1");
      }
      if (pagination.getQueryCriterions().get("subjectName1") != null) {
        subjectName1 = (String) pagination.getQueryCriterions().get(
            "subjectName1");
      }
      if (pagination.getQueryCriterions().get("subjectDirection1") != null) {
        subjectDirection1 = (String) pagination.getQueryCriterions().get(
            "subjectDirection1");
      }
      if (pagination.getQueryCriterions().get("bizType1") != null) {
        bizType1 = (String) pagination.getQueryCriterions().get("bizType1");
      }
      if (securityInfo.getBookId() != null) {
        bookId = securityInfo.getBookId();
      }
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list = credenceModleDAO.queryCredencemodleList_wsh(subjectCode1,
          subjectName1, subjectDirection1, bizType1, bookId, orderBy, order,
          start, pageSize, page);
      int count = credenceModleDAO.queryCredencemodleCountList_wsh(
          subjectCode1, subjectName1, subjectDirection1, bizType1, bookId);
      pagination.setNrOfElements(count);
      Iterator iter = list.iterator();
      CredencemodleListDTO credencemodleListDTO = null;
      while (iter.hasNext()) {
        credencemodleListDTO = (CredencemodleListDTO) iter.next();
        credencemodleListDTO.setBizType(BusiTools.getBusiValue(Integer
            .parseInt(credencemodleListDTO.getBizType().toString()),
            BusiConst.FNBUSINESSTYPE));
        credencemodleListDTO.setMoneyType(BusiTools.getBusiValue(Integer
            .parseInt(credencemodleListDTO.getMoneyType().toString()),
            BusiConst.FNMONEYTYPE));
        credencemodleListDTO.setBalanceDirection(BusiTools.getBusiValue_WL(
            credencemodleListDTO.getBalanceDirection().toString(),
            BusiConst.BALANCEDIRECTION));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**
   * author wsh ƾ֤ģʽ���ò�ѯ�б���Ϣ
   * 
   * @param pagination  
   * @param securityInfo Ȩ��
   * @2007-10-24
   * @return
   */
  public void deleteCredencemodle(String id,SecurityInfo securityInfo) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    try {
      BusinessException be = null;
      int count = 0;
      count = credenceModleDAO.findCredencemodleExist_wsh(id,securityInfo.getBookId())
          .intValue();
      if (count == 0) {
        be = new BusinessException("�ü�¼�Ѿ�ɾ����");
        throw be;
      } else {
        credenceModleDAO.deleteCredencemodle_wsh(id,securityInfo.getBookId());
        FnOperateLog fnOperateLog = new FnOperateLog();
        fnOperateLog.setBookId(securityInfo.getBookId());
        fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_DELETE));
        fnOperateLog.setOperator(securityInfo.getUserName());
        fnOperateLog.setOpIp(securityInfo.getUserIp());
        fnOperateLog.setOpModel(String
            .valueOf(BusiLogConst.FN_OP_BOOKMNG_CREDENCEMODLE));
        fnOperateLog
            .setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
        fnOperateLog.setOpTime(new Date());
        fnOperateLogDAO.insert(fnOperateLog);
      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }
  /**
   * author wsh ƾ֤ģʽ���ò�ѯ���¼�¼������
   * 
   * @param subjectCode ��Ŀ����
   * @param securityInfo Ȩ��
   * @2007-10-24
   * @return
   */
  public CredencemodleDTO findCredencemodleUpdateInfo(String subjectCode, SecurityInfo securityInfo,String id) throws Exception {
    // TODO Auto-generated method stub
    CredencemodleDTO credencemodleDTO=new CredencemodleDTO();
    try {
      credencemodleDTO=subjectDAO.findCredencemodleUpdateInfo(subjectCode, securityInfo,id);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return credencemodleDTO;
  }
}
