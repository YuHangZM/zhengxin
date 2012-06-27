package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.ChgOrgRateDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.bsinterface.IChgorgrateBS;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.form.ChgorgrateAF;
import org.xpup.hafmis.syscommon.dao.OrgInfoDAO;
import org.xpup.hafmis.syscommon.domain.entity.OrgInfo;

/**
 * @author �����
 */
public class ChgorgrateBS implements IChgorgrateBS {

  private ChgOrgRateDAO chgOrgRateDAO = null;

  private OrgInfoDAO orgInfoDAO = null;

  private OrgDAO orgDAO = null;

  private OrganizationUnitDAO organizationUnitDAO = null;

  private CollBankDAO collBankDAO = null;

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public void setOrgInfoDAO(OrgInfoDAO orgInfoDAO) {
    this.orgInfoDAO = orgInfoDAO;
  }

  public void setChgOrgRateDAO(ChgOrgRateDAO chgOrgRateDAO) {
    this.chgOrgRateDAO = chgOrgRateDAO;
  }

  public ChgorgrateAF findChgorgrateByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    ChgorgrateAF chgorgrateAF = new ChgorgrateAF();
    List collBankList = securityInfo.getCollBankList();
    List officeList = securityInfo.getOfficeList();
    chgorgrateAF.setCollBankList(collBankList);
    chgorgrateAF.setOfficeList(officeList);

    List list = new ArrayList();
    List lista = new ArrayList();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    String orderBy = (String) pagination.getOrderBy();
    String order = pagination.getOrderother();
    String officeCode = null;// ���´�
    String collectionBank = null;// �鼯����
    String orgId = null;// ��λ���
    String orgName = null;// ��λ����
    String chgMonthStart = null;// �ɴ����¿�ʼ
    String chgMonthEnd = null;// �ɴ����½���
    String chgDateStart = null;// �ɴ����ڿ�ʼ
    String chgDateEnd = null;// �ɴ����ڽ���
    Integer chgStatus = null;// ҵ��״̬
    BigDecimal sumPreSumPay = new BigDecimal(0.00);// sum����ǰ
    BigDecimal sumSumPay = new BigDecimal(0.00);// sum������

    // ��ڵ�,Ĭ�ϲ���ѯ
    if (pagination.getQueryCriterions().isEmpty()) {
      list = new ArrayList();
    } else {
      officeCode = (String) pagination.getQueryCriterions().get("officeCode");// ���´�
      collectionBank = (String) pagination.getQueryCriterions().get(
          "collectionBank");// �鼯����
      orgId = (String) pagination.getQueryCriterions().get("orgId");// ��λ���
      orgName = (String) pagination.getQueryCriterions().get("orgName");// ��λ����
      chgMonthStart = (String) pagination.getQueryCriterions().get(
          "chgMonthStart");// �ɴ����¿�ʼ
      chgMonthEnd = (String) pagination.getQueryCriterions().get("chgMonthEnd");// �ɴ����½���
      chgDateStart = (String) pagination.getQueryCriterions().get(
          "chgDateStart");// �ɴ����ڿ�ʼ
      chgDateEnd = (String) pagination.getQueryCriterions().get("chgDateEnd");// �ɴ����ڽ���
      chgStatus = (Integer) pagination.getQueryCriterions().get("chgStatus");// ҵ��״̬

      list = chgOrgRateDAO.queryChgorgrateListByCriterions(officeCode,
          collectionBank, orgId, orgName, chgMonthStart, chgMonthEnd,
          chgDateStart, chgDateEnd, chgStatus, start, pageSize, orderBy, order,
          securityInfo);
    }

    if (list != null) {
      double drate = 0.0;
      for (int i = 0; i < list.size(); i++) {
        ChgOrgRate chgOrgRate = (ChgOrgRate) list.get(i);

        // ���ݰ��´�IDת��������,���ݹ鼯����IDת������
        String officecodeId = chgOrgRate.getOrg().getOrgInfo().getOfficecode();
        String collectionbankId = chgOrgRate.getOrg().getOrgInfo()
            .getCollectionBankId();

        OrganizationUnit organizationUnit = organizationUnitDAO
            .queryOrganizationUnitListByCriterions(officecodeId);
        CollBank collBank = collBankDAO
            .getCollBankByCollBankid(collectionbankId);

        String unitName = organizationUnit.getName();
        String bankName = collBank.getCollBankName();

        chgOrgRate.setReserveaB(unitName);
        chgOrgRate.setReserveaC(bankName);
        // ҵ��״̬ת��
        chgOrgRate.setTemp_chgStatus(BusiTools.getBusiValue(Integer.parseInt(""
            + chgOrgRate.getChgStatus()), BusiConst.CHGTYPESTATUS));
        String orgId_ = chgOrgRate.getOrg().getId().toString();
        BusiTools busiTools = null;
        String convertOrgId = busiTools.convertSixNumber(orgId_);
        chgOrgRate.getOrg().setId(new Integer(convertOrgId));
        BigDecimal oldOrgPay = chgOrgRate.getOldOrgPay();
        BigDecimal oldEmpPay = chgOrgRate.getOldEmpPay();
        BigDecimal orgemppay = new BigDecimal(0);
        if (oldOrgPay != null || !oldOrgPay.equals("") && oldEmpPay != null
            || !oldEmpPay.equals("")) {
          orgemppay = oldOrgPay.add(oldEmpPay);
        }
        if (!orgemppay.toString().equals("0")) {
          String corId = chgOrgRate.getId().toString();
          BigDecimal rate = chgOrgRateDAO.queryRate(new Integer(corId));
          drate = rate.doubleValue() * 100;
          // chgOrgRate.setRate(rate);
          chgOrgRate.setRate_(new Double(drate).toString().substring(0,
              (new Double(drate).toString().indexOf(".")) + 2)
              + "%");
        } else {
          chgOrgRate.setRate_("0.0%");
        }
        if(chgOrgRate.getChgMonth().substring(4, 6).equals("01")){
          chgOrgRate.setMonth(String.valueOf(Integer.parseInt(chgOrgRate.getChgMonth().substring(0, 4))-1)+"12");
          
        }else
        if(chgOrgRate.getChgMonth().substring(4, 6).equals("12")||chgOrgRate.getChgMonth().substring(4, 6).equals("11")){
          chgOrgRate.setMonth(String.valueOf(Integer.parseInt(chgOrgRate.getChgMonth().substring(0, 4))-1)+String.valueOf(Integer.parseInt(chgOrgRate.getChgMonth().substring(4, 6))-1));
          
        }else{
          chgOrgRate.setMonth(String.valueOf(Integer.parseInt(chgOrgRate.getChgMonth().substring(0, 4)))+"0"+String.valueOf(Integer.parseInt(chgOrgRate.getChgMonth().substring(4, 6))-1));
        }
        chgOrgRate.setPay(chgOrgRate.getSumPay().subtract(chgOrgRate.getPreSumPay()));
        
        
      }
    }

    if (list.size() != 0) {
      lista = chgOrgRateDAO.queryChgorgrateListSizeByCriterions(officeCode,
          collectionBank, orgId, orgName, chgMonthStart, chgMonthEnd,
          chgDateStart, chgDateEnd, chgStatus, start, pageSize, orderBy, order,
          securityInfo);
      List listb = chgOrgRateDAO.queryChgorgrateOrgSizeByCriterions(officeCode,
          collectionBank, orgId, orgName, chgMonthStart, chgMonthEnd,
          chgDateStart, chgDateEnd, chgStatus, start, pageSize, orderBy, order,
          securityInfo);
      List listc = chgOrgRateDAO.queryCountsByCriterions(officeCode,
          collectionBank, orgId, orgName, chgMonthStart, chgMonthEnd,
          chgDateStart, chgDateEnd, chgStatus, start, pageSize, orderBy, order,
          securityInfo);
      int count = listb.size();
      int counts = listc.size();
      int paginationCount = lista.size();

      for (int j = 0; j < lista.size(); j++) {
        double d_rate = 0.0;
        ChgOrgRate chgOrgRate = (ChgOrgRate) lista.get(j);
        // ���ݰ��´�IDת��������,���ݹ鼯����IDת������
        String officecodeId = chgOrgRate.getOrg().getOrgInfo().getOfficecode();
        String collectionbankId = chgOrgRate.getOrg().getOrgInfo()
            .getCollectionBankId();

        OrganizationUnit organizationUnit = organizationUnitDAO
            .queryOrganizationUnitListByCriterions(officecodeId);
        CollBank collBank = collBankDAO
            .getCollBankByCollBankid(collectionbankId);

        String unitName = organizationUnit.getName();
        String bankName = collBank.getCollBankName();

        // ҵ��״̬ת��
        chgOrgRate.setTemp_chgStatus(BusiTools.getBusiValue(Integer.parseInt(""
            + chgOrgRate.getChgStatus()), BusiConst.CHGTYPESTATUS));
        // ����
        BigDecimal oldOrgPay = chgOrgRate.getOldOrgPay();
        BigDecimal oldEmpPay = chgOrgRate.getOldEmpPay();
        BigDecimal orgemppay = new BigDecimal(0);
        if (oldOrgPay != null || !oldOrgPay.equals("") && oldEmpPay != null
            || !oldEmpPay.equals("")) {
          orgemppay = oldOrgPay.add(oldEmpPay);
        }
        if (!orgemppay.toString().equals("0")) {
          String corId = chgOrgRate.getId().toString();
          BigDecimal rate = chgOrgRateDAO.queryRate(new Integer(corId));
          d_rate = rate.doubleValue() * 100;
          // chgOrgRate.setRate(rate);
          chgOrgRate.setRate_(new Double(d_rate).toString().substring(0,
              (new Double(d_rate).toString().indexOf(".")) + 2)
              + "%");
        } else {
          chgOrgRate.setRate_("0.0%");
        }

        chgOrgRate.setReserveaB(unitName);
        chgOrgRate.setReserveaC(bankName);
        // sum���
        sumPreSumPay = sumPreSumPay.add(chgOrgRate.getOldEmpPay().add(
            chgOrgRate.getOldOrgPay()));
        sumSumPay = sumSumPay.add(chgOrgRate.getEmpPay().add(
            chgOrgRate.getOrgPay()));
        if(chgOrgRate.getChgMonth().substring(4, 6).equals("01")){
          chgOrgRate.setMonth(String.valueOf(Integer.parseInt(chgOrgRate.getChgMonth().substring(0, 4))-1)+"12");
          
        }else
        if(chgOrgRate.getChgMonth().substring(4, 6).equals("12")||chgOrgRate.getChgMonth().substring(4, 6).equals("11")){
          chgOrgRate.setMonth(String.valueOf(Integer.parseInt(chgOrgRate.getChgMonth().substring(0, 4))-1)+String.valueOf(Integer.parseInt(chgOrgRate.getChgMonth().substring(4, 6))-1));
          
        }else{
          chgOrgRate.setMonth(String.valueOf(Integer.parseInt(chgOrgRate.getChgMonth().substring(0, 4)))+"0"+String.valueOf(Integer.parseInt(chgOrgRate.getChgMonth().substring(4, 6))-1));
        }
        chgOrgRate.setPay(chgOrgRate.getSumPay().subtract(chgOrgRate.getPreSumPay()));
      }
      pagination.setNrOfElements(paginationCount);
      chgorgrateAF.setSumPre(sumPreSumPay);
      chgorgrateAF.setSumSith(sumSumPay);
      chgorgrateAF.setOrgCount(new Integer(count));
      chgorgrateAF.setCounts(new Integer(counts));
    } else {
      int paginationCount = 0;
      pagination.setNrOfElements(paginationCount);
    }
    chgorgrateAF.setList(list);
    chgorgrateAF.setAlllist(lista);
    return chgorgrateAF;
  }
}
