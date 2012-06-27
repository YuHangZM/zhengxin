package org.xpup.hafmis.syscollection.collloanbackcheck.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.collloanbackcheck.bsinterface.ICollLoanbackcheckBS;
import org.xpup.hafmis.syscollection.collloanbackcheck.dto.CollLoanbackcheckDTO;
import org.xpup.hafmis.syscollection.common.dao.BizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.PickBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.PickHeadDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.BizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.PickBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.PickHead;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;

public class CollLoanbackcheckBS implements ICollLoanbackcheckBS {

  private PickHeadDAO pickHeadDAO = null ;
  
  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO = null ;

  private PickBizActivityLogDAO pickBizActivityLogDAO = null;

  private HafOperateLogDAO hafOperateLogDAO = null;

  private BizActivityLogDAO bizActivityLogDAO = null;
  
  public PickHeadDAO getPickHeadDAO() {
    return pickHeadDAO;
  }
  public void setPickHeadDAO(PickHeadDAO pickHeadDAO) {
    this.pickHeadDAO = pickHeadDAO;
  }
  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }
  public void setPickBizActivityLogDAO(PickBizActivityLogDAO pickBizActivityLogDAO) {
    this.pickBizActivityLogDAO = pickBizActivityLogDAO;
  }
  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }
  public void setBizActivityLogDAO(BizActivityLogDAO bizActivityLogDAO) {
    this.bizActivityLogDAO = bizActivityLogDAO;
  }
  /**
   * ��ѯҵ�񸴺��б�
   */
  public List collLoanbackcheckForwardFind(Pagination pagination,SecurityInfo securityInfo) throws BusinessException, Exception {
    List returnList=new ArrayList();
    try{
      Integer org_id=null;
      Integer emp_id=null;
      
      String flag=(String) pagination.getQueryCriterions().get("flag");
      String officeCode = (String) pagination.getQueryCriterions().get("officeCode");
      String collectionBankId = (String) pagination.getQueryCriterions().get("collectionBankId");
      String bizStatus = (String) pagination.getQueryCriterions().get("bizStatus");
      String batch_num = (String) pagination.getQueryCriterions().get("batch_num");
      String startDate = (String) pagination.getQueryCriterions().get("startDate");
      String endDate = (String) pagination.getQueryCriterions().get("endDate");
      String orgid = (String) pagination.getQueryCriterions().get("orgid");
      if(orgid!=null && orgid !=""){
        org_id= new Integer(orgid.trim());
      }
      String orgname = (String) pagination.getQueryCriterions().get("orgname");
      String empid = (String) pagination.getQueryCriterions().get("empid");
      if(empid!=null && empid !=""){
        emp_id= new Integer(empid.trim());
      }
      String empname = (String) pagination.getQueryCriterions().get("empname");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      returnList=pickHeadDAO.collLoanbackcheckFindList(flag,officeCode,collectionBankId,bizStatus,batch_num,startDate,endDate,
          orderBy,order,start,pageSize,page,org_id,orgname,emp_id,empname);

    }catch(Exception e){
      e.printStackTrace();
    }
    return returnList;
  }
  /**
   * ��ѯҵ�񸴺�ȫ�б�
   */
  public List collLoanbackcheckForwardFindAll(Pagination pagination,SecurityInfo securityInfo) throws BusinessException, Exception {
    List returnList=new ArrayList();
    try{
      Integer org_id=null;
      Integer emp_id=null;
      
      String flag=(String) pagination.getQueryCriterions().get("flag");
      String officeCode = (String) pagination.getQueryCriterions().get("officeCode");
      String collectionBankId = (String) pagination.getQueryCriterions().get("collectionBankId");
      String bizStatus = (String) pagination.getQueryCriterions().get("bizStatus");
      String batch_num = (String) pagination.getQueryCriterions().get("batch_num");
      String startDate = (String) pagination.getQueryCriterions().get("startDate");
      String endDate = (String) pagination.getQueryCriterions().get("endDate");
      
      String orgid = (String) pagination.getQueryCriterions().get("orgid");
      if(orgid!=null && orgid !=""){
        org_id= new Integer(orgid.trim());
      }
      String orgname = (String) pagination.getQueryCriterions().get("orgname");
      String empid = (String) pagination.getQueryCriterions().get("empid");
      if(empid!=null && empid !=""){
        emp_id= new Integer(empid.trim());
      }
      String empname = (String) pagination.getQueryCriterions().get("empname");
      
      returnList=pickHeadDAO.collLoanbackcheckFindAllList(flag,officeCode,collectionBankId,bizStatus,batch_num,startDate,endDate,
          org_id,orgname,emp_id,empname);
      int count = returnList.size();
      pagination.setNrOfElements(count);
      
    }catch(Exception e){
      e.printStackTrace();
    }
    return returnList;
  }
  /**
   * ����
   */
  public void bizcheck(String id, SecurityInfo securityInfo) throws BusinessException, Exception {
    try{
      String seqid="";
      CollLoanbackcheckDTO collLoanbackcheckDTO=null;
      //�������κŵõ�AA306��ID
      List seqidList=pickHeadDAO.getPickHeadIDByButenum(id);
      
      for(int i=0;i<seqidList.size();i++){
        collLoanbackcheckDTO=new CollLoanbackcheckDTO();
        collLoanbackcheckDTO=(CollLoanbackcheckDTO)seqidList.get(i);
        seqid=collLoanbackcheckDTO.getSeqid();
        //�жϸ����������Ա�Ƿ���ͬһ��
         OrgHAFAccountFlow orgHAFAccountFlow =orgHAFAccountFlowDAO.queryByBizId_WL(seqid);
//        String userName = orgHAFAccountFlow.getReserveaA().toLowerCase();
//        if(securityInfo.getUserName().equals(userName)){
//          throw new BusinessException("�Ƶ����븴���˲���Ϊͬһ��!");
//        }
        //����ID�޸�AA306��AA101��ҵ��״̬
        
        PickHead pickhead=pickHeadDAO.queryById(new Integer(seqid));
        pickhead.setPickSatatus(new BigDecimal(4));
        orgHAFAccountFlow.setBizStatus(new BigDecimal(4));
//        //����AA319��BA003
//        // ����AA319��
//        PickBizActivityLog pickBizActivityLog = new PickBizActivityLog();
//        pickBizActivityLog.setBizid(new Integer(seqid));
//        pickBizActivityLog.setAction(new Integer(4));
//        pickBizActivityLog.setOpTime(new Date());
//        pickBizActivityLog.setOperator(securityInfo.getUserName());
//        pickBizActivityLogDAO.insert(pickBizActivityLog);
//          // ����BA003��
//          HafOperateLog hafOperateLog = new HafOperateLog();
//          hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
//          hafOperateLog.setOpModel(""+BusiLogConst.OP_MODE_DRAWING_COLLLOANBACK);
//          hafOperateLog.setOpButton(""+BusiLogConst.BIZLOG_ACTION_CHECKS);
//          hafOperateLog.setOpBizId(new Integer(seqid));
//          hafOperateLog.setOperator(securityInfo.getUserName());
//          hafOperateLog.setOpIp(securityInfo.getUserIp());
//          hafOperateLog.setOpTime(new Date());
//          hafOperateLog.setOrgId(new Integer(pickhead.getOrg().getId().toString()));
//          hafOperateLogDAO.insert(hafOperateLog);
        
      }
      
    }catch(BusinessException be){
       throw be;
    }catch(Exception e){
      e.printStackTrace();
    }
    
  }
  /**
   * ����
   */
  public String[] clearaccount(String id, SecurityInfo securityInfo) throws BusinessException, Exception {
    String[] rowArray=null;
    try{
      String seqid="";
      CollLoanbackcheckDTO collLoanbackcheckDTO=null;
      //�������κŵõ�AA306��ID
      List seqidList=pickHeadDAO.getPickHeadIDByButenum(id);
      List resList = new ArrayList();
      for(int i=0;i<seqidList.size();i++){
        collLoanbackcheckDTO=new CollLoanbackcheckDTO();
        collLoanbackcheckDTO=(CollLoanbackcheckDTO)seqidList.get(i);
        seqid=collLoanbackcheckDTO.getSeqid();
        OrgHAFAccountFlow orgHAFAccountFlow =orgHAFAccountFlowDAO.queryByBizId_WL(seqid);
        resList.add(orgHAFAccountFlow.getId().toString());
      }
      rowArray=new String[resList.size()];
      for(int i=0;i<resList.size();i++){
        rowArray[i]=resList.get(i).toString();
      }
      return rowArray;
    }catch(BusinessException be){
      throw be;
    }catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }
  
  /**
   * ��������
   */
  public void delbizcheck(String id, SecurityInfo securityInfo) throws BusinessException, Exception {
    try{
      String seqid="";
      CollLoanbackcheckDTO collLoanbackcheckDTO=null;
      Integer count = new Integer(0);
      count = pickHeadDAO.query_havingstatus(id);
      
    if(count.intValue()>0){
    throw new BusinessException("�����κ��µĹ����𻹴�ҵ���Ѿ�������״̬��¼,���ܳ�������!");
    }
      //�������κŵõ�AA306��ID
      List seqidList=pickHeadDAO.getPickHeadIDByButenum(id);
      for(int i=0;i<seqidList.size();i++){
        collLoanbackcheckDTO=new CollLoanbackcheckDTO();
        collLoanbackcheckDTO=(CollLoanbackcheckDTO)seqidList.get(i);
        seqid=collLoanbackcheckDTO.getSeqid();
        //�жϸ����������Ա�Ƿ���ͬһ��
        OrgHAFAccountFlow orgHAFAccountFlow =orgHAFAccountFlowDAO.queryByBizId_WL(seqid);
        String userName = orgHAFAccountFlow.getReserveaA().toLowerCase();
//        if(securityInfo.getUserName().equals(userName)){
//          throw new BusinessException("�Ƶ����븴���˲���Ϊͬһ��!");
//        }
        //����ID�޸�AA306��AA101��ҵ��״̬
        PickHead pickhead=pickHeadDAO.queryById(new Integer(seqid));
        pickhead.setPickSatatus(new BigDecimal(3));
        orgHAFAccountFlow.setBizStatus(new BigDecimal(3));
        //����AA319��BA003
        // ɾ��AA319��
        BizActivityLog bizActivityLog = new BizActivityLog();
        bizActivityLog = bizActivityLogDAO.queryBizActivityLogWuht(seqid,
            "4", "D");
        if(bizActivityLog!=null){
        bizActivityLogDAO.deleteWuht(bizActivityLog);
        }
          // ����BA003��
          HafOperateLog hafOperateLog = new HafOperateLog();
          hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
          hafOperateLog.setOpModel(""+BusiLogConst.OP_MODE_DRAWING_COLLLOANBACK);
          hafOperateLog.setOpButton(""+BusiLogConst.BIZLOG_ACTION_REVOCATION);
          hafOperateLog.setOpBizId(new Integer(seqid));
          hafOperateLog.setOperator(securityInfo.getUserName());
          hafOperateLog.setOpIp(securityInfo.getUserIp());
          hafOperateLog.setOpTime(new Date());
          hafOperateLog.setOrgId(new Integer(pickhead.getOrg().getId().toString()));
          hafOperateLogDAO.insert(hafOperateLog);
      }
    }catch(BusinessException be){
      throw be;
    }catch(Exception e){
      e.printStackTrace();
    }
    
  }
  /**
   * ��������
   */
  public void bizcheckAll(List list, SecurityInfo securityInfo) throws BusinessException, Exception {
    try{
      CollLoanbackcheckDTO collLoanbackcheckDTO=null;
      CollLoanbackcheckDTO collLoanbackcheckDTO_in=null;
//      for(int i=0;i<list.size();i++){
        collLoanbackcheckDTO=new CollLoanbackcheckDTO();
        collLoanbackcheckDTO=(CollLoanbackcheckDTO)list.get(0);
        String seqid="";
        //�������κŵõ�AA306��ID
        List seqidList=pickHeadDAO.getPickHeadIDByButenum(collLoanbackcheckDTO.getBatch_num());
        for(int j=0;j<seqidList.size();j++){
          collLoanbackcheckDTO_in=new CollLoanbackcheckDTO();
          collLoanbackcheckDTO_in=(CollLoanbackcheckDTO)seqidList.get(j);
          seqid=collLoanbackcheckDTO_in.getSeqid();
          //�жϸ����������Ա�Ƿ���ͬһ��
          OrgHAFAccountFlow orgHAFAccountFlow =orgHAFAccountFlowDAO.queryByBizId_WL(seqid);
//          String userName = orgHAFAccountFlow.getReserveaA().toLowerCase();
//          if(securityInfo.getUserName().equals(userName)){
//            throw new BusinessException("��"+(j+1)+"��ҵ����Ƶ����븴���˲���Ϊͬһ��!");
//          }
          //����ID�޸�AA306��AA101��ҵ��״̬
          PickHead pickhead=pickHeadDAO.queryById(new Integer(seqid));
          pickhead.setPickSatatus(new BigDecimal(4));
          orgHAFAccountFlow.setBizStatus(new BigDecimal(4));
          //����AA319��BA003
          // ����AA319��
//          PickBizActivityLog pickBizActivityLog = new PickBizActivityLog();
//          pickBizActivityLog.setBizid(new Integer(seqid));
//          pickBizActivityLog.setAction(new Integer(4));
//          pickBizActivityLog.setOpTime(new Date());
//          pickBizActivityLog.setOperator(securityInfo.getUserName());
//          pickBizActivityLogDAO.insert(pickBizActivityLog);
//            // ����BA003��
//            HafOperateLog hafOperateLog = new HafOperateLog();
//            hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
//            hafOperateLog.setOpModel(""+BusiLogConst.OP_MODE_DRAWING_COLLLOANBACK);
//            hafOperateLog.setOpButton(""+BusiLogConst.BIZLOG_ACTION_CHECKS);
//            hafOperateLog.setOpBizId(new Integer(seqid));
//            hafOperateLog.setOperator(securityInfo.getUserName());
//            hafOperateLog.setOpIp(securityInfo.getUserIp());
//            hafOperateLog.setOpTime(new Date());
//            hafOperateLog.setOrgId(new Integer(pickhead.getOrg().getId().toString()));
//            hafOperateLogDAO.insert(hafOperateLog);
        }
        
//      }
    }catch(BusinessException be){
       throw be;
    }catch(Exception e){
      e.printStackTrace();
    }
    
  }
  /**
   * ������������
   */
  public void delbizcheckAll(List list, SecurityInfo securityInfo) throws BusinessException, Exception {
    try{
      CollLoanbackcheckDTO collLoanbackcheckDTO=null;
      CollLoanbackcheckDTO collLoanbackcheckDTO_in=null;
//      for(int i=0;i<list.size();i++){
        collLoanbackcheckDTO=new CollLoanbackcheckDTO();
        collLoanbackcheckDTO=(CollLoanbackcheckDTO)list.get(0);
        String seqid="";
        //�������κŵõ�AA306��ID
        List seqidList=pickHeadDAO.getPickHeadIDByButenum(collLoanbackcheckDTO.getBatch_num());
        for(int j=0;j<seqidList.size();j++){
          collLoanbackcheckDTO_in=new CollLoanbackcheckDTO();
          collLoanbackcheckDTO_in=(CollLoanbackcheckDTO)seqidList.get(j);
          seqid=collLoanbackcheckDTO_in.getSeqid();
          //�жϸ����������Ա�Ƿ���ͬһ��
          OrgHAFAccountFlow orgHAFAccountFlow =orgHAFAccountFlowDAO.queryByBizId_WL(seqid);
          String userName = orgHAFAccountFlow.getReserveaA().toLowerCase();
//          if(securityInfo.getUserName().equals(userName)){
//            throw new BusinessException("��"+(j+1)+"��ҵ����Ƶ����븴���˲���Ϊͬһ��!");
//          }
          //����ID�޸�AA306��AA101��ҵ��״̬
          PickHead pickhead=pickHeadDAO.queryById(new Integer(seqid));
          pickhead.setPickSatatus(new BigDecimal(3));
          orgHAFAccountFlow.setBizStatus(new BigDecimal(3));
          //����AA319��BA003
          // ɾ��AA319��
//          BizActivityLog bizActivityLog = new BizActivityLog();
//          bizActivityLog = bizActivityLogDAO.queryBizActivityLogWuht(seqid,
//              "4", "D");
//          bizActivityLogDAO.deleteWuht(bizActivityLog);
//            // ����BA003��
//            HafOperateLog hafOperateLog = new HafOperateLog();
//            hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
//            hafOperateLog.setOpModel(""+BusiLogConst.OP_MODE_DRAWING_COLLLOANBACK);
//            hafOperateLog.setOpButton(""+BusiLogConst.BIZLOG_ACTION_REVOCATION);
//            hafOperateLog.setOpBizId(new Integer(seqid));
//            hafOperateLog.setOperator(securityInfo.getUserName());
//            hafOperateLog.setOpIp(securityInfo.getUserIp());
//            hafOperateLog.setOpTime(new Date());
//            hafOperateLog.setOrgId(new Integer(pickhead.getOrg().getId().toString()));
//            hafOperateLogDAO.insert(hafOperateLog);
        }
//      }
    }catch(BusinessException be){
      throw be;
    }catch(Exception e){
      e.printStackTrace();
    }
    
  }

  

}
