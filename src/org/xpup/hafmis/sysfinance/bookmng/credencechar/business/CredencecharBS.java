package org.xpup.hafmis.sysfinance.bookmng.credencechar.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.credencechar.bsinterface.ICredencecharBS;
import org.xpup.hafmis.sysfinance.bookmng.credencechar.dto.CredencecharDTO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnOperateLogDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.BookParameter;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnOperateLog;

/**
 * Copy Right Information   : ƾ֤��
 * Project                  : �ļ���
 * @Version                 : 1.0
 * @author                  : ����
 * ��������                   : 10-22-2007
 */
public class CredencecharBS implements ICredencecharBS {

  private BookParameterDAO bookParameterDAO = null;
  private FnOperateLogDAO fnOperateLogDAO = null;

  public BookParameterDAO getBookParameterDAO() {
    return bookParameterDAO;
  }

  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }

  /**
   * ���ز�ѯ���(List) ƾ֤����Ϣ
   */
  public List findCredencecharList(Pagination pagination,String bookId) throws Exception {
    List list=new ArrayList();
    try {
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list = this.bookParameterDAO.queryCredencecharList(bookId ,orderBy, order, start, pageSize,page);
    } catch (Exception e) {
      // TODO: handle exception
       e.printStackTrace();
    }
    return list;
  }

  /**
   * ���ز�ѯ�����¼��
   */
  public int queryCredencecharCount(String bookId) throws Exception {
    int count = 0;
    try {
      String temp_count = bookParameterDAO.queryCredencecharCount(bookId);
      count = Integer.parseInt(temp_count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  /**
   * �ж������ƾ֤����FN102.PARAM_NUM=2�ļ�¼��PARAM_EXPLAIN�Ƿ����(������)
   */
  public boolean is_CredencecharParamExplainInsert(CredencecharDTO credencecharDTO) throws Exception {
    // TODO Auto-generated method stub
    try {
      String temp_ParamExplain = this.bookParameterDAO.queryCredencecharParamExplainInsert(credencecharDTO.getBookId(),credencecharDTO.getParamExplain());
      int count = Integer.parseInt(temp_ParamExplain);
      //�м�¼
      if(count > 0){
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    //û�м�¼���Բ���
    return true;
  }
  
  /**
   * �ж������ƾ֤����FN102.PARAM_NUM=2�ļ�¼��PARAM_EXPLAIN�Ƿ����(�޸���)
   */
  public boolean is_CredencecharParamExplainUpdate(CredencecharDTO credencecharDTO) throws Exception {
    // TODO Auto-generated method stub
    try {
      String temp_ParamExplain = this.bookParameterDAO.queryCredencecharParamExplainUpdate(credencecharDTO.getBookId(),credencecharDTO.getParamExplain(),credencecharDTO.getParaId());
      int count = Integer.parseInt(temp_ParamExplain);
      //�м�¼
      if(count > 0){
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    //û�м�¼���Բ���
    return true;
  }
  
  /**
   * ����FN311
      OP_SYS=����ϵͳ
      OP_MODEL=ƾ֤��
      OP_BUTTON=1
      OP_IP=����Աip
      OP_TIME=ϵͳʱ��
      OPERATOR=����Ա
   * ����FN102
      BOOK_ID=��������
      PARAM_NUM=2
      PARAM_DESCRIP=��ƾ֤�֡�
      PARAM_VALUE=����PARAM_VALUE+1
      PARAM_EXPLAIN=�����ƾ֤��
      PARAM_EXPLAIN_EXPLAIN=�����ƾ֤������
   */
  public void insertCredencecharInfo(CredencecharDTO credencecharDTO,SecurityInfo securityInfo)throws Exception{
    try {
      //����FN311
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
      fnOperateLog.setOpModel(BusiLogConst.FN_OP_BOOKMNG_CREDENCECHAR+"");
      fnOperateLog.setOpButton("1");
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLogDAO.insert(fnOperateLog);
      
      //����FN102
      BookParameter bookParameter = new BookParameter();
      bookParameter.setBookId(securityInfo.getBookId());
      bookParameter.setParamNum("2");
      bookParameter.setParamDescrip("ƾ֤��");
      //���fn102 ����param_value�����ֵ
      List temp_list = bookParameterDAO.queryParamValueMax(securityInfo.getBookId());
      if(temp_list.size() == 0){
        bookParameter.setParamValue("1");
      }else{
        int paramValue = Integer.parseInt(temp_list.get(0).toString())+1;
        bookParameter.setParamValue(paramValue+"");
      }
      bookParameter.setParamExplain(credencecharDTO.getParamExplain());
      bookParameter.setParamExplainExplain(credencecharDTO.getParamExplainExplain());
      bookParameterDAO.insert(bookParameter);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public FnOperateLogDAO getFnOperateLogDAO() {
    return fnOperateLogDAO;
  }

  public void setFnOperateLogDAO(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }

  /**
   * ����ID ��ѯ��ƾ֤�� �� ƾ֤������
   */
  public CredencecharDTO queryCredencecharParamExplainInfo(String paraId) throws Exception {
    // TODO Auto-generated method stub
    CredencecharDTO credencecharDTO = new CredencecharDTO();
    try {
      List temp_list = bookParameterDAO.queryCredencecharParamExplainInfo(paraId);
      if(temp_list.size() == 0){
        return credencecharDTO;
      }
      String paramExplain = ((CredencecharDTO)(temp_list.get(0))).getParamExplain();
      String paramExplainExplain = ((CredencecharDTO)(temp_list.get(0))).getParamExplainExplain();
      credencecharDTO.setParamExplain(paramExplain);
      credencecharDTO.setParamExplainExplain(paramExplainExplain);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return credencecharDTO;
  }

  /**
   * ����FN102
      PARAM_EXPLAIN=�����ƾ֤��
      PARAM_EXPLAIN_EXPLAIN=�����ƾ֤������
   * ����FN311
      OP_SYS=����ϵͳ
      OP_MODEL=ƾ֤��
      OP_BUTTON=2
      OP_IP=����Աip
      OP_TIME=ϵͳʱ��
      OPERATOR=����Ա
   */
  public void updateCredencecharInfo(CredencecharDTO credencecharDTO, SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    try {
      //����FN102
      this.bookParameterDAO.updateCredencecharInfo(credencecharDTO.getParaId(), credencecharDTO.getParamExplain(), credencecharDTO.getParamExplainExplain());
      //����FN311
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
      fnOperateLog.setOpModel(BusiLogConst.FN_OP_BOOKMNG_CREDENCECHAR+"");
      fnOperateLog.setOpButton("2");
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLogDAO.insert(fnOperateLog);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * ����paraId �ж�FN102�����Ƿ��м�¼
   */
  public boolean isCredencecharById(String paraId) throws Exception {
    // TODO Auto-generated method stub
    try {
      int count = Integer.parseInt(bookParameterDAO.isCredencecharById(paraId));
      if(count>0){
        return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
  
  /**
   * ɾ�� FN102���е� paraId ��¼
   * ����FN311��־
   * @param paraId
   * @throws Exception
   */
  public void deleteCredencecharInfo(String paraId,SecurityInfo securityInfo) throws Exception {
    try {
      //ɾ�� FN102���е� paraId ��¼
      bookParameterDAO.deleteCredencecharInfo(paraId);
      //����FN311
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
      fnOperateLog.setOpModel(BusiLogConst.FN_OP_BOOKMNG_CREDENCECHAR+"");
      fnOperateLog.setOpButton("3");
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLogDAO.insert(fnOperateLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * �жϸü�¼��FN102.paraId��FN201.CREDENCE_CHARACTER or FN210.CREDENCE_CHARACTER���Ƿ����
   */
  public boolean isCredencecharByParamValue(String paraId,String bookId) throws Exception {
    // TODO Auto-generated method stub
    try {
      String existence = bookParameterDAO.isCredencecharByParamValue(paraId,bookId);
      if(existence.equals("0")){
        return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
  
}
