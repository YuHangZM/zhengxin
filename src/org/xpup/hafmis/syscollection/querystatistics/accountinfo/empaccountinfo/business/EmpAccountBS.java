package org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.business;

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
import org.xpup.hafmis.syscollection.common.dao.EmpHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.HafInterestRateDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.ChangeRateBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.HafInterestRate;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInOrg;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutOrg;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.bsinterface.IEmpAccountBS;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.dto.EmpaccountinfoDTO;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;



public class EmpAccountBS implements IEmpAccountBS {

  private EmpHAFAccountFlowDAO empHAFAccountFlowDAO = null;

  public void setEmpHAFAccountFlowDAO(EmpHAFAccountFlowDAO empHAFAccountFlowDAO) {
    this.empHAFAccountFlowDAO = empHAFAccountFlowDAO;
  }
  /**
   * @param û������
   * @return 
   * @throws BusinessException
   */
  public List findEmpAccountList_sy(Pagination pagination,SecurityInfo securityInfo)throws Exception, BusinessException{
    BusinessException be = null;
    List list=new ArrayList();
    List returnList=new ArrayList();
    List printList=new ArrayList();
    try{
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      String startDate=(String) pagination.getQueryCriterions().get("startDate");
      String lastDate=(String) pagination.getQueryCriterions().get("lastDate");
      String orgIdaa101=(String) pagination.getQueryCriterions().get("orgIdaa101");
      String nameba001=(String)pagination.getQueryCriterions().get("nameba001");
      String empIdaa102=(String)pagination.getQueryCriterions().get("empIdaa102");
      String nameba002=(String)pagination.getQueryCriterions().get("nameba002");
      BigDecimal temp_prebalance=new BigDecimal(0.00);
      BigDecimal empBalance=new BigDecimal(0.00);
      //���ҷ��ص�������,�����д��empid��orgid
      list=empHAFAccountFlowDAO.queryEmpAccountList_sy(orderBy, order, start, pageSize,page,startDate,lastDate,orgIdaa101,nameba001,empIdaa102,nameba002,securityInfo);
       if(!list.isEmpty()){
       for(int i=0;i<list.size();i++){
         Object[] object = (Object[])list.get(i);
         BigDecimal empid =(BigDecimal) object[0];
         BigDecimal orgid=(BigDecimal) object[1];
         EmpHAFAccountFlow empHAFAccountFlow=new EmpHAFAccountFlow();
         List temp_list=empHAFAccountFlowDAO.queryEmpHAFAccount_sy(startDate, empid.toString(), orgid.toString(), lastDate);
         empHAFAccountFlow=(EmpHAFAccountFlow) temp_list.get(0);
       // �����˻����
         empBalance=empHAFAccountFlowDAO.queryEmpBalance(empHAFAccountFlow.getEmpId(),new Integer(empHAFAccountFlow.getOrg().getId().toString()));
         empHAFAccountFlow.getEmp().setBalance(empBalance);
         
         returnList.add(empHAFAccountFlow);

       }
     }
       //�������е�,��Ҫ���ڼ��㷢�����,��ӡ.�����жϲ���ͬ��.
     List  temp_list= empHAFAccountFlowDAO.queryEmpAccountCountList_sy(startDate, lastDate, orgIdaa101, nameba001, empIdaa102, nameba002, securityInfo);
     //������Ϊ��ӡ׼������
     if(!temp_list.isEmpty()){
       EmpaccountinfoDTO empaccountinfoDTO=new EmpaccountinfoDTO();
       for(int i=0;i<temp_list.size();i++){
         Object[] object = (Object[])temp_list.get(i);
         BigDecimal empid =(BigDecimal) object[0];
         BigDecimal orgid=(BigDecimal) object[1];
         //ҳ����ʾ��������
         EmpHAFAccountFlow empHAFAccountFlow=new EmpHAFAccountFlow();
         List temp_list2=empHAFAccountFlowDAO.queryEmpHAFAccount_sy(startDate,empid.toString(),orgid.toString(),lastDate);
         empHAFAccountFlow=(EmpHAFAccountFlow) temp_list2.get(0);
         // �����ʻ����
         empBalance=empHAFAccountFlowDAO.queryEmpBalance(empHAFAccountFlow.getEmpId(),new Integer(empHAFAccountFlow.getOrg().getId().toString()));
         empHAFAccountFlow.getEmp().setBalance(empBalance);
        
         empaccountinfoDTO.setTemp_credit(empaccountinfoDTO.getTemp_credit().add(empHAFAccountFlow.getTemp_credit()));
         empaccountinfoDTO.setTemp_debit(empaccountinfoDTO.getTemp_debit().add(empHAFAccountFlow.getTemp_debit()));
         empaccountinfoDTO.setTemp_interest(empaccountinfoDTO.getTemp_interest().add(empHAFAccountFlow.getTemp_interest()));
         printList.add(empHAFAccountFlow);
       }
       pagination.getQueryCriterions().put("temp1_interest", empaccountinfoDTO.getTemp_interest().toString());
       pagination.getQueryCriterions().put("temp_credit",empaccountinfoDTO.getTemp_credit().toString());
       pagination.getQueryCriterions().put("temp_debit",empaccountinfoDTO.getTemp_debit().toString());
     }
     pagination.getQueryCriterions().put("printList", printList);
     pagination.setNrOfElements(temp_list.size());
    }catch(Exception e){
      e.printStackTrace();
      throw be;
    }
    return returnList;
  }
  /**
   * �����²�ѯ
   * 
  */
  public List findEmpAccountMonthList_sy(Pagination pagination,SecurityInfo securityInfo){
    List list=new ArrayList();
    List returnList=new ArrayList();
    List printList=new ArrayList();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    String startDate=(String) pagination.getQueryCriterions().get("startDate");
    String lastDate=(String) pagination.getQueryCriterions().get("lastDate");
    String orgIdaa101=(String) pagination.getQueryCriterions().get("orgIdaa101");
    String empIdaa102=(String)pagination.getQueryCriterions().get("empIdaa102");
    BigDecimal temp_prebalance=new BigDecimal(0.00);
   try{
         //��Ϊ����empid���Զ���Ψһ��ֻȡһ��emp��Ϊ������Ϣ
     EmpHAFAccountFlow empHAFAccountFlow=new EmpHAFAccountFlow();
     List temp_list=empHAFAccountFlowDAO.queryEmpHAFAccountFlowList(empIdaa102,orgIdaa101);
     empHAFAccountFlow=(EmpHAFAccountFlow) temp_list.get(0);
     //Ϊǰ̨��ʾ�������׼������.
     //��ʾ��λ����
     pagination.getQueryCriterions().put("nameba001", empHAFAccountFlow.getOrg().getOrgInfo().getName());
     //��ʾְ������
     pagination.getQueryCriterions().put("nameba002", empHAFAccountFlow.getEmpName());
         //����group by������Ҽ�¼��ÿ���·������,���ҷ�����������������,0�Ƿֶ�ʱ�����ǰ���ȡ��,1�Ǳ��ڴ���������,2�Ǳ��ڽ跽������
         List temp_empHAFAccountFlow2=empHAFAccountFlowDAO.empAccountcurbalance1(orderBy, order, start, pageSize,page,startDate, empIdaa102, orgIdaa101, lastDate);
         if(!temp_empHAFAccountFlow2.isEmpty()){
           for(int j=0;j<temp_empHAFAccountFlow2.size();j++){
              EmpHAFAccountFlow empHAFAccountFlow1=new EmpHAFAccountFlow();
              empHAFAccountFlow1.setEmp(empHAFAccountFlow.getEmp());
              empHAFAccountFlow1.setId(empHAFAccountFlow.getId());
              empHAFAccountFlow1.setOrg(empHAFAccountFlow.getOrg());
              empHAFAccountFlow1.setEmpId(empHAFAccountFlow.getEmpId());
              empHAFAccountFlow1.setEmpName(empHAFAccountFlow.getEmpName());
             Object[] empHAFAccount = (Object[]) temp_empHAFAccountFlow2.get(j);
           //���ڴ���������
           BigDecimal  temp_credit=(BigDecimal) empHAFAccount[1];
           //���ڽ跽������
           BigDecimal  temp_debit=(BigDecimal) empHAFAccount[2];
           
           String tempdate=(String) empHAFAccount[0];
           String tempStart=startDate.substring(0,6);
           String tempLast=lastDate.substring(0,6);
           //�ڳ�ʱ��
           String tempStartDate="";
           //��ĩʱ��
           String tempLastDate="";
           if(Integer.parseInt(tempdate)>Integer.parseInt(tempStart)){
             tempStartDate=tempdate+"01";
             if(Integer.parseInt(tempdate)>=Integer.parseInt(tempLast)){
               tempLastDate=lastDate;
             }else{
               tempLastDate=tempdate+"31";
             }
           }
           if(Integer.parseInt(tempdate)==Integer.parseInt(tempStart)){
             tempStartDate=startDate;
             if(Integer.parseInt(tempdate)>=Integer.parseInt(tempLast)){
               tempLastDate=lastDate;
             }else{
               tempLastDate=tempdate+"31";
             }
           } 
           //���ڴ�������
           List countCreditlist=empHAFAccountFlowDAO.countEmpHAFAccountCredit(tempStartDate, empIdaa102, orgIdaa101, tempLastDate);
           Integer countCredit=(Integer) countCreditlist.get(0);
           //���ڽ跽����
           List countDebitlist=empHAFAccountFlowDAO.countEmpHAFAccountDebit(tempStartDate, empIdaa102, orgIdaa101, tempLastDate);
           Integer countDebit=(Integer) countDebitlist.get(0);
           empHAFAccountFlow1.setCountCredit(countCredit+"");
           empHAFAccountFlow1.setCountDebit(countDebit+"");
           empHAFAccountFlow1.setTemp_credit(temp_credit);
           empHAFAccountFlow1.setTemp_debit(temp_debit);
           //�ڳ����
           List temp_empHAFAccountFlow=empHAFAccountFlowDAO.empAccountPrebalance(tempStartDate, empIdaa102, orgIdaa101);
           if(!temp_empHAFAccountFlow.isEmpty()&&temp_empHAFAccountFlow!=null){
             if(temp_empHAFAccountFlow.get(0)!=null&&!temp_empHAFAccountFlow.equals("")){
               
             temp_prebalance=(BigDecimal)temp_empHAFAccountFlow.get(0);
             empHAFAccountFlow1.setPrebalance(temp_prebalance);
             }else{
               empHAFAccountFlow1.setPrebalance(new BigDecimal(0.00));
             }     
             }
           //��ĩ���
           List temp_empHAFAccountFlow3=empHAFAccountFlowDAO.empAccountCurbalance(tempLastDate,empIdaa102, orgIdaa101);
           if(!temp_empHAFAccountFlow3.isEmpty()){
             if(temp_empHAFAccountFlow3.get(0)!=null&&!temp_empHAFAccountFlow3.equals("")){
             BigDecimal temp_curbalance=(BigDecimal)temp_empHAFAccountFlow3.get(0);
             empHAFAccountFlow1.setCurbalance(temp_curbalance);
             }else{
               empHAFAccountFlow1.setCurbalance(new BigDecimal(0.00));
             }
           }
//         ʱ���ȡ����
           String temp_settDate=tempdate;
           empHAFAccountFlow1.setDisplayTme(temp_settDate);
           returnList.add(empHAFAccountFlow1);
           
         }
         } 
         //��ѯ���м�¼,ͳ�ƺϼƷ�����,��ӡ׼������.���÷���ͬ��.
         List temp_count_list=empHAFAccountFlowDAO.empAccountcurCountBalance1(startDate, empIdaa102, orgIdaa101, lastDate);
         //���ںϼ���ʾ������
         EmpaccountinfoDTO empaccountinfoDTO=new EmpaccountinfoDTO();
         for(int j=0;j<temp_count_list.size();j++){
           Object[] empHAFAccount = (Object[]) temp_count_list.get(j);
           
           //���ڴ���������
           BigDecimal  temp_credit=(BigDecimal) empHAFAccount[1];
           //���ڽ跽������
           BigDecimal  temp_debit=(BigDecimal) empHAFAccount[2];
           //������Ϣ
           BigDecimal  temp_interest=(BigDecimal) empHAFAccount[3];
           empaccountinfoDTO.setTemp_credit(empaccountinfoDTO.getTemp_credit().add(temp_credit));
           empaccountinfoDTO.setTemp_debit(empaccountinfoDTO.getTemp_debit().add(temp_debit));
           empaccountinfoDTO.setTemp_interest(empaccountinfoDTO.getTemp_interest().add(temp_interest));
         }
         pagination.getQueryCriterions().put("temp1_interest", empaccountinfoDTO.getTemp_interest().toString());
         pagination.getQueryCriterions().put("temp_credit",empaccountinfoDTO.getTemp_credit().toString());
         pagination.getQueryCriterions().put("temp_debit",empaccountinfoDTO.getTemp_debit().toString());
         //������Ϊ��ӡ׼������.
         if(!temp_count_list.isEmpty()){
           for(int j=0;j<temp_count_list.size();j++){
              EmpHAFAccountFlow empHAFAccountFlow1=new EmpHAFAccountFlow();
              empHAFAccountFlow1.setEmp(empHAFAccountFlow.getEmp());
              empHAFAccountFlow1.setId(empHAFAccountFlow.getId());
              empHAFAccountFlow1.setOrg(empHAFAccountFlow.getOrg());
              empHAFAccountFlow1.setEmpId(empHAFAccountFlow.getEmpId());
              empHAFAccountFlow1.setEmpName(empHAFAccountFlow.getEmpName());
             Object[] empHAFAccount = (Object[]) temp_count_list.get(j);
           //���ڴ���������
           BigDecimal  temp_credit=(BigDecimal) empHAFAccount[1];
           //���ڽ跽������
           BigDecimal  temp_debit=(BigDecimal) empHAFAccount[2];
           
           String tempdate=(String) empHAFAccount[0];
           String tempStart=startDate.substring(0,6);
           String tempLast=lastDate.substring(0,6);
           //�ڳ�ʱ��
           String tempStartDate="";
           //��ĩʱ��
           String tempLastDate="";
           if(Integer.parseInt(tempdate)>Integer.parseInt(tempStart)){
             tempStartDate=tempdate+"01";
             if(Integer.parseInt(tempdate)>=Integer.parseInt(tempLast)){
               tempLastDate=lastDate;
             }else{
               tempLastDate=tempdate+"31";
             }
           }
           if(Integer.parseInt(tempdate)==Integer.parseInt(tempStart)){
             tempStartDate=startDate;
             if(Integer.parseInt(tempdate)>=Integer.parseInt(tempLast)){
               tempLastDate=lastDate;
             }else{
               tempLastDate=tempdate+"31";
             }
           } 
           //���ڴ�������
           List countCreditlist=empHAFAccountFlowDAO.countEmpHAFAccountCredit(tempStartDate, empIdaa102, orgIdaa101, tempLastDate);
           Integer countCredit=(Integer) countCreditlist.get(0);
           //���ڽ跽����
           List countDebitlist=empHAFAccountFlowDAO.countEmpHAFAccountDebit(tempStartDate, empIdaa102, orgIdaa101, tempLastDate);
           Integer countDebit=(Integer) countDebitlist.get(0);
           empHAFAccountFlow1.setCountCredit(countCredit+"");
           empHAFAccountFlow1.setCountDebit(countDebit+"");
           empHAFAccountFlow1.setTemp_credit(temp_credit);
           empHAFAccountFlow1.setTemp_debit(temp_debit);
           //�ڳ����
           List temp_empHAFAccountFlow=empHAFAccountFlowDAO.empAccountPrebalance(tempStartDate, empIdaa102, orgIdaa101);
           if(!temp_empHAFAccountFlow.isEmpty()&&temp_empHAFAccountFlow!=null){
             if(temp_empHAFAccountFlow.get(0)!=null&&!temp_empHAFAccountFlow.equals("")){
               
             temp_prebalance=(BigDecimal)temp_empHAFAccountFlow.get(0);
             empHAFAccountFlow1.setPrebalance(temp_prebalance);
             }else{
               empHAFAccountFlow1.setPrebalance(new BigDecimal(0.00));
             }     
             }
           //��ĩ���
           List temp_empHAFAccountFlow3=empHAFAccountFlowDAO.empAccountCurbalance(tempLastDate,empIdaa102, orgIdaa101);
           if(!temp_empHAFAccountFlow3.isEmpty()){
             if(temp_empHAFAccountFlow3.get(0)!=null&&!temp_empHAFAccountFlow3.equals("")){
             BigDecimal temp_curbalance=(BigDecimal)temp_empHAFAccountFlow3.get(0);
             empHAFAccountFlow1.setCurbalance(temp_curbalance);
             }else{
               empHAFAccountFlow1.setCurbalance(new BigDecimal(0.00));
             }
           }
//         ʱ���ȡ����
           String temp_settDate=tempdate;
           empHAFAccountFlow1.setDisplayTme(temp_settDate);
           printList.add(empHAFAccountFlow1);
           
         }
         } 
     pagination.getQueryCriterions().put("printList", printList);
     pagination.setNrOfElements(temp_count_list.size()); 
   }catch(Exception e){
     e.printStackTrace();
   } 
    return returnList;
  }
  /**
   * �����ղ�ѯ
   * temp_timeΪ�����������·�
  */
  public List findEmpAccountDayList_sy(Pagination pagination,SecurityInfo securityInfo){
    List list=new ArrayList();
    List returnList=new ArrayList();
    List printList=new ArrayList();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    String startDate=(String) pagination.getQueryCriterions().get("startDate");
    String lastDate=(String) pagination.getQueryCriterions().get("lastDate");
    String orgIdaa101=(String) pagination.getQueryCriterions().get("orgIdaa101");
    String empIdaa102=(String)pagination.getQueryCriterions().get("empIdaa102");
    String temp_time=(String)pagination.getQueryCriterions().get("temp_time");
    BigDecimal temp_prebalance=new BigDecimal(0.00);
   try{
         //��Ϊ����empid���Զ���Ψһ��ֻȡһ��emp��Ϊ������Ϣ
     EmpHAFAccountFlow empHAFAccountFlow=new EmpHAFAccountFlow();
     List temp_list=empHAFAccountFlowDAO.queryEmpHAFAccountFlowList(empIdaa102,orgIdaa101);
     empHAFAccountFlow=(EmpHAFAccountFlow) temp_list.get(0);
     //ҳ����ʾ�ڲ�ѯ����׼������.
     //��ʾ��λ����
     pagination.getQueryCriterions().put("nameba001", empHAFAccountFlow.getOrg().getOrgInfo().getName());
     //��ʾְ������
     pagination.getQueryCriterions().put("nameba002", empHAFAccountFlow.getEmpName());
     String nowStartDate="";
     String nowLastDate="";
     if(Integer.parseInt(temp_time+"01")>Integer.parseInt(startDate)){
       nowStartDate=temp_time+"01";
       if(Integer.parseInt(temp_time+"31")>Integer.parseInt(lastDate)){
         nowLastDate=lastDate;
       }else{
         nowLastDate=temp_time+"31";
       }
     }else{
       nowStartDate=startDate;
       if(Integer.parseInt(temp_time+"31")>Integer.parseInt(lastDate)){
         nowLastDate=lastDate;
       }else{
         nowLastDate=temp_time+"31";
       }
     }
         //����group by������Ҽ�¼��ÿ�����е���������,����ֵ������,0�Ǳ��ڴ���������,1�Ǳ��ڽ跽������,2,3�Ƿ����ʱ��.
         List temp_empHAFAccountFlow2=empHAFAccountFlowDAO.empAccountcurbalance2(orderBy, order, start, pageSize,page,nowStartDate,empIdaa102,orgIdaa101, nowLastDate);
         if(!temp_empHAFAccountFlow2.isEmpty()){
           for(int j=0;j<temp_empHAFAccountFlow2.size();j++){
              EmpHAFAccountFlow empHAFAccountFlow1=new EmpHAFAccountFlow();
              empHAFAccountFlow1.setEmp(empHAFAccountFlow.getEmp());
              empHAFAccountFlow1.setId(empHAFAccountFlow.getId());
              empHAFAccountFlow1.setOrg(empHAFAccountFlow.getOrg());
              empHAFAccountFlow1.setEmpId(empHAFAccountFlow.getEmpId());
              empHAFAccountFlow1.setEmpName(empHAFAccountFlow.getEmpName());
             Object[] empHAFAccount = (Object[]) temp_empHAFAccountFlow2.get(j);
           //���ڴ���������
           BigDecimal  temp_credit=(BigDecimal) empHAFAccount[0];
           //���ڽ跽������
           BigDecimal  temp_debit=(BigDecimal) empHAFAccount[1];
          
           //��ȡ��ȡʱ������������
           String tempdate=(String) empHAFAccount[2];
           //���ڴ�������
           List countCreditlist=empHAFAccountFlowDAO.countEmpHAFAccountCredit(tempdate, empIdaa102, orgIdaa101, tempdate);
           Integer countCredit=(Integer) countCreditlist.get(0);
           //���ڽ跽����
           List countDebitlist=empHAFAccountFlowDAO.countEmpHAFAccountDebit(tempdate, empIdaa102, orgIdaa101, tempdate);
           Integer countDebit=(Integer) countDebitlist.get(0);
           empHAFAccountFlow1.setCountCredit(countCredit+"");
           empHAFAccountFlow1.setCountDebit(countDebit+"");
           empHAFAccountFlow1.setTemp_credit(temp_credit);
           empHAFAccountFlow1.setTemp_debit(temp_debit);
           //��ȡʱ��������
           String settDate=tempdate;
           //�ڳ����
           List temp_empHAFAccountFlow=empHAFAccountFlowDAO.empAccountPrebalance(settDate, empIdaa102, orgIdaa101);
           if(!temp_empHAFAccountFlow.isEmpty()){
             if(temp_empHAFAccountFlow.get(0)!=null&&!temp_empHAFAccountFlow.equals("")){
             temp_prebalance=(BigDecimal)temp_empHAFAccountFlow.get(0);
             empHAFAccountFlow1.setPrebalance(temp_prebalance);
           }else{
             empHAFAccountFlow1.setPrebalance(new BigDecimal(0.00));
           }
           }
           //��ĩ���
           List temp_empHAFAccountFlow3=empHAFAccountFlowDAO.empAccountCurbalance(settDate, empIdaa102, orgIdaa101);
           if(!temp_empHAFAccountFlow3.isEmpty()){
             if(temp_empHAFAccountFlow3.get(0)!=null&&!temp_empHAFAccountFlow3.equals("")){
             BigDecimal temp_curbalance=(BigDecimal)temp_empHAFAccountFlow3.get(0);
             empHAFAccountFlow1.setCurbalance(temp_curbalance);
           }else{
             empHAFAccountFlow1.setCurbalance(new BigDecimal(0.00));
           }
           }
           empHAFAccountFlow1.setDisplayTme(settDate);
           returnList.add(empHAFAccountFlow1);
         }
         } 
         //��������,���ؼ��㷢����,��ӡ׼������
         List temp_count_list=empHAFAccountFlowDAO.empAccountcurCountBalance2(nowStartDate, empIdaa102, orgIdaa101, nowLastDate);
         EmpaccountinfoDTO empaccountinfoDTO=new EmpaccountinfoDTO();
         //���ںϼ���ʾ������
         for(int j=0;j<temp_count_list.size();j++){
           Object[] empHAFAccount = (Object[]) temp_count_list.get(j);
           //���ڴ���������
           BigDecimal  temp_credit=(BigDecimal) empHAFAccount[0];
           //���ڽ跽������
           BigDecimal  temp_debit=(BigDecimal) empHAFAccount[1];
           //������Ϣ
           BigDecimal  temp_interest=(BigDecimal) empHAFAccount[2];
           empaccountinfoDTO.setTemp_credit(empaccountinfoDTO.getTemp_credit().add(temp_credit));
           empaccountinfoDTO.setTemp_debit(empaccountinfoDTO.getTemp_debit().add(temp_debit));
           empaccountinfoDTO.setTemp_interest(empaccountinfoDTO.getTemp_interest().add(temp_interest));
         }
         pagination.getQueryCriterions().put("temp1_interest", empaccountinfoDTO.getTemp_interest().toString());
         pagination.getQueryCriterions().put("temp_credit",empaccountinfoDTO.getTemp_credit().toString());
         pagination.getQueryCriterions().put("temp_debit",empaccountinfoDTO.getTemp_debit().toString());
         //������Ϊ��ӡ׼������
         if(!temp_count_list.isEmpty()){
           for(int j=0;j<temp_count_list.size();j++){
              EmpHAFAccountFlow empHAFAccountFlow1=new EmpHAFAccountFlow();
              empHAFAccountFlow1.setEmp(empHAFAccountFlow.getEmp());
              empHAFAccountFlow1.setId(empHAFAccountFlow.getId());
              empHAFAccountFlow1.setOrg(empHAFAccountFlow.getOrg());
              empHAFAccountFlow1.setEmpId(empHAFAccountFlow.getEmpId());
              empHAFAccountFlow1.setEmpName(empHAFAccountFlow.getEmpName());
             Object[] empHAFAccount = (Object[]) temp_count_list.get(j);
           //���ڴ���������
           BigDecimal  temp_credit=(BigDecimal) empHAFAccount[0];
           //���ڽ跽������
           BigDecimal  temp_debit=(BigDecimal) empHAFAccount[1];
          
           //��ȡ��ȡʱ������������
           String tempdate=(String) empHAFAccount[3];
           //���ڴ�������
           List countCreditlist=empHAFAccountFlowDAO.countEmpHAFAccountCredit(tempdate, empIdaa102, orgIdaa101, tempdate);
           Integer countCredit=(Integer) countCreditlist.get(0);
           //���ڽ跽����
           List countDebitlist=empHAFAccountFlowDAO.countEmpHAFAccountDebit(tempdate, empIdaa102, orgIdaa101, tempdate);
           Integer countDebit=(Integer) countDebitlist.get(0);
           empHAFAccountFlow1.setCountCredit(countCredit+"");
           empHAFAccountFlow1.setCountDebit(countDebit+"");
           empHAFAccountFlow1.setTemp_credit(temp_credit);
           empHAFAccountFlow1.setTemp_debit(temp_debit);
           //��ȡʱ��������
           String settDate=tempdate;
           //�ڳ����
           List temp_empHAFAccountFlow=empHAFAccountFlowDAO.empAccountPrebalance(settDate, empIdaa102, orgIdaa101);
           if(!temp_empHAFAccountFlow.isEmpty()){
             if(temp_empHAFAccountFlow.get(0)!=null&&!temp_empHAFAccountFlow.equals("")){
             temp_prebalance=(BigDecimal)temp_empHAFAccountFlow.get(0);
             empHAFAccountFlow1.setPrebalance(temp_prebalance);
           }else{
             empHAFAccountFlow1.setPrebalance(new BigDecimal(0.00));
           }
           }
           //��ĩ���
           List temp_empHAFAccountFlow3=empHAFAccountFlowDAO.empAccountCurbalance(settDate, empIdaa102, orgIdaa101);
           if(!temp_empHAFAccountFlow3.isEmpty()){
             if(temp_empHAFAccountFlow3.get(0)!=null&&!temp_empHAFAccountFlow3.equals("")){
             BigDecimal temp_curbalance=(BigDecimal)temp_empHAFAccountFlow3.get(0);
             empHAFAccountFlow1.setCurbalance(temp_curbalance);
           }else{
             empHAFAccountFlow1.setCurbalance(new BigDecimal(0.00));
           }
           }
           empHAFAccountFlow1.setDisplayTme(settDate);
           printList.add(empHAFAccountFlow1);
         }
         } 
         pagination.getQueryCriterions().put("printList", printList);
         pagination.setNrOfElements(temp_count_list.size());    
   }catch(Exception e){
     e.printStackTrace();
   } 
    return returnList;
  }
}