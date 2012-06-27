package org.xpup.hafmis.syscollection.chgbiz.chgorgrate.business;

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
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.bsinterface.IChgorgrateBS;
import org.xpup.hafmis.syscollection.common.dao.ChgOrgRateBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgOrgRateDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgOrgRateTailDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentSalaryBaseDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPersonHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.MonthPaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgChgDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgEditionDAO;
import org.xpup.hafmis.syscollection.common.daoDW.ChgOrgRateDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.EmpDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.OrgDAODW;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRateBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRateTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgChg;
import org.xpup.hafmis.syscommon.arithmetic.ArithmeticFactory;
import org.xpup.hafmis.syscommon.arithmetic.ArithmeticInterface;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;

/**
 * @author ���� 2007-6-27
 */
public class ChgorgrateBS implements IChgorgrateBS {

  private ChgOrgRateDAO chgOrgRateDAO = null;

  private ChgOrgRateDAODW chgOrgRateDAODW = null;

  private OrgChgDAO orgChgDAO = null;

  private OrgDAO orgDAO = null;

  private EmpDAO empDAO = null;

  private ChgPaymentPaymentDAO chgPaymentPaymentDAO = null;

  private ChgPaymentSalaryBaseDAO chgPaymentSalaryBaseDAO = null;

  private ChgPersonHeadDAO chgPersonHeadDAO = null;

  private MonthPaymentHeadDAO monthPaymentHeadDAO = null;

  private HafOperateLogDAO hafOperateLogDAO = null;

  private ChgOrgRateBizActivityLogDAO chgOrgRateBizActivityLogDAO = null;

  private OrgEditionDAO orgEditionDAO = null;// �������ݿ�da002

  private OrgDAODW orgDAODW = null; // ��λ��aa001

  private EmpDAODW empDAODW = null;// ��λ���ݿ�aa002

  private ChgOrgRateTailDAO chgOrgRateTailDAO = null;

  public void setChgOrgRateTailDAO(ChgOrgRateTailDAO chgOrgRateTailDAO) {
    this.chgOrgRateTailDAO = chgOrgRateTailDAO;
  }

  public void setOrgChgDAO(OrgChgDAO orgChgDAO) {
    this.orgChgDAO = orgChgDAO;
  }

  public void setChgPersonHeadDAO(ChgPersonHeadDAO chgPersonHeadDAO) {
    this.chgPersonHeadDAO = chgPersonHeadDAO;
  }

  public void setChgOrgRateDAODW(ChgOrgRateDAODW chgOrgRateDAODW) {
    this.chgOrgRateDAODW = chgOrgRateDAODW;
  }

  public void setMonthPaymentHeadDAO(MonthPaymentHeadDAO monthPaymentHeadDAO) {
    this.monthPaymentHeadDAO = monthPaymentHeadDAO;
  }

  public void setChgOrgRateDAO(ChgOrgRateDAO chgOrgRateDAO) {
    this.chgOrgRateDAO = chgOrgRateDAO;
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

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }

  public void setChgOrgRateBizActivityLogDAO(
      ChgOrgRateBizActivityLogDAO chgOrgRateBizActivityLogDAO) {
    this.chgOrgRateBizActivityLogDAO = chgOrgRateBizActivityLogDAO;
  }

  public void setEmpDAODW(EmpDAODW empDAODW) {
    this.empDAODW = empDAODW;
  }

  public void setOrgDAODW(OrgDAODW orgDAODW) {
    this.orgDAODW = orgDAODW;
  }

  public void setOrgEditionDAO(OrgEditionDAO orgEditionDAO) {
    this.orgEditionDAO = orgEditionDAO;
  }

  /**
   * ���ݵ�λID ��ѯ��λӦ�ɽ��
   */
  public int queryPersonCountByOrgID(String orgID) throws BusinessException,
      Exception {
    return empDAO.queryPersonCountByOrgID(orgID);
  }

  public void saveOrgChg(OrgChg orgChg) throws BusinessException {
    orgChgDAO.insert(orgChg);
  }

  /**
   * �жϸõ�λ�Ƿ��ǰ��ʽɴ�
   */
  public ChgOrgRate checkOrgMessage(String orgID, SecurityInfo securityInfo)
      throws BusinessException, Exception {
    // TODO Auto-generated method stub
    ChgOrgRate chgOrgRate = null;
    try {
      Org org = null;
      org = orgDAO.queryByCriterions(orgID, null, null, securityInfo);

      if (org == null) {
        throw new BusinessException(" �����ڸõ�λ��λ����Ȩ�޷�Χ֮�ڣ���");
      } else if (org.getPayMode().equals("2")) {
        throw new BusinessException("�õ�λ���ǰ��ʽɴ棬���ܽ��л�ɱ�������ҵ�񣡣�");
      }

      // AA201���Ƿ��õ�λδ�����õĻ�ɱ�����Ϣ
      String status = "1";
      chgOrgRate = chgOrgRateDAO.getOrgRateMessage_WL(orgID, status);
      if (chgOrgRate == null) {
        // AA001���Ƿ�����������������ļ�¼
        Org returnOrg = orgDAO.getOrgByIDStatus_WL(orgID);
        if (returnOrg == null) {
          throw new BusinessException("�õ�λ���ܽ��л�ɱ���������");
        } else {
          // �Ƿ񲻴���δ���õ��������͵ı��
          boolean chgRateStratus = chgPersonHeadDAO.getChgStatus(new Integer(
              orgID));
          if (chgRateStratus == false) {
            throw new BusinessException("�õ�λ��δ���õ���Ա���!!");
          }
          boolean chgPaymentPaymentStratus = chgPaymentPaymentDAO
              .getChgStatus(new Integer(orgID));
          if (chgPaymentPaymentStratus == false) {
            throw new BusinessException("�õ�λ��δ���õĽɶ����!!");
          }
          boolean chgPaymentSalaryBaseStratus = chgPaymentSalaryBaseDAO
              .getChgStatus(new Integer(orgID));
          if (chgPaymentSalaryBaseStratus == false) {
            throw new BusinessException("�õ�λ��δ���õĹ��ʻ�������!!");
          }
        }
      }

      // ���Խ��л�ɱ�������ҵ��ģ�����ҳ����ʾ��Ϣ�Ĳ�ѯ
      // ��ѯ������£��Ȳ�AA201���޾Ͳ�AA302�����ٲ�AA001�ĳ�������
      String date = null;
      Org returnOrg = orgDAO.queryById(new Integer(orgID));
      if (chgOrgRate != null) {

        date = chgOrgRate.getChgMonth();
        if (date == null || date.equals("")) {
          date = monthPaymentHeadDAO.getOrgMonthPayment(orgID);
          if (date == null || date.equals("")) {
            date = BusiTools.addMonth(returnOrg.getOrgPayMonth(), 1);
          } else {
            date = BusiTools.addMonth(date, 1);
          }
        }
      } else {
        chgOrgRate = new ChgOrgRate();
        chgOrgRate.setId("");

        date = monthPaymentHeadDAO.getOrgMonthPayment(orgID);
        if (date == null || date.equals("")) {
          date = BusiTools.addMonth(returnOrg.getOrgPayMonth(), 1);
        } else {
          date = BusiTools.addMonth(date, 1);
        }
        chgOrgRate.setOrg(returnOrg);
        chgOrgRate.setPreOrgRate(returnOrg.getOrgRate());
        chgOrgRate.setPreEmpRate(returnOrg.getEmpRate());
        chgOrgRate.setOrgRate(new BigDecimal(0.00));
        chgOrgRate.setEmpRate(new BigDecimal(0.00));

        BigDecimal oldPayment = empDAO.getOldPayment_WL(returnOrg.getId()
            .toString());
        if (oldPayment == null) {
          chgOrgRate.setPreSumPay(new BigDecimal(0.00));
        } else {
          chgOrgRate.setPreSumPay(oldPayment);
        }

      }
      chgOrgRate.setChgMonth(date);// ��Ÿõ�λ�ı������

      BigDecimal empSalary = empDAO
          .queryEmpSalary(returnOrg.getId().toString());// �õ�λ�µ�ְ�����ʻ���
      chgOrgRate.setSalarybase(empSalary);

    } catch (BusinessException be) {
      be.printStackTrace();
      throw be;
    }
    // yqf 20080927 �� ��λ��Ų�10λ
    Integer tempId = (Integer) chgOrgRate.getOrg().getId();
    String sid = BusiTools.convertTenNumber(tempId.toString());
    chgOrgRate.getOrg().setSid(sid);
    return chgOrgRate;
  }

  /**
   * ����ɱ�������ʱ����ѯ������£��Ȳ�AA201���޾Ͳ�AA302�����ٲ�AA001�ĳ�������
   */
  public String getChgMonth(ChgOrgRate chgOrgRate, String orgID)
      throws BusinessException, Exception {

    String date = null;
    date = chgOrgRate.getChgMonth();
    if (date == null || date.equals("")) {
      date = monthPaymentHeadDAO.getOrgMonthPayment(orgID);
      if (date == null || date.equals("")) {
        Org returnOrg = orgDAO.queryById(new Integer(orgID));
        date = BusiTools.addMonth(returnOrg.getOrgPayMonth(), 1);
      } else {
        date = BusiTools.addMonth(date, 1);
      }
    }
    return date;

  }

  /**
   * ���ݵ�λID ��ѯ��λ��Ϣ
   */
  public Org queryOrgByorgID(String orgID) throws BusinessException, Exception {
    // TODO Auto-generated method stub

    Org org = null;
    org = orgDAO.getOrg_WL(orgID);
    if (org == null) {
      return null;
    }
    BigDecimal empSalary = empDAO.queryEmpSalary(org.getId().toString());// �õ�λ�µ�ְ�����ʻ���
    org.setTemp_salary(empSalary);
    // yqf 20080927 �� ��λ��Ų�10λ
    String tempid = org.getId().toString();
    String sid = BusiTools.convertTenNumber(tempid);
    org.setSid(sid);
    return org;
  }

  /**
   * ���ݵ�λID ��ѯ��λ��Ϣ
   */
  public Org queryOrgByorgIDYg(String orgID, SecurityInfo securityInfo)
      throws BusinessException, Exception {
    // TODO Auto-generated method stub

    Org org = null;
    org = orgDAO.getOrg_YG(orgID, securityInfo);
    if (org == null) {
      return null;
    }
    BigDecimal empSalary = empDAO.queryEmpSalary(org.getId().toString());// �õ�λ�µ�ְ�����ʻ���
    org.setTemp_salary(empSalary);
    // yqf 20080927 �� ��λ��Ų�10λ
    String tempid = org.getId().toString();
    String sid = BusiTools.convertTenNumber(tempid);
    org.setSid(sid);
    return org;
  }

  /**
   * ���ݵ�λID ��ѯ��λӦ�ɽ��
   */
  public BigDecimal querySumPayByOrgID(String orgID) throws BusinessException,
      Exception {
    // TODO Auto-generated method stub

    BigDecimal sumPay = new BigDecimal(0.00);
    sumPay = empDAO.querySumPayByOrgID_WL(orgID);
    if (sumPay == null) {
      sumPay = new BigDecimal(0.00);
    }
    return sumPay;
  }

  /**
   * ����AA201����������¼��
   */
  public BigDecimal saveChgOrgRate(ChgOrgRate chgOrgRate,
      SecurityInfo securityInfo) throws BusinessException, Exception {

    String ip = securityInfo.getUserInfo().getUserIp();
    String oper = securityInfo.getUserInfo().getUsername();
    String chgOrgRateHeadID = "";
    ChgOrgRate returnChgOrgRate = chgOrgRateDAO.getOrgRateMessage_WL(chgOrgRate
        .getOrg().getId().toString(), "1");
    // BigDecimal empSalary =
    // empDAO.queryEmpSalary(chgOrgRate.getOrg().getId().toString());//�õ�λ�µ�ְ�����ʻ���

    Org returnOrg = orgDAO.getOrg_WL(chgOrgRate.getOrg().getId().toString());
    // �ɴ澫��ID:payPrecision
    int payPrecision = Integer.parseInt(returnOrg.getPayPrecision().toString());
    // ���ýɴ澫��
    ArithmeticInterface arithmeticInterface = ArithmeticFactory.getArithmetic()
        .getArithmeticDAO(payPrecision);
    List empList = empDAO.getExportData_wsh(Integer.parseInt(chgOrgRate
        .getOrg().getId().toString()));
    BigDecimal orgPay = new BigDecimal(0.00);
    BigDecimal empPay = new BigDecimal(0.00);

    if (returnChgOrgRate != null) {// �޸�AA201
      returnChgOrgRate.setOrgRate(chgOrgRate.getOrgRate());
      returnChgOrgRate.setEmpRate(chgOrgRate.getEmpRate());
      returnChgOrgRate.setPreOrgRate(chgOrgRate.getPreOrgRate());
      returnChgOrgRate.setPreEmpRate(chgOrgRate.getPreEmpRate());

      try {
        // ��������ɶ�
        List list = this.queryEmpSalaryByTIAOJIAN(returnChgOrgRate.getOrg()
            .getId().toString());
        for (int i = 0; i < list.size(); i++) {
          BigDecimal salarybase = (BigDecimal) list.get(i);
          orgPay = orgPay.add(arithmeticInterface.getPay(salarybase,
              returnChgOrgRate.getOrgRate()));
          empPay = empPay.add(arithmeticInterface.getPay(salarybase,
              returnChgOrgRate.getEmpRate()));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      returnChgOrgRate.setOrgPay(orgPay);// ������λӦ�ɶ�
      returnChgOrgRate.setEmpPay(empPay);// ������ְ��Ӧ�ɶ�

      returnChgOrgRate.setOrgRate(chgOrgRate.getOrgRate());// ��λ����
      returnChgOrgRate.setEmpRate(chgOrgRate.getEmpRate());// ְ������
      returnChgOrgRate.setChgMonth(chgOrgRate.getChgMonth());// ��������
      chgOrgRateHeadID = chgOrgRateDAO.insert(returnChgOrgRate).toString();
      chgOrgRateTailDAO.updateChgOrgRateTail_wsh(returnChgOrgRate.getId()
          .toString(), returnChgOrgRate.getOrgRate().toString(),
          returnChgOrgRate.getEmpRate().toString(), null, null, securityInfo);
      // if(empList!=null){
      // for (int i = 0; i < empList.size(); i++) {
      // ChgOrgRateTail chgOrgRateTail=new ChgOrgRateTail();
      // chgOrgRateTail.setChgOrgRate(returnChgOrgRate);
      // chgOrgRateTail.setEmp((Emp)empList.get(i));
      // chgOrgRateTail.setEmpId(((Emp)empList.get(i)).getEmpId());
      // chgOrgRateTail.setPayStatus(Integer.valueOf(((Emp)empList.get(i)).getPayStatus().toString()));
      // chgOrgRateTail.setOldEmpRate(chgOrgRate.getPreEmpRate());
      // chgOrgRateTail.setOldOrgRate(chgOrgRate.getPreOrgRate());
      // chgOrgRateTail.setEmpRate(chgOrgRate.getEmpRate());
      // chgOrgRateTail.setOrgRate(chgOrgRate.getOrgRate());
      // chgOrgRateTailDAO.insert(chgOrgRateTail);
      // }
      // }
    } else {// ���AA201
      returnChgOrgRate = new ChgOrgRate();
      // ��λ���
      Org org = orgDAO.getOrg_WL(chgOrgRate.getOrg().getId().toString());
      returnChgOrgRate.setOrg(org);
      Integer count = empDAO.queryEmpCount(chgOrgRate.getOrg().getId()
          .toString());// ��������
      returnChgOrgRate.setChgPersonCount(count);
      returnChgOrgRate.setOrgRate(chgOrgRate.getOrgRate());
      returnChgOrgRate.setEmpRate(chgOrgRate.getEmpRate());
      returnChgOrgRate.setPreOrgRate(org.getOrgRate());
      returnChgOrgRate.setPreEmpRate(org.getEmpRate());
      BigDecimal oldOrgPay = empDAO.queryOrgpay(chgOrgRate.getOrg().getId()
          .toString());// ����ǰ��λ�µĵ�λӦ�ɶ�
      BigDecimal oldEmpPay = empDAO.queryEmppay(chgOrgRate.getOrg().getId()
          .toString());// ����ǰ��λ�µ�ְ��Ӧ�ɶ�
      returnChgOrgRate.setOldOrgPay(oldOrgPay);
      returnChgOrgRate.setOldEmpPay(oldEmpPay);

      try {
        // ��������ɶ�
        List list = this.queryEmpSalaryByTIAOJIAN(org.getId().toString());
        for (int i = 0; i < list.size(); i++) {
          BigDecimal salarybase = (BigDecimal) list.get(i);
          orgPay = orgPay.add(arithmeticInterface.getPay(salarybase, chgOrgRate
              .getOrgRate()));
          empPay = empPay.add(arithmeticInterface.getPay(salarybase, chgOrgRate
              .getEmpRate()));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      returnChgOrgRate.setOrgPay(orgPay);// ������λӦ�ɶ�
      returnChgOrgRate.setEmpPay(empPay);// ������ְ��Ӧ�ɶ�

      returnChgOrgRate.setChgStatus(new Integer(1));// ���״̬��δ����
      returnChgOrgRate.setChgMonth(chgOrgRate.getChgMonth());// ��������
      returnChgOrgRate.setBizDate(securityInfo.getUserInfo().getBizDate());// ҵ������
      chgOrgRateHeadID = chgOrgRateDAO.insert(returnChgOrgRate).toString();
      if (empList != null) {
        for (int i = 0; i < empList.size(); i++) {
          ChgOrgRateTail chgOrgRateTail = new ChgOrgRateTail();
          chgOrgRateTail.setChgOrgRate(returnChgOrgRate);
          chgOrgRateTail.setEmp((Emp) empList.get(i));
          chgOrgRateTail.setEmpId(((Emp) empList.get(i)).getEmpId());
          chgOrgRateTail.setPayStatus(Integer.valueOf(((Emp) empList.get(i))
              .getPayStatus().toString()));
          chgOrgRateTail.setSalaryBase(((Emp) empList.get(i)).getSalaryBase());
          chgOrgRateTail.setOldEmpPay(((Emp) empList.get(i)).getEmpPay());
          chgOrgRateTail.setOldOrgPay(((Emp) empList.get(i)).getOrgPay());
          chgOrgRateTail.setEmpPay(arithmeticInterface.getPay(((Emp) empList
              .get(i)).getSalaryBase(), chgOrgRate.getEmpRate()));
          chgOrgRateTail.setOrgPay(arithmeticInterface.getPay(((Emp) empList
              .get(i)).getSalaryBase(), chgOrgRate.getOrgRate()));

          chgOrgRateTail.setOldEmpRate(chgOrgRate.getPreEmpRate());
          chgOrgRateTail.setOldOrgRate(chgOrgRate.getPreOrgRate());
          chgOrgRateTail.setEmpRate(chgOrgRate.getEmpRate());
          chgOrgRateTail.setOrgRate(chgOrgRate.getOrgRate());
          chgOrgRateTailDAO.insert(chgOrgRateTail);
        }
      }
      // ����AA319
      ChgOrgRateBizActivityLog chgOrgRateBizActivityLog = new ChgOrgRateBizActivityLog();
      chgOrgRateBizActivityLog.setBizid(new Integer(chgOrgRateHeadID));
      chgOrgRateBizActivityLog
          .setAction(new Integer(BusiConst.BUSINESSSTATE_2));
      chgOrgRateBizActivityLog.setOpTime(new Date());
      chgOrgRateBizActivityLog.setOperator(oper);
      chgOrgRateBizActivityLogDAO.insert(chgOrgRateBizActivityLog);

    }

    // ����BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_CHANGE_CHGRATE_DO);
    hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_ADD);
    hafOperateLog.setOpBizId(new Integer(chgOrgRateHeadID));
    hafOperateLog.setOpIp(ip);
    hafOperateLog.setOrgId(new Integer(returnChgOrgRate.getOrg().getId()
        .toString()));
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOperator(oper);
    hafOperateLogDAO.insert(hafOperateLog);

    return returnChgOrgRate.getOrgPay().add(returnChgOrgRate.getEmpPay());
  }

  /**
   * ����AA201�����������޸�
   */
  public BigDecimal updateChgOrgRate(ChgOrgRate chgOrgRate,
      SecurityInfo securityInfo) throws BusinessException, Exception {

    String ip = securityInfo.getUserInfo().getUserIp();
    String oper = securityInfo.getUserInfo().getUsername();
    String chgOrgRateHeadID = "";
    ChgOrgRate returnChgOrgRate = chgOrgRateDAO.getOrgRateMessage_WL(chgOrgRate
        .getOrg().getId().toString(), "1");
    BigDecimal empSalary = empDAO.queryEmpSalary(chgOrgRate.getOrg().getId()
        .toString());// �õ�λ�µ�ְ�����ʻ���

    Org returnOrg = orgDAO.getOrg_WL(chgOrgRate.getOrg().getId().toString());
    // �ɴ澫��ID:payPrecision
    int payPrecision = Integer.parseInt(returnOrg.getPayPrecision().toString());
    // ���ýɴ澫��
    ArithmeticInterface arithmeticInterface = ArithmeticFactory.getArithmetic()
        .getArithmeticDAO(payPrecision);

    // �޸�AA201
    returnChgOrgRate.setOrgRate(chgOrgRate.getOrgRate());
    returnChgOrgRate.setEmpRate(chgOrgRate.getEmpRate());
    returnChgOrgRate.setPreOrgRate(chgOrgRate.getPreOrgRate());
    returnChgOrgRate.setPreEmpRate(chgOrgRate.getPreEmpRate());
    returnChgOrgRate.setOrgPay(arithmeticInterface.getPay(empSalary, chgOrgRate
        .getOrgRate()));// ������λӦ�ɶ�
    returnChgOrgRate.setEmpPay(arithmeticInterface.getPay(empSalary, chgOrgRate
        .getEmpRate()));// ������ְ��Ӧ�ɶ�
    returnChgOrgRate.setOrgRate(chgOrgRate.getOrgRate());// ��λ����
    returnChgOrgRate.setEmpRate(chgOrgRate.getEmpRate());// ְ������
    returnChgOrgRate.setChgMonth(chgOrgRate.getChgMonth());// ��������
    chgOrgRateHeadID = chgOrgRateDAO.insert(returnChgOrgRate).toString();

    chgOrgRateTailDAO.updateChgOrgRateTail_wsh(returnChgOrgRate.getId()
        .toString(), returnChgOrgRate.getOrgRate().toString(), returnChgOrgRate
        .getEmpRate().toString(), null, null, securityInfo);
    // ����BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_CHANGE_CHGRATE_MAINTAIN);
    hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_MODIFY);
    hafOperateLog.setOpBizId(new Integer(chgOrgRateHeadID));
    hafOperateLog.setOpIp(ip);
    hafOperateLog.setOrgId(new Integer(returnChgOrgRate.getOrg().getId()
        .toString()));
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOperator(oper);
    hafOperateLogDAO.insert(hafOperateLog);

    return returnChgOrgRate.getOrgPay().add(returnChgOrgRate.getEmpPay());
  }

  /**
   * ��ѡɾ��
   */
  public void deleteChgOrgRate(Integer id, SecurityInfo securityInfo)
      throws BusinessException {
    try {

      String ip = securityInfo.getUserInfo().getUserIp();
      String oper = securityInfo.getUserInfo().getUsername();

      // ɾ��AA201
      chgOrgRateTailDAO.deleteChgTail(id);
      ChgOrgRate chgOrgRate = chgOrgRateDAO.queryById(id);
      chgOrgRateDAO.delete_WL(chgOrgRate);

      // ɾ��AA319
      ChgOrgRateBizActivityLog chgOrgRateBizActivityLog = chgOrgRateBizActivityLogDAO
          .queryChgOrgRateBiz_WL(chgOrgRate.getId().toString(), ""
              + BusiConst.BUSINESSSTATE_2);
      chgOrgRateBizActivityLogDAO.delete_WL(chgOrgRateBizActivityLog);

      // ����BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_CHANGE_CHGPERSON_DO);
      hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_DELETE);
      hafOperateLog.setOpBizId(new Integer(chgOrgRate.getId().toString()));
      hafOperateLog.setOpIp(ip);
      hafOperateLog
          .setOrgId(new Integer(chgOrgRate.getOrg().getId().toString()));
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOperator(oper);
      hafOperateLogDAO.insert(hafOperateLog);

    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("ɾ��ʧ��!");
    }
  }

  /**
   * ������������
   */
  // ������޸�2008��3��18 �������еĲ��뵥λ�����Ϣɾ��
  public void useChgOrgRate(ChgOrgRate chgOrgRate, SecurityInfo securityInfo)
      throws BusinessException, Exception {

    String ip = securityInfo.getUserInfo().getUserIp();
    String oper = securityInfo.getUserInfo().getUsername();
    ChgOrgRate returnChgOrgRate = chgOrgRateDAO.getOrgRateMessage_WL(chgOrgRate
        .getOrg().getId().toString(), "1");
    try {
      // �ж�ά���е�����Ҫ�жϸñʼ�¼��״̬�Ƿ����1����������1����ʾ�ñ�ҵ��������
      if (returnChgOrgRate == null) {
        throw new BusinessException("�ñ�ҵ�������ã���");
      }

      // ����AA201
      returnChgOrgRate.setChgMonth(chgOrgRate.getChgMonth());
      returnChgOrgRate.setChgStatus(new Integer(2));
      // String id=chgOrgRateDAO.insert(returnChgOrgRate).toString();

      // ����AA001
      Org org = orgDAO.getOrg_WL(chgOrgRate.getOrg().getId().toString());
      org.setOrgRate(chgOrgRate.getOrgRate());
      org.setEmpRate(chgOrgRate.getEmpRate());
      orgDAO.insert(org);

      // ����AA002
      List list = empDAO.getEmpList_WL(chgOrgRate.getOrg().getId().toString());
      Emp emp = null;

      // �ɴ澫��ID:payPrecision
      int payPrecision = Integer.parseInt(org.getPayPrecision().toString());
      // ���ýɴ澫��
      ArithmeticInterface arithmeticInterface = ArithmeticFactory
          .getArithmetic().getArithmeticDAO(payPrecision);
      int count = 0;
      BigDecimal money = new BigDecimal("0.00");
      for (int i = 0; i < list.size(); i++) {

        emp = (Emp) list.get(i);
        emp.setOrgPay(arithmeticInterface.getPay(emp.getSalaryBase(),
            chgOrgRate.getOrgRate()));
        emp.setEmpPay(arithmeticInterface.getPay(emp.getSalaryBase(),
            chgOrgRate.getEmpRate()));
        empDAO.insert(emp);
        if (emp.getPayStatus().compareTo(new BigDecimal("1")) == 0
            || emp.getPayStatus().compareTo(new BigDecimal("3")) == 0
            || emp.getPayStatus().compareTo(new BigDecimal("4")) == 0) {
          count++;
          money = money.add(emp.getEmpPay().add(emp.getOrgPay()));
        }
      }
      returnChgOrgRate.setReserveaA(String.valueOf(count));
      returnChgOrgRate.setReserveaB(money.toString());
      chgOrgRateDAO.insert(returnChgOrgRate);
      // ����AA319
      ChgOrgRateBizActivityLog chgOrgRateBizActivityLog = new ChgOrgRateBizActivityLog();
      chgOrgRateBizActivityLog.setBizid(new Integer(returnChgOrgRate.getId()
          .toString()));
      chgOrgRateBizActivityLog
          .setAction(new Integer(BusiConst.BUSINESSSTATE_3));
      chgOrgRateBizActivityLog.setOpTime(new Date());
      chgOrgRateBizActivityLog.setOperator(oper);
      chgOrgRateBizActivityLogDAO.insert(chgOrgRateBizActivityLog);

      // ����BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_CHANGE_CHGRATE_DO);
      hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_OPENING);
      hafOperateLog
          .setOpBizId(new Integer(returnChgOrgRate.getId().toString()));
      hafOperateLog.setOpIp(ip);
      hafOperateLog.setOrgId(new Integer(returnChgOrgRate.getOrg().getId()
          .toString()));
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOperator(oper);
      hafOperateLogDAO.insert(hafOperateLog);
      // ��λ��
      // int isOrgEdition = securityInfo.getIsOrgEdition();
      // if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER)// Ϊ���İ�
      // {
      // // �ж��Ƿ���ڵ�λ��
      // boolean flag = orgEditionDAO.findIsOrg(chgOrgRate.getOrg().getId()
      // .toString());
      // // flagΪtrue���ڵ�λ��
      // if (flag) {
      // ChgOrgRate returnChgOrgRate2=new ChgOrgRate();
      //      
      //
      // returnChgOrgRate2.setOrg(returnChgOrgRate.getOrg()) ;
      // returnChgOrgRate2.setChgMonth(returnChgOrgRate.getChgMonth()) ;
      // returnChgOrgRate2.setOrgRate(returnChgOrgRate.getOrgRate()) ;
      // returnChgOrgRate2.setEmpRate(returnChgOrgRate.getEmpRate()) ;
      // returnChgOrgRate2.setChgPersonCount(returnChgOrgRate.getChgPersonCount())
      // ;
      // returnChgOrgRate2.setOldOrgPay(returnChgOrgRate.getOldOrgPay()) ;
      // returnChgOrgRate2.setOldEmpPay(returnChgOrgRate.getOldEmpPay()) ;
      // returnChgOrgRate2.setOrgPay(returnChgOrgRate.getOrgPay()) ;
      // returnChgOrgRate2.setEmpPay(returnChgOrgRate.getEmpPay()) ;
      // returnChgOrgRate2.setBizDate(returnChgOrgRate.getBizDate()) ;
      // returnChgOrgRate2.setChgStatus(returnChgOrgRate.getChgStatus()) ;
      // returnChgOrgRate2.setPaymentHead(returnChgOrgRate.getPaymentHead()) ;
      // returnChgOrgRate2.setReserveaA(returnChgOrgRate.getReserveaA()) ;
      // returnChgOrgRate2.setReserveaB(returnChgOrgRate.getReserveaB()) ;
      // returnChgOrgRate2.setReserveaC(returnChgOrgRate.getReserveaC()) ;
      // returnChgOrgRate2.setPreEmpRate(returnChgOrgRate.getPreEmpRate()) ;
      // returnChgOrgRate2.setPreOrgRate(returnChgOrgRate.getPreOrgRate()) ;
      // returnChgOrgRate2.setPreSumPay(returnChgOrgRate.getPreSumPay()) ;
      // returnChgOrgRate2.setSumPay(returnChgOrgRate.getSumPay()) ;
      // returnChgOrgRate2.setTemp_chgStatus(returnChgOrgRate.getTemp_chgStatus())
      // ;
      // returnChgOrgRate2.setSalarybase(returnChgOrgRate.getSalarybase()) ;
      // returnChgOrgRate2.setSumPreSumPay(returnChgOrgRate.getSumPreSumPay()) ;
      // returnChgOrgRate2.setSumSumPay(returnChgOrgRate.getSumSumPay()) ;
      // returnChgOrgRate2.setChgStatus_(returnChgOrgRate.getChgStatus_()) ;
      // returnChgOrgRate2.setRate(returnChgOrgRate.getRate()) ;
      // returnChgOrgRate2.setRate_(returnChgOrgRate.getRate_());
      //
      // System.out.println(returnChgOrgRate2+"----------------returnChgOrgRate2");
      // System.out.println("----------------id2");
      // orgDAODW.insert(returnChgOrgRate2);
      //    
      //
      // // ���µ�λ��AA001
      // Org orgdw = orgDAODW
      // .getOrg_WL(chgOrgRate.getOrg().getId().toString());
      // orgdw.setOrgRate(chgOrgRate.getOrgRate());
      // orgdw.setEmpRate(chgOrgRate.getEmpRate());
      //    
      // orgDAODW.insertOrg(orgdw);
      //  
      //
      // // ���µ�λ�����ݿ�AA002
      // List listdw = empDAODW.getEmpList_WL(chgOrgRate.getOrg().getId()
      // .toString());
      // Emp empdw = null;
      // // �ɴ澫��ID:payPrecision
      // int payPrecisiondw = Integer.parseInt(orgdw.getPayPrecision()
      // .toString());
      // // ���ýɴ澫��
      // ArithmeticInterface arithmeticInterfacedw = ArithmeticFactory
      // .getArithmetic().getArithmeticDAO(payPrecisiondw);
      // for (int i = 0; i < listdw.size(); i++) {
      // empdw = (Emp) listdw.get(i);
      // empdw.setOrgPay(arithmeticInterfacedw.getPay(empdw.getSalaryBase(),
      // chgOrgRate.getOrgRate()));
      // //
      // System.out.println(arithmeticInterfacedw.getPay(empdw.getSalaryBase(),chgOrgRate.getOrgRate())+"arithmeticInterfacedw.getPay(empdw.getSalaryBase(),chgOrgRate.getOrgRate())");
      // empdw.setEmpPay(arithmeticInterfacedw.getPay(empdw.getSalaryBase(),
      // chgOrgRate.getEmpRate()));
      // //
      // System.out.println(arithmeticInterfacedw.getPay(empdw.getSalaryBase(),chgOrgRate.getEmpRate())+"arithmeticInterfacedw.getPay(empdw.getSalaryBase(),chgOrgRate.getEmpRate())");
      // empDAODW.insert(empdw);
      // }
      // }
      // }
    } catch (BusinessException be) {
      throw be;
    }
  }

  /**
   * ���� ά�� ��һ�ν���ʱȡ���б���Ϣ
   */
  public List findChgpersonMaintainList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = null;
    BusinessException be = null;
    try {
      list = new ArrayList();

      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();

      list = chgOrgRateDAO.getChgOrgRateList_WL(orderBy, order, start,
          pageSize, page, securityInfo);
      ChgOrgRate chgOrgRate = null;
      if (list.size() != 0 || list != null) {
        for (int i = 0; i < list.size(); i++) {
          chgOrgRate = (ChgOrgRate) list.get(i);
          // ת��
          chgOrgRate.setTemp_chgStatus(BusiTools.getBusiValue(Integer
              .parseInt(chgOrgRate.getChgStatus().toString()),
              BusiConst.CHGTYPESTATUS));
          // chgOrgRate.getOrg().setId("0"+chgOrgRate.getOrg().getId().toString());
          // System.out.println("orgid=="+chgOrgRate.getOrg().getId());
        }
      }

      int count = chgOrgRateDAO.queryChgorgrateMaintainList(securityInfo);
      pagination.setNrOfElements(count);

      return list;
    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw be;
      }
    }
  }

  /**
   * ���ά��������������ѯ�б���Ϣ
   */
  public List findChgorgrateMaintainListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    List list = null;
    BusinessException be = null;
    try {
      list = new ArrayList();

      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      String orgID = (String) pagination.getQueryCriterions().get("orgID");
      String orgName = (String) pagination.getQueryCriterions().get("orgName");
      String chgDate = (String) pagination.getQueryCriterions().get("chgDate");
      String endDate = (String) pagination.getQueryCriterions().get("endDate");
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();

      list = chgOrgRateDAO
          .getChgOrgRateListByCriterions_wsh(orderBy, order, start, pageSize,
              orgID, orgName, chgDate, endDate, page, securityInfo);
      ChgOrgRate chgOrgRate = null;
      if (list.size() != 0 || list != null) {
        for (int i = 0; i < list.size(); i++) {
          chgOrgRate = (ChgOrgRate) list.get(i);
          // ת��
          chgOrgRate.setTemp_chgStatus(BusiTools.getBusiValue(Integer
              .parseInt(chgOrgRate.getChgStatus().toString()),
              BusiConst.CHGTYPESTATUS));
          // chgOrgRate.getOrg().setId("0"+chgOrgRate.getOrg().getId().toString());
        }
      }

      int count = chgOrgRateDAO.queryChgorgrateMaintainListByCriterions_wsh(
          orgID, orgName, chgDate, endDate, securityInfo);
      pagination.setNrOfElements(count);

      return list;
    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    } finally {
      if (be != null) {
        throw be;
      }
    }
  }

  /**
   * ����ͷ��ID ��ѯAA201����Ϣ
   */
  public ChgOrgRate queryChgorgrateMessage(String id) throws Exception,
      BusinessException {

    ChgOrgRate chgOrgRate = null;
    chgOrgRate = chgOrgRateDAO.queryById(new Integer(id));
    BigDecimal empSalary = empDAO.queryEmpSalary(chgOrgRate.getOrg().getId()
        .toString());// �õ�λ�µ�ְ�����ʻ���
    chgOrgRate.setSalarybase(empSalary);
    String tempid = chgOrgRate.getOrg().getId().toString();
    String sid = BusiTools.convertTenNumber(tempid);
    chgOrgRate.getOrg().setSid(sid);
    return chgOrgRate;

  }

  /**
   * ����AA201ͷ��ID��������У��,�Ƿ���Խ���ҵ����
   */
  public void checkDelUseMessage(String chgorgrateID, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    try {
      ChgOrgRate chgOrgRate = chgOrgRateDAO
          .queryById(new Integer(chgorgrateID));

      // �ж�ά���е�����Ҫ�жϸñʼ�¼��״̬�Ƿ����1����������1����ʾ�ñ�ҵ��������
      if (chgOrgRate.getChgStatus().toString().equals("1")) {
        throw new BusinessException("�ñ�ҵ���ѳ�������");
      }

      // �ñʱ�����Ƿ�����������
      Integer maxHeadID = chgPersonHeadDAO.getMaxHeadID_WL(chgOrgRate.getOrg()
          .getId().toString());
      int temp_ID = (maxHeadID.toString()).compareTo(chgorgrateID);
      if (temp_ID > 0) {
        throw new BusinessException("���ȳ�������ı����");
      }
      // �ñʱ���Ƿ񱻻��Ӧ��
      if (chgOrgRate.getPaymentHead() != null) {
        throw new BusinessException("���ȳ���Ӧ�øñʱ���Ļ�ɣ�");
      }
      // �õ�λ���Ƿ����û�����õ���Ա���
      boolean flag = chgOrgRateDAO.getChgStatus(new Integer(chgOrgRate.getOrg()
          .getId().toString()));
      if (flag == false) {
        throw new BusinessException("����δ���õĻ�ɱ����������������ã�");
      }
      // ��������
      this.deluserChgPersonMessage(chgorgrateID, securityInfo);

    } catch (BusinessException b) {
      b.printStackTrace();
      throw b;
    }
  }

  /**
   * ��������
   */
  public void deluserChgPersonMessage(String chgorgrateID,
      SecurityInfo securityInfo) throws BusinessException {
    try {
      String ip = securityInfo.getUserInfo().getUserIp();
      String oper = securityInfo.getUserInfo().getUsername();

      // ����AA201
      ChgOrgRate chgOrgRate = chgOrgRateDAO
          .queryById(new Integer(chgorgrateID));

      chgOrgRate.setChgStatus(new Integer(1));
      chgOrgRateDAO.insert(chgOrgRate);

      // ����AA001
      Org org = orgDAO.getOrg_WL(chgOrgRate.getOrg().getId().toString());
      org.setOrgRate(chgOrgRate.getPreOrgRate());
      org.setEmpRate(chgOrgRate.getPreEmpRate());
      orgDAO.insert(org);

      // ����AA002
      List list = empDAO.getEmpList_WL(chgOrgRate.getOrg().getId().toString());
      Emp emp = null;

      // �ɴ澫��ID:payPrecision
      int payPrecision = Integer.parseInt(org.getPayPrecision().toString());
      // ���ýɴ澫��
      ArithmeticInterface arithmeticInterface = ArithmeticFactory
          .getArithmetic().getArithmeticDAO(payPrecision);

      for (int i = 0; i < list.size(); i++) {
        emp = (Emp) list.get(i);
        emp.setOrgPay(arithmeticInterface.getPay(emp.getSalaryBase(),
            chgOrgRate.getPreOrgRate()));
        emp.setEmpPay(arithmeticInterface.getPay(emp.getSalaryBase(),
            chgOrgRate.getPreEmpRate()));
        empDAO.insert(emp);
      }

      // ɾ��AA319
      ChgOrgRateBizActivityLog chgOrgRateBizActivityLog = chgOrgRateBizActivityLogDAO
          .queryChgOrgRateBiz_WL(chgOrgRate.getId().toString(), ""
              + BusiConst.BUSINESSSTATE_3);
      chgOrgRateBizActivityLogDAO.delete_WL(chgOrgRateBizActivityLog);

      // ����BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_CHANGE_CHGPERSON_DO);
      hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_REVOCATION);
      hafOperateLog.setOpBizId(new Integer(chgOrgRate.getId().toString()));
      hafOperateLog.setOpIp(ip);
      hafOperateLog
          .setOrgId(new Integer(chgOrgRate.getOrg().getId().toString()));
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOperator(oper);
      hafOperateLogDAO.insert(hafOperateLog);

    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("��������ʧ��!");
    }

  }

  /**
   * ȡ��ְ��״̬Ϊ(1,3,4)�Ĺ��ʻ����б�
   */
  public List queryEmpSalaryByTIAOJIAN(String orgID) throws BusinessException {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    list = empDAO.queryEmpSalaryByTIAOJIAN_WL(orgID);
    return list;
  }

  // ������޸� 2008-3-18 ��λ_��ɱ�������
  public ChgOrgRate queryChgorgrate_OrgEdition(String orgId, String chgMonth,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    // ��λ��
    ChgOrgRate chgOrgRate = new ChgOrgRate();
    int isOrgEdition = securityInfo.getIsOrgEdition();
    try {
      if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG)// ��λ��{
      {
        List listPaymentHead = orgDAODW.queryPaymentHeadlist_wuht(orgId,
            chgMonth);// ��ѯAA301���ж�Ӧ��������¸õ�λ�������Ƿ���
        if (listPaymentHead != null && listPaymentHead.size() > 0) {
          List listChgOrgRate = orgDAODW.queryChgOrgRatelist_wuht(orgId,
              chgMonth);// ��ѯAA201���жϱ��û��Ӧ�õı���Ƿ��л�ɱ�������
          if (listChgOrgRate != null && listChgOrgRate.size() > 0) {
            ChgOrgRate chgOrgRate_new = (ChgOrgRate) listChgOrgRate
                .get(listChgOrgRate.size() - 1);
            chgOrgRate.setEmpRate(chgOrgRate_new.getEmpRate());
            chgOrgRate.setOrgRate(chgOrgRate_new.getOrgRate());

          }
        } else {
          List listChgOrgRate = orgDAODW.queryChgOrgRateList_wuht(orgId);// ��ѯAA201���жϸ��Ƿ����δ�����Ӧ�õĻ�ɱ���������¼
          if (listChgOrgRate != null && listChgOrgRate.size() > 0) {
            ChgOrgRate chgOrgRate_new = (ChgOrgRate) listChgOrgRate
                .get(listChgOrgRate.size() - 1);
            chgOrgRate.setEmpRate(chgOrgRate_new.getEmpRate());
            chgOrgRate.setOrgRate(chgOrgRate_new.getOrgRate());

          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return chgOrgRate;
  }

  public List queryOrgChgList(Pagination pagination, SecurityInfo securityInfo)
      throws BusinessException {
    String orgid = (String) pagination.getQueryCriterions().get("orgid");
    String status = (String) pagination.getQueryCriterions().get("status");
    String print = (String) pagination.getQueryCriterions().get("print");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    List list = new ArrayList();
    list = orgChgDAO.queryOrgChgList(orgid, status, print, orderBy, order,
        start, pageSize, securityInfo);
    return list;
  }

  public int queryOrgChgListAll(Pagination pagination, SecurityInfo securityInfo)
      throws BusinessException {
    String orgid = (String) pagination.getQueryCriterions().get("orgid");
    String status = (String) pagination.getQueryCriterions().get("status");
    String print = (String) pagination.getQueryCriterions().get("print");
    return orgChgDAO.queryOrgChgListAll(orgid, status, print, securityInfo);
  }

  public OrgChg queryOrgChgById(String id) throws BusinessException {
    OrgChg orgChg = orgChgDAO.queryById(new Integer(id));
    return orgChg;
  }

  public void deleteOrgChg(OrgChg orgChg) throws BusinessException {
    orgChgDAO.delete(orgChg);
  }

  public void submitOrgChgById(String id) throws BusinessException {
    OrgChg orgChg = orgChgDAO.queryById(new Integer(id));
    orgChg.setStatus("1");
  }

  public void delSubmitOrgChgById(String id) throws BusinessException {
    OrgChg orgChg = orgChgDAO.queryById(new Integer(id));
    orgChg.setStatus("0");
  }

  public void passOrgChgById(String id, SecurityInfo securityInfo)
      throws BusinessException {
    OrgChg orgChg = orgChgDAO.queryById(new Integer(id));
    orgChg.setPrint("1");
    orgChg.setPerson(securityInfo.getUserInfo().getRealName());
  }

  public void delPassOrgChgById(String id) throws BusinessException {
    OrgChg orgChg = orgChgDAO.queryById(new Integer(id));
    orgChg.setPrint("0");
    orgChg.setPerson("");
  }

  public void updateOrgChg(OrgChg orgChg) throws BusinessException {
    try {
      OrgChg orgChgs = orgChgDAO.queryById(new Integer(orgChg.getId()
          .toString()));
      orgChgs.setOrg(orgChg.getOrg());
      orgChgs.setAddCount(orgChg.getAddCount());
      orgChgs.setAddEmp(orgChg.getAddEmp());
      orgChgs.setAddMonth(orgChg.getAddMonth());
      orgChgs.setAddOrg(orgChg.getAddOrg());
      orgChgs.setAddStEndMonth(orgChg.getAddStEndMonth());
      orgChgs.setAddSum(orgChg.getAddSum());
      orgChgs.setNewPay(orgChg.getNewPay());
      orgChgs.setNewRate(orgChg.getNewRate());
      orgChgs.setPayRate(orgChg.getPayRate());
      orgChgs.setPaySalary(orgChg.getPaySalary());
      orgChgs.setPrePay(orgChg.getPrePay());
      orgChgs.setPreRate(orgChg.getPreRate());
      orgChgs.setMake(orgChg.getMake());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List queryOrgChgListCheck(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException {
    String orgid = (String) pagination.getQueryCriterions().get("orgid");
    String status = (String) pagination.getQueryCriterions().get("status");
    String print = (String) pagination.getQueryCriterions().get("print");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    List list = new ArrayList();
    list = orgChgDAO.queryOrgChgListCheck(orgid, status, print, orderBy, order,
        start, pageSize, securityInfo);
    return list;
  }

  public int queryOrgChgListAllCheck(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException {
    String orgid = (String) pagination.getQueryCriterions().get("orgid");
    String status = (String) pagination.getQueryCriterions().get("status");
    String print = (String) pagination.getQueryCriterions().get("print");
    return orgChgDAO
        .queryOrgChgListAllCheck(orgid, status, print, securityInfo);
  }
}
