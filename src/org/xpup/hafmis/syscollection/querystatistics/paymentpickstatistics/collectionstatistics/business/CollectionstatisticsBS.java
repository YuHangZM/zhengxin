package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.bsinterface.ICollectionstatisticsBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.dto.CollectionstatisticsExportDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.form.CollectionstatisticsAF;
import org.xpup.hafmis.syscommon.dao.OrgInfoDAO;
import org.xpup.hafmis.syscommon.domain.entity.OrgInfo;

public class CollectionstatisticsBS implements ICollectionstatisticsBS {

  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO = null;
  
  private OrgDAO orgDAO = null;

  private OrgInfoDAO orgInfoDAO = null;
  
  private OrganizationUnitDAO organizationUnitDAO = null;
  
  private CollBankDAO collBankDAO = null;

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public void setOrgInfoDAO(OrgInfoDAO orgInfoDAO) {
    this.orgInfoDAO = orgInfoDAO;
  }

  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }

  public CollectionstatisticsAF findChgorgrateByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    CollectionstatisticsAF csAF = new CollectionstatisticsAF();
    List collBankList = securityInfo.getCollBankList();
    List officeList = securityInfo.getOfficeList();
    csAF.setCollBankList(collBankList);
    csAF.setOfficeList(officeList);
    List list = new ArrayList();
    List countlist = new ArrayList();
    CollectionstatisticsExportDTO collectionstatisticsExportDTO = new CollectionstatisticsExportDTO();
    if (!pagination.getQueryCriterions().isEmpty()) {
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      String orderBy = (String) pagination.getOrderBy();
      String order = pagination.getOrderother();
      String officeCode = (String) pagination.getQueryCriterions().get(
          "officeCode");// ���´�
      String collectionBank = (String) pagination.getQueryCriterions().get(
          "collectionBank");// �鼯����
      String orgId = (String) pagination.getQueryCriterions().get("orgId");// ��λ���
      String orgName = (String) pagination.getQueryCriterions().get("orgName");// ��λ����
      String orgCharacter = (String) pagination.getQueryCriterions().get(
          "orgCharacter");// ��λ����
      String deptInCharge = (String) pagination.getQueryCriterions().get(
          "deptInCharge");// ���ܲ���
      String startDate = (String) pagination.getQueryCriterions().get(
          "startDate");// �ɴ����ڿ�ʼ
      String endDate = (String) pagination.getQueryCriterions().get("endDate");// �ɴ����ڽ���
      String region = (String) pagination.getQueryCriterions().get("region");// ���ڵ���
   
      
      list = orgHAFAccountFlowDAO.queryByCollectionstatisticsCriterions(
          officeCode, collectionBank, orgId, orgName, orgCharacter,
          deptInCharge, startDate, endDate, region, start, pageSize, orderBy,
          order, securityInfo);
   
      collectionstatisticsExportDTO = orgHAFAccountFlowDAO
          .queryCountByCollectionstatisticsCriterions(officeCode,
              collectionBank, orgId, orgName, orgCharacter, deptInCharge,
              startDate, endDate, region, start, pageSize, orderBy, order,
              securityInfo);
      
      countlist = orgHAFAccountFlowDAO.queryByCollectionstatisticsCriterions_(officeCode, collectionBank, orgId, orgName, orgCharacter, deptInCharge, startDate, endDate, region, start, pageSize, orderBy, order, securityInfo);
      
//      OrgHAFAccountFlow orgHAFAccountFlow = null;
//      BigDecimal lastMonthCollection = new BigDecimal(0.00);// ���¹鼯
//      BigDecimal monthPay = new BigDecimal(0.00);// �������
//      BigDecimal orgAddPay = new BigDecimal(0.00);// ��λ����
//      BigDecimal orgoverPay = new BigDecimal(0.00);// ��λ����
//      BigDecimal peronaddPay = new BigDecimal(0.00);// ���˲���
//      BigDecimal chgPay = new BigDecimal(0.00);// ���ɴ�
//      BigDecimal thisMonthCollection = new BigDecimal(0.00);// ���¹鼯
//      double rate = 0.00;// ����
//      BigDecimal sumLastMonthCollection = new BigDecimal(0.00);// ���¹鼯
//      BigDecimal sumMonthPay = new BigDecimal(0.00);// �������
//      BigDecimal sumOrgAddPay = new BigDecimal(0.00);// ��λ����
//      BigDecimal sumOrgoverPay = new BigDecimal(0.00);// ��λ����
//      BigDecimal sumPeronaddPay = new BigDecimal(0.00);// ���˲���
//      BigDecimal sumChgPay = new BigDecimal(0.00);// ���ɴ�
//      BigDecimal sumThisMonthCollection = new BigDecimal(0.00);// ���¹鼯
//      double sumRate = 0.00;// ����
//      double thisMonth = 0.00;
//      double lastMonth_ = 0.00;
//      double sumThisMonth = 0.00;
//      double sumLastMonth = 0.00;
     
//      if (list.size() != 0) {
//      
//        for (int i = 0; i < list.size(); i++) {
//          orgHAFAccountFlow = (OrgHAFAccountFlow) list.get(i);
////          int settDate = Integer.parseInt(orgHAFAccountFlow.getSettDate()
////              .substring(0, 6));
////          int lastMonth = settDate - 1;
//          //���ݰ��´�IDת��������,���ݹ鼯����IDת������
//          String officecodeId = orgHAFAccountFlow.getOrg().getOrgInfo().getOfficecode();
//          String collectionbankId = orgHAFAccountFlow.getOrg().getOrgInfo().getCollectionBankId();
//           
//          OrganizationUnit organizationUnit=organizationUnitDAO.queryOrganizationUnitListByCriterions(officecodeId);
//          CollBank collBank =collBankDAO.getCollBankByCollBankid(collectionbankId);
//          
//          String unitName = organizationUnit.getName();
//          String bankName = collBank.getCollBankName();
//      
//     
//          orgHAFAccountFlow.setReserveaB(unitName);
//          orgHAFAccountFlow.setReserveaC(bankName);
//
//       
////          String lastsettDate = new Integer(lastMonth).toString();
////          String thissettDate = new Integer(settDate).toString();
//          // ����lastMonth��ѯAA101.sum biz_type=A,B,C,K,M �� cradit-debit
//          lastMonthCollection = orgHAFAccountFlowDAO.queryLastMonthCollection(startDate);//���¹鼯
//          
//          orgHAFAccountFlow.setLastMonthCollection(lastMonthCollection);// set���¹鼯
//          
//          thisMonthCollection = orgHAFAccountFlowDAO.queryLastMonthCollection(endDate);//���¹鼯
//          
//          orgHAFAccountFlow.setThisMonthCollection(thisMonthCollection);//set���¹鼯
//          
//          monthPay = orgHAFAccountFlowDAO.queryMonthPay(startDate,endDate);
//         
//          orgHAFAccountFlow.setMonthPay(monthPay);
//          orgAddPay = orgHAFAccountFlowDAO.queryOrgAddPay(startDate, endDate);
//         
//          orgHAFAccountFlow.setOrgAddPay(orgAddPay);
//          orgoverPay = orgHAFAccountFlowDAO.queryOrgOverPay(startDate, endDate);
//        
//          orgHAFAccountFlow.setOrgoverPay(orgoverPay);
//          peronaddPay = orgHAFAccountFlowDAO.queryPersonAddPay(startDate, endDate);
//         
//          orgHAFAccountFlow.setPeronaddPay(peronaddPay);
//          chgPay = orgHAFAccountFlowDAO.queryChgPay(startDate, endDate);
//         
//          orgHAFAccountFlow.setChgPay(chgPay);
//          
////          BigDecimal thisMonthCollection = new BigDecimal(0.00);// ���¹鼯
////          if (monthPay != null) {
////            thisMonthCollection = thisMonthCollection.add(monthPay);
////          }
////          if (orgAddPay != null) {
////            thisMonthCollection = thisMonthCollection.add(orgAddPay);
////          }
////          if (orgoverPay != null) {
////            thisMonthCollection = thisMonthCollection.add(orgoverPay);
////          }
////          if (peronaddPay != null) {
////            thisMonthCollection = thisMonthCollection.add(peronaddPay);
////          }
////          if (chgPay != null) {
////            thisMonthCollection = thisMonthCollection.add(chgPay);
////          }
////        
////          orgHAFAccountFlow.setThisMonthCollection(thisMonthCollection);//���¹鼯
//          
//          if (thisMonthCollection != null) {
//            thisMonth = thisMonthCollection.doubleValue();
//          }
//          if (lastMonthCollection != null) {
//            lastMonth_ = lastMonthCollection.doubleValue();
//          }
//         
//          rate = ((thisMonth - lastMonth_) / lastMonth_)*100;
//          if(new Double(rate).toString()!= null){
//            
//           orgHAFAccountFlow.setRate(new Double(rate).toString().substring(0,(new Double(rate).toString().indexOf("."))+2)+"%");//����  
//            
//          }else{
//          orgHAFAccountFlow.setRate("0.0%");//����    
//          }
//        }
//      }
//      if (countlist.size() != 0) {
//        for (int i = 0; i < countlist.size(); i++) {
//          orgHAFAccountFlow = (OrgHAFAccountFlow) countlist.get(i);
//          int settDate = Integer.parseInt(orgHAFAccountFlow.getSettDate()
//              .substring(0, 6));
//          int lastMonth = settDate - 1;
//          
//          //���ݰ��´�IDת��������,���ݹ鼯����IDת������
//          String officecodeId = orgHAFAccountFlow.getOrg().getOrgInfo().getOfficecode();
//          String collectionbankId = orgHAFAccountFlow.getOrg().getOrgInfo().getCollectionBankId();
//           
//          OrganizationUnit organizationUnit=organizationUnitDAO.queryOrganizationUnitListByCriterions(officecodeId);
//          CollBank collBank =collBankDAO.getCollBankByCollBankid(collectionbankId);
//          
//          String unitName = organizationUnit.getName();
//          String bankName = collBank.getCollBankName();
//      
//     
//          orgHAFAccountFlow.setReserveaB(unitName);
//          orgHAFAccountFlow.setReserveaC(bankName);
//   
//          
//          //��λ���ʣ����ܲ��ţ����ڵ��� ö��ת��
//          orgHAFAccountFlow.getOrg().setReserveaA(BusiTools.getBusiValue(Integer.parseInt(""
//              + orgHAFAccountFlow.getOrg().getOrgInfo().getCharacter()), BusiConst.NATUREOFUNITS));
//          orgHAFAccountFlow.getOrg().setReserveaB(BusiTools.getBusiValue(Integer.parseInt(""
//              + orgHAFAccountFlow.getOrg().getOrgInfo().getDeptInCharge()), BusiConst.AUTHORITIES));
//          orgHAFAccountFlow.getOrg().setReserveaC(BusiTools.getBusiValue(Integer.parseInt(""
//              + orgHAFAccountFlow.getOrg().getOrgInfo().getRegion()), BusiConst.INAREA));
//          
//          String lastsettDate = new Integer(lastMonth).toString();
//          String thissettDate = new Integer(settDate).toString();
//          
//          // ����lastMonth��ѯAA101.sum biz_type=A,B,C,K,M �� cradit-debit
//          lastMonthCollection = orgHAFAccountFlowDAO.queryLastMonthCollection(lastsettDate);//���¹鼯
//          orgHAFAccountFlow.setLastMonthCollection(lastMonthCollection);// ���¹鼯
//          if(lastMonthCollection != null){
//          sumLastMonthCollection = sumLastMonthCollection.add(lastMonthCollection);//sum���¹鼯
//          }
//          
//          thisMonthCollection = orgHAFAccountFlowDAO.queryLastMonthCollection(endDate);//���¹鼯
//          orgHAFAccountFlow.setThisMonthCollection(thisMonthCollection);//set���¹鼯
//          if(thisMonthCollection != null){
//            sumThisMonthCollection = sumThisMonthCollection.add(thisMonthCollection);//sum���¹鼯
//          }
//          
//          monthPay = orgHAFAccountFlowDAO.queryMonthPay(startDate, endDate);
//          orgHAFAccountFlow.setMonthPay(monthPay);
//          if(monthPay != null){
//          sumMonthPay = sumMonthPay.add(monthPay);//sum
//          }
//          orgAddPay = orgHAFAccountFlowDAO.queryOrgAddPay(startDate, endDate);
//          orgHAFAccountFlow.setOrgAddPay(orgAddPay);
//          if(orgAddPay != null){
//          sumOrgAddPay = sumOrgAddPay.add(orgAddPay);//sum
//          }
//          orgoverPay = orgHAFAccountFlowDAO.queryOrgOverPay(startDate, endDate);
//          orgHAFAccountFlow.setOrgoverPay(orgoverPay);
//          if(orgoverPay != null){
//          sumOrgoverPay = sumOrgoverPay.add(orgoverPay);//sum
//          }
//          peronaddPay = orgHAFAccountFlowDAO.queryPersonAddPay(startDate, endDate);
//          orgHAFAccountFlow.setPeronaddPay(peronaddPay);
//          if(peronaddPay != null){
//          sumPeronaddPay = sumPeronaddPay.add(peronaddPay);//sum
//          }
//          chgPay = orgHAFAccountFlowDAO.queryChgPay(startDate, endDate);
//          orgHAFAccountFlow.setChgPay(chgPay);
//          if(chgPay != null){
//          sumChgPay = sumChgPay.add(chgPay);//sum
//          }
//          
////          BigDecimal thisMonthCollection = new BigDecimal(0.00);// ���¹鼯
////          if (monthPay != null) {
////            thisMonthCollection = thisMonthCollection.add(monthPay);
////          }
////          if (orgAddPay != null) {
////            thisMonthCollection = thisMonthCollection.add(orgAddPay);
////          }
////          if (orgoverPay != null) {
////            thisMonthCollection = thisMonthCollection.add(orgoverPay);
////          }
////          if (peronaddPay != null) {
////            thisMonthCollection = thisMonthCollection.add(peronaddPay);
////          }
////          if (chgPay != null) {
////            thisMonthCollection = thisMonthCollection.add(chgPay);
////          }
////          sumThisMonthCollection = sumThisMonthCollection.add(thisMonthCollection);//sum���¹鼯
////          orgHAFAccountFlow.setThisMonthCollection(thisMonthCollection);
//          
//          /*------------------------------7-----------------------------------*/
//          if (thisMonthCollection != null) {
//            thisMonth = thisMonthCollection.doubleValue();
//          }
//          if (lastMonthCollection != null) {
//            lastMonth_ = lastMonthCollection.doubleValue();
//          }
//          if(thisMonthCollection != null && !thisMonthCollection.toString().equals("0")&& lastMonthCollection != null && !lastMonthCollection.toString().equals("0") ){
//          rate = ((thisMonth - lastMonth_) / lastMonth_)*100;
//          }else{
//            rate=1;
//          }
//          orgHAFAccountFlow.setRate(new Double(rate).toString().substring(0,(new Double(rate).toString().indexOf("."))+2)+"%");
//        }
//      }
//      if(sumLastMonthCollection != null && !sumLastMonthCollection.toString().equals("0")&&sumThisMonthCollection != null && !sumThisMonthCollection.toString().equals("0")){
//        sumLastMonth = sumLastMonthCollection.doubleValue();
//        sumThisMonth = sumThisMonthCollection.doubleValue();
//        sumRate = ((sumThisMonth-sumLastMonth)/sumLastMonth)*100;
//     }else{
//       sumRate = 1.0;
//     }
//      csAF.setLastMonthCollect(sumLastMonthCollection);
//      csAF.setThisMonthCollect(sumThisMonthCollection);
//      csAF.setMonthPay(sumMonthPay);
//      csAF.setOrgAddPay(sumOrgAddPay);
//      csAF.setOrgOverPay(sumOrgoverPay);
//      csAF.setPersonAddPay(sumPeronaddPay);
//      csAF.setChgPay(sumChgPay);
//      csAF.setRate(new Double(sumRate).toString().substring(0,(new Double(sumRate).toString().indexOf("."))+2)+"%");
//  
    }
    pagination.setNrOfElements(countlist.size());
    csAF.setList(list);
    csAF.setAlllist(countlist);
    csAF.setCollectionstatisticsExportDTO(collectionstatisticsExportDTO);
    return csAF;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }
}
