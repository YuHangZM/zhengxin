package org.xpup.hafmis.syscollection.accounthandle.ratemng.business;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.ratemng.bsinterface.IRatemngBS;
import org.xpup.hafmis.syscollection.common.dao.ChangeRateBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.HafInterestRateDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.ChangeRateBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.HafInterestRate;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInOrg;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutOrg;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;



public class RatemngBS implements IRatemngBS {


  private HafInterestRateDAO hafInterestRateDAO = null;
  private HafOperateLogDAO hafOperateLogDAO = null;
  private ChangeRateBizActivityLogDAO changeRateBizActivityLogDAO = null;
  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO = null;

  public void setHafInterestRateDAO(HafInterestRateDAO hafInterestRateDAO) {
    this.hafInterestRateDAO = hafInterestRateDAO;
  }
  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }
  public void setChangeRateBizActivityLogDAO(
      ChangeRateBizActivityLogDAO changeRateBizActivityLogDAO) {
    this.changeRateBizActivityLogDAO = changeRateBizActivityLogDAO;
  }
  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }
  /**
   * @param û������
   * @return ��ȡ���´�
   * @throws BusinessException
   */
  public List findRatemngList_sy(Pagination pagination)throws Exception, BusinessException{
    BusinessException be = null;
    List list=new ArrayList();
    List returnList=new ArrayList();
    try{
      String officecode = (String) pagination.getQueryCriterions().get("officecode");
      String usetime = (String) pagination.getQueryCriterions().get("usetime");
      String ratetype = (String) pagination.getQueryCriterions().get("ratetype");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
     list=hafInterestRateDAO.queryRatemngList_sy(officecode,usetime,ratetype,orderBy, order, start, pageSize,page);
     if(!list.isEmpty()){
       for(int i=0;i<list.size();i++){
         HafInterestRate hafInterestRate=(HafInterestRate) list.get(i);
//         String temp_preRate = hafInterestRate.getPreRate().multiply(new BigDecimal(100)).toString();

         List temp_officecode=hafInterestRateDAO.getOfficecodeName(hafInterestRate.getOfficecode());
         if(!temp_officecode.isEmpty()){
           Object[] temp_object = (Object[]) temp_officecode.get(0);
           OrganizationUnit organizationUnit=(OrganizationUnit)temp_object[1];
           hafInterestRate.setOfficecodeName(organizationUnit.getName());
         }
         hafInterestRate.setPreRate(hafInterestRate.getPreRate().multiply(new BigDecimal(100.00)).divide(new BigDecimal(1.00), 2,BigDecimal.ROUND_HALF_UP));
         hafInterestRate.setCurRate(hafInterestRate.getCurRate().multiply(new BigDecimal(100.00)).divide(new BigDecimal(1.00), 2,BigDecimal.ROUND_HALF_UP));
         hafInterestRate.setShowIsStart(BusiTools.getBusiValue(Integer.parseInt(hafInterestRate.getIsStart().toString()), BusiConst.CHGTYPESTATUS));
         returnList.add(hafInterestRate);
       }
     }
     List  temp_list= hafInterestRateDAO.findRatemngCountList_sy(officecode,usetime,ratetype);
     pagination.setNrOfElements(temp_list.size());
    }catch(Exception e){
      e.printStackTrace();
      throw be;
    }
    return returnList;
  }
  /*
  ɾ��Ϊ���õ�����
  */
  public void removeRatemng_sy(String id,SecurityInfo securityInfo){
    try{
    HafInterestRate hafInterestRate=hafInterestRateDAO.queryById(new Integer(id));
    hafInterestRateDAO.deletRatemng_sy(hafInterestRate);
    //AA319��ɾ������
    ChangeRateBizActivityLog changeRateBizActivityLog=null;
    
    changeRateBizActivityLog=changeRateBizActivityLogDAO.queryByBizId(id, new Integer(1), "J");
    changeRateBizActivityLogDAO.delete_sy(changeRateBizActivityLog);
    // ����BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog
        .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(new Integer(
        BusiLogConst.OP_MODE_ACCOUNTMANAGE_MAINTAINRATE).toString());
    hafOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_DELETE)
        .toString());
    hafOperateLog.setOpBizId(new Integer(id));
    hafOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
    hafOperateLog.setOpIp(securityInfo.getUserInfo().getUserIp());
    hafOperateLog.setOrgId(new Integer(0));
    hafOperateLog.setOpTime(new Date());
    hafOperateLogDAO.insert(hafOperateLog);
    }catch(Exception e){
      e.printStackTrace();
    }
    }
  //��������
  public void useRatemng_sy(SecurityInfo securityInfo) throws BusinessException{
    try{
     String ip=securityInfo.getUserInfo().getUserIp();
     String oper=securityInfo.getUserInfo().getUsername();
     String bizdDay=securityInfo.getUserInfo().getBizDate();
     //��ȡ���´�Ŀ�Ĳ��Ұ��´��Ѿ���Ϣ������
     List allOfficeList=securityInfo.getAllOfficeList();
     OfficeDto officeDto=(OfficeDto) allOfficeList.get(0);
     String officecode=officeDto.getOfficeCode();
     //�ж��Ƿ�����ȡ��ת����ת�롢���ʵ���¼������ҵ������У��������������á�
     String flag=orgHAFAccountFlowDAO.queryBizstatus_WL();
     if(flag.equals("D")||flag.equals("E")||flag.equals("F")||flag.equals("G")){
       throw new BusinessException("����ʧ�ܣ���鿴�Ƿ���δ��ɵ�ҵ��");
     }
     List list=orgHAFAccountFlowDAO.queryorgHAFAccountFlow_sy();
     if(list.isEmpty()){
       Map map=hafInterestRateDAO.getday_sy(securityInfo.getUserInfo().getBizDate());
       String nowDate=(String) map.get("nowDate");
       String oldDate=(String) map.get("oldDate");
       //�ж��ǵڼ��ε�Ϣ��
       List temp_bizDate=hafInterestRateDAO.queryHafInterestRate_sy(nowDate, oldDate,officecode);
       // �޸ļ�¼��temp_bizDate.size()<4�ĳ�<13 wangy 2007-12-24
       if(temp_bizDate.size()<13){
       hafInterestRateDAO.doUseRatemng(ip, oper,bizdDay,temp_bizDate.size());
       }else{
         throw new BusinessException("����ʧ�ܣ���Ϣ�����Ѿ��ﵽ����");
       }
      }else {
       throw new BusinessException("����ʧ�ܣ���鿴�Ƿ���δ��ɵ�ҵ��");
     }
     }catch(BusinessException e){
      throw e;
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }
  //�������
  public void addRatemng_sy(HafInterestRate hafInterestRate,String bizDate,SecurityInfo securityInfo) throws BusinessException{
    String officecode=hafInterestRate.getOfficecode().toString();
    String info="";
    try{
      info=this.checkOfficeCode(officecode);
      if(info.equals("hi")){
       Map map=hafInterestRateDAO.getday_sy(bizDate);
       String nowDate=(String) map.get("nowDate");
       String oldDate=(String) map.get("oldDate");
       List temp_bizDate=hafInterestRateDAO.queryHafInterestRate_sy(nowDate, oldDate,officecode);
       // �޸ļ�¼���ж����ʵ�������<���ĳ�<12 wangy 2007-12-24
       if(temp_bizDate.size()<12){
       hafInterestRate.setIsStart(new BigDecimal(1.00));
       hafInterestRate.setBizDate(hafInterestRate.getBizDate());
       BigDecimal temp_curRate=hafInterestRate.getCurRate();
       //�����������С100��
       BigDecimal temp_last_curRate=temp_curRate.divide(new BigDecimal(100.00), 4, BigDecimal.ROUND_HALF_UP);
       BigDecimal temp_preRate=hafInterestRate.getPreRate();
       BigDecimal temp_last_preRate=temp_preRate.divide(new BigDecimal(100.00), 4, BigDecimal.ROUND_HALF_UP);
       hafInterestRate.setCurRate(temp_last_curRate);
       hafInterestRate.setPreRate(temp_last_preRate);
       String id=(String) hafInterestRateDAO.insert(hafInterestRate);
       //AA319�в�������
       ChangeRateBizActivityLog changeRateBizActivityLog=new ChangeRateBizActivityLog();
       changeRateBizActivityLog.setAction(new Integer(1));
       changeRateBizActivityLog.setBizid(new Integer(id));
       changeRateBizActivityLog.setOpTime(new Date());
       changeRateBizActivityLog.setOperator(securityInfo.getUserInfo().getUsername());
       changeRateBizActivityLogDAO.insert(changeRateBizActivityLog);
       // ����BA003
       HafOperateLog hafOperateLog = new HafOperateLog();
       hafOperateLog
           .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
       hafOperateLog.setOpModel(new Integer(
           BusiLogConst.OP_MODE_ACCOUNTMANAGE_MAINTAINRATE).toString());
       hafOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_ADD)
           .toString());
       hafOperateLog.setOpBizId(new Integer(id));
       hafOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
       hafOperateLog.setOpIp(securityInfo.getUserInfo().getUserIp());
       hafOperateLog.setOrgId(new Integer(0));
       hafOperateLog.setOpTime(new Date());
       hafOperateLogDAO.insert(hafOperateLog);
       }
       }else{
         throw new BusinessException("�˰��´��Ѵ���δ����,��ֹ���!��ȷ�Ϻ�����ӡ�");
       }
    }catch(BusinessException e){
      throw e;
    }
    catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("�������ʧ�ܣ�");
    }
  }
  //��ѯ�Ƿ���δ����
  public String checkOfficeCode(String officecode){
    String info="hi";
    try{
      List list=hafInterestRateDAO.findRatemngByCriterions(officecode);
     if(!list.isEmpty()){
      for(int i=0;i<list.size();i++){
        HafInterestRate temp_hafInterestRate=(HafInterestRate)list.get(i);
        //���Ƿ���Ϊ���õ�����оͷ���nohi
        if(temp_hafInterestRate.getIsStart().toString().compareTo("1")==0){
          return info="nohi";
        }
      }
     }
     }catch(Exception e){
       e.printStackTrace();
     }
     return info;
   }
}