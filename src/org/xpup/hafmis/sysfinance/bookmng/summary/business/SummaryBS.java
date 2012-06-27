package org.xpup.hafmis.sysfinance.bookmng.summary.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.summary.bsinterface.ISummaryBS;
import org.xpup.hafmis.sysfinance.bookmng.summary.dto.SummaryDTO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnOperateLogDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.BookParameter;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnOperateLog;

/**
 * Copy Right Information   : ����ժҪ
 * Project                  : �ļ���
 * @Version                 : 1.0
 * @author                  : ����
 * ��������                   : 10-25-2007
 */
public class SummaryBS implements ISummaryBS {
  
  private BookParameterDAO bookParameterDAO = null;
  private FnOperateLogDAO fnOperateLogDAO = null;
  
  public BookParameterDAO getBookParameterDAO() {
    return bookParameterDAO;
  }
  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }
  public FnOperateLogDAO getFnOperateLogDAO() {
    return fnOperateLogDAO;
  }
  public void setFnOperateLogDAO(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }
  
  /**
   * ���ز�ѯ���(List) ����ժҪ��Ϣ
   */
  public List findSummaryList(Pagination pagination, String bookId) throws Exception {
    List list=new ArrayList();
    try {
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list = this.bookParameterDAO.querySummaryList(bookId ,orderBy, order, start, pageSize,page);
    } catch (Exception e) {
      // TODO: handle exception
       e.printStackTrace();
    }
    return list;
  }
  
  /**
   * ���ز�ѯ�����¼��
   */
  public int querySummaryCount(String bookId) throws Exception {
    int count = 0;
    try {
      String temp_count = bookParameterDAO.querySummaryCount(bookId);
      count = Integer.parseInt(temp_count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }
  
  /**
   * �ж�����ĳ���ժҪ��FN102.PARAM_NUM=4�ļ�¼��PARAM_EXPLAIN�Ƿ����(���ڲ���)
   */
  public boolean is_SummaryParamExplainInsert(SummaryDTO summaryDTO) throws Exception {
    // TODO Auto-generated method stub
    try {
      String temp_ParamExplain = this.bookParameterDAO.querySummaryParamExplainInsert(summaryDTO.getBookId(),summaryDTO.getParamExplain());
      int count = Integer.parseInt(temp_ParamExplain);
      //�м�¼
      if(count > 0){
        return false;
      }
      } catch (Exception e) {
      e.printStackTrace();
    }
    return true;
  }
  public boolean is_SummaryPInFn201(String bookId,String  summaryId) throws Exception {
    // TODO Auto-generated method stub
    try {
      String temp_ParamExplain = bookParameterDAO.querySummaryInFn201(bookId,summaryId);
      int count = Integer.parseInt(temp_ParamExplain);
      //�м�¼
      if(count > 0){
        return false;
      }
      } catch (Exception e) {
      e.printStackTrace();
    }
    return true;
  }
  /**
   * ����FN311
      OP_SYS=����ϵͳ
      OP_MODEL=����ժҪ
      OP_BUTTON=1
      OP_IP=����Աip
      OP_TIME=ϵͳʱ��
      OPERATOR=����Ա
   * ����FN102
      BOOK_ID=��������
      PARAM_NUM=4
      PARAM_DESCRIP=����ժҪ
      PARAM_VALUE=����PARAM_VALUE+1(��11��ʼ���룩
      PARAM_EXPLAIN=�����ժҪ
      PARAM_EXPLAIN_EXPLA=����ժҪ���Ƶ�ȫƴ��
   */
  public void insertSummaryInfo(SummaryDTO summaryDTO,SecurityInfo securityInfo)throws Exception{
    try {
      //����FN311
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
      fnOperateLog.setOpModel(BusiLogConst.FN_OP_BOOKMNG_SUMMARY+"");
      fnOperateLog.setOpButton("1");
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLogDAO.insert(fnOperateLog);
      
      //����FN102
      BookParameter bookParameter = new BookParameter();
      bookParameter.setBookId(securityInfo.getBookId());
      bookParameter.setParamNum("4");
      bookParameter.setParamDescrip("����ժҪ");
      //���fn102 ����param_value�����ֵ
      List temp_list = bookParameterDAO.queryParamValue4Max(securityInfo.getBookId());
      if(temp_list.size() == 0){
        bookParameter.setParamValue("11");
      }else{
        int paramValue = Integer.parseInt(temp_list.get(0).toString())+1;
        bookParameter.setParamValue(paramValue+"");
      }
      bookParameter.setParamExplain(summaryDTO.getParamExplain());
      bookParameter.setParamExplainExplain(summaryDTO.getParamExplainPY().toUpperCase());
      bookParameterDAO.insert(bookParameter);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * ����paraId �ж�FN102�����Ƿ��м�¼
   */
  public boolean isSummaryById(String paraId) throws Exception {
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
   * �ж�����ĳ���ժҪ��FN102.PARAM_NUM=4�ļ�¼��PARAM_EXPLAIN�Ƿ����(�޸���)
   */
  public boolean is_SummaryParamExplainUpdate(SummaryDTO summaryDTO) throws Exception {
    // TODO Auto-generated method stub
    try {
      String temp_ParamExplain = this.bookParameterDAO.querySummaryParamExplainUpdate(summaryDTO.getBookId(),summaryDTO.getParamExplain(),summaryDTO.getParaId());
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
      OP_MODEL=����ժҪ
      OP_BUTTON=2
      OP_IP=����Աip
      OP_TIME=ϵͳʱ��
      OPERATOR=����Ա
   * ����FN102
      PARAM_EXPLAIN=�����ժҪ
      PARAM_EXPLAIN_EXPLA=����ժҪ���Ƶ�ȫƴ��
   * @param summaryDTO
   * @param securityInfo
   * @throws Exception
   */
  public void updateSummaryInfo(SummaryDTO summaryDTO, SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    try {
      //����FN102
      this.bookParameterDAO.updateSummaryInfo(summaryDTO.getParaId(), summaryDTO.getParamExplain(),summaryDTO.getParamExplainPY().toUpperCase());
      //����FN311
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
      fnOperateLog.setOpModel(BusiLogConst.FN_OP_BOOKMNG_SUMMARY+"");
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
   * ����ID ��ѯ����ժҪ
   */
  public SummaryDTO querySummaryParamExplainInfo(String paraId) throws Exception {
    // TODO Auto-generated method stub
    SummaryDTO summaryDTO = new SummaryDTO();
    try {
      List temp_list = bookParameterDAO.querySummaryParamExplainInfo(paraId);
      if(temp_list.size() == 0){
        return summaryDTO;
      }
      String paramExplain = ((SummaryDTO)(temp_list.get(0))).getParamExplain();
      summaryDTO.setParamExplain(paramExplain);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return summaryDTO;
  }
  
  
  /**
   * �жϸü�¼��FN102.PARA_ID��FN201.SUMMAY or FN210.SUMMAY���Ƿ����
   */
  public boolean isSummaryByParamValue(String paraId,String bookId) throws Exception {
    // TODO Auto-generated method stub
    try {
      String existence = bookParameterDAO.isSummaryByParamValue(paraId, bookId);
      if(existence.equals("0")){
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
  public void deleteSummaryInfo(String paraId,SecurityInfo securityInfo) throws Exception {
    try {
      //ɾ�� FN102���е� paraId ��¼
      bookParameterDAO.deleteCredencecharInfo(paraId);
      //����FN311
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
      fnOperateLog.setOpModel(BusiLogConst.FN_OP_BOOKMNG_SUMMARY+"");
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
  public boolean querySummaryInFn201(String bookId, String summaryId) throws Exception {
    // TODO Auto-generated method stub
    try {
      String temp_ParamExplain = bookParameterDAO.querySummaryInFn201(bookId,summaryId);
      int count = Integer.parseInt(temp_ParamExplain);
      //�м�¼
      if(count > 0){
        return false;
      }
      } catch (Exception e) {
      e.printStackTrace();
    }
    return true;
  }
}
