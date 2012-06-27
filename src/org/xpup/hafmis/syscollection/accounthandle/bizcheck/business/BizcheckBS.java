package org.xpup.hafmis.syscollection.accounthandle.bizcheck.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.bsinterface.IBizcheckBS;
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.dto.BizcheckDTO;
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.form.BizcheckAF;
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.form.BizcheckDetailAF;
import org.xpup.hafmis.syscollection.common.dao.AdjustWrongAccountHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.BizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.ChangeAccountBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpAddPayBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.MonthPaymentBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.MonthPaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.OfficeParaDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgAddPayBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgExcessPaymentBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgExcessPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowExcessPaymentBalanceEndTransferDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowExcessPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.PaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.PickBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.PickHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.TranInBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.TranInHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.TranInTailDAO;
import org.xpup.hafmis.syscollection.common.dao.TranOutBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.TranOutHeadDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountHead;
import org.xpup.hafmis.syscollection.common.domain.entity.BizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.ChangeAccountBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpAddPayBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.OfficePara;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgAddPayBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgExcessPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgExcessPaymentBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowExcessPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowExcessPaymentBalanceEndTransfer;
import org.xpup.hafmis.syscollection.common.domain.entity.PaymentHead;
import org.xpup.hafmis.syscollection.common.domain.entity.PickBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.PickHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutHead;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;

public class BizcheckBS implements IBizcheckBS {
  private TranInTailDAO tranInTailDAO = null;

  private OrgHAFAccountFlowExcessPaymentBalanceEndTransferDAO orgHAFAccountFlowExcessPaymentBalanceEndTransferDAO = null;

  private OrgExcessPaymentDAO orgExcessPaymentDAO = null;

  private OrgHAFAccountFlowExcessPaymentDAO orgHAFAccountFlowExcessPaymentDAO = null;

  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO = null;

  private EmpHAFAccountFlowDAO empHAFAccountFlowDAO = null;

  private PaymentHeadDAO paymentHeadDAO = null;

  private PickHeadDAO pickHeadDAO = null;

  private TranOutHeadDAO tranOutHeadDAO = null;

  private TranInHeadDAO tranInHeadDAO = null;

  private AdjustWrongAccountHeadDAO adjustWrongAccountHeadDAO = null;

  private HafOperateLogDAO hafOperateLogDAO = null;

  private BizActivityLogDAO bizActivityLogDAO = null;

  private PickBizActivityLogDAO pickBizActivityLogDAO = null;

  private TranOutBizActivityLogDAO tranOutBizActivityLogDAO = null;

  private TranInBizActivityLogDAO tranInBizActivityLogDAO = null;

  private ChangeAccountBizActivityLogDAO changeAccountBizActivityLogDAO = null;

  private MonthPaymentBizActivityLogDAO monthPaymentBizActivityLogDAO = null;

  private OrgAddPayBizActivityLogDAO orgAddPayBizActivityLogDAO = null;

  private OrgExcessPaymentBizActivityLogDAO orgExcessPaymentBizActivityLogDAO = null;

  private EmpDAO empDAO = null;

  private CollBankDAO collBankDAO = null;

  private EmpAddPayBizActivityLogDAO empAddPayBizActivityLogDAO = null;

  private MonthPaymentHeadDAO monthPaymentHeadDAO = null;

  private OfficeParaDAO officeParaDAO = null;

  private OrgDAO orgDAO = null;

  private SecurityDAO securityDAO = null;

  public void setMonthPaymentHeadDAO(MonthPaymentHeadDAO monthPaymentHeadDAO) {
    this.monthPaymentHeadDAO = monthPaymentHeadDAO;
  }

  public void setEmpHAFAccountFlowDAO(EmpHAFAccountFlowDAO empHAFAccountFlowDAO) {
    this.empHAFAccountFlowDAO = empHAFAccountFlowDAO;
  }

  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }

  public void setPaymentHeadDAO(PaymentHeadDAO paymentHeadDAO) {
    this.paymentHeadDAO = paymentHeadDAO;
  }

  public void setPickHeadDAO(PickHeadDAO pickHeadDAO) {
    this.pickHeadDAO = pickHeadDAO;
  }

  public void setTranOutHeadDAO(TranOutHeadDAO tranOutHeadDAO) {
    this.tranOutHeadDAO = tranOutHeadDAO;
  }

  public void setTranInHeadDAO(TranInHeadDAO tranInHeadDAO) {
    this.tranInHeadDAO = tranInHeadDAO;
  }

  public void setAdjustWrongAccountHeadDAO(
      AdjustWrongAccountHeadDAO adjustWrongAccountHeadDAO) {
    this.adjustWrongAccountHeadDAO = adjustWrongAccountHeadDAO;
  }

  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }

  public void setBizActivityLogDAO(BizActivityLogDAO bizActivityLogDAO) {
    this.bizActivityLogDAO = bizActivityLogDAO;
  }

  public void setPickBizActivityLogDAO(
      PickBizActivityLogDAO pickBizActivityLogDAO) {
    this.pickBizActivityLogDAO = pickBizActivityLogDAO;
  }

  public void setTranOutBizActivityLogDAO(
      TranOutBizActivityLogDAO tranOutBizActivityLogDAO) {
    this.tranOutBizActivityLogDAO = tranOutBizActivityLogDAO;
  }

  public void setTranInBizActivityLogDAO(
      TranInBizActivityLogDAO tranInBizActivityLogDAO) {
    this.tranInBizActivityLogDAO = tranInBizActivityLogDAO;
  }

  public void setChangeAccountBizActivityLogDAO(
      ChangeAccountBizActivityLogDAO changeAccountBizActivityLogDAO) {
    this.changeAccountBizActivityLogDAO = changeAccountBizActivityLogDAO;
  }

  public void setMonthPaymentBizActivityLogDAO(
      MonthPaymentBizActivityLogDAO monthPaymentBizActivityLogDAO) {
    this.monthPaymentBizActivityLogDAO = monthPaymentBizActivityLogDAO;
  }

  public void setOrgAddPayBizActivityLogDAO(
      OrgAddPayBizActivityLogDAO orgAddPayBizActivityLogDAO) {
    this.orgAddPayBizActivityLogDAO = orgAddPayBizActivityLogDAO;
  }

  public void setOrgExcessPaymentBizActivityLogDAO(
      OrgExcessPaymentBizActivityLogDAO orgExcessPaymentBizActivityLogDAO) {
    this.orgExcessPaymentBizActivityLogDAO = orgExcessPaymentBizActivityLogDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setEmpAddPayBizActivityLogDAO(
      EmpAddPayBizActivityLogDAO empAddPayBizActivityLogDAO) {
    this.empAddPayBizActivityLogDAO = empAddPayBizActivityLogDAO;
  }

  public void setOfficeParaDAO(OfficeParaDAO officeParaDAO) {
    this.officeParaDAO = officeParaDAO;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  // Ĭ�ϲ�ѯAA101.biz_status = 3�ļ�¼
  public BizcheckAF findOrgHAFAccountFlowListBydefault(Pagination pagination)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BizcheckAF bizcheckAF = new BizcheckAF();
    int bizcheclistcount = 0;
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    String orderBy = (String) pagination.getOrderBy();
    String order = pagination.getOrderother();
    String type = null;
    String yga = "";
    List bizcheclist = new ArrayList();
    List bizchectotlallist = new ArrayList();
    String StatusType0 = "";
    String StatusType1 = "";
    String StatusType2 = "";
    int totalCount = 0;
    BigDecimal totalDcitsum = new BigDecimal(0.0);
    BigDecimal totalInterest = new BigDecimal(0.0);
    try {
      type = (String) pagination.getQueryCriterions().get("type");
      yga = (String) pagination.getQueryCriterions().get("yga");
      String orgId = (String) pagination.getQueryCriterions().get("orgId");
      // System.out.println("bizcheckbs.."+orgId);
      if (orgId != null && orgId != "") {
        if (orgId.length() == 10) {
          orgId = orgId.substring(1, orgId.length());
        }
      }
      String noteNum = (String) pagination.getQueryCriterions().get("noteNum");// Ʊ�ݱ��
      String docNum = (String) pagination.getQueryCriterions().get("docNum");// ƾ֤���
      String orgName = (String) pagination.getQueryCriterions().get("orgName");// ��λ����
      String collectionBank = (String) pagination.getQueryCriterions().get(
          "collectionBank");// �鼯����
      SecurityInfo securityInfo = (SecurityInfo) pagination
          .getQueryCriterions().get("SecurityInfo");
      String bizStatus = (String) pagination.getQueryCriterions().get(
          "bizStatus");
      String startDate = (String) pagination.getQueryCriterions().get(
          "startDate");// ��ʼ����
      String endDate = (String) pagination.getQueryCriterions().get("endDate");// ��ʼ����
      String biz_Type = (String) pagination.getQueryCriterions()
          .get("biz_Type");// ҵ������
      if (yga.equals("search")) {// ��Forward������
        startDate = securityInfo.getUserInfo().getBizDate();
        endDate = securityInfo.getUserInfo().getBizDate();
        type = "1";
        bizStatus = "3";
      }
      String operator = (String) pagination.getQueryCriterions()
          .get("operator");// �Ƶ���

      // �����Ƶ��˿ɵ�ҵ����־AA319����ȥȡ������ҵ��ID�ż�ҵ�����͡�ҵ��״̬=3
      if (operator != null && !operator.equals("")) {
        bizcheclist = orgHAFAccountFlowDAO.queryByCriterionsByoperatorWuht(
            noteNum, docNum, orgId, orgName, operator, collectionBank,
            bizStatus, startDate, endDate, start, pageSize, orderBy, order,
            securityInfo, page, biz_Type);

        bizchectotlallist = orgHAFAccountFlowDAO
            .queryByCriterionsByoperatorOtherWuht(noteNum, docNum, orgId,
                orgName, operator, collectionBank, bizStatus, startDate,
                endDate, start, pageSize, orderBy, order, securityInfo,
                biz_Type);
        bizcheclistcount = orgHAFAccountFlowDAO
            .queryByCriterionsByoperatorCountWuht(noteNum, docNum, orgId,
                orgName, operator, collectionBank, bizStatus, startDate,
                endDate, start, pageSize, orderBy, order, securityInfo,
                biz_Type);// ͷ��;
      } else if (type != null && type.equals("1")) {

        bizcheclist = orgHAFAccountFlowDAO.queryByCriterions(noteNum, docNum,
            orgId, orgName, operator, collectionBank, bizStatus, startDate,
            endDate, start, pageSize, orderBy, order, securityInfo, page,
            biz_Type);

        bizchectotlallist = orgHAFAccountFlowDAO.queryByCriterionsOtherWuht(
            noteNum, docNum, orgId, orgName, operator, collectionBank,
            bizStatus, startDate, endDate, start, pageSize, orderBy, order,
            securityInfo, biz_Type);
        bizcheclistcount = orgHAFAccountFlowDAO.queryByCriterionsWuht(noteNum,
            docNum, orgId, orgName, operator, collectionBank, bizStatus,
            startDate, endDate, start, pageSize, orderBy, order, securityInfo,
            biz_Type);// ͷ��;
        // �ж�ҵ��״̬0������ȷ�Ϻ͸��� 1��ֻ��ȷ�� 2��ֻ�и��ˣ���ҳ������ʾ��ť��

      } else {
        // ҵ�񸴺��б��дӴ�AA101��AA102��ȡ����صļ�¼������Ϊת�뵥λ��ţ�AA101.BIZ_STATUS=3��
        bizcheclist = orgHAFAccountFlowDAO.queryOrgHAFAccountFlowListWuht(
            start, pageSize, orderBy, order, securityInfo, page);
        bizchectotlallist = orgHAFAccountFlowDAO
            .queryOrgHAFAccountFlowListOtherWuht(start, pageSize, orderBy,
                order, securityInfo);
        bizcheclistcount = orgHAFAccountFlowDAO
            .queryOrgHAFAccountFlowListWuht(securityInfo);// ͷ��;
        // �ж�ҵ��״̬0������ȷ�Ϻ͸��� 1��ֻ��ȷ�� 2��ֻ�и��ˣ���ҳ������ʾ��ť��
        bizcheckAF.setStatusType("1");
      }

      if (bizchectotlallist != null) {
        totalCount = 0;
        totalDcitsum = new BigDecimal(0.0);
        totalInterest = new BigDecimal(0.0);
        StatusType0 = "";
        StatusType1 = "";
        StatusType2 = "";
        for (int k = 0; k < bizchectotlallist.size(); k++) {
          BizcheckDTO bizcheckDTO = (BizcheckDTO) bizchectotlallist.get(k);
          totalCount = totalCount
              + new Integer(bizcheckDTO.getPersonTotal().toString()).intValue();
          totalDcitsum = totalDcitsum.add(new BigDecimal(bizcheckDTO.getMoney()
              .toString()));
          if (bizcheckDTO.getInterest().toString() != null) {
            totalInterest = totalInterest.add(new BigDecimal(bizcheckDTO
                .getInterest().toString()));
          }
          if ("3".equals(bizcheckDTO.getBizStatus())) {
            StatusType1 = "1";
          }
          if ("4".equals(bizcheckDTO.getBizStatus())) {
            StatusType2 = "2";
          }
          if ("5".equals(bizcheckDTO.getBizStatus())) {// "5"==���˵�ʱ��
            StatusType0 = "0";
          }
        }
      }

      if (StatusType0 == "0") {
        bizcheckAF.setStatusType("0");
      } else {
        if (StatusType1 == "1" && StatusType2 == "") {
          bizcheckAF.setStatusType("1");
        }
        if (StatusType1 == "" && StatusType2 == "2") {
          bizcheckAF.setStatusType("2");
        }
        if (StatusType1 == "1" && StatusType0 == "0") {
          bizcheckAF.setStatusType("3");
        }
        if (StatusType1 == "1" && StatusType2 == "2") {
          bizcheckAF.setStatusType("0");
        }
      }
      bizcheckAF.setTotalCount(totalCount);
      bizcheckAF.setTotalDcitsum(totalDcitsum);
      bizcheckAF.setTotalInterest(totalInterest);

      pagination.setNrOfElements(bizcheclistcount);
      if (bizcheclist != null && bizcheclist.size() > 0) {
      } else {
        bizcheclist = new ArrayList();
      }
      for (int i = 0; i < bizcheclist.size(); i++) {
        BizcheckDTO bizcheckDTO = (BizcheckDTO) bizcheclist.get(i);
        bizcheckDTO.setReserveaA(orgHAFAccountFlowDAO
            .getOperator_yg(bizcheckDTO.getReserveaA()));
      }
      bizcheckAF.setBizchectotlallist(bizchectotlallist);
      bizcheckAF.setList(bizcheclist);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return bizcheckAF;
  }

  // ����ͷ��(aa101)ID��ѯβ��(aa102)����
  public int queryEmpCountById() {

    return 0;
  }

  // /������ҵ�񸴺ˡ�����ͨ��
  /**
   * ����������ѯ ���� A ��� (MonthPaymentBizActivityLog :A)
   * B:����(EmpAddPayBizActivityLog:K) C:
   * ����(OrgExcessPaymentBizActivityLog:C)M:��λ����(OrgAddPayBizActivityLog:B)
   * ��ӦAA301 D:��ȡ(PickBizActivityLog:D) AA306 E:ת��(TranOutBizActivityLog:E)AA309
   * F:ת��(TranInBizActivityLog:F)AA311 G:����(ChangeAccountBizActivityLog:G)
   * K�����ɴ�(ChangeAccountBizActivityLog)L������ȡ(ChangeAccountBizActivityLog)AA314
   */
  public void checkthroughOrgHAFAccountFlow(Integer id, String ip, String name,
      String officeCode, String moneyBank) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    OrgHAFAccountFlow orgHAFAccountFlow = null;
    PickBizActivityLog pickBizActivityLog = new PickBizActivityLog();
    TranOutBizActivityLog tranOutBizActivityLog = new TranOutBizActivityLog();
    TranInBizActivityLog tranInBizActivityLog = new TranInBizActivityLog();
    ChangeAccountBizActivityLog changeAccountBizActivityLog = new ChangeAccountBizActivityLog();
    MonthPaymentBizActivityLog monthPaymentBizActivityLog = new MonthPaymentBizActivityLog();
    OrgAddPayBizActivityLog orgAddPayBizActivityLog = new OrgAddPayBizActivityLog();
    OrgExcessPaymentBizActivityLog orgExcessPaymentBizActivityLog = new OrgExcessPaymentBizActivityLog();
    EmpAddPayBizActivityLog empAddPayBizActivityLog = new EmpAddPayBizActivityLog();

    PaymentHead paymentHead = null;
    PickHead pickHead = null;
    TranOutHead tranOutHead = null;
    TranInHead tranInHead = null;
    AdjustWrongAccountHead adjustWrongAccountHead = null;
    try {
      orgHAFAccountFlow = orgHAFAccountFlowDAO.queryById(id);

      String orgId = orgHAFAccountFlow.getOrg().getId().toString();
      String bizId = orgHAFAccountFlow.getBizId().toString();

      String bizTrpe = orgHAFAccountFlow.getBiz_Type();
      orgHAFAccountFlow.setBizStatus(new BigDecimal(4));
      orgHAFAccountFlow.setCheckPerson(name);
      // ����������ʼ
      if (moneyBank != null && !"".equals(moneyBank)) {
        orgHAFAccountFlow.setMoneyBank(moneyBank);
      }
      if (officeCode != null && !"".equals(officeCode)) {
        orgHAFAccountFlow.setOfficeCode(officeCode);
      }
      // ����
      if (bizTrpe.equals("A") || bizTrpe.equals("B") || bizTrpe.equals("C")
          || bizTrpe.equals("M")) {
        // ����AA301
        paymentHead = paymentHeadDAO.queryById(new Integer(bizId));
        paymentHead.setPayStatus(new Integer(4));
        if (bizTrpe.equals("A")) {
          // ����AA319��
          monthPaymentBizActivityLog.setBizid(new Integer(bizId));
          monthPaymentBizActivityLog.setAction(new Integer(4));
          monthPaymentBizActivityLog.setOpTime(new Date());
          monthPaymentBizActivityLog.setOperator(name);
          monthPaymentBizActivityLogDAO.insert(monthPaymentBizActivityLog);
          // �ж��Ƿ�����Զ�����ҵ��
          if (orgHAFAccountFlow.getReserveaC() != null
              && !orgHAFAccountFlow.getReserveaC().equals("")) {
            OrgHAFAccountFlow temp_orgHAFAccountFlow = orgHAFAccountFlowDAO
                .queryByBizId_wsh(orgHAFAccountFlow.getReserveaC(), "C");
            temp_orgHAFAccountFlow.setBizStatus(new BigDecimal(4));
            temp_orgHAFAccountFlow.setMoneyBank(orgHAFAccountFlow
                .getMoneyBank());
            temp_orgHAFAccountFlow.setOfficeCode(orgHAFAccountFlow
                .getOfficeCode());

            PaymentHead temp_paymentHead = paymentHeadDAO
                .queryById(new Integer(orgHAFAccountFlow.getReserveaC()));
            temp_paymentHead.setPayStatus(new Integer(4));
            // ����AA319��
            orgExcessPaymentBizActivityLog.setBizid(new Integer(
                orgHAFAccountFlow.getReserveaC()));
            orgExcessPaymentBizActivityLog.setAction(new Integer(4));
            orgExcessPaymentBizActivityLog.setOpTime(new Date());
            orgExcessPaymentBizActivityLog.setOperator(name);
            orgExcessPaymentBizActivityLogDAO
                .insert(orgExcessPaymentBizActivityLog);
          }
        }
        if (bizTrpe.equals("B")) {
          // ����AA319��

          empAddPayBizActivityLog.setBizid(new Integer(bizId));
          empAddPayBizActivityLog.setAction(new Integer(4));
          empAddPayBizActivityLog.setOpTime(new Date());
          empAddPayBizActivityLog.setOperator(name);
          empAddPayBizActivityLogDAO.insert(empAddPayBizActivityLog);
        }
        if (bizTrpe.equals("M")) {
          // ����AA319��

          orgAddPayBizActivityLog.setBizid(new Integer(bizId));
          orgAddPayBizActivityLog.setAction(new Integer(4));
          orgAddPayBizActivityLog.setOpTime(new Date());
          orgAddPayBizActivityLog.setOperator(name);
          orgAddPayBizActivityLogDAO.insert(orgAddPayBizActivityLog);
        }
        if (bizTrpe.equals("C")) {
          // ����AA319��
          orgExcessPaymentBizActivityLog.setBizid(new Integer(bizId));
          orgExcessPaymentBizActivityLog.setAction(new Integer(4));
          orgExcessPaymentBizActivityLog.setOpTime(new Date());
          orgExcessPaymentBizActivityLog.setOperator(name);
          orgExcessPaymentBizActivityLogDAO
              .insert(orgExcessPaymentBizActivityLog);
          // �ж��Ƿ�����Զ�����ҵ��
          if (orgHAFAccountFlow.getReserveaC() != null
              && !orgHAFAccountFlow.getReserveaC().equals("")) {
            OrgHAFAccountFlow temp_orgHAFAccountFlow = orgHAFAccountFlowDAO
                .queryByBizId_wsh(orgHAFAccountFlow.getReserveaC(), "A");
            temp_orgHAFAccountFlow.setBizStatus(new BigDecimal(4));
            temp_orgHAFAccountFlow.setMoneyBank(orgHAFAccountFlow
                .getMoneyBank());
            temp_orgHAFAccountFlow.setOfficeCode(orgHAFAccountFlow
                .getOfficeCode());

            PaymentHead temp_paymentHead = paymentHeadDAO
                .queryById(new Integer(orgHAFAccountFlow.getReserveaC()));
            temp_paymentHead.setPayStatus(new Integer(4));

            // ����AA319��
            monthPaymentBizActivityLog.setBizid(new Integer(orgHAFAccountFlow
                .getReserveaC()));
            monthPaymentBizActivityLog.setAction(new Integer(4));
            monthPaymentBizActivityLog.setOpTime(new Date());
            monthPaymentBizActivityLog.setOperator(name);
            monthPaymentBizActivityLogDAO.insert(monthPaymentBizActivityLog);
          }
        }
      }

      if (bizTrpe.equals("D")) {
        // ����AA306
        pickHead = pickHeadDAO.queryById(new Integer(bizId));
        pickHead.setPickSatatus(new BigDecimal(4));
        // ����AA319��
        pickBizActivityLog.setBizid(new Integer(bizId));
        pickBizActivityLog.setAction(new Integer(4));
        pickBizActivityLog.setOpTime(new Date());
        pickBizActivityLog.setOperator(name);
        pickBizActivityLogDAO.insert(pickBizActivityLog);
      }
      if (bizTrpe.equals("E")) {
        // ����AA309
        tranOutHead = tranOutHeadDAO.queryById(new Integer(bizId));
        tranOutHead.setTranStatus(new BigDecimal(4));

        // ����AA319��
        tranOutBizActivityLog.setBizid(new Integer(bizId));
        tranOutBizActivityLog.setAction(new Integer(4));
        tranOutBizActivityLog.setOpTime(new Date());
        tranOutBizActivityLog.setOperator(name);
        tranOutBizActivityLogDAO.insert(tranOutBizActivityLog);
      }
      if (bizTrpe.equals("F")) {
        // ����AA311
        tranInHead = tranInHeadDAO.queryByIdWuht(new Integer(bizId));
        tranInHead.setTranStatus(new BigDecimal(4));

        // ����AA319��
        tranInBizActivityLog.setBizid(new Integer(bizId));
        tranInBizActivityLog.setAction(new Integer(4));
        tranInBizActivityLog.setOpTime(new Date());
        tranInBizActivityLog.setOperator(name);
        tranInBizActivityLogDAO.insert(tranInBizActivityLog);

      }
      if (bizTrpe.equals("G") || bizTrpe.equals("K") || bizTrpe.equals("L")) {
        // ����AA314
        adjustWrongAccountHead = adjustWrongAccountHeadDAO
            .queryById(new Integer(bizId));
        adjustWrongAccountHead.setAdjustStatus(new BigDecimal(4));

        // ����AA319��
        changeAccountBizActivityLog.setBizid(new Integer(bizId));
        changeAccountBizActivityLog.setAction(new Integer(4));
        changeAccountBizActivityLog.setOpTime(new Date());
        changeAccountBizActivityLog.setOperator(name);
        changeAccountBizActivityLogDAO.insert(changeAccountBizActivityLog);
      }

      // ����BA003��
      BusiLogConst busiLogConst = null;
      int opButton = busiLogConst.BIZLOG_ACTION_CHECKS;
      int opModel = busiLogConst.OP_MODE_ACCOUNTMANAGE_OPERATIONCHECK;

      this.addhafOperateLog(name, opButton, opModel, bizId, ip, orgId);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // /������ҵ�񸴺ˡ���������
  public void delcheckthroughOrgHAFAccountFlow(Integer id, String ip,
      String name, String collectionBankId, String officeCode)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {
      OrgHAFAccountFlow orgHAFAccountFlow = null;

      BizActivityLog bizActivityLog = new BizActivityLog();
      EmpAddPayBizActivityLog empAddPayBizActivityLog = new EmpAddPayBizActivityLog();
      PaymentHead paymentHead = null;
      PickHead pickHead = null;
      TranOutHead tranOutHead = null;
      TranInHead tranInHead = null;
      AdjustWrongAccountHead adjustWrongAccountHead = null;

      orgHAFAccountFlow = orgHAFAccountFlowDAO.queryById(id);
      String orgId = orgHAFAccountFlow.getOrg().getId().toString();
      String bizId = orgHAFAccountFlow.getBizId().toString();

      String bizTrpe = orgHAFAccountFlow.getBiz_Type();

      String bizStatus = orgHAFAccountFlow.getBizStatus().toString();

      orgHAFAccountFlow.setBizStatus(new BigDecimal(3));
      // ����������ʼ
      orgHAFAccountFlow.setMoneyBank(collectionBankId);
      orgHAFAccountFlow.setOfficeCode(officeCode);
      // ����
      if (bizTrpe.equals("A") || bizTrpe.equals("B") || bizTrpe.equals("C")
          || bizTrpe.equals("M")) {
        paymentHead = paymentHeadDAO.queryById(new Integer(bizId));
        paymentHead.setPayStatus(new Integer(3));
        // �ж��Ƿ�Ϊ�Զ�����ҵ��
        if (orgHAFAccountFlow.getReserveaC() != null
            && !orgHAFAccountFlow.getReserveaC().equals("")) {

          if (bizTrpe.equals("A")) {
            OrgHAFAccountFlow temp_orgHAFAccountFlow = orgHAFAccountFlowDAO
                .queryByBizId_wsh(orgHAFAccountFlow.getReserveaC(), "C");
            temp_orgHAFAccountFlow.setBizStatus(new BigDecimal(3));
            paymentHead = paymentHeadDAO.queryById(new Integer(
                orgHAFAccountFlow.getReserveaC()));
            paymentHead.setPayStatus(new Integer(3));
            bizActivityLog = bizActivityLogDAO.queryBizActivityLogWuht_(
                orgHAFAccountFlow.getReserveaC(), "C", bizStatus);
            bizActivityLogDAO.deleteWuht(bizActivityLog);
          } else if (bizTrpe.equals("C")) {
            OrgHAFAccountFlow temp_orgHAFAccountFlow = orgHAFAccountFlowDAO
                .queryByBizId_wsh(orgHAFAccountFlow.getReserveaC(), "A");
            temp_orgHAFAccountFlow.setBizStatus(new BigDecimal(3));
            paymentHead = paymentHeadDAO.queryById(new Integer(
                orgHAFAccountFlow.getReserveaC()));
            paymentHead.setPayStatus(new Integer(3));
            bizActivityLog = bizActivityLogDAO.queryBizActivityLogWuht_(
                orgHAFAccountFlow.getReserveaC(), "A", bizStatus);
            bizActivityLogDAO.deleteWuht(bizActivityLog);
          }
        }

      }

      if (bizTrpe.equals("D")) {
        pickHead = pickHeadDAO.queryById(new Integer(bizId));
        pickHead.setPickSatatus(new BigDecimal(3));

      }
      if (bizTrpe.equals("E")) {
        tranOutHead = tranOutHeadDAO.queryById(new Integer(bizId));
        tranOutHead.setTranStatus(new BigDecimal(3));

      }
      if (bizTrpe.equals("F")) {
        tranInHead = tranInHeadDAO.queryByIdWuht(new Integer(bizId));
        tranInHead.setTranStatus(new BigDecimal(3));

      }
      if (bizTrpe.equals("G") || bizTrpe.equals("K") || bizTrpe.equals("L")) {
        adjustWrongAccountHead = adjustWrongAccountHeadDAO
            .queryById(new Integer(bizId));
        adjustWrongAccountHead.setAdjustStatus(new BigDecimal(3));

      }

      if (bizTrpe.equals("B")) {
        // ɾ��AA319��
        String status = "K";
        bizActivityLog = bizActivityLogDAO.queryBizActivityLogWuht(bizId,
            bizTrpe, status.toString());
        bizActivityLogDAO.deleteWuht(bizActivityLog);

      } else if (bizTrpe.equals("M")) {
        String status = "B";
        bizActivityLog = bizActivityLogDAO.queryBizActivityLogWuht(bizId,
            bizTrpe, status.toString());
        bizActivityLogDAO.deleteWuht(bizActivityLog);
      } else if (bizTrpe.equals("K")) {
        String status = "G";
        bizActivityLog = bizActivityLogDAO.queryBizActivityLogWuht(bizId,
            bizTrpe, status.toString());
        bizActivityLogDAO.deleteWuht(bizActivityLog);
      } else if (bizTrpe.equals("L")) {
        String status = "G";
        bizActivityLog = bizActivityLogDAO.queryBizActivityLogWuht(bizId,
            bizTrpe, status.toString());
        bizActivityLogDAO.deleteWuht(bizActivityLog);
      } else {

        bizActivityLog = bizActivityLogDAO.queryBizActivityLogWuht_(bizId,
            bizTrpe, bizStatus);

        if (bizActivityLog == null || bizActivityLog.equals("")) {

          throw new BusinessException("");

        } else {

          bizActivityLogDAO.deleteWuht(bizActivityLog);
        }

      }

      // ����BA003��
      BusiLogConst busiLogConst = null;
      int opButton = busiLogConst.BIZLOG_ACTION_REVOCATION;
      int opModel = busiLogConst.OP_MODE_ACCOUNTMANAGE_OPERATIONCHECK;

      this.addhafOperateLog(name, opButton, opModel, bizId, ip, orgId);
    } catch (Exception e) {
      throw new BusinessException("");
    }

  }

  // ������ҵ�񸴺ˡ���������z
  public void checkallOrgHAFAccountFlow(Pagination pagination, String moneyBank)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    // List list = (List)
    // pagination.getQueryCriterions().get("bizchectotlallist");
    BizcheckAF bizcheckAF = null;
    bizcheckAF = findOrgHAFAccountFlowListBydefault(pagination);
    List list = bizcheckAF.getBizchectotlallist();
    PaymentHead paymentHead = null;
    PickHead pickHead = null;
    TranOutHead tranOutHead = null;
    TranInHead tranInHead = null;
    AdjustWrongAccountHead adjustWrongAccountHead = null;
    String name = (String) pagination.getQueryCriterions().get("name");
    String ip = (String) pagination.getQueryCriterions().get("ip");

    for (int i = 0; i < list.size(); i++) {
      // OrgHAFAccountFlow orgHAFAccountFlow = (OrgHAFAccountFlow) list.get(i);
      OrgHAFAccountFlow orgHAFAccountFlow = null;
      BizcheckDTO bizcheckDTO = (BizcheckDTO) list.get(i);
      String orgId = bizcheckDTO.getOrgId();
      String bizId = bizcheckDTO.getBizId();
      String bizTrpe = bizcheckDTO.getBizType();
      String bizStatus = bizcheckDTO.getBizStatus();
      String id = bizcheckDTO.getId();
      try {
        if (bizStatus.equals("3")) {

          orgHAFAccountFlow = orgHAFAccountFlowDAO.queryById(new Integer(id));

          orgHAFAccountFlow.setBizStatus(new BigDecimal(4));
          orgHAFAccountFlow.setCheckPerson(name);
          // ����������ʼ
          if (moneyBank != null) {
            orgHAFAccountFlow.setMoneyBank(moneyBank);
          }
          // ����

          if (bizTrpe.equals("A") || bizTrpe.equals("B") || bizTrpe.equals("C")
              || bizTrpe.equals("M")) {
            paymentHead = paymentHeadDAO.queryById(new Integer(bizId));
            paymentHead.setPayStatus(new Integer(4));
            if (bizTrpe.equals("A")) {
              // ����AA319��
              MonthPaymentBizActivityLog monthPaymentBizActivityLog = new MonthPaymentBizActivityLog();
              monthPaymentBizActivityLog.setBizid(new Integer(bizId));
              monthPaymentBizActivityLog.setAction(new Integer(4));
              monthPaymentBizActivityLog.setOpTime(new Date());
              monthPaymentBizActivityLog.setOperator(name);
              monthPaymentBizActivityLogDAO.insert(monthPaymentBizActivityLog);

              // �ж��Ƿ�����Զ�����
              if (paymentHead.getReserveaC() != null
                  && !paymentHead.getReserveaC().equals("")) {
                boolean flag = true;
                // �ж�������������ҵ�����Ƿ���ڸñ�ҵ��
                for (int j = 0; j < list.size(); j++) {
                  BizcheckDTO temp_bizcheckDTO = (BizcheckDTO) list.get(i);
                  if (temp_bizcheckDTO.getBizId().equals(
                      paymentHead.getReserveaC())) {
                    flag = false;
                    break;
                  }
                }
                if (flag) {
                  OrgHAFAccountFlow temp_orgHAFAccountFlow = orgHAFAccountFlowDAO
                      .queryByBizId_wsh(orgHAFAccountFlow.getReserveaC(), "C");
                  temp_orgHAFAccountFlow.setBizStatus(new BigDecimal(4));
                  temp_orgHAFAccountFlow.setMoneyBank(moneyBank);
                  temp_orgHAFAccountFlow.setOfficeCode(orgHAFAccountFlow
                      .getOfficeCode());

                  PaymentHead temp_paymentHead = paymentHeadDAO
                      .queryById(new Integer(orgHAFAccountFlow.getReserveaC()));
                  temp_paymentHead.setPayStatus(new Integer(4));
                  // ����AA319��
                  OrgExcessPaymentBizActivityLog orgExcessPaymentBizActivityLog = new OrgExcessPaymentBizActivityLog();
                  orgExcessPaymentBizActivityLog.setBizid(new Integer(
                      orgHAFAccountFlow.getReserveaC()));
                  orgExcessPaymentBizActivityLog.setAction(new Integer(4));
                  orgExcessPaymentBizActivityLog.setOpTime(new Date());
                  orgExcessPaymentBizActivityLog.setOperator(name);
                  orgExcessPaymentBizActivityLogDAO
                      .insert(orgExcessPaymentBizActivityLog);
                }
              }
            }
            if (bizTrpe.equals("B")) {
              // ����AA319��
              EmpAddPayBizActivityLog empAddPayBizActivityLog = new EmpAddPayBizActivityLog();
              empAddPayBizActivityLog.setBizid(new Integer(bizId));
              empAddPayBizActivityLog.setAction(new Integer(4));
              empAddPayBizActivityLog.setOpTime(new Date());
              empAddPayBizActivityLog.setOperator(name);
              empAddPayBizActivityLogDAO.insert(empAddPayBizActivityLog);
            }
            if (bizTrpe.equals("M")) {
              // ����AA319��
              OrgAddPayBizActivityLog orgAddPayBizActivityLog = new OrgAddPayBizActivityLog();
              orgAddPayBizActivityLog.setBizid(new Integer(bizId));
              orgAddPayBizActivityLog.setAction(new Integer(4));
              orgAddPayBizActivityLog.setOpTime(new Date());
              orgAddPayBizActivityLog.setOperator(name);
              orgAddPayBizActivityLogDAO.insert(orgAddPayBizActivityLog);
            }
            if (bizTrpe.equals("C")) {
              // ����AA319��
              OrgExcessPaymentBizActivityLog orgExcessPaymentBizActivityLog = new OrgExcessPaymentBizActivityLog();
              orgExcessPaymentBizActivityLog.setBizid(new Integer(bizId));
              orgExcessPaymentBizActivityLog.setAction(new Integer(4));
              orgExcessPaymentBizActivityLog.setOpTime(new Date());
              orgExcessPaymentBizActivityLog.setOperator(name);
              orgExcessPaymentBizActivityLogDAO
                  .insert(orgExcessPaymentBizActivityLog);
              // �ж��Ƿ�����Զ�����
              if (paymentHead.getReserveaC() != null
                  && !paymentHead.getReserveaC().equals("")) {
                boolean flag = true;
                // �ж�������������ҵ�����Ƿ���ڸñ�ҵ��
                for (int j = 0; j < list.size(); j++) {
                  BizcheckDTO temp_bizcheckDTO = (BizcheckDTO) list.get(i);
                  if (temp_bizcheckDTO.getBizId().equals(
                      paymentHead.getReserveaC())) {
                    flag = false;
                    break;
                  }
                }
                if (flag) {
                  OrgHAFAccountFlow temp_orgHAFAccountFlow = orgHAFAccountFlowDAO
                      .queryByBizId_wsh(orgHAFAccountFlow.getReserveaC(), "A");
                  temp_orgHAFAccountFlow.setBizStatus(new BigDecimal(4));
                  temp_orgHAFAccountFlow.setMoneyBank(moneyBank);
                  temp_orgHAFAccountFlow.setOfficeCode(orgHAFAccountFlow
                      .getOfficeCode());

                  PaymentHead temp_paymentHead = paymentHeadDAO
                      .queryById(new Integer(orgHAFAccountFlow.getReserveaC()));
                  temp_paymentHead.setPayStatus(new Integer(4));

                  // ����AA319��
                  MonthPaymentBizActivityLog monthPaymentBizActivityLog = new MonthPaymentBizActivityLog();
                  monthPaymentBizActivityLog.setBizid(new Integer(
                      orgHAFAccountFlow.getReserveaC()));
                  monthPaymentBizActivityLog.setAction(new Integer(4));
                  monthPaymentBizActivityLog.setOpTime(new Date());
                  monthPaymentBizActivityLog.setOperator(name);
                  monthPaymentBizActivityLogDAO
                      .insert(monthPaymentBizActivityLog);
                }
              }
            }
          }

          if (bizTrpe.equals("D")) {
            pickHead = pickHeadDAO.queryById(new Integer(bizId));
            pickHead.setPickSatatus(new BigDecimal(4));
            // ����AA319��
            PickBizActivityLog pickBizActivityLog = new PickBizActivityLog();
            pickBizActivityLog.setBizid(new Integer(bizId));
            pickBizActivityLog.setAction(new Integer(4));
            pickBizActivityLog.setOpTime(new Date());
            pickBizActivityLog.setOperator(name);
            pickBizActivityLogDAO.insert(pickBizActivityLog);
          }
          if (bizTrpe.equals("E")) {
            tranOutHead = tranOutHeadDAO.queryById(new Integer(bizId));
            tranOutHead.setTranStatus(new BigDecimal(4));

            // ����AA319��
            TranOutBizActivityLog tranOutBizActivityLog = new TranOutBizActivityLog();
            tranOutBizActivityLog.setBizid(new Integer(bizId));
            tranOutBizActivityLog.setAction(new Integer(4));
            tranOutBizActivityLog.setOpTime(new Date());
            tranOutBizActivityLog.setOperator(name);
            tranOutBizActivityLogDAO.insert(tranOutBizActivityLog);
          }
          if (bizTrpe.equals("F")) {
            tranInHead = tranInHeadDAO.queryByIdWuht(new Integer(bizId));
            tranInHead.setTranStatus(new BigDecimal(4));

            // ����AA319��
            TranInBizActivityLog tranInBizActivityLog = new TranInBizActivityLog();
            tranInBizActivityLog.setBizid(new Integer(bizId));
            tranInBizActivityLog.setAction(new Integer(4));
            tranInBizActivityLog.setOpTime(new Date());
            tranInBizActivityLog.setOperator(name);
            tranInBizActivityLogDAO.insert(tranInBizActivityLog);
          }
          if (bizTrpe.equals("G") || bizTrpe.equals("K") || bizTrpe.equals("L")) {
            adjustWrongAccountHead = adjustWrongAccountHeadDAO
                .queryById(new Integer(bizId));
            adjustWrongAccountHead.setAdjustStatus(new BigDecimal(4));

            // ����AA319��
            ChangeAccountBizActivityLog changeAccountBizActivityLog = new ChangeAccountBizActivityLog();
            changeAccountBizActivityLog.setBizid(new Integer(bizId));
            changeAccountBizActivityLog.setAction(new Integer(4));
            changeAccountBizActivityLog.setOpTime(new Date());
            changeAccountBizActivityLog.setOperator(name);
            changeAccountBizActivityLogDAO.insert(changeAccountBizActivityLog);
          }

          // ����BA003��
          BusiLogConst busiLogConst = null;
          int opButton = busiLogConst.BIZLOG_ACTION_CHECKS;
          int opModel = busiLogConst.OP_MODE_ACCOUNTMANAGE_OPERATIONCHECK;

          this.addhafOperateLog(name, opButton, opModel, bizId, ip, orgId);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  // ������ҵ�񸴺ˡ��������˳���
  public void delcheckallOrgHAFAccountFlow(Pagination pagination)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    EmpAddPayBizActivityLog empAddPayBizActivityLog = new EmpAddPayBizActivityLog();
    OrgHAFAccountFlow orgHAFAccountFlow = null;
    // List list = (List)
    // pagination.getQueryCriterions().get("bizchectotlallist");
    BizcheckAF bizcheckAF = null;
    bizcheckAF = findOrgHAFAccountFlowListBydefault(pagination);
    List list = bizcheckAF.getBizchectotlallist();
    BizActivityLog bizActivityLog = new BizActivityLog();
    PaymentHead paymentHead = null;
    PickHead pickHead = null;
    TranOutHead tranOutHead = null;
    TranInHead tranInHead = null;
    AdjustWrongAccountHead adjustWrongAccountHead = null;
    String name = (String) pagination.getQueryCriterions().get("name");
    String ip = (String) pagination.getQueryCriterions().get("ip");
    for (int i = 0; i < list.size(); i++) {
      // orgHAFAccountFlow = (OrgHAFAccountFlow) list.get(i);
      BizcheckDTO bizcheckDTO = (BizcheckDTO) list.get(i);
      String orgId = bizcheckDTO.getOrgId();
      String bizId = bizcheckDTO.getBizId();
      String bizTrpe = bizcheckDTO.getBizType();
      String bizStatus = bizcheckDTO.getBizStatus();

      String id = bizcheckDTO.getId();
      if (bizStatus.equals("4")) {

        orgHAFAccountFlow = orgHAFAccountFlowDAO.queryById(new Integer(id));
        orgHAFAccountFlow.setBizStatus(new BigDecimal(3));
        // ����������ʼ
        String collectionBankId = queryOrgCollectionBankId(orgId);
        orgHAFAccountFlow.setMoneyBank(collectionBankId);
        String officeCode = orgHAFAccountFlow.getOrg().getOrgInfo()
            .getOfficecode();
        orgHAFAccountFlow.setOfficeCode(officeCode);
        // ����
        if (bizTrpe.equals("A") || bizTrpe.equals("B") || bizTrpe.equals("C")
            || bizTrpe.equals("M")) {
          paymentHead = paymentHeadDAO.queryById(new Integer(bizId));
          paymentHead.setPayStatus(new Integer(3));
          // �ж��Ƿ�����Զ�����
          if (paymentHead.getReserveaC() != null
              && !paymentHead.getReserveaC().equals("")) {
            boolean flag = true;
            // �ж�������������ҵ�����Ƿ���ڸñ�ҵ��
            for (int j = 0; j < list.size(); j++) {
              BizcheckDTO temp_bizcheckDTO = (BizcheckDTO) list.get(i);
              if (temp_bizcheckDTO.getBizId()
                  .equals(paymentHead.getReserveaC())) {
                flag = false;
                break;
              }
            }
            if (flag) {
              PaymentHead temp_paymentHead = null;
              if (bizTrpe.equals("A")) {
                OrgHAFAccountFlow temp_orgHAFAccountFlow = orgHAFAccountFlowDAO
                    .queryByBizId_wsh(orgHAFAccountFlow.getReserveaC(), "C");
                temp_orgHAFAccountFlow.setBizStatus(new BigDecimal(3));
                temp_paymentHead = paymentHeadDAO.queryById(new Integer(
                    orgHAFAccountFlow.getReserveaC()));
                temp_paymentHead.setPayStatus(new Integer(3));
                bizActivityLog = bizActivityLogDAO.queryBizActivityLogWuht_(
                    orgHAFAccountFlow.getReserveaC(), "C", bizStatus);
                bizActivityLogDAO.deleteWuht(bizActivityLog);
              } else if (bizTrpe.equals("C")) {
                OrgHAFAccountFlow temp_orgHAFAccountFlow = orgHAFAccountFlowDAO
                    .queryByBizId_wsh(orgHAFAccountFlow.getReserveaC(), "A");
                temp_orgHAFAccountFlow.setBizStatus(new BigDecimal(3));
                temp_paymentHead = paymentHeadDAO.queryById(new Integer(
                    orgHAFAccountFlow.getReserveaC()));
                temp_paymentHead.setPayStatus(new Integer(3));
                bizActivityLog = bizActivityLogDAO.queryBizActivityLogWuht_(
                    orgHAFAccountFlow.getReserveaC(), "A", bizStatus);
                bizActivityLogDAO.deleteWuht(bizActivityLog);
              }
            }
          }
        }

        if (bizTrpe.equals("D")) {
          pickHead = pickHeadDAO.queryById(new Integer(bizId));
          pickHead.setPickSatatus(new BigDecimal(3));

        }
        if (bizTrpe.equals("E")) {
          tranOutHead = tranOutHeadDAO.queryById(new Integer(bizId));
          tranOutHead.setTranStatus(new BigDecimal(3));

        }
        if (bizTrpe.equals("F")) {
          tranInHead = tranInHeadDAO.queryByIdWuht(new Integer(bizId));
          tranInHead.setTranStatus(new BigDecimal(3));

        }
        if (bizTrpe.equals("G") || bizTrpe.equals("K") || bizTrpe.equals("L")) {
          adjustWrongAccountHead = adjustWrongAccountHeadDAO
              .queryById(new Integer(bizId));
          adjustWrongAccountHead.setAdjustStatus(new BigDecimal(3));

        }
        if (bizTrpe.equals("B")) {
          // ɾ��AA319��
          String status = "K";
          bizActivityLog = bizActivityLogDAO.queryBizActivityLogWuht(bizId,
              bizTrpe, status.toString());
          bizActivityLogDAO.deleteWuht(bizActivityLog);

        } else if (bizTrpe.equals("M")) {
          String status = "B";
          bizActivityLog = bizActivityLogDAO.queryBizActivityLogWuht(bizId,
              bizTrpe, status.toString());
          bizActivityLogDAO.deleteWuht(bizActivityLog);
        } else if (bizTrpe.equals("K")) {
          String status = "G";
          bizActivityLog = bizActivityLogDAO.queryBizActivityLogWuht(bizId,
              bizTrpe, status.toString());
          bizActivityLogDAO.deleteWuht(bizActivityLog);
        } else if (bizTrpe.equals("L")) {
          String status = "G";
          bizActivityLog = bizActivityLogDAO.queryBizActivityLogWuht(bizId,
              bizTrpe, status.toString());
          bizActivityLogDAO.deleteWuht(bizActivityLog);
        } else {

          // ɾ��AA319��
          bizActivityLog = bizActivityLogDAO.queryBizActivityLogWuht_(bizId,
              bizTrpe, bizStatus);

          if (bizActivityLog == null || bizActivityLog.equals("")) {
            throw new BusinessException("");
          } else {
            bizActivityLogDAO.deleteWuht(bizActivityLog);
          }
        }

        // ����BA003��
        BusiLogConst busiLogConst = null;
        int opButton = busiLogConst.BIZLOG_ACTION_REVOCATION;
        int opModel = busiLogConst.OP_MODE_ACCOUNTMANAGE_OPERATIONCHECK;

        this.addhafOperateLog(name, opButton, opModel, bizId, ip, orgId);

      }
    }
  }

  /**
   * ������ˮͷ��id����ͷ�� �б���ʾ
   * 
   * @param id
   * @return AdjustWrongAccountHead
   * @throws BusinessException
   */
  public BizcheckDetailAF findOrgHAFAccountFlowByID(Pagination pagination,
      SecurityInfo securityInfo, ILoanDocNumDesignBS loanDocNumDesignBS)
      throws Exception, BusinessException {
    String headid = (String) pagination.getQueryCriterions().get("headid");
    String print = (String) pagination.getQueryCriterions().get("print");
    String empId = "";
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO
        .queryById(new Integer(headid + ""));
    BizActivityLog bizActivityLog = new BizActivityLog();
    String idd = orgHAFAccountFlow.getBizId().toString();
    String traninId = orgHAFAccountFlow.getOrg().getId().toString();
    String traninName = orgHAFAccountFlow.getOrg().getOrgInfo().getName();
    String biz_type = orgHAFAccountFlow.getBizType();
    String biz_id = orgHAFAccountFlow.getBizId().toString();
    String type = "0";// Ĭ��Ϊ������ʽ��
    String settDate = "";
    BizcheckDetailAF bizcheckDetailAF = new BizcheckDetailAF();
    List list = null;
    list = orgHAFAccountFlowDAO.queryOrgHAFAccountFlowTailList(headid, orderBy,
        order, start, pageSize);

    TranInTail tranInTail = null;
    if (list != null) {
      for (int i = 0; i < list.size(); i++) {// ���ܳ��ֵ����� Ա�����ڸõ�λ��
        EmpHAFAccountFlow empHAFAccountFlow = (EmpHAFAccountFlow) list.get(i);
        empId = empHAFAccountFlow.getEmpId().toString();

        Emp emp = empDAO.queryByCriterions(empHAFAccountFlow.getEmpId()
            .toString(), orgHAFAccountFlow.getOrg().getId().toString());
        empHAFAccountFlow.setEmp(emp);
        if (!empHAFAccountFlow.getCredit().toString().equals("0")) {
          empHAFAccountFlow.setMoney(empHAFAccountFlow.getCredit());
        } else if (!empHAFAccountFlow.getDebit().toString().equals("0")) {
          empHAFAccountFlow.setMoney(empHAFAccountFlow.getDebit());
        } else {
          empHAFAccountFlow.setMoney(new BigDecimal(0.00));
        }
      }
    }
    if ("���".equals(biz_type)) {
      type = "5";
      List lists = new ArrayList();
      String monthstr = "";
      String monthend = "";
      lists = monthPaymentHeadDAO.getPayMonthyqf(orgHAFAccountFlow.getBizId()
          .toString());

      for (int i = 0; i < lists.size(); i++) {
        Object[] obj = (Object[]) lists.get(0);
        monthstr = obj[0].toString();
        monthend = obj[1].toString();
      }
      settDate = monthstr + "-" + monthend;

    }
    if ("��λ����".equals(biz_type)) {
      type = "6";
      List lists = new ArrayList();
      String monthstr = "";
      String monthend = "";
      lists = monthPaymentHeadDAO.getPayMonthbyyqf(orgHAFAccountFlow.getBizId()
          .toString());
      for (int i = 0; i < lists.size(); i++) {
        Object[] obj = (Object[]) lists.get(0);
        monthstr = obj[0].toString();
        monthend = obj[1].toString();
      }
      settDate = monthstr + "-" + monthend;
    }
    if ("ת��".equals(biz_type)) {// ת��
      type = "1";
      String id = orgHAFAccountFlow.getBizId().toString();
      TranOutHead tranOutHead = tranOutHeadDAO.queryById(new Integer(id));

      // TranInHead tranInHead = tranInHeadDAO
      // .queryTranInOrgIdByOutorgId(tranOutHead.getTranOutOrg().getId()
      // .toString());
      // if (tranInHead != null) {
      // bizcheckDetailAF.setTraninId(tranOutHead.getTranInOrg().getId() + "");
      // bizcheckDetailAF.setTraninName(tranOutHead.getTranInOrg().getOrgInfo()
      // .getName());
      // }
      bizcheckDetailAF.setTranoutId(tranOutHead.getTranOutOrg().getId() + "");
      bizcheckDetailAF.setTranoutName(tranOutHead.getTranOutOrg().getOrgInfo()
          .getName());
      bizcheckDetailAF.setList(list);
    } else if ("ת��".equals(biz_type)) {// ת��
      type = "2";
      bizcheckDetailAF.setTraninId(orgHAFAccountFlow.getOrg().getId() + "");
      bizcheckDetailAF.setTraninName(orgHAFAccountFlow.getOrg().getOrgInfo()
          .getName());
      // orderBy
      list = tranInTailDAO.queryTraninListByCriterionsAll_wsh(biz_id, null,
          order, start, securityInfo);
      if (list.size() != 0) {
        for (int i = 0; i < list.size(); i++) {
          tranInTail = (TranInTail) list.get(i);
          BigDecimal money = tranInTail.getPreBalance().add(
              tranInTail.getCurBalance());
          tranInTail.setReserveaA(money.toString());
          BigDecimal interest = tranInTail.getPreInterest().add(
              tranInTail.getCurInterest());
          tranInTail.setReserveaB(interest.toString());
        }
      }
      bizcheckDetailAF.setList(list);
    } else if ("��ȡ".equals(biz_type)) {// ��ȡ
      type = "3";
      bizcheckDetailAF.setTraninId(orgHAFAccountFlow.getOrg().getId() + "");
      bizcheckDetailAF.setTraninName(orgHAFAccountFlow.getOrg().getOrgInfo()
          .getName());
      bizcheckDetailAF.setList(list);
    } else if ("����".equals(biz_type)) {// ����
      OrgExcessPayment orgExcessPayment = orgExcessPaymentDAO
          .queryById(new Integer(idd.toString()));
      Serializable orgId = orgExcessPayment.getOrg().getId().toString();
      BigDecimal banlance = new BigDecimal(0.00);
      banlance = this.queryOrgoverpayBalance(orgId);
      // bizcheckDetailAF.setOrgId(BusiTools.convertSixNumber(orgId.toString()));
      // bizcheckDetailAF.setUnitName(orgExcessPayment.getOrg().getOrgInfo().getName());
      // bizcheckDetailAF.setNoteNum(orgExcessPayment.getNoteNum());
      bizcheckDetailAF.setHeadid(headid);
      bizcheckDetailAF.setBanlance(banlance.toString());
      bizcheckDetailAF.setAmount(orgExcessPayment.getPayMoney().toString());
      bizcheckDetailAF.setReason(orgExcessPayment.getExcessReason());
      bizcheckDetailAF.setOtherList(list);

    } else {
      bizcheckDetailAF.setTraninId(orgHAFAccountFlow.getOrg().getId() + "");
      bizcheckDetailAF.setTraninName(orgHAFAccountFlow.getOrg().getOrgInfo()
          .getName());
      bizcheckDetailAF.setOtherList(list);
    }

    // if(biz_type.equals("����")){
    // bizActivityLog =
    // orgHAFAccountFlowDAO.queryOperatorByBizId_(orgHAFAccountFlow.getBizId() +
    // "");
    // }else if(biz_type.equals("��λ����")){
    // bizActivityLog =
    // orgHAFAccountFlowDAO.queryOperatorByBizIdOrgAdd(orgHAFAccountFlow.getBizId()
    // + "");
    // }else{
    // bizActivityLog =
    // orgHAFAccountFlowDAO.queryOperatorByBizId(orgHAFAccountFlow.getBizId() +
    // "");
    // }

    List lists = orgHAFAccountFlowDAO.queryOrgHAFAccountFlowTailList_(headid);
    pagination.setNrOfElements(lists.size());
    // clearAccountDetailAF.setHeadid(headid);
    bizcheckDetailAF.setDocNum(orgHAFAccountFlow.getDocNum());
    bizcheckDetailAF.setNoteNum(orgHAFAccountFlow.getNoteNum());
    bizcheckDetailAF.setType(type);
    bizcheckDetailAF.setTraninId("0" + traninId);
    bizcheckDetailAF.setTraninName(traninName);
    // bizcheckDetailAF.setOperator(bizActivityLog.getOperator());securityInfo
    // �ж� �û�������

    try {
      String name = loanDocNumDesignBS.getNamePara();

      if (name.equals("1")) {
        bizcheckDetailAF.setOperator(orgHAFAccountFlow.getReserveaA());
        System.out.println("s.." + orgHAFAccountFlow.getReserveaA());
      } else {
        String realName = "";
        realName = securityDAO.queryByUserid(orgHAFAccountFlow.getReserveaA());
        bizcheckDetailAF.setOperator(realName);
      }

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    String collBankid = orgHAFAccountFlow.getOrg().getOrgInfo()
        .getCollectionBankId();
    CollBank collBank = collBankDAO.getCollBankByCollBankid_(collBankid);
    if (print != null) {
      collBankid = orgHAFAccountFlow.getMoneyBank();
      collBank = collBankDAO.getCollBankByCollBankid_(collBankid);
    }
    bizcheckDetailAF.setBank(collBank.getCollBankName());
    bizcheckDetailAF.setSettDate(settDate);
    // biz_type
    bizcheckDetailAF.setBiz_type(biz_type);
    bizcheckDetailAF.setList(list);
    bizcheckDetailAF.setOtherList(lists);

    return bizcheckDetailAF;
  }

  /**
   * ������ˮͷ��id����ͷ�� ��ӡ��ʾ
   * 
   * @param Pagination
   * @param securityInfo
   * @return ClearAccountDetailAF
   * @throws BusinessException
   */
  public BizcheckDetailAF findOrgHAFAccountFlowCellByID(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String headid = (String) pagination.getQueryCriterions().get("headid");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO
        .queryById(new Integer(headid + ""));
    BizActivityLog bizActivityLog = orgHAFAccountFlowDAO
        .queryOperatorByBizId(orgHAFAccountFlow.getBizId() + "");
    String biz_type = orgHAFAccountFlow.getBizType();
    String type = "0";// Ĭ��Ϊ������ʽ��
    BizcheckDetailAF bizcheckDetailAF = new BizcheckDetailAF();
    List list = null;
    list = orgHAFAccountFlowDAO.queryOrgHAFAccountFlowAllTailList(headid,
        orderBy, order, start, pageSize);
    if (list != null) {
      for (int i = 0; i < list.size(); i++) {// ���ܳ��ֵ����� Ա�����ڸõ�λ��
        EmpHAFAccountFlow empHAFAccountFlow = (EmpHAFAccountFlow) list.get(i);

        Emp emp = empDAO.queryByCriterions(empHAFAccountFlow.getEmpId()
            .toString(), orgHAFAccountFlow.getOrg().getId().toString());
        empHAFAccountFlow.setEmp(emp);
      }
    }
    if ("ת��".equals(biz_type)) {// ת��
      type = "1";
      TranOutHead tranOutHead = orgHAFAccountFlowDAO
          .queryTranInOrgIdByBizId(orgHAFAccountFlow.getOrg().getId() + "");

      TranInHead tranInHead = tranInHeadDAO
          .queryTranInOrgIdByOutorgId(tranOutHead.getTranOutOrg().getId()
              .toString());
      if (tranInHead != null) {
        bizcheckDetailAF.setTraninId(tranOutHead.getTranInOrg().getId() + "");
        bizcheckDetailAF.setTraninName(tranOutHead.getTranInOrg().getOrgInfo()
            .getName());
      }
      bizcheckDetailAF.setTranoutId(tranOutHead.getTranOutOrg().getId() + "");
      bizcheckDetailAF.setTranoutName(tranOutHead.getTranOutOrg().getOrgInfo()
          .getName());
      bizcheckDetailAF.setList(list);
    } else if ("ת��".equals(biz_type)) {// ת��
      type = "2";
      bizcheckDetailAF.setTraninId(orgHAFAccountFlow.getOrg().getId() + "");
      bizcheckDetailAF.setTraninName(orgHAFAccountFlow.getOrg().getOrgInfo()
          .getName());
      bizcheckDetailAF.setList(list);
    } else if ("��ȡ".equals(biz_type)) {// ��ȡ
      type = "3";
      bizcheckDetailAF.setTraninId(orgHAFAccountFlow.getOrg().getId() + "");
      bizcheckDetailAF.setTraninName(orgHAFAccountFlow.getOrg().getOrgInfo()
          .getName());
      bizcheckDetailAF.setList(list);
    } else {
      bizcheckDetailAF.setTraninId(orgHAFAccountFlow.getOrg().getId() + "");
      bizcheckDetailAF.setTraninName(orgHAFAccountFlow.getOrg().getOrgInfo()
          .getName());
      bizcheckDetailAF.setOtherList(list);
    }
    bizcheckDetailAF.setDocNum(orgHAFAccountFlow.getDocNum());
    bizcheckDetailAF.setNoteNum(orgHAFAccountFlow.getNoteNum());
    bizcheckDetailAF.setType(type);
    bizcheckDetailAF.setOperator(orgHAFAccountFlow.getReserveaA());
    // biz_type
    bizcheckDetailAF.setBiz_type(biz_type);

    return bizcheckDetailAF;
  }

  // //����BA003��
  public void addhafOperateLog(String name, int opButton, int opModel,
      String pkid, String ip, String orgid) throws BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    HafOperateLog hafOperateLog = new HafOperateLog();
    BusiLogConst busiLogConst = null;

    try {
      // ����BA003��
      hafOperateLog
          .setOpSys(new Integer(busiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(Integer.toString(opModel));
      hafOperateLog.setOpButton(Integer.toString(opButton));
      hafOperateLog.setOpBizId(new Integer(pkid));
      hafOperateLog.setOperator(name);
      hafOperateLog.setOpIp(ip);
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOrgId(new Integer(orgid));
      hafOperateLogDAO.insert(hafOperateLog);

    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw new BusinessException("");
      }
    }

  }

  public OrgHAFAccountFlow findOrgHAFAccountFlowByID_(Integer id)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    OrgHAFAccountFlow orgHAFAccountFlow = null;

    return orgHAFAccountFlow = orgHAFAccountFlowDAO.queryById(id);
  }

  // ��ѯ��λ�������
  public BigDecimal queryOrgoverpayBalance(Serializable orgid)
      throws BusinessException {
    // TODO Auto-generated method stub
    BigDecimal orgoverpayBalance = new BigDecimal(0.00);
    if ((orgid != null)) {
      List list = orgHAFAccountFlowExcessPaymentDAO
          .queryOrgoverpayHAFAccountFlow(orgid, new Integer(5));
      for (int i = 0; i < list.size(); i++) {
        OrgHAFAccountFlowExcessPayment orgHAFAccountFlowExcessPayment = (OrgHAFAccountFlowExcessPayment) list
            .get(i);
        orgoverpayBalance = orgoverpayBalance
            .add(orgHAFAccountFlowExcessPayment.getCredit().subtract(
                orgHAFAccountFlowExcessPayment.getDebit()));
      }
      List lists = orgHAFAccountFlowExcessPaymentBalanceEndTransferDAO
          .orgHAFAccountFlowExcessPaymentBalanceEndTransfer(orgid, new Integer(
              5));
      for (int i = 0; i < lists.size(); i++) {
        OrgHAFAccountFlowExcessPaymentBalanceEndTransfer orgHAFAccountFlowExcessPaymentBalanceEndTransfer = (OrgHAFAccountFlowExcessPaymentBalanceEndTransfer) lists
            .get(i);
        orgoverpayBalance = orgoverpayBalance
            .add(orgHAFAccountFlowExcessPaymentBalanceEndTransfer
                .getCredit()
                .subtract(
                    orgHAFAccountFlowExcessPaymentBalanceEndTransfer.getDebit()));
      }
    }

    return orgoverpayBalance;
  }

  public void setOrgHAFAccountFlowExcessPaymentDAO(
      OrgHAFAccountFlowExcessPaymentDAO orgHAFAccountFlowExcessPaymentDAO) {
    this.orgHAFAccountFlowExcessPaymentDAO = orgHAFAccountFlowExcessPaymentDAO;
  }

  public void setOrgExcessPaymentDAO(OrgExcessPaymentDAO orgExcessPaymentDAO) {
    this.orgExcessPaymentDAO = orgExcessPaymentDAO;
  }

  public void setOrgHAFAccountFlowExcessPaymentBalanceEndTransferDAO(
      OrgHAFAccountFlowExcessPaymentBalanceEndTransferDAO orgHAFAccountFlowExcessPaymentBalanceEndTransferDAO) {
    this.orgHAFAccountFlowExcessPaymentBalanceEndTransferDAO = orgHAFAccountFlowExcessPaymentBalanceEndTransferDAO;
  }

  public void setTranInTailDAO(TranInTailDAO tranInTailDAO) {
    this.tranInTailDAO = tranInTailDAO;
  }

  // ����AA101������ID���ض�Ӧҵ���ҵ��ID
  public String queryBizIDByFlowID(String flowID) throws Exception {
    // TODO Auto-generated method stub
    String bizID = "";
    bizID = orgHAFAccountFlowDAO.queryBizIDByFlowID_WL(flowID);
    return bizID;
  }

  /*
   * ��ѯ��λ���ڰ��´��Ƿ�����޸Ĵ�����еĲ�������1.�����޸ġ�0.�������޸� (non-Javadoc)
   * 
   * @see org.xpup.hafmis.syscollection.accounthandle.bizcheck.bsinterface.IBizcheckBS#queruIsBankModify(java.lang.String)
   */
  public String queruIsBankModify(String orgId) throws Exception {
    // TODO Auto-generated method stub
    OfficePara officePara = new OfficePara();
    try {
      Org org = new Org();
      org = orgDAO.queryById(new Integer(orgId));
      if (officeParaDAO.queryOfficeParaByOffice(
          org.getOrgInfo().getOfficecode()).size() != 0) {
        officePara = (OfficePara) officeParaDAO.queryOfficeParaByOffice(
            org.getOrgInfo().getOfficecode()).get(0);
      }

    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return officePara.getParamValue();
  }

  /*
   * ��ѯ�������˵ĵ�λ���ڰ��´��ĸ��� ��ѯ����Ϊ��λ��ŵ��ַ��� (non-Javadoc)
   * 
   * @see org.xpup.hafmis.syscollection.accounthandle.bizcheck.bsinterface.IBizcheckBS#findOfficeCount_wsh(java.lang.String)
   */
  public int findOfficeCount_wsh(String orgIds) throws Exception {
    // TODO Auto-generated method stub
    int count = 0;
    try {
      count = orgDAO.findOfficeCount_wsh(new Integer(orgIds));
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return count;
  }

  /*
   * ��ѯ��λ��� ��ѯ����Ϊ��ˮ������ (non-Javadoc)
   * 
   * @see org.xpup.hafmis.syscollection.accounthandle.bizcheck.bsinterface.IBizcheckBS#findOfficeCount_wsh(java.lang.String)
   */
  public String queryOrgId(String id) throws Exception {
    // TODO Auto-generated method stub
    String orgId = "";
    try {
      orgId = orgHAFAccountFlowDAO.queryOrgID(new Integer(id));
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return orgId;
  }

  /*
   * ��ѯ��λ�Ĺ鼯���� ��ѯ����Ϊ��λ��ŵ��ַ��� (non-Javadoc)
   * 
   * @see org.xpup.hafmis.syscollection.accounthandle.bizcheck.bsinterface.IBizcheckBS#findOfficeCount_wsh(java.lang.String)
   */
  public String queryOrgCollectionBankId(String orgId) throws Exception {
    // TODO Auto-generated method stub
    String collectionBankId = "";
    try {
      collectionBankId = orgDAO.queryOrgCollectionBankId(new Integer(orgId));
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return collectionBankId;
  }

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  public String isTansInFive(String flowID) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    String transInStatus = "";
    try {
      transInStatus = orgHAFAccountFlowDAO.isTansInFive(new Integer(flowID));
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return transInStatus;
  }

  public String queryAA306ReserveaA(String doc_num, String note_num)
      throws Exception, BusinessException {
    return orgHAFAccountFlowDAO.queryAA306ReserveaA(doc_num, note_num);
  }

  public String queryAA306ReserveaB(String doc_num, String note_num)
      throws Exception, BusinessException {
    return orgHAFAccountFlowDAO.queryAA306ReserveaB(doc_num, note_num);
  }
}
