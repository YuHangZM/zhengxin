package org.xpup.hafmis.syscollection.tranmng.tranout.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.imp.rule.UtilRule;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.CardMunChange;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.AutoInfoPickDAO;
import org.xpup.hafmis.syscollection.common.dao.BizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.CollParaDAO;
import org.xpup.hafmis.syscollection.common.dao.DocNumCancelDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.HafInterestRateDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowTransOutDAO;
import org.xpup.hafmis.syscollection.common.dao.PickTailDAO;
import org.xpup.hafmis.syscollection.common.dao.TranInOrgDAO;
import org.xpup.hafmis.syscollection.common.dao.TranOutBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.TranOutHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.TranOutOrgDAO;
import org.xpup.hafmis.syscollection.common.dao.TranOutTailDAO;
import org.xpup.hafmis.syscollection.common.daoDW.AutoInfoPickDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.EmpDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.TranOutHeadDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.TranOutTailDAODW;
import org.xpup.hafmis.syscollection.common.domain.entity.AutoInfoPick;
import org.xpup.hafmis.syscollection.common.domain.entity.BizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.DocNumCancel;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.HafInterestRate;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowTransOut;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInOrg;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutOrg;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail;
import org.xpup.hafmis.syscollection.tranmng.tranout.bsinterface.ItranoutBS;
import org.xpup.hafmis.syscollection.tranmng.tranout.dto.TranoutHeadImportDTO;
import org.xpup.hafmis.syscollection.tranmng.tranout.dto.TranoutInfoDTO;
import org.xpup.hafmis.syscollection.tranmng.tranout.dto.TranoutListImportDTO;
import org.xpup.hafmis.syscollection.tranmng.tranout.dto.TranoutTbDTO;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranAF;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranAddAF;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbAF;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbPrintAF;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;

/*
 * @author: ��־ǿ
 * 2007-06-27
 */
public class tranoutBS implements ItranoutBS {
  String temp_headid = "";

  private EmpDAODW empDAODW;

  private TranOutHeadDAO tranOutHeadDAO = null;

  private TranOutHeadDAODW tranOutHeadDAODW = null;

  private TranOutTailDAO tranOutTailDAO = null;

  private AutoInfoPickDAO autoInfoPickDAO;

  private OrgDAO orgDAO = null;

  private AutoInfoPickDAODW autoInfoPickDAODW;

  private TranOutTailDAODW tranOutTailDAODW = null;

  private EmpDAO empDAO = null;

  private CollParaDAO collParaDAO = null;

  private PickTailDAO pickTailDAO = null;

  private HafOperateLogDAO hafOperateLogDAO = null;// BA003

  private OrgHAFAccountFlowTransOutDAO orgHAFAccountFlowTransOutDAO = null;

  private EmpHAFAccountFlowDAO empHAFAccountFlowDAO = null;// aaa102

  private BizActivityLogDAO bizActivityLogDAO = null;// ҵ����־

  private TranOutOrgDAO tranOutOrgDAO = null;// ��λʵ��

  private TranInOrgDAO tranInOrgDAO = null;// ת�뵥λ

  private TranOutBizActivityLogDAO tranOutBizActivityLogDAO = null;// ҵ��

  private HafInterestRateDAO hafInterestRateDAO = null;// ��Ϣ����

  private DocNumCancelDAO docNumCancelDAO = null;

  private OrganizationUnitDAO organizationUnitDAO = null;

  private CollBankDAO collBankDAO = null;

  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO = null;

  public void setAutoInfoPickDAO(AutoInfoPickDAO autoInfoPickDAO) {
    this.autoInfoPickDAO = autoInfoPickDAO;
  }

  public void setAutoInfoPickDAODW(AutoInfoPickDAODW autoInfoPickDAODW) {
    this.autoInfoPickDAODW = autoInfoPickDAODW;
  }

  public void setEmpDAODW(EmpDAODW empDAODW) {
    this.empDAODW = empDAODW;
  }

  public void setTemp_headid(String temp_headid) {
    this.temp_headid = temp_headid;
  }

  public void setTranOutHeadDAODW(TranOutHeadDAODW tranOutHeadDAODW) {
    this.tranOutHeadDAODW = tranOutHeadDAODW;
  }

  public void setTranOutTailDAODW(TranOutTailDAODW tranOutTailDAODW) {
    this.tranOutTailDAODW = tranOutTailDAODW;
  }

  public void setDocNumCancelDAO(DocNumCancelDAO docNumCancelDAO) {
    this.docNumCancelDAO = docNumCancelDAO;
  }

  public void setPickTailDAO(PickTailDAO pickTailDAO) {
    this.pickTailDAO = pickTailDAO;
  }

  public void setTranOutOrgDAO(TranOutOrgDAO tranOutOrgDAO) {
    this.tranOutOrgDAO = tranOutOrgDAO;
  }

  public void setOrgHAFAccountFlowTransOutDAO(
      OrgHAFAccountFlowTransOutDAO orgHAFAccountFlowTransOutDAO) {
    this.orgHAFAccountFlowTransOutDAO = orgHAFAccountFlowTransOutDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public void setTranOutTailDAO(TranOutTailDAO tranOutTailDAO) {
    this.tranOutTailDAO = tranOutTailDAO;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }

  public void setTranOutHeadDAO(TranOutHeadDAO tranOutHeadDAO) {
    this.tranOutHeadDAO = tranOutHeadDAO;
  }

  public void setEmpHAFAccountFlowDAO(EmpHAFAccountFlowDAO empHAFAccountFlowDAO) {
    this.empHAFAccountFlowDAO = empHAFAccountFlowDAO;
  }

  public void setBizActivityLogDAO(BizActivityLogDAO bizActivityLogDAO) {
    this.bizActivityLogDAO = bizActivityLogDAO;
  }

  public void setTranInOrgDAO(TranInOrgDAO tranInOrgDAO) {
    this.tranInOrgDAO = tranInOrgDAO;
  }

  public void setTranOutBizActivityLogDAO(
      TranOutBizActivityLogDAO tranOutBizActivityLogDAO) {
    this.tranOutBizActivityLogDAO = tranOutBizActivityLogDAO;
  }

  public void setHafInterestRateDAO(HafInterestRateDAO hafInterestRateDAO) {
    this.hafInterestRateDAO = hafInterestRateDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }
  public String findCollBank(String collBankid) throws Exception, BusinessException {
    String bankname = "";
    CollBank collBank = collBankDAO.getCollBankByCollBankid(collBankid);
    bankname = collBank.getCollBankName();
    return bankname;
  }
  public String queryMakerPara() throws Exception
  {
    String name="";
    name=collBankDAO.getNamePara();
    return name;
  }
  public String queryTranOutHeadByTailId(String tailid) throws Exception{
    TranOutTail tranOutTail = tranOutTailDAO.queryById(new Integer(tailid));
    return tranOutTail.getTranOutHead().getId().toString();
  }
  public void updateByTranOutHeadId(String headid) throws BusinessException, Exception
  {
    TranOutHead tranOutHead=new TranOutHead();
    tranOutHead = tranOutHeadDAO.queryById(new Integer(headid));
    if(tranOutHead.getTranStatus().toString().equals("1")){
      tranOutHead.setTranStatus(new BigDecimal(2));
    }else if(tranOutHead.getTranStatus().toString().equals("2")){
      String orgid = tranOutHead.getTranOutOrg().getId().toString();
      TranOutHead tranOutHead1 = tranOutHeadDAO.queryTranOutHeadByOrgid(orgid);
      if(tranOutHead1!=null){
        throw new BusinessException("�õ�λ��¼������ҵ�񣬲��������Ǽǣ�����");
      }else{
        tranOutHead.setTranStatus(new BigDecimal(1));
      }
    }else{
      throw new BusinessException("�ñ�ҵ��״̬���ԣ�����");
    }
  }
  // ɾ��������¼
  public void deleteEmp(Integer empid, String ip, String userName,
      String setDate, String count) throws Exception, BusinessException {
    List list = new ArrayList();
    TranOutHead tranOutHead = new TranOutHead();
    try {
      if (empid != null && empid.longValue() != 0) {
        TranOutTail tranOutTail = tranOutTailDAO.queryById(empid);// aa310 ����
        String OrgOutId = tranOutTail.getTranOutHead().getTranOutOrg().getId()
            .toString();// ���ͷ��ת����λID
        String HeadPkid = tranOutTail.getTranOutHead().getId().toString();// 309
        // pkid

        if (count.equals("1")) {
          list = tranOutTailDAO.FindDelEmpInfo(HeadPkid);
          if (list.size() > 0) {
            TranOutTail tranOutTail2 = (TranOutTail) list.get(0);
            tranOutTailDAO.delete(tranOutTail2);// ��ɾ��β��
          }
          tranOutHead = tranOutHeadDAO.queryById(HeadPkid);
          tranOutHeadDAO.delete(tranOutHead);// ��ɾ��ͷ��
          HafOperateLog hafOperateLog = new HafOperateLog();

          hafOperateLog.setOpSys(new Integer(
              BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_TRANINOUT_TRANOUT_DO));
          hafOperateLog.setOpButton(new Integer(
              BusiLogConst.BIZLOG_ACTION_DELETE).toString());
          hafOperateLog.setOpBizId(new Integer(10201010));// ye wu id
          hafOperateLog.setOpIp(ip);
          hafOperateLog.setOrgId(new Integer(OrgOutId));// ת����λ
          hafOperateLog.setOpTime(new Date());
          hafOperateLog.setOperator(userName);
          hafOperateLogDAO.insert(hafOperateLog);

          BizActivityLog bizActivityLog = bizActivityLogDAO
              .queryByBizId_sy(HeadPkid);
          bizActivityLogDAO.deleteBizActivityLog(bizActivityLog);// ɾ��ҵ����־AA319
        } else {
          tranOutTailDAO.delete(tranOutTail);

          HafOperateLog hafOperateLog = new HafOperateLog();
          ;
          hafOperateLog.setOpSys(new Integer(
              BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_TRANINOUT_TRANOUT_DO));
          hafOperateLog.setOpButton(new Integer(
              BusiLogConst.BIZLOG_ACTION_DELETE).toString());
          hafOperateLog.setOpBizId(new Integer(10201010));// ye wu id
          hafOperateLog.setOpIp(ip);
          hafOperateLog.setOrgId(new Integer(OrgOutId));// ת����λ
          hafOperateLog.setOpTime(new Date());
          hafOperateLog.setOperator(userName);
          hafOperateLogDAO.insert(hafOperateLog);
        }
      }
    } catch (Exception e) {
      throw new BusinessException("");
    }

  }

  // ȫ��ɾ��
  public void deleteAll(List taillist, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    try {
      String username = securityInfo.getUserName();
      String ip = securityInfo.getUserIp();
      String setDates = securityInfo.getUserInfo().getBizDate();// �������
      TranOutTail tranOutTail = null;
      if (taillist.size() > 0) {
        tranOutTail = (TranOutTail) taillist.get(0);
      }
      String headid = tranOutTail.getTranOutHead().getId().toString();
      int isOrgEdition = securityInfo.getIsOrgEdition();
      if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// ��λ��
        boolean isNoPickUp = autoInfoPickDAODW.isNOPickUp(tranOutTail
            .getTranOutHead().getTranOutOrg().getId().toString(), headid,
            BusiConst.ORGBUSINESSTYPE_E);
        String stype = autoInfoPickDAODW.findStatus(tranOutTail
            .getTranOutHead().getTranOutOrg().getId().toString(), headid,
            BusiConst.ORGBUSINESSTYPE_E);
        if (stype.equals(BusiConst.OC_PICK_UP)) {
          throw new BusinessException("��������ȡ������ɾ��");
        }
        if (isNoPickUp) {
          throw new BusinessException("���ȳ����ύ��");
        }
      } else {// ���İ�

        for (int i = 0; i < taillist.size(); i++) {
          TranOutTail tail = (TranOutTail) taillist.get(i);
          String orgheadiid = null;
          orgheadiid = autoInfoPickDAO.queryOrgHeadid1(tail.getTranOutHead()
              .getId().toString());
          if (orgheadiid != null) {

            TranOutTail taill = tranOutTailDAODW.queryTailInfo(orgheadiid, tail
                .getEmpId());// ���µ�λ���310
            taill.setPreInterest(new BigDecimal(0.00));
            taill.setCurInterest(new BigDecimal(0.00));
            String pkid = tranOutTailDAODW.queryPkid(orgheadiid);// �����λ��311�е�������
            if (pkid != null) {
              TranInTail tranInTail = tranOutTailDAODW.queryinTailInfo(pkid,
                  tail.getEmpId()); // ���µ�λ���312
              tranInTail.setPreInterest(new BigDecimal(0.00));
              tranInTail.setCurInterest(new BigDecimal(0.00));
            }
          }
          String center_head_id = "";
          String centerBizDate = "";
          autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP,
              center_head_id, centerBizDate, tranOutTail.getTranOutHead()
                  .getTranOutOrg().getId().toString(), headid,
              BusiConst.ORGBUSINESSTYPE_E);
        }
      }
      // ɾ��AA310
      tranOutTailDAO.deleteList(taillist);
      // ɾ��AA309
      TranOutHead tranOutHead = tranOutHeadDAO.queryById(new Integer(headid));
      tranOutHeadDAO.delete(tranOutHead);

      // ɾ��319
      TranOutBizActivityLog tranOutBizActivityLog = new TranOutBizActivityLog();
      List list = tranOutBizActivityLogDAO.queryBiZInfo(headid);
      if (list.size() > 0) {
        for (int t = 0; t < list.size(); t++) {
          tranOutBizActivityLog = (TranOutBizActivityLog) list.get(t);
          tranOutBizActivityLogDAO.delete(tranOutBizActivityLog);
        }
      }
      // ����BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(Integer
          .toString(BusiLogConst.OP_MODE_TRANINOUT_TRANOUT_DO));
      hafOperateLog.setOpButton(Integer
          .toString(BusiLogConst.BIZLOG_ACTION_DELETEALL));
      hafOperateLog.setOpBizId(new Integer(headid));
      hafOperateLog.setOperator(username);
      hafOperateLog.setOpIp(ip);
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOrgId(new Integer(tranOutHead.getTranOutOrg().getId()
          .toString()));
      hafOperateLogDAO.insert(hafOperateLog);
    } catch (Exception b) {
      b.printStackTrace();
      throw new BusinessException(b.getMessage());
    }

  }

  // ��ѯ��λ����
  public Org fingOrgInfo(String orgid, SecurityInfo securityInfo)
      throws BusinessException {
    Org org = null;
    if (orgid != null) {
      org = orgDAO.queryByCriterions(orgid, "2", null, securityInfo);
    }
    return org;
  }

  // ��ѯת�뵥λ
  public Org fingInOrgInfo(String orgid) throws BusinessException {
    Org org = null;
    if (orgid != null) {
      org = orgDAO.queryById(new Integer(orgid));
    }
    return org;
  }

  /**
   * ��ѯת����λ��Ϣ��ת�����
   */
  public TranAF findtranoutOrgName(String orgid, Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    String id = (String) pagination.getQueryCriterions().get("id");
    BigDecimal sumBalance = new BigDecimal(0.00);
    BigDecimal sumInterest = new BigDecimal(0.00);
    BigDecimal sumMoney = new BigDecimal(0.00);
    TranOutTail tranOutTail = null;
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    Org org = null;
    TranOutHead tranOutHead = null;
    TranAF tranAF = new TranAF();
    List tranOutEmplist = new ArrayList();
    List templist = new ArrayList();
    List tranOutHeadList = new ArrayList();
    if (orgid != null) {
      org = orgDAO.queryByCriterions(orgid, "2", null, securityInfo);
      if (org == null) {
        throw new BusinessException("û�д˵�λ��Ϣ��");
      } else {
        tranOutHead = tranOutHeadDAO.queryTranOutHeadByOrgid(orgid);
        System.out.println(tranOutHead + "--------------tranOutHead");
        if (tranOutHead != null) {
          tranOutEmplist = tranOutTailDAO.queryTranOutEmpList(tranOutHead
              .getId(), orderBy, order, start, pageSize, page);
          if (tranOutEmplist != null) {
            for (int i = 0; i < tranOutEmplist.size(); i++) {
              tranOutTail = (TranOutTail) tranOutEmplist.get(i);
              Emp emp = empDAO.queryByCriterions(tranOutTail.getEmpId()
                  .toString(), org.getId().toString());
              tranOutTail.setEmp(emp);
              templist.add(tranOutTail);
            }
          }
          // ����ӣ��жϰ�ť
          // String statetype = autoInfoPickDAODW.findStatus(
          // tranOutTail.getId().toString(),
          // tranOutTail.getTranOutHead().getId().toString(),BusiConst.ORGBUSINESSTYPE_E);
          // tranOutTail.getTranOutHead().setTemp_pickState(BusiTools.getBusiValue(Integer.parseInt(statetype),BusiConst.OC_NOT_PICK_UP_INFO));

          sumBalance = tranOutHead.getSalary();
          sumInterest = tranOutHead.getInterest();
          sumMoney = tranOutHead.getSumSalary();
          count = tranOutTailDAO.queryCountTranOutEmpList(tranOutHead.getId());
          tranAF.setList(tranOutEmplist);
          if (tranOutHead.getTranInOrg() != null) {
            tranAF.setInOrgId(BusiTools.convertTenNumber(tranOutHead
                .getTranInOrg().getId().toString()));
            tranAF.setInOrgName(tranOutHead.getTranInOrg().getOrgInfo()
                .getName());
          }
          tranAF.setNoteNumber(tranOutHead.getNoteNum());
          tranAF.setHeadId(tranOutHead.getId().toString());
        } else {
          // ��ѯ״̬Ϊ3��4
          tranOutHeadList = tranOutHeadDAO.queryTranOutHeadsByOrgid(orgid);
          if (tranOutHeadList != null && tranOutHeadList.size() > 0) {
            tranOutHead = (TranOutHead) tranOutHeadList.get(0);
            tranAF.setTranStatus(tranOutHead.getTranStatus().toString());
          }
        }
      }

      pagination.setNrOfElements(count);
      tranAF.setOutOrgId(BusiTools.convertTenNumber(id));
      tranAF.setOutOrgname(org.getOrgInfo().getName());
      tranAF.setSumBalance(sumBalance.toString());
      tranAF.setSumInterest(sumInterest.toString());
      tranAF.setSumMoney(sumMoney.toString());
    }

    return tranAF;
  }

  // ======================================================================

  /**
   * ��ѯת��ְ����Ϣ
   */
  public TranAddAF findEmpInfo(String empid, String orgid,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // List list = new ArrayList();
    // List Head_List = new ArrayList();// ���ͷ��
    List tail_List = new ArrayList();// ���β��
    TranAddAF tranAddAF = new TranAddAF();
    // InterestDto interestDto = new InterestDto();
    // ClearingInterestInterface clearingInterestBS = new ClearingInterestBS();
    // ClearingInterestDTO clearingInterestDTO=new ClearingInterestDTO();
    Org org = orgDAO.queryById(new Integer(orgid));
    // �жϸ�ְ�������AA002.ID�д��ڣ�����Ϊ֮ǰ�����ת����λ���=AA002.ORG_ID��
    Emp emp = empDAO.queryByCriterions(empid, orgid);
    if (emp == null) {
      throw new BusinessException("�����ڸ�ְ����Ϣ��");
    } else {
      // �ѱ�AA309��ת����λ��ת��״̬��=5��ҵ��IDȡ����������AA310�����Ƿ�������ְ�������ͬ�ļ�¼
      // ����orgid,empid��ѯ�Ƿ���β����Ϣ
      tail_List = tranOutTailDAO.queryTranOutTailList(orgid, empid);
      if (tail_List.size() > 0) {
        throw new BusinessException("��ְ��������δ���˵�ת��ҵ�񣬲��ܰ���ת����");
      } else {
        // �õ���ȡ���и�ְ��δ����ҵ��Ľ��
        BigDecimal pickMoney = new BigDecimal(0.00);
        Object obj = tranOutHeadDAO.queryPickMoney(orgid, empid);
        if (obj != null) {
          pickMoney = new BigDecimal(obj.toString());
        }
        // �õ����˵������и�ְ��δ����ҵ��Ľ��
        BigDecimal adjustMoney = new BigDecimal(0.00);
        adjustMoney = tranOutHeadDAO.queryAdjustMoney(orgid, empid);

        // ��ҳ���е�ֻ��סϢ�ӱ�BA002�ͱ�AA002��ȡ����
        // HafInterestRate hafInterestRate=
        // hafInterestRateDAO.queryHafInterestRate(org.getOrgInfo().getOfficecode());
        // clearingInterestDTO.setCurIntegealSubA(emp.getCurIntegralSubA());
        // clearingInterestDTO.setCurIntegealSubB(emp.getCurIntegralSubB());
        // clearingInterestDTO.setCurIntegealSubC(emp.getCurIntegralSubC());
        // clearingInterestDTO.setCurIntegral(emp.getCurIntegral());
        // clearingInterestDTO.setCurRate(hafInterestRate.getCurRate());
        // clearingInterestDTO.setCurRateA(emp.getCurRateA());
        // clearingInterestDTO.setCurRateB(emp.getCurRateB());
        // clearingInterestDTO.setCurRateC(emp.getCurRateC());
        // clearingInterestDTO.setPreIntegealSubA(emp.getPreIntegralSubA());
        // clearingInterestDTO.setPreIntegealSubB(emp.getPreIntegralSubB());
        // clearingInterestDTO.setPreIntegealSubC(emp.getPreIntegralSubC());
        // clearingInterestDTO.setPreIntegral(emp.getPreIntegral());
        // clearingInterestDTO.setPreRate(hafInterestRate.getPreRate());
        // clearingInterestDTO.setPreRateA(emp.getPreRateA());
        // clearingInterestDTO.setPreRateB(emp.getPreRateB());
        // clearingInterestDTO.setPreRateC(emp.getPreRateC());
        // interestDto =
        // clearingInterestBS.getClearinginterest(clearingInterestDTO);
        BigDecimal preInterest = this.getPreInterest(new Integer(orgid),
            new Integer(empid), securityInfo.getUserInfo().getBizDate());
        BigDecimal curInterest = this.getCurInterest(new Integer(orgid),
            new Integer(empid), securityInfo.getUserInfo().getBizDate());
        BigDecimal sumInterest = this
            .getDistroyTranOutInterest(new Integer(orgid), new Integer(empid),
                securityInfo.getUserInfo().getBizDate());
        preInterest = preInterest.divide(new BigDecimal(1), 2,
            BigDecimal.ROUND_HALF_UP);
        curInterest = curInterest.divide(new BigDecimal(1), 2,
            BigDecimal.ROUND_HALF_UP);
        sumInterest = sumInterest.divide(new BigDecimal(1), 2,
            BigDecimal.ROUND_HALF_UP);
        tranAddAF.setEmp(emp);
        // tranAddAF.setSalary(emp.getPreBalance().add(emp.getCurBalance()).toString());//���
        tranAddAF.setSalary(emp.getPreBalance().add(emp.getCurBalance())
            .subtract(pickMoney).add(adjustMoney).toString());// ���
        tranAddAF.setPreInterest(String.valueOf(preInterest));
        tranAddAF.setCurInterest(String.valueOf(curInterest));
        tranAddAF.setSumInterest(String.valueOf(sumInterest));
        // tranAddAF.setTransum(emp.getPreBalance().add(emp.getCurBalance()).add(sumInterest).divide(new
        // BigDecimal(1),2,BigDecimal.ROUND_HALF_UP).toString());
        tranAddAF.setTransum(emp.getPreBalance().add(emp.getCurBalance()).add(
            sumInterest).subtract(pickMoney).add(adjustMoney).divide(
            new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP).toString());
        return tranAddAF;
      }
    }

  }

  // ���--����AA310
  // ----------------------------------------------------------------------------------------------
  public void InserEmpInfo(String orginid, String ip, String isset, Emp emp,
      String orgid, String preRatea, String curRatea, String reteaSum,
      String transum, String salary, String setDates) throws Exception {

    TranOutHead tranOutHead = new TranOutHead();// ͷ��ʵ��
    TranOutTail tranOutTail = new TranOutTail();
    List HeadList = new ArrayList();
    List TailList = new ArrayList();
    String HeadPkid = null;// ͷ��Pkid

    HafInterestRate hafInterestRate = new HafInterestRate();

    if (emp != null && orgid != null) { // ת����λ���
      Integer empid = (Integer) emp.getEmpId();

      BigDecimal preBal = emp.getBalance();// �������
      BigDecimal curBal = emp.getCurBalance();
      BigDecimal preInter = new BigDecimal(4.23); // ������Ϣ
      BigDecimal curInter = new BigDecimal(4.23); // ������Ϣ
      BigDecimal preInterReg = emp.getPreIntegral();// �������
      BigDecimal curInterReg = emp.getCurIntegral();
      BigDecimal currentRatea = new BigDecimal(0);// ��ǰ���� 313

      BigDecimal CurIntegralSubA = emp.getCurIntegralSubA();// �ֶα������A
      BigDecimal CurIntegralSubB = emp.getCurIntegralSubB();// �ֶα������B
      BigDecimal CurIntegralSubC = emp.getCurIntegralSubC();// �ֶα������C

      BigDecimal CurRateA = emp.getCurRateA();// ��������A
      BigDecimal CurRateB = emp.getCurRateB();// ��������B
      BigDecimal CurRateC = emp.getCurRateC();// ��������C

      BigDecimal PreRateA = emp.getPreRateA();// ��������A
      BigDecimal PreRateB = emp.getPreRateB();// ��������B
      BigDecimal PreRateC = emp.getPreRateC();// ��������C

      HeadList = tranOutHeadDAO.FindPkid(orgid); // ת����λ���,�õ�ͷ������

      if (isset.equals("0")) { // 0
        if (setDates != null && setDates.equals(" ")) {

          String OfficeCode = tranOutHead.getTranOutOrg().getOrgInfo()
              .getOfficecode();// ���officeCode
          List hafIntList = hafInterestRateDAO.queryOfficeWZQ(OfficeCode);

          if (hafIntList.size() > 0) { // ����313Office Code

            currentRatea = hafInterestRate.getCurRate(); // �õ�313��ǰ����
            int day = tranOutTailDAO.getDay(setDates);
            BigDecimal dayb = new BigDecimal(day);

            // ���㱾����Ϣ
            curBal.multiply(dayb); // curBal*day
            curInterReg.subtract(curBal); // -
            curInterReg.multiply(currentRatea);// *
            curInterReg.add(CurIntegralSubA.multiply(CurRateA)).add(
                CurIntegralSubB.multiply(PreRateB)).add(
                CurIntegralSubC.multiply(PreRateC));
            curInterReg.divide(new BigDecimal(365), 0);
            curInter.add(curInterReg);// ������Ϣ

            // ����������Ϣ
            curBal.multiply(dayb); // curBal*day
            preInterReg.subtract(preBal); // -
            preInterReg.multiply(currentRatea);// *
            preInterReg.add(CurIntegralSubA.multiply(PreRateA)).add(
                CurIntegralSubB.multiply(CurRateB)).add(
                CurIntegralSubC.multiply(CurRateC));
            curInterReg.divide(new BigDecimal(365), 0);
            preInter.add(curInterReg);// ������Ϣ
          }
        }
      }

      if (HeadList.size() > 0) { // ����0ͷ����
        tranOutHead = (TranOutHead) HeadList.get(0); // �õ�309pkid,֤��ͷ������ֵ
        String insetStatus = tranOutHead.getTranStatus().toString();

        if (insetStatus.equals("1")) {
          tranOutTail.setTranOutHead(tranOutHead); // ת����λ���
          tranOutTail.setEmpId(empid);// ����Ա�����
          tranOutTail.setPreBalance(preBal);// �������
          tranOutTail.setCurBalance(curBal);
          tranOutTail.setPreInterest(preInter);// ������Ϣ
          tranOutTail.setCurInterest(curInter);
          tranOutTail.setPreIntegral(preInterReg);// ����
          tranOutTail.setCurIntegral(curInterReg);
          tranOutTail.setIsSettIntrerest(new BigDecimal(0.00));// �Ƿ����
          tranOutTailDAO.insert(tranOutTail);

          // ����ҵ����־
          TranOutBizActivityLog tranOutBizActivityLog = new TranOutBizActivityLog();
          tranOutBizActivityLog.setBizid(new Integer(tranOutHead.getId()
              .toString()));
          tranOutBizActivityLog.setAction(new Integer(insetStatus));// 309 ��λ״̬
          tranOutBizActivityLog.setOpTime(new Date());
          tranOutBizActivityLog.setOperator("wzq");
          tranOutBizActivityLogDAO.insert(tranOutBizActivityLog);
        } else if (insetStatus.equals("3")) {
          // 309 û�м�¼!! ����ͷ��
          TranInOrg tranInOrg = null;// ת�뵥λ
          TranOutOrg tranOutOrg = tranOutOrgDAO.queryById(new Integer(orgid));
          if (orginid != null && orginid.equals(" ")) {
            tranInOrg = tranInOrgDAO.queryById(new Integer(orginid));
          }
          tranOutHead.setTranOutOrg(tranOutOrg);
          tranOutHead.setTranInOrg(tranInOrg);
          tranOutHead.setTranStatus(new BigDecimal(1));
          tranOutHeadDAO.insert(tranOutHead);
          // �ٲ���β��
          TranOutTail tranOutTail2 = new TranOutTail();
          TranOutHead tranOutHead2 = tranOutHeadDAO.queryById(new Integer(
              tranOutHead.getId().toString()));

          Emp empInfo = empDAO.queryById(empid);// ��Ա�����ѯ����Ա��
          tranOutTail2.setTranOutHead(tranOutHead2);

          tranOutTail2.setEmpId(empid);
          tranOutTail2.setPreBalance(preBal);
          tranOutTail2.setCurBalance(curBal);
          tranOutTail2.setPreInterest(preInter);// ������Ϣ
          tranOutTail2.setCurInterest(curInter);
          tranOutTail2.setPreIntegral(preInterReg);// ����
          tranOutTail2.setCurIntegral(curInterReg);
          tranOutTail2.setIsSettIntrerest(new BigDecimal(1));
          tranOutTailDAO.insert(tranOutTail2);

          // ����ҵ����־
          TranOutBizActivityLog tranOutBizActivityLog = new TranOutBizActivityLog();
          tranOutBizActivityLog.setBizid(new Integer(tranOutHead.getId()
              .toString()));
          tranOutBizActivityLog.setAction(new Integer(3));
          tranOutBizActivityLog.setOpTime(new Date());
          tranOutBizActivityLog.setOperator("wzq");
          tranOutBizActivityLogDAO.insert(tranOutBizActivityLog);

        }

      } else { // 309 û��!! ����ͷ��
        TranInOrg tranInOrg = null;// ת�뵥λ
        TranOutOrg tranOutOrg = tranOutOrgDAO.queryById(new Integer(orgid));
        if (orginid != null && orginid.equals(" ")) {
          tranInOrg = tranInOrgDAO.queryById(new Integer(orginid));
        }
        tranOutHead.setTranOutOrg(tranOutOrg);
        tranOutHead.setTranInOrg(tranInOrg);
        tranOutHead.setTranStatus(new BigDecimal(1));
        tranOutHeadDAO.insert(tranOutHead);
        // �ٲ���β��
        TranOutTail tranOutTail2 = new TranOutTail();
        TranOutHead tranOutHead2 = tranOutHeadDAO.queryById(new Integer(
            tranOutHead.getId().toString()));

        Emp empInfo = empDAO.queryById(empid);// ��Ա�����ѯ����Ա��
        tranOutTail2.setTranOutHead(tranOutHead2);

        tranOutTail2.setEmpId(empid);
        tranOutTail2.setPreBalance(preBal);
        tranOutTail2.setCurBalance(curBal);
        tranOutTail2.setPreInterest(preInter); // ������Ϣ
        tranOutTail2.setCurInterest(curInter);
        tranOutTail2.setPreIntegral(preInterReg);// ����
        tranOutTail2.setCurIntegral(curInterReg);
        tranOutTail2.setIsSettIntrerest(new BigDecimal(1));
        tranOutTailDAO.insert(tranOutTail2);

        // ����ҵ����־
        TranOutBizActivityLog tranOutBizActivityLog = new TranOutBizActivityLog();
        tranOutBizActivityLog.setBizid(new Integer(tranOutHead.getId()
            .toString()));
        tranOutBizActivityLog.setAction(new Integer(3));
        tranOutBizActivityLog.setOpTime(new Date());
        tranOutBizActivityLog.setOperator("wzq");
        tranOutBizActivityLogDAO.insert(tranOutBizActivityLog);
        // bizActivityLogDAO.insert(bizActivityLog); ���������

      }// ----------------------------------------------------------------------------------------------
    }

  }

  // ת��ά�� ��ѯβ��
  public List queryOrgInfoTb(String outOrgid, String inOrgid,
      Pagination pagination) throws Exception, BufferUnderflowException { // queryOrgInfoTBWZQ

    List list = new ArrayList();

    String orderBy = pagination.getOrderBy();
    String order = pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();

    list = tranOutTailDAO.queryOrgInfoTBWZQ(outOrgid, "0", orderBy, order,
        start, pageSize);

    // ********************************************
    List list2 = new ArrayList();
    BigDecimal sumPay = new BigDecimal(0.00);
    BigDecimal salaryBase = new BigDecimal(0.00);
    BigDecimal preReatA = new BigDecimal(0.00);
    BigDecimal sumsal = new BigDecimal(0.00);
    BigDecimal maxSum = new BigDecimal(0.00);
    TranTbAF tranTbAF = new TranTbAF();

    if (outOrgid != null) {
      Org org = fingOrgInfo(outOrgid, null);
      if (org == null) {
        throw new BusinessException("��λ������BS!!");
      }
      if (org != null) {
        list = tranOutTailDAO.queryOutOrg(outOrgid, orderBy, order, start,
            pageSize, page);
      }
      BigDecimal sum3 = new BigDecimal(0.00);
      TranOutTail tranOutTail = null;
      if (list.size() > 0) {
        for (int i = 0; i < list.size(); i++) {
          tranOutTail = (TranOutTail) list.get(i);
          String empId = tranOutTail.getEmpId().toString();
          List emplist = empDAO.queryByCriterionsWZQ(empId, null);

          for (int j = 0; j < emplist.size(); j++) {// �ۼ�
            Emp emp = (Emp) emplist.get(j);
            salaryBase = emp.getSalaryBase();
            preReatA = emp.getPreRateA();
            sumPay = salaryBase.add(preReatA);
          }
          tranOutTail.setSumPay(sumPay);
          sumsal = tranOutTail.getSumPay();
          maxSum = maxSum.add(sumsal);
          if (emplist.size() != 0)
            try {
              Emp emp = (Emp) emplist.get(0);
              if (emp != null) {
                tranOutTail.setEmp(emp);
                list2.add(tranOutTail);
              } else {
              }
            } catch (Exception e) {
              e.printStackTrace();
            }
        }
      }
    }
    tranTbAF.setList(list2);
    return list2;

  }

  // ��ѯƱ�ݺ�
  public List FindNot_num(String orgid) throws Exception {
    List list = tranOutHeadDAO.FindStatusNoteNum(orgid);
    if (list.size() > 0) {
      return list;
    } else {
      return null;
    }
  }

  // ͨ��ת����λ��Ų�ѯ 309 primary key id
  public List FindOutPkid(String outOrgid) throws Exception, BusinessException {
    List list = new ArrayList();
    list = tranOutHeadDAO.FindPkid(outOrgid);
    if (list.size() > 0) {
      return list;
    } else {
      return null;
    }

  }

  // ��������
  public List findPaylistBatch(Serializable orgOutid, String orgOutName,
      String orgInId, String orgInName, String noteNum, String ip)
      throws Exception {

    List list = new ArrayList();

    for (int i = 0; i < list.size(); i++) {
      TranOutTail tranOutTail = (TranOutTail) list.get(i);
      // bit add ������״̬Ϊɾ����ְ��
      String status = tranOutTail.getEmp().getPayStatus().toString();
      if (status.equals("5")) {
        continue;
      }

      TranoutInfoDTO tranoutInfoDTO = new TranoutInfoDTO();

      tranoutInfoDTO.setOrgOutid(orgOutid.toString());
      tranoutInfoDTO.setOrgOutName(orgOutName);
      tranoutInfoDTO.setOrgInid(orgInId);
      tranoutInfoDTO.setOrgInName(orgInName);
      tranoutInfoDTO.setNoteNum(noteNum);

      tranoutInfoDTO.setEmpId(tranOutTail.getEmp().getEmpInfo().getId()
          .toString());
      tranoutInfoDTO.setEmpName(tranOutTail.getEmp().getEmpInfo().getName());
      tranoutInfoDTO.setCard_king(tranOutTail.getEmp().getEmpInfo()
          .getCardKind().toString());
      tranoutInfoDTO.setCard_num(tranOutTail.getEmp().getEmpInfo().getCardNum()
          .toString());
      tranoutInfoDTO.setIssettinrest(tranOutTail.getIsSettIntrerest()
          .toString());

      list.add(tranoutInfoDTO);
    }

    return list;
  }

  // ����ת��--���ת��-- ƾ֤�Ų�ѯOfficeCode
  public String GetOfficeCode(String pkid) throws Exception {
    List list = tranOutTailDAO.FindOfficeCode(pkid);
    String officeCode = null;
    try {
      if (list.size() > 0) {
        TranOutTail tot = (TranOutTail) list.get(0);
        officeCode = tot.getTranOutHead().getTranOutOrg().getOrgInfo()
            .getOfficecode();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return officeCode;
  }

  // ת��ά��--���ת��-- ƾ֤�Ų�ѯOfficeCode
  public String GetOfficeCodeTb(String Headpkid) throws Exception {
    String officeCode = null;
    if (Headpkid != null && Headpkid.length() > 0) {
      List headList = tranOutHeadDAO.FindOfficeCodeTb(Headpkid);
      if (headList.size() > 0) {
        TranOutHead tranOutHead = (TranOutHead) headList.get(0);
        officeCode = tranOutHead.getTranOutOrg().getOrgInfo().getOfficecode();
      }
    }
    return officeCode;
  }

  // ����ת��--���ת��
  public String updateOrgHafaccountFlow(String pkid, String docNum,
      String noteNum, SecurityInfo securityInfo) throws Exception {
    String temp = "";
    String headid = null;
    TranOutHead tranOutHead = null;
    String HeadPkid = null;// ͷ��Id Pkid
    String sumSalary = null;// ת�����
    String sumInterest = null;// ת����Ϣ
    String DocNum = null;// ƾ֤��
    String NoteNum = null;// Ʊ�ݱ��
    String setDate = securityInfo.getUserInfo().getBizDate();// ��������
    String tailTranHeadId = null;// 310 TranOutHead.id
    List TailList = null;// ͨ��309pkid ��310 Ա����Ϣ ������ 102 ��ˮ

    String userName = securityInfo.getUserName();
    String ip = securityInfo.getUserIp();

    TranOutBizActivityLog tranOutBizActivityLog = new TranOutBizActivityLog();// ҵ��

    String HeadOutOrgid = null;
    if (pkid != null && pkid.length() > 0) {
      TranOutTail tranOutTail1 = tranOutTailDAO.FindTranHeadPkid(pkid);// 310
      // pkid
      HeadPkid = tranOutTail1.getTranOutHead().getId().toString();// �õ�

      // ͷ������
      tranOutHead = tranOutHeadDAO.queryById(new Integer(HeadPkid));// ����ͷ��ʵ��
      tranOutHead.setTranStatus(new BigDecimal(3));
      tranOutHead.setSettDate(setDate);
      tranOutHead.setDocNum(docNum);
      tranOutHead.setNoteNum(noteNum);
      String tranHeadOutid = tranOutHead.getTranOutOrg().getId().toString();// ͷ��ת����λ���

      sumSalary = tranOutHead.getSalary().toString();// ת�����
      sumInterest = tranOutHead.getInterest().toString();// ת����Ϣ
      DocNum = tranOutHead.getDocNum();// ƾ֤��
      NoteNum = tranOutHead.getNoteNum();// Ʊ�ݱ��
      setDate = tranOutHead.getSettDate();// ��������

      HeadOutOrgid = tranOutHead.getTranOutOrg().getId().toString();

      Org org = orgDAO.queryById(new Integer(HeadOutOrgid));
      if(tranOutHead.getTranInOrg()!=null){
        Org orgs = orgDAO.queryById(new Integer(tranOutHead.getTranInOrg()
            .getId().toString()));
        if (org.getOrgInfo().getCollectionBankId().equals(
            orgs.getOrgInfo().getCollectionBankId())) {
          temp = "yes";
        }
      }
      TailList = tranOutTailDAO.fIndTailEmpInfoWZQ(HeadPkid);// 309 pk
      OrgHAFAccountFlowTransOut orgHAFAccountFlowTransOut = orgHAFAccountFlowTransOutDAO
          .queryOrgHAFAccount(HeadPkid);
      if (orgHAFAccountFlowTransOut != null) {
        throw new BusinessException("����ת������ɣ�");
      }
      orgHAFAccountFlowTransOut = new OrgHAFAccountFlowTransOut();
      orgHAFAccountFlowTransOut.setOrg(org);
      orgHAFAccountFlowTransOut.setDebit(new BigDecimal(sumSalary));// ת�����
      orgHAFAccountFlowTransOut.setCredit(new BigDecimal(0.00));
      orgHAFAccountFlowTransOut.setInterest(new BigDecimal(sumInterest));// ת����Ϣ
      orgHAFAccountFlowTransOut.setBizStatus(new BigDecimal(3));
      if (temp.equals("yes")) {
        orgHAFAccountFlowTransOut.setIsFinance(new BigDecimal(2));
      } else {
        orgHAFAccountFlowTransOut.setIsFinance(new BigDecimal(1));// �����ʾ
      }
      orgHAFAccountFlowTransOut.setDocNum(DocNum);
      orgHAFAccountFlowTransOut.setNoteNum(NoteNum);
      orgHAFAccountFlowTransOut.setSettDate(setDate);
      orgHAFAccountFlowTransOut.setBizId(new BigDecimal(tranOutHead.getId()
          .toString()));
      orgHAFAccountFlowTransOut.setReserveaA(userName);
      orgHAFAccountFlowTransOut.setPersonTotal(new Integer(TailList.size()));
      // ����������ʼ
      orgHAFAccountFlowTransOut.setOfficeCode(org.getOrgInfo().getOfficecode());
      orgHAFAccountFlowTransOut.setMoneyBank(org.getOrgInfo()
          .getCollectionBankId());
      BizActivityLog bizActivityLog = bizActivityLogDAO
          .queryBizActivityLogWuht_(tranOutHead.getId().toString(), "E", "1");// ��aa319�в��ҵǼ���
      String registrations = bizActivityLog.getOperator();// ����Ǽ���
      orgHAFAccountFlowTransOut.setRegistrations(registrations);
      // ����
      orgHAFAccountFlowTransOutDAO.insert(orgHAFAccountFlowTransOut); // ����101

      if (TailList.size() > 0) {
        for (int i = 0; i < TailList.size(); i++) {
          EmpHAFAccountFlow empHAFAccountFlow = new EmpHAFAccountFlow();
          TranOutTail tranOutTail = (TranOutTail) TailList.get(i);
          BigDecimal sumBala = tranOutTail.getSumBalance();// Ա���Ľ��
          BigDecimal sumInte = tranOutTail.getSumInterest();// Ա����Ϣ
          String EmpId = tranOutTail.getEmpId().toString();// Ա�����
          tailTranHeadId = tranOutTail.getTranOutHead().getId().toString();// 310
          empHAFAccountFlow.setOrgHAFAccountFlow(orgHAFAccountFlowTransOut);// ��ˮId
          empHAFAccountFlow.setDebit(sumBala); // ����跽
          empHAFAccountFlow.setCredit(new BigDecimal(0.00));// �������
          empHAFAccountFlow.setInterest(sumInte); // ������Ϣ
          empHAFAccountFlow.setEmpId(new Integer(EmpId));// ����Ա����Ϣ
          empHAFAccountFlowDAO.insert(empHAFAccountFlow);
        }
      }
      // ������־
      HafOperateLog hafOperateLog = new HafOperateLog();

      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(new Integer(
          BusiLogConst.OP_MODE_TRANINOUT_TRANOUT_DO).toString());
      hafOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_OPENING)
          .toString());
      hafOperateLog.setOpBizId(new Integer(tranOutHead.getId().toString()));// ҵ����
                                                                            // TailTranHeadId
      hafOperateLog.setOpIp(ip);
      hafOperateLog.setOrgId(new Integer(tranHeadOutid));
      hafOperateLog.setOpTime(new Date());// 
      hafOperateLog.setOperator(securityInfo.getUserName());
      hafOperateLogDAO.insert(hafOperateLog);
      // ҵ����־
      tranOutBizActivityLog
          .setBizid(new Integer(tranOutHead.getId().toString()));// TailTranHeadId
      tranOutBizActivityLog.setAction(new Integer(3));
      tranOutBizActivityLog.setOpTime(new Date());// 
      tranOutBizActivityLog.setOperator(userName);

      tranOutBizActivityLogDAO.insert(tranOutBizActivityLog);
      headid = tranOutHead.getId().toString();
    }
    return headid;
  }

  // ת��ά���������ת��

  public void updateOrgHafaccountFlowTb(String headid, String docNum,
      SecurityInfo securityInfo) throws Exception {
    String temp = "";
    String ip = securityInfo.getUserIp();
    String userName = securityInfo.getUserName();
    List tailList = null;// ͨ��309pkid ��310 Ա����Ϣ ������ 102 ��ˮ
    TranOutHead tranOutHead = null;
    // ȡ���ñ�ҵ���AA309.ID������AA309.TRAN_STATUS=3,AA309.SETT_DATE=����Ա�������
    tranOutHead = tranOutHeadDAO.queryById(new Integer(headid));
    tranOutHead.setTranStatus(new BigDecimal(3));
    tranOutHead.setSettDate(securityInfo.getUserInfo().getBizDate());
    tranOutHead.setDocNum(docNum);
    Org org = orgDAO.queryById(new Integer(tranOutHead.getTranOutOrg().getId()
        .toString()));
    if(tranOutHead.getTranInOrg()!=null){
    Org orgs = orgDAO.queryById(new Integer(tranOutHead.getTranInOrg().getId()+""));
      if (org.getOrgInfo().getCollectionBankId().equals(
          orgs.getOrgInfo().getCollectionBankId())) {
        temp = "yes";
      }
    }
    tailList = tranOutTailDAO.fIndTailEmpInfoWZQ(headid);// 309 pk
    // �Ѹñ�ת��ҵ����뵽��AA101�ͱ�AA102��
    // ����AA101
    OrgHAFAccountFlowTransOut orgHAFAccountFlowTransOut = orgHAFAccountFlowTransOutDAO
        .queryOrgHAFAccount(tranOutHead.getId().toString());
    if (orgHAFAccountFlowTransOut != null) {
      throw new BusinessException("����ת������ɣ�");
    }
    orgHAFAccountFlowTransOut = new OrgHAFAccountFlowTransOut();
    orgHAFAccountFlowTransOut.setOrg(org);
    orgHAFAccountFlowTransOut.setDebit(tranOutHead.getSalary());// ת�����
    orgHAFAccountFlowTransOut.setCredit(new BigDecimal(0.00));
    orgHAFAccountFlowTransOut.setInterest(tranOutHead.getInterest());// ת����Ϣ
    orgHAFAccountFlowTransOut.setBizId(new BigDecimal(tranOutHead.getId()
        .toString()));
    orgHAFAccountFlowTransOut.setBizStatus(new BigDecimal(3));
    orgHAFAccountFlowTransOut.setDocNum(docNum);
    orgHAFAccountFlowTransOut.setNoteNum(tranOutHead.getNoteNum());
    orgHAFAccountFlowTransOut.setSettDate(tranOutHead.getSettDate());
    orgHAFAccountFlowTransOut.setReserveaA(userName);
    orgHAFAccountFlowTransOut.setPersonTotal(new Integer(tailList.size()));
    if (temp.equals("yes")) {
      orgHAFAccountFlowTransOut.setIsFinance(new BigDecimal(2));
    } else {
      orgHAFAccountFlowTransOut.setIsFinance(new BigDecimal(1));// �����ʾ
    }
    // ����������ʼ
    orgHAFAccountFlowTransOut.setOfficeCode(org.getOrgInfo().getOfficecode());
    orgHAFAccountFlowTransOut.setMoneyBank(org.getOrgInfo()
        .getCollectionBankId());
    BizActivityLog bizActivityLog = bizActivityLogDAO.queryBizActivityLogWuht_(
        tranOutHead.getId().toString(), "E", "1");// ��aa319�в��ҵǼ���
    String registrations = bizActivityLog.getOperator();// ����Ǽ���
    orgHAFAccountFlowTransOut.setRegistrations(registrations);
    // ����
    orgHAFAccountFlowTransOutDAO.insert(orgHAFAccountFlowTransOut); // ����101
    // ����AA102
    if (tailList != null && tailList.size() > 0) {
      for (int i = 0; i < tailList.size(); i++) {
        EmpHAFAccountFlow empHAFAccountFlow = new EmpHAFAccountFlow();
        TranOutTail tranOutTail = (TranOutTail) tailList.get(i);
        empHAFAccountFlow.setOrgHAFAccountFlow(orgHAFAccountFlowTransOut);// ��ˮId
        empHAFAccountFlow.setDebit(tranOutTail.getCurBalance().add(
            tranOutTail.getPreBalance())); // ����跽
        empHAFAccountFlow.setCredit(new BigDecimal(0.00));// �������
        empHAFAccountFlow.setInterest(tranOutTail.getCurInterest().add(
            tranOutTail.getPreInterest())); // ������Ϣ
        empHAFAccountFlow.setEmpId(new Integer(tranOutTail.getEmpId()
            .toString()));// ����Ա����Ϣ
        empHAFAccountFlowDAO.insert(empHAFAccountFlow);
      }
    }
    // ������־
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(new Integer(
        BusiLogConst.OP_MODE_TRANINOUT_TRANOUT_MAINTAIN).toString());
    hafOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_OPENING)
        .toString());
    hafOperateLog.setOpBizId(new Integer(tranOutHead.getId().toString()));// ҵ����
                                                                          // TailTranHeadId
    hafOperateLog.setOpIp(ip);
    hafOperateLog.setOrgId(new Integer(tranOutHead.getTranOutOrg().getId()
        .toString()));
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOperator(userName);
    hafOperateLogDAO.insert(hafOperateLog);
    // ҵ����־ BA003
    TranOutBizActivityLog tranOutBizActivityLog = new TranOutBizActivityLog();
    tranOutBizActivityLog.setBizid(new Integer(tranOutHead.getId().toString()));// TailTranHeadId
    tranOutBizActivityLog.setAction(new Integer(3));
    tranOutBizActivityLog.setOpTime(new Date());// 
    tranOutBizActivityLog.setOperator(userName);
    tranOutBizActivityLogDAO.insert(tranOutBizActivityLog);

  }

  /**
   * ά����ѯ Ĭ�ϲ�ѯaa301 pay_status=2 or ��
   * wzq---------------------------------------------------------------------
   */
  public TranTbAF findTranListBydefaultWZQ(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    String orderBy = (String) pagination.getOrderBy();
    String order = pagination.getOrderother();
    String orgOutid = (String) pagination.getQueryCriterions().get("orgOutid");
    String orgOutName = (String) pagination.getQueryCriterions().get(
        "orgOutName");
    String orgInid = (String) pagination.getQueryCriterions().get("orgInid");
    String orgInName = (String) pagination.getQueryCriterions()
        .get("orgInName");
    String noteNum = (String) pagination.getQueryCriterions().get("Note_num");
    String docNum = (String) pagination.getQueryCriterions().get("Doc_num");
    String Dates = (String) pagination.getQueryCriterions().get("Dates");
    String Datesa = (String) pagination.getQueryCriterions().get("Datesa");
    String status = (String) pagination.getQueryCriterions().get("status");
    String tranType = (String) pagination.getQueryCriterions().get("tranType");
    String collBankId = (String) pagination.getQueryCriterions().get(
        "collBankId");
    int page = pagination.getPage();
    List whereList = new ArrayList();
    TranTbAF tranTbAF = new TranTbAF();
    TranOutHead tranOutHead = null;
    BigDecimal summ = new BigDecimal(0.00);// �ܽ��
    BigDecimal salarySum = new BigDecimal(0.00); // ת�����
    BigDecimal Intererst = new BigDecimal(0.00); // ת����Ϣ
    int sumcounts = 0; // ת����Ϣ
    List returnlist = new ArrayList();
    List totallist = null;
    try {
      whereList = tranOutHeadDAO.DefaultQueryWhereTbWZQ(orgOutid, orgOutName,
          orgInid, orgInName, noteNum, docNum, Dates, Datesa, status, orderBy, order,
          start, pageSize, page, tranType, securityInfo, collBankId);
      int count = 0;
      if (whereList != null && whereList.size() > 0) {
        for (int i = 0; i < whereList.size(); i++) {
          tranOutHead = (TranOutHead) whereList.get(i);

          String statetype = autoInfoPickDAODW.findStatus(tranOutHead
              .getTranOutOrg().getId().toString(), tranOutHead.getId()
              .toString(), BusiConst.ORGBUSINESSTYPE_E);
          tranOutHead.setTemp_pickState(BusiTools.getBusiValue(Integer
              .parseInt(statetype), BusiConst.OC_NOT_PICK_UP_INFO));

          tranOutHead.setTranStatus2(BusiTools.getBusiValue(Integer.parseInt(""
              + tranOutHead.getTranStatus()), BusiConst.BUSINESSSTATE));
          TranoutTbDTO dto = new TranoutTbDTO();
          dto.setCounts(tranOutHead.getPersons().toString());
          dto.setDocNum(tranOutHead.getDocNum());
          dto.setInterest(tranOutHead.getInterest().divide(new BigDecimal(1),
              2, BigDecimal.ROUND_HALF_DOWN).toString());
          dto.setMoney(tranOutHead.getSalary().divide(new BigDecimal(1), 2,
              BigDecimal.ROUND_HALF_DOWN).toString());
          dto.setNoteNum(tranOutHead.getNoteNum());
          if (tranOutHead.getTranInOrg() == null) {
            dto.setOrgInid(null);
            dto.setOrgInName("");
          } else {
            dto.setOrgInid(tranOutHead.getTranInOrg().getId());
            dto.setOrgInName(tranOutHead.getTranInOrg().getOrgInfo().getName());
          }
          dto.setOrgOutid(tranOutHead.getTranOutOrg().getId());
          dto.setOrgOutName(tranOutHead.getTranOutOrg().getOrgInfo().getName());
          dto.setSetDate(tranOutHead.getSettDate());
          dto.setStatus(tranOutHead.getTranStatus2());
          dto.setSumMoney(tranOutHead.getSumSalary().divide(new BigDecimal(1),
              2, BigDecimal.ROUND_HALF_DOWN).toString());
          dto.setId(tranOutHead.getId());
          dto.setTemp_pickStatus(tranOutHead.getTemp_pickState());
          returnlist.add(dto);
        }
        count = tranOutHeadDAO.DefaultQueryWhereTbCountWZQ(orgOutid,
            orgOutName, orgInid, orgInName, noteNum, docNum, Dates, Datesa, status,
            orderBy, order, start, pageSize, page, tranType, securityInfo,
            collBankId);
        totallist = tranOutHeadDAO.queryTranOutTotalWZQ(orgOutid, orgOutName,
            orgInid, orgInName, noteNum, docNum, Dates, status, tranType,
            securityInfo, collBankId);
        if (totallist != null) {
          for (int i = 0; i < totallist.size(); i++) {
            TranOutHead tranOutHead2 = (TranOutHead) totallist.get(i);
            salarySum = salarySum.add(tranOutHead2.getSalary());// �ܽ��
            Intererst = Intererst.add(tranOutHead2.getInterest()); // ����Ϣ
            summ = summ.add(tranOutHead2.getSumSalary());
            sumcounts += tranOutHead2.getPersons().intValue();
          }
        }
      }
      pagination.setNrOfElements(count);
      tranTbAF.setList(returnlist);
      tranTbAF.setSum_salary(salarySum);
      tranTbAF.setSum_Interst(Intererst);
      tranTbAF.setSum_sum(summ);
      tranTbAF.setSum_counts(new Integer(sumcounts));

    } catch (Exception exw) {
       exw.printStackTrace();
    }

    return tranTbAF;
  }
  public TranTbAF findTranListBydefaultWZQ_yg(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    String orderBy = (String) pagination.getOrderBy();
    String order = pagination.getOrderother();
    String orgOutid = (String) pagination.getQueryCriterions().get("orgOutid");
    String orgOutName = (String) pagination.getQueryCriterions().get(
    "orgOutName");
    String orgInid = (String) pagination.getQueryCriterions().get("orgInid");
    String orgInName = (String) pagination.getQueryCriterions()
    .get("orgInName");
    String noteNum = (String) pagination.getQueryCriterions().get("Note_num");
    String docNum = (String) pagination.getQueryCriterions().get("Doc_num");
    String Dates = (String) pagination.getQueryCriterions().get("Dates");
    String Datesa = (String) pagination.getQueryCriterions().get("Datesa");
    String status = (String) pagination.getQueryCriterions().get("status");
    String tranType = (String) pagination.getQueryCriterions().get("tranType");
    String collBankId = (String) pagination.getQueryCriterions().get(
    "collBankId");
    int page = pagination.getPage();
    List whereList = new ArrayList();
    TranTbAF tranTbAF = new TranTbAF();
    TranOutHead tranOutHead = null;
    List returnlist = new ArrayList();
    try {
      whereList = tranOutHeadDAO.DefaultQueryWhereTbWZQ_yg(orgOutid, orgOutName,
          orgInid, orgInName, noteNum, docNum, Dates, Datesa, status, orderBy, order,
          start, pageSize, page, tranType, securityInfo, collBankId);
      if (whereList != null && whereList.size() > 0) {
        for (int i = 0; i < whereList.size(); i++) {
          tranOutHead = (TranOutHead) whereList.get(i);
          
          String statetype = autoInfoPickDAODW.findStatus(tranOutHead
              .getTranOutOrg().getId().toString(), tranOutHead.getId()
              .toString(), BusiConst.ORGBUSINESSTYPE_E);
          tranOutHead.setTemp_pickState(BusiTools.getBusiValue(Integer
              .parseInt(statetype), BusiConst.OC_NOT_PICK_UP_INFO));
          
          tranOutHead.setTranStatus2(BusiTools.getBusiValue(Integer.parseInt(""
              + tranOutHead.getTranStatus()), BusiConst.BUSINESSSTATE));
          TranoutTbDTO dto = new TranoutTbDTO();
          dto.setCounts(tranOutHead.getPersons().toString());
          dto.setDocNum(tranOutHead.getDocNum());
          dto.setInterest(tranOutHead.getInterest().divide(new BigDecimal(1),
              2, BigDecimal.ROUND_HALF_DOWN).toString());
          dto.setMoney(tranOutHead.getSalary().divide(new BigDecimal(1), 2,
              BigDecimal.ROUND_HALF_DOWN).toString());
          dto.setNoteNum(tranOutHead.getNoteNum());
          if (tranOutHead.getTranInOrg() == null) {
            dto.setOrgInid(null);
            dto.setOrgInName("");
          } else {
            dto.setOrgInid(tranOutHead.getTranInOrg().getId());
            dto.setOrgInName(tranOutHead.getTranInOrg().getOrgInfo().getName());
          }
          dto.setOrgOutid(tranOutHead.getTranOutOrg().getId());
          dto.setOrgOutName(tranOutHead.getTranOutOrg().getOrgInfo().getName());
          dto.setSetDate(tranOutHead.getSettDate());
          dto.setStatus(tranOutHead.getTranStatus2());
          dto.setSumMoney(tranOutHead.getSumSalary().divide(new BigDecimal(1),
              2, BigDecimal.ROUND_HALF_DOWN).toString());
          dto.setId(tranOutHead.getId());
          dto.setTemp_pickStatus(tranOutHead.getTemp_pickState());
          returnlist.add(dto);
        }
      }
      tranTbAF.setList(returnlist);
      
    } catch (Exception exw) {
      exw.printStackTrace();
    }
    
    return tranTbAF;
  }

  // ת��ά��-- ɾ����λ
  public void DeleteOrg(String pkid, String ip, String username,
      String BizDate, SecurityInfo securityInfo) throws Exception,
      BufferUnderflowException {
    try {
      Integer pkidd = new Integer(pkid);
      TranOutHead tranOutHead = tranOutHeadDAO.queryById(pkidd);
      List list = tranOutTailDAO.queryTranOutHeadID(pkidd);
      int isOrgEdition = securityInfo.getIsOrgEdition();
      if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// ��λ��
        boolean isNoPickUp = autoInfoPickDAODW.isNOPickUp(tranOutHead
            .getTranOutOrg().getId().toString(),
            tranOutHead.getId().toString(), BusiConst.ORGBUSINESSTYPE_E);
        String stype = autoInfoPickDAODW.findStatus(tranOutHead.getTranOutOrg()
            .getId().toString(), tranOutHead.getId().toString(),
            BusiConst.ORGBUSINESSTYPE_E);
        if (stype.equals(BusiConst.OC_PICK_UP)) {
          throw new BusinessException("��������ȡ������ɾ��");
        }
        if (isNoPickUp) {
          throw new BusinessException("���ȳ����ύ��");
        }
      } else {// ���İ�

        String orgheadiid = autoInfoPickDAO.queryOrgHeadid1(pkid);
        if (orgheadiid != null) {
          for (int i = 0; i < list.size(); i++) {
            TranOutTail tail = (TranOutTail) list.get(i);
            TranOutTail taill = tranOutTailDAODW.queryTailInfo(orgheadiid, tail
                .getEmpId());// ���µ�λ���310
            taill.setPreInterest(new BigDecimal(0.00));
            taill.setCurInterest(new BigDecimal(0.00));
            String pkid1 = tranOutTailDAODW.queryPkid(orgheadiid);// �����λ��311�е�������
            if (pkid1 != null) {
              TranInTail tranInTail = tranOutTailDAODW.queryinTailInfo(pkid1,
                  tail.getEmpId()); // ���µ�λ���312
              tranInTail.setPreInterest(new BigDecimal(0.00));
              tranInTail.setCurInterest(new BigDecimal(0.00));
            }
          }
        }
        String center_head_id = "";
        String centerBizDate = "";
        autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP,
            center_head_id, centerBizDate, tranOutHead.getTranOutOrg().getId()
                .toString(), tranOutHead.getId().toString(),
            BusiConst.ORGBUSINESSTYPE_E);
      }
      if (list.size() > 0) {
        for (int i = 0; i < list.size(); i++) {
          TranOutTail tranOutTail = (TranOutTail) list.get(i);
          tranOutTailDAO.delete(tranOutTail);
        }
      }
      if (tranOutHead != null) {
        tranOutHeadDAO.delete(tranOutHead);
      }
      // ɾ��319

      TranOutBizActivityLog tranOutBizActivityLog = tranOutBizActivityLogDAO
          .queryTranOutBizActivityLog(tranOutHead.getId(), new Integer(1));
      tranOutBizActivityLogDAO.delete(tranOutBizActivityLog);
      // ���������־ BS003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(Integer
          .toString(BusiLogConst.OP_MODE_TRANINOUT_TRANOUT_MAINTAIN));
      hafOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_DELETE)
          .toString());
      hafOperateLog.setOpBizId(new Integer(pkid));// ye wu id
      hafOperateLog.setOpIp(ip);
      hafOperateLog.setOrgId(new Integer(tranOutHead.getTranOutOrg().getId()
          .toString()));
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOperator(username);
      hafOperateLogDAO.insert(hafOperateLog);

    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }

  }

  // ת��ά��--�޸�
  public TranOutHead FindOrgTb(String pkid) throws Exception {
    TranOutHead tranOutHead = null;
    try {
      tranOutHead = tranOutHeadDAO.queryById(new Integer(pkid));
      return tranOutHead;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  // ת��ά��--����ת��

  public void UpdateTranHead(String pkid, String username, String ip,
      String setDates) throws Exception {
    // �жϸ�ת����λ����Ƿ����AA309.TRAN_STATUS=1�ļ�¼
    if (pkid != null && pkid.length() > 0) { // 309 pkid
      TranOutHead tranOutHead = tranOutHeadDAO.queryById(new Integer(pkid));
      String orgid = tranOutHead.getTranOutOrg().getId().toString();
      TranOutHead tranOutHead2 = tranOutHeadDAO.queryTranOutHeadByOrgid(orgid);
      if (tranOutHead2 != null) {
        throw new BusinessException("�˵�λ������δ��ɵ�ת����ᣬ���ܳ�����");
      }
      // �����Ѿ�����ת��Ǽǵ�ҵ���ڽ��г���ʱ��Ӧ��ʾ���ñ�ת��ҵ���Ӧ��ת��ҵ���Ѱ����Ƿ�Ҫ������
      List list = tranOutHeadDAO.queryTranOutHeadsByTranOutHeadid(tranOutHead
          .getId().toString());
      if (list != null && list.size() > 0) {
        throw new BusinessException("yes");
      }
      String docNum = tranOutHead.getDocNum();
      String officeCode = tranOutHead.getTranOutOrg().getOrgInfo()
          .getOfficecode();
      String docNumDocument = collParaDAO.getDocNumDesignPara();
      if (docNumDocument.equals("1")) {
        officeCode = tranOutHead.getTranOutOrg().getOrgInfo().getOfficecode();
      } else {
        officeCode = tranOutHead.getTranOutOrg().getOrgInfo()
            .getCollectionBankId();
      }
      String bizYearmonth = setDates.substring(0, 6);
      Integer HeadOrgOutId = (Integer) tranOutHead.getTranOutOrg().getId();
      // ȡ���ñ�ҵ���AA309.ID������AA309.TRAN_STATUS=1,AA309.SETT_DATE=�գ�ɾ��ƾ֤��
      tranOutHead.setTranStatus(new BigDecimal(1));
      tranOutHead.setSettDate("");
      tranOutHead.setDocNum("");// ״̬��Ʊ�ݱ����Ϊ��
      // ͨ��AA309.ID=AA101.BIZ_ID ��AA101.ID=AA102.ORG_FLOW_ID
      // �������Ѹñ�ת��ҵ���ڱ�AA101�ͱ�AA102�в���ļ�¼ɾ������
      OrgHAFAccountFlowTransOut orgHAFAccountFlowTransOut = orgHAFAccountFlowTransOutDAO
          .queryOrgHAFAccount(tranOutHead.getId().toString());
      if (orgHAFAccountFlowTransOut == null) {
        throw new BusinessException("�ñ�ҵ���ѱ������򲻴��ڼ�¼��");
      }

      // ɾ��102
      empHAFAccountFlowDAO.deleteEmpHAFAccountFlowAll(new Integer(
          orgHAFAccountFlowTransOut.getId().toString()));
      // ɾ��101
      orgHAFAccountFlowTransOutDAO.delete(orgHAFAccountFlowTransOut);
      // List empHAFAccountFlowlist = empHAFAccountFlowDAO
      // .queryEmpHAFAccountFlowListByOrgFlowId(orgHAFAccountFlowTransOut.getId().toString());

      // String orgHafpkid = null;
      // List orgHAFlist =
      // orgHAFAccountFlowTransOutDAO.FindOrgHAFAccountWZQ(pkid);
      // String empHAFPkid = null;// 102 pk
      // if (orgHAFlist.size() > 0) {
      // for (int i = 0; i < orgHAFlist.size(); i++) {
      // orgHAFAccountFlowTransOut = (OrgHAFAccountFlowTransOut) orgHAFlist
      // .get(i);
      // orgHafpkid = orgHAFAccountFlowTransOut.getId().toString();
      // if (orgHafpkid.length() > 0) {
      // List EmpHAFList = empHAFAccountFlowDAO
      // .queryEmpHafByCriterionsWZQ(orgHafpkid); // Ϊ���ѯ
      // if (EmpHAFList.size() > 0) {
      // for (int j = 0; j < EmpHAFList.size(); j++) {
      // EmpHAFAccountFlow empHAFAccountFlow = (EmpHAFAccountFlow) EmpHAFList
      // .get(j);
      // empHAFPkid = empHAFAccountFlow.getId().toString();
      // empHAFAccountFlowDAO.deleteById(empHAFPkid);
      // }
      // }
      //
      // orgHAFAccountFlowTransOut = orgHAFAccountFlowTransOutDAO
      // .queryById(new Integer(orgHafpkid));
      // orgHAFAccountFlowTransOutDAO.delete(orgHAFAccountFlowTransOut);
      //
      // }
      // }
      // }
      // ����AA312
      DocNumCancel docNumCancel = new DocNumCancel();
      docNumCancel.setDocNum(docNum.substring(8));
      docNumCancel.setBizYearmonth(bizYearmonth);
      docNumCancel.setOfficeCode(officeCode);
      docNumCancelDAO.insert(docNumCancel);
      // ���������־ BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(new Integer(
          BusiLogConst.OP_MODE_TRANINOUT_TRANOUT_MAINTAIN).toString());
      hafOperateLog.setOpButton(new Integer(
          BusiLogConst.BIZLOG_ACTION_REVOCATION).toString());
      hafOperateLog.setOpBizId(new Integer(pkid));
      hafOperateLog.setOpIp(ip);
      hafOperateLog.setOrgId(HeadOrgOutId);
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOperator(username);
      hafOperateLogDAO.insert(hafOperateLog);
      // ɾ�� 319
      String headPkid = tranOutHead.getId().toString();
      TranOutBizActivityLog tranOutBizActivityLog = tranOutBizActivityLogDAO
          .queryTranOutBizActivityLogByBizid(headPkid);
      tranOutBizActivityLogDAO.delete(tranOutBizActivityLog);
      // if (HeadPkid != null && HeadPkid.length() > 0) {
      // List BizList = tranOutBizActivityLogDAO.queryBiZInfo(HeadPkid);
      // if (BizList.size() > 0) {
      // for (int i = 0; i < BizList.size(); i++) {
      // TranOutBizActivityLog tranOutBizActivityLog = (TranOutBizActivityLog)
      // BizList
      // .get(i);
      // tranOutBizActivityLogDAO.delete(tranOutBizActivityLog);
      // }
      // }
      // }

    }

  }

  // ���--����AA310
  // ----------------------------------------------------------------------------------------------
  public void addTranTail(TranAddAF tranAddAF, SecurityInfo securityInfo)
      throws BusinessException, Exception {
    String empid = tranAddAF.getEmp().getEmpId().toString();
    String orgid = tranAddAF.getOutOrgId();
    String inOrgid = tranAddAF.getInOrgId();
    String headid = tranAddAF.getHeadId();

    // ���ж�һ�¸�ְ���Ƿ��Ѿ�������ҵ��β��ͷ��״̬��¼����ᣩ
    String temp_count = tranInOrgDAO.queryOrgHeadId(orgid, empid);
    // temp_count!=0 ���ڼ�¼
    if (!temp_count.equals("0")) {
      throw new BusinessException("��ְ��ת����Ϣ�Ѿ�����ϵͳ!");
    } else {
      TranOutOrg tranOutOrg = null;
      String typetran = tranAddAF.getTypetran();
      TranInOrg tranInOrg = null;
      if (typetran.equals("2")) {
        if (tranAddAF.getCurInterest() == null
            || tranAddAF.getCurInterest().length() <= 0
            || tranAddAF.getYesNo() == null
            && tranAddAF.getYesNo().length() <= 0
            || tranAddAF.getPreInterest() == null
            && tranAddAF.getPreInterest().length() <= 0) {
          throw new BusinessException("��ѡ���Ƿ��Ϣ��");
        }
      }

      TranOutHead tranOutHead = null;
      if (!orgid.equals("") && orgid.length() > 0) {
        tranOutOrg = tranOutOrgDAO.queryById(new Integer(orgid));
      }
      // ����ͷ����Ϣ
      if (headid.equals("") && headid.length() <= 0) {
        if (!orgid.equals("") && orgid.length() > 0) {
          TranOutHead tranOutHead1 = tranOutHeadDAO
              .queryTranOutHeadByOrgid(orgid);
          if (tranOutHead1 == null) {
            tranOutHead = new TranOutHead();
            tranOutHead.setTranOutOrg(tranOutOrg);
            if (!inOrgid.equals("") && inOrgid.length() > 0) {
              tranInOrg = tranInOrgDAO.queryById(new Integer(inOrgid));
              tranOutHead.setTranInOrg(tranInOrg);
            } else {
              TranInOrg tranInOrg1 = null;
              tranOutHead.setTranInOrg(tranInOrg1);
            }
            tranOutHead.setNoteNum(tranAddAF.getNoteNum());
            tranOutHead.setTranStatus(new BigDecimal(1));
            tranOutHeadDAO.insert(tranOutHead);
            // ����ҵ����־319
            TranOutBizActivityLog tranOutBizActivityLog = new TranOutBizActivityLog();
            tranOutBizActivityLog.setBizid(new Integer(tranOutHead.getId()
                .toString()));
            tranOutBizActivityLog.setAction(new Integer(1));// 309 ��λ״̬
            tranOutBizActivityLog.setOpTime(new Date());
            tranOutBizActivityLog.setOperator(securityInfo.getUserName());
            tranOutBizActivityLogDAO.insert(tranOutBizActivityLog);
          } else {
            tranOutHead = tranOutHead1;
          }
        }
      } else {
        tranOutHead = tranOutHeadDAO.queryTranOutHeadByOrgid(orgid);
      }
      // ����β����Ϣ310
      Emp emp = empDAO.queryByCriterions(empid, orgid);
      TranOutTail tranOutTail = new TranOutTail();
      tranOutTail.setCurBalance(emp.getCurBalance());
      tranOutTail.setCurIntegral(emp.getCurIntegral());
      tranOutTail.setTranin_empid(tranAddAF.getTranin_empId());
      tranOutTail.setCurIntegralSubA(emp.getCurIntegralSubA());
      tranOutTail.setCurIntegralSubB(emp.getCurIntegralSubB());
      tranOutTail.setCurIntegralSubC(emp.getCurIntegralSubC());
      tranOutTail.setCurIntegralSubD(emp.getCurIntegralSubD());
      tranOutTail.setCurIntegralSubE(emp.getCurIntegralSubE());
      tranOutTail.setCurIntegralSubF(emp.getCurIntegralSubF());
      tranOutTail.setCurIntegralSubG(emp.getCurIntegralSubG());
      tranOutTail.setCurIntegralSubH(emp.getCurIntegralSubH());
      tranOutTail.setCurIntegralSubI(emp.getCurIntegralSubI());
      tranOutTail.setCurIntegralSubJ(emp.getCurIntegralSubJ());
      tranOutTail.setCurIntegralSubK(emp.getCurIntegralSubK());
      tranOutTail.setCurIntegralSubL(emp.getCurIntegralSubL());
      if (typetran.equals("2")) {
        tranOutTail.setCurInterest(new BigDecimal(tranAddAF.getCurInterest()));
      }
      tranOutTail.setCurRateA(emp.getCurRateA());
      tranOutTail.setCurRateB(emp.getCurRateB());
      tranOutTail.setCurRateC(emp.getCurRateC());
      tranOutTail.setCurRateD(emp.getCurRateD());
      tranOutTail.setCurRateE(emp.getCurRateE());
      tranOutTail.setCurRateF(emp.getCurRateF());
      tranOutTail.setCurRateG(emp.getCurRateG());
      tranOutTail.setCurRateH(emp.getCurRateH());
      tranOutTail.setCurRateI(emp.getCurRateI());
      tranOutTail.setCurRateJ(emp.getCurRateJ());
      tranOutTail.setCurRateK(emp.getCurRateK());
      tranOutTail.setCurRateL(emp.getCurRateL());

      tranOutTail.setEmpId(new Integer(emp.getEmpId().toString()));
      tranOutTail.setIsSettIntrerest(new BigDecimal(tranAddAF.getYesNo()));
      tranOutTail.setPreBalance(emp.getPreBalance());
      tranOutTail.setPreIntegral(emp.getPreIntegral());
      tranOutTail.setPreIntegralSubA(emp.getPreIntegralSubA());
      tranOutTail.setPreIntegralSubB(emp.getPreIntegralSubB());
      tranOutTail.setPreIntegralSubC(emp.getPreIntegralSubC());
      tranOutTail.setPreIntegralSubD(emp.getPreIntegralSubD());
      tranOutTail.setPreIntegralSubE(emp.getPreIntegralSubE());
      tranOutTail.setPreIntegralSubF(emp.getPreIntegralSubF());
      tranOutTail.setPreIntegralSubG(emp.getPreIntegralSubG());
      tranOutTail.setPreIntegralSubH(emp.getPreIntegralSubH());
      tranOutTail.setPreIntegralSubI(emp.getPreIntegralSubI());
      tranOutTail.setPreIntegralSubJ(emp.getPreIntegralSubJ());
      tranOutTail.setPreIntegralSubK(emp.getPreIntegralSubK());
      tranOutTail.setPreIntegralSubL(emp.getPreIntegralSubL());
      if (typetran.equals("2")) {
        tranOutTail.setPreInterest(new BigDecimal(tranAddAF.getPreInterest()));
      }
      tranOutTail.setPreRateA(emp.getPreRateA());
      tranOutTail.setPreRateB(emp.getPreRateB());
      tranOutTail.setPreRateC(emp.getPreRateC());
      tranOutTail.setPreRateD(emp.getPreRateD());
      tranOutTail.setPreRateE(emp.getPreRateE());
      tranOutTail.setPreRateF(emp.getPreRateF());
      tranOutTail.setPreRateG(emp.getPreRateG());
      tranOutTail.setPreRateH(emp.getPreRateH());
      tranOutTail.setPreRateI(emp.getPreRateI());
      tranOutTail.setPreRateJ(emp.getPreRateJ());
      tranOutTail.setPreRateK(emp.getPreRateK());
      tranOutTail.setPreRateL(emp.getPreRateL());

      tranOutTail.setTranOutHead(tranOutHead);
      tranOutTail.setTranReason(tranAddAF.getTranReason());
      tranOutTailDAO.insert(tranOutTail);
      // ����BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(Integer
          .toString(BusiLogConst.OP_MODE_TRANINOUT_TRANOUT_DO));
      hafOperateLog.setOpButton(Integer
          .toString(BusiLogConst.BIZLOG_ACTION_ADD));
      hafOperateLog.setOpBizId(new Integer(tranOutHead.getId().toString()));
      hafOperateLog.setOperator(securityInfo.getUserName());
      hafOperateLog.setOpIp(securityInfo.getUserIp());
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOrgId(new Integer(tranOutHead.getTranOutOrg().getId()
          .toString()));
      hafOperateLogDAO.insert(hafOperateLog);
    }

  }

  // ɾ��������¼
  public void deleteTailInfo(String tailid, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    try {
      List list = new ArrayList();
      TranOutHead tranOutHead = null;
      // ɾ���ĸ�����¼�Ƿ�Ϊ�ñ�ҵ������һ����¼.ͨ��AA309.ID��AA310.TRAN_OUT_HEAD_ID����
      TranOutTail tranOutTail = null;
      tranOutTail = tranOutTailDAO.queryById(new Integer(tailid));
      tranOutHead = tranOutHeadDAO.queryById(new Integer(tranOutTail
          .getTranOutHead().getId().toString()));
      String headid = tranOutHead.getId().toString();
      List list1 = tranOutTailDAO.fIndTailEmpInfoWZQ(tranOutHead.getId()
          .toString());
      int isOrgEdition = securityInfo.getIsOrgEdition();
      if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {
        String orgheadiid = autoInfoPickDAO.queryOrgHeadid1(tranOutTail
            .getTranOutHead().getId().toString());
        if (orgheadiid != null) {
          TranOutTail taill = tranOutTailDAODW.queryTailInfo(orgheadiid,
              tranOutTail.getEmpId());// ���µ�λ���310
          taill.setPreInterest(new BigDecimal(0.00));
          taill.setCurInterest(new BigDecimal(0.00));
          String pkid1 = tranOutTailDAODW.queryPkid(orgheadiid);// �����λ��311�е�������
          if (pkid1 != null) {
            TranInTail tranInTail = tranOutTailDAODW.queryinTailInfo(pkid1,
                tranOutTail.getEmpId()); // ���µ�λ���312
            tranInTail.setPreInterest(new BigDecimal(0.00));
            tranInTail.setCurInterest(new BigDecimal(0.00));
          }
        }

      }
      if (list1.size() == 1) {// ��ʣ�����һ����

        if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// ��λ��
          boolean isNoPickUp = autoInfoPickDAODW.isNOPickUp(tranOutHead
              .getTranOutOrg().getId().toString(), tranOutHead.getId()
              .toString(), BusiConst.ORGBUSINESSTYPE_E);
          String stype = autoInfoPickDAODW.findStatus(tranOutHead
              .getTranOutOrg().getId().toString(), tranOutHead.getId()
              .toString(), BusiConst.ORGBUSINESSTYPE_E);
          if (stype.equals(BusiConst.OC_PICK_UP)) {
            throw new BusinessException("��������ȡ������ɾ��");
          }
          if (isNoPickUp) {
            throw new BusinessException("���ȳ����ύ��");
          }
        } else {// ���İ�
          String center_head_id = "";
          String centerBizDate = "";
          autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP,
              center_head_id, centerBizDate, tranOutHead.getTranOutOrg()
                  .getId().toString(), tranOutHead.getId().toString(),
              BusiConst.ORGBUSINESSTYPE_E);

        }
      }

      // ɾ��β����Ϣ
      tranOutTailDAO.delete(tranOutTail);
      list = tranOutTailDAO.fIndTailEmpInfoWZQ(tranOutHead.getId().toString());
      if (list.size() <= 0) {
        // �����һ����¼
        // ɾ��ͷ��
        tranOutHeadDAO.delete(tranOutHead);
        // ɾ��ҵ����־319
        TranOutBizActivityLog tranOutBizActivityLog = tranOutBizActivityLogDAO
            .queryTranOutBizActivityLog(tranOutHead.getId(), new Integer(1));
        tranOutBizActivityLogDAO.delete(tranOutBizActivityLog);
      }
      // ����BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(Integer
          .toString(BusiLogConst.OP_MODE_TRANINOUT_TRANOUT_DO));
      hafOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_DELETE)
          .toString());
      hafOperateLog.setOpBizId(new Integer(headid));// ye wu id
      hafOperateLog.setOpIp(securityInfo.getUserIp());
      hafOperateLog.setOrgId(new Integer(tranOutHead.getTranOutOrg().getId()
          .toString()));// ת����λ
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOperator(securityInfo.getUserName());
      hafOperateLogDAO.insert(hafOperateLog);
    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }

  }

  // ��������
  public List findPaylistBatch(Pagination pagination) throws Exception {
    String orgOutid = (String) pagination.getQueryCriterions().get("orgOutid");
    String orgOutName = (String) pagination.getQueryCriterions().get(
        "orgOutName");
    String orgInId = (String) pagination.getQueryCriterions().get("orgInId");
    String orgInName = (String) pagination.getQueryCriterions()
        .get("orgInName");
    if (orgInId != null && orgInId.length() > 0 && !orgInId.equals("")) {
      if (orgInName.equals("") && orgInName.length() <= 0) {
        orgInId = "";
      }
    }
    String noteNum = (String) pagination.getQueryCriterions().get("noteNum");
    String ip = (String) pagination.getQueryCriterions().get("ip");
    String name = (String) pagination.getQueryCriterions().get("name");
    BusiLogConst busiLogConst = new BusiLogConst();
    HafOperateLog hafOperateLog = new HafOperateLog();

    String orderArray[] = (String[]) pagination.getQueryCriterions().get(
        "orderArray");

    List list = new ArrayList();

    try {
      // List list2 = empDAO.queryEmpByOrgIdSL(orgOutid);
      List list2 = empDAO.queryEmpByOrgIdSLPL(orgOutid, orderArray);
      if (list2.size() > 0) {
        for (int i = 0; i < list2.size(); i++) {
          Emp emp = (Emp) list2.get(i);
          TranoutInfoDTO tranoutInfoDTO = new TranoutInfoDTO();
          tranoutInfoDTO.setOrgOutid(orgOutid.toString());
          tranoutInfoDTO.setOrgOutName(orgOutName);
          tranoutInfoDTO.setOrgInid(orgInId);
          tranoutInfoDTO.setOrgInName(orgInName);
          tranoutInfoDTO.setNoteNum(noteNum);

          tranoutInfoDTO.setEmpId(emp.getEmpId().toString());
          tranoutInfoDTO.setEmpName(emp.getEmpInfo().getName());
          tranoutInfoDTO
              .setCard_king(emp.getEmpInfo().getCardKind().toString());
          tranoutInfoDTO.setCard_num(emp.getEmpInfo().getCardNum().toString());
          tranoutInfoDTO.setIssettinrest("");
          tranoutInfoDTO.setTranReason("");
          list.add(tranoutInfoDTO);
        }

        // ����ҵ����־

        hafOperateLog.setOpSys(new Integer(
            busiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        hafOperateLog.setOpModel(Integer
            .toString(busiLogConst.OP_MODE_TRANINOUT_TRANOUT_DO));
        hafOperateLog.setOpButton(Integer
            .toString(busiLogConst.BIZLOG_ACTION_EXP));
        hafOperateLog.setOpBizId(new Integer(0));
        hafOperateLog.setOperator(name);
        hafOperateLog.setOpIp(ip);
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOrgId(new Integer(orgOutid));
        hafOperateLogDAO.insert(hafOperateLog);

      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // ��������
  public List addTranoutListBatch(List addtranoutHeadImportList,
      List addtranoutListImportList, String orgOutid, String orgOutName,
      String orgInId, String orgInName, String noteNum, String ip, String name,
      String date, SecurityInfo securityInfo) throws BusinessException,Exception {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    TranOutHead tranOutHead = new TranOutHead();// ͷ��ʵ��
    BusiLogConst busiLogConst = new BusiLogConst();
    HafOperateLog hafOperateLog = new HafOperateLog();
    TranOutOrg tranOutOrg = null;
    TranInOrg tranInOrg = null;

    if (addtranoutHeadImportList.size() <= 0) {
      throw new BusinessException("��������Ϊ�գ�");
    }
    if (addtranoutListImportList.size() <= 0) {
      throw new BusinessException("��������Ϊ�գ�");
    }
    TranoutHeadImportDTO tranoutHeadImportDTO = (TranoutHeadImportDTO) addtranoutHeadImportList
        .get(1);

    if (!orgOutid.equals(tranoutHeadImportDTO.getOrgOutid())) {
      throw new BusinessException("ѡ��ĵ����ļ��������ת����λ��Ų�����");
    }
    // if (!orgInId.equals(tranoutHeadImportDTO.getOrgInid())) {
    //
    // throw new BusinessException("ѡ��ĵ����ļ��������ת�뵥λ��Ų�����");
    // }
    if (tranoutHeadImportDTO.getOrgInid() != null
        && tranoutHeadImportDTO.getOrgInid().length() > 0) {
      Org org1 = orgDAO
          .queryById(new Integer(tranoutHeadImportDTO.getOrgInid()));
      if (org1 != null) {
        if (!org1.getOrgInfo().getOpenstatus().equals("2")) {
          throw new BusinessException("ת�뵥λ��Ų���ȷ��");
        }
      } else {
        throw new BusinessException("ת�뵥λ��Ų���ȷ��");
      }
    }
    Org org = null;
    org = orgDAO.queryByCriterions(tranoutHeadImportDTO.getOrgOutid(), "2",
        null, securityInfo);

    // ����ͷ����Ϣ

    if (!orgOutid.equals("") && orgOutid.length() > 0) {
      tranOutOrg = tranOutOrgDAO.queryById(new Integer(orgOutid));
    }
    if (!orgInId.equals("") && orgInId.length() > 0) {
      tranInOrg = tranInOrgDAO.queryById(new Integer(orgInId));
    }
    tranOutHead.setTranOutOrg(tranOutOrg);
    tranOutHead.setTranInOrg(tranInOrg);
    tranOutHead.setNoteNum(noteNum);
    tranOutHead.setTranStatus(new BigDecimal(1));

    Serializable headId = tranOutHeadDAO.insert(tranOutHead);
    temp_headid = headId.toString();
    // ����aa310
    for (int i = 1; i < addtranoutListImportList.size(); i++) {
      TranoutListImportDTO tranoutListImportDTO = (TranoutListImportDTO) addtranoutListImportList
          .get(i);
      UtilRule utilRule = new UtilRule();
      TranOutTail tranOutTail = new TranOutTail();
      TranoutInfoDTO tranoutInfoDTO = new TranoutInfoDTO();

      if (tranoutListImportDTO.getIssettinrest() == null
          || tranoutListImportDTO.getIssettinrest().equals("")) {
        throw new BusinessException("�������Ϣ���� ");
      } else if (utilRule.moneyRule(tranoutListImportDTO.getIssettinrest(), 15,
          2) == false) {
        tranoutInfoDTO.setIssettinrest(tranoutListImportDTO.getIssettinrest());
        list.add(tranoutInfoDTO);
        throw new BusinessException("�������Ϣ���� ");
      } else if (!tranoutListImportDTO.getIssettinrest().trim().equals("1")
          && !tranoutListImportDTO.getIssettinrest().trim().equals("2")) {
        throw new BusinessException("�������Ϣ���� ");
      }
      Emp emp = empDAO.queryByCriterions(tranoutListImportDTO.getEmpId(),
          orgOutid);
      
      if (emp == null) {
        throw new BusinessException("�����ְ��"+tranoutListImportDTO.getEmpId()+"�������ڱ���λ");
      }
      if(tranoutListImportDTO.getTranin_empid()!=null && !tranoutListImportDTO.getTranin_empid().equals("")){
        Emp emp_in = empDAO.queryByCriterions(tranoutListImportDTO.getTranin_empid(),
            orgInId);
        if(emp_in==null){
          throw new BusinessException("��"+(i+3)+"��ת��ְ��������ת�뵥λ");
        }
        if(!emp.getEmpInfo().getName().equals(emp_in.getEmpInfo().getName()) || !emp.getEmpInfo().getCardNum().equals(emp_in.getEmpInfo().getCardNum())){
          throw new BusinessException("��"+(i+3)+"��ת��ְ���������֤��ת��ְ���������֤��ͬ");
        }
      }
      String orginid = "";
      if (tranoutHeadImportDTO.getOrgInid() != null) {
        orginid = tranoutHeadImportDTO.getOrgInid();
      }
      if (orgOutid.equals(orginid)) {
        if (tranoutListImportDTO.getTranin_empid() == null
            || tranoutListImportDTO.getTranin_empid().equals("")) {
          throw new BusinessException("ת��ְ����ű���");
        }
      }
      if (orginid.equals("")) {
        if (tranoutListImportDTO.getTranin_empid() == null
            || tranoutListImportDTO.getTranin_empid().equals("")) {

        } else {
          throw new BusinessException("ת��ְ����Ų�������");
        }
      }
      tranOutTail.setCurBalance(emp.getCurBalance());
      tranOutTail.setCurIntegral(emp.getCurIntegral());
      tranOutTail.setCurIntegralSubA(emp.getCurIntegralSubA());
      tranOutTail.setCurIntegralSubB(emp.getCurIntegralSubB());
      tranOutTail.setCurIntegralSubC(emp.getCurIntegralSubC());

      tranOutTail.setCurIntegralSubD(emp.getCurIntegralSubD());
      tranOutTail.setCurIntegralSubE(emp.getCurIntegralSubE());
      tranOutTail.setCurIntegralSubF(emp.getCurIntegralSubF());
      tranOutTail.setCurIntegralSubG(emp.getCurIntegralSubG());
      tranOutTail.setCurIntegralSubH(emp.getCurIntegralSubH());
      tranOutTail.setCurIntegralSubI(emp.getCurIntegralSubI());
      tranOutTail.setCurIntegralSubJ(emp.getCurIntegralSubJ());
      tranOutTail.setCurIntegralSubK(emp.getCurIntegralSubK());
      tranOutTail.setCurIntegralSubL(emp.getCurIntegralSubL());

      tranOutTail.setCurRateA(emp.getCurRateA());
      tranOutTail.setCurRateB(emp.getCurRateB());
      tranOutTail.setCurRateC(emp.getCurRateC());
      tranOutTail.setCurRateD(emp.getCurRateD());
      tranOutTail.setCurRateE(emp.getCurRateE());
      tranOutTail.setCurRateF(emp.getCurRateF());
      tranOutTail.setCurRateG(emp.getCurRateG());
      tranOutTail.setCurRateH(emp.getCurRateH());
      tranOutTail.setCurRateI(emp.getCurRateI());
      tranOutTail.setCurRateJ(emp.getCurRateJ());
      tranOutTail.setCurRateK(emp.getCurRateK());
      tranOutTail.setCurRateL(emp.getCurRateL());

      tranOutTail.setEmpId(new Integer(tranoutListImportDTO.getEmpId()
          .toString()));
      tranOutTail.setIsSettIntrerest(new BigDecimal(tranoutListImportDTO
          .getIssettinrest()));
      tranOutTail.setTranReason(tranoutListImportDTO.getTranReason());
      tranOutTail.setPreBalance(emp.getPreBalance());
      tranOutTail.setPreIntegral(emp.getPreIntegral());
      tranOutTail.setPreIntegralSubA(emp.getPreIntegralSubA());
      tranOutTail.setPreIntegralSubB(emp.getPreIntegralSubB());
      tranOutTail.setPreIntegralSubC(emp.getPreIntegralSubC());
      tranOutTail.setPreIntegralSubD(emp.getPreIntegralSubD());
      tranOutTail.setPreIntegralSubE(emp.getPreIntegralSubE());
      tranOutTail.setPreIntegralSubF(emp.getPreIntegralSubF());
      tranOutTail.setPreIntegralSubG(emp.getPreIntegralSubG());
      tranOutTail.setPreIntegralSubH(emp.getPreIntegralSubH());
      tranOutTail.setPreIntegralSubI(emp.getPreIntegralSubI());
      tranOutTail.setPreIntegralSubJ(emp.getPreIntegralSubJ());
      tranOutTail.setPreIntegralSubK(emp.getPreIntegralSubK());
      tranOutTail.setPreIntegralSubL(emp.getPreIntegralSubL());
      // tranOutTail.setPreIntegralSubL(tranoutListImportDTO.get);

      if (tranoutListImportDTO.getIssettinrest().trim().equals("1")) {
        tranOutTail.setPreInterest(this.getPreInterest(new Integer(tranOutHead
            .getTranOutOrg().getId().toString()), new Integer(emp.getEmpId()
            .toString()), securityInfo.getUserInfo().getBizDate()));
        tranOutTail.setCurInterest(this.getCurInterest(new Integer(tranOutHead
            .getTranOutOrg().getId().toString()), new Integer(emp.getEmpId()
            .toString()), securityInfo.getUserInfo().getBizDate()));
      } else {
        tranOutTail.setPreInterest(new BigDecimal(0.00));
        tranOutTail.setCurInterest(new BigDecimal(0.00));
      }
      tranOutTail.setPreRateA(emp.getPreRateA());
      tranOutTail.setPreRateB(emp.getPreRateB());
      tranOutTail.setPreRateC(emp.getPreRateC());
      tranOutTail.setPreRateD(emp.getPreRateD());
      tranOutTail.setPreRateE(emp.getPreRateE());
      tranOutTail.setPreRateF(emp.getPreRateF());
      tranOutTail.setPreRateG(emp.getPreRateG());
      tranOutTail.setPreRateH(emp.getPreRateH());
      tranOutTail.setPreRateI(emp.getPreRateI());
      tranOutTail.setPreRateJ(emp.getPreRateJ());
      tranOutTail.setPreRateK(emp.getPreRateK());
      tranOutTail.setPreRateL(emp.getPreRateL());

      tranOutTail.setTranOutHead(tranOutHead);
      String inempid = tranoutListImportDTO.getTranin_empid();
      if (inempid == null || inempid.equals("")) {
        tranOutTail.setTranin_empid(null);
      } else {
        Emp emp1 = empDAO.queryByCriterions(inempid, orgInId);
        if (emp1 == null) {
          throw new BusinessException("�����ڸ�ְ����Ϣ��");
        }
        tranOutTail.setTranin_empid(new Integer(inempid));
      }
      tranOutTailDAO.insert(tranOutTail);

    }

    // ����AA319��
    TranOutBizActivityLog tranOutBizActivityLog = new TranOutBizActivityLog();
    tranOutBizActivityLog.setBizid(new Integer(tranOutHead.getId().toString()));
    tranOutBizActivityLog.setAction(new Integer(1));
    tranOutBizActivityLog.setOpTime(new Date());
    tranOutBizActivityLog.setOperator(name);
    tranOutBizActivityLogDAO.insert(tranOutBizActivityLog);
    TranoutHeadImportDTO dto = (TranoutHeadImportDTO) addtranoutHeadImportList
        .get(0);
    String orgid = dto.getOrgOutid();
    if (orgid != null) {// ˵�����Ǵ���ȡ�����ģ�����ba003��
      // ����BA003��
      String id = tranOutHead.getId().toString();
      hafOperateLog
          .setOpSys(new Integer(busiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(Integer
          .toString(busiLogConst.OP_MODE_TRANINOUT_TRANOUT_DO));
      hafOperateLog.setOpButton(Integer
          .toString(busiLogConst.BIZBLOG_ACTION_IMP));
      hafOperateLog.setOpBizId(new Integer(id));
      hafOperateLog.setOperator(name);
      hafOperateLog.setOpIp(ip);
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOrgId(new Integer(orgOutid));
      hafOperateLogDAO.insert(hafOperateLog);
    }
    return list;
  }

  /**
   * ��ӡ
   */
  public List getTranTailListPrint(String headid) {
    List list = null;
    list = tranOutTailDAO.fIndTailEmpInfoWZQ(headid);
    List templist = new ArrayList();
    if (list != null) {
      for (int i = 0; i < list.size(); i++) {
        TranOutTail tranOutTail = (TranOutTail) list.get(i);
        Emp emp = empDAO.queryByCriterions(tranOutTail.getEmpId().toString(),
            tranOutTail.getTranOutHead().getTranOutOrg().getId().toString());
        tranOutTail.setEmp(emp);
        templist.add(tranOutTail);
      }

    }
    return templist;
  }

  /**
   * ת����Ϣ
   * 
   * @param orgId
   * @param empId
   * @param moneyDate
   * @return
   * @throws BusinessException
   */
  public BigDecimal getDistroyTranOutInterest(Integer orgId, Integer empId,
      String moneyDate) throws BusinessException {
    return this.getCurInterest(orgId, empId, moneyDate).add(
        this.getPreInterest(orgId, empId, moneyDate)).divide(new BigDecimal(1),
        2, BigDecimal.ROUND_HALF_DOWN);
  }

  /**
   * ��ñ�����Ϣ
   */
  public BigDecimal getCurInterest(Integer orgId, Integer empId,
      String moneyDate) throws BusinessException {
    Org org = orgDAO.queryById(orgId);
    String office = org.getOrgInfo().getOfficecode();
    BigDecimal curInterest = new BigDecimal(0.00);
    BigDecimal curSubA = null;
    BigDecimal curSubB = null;
    BigDecimal curSubC = null;
    BigDecimal curSubD = null;
    BigDecimal curSubE = null;
    BigDecimal curSubF = null;
    BigDecimal curSubG = null;
    BigDecimal curSubH = null;
    BigDecimal curSubI = null;
    BigDecimal curSubJ = null;
    BigDecimal curSubK = null;
    BigDecimal curSubL = null;

    // �������
    double curIntegral = pickTailDAO.getCurInt(orgId, empId, moneyDate);
    BigDecimal bCurIntegral = new BigDecimal(curIntegral);
    bCurIntegral = bCurIntegral.divide(new BigDecimal(1), 2,
        BigDecimal.ROUND_HALF_DOWN);
    // ������������
    double curRate = pickTailDAO.getCurRate(office);
    curSubA = pickTailDAO.getCurSubA(orgId.intValue(), empId.intValue());
    curSubB = pickTailDAO.getCurSubB(orgId.intValue(), empId.intValue());
    curSubC = pickTailDAO.getCurSubC(orgId.intValue(), empId.intValue());
    curSubD = pickTailDAO.getCurSubD(orgId.intValue(), empId.intValue());
    curSubE = pickTailDAO.getCurSubE(orgId.intValue(), empId.intValue());
    curSubF = pickTailDAO.getCurSubF(orgId.intValue(), empId.intValue());
    curSubG = pickTailDAO.getCurSubG(orgId.intValue(), empId.intValue());
    curSubH = pickTailDAO.getCurSubH(orgId.intValue(), empId.intValue());
    curSubI = pickTailDAO.getCurSubI(orgId.intValue(), empId.intValue());
    curSubJ = pickTailDAO.getCurSubJ(orgId.intValue(), empId.intValue());
    curSubK = pickTailDAO.getCurSubK(orgId.intValue(), empId.intValue());
    curSubL = pickTailDAO.getCurSubL(orgId.intValue(), empId.intValue());
    curInterest = bCurIntegral.multiply(new BigDecimal(curRate)).divide(
        new BigDecimal(365), 2, BigDecimal.ROUND_HALF_DOWN);
    curInterest = curInterest.add(curSubA).add(curSubB).add(curSubC).add(
        curSubD).add(curSubE).add(curSubF).add(curSubG).add(curSubH).add(
        curSubI).add(curSubJ).add(curSubK).add(curSubL);
    return curInterest;
  }

  /**
   * ����������Ϣ
   */
  public BigDecimal getPreInterest(Integer orgId, Integer empId,
      String moneyDate) throws BusinessException {
    Org org = orgDAO.queryById(orgId);
    String office = org.getOrgInfo().getOfficecode();
    // ������Ϣ
    BigDecimal preInterest = new BigDecimal(0.00);
    BigDecimal preSubA = null;
    BigDecimal preSubB = null;
    BigDecimal preSubC = null;
    BigDecimal preSubD = null;
    BigDecimal preSubE = null;
    BigDecimal preSubF = null;
    BigDecimal preSubG = null;
    BigDecimal preSubH = null;
    BigDecimal preSubI = null;
    BigDecimal preSubJ = null;
    BigDecimal preSubK = null;
    BigDecimal preSubL = null;
    // double preSub = pickTailDAO.getPreSub(orgId.intValue(),
    // empId.intValue());
    // �������
    double preIntegral = pickTailDAO.getPreInt(orgId, empId, moneyDate);
    BigDecimal bPreIntegral = new BigDecimal(preIntegral);
    bPreIntegral = bPreIntegral.divide(new BigDecimal(1), 2,
        BigDecimal.ROUND_HALF_DOWN);
    preSubA = pickTailDAO.getPreSubA(orgId.intValue(), empId.intValue());
    preSubB = pickTailDAO.getPreSubB(orgId.intValue(), empId.intValue());
    preSubC = pickTailDAO.getPreSubC(orgId.intValue(), empId.intValue());
    preSubD = pickTailDAO.getPreSubD(orgId.intValue(), empId.intValue());
    preSubE = pickTailDAO.getPreSubE(orgId.intValue(), empId.intValue());
    preSubF = pickTailDAO.getPreSubF(orgId.intValue(), empId.intValue());
    preSubG = pickTailDAO.getPreSubG(orgId.intValue(), empId.intValue());
    preSubH = pickTailDAO.getPreSubH(orgId.intValue(), empId.intValue());
    preSubI = pickTailDAO.getPreSubI(orgId.intValue(), empId.intValue());
    preSubJ = pickTailDAO.getPreSubJ(orgId.intValue(), empId.intValue());
    preSubK = pickTailDAO.getPreSubK(orgId.intValue(), empId.intValue());
    preSubL = pickTailDAO.getPreSubL(orgId.intValue(), empId.intValue());

    // ��������
    double preRate = pickTailDAO.getPreRate(office);
    preInterest = bPreIntegral.multiply(new BigDecimal(preRate)).divide(
        new BigDecimal(365), 2, BigDecimal.ROUND_HALF_DOWN);
    preInterest = preInterest.add(preSubA).add(preSubB).add(preSubC).add(
        preSubD).add(preSubE).add(preSubF).add(preSubG).add(preSubH).add(
        preSubI).add(preSubJ).add(preSubK).add(preSubL);
    return preInterest;
  }

  public TranAF findTranOutInfoMX(Pagination pagination) throws Exception {
    TranAF tranAF = new TranAF();
    BigDecimal sumBalance = new BigDecimal(0.00);
    BigDecimal sumInterest = new BigDecimal(0.00);
    BigDecimal sumMoney = new BigDecimal(0.00);
    String tranOutHeadid = (String) pagination.getQueryCriterions().get(
        "tranOutHeadid");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    TranOutHead tranOutHead = null;
    TranOutTail tranOutTail = null;
    List list = tranOutTailDAO.queryTranOutEmpListMX(tranOutHeadid, orderBy,
        order, start, pageSize, page);
    List list1 = new ArrayList();
    if (list != null) {
      tranOutTail = (TranOutTail) list.get(0);
      tranOutHead = tranOutTail.getTranOutHead();
      for (int i = 0; i < list.size(); i++) {
        tranOutTail = (TranOutTail) list.get(i);
        Emp emp = empDAO.queryByCriterions(tranOutTail.getEmpId().toString(),
            tranOutHead.getTranOutOrg().getId().toString());
        if (!tranOutTail.getTranin_empid().toString().equals("0")) {
          Emp emp1 = empDAO.queryByCriterions(tranOutTail.getTranin_empid()
              .toString(), tranOutHead.getTranInOrg().getId().toString());
          tranOutTail.setReserveaB(emp1.getEmpInfo().getName());
          tranOutTail.setReserveaC(emp1.getEmpInfo().getCardNum());
        }
        tranOutTail.setEmp(emp);
        list1.add(tranOutTail);
      }
    }
    count = tranOutTailDAO.queryCountTranOutEmpListMX(tranOutHeadid);
    pagination.setNrOfElements(count);
    sumBalance = tranOutHead.getSalary();
    sumInterest = tranOutHead.getInterest();
    sumMoney = tranOutHead.getSumSalary();
    tranAF.setOutOrgId(BusiTools.convertTenNumber(tranOutHead.getTranOutOrg()
        .getId().toString()));
    tranAF.setOutOrgname(tranOutHead.getTranOutOrg().getOrgInfo().getName());
    if (tranOutHead.getTranInOrg() != null) {
      tranAF.setInOrgId(BusiTools.convertTenNumber(tranOutHead.getTranInOrg()
          .getId().toString()));
      tranAF.setInOrgName(tranOutHead.getTranInOrg().getOrgInfo().getName());
    }
    tranAF.setList(list1);
    tranAF.setNoteNumber(tranOutHead.getNoteNum());
    // if(!tranOutHead.getTranStatus().equals("1")){
    //      
    // }
    tranAF.setHeadid(tranOutHead.getId().toString());
    tranAF.setDocNum(tranOutHead.getDocNum());
    tranAF.setSumBalance(sumBalance.toString());
    tranAF.setSumInterest(sumInterest.toString());
    tranAF.setSumMoney(sumMoney.toString());
    return tranAF;
  }

  public List findTranOutInfoMXPrint(String headid) throws Exception {
    List list = new ArrayList();
    list = tranOutTailDAO.queryTranOutHeadID(new Integer(headid));
    List templist = new ArrayList();
    if (list != null) {
      for (int i = 0; i < list.size(); i++) {
        TranOutTail tranOutTail = (TranOutTail) list.get(i);
        if (tranOutTail.getTranin_empid() == null) {
          tranOutTail.setTranin_empid(null);
        }
        Emp emp = empDAO.queryByCriterions(tranOutTail.getEmpId().toString(),
            tranOutTail.getTranOutHead().getTranOutOrg().getId().toString());
        tranOutTail.setEmp(emp);
        templist.add(tranOutTail);
      }
    }
    return list;
  }

  /**
   * ����
   * 
   * @param pkid
   * @param username
   * @param ip
   * @param setDates
   * @throws Exception
   */
  public void updateTranHeadTranIn(String pkid, String username, String ip,
      String setDates) throws Exception {
    // �жϸ�ת����λ����Ƿ����AA309.TRAN_STATUS=1�ļ�¼
    if (pkid != null && pkid.length() > 0) { // 309 pkid
      TranOutHead tranOutHead = tranOutHeadDAO.queryById(new Integer(pkid));
      String orgid = tranOutHead.getTranOutOrg().getId().toString();
      TranOutHead tranOutHead2 = tranOutHeadDAO.queryTranOutHeadByOrgid(orgid);
      if (tranOutHead2 != null) {
        throw new BusinessException("�˵�λ������δ��ɵ�ת����ᣬ���ܳ�����");
      }
      // //�����Ѿ�����ת��Ǽǵ�ҵ���ڽ��г���ʱ��Ӧ��ʾ���ñ�ת��ҵ���Ӧ��ת��ҵ���Ѱ����Ƿ�Ҫ������
      // List list =
      // tranOutHeadDAO.queryTranOutHeadsByTranOutHeadid(tranOutHead.getId().toString());
      // if(list != null && list.size()>0){
      // throw new BusinessException("yes");
      // }
      String docNum = tranOutHead.getDocNum();
      String officeCode = tranOutHead.getTranOutOrg().getOrgInfo()
          .getOfficecode();
      String docNumDocument = collParaDAO.getDocNumDesignPara();
      if (docNumDocument.equals("1")) {
        officeCode = tranOutHead.getTranOutOrg().getOrgInfo().getOfficecode();
      } else {
        officeCode = tranOutHead.getTranOutOrg().getOrgInfo()
            .getCollectionBankId();
      }
      String bizYearmonth = setDates.substring(0, 6);
      Integer HeadOrgOutId = (Integer) tranOutHead.getTranOutOrg().getId();
      // ȡ���ñ�ҵ���AA309.ID������AA309.TRAN_STATUS=1,AA309.SETT_DATE=�գ�ɾ��ƾ֤��
      tranOutHead.setTranStatus(new BigDecimal(1));
      tranOutHead.setSettDate("");
      tranOutHead.setDocNum("");// ״̬��Ʊ�ݱ����Ϊ��
      // ͨ��AA309.ID=AA101.BIZ_ID ��AA101.ID=AA102.ORG_FLOW_ID
      // �������Ѹñ�ת��ҵ���ڱ�AA101�ͱ�AA102�в���ļ�¼ɾ������
      OrgHAFAccountFlowTransOut orgHAFAccountFlowTransOut = orgHAFAccountFlowTransOutDAO
          .queryOrgHAFAccount(tranOutHead.getId().toString());
      if (orgHAFAccountFlowTransOut == null) {
        throw new BusinessException("�ñ�ҵ���ѱ������򲻴��ڼ�¼��");
      }
      // List empHAFAccountFlowlist = empHAFAccountFlowDAO
      // .queryEmpHAFAccountFlowListByOrgFlowId(orgHAFAccountFlowTransOut.getId().toString());
      // ɾ��102
      empHAFAccountFlowDAO.deleteEmpHAFAccountFlowAll(new Integer(
          orgHAFAccountFlowTransOut.getId().toString()));
      // ɾ��101
      orgHAFAccountFlowTransOutDAO.delete(orgHAFAccountFlowTransOut);
      // ����AA312
      DocNumCancel docNumCancel = new DocNumCancel();
      docNumCancel.setDocNum(docNum.substring(8));
      docNumCancel.setBizYearmonth(bizYearmonth);
      docNumCancel.setOfficeCode(officeCode);
      docNumCancelDAO.insert(docNumCancel);
      // ���������־ BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(new Integer(
          BusiLogConst.OP_MODE_TRANINOUT_TRANOUT_MAINTAIN).toString());
      hafOperateLog.setOpButton(new Integer(
          BusiLogConst.BIZLOG_ACTION_REVOCATION).toString());
      hafOperateLog.setOpBizId(new Integer(pkid));
      hafOperateLog.setOpIp(ip);
      hafOperateLog.setOrgId(HeadOrgOutId);
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOperator(username);
      hafOperateLogDAO.insert(hafOperateLog);
      // ɾ�� 319
      String headPkid = tranOutHead.getId().toString();
      TranOutBizActivityLog tranOutBizActivityLog = tranOutBizActivityLogDAO
          .queryTranOutBizActivityLogByBizid(headPkid);
      tranOutBizActivityLogDAO.delete(tranOutBizActivityLog);

    }

  }

  // ���ݵ�λid��306�������޴˵�λ
  public boolean check(String orgID) {
    boolean b = false;
    List a = tranOutHeadDAO.queryByOrgId(orgID);
    if (a.size() > 0) {
      return true;
    }
    return b;
  }

  public boolean findAdjustWrongFAccountByOrgid(String orgid,
      SecurityInfo securityInfo) {
    boolean b = false;
    List a = tranOutHeadDAO.queryByIDGJP(orgid, securityInfo);
    if (a.size() > 0) {
      return true;
    }
    return b;
  }

  public String findtraninEmpInfo(String name, String cardNum, String empid,
      String orgid) throws BusinessException, Exception {
    try {
      Emp emp = empDAO.queryByCriterions(empid, orgid);
      if (emp == null) {
        return "a";
      } else {
        if (name.equals(emp.getEmpInfo().getName() + "")
            && (CardMunChange.get18Id(cardNum).equals(CardMunChange.get18Id(emp.getEmpInfo().getCardNum() + "")))) {
          return "b";
        } else {
          return "c";
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;

  }

  public void referringDate(String pkid, String ip, String userName,
      String dates, SecurityInfo securityInfo, String temp_P)
      throws BusinessException {
    // TODO Auto-generated method stub
    try {
      TranOutHead tranOutHead = tranOutHeadDAO.queryById(new Integer(pkid));
      boolean flag = autoInfoPickDAODW.isNOPickIn(tranOutHead.getTranOutOrg()
          .getId().toString(), tranOutHead.getId().toString(),
          BusiConst.ORGBUSINESSTYPE_E);
      if (flag) {
        throw new BusinessException("�ñ�ҵ�����ύ");
      } else {

        AutoInfoPick autoInfoPick = new AutoInfoPick();
        autoInfoPick.setOrgId(new Integer(tranOutHead.getTranOutOrg().getId()
            .toString()));
        autoInfoPick.setOrgHeadId(new Integer(tranOutHead.getId().toString()));
        autoInfoPick.setType(BusiConst.ORGBUSINESSTYPE_E);
        autoInfoPick.setStatus(BusiConst.OC_NOT_PICK_UP);
        autoInfoPick.setOrgBizDate(new Date());
        Integer temp_a = null;
        Integer temp_b = null;
        autoInfoPick.setCenterHeadId(temp_a);
        autoInfoPick.setPayHeadId(temp_b);
        autoInfoPickDAODW.insert(autoInfoPick);
        // ����BA003��
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(
            BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        if (temp_P.equals("1")) {
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO));// ����
        } else {
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_DRAWING_DRAWING_MAINTAIN));// ά��
        }
        hafOperateLog.setOpButton(Integer
            .toString(BusiLogConst.BIZLOG_ACTION_REFERRINGDATE));
        hafOperateLog.setOpBizId(new Integer(tranOutHead.getId().toString()));// AA306.ID
        hafOperateLog.setOperator(userName);
        hafOperateLog.setOpIp(ip);
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOrgId(new Integer(tranOutHead.getTranOutOrg().getId()
            .toString()));
        hafOperateLogDAO.insert(hafOperateLog);
      }
    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }
  }

  public void pprovalDate(String pkid, String ip, String userName,
      String dates, SecurityInfo securityInfo, String temp_P)
      throws BusinessException {

    TranOutHead tranOutHead = null;
    tranOutHead = tranOutHeadDAO.queryById(new Integer(pkid));

    // boolean flag = autoInfoPickDAODW.isNOPickIn(tranOutHead.getTranOutOrg()
    // .getId().toString(), tranOutHead.getId().toString(),
    // BusiConst.ORGBUSINESSTYPE_E);
    try {
      // if (!flag) {
      String status = autoInfoPickDAODW.findStatus(tranOutHead.getTranOutOrg()
          .getId().toString(), tranOutHead.getId().toString(),
          BusiConst.ORGBUSINESSTYPE_E);
      if (status.equals(BusiConst.OC_PICK_UP)) {
        throw new BusinessException("��ҵ���ѱ�������ȡ�������Գ�����");
      }
      if (status.equals(BusiConst.OC_PICK_UP_OVER)) {
        throw new BusinessException("�ñ�ҵ��û���ύ�������Գ�����");
      }
      // } else {
      autoInfoPickDAODW.deleteAutoInfoPick(tranOutHead.getTranOutOrg().getId()
          .toString(), tranOutHead.getId().toString(),
          BusiConst.ORGBUSINESSTYPE_E);
      // ����BA003��
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      if (temp_P.equals("1")) {
        hafOperateLog.setOpModel(Integer
            .toString(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO));// ����
      } else {
        hafOperateLog.setOpModel(Integer
            .toString(BusiLogConst.OP_MODE_DRAWING_DRAWING_MAINTAIN));// ά��
      }
      hafOperateLog.setOpButton(Integer
          .toString(BusiLogConst.BIZLOG_ACTION_PPROVALDATA));
      hafOperateLog.setOpBizId(new Integer(tranOutHead.getId().toString()));// AA306.ID
      hafOperateLog.setOperator(userName);
      hafOperateLog.setOpIp(ip);
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOrgId(new Integer(tranOutHead.getTranOutOrg().getId()
          .toString()));
      hafOperateLogDAO.insert(hafOperateLog);
      // }

    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }
  }

  public void pickUpData(String outOrgId, SecurityInfo securityInfo)
      throws BusinessException {
    try {
      String minid = autoInfoPickDAO.findOrgHeadid(outOrgId,
          BusiConst.ORGBUSINESSTYPE_E, BusiConst.OC_NOT_PICK_UP);

      if (minid == null || "".equals(minid)) {
        throw new BusinessException("�õ�λ������δ��ȡ�ļ�¼");
      } else {
        TranOutHead tranOutHead = tranOutHeadDAODW
            .queryById(new Integer(minid));// ���ݵõ���ͷ�������Ե�������orgID
        List list = tranOutTailDAODW.findailList(minid);// �ڵ�λ�����ݿ��У�����ȡ����ORG_HEAD_ID��ѯAA306��AA307��ȡ���ļ�¼
        List headlist = new ArrayList();
        TranoutHeadImportDTO dtoo1 = new TranoutHeadImportDTO();
        TranoutHeadImportDTO dtoo2 = new TranoutHeadImportDTO();
        dtoo2.setOrgOutid(tranOutHead.getTranOutOrg().getId().toString());
        dtoo2.setOrgOutName(tranOutHead.getTranOutOrg().getOrgInfo().getName());
        dtoo2.setOrgheadid(minid);
        String traninorgid = "";
        String traninorgname = "";
        String notenum = "";
        if (tranOutHead.getTranInOrg() == null) {
          dtoo2.setOrgInid(traninorgid);
          dtoo2.setOrgInName(traninorgname);
        } else {
          traninorgid = tranOutHead.getTranInOrg().getId().toString();
          traninorgname = tranOutHead.getTranInOrg().getOrgInfo().getName();
          dtoo2.setOrgInid(traninorgid);
          dtoo2.setOrgInName(traninorgname);
        }
        if (tranOutHead.getNoteNum() == null) {
          dtoo2.setNoteNum(notenum);
        } else {
          notenum = tranOutHead.getNoteNum();
          dtoo2.setNoteNum(notenum);
        }
        headlist.add(dtoo1);
        headlist.add(dtoo2);
        String userName = securityInfo.getUserName();
        String ip = securityInfo.getUserIp();
        String dates = securityInfo.getUserInfo().getBizDate();

        try {
          this.addTranoutListBatch(headlist, list, tranOutHead.getTranOutOrg()
              .getId().toString(), tranOutHead.getTranOutOrg().getOrgInfo()
              .getName(), traninorgid, traninorgname, notenum, ip, userName,
              dates, securityInfo);
        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        String bizdate = "1";
        autoInfoPickDAO.updateAutoInfoPick(BusiConst.OC_PICK_UP, temp_headid,
            bizdate, tranOutHead.getTranOutOrg().getId().toString(),
            tranOutHead.getId().toString(), BusiConst.ORGBUSINESSTYPE_E);
        // ����BA003��
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(
            BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        hafOperateLog.setOpModel(Integer
            .toString(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO));// ����
        hafOperateLog.setOpButton(Integer
            .toString(BusiLogConst.BIZLOG_ACTION_PICKUPDATA));
        hafOperateLog.setOpBizId(new Integer(temp_headid));
        hafOperateLog.setOperator(userName);
        hafOperateLog.setOpIp(ip);
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOrgId(new Integer(tranOutHead.getTranOutOrg().getId()
            .toString()));
        hafOperateLogDAO.insert(hafOperateLog);
      }
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String queryCollBankName(String orgId, String bizId, String bizType,
      SecurityInfo securityInfo) throws Exception {
    String bankName = "";
    OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO
        .queryByBizId_wsh(bizId, bizType);
    if (orgHAFAccountFlow != null) {
      if (orgHAFAccountFlow.getMoneyBank() != null) {
        CollBank collBank = collBankDAO
            .getCollBankByCollBankid(orgHAFAccountFlow.getMoneyBank());
        bankName = collBank.getCollBankName();
      }
    } else {
      Org org = null;
      String orgid = "";
      if (orgId != null) {
        orgid = orgId;
      }
      org = orgDAO.queryByCriterions(orgid, "2", null, securityInfo);
      if (org == null && orgid != null) {
        org = new Org();
      }
      if (org.getOrgInfo().getCollectionBankId() != null) {
        CollBank collBank = collBankDAO.getCollBankByCollBankid(org
            .getOrgInfo().getCollectionBankId());
        bankName = collBank.getCollBankName();
      }
    }
    return bankName;
  }

  public String[] queryOfficeBankNames(String orgId, String openStatus,
      String bizId, String bizType, SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    // ��ѯ���´����п�ʼ
    String officeName = "";
    String bankName = "";
    String str[] = new String[2];
    OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO
        .queryByBizId_wsh(bizId, bizType);
    if (orgHAFAccountFlow != null) {
      if (orgHAFAccountFlow.getOfficeCode() != null) {
        try {
          OrganizationUnit organizationUnit = new OrganizationUnit();
          organizationUnit = organizationUnitDAO.queryById(orgHAFAccountFlow
              .getOfficeCode());
          if (organizationUnit != null) {
            if (organizationUnit.getName() != null) {
              officeName = organizationUnit.getName();
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }

      }
      if (orgHAFAccountFlow.getMoneyBank() != null) {
        CollBank collBank = collBankDAO
            .getCollBankByCollBankid(orgHAFAccountFlow.getMoneyBank());
        bankName = collBank.getCollBankName();
      }
    } else {
      Org org = null;
      String orgid = "";
      if (orgId != null) {
        orgid = orgId;
      }
      org = orgDAO.queryByCriterions(orgid, "2", null, securityInfo);
      if (org == null && orgid != null) {
        org = new Org();
      }
      if (org.getOrgInfo().getOfficecode() != null) {
        try {
          OrganizationUnit organizationUnit = new OrganizationUnit();
          organizationUnit = organizationUnitDAO.queryById(org.getOrgInfo()
              .getOfficecode());
          if (organizationUnit != null) {
            if (organizationUnit.getName() != null) {
              officeName = organizationUnit.getName();
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      if (org.getOrgInfo().getCollectionBankId() != null) {
        CollBank collBank = collBankDAO.getCollBankByCollBankid(org
            .getOrgInfo().getCollectionBankId());
        bankName = collBank.getCollBankName();
      }
    }
    // ��ѯ���´����н���
    str[0] = officeName;
    str[1] = bankName;
    return str;
  }

  // ����ת��--���ת��-- ƾ֤�Ų�ѯOfficeCode
  public String GetOfficeCode_yg(String pkid) throws Exception {
    List list = tranOutTailDAO.FindOfficeCode(pkid);
    String collectionBankId = "";
    try {
      if (list.size() > 0) {
        TranOutTail tot = (TranOutTail) list.get(0);
        collectionBankId = tot.getTranOutHead().getTranOutOrg().getOrgInfo()
            .getCollectionBankId();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return collectionBankId;
  }

  // ת��ά��--���ת��-- ƾ֤�Ų�ѯOfficeCode
  public String GetOfficeCodeTb_yg(String Headpkid) throws Exception {
    String collectionBankId = null;
    if (Headpkid != null && Headpkid.length() > 0) {
      List headList = tranOutHeadDAO.FindOfficeCodeTb(Headpkid);
      if (headList.size() > 0) {
        TranOutHead tranOutHead = (TranOutHead) headList.get(0);
        collectionBankId = tranOutHead.getTranOutOrg().getOrgInfo()
            .getCollectionBankId();
      }
    }
    return collectionBankId;
  }

  public String GetBankName_yg(String bankid) throws Exception {
    String collectionBankName = tranOutHeadDAO.FindBankName_yg(bankid);
    return collectionBankName;
  }

  public TranTbPrintAF printCredence(String headid) throws BusinessException,
      Exception {
    TranTbPrintAF printAf = new TranTbPrintAF();
    TranOutHead tranOutHead = null;
    List list = new ArrayList();
    try {
      tranOutHead = tranOutHeadDAO.queryById(Integer.valueOf(headid));
      printAf.setStartDate(tranOutHead.getSettDate());
      printAf.setNote_num(tranOutHead.getNoteNum());
      printAf.setDoc_num(tranOutHead.getDocNum());
      if (tranOutHead != null) {
        TranInOrg inOrg = tranOutHead.getTranInOrg();
        if (inOrg != null) {
          printAf.setInOrgId(inOrg.getId().toString());
          String inOrgCollBank = tranOutHeadDAO.FindBankName_yg(tranOutHead
              .getTranInOrg().getOrgInfo().getCollectionBankId());
          printAf.setInOrgCollBank(inOrgCollBank);
          printAf.setInOrgName(inOrg.getOrgInfo().getName());
          if (inOrg.getOrgInfo().getPayBank() != null) {
            printAf.setInOrgOpenBank(inOrg.getOrgInfo().getPayBank().getName());
            printAf.setInPayBankAccNum(inOrg.getOrgInfo().getPayBank()
                .getAccountNum());
          } else {
            printAf.setInOrgOpenBank("");
            printAf.setInPayBankAccNum("");
          }
        }
        String outOrgId = tranOutHead.getTranOutOrg().getId().toString();
        if (outOrgId != null && !"".equals(outOrgId)) {
          String outOrgCollBank = tranOutHeadDAO.FindBankName_yg(tranOutHead
              .getTranOutOrg().getOrgInfo().getCollectionBankId());
          printAf.setOutOrgCollBank(outOrgCollBank);
          TranOutOrg tranOutOrg = tranOutOrgDAO
              .queryById(new Integer(outOrgId));
          if (tranOutOrg.getOrgInfo().getPayBank() != null) {
            printAf.setOutOrgOpenBank(tranOutOrg.getOrgInfo().getPayBank()
                .getName());
            printAf.setOutPayBankAccNum(tranOutOrg.getOrgInfo().getPayBank()
                .getAccountNum());
          } else {
            printAf.setOutOrgOpenBank("");
            printAf.setOutPayBankAccNum("");
          }
          printAf.setOutOrgId(outOrgId);
          printAf.setOutOrgname(tranOutOrg.getOrgInfo().getName());
        }
        list = tranOutTailDAO.fIndTailEmpInfoWZQ(headid);
        for (int i = 0; i < list.size(); i++) {
          TranOutTail tranOutTail = (TranOutTail) list.get(i);
          Emp emp = empDAO.queryByCriterions(tranOutTail.getEmpId().toString(),
              tranOutTail.getTranOutHead().getTranOutOrg().getId().toString());
          if (tranOutTail.getTranin_empid() == null) {
            tranOutTail.setTranin_empid(new Integer(0));
          }
          if (tranOutTail.getTranReason() == null) {
            tranOutTail.setTranReason("");
          }
          tranOutTail.setEmp(emp);
        }
        printAf.setList(list);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return printAf;
  }

  /**
   * @author wangshuang ���������ӡ����Ϣ
   */
  public List printAll(Pagination pagination, SecurityInfo securityInfo)
      throws BusinessException, Exception {
    List list = new ArrayList();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    String orderBy = (String) pagination.getOrderBy();
    String order = pagination.getOrderother();
    String orgOutid = (String) pagination.getQueryCriterions().get("orgOutid");
    String orgOutName = (String) pagination.getQueryCriterions().get(
        "orgOutName");
    String orgInid = (String) pagination.getQueryCriterions().get("orgInid");
    String orgInName = (String) pagination.getQueryCriterions()
        .get("orgInName");
    String noteNum = (String) pagination.getQueryCriterions().get("Note_num");
    String docNum = (String) pagination.getQueryCriterions().get("Doc_num");
    String Dates = (String) pagination.getQueryCriterions().get("Dates");
    String status = (String) pagination.getQueryCriterions().get("status");
    String tranType = (String) pagination.getQueryCriterions().get("tranType");
    int page = pagination.getPage();
    BigDecimal salaryBase = new BigDecimal(0.00);
    BigDecimal curIntegral = new BigDecimal(0.00);
    BigDecimal sumBalance = new BigDecimal(0.00);
    try {
      List totalList = tranOutHeadDAO.queryTranOutTotalWZQ(orgOutid,
          orgOutName, orgInid, orgInName, noteNum, docNum, Dates, status,
          tranType, securityInfo);
      for (int i = 0; i < totalList.size(); i++) {
        TranOutHead head = (TranOutHead) totalList.get(i);
        TranTbPrintAF af = this.printCredence(head.getId().toString());
        String str_in = this.queryCollBankName(af.getInOrgId(), head.getId()
            .toString(), "E", securityInfo);
        af.setInOrgCollBank(str_in);
        String str_out = this.queryCollBankName(af.getOutOrgId(), head.getId()
            .toString(), "E", securityInfo);
        af.setOutOrgCollBank(str_out);
        list.add(af);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public CollParaDAO getCollParaDAO() {
    return collParaDAO;
  }

  public void setCollParaDAO(CollParaDAO collParaDAO) {
    this.collParaDAO = collParaDAO;
  }

  public String FindAA103_DayTime(String collbankid) throws Exception {
    String date = tranOutHeadDAO.findAA103_DayTime(collbankid);
    return date;
  }

  public String queryNoteNum() throws Exception {
    String notenum = orgDAO.queryNoteNum();
    return notenum;
  }
  // ��ѯת����Ӧת���Ѿ����ʵ��б�
  public List queryTransInIsFiveList(SecurityInfo securityInfo) throws Exception, BufferUnderflowException {
    // TODO Auto-generated method stub
    List list = new ArrayList();
   
    try {
      list = tranOutTailDAO.queryTransInIsFiveList( securityInfo);
     
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

}
