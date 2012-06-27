package org.xpup.hafmis.syscollection.chgbiz.chgslarybase.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.imp.rule.UtilRule;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.bsinterface.IChgslarybaseBS;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto.ChgslarybaseCellListListExportDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto.ChgslarybaseHeadImportDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto.ChgslarybaseInfoDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto.ChgslarybaseListImportDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.form.ChgslarybaseListAF;
import org.xpup.hafmis.syscollection.common.dao.AutoInfoPickDAO;
import org.xpup.hafmis.syscollection.common.dao.BizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.ChangeSalaryBaseBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgOrgRateDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentSalaryBaseDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentTailDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPersonHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.PaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.daoDW.AutoInfoPickDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.ChgPaymentHeadDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.ChgPaymentTailDAODW;
import org.xpup.hafmis.syscollection.common.domain.entity.AutoInfoPick;
import org.xpup.hafmis.syscollection.common.domain.entity.ChangeSalaryBaseBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentHead;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentSalaryBase;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.PaymentHead;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.dto.SalaryBaseChgDTO;
import org.xpup.hafmis.syscommon.arithmetic.ArithmeticFactory;
import org.xpup.hafmis.syscommon.arithmetic.ArithmeticInterface;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;

public class ChgslarybaseBS implements IChgslarybaseBS {
  protected ChgPaymentPaymentDAO chgPaymentPaymentDAO = null;

  protected ChgPaymentSalaryBaseDAO chgPaymentSalaryBaseDAO = null;

  protected OrgDAO orgDAO = null;

  protected ChgPersonHeadDAO chgPersonHeadDAO = null;

  protected ChgOrgRateDAO chgOrgRateDAO = null;

  protected ChgPaymentTailDAO chgPaymentTailDAO = null;

  protected EmpDAO empDAO = null;

  protected HafOperateLogDAO hafOperateLogDAO = null;

  protected ChgPaymentHeadDAO chgPaymentHeadDAO = null;

  protected PaymentHeadDAO paymentHeadDAO = null;

  protected BizActivityLogDAO bizActivityLogDAO = null;

  protected ChangeSalaryBaseBizActivityLogDAO changeSalaryBaseBizActivityLogDAO = null;

  protected AutoInfoPickDAO autoInfoPickDAO = null;

  protected AutoInfoPickDAODW autoInfoPickDAODW = null;

  protected ChgPaymentTailDAODW chgPaymentTailDAODW = null;

  protected ChgPaymentHeadDAODW chgPaymentHeadDAODW = null;

  private String center_head_id = "";

  private String hafOperateLogid = "";

  public void setChgPersonHeadDAO(ChgPersonHeadDAO chgPersonHeadDAO) {
    this.chgPersonHeadDAO = chgPersonHeadDAO;
  }

  public void setChgPaymentPaymentDAO(ChgPaymentPaymentDAO chgPaymentPaymentDAO) {
    this.chgPaymentPaymentDAO = chgPaymentPaymentDAO;
  }

  public void setChgPaymentSalaryBaseDAO(
      ChgPaymentSalaryBaseDAO chgPaymentSalaryBaseDAO) {
    this.chgPaymentSalaryBaseDAO = chgPaymentSalaryBaseDAO;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setChgOrgRateDAO(ChgOrgRateDAO chgOrgRateDAO) {
    this.chgOrgRateDAO = chgOrgRateDAO;
  }

  public void setChgPaymentTailDAO(ChgPaymentTailDAO chgPaymentTailDAO) {
    this.chgPaymentTailDAO = chgPaymentTailDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }

  public void setChgPaymentHeadDAO(ChgPaymentHeadDAO chgPaymentHeadDAO) {
    this.chgPaymentHeadDAO = chgPaymentHeadDAO;
  }

  public void setBizActivityLogDAO(BizActivityLogDAO bizActivityLogDAO) {
    this.bizActivityLogDAO = bizActivityLogDAO;
  }

  public void setChangeSalaryBaseBizActivityLogDAO(
      ChangeSalaryBaseBizActivityLogDAO changeSalaryBaseBizActivityLogDAO) {
    this.changeSalaryBaseBizActivityLogDAO = changeSalaryBaseBizActivityLogDAO;
  }

  public void setAutoInfoPickDAO(AutoInfoPickDAO autoInfoPickDAO) {
    this.autoInfoPickDAO = autoInfoPickDAO;
  }

  public void setAutoInfoPickDAODW(AutoInfoPickDAODW autoInfoPickDAODW) {
    this.autoInfoPickDAODW = autoInfoPickDAODW;
  }

  public void setChgPaymentTailDAODW(ChgPaymentTailDAODW chgPaymentTailDAODW) {
    this.chgPaymentTailDAODW = chgPaymentTailDAODW;
  }

  public void setChgPaymentHeadDAODW(ChgPaymentHeadDAODW chgPaymentHeadDAODW) {
    this.chgPaymentHeadDAODW = chgPaymentHeadDAODW;
  }

  // ��λ�������
  public ChgslarybaseListAF findChgslarybaseWindow(Pagination pagination)
      throws Exception, BusinessException {
    String id = (String) pagination.getQueryCriterions().get("paymentid");
    List list = null;
    Emp emp = null;
    ChgPaymentSalaryBase chgPaymentSalaryBase = null;
    ChgslarybaseListAF chgslarybaseListAF = new ChgslarybaseListAF();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    Object payObj[] = chgPaymentTailDAO.queryChgPaymentTailPay(id);

    chgslarybaseListAF.setOldOrgPaySum(new Double(payObj[0].toString()));
    chgslarybaseListAF.setOldEmpPaySum(new Double(payObj[1].toString()));
    chgslarybaseListAF.setOldTotalAmount(new Double(new BigDecimal(payObj[0]
        .toString()).add(new BigDecimal(payObj[0].toString())).toString()));
    chgPaymentSalaryBase = chgPaymentSalaryBaseDAO.queryById(new Integer(id));
    if (chgPaymentSalaryBase != null) {
      chgslarybaseListAF.getOrg().setId(
          BusiTools.convertTenNumber(chgPaymentSalaryBase.getOrg().getId()
              .toString()));
      chgslarybaseListAF.getOrg().getOrgInfo().setName(
          chgPaymentSalaryBase.getOrg().getOrgInfo().getName());
      chgslarybaseListAF.getOrg().setOrgRate(
          chgPaymentSalaryBase.getOrg().getOrgRate());
      chgslarybaseListAF.getOrg().setEmpRate(
          chgPaymentSalaryBase.getOrg().getEmpRate());
      chgslarybaseListAF.setChgMonth(chgPaymentSalaryBase.getChgMonth());
      chgslarybaseListAF.setOldSalaryBaseSum(chgPaymentSalaryBase
          .getOldSalaryBaseSum());
      chgslarybaseListAF.setOlddSalaryBase(chgPaymentSalaryBase
          .getOlddSalaryBase());
      chgslarybaseListAF.setSalaryBase(chgPaymentSalaryBase.getSalaryBase());
      chgslarybaseListAF.setOrgPaySum(chgPaymentSalaryBase.getOrgPaySum());
      chgslarybaseListAF.setEmpPaySum(chgPaymentSalaryBase.getEmpPaySum());
      chgslarybaseListAF.setTotalAmount(chgPaymentSalaryBase.getTotalAmount());
      chgslarybaseListAF.setOldPayment(chgPaymentSalaryBase.getOldPayment());
      String paysum = chgPaymentSalaryBase.getPaySum().intValue() + "";
      if (paysum.equals("0")) {
        chgslarybaseListAF.setPaySum(new Double(chgPaymentSalaryBase
            .getOldPayment().toString()));
      }
      chgslarybaseListAF.setId(chgPaymentSalaryBase.getId());
      // ����ǰ��ɽ��
      chgslarybaseListAF.setData_1(Double.valueOf(chgPaymentSalaryBase
          .getOldPayment().toString()));
      // �������ɽ��

      chgslarybaseListAF.setData_2(Double.valueOf(chgPaymentSalaryBase
          .getOldPayment().add(
              new BigDecimal(chgPaymentSalaryBase.getOrgPaySum().toString()))
          .add(new BigDecimal(chgPaymentSalaryBase.getEmpPaySum().toString()))
          .subtract(
              new BigDecimal(chgslarybaseListAF.getOldOrgPaySum().toString()))
          .subtract(
              new BigDecimal(chgslarybaseListAF.getOldEmpPaySum().toString()))
          .toString()));
      // ���ӹ��ʻ���
      chgslarybaseListAF.setData_3(Double.valueOf((new BigDecimal(
          chgPaymentSalaryBase.getSalaryBase().toString())).subtract(
          new BigDecimal(chgPaymentSalaryBase.getOlddSalaryBase().toString()))
          .toString()));
      // ���ٹ��ʻ���

      // ���ӽɴ���
      chgslarybaseListAF.setData_4(Double
          .valueOf((new BigDecimal(chgslarybaseListAF.getData_2().toString()))
              .subtract(
                  new BigDecimal(chgslarybaseListAF.getData_1().toString()))
              .toString()));
      // ���ٽ��ɽ��
      if (chgslarybaseListAF.getData_3().compareTo(new Double(0)) < 0) {
        chgslarybaseListAF.setData_3(Double.valueOf((new BigDecimal(
            chgslarybaseListAF.getData_3().toString()).multiply(new BigDecimal(
            -1)).toString())));
      }
      if (chgslarybaseListAF.getData_4().compareTo(new Double(0)) < 0) {
        chgslarybaseListAF.setData_4(Double.valueOf((new BigDecimal(
            chgslarybaseListAF.getData_4().toString()).multiply(new BigDecimal(
            -1)).toString())));
      }
      if (chgslarybaseListAF.getData_2().compareTo(
          chgslarybaseListAF.getData_1()) >= 0) {
        chgslarybaseListAF.setData_5("1");
      } else {
        chgslarybaseListAF.setData_5("2");
      }
    }

    // �����2008616
    list = chgPaymentTailDAO.queryChgPaymentTailByChgPaymentPayment(id,
        orderBy, order, start, pageSize);
    BigDecimal oldOrgPayEmpPaySum = new BigDecimal(0.00);
    if (list != null) {
      for (int i = 0; i < list.size(); i++) {
        ChgPaymentTail chgPaymentTaillist = (ChgPaymentTail) list.get(i);
        // wuht
        oldOrgPayEmpPaySum = chgPaymentTaillist.getOldEmpPay().add(
            chgPaymentTaillist.getOldOrgPay());
        if (oldOrgPayEmpPaySum != null && !oldOrgPayEmpPaySum.equals("")) {
          chgPaymentTaillist.setOldOrgPayEmpPaySum(new Double(
              oldOrgPayEmpPaySum.toString()));
        }
        // wuht
        emp = empDAO.queryByCriterions(
            chgPaymentTaillist.getEmpId().toString(), chgPaymentSalaryBase
                .getOrg().getId().toString());
        chgPaymentTaillist.setEmp(emp);
      }
    }

    // �����2008616

    int count = chgPaymentTailDAO.countChgPaymentTailByChgPaymentPayment(id);
    pagination.setNrOfElements(count);
    chgslarybaseListAF.setList(list);
    return chgslarybaseListAF;
  }

  public ChgslarybaseListAF findChgslarybaseList(Pagination pagination)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    Org org = null;
    List empSalaryBaseChangements = null;
    ChgPaymentSalaryBase chgPaymentSalaryBase = null;
    ChgslarybaseListAF chgslarybaseListAF = new ChgslarybaseListAF();
    Emp emp = null;
    String chgMonth = "";
    String chgMonth2 = null;
    SecurityInfo securityInfo = (SecurityInfo) pagination.getQueryCriterions()
        .get("SecurityInfo");
    String orgid = (String) pagination.getQueryCriterions().get("org.id");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int page = pagination.getPage();
    int pageSize = pagination.getPageSize();
    chgslarybaseListAF.setData_5("3");
    if (((orgid != null) && (orgid.trim() != "") && (orgid.trim().length() > 0))) {

      org = orgDAO.queryByCriterions(orgid, null, null, securityInfo);
      if (org == null) {
        org = new Org();
        chgslarybaseListAF = new ChgslarybaseListAF();
        chgslarybaseListAF.setList(empSalaryBaseChangements);
        chgslarybaseListAF.setOrg(org);
        throw new BusinessException("�Բ��𣬲����ڸõ�λ���߲���Ȩ���ڣ�");

      }
      Integer orgid1 = new Integer(orgid);
      boolean Status1 = chgPaymentPaymentDAO.getChgStatus(orgid1);
      boolean Status3 = chgOrgRateDAO.getChgStatus(orgid1);
      boolean Status2 = chgPersonHeadDAO.getChgStatus(orgid1);

      org = orgDAO.queryByCriterions(orgid, "2", "1", securityInfo);
      if (org == null) {
        org = new Org();
        chgslarybaseListAF = new ChgslarybaseListAF();
        chgslarybaseListAF.setList(empSalaryBaseChangements);
        chgslarybaseListAF.setOrg(org);
        throw new BusinessException("�Բ��𣬸õ�λ�ɴ淽ʽΪְ���ɶ���߲���Ȩ���ڣ�");
      }
      if (!Status1) {
        org = new Org();
        chgslarybaseListAF = new ChgslarybaseListAF();
        chgslarybaseListAF.setList(empSalaryBaseChangements);
        chgslarybaseListAF.setOrg(org);
        throw new BusinessException("�Բ��𣬸õ�λ����δ���õĽɶ���������");
      }
      if (!Status2) {
        org = new Org();
        chgslarybaseListAF = new ChgslarybaseListAF();
        chgslarybaseListAF.setList(empSalaryBaseChangements);
        chgslarybaseListAF.setOrg(org);
        throw new BusinessException("�Բ��𣬸õ�λ����δ���õ���Ա��������");
      }
      if (!Status3) {
        org = new Org();
        chgslarybaseListAF = new ChgslarybaseListAF();
        chgslarybaseListAF.setList(empSalaryBaseChangements);
        chgslarybaseListAF.setOrg(org);
        throw new BusinessException("�Բ��𣬸õ�λ����δ���õĻ�ɱ������������");
      }
      chgslarybaseListAF.getOrg().getOrgInfo().setName(
          org.getOrgInfo().getName());
      // ��chgPaymentSalaryBase�в�ѯ��org.id��chgPaymentSalaryBase.chgStatus=1

      // ��aa202������
      chgPaymentSalaryBase = chgPaymentSalaryBaseDAO.queryByCriterions(orgid);// ͷ��;
      if (chgPaymentSalaryBase == null) {
        // aa301���ID
        String headMaxId_temp = paymentHeadDAO.queryMaxID(orgid);
        if (!headMaxId_temp.equals("")) {
          String payMonth_temp = paymentHeadDAO
              .queryPaymentHeadMaxID(headMaxId_temp);
          if (!payMonth_temp.equals("")) {
            chgMonth = BusiTools.addMonth(payMonth_temp, 1);
            chgslarybaseListAF.setChgMonth(chgMonth);
          } else {
            chgMonth = BusiTools.addMonth(org.getOrgPayMonth(), 1);
            chgslarybaseListAF.setChgMonth(chgMonth);
          }
        } else {
          chgMonth = BusiTools.addMonth(org.getOrgPayMonth(), 1);
          chgslarybaseListAF.setChgMonth(chgMonth);
        }

        chgslarybaseListAF.setData_5("3");

      } else {
        chgMonth = chgPaymentSalaryBase.getChgMonth();

        chgslarybaseListAF.setChgMonth(chgPaymentSalaryBase.getChgMonth());
        chgslarybaseListAF.setOldSalaryBaseSum(chgPaymentSalaryBase
            .getOldSalaryBaseSum());
        chgslarybaseListAF.setOlddSalaryBase(chgPaymentSalaryBase
            .getOlddSalaryBase());
        chgslarybaseListAF.setSalaryBase(chgPaymentSalaryBase.getSalaryBase());
        chgslarybaseListAF.setOrgPaySum(chgPaymentSalaryBase.getOrgPaySum());
        chgslarybaseListAF.setEmpPaySum(chgPaymentSalaryBase.getEmpPaySum());
        chgslarybaseListAF
            .setTotalAmount(chgPaymentSalaryBase.getTotalAmount());

        List empSalaryBaseChangementsAll = chgPaymentTailDAO
            .queryByCriterionsAll(orgid, chgMonth2, orderBy, order, start,
                pageSize, page);// β��
        BigDecimal oldOrgPaySum = new BigDecimal(0.00);
        BigDecimal oldEmpPaySum = new BigDecimal(0.00);
        BigDecimal oldTotalAmount = new BigDecimal(0.00);

        if (empSalaryBaseChangementsAll != null
            && empSalaryBaseChangementsAll.size() > 0) {

          for (int i = 0; i < empSalaryBaseChangementsAll.size(); i++) {
            ChgPaymentTail chgPaymentTaillist = (ChgPaymentTail) empSalaryBaseChangementsAll
                .get(i);
            oldOrgPaySum = chgPaymentTaillist.getOldOrgPay().add(oldOrgPaySum);
            oldEmpPaySum = chgPaymentTaillist.getOldEmpPay().add(oldEmpPaySum);
            if (chgPaymentTaillist.getOldSalaryBase().compareTo(
                chgPaymentTaillist.getSalaryBase()) > 0) {
              chgslarybaseListAF.setData_6(new Double(chgslarybaseListAF
                  .getData_6().floatValue()
                  - chgPaymentTaillist.getSalaryBase().floatValue()
                  + chgPaymentTaillist.getOldSalaryBase().floatValue()));
              chgslarybaseListAF.setData_8(new Double(chgslarybaseListAF
                  .getData_8().floatValue()
                  + chgPaymentTaillist.getOldEmpPay().floatValue()
                  - chgPaymentTaillist.getEmpPay().floatValue()
                  + chgPaymentTaillist.getOldOrgPay().floatValue()
                  - chgPaymentTaillist.getOrgPay().floatValue()));
            } else {
              chgslarybaseListAF.setData_7(new Double(chgslarybaseListAF
                  .getData_7().floatValue()
                  + chgPaymentTaillist.getSalaryBase().floatValue()
                  - chgPaymentTaillist.getOldSalaryBase().floatValue()));
              chgslarybaseListAF.setData_9(new Double(chgslarybaseListAF
                  .getData_9().floatValue()
                  - chgPaymentTaillist.getOldEmpPay().floatValue()
                  + chgPaymentTaillist.getEmpPay().floatValue()
                  - chgPaymentTaillist.getOldOrgPay().floatValue()
                  + chgPaymentTaillist.getOrgPay().floatValue()));
            }

          }
        }
        oldTotalAmount = oldTotalAmount.add(oldEmpPaySum.add(oldOrgPaySum));
        chgslarybaseListAF.setOldOrgPaySum(new Double(oldOrgPaySum.toString()));
        chgslarybaseListAF.setOldEmpPaySum(new Double(oldEmpPaySum.toString()));
        chgslarybaseListAF.setOldTotalAmount(new Double(oldTotalAmount
            .toString()));
        // ����ǰ��ɽ��
        chgslarybaseListAF.setData_1(Double.valueOf(chgPaymentSalaryBase
            .getOldPayment().toString()));
        // �������ɽ��

        chgslarybaseListAF.setData_2(Double
            .valueOf(chgPaymentSalaryBase.getOldPayment().add(
                new BigDecimal(chgPaymentSalaryBase.getOrgPaySum().toString()))
                .add(
                    new BigDecimal(chgPaymentSalaryBase.getEmpPaySum()
                        .toString())).subtract(
                    new BigDecimal(chgslarybaseListAF.getOldOrgPaySum()
                        .toString())).subtract(
                    new BigDecimal(chgslarybaseListAF.getOldEmpPaySum()
                        .toString())).toString()));
        // ���ӹ��ʻ���
        chgslarybaseListAF.setData_3(Double.valueOf((new BigDecimal(
            chgPaymentSalaryBase.getSalaryBase().toString()))
            .subtract(
                new BigDecimal(chgPaymentSalaryBase.getOlddSalaryBase()
                    .toString())).toString()));
        // ���ٹ��ʻ���

        // ���ӽɴ���
        chgslarybaseListAF.setData_4(Double.valueOf((new BigDecimal(
            chgslarybaseListAF.getData_2().toString())).subtract(
            new BigDecimal(chgslarybaseListAF.getData_1().toString()))
            .toString()));
        // ���ٽ��ɽ��
        if (chgslarybaseListAF.getData_3().compareTo(new Double(0)) < 0) {
          chgslarybaseListAF.setData_3(Double.valueOf((new BigDecimal(
              chgslarybaseListAF.getData_3().toString())
              .multiply(new BigDecimal(-1)).toString())));
        }
        if (chgslarybaseListAF.getData_4().compareTo(new Double(0)) < 0) {
          chgslarybaseListAF.setData_4(Double.valueOf((new BigDecimal(
              chgslarybaseListAF.getData_4().toString())
              .multiply(new BigDecimal(-1)).toString())));
        }
        if (chgslarybaseListAF.getData_2().compareTo(
            chgslarybaseListAF.getData_1()) >= 0) {
          chgslarybaseListAF.setData_5("1");
        } else {
          chgslarybaseListAF.setData_5("2");
        }

        if (chgPaymentSalaryBase.getOldPayment() == null) {
          chgslarybaseListAF.setOldPayment(new BigDecimal(0.00));
        } else {
          chgslarybaseListAF
              .setOldPayment(chgPaymentSalaryBase.getOldPayment());
        }
        chgslarybaseListAF.setId(chgPaymentSalaryBase.getId());
        List returnList = chgPaymentSalaryBaseDAO
            .querypay_statusByHeadID_WL(chgPaymentSalaryBase.getId().toString());
        // Integer pay_status=(Integer)returnList.get(0);
        // if((chgPaymentSalaryBase.getPaySum()==null)&&(pay_status.toString().equals("2")||pay_status.toString().equals("5"))){
        // chgslarybaseListAF.setPaySum(new
        // Double(chgPaymentSalaryBase.getOldPayment().toString()));
        // }else if(chgPaymentSalaryBase.getPaySum()==null){
        // chgslarybaseListAF.setPaySum(new Double(0));
        // }else{
        // chgslarybaseListAF.setPaySum(chgPaymentSalaryBase.getPaySum());
        // }

        Integer pay_status = (Integer) returnList.get(0);
        if ((chgPaymentSalaryBase.getPaySum().equals(new Double(0.0)))
            && (pay_status.toString().equals("2") || pay_status.toString()
                .equals("5"))) {
          chgslarybaseListAF.setPaySum(new Double(chgPaymentSalaryBase
              .getOldPayment().toString()));
        } else if (chgPaymentSalaryBase.getPaySum() == null) {
          chgslarybaseListAF.setPaySum(new Double(0));
        } else {
          chgslarybaseListAF.setPaySum(chgPaymentSalaryBase.getPaySum());
        }
      }

      // �����2008616
      empSalaryBaseChangements = chgPaymentTailDAO.queryByCriterions(orgid,
          chgMonth2, orderBy, order, start, pageSize, page);// β��
      BigDecimal oldOrgPayEmpPaySum = new BigDecimal(0.00);
      if (empSalaryBaseChangements != null) {

        for (int i = 0; i < empSalaryBaseChangements.size(); i++) {
          ChgPaymentTail chgPaymentTaillist = (ChgPaymentTail) empSalaryBaseChangements
              .get(i);
          // wuht
          oldOrgPayEmpPaySum = chgPaymentTaillist.getOldEmpPay().add(
              chgPaymentTaillist.getOldOrgPay());
          if (oldOrgPayEmpPaySum != null && !oldOrgPayEmpPaySum.equals("")) {
            chgPaymentTaillist.setOldOrgPayEmpPaySum(new Double(
                oldOrgPayEmpPaySum.toString()));
          }
          // wuht
          emp = empDAO.queryByCriterions(chgPaymentTaillist.getEmpId()
              .toString(), orgid);
          chgPaymentTaillist.setEmp(emp);
          // String statetype =
          // autoInfoPickDAODW.findStatus(chgPaymentTaillist.getChgPaymentHead().getOrg().getId().toString(),chgPaymentTaillist.getChgPaymentHead().getId().toString(),BusiConst.ORGBUSINESSTYPE_M);
          // chgslarybaseListAF.setStatetype(BusiTools.getBusiValue(Integer.parseInt(statetype),BusiConst.OC_NOT_PICK_UP_INFO));
        }
      }

      List empSalaryBaseChangementsAll = chgPaymentTailDAO
          .queryByCriterionsAll(orgid, chgMonth2, orderBy, order, start,
              pageSize, page);// β��
      BigDecimal oldOrgPaySum = new BigDecimal(0.00);
      BigDecimal oldEmpPaySum = new BigDecimal(0.00);
      BigDecimal oldTotalAmount = new BigDecimal(0.00);

      if (empSalaryBaseChangementsAll != null
          && empSalaryBaseChangementsAll.size() > 0) {

        for (int i = 0; i < empSalaryBaseChangementsAll.size(); i++) {
          ChgPaymentTail chgPaymentTaillist = (ChgPaymentTail) empSalaryBaseChangementsAll
              .get(i);
          oldOrgPaySum = chgPaymentTaillist.getOldOrgPay().add(oldOrgPaySum);
          oldEmpPaySum = chgPaymentTaillist.getOldEmpPay().add(oldEmpPaySum);
          oldOrgPayEmpPaySum = chgPaymentTaillist.getOldEmpPay().add(
              chgPaymentTaillist.getOldOrgPay());
          if (oldOrgPayEmpPaySum != null && !oldOrgPayEmpPaySum.equals("")) {
            chgPaymentTaillist.setOldOrgPayEmpPaySum(new Double(
                oldOrgPayEmpPaySum.toString()));
          }
          emp = empDAO.queryByCriterions(chgPaymentTaillist.getEmpId()
              .toString(), orgid);
          chgPaymentTaillist.setEmp(emp);
        }
      }
      oldTotalAmount = oldTotalAmount.add(oldEmpPaySum.add(oldOrgPaySum));
      chgslarybaseListAF.setOldOrgPaySum(new Double(oldOrgPaySum.toString()));
      chgslarybaseListAF.setOldEmpPaySum(new Double(oldEmpPaySum.toString()));
      chgslarybaseListAF
          .setOldTotalAmount(new Double(oldTotalAmount.toString()));
      // �����2008616
      int count = chgPaymentTailDAO.queryCountByCriterions(orgid, chgMonth);
      pagination.setNrOfElements(count);

      // �ɷѺ˶��Ƿ�Ӧ���˸õ�λ�����µı��
      if (org == null) {
        org = new Org();
      }
      if (chgslarybaseListAF == null) {
        chgslarybaseListAF = new ChgslarybaseListAF();
      }
      chgslarybaseListAF.setList(empSalaryBaseChangements);
      chgslarybaseListAF.setChgslarybaseCellList(empSalaryBaseChangementsAll);
      String tempid = org.getId().toString();
      if (tempid != null && !"".equals(tempid)) {
        String sid = BusiTools.convertTenNumber(tempid);
        org.setSid(sid);
      }
      chgslarybaseListAF.setOrg(org);
    }
    return chgslarybaseListAF;
  }

  public void deleteChgPaymentTail(Integer id, String pkid, String orgid,
      String ip, String nrOfElements, String name, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    HafOperateLog hafOperateLog = new HafOperateLog();
    ChgPaymentSalaryBase chgPaymentSalaryBase = null;
    BusiLogConst busiLogConst = null;
    ChgPaymentTail chgPaymentTail = null;
    ChangeSalaryBaseBizActivityLog changeSalaryBaseBizActivityLog = new ChangeSalaryBaseBizActivityLog();
    try {
      // //�ж��Ƿ�Ϊ��λ��
      int isOrgEdition = securityInfo.getIsOrgEdition();
      if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// ��λ��
        // �ж��Ƿ���AA203�����һ����¼
        if (nrOfElements.equals("1")) {
          // �ж��ύ״̬�Ƿ�Ϊδ��ȡ
          boolean isNoPickUp = autoInfoPickDAODW.isNOPickUp(orgid, pkid,
              BusiConst.ORGBUSINESSTYPE_M);
          // �������δ�ύ�ģ���ʾ�ȳ����ύ
          if (isNoPickUp) {
            throw new BusinessException("���ȳ����ύ��");
          }
          String stype = autoInfoPickDAODW.findStatus(orgid, pkid,
              BusiConst.ORGBUSINESSTYPE_M);
          if (stype.equals(BusiConst.OC_PICK_UP)) {
            throw new BusinessException("��������ȡ������ɾ��");
          }
        }
      } else if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// ���İ�
        // �ж��Ƿ���AA203�����һ����¼
        if (nrOfElements.equals("1")) {
          String center_head_id = "";
          String centerBizDate = "";
          autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP,
              center_head_id, centerBizDate, orgid, pkid,
              BusiConst.ORGBUSINESSTYPE_M);
        }
      }
      // ɾ��AA203��
      chgPaymentTail = chgPaymentTailDAO.queryById(id);
      chgPaymentTailDAO.delete(chgPaymentTail);

      if (nrOfElements.equals("1")) {

        // ɾ��AA319��
        changeSalaryBaseBizActivityLog = changeSalaryBaseBizActivityLogDAO
            .queryChgPaymentBizActivityLogByIdWuht(pkid.toLowerCase(), "1");
        changeSalaryBaseBizActivityLogDAO
            .deleteWuht(changeSalaryBaseBizActivityLog);
        // ɾ��AA203��
        chgPaymentTail = chgPaymentTailDAO.queryById(id);
        chgPaymentTailDAO.delete(chgPaymentTail);
        // ɾ��AA202��
        chgPaymentSalaryBase = chgPaymentSalaryBaseDAO.queryById(new Integer(
            pkid));
        chgPaymentSalaryBaseDAO.delete(chgPaymentSalaryBase);

      }

      // ����BA003��
      int opButton = busiLogConst.BIZLOG_ACTION_DELETE;
      int opModel = busiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO;
      this.addhafOperateLog(name, opButton, opModel, pkid, ip, orgid);

    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }

  }

  public void deleteAllChgPaymentTail(Integer id, String pkid, String orgid,
      String ip, String name, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    ChangeSalaryBaseBizActivityLog changeSalaryBaseBizActivityLog = new ChangeSalaryBaseBizActivityLog();
    HafOperateLog hafOperateLog = new HafOperateLog();
    ChgPaymentSalaryBase chgPaymentSalaryBase = null;
    BusiLogConst busiLogConst = null;
    List deleteList = new ArrayList();
    try {

      // �ж��Ƿ�Ϊ��λ��
      int isOrgEdition = securityInfo.getIsOrgEdition();
      if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// ��λ��
        // �ж��ύ״̬�Ƿ�Ϊδ��ȡ
        boolean isNoPickUp = autoInfoPickDAODW.isNOPickUp(orgid, pkid,
            BusiConst.ORGBUSINESSTYPE_M);
        // �������δ�ύ�ģ���ʾ�ȳ����ύ
        if (isNoPickUp) {
          throw new BusinessException("���ȳ����ύ��");
        }
        String stype = autoInfoPickDAODW.findStatus(orgid, pkid,
            BusiConst.ORGBUSINESSTYPE_M);
        if (stype.equals(BusiConst.OC_PICK_UP)) {
          throw new BusinessException("��������ȡ������ɾ��");
        }
      } else if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// ���İ�
        String center_head_id = "";
        String centerBizDate = "";
        autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP,
            center_head_id, centerBizDate, orgid, pkid,
            BusiConst.ORGBUSINESSTYPE_M);
      }
      // ɾ��AA319��
      changeSalaryBaseBizActivityLog = changeSalaryBaseBizActivityLogDAO
          .queryChgPaymentBizActivityLogByIdWuht(pkid.toLowerCase(), "1");
      changeSalaryBaseBizActivityLogDAO
          .deleteWuht(changeSalaryBaseBizActivityLog);
      // ɾ��AA203��
      // deleteList = chgPaymentTailDAO.queryByCriterionsWuht(orgid, null, null,
      // null,
      // 0, 0);
      chgPaymentTailDAO.deleteChgTail(new Integer(pkid));
      // ɾ��AA202��
      chgPaymentSalaryBase = chgPaymentSalaryBaseDAO
          .queryById(new Integer(pkid));
      chgPaymentSalaryBaseDAO.delete(chgPaymentSalaryBase);

      // ����BA003��
      int opButton = busiLogConst.BIZLOG_ACTION_DELETEALL;
      int opModel = busiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO;
      this.addhafOperateLog(name, opButton, opModel, pkid, ip, orgid);

    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }

  }

  // ��Ӳ�ѯ
  public ChgPaymentTail findEmpinfo(String empid, String orgid)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    ChgPaymentTail chgPaymentTail = null;

    BusinessException be = null;
    ChgPaymentSalaryBase chgPaymentSalaryBase = new ChgPaymentSalaryBase();
    Emp emp;
    try {
      if (((empid != null) && (empid.trim() != "") && (empid.trim().length() > 0))) {
        // ¼���ְ������Ƿ���ѡ��ĵ�λ�ģ�¼���ְ�������AA002��Ӧ�ĵ�λ����Ƿ����֮ǰ¼��ĵ�λ���
        // emp = empDAO.queryByCriterions(empid, orgid);
        emp = empDAO.queryByCriterionsByEmpPayStatus(empid, orgid,
            new String[] { "1", "3", "4" }); // wuht�޸�
        if (emp == null) {
          emp = new Emp();
          chgPaymentTail = new ChgPaymentTail();
          be = new BusinessException("��ְ�����������λ�ģ���");
          return null;
        }
        // ��δ�����õı��������Ƿ���ڸ�ְ����AA202��
        // �������=1.���ʻ������������״̬=1.δ���ö�Ӧ��id��AA203���Ӧ��ְ��������Ƿ���¼���ְ�����
        chgPaymentSalaryBase = chgPaymentSalaryBaseDAO.queryByCriterions(orgid);
        if (chgPaymentSalaryBase != null) {
          chgPaymentTail = chgPaymentTailDAO.queryChgPaymentSalaryBaseWUHT(
              empid, orgid);
        }
        if (chgPaymentTail != null) {
          chgPaymentTail = new ChgPaymentTail();
          be = new BusinessException("��ְ���Ѿ��������ʻ�����������Ҫ�޸ģ������޸ģ�");
          return null;
        }
        chgPaymentTail = new ChgPaymentTail();
        chgPaymentTail.setEmp(emp);

      }
    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw be;
      }
    }
    return chgPaymentTail;
  }

  // �����ӵ�ȷ����ť
  public void addChgPaymentTail(ChgPaymentTail chgPaymentTail,
      Pagination pagination) throws BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    HafOperateLog hafOperateLog = new HafOperateLog();
    BusiLogConst busiLogConst = null;
    ChgPaymentSalaryBase chgPaymentSalaryBase = new ChgPaymentSalaryBase();
    ChgPaymentSalaryBase chgPaymentSalaryBase1 = new ChgPaymentSalaryBase();
    ChangeSalaryBaseBizActivityLog changeSalaryBaseBizActivityLog = new ChangeSalaryBaseBizActivityLog();
    Emp emp = null;
    Org org = null;
    try {
      String name = (String) pagination.getQueryCriterions().get("name");
      String date = (String) pagination.getQueryCriterions().get("date");
      String pkid = (String) pagination.getQueryCriterions().get("pkid");
      String ip = (String) pagination.getQueryCriterions().get("ip");
      String orgid = (String) pagination.getQueryCriterions().get("org.id");
      String empid = (String) pagination.getQueryCriterions().get(
          "chgPaymentTail.empId");

      String chgMonth = (String) pagination.getQueryCriterions()
          .get("chgMonth");
      chgPaymentSalaryBase1 = chgPaymentSalaryBaseDAO.queryByCriterions(orgid);
      ChgPaymentTail chgPaymentTail1 = null;
      if (chgPaymentSalaryBase1 != null) {
        chgPaymentTail1 = chgPaymentTailDAO.queryChgPaymentSalaryBaseWUHT(
            empid, orgid);
      }
      if (chgPaymentTail1 != null) {
        be = new BusinessException("��ְ���Ѿ��������ʻ���������");
      } else {

        chgPaymentSalaryBase = chgPaymentSalaryBaseDAO.queryByCriterions(orgid);
        // �Ƿ����δ�����õı����᣺AA202�� �������=1.���ʻ�������
        // �����״̬=1.δ���ã�chgPaymentSalaryBase==null������
        if (chgPaymentSalaryBase == null || chgPaymentSalaryBase.equals("")) {
          org = orgDAO.queryById(new Integer(orgid));
          chgPaymentSalaryBase = new ChgPaymentSalaryBase();

          chgPaymentSalaryBase.setBizDate(date);
          chgPaymentSalaryBase.setChgMonth(chgMonth);
          chgPaymentSalaryBase.setChgStatus(new Integer(1));
          chgPaymentSalaryBase.setOrg(org);
          // chgPaymentSalaryBase.setPayHeadID(null);

          List list = new ArrayList();
          // list = empDAO.getEmpListWuht(orgid);
          list = empDAO.getEmpListWuhtPayStatus(orgid);
          BigDecimal sumSalaryBase = new BigDecimal(0.00);
          BigDecimal sumPay = new BigDecimal(0.00);
          if (list != null) {
            for (int i = 0; i < list.size(); i++) {
              Emp listemp = (Emp) list.get(i);
              sumSalaryBase = sumSalaryBase.add(listemp.getSalaryBase());
              sumPay = sumPay.add(listemp.getOrgPay());
              sumPay = sumPay.add(listemp.getEmpPay());
            }
          }

          chgPaymentSalaryBase.setOldPayment(sumPay);
          chgPaymentSalaryBase.setOldSlarayBase(sumSalaryBase);

          chgPaymentSalaryBaseDAO.insert(chgPaymentSalaryBase);

          // ����AA319��

          changeSalaryBaseBizActivityLog.setBizid(new Integer(
              chgPaymentSalaryBase.getId().toString()));
          changeSalaryBaseBizActivityLog.setAction(new Integer(1));
          changeSalaryBaseBizActivityLog.setOpTime(new Date());
          changeSalaryBaseBizActivityLog.setOperator(name);
          changeSalaryBaseBizActivityLogDAO
              .insert(changeSalaryBaseBizActivityLog);

        } else {
          if (pkid.equals("")) {

            chgPaymentSalaryBase = chgPaymentSalaryBaseDAO
                .queryByCriterions(orgid);

          } else {

            chgPaymentSalaryBase = chgPaymentSalaryBaseDAO
                .queryById(new Integer(pkid));
          }
        }
        emp = empDAO.queryByCriterions(empid, orgid);
        chgPaymentTail.setChgPaymentHead(chgPaymentSalaryBase);
        chgPaymentTail.setEmp(emp);
        chgPaymentTail.setPayStatus(new Integer(emp.getPayStatus().toString()));
        chgPaymentTail.setReserveaA(chgPaymentSalaryBase.getOrg().getOrgRate()
            .toString());
        chgPaymentTail.setReserveaB(chgPaymentSalaryBase.getOrg().getEmpRate()
            .toString());
        chgPaymentTailDAO.insert(chgPaymentTail);
        // ����BA003��
        pkid = chgPaymentSalaryBase.getId().toString();

        int opButton = busiLogConst.BIZLOG_ACTION_ADD;
        int opModel = busiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO;
        this.addhafOperateLog(name, opButton, opModel, pkid, ip, orgid);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw new BusinessException("");
      }
    }

  }

  // �޸ĵĲ�ѯ
  public ChgPaymentTail findChgPaymentTailWuht(String id, Pagination pagination)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    ChgPaymentTail chgPaymentTail = new ChgPaymentTail();
    Emp emp = new Emp();
    String orgid = (String) pagination.getQueryCriterions().get("org.id");
    try {
      chgPaymentTail = chgPaymentTailDAO.queryById(new Integer(id));
      emp = empDAO.queryByCriterions(chgPaymentTail.getEmpId().toString(),
          orgid);
      chgPaymentTail.setEmp(emp);

    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("");
    }
    return chgPaymentTail;
  }

  // �޸�AA202����������=¼��ĵ��������޸�AA203������BA003������ҵ��ϵͳ����=���������ģ��=���ʻ�������_����������=�޸Ĳ���ҵ��=AA203�иñʱ����id
  public void updateChgPaymentTail(ChgPaymentTail chgPaymentTail,
      Pagination pagination) throws BusinessException {
    // TODO Auto-generated method stub
    ChgPaymentTail chgPaymentTail1 = new ChgPaymentTail();
    BusinessException be = null;
    HafOperateLog hafOperateLog = new HafOperateLog();
    BusiLogConst busiLogConst = null;
    ChgPaymentSalaryBase chgPaymentSalaryBase = new ChgPaymentSalaryBase();
    try {
      String name = (String) pagination.getQueryCriterions().get("name");
      String pkid = (String) pagination.getQueryCriterions().get("pkid");
      String ip = (String) pagination.getQueryCriterions().get("ip");
      String orgid = (String) pagination.getQueryCriterions().get("org.id");
      String chgMonth = (String) pagination.getQueryCriterions()
          .get("chgMonth");
      chgPaymentSalaryBase = chgPaymentSalaryBaseDAO.queryByCriterions(orgid);
      chgPaymentSalaryBase.setChgMonth(chgMonth);
      chgPaymentTail1 = chgPaymentTailDAO.queryById(new Integer(chgPaymentTail
          .getId().toString()));
      chgPaymentTail1.setSalaryBase(chgPaymentTail.getSalaryBase());
      chgPaymentTail1.setEmpPay(chgPaymentTail.getEmpPay());
      chgPaymentTail1.setOrgPay(chgPaymentTail.getOrgPay());

      // ����BA003��
      int opButton = busiLogConst.BIZLOG_ACTION_MODIFY;
      int opModel = busiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO;
      this.addhafOperateLog(name, opButton, opModel, pkid, ip, orgid);

    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw new BusinessException("");
      }
    }

  }

  public void useChgPaymentSalaryBase(Pagination pagination) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    ChgPaymentSalaryBase chgPaymentSalaryBase = null;
    Emp emp = null;
    List empSalaryBaseChangements = null;
    HafOperateLog hafOperateLog = new HafOperateLog();
    BusiLogConst busiLogConst = null;
    BusinessException be = null;
    ChangeSalaryBaseBizActivityLog changeSalaryBaseBizActivityLog = new ChangeSalaryBaseBizActivityLog();
    try {
      String name = (String) pagination.getQueryCriterions().get("name");
      String pkid = (String) pagination.getQueryCriterions().get("pkid");
      String chgMonth = (String) pagination.getQueryCriterions()
          .get("chgMonth");
      String orgid = (String) pagination.getQueryCriterions().get("org.id");
      String ip = (String) pagination.getQueryCriterions().get("ip");
      chgPaymentSalaryBase = chgPaymentSalaryBaseDAO
          .queryChgPaymentSalaryBaseByIdWuht(pkid.toString(), "1");
      if (chgPaymentSalaryBase == null) {
        be = new BusinessException("��ְ���Ѿ��������ʻ�����������Ϊ���ã�");
      }

      else {

        // ����AA002����λ�ɶ� ְ���ɶ� ���ʻ���
        // ����AA202�����״̬=2.������ ��������=����¼��ĵ�������
        chgPaymentPaymentDAO.updatePayChgUse(new Integer(orgid), new Integer(
            pkid), chgMonth);
        Org org = new Org();
        org = orgDAO.queryById(new Integer(orgid.toString()));
        // ����AA002����λ�ɶ� ְ���ɶ� ���ʻ���
        // ����AA202�����״̬=2.������ ��������=����¼��ĵ�������
        chgPaymentPaymentDAO.updateAA203(org.getOrgRate().toString(), org
            .getEmpRate().toString(), pkid);
        // ����AA319��

        changeSalaryBaseBizActivityLog.setBizid(new Integer(pkid));
        changeSalaryBaseBizActivityLog.setAction(new Integer(3));
        changeSalaryBaseBizActivityLog.setOpTime(new Date());
        changeSalaryBaseBizActivityLog.setOperator(name);
        changeSalaryBaseBizActivityLogDAO
            .insert(changeSalaryBaseBizActivityLog);

        // ����BA003��
        int opButton = busiLogConst.BIZLOG_ACTION_OPENING;
        int opModel = busiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO;
        this.addhafOperateLog(name, opButton, opModel, pkid, ip, orgid);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw be;
      }
    }
  }

  public ChgslarybaseListAF findOrgChgslarybaseList(Pagination pagination)
      throws Exception, BusinessException {
    List list = null;
    ChgslarybaseListAF chgslarybaseListAF = new ChgslarybaseListAF();
    try {
      String chgMonth = (String) pagination.getQueryCriterions()
          .get("chgMonth");
      String endChgMonth = (String) pagination.getQueryCriterions().get(
          "endChgMonth");
      String orgid = (String) pagination.getQueryCriterions().get("orgId");
      String name = (String) pagination.getQueryCriterions().get("orgName");
      String typetb = (String) pagination.getQueryCriterions().get("typetb");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      SecurityInfo securityInfo = (SecurityInfo) pagination
          .getQueryCriterions().get("SecurityInfo");
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();

      list = chgPaymentSalaryBaseDAO.querySalaryBaseChgList(orgid, name,
          chgMonth, endChgMonth, typetb, orderBy, order, start, pageSize,
          securityInfo);
      int count = chgPaymentSalaryBaseDAO.querySalaryBaseChgCount(orgid, name,
          chgMonth, endChgMonth, typetb, securityInfo);
      for (int i = 0; i < list.size(); i++) {
        SalaryBaseChgDTO dto = (SalaryBaseChgDTO) list.get(i);
        dto.setChgStatus(BusiTools.getBusiValue(Integer.parseInt(dto
            .getChgStatus().toString()), BusiConst.CHGTYPESTATUS));
        if(dto.getCurPay().intValue() == 0)
          dto.setCurPay(dto.getPrePay());
      }
      pagination.setNrOfElements(count);
      chgslarybaseListAF.setList(list);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return chgslarybaseListAF;

  }

  public String findOrgidById(String id) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    ChgPaymentSalaryBase chgPaymentSalaryBase = new ChgPaymentSalaryBase();
    String orgid = null;
    BusinessException be = null;
    try {

      chgPaymentSalaryBase = chgPaymentSalaryBaseDAO
          .queryChgPaymentSalaryBaseByIdWuht(id, "1");
      if (chgPaymentSalaryBase == null) {

        be = new BusinessException("��ְ���Ѿ��������ʻ�����������Ϊ���ã�����δ���õ��޸ģ�");
        return null;
      }
      orgid = chgPaymentSalaryBase.getOrg().getId().toString();
    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw be;
      }
    }
    return orgid;
  }

  public void deleteAllChgPaymentTailMaintain(Integer id, String ip,
      String name, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    ChgPaymentSalaryBase chgPaymentSalaryBase = new ChgPaymentSalaryBase();
    String orgid = null;
    ChgPaymentTail chgPaymentTail = null;
    BusiLogConst busiLogConst = null;
    List deleteList = new ArrayList();
    ChangeSalaryBaseBizActivityLog changeSalaryBaseBizActivityLog = new ChangeSalaryBaseBizActivityLog();

    try {

      chgPaymentSalaryBase = chgPaymentSalaryBaseDAO
          .queryChgPaymentSalaryBaseByIdWuht(id.toString(), "1");
      if (chgPaymentSalaryBase == null) {
        be = new BusinessException("��ְ���Ѿ��������ʻ�����������Ϊ���ã�����δ���õ�ɾ����");
      } else {
        orgid = chgPaymentSalaryBase.getOrg().getId().toString();

        // �ж��Ƿ�Ϊ��λ��
        int isOrgEdition = securityInfo.getIsOrgEdition();
        if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// ��λ��
          // �ж��ύ״̬�Ƿ�Ϊδ��ȡ
          boolean isNoPickUp = autoInfoPickDAODW.isNOPickUp(orgid, id
              .toString(), BusiConst.ORGBUSINESSTYPE_M);
          // �������δ�ύ�ģ���ʾ�ȳ����ύ
          if (isNoPickUp) {
            throw new BusinessException("���ȳ����ύ��");
          }
          String stype = autoInfoPickDAODW.findStatus(orgid, id.toString(),
              BusiConst.ORGBUSINESSTYPE_M);
          if (stype.equals(BusiConst.OC_PICK_UP)) {
            throw new BusinessException("��������ȡ������ɾ��");
          }
        } else if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// ���İ�
          String center_head_id = "";
          String centerBizDate = "";
          autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP,
              center_head_id, centerBizDate, orgid, id.toString(),
              BusiConst.ORGBUSINESSTYPE_M);

        }

        // ɾ��AA319��
        changeSalaryBaseBizActivityLog = changeSalaryBaseBizActivityLogDAO
            .queryChgPaymentBizActivityLogByIdWuht(id.toString(), "1");
        changeSalaryBaseBizActivityLogDAO
            .deleteWuht(changeSalaryBaseBizActivityLog);
        // ɾ��AA203��
        // deleteList = chgPaymentTailDAO.queryByCriterionsWuht(orgid, null,
        // null,
        // null, 0, 0);
        chgPaymentTailDAO.deleteChgTail(id);
        // ɾ��AA202��
        chgPaymentSalaryBaseDAO.delete(chgPaymentSalaryBase);

        // ����BA003��
        int opButton = busiLogConst.BIZLOG_ACTION_DELETE;
        int opModel = busiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_MAINTAIN;
        this
            .addhafOperateLog(name, opButton, opModel, id.toString(), ip, orgid);

      }
    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    } finally {
      if (be != null) {
        throw be;
      }
    }
  }

  public void useChgPaymentSalaryBaseMaintain(Pagination pagination)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    ChgPaymentSalaryBase chgPaymentSalaryBase = null;
    Emp emp = null;
    List empSalaryBaseChangements = null;
    BusiLogConst busiLogConst = null;
    BusinessException be = null;
    String orgid = null;
    String chgMonth = null;
    String chgMonth2 = null;
    ChangeSalaryBaseBizActivityLog changeSalaryBaseBizActivityLog = new ChangeSalaryBaseBizActivityLog();
    try {
      String name = (String) pagination.getQueryCriterions().get("name");
      String id = (String) pagination.getQueryCriterions().get("id");
      chgPaymentSalaryBase = chgPaymentSalaryBaseDAO
          .queryChgPaymentSalaryBaseByIdWuht(id.toString(), "1");
      if (chgPaymentSalaryBase == null) {
        be = new BusinessException("��ְ���Ѿ��������ʻ�����������Ϊ���ã�����δ���õ����ã�");
      } else {
        chgMonth = chgPaymentSalaryBase.getChgMonth();

        orgid = chgPaymentSalaryBase.getOrg().getId().toString();
        String orderBy = (String) pagination.getOrderBy();
        String order = (String) pagination.getOrderother();
        int start = pagination.getFirstElementOnPage() - 1;
        int pageSize = pagination.getPageSize();
        String ip = (String) pagination.getQueryCriterions().get("ip");

        // ����AA002����λ�ɶ� ְ���ɶ� ���ʻ���

        // ����AA202�����״̬=2.������ ��������=����¼��ĵ�������
        chgPaymentPaymentDAO.updatePayChgUse(new Integer(orgid),
            new Integer(id), chgMonth);
        Org org = new Org();
        org = orgDAO.queryById(new Integer(orgid.toString()));
        // ����AA002����λ�ɶ� ְ���ɶ� ���ʻ���
        // ����AA202�����״̬=2.������ ��������=����¼��ĵ�������
        chgPaymentPaymentDAO.updateAA203(org.getOrgRate().toString(), org
            .getEmpRate().toString(), id);

        // ����AA319��
        changeSalaryBaseBizActivityLog.setBizid(new Integer(id));
        changeSalaryBaseBizActivityLog.setAction(new Integer(3));
        changeSalaryBaseBizActivityLog.setOpTime(new Date());
        changeSalaryBaseBizActivityLog.setOperator(name);
        changeSalaryBaseBizActivityLogDAO
            .insert(changeSalaryBaseBizActivityLog);

        // ����BA003��
        int opButton = busiLogConst.BIZLOG_ACTION_OPENING;
        int opModel = busiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_MAINTAIN;
        this
            .addhafOperateLog(name, opButton, opModel, id.toString(), ip, orgid);
      }

    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw be;
      }
    }
  }

  public boolean deluseChgPaymentSalaryBaseMaintain(Pagination pagination)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    ChgPaymentSalaryBase chgPaymentSalaryBase = null;
    String chgPersonHeadid = null;
    BusiLogConst busiLogConst = null;
    BusinessException be = null;
    String orgid = null;
    ChgPaymentSalaryBase chgPaymentSalaryBase3 = null;

    ChangeSalaryBaseBizActivityLog changeSalaryBaseBizActivityLog = new ChangeSalaryBaseBizActivityLog();
    try {
      String name = (String) pagination.getQueryCriterions().get("name");
      String id = (String) pagination.getQueryCriterions().get("id");
      int chgPaymentSalaryBaseid = Integer.parseInt(id);
      String ip = (String) pagination.getQueryCriterions().get("ip");
      // �ڳ������õ�ʱ�����ж�һ��״̬�Ƿ�Ϊ����
      ChgPaymentSalaryBase chgPaymentSalaryBase1 = null;
      chgPaymentSalaryBase1 = chgPaymentSalaryBaseDAO
          .queryById(new Integer(id));
      if (chgPaymentSalaryBase1 != null) {
        orgid = chgPaymentSalaryBase1.getOrg().getId().toString();
        chgPaymentSalaryBase3 = chgPaymentSalaryBaseDAO
            .queryByCriterions(orgid);
      }
      if (chgPaymentSalaryBase3 != null) {
        be = new BusinessException("����δ���õĹ��ʻ��������������������ã�");
      } else {
        // * ��ѯ��Ա���ͷ�� ��AA204���ĵ�����id=
        // �ñʱ�����Ƿ������������жϷ�����AA201��AA202��AA204�е�id�Ƿ���ڴ��ڸñʱ��id��
        chgPaymentSalaryBase = chgPaymentSalaryBaseDAO
            .queryChgPaymentSalaryBaseByIdWuht(id, "2");
        orgid = chgPaymentSalaryBase.getOrg().getId().toString();
        chgPersonHeadid = chgPersonHeadDAO.getMaxHeadID_WL(orgid).toString();

        if (Integer.parseInt(chgPersonHeadid) > chgPaymentSalaryBaseid) {
          be = new BusinessException("���ȳ�������ı����");
        } else {
          // �ñʱ���Ƿ񱻻��Ӧ���жϷ�����AA202�еĸñʱ����Ӧ�Ľɴ�id�Ƿ�Ϊ��
          chgPaymentSalaryBase = chgPaymentSalaryBaseDAO
              .queryChgPaymentSalaryBaseByIdWuht(id, "2");
          if (chgPaymentSalaryBase == null) {

            be = new BusinessException("��ְ����δ���ã�������ã�");

          } else {

            PaymentHead paymentHeadid = chgPaymentSalaryBase.getPaymentHead();
            if (paymentHeadid != null) {
              be = new BusinessException("���ȳ���Ӧ�øñʱ���Ļ�ɣ�");
            } else {
              orgid = chgPaymentSalaryBase.getOrg().getId().toString();
              ChgPaymentSalaryBase chgPaymentSalaryBase2 = null;
              chgPaymentSalaryBase2 = chgPaymentSalaryBaseDAO
                  .queryByCriterions(orgid);

              if (chgPaymentSalaryBase2 != null) {
                be = new BusinessException("����δ���õĹ��ʻ��������������������ã�");
              } else {

                orgid = chgPaymentSalaryBase.getOrg().getId().toString();

                // ɾ��AA319��
                changeSalaryBaseBizActivityLog = changeSalaryBaseBizActivityLogDAO
                    .queryChgPaymentBizActivityLogByIdWuht(id, "3");
                changeSalaryBaseBizActivityLogDAO
                    .deleteWuht(changeSalaryBaseBizActivityLog);

                // ����BA003��
                int opButton = busiLogConst.BIZLOG_ACTION_REVOCATION;
                int opModel = busiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_MAINTAIN;
                this.addhafOperateLog(name, opButton, opModel, id.toString(),
                    ip, orgid);
                // ����AA002����λ�ɶ� ְ���ɶ� ���ʻ���
                // ����AA202�����״̬=1.δ���� ��������=����¼��ĵ�������
                chgPaymentPaymentDAO.updatePayChgReUse(new Integer(orgid),
                    new Integer(id));
                return true;
              }
            }
          }
        }
        return true;
      }
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw be;
      }
    }
  }

  // ����
  public List queryEmpOrgList(Pagination pagination) throws Exception,
      BusinessException {

    List list = new ArrayList();
    BusinessException be = null;
    BusiLogConst busiLogConst = null;
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    String orgId = (String) pagination.getQueryCriterions().get("org.id");
    String name = (String) pagination.getQueryCriterions().get("name");
    String ip = (String) pagination.getQueryCriterions().get("ip");
    String orgName = (String) pagination.getQueryCriterions().get("orgName");
    String chgMonth = (String) pagination.getQueryCriterions().get("chgMonth");

    String orderArray[] = (String[]) pagination.getQueryCriterions().get(
        "orderArray");// ���������
    try {
      Org org = orgDAO.getOrg_WL(orgId);
      if (org != null) {
        orgName = org.getOrgInfo().getName();
      }
      // ��aa202������
      ChgPaymentSalaryBase chgPaymentSalaryBase = chgPaymentSalaryBaseDAO
          .queryByCriterions(orgId);// ͷ��;
      if (chgPaymentSalaryBase == null) {
        // aa301���ID
        String headMaxId_temp = paymentHeadDAO.queryMaxID(orgId);
        if (!headMaxId_temp.equals("")) {
          String payMonth_temp = paymentHeadDAO
              .queryPaymentHeadMaxID(headMaxId_temp);
          if (!payMonth_temp.equals("")) {
            chgMonth = BusiTools.addMonth(payMonth_temp, 1);

          } else {
            chgMonth = BusiTools.addMonth(org.getOrgPayMonth(), 1);

          }
        } else {
          chgMonth = BusiTools.addMonth(org.getOrgPayMonth(), 1);

        }
      } else {
        chgMonth = chgPaymentSalaryBase.getChgMonth();
      }

      // list = empDAO.queryChgslarybaseEmpOrgWuht(orgId, orgName, chgMonth,
      // orderArray, order, start, pageSize); // ���ӵ�������
      // ���ʻ�������������������Ӧ�õ���״̬Ϊ1��2��3��4��ְ��
      list = empDAO.queryChgslarybaseEmpOrgWuhtExports(orgId, orgName,
          chgMonth, orderArray, order, start, pageSize); // ���ӵ�������

      // list = empDAO.queryChgslarybaseEmpOrgWuht(orgId, orgName, chgMonth,
      // orderBy, order, start, pageSize);
      // ����BA003��
      int opButton = busiLogConst.BIZLOG_ACTION_EXP;
      int opModel = busiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO;

      this.addhafOperateLog(name, opButton, opModel, "0".toString(), ip, orgId);
      if (list == null || list.size() == 0) {

        be = new BusinessException("�õ�λ����ְ����");
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw be;
      }
    }

    return list;

  }

  // ����
  public List addChgslarybaseListBatch(List addchgslarybaseHeadImportList,
      List addchgslarybaseListImportList, String orgId, String chgMonth,
      String ip, String name, String date, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    ChangeSalaryBaseBizActivityLog changeSalaryBaseBizActivityLog = new ChangeSalaryBaseBizActivityLog();
    if (addchgslarybaseHeadImportList.size() <= 0) {
      throw new BusinessException("��������Ϊ�գ�");
    }
    if (addchgslarybaseListImportList.size() <= 0) {
      throw new BusinessException("��������Ϊ�գ�");
    }
    ChgslarybaseHeadImportDTO chgslarybaseHeadImportDTO = (ChgslarybaseHeadImportDTO) addchgslarybaseHeadImportList
        .get(1);
    int OrgId1 = Integer.parseInt(chgslarybaseHeadImportDTO.getOrgId());
    String OrgId2 = OrgId1 + "";
    if (!orgId.equals(OrgId2.trim())) {
      throw new BusinessException("ѡ��ĵ����ļ�������ĵ�λ��Ų�����");
    }
    Org org = null;

    org = orgDAO.queryByCriterions(chgslarybaseHeadImportDTO.getOrgId(), "2",
        "1", securityInfo);
    boolean Status1 = chgPaymentSalaryBaseDAO.getChgStatus(new Integer(
        chgslarybaseHeadImportDTO.getOrgId()));
    if (!Status1 || org == null) {
      org = new Org();
      throw new BusinessException("�Բ��𣬸õ�λ���ܽ��е��룡");
    }
    // ����aa202��
    ChgPaymentSalaryBase chgPaymentSalaryBase = new ChgPaymentSalaryBase();

    chgPaymentSalaryBase.setBizDate(date);
    chgPaymentSalaryBase.setChgMonth(chgMonth);
    chgPaymentSalaryBase.setChgStatus(new Integer(1));
    chgPaymentSalaryBase.setOrg(org);
    Object obj[] = empDAO.getSalaryPayAllByOrgId(orgId);
    chgPaymentSalaryBase.setOldPayment(new BigDecimal(obj[1].toString()));
    chgPaymentSalaryBase.setOldSlarayBase(new BigDecimal(obj[0].toString()));
    Serializable centerHeadId = chgPaymentSalaryBaseDAO
        .insert(chgPaymentSalaryBase);
    center_head_id = centerHeadId.toString();
    // ����aa203
    for (int i = 1; i < addchgslarybaseListImportList.size(); i++) {
      ChgslarybaseListImportDTO chgslarybaseListImportDto = (ChgslarybaseListImportDTO) addchgslarybaseListImportList
          .get(i);
      UtilRule utilRule = new UtilRule();
      ChgslarybaseInfoDTO chgslarybaseInfoDTO = new ChgslarybaseInfoDTO();

      Pattern pNotenumber = Pattern.compile("^([0-9]{1,10})(\\.[0-9]{1,2})?$");
      Matcher mNotenumber = pNotenumber.matcher(chgslarybaseListImportDto
          .getSalaryBase());
      if (mNotenumber.find() == false) {
        throw new BusinessException("��¼����ȷ���ʻ�����");
      } else if (chgslarybaseListImportDto.getSalaryBase().length() > 20) {
        throw new BusinessException("���ʻ����������");
      }
      if (chgslarybaseListImportDto.getSalaryBase() == null
          || chgslarybaseListImportDto.getSalaryBase().equals("")) {
        throw new BusinessException("����Ĺ��ʻ�������");
      } else if (utilRule.moneyRule(chgslarybaseListImportDto.getSalaryBase(),
          10, 2) == false) {
        chgslarybaseInfoDTO.setSalaryBase(chgslarybaseListImportDto
            .getSalaryBase());
        list.add(chgslarybaseInfoDTO);
        throw new BusinessException("����Ĺ��ʻ�������");
      }
      ChgPaymentTail chgPaymentTail = new ChgPaymentTail();
      ChgPaymentTail chgPaymentTail1 = new ChgPaymentTail();
      Emp emp = empDAO.queryByCriterions(chgslarybaseListImportDto.getEmpId(),
          orgId);
      if (emp == null) {
        throw new BusinessException("�����ְ�� "
            + chgslarybaseListImportDto.getEmpId() + " �������ڱ���λ");
      }
      chgPaymentTail1 = chgPaymentTailDAO.queryChgPaymentSalaryBaseWUHT(
          chgslarybaseListImportDto.getEmpId(), orgId);
      if (chgPaymentTail1 != null) {
        throw new BusinessException(chgslarybaseListImportDto.getEmpId()
            + " ��ְ���Ѿ��������ʻ���������");
      }
      chgPaymentTail.setChgPaymentHead(chgPaymentSalaryBase);
      chgPaymentTail.setEmp(emp);
      chgPaymentTail
          .setEmpId(new Integer(chgslarybaseListImportDto.getEmpId()));
      chgPaymentTail.setOldSalaryBase(new BigDecimal(chgslarybaseListImportDto
          .getOldSalaryBase()));
      chgPaymentTail.setOldOrgPay(emp.getOrgPay());
      chgPaymentTail.setOldEmpPay(emp.getEmpPay());
      chgPaymentTail.setSalaryBase(new BigDecimal(chgslarybaseListImportDto
          .getSalaryBase()));
      // salaryBase:�������ʻ��� orgPay:������λ�ɶ� empPay:������ְ���ɶ�
      // org.getPayPrecision():�ɴ澫��ID
      // orgRat:��λ���� empRate:ְ������
      BigDecimal orgPay = new BigDecimal(0.00);
      BigDecimal empPay = new BigDecimal(0.00);
      BigDecimal orgRat = new BigDecimal(0.00);
      BigDecimal empRate = new BigDecimal(0.00);
      BigDecimal salaryBase = new BigDecimal(0.00);
      salaryBase = new BigDecimal(chgslarybaseListImportDto.getSalaryBase());
      orgRat = org.getOrgRate();
      empRate = org.getEmpRate();

      int payPrecision = org.getPayPrecision().intValue();
      ArithmeticInterface arithmeticInterface = ArithmeticFactory
          .getArithmetic().getArithmeticDAO(payPrecision);
      try {
        orgPay = arithmeticInterface.getPay(salaryBase, orgRat);
        empPay = arithmeticInterface.getPay(salaryBase, empRate);

      } catch (Exception e) {
        e.printStackTrace();
      }
      chgPaymentTail.setOrgPay(orgPay);
      chgPaymentTail.setEmpPay(empPay);
      chgPaymentTail.setPayStatus(new Integer(emp.getPayStatus().toString()));
      chgPaymentTailDAO.insert(chgPaymentTail);
    }
    // ����AA319��
    changeSalaryBaseBizActivityLog.setBizid(new Integer(chgPaymentSalaryBase
        .getId().toString()));
    changeSalaryBaseBizActivityLog.setAction(new Integer(1));
    changeSalaryBaseBizActivityLog.setOpTime(new Date());
    changeSalaryBaseBizActivityLog.setOperator(name);
    changeSalaryBaseBizActivityLogDAO.insert(changeSalaryBaseBizActivityLog);
    // ����BA003��
    String pkid = chgPaymentSalaryBase.getId().toString();
    BusiLogConst busiLogConst = null;
    int opButton = busiLogConst.BIZLOG_ACTION_EXP;
    int opModel = busiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO;
    this.addhafOperateLog(name, opButton, opModel, pkid, ip, orgId);
    return list;
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
      Serializable hafOperateLogtempid = hafOperateLogDAO.insert(hafOperateLog);
      hafOperateLogid = hafOperateLogtempid.toString();

    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw new BusinessException("");
      }
    }

  }

  public String queryOrgidByChgPaymentHeadID(String chgPaymentHeadID)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    ChgPaymentHead chgPaymentHead = new ChgPaymentHead();
    String orgid = "";
    try {
      chgPaymentHead = chgPaymentHeadDAO
          .queryById(new Integer(chgPaymentHeadID));
      orgid = chgPaymentHead.getOrg().getId().toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return orgid;
  }

  // �ύ����
  public void PickInChgPaymentTailMaintain(String id, String orgid,
      SecurityInfo securityInfo, String flag) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    try {

      boolean isNoPickIn = autoInfoPickDAODW.isNOPickIn(orgid, id,
          BusiConst.ORGBUSINESSTYPE_M);
      if (isNoPickIn) {
        throw new BusinessException("�ñ�ҵ�����ύ��");
      } else {
        ChgPaymentHead chgPaymentHead = chgPaymentHeadDAO
            .queryById(new Integer(id));
        String ip = securityInfo.getUserInfo().getUserIp();
        String name = securityInfo.getUserInfo().getUsername();
        AutoInfoPick autoInfoPick = new AutoInfoPick();
        autoInfoPick.setOrgId(new Integer(orgid));
        autoInfoPick.setOrgHeadId(new Integer(id));
        autoInfoPick.setCenterHeadId(null);
        autoInfoPick.setType(BusiConst.ORGBUSINESSTYPE_M);
        autoInfoPick.setStatus(BusiConst.OC_NOT_PICK_UP);
        autoInfoPick.setOrgBizDate(new Date());
        if (chgPaymentHead.getPaymentHead() != null) {
          autoInfoPick.setPayHeadId(new Integer(chgPaymentHead.getPaymentHead()
              .getId().toString()));
        } else {
          autoInfoPick.setPayHeadId(null);
        }
        autoInfoPickDAODW.insert(autoInfoPick);
        // ����BA003��
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(
            BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        if (flag.equals("1")) {
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO));
        } else {
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_MAINTAIN));
        }
        hafOperateLog.setOpButton(Integer
            .toString(BusiLogConst.BIZLOG_ACTION_REFERRINGDATE));
        hafOperateLog.setOpBizId(new Integer(id));// AA202.ID
        hafOperateLog.setOperator(name);
        hafOperateLog.setOpIp(ip);
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOrgId(new Integer(orgid));
        hafOperateLogDAO.insert(hafOperateLog);
      }
    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }

  }

  // �����ύ����
  public void removePickInChgPaymentTailMaintain(String id, String orgid,
      SecurityInfo securityInfo, String flag) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    try {
      String status = autoInfoPickDAODW.findStatus(orgid, id,
          BusiConst.ORGBUSINESSTYPE_M);
      if (status.equals(BusiConst.OC_NOT_PICK_UP)) {

        // ɾ��DA001
        autoInfoPickDAODW.deleteAutoInfoPick(orgid, id,
            BusiConst.ORGBUSINESSTYPE_M);
        // ����BA003��
        String ip = securityInfo.getUserInfo().getUserIp();
        String name = securityInfo.getUserInfo().getUsername();
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(
            BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        if (flag.equals("1")) {
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO));
        } else {
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_MAINTAIN));
        }
        hafOperateLog.setOpButton(Integer
            .toString(BusiLogConst.BIZLOG_ACTION_PPROVALDATA));
        hafOperateLog.setOpBizId(new Integer(id));// AA301.ID
        hafOperateLog.setOperator(name);
        hafOperateLog.setOpIp(ip);
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOrgId(new Integer(orgid));
        hafOperateLogDAO.insert(hafOperateLog);
      } else {
        if (status.equals(BusiConst.OC_PICK_UP)) {
          throw new BusinessException("��ҵ���ѱ�������ȡ�������Գ�����");
        }
        if (status.equals(BusiConst.OC_PICK_UP_OVER)) {
          throw new BusinessException("�ñ�ҵ��û���ύ�������Գ�����");
        }
      }
    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }
  }

  // ��ȡ����
  public void pickUpChgPaymentChgslarybase(String orgid, String chgMonth,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {
      int count = autoInfoPickDAO.queryNoPickUpListbyOrgid(orgid);
      if (count == 0) {
        throw new BusinessException("�õ�λ������δ��ȡ�ļ�¼��");
      }
      String ip = securityInfo.getUserInfo().getUserIp();
      String name = securityInfo.getUserInfo().getUsername();
      String date = securityInfo.getUserInfo().getBizDate();
      // ����orgid��ѯ orgheadid ��type
      Object[] obj = autoInfoPickDAO.queryOrgHeadidAndType(orgid);

      if (obj != null) {
        if ((obj[1].toString().equals(BusiConst.ORGBUSINESSTYPE_M))) {
          String orgheadID = autoInfoPickDAO.findOrgHeadid(orgid,
              BusiConst.ORGBUSINESSTYPE_M, BusiConst.OC_NOT_PICK_UP);
          // ��ѯ�����õ�list
          List addchgslarybaseHeadImportList = chgPaymentHeadDAODW
              .findAddchgslarybaseListImportList(orgid);
          List addchgslarybaseListImportList = chgPaymentTailDAODW
              .findAddchgslarybaseListImportList(orgheadID);

          // ���õ��뷽��
          addChgslarybaseListBatch(addchgslarybaseHeadImportList,
              addchgslarybaseListImportList, orgid, chgMonth, ip, name, date,
              securityInfo);
          // ɾ������Ĳ���BA003
          hafOperateLogDAO.delete(hafOperateLogid);
          // ����da001
          autoInfoPickDAO.updateAutoInfoPick(BusiConst.OC_PICK_UP,
              center_head_id, new Date().toString(), orgid, orgheadID,
              BusiConst.ORGBUSINESSTYPE_M);
          // ����BA003��
          HafOperateLog hafOperateLog = new HafOperateLog();
          hafOperateLog.setOpSys(new Integer(
              BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO));
          hafOperateLog.setOpButton(Integer
              .toString(BusiLogConst.BIZLOG_ACTION_PICKUPDATA));
          hafOperateLog.setOpBizId(new Integer(center_head_id));// AA301.ID
          hafOperateLog.setOperator(name);
          hafOperateLog.setOpIp(ip);
          hafOperateLog.setOpTime(new Date());
          hafOperateLog.setOrgId(new Integer(orgid));
          hafOperateLogDAO.insert(hafOperateLog);
        } else {
          if (obj[1].toString().equals(BusiConst.ORGBUSINESSTYPE_O)) {
            throw new BusinessException("����δ��ȡ����Ա�����");
          }
          if (obj[1].toString().equals(BusiConst.ORGBUSINESSTYPE_N)) {
            throw new BusinessException("����δ��ȡ�Ľɶ���������");
          }

        }

      } else {
        Object[] objtem = autoInfoPickDAO.findOrgHeadidAndType(orgid);
        if (objtem != null) {
          if ((objtem[1].toString().equals(BusiConst.ORGBUSINESSTYPE_M))) {
            String orgheadID = autoInfoPickDAO.findOrgHeadid(orgid,
                BusiConst.ORGBUSINESSTYPE_M, BusiConst.OC_NOT_PICK_UP);
            // ��ѯ�����õ�list
            List addchgslarybaseHeadImportList = chgPaymentHeadDAODW
                .findAddchgslarybaseListImportList(orgid);
            List addchgslarybaseListImportList = chgPaymentTailDAODW
                .findAddchgslarybaseListImportList(orgheadID);
            // ���õ��뷽��
            addChgslarybaseListBatch(addchgslarybaseHeadImportList,
                addchgslarybaseListImportList, orgid, chgMonth, ip, name, date,
                securityInfo);
            // ɾ������Ĳ���BA003
            hafOperateLogDAO.delete(hafOperateLogid);
            // ����da001
            autoInfoPickDAO.updateAutoInfoPick(BusiConst.OC_PICK_UP,
                center_head_id, new Date().toString(), orgid, orgheadID,
                BusiConst.ORGBUSINESSTYPE_M);
            // ����BA003��
            HafOperateLog hafOperateLog = new HafOperateLog();
            hafOperateLog.setOpSys(new Integer(
                BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
            hafOperateLog.setOpModel(Integer
                .toString(BusiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO));
            hafOperateLog.setOpButton(Integer
                .toString(BusiLogConst.BIZLOG_ACTION_PICKUPDATA));
            hafOperateLog.setOpBizId(new Integer(center_head_id));// AA301.ID
            hafOperateLog.setOperator(name);
            hafOperateLog.setOpIp(ip);
            hafOperateLog.setOpTime(new Date());
            hafOperateLog.setOrgId(new Integer(orgid));
            hafOperateLogDAO.insert(hafOperateLog);
          } else {
            if (obj[1].toString().equals(BusiConst.ORGBUSINESSTYPE_O)) {
              throw new BusinessException("����δ��ȡ����Ա�����");
            }
            if (obj[1].toString().equals(BusiConst.ORGBUSINESSTYPE_N)) {
              throw new BusinessException("����δ��ȡ�Ľɶ���������");
            }
          }
        }
      }
    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }

  }

  public PaymentHeadDAO getPaymentHeadDAO() {
    return paymentHeadDAO;
  }

  public void setPaymentHeadDAO(PaymentHeadDAO paymentHeadDAO) {
    this.paymentHeadDAO = paymentHeadDAO;
  }

  // �����2008616
  public List chgslarybaseCellList(List chgslarybaseCellList, String orgid,
      String chgMonth, SecurityInfo securityInfo, Pagination pagination)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    List chgslarybaseCellList1 = new ArrayList();
    try {
      Org org = orgDAO.queryByCriterions(orgid, null, null, securityInfo);
      String orgName = "";

      if (org != null) {
        orgName = org.getOrgInfo().getName();
      }
      if (chgslarybaseCellList != null && chgslarybaseCellList.size() > 0) {
        for (int i = 0; i < chgslarybaseCellList.size(); i++) {
          ChgslarybaseCellListListExportDTO chgslarybaseCellListInfoDTO = new ChgslarybaseCellListListExportDTO();
          ChgPaymentTail chgPaymentTail = (ChgPaymentTail) chgslarybaseCellList
              .get(i);

          chgslarybaseCellListInfoDTO.setOrgId(orgid);
          chgslarybaseCellListInfoDTO.setOrgName(orgName);
          chgslarybaseCellListInfoDTO.setChgMonth(chgMonth);

          String empId = chgPaymentTail.getEmpId().toString();
          chgslarybaseCellListInfoDTO.setEmpId(BusiTools
              .convertSixNumber(empId));
          chgslarybaseCellListInfoDTO.setEmpName(chgPaymentTail.getEmpName());
          chgslarybaseCellListInfoDTO.setCardNum(chgPaymentTail.getEmp()
              .getEmpInfo().getCardNum());

          chgslarybaseCellListInfoDTO.setOldSalaryBase(chgPaymentTail
              .getOldSalaryBase().toString());
          chgslarybaseCellListInfoDTO.setOldOrgPay(chgPaymentTail
              .getOldOrgPay().toString());
          chgslarybaseCellListInfoDTO.setOldEmpPay(chgPaymentTail
              .getOldEmpPay().toString());

          chgslarybaseCellListInfoDTO.setOldEmpPay(chgPaymentTail
              .getOldEmpPay().toString());
          chgslarybaseCellListInfoDTO.setOldOrgPayEmpPaySum(chgPaymentTail
              .getOldOrgPayEmpPaySum().toString());
          chgslarybaseCellListInfoDTO.setSalaryBase(chgPaymentTail
              .getSalaryBase().toString());

          chgslarybaseCellListInfoDTO.setOrgPay(chgPaymentTail.getOrgPay()
              .toString());
          chgslarybaseCellListInfoDTO.setEmpPay(chgPaymentTail.getEmpPay()
              .toString());
          chgslarybaseCellListInfoDTO.setOldPaySum(chgPaymentTail
              .getOldPaySum().toString());
          chgslarybaseCellList1.add(chgslarybaseCellListInfoDTO);
        }

      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return chgslarybaseCellList1;
  }

  public void setAa202(Pagination pagination) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    ChgPaymentSalaryBase chgPaymentSalaryBase = null;
    BusinessException be = null;
    ChangeSalaryBaseBizActivityLog changeSalaryBaseBizActivityLog = new ChangeSalaryBaseBizActivityLog();
    try {
      String name = (String) pagination.getQueryCriterions().get("name");
      String pkid = (String) pagination.getQueryCriterions().get("pkid");
      String chgMonth = (String) pagination.getQueryCriterions()
          .get("chgMonth");
      String orgid = (String) pagination.getQueryCriterions().get("org.id");
      String ip = (String) pagination.getQueryCriterions().get("ip");
      chgPaymentSalaryBase = chgPaymentSalaryBaseDAO
          .queryChgPaymentSalaryBaseByIdWuht(pkid.toString(), "1");
      if (false) {
        be = new BusinessException("��ְ���Ѿ��������ʻ�����������Ϊ���ã�");
      }

      else {

        // ����AA002����λ�ɶ� ְ���ɶ� ���ʻ���
        // ����AA202�����״̬=2.������ ��������=����¼��ĵ�������

        Org org = new Org();
        org = orgDAO.queryById(new Integer(orgid.toString()));
        List list = empDAO.getEmpListWuhtPayStatus(orgid);
        int count = 0;
        BigDecimal money = new BigDecimal("0.00");
        for (int i = 0; i < list.size(); i++) {
          Emp emp_1 = (Emp) list.get(i);
          money.add(emp_1.getEmpPay().add(emp_1.getOrgPay()));
        }
        if (list != null && list.size() > 0) {
          count = list.size();
        }

        chgPaymentPaymentDAO.updateAA202(String.valueOf(count), money
            .toString(), pkid);

      }
    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw be;
      }
    }
  }

  public void setAa202_wsh(String pkid) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    ChgPaymentSalaryBase chgPaymentSalaryBase = null;
    Emp emp = null;
    List empSalaryBaseChangements = null;
    HafOperateLog hafOperateLog = new HafOperateLog();
    BusiLogConst busiLogConst = null;
    BusinessException be = null;
    ChangeSalaryBaseBizActivityLog changeSalaryBaseBizActivityLog = new ChangeSalaryBaseBizActivityLog();
    try {

      chgPaymentSalaryBase = chgPaymentSalaryBaseDAO
          .queryById(new Integer(pkid));

      if (false) {
        be = new BusinessException("��ְ���Ѿ��������ʻ�����������Ϊ���ã�");
      }

      else {

        // ����AA002����λ�ɶ� ְ���ɶ� ���ʻ���
        // ����AA202�����״̬=2.������ ��������=����¼��ĵ�������
        String orgid = chgPaymentSalaryBase.getOrg().getId().toString();
        Org org = new Org();
        org = orgDAO.queryById(new Integer(orgid.toString()));
        List list = empDAO.getEmpListWuhtPayStatus(orgid);
        int count = 0;
        BigDecimal money = new BigDecimal("0.00");
        for (int i = 0; i < list.size(); i++) {
          Emp emp_1 = (Emp) list.get(i);
          money.add(emp_1.getEmpPay().add(emp_1.getOrgPay()));
        }
        if (list != null && list.size() > 0) {
          count = list.size();
        }

        chgPaymentPaymentDAO.updateAA202(String.valueOf(count), money
            .toString(), pkid);

      }
    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw be;
      }
    }
  }
}
