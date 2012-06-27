package org.xpup.hafmis.syscollection.paymng.paysure.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.AddPayTailDAO;
import org.xpup.hafmis.syscollection.common.dao.BizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.CollParaDAO;
import org.xpup.hafmis.syscollection.common.dao.DocNumCancelDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpAddPayDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.MonthPaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.MonthPaymentTailDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgAddPayDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgExcessPaymentBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowExcessPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowOrgAddPayDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowOverduePaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.PaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.PickHeadDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.AddPayTail;
import org.xpup.hafmis.syscollection.common.domain.entity.BizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpAddPayBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentHead;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgAddPay;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgAddPayBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgExcessPaymentBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowExcessPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowOrgAddPay;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowOverduePayment;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.PaymentHead;
import org.xpup.hafmis.syscollection.common.domain.entity.PickHead;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.form.OrgaddpayTaAF;
import org.xpup.hafmis.syscollection.paymng.paysure.bsinterface.IPaymentHeadBS;
import org.xpup.hafmis.syscollection.paymng.paysure.dto.AddPayTailDTO;
import org.xpup.hafmis.syscollection.paymng.paysure.form.PaymentAF;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;

/**
 * @author ����� 2007-06-28
 */
public class PaymentHeadBS implements IPaymentHeadBS {

  private Serializable serializable = "";

  private Serializable aa102Id = "";

  private Serializable aa319Id = "";

  private Serializable ba003Id = "";

  /**
   * SPRING ��עDAO
   */
  private CollParaDAO collParaDAO = null;
  private PaymentHeadDAO paymentHeadDAO = null;

  private OrgHAFAccountFlowOrgAddPayDAO orgHAFAccountFlowOrgAddPayDAO = null;

  private MonthPaymentTailDAO monthPaymentTailDAO = null;

  private MonthPaymentHeadDAO monthPaymentHeadDAO = null;

  private EmpHAFAccountFlowDAO empHAFAccountFlowDAO = null;

  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO = null;

  private PickHeadDAO pickHeadDAO = null;

  private BizActivityLogDAO bizActivityLogDAO = null;

  private OrgHAFAccountFlowExcessPaymentDAO orgHAFAccountFlowExcessPaymentDAO = null;

  private OrgExcessPaymentBizActivityLogDAO orgExcessPaymentBizActivityLogDAO = null;

  private EmpAddPayDAO empAddPayDAO = null;

  private EmpDAO empDAO = null;

  private OrgAddPayDAO orgAddPayDAO = null;

  private OrgHAFAccountFlowOverduePaymentDAO orgHAFAccountFlowOverduePaymentDAO = null;

  private AddPayTailDAO addPayTailDAO = null;

  private OrgHAFAccountFlowPaymentDAO orgHAFAccountFlowPaymentDAO = null;

  private HafOperateLogDAO hafOperateLogDAO = null;

  private DocNumCancelDAO docNumCancelDAO = null;

  public void setDocNumCancelDAO(DocNumCancelDAO docNumCancelDAO) {
    this.docNumCancelDAO = docNumCancelDAO;
  }

  public void setOrgAddPayDAO(OrgAddPayDAO orgAddPayDAO) {
    this.orgAddPayDAO = orgAddPayDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }

  public void setOrgHAFAccountFlowPaymentDAO(
      OrgHAFAccountFlowPaymentDAO orgHAFAccountFlowPaymentDAO) {
    this.orgHAFAccountFlowPaymentDAO = orgHAFAccountFlowPaymentDAO;
  }

  public void setAddPayTailDAO(AddPayTailDAO addPayTailDAO) {
    this.addPayTailDAO = addPayTailDAO;
  }

  public void setOrgHAFAccountFlowOverduePaymentDAO(
      OrgHAFAccountFlowOverduePaymentDAO orgHAFAccountFlowOverduePaymentDAO) {
    this.orgHAFAccountFlowOverduePaymentDAO = orgHAFAccountFlowOverduePaymentDAO;
  }

  public void setEmpAddPayDAO(EmpAddPayDAO empAddPayDAO) {
    this.empAddPayDAO = empAddPayDAO;
  }

  public void setOrgExcessPaymentBizActivityLogDAO(
      OrgExcessPaymentBizActivityLogDAO orgExcessPaymentBizActivityLogDAO) {
    this.orgExcessPaymentBizActivityLogDAO = orgExcessPaymentBizActivityLogDAO;
  }

  public void setOrgHAFAccountFlowExcessPaymentDAO(
      OrgHAFAccountFlowExcessPaymentDAO orgHAFAccountFlowExcessPaymentDAO) {
    this.orgHAFAccountFlowExcessPaymentDAO = orgHAFAccountFlowExcessPaymentDAO;
  }

  public void setBizActivityLogDAO(BizActivityLogDAO bizActivityLogDAO) {
    this.bizActivityLogDAO = bizActivityLogDAO;
  }

  public void setPickHeadDAO(PickHeadDAO pickHeadDAO) {
    this.pickHeadDAO = pickHeadDAO;
  }

  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }

  public void setMonthPaymentHeadDAO(MonthPaymentHeadDAO monthPaymentHeadDAO) {
    this.monthPaymentHeadDAO = monthPaymentHeadDAO;
  }

  public void setPaymentHeadDAO(PaymentHeadDAO paymentHeadDAO) {
    this.paymentHeadDAO = paymentHeadDAO;
  }

  public void setOrgHAFAccountFlowOrgAddPayDAO(
      OrgHAFAccountFlowOrgAddPayDAO orgHAFAccountFlowOrgAddPayDAO) {
    this.orgHAFAccountFlowOrgAddPayDAO = orgHAFAccountFlowOrgAddPayDAO;
  }

  public void setMonthPaymentTailDAO(MonthPaymentTailDAO monthPaymentTailDAO) {
    this.monthPaymentTailDAO = monthPaymentTailDAO;
  }

  public void setEmpHAFAccountFlowDAO(EmpHAFAccountFlowDAO empHAFAccountFlowDAO) {
    this.empHAFAccountFlowDAO = empHAFAccountFlowDAO;
  }

  /**
   * ����������ѯaa301
   */
  public PaymentHead findPaymentListById(Integer id) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    return paymentHeadDAO.queryById(id);
  }

  /**
   * ����paymentHead.id��ѯaa302
   */
  public List findMonthPaymentHeadById(Integer id) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    return monthPaymentHeadDAO.queryByPaymentHeadId(id);
  }

  /**
   * ����paymentHead.id��ѯaa304
   */
  public List findAddPayTailByPaymentId(String id) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    return addPayTailDAO.queryByHeadId(new Integer(id));
  }

  /**
   * ����MothpaymentHead.id��ѯaa303
   */
  public List findMonthPaymentTailById(Integer id) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    Serializable sid = id;
    return monthPaymentTailDAO.queryByMonthPaymentHeadId(sid);
  }

  /**
   * ����paymentheadId��ѯaa303 sumPay
   */
  public List findMonthPaymentTailSumPayByEmpId(Integer id) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    return monthPaymentTailDAO.querySumPayByPaymentHeadId(id);
  }

  /**
   * ����orgId������Ϊ���ˣĲ�ѯaa101
   */
  public PaymentAF findOrgHAFAccountFlowByIdAndType(String id, String type)
      throws Exception {
    BigDecimal balance = new BigDecimal(0);
    PaymentAF paymentAF = new PaymentAF();
    if (type.equals("��λ����")) {
      type = "C";
    }
    OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO
        .queryOrgHAFAccountFlowByIdAndType(id, type);
    if (orgHAFAccountFlow != null) {
      BigDecimal debit = orgHAFAccountFlow.getDebit();// �跽
      BigDecimal credit = orgHAFAccountFlow.getCredit();// ����
      if (debit.compareTo(credit) == 1) {// �跽>����
        balance = debit.subtract(credit);// �������
      } else if (debit.compareTo(credit) == -1) {
        balance = credit.subtract(debit);
      } else if (debit.compareTo(credit) == 0) {
        balance = new BigDecimal(0);
      }
      paymentAF.setBalance(balance);
    }
    return paymentAF;
  }

  /**
   * ����������ѯ(pagination)
   */
  public PaymentAF findPaymentListByPaymentFindAF(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    PaymentAF paymentAF = new PaymentAF();

    BigDecimal sumPayMoney = new BigDecimal(0);
    PaymentHead paymentHead = null;
    try {
      String orgId = (String) pagination.getQueryCriterions().get("orgId");
      String orgName = (String) pagination.getQueryCriterions().get("orgName");
      String noteNum = (String) pagination.getQueryCriterions().get("noteNum");
      String docNum = (String) pagination.getQueryCriterions().get("docNum");
      String payMoney = (String) pagination.getQueryCriterions()
          .get("payMoney");
      String settDate = (String) pagination.getQueryCriterions()
          .get("settDate");
      String settDate1 = (String) pagination.getQueryCriterions()
      .get("settDate1");
      String Type = (String) pagination.getQueryCriterions().get("payType");
      if (Type != null && Type.equals("1")) {
        Type = "A";
      }
      if (Type != null && Type.equals("2")) {
        Type = "B";
      }
      if (Type != null && Type.equals("3")) {
        Type = "C";
      }
      if (Type != null && Type.equals("4")) {
        Type = "D";
      }
      String payType = Type;
      Integer peyStatus = (Integer) pagination.getQueryCriterions().get(
          "payStatus");

      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      String orderBy = (String) pagination.getOrderBy();
      String order = pagination.getOrderother();

      list = paymentHeadDAO.queryByCondition(orgId, orgName, docNum, noteNum,
          payMoney, settDate,settDate1,  payType, peyStatus, start, pageSize, orderBy,
          order, securityInfo);

      // ҵ��״̬ת��
      if (list != null) {

        for (int i = 0; i < list.size(); i++) {
          paymentHead = (PaymentHead) list.get(i);
          paymentHead.setPayStatus_(BusiTools.getBusiValue(Integer.parseInt(""
              + paymentHead.getPayStatus()), BusiConst.BUSINESSSTATE));
        }

      }

      if (list.size() != 0) {
        // ����������,�ϼƽ��
        List lista = new ArrayList();
        lista = paymentHeadDAO.queryPaymentCountByCriterions2(orgId, orgName,
            docNum, noteNum, payMoney, settDate, settDate1, payType, peyStatus, start,
            pageSize, orderBy, order, securityInfo);
        for (int j = 0; j < lista.size(); j++) {
          paymentHead = (PaymentHead) lista.get(j);
          sumPayMoney = sumPayMoney.add(paymentHead.getPayMoney());
        }
        paymentAF.setSumPayMoney(sumPayMoney);
        int count = lista.size();
        pagination.setNrOfElements(count);
      } else {
        int count = 0;
        pagination.setNrOfElements(count);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    paymentAF.setList(list);
    return paymentAF;
  }

  /**
   * Ĭ�ϲ�ѯaa301 pay_status=2 or ��
   */
  public PaymentAF findPaymentListBydefault(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    String orgId = null;
    String orgName = null;
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    String orderBy = (String) pagination.getOrderBy();
    String order = pagination.getOrderother();
    List list = new ArrayList();
    BigDecimal sumPayMoney = new BigDecimal(0);
    PaymentAF paymentAF = new PaymentAF();
    list = paymentHeadDAO.defaultQuery(start, pageSize, orderBy, order,
        securityInfo);
    // ת��ҵ��״̬
    if (list != null) {
      for (int i = 0; i < list.size(); i++) {
        PaymentHead paymentHead = (PaymentHead) list.get(i);
        paymentHead.setPayStatus_(BusiTools.getBusiValue(Integer.parseInt(""
            + paymentHead.getPayStatus()), BusiConst.BUSINESSSTATE));
      }

    }
    if (list.size() != 0) {
      // ��������,�ϼƽ��
      List listb = new ArrayList();
      listb = paymentHeadDAO.queryPaymentCountByCriterions(securityInfo);
      for (int k = 0; k < listb.size(); k++) {
        PaymentHead paymentHead = (PaymentHead) listb.get(k);
        sumPayMoney = sumPayMoney.add(paymentHead.getPayMoney());
      }
      paymentAF.setSumPayMoney(sumPayMoney);
      int count = listb.size();
      pagination.setNrOfElements(count);
    } else {
      int count = 0;
      pagination.setNrOfElements(count);
    }
    paymentAF.setList(list);
    return paymentAF;
  }

  /**
   * ͳһ��ѯ
   */
  public PaymentAF queryPaymentListBydefault(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = new ArrayList();
    PaymentAF paymentAF = new PaymentAF();
    BigDecimal sumPayMoney = new BigDecimal(0);
    PaymentHead paymentHead = null;
    List list1=new ArrayList();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    String orderBy = (String) pagination.getOrderBy();
    String order = pagination.getOrderother();
    String key = (String) pagination.getQueryCriterions().get("condition");
    Integer tempCount= new Integer(0);
    int empCount=0;
    if(key != null){// ��������ѯ
      String orgId = (String) pagination.getQueryCriterions().get("orgId");
      String orgName = (String) pagination.getQueryCriterions().get("orgName");
      String noteNum = (String) pagination.getQueryCriterions().get("noteNum");
      String docNum = (String) pagination.getQueryCriterions().get("docNum");
      String payMoney = (String) pagination.getQueryCriterions()
          .get("payMoney");
      String settDate = (String) pagination.getQueryCriterions()
          .get("settDate");
      String settDate1 = (String) pagination.getQueryCriterions()
      .get("settDate1");
      String Type = (String) pagination.getQueryCriterions().get("payType");
      if (Type != null && Type.equals("1")) {
        Type = "A";
      }
      if (Type != null && Type.equals("2")) {
        Type = "B";
      }
      if (Type != null && Type.equals("3")) {
        Type = "C";
      }
      if (Type != null && Type.equals("4")) {
        Type = "D";
      }
      String payType = Type;
      Integer peyStatus = null;
      if(pagination.getQueryCriterions().get("payStatus")!=null){
        Object sty= pagination.getQueryCriterions().get(
          "payStatus");
        peyStatus = new Integer(sty.toString());
      }
      list = paymentHeadDAO.queryByCondition(orgId, orgName, docNum, noteNum,
          payMoney, settDate, settDate1, payType, peyStatus, start, pageSize, orderBy,
          order, securityInfo);
      // ҵ��״̬ת��
      if (list != null) {
        for (int i = 0; i < list.size(); i++) {
          paymentHead = (PaymentHead) list.get(i);
          paymentHead.setPayStatus_(BusiTools.getBusiValue(Integer.parseInt(""
              + paymentHead.getPayStatus()), BusiConst.BUSINESSSTATE));
          if(paymentHead.getMinMaxMonth().equals("-")){
            paymentHead.setMinMaxMonth("");
          }
        }
      }

      if (list.size() != 0) {
        // ����������,�ϼƽ��
        List lista = new ArrayList();
        lista = paymentHeadDAO.queryPaymentCountByCriterions2(orgId, orgName,
            docNum, noteNum, payMoney, settDate,settDate1, payType, peyStatus, start,
            pageSize, orderBy, order, securityInfo);
        for (int j = 0; j < list.size(); j++) {
          paymentHead = (PaymentHead) list.get(j);
          sumPayMoney = sumPayMoney.add(paymentHead.getPayMoney());
          tempCount= new Integer(0);
          if(!paymentHead.getPayType_().equals("D")&&!paymentHead.getPayType_().equals("C"))
          {
            tempCount=monthPaymentTailDAO.queryPaymentPersonCountsYQF(paymentHead.getId());
            paymentHead.setEmpCount(tempCount);
          }
          if(paymentHead.getPayType_().equals("C"))
          {
            tempCount=monthPaymentTailDAO.queryAddPaymentPersonCounts(paymentHead.getId());
            paymentHead.setEmpCount(tempCount);
          }
          empCount=empCount+tempCount.intValue();
          list1.add(paymentHead);
        }
        paymentAF.setSumPayMoney(sumPayMoney);
        int count = lista.size();
        pagination.setNrOfElements(count);
      } else {
        int count = 0;
        pagination.setNrOfElements(count);
      }
    }else{// Ĭ�ϲ�ѯ
     
      list = paymentHeadDAO.defaultQuery(start, pageSize, orderBy, order,
          securityInfo);
      // ת��ҵ��״̬
      if (list != null) {
        for (int i = 0; i < list.size(); i++) {
          paymentHead = (PaymentHead) list.get(i);
          paymentHead.setPayStatus_(BusiTools.getBusiValue(Integer.parseInt(""
              + paymentHead.getPayStatus()), BusiConst.BUSINESSSTATE));   
          if(paymentHead.getMinMaxMonth().equals("-")){
            paymentHead.setMinMaxMonth("");
          }
        }
      }
      if (list.size() != 0) {
        // ��������,�ϼƽ��
        List listb = new ArrayList();
        listb = paymentHeadDAO.queryPaymentCountByCriterions(securityInfo);
        for (int k = 0; k < list.size(); k++) {
          paymentHead = (PaymentHead) list.get(k);
          sumPayMoney = sumPayMoney.add(paymentHead.getPayMoney());
          tempCount= new Integer(0);
          if(!paymentHead.getPayType_().equals("D")&&!paymentHead.getPayType_().equals("C"))
          {
            tempCount=monthPaymentTailDAO.queryPaymentPersonCountsYQF(paymentHead.getId());
            paymentHead.setEmpCount(tempCount);
          }
          if(paymentHead.getPayType_().equals("C"))
          {
            tempCount=monthPaymentTailDAO.queryAddPaymentPersonCounts(paymentHead.getId());
            paymentHead.setEmpCount(tempCount);
          }
          empCount=empCount+tempCount.intValue();
          list1.add(paymentHead);
        }
        paymentAF.setSumPayMoney(sumPayMoney);
        int count = listb.size();
        pagination.setNrOfElements(count);
      } else {
        int count = 0;
        pagination.setNrOfElements(count);
      }
    }
    paymentAF.setList(list1);
    paymentAF.setCount(new Integer(empCount));
    return paymentAF;
  }

  /**
   * ����ȷ��֮��λ���� ��aa101
   */
  public Serializable maintainSureOrgAddPay(PaymentHead paymentHead,
      String bizDate, String docNum,String userName,Integer busicount) {
    
    // String settDate = paymentHead.getSettDate();
    BigDecimal payMoney = paymentHead.getPayMoney();
    String id = paymentHead.getId().toString();
    String noteNum = paymentHead.getNoteNum();
    Org org = paymentHead.getOrg();

    // ���ã�������������
    OrgHAFAccountFlowOrgAddPay orgHAFAccountFlowOrgAddPay = new OrgHAFAccountFlowOrgAddPay();

    orgHAFAccountFlowOrgAddPay.setOrg(org);
    orgHAFAccountFlowOrgAddPay.setDocNum(docNum);
    orgHAFAccountFlowOrgAddPay.setSettDate(bizDate);
    orgHAFAccountFlowOrgAddPay.setDebit(new BigDecimal(0));
    orgHAFAccountFlowOrgAddPay.setCredit(payMoney);
    orgHAFAccountFlowOrgAddPay.setInterest(new BigDecimal(0));
    orgHAFAccountFlowOrgAddPay.setBizId(new BigDecimal(id));
    orgHAFAccountFlowOrgAddPay.setBizStatus(new BigDecimal(3));
    orgHAFAccountFlowOrgAddPay.setNoteNum(noteNum);
    orgHAFAccountFlowOrgAddPay.setIsFinance(new BigDecimal(1));
    orgHAFAccountFlowOrgAddPay.setSummary("��λ����");
    orgHAFAccountFlowOrgAddPay.setReserveaA(userName);
    //����������ʼ
    orgHAFAccountFlowOrgAddPay.setOfficeCode(org.getOrgInfo().getOfficecode());
    orgHAFAccountFlowOrgAddPay.setMoneyBank(org.getOrgInfo().getCollectionBankId());
    //����
    if(busicount != null && !busicount.equals("")){
      orgHAFAccountFlowOrgAddPay.setPersonTotal(busicount);
    }else{
    orgHAFAccountFlowOrgAddPay.setPersonTotal(new Integer(0));
    }
    Serializable idd = orgHAFAccountFlowOrgAddPayDAO
        .insert(orgHAFAccountFlowOrgAddPay);
    if (idd != null) {
      return idd;
    } else {
      return null;
    }
  }

  /**
   * ����ȷ��֮��λ���� ��aa101
   */
  public Serializable maintainSureOrgShelve(PaymentHead paymentHead,
      String bizDate, String docNum,String userName,Integer busicount) throws Exception, BusinessException {

    BigDecimal payMoney = paymentHead.getPayMoney();
    String id = paymentHead.getId().toString();
    String noteNum = paymentHead.getNoteNum();
    Org org = paymentHead.getOrg();
    String reason = paymentHead.getExcessReason();
    // ���ã�������������
    OrgHAFAccountFlowExcessPayment orgHAFAccountFlowExcessPayment = new OrgHAFAccountFlowExcessPayment();

    orgHAFAccountFlowExcessPayment.setOrg(org);
    orgHAFAccountFlowExcessPayment.setDocNum(docNum);
    orgHAFAccountFlowExcessPayment.setSettDate(bizDate);

    if (payMoney.signum() == 1) {
      orgHAFAccountFlowExcessPayment.setDebit(new BigDecimal(0));
      orgHAFAccountFlowExcessPayment.setCredit(payMoney);
    }
    if (payMoney.signum() == -1) {
      orgHAFAccountFlowExcessPayment.setDebit(payMoney.abs());
      orgHAFAccountFlowExcessPayment.setCredit(new BigDecimal(0));
    }
    orgHAFAccountFlowExcessPayment.setInterest(new BigDecimal(0));
    orgHAFAccountFlowExcessPayment.setBizId(new BigDecimal(id));
    orgHAFAccountFlowExcessPayment.setBizStatus(new BigDecimal(3));
    orgHAFAccountFlowExcessPayment.setNoteNum(noteNum);
    orgHAFAccountFlowExcessPayment.setIsFinance(new BigDecimal(1));
    orgHAFAccountFlowExcessPayment.setSummary(reason);
    orgHAFAccountFlowExcessPayment.setDocNum(docNum);
    orgHAFAccountFlowExcessPayment.setReserveaA(userName);
    if(paymentHead.getTmpplaceKind()!=null&&paymentHead.getTmpplaceKind().equals("1")){
      orgHAFAccountFlowExcessPayment.setReserveaB(paymentHead.getTmpplaceKind());
    }
    // �ж��Ƿ���뱸ѡ�ֶ�C
    if (paymentHead.getReserveaA()!=null&&!paymentHead.getReserveaA().equals("")) {
      orgHAFAccountFlowExcessPayment.setReserveaC(paymentHead.getReserveaA());
    }
    //����������ʼ
    orgHAFAccountFlowExcessPayment.setOfficeCode(org.getOrgInfo().getOfficecode());
    orgHAFAccountFlowExcessPayment.setMoneyBank(org.getOrgInfo().getCollectionBankId());
    //����
    if(busicount != null && !busicount.equals("")){
      orgHAFAccountFlowExcessPayment.setPersonTotal(busicount);
    }else{
      orgHAFAccountFlowExcessPayment.setPersonTotal(new Integer(0));
    }

    // ���ʲ��
    Serializable idd = orgHAFAccountFlowExcessPaymentDAO
        .insert(orgHAFAccountFlowExcessPayment);

    if (idd != null) {
      return idd;
    } else {
      return null;
    }
  }

  /**
   * ����ȷ��֮��λ���ɲ�aa102
   */
  public Serializable maintainSureOrgAddPayInsertAA102(Integer empId,
      BigDecimal credit, OrgHAFAccountFlow orgHAFAccountFlow, String addmonth) {

    EmpHAFAccountFlow empHAFAccountFlow = new EmpHAFAccountFlow();

    empHAFAccountFlow.setEmpId(empId);
    empHAFAccountFlow.setOrgHAFAccountFlow(orgHAFAccountFlow);
    empHAFAccountFlow.setDebit(new BigDecimal(0));
    empHAFAccountFlow.setCredit(credit);
    empHAFAccountFlow.setInterest(new BigDecimal(0));
    empHAFAccountFlow.setSummary("��λ����" + " ��������:" + addmonth);

    Serializable idd = empHAFAccountFlowDAO.insert(empHAFAccountFlow);

    if (idd != null) {
      return idd;
    } else {
      return null;
    }
  }

  /**
   * ����ȷ��֮���˲��ɲ�aa102
   */
  public Serializable maintainSureEmpAddPayInsertAA102(Integer empId,
      BigDecimal credit, OrgHAFAccountFlow orgHAFAccountFlow) {

    EmpHAFAccountFlow empHAFAccountFlow = new EmpHAFAccountFlow();

    empHAFAccountFlow.setEmpId(empId);
    empHAFAccountFlow.setOrgHAFAccountFlow(orgHAFAccountFlow);
    empHAFAccountFlow.setDebit(new BigDecimal(0));
    empHAFAccountFlow.setCredit(credit);
    empHAFAccountFlow.setInterest(new BigDecimal(0));
    empHAFAccountFlow.setSummary("���˲���");

    Serializable idd = empHAFAccountFlowDAO.insert(empHAFAccountFlow);

    if (idd != null) {
      return idd;
    } else {
      return null;
    }
  }

  /**
   * ����ȷ��֮������ɲ�aa102
   */
  public Serializable maintainSureFlowPaymentInsertAA102(Integer empId,
      BigDecimal credit, OrgHAFAccountFlow orgHAFAccountFlow) {

    EmpHAFAccountFlow empHAFAccountFlow = new EmpHAFAccountFlow();

    empHAFAccountFlow.setEmpId(empId);
    empHAFAccountFlow.setOrgHAFAccountFlow(orgHAFAccountFlow);
    empHAFAccountFlow.setDebit(new BigDecimal(0));
    empHAFAccountFlow.setCredit(credit);
    empHAFAccountFlow.setInterest(new BigDecimal(0));
    Serializable idd = empHAFAccountFlowDAO.insert(empHAFAccountFlow);

    if (idd != null) {
      return idd;
    } else {
      return null;
    }
  }

  /**
   * ����ȷ��֮��ҵ����־(aa319)
   */
  public Serializable insertBizActivityLog(BizActivityLog bizActivityLog,
      PaymentHead paymentHead, String operatorName) {

    Integer bizid = (Integer) paymentHead.getId();
    bizActivityLog.setAction(new Integer(3));
    bizActivityLog.setBizid(bizid);
    bizActivityLog.setOpTime(new Date());
    bizActivityLog.setOperator(operatorName);
    aa319Id = bizActivityLogDAO.insert(bizActivityLog);
    if (aa319Id != null) {
      return aa319Id;
    } else {
      return null;
    }
  }

  /**
   * ����ȷ��update paymentHead(aa301)
   */
  public void updatePaymentHead(PaymentHead paymentHead, String docNum,
      String bizDate) {

    PaymentHead newPaymentHead = paymentHeadDAO.queryById(new Integer(
        paymentHead.getId().toString()));
    newPaymentHead.setDocNum(docNum);
    // ҵ������
    newPaymentHead.setSettDate(bizDate);
    newPaymentHead.setPayStatus(new Integer(3));
  }

  /**
   * ��������ȷ�ϣ���//����aa301.id = aa101.biz_id��ѯ
   */
  public List findOrgHAFAccountFlowByPaymentHeadId(String id) {

    return orgHAFAccountFlowDAO.queryOrgHAFAccountFlowByBizId(id);
  }
  /**
   * ��������ȷ�ϣ���//����aa301.id = aa101.biz_id type=type��ѯ
   * @param id
   * @param type
   * @return
   */
  public String findOrgHAFByPayIdType(String id,String type){
    return orgHAFAccountFlowDAO.queryOrgHAFAccountFlowByBizIdType(id, type);
  }

  /**
   * ��������ȷ�ϣ���//����aa101.id = aa102.org_flow_id��ѯaa102
   */
  public List findEmpHAFAccountFlowByOrgFlowId(String id) {

    return empHAFAccountFlowDAO.queryEmpHAFAccountFlowListByOrgFlowId(id);
  }

  /**
   * ��������ȷ�ϣ���//����aa301.id = aa319.bizId��ѯaa319
   * 
   * @param id
   * @return
   */
  public BizActivityLog findBizActivityLogByPaymentHeadId(
      PaymentHead paymentHead) {
    String id = paymentHead.getId().toString();
    Integer action = new Integer(3);
    String type = paymentHead.getPayType_();
    return bizActivityLogDAO.queryByBizId(id, action, type);

  }

  public BizActivityLog findBizActivityLogByPaymentHeadId__(
      PaymentHead paymentHead) {
    String id = paymentHead.getId().toString();
    Integer action = new Integer(3);
    String type = "C";
    return bizActivityLogDAO.queryByBizId(id, action, type);

  }

  /**
   * ��������ȷ�ϣ����˲��ɣ�//����aa301.id = aa319.bizId��ѯaa319
   * 
   * @param paymentHead
   * @return
   */
  public BizActivityLog findBizActivityLogByPaymentHeadId_(
      PaymentHead paymentHead) {
    String id = paymentHead.getId().toString();
    Integer action = new Integer(3);
    String type = "K";
    return bizActivityLogDAO.queryByBizId(id, action, type);

  }

  /**
   * ��������ȷ�ϣ���//update aa301 docNum settDate == null payStatus = 2 (ȷ��)
   * 
   * @param paymentHead
   */
  public void updatePayment(PaymentHead paymentHead) {
    PaymentHead newPaymentHead = paymentHeadDAO.queryById(new Integer(
        paymentHead.getId().toString()));
    newPaymentHead.setDocNum(null);
    newPaymentHead.setSettDate(null);
    newPaymentHead.setPayStatus(new Integer(2));
  }

  /**
   * ��������ȷ�ϣ���//��£������� hafOperateLog
   * 
   * @param paymentHead
   */
  public Serializable insertHafOperateLog(String id, Serializable orgId,
      int OpButton, String ip, String operatorName, int OpModel) {
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(1));
    hafOperateLog.setOpModel(new Integer(OpModel).toString());
    hafOperateLog.setOpButton(new Integer(OpButton).toString());
    hafOperateLog.setOpBizId(new Integer(id));
    hafOperateLog.setOpIp(ip);
    hafOperateLog.setOrgId((Integer) orgId);
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOperator(operatorName);
    return hafOperateLogDAO.insert(hafOperateLog);

  }

  /**
   * 
   */

  public List findAddPayTail(Integer paymentHeadId, int start, int pageSize,
      String orderBy, String order) {

    return addPayTailDAO.queryByHeadId(paymentHeadId, start, pageSize, orderBy,
        order);
  }

  /**
   * 
   */
  public List queryByHeadId(Integer paymentHeadId) {
    return addPayTailDAO.queryByHeadId(paymentHeadId);
  }

  /**
   * ����ȷ�� �ж�ҵ������
   */
  public int SureType(PaymentHead paymentHead, String docNum,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    BigDecimal payMoney = paymentHead.getPayMoney();
    String id = paymentHead.getId().toString();
    String noteNum = paymentHead.getNoteNum();
    Org org = paymentHead.getOrg();
    Serializable orgId = paymentHead.getOrg().getId();
    String bizDate = securityInfo.getUserInfo().getBizDate().substring(0, 8);// �������

    String operateName = securityInfo.getUserName();// ����Ա
    String userIp = securityInfo.getUserIp();// ����ԱIP
    String monthMX ="";
    String month1="";
    Integer busicount = new Integer(0);
    List list1 = monthPaymentHeadDAO.queryMonthPaymentHeadLJ(id);
    if(list1!=null && list1.size()!=0){
      MonthPaymentHead monthPaymentHead = (MonthPaymentHead)list1.get(0);
      month1=monthPaymentHead.getPayMonth();
      monthMX = monthPaymentHeadDAO.queryMonth(paymentHead.getOrg().getId(), month1);
    }
    
    
    
    busicount = monthPaymentTailDAO.queryPaymentPersonCountsYQF(new Integer(id));
 
    // TODO Auto-generated method stub
    // -----------------�ж�ҵ�����ͣ�����������������;

    if (paymentHead.getPayType().equals("��λ����")) {

      // update aa301
      this.updatePaymentHead(paymentHead, docNum, bizDate);
      // ��aa101
      serializable = this.maintainSureOrgAddPay(paymentHead, bizDate, docNum,operateName,busicount);
      this.updateRegistrations(serializable.toString(),id, "B");//����aa101�еĵǼ���
      // *********************��aa102********************
      List empSumPaylist = new ArrayList();
      empSumPaylist = this.findMonthPaymentTailSumPayByEmpId(new Integer(id));
      if (!empSumPaylist.equals("") && !empSumPaylist.isEmpty()) {
        for (int i = 0; i < empSumPaylist.size(); i++) {
          Object[] empSumpay = (Object[]) empSumPaylist.get(i);
          Integer empId = new Integer(empSumpay[0].toString());
          BigDecimal sumPay = new BigDecimal(empSumpay[1].toString());
          OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO
              .queryById((Integer) serializable);
          aa102Id = this.maintainSureFlowPaymentInsertAA102(empId, sumPay,
              orgHAFAccountFlow);
        }
      }

      OrgAddPayBizActivityLog orgAddPayBizActivityLog = new OrgAddPayBizActivityLog();
      aa319Id = this.insertBizActivityLog(orgAddPayBizActivityLog, paymentHead,
          operateName);
      int OpButton = BusiLogConst.BIZLOG_ACTION_CONFIRM;
      int OpModel = BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENTCONFIRM;
      ba003Id = this.insertHafOperateLog(id, orgId, OpButton, userIp,
          operateName, OpModel);

      if (aa102Id != null && aa319Id != null && ba003Id != null) {
        return 1;
      } else {
        throw new BusinessException("����ȷ��ʧ�ܣ�");
      }
    }

    if (paymentHead.getPayType().equals("��λ����")) {

      // update aa301
      this.updatePaymentHead(paymentHead, docNum, bizDate);
      // ��aa101
      serializable = this.maintainSureOrgShelve(paymentHead, bizDate, docNum,operateName,busicount);
      this.updateRegistrations(serializable.toString(),id,"C");//����aa101�еĵǼ���
      Integer bizid = (Integer) paymentHead.getId();
      // ��ò���Ա
      // ϵͳ����
      Date date = new java.util.Date();
      // �� bizid������Ա��������ڲ��뵽aa319
      OrgExcessPaymentBizActivityLog orgExcessPaymentBizActivityLog = new OrgExcessPaymentBizActivityLog();
      // bizid = aa306ID
      orgExcessPaymentBizActivityLog.setBizid(bizid);
      // action = 3 (ȷ��)
      orgExcessPaymentBizActivityLog.setAction(new Integer(3));
      // �������
      orgExcessPaymentBizActivityLog.setOpTime(date);
      // ����Ա
      orgExcessPaymentBizActivityLog.setOperator(operateName);
      // ����aa319
      aa319Id = orgExcessPaymentBizActivityLogDAO
          .insert(orgExcessPaymentBizActivityLog);

      int OpButton = BusiLogConst.BIZLOG_ACTION_CONFIRM;
      int OpModel = BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENTCONFIRM;
      ba003Id = this.insertHafOperateLog(id, orgId, OpButton, userIp,
          operateName, OpModel);
      
      /*----�ж��Զ�����ҵ��----*/
      if (paymentHead.getReserveaA()!=null&&!paymentHead.getReserveaA().equals("")) {
        PaymentHead temp_paymentHead = paymentHeadDAO.queryById(new Integer(paymentHead.getReserveaA()));
        
        Integer temp_busicount = new Integer(0);
        temp_busicount = monthPaymentTailDAO.queryPaymentPersonCountsYQF(new Integer(temp_paymentHead.getId().toString()));
        
        // �����Ӧ���ҵ�� update aa301
        this.updatePaymentHead(temp_paymentHead, docNum, bizDate);
        // *********************��aa101********************
        OrgHAFAccountFlowPayment orgHAFAccountFlowPayment = new OrgHAFAccountFlowPayment();

        orgHAFAccountFlowPayment.setOrg(org);

        orgHAFAccountFlowPayment.setDocNum(docNum);

        orgHAFAccountFlowPayment.setSettDate(bizDate);

        orgHAFAccountFlowPayment.setDebit(new BigDecimal(0));

        orgHAFAccountFlowPayment.setCredit(temp_paymentHead.getPayMoney());

        orgHAFAccountFlowPayment.setInterest(new BigDecimal(0));

        orgHAFAccountFlowPayment.setBizId(new BigDecimal(temp_paymentHead.getId().toString()));

        orgHAFAccountFlowPayment.setBizStatus(new BigDecimal(3));

        orgHAFAccountFlowPayment.setNoteNum(noteNum);

        orgHAFAccountFlowPayment.setIsFinance(new BigDecimal(1));

        orgHAFAccountFlowPayment.setSummary(null);
        
        orgHAFAccountFlowPayment.setReserveaA(operateName);
        
        orgHAFAccountFlowPayment.setReserveaC(paymentHead.getId().toString());
        //����������ʼ
        orgHAFAccountFlowPayment.setOfficeCode(org.getOrgInfo().getOfficecode());
        orgHAFAccountFlowPayment.setMoneyBank(org.getOrgInfo().getCollectionBankId());
        //����
        if(temp_busicount!=null && !"".equals(temp_busicount)){
          orgHAFAccountFlowPayment.setPersonTotal(temp_busicount);
        }else{
        orgHAFAccountFlowPayment.setPersonTotal(new Integer(0));
        }

        //AA301 id������Ϊ��A����¼�Ĵ�������
        String temp_payMent = paymentHeadDAO.queryPaymentInfoCounts(orgHAFAccountFlowPayment.getBizId().toString());
        //�д�������
        if(!temp_payMent.equals("")&&!temp_payMent.equals("0")){
          //�������� Ϊ1
          orgHAFAccountFlowPayment.setSpecailType("1");
        }
        serializable = orgHAFAccountFlowPaymentDAO
            .insert(orgHAFAccountFlowPayment);

        // *********************��aa102********************
        List empSumPaylist = new ArrayList();
        empSumPaylist = this.findMonthPaymentTailSumPayByEmpId(new Integer(orgHAFAccountFlowPayment.getBizId().toString()));
        if (!empSumPaylist.equals("") && !empSumPaylist.isEmpty()) {
          for (int i = 0; i < empSumPaylist.size(); i++) {
            Object[] empSumpay = (Object[]) empSumPaylist.get(i);
            Integer empId = new Integer(empSumpay[0].toString());
            BigDecimal sumPay = new BigDecimal(empSumpay[1].toString());
            OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO
                .queryById((Integer) serializable);
            aa102Id = this.maintainSureFlowPaymentInsertAA102(empId, sumPay,
                orgHAFAccountFlow);
          }
        }
        // ��aa319
        MonthPaymentBizActivityLog monthPaymentBizActivityLog = new MonthPaymentBizActivityLog();
        aa319Id = this.insertBizActivityLog(monthPaymentBizActivityLog,
            temp_paymentHead, operateName);
        // ����ba003
        int OpButton1 = BusiLogConst.BIZLOG_ACTION_CONFIRM;
        int OpModel1 = BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENTCONFIRM;
        ba003Id = this.insertHafOperateLog(temp_paymentHead.getId().toString(), orgId, OpButton1, userIp,
            operateName, OpModel1);
      }
      /*----�ж��Զ�����ҵ��--����--*/
      if (serializable != null && aa319Id != null && ba003Id != null) {
        return 1;
      } else {
        throw new BusinessException("����ȷ��ʧ�ܣ�");
      }  
    }

    if (paymentHead.getPayType().equals("���˲���")) {
      busicount = addPayTailDAO.queryPaymentPersonCountsYQF(new Integer(id));
      // ��update��aa301
      this.updatePaymentHead(paymentHead, docNum, bizDate);

      // ****************************��aa101*********************************
      OrgHAFAccountFlowOverduePayment orgHAFAccountFlowOverduePayment = new OrgHAFAccountFlowOverduePayment();

      orgHAFAccountFlowOverduePayment.setOrg(org);

      orgHAFAccountFlowOverduePayment.setDocNum(docNum);

      orgHAFAccountFlowOverduePayment.setSettDate(bizDate);

      orgHAFAccountFlowOverduePayment.setDebit(new BigDecimal(0));

      orgHAFAccountFlowOverduePayment.setCredit(payMoney);

      orgHAFAccountFlowOverduePayment.setInterest(new BigDecimal(0));

      orgHAFAccountFlowOverduePayment.setBizId(new BigDecimal(id));

      orgHAFAccountFlowOverduePayment.setBizStatus(new BigDecimal(3));

      orgHAFAccountFlowOverduePayment.setNoteNum(noteNum);

      orgHAFAccountFlowOverduePayment.setIsFinance(new BigDecimal(1));

      orgHAFAccountFlowOverduePayment.setSummary("���˲���");
      
      orgHAFAccountFlowOverduePayment.setReserveaA(operateName);
      //����������ʼ
      orgHAFAccountFlowOverduePayment.setOfficeCode(org.getOrgInfo().getOfficecode());
      orgHAFAccountFlowOverduePayment.setMoneyBank(org.getOrgInfo().getCollectionBankId());
      //����
      if(busicount != null && !"".equals(busicount)){
        orgHAFAccountFlowOverduePayment.setPersonTotal(busicount);
      }else{
      orgHAFAccountFlowOverduePayment.setPersonTotal(new Integer(0));
      }
      serializable = orgHAFAccountFlowOverduePaymentDAO
          .insert(orgHAFAccountFlowOverduePayment);
      this.updateRegistrations(serializable.toString(),id,"K");//����aa101�еĵǼ���
      // *********************��aa102********************
      // ����aa301��id ���� aa304 MonthPaymentHead
      List empSumPaylist = new ArrayList();
      empSumPaylist = addPayTailDAO.querySumPayByEmpId(new Integer(id));
      if (!empSumPaylist.equals("") && !empSumPaylist.isEmpty()) {
        for (int i = 0; i < empSumPaylist.size(); i++) {
          Object[] empSumpay = (Object[]) empSumPaylist.get(i);
          Integer empId = new Integer(empSumpay[0].toString());
          BigDecimal sumPay = new BigDecimal(empSumpay[1].toString());
          OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO
              .queryById((Integer) serializable);
          aa102Id = this.maintainSureEmpAddPayInsertAA102(empId, sumPay,
              orgHAFAccountFlow);

        }
      }

      // ��aa319
      EmpAddPayBizActivityLog empAddPayBizActivityLog = new EmpAddPayBizActivityLog();
      aa319Id = this.insertBizActivityLog(empAddPayBizActivityLog, paymentHead,
          operateName);

      int OpButton = BusiLogConst.BIZLOG_ACTION_CONFIRM;
      int OpModel = BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENTCONFIRM;
      ba003Id = this.insertHafOperateLog(id, orgId, OpButton, userIp,
          operateName, OpModel);

      if (aa102Id != null && aa319Id != null && ba003Id != null) {
        return 1;
      } else {
        throw new BusinessException("����ȷ��ʧ�ܣ�");
      }
    }

    if (paymentHead.getPayType().equals("���")) {

      // update aa301
      this.updatePaymentHead(paymentHead, docNum, bizDate);
      // *********************��aa101********************
      OrgHAFAccountFlowPayment orgHAFAccountFlowPayment = new OrgHAFAccountFlowPayment();

      orgHAFAccountFlowPayment.setOrg(org);

      orgHAFAccountFlowPayment.setDocNum(docNum);

      orgHAFAccountFlowPayment.setSettDate(bizDate);

      orgHAFAccountFlowPayment.setDebit(new BigDecimal(0));

      orgHAFAccountFlowPayment.setCredit(payMoney);

      orgHAFAccountFlowPayment.setInterest(new BigDecimal(0));

      orgHAFAccountFlowPayment.setBizId(new BigDecimal(id));

      orgHAFAccountFlowPayment.setBizStatus(new BigDecimal(3));

      orgHAFAccountFlowPayment.setNoteNum(noteNum);

      orgHAFAccountFlowPayment.setIsFinance(new BigDecimal(1));

      orgHAFAccountFlowPayment.setSummary(null);
      
      orgHAFAccountFlowPayment.setReserveaA(operateName);
      if (paymentHead.getReserveaA()!=null&&!paymentHead.getReserveaA().equals("")) {
        PaymentHead temp_paymentHead = paymentHeadDAO.queryById(new Integer(paymentHead.getReserveaA()));
        orgHAFAccountFlowPayment.setReserveaC(temp_paymentHead.getId().toString());
      }
      //����������ʼ
      orgHAFAccountFlowPayment.setOfficeCode(org.getOrgInfo().getOfficecode());
      orgHAFAccountFlowPayment.setMoneyBank(org.getOrgInfo().getCollectionBankId());
      //����
      if(busicount!=null && !"".equals(busicount)){
        orgHAFAccountFlowPayment.setPersonTotal(busicount);
      }else{
      orgHAFAccountFlowPayment.setPersonTotal(new Integer(0));
      }

      //AA301 id������Ϊ��A����¼�Ĵ�������
      String temp_payMent = paymentHeadDAO.queryPaymentInfoCounts(id);
      //�д�������
      if(!temp_payMent.equals("")&&!temp_payMent.equals("0")){
        //�������� Ϊ1
        orgHAFAccountFlowPayment.setSpecailType("1");
      }
      serializable = orgHAFAccountFlowPaymentDAO
          .insert(orgHAFAccountFlowPayment);
      this.updateRegistrations(serializable.toString(),id,"A");//����aa101�еĵǼ���
      // *********************��aa102********************
      List empSumPaylist = new ArrayList();
      empSumPaylist = this.findMonthPaymentTailSumPayByEmpId(new Integer(id));
      if (!empSumPaylist.equals("") && !empSumPaylist.isEmpty()) {
        for (int i = 0; i < empSumPaylist.size(); i++) {
          Object[] empSumpay = (Object[]) empSumPaylist.get(i);
          Integer empId = new Integer(empSumpay[0].toString());
          BigDecimal sumPay = new BigDecimal(empSumpay[1].toString());
          OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO
              .queryById((Integer) serializable);
          aa102Id = this.maintainSureFlowPaymentInsertAA102(empId, sumPay,
              orgHAFAccountFlow);
        }
      }

      // ��aa319
      MonthPaymentBizActivityLog monthPaymentBizActivityLog = new MonthPaymentBizActivityLog();
      aa319Id = this.insertBizActivityLog(monthPaymentBizActivityLog,
          paymentHead, operateName);
      // ����ba003
      int OpButton = BusiLogConst.BIZLOG_ACTION_CONFIRM;
      int OpModel = BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENTCONFIRM;
      ba003Id = this.insertHafOperateLog(id, orgId, OpButton, userIp,
          operateName, OpModel);
      
      /*---�ж��Զ�����ҵ��----*/
      if (paymentHead.getReserveaA()!=null&&!paymentHead.getReserveaA().equals("")) {
        PaymentHead temp_paymentHead = paymentHeadDAO.queryById(new Integer(paymentHead.getReserveaA()));
        // update aa301
        this.updatePaymentHead(temp_paymentHead, docNum, bizDate);
        // ��aa101
        serializable = this.maintainSureOrgShelve(temp_paymentHead, bizDate, docNum,operateName,new Integer(0));
        Integer bizid = (Integer) temp_paymentHead.getId();
        // ��ò���Ա
        // ϵͳ����
        Date date = new java.util.Date();
        // �� bizid������Ա��������ڲ��뵽aa319
        OrgExcessPaymentBizActivityLog orgExcessPaymentBizActivityLog = new OrgExcessPaymentBizActivityLog();
        // bizid = aa306ID
        orgExcessPaymentBizActivityLog.setBizid(bizid);
        // action = 3 (ȷ��)
        orgExcessPaymentBizActivityLog.setAction(new Integer(3));
        // �������
        orgExcessPaymentBizActivityLog.setOpTime(date);
        // ����Ա
        orgExcessPaymentBizActivityLog.setOperator(operateName);
        // ����aa319
        aa319Id = orgExcessPaymentBizActivityLogDAO
            .insert(orgExcessPaymentBizActivityLog);

        int OpButton1 = BusiLogConst.BIZLOG_ACTION_CONFIRM;
        int OpModel1 = BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENTCONFIRM;
        ba003Id = this.insertHafOperateLog(bizid.toString(), orgId, OpButton1, userIp,
            operateName, OpModel1);

      }
      /*---�ж��Զ�����ҵ��--����--*/
      if (aa102Id != null && aa319Id != null && ba003Id != null) {
        try{
          if(monthMX!=null&&!"".equals(monthMX)){
            throw new BusinessException("�õ�λ�ñ�ҵ��֮ǰ����Ϊ����ȷ�ϵ�ҵ��");
          }
        }catch(BusinessException e){
          throw new BusinessException("�õ�λ�ñ�ҵ��֮ǰ����Ϊ����ȷ�ϵ�ҵ��");
        }
        return 1;
      } else {
        throw new BusinessException("����ȷ��ʧ�ܣ�");
      }
    }
    return 0;

  }

  /**
   * ��������ȷ��
   */
  public int DelType(PaymentHead paymentHead, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    String settDate = paymentHead.getSettDate();
    BigDecimal payMoney = paymentHead.getPayMoney();
    String id = paymentHead.getId().toString();
    String noteNum = paymentHead.getNoteNum();
    Org org = paymentHead.getOrg();
    String docnum = paymentHead.getDocNum().substring(8);
    String officeCode = "";
    String docNumDocument=collParaDAO.getDocNumDesignPara();
    if(docNumDocument.equals("1")){
      officeCode = paymentHead.getOrg().getOrgInfo().getOfficecode();
    }else{
      officeCode = paymentHead.getOrg().getOrgInfo().getCollectionBankId();
    }
    Serializable orgId = paymentHead.getOrg().getId();

    String operateName = securityInfo.getUserName();// ����Ա
    String userIp = securityInfo.getUserIp();// ����ԱIP

    if (paymentHead.getPayType().equals("��λ����")) {
      String biz_Type = "M";
     try{
      // ��321
      docNumCancelDAO.insertDocNumCancel(docnum, officeCode, settDate
          .substring(0, 6));
      // update aa301
      this.updatePayment(paymentHead);
      // ɾ��aa101
      
      // ���ݲ�ѯaa301.id = aa101.biz_id
//      List orgHAFAccountFlowList = this
//          .findOrgHAFAccountFlowByPaymentHeadId(id);
      
      //AA301 id������Ϊ��A����¼�Ĵ�������
      String temp_payMent = paymentHeadDAO.queryPaymentInfoCounts(id);
      //�������Ų�Ϊ��
      if(!temp_payMent.equals("")){
        //��ʾ����
        throw new BusinessException("��ҵ����ڴ�����Ϣ�������ڴ�ɾ����");
      }
//      OrgHAFAccountFlow orgHAFAccountFlow =  this.findOrgHAFByPayIdType(id, biz_Type);
//      if (orgHAFAccountFlowList.size() != 0) {
//        for (int i = 0; i < orgHAFAccountFlowList.size(); i++) {
//          OrgHAFAccountFlow orgHAFAccountFlow = (OrgHAFAccountFlow) orgHAFAccountFlowList
//              .get(i);
//
//          String aa101Id = orgHAFAccountFlow.getId().toString();
      String aa101Id = this.findOrgHAFByPayIdType(id, biz_Type);
      //����ͷ��aa101IDɾ��β��aa102ȫ��
      empHAFAccountFlowDAO.deleteEmpHAFAccountFlowAll(new Integer(aa101Id));
          // ����aa101.id = aa102.org_flow_id��ѯ
//          List empHAFAccountFlowList = this
//              .findEmpHAFAccountFlowByOrgFlowId(aa101Id);
//
//          if (empHAFAccountFlowList.size() != 0) {
//            for (int j = 0; j < empHAFAccountFlowList.size(); j++) {
//              EmpHAFAccountFlow empHAFAccountFlow = (EmpHAFAccountFlow) empHAFAccountFlowList
//                  .get(j);
//
//              String aa102Id = empHAFAccountFlow.getId().toString();
//              // ɾ����������ɾ��aa102
//              empHAFAccountFlowDAO.deleteById(aa102Id);
//
//            }
//          }

          orgHAFAccountFlowDAO.deleteById(aa101Id);
//        }
//      }
      // ɾ��aa319
      BizActivityLog bizActivityLog = this
          .findBizActivityLogByPaymentHeadId(paymentHead);
      String bizActivityLogId = bizActivityLog.getId().toString();
      bizActivityLogDAO.deleteById(bizActivityLogId);

      // ����ba003
      int OpButton = BusiLogConst.BIZLOG_ACTION_REVOCATION;
      int OpModel = BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENTCONFIRM;
      ba003Id = this.insertHafOperateLog(id, orgId, OpButton, userIp,

      operateName, OpModel);
      if (ba003Id != null) {
        return 1;// ��ת
      } else {
        throw new BusinessException("��������ȷ��ʧ�ܣ�");
      }
     }catch(BusinessException b){
       throw  b;
     }catch(Exception e){
       throw new BusinessException("");
     }
    }

    if (paymentHead.getPayType().equals("��λ����")) {
      String biz_Type = "C";
      try{
      // ��321
      docNumCancelDAO.insertDocNumCancel(docnum, officeCode, settDate
          .substring(0, 6));
      // update aa301
      this.updatePayment(paymentHead);
      // ɾ��aa101
      // ���ݲ�ѯaa301.id = aa101.biz_id
//      List orgHAFAccountFlowList = this
//          .findOrgHAFAccountFlowByPaymentHeadId(id);
//      OrgHAFAccountFlow orgHAFAccountFlow =  this.findOrgHAFByPayIdType(id, biz_Type);
//      if (orgHAFAccountFlowList.size() != 0) {
//        for (int i = 0; i < orgHAFAccountFlowList.size(); i++) {
//          // ѭ��������aa101(ҵ����ˮ) orgHAFAccountFlow����
//          OrgHAFAccountFlow orgHAFAccountFlow = (OrgHAFAccountFlow) orgHAFAccountFlowList
//              .get(i);
//          String aa101Id = orgHAFAccountFlow.getId().toString();
      String aa101Id = this.findOrgHAFByPayIdType(id, biz_Type);
          orgHAFAccountFlowDAO.deleteById(aa101Id);
//        }
//      }
      // ɾ��aa319
      BizActivityLog bizActivityLog = this
          .findBizActivityLogByPaymentHeadId__(paymentHead);
      String bizActivityLogId = bizActivityLog.getId().toString();
      bizActivityLogDAO.deleteById(bizActivityLogId);

      // ����ba003
      int OpButton = BusiLogConst.BIZLOG_ACTION_REVOCATION;
      int OpModel = BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENTCONFIRM;
      ba003Id = this.insertHafOperateLog(id, orgId, OpButton, userIp,
          operateName, OpModel);
      /*---�ж��Զ�����ҵ��----*/
      if (paymentHead.getReserveaA() != null
            && !paymentHead.getReserveaA().equals("")) {
          PaymentHead temp_paymentHead = paymentHeadDAO.queryById(new Integer(paymentHead
              .getReserveaA()));
          String temp_id = temp_paymentHead.getId().toString();
          String temp_biz_Type = "A";
//          String aa101Id = null;
          String aa102Id = null;
          String ba003Id = null;

          this.updatePayment(temp_paymentHead);
          aa101Id = this.findOrgHAFByPayIdType(temp_id, temp_biz_Type);
          
          // ����ͷ��aa101IDɾ��β��aa102ȫ��
          empHAFAccountFlowDAO.deleteEmpHAFAccountFlowAll(new Integer(aa101Id));
          orgHAFAccountFlowDAO.deleteById(aa101Id);

          // ɾ��aa319
          BizActivityLog temp_bizActivityLog = this
              .findBizActivityLogByPaymentHeadId(temp_paymentHead);
          String temp_bizActivityLogId = temp_bizActivityLog.getId().toString();
          bizActivityLogDAO.deleteById(temp_bizActivityLogId);

          // ����ba003
          int OpButton1 = BusiLogConst.BIZLOG_ACTION_REVOCATION;
          int OpModel1 = BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENTCONFIRM;
          ba003Id = this.insertHafOperateLog(temp_id, orgId, OpButton1, userIp,
              operateName, OpModel1).toString();
        }
      /*---�ж��Զ�����ҵ��--����--*/
      if (ba003Id != null) {
        return 1;// ��ת
      } else {
        throw new BusinessException("��������ȷ��ʧ�ܣ�");
      }
      }catch(BusinessException b){
        throw  b;
      }catch(Exception e){
        e.printStackTrace();
        throw new BusinessException("");
      }
    }
    
    if (paymentHead.getPayType().equals("���˲���")) {
      String biz_Type = "B";
      try{
      // ��321
      docNumCancelDAO.insertDocNumCancel(docnum, officeCode, settDate
          .substring(0, 6));
      // update aa301
      this.updatePayment(paymentHead);

      // ɾ��aa101
      // ���ݲ�ѯaa301.id = aa101.biz_id
//      List orgHAFAccountFlowList = this
//          .findOrgHAFAccountFlowByPaymentHeadId(id);
//      OrgHAFAccountFlow orgHAFAccountFlow =  this.findOrgHAFByPayIdType(id, biz_Type);
//      if (orgHAFAccountFlowList.size() != 0) {
//        for (int i = 0; i < orgHAFAccountFlowList.size(); i++) {
//          OrgHAFAccountFlow orgHAFAccountFlow = (OrgHAFAccountFlow) orgHAFAccountFlowList
//              .get(i);

//          String aa101Id = orgHAFAccountFlow.getId().toString();
      String aa101Id = this.findOrgHAFByPayIdType(id, biz_Type);
      
//    ����ͷ��aa101IDɾ��β��aa102ȫ��
          empHAFAccountFlowDAO.deleteEmpHAFAccountFlowAll(new Integer(aa101Id));
          // ����aa101.id = aa102.org_flow_id��ѯ
//          List empHAFAccountFlowList = this
//              .findEmpHAFAccountFlowByOrgFlowId(aa101Id);
//
//          if (empHAFAccountFlowList.size() != 0) {
//            for (int j = 0; j < empHAFAccountFlowList.size(); j++) {
//              EmpHAFAccountFlow empHAFAccountFlow = (EmpHAFAccountFlow) empHAFAccountFlowList
//                  .get(j);
//              String aa102Id = empHAFAccountFlow.getId().toString();
//
//              // ɾ����������ɾ��aa102
//
//              empHAFAccountFlowDAO.deleteById(aa102Id);
//            }
//          }

          orgHAFAccountFlowDAO.deleteById(aa101Id);

//        }
//      }
      // ɾ��aa319
      BizActivityLog bizActivityLog = this
          .findBizActivityLogByPaymentHeadId_(paymentHead);
     
      String bizActivityLogId = bizActivityLog.getId().toString();
      bizActivityLogDAO.deleteById(bizActivityLogId);

      // ����ba003
      int OpButton = BusiLogConst.BIZLOG_ACTION_REVOCATION;
      int OpModel = BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENTCONFIRM;
      ba003Id = this.insertHafOperateLog(id, orgId, OpButton, userIp,
          operateName, OpModel);
      if (ba003Id != null) {
        return 1;// ��ת
      } else {
        throw new BusinessException("��������ȷ��ʧ�ܣ�");
      }
      }catch(Exception e){
        throw new BusinessException("");
      }
    }

    if (paymentHead.getPayType().equals("���")) {
      String biz_Type = "A";
      String aa101Id = null;
      String aa102Id = null;
      String ba003Id = null;
      try{
      // ��321
      try{
      docNumCancelDAO.insertDocNumCancel(docnum, officeCode, settDate
          .substring(0, 6));
      // update aa301
      this.updatePayment(paymentHead);

      // ɾ��aa101
      // ���ݲ�ѯaa301.id = aa101.biz_id
//      List orgHAFAccountFlowList = this
//          .findOrgHAFAccountFlowByPaymentHeadId(id);
//      OrgHAFAccountFlow orgHAFAccountFlow =  this.findOrgHAFByPayIdType(id, biz_Type);
    
//      if (orgHAFAccountFlowList.size() != 0) {
//        for (int i = 0; i < orgHAFAccountFlowList.size(); i++) {
//          OrgHAFAccountFlow orgHAFAccountFlow = (OrgHAFAccountFlow) orgHAFAccountFlowList
//              .get(i);

//          aa101Id = orgHAFAccountFlow.getId().toString();
            aa101Id = this.findOrgHAFByPayIdType(id, biz_Type);

//          ����ͷ��aa101IDɾ��β��aa102ȫ��
                empHAFAccountFlowDAO.deleteEmpHAFAccountFlowAll(new Integer(aa101Id));
          // ����aa101.id = aa102.org_flow_id��ѯ
//          List empHAFAccountFlowList = this
//              .findEmpHAFAccountFlowByOrgFlowId(aa101Id);
//
//          if (empHAFAccountFlowList.size() != 0) {
//            for (int j = 0; j < empHAFAccountFlowList.size(); j++) {
//              EmpHAFAccountFlow empHAFAccountFlow = (EmpHAFAccountFlow) empHAFAccountFlowList
//                  .get(j);
//
//              aa102Id = empHAFAccountFlow.getId().toString();
//
//              // ɾ����������ɾ��aa102
//              empHAFAccountFlowDAO.deleteById(aa102Id);
//
//            }
//          }

          orgHAFAccountFlowDAO.deleteById(aa101Id);
//        }
//      }
      // ɾ��aa319
      BizActivityLog bizActivityLog = this
          .findBizActivityLogByPaymentHeadId(paymentHead);
      String bizActivityLogId = bizActivityLog.getId().toString();
      bizActivityLogDAO.deleteById(bizActivityLogId);

      // ����ba003
      int OpButton = BusiLogConst.BIZLOG_ACTION_REVOCATION;
      int OpModel = BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENTCONFIRM;
      ba003Id = this.insertHafOperateLog(id, orgId, OpButton, userIp,
          operateName, OpModel).toString();
      /*---�ж��Զ�����ҵ��---*/
      if (paymentHead.getReserveaA()!=null&&!paymentHead.getReserveaA().equals("")) {
        PaymentHead temp_paymentHead = paymentHeadDAO.queryById(new Integer(paymentHead.getReserveaA()));
        String temp_biz_Type = "C";
        // update aa301
        this.updatePayment(temp_paymentHead);
        String temp_id = temp_paymentHead.getId().toString();

        String temp_aa101Id = this.findOrgHAFByPayIdType(temp_id, temp_biz_Type);
            orgHAFAccountFlowDAO.deleteById(temp_aa101Id);
        // ɾ��aa319
        BizActivityLog temp_bizActivityLog = this
            .findBizActivityLogByPaymentHeadId__(temp_paymentHead);
        String temp_bizActivityLogId = temp_bizActivityLog.getId().toString();
        bizActivityLogDAO.deleteById(temp_bizActivityLogId);

        // ����ba003
        int OpButton1 = BusiLogConst.BIZLOG_ACTION_REVOCATION;
        int OpModel1 = BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENTCONFIRM;
        Serializable temp_ba003Id = this.insertHafOperateLog(temp_id, orgId, OpButton1, userIp,
            operateName, OpModel1);

      }
      /*---�ж��Զ�����ҵ��--����--*/
      if (aa101Id != null && aa102Id != null && ba003Id != null
          && bizActivityLogId != null) 
        return 1;// ��ת
      }catch(Exception e){
        throw new BusinessException("��������ȷ��ʧ�ܣ�");
      }
      }catch(Exception e){
        throw new BusinessException("�ñ�ҵ������ɳ���,��ȷ��!");
      }
    }

    return 1;
  }

  public PaymentAF findPersonAddPayListByCriterions(Pagination pagination)
      throws Exception {
    // TODO Auto-generated method stub
    PaymentAF paymentAF = new PaymentAF();
    BigDecimal orgAddPaySum = new BigDecimal(0);
    BigDecimal empAddPaySum = new BigDecimal(0);
    BigDecimal addPayAmount = new BigDecimal(0);
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    String orderBy = (String) pagination.getOrderBy();
    String order = pagination.getOrderother();
    List addPayTailList = new ArrayList();
    String paymentHeadId = (String) pagination.getQueryCriterions().get(
        "paymentHeadId");
    addPayTailList = this.findAddPayTail(new Integer(paymentHeadId), start,
        pageSize, orderBy, order);
    for (int i = 0; i < addPayTailList.size(); i++) {
      AddPayTail addPayTail = (AddPayTail) addPayTailList.get(i);
      orgAddPaySum = orgAddPaySum.add(addPayTail.getOrgAddMoney());
      empAddPaySum = empAddPaySum.add(addPayTail.getEmpAddMoney());
      addPayAmount = addPayTail.getOrgAddMoney().add(
          addPayTail.getEmpAddMoney());
      addPayTail.setAddPayAmount(addPayAmount);
    }
    paymentAF.setCount(new Integer(addPayTailList.size()));
    paymentAF.setOrgAddPaySum(orgAddPaySum);
    paymentAF.setEmpAddPaySum(empAddPaySum);
    paymentAF.setOrgEmpPaySum(orgAddPaySum.add(empAddPaySum));
    paymentAF.setList(addPayTailList);
    return paymentAF;
  }

  public PaymentAF findOrgaddpayMX(Pagination pagination) throws Exception {
    // TODO Auto-generated method stub
    // PaymentAF paymentAF = new PaymentAF();
    // int start = pagination.getFirstElementOnPage() - 1;
    // int pageSize = pagination.getPageSize();
    // String orderBy = (String) pagination.getOrderBy();
    // String order = pagination.getOrderother();
    // List list = new ArrayList();
    // String
    // paymentHeadId=(String)pagination.getQueryCriterions().get("paymentHeadId");
    // List list=new ArrayList();
    // List emplist=new ArrayList();
    // MonthPaymentHead monthPaymentHead = new MonthPaymentHead();
    // OrgaddpayTaAF orgaddpayTaAF = new OrgaddpayTaAF();
    // Serializable
    // paymentid=(Serializable)pagination.getQueryCriterions().get("paymentid");
    // String orderBy=(String) pagination.getOrderBy();
    // String order = (String) pagination.getOrderother();
    // int start = pagination.getFirstElementOnPage() - 1;
    // int pageSize = pagination.getPageSize();
    // OrgAddPay orgAddPay = orgAddPayDAO.queryById(new
    // Integer(paymentid.toString()));
    // String orgid = orgAddPay.getOrg().getId().toString();
    // list=monthPaymentTailDAO.queryOrgaddpayEmpListMXLJ(paymentid.toString(),
    // orderBy, order, start, pageSize);
    // if(list.size()>0){
    // for(int i=0;i<list.size();i++){
    // MonthPaymentTail m = (MonthPaymentTail)list.get(i);
    // Emp emp = empDAO.queryByCriterions(m.getEmpId().toString(),orgid);
    // m.setEmp(emp);
    // emplist.add(m);
    // }
    // }
    // int count =
    // monthPaymentTailDAO.queryOrgaddpayEmpListCountMXLJ(paymentid.toString());
    // pagination.setNrOfElements(count);
    // monthPaymentHead =
    // (MonthPaymentHead)monthPaymentHeadDAO.queryMonthPaymentHeadLJ(paymentid.toString()).get(0);
    // orgaddpayTaAF.setNoteNum(orgAddPay.getNoteNum());
    // orgaddpayTaAF.setDocNum(orgAddPay.getDocNum());
    //
    // orgaddpayTaAF.setList(emplist);
    // orgaddpayTaAF.setMonthPaymentHead(monthPaymentHead);
    // orgaddpayTaAF.setPayMoneh(monthPaymentHead.getPayMonth());
    // orgaddpayTaAF.setName(orgAddPay.getOrg().getOrgInfo().getName());
    // orgaddpayTaAF.setOrgid(orgid);
    return null;
  }

  public OrgaddpayTaAF findOrgaddpayMX_(Pagination pagination)
      throws Exception, BusinessException {
    List list = new ArrayList();
    List emplist = new ArrayList();
    MonthPaymentHead monthPaymentHead = new MonthPaymentHead();
    OrgaddpayTaAF orgaddpayTaAF = new OrgaddpayTaAF();
    Serializable paymentid = (Serializable) pagination.getQueryCriterions()
        .get("paymentid");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    OrgAddPay orgAddPay = orgAddPayDAO.queryById(new Integer(paymentid
        .toString()));
    String orgid = orgAddPay.getOrg().getId().toString();
    list = monthPaymentTailDAO.queryOrgaddpayEmpListMXLJ(paymentid.toString(),
        orderBy, order, start, pageSize);
    if (list.size() > 0) {
      for (int i = 0; i < list.size(); i++) {
        MonthPaymentTail m = (MonthPaymentTail) list.get(i);
        Emp emp = empDAO.queryByCriterions(m.getEmpId().toString(), orgid);
        m.setEmp(emp);
        emplist.add(m);
      }
    }
    int count = monthPaymentTailDAO.queryOrgaddpayEmpListCountMXLJ(paymentid
        .toString());
    pagination.setNrOfElements(count);
    monthPaymentHead = (MonthPaymentHead) monthPaymentHeadDAO
        .queryMonthPaymentHeadLJ(paymentid.toString()).get(0);
    orgaddpayTaAF.setNoteNum(orgAddPay.getNoteNum());
    orgaddpayTaAF.setDocNum(orgAddPay.getDocNum());

    orgaddpayTaAF.setList(emplist);
    orgaddpayTaAF.setMonthPaymentHead(monthPaymentHead);
    orgaddpayTaAF.setPayMonth(monthPaymentHead.getPayMonth());
    orgaddpayTaAF.setName(orgAddPay.getOrg().getOrgInfo().getName());
    orgaddpayTaAF.setOrgid(orgid);
    return orgaddpayTaAF;

  }
  /**
   *����aa101�еĵǼ���
   *id��aa101�е�
   *iid��aa319�е� 
   *ld_add
   */
  public void updateRegistrations(String id,String iid,String type) {
    BizActivityLog bizActivityLog=bizActivityLogDAO.queryBizActivityLogWuht_(iid, type, "2");//��aa319�в��ҵǼ���
    String registrations = bizActivityLog.getOperator();
    OrgHAFAccountFlow newOrgHAFAccountFlow = orgHAFAccountFlowDAO.queryById(new Integer(id));
    newOrgHAFAccountFlow.setRegistrations(registrations);
  }

  public CollParaDAO getCollParaDAO() {
    return collParaDAO;
  }

  public void setCollParaDAO(CollParaDAO collParaDAO) {
    this.collParaDAO = collParaDAO;
  }
  public String findPayHeadIdInAA201AA202AA204(String id) throws Exception, BusinessException {
    String countaa201 = monthPaymentTailDAO.findPayHeadIdInAA201(id);
    if(!countaa201.equals("0")){
      return "yes";
    }else{
      String countaa202 = monthPaymentTailDAO.findPayHeadIdInAA202(id);
      if(!countaa202.equals("0")){
        return "yes";
      }else{
        String countaa204 = monthPaymentTailDAO.findPayHeadIdInAA204(id);
        if(!countaa204.equals("0")){
          return "yes";
        }else{
          return "no";
        }
      }
    }
  }
}
