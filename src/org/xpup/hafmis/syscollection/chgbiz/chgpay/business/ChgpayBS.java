package org.xpup.hafmis.syscollection.chgbiz.chgpay.business;

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
import org.xpup.hafmis.syscollection.chgbiz.chgpay.bsinterface.IChgpayBS;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.dto.ChgpayHeadImportDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.dto.ChgpayInfoDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.dto.ChgpayListImportDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.form.ChgpayListAF;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto.ChgslarybaseHeadImportDTO;
import org.xpup.hafmis.syscollection.common.dao.AutoInfoPickDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgOrgRateDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentSalaryBaseDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentTailDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPersonHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.PaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.daoDW.AutoInfoPickDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.ChgPaymentPaymentDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.ChgPersonHeadDAODW;
import org.xpup.hafmis.syscollection.common.domain.entity.AutoInfoPick;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentHead;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.PaymentHead;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;

/**
 * @author Administrator
 */
public class ChgpayBS implements IChgpayBS {

  protected OrgDAO orgDAO = null;

  protected AutoInfoPickDAO autoInfoPickDAO = null;

  protected AutoInfoPickDAODW autoInfoPickDAODW = null;

  protected ChgOrgRateDAO chgOrgRateDAO = null;

  protected ChgPersonHeadDAO chgPersonHeadDAO = null;

  protected ChgPaymentSalaryBaseDAO chgPaymentSalaryBaseDAO = null;

  protected ChgPaymentPaymentDAO chgPaymentPaymentDAO = null;

  protected ChgPaymentTailDAO chgPaymentTailDAO = null;

  protected ChgPaymentPaymentDAODW chgPaymentPaymentDAODW = null;

  protected ChgPaymentHeadDAO chgPaymentHeadDAO = null;

  protected EmpDAO empDAO = null;

  protected HafOperateLogDAO hafOperateLogDAO = null;

  protected ChgPaymentBizActivityLogDAO chgPaymentBizActivityLogDAO = null;

  protected PaymentHeadDAO paymentHeadDAO = null;

  protected ChgPersonHeadDAODW chgPersonHeadDAODW = null;

  private String center_head_id = "";

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setChgOrgRateDAO(ChgOrgRateDAO chgOrgRateDAO) {
    this.chgOrgRateDAO = chgOrgRateDAO;
  }

  public void setChgPersonHeadDAO(ChgPersonHeadDAO chgPersonHeadDAO) {
    this.chgPersonHeadDAO = chgPersonHeadDAO;
  }

  public void setChgPaymentSalaryBaseDAO(
      ChgPaymentSalaryBaseDAO chgPaymentSalaryBaseDAO) {
    this.chgPaymentSalaryBaseDAO = chgPaymentSalaryBaseDAO;
  }

  public void setChgPaymentPaymentDAO(ChgPaymentPaymentDAO chgPaymentPaymentDAO) {
    this.chgPaymentPaymentDAO = chgPaymentPaymentDAO;
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

  public ChgPaymentBizActivityLogDAO getChgPaymentBizActivityLogDAO() {
    return chgPaymentBizActivityLogDAO;
  }

  public void setChgPaymentBizActivityLogDAO(
      ChgPaymentBizActivityLogDAO chgPaymentBizActivityLogDAO) {
    this.chgPaymentBizActivityLogDAO = chgPaymentBizActivityLogDAO;
  }

  public void setAutoInfoPickDAO(AutoInfoPickDAO autoInfoPickDAO) {
    this.autoInfoPickDAO = autoInfoPickDAO;
  }

  public void setAutoInfoPickDAODW(AutoInfoPickDAODW autoInfoPickDAODW) {
    this.autoInfoPickDAODW = autoInfoPickDAODW;
  }

  public void setChgPaymentPaymentDAODW(
      ChgPaymentPaymentDAODW chgPaymentPaymentDAODW) {
    this.chgPaymentPaymentDAODW = chgPaymentPaymentDAODW;
  }

  public void setChgPersonHeadDAODW(ChgPersonHeadDAODW chgPersonHeadDAODW) {
    this.chgPersonHeadDAODW = chgPersonHeadDAODW;
  }

  public void setChgPaymentHeadDAO(ChgPaymentHeadDAO chgPaymentHeadDAO) {
    this.chgPaymentHeadDAO = chgPaymentHeadDAO;
  }

  public ChgpayListAF findChgpayList(Pagination pagination) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    // TODO Auto-generated method stub
    Org org = null;
    List empChgpayChangements = null;
    ChgPaymentPayment chgPaymentPayment = null;
    ChgpayListAF chgpayListAF = new ChgpayListAF();
    Emp emp = null;
    String chgMonth = "";

    SecurityInfo securityInfo = (SecurityInfo) pagination.getQueryCriterions()
        .get("SecurityInfo");
    String orgid = (String) pagination.getQueryCriterions().get("org.id");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    if (((orgid != null) && (orgid.trim() != "") && (orgid.trim().length() > 0))) {

      org = orgDAO.queryByCriterions(orgid, null, null, securityInfo);
      if (org == null) {
        org = new Org();
        chgpayListAF = new ChgpayListAF();
        chgpayListAF.setList(empChgpayChangements);
        chgpayListAF.setOrg(org);
        throw new BusinessException("�Բ��𣬲����ڸõ�λ���߲���Ȩ���ڣ�");

      }
      Integer orgid1 = new Integer(orgid);
      boolean Status1 = chgPaymentSalaryBaseDAO.getChgStatus(orgid1);
      boolean Status3 = chgOrgRateDAO.getChgStatus(orgid1);
      boolean Status2 = chgPersonHeadDAO.getChgStatus(orgid1);

      org = orgDAO.queryByCriterions(orgid, "2", "2", securityInfo);

      if (org == null) {

        org = new Org();
        chgpayListAF = new ChgpayListAF();
        chgpayListAF.setList(empChgpayChangements);
        chgpayListAF.setOrg(org);
        throw new BusinessException("�Բ��𣬸õ�λ�ɴ淽ʽΪ��һ���ʻ��߲���Ȩ���ڣ�");
      }
      if (!Status1) {

        org = new Org();
        chgpayListAF = new ChgpayListAF();
        chgpayListAF.setList(empChgpayChangements);
        chgpayListAF.setOrg(org);
        throw new BusinessException("�Բ��𣬸õ�λ����δ���õĹ��ʻ���������");
      }
      if (!Status3) {

        org = new Org();
        chgpayListAF = new ChgpayListAF();
        chgpayListAF.setList(empChgpayChangements);
        chgpayListAF.setOrg(org);
        throw new BusinessException("�Բ��𣬸õ�λ����δ���õĻ�ɱ���������");
      }
      if (!Status2) {

        org = new Org();
        chgpayListAF = new ChgpayListAF();
        chgpayListAF.setList(empChgpayChangements);
        chgpayListAF.setOrg(org);
        throw new BusinessException("�Բ��𣬸õ�λ����δ���õ���Ա�����");
      }
      chgpayListAF.getOrg().getOrgInfo().setName(org.getOrgInfo().getName());
      // ��chgPaymentSalaryBase�в�ѯ��org.id��chgPaymentSalaryBase.chgStatus=1

      // ��aa202������
      chgPaymentPayment = chgPaymentPaymentDAO.queryByCriterionsWuht(orgid);// ͷ��;

      if (chgPaymentPayment == null) {
        // aa301���ID
        String headMaxId_temp = paymentHeadDAO.queryMaxID(orgid);
        if (!headMaxId_temp.equals("")) {
          String payMonth_temp = paymentHeadDAO
              .queryPaymentHeadMaxID(headMaxId_temp);
          if (!payMonth_temp.equals("")) {
            chgMonth = BusiTools.addMonth(payMonth_temp, 1);
            chgpayListAF.setChgMonth(chgMonth);
          } else {
            chgMonth = BusiTools.addMonth(org.getOrgPayMonth(), 1);
            chgpayListAF.setChgMonth(chgMonth);
          }
        } else {
          chgMonth = BusiTools.addMonth(org.getOrgPayMonth(), 1);
          chgpayListAF.setChgMonth(chgMonth);
        }
      } else {

        chgMonth = chgPaymentPayment.getChgMonth();
        // �ϼƣ���������oldPaymenSum
        // //����ǰ��λ�ɶ�oldOrgPaySum;//������λ�ɶ�OrgPaySum;//����ǰְ���ɶ�oldEmpPaySum;//������ְ���ɶ�EmpPaySum;//������Ӧ��ɶ�:-->

        chgpayListAF.setChgMonth(chgPaymentPayment.getChgMonth());
        chgpayListAF.setOldPaymenSum(chgPaymentPayment.getOldPaymenSum());
        chgpayListAF.setOldOrgPaySum(chgPaymentPayment.getOldOrgPaySum());
        chgpayListAF.setOrgPaySum(chgPaymentPayment.getOrgPaySum());
        chgpayListAF.setOldEmpPaySum(chgPaymentPayment.getOldEmpPaySum());
        chgpayListAF.setEmpPaySum(chgPaymentPayment.getEmpPaySum());
        chgpayListAF.setOldPayment(chgPaymentPayment.getOldPayment());

        chgpayListAF.setId(chgPaymentPayment.getId());

        List returnList = chgPaymentSalaryBaseDAO
            .querypay_statusByHeadID_WL(chgPaymentPayment.getId().toString());
        Integer pay_status = (Integer) returnList.get(0);
        if ((chgPaymentPayment.getPaySum() == null)
            && (pay_status.toString().equals("2") || pay_status.toString()
                .equals("5"))) {
          chgpayListAF.setPaySum(new Double(chgPaymentPayment.getOldPayment()
              .toString()));
        } else if (chgPaymentPayment.getPaySum() == null) {
          chgpayListAF.setPaySum(new Double(0));
        } else {
          chgpayListAF.setPaySum(chgPaymentPayment.getPaySum());
        }

      }
      empChgpayChangements = chgPaymentTailDAO.queryChgPaymentPaymentWuht(
          orgid, chgMonth, orderBy, order, start, pageSize, page);// β��
      for (int i = 0; i < empChgpayChangements.size(); i++) {
        ChgPaymentTail chgPaymentTaillist = (ChgPaymentTail) empChgpayChangements
            .get(i);

        emp = empDAO.queryByCriterions(
            chgPaymentTaillist.getEmpId().toString(), orgid);

        chgPaymentTaillist.setEmp(emp);
        String statetype = autoInfoPickDAODW.findStatus(chgPaymentTaillist
            .getChgPaymentHead().getOrg().getId().toString(),
            chgPaymentTaillist.getChgPaymentHead().getId().toString(),
            BusiConst.ORGBUSINESSTYPE_N);
        chgpayListAF.setStatetype(BusiTools.getBusiValue(Integer
            .parseInt(statetype), BusiConst.OC_NOT_PICK_UP_INFO));
      }
      int count = chgPaymentTailDAO.queryChgPaymentPaymentWuht(orgid, chgMonth);
      pagination.setNrOfElements(count);

      // �ɷѺ˶��Ƿ�Ӧ���˸õ�λ�����µı��
      if (org == null) {
        org = new Org();
      }
      if (chgpayListAF == null) {
        chgpayListAF = new ChgpayListAF();
      }
      chgpayListAF.setList(empChgpayChangements);
      chgpayListAF.setOrg(org);

    }
    return chgpayListAF;
  }

  // ��Ӳ�ѯ
  public ChgPaymentTail findEmpinfo(String empid, String orgid)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    ChgPaymentTail chgPaymentTail = null;

    BusinessException be = null;
    ChgPaymentPayment chgPaymentPayment = new ChgPaymentPayment();
    Emp emp;
    try {
      if (((empid != null) && (empid.trim() != "") && (empid.trim().length() > 0))) {
        // ¼���ְ������Ƿ���ѡ��ĵ�λ�ģ�¼���ְ�������AA002��Ӧ�ĵ�λ����Ƿ����֮ǰ¼��ĵ�λ���
        emp = empDAO.queryByCriterions(empid, orgid);
        if (emp == null) {
          emp = new Emp();
          chgPaymentTail = new ChgPaymentTail();
          be = new BusinessException("��ְ�����������λ�ģ���");
          return null;
        }
        // ��δ�����õı��������Ƿ���ڸ�ְ����AA202��
        // �������=2.�ɶ���������״̬=1.δ���ö�Ӧ��id��AA203���Ӧ��ְ��������Ƿ���¼���ְ�����
        chgPaymentPayment = chgPaymentPaymentDAO.queryByCriterionsWuht(orgid);
        if (chgPaymentPayment != null) {

          chgPaymentTail = chgPaymentTailDAO.queryChgPaymentPaymentWUHT(empid,
              orgid);
        }
        if (chgPaymentTail != null) {
          chgPaymentTail = new ChgPaymentTail();
          be = new BusinessException("��ְ���Ѿ������ɶ��������Ҫ�޸ģ������޸ģ�");
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
    ChgPaymentPayment chgPaymentPayment = new ChgPaymentPayment();
    ChgPaymentPayment chgPaymentPayment1 = null;
    ChgPaymentBizActivityLog chgPaymentBizActivityLog = new ChgPaymentBizActivityLog();
    Emp emp = null;
    Org org = null;
    try {
      String name = (String) pagination.getQueryCriterions().get("name");
      String date = (String) pagination.getQueryCriterions().get("date");
      String pkid = (String) pagination.getQueryCriterions().get("pkid");
      String orgid = (String) pagination.getQueryCriterions().get("org.id");
      String empid = (String) pagination.getQueryCriterions().get(
          "chgPaymentTail.empId");
      String chgMonth = (String) pagination.getQueryCriterions()
          .get("chgMonth");
      String ip = (String) pagination.getQueryCriterions().get("ip");
      String salaryBase = (String) pagination.getQueryCriterions().get(
          "salaryBase");
      String orgPay = (String) pagination.getQueryCriterions().get("orgPay");
      String empPay = (String) pagination.getQueryCriterions().get("empPay");

      chgPaymentPayment1 = chgPaymentPaymentDAO.queryByCriterionsWuht(orgid);
      String empStatus = chgPaymentPaymentDAO.empstatuslishan(
          new Integer(empid), new Integer(orgid));
      ChgPaymentTail chgPaymentTail1 = null;
      if (chgPaymentPayment1 != null) {

        chgPaymentTail1 = chgPaymentTailDAO.queryChgPaymentPaymentWUHT(empid,
            orgid);
      }
      if (chgPaymentTail1 != null) {
        be = new BusinessException("��ְ���Ѿ������ɶ��������Ҫ�޸ģ������޸ģ�");
      } else if (!empStatus.equals("") && empStatus.equals("5")) {
        be = new BusinessException("��ְ����״̬Ϊɾ����");
      } else {
        chgPaymentPayment = chgPaymentPaymentDAO.queryByCriterionsWuht(orgid);
        // �Ƿ����δ�����õı����᣺AA202�� �������=B.�ɶ����
        // �����״̬=1.δ���ã�chgPaymentPayment==null������
        if (chgPaymentPayment == null || chgPaymentPayment.equals("")) {
          org = orgDAO.queryById(new Integer(orgid));
          chgPaymentPayment = new ChgPaymentPayment();
          // дnew Date() �д���

          chgPaymentPayment.setBizDate(date);

          chgPaymentPayment.setChgMonth(chgMonth);
          chgPaymentPayment.setChgStatus(new Integer(1));
          chgPaymentPayment.setOrg(org);

          List list = new ArrayList();
          list = empDAO.getEmpListWuht(orgid);
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
          chgPaymentPayment.setOldPayment(sumPay);
          chgPaymentPayment.setOldSlarayBase(sumSalaryBase);

          chgPaymentPaymentDAO.insert(chgPaymentPayment);

          // ����AA319��
          chgPaymentBizActivityLog.setBizid(new Integer(chgPaymentPayment
              .getId().toString()));
          chgPaymentBizActivityLog.setAction(new Integer(1));
          chgPaymentBizActivityLog.setOpTime(new Date());
          chgPaymentBizActivityLog.setOperator(name);
          chgPaymentBizActivityLogDAO.insert(chgPaymentBizActivityLog);

        } else {
          if (pkid.equals("")) {

            chgPaymentPayment = chgPaymentPaymentDAO
                .queryByCriterionsWuht(orgid);

          } else {

            chgPaymentPayment = chgPaymentPaymentDAO
                .queryById(new Integer(pkid));
          }
        }
        emp = empDAO.queryByCriterions(empid, orgid);
        chgPaymentTail.setChgPaymentHead(chgPaymentPayment);
        chgPaymentTail.setEmp(emp);
        chgPaymentTail.setPayStatus(new Integer(emp.getPayStatus().toString()));
        chgPaymentTail.setSalaryBase(new BigDecimal(salaryBase));

        chgPaymentTailDAO.insert(chgPaymentTail);

        // ����BA003��

        pkid = chgPaymentPayment.getId().toString();
        int opModel = busiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO;
        int opButton = busiLogConst.BIZLOG_ACTION_ADD;
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

  // �޸�AA202����������=¼��ĵ��������޸�AA203������BA003��
  public void updateChgPaymentTail(ChgPaymentTail chgPaymentTail,
      Pagination pagination) throws BusinessException {
    // TODO Auto-generated method stub
    ChgPaymentTail chgPaymentTail1 = new ChgPaymentTail();
    BusinessException be = null;
    HafOperateLog hafOperateLog = new HafOperateLog();
    BusiLogConst busiLogConst = null;
    ChgPaymentPayment chgPaymentPayment = new ChgPaymentPayment();
    try {
      String name = (String) pagination.getQueryCriterions().get("name");
      String pkid = (String) pagination.getQueryCriterions().get("pkid");
      String ip = (String) pagination.getQueryCriterions().get("ip");
      String orgid = (String) pagination.getQueryCriterions().get("org.id");
      String chgMonth = (String) pagination.getQueryCriterions()
          .get("chgMonth");
      chgPaymentPayment = chgPaymentPaymentDAO.queryById(new Integer(pkid));
      chgPaymentPayment.setChgMonth(chgMonth);
      chgPaymentTail1 = chgPaymentTailDAO.queryById(new Integer(chgPaymentTail
          .getId().toString()));
      chgPaymentTail1.setSalaryBase(chgPaymentTail.getEmp().getSalaryBase());
      chgPaymentTail1.setEmpPay(chgPaymentTail.getEmpPay());
      chgPaymentTail1.setOrgPay(chgPaymentTail.getOrgPay());
      // ����BA003��
      int opModel = busiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO;
      int opButton = busiLogConst.BIZLOG_ACTION_MODIFY;
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

  // ɾ��
  public void deleteChgPaymentTail(Integer id, String pkid, String orgid,
      String ip, String nrOfElements, String name, SecurityInfo securityInfo)
      throws Exception, BusinessException {

    // �ж��Ƿ�Ϊ��λ��
    int isOrgEdition = securityInfo.getIsOrgEdition();
    ChgPaymentBizActivityLog chgPaymentBizActivityLog = new ChgPaymentBizActivityLog();
    ChgPaymentPayment chgPaymentPayment = null;
    BusiLogConst busiLogConst = null;
    ChgPaymentTail chgPaymentTail = null;
    try {

      if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// ��λ��
        // �ж��Ƿ���AA203�����һ����¼
        if (nrOfElements.equals("1")) {
          // �ж��ύ״̬�Ƿ�Ϊδ��ȡ
          boolean isNoPickUp = autoInfoPickDAODW.isNOPickUp(orgid, pkid,
              BusiConst.ORGBUSINESSTYPE_N);
          // �������δ�ύ�ģ���ʾ�ȳ����ύ
          if (isNoPickUp) {
            throw new BusinessException("���ȳ����ύ��");
          }
          String stype = autoInfoPickDAODW.findStatus(orgid, pkid,
              BusiConst.ORGBUSINESSTYPE_N);
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
              BusiConst.ORGBUSINESSTYPE_N);
        }
      }

      chgPaymentTail = chgPaymentTailDAO.queryById(id);
      chgPaymentTailDAO.delete(chgPaymentTail);

      if (nrOfElements.equals("1")) {

        // ɾ��AA319��
        chgPaymentBizActivityLog = chgPaymentBizActivityLogDAO
            .queryChgPaymentBizActivityLogByIdWuht(pkid.toString(), "1");
        chgPaymentBizActivityLogDAO.deleteWuht(chgPaymentBizActivityLog);
        // ɾ��AA203��
        chgPaymentTail = chgPaymentTailDAO.queryById(id);

        chgPaymentTailDAO.delete(chgPaymentTail);
        // ɾ��AA202��
        chgPaymentPayment = chgPaymentPaymentDAO.queryById(new Integer(pkid));
        chgPaymentPaymentDAO.delete(chgPaymentPayment);

      }
      int opButton = busiLogConst.BIZLOG_ACTION_DELETE;
      int opModel = busiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO;
      this.addhafOperateLog(name, opButton, opModel, pkid, ip, orgid);
    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }

  }

  // ȫ��ɾ��
  public void deleteAllChgPaymentTail(Integer id, String pkid, String orgid,
      String ip, String name, SecurityInfo securityInfo) throws Exception,
      BusinessException {

    // �ж��Ƿ�Ϊ��λ��
    int isOrgEdition = securityInfo.getIsOrgEdition();

    try {
      if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// ��λ��

        // �ж��ύ״̬�Ƿ�Ϊδ��ȡ
        boolean isNoPickUp = autoInfoPickDAODW.isNOPickUp(orgid, pkid,
            BusiConst.ORGBUSINESSTYPE_N);
        System.out.println("isNoPickUp======" + isNoPickUp);
        // �������δ�ύ�ģ���ʾ�ȳ����ύ
        if (isNoPickUp) {
          throw new BusinessException("���ȳ����ύ��");
        }
        String stype = autoInfoPickDAODW.findStatus(orgid, pkid,
            BusiConst.ORGBUSINESSTYPE_N);
        if (stype.equals(BusiConst.OC_PICK_UP)) {
          throw new BusinessException("��������ȡ������ɾ��");
        }
      } else if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// ���İ�
        String center_head_id = "";
        String centerBizDate = "";
        autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP,
            center_head_id, centerBizDate, orgid, pkid,
            BusiConst.ORGBUSINESSTYPE_N);
      }

      // ����ȫ��ɾ��
      ChgPaymentBizActivityLog chgPaymentBizActivityLog = new ChgPaymentBizActivityLog();
      HafOperateLog hafOperateLog = new HafOperateLog();
      ChgPaymentPayment chgPaymentPayment = null;
      BusiLogConst busiLogConst = null;
      ChgPaymentTail chgPaymentTail = null;
      List deleteList = new ArrayList();

      // ɾ��AA319��
      chgPaymentBizActivityLog = chgPaymentBizActivityLogDAO
          .queryChgPaymentBizActivityLogByIdWuht(pkid.toString(), "1");
      chgPaymentBizActivityLogDAO.deleteWuht(chgPaymentBizActivityLog);
      // ɾ��AA203��
      chgPaymentTailDAO.deleteChgTail(new Integer(pkid));
      // ɾ��AA2023��
      chgPaymentPayment = chgPaymentPaymentDAO.queryById(new Integer(pkid));
      chgPaymentPaymentDAO.delete(chgPaymentPayment);

      int opButton = busiLogConst.BIZLOG_ACTION_DELETEALL;
      int opModel = busiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO;
      this.addhafOperateLog(name, opButton, opModel, pkid, ip, orgid);
    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }
  }

  // ����ҳ�������
  public void useChgPaymentPayment(Pagination pagination) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    ChgPaymentPayment chgPaymentPayment = null;
    ChgPaymentPayment chgPaymentPayment1 = null;
    Emp emp = null;
    List empChgPaymentPayment = null;
    HafOperateLog hafOperateLog = new HafOperateLog();
    BusiLogConst busiLogConst = null;
    String chgMonth2 = null;
    BusinessException be = null;
    ChgPaymentBizActivityLog chgPaymentBizActivityLog = new ChgPaymentBizActivityLog();
    try {
      String pkid = (String) pagination.getQueryCriterions().get("pkid");
      String chgMonth = (String) pagination.getQueryCriterions()
          .get("chgMonth");

      String name = (String) pagination.getQueryCriterions().get("name");
      String orgid = (String) pagination.getQueryCriterions().get("org.id");
      String ip = (String) pagination.getQueryCriterions().get("ip");

      chgPaymentPayment1 = chgPaymentPaymentDAO.queryChgPaymentPaymentByIdWuht(
          pkid.toString(), "1");
      if (chgPaymentPayment1 == null) {
        be = new BusinessException("��ְ���Ѿ������ɶ��������Ϊ���ã�����δ���õ����ã�");
      } else {
        // ����AA002����λ�ɶ� ְ���ɶ� ���ʻ���
        // ����AA202�����״̬=2.������ ��������=����¼��ĵ�������
        chgPaymentPaymentDAO.updatePayChgUse(new Integer(orgid), new Integer(
            pkid), chgMonth);

        // ����AA319��
        chgPaymentBizActivityLog.setBizid(new Integer(pkid));
        chgPaymentBizActivityLog.setAction(new Integer(3));
        chgPaymentBizActivityLog.setOpTime(new Date());
        chgPaymentBizActivityLog.setOperator(name);
        chgPaymentBizActivityLogDAO.insert(chgPaymentBizActivityLog);

        // ����BA003��
        int opModel = busiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO;
        int opButton = busiLogConst.BIZLOG_ACTION_OPENING;
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

  public ChgpayListAF findOrgChgpayList(Pagination pagination)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    Org org = new Org();
    List orgChgPaymentPayment = null;
    ChgpayListAF chgpayListAF = new ChgpayListAF();
    String chgMonth = (String) pagination.getQueryCriterions().get("chgMonth");
    String name = (String) pagination.getQueryCriterions().get(
        "org.orgInfo.name");
    String orgid = (String) pagination.getQueryCriterions().get("org.id");
    String typetb = (String) pagination.getQueryCriterions().get("typetb");
    int count = 0;
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    SecurityInfo securityInfo = (SecurityInfo) pagination.getQueryCriterions()
        .get("SecurityInfo");

    if (typetb != null && typetb.equals("1")) {
      orgChgPaymentPayment = chgPaymentPaymentDAO
          .queryChgPaymentPaymentByCriterionsWuht(orgid, name, chgMonth,
              orderBy, order, start, pageSize, page, securityInfo);
      count = chgPaymentPaymentDAO.queryChgPaymentPaymentByCriterionsCountWuht(
          orgid, name, chgMonth, securityInfo);// ͷ��;
    } else {
      orgChgPaymentPayment = chgPaymentPaymentDAO.queryChgPaymentPaymentWuht(
          orgid, name, chgMonth, orderBy, order, start, pageSize, page,
          securityInfo);
      count = chgPaymentPaymentDAO.queryChgPaymentPaymentWuht(orgid, name,
          chgMonth, securityInfo);// ͷ��;

    }
    if (orgChgPaymentPayment != null) {
      for (int i = 0; i < orgChgPaymentPayment.size(); i++) {
        ChgPaymentPayment chgPaymentPaymentlist = (ChgPaymentPayment) orgChgPaymentPayment
            .get(i);
        // ��ѯ��λ�� status=2, paymode=2

        chgPaymentPaymentlist.setWuhtChgStatus(BusiTools.getBusiValue(Integer
            .parseInt(chgPaymentPaymentlist.getChgStatus().toString()),
            BusiConst.CHGTYPESTATUS));
        String statetype = autoInfoPickDAODW.findStatus(chgPaymentPaymentlist
            .getOrg().getId().toString(), chgPaymentPaymentlist.getId()
            .toString(), BusiConst.ORGBUSINESSTYPE_N);
        chgPaymentPaymentlist.setTemp_pick(BusiTools.getBusiValue(Integer
            .parseInt(statetype), BusiConst.OC_NOT_PICK_UP_INFO));
      }

      pagination.setNrOfElements(count);
      if (org == null) {
        org = new Org();
      }

      if (chgpayListAF == null) {
        chgpayListAF = new ChgpayListAF();
      }
      chgpayListAF.setList(orgChgPaymentPayment);

      chgpayListAF.setOrg(org);
    }
    return chgpayListAF;

  }

  public String findOrgidById(String id) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    ChgPaymentPayment chgPaymentPayment = new ChgPaymentPayment();
    String orgid = null;
    BusinessException be = null;
    try {

      chgPaymentPayment = chgPaymentPaymentDAO.queryChgPaymentPaymentByIdWuht(
          id, "1");
      if (chgPaymentPayment == null) {

        be = new BusinessException("��ְ���Ѿ��������ʻ�����������Ϊ���ã�����δ���õ��޸ģ�");
        return null;
      }
      orgid = chgPaymentPayment.getOrg().getId().toString();
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
    ChgPaymentBizActivityLog chgPaymentBizActivityLog = new ChgPaymentBizActivityLog();
    ChgPaymentPayment chgPaymentPayment = new ChgPaymentPayment();
    String orgid = null;
    ChgPaymentTail chgPaymentTail = null;
    BusiLogConst busiLogConst = null;
    List deleteList = new ArrayList();

    try {
      chgPaymentPayment = chgPaymentPaymentDAO.queryChgPaymentPaymentByIdWuht(
          id.toString(), "1");
      if (chgPaymentPayment == null) {
        be = new BusinessException("��ְ���Ѿ������ɶ��������Ϊ���ã�����δ���õ�ɾ����");
      } else {
        orgid = chgPaymentPayment.getOrg().getId().toString();

        // �ж��Ƿ�Ϊ��λ��
        int isOrgEdition = securityInfo.getIsOrgEdition();
        System.out.println("isOrgEdition======" + isOrgEdition);
        if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// ��λ��
          // �ж��ύ״̬�Ƿ�Ϊδ��ȡ
          boolean isNoPickUp = autoInfoPickDAODW.isNOPickUp(orgid, id
              .toString(), BusiConst.ORGBUSINESSTYPE_N);
          System.out.println("isNoPickUp======" + isNoPickUp);
          // �������δ�ύ�ģ���ʾ�ȳ����ύ
          if (isNoPickUp) {
            throw new BusinessException("���ȳ����ύ��");
          }
          String stype = autoInfoPickDAODW.findStatus(orgid, id.toString(),
              BusiConst.ORGBUSINESSTYPE_N);
          if (stype.equals(BusiConst.OC_PICK_UP)) {
            throw new BusinessException("��������ȡ������ɾ��");
          }
        } else if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// ���İ�
          String center_head_id = "";
          String centerBizDate = "";
          autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP,
              center_head_id, centerBizDate, orgid, id.toString(),
              BusiConst.ORGBUSINESSTYPE_N);
        }

        // ɾ��AA319��
        chgPaymentBizActivityLog = chgPaymentBizActivityLogDAO
            .queryChgPaymentBizActivityLogByIdWuht(id.toString(), "1");
        chgPaymentBizActivityLogDAO.deleteWuht(chgPaymentBizActivityLog);
        // ɾ��AA203��
        // deleteList=chgPaymentTailDAO.queryChgPaymentPaymentOtherWuht(orgid,
        // null, null, null, 0, 0);
        // chgPaymentTailDAO.deleteList(deleteList);
        chgPaymentTailDAO.deleteChgTail(id);
        // ɾ��AA202��
        chgPaymentPaymentDAO.delete(chgPaymentPayment);

        // // ����BA003
        int opButton = busiLogConst.BIZLOG_ACTION_DELETE;
        int opModel = busiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_MAINTAIN;
        this
            .addhafOperateLog(name, opButton, opModel, id.toString(), ip, orgid);

      }
    } catch (Exception e) {
      // e.printStackTrace();
      // throw be;
      throw new BusinessException(e.getMessage());
    } finally {
      if (be != null) {
        throw be;
      }
    }
  }

  // ά��ҳ�������
  public void useChgPaymentPaymentMaintain(Pagination pagination)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    ChgPaymentPayment chgPaymentPayment = null;
    BusiLogConst busiLogConst = null;
    BusinessException be = null;
    String orgid = null;
    String chgMonth = null;
    ChgPaymentBizActivityLog chgPaymentBizActivityLog = new ChgPaymentBizActivityLog();
    try {
      String id = (String) pagination.getQueryCriterions().get("id");
      String name = (String) pagination.getQueryCriterions().get("name");
      chgPaymentPayment = chgPaymentPaymentDAO.queryChgPaymentPaymentByIdWuht(
          id.toString(), "1");
      if (chgPaymentPayment == null) {
        be = new BusinessException("��ְ���Ѿ������ɶ��������Ϊ���ã�����δ���õ����ã�");
      } else {
        chgMonth = chgPaymentPayment.getChgMonth();

        orgid = chgPaymentPayment.getOrg().getId().toString();
        String ip = (String) pagination.getQueryCriterions().get("ip");

        // ����AA002����λ�ɶ� ְ���ɶ� ���ʻ�
        // ����AA202�����״̬=2.������ ��������=����¼��ĵ�������
        chgPaymentPaymentDAO.updatePayChgUse(new Integer(orgid),
            new Integer(id), chgMonth);

        // ����AA319��
        chgPaymentBizActivityLog.setBizid(new Integer(id));
        chgPaymentBizActivityLog.setAction(new Integer(3));
        chgPaymentBizActivityLog.setOpTime(new Date());
        chgPaymentBizActivityLog.setOperator(name);
        chgPaymentBizActivityLogDAO.insert(chgPaymentBizActivityLog);

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

  public boolean deluseChgPaymentPaymentMaintain(Pagination pagination)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    ChgPaymentPayment chgPaymentPayment = null;
    String chgPersonHeadid = null;
    String chgOrgRateid = null;
    String chgPaymentHeadid = null;
    Emp emp = null;
    List empChgPaymentPayment = null;
    BusiLogConst busiLogConst = null;
    BusinessException be = null;
    String orgid = null;
    ChgPaymentBizActivityLog chgPaymentBizActivityLog = new ChgPaymentBizActivityLog();
    try {
      String id = (String) pagination.getQueryCriterions().get("id");
      String name = (String) pagination.getQueryCriterions().get("name");
      int chgPaymentSalaryBaseid = Integer.parseInt(id);
      String ip = (String) pagination.getQueryCriterions().get("ip");

      // �ڳ������õ�ʱ�����ж�һ��״̬�Ƿ�Ϊ����
      ChgPaymentPayment chgPaymentPayment1 = null;
      ChgPaymentPayment chgPaymentPayment3 = null;
      chgPaymentPayment1 = chgPaymentPaymentDAO.queryById(new Integer(id));
      if (chgPaymentPayment1 != null) {
        orgid = chgPaymentPayment1.getOrg().getId().toString();
        chgPaymentPayment3 = chgPaymentPaymentDAO.queryByCriterionsWuht(orgid);
      }
      if (chgPaymentPayment3 != null) {
        be = new BusinessException("����δ���õĽɶ�������������ã�");
      } else {
        // * ��ѯ��Ա���ͷ�� ��AA204���ĵ�����id=
        // �ñʱ�����Ƿ������������жϷ�����AA201��AA202��AA204�е�id�Ƿ���ڴ��ڸñʱ��id��
        chgPaymentPayment = chgPaymentPaymentDAO
            .queryChgPaymentPaymentByIdWuht(id, "2");
        orgid = chgPaymentPayment.getOrg().getId().toString();
        chgPersonHeadid = chgPersonHeadDAO.getMaxHeadID_WL(orgid).toString();

        if (Integer.parseInt(chgPersonHeadid) > chgPaymentSalaryBaseid) {
          be = new BusinessException("���ȳ�������ı����");
        } else {
          // �ñʱ���Ƿ񱻻��Ӧ���жϷ�����AA202�еĸñʱ����Ӧ�Ľɴ�id�Ƿ�Ϊ��
          chgPaymentPayment = chgPaymentPaymentDAO
              .queryChgPaymentPaymentByIdWuht(id, "2");
          if (chgPaymentPayment == null) {

            be = new BusinessException("��ְ����δ���ã�������ã�");

          } else {

            PaymentHead paymentHeadid = chgPaymentPayment.getPaymentHead();
            if (paymentHeadid != null) {
              be = new BusinessException("���ȳ���Ӧ�øñʱ���Ļ�ɣ�");
            } else {
              ChgPaymentPayment chgPaymentPayment2 = null;
              orgid = chgPaymentPayment.getOrg().getId().toString();
              chgPaymentPayment2 = chgPaymentPaymentDAO
                  .queryByCriterionsWuht(orgid);

              if (chgPaymentPayment2 != null) {
                be = new BusinessException("����δ���õĽɶ�������������ã�");
              } else {

                orgid = chgPaymentPayment.getOrg().getId().toString();
                // ɾ��AA319��
                chgPaymentBizActivityLog = chgPaymentBizActivityLogDAO
                    .queryChgPaymentBizActivityLogByIdWuht(id, "3");
                chgPaymentBizActivityLogDAO
                    .deleteWuht(chgPaymentBizActivityLog);

                // ����BA003��
                int opButton = busiLogConst.BIZLOG_ACTION_REVOCATION;
                int opModel = busiLogConst.OP_MODE_CHANGE_CHGPAYMENT_MAINTAIN;
                this.addhafOperateLog(name, opButton, opModel, id.toString(),
                    ip, orgid);
                // ����AA002����λ�ɶ� ְ���ɶ� ���ʻ���
                // ����AA202�����״̬=1.δ���� ��������=����¼��ĵ�������
                chgPaymentPaymentDAO.updatePayChgReUse(new Integer(orgid),
                    new Integer(id));
              }
            }
          }
        }
      }
      return true;
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
    // TODO Auto-generated method stub
    List list = new ArrayList();
    BusinessException be = null;
    BusiLogConst busiLogConst = null;
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    String orgId = (String) pagination.getQueryCriterions().get("org.id");
    String ip = (String) pagination.getQueryCriterions().get("ip");
    String orgName = (String) pagination.getQueryCriterions().get(
        "org.orgInfo.name");
    String chgMonth = (String) pagination.getQueryCriterions().get("chgMonth");
    String name = (String) pagination.getQueryCriterions().get("name");

    String orderArray[] = (String[]) pagination.getQueryCriterions().get(
        "orderArray");// ���������

    try {

      // list=empDAO.queryChgpayEmpOrgWuht(orgId, orgName, chgMonth, orderBy,
      // order, start, pageSize);

      list = empDAO.queryChgpayEmpOrgWuhts(orgId, orgName, chgMonth,
          orderArray, order, start, pageSize);
      // ����BA003��
      int opButton = busiLogConst.BIZLOG_ACTION_EXP;
      int opModel = busiLogConst.OP_MODE_CHANGE_CHGPAYMENT_DO;

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
  public List addChgpayListBatch(List addchgpayHeadImportList,
      List addchgpayListImportList, String orgId, String chgMonth, String ip,
      String name, String date, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub

    List list = new ArrayList();
    ChgPaymentBizActivityLog chgPaymentBizActivityLog = new ChgPaymentBizActivityLog();
    if (addchgpayHeadImportList.size() <= 0) {
      throw new BusinessException("��������Ϊ�գ�");
    }
    if (addchgpayListImportList.size() <= 0) {
      throw new BusinessException("��������Ϊ�գ�");
    }

    ChgpayHeadImportDTO chgpayHeadImportDTO = (ChgpayHeadImportDTO) addchgpayHeadImportList
        .get(1);

    int OrgId1 = Integer.parseInt(chgpayHeadImportDTO.getOrgId());
    String OrgId2 = OrgId1 + "";
    if (!orgId.equals(OrgId2.trim())) {
      throw new BusinessException("ѡ��ĵ����ļ�������ĵ�λ��Ų�����");
    }
    Org org = null;
    ;
    org = orgDAO.queryByCriterions(chgpayHeadImportDTO.getOrgId(), "2", "2",
        securityInfo);
    boolean Status1 = chgPaymentPaymentDAO.getChgStatus(new Integer(
        chgpayHeadImportDTO.getOrgId()));
    if (!Status1 || org == null) {

      org = new Org();

      throw new BusinessException("�Բ��𣬸õ�λ���ܽ��е��룡");
    }
    // ����aa202��
    ChgPaymentPayment chgPaymentPayment = new ChgPaymentPayment();
    // дnew Date() �д���

    chgPaymentPayment.setBizDate(date);
    chgPaymentPayment.setChgMonth(chgMonth);
    chgPaymentPayment.setChgStatus(new Integer(1));
    chgPaymentPayment.setOrg(org);

    List list2 = new ArrayList();
    list2 = empDAO.getEmpListWuht(orgId);
    BigDecimal sumSalaryBase = new BigDecimal(0.00);
    BigDecimal sumPay = new BigDecimal(0.00);
    if (list2 != null) {
      for (int i = 0; i < list2.size(); i++) {
        Emp listemp = (Emp) list2.get(i);
        sumSalaryBase = sumSalaryBase.add(listemp.getSalaryBase());
        sumPay = sumPay.add(listemp.getOrgPay());
        sumPay = sumPay.add(listemp.getEmpPay());
      }
    }
    chgPaymentPayment.setOldPayment(sumPay);
    chgPaymentPayment.setOldSlarayBase(sumSalaryBase);
    Serializable id = chgPaymentPaymentDAO.insert(chgPaymentPayment);
    this.center_head_id = id.toString();
    // ����aa203
    for (int i = 1; i < addchgpayListImportList.size(); i++) {
      ChgpayListImportDTO chgpayListImportDTO = (ChgpayListImportDTO) addchgpayListImportList
          .get(i);
      UtilRule utilRule = new UtilRule();

      ChgpayInfoDTO chgpayInfoDTO = new ChgpayInfoDTO();

      Pattern pNotenumber = Pattern.compile("^([0-9]{1,10})(\\.[0-9]{1,2})?$");
      Matcher mNotenumber = pNotenumber.matcher(chgpayListImportDTO
          .getSalaryBase());
      if (mNotenumber.find() == false) {
        throw new BusinessException("��¼����ȷ���ʻ�����");
      } else if (chgpayListImportDTO.getSalaryBase().length() > 20) {
        throw new BusinessException("���ʻ����������");
      }

      Matcher mNotenumber2 = pNotenumber.matcher(chgpayListImportDTO
          .getOrgPay());
      if (mNotenumber2.find() == false) {
        throw new BusinessException("��¼����ȷ�ɶ");
      } else if (chgpayListImportDTO.getOrgPay().length() > 20) {
        throw new BusinessException("�ɶ��������");
      }

      Matcher mNotenumber3 = pNotenumber.matcher(chgpayListImportDTO
          .getEmpPay());
      if (mNotenumber3.find() == false) {
        throw new BusinessException("��¼����ȷ�ɶ");
      } else if (chgpayListImportDTO.getEmpPay().length() > 20) {
        throw new BusinessException("�ɶ��������");
      }

      if (chgpayListImportDTO.getOrgPay() == null
          || chgpayListImportDTO.getOrgPay().equals("")
          || chgpayListImportDTO.getEmpPay() == null
          || chgpayListImportDTO.getEmpPay().equals("")) {
        throw new BusinessException("����Ľɶ��");
      } else if (utilRule.moneyRule(chgpayListImportDTO.getEmpPay(), 10, 2) == false) {
        chgpayInfoDTO.setEmpPay(chgpayListImportDTO.getEmpPay());
        list.add(chgpayInfoDTO);
        throw new BusinessException("����Ľɶ��");
      }
      if (utilRule.moneyRule(chgpayListImportDTO.getOrgPay(), 10, 2) == false) {
        chgpayInfoDTO.setOrgPay(chgpayListImportDTO.getOrgPay());
        list.add(chgpayInfoDTO);
        throw new BusinessException("����Ľɶ��");
      }
      if (chgpayListImportDTO.getSalaryBase() == null
          || chgpayListImportDTO.getSalaryBase().equals("")) {
        throw new BusinessException("����Ĺ��ʻ�������");
      } else if (utilRule.moneyRule(chgpayListImportDTO.getSalaryBase(), 10, 2) == false) {
        chgpayInfoDTO.setSalaryBase(chgpayListImportDTO.getSalaryBase());
        list.add(chgpayInfoDTO);
        throw new BusinessException("����Ĺ��ʻ�������");
      }

      ChgPaymentTail chgPaymentTail = new ChgPaymentTail();
      ChgPaymentTail chgPaymentTail1 = new ChgPaymentTail();
      Emp emp = empDAO.queryByCriterions(chgpayListImportDTO.getEmpId(), orgId);
      if (emp == null) {
        throw new BusinessException("�����ְ���������ڱ���λ");
      }
      chgPaymentTail1 = chgPaymentTailDAO.queryChgPaymentSalaryBaseWUHT(
          chgpayListImportDTO.getEmpId(), orgId);
      if (chgPaymentTail1 != null) {
        throw new BusinessException("��ְ���Ѿ������ɶ������");
      }

      chgPaymentTail.setChgPaymentHead(chgPaymentPayment);
      chgPaymentTail.setEmp(emp);
      chgPaymentTail.setPayStatus(new Integer(emp.getPayStatus().toString()));

      chgPaymentTail.setEmpId(new Integer(chgpayListImportDTO.getEmpId()));
      chgPaymentTail.setOldOrgPay(new BigDecimal(chgpayListImportDTO
          .getOldOrgPay()));
      chgPaymentTail.setOldEmpPay(new BigDecimal(chgpayListImportDTO
          .getOldEmpPay()));

      chgPaymentTail.setOrgPay(new BigDecimal(chgpayListImportDTO.getOrgPay()));
      chgPaymentTail.setEmpPay(new BigDecimal(chgpayListImportDTO.getEmpPay()));
      chgPaymentTail.setSalaryBase(new BigDecimal(chgpayListImportDTO
          .getSalaryBase()));
      chgPaymentTailDAO.insert(chgPaymentTail);

    }
    // ����AA319��
    chgPaymentBizActivityLog.setBizid(new Integer(chgPaymentPayment.getId()
        .toString()));
    chgPaymentBizActivityLog.setAction(new Integer(BusiConst.BUSINESSSTATE_1));
    chgPaymentBizActivityLog.setOpTime(new Date());
    chgPaymentBizActivityLog.setOperator(name);
    chgPaymentBizActivityLogDAO.insert(chgPaymentBizActivityLog);
    // ����BA003��
    String pkid = chgPaymentPayment.getId().toString();
    BusiLogConst busiLogConst = null;
    int opButton = busiLogConst.BIZLOG_ACTION_EXP;
    int opModel = busiLogConst.OP_MODE_CHANGE_CHGPAYMENT_DO;

    this.addhafOperateLog(name, opButton, opModel, pkid, ip, orgId);
    return list;
  }

  // // ����BA003��
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

  public ChgpayListAF findChgpayWindowList(Pagination pagination)
      throws Exception, BusinessException {
    ChgpayListAF chgpayListAF = new ChgpayListAF();
    ChgPaymentPayment chgPaymentPayment = null;
    List empChgpayChangements = null;
    Emp emp = null;
    try {

      String id = (String) pagination.getQueryCriterions().get("pk_id");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();

      chgPaymentPayment = chgPaymentPaymentDAO.queryById(new Integer(id));
      Serializable orgid = chgPaymentPayment.getOrg().getId();
      chgpayListAF.getOrg().setId(orgid);
      chgpayListAF.getOrg().getOrgInfo().setName(
          chgPaymentPayment.getOrg().getOrgInfo().getName());

      chgpayListAF.setChgMonth(chgPaymentPayment.getChgMonth());
      chgpayListAF.setOldPaymenSum(chgPaymentPayment.getOldPaymenSum());
      chgpayListAF.setOldOrgPaySum(chgPaymentPayment.getOldOrgPaySum());
      chgpayListAF.setOrgPaySum(chgPaymentPayment.getOrgPaySum());
      chgpayListAF.setOldEmpPaySum(chgPaymentPayment.getOldEmpPaySum());
      chgpayListAF.setEmpPaySum(chgPaymentPayment.getEmpPaySum());
      chgpayListAF.setOldPayment(chgPaymentPayment.getOldPayment());
      chgpayListAF.setPaySum(chgPaymentPayment.getPaySum());

      empChgpayChangements = chgPaymentTailDAO
          .queryChgPaymentTailByChgPaymentPayment(id, orderBy, order, start,
              pageSize);
      // ѭ���б��е���Ϣ
      for (int i = 0; i < empChgpayChangements.size(); i++) {
        ChgPaymentTail chgPaymentTaillist = (ChgPaymentTail) empChgpayChangements
            .get(i);

        emp = empDAO.queryByCriterions(
            chgPaymentTaillist.getEmpId().toString(), orgid.toString());
        chgPaymentTaillist.setEmp(emp);

      }
      int count = chgPaymentTailDAO.countChgPaymentTailByChgPaymentPayment(id);
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      // TODO: handle exception
    }

    chgpayListAF.setList(empChgpayChangements);

    return chgpayListAF;
  }

  /**
   * ������ȡ
   * 
   * @author ������
   * @2008-02-26
   * @param orgid
   * @param chgMonth
   * @param securityInfo
   * @throws Exception
   * @throws BusinessException
   */
  public void pickUp(String orgid, String chgMonth, SecurityInfo securityInfo)
      throws Exception, BusinessException {

    try {
      int count = autoInfoPickDAO.queryNoPickUpListbyOrgid(orgid);
      if (count == 0) {
        throw new BusinessException("�õ�λ������δ��ȡ�ļ�¼��");
      }
      String ip = securityInfo.getUserInfo().getUserIp();
      String name = securityInfo.getUserInfo().getUsername();
      String date = securityInfo.getUserInfo().getBizDate();
      // ����orgid��ѯpay_head_id��Ϊ�յ�orgheadid ��type
      Object[] obj = autoInfoPickDAO.queryOrgHeadidAndType(orgid);

      if (obj == null) {
        // PAY_HEAD_ID=null��ȡORG_HEAD_ID��С�ļ�¼���õ�ORG_HEAD_ID��TYPE
        obj = autoInfoPickDAO.findOrgHeadidAndType(orgid);
      }
      if (obj != null) {
        if ((obj[1].toString().equals(BusiConst.ORGBUSINESSTYPE_N))) {
          // ����ΪBusiConst.ORGBUSINESSTYPE_N(�ɶ����)
          String orgheadID = autoInfoPickDAO.findOrgHeadid(orgid,
              BusiConst.ORGBUSINESSTYPE_N, BusiConst.OC_NOT_PICK_UP);
          // ��ѯ�����õ�list
          List addchgslarybaseHeadImportList = chgPersonHeadDAODW
              .findAddchgpayListImportList(orgid);
          List addchgslarybaseListImportList = chgPaymentPaymentDAODW
              .findAddpaymentListImportList(orgheadID);

          // ���õ��뷽��
          addChgpayListBatch(addchgslarybaseHeadImportList,
              addchgslarybaseListImportList, orgid, chgMonth, ip, name, date,
              securityInfo);
          // ����da001
          autoInfoPickDAO.updateAutoInfoPick(BusiConst.OC_PICK_UP,
              center_head_id, new Date().toString(), orgid, orgheadID,
              BusiConst.ORGBUSINESSTYPE_N);
          System.out.println(center_head_id + "zhongxiID");
          // ����BA003��
          HafOperateLog hafOperateLog = new HafOperateLog();
          hafOperateLog.setOpSys(new Integer(
              BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO));
          hafOperateLog.setOpButton(Integer
              .toString(BusiLogConst.BIZLOG_ACTION_PICKUPDATA));
          hafOperateLog.setOpBizId(new Integer(center_head_id));
          hafOperateLog.setOperator(name);
          hafOperateLog.setOpIp(ip);
          hafOperateLog.setOpTime(new Date());
          hafOperateLog.setOrgId(new Integer(orgid));
          hafOperateLogDAO.insert(hafOperateLog);
        } else {
          if (obj[1].toString().equals(BusiConst.ORGBUSINESSTYPE_M)) {
            throw new BusinessException("����δ��ȡ�Ĺ��ʻ������������");
          }
          if (obj[1].toString().equals(BusiConst.ORGBUSINESSTYPE_O)) {
            throw new BusinessException("����δ��ȡ����Ա�����");
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException(e.getMessage());
    }
  }

  public PaymentHeadDAO getPaymentHeadDAO() {
    return paymentHeadDAO;
  }

  public void setPaymentHeadDAO(PaymentHeadDAO paymentHeadDAO) {
    this.paymentHeadDAO = paymentHeadDAO;
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

  /**
   * �����ά��ҳ����ύ
   * 
   * @author ������
   * @2008-02-26
   * @param id
   * @param orgid
   * @param securityInfo
   * @param flag
   * @throws Exception
   * @throws BusinessException
   */
  public void PickInChgPaymentTailMaintain(String id, String orgid,
      SecurityInfo securityInfo, String flag) throws Exception,
      BusinessException {

    try {

      // �ж�DA001���Ƿ�����ύ��¼
      boolean isNoPickIn = autoInfoPickDAODW.isNOPickIn(orgid, id,
          BusiConst.ORGBUSINESSTYPE_N);
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
        autoInfoPick.setType(BusiConst.ORGBUSINESSTYPE_N);
        autoInfoPick.setStatus(BusiConst.OC_NOT_PICK_UP);
        autoInfoPick.setOrgBizDate(new Date());
        if (chgPaymentHead.getPaymentHead() != null) {
          autoInfoPick.setPayHeadId(new Integer(chgPaymentHead.getId()
              .toString()));
        } else {
          autoInfoPick.setPayHeadId(null);
        }

        autoInfoPickDAODW.insert(autoInfoPick);
        // ����BA003��
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(
            BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        if (flag.equals("t1")) {
          // flag=t1Ϊ�����
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_CHANGE_CHGPAYMENT_DO));
        } else {
          // ά����
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_CHANGE_CHGPAYMENT_MAINTAIN));
        }
        // ҵ����־��Ĳ�����Ϊ���ύ����
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

  /**
   * �����ά���ĳ����ύ����
   * 
   * @author ������
   * @2008-02-26
   * @param id
   * @param orgid
   * @param securityInfo
   * @param flag
   * @throws Exception
   * @throws BusinessException
   */
  public void removePickInChgPaymentTailMaintain(String id, String orgid,
      SecurityInfo securityInfo, String flag) throws Exception,
      BusinessException {

    try {
      String status = autoInfoPickDAODW.findStatus(orgid, id,
          BusiConst.ORGBUSINESSTYPE_N);
      // �ж�DA001�иñ�ҵ���״̬�Ƿ�Ϊδ��ȡ
      if (status.equals(BusiConst.OC_NOT_PICK_UP)) {

        // ɾ��DA001
        autoInfoPickDAODW.deleteAutoInfoPick(orgid, id,
            BusiConst.ORGBUSINESSTYPE_N);
        // ����BA003��
        String ip = securityInfo.getUserInfo().getUserIp();
        String name = securityInfo.getUserInfo().getUsername();
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(
            BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        if (flag.equals("c1")) {
          // ����ҳ�泷���ύ
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_CHANGE_CHGPAYMENT_DO));
        } else {
          // ά��ҳ�泷���ύ
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_CHANGE_CHGPAYMENT_MAINTAIN));
        }
        hafOperateLog.setOpButton(Integer
            .toString(BusiLogConst.BIZLOG_ACTION_PPROVALDATA));
        hafOperateLog.setOpBizId(new Integer(id));// AA202.ID
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

}
