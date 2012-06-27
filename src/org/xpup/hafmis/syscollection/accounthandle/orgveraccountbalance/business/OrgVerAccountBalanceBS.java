/**
 * Copy Right Information   : Goldsoft 
 * Project                  : OrgVerAccountBalanceBS
 * @Version                 : 1.0
 * @author                  : wangy
 * ��������                   :2007-12-19
 **/
package org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.bsinterface.IOrgVerAccountBalanceBS;
import org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.dto.OrgVerAccountBalanceDTO;
import org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.form.OrgVerAccountBalanceAF;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowEndAccrualDAO;
import org.xpup.hafmis.syscollection.common.dao.SearchLackInfoDAO;
import org.xpup.hafmis.syscollection.common.dao.SettInterestHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.SettInterestResultDAO;
import org.xpup.hafmis.syscollection.common.daoDW.AutoInfoPickDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.SettInterestHeadDAODW;
import org.xpup.hafmis.syscollection.common.domain.entity.AutoInfoPick;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowEndAccrual;
import org.xpup.hafmis.syscollection.common.domain.entity.SearchLackInfo;

public class OrgVerAccountBalanceBS implements IOrgVerAccountBalanceBS {

  private OrgDAO orgDAO = null;

  private EmpDAO empDAO = null;

  private SettInterestHeadDAO settInterestHeadDAO = null;

  private SettInterestHeadDAODW settInterestHeadDAODW = null;

  private OrgHAFAccountFlowEndAccrualDAO orgHAFAccountFlowEndAccrualDAO = null;// AA101

  private EmpHAFAccountFlowDAO empHAFAccountFlowDAO = null;// AA102

  private AutoInfoPickDAODW autoInfoPickDAODW = null;// DA001_CENTER

  private SettInterestResultDAO settInterestResultDAO = null;

  private SearchLackInfoDAO searchLackInfoDAO = null;// AA305

  public void setSearchLackInfoDAO(SearchLackInfoDAO searchLackInfoDAO) {
    this.searchLackInfoDAO = searchLackInfoDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public void setSettInterestResultDAO(
      SettInterestResultDAO settInterestResultDAO) {
    this.settInterestResultDAO = settInterestResultDAO;
  }

  public void setAutoInfoPickDAODW(AutoInfoPickDAODW autoInfoPickDAODW) {
    this.autoInfoPickDAODW = autoInfoPickDAODW;
  }

  public void setEmpHAFAccountFlowDAO(EmpHAFAccountFlowDAO empHAFAccountFlowDAO) {
    this.empHAFAccountFlowDAO = empHAFAccountFlowDAO;
  }

  public void setOrgHAFAccountFlowEndAccrualDAO(
      OrgHAFAccountFlowEndAccrualDAO orgHAFAccountFlowEndAccrualDAO) {
    this.orgHAFAccountFlowEndAccrualDAO = orgHAFAccountFlowEndAccrualDAO;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setSettInterestHeadDAO(SettInterestHeadDAO settInterestHeadDAO) {
    this.settInterestHeadDAO = settInterestHeadDAO;
  }

  public void setSettInterestHeadDAODW(
      SettInterestHeadDAODW settInterestHeadDAODW) {
    this.settInterestHeadDAODW = settInterestHeadDAODW;
  }

  // ��ѯ��λ����(��λ״̬Ϊ2_������)
  public Org findOrgInfo(String orgid, SecurityInfo securityInfo)
      throws BusinessException {
    Org org = null;
    if (orgid != null) {
      org = orgDAO.queryByCriterions(orgid, "2", null, securityInfo);
    }
    return org;
  }

  /**
   * ��ѯ��λ���ת����б�
   * 
   * @author wangy 2007-12-19
   * @param accYear ��ת���
   * @param pagination
   * @param securityInfo
   * @param ��OrgVerAccountBalanceShowAC����
   */
  public OrgVerAccountBalanceAF queryOVAccountBalanceListByCriterions(
      String accYear, Pagination pagination, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    OrgVerAccountBalanceAF orgVerAccountBalanceAF = new OrgVerAccountBalanceAF();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String orgId = (String) pagination.getQueryCriterions().get("orgId");
    String empId = (String) pagination.getQueryCriterions().get("empId");
    String empName = (String) pagination.getQueryCriterions().get("empName");
    // ��ѯ��λʱ����ѯ״̬��OPENSTATUS��Ϊ2���������ģ��鲻����ʾ��û�д˵�λ��Ϣ�� wangy 2008-03-01
    Org org = null;
    org = findOrgInfo(orgId, securityInfo);
    if(org == null && orgId !=null){
      throw new BusinessException("û�д˵�λ��Ϣ��");
    }
    // ͨ����λID,���Ϣ����б� AA316
    if (orgId != null && !orgId.equals("")) {
      List accYearList = settInterestHeadDAO
          .queryOVAccountBalanceAccYearList(orgId);
      if (!accYearList.isEmpty()) {
        orgVerAccountBalanceAF.setAccYearList(accYearList);
      }
    }
    List ovAccountBalanceList = settInterestHeadDAO
        .queryOVAccountBalanceListByCriterions(orgId, accYear, empId, empName,
            start, orderBy, order, pageSize, page);
    List ovAccountBalanceCountList = settInterestHeadDAO
        .queryOVAccountBalanceAllByCriterions(orgId, accYear, empId, empName);
    if (!ovAccountBalanceCountList.isEmpty()) {
      count = ovAccountBalanceCountList.size();
    }
    // orgVerAccountBalanceDTO
    if (!ovAccountBalanceList.isEmpty()) {
      orgVerAccountBalanceAF.setList(ovAccountBalanceList);
    }
    pagination.setNrOfElements(count);
    return orgVerAccountBalanceAF;
  }

  /**
   * ��ת���
   * 
   * @author wangy 2007-12-21
   * @param id AA316.ID
   * @param pagination
   * @param securityInfo
   * @param ��OrgVerAccountBalanceMaintainAC����
   */
  public void doOrgVerAccountBalance(Serializable id, String docNum,
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    try {
      String orgId = (String) pagination.getQueryCriterions().get("orgId");
      String accYear = (String) pagination.getQueryCriterions().get("accYear");
      // �ж�DA001���Ƿ����DA001.ORG_HEAD_ID = ��λ��AA316.ID�ļ�¼
      boolean isExistsOVAccBalance = settInterestHeadDAODW
          .isExistsOVAccBalance(id.toString(), orgId, securityInfo);
      if (isExistsOVAccBalance) {
        throw new BusinessException("�Ѿ����й�����ת��");
      }
      String empId = null;
      String empName = null;
      // �ж��Ƿ��������������=��λ������� or ���ı�����=��λ�������ļ�¼
      List existsEqualsOVAccBalanceList = settInterestHeadDAO
          .queryExistsEqualsOVAccBalanceList(orgId, accYear, securityInfo);
      if (existsEqualsOVAccBalanceList.size() == 0) {
        throw new BusinessException("��λ�˻���������Ĳ�һ�£������Խ�������ת��");
      }
      // ��ѯҪ��ת��ȫ��ְ������
      List ovAccountBalanceAllList = settInterestHeadDAO
          .queryOVAccountBalanceAllByCriterions(orgId, accYear, empId, empName);
      int busiCount = ovAccountBalanceAllList.size();// BUSICOUNT=count��AA318.id�������

      // ����AA101
      BigDecimal sumInterest = new BigDecimal(0.00);
      // CREDIT=sum��AA318.PRE_INTEREST+AA318.CUR_INTEREST��
      sumInterest = settInterestResultDAO
          .queryOVAccBalanceInterestByOrgIdAndYear(orgId, accYear);
      // ���ò�ѯ�������е�λ״̬�ķ��� wangy 2008-03-01 ����Ȼ�ڵ�λ���в����е�λ״̬Ϊ������_1�ģ�
      Org org = this.findOrgInfo(orgId, securityInfo);
      String bizDate = securityInfo.getUserInfo().getBizDate();// �������
      OrgHAFAccountFlowEndAccrual orgHAFAccountFlowEndAccrual = new OrgHAFAccountFlowEndAccrual();
      orgHAFAccountFlowEndAccrual.setOrg(org);
      orgHAFAccountFlowEndAccrual.setDocNum(docNum);
      orgHAFAccountFlowEndAccrual.setSettDate(bizDate);
      orgHAFAccountFlowEndAccrual.setDebit(new BigDecimal(0));
      orgHAFAccountFlowEndAccrual.setCredit(sumInterest);
      orgHAFAccountFlowEndAccrual.setInterest(new BigDecimal(0));
      orgHAFAccountFlowEndAccrual.setBizId(new BigDecimal(id.toString()));
      orgHAFAccountFlowEndAccrual.setBizStatus(new BigDecimal(5));
      orgHAFAccountFlowEndAccrual.setIsFinance(new BigDecimal(1));
      orgHAFAccountFlowEndAccrual.setPersonTotal(new Integer(busiCount));
      orgHAFAccountFlowEndAccrualDAO.insert(orgHAFAccountFlowEndAccrual);
      Object[] obj = null;
      for (int i = 0; i < ovAccountBalanceAllList.size(); i++) {
        obj = (Object[]) ovAccountBalanceAllList.get(i);
        String empid = "";// AA102.EMP_ID=AA318.EMP_ID
        BigDecimal creditInterest = new BigDecimal(0.00);
        BigDecimal preInterest = new BigDecimal(0.00);
        BigDecimal curInterest = new BigDecimal(0.00);
        if (obj[0] != null) {
          empid = obj[0].toString();
        }
        if (obj[8] != null) {
          preInterest = new BigDecimal(obj[8].toString());
        }
        if (obj[9] != null) {
          curInterest = new BigDecimal(obj[9].toString());
        }
        creditInterest = preInterest.add(curInterest);// AA102.CREDIT=AA318.PRE_INTEREST+AA318.CUR_INTEREST
        // ����AA102
        EmpHAFAccountFlow empHAFAccountFlow = new EmpHAFAccountFlow();
        empHAFAccountFlow.setEmpId(new Integer(empid));
        empHAFAccountFlow.setOrgHAFAccountFlow(orgHAFAccountFlowEndAccrual);
        empHAFAccountFlow.setDebit(new BigDecimal(0));
        empHAFAccountFlow.setCredit(creditInterest);
        empHAFAccountFlow.setInterest(new BigDecimal(0));
        empHAFAccountFlowDAO.insert(empHAFAccountFlow);

        // ����AA002
        // ְ���ı���������ֶ�����������ֶα���������Լ��ֶμ���Ӧ�ı������ʡ��������ʡ�������Ϊ0
        List empList = empDAO.queryByEmpId_lg(new Integer(empid), new Integer(orgId));
        Emp emp = null;
        if ( !empList.isEmpty()) {
          emp = (Emp) empList.get(0);
        }
        BigDecimal empPreBalance = new BigDecimal(0.00);// ְ�����������
        BigDecimal preIntegral = new BigDecimal(0.00);// �������=�������*360
        BigDecimal preBalance = new BigDecimal(0.00);
        BigDecimal curBalance = new BigDecimal(0.00);
        if (obj[2] != null) {
          preBalance = new BigDecimal(obj[2].toString());
        }
        if (obj[4] != null) {
          curBalance = new BigDecimal(obj[4].toString());
        }
        empPreBalance = creditInterest.add(preBalance.add(curBalance));// =AA318.BEF_PRE_BALANCE+AA318.BEF_CUR_BALANCE+AA318.PRE_INTEREST+AA318.CUR_INTEREST
        preIntegral = empPreBalance.multiply(new BigDecimal(365));
        emp.setPreBalance(empPreBalance);
        emp.setPreIntegral(preIntegral);
        emp.setCurBalance(new BigDecimal(0));
        emp.setCurIntegral(new BigDecimal(0));
        emp.setCurIntegralSubA(new BigDecimal(0));
        emp.setCurIntegralSubB(new BigDecimal(0));
        emp.setCurIntegralSubC(new BigDecimal(0));
        emp.setPreIntegralSubA(new BigDecimal(0));
        emp.setPreIntegralSubB(new BigDecimal(0));
        emp.setPreIntegralSubC(new BigDecimal(0));
        emp.setCurRateA(new BigDecimal(0));
        emp.setCurRateB(new BigDecimal(0));
        emp.setCurRateC(new BigDecimal(0));
        emp.setPreRateA(new BigDecimal(0));
        emp.setPreRateC(new BigDecimal(0));
        emp.setPreRateB(new BigDecimal(0));
      }
      // ����AA305 ÿ����λ���²���12�����ݣ�����7��-�ڶ���6��
      BigDecimal orgPaySum = new BigDecimal(0.00);// ��λ�ɶ�=�õ�λ��AA002���нɴ�״̬Ϊ1��3��4�ĵ�λ�ɶ�֮��
      BigDecimal empPaySum = new BigDecimal(0.00);// ְ���ɶ�=�õ�λ��AA002���нɴ�״̬Ϊ1��3��4��ְ���ɶ�֮��
      OrgVerAccountBalanceDTO orgVerAccountBalanceDTO = null;
      List paySumList = orgDAO.queryOrgPaySumAndEmpPaySumByOrgId(orgId);
      if ( !paySumList.isEmpty()) {
        orgVerAccountBalanceDTO = (OrgVerAccountBalanceDTO) paySumList.get(0);
        orgPaySum = orgVerAccountBalanceDTO.getOrgPaySum();
        empPaySum = orgVerAccountBalanceDTO.getEmpPaySum();
      }
      // ͨ��orgIdȡAA305��������
      String maxYearMonth = searchLackInfoDAO.queryMaxYearMonthByOrgId(orgId);
      int maxYear = new Integer(maxYearMonth.substring(0, 4)).intValue();
      int bizYear = new Integer(bizDate.substring(0, 4)).intValue();
      if (maxYear <= bizYear) {// ��������С��ҵ���꣬����AA305����12����¼(�������7�£��������+1���6��)
        // ��ʼ����
        String sDate = maxYearMonth.substring(0, 4) + "07";
        // �����ֹ����
        int eYear = maxYear + 1;
        String eDate = eYear + "06";
        int i = BusiTools.monthInterval(sDate, eDate);
        for (int j = 0; j <= i; j++) {
          String month = BusiTools.addMonth(sDate, j);
          SearchLackInfo searchLackInfo = new SearchLackInfo();
          searchLackInfo.setYearMonth(month);
          searchLackInfo.setOrg(org);
          searchLackInfo.setOegPay(orgPaySum);
          searchLackInfo.setEmpPay(empPaySum);
          searchLackInfo.setEmpPayReal(new BigDecimal(0.00));// ְ��ʵ�ɶ�=0
          searchLackInfo.setOrgPayReal(new BigDecimal(0.00));// ��λʵ�ɶ�=0
          searchLackInfoDAO.insert(searchLackInfo);
        }
      }

      // ����DA001
      // ͨ��OrgId,accYear,��ѯ���İ�AA316.ID
      String centerHeadId = settInterestHeadDAODW
          .querySettInterestHeadIdByOrgIdAndYear(orgId, accYear);
      Integer payHeadId = null;
      Date centerBizDate = null;
      AutoInfoPick autoInfoPick = new AutoInfoPick();
      autoInfoPick.setOrgId(new Integer(orgId));
      autoInfoPick.setOrgHeadId(new Integer(id.toString()));
      autoInfoPick.setCenterHeadId(new Integer(centerHeadId));// DA001.CENTER_HEAD_ID
                                                              // = ���İ�AA316.ID
      autoInfoPick.setType(BusiConst.ORGBUSINESSTYPE_H);// H�����ս�Ϣ��
      autoInfoPick.setStatus(BusiConst.OC_PICK_UP);// 1������ȡ��
      autoInfoPick.setPayHeadId(payHeadId);
      autoInfoPick.setOrgBizDate(new Date());
      autoInfoPick.setCenterBizDate(centerBizDate);
      autoInfoPickDAODW.insert(autoInfoPick);
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * ��ѯ��λ��Ϣ
   * 
   * @author wangy 2007-12-21
   * @param orgId ��λ���
   * @param ��OrgVerAccountBalanceMaintainAC����
   */
  public Org queryOrgInfo(String orgId, SecurityInfo securityInfo)
      throws BusinessException {
    Org org = null;
    if (orgId != null) {
      org = orgDAO.queryById(new Integer(orgId));
    }
    return org;
  }

}
