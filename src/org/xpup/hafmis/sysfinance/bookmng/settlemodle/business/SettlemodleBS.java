package org.xpup.hafmis.sysfinance.bookmng.settlemodle.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.settlemodle.bsinterface.ISettlemodleBS;
import org.xpup.hafmis.sysfinance.bookmng.settlemodle.dto.SettlemodleDTO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnOperateLogDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.BookParameter;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnOperateLog;

/**
 * Copy Right Information   : ���㷽ʽ
 * Project                  : �ļ���
 * @Version                 : 1.0
 * @author                  : ����
 * ��������                   : 10-24-2007
 */
public class SettlemodleBS implements ISettlemodleBS {

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
   * ���ز�ѯ���(List) ���㷽ʽ��Ϣ
   */
  public List findSettlemodleList(Pagination pagination, String bookId) throws Exception {
    List list=new ArrayList();
    try {
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list = this.bookParameterDAO.querySettlemodleList(bookId ,orderBy, order, start, pageSize,page);
    } catch (Exception e) {
      // TODO: handle exception
       e.printStackTrace();
    }
    return list;
  }
  
  /**
   * ���ز�ѯ�����¼��
   */
  public int querySettlemodleCount(String bookId) throws Exception {
    int count = 0;
    try {
      String temp_count = bookParameterDAO.querySettlemodleCount(bookId);
      count = Integer.parseInt(temp_count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }
  
  /**
   * �ж�����Ľ��㷽ʽ��FN102.PARAM_NUM=3�ļ�¼��PARAM_EXPLAIN�Ƿ����(���ڲ���)
   */
  public boolean is_SettlemodleParamExplainInsert(SettlemodleDTO settlemodleDTO) throws Exception {
    // TODO Auto-generated method stub
    try {
      String temp_ParamExplain = this.bookParameterDAO.querySettlemodleParamExplainInsert(settlemodleDTO.getBookId(),settlemodleDTO.getParamExplain());
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
   * �ж�����Ľ��㷽ʽ��FN102.PARAM_NUM=3�ļ�¼��PARAM_EXPLAIN�Ƿ����(�޸���)
   */
  public boolean is_SettlemodleParamExplainUpdate(SettlemodleDTO settlemodleDTO) throws Exception {
    // TODO Auto-generated method stub
    try {
      String temp_ParamExplain = this.bookParameterDAO.querySettlemodleParamExplainUpdate(settlemodleDTO.getBookId(),settlemodleDTO.getParamExplain(),settlemodleDTO.getParaId());
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
      OP_MODEL=���㷽ʽ
      OP_BUTTON=1
      OP_IP=����Աip
      OP_TIME=ϵͳʱ��
      OPERATOR=����Ա
   * ����FN102
      BOOK_ID=��������
      PARAM_NUM=2
      PARAM_DESCRIP=�����㷽ʽ��
      PARAM_VALUE=����PARAM_VALUE+1
      PARAM_EXPLAIN=�����ֵ
   */
  public void insertSettlemodleInfo(SettlemodleDTO settlemodleDTO,SecurityInfo securityInfo)throws Exception{
    try {
      //����FN311
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
      fnOperateLog.setOpModel(BusiLogConst.FN_OP_BOOKMNG_SETTLEMODLE+"");
      fnOperateLog.setOpButton("1");
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLogDAO.insert(fnOperateLog);
      
      //����FN102
      BookParameter bookParameter = new BookParameter();
      bookParameter.setBookId(securityInfo.getBookId());
      bookParameter.setParamNum("3");
      bookParameter.setParamDescrip("���㷽ʽ");
      //���fn102 ����param_value�����ֵ
      List temp_list = bookParameterDAO.queryParamValue3Max(securityInfo.getBookId());
      if(temp_list.size() == 0){
        bookParameter.setParamValue("1");
      }else{
        int paramValue = Integer.parseInt(temp_list.get(0).toString())+1;
        bookParameter.setParamValue(paramValue+"");
      }
      bookParameter.setParamExplain(settlemodleDTO.getParamExplain());
      bookParameterDAO.insert(bookParameter);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * ����FN102
      PARAM_EXPLAIN=����Ľ��㷽ʽ
   * ����FN311
      OP_SYS=����ϵͳ
      OP_MODEL=���㷽ʽ
      OP_BUTTON=2
      OP_IP=����Աip
      OP_TIME=ϵͳʱ��
      OPERATOR=����Ա 
   */
  public void updateSettlemodleInfo(SettlemodleDTO settlemodleDTO, SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    try {
      //����FN102
      this.bookParameterDAO.updateSettlemodleInfo(settlemodleDTO.getParaId(), settlemodleDTO.getParamExplain());
      //����FN311
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
      fnOperateLog.setOpModel(BusiLogConst.FN_OP_BOOKMNG_SETTLEMODLE+"");
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
  public boolean isSettlemodleById(String paraId) throws Exception {
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
   * ����ID ��ѯ���㷽ʽ
   */
  public SettlemodleDTO querySettlemodleParamExplainInfo(String paraId) throws Exception {
    // TODO Auto-generated method stub
    SettlemodleDTO settlemodleDTO = new SettlemodleDTO();
    try {
      List temp_list = bookParameterDAO.querySettlemodleParamExplainInfo(paraId);
      if(temp_list.size() == 0){
        return settlemodleDTO;
      }
      String paramExplain = ((SettlemodleDTO)(temp_list.get(0))).getParamExplain();
      settlemodleDTO.setParamExplain(paramExplain);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return settlemodleDTO;
  }
  
  /**
   * �жϸü�¼��FN102.PARA_ID��FN201.SETT_TYPE or FN210.SETT_TYPE���Ƿ����
   */
  public boolean isSettlemodleByParamValue(String paraId,String bookId) throws Exception {
    // TODO Auto-generated method stub
    try {
      String existence = bookParameterDAO.isSettlemodleByParamValue(paraId, bookId);
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
  public void deleteSettlemodleInfo(String paraId,SecurityInfo securityInfo) throws Exception {
    try {
      //ɾ�� FN102���е� paraId ��¼
      bookParameterDAO.deleteCredencecharInfo(paraId);
      //����FN311
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
      fnOperateLog.setOpModel(BusiLogConst.FN_OP_BOOKMNG_SETTLEMODLE+"");
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
  
}
