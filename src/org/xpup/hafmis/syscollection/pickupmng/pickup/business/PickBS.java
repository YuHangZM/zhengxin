package org.xpup.hafmis.syscollection.pickupmng.pickup.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.AdjustWrongAccountHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.AutoInfoPickDAO;
import org.xpup.hafmis.syscollection.common.dao.BizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.CollParaDAO;
import org.xpup.hafmis.syscollection.common.dao.DocNumCancelDAO;
import org.xpup.hafmis.syscollection.common.dao.DocNumMaintainDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDrawingDAO;
import org.xpup.hafmis.syscollection.common.dao.PartPickupConditionDAO;
import org.xpup.hafmis.syscollection.common.dao.PickBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.PickHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.PickTailDAO;
import org.xpup.hafmis.syscollection.common.dao.SpecialPickDAO;
import org.xpup.hafmis.syscollection.common.daoDW.AutoInfoPickDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.EmpDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.PickTailDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.PickupHeadDaoDW;
import org.xpup.hafmis.syscollection.common.domain.entity.AutoInfoPick;
import org.xpup.hafmis.syscollection.common.domain.entity.BizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowDrawing;
import org.xpup.hafmis.syscollection.common.domain.entity.PickBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.PickHead;
import org.xpup.hafmis.syscollection.common.domain.entity.PickTail;
import org.xpup.hafmis.syscollection.common.domain.entity.SpecialPick;
import org.xpup.hafmis.syscollection.pickupmng.partpickupcondition.dto.PartPickupConditionDTO;
import org.xpup.hafmis.syscollection.pickupmng.pickup.bsinterface.IPickupBS;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.EmpInfoPick;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.OrgSearchConditionDTO;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.PickHeadImportDTO;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.PickInterestReteDTO;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.PickTailImportDTO;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.NameAF;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.PickGetEmployeeInfoAF;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.PickupAddAF;
import org.xpup.hafmis.syscollection.pickupmng.pickup.pickrule.DrawRuleDAO;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.dao.OrgInfoDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;
import org.xpup.hafmis.syscommon.domain.entity.OrgInfo;

/**
 * @author ���ĺ� ��ͨ��ȡ��ҵ����
 */
public class PickBS implements IPickupBS {
  private SpecialPickDAO specialPickDao;

  String temp_headid = "";

  private PickHeadDAO headDao;

  private HafOperateLogDAO hafOperateLogDAO;

  private DocNumMaintainDAO maintainDao;

  private CollParaDAO collParaDAO;

  private AutoInfoPickDAODW autoInfoPickDAODW;

  private DocNumCancelDAO insertDao;

  private EmpDAODW empDAODW;

  private EmpHAFAccountFlowDAO empFlowDao;

  private PickupHeadDaoDW pickupheadDaoDW;

  private PickTailDAODW tailDaoDW;

  private OrgHAFAccountFlowDrawingDAO orgFlowDao;

  private BizActivityLogDAO bizActivityLogDAO;

  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO = null;

  private DrawRuleDAO drawRuleDAO;

  public void setDrawRuleDAO(DrawRuleDAO drawRuleDAO) {
    this.drawRuleDAO = drawRuleDAO;
  }

  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }

  public void setPickupheadDaoDW(PickupHeadDaoDW pickupheadDaoDW) {
    this.pickupheadDaoDW = pickupheadDaoDW;
  }

  public void setTailDaoDW(PickTailDAODW tailDaoDW) {
    this.tailDaoDW = tailDaoDW;
  }

  private PickTailDAO tailDao;

  private HafOperateLogDAO hafDao;

  private OrgDAO orgDao;

  private PickBizActivityLogDAO pickBizDao;

  private EmpDAO empDao;

  private CollBankDAO collBankDAO;

  private AutoInfoPickDAO autoInfoPickDAO;

  private OrganizationUnitDAO organizationUnitDAO;

  private OrgInfoDAO orgInfoDao;

  private AdjustWrongAccountHeadDAO adjustWrongAccountHeadDAO;

  private PartPickupConditionDAO partPickupConditionDAO;

  public AdjustWrongAccountHeadDAO getAdjustWrongAccountHeadDAO() {
    return adjustWrongAccountHeadDAO;
  }

  public void setAutoInfoPickDAODW(AutoInfoPickDAODW autoInfoPickDAODW) {
    this.autoInfoPickDAODW = autoInfoPickDAODW;
  }

  public void setAdjustWrongAccountHeadDAO(
      AdjustWrongAccountHeadDAO adjustWrongAccountHeadDAO) {
    this.adjustWrongAccountHeadDAO = adjustWrongAccountHeadDAO;
  }

  public void setAutoInfoPickDAO(AutoInfoPickDAO autoInfoPickDAO) {
    this.autoInfoPickDAO = autoInfoPickDAO;
  }

  public void setHeadDao(PickHeadDAO headDao) {
    this.headDao = headDao;
  }

  public void setOrgInfoDao(OrgInfoDAO orgInfoDao) {
    this.orgInfoDao = orgInfoDao;
  }

  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }

  public void setTailDao(PickTailDAO tailDao) {
    this.tailDao = tailDao;
  }

  public SpecialPick findSpecialPickByOrgId(int id) throws BusinessException {
    return specialPickDao.findSpecialPickByOrgId(id);
  }

  public void setOrgDao(OrgDAO orgDao) {
    this.orgDao = orgDao;
  }

  public Org orgFindById(int id, SecurityInfo info) throws BusinessException {// ����id�ҳ�����λ--��Ȩ��
    return orgDao.findById(new Integer(id), info);
  }

  public Org getOrgByHeadId(Integer headId) throws BusinessException {
    return headDao.getOrgByPickHeadId(headId);
  }

  public OrgInfo orgInfoFindById(int id) throws BusinessException {
    return (OrgInfo) orgInfoDao.queryById(new Integer(id));
  }

  public OrgInfo findOrgInfoStatus(Serializable id) throws BusinessException {
    return (OrgInfo) orgInfoDao.findOrgInfoStatus(id);
  }

  public OrgInfo findOrgInfoStatus1(Serializable id) throws BusinessException {
    return (OrgInfo) orgInfoDao.findOrgInfoStatus1(id);
  }

  public PickHead headFindById(Serializable id) throws BusinessException {
    return (PickHead) headFindById(id);
  }

  public PickHead findPickHeadOrgIdStatusIsOne(int id) throws BusinessException {// �ҵ�AA306״̬Ϊ1�Ľ����..״̬Ϊ1��ֻ����һ����¼
    return headDao.findByOrgId(id);
  }

  public List findTailByHeadId(PickHead head) throws BusinessException {
    return tailDao.findTailByHeadId(head);
  }

  public PickHead queryById(Integer headId) throws BusinessException {
    return headDao.queryById(headId);
  }

  public void updatePickHeadReserveaA(Integer headId, String status,
      String registrations) throws BusinessException {
    PickHead pickHead = headDao.queryById(headId);
    if(status.equals("0")){
      String srea = "";
      try {
        srea = getAA306_1();
      } catch (Exception e) {
        e.printStackTrace();
      }
      List list = tailDao.findPickTailByPickHeadId(Integer.parseInt(pickHead.getId().toString()));
      PickTail tail = (PickTail)list.get(0);
      String s[]=srea.split(",");
      String y = "a";
      for(int i=0;i<s.length;i++){
        if(tail.getPickReason().toString().equals(s[i])){
          y="b";
        }
      }
      if(y.equals("a")){
        pickHead.setReserveaB("0");
      }else{
        pickHead.setReserveaB("1");
      }
    }
    pickHead.setReserveaA(status);
    String docNum = pickHead.getDocNum();
    String notNum = pickHead.getNoteNum();
    OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO.updateByDocNum(
        docNum, notNum);
    orgHAFAccountFlow.setRegistrations(registrations);
  }

  public boolean queryUnitAcc(String org_id) throws BusinessException {
    String str = headDao.queryUnitAcc(org_id);
    if (str.equals("0")) {
      return true;
    } else {
      return false;
    }
  }

  // ��ѯ����
  public String findCollBank(String collBankid) {
    String bankname = "";
    CollBank collBank = collBankDAO.getCollBankByCollBankid(collBankid);
    bankname = collBank.getCollBankName();
    return bankname;
  }

  /**
   * 
   */
  public boolean getOrgPayMonthEmpPayMonth(String orgid, String empid) {
    String ss = "";
    try {
      ss = empDao.getOrgPayMonthEmpPayMonth(orgid, empid);
    } catch (Exception s) {
      s.printStackTrace();
    }
    return ss.equals("1");
  }

  public String getAA307PhotoURL(String pickheadid) {
    String ss = "";
    try {
      ss = empDao.getAA307PhotoURL(pickheadid);
    } catch (Exception s) {
      s.printStackTrace();
    }
    return ss;
  }

  /**
   * ���ݲ�ͬ����������õ�λ��ȡ�ĵ���Ϣ..
   */
  public List getVindicatePageData(Pagination pa, SecurityInfo info) {
    try {
      String orgId = (String) pa.getQueryCriterions().get("orgId");
      String orgName = (String) pa.getQueryCriterions().get("orgName");
      String noteNumber = (String) pa.getQueryCriterions().get("noteNumber");
      String docNumber = (String) pa.getQueryCriterions().get("docNumber");
      String start = (String) pa.getQueryCriterions().get("start");
      String end = (String) pa.getQueryCriterions().get("end");
      String pickDate = (String) pa.getQueryCriterions().get("pickDate");
      String pickDate_end = (String) pa.getQueryCriterions()
          .get("pickDate_end");
      String state = (String) pa.getQueryCriterions().get("state");
      String pickType = (String) pa.getQueryCriterions().get("pickType");
      String select = (String) pa.getQueryCriterions().get("select");
      String reason = (String) pa.getQueryCriterions().get("reason");
      String collectionBank = (String) pa.getQueryCriterions().get(
          "collectionBank");
      String orderBy = (String) pa.getOrderBy();
      String order = (String) pa.getOrderother();
      OrgSearchConditionDTO search = new OrgSearchConditionDTO();
      if (orgId != null)
        search.setOrgId(orgId.trim());
      if (orgName != null)
        search.setOrgName(orgName.trim());
      if (noteNumber != null)
        search.setNoteNumber(noteNumber.trim());
      if (docNumber != null)
        search.setDocNumber(docNumber.trim());
      if (start != null)
        search.setStart(start.trim());
      if (end != null)
        search.setEnd(end.trim());
      if (pickDate != null)
        search.setPickDate(pickDate.trim());
      if (pickDate_end != null)
        search.setPickDate_end(pickDate_end.trim());
      if (state != null)
        search.setState(state.trim());
      if (pickDate != null)
        search.setPickDate(pickDate.trim());
      if (select != null)
        search.setSelect(select.trim());
      if (reason != null) {
        search.setReason(reason);
      }
      if (pickType != null) {
        search.setPickType(pickType);
      }
      if (collectionBank != null) {
        search.setCollectionBank(collectionBank);
      }
      int firstStart = pa.getFirstElementOnPage() - 1;
      int pageSize = pa.getPageSize();
      List list = headDao.getVindicatePageData(search, orderBy, order,
          firstStart, pageSize, info);// �����ݼ�Ȩ��..
      int count = headDao.getVindicateCount(search, info);
      pa.setNrOfElements(count);
      if (!list.isEmpty()) {
        for (int i = 0; i < list.size(); i++) {
          PickHead h = (PickHead) list.get(i);
          h.setBusinessState(BusiTools.getBusiValue(h.getPickSatatus()
              .intValue(), BusiConst.BUSINESSSTATE));
          if (h.getReserveaB()!=null && h.getReserveaB().equals("0")) {
            h.setReserveaC("����ͨ��");
          } else {
            h.setReserveaC("����δͨ��");
          }
          h.setReserveaB(headDao.getOperator_yg(h.getId().toString()));
          if (h.getReserveaA().equals("0")) {
            h.setReserveaA("���ͨ��");
          } else if (h.getReserveaA().equals("1")) {
            h.setReserveaA("���δͨ��");
          }
        }
      }
      return list;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  /**
   * ���ά����ȡ��ȫ�����ݡ��� �Ժ����������������������Ļ���
   */
  public List getVindicateAllDate(Pagination pa, SecurityInfo info)
      throws BusinessException {
    String orgId = (String) pa.getQueryCriterions().get("orgId");
    String orgName = (String) pa.getQueryCriterions().get("orgName");
    String noteNumber = (String) pa.getQueryCriterions().get("noteNumber");
    String docNumber = (String) pa.getQueryCriterions().get("docNumber");
    String start = (String) pa.getQueryCriterions().get("start");
    String end = (String) pa.getQueryCriterions().get("end");
    String pickDate = (String) pa.getQueryCriterions().get("pickDate");
    String pickDate_end = (String) pa.getQueryCriterions().get("pickDate_end");
    String state = (String) pa.getQueryCriterions().get("state");
    String pickType = (String) pa.getQueryCriterions().get("pickType");
    String select = (String) pa.getQueryCriterions().get("select");
    String reason = (String) pa.getQueryCriterions().get("reason");
    String collectionBank = (String) pa.getQueryCriterions().get(
        "collectionBank");
    String orderBy = (String) pa.getOrderBy();
    String order = (String) pa.getOrderother();
    OrgSearchConditionDTO search = new OrgSearchConditionDTO();
    if (orgId != null)
      search.setOrgId(orgId.trim());
    if (orgName != null)
      search.setOrgName(orgName.trim());
    if (noteNumber != null)
      search.setNoteNumber(noteNumber.trim());
    if (docNumber != null)
      search.setDocNumber(docNumber.trim());
    if (start != null)
      search.setStart(start.trim());
    if (end != null)
      search.setEnd(end.trim());
    if (pickDate != null)
      search.setPickDate(pickDate.trim());
    if (pickDate_end != null)
      search.setPickDate_end(pickDate_end.trim());
    if (state != null)
      search.setState(state.trim());
    if (pickDate != null)
      search.setPickDate(pickDate.trim());
    if (select != null)
      search.setSelect(select.trim());
    if (reason != null) {
      search.setReason(reason);
    }
    if (pickType != null) {
      search.setPickType(pickType);
    }
    if (collectionBank != null) {
      search.setCollectionBank(collectionBank);
    }
    return headDao.getVindicateAllDate(search, info);
  }

  public List getEmployeePickCountMoney(Pagination p) throws BusinessException {
    String empId = (String) p.getQueryCriterions().get("empId");
    String time = (String) p.getQueryCriterions().get("time");
    String reason = (String) p.getQueryCriterions().get("reason");
    List list = tailDao.getEmployeePickCountMoney(empId, time, reason);
    return list;
  }

  /**
   * ���ݵ㳬���ӿ��Ի�ô˵�λ�µ�����Ա������ҵ��
   * 
   * @return
   */
  public List findTheOrgAllEmployee(Pagination p) throws BusinessException {
    String id = (String) p.getQueryCriterions().get("idValue");// ����û��ύ��id
    String orderBy = (String) p.getOrderBy();
    String order = (String) p.getOrderother();
    int start = p.getFirstElementOnPage() - 1;
    int pageSize = p.getPageSize();
    List list = tailDao.findTheOrgAllEmployee_LY(id, orderBy, order, start,
        pageSize);
    try {
      for (int i = 0; i < list.size(); i++) {// ת����ȡ���͵�һ��ССѭ��
        PickTail pick = (PickTail) list.get(i);
        pick.setPickDisplayType(BusiTools.getBusiValue(pick.getPickType()
            .intValue(), BusiConst.PICKUPTYPE));
      }
      for (int i = 0; i < list.size(); i++) {// z
        PickTail tail = (PickTail) list.get(i);
        int reason = tail.getPickReason().intValue();
        if (reason <= 6) // ������ȡ
          tail.setReason(BusiTools.getBusiValue(reason, BusiConst.SOMEPICK));
        else
          // ������ȡ
          tail.setReason(BusiTools.getBusiValue(reason, BusiConst.DISTROYPICK));
        tail.setTotal(tail.getPickSalary().add(tail.getPickInterest()));
      }
    } catch (Exception s) {
      s.printStackTrace();
    }
    Object[] obj = tailDao.getTheOrgAllEmployee_LY(Integer.parseInt(id));
    int count = ((Integer) obj[0]).intValue();
    p.setNrOfElements(count);
    return list;
  }

  public List getPrintAllEmployeeList(String id) throws BusinessException {
    Object[] obj = tailDao.getTheOrgAllEmployee_LY(Integer.parseInt(id));
    List list = (List) obj[1];
    try {
      // for (int i = 0; i < list.size(); i++) {// ת����ȡ���͵�һ��ССѭ��
      // PickTail pick = (PickTail) list.get(i);
      // pick.setPickDisplayType(BusiTools.getBusiValue(pick.getPickType()
      // .intValue(), BusiConst.PICKUPTYPE));
      // }
      // for (int i = 0; i < list.size(); i++) {// z
      // PickTail tail = (PickTail) list.get(i);
      // int reason = tail.getPickReason().intValue();
      // if (reason <= 3) // ������ȡ
      // tail.setReason(BusiTools.getBusiValue(reason, BusiConst.SOMEPICK));
      // else
      // // ������ȡ
      // tail.setReason(BusiTools.getBusiValue(reason, BusiConst.DISTROYPICK));
      // }
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  public List getPrintAllEmployeeList_yg(String id) throws BusinessException {
    List list = tailDao.getTheOrgAllEmployee_LY_YG(Integer.parseInt(id));

    try {
      // for (int i = 0; i < list.size(); i++) {// ת����ȡ���͵�һ��ССѭ��
      // PickTail pick = (PickTail) list.get(i);
      // pick.setPickDisplayType(BusiTools.getBusiValue(pick.getPickType()
      // .intValue(), BusiConst.PICKUPTYPE));
      // }
      // for (int i = 0; i < list.size(); i++) {// z
      // PickTail tail = (PickTail) list.get(i);
      // int reason = tail.getPickReason().intValue();
      // if (reason <= 3) // ������ȡ
      // tail.setReason(BusiTools.getBusiValue(reason, BusiConst.SOMEPICK));
      // else
      // // ������ȡ
      // tail.setReason(BusiTools.getBusiValue(reason, BusiConst.DISTROYPICK));
      // }
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  public String getPrintAllEmployeeList_yga(String id) throws BusinessException {
    return tailDao.getTheOrgAllEmployee_LY_YGA(Integer.parseInt(id));
  }

  /** ��õ�һҳ�ķ��� */
  public List findFirstPageData(Pagination p) {
    String id = (String) p.getQueryCriterions().get("idValue");// ����û��ύ��id
    String orderBy = (String) p.getOrderBy();
    String order = (String) p.getOrderother();
    int start = p.getFirstElementOnPage() - 1;
    int pageSize = p.getPageSize();
    List list = tailDao.findFirstPageData_LY(id, orderBy, order, start,
        pageSize);
    if (list == null || list.isEmpty() || list.size() == 0) {
      // ����ط��Ƿ�ֹɾ����...�����ѡɾ����ʱ��..ɾ���������һҳ������..list��Ϊ0..�������ݿ��ﻹ������..
      // ������������findFirstPageData()startΪ0�ڲ�һ��..�������ܹ�ȷ�����������
      list = tailDao.findFirstPageData_LY(id, orderBy, order, 0, pageSize);
    }
    // try {
    // for (int i = 0; i < list.size(); i++) {// ת����ȡ���͵�һ��ССѭ��
    // PickTail pick = (PickTail) list.get(i);
    // if (pick.getPickReason().intValue() <= 3) {
    // pick.setReason(BusiTools.getBusiValue(
    // pick.getPickReason().intValue(), BusiConst.SOMEPICK));
    // } else if (pick.getPickReason().intValue() > 3) {
    // pick.setReason(BusiTools.getBusiValue(
    // pick.getPickReason().intValue(), BusiConst.DISTROYPICK));
    // }
    // pick.setPickDisplayType(BusiTools.getBusiValue(pick.getPickType()
    // .intValue(), BusiConst.PICKUPTYPE));
    // }
    // } catch (Exception s) {
    // s.printStackTrace();
    // }
    int count = tailDao.findFirstPageDataCount(id);
    p.setNrOfElements(count);
    return list;
  }

  public List getFirstPageCount(String id) throws BusinessException {
    Object[] obj = tailDao.queryPickTailCount_LY(Integer.parseInt(id));
    List list = (List) obj[1];
    return list;
  }

  /**
   * ����ְ����id�͵�λ��id���жϴ˵�λ��״̬�Ƿ�<5
   */
  public List findPickHeadStateByOrgIdAndEmpId(int orgId, int empId)
      throws BusinessException {
    return tailDao.findPickHeadStateByOrgIdAndEmpId(orgId, empId);
  }

  /**
   * ���ݵ�λ������ж���ȡ״̬�Ƿ�!=5
   */
  public PickHead findStatusNotIsFive(int id) throws BusinessException {
    return headDao.findStatusNotIsFive(id);
  }

  public void setSpecialPickDao(SpecialPickDAO specialPickDao) {
    this.specialPickDao = specialPickDao;
  }

  /**
   * ���ĺ� ����ְ����ŵó�������ȡ���
   */
  public List getYearPickBalance(int empId, SecurityInfo securityInfo) {
    return tailDao.getYearPickBalanceAndCount(empId, securityInfo);
  }

  /**
   * ���ĺ�... ����ְ����ź͵�λ��λ����������ְ���Ƿ����
   */
  public Emp findEmpByOrdIdAndEmpId(Integer orgId, Integer empId)
      throws BusinessException {
    return empDao.findEmpByOrdIdAndEmpId(orgId, empId);
  }

  /**
   * ���ĺ�.. �ж������ְ������Ƿ���β�������
   */
  public List findPickTailByEmpId(Integer empId) throws BusinessException {
    return tailDao.findPickTailByEmpId(empId.intValue());
  }

  public void setEmpDao(EmpDAO empDao) {
    this.empDao = empDao;
  }

  /**
   * ���ĺ� ��ȡ�Լ�����������Ϣ
   */
  public BigDecimal getDistroyPickupInterest1(Integer orgId, Integer empId,
      String moneyDate) throws BusinessException {
    System.out.println(this.getCurInterest(orgId, empId, moneyDate).add(
        this.getPreInterest(orgId, empId, moneyDate)).divide(new BigDecimal(1),
        2, BigDecimal.ROUND_HALF_DOWN));
    return this.getCurInterest(orgId, empId, moneyDate).add(
        this.getPreInterest(orgId, empId, moneyDate)).divide(new BigDecimal(1),
        2, BigDecimal.ROUND_HALF_DOWN);
  }

  public double getDistroyPickupInterest(Integer orgId, Integer empId,
      String moneyDate) throws BusinessException {
    return 0;
  }

  /**
   * ��ñ�����Ϣ
   */
  public BigDecimal getCurInterest(Integer orgId, Integer empId,
      String moneyDate) throws BusinessException {
    Org org = orgDao.queryById(orgId);
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
    double curIntegral = tailDao.getCurInt(orgId, empId, moneyDate);
    BigDecimal bCurIntegral = new BigDecimal(curIntegral);
    bCurIntegral = bCurIntegral.divide(new BigDecimal(1), 2,
        BigDecimal.ROUND_HALF_DOWN);
    curSubA = tailDao.getCurSubA(orgId.intValue(), empId.intValue());
    curSubB = tailDao.getCurSubB(orgId.intValue(), empId.intValue());
    curSubC = tailDao.getCurSubC(orgId.intValue(), empId.intValue());
    curSubD = tailDao.getCurSubD(orgId.intValue(), empId.intValue());
    curSubE = tailDao.getCurSubE(orgId.intValue(), empId.intValue());
    curSubF = tailDao.getCurSubF(orgId.intValue(), empId.intValue());
    curSubG = tailDao.getCurSubG(orgId.intValue(), empId.intValue());
    curSubH = tailDao.getCurSubH(orgId.intValue(), empId.intValue());
    curSubI = tailDao.getCurSubI(orgId.intValue(), empId.intValue());
    curSubJ = tailDao.getCurSubJ(orgId.intValue(), empId.intValue());
    curSubK = tailDao.getCurSubK(orgId.intValue(), empId.intValue());
    curSubL = tailDao.getCurSubL(orgId.intValue(), empId.intValue());
    double curRate = tailDao.getCurRate(office);
    System.out.println("--bPreIntegral=" + bCurIntegral + "--preRate="
        + curRate);
    curInterest = bCurIntegral.multiply(new BigDecimal(curRate)).divide(
        new BigDecimal(365), 2, BigDecimal.ROUND_HALF_DOWN);
    System.out.println("--curInterest=" + curInterest);
    curInterest = curInterest.add(curSubA).add(curSubB).add(curSubC).add(
        curSubD).add(curSubE).add(curSubF).add(curSubG).add(curSubH).add(
        curSubI).add(curSubJ).add(curSubK).add(curSubL);
    System.out.println("---curInterest=" + curInterest);
    return curInterest;
  }

  /**
   * ����������Ϣ
   */
  public BigDecimal getPreInterest(Integer orgId, Integer empId,
      String moneyDate) throws BusinessException {
    Org org = orgDao.queryById(orgId);
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
    double preIntegral = tailDao.getPreInt(orgId, empId, moneyDate);
    BigDecimal bPreIntegral = new BigDecimal(preIntegral);
    bPreIntegral = bPreIntegral.divide(new BigDecimal(1), 2,
        BigDecimal.ROUND_HALF_DOWN);
    preSubA = tailDao.getPreSubA(orgId.intValue(), empId.intValue());
    preSubB = tailDao.getPreSubB(orgId.intValue(), empId.intValue());
    preSubC = tailDao.getPreSubC(orgId.intValue(), empId.intValue());
    preSubD = tailDao.getPreSubD(orgId.intValue(), empId.intValue());
    preSubE = tailDao.getPreSubE(orgId.intValue(), empId.intValue());
    preSubF = tailDao.getPreSubF(orgId.intValue(), empId.intValue());
    preSubG = tailDao.getPreSubG(orgId.intValue(), empId.intValue());
    preSubH = tailDao.getPreSubH(orgId.intValue(), empId.intValue());
    preSubI = tailDao.getPreSubI(orgId.intValue(), empId.intValue());
    preSubJ = tailDao.getPreSubJ(orgId.intValue(), empId.intValue());
    preSubK = tailDao.getPreSubK(orgId.intValue(), empId.intValue());
    preSubL = tailDao.getPreSubL(orgId.intValue(), empId.intValue());
    // ��������
    double preRate = tailDao.getPreRate(office);
    System.out.println("--bPreIntegral=" + bPreIntegral + "--preRate="
        + preRate);
    preInterest = bPreIntegral.multiply(new BigDecimal(preRate)).divide(
        new BigDecimal(365), 2, BigDecimal.ROUND_HALF_DOWN);
    System.out.println("--preInterest=" + preInterest);
    preInterest = preInterest.add(preSubA).add(preSubB).add(preSubC).add(
        preSubD).add(preSubE).add(preSubF).add(preSubG).add(preSubH).add(
        preSubI).add(preSubJ).add(preSubK).add(preSubL);
    System.out.println("---preInterest=" + preInterest);
    return preInterest;
  }

  /**
   * �ж��Ƿ�Ϊ������ȡ
   */
  public SpecialPick isSpecialPick(int orgId, int empId)
      throws BusinessException {
    return specialPickDao.isSpecialPick(orgId, empId);
  }

  /**
   * ��ӵķ��� ���ĺ�
   */
  public void savePickup(PickupAddAF add,
      PickGetEmployeeInfoAF pickGetEmployeeInfoAF, SpecialPick sp, String ip,
      String userName, String moneyDate) throws BusinessException {
    try {
      String pickBalance = add.getPickBalance();
      String reason = add.getReason();
      String type = add.getType();
      Integer orgId = new Integer(add.getOrgId());
      Integer empId = new Integer(add.getEmpId());
      HafOperateLog haf = new HafOperateLog();
      PickBizActivityLog biz = new PickBizActivityLog();
      String noteNumber = null;
      String other_card_num = "";
      if (add.getNoteNumber() != null && !add.getNoteNumber().equals(""))
        noteNumber = add.getNoteNumber();
      if (add.getOther_card_num() != null
          && !add.getOther_card_num().equals(""))
        other_card_num = add.getOther_card_num();
      // ���ҵ���ʱ�����ж�һ�¸�ְ���Ƿ��Ѿ�������ҵ��β��ͷ��״̬��¼����ᣩ�У��Ǹ���������ʾ�������������ҵ��20080317
      List list_gjp = headDao.isDoubleAdd(orgId, empId);
      if (list_gjp.size() != 0) {
        throw new BusinessException("��ְ����ȡ��Ϣ�Ѿ�����ϵͳ���벻Ҫ�ظ�������");
      }

      PickHead head = new PickHead();
      PickTail tail = new PickTail();// ����ط������б��id
      Serializable isHeadIdValue = headDao.findPickHeadStatueOneByOrgId(orgId
          .intValue());// ����ͷ���Ƿ���ڼ�¼
      String headId = null;
      if (sp != null) {// �����������ȡ..����������ȡ�ı�
        // ���ҵ���ʱ�����ж�һ�¸�ְ���Ƿ��Ѿ�������ҵ��β��ͷ��״̬��δʹ�ã��У��Ǹ���������ʾ�������������ҵ��20080317
        List specialPickList_gjp = headDao
            .isDoubleAdd_SpecialPick(orgId, empId);
        if (specialPickList_gjp.size() != 0) {
          throw new BusinessException("��ְ����ȡ��Ϣ�Ѿ�����ϵͳ���벻Ҫ�ظ�������");
        }
        sp.setIsPick(new BigDecimal(2));
        specialPickDao.updateIsPick(sp);// ֻ�Ǹı�������ȡ��״̬..
      }
      if (isHeadIdValue == null) {// ���ͷ������..
        head.setPickSatatus(new BigDecimal(1));
        if (noteNumber != null)// �������Ʊ�ݱ��
          head.setNoteNum(noteNumber);
        head.setReserveaA("1");
        head.getOrg().setId(orgId);
        // ����ط�����ֵ�Ժ� ���ܹ���Serializable�ı��������� ������String��Integer������
        headId = headDao.insert(head).toString();// ͷ������...����ͷ��..���ظղ����idֵ
      }
      if (headId == null) {// ˵��ͷ����ڼ�¼
        headId = isHeadIdValue.toString();// ���ҵ���������¼��id��headId
      }
      EmpInfoPick emp = tailDao.getEmpInfo_LY(orgId, empId);// ���Emp����
      // ���Ը������������ȡ���������
      if (sp != null)// ��������ȡ...
        tail.setSpecialPick(sp);// ����������ȡ��id...
      if (add.getType().equals("1")) {// ����ǲ�����ȡ..
        PartPickupConditionDTO partPickupConditionDTO = partPickupConditionDAO
            .queryPartPickupConditionInfo();

        double pickBalan = Double.parseDouble(add.getPickBalance());// �Ѳ���Ա��ȡ���ת����double
        double preBalan = emp.getPre_balance().doubleValue();// ���aa002�������
        if (sp == null) {
          if (!pickGetEmployeeInfoAF.getReason().equals("4")
              && !pickGetEmployeeInfoAF.getReason().equals("5")) {
            if (pickBalan > partPickupConditionDTO.getPickMoneyMax()
                .doubleValue()) {
              throw new BusinessException("��ȡ���ܴ��ڲ�����ȡǰ��¼���е������ȡ�޶");
            }
            if (Integer.parseInt(pickGetEmployeeInfoAF.getYearPickNumber()) + 1 > partPickupConditionDTO
                .getPickTimeMax()) {
              throw new BusinessException("��ȡ���ܴ��ڲ�����ȡǰ��¼���е������ȡ������");
            }

            if (Double.parseDouble(pickGetEmployeeInfoAF.getBalance())
                - pickBalan < partPickupConditionDTO.getLeavingsBalance()
                .doubleValue()) {
              throw new BusinessException("��ȡ��Ľ���С�ڲ�����ȡǰ��¼���е�������");
            }
          }
        }

        if (pickBalan < preBalan) {// ��ȡ���С��������
          tail.setPickPreBalance(new BigDecimal(pickBalan));
          tail.setPickCurBalance(new BigDecimal("0.00"));
          tail.setPickCurInterest(new BigDecimal("0.00"));
          tail.setPickPreInterest(new BigDecimal("0.00"));
          // PickHead h = new PickHead();
          // h.setId(new Integer(500));
          // h.setPickSatatus(new BigDecimal(5));
          // h.getOrg().setId(new Integer(100));
          tail.setPickHead(headDao.queryById(new Integer(headId)));
          tail.setPickReason(new BigDecimal(reason));
          tail.setPickType(new BigDecimal(type));
          tail.setEmpId(empId);
          tail.setSpecialPick(sp);
          tail.setReserveaA(add.getHouseNum());
          tailDao.insert(tail);
        } else {
          tail.setPickCurBalance(new BigDecimal(pickBalan - preBalan));
          tail.setPickPreBalance(emp.getPre_balance());
          tail.setPickCurInterest(new BigDecimal("0.00"));
          tail.setPickPreInterest(new BigDecimal("0.00"));
          // PickHead h = new PickHead();
          // h.setPickSatatus(new BigDecimal(23));
          tail.setPickHead(headDao.queryById(new Integer(headId)));
          tail.setPickReason(new BigDecimal(reason));
          tail.setPickType(new BigDecimal(type));
          tail.setEmpId(empId);
          tail.setSpecialPick(sp);
          tail.setReserveaA(add.getHouseNum());
          tailDao.insert(tail);
        }
      } else {// ������ȡ
        // ������ȡʱ�õ�ְ���ķֶ���Ϣ�Լ�����
        PickInterestReteDTO pickInterestReteDTO = tailDao.queryInterestAndRete(
            orgId, empId);
        tail.setPickCurBalance(emp.getCur_balance());// �������
        tail.setPickPreBalance(emp.getPre_balance());// �������
        tail.setPickCurInterest(getCurInterest(orgId, empId, moneyDate));// ������Ϣ
        tail.setPickPreInterest(getPreInterest(orgId, empId, moneyDate));// ������Ϣ
        tail.setPickReason(new BigDecimal(reason));
        tail.setPickType(new BigDecimal(type));
        tail.setEmpId(empId);
        tail.setPickHead(headDao.queryById(new Integer(headId)));
        tail.setSpecialPick(sp);
        // ����ֶε���Ϣ�Լ�����
        tail.setPreIntegralSubA(pickInterestReteDTO.getPreIntegralSubA());
        tail.setCurIntegralSubA(pickInterestReteDTO.getCurIntegralSubA());
        tail.setPreRateA(pickInterestReteDTO.getPreRateA());
        tail.setCurRateA(pickInterestReteDTO.getCurRateA());

        tail.setPreIntegralSubB(pickInterestReteDTO.getPreIntegralSubB());
        tail.setCurIntegralSubB(pickInterestReteDTO.getCurIntegralSubB());
        tail.setPreRateB(pickInterestReteDTO.getPreRateB());
        tail.setCurRateB(pickInterestReteDTO.getCurRateB());

        tail.setPreIntegralSubC(pickInterestReteDTO.getPreIntegralSubC());
        tail.setCurIntegralSubC(pickInterestReteDTO.getCurIntegralSubC());
        tail.setPreRateC(pickInterestReteDTO.getPreRateC());
        tail.setCurRateC(pickInterestReteDTO.getCurRateC());

        tail.setPreIntegralSubD(pickInterestReteDTO.getPreIntegralSubD());
        tail.setCurIntegralSubD(pickInterestReteDTO.getCurIntegralSubD());
        tail.setPreRateD(pickInterestReteDTO.getPreRateD());
        tail.setCurRateD(pickInterestReteDTO.getCurRateD());

        tail.setPreIntegralSubE(pickInterestReteDTO.getPreIntegralSubE());
        tail.setCurIntegralSubE(pickInterestReteDTO.getCurIntegralSubE());
        tail.setPreRateE(pickInterestReteDTO.getPreRateE());
        tail.setCurRateE(pickInterestReteDTO.getCurRateE());

        tail.setPreIntegralSubF(pickInterestReteDTO.getPreIntegralSubF());
        tail.setCurIntegralSubF(pickInterestReteDTO.getCurIntegralSubF());
        tail.setPreRateF(pickInterestReteDTO.getPreRateF());
        tail.setCurRateF(pickInterestReteDTO.getCurRateF());

        tail.setPreIntegralSubG(pickInterestReteDTO.getPreIntegralSubG());
        tail.setCurIntegralSubG(pickInterestReteDTO.getCurIntegralSubG());
        tail.setPreRateG(pickInterestReteDTO.getPreRateG());
        tail.setCurRateG(pickInterestReteDTO.getCurRateG());

        tail.setPreIntegralSubH(pickInterestReteDTO.getPreIntegralSubH());
        tail.setCurIntegralSubH(pickInterestReteDTO.getCurIntegralSubH());
        tail.setPreRateH(pickInterestReteDTO.getPreRateH());
        tail.setCurRateH(pickInterestReteDTO.getCurRateH());

        tail.setPreIntegralSubI(pickInterestReteDTO.getPreIntegralSubI());
        tail.setCurIntegralSubI(pickInterestReteDTO.getCurIntegralSubI());
        tail.setPreRateI(pickInterestReteDTO.getPreRateI());
        tail.setCurRateI(pickInterestReteDTO.getCurRateI());

        tail.setPreIntegralSubJ(pickInterestReteDTO.getPreIntegralSubJ());
        tail.setCurIntegralSubJ(pickInterestReteDTO.getCurIntegralSubJ());
        tail.setPreRateJ(pickInterestReteDTO.getPreRateJ());
        tail.setCurRateJ(pickInterestReteDTO.getCurRateJ());

        tail.setPreIntegralSubK(pickInterestReteDTO.getPreIntegralSubK());
        tail.setCurIntegralSubK(pickInterestReteDTO.getCurIntegralSubK());
        tail.setPreRateK(pickInterestReteDTO.getPreRateK());
        tail.setCurRateK(pickInterestReteDTO.getCurRateK());

        tail.setPreIntegralSubL(pickInterestReteDTO.getPreIntegralSubL());
        tail.setCurIntegralSubL(pickInterestReteDTO.getCurIntegralSubL());
        tail.setPreRateL(pickInterestReteDTO.getPreRateL());
        tail.setCurRateL(pickInterestReteDTO.getCurRateL());

        tail.setPreIntegral(pickInterestReteDTO.getPreIntegral());
        tail.setCurIntegral(pickInterestReteDTO.getCurIntegral());

        tailDao.insert(tail);
      }
      // ������־
      if (isHeadIdValue == null) {// ͷ�����ڲ�����־
        haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// ס��������鼯ϵͳ
        haf.setOpModel(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO + "");// ����ģ��
        haf.setOpButton(BusiLogConst.BIZLOG_ACTION_ADD + "");// ����
        haf.setOpBizId(new Integer(headId.toString()));// ȡͷ��ID
        haf.setOpIp(ip);// IP��ַ
        haf.setOpTime(new Date(new java.util.Date().getTime()));// ʱ��
        haf.setOperator(userName);
        haf.setOrgId(orgId);
        hafDao.insert(haf);
        biz.setBizid(new Integer(headId.toString()));// ͷ��id
        biz.setAction(new Integer(1));
        biz.setOpTime(new Date(new java.util.Date().getTime()));
        biz.setOperator(userName);// �������Ĳ���Ա...��Ȩ���й�ϵ
        pickBizDao.insert(biz);
      } else {
        haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// ס��������鼯ϵͳ
        haf.setOpModel(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO + "");// ����ģ��
        haf.setOpButton(BusiLogConst.BIZLOG_ACTION_ADD + "");// ����
        haf.setOpBizId(new Integer(headId.toString()));// ȡͷ��ID
        haf.setOpIp(ip);// IP��ַ
        haf.setOpTime(new Date(new java.util.Date().getTime()));// ʱ��
        haf.setOperator(userName);
        haf.setOrgId(orgId);
        hafDao.insert(haf);
      }
    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception s) {
      s.printStackTrace();
    }
  }

  public void setHafDao(HafOperateLogDAO hafDao) {
    this.hafDao = hafDao;
  }

  public void setPickBizDao(PickBizActivityLogDAO pickBizDao) {
    this.pickBizDao = pickBizDao;
  }

  public List getExportData(final int orgId, String ip, String operator) {

    // ���������־...
    HafOperateLog haf = new HafOperateLog();
    // OPA_IDΪAA001�в����¼��ID
    // OP_TIMEΪϵͳʱ��
    // OPERATORΪ�������Ĳ���Ա
    haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// ס��������鼯ϵͳ
    haf.setOpModel(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO + "");// ����ģ��
    haf.setOpButton(BusiLogConst.BIZLOG_ACTION_EXP + "");// ����
    haf.setOpBizId(new Integer(0));// ȡͷ��ID
    haf.setOpIp(ip);// IP��ַ
    haf.setOpTime(new Date(new java.util.Date().getTime()));// ʱ��
    haf.setOperator(operator);
    haf.setOrgId(new Integer(orgId));
    hafDao.insert(haf);
    return empDao.getExportData(orgId);
  }

  public Org findOrgById(Integer i) throws BusinessException {
    return orgDao.queryById(i);
  }

  /**
   * ���ĺ�... ������ȡ�ķ���..
   */
  public String recallPickup(int headId, SecurityInfo info) {
    try {
      PickHead h = headDao.queryById(new Integer(headId));
      int orgId = Integer.parseInt(h.getOrg().getId().toString());
      PickHead test = headDao.findByOrgId(orgId);// �������null�Ǿ��ǲ�����(���Գ�����ȡ)����������ز���null�Ǿ��Ǵ���
      if (test != null) {
        return "�˵�λ�Ѿ���ͷ������״̬Ϊ1�ļ�¼��";
      } else {
        String officeCode = "";
        String docNumDocument = collParaDAO.getDocNumDesignPara();
        if (docNumDocument.equals("1")) {
          officeCode = h.getOrg().getOrgInfo().getOfficecode();
        } else {
          officeCode = h.getOrg().getOrgInfo().getCollectionBankId();
        }
        // ����AA306��ƾ֤�Ų���AA306��¼
        insertDocNumCancel(h.getDocNum().substring(8), officeCode, h
            .getSettDate().toString().substring(0, 6));
        // ɾ��ƾ֤��źͻ������---�޸�306ͷ��
        h.setDocNum("");
        h.setSettDate("");
        h.setPickSatatus(new BigDecimal(1));
        OrgHAFAccountFlow orgFlow = orgFlowDao
            .getOrgHAFAccountFlow(new Integer(headId));
        int aa101Id = Integer.parseInt(orgFlow.getId().toString());
        // ɾ��aa102...����ط�������ɾ��aa102
        empFlowDao.deleteEmpHAFAccountFlowAll(new Integer(aa101Id));
        // ����ͷ��idɾ��aa101���еļ�¼
        orgFlowDao.deleteOrgFlowToRecallPickup(headId);// ɾ��aa101�����
        // ����ͷ��idɾ��aa319 type=3
        pickBizDao.delete(pickBizDao.queryByBizId1(headId));
        // ����BA003��־
        HafOperateLog haf = new HafOperateLog();
        haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// ס��������鼯ϵͳ
        haf.setOpModel(BusiLogConst.OP_MODE_DRAWING_DRAWING_MAINTAIN + "");// ����ģ��
        haf.setOpButton(BusiLogConst.BIZLOG_ACTION_REVOCATION + "");// ����
        haf.setOpBizId(new Integer(headId + ""));// ȡͷ��ID
        haf.setOpIp(info.getUserIp());// IP��ַ
        haf.setOpTime(new Date(new java.util.Date().getTime()));// ʱ��
        haf.setOperator(info.getUserName());
        haf.setOrgId(new Integer(orgId));
        hafDao.insert(haf);
        headDao.updatePickHead(h);
        return null;
      }

    } catch (Exception s) {
      s.printStackTrace();
    }
    return "";// ���������ȡʧ��..��ôֱ�ӷ��صĲ���null���ǳ���״̬����1�Ĵ���
  }

  public String getBatchErrorData(List headList, List tailList,
      SecurityInfo info, String note_num, String other_card_num)
      throws BusinessException, Exception {
    List errorList = new ArrayList();
    if (headList.isEmpty() || headList.size() <= 0) {// ͷ������Ϊnull
      throw new BusinessException("��������Ϊ��!");
    }
    if (tailList.isEmpty() || tailList.size() <= 0) {// β������Ϊnull
      throw new BusinessException("��������Ϊ��!");
    }
    PickHeadImportDTO head = (PickHeadImportDTO) headList.get(1);
    String orgId = head.getOrgId();
    if (orgId == null || orgId.trim().length() == 0) {// �����λ�����null��ʱ��
      throw new BusinessException("����������ȷ�ĵ�λ���");
    }
    try {
      Integer.parseInt(head.getOrgId());
    } catch (Exception s) {
      throw new BusinessException("��λ��Ÿ�ʽ����ȷ");
    }
    // Org org = orgDao.isHaveOrg(new Integer(head.getOrgId()),
    // info);//������ط�������ô�Ȩ�޵Ĳ��ҵ�λ�ķ���
    if (orgDao.isHaveOrg(new Integer(head.getOrgId()), info))// �����ڵ�λ���
      throw new BusinessException("�㵼��ĵ�λ��Ų�����");
    else {// ���ڵ�λ���,,�����˵�λ��״̬�Ƿ�Ϊ������!=5
      if (headDao.isHaveNoComplite(Integer.parseInt(orgId)))
        throw new BusinessException("����ĵ�λ��δ��ɵ���ȡ״̬");
    }
    String sun_re = "no";
    String sun_rea = "no";
    for (int i = 1; i < tailList.size(); i++) {
      PickTailImportDTO dto = (PickTailImportDTO) tailList.get(i);
      // �ж�ְ�����
      if (dto.getEmpId() == null || dto.getEmpId().trim().length() <= 0) {
        throw new BusinessException("ְ����Ų���Ϊ��");
      }
      try {
        Integer.parseInt(dto.getEmpId());
      } catch (Exception s) {
        throw new BusinessException("ְ����Ÿ�ʽ����ȷ");
      }
      String empId = dto.getEmpId();
      String result = "ְ�����Ϊ" + empId;
      // �ж���ȡ����
      if (dto.getPickType() == null || dto.getPickType().trim().length() <= 0) {
        throw new BusinessException(result + "��ȡ���Ͳ���Ϊ��");
      }
      try {
        Integer.parseInt(dto.getPickType());
      } catch (Exception s) {
        throw new BusinessException(result + "��ȡ���͸�ʽ����ȷ");
      }
      if (!dto.getPickType().equals("1") && !dto.getPickType().equals("2")) {
        throw new BusinessException(result + "�����ڵ������ȡ����");
      }
      if (dto.getPickReason().equals("1")
          && (dto.getHouseNum() == null || "".equals(dto.getHouseNum().trim()))) {
        throw new BusinessException(result + "����ȡ����Ϊ����������д�䷿�պţ�");
      }
      if (!dto.getPickReason().equals("1")
          && (dto.getHouseNum() != null && !"".equals(dto.getHouseNum().trim()))) {
        throw new BusinessException(result + "����ȡ���Ͳ�Ϊ�������뽫�䷿�պ�ȥ����");
      }
      if (dto.getPickType().equals(BusiConst.PICKUPTYPE_1 + "")) {
        // �ж���ȡ���
        if (dto.getPickBalance() == null
            || dto.getPickBalance().trim().length() <= 0) {
          throw new BusinessException(result + "��ȡ����Ϊ��");
        }
        try {
          Double.parseDouble(dto.getPickBalance());
        } catch (Exception s) {
          throw new BusinessException(result + "��ȡ����ʽ���ִ���");
        }
      }
      // �ж���ȡԭ��
      if (dto.getPickReason() == null
          || dto.getPickReason().trim().length() <= 0) {
        throw new BusinessException(result + "��ȡԭ����Ϊ��");
      }
      try {
        Integer.parseInt(dto.getPickReason());
      } catch (Exception s) {
        throw new BusinessException(result + "��ȡԭ���ʽ����ȷ");
      }

      if (errorPickReason(dto.getPickReason())) {
        throw new BusinessException(result + "�����ڵ������ȡԭ��");
      }
      if (dto.getPickReason().toString().equals("2")
          || dto.getPickReason().toString().equals("3")) {
        throw new BusinessException(result + "���������������𻹴�����һ���Ի����ҵ��");
      }
      // �жϵ�λ���Ƿ���ڴ�ְ��
      if (empDao.isEmpAtOrg(new Integer(orgId), new Integer(empId))) {
        throw new BusinessException("�������ְ�����" + empId + "�˵�λ�²�����");
      }
      if (dto.getPickType().equals(BusiConst.PICKUPTYPE_1 + "")) {
        if (!dto.getPickReason().equals(BusiConst.BUYHOUSE + "")
            && !dto.getPickReason().equals(BusiConst.GIVEMONEYBYMON + "")
            && !dto.getPickReason().equals(BusiConst.GIVEMONEYClEAR + "")
            && !dto.getPickReason().equals(BusiConst.DISEASE + "")
            && !dto.getPickReason().equals(BusiConst.DISTRESS + "")
            && !dto.getPickReason().equals(BusiConst.PARTREST + "")) {
          throw new BusinessException(result + "����ȡԭ������ȡ���Ͳ�����");
        }
      } else if (dto.getPickType().equals(BusiConst.PICKUPTYPE_2 + "")) {
        if (!dto.getPickReason().equals(BusiConst.BOWOUT + "")
            && !dto.getPickReason().equals(BusiConst.DEATH + "")
            && !dto.getPickReason().equals(BusiConst.DECRUITMENT + "")
            && !dto.getPickReason().equals(BusiConst.DISTORY + "")
            && !dto.getPickReason().equals(BusiConst.JOBLESS + "")
            && !dto.getPickReason().equals(BusiConst.SETTLE + "")) {
          throw new BusinessException(result + "����ȡԭ������ȡ���Ͳ�����");
        }
      }

      /* �����ӣ��ж���ȡԭ�� */
      String checkreason = this.getAA306_1();
      String[] cr = checkreason.split(",");
      if (i == 1) {
        for (int c = 0; c < cr.length; c++) {
          if (cr[c].equals(dto.getPickReason())) {
            sun_re = "yes";
            break;
          }
        }
      } else {
        sun_rea = "no";
        for (int c = 0; c < cr.length; c++) {
          if (cr[c].equals(dto.getPickReason())) {
            sun_rea = "yes";
            break;
          }
        }
      }

      if (i!=1 && !sun_re.equals(sun_rea)) {
        throw new BusinessException("���ļ�������Ҫ�����Ͳ���Ҫ�����ģ���ֿ����룡");
      }
      /* �����ӣ��ж���ȡԭ�� */

      double balance = empDao.getTheEmployeeBalance(
          Integer.parseInt(head.getOrgId()), Integer.parseInt(dto.getEmpId()))
          .doubleValue();

      // ׷����ȡ����ж�����...

      // ��ѯ����Ƚɶ�.
      Emp emp = empDao.findEmpByOrdIdAndEmpId(new Integer(orgId), new Integer(
          empId));
      BigDecimal paymoney = emp.getPayOldYear();
      // �����Ϊ��ʱ,ȡ��ǰ�ɶ�.
      if (paymoney == null || paymoney.toString().equals("0")) {
        paymoney = emp.getEmpPay().add(emp.getOrgPay());
      }

      // ȡ����ȡ��������.
      PartPickupConditionDTO partPickupConditionDTO = new PartPickupConditionDTO();
      partPickupConditionDTO = drawRuleDAO.queryPartPickupConditionInfo();
      BigDecimal mul = new BigDecimal(partPickupConditionDTO.getMultiple());

      // ������ȡ---�������ش󼲲�ʱȡ�������;��������ɶ�*����.
      if (dto.getPickType().equals(BusiConst.PICKUPTYPE_1 + "")) {
        if (dto.getPickReason().equals("4") || dto.getPickReason().equals("5")) {
          balance = drawRuleDAO.getPerbalance(
              Integer.parseInt(head.getOrgId()),
              Integer.parseInt(dto.getEmpId()), dto.getPickReason().toString())
              .doubleValue();
        } else {
          BigDecimal maxMoney = drawRuleDAO.getSomePickMoney(Integer
              .parseInt(head.getOrgId()), Integer.parseInt(dto.getEmpId()), dto
              .getPickReason().toString());
          balance = maxMoney.subtract(paymoney.multiply(mul)).doubleValue();
        }
      }

      if (dto.getPickType().equals(BusiConst.PICKUPTYPE_1 + "")) {
        if (Double.parseDouble(dto.getPickBalance()) > balance) {
          throw new BusinessException(result + "��ְ����ȡ������");
        }
      }
    }
    // ����AA306
    PickHead pickHead = new PickHead();
    pickHead.getOrg().setId(new Integer(head.getOrgId()));
    pickHead.setPickSatatus(new BigDecimal(1));

    if (head.getOrgNoteNumber() != null
        && head.getOrgNoteNumber().trim().length() > 0) {// ��Ʊ�ݱ��
      pickHead.setNoteNum(head.getOrgNoteNumber());
    } else {
      if (note_num != "" && note_num != null) {
        pickHead.setNoteNum(note_num);
      }
    }
    pickHead.setReserveaA("1");
    Serializable headId = headDao.insert(pickHead);
    temp_headid = headId.toString();
    // ����AA307
    // PickHead pickHeadset=headDao.queryById(new Integer(headId.toString()));
    for (int i = 1; i < tailList.size(); i++) {
      PickTailImportDTO ok = (PickTailImportDTO) tailList.get(i);
      PickTail p = new PickTail();
      String empIdInsert = ok.getEmpId();
      EmpInfoPick emp = tailDao.getEmpInfo_LY(new Integer(orgId), new Integer(
          empIdInsert));
      p.setPickHead(pickHead);// ����ͷ��id;
      p.setPickReason(new BigDecimal(ok.getPickReason()));// ԭ��
      p.setPickType(new BigDecimal(ok.getPickType()));// ����
      p.setEmpId(new Integer(empIdInsert));// ְ�����
      p.setReserveaA(ok.getHouseNum());
      if (ok.getPickType().equals("1")) {// ������ȡ..
        // if (Double.parseDouble(ok.getPickBalance()) >= emp.getBalance()
        // .doubleValue()) {// ����ȡ�����ڱ������
        // p.setPickPreBalance(emp.getPreBalance());
        // p.setPickCurBalance(new BigDecimal(Double.parseDouble(ok
        // .getPickBalance())
        // - emp.getPreBalance().doubleValue()));
        // } else {
        // p.setPickPreBalance(new BigDecimal(ok.getPickBalance()));
        // p.setPickCurBalance(new BigDecimal(0.00));
        // }
        // p.setPickCurInterest(new BigDecimal(0.00));
        // p.setPickPreInterest(new BigDecimal(0.00));

        List empList = tailDao.findPickTailByEmpId(new Integer(empIdInsert)
            .intValue());
        String result = "ְ�����Ϊ" + empIdInsert;
        Object[] obj = (Object[]) tailDao.getYearPickBalanceAndCount(
            new Integer(empIdInsert).intValue(), info).get(0);
        PartPickupConditionDTO partPickupConditionDTO = null;
        try {
          partPickupConditionDTO = partPickupConditionDAO
              .queryPartPickupConditionInfo();
        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        if (partPickupConditionDTO != null) {
          if (Double.parseDouble(ok.getPickBalance()) > partPickupConditionDTO
              .getPickMoneyMax().doubleValue()) {
            throw new BusinessException(result + "��ְ����ȡ���ܴ��ڲ�����ȡǰ��¼���е������ȡ�޶");
          }

          // if(empList!=null){
          int picktime = Integer.parseInt(obj[0].toString());
          // double balance=Double.parseDouble(obj[1].toString());
          double balance = 0;
          if (picktime > partPickupConditionDTO.getPickTimeMax() - 1) {
            throw new BusinessException(result + "��ְ����ȡ���ܴ��ڲ�����ȡǰ��¼���е������ȡ������");
          }
          BigDecimal maxMoney = tailDao.getMaxPickMoney(
              Integer.parseInt(orgId), Integer.parseInt(empIdInsert), null);
          if (maxMoney == null) {
            balance = 0;
          } else {
            balance = Double.parseDouble(maxMoney.setScale(2).toString());
          }

          if (balance - Double.parseDouble(ok.getPickBalance()) < partPickupConditionDTO
              .getLeavingsBalance().doubleValue()) {
            throw new BusinessException(result + "��ְ����ȡ��Ľ���С�ڲ�����ȡǰ��¼���е�������");
          }

          // }

        }
        if (Double.parseDouble(ok.getPickBalance()) < emp.getPre_balance()
            .doubleValue()) {// ��ȡ���С��������
          p.setPickPreBalance(new BigDecimal(ok.getPickBalance()));
          p.setPickCurBalance(new BigDecimal("0.00"));
          p.setPickCurInterest(new BigDecimal("0.00"));
          p.setPickPreInterest(new BigDecimal("0.00"));
        } else {
          p.setPickCurBalance(new BigDecimal(Double.parseDouble(ok
              .getPickBalance())
              - emp.getPre_balance().doubleValue()));
          p.setPickPreBalance(emp.getPre_balance());
          p.setPickCurInterest(new BigDecimal("0.00"));
          p.setPickPreInterest(new BigDecimal("0.00"));
        }
      } else {// ������ȡ
        PickInterestReteDTO pickInterestReteDTO = tailDao.queryInterestAndRete(
            new Integer(orgId), new Integer(empIdInsert));
        p.setPickPreBalance(emp.getPre_balance());
        p.setPickCurBalance(emp.getCur_balance());
        p.setPickPreInterest(this.getPreInterest(new Integer(head.getOrgId()),
            new Integer(empIdInsert), info.getUserInfo().getBizDate()));
        p.setPickCurInterest(this.getCurInterest(new Integer(head.getOrgId()),
            new Integer(empIdInsert), info.getUserInfo().getBizDate()));
        // ����ֶε���Ϣ�Լ�����
        p.setPreIntegralSubA(pickInterestReteDTO.getPreIntegralSubA());
        p.setCurIntegralSubA(pickInterestReteDTO.getCurIntegralSubA());
        p.setPreRateA(pickInterestReteDTO.getPreRateA());
        p.setCurRateA(pickInterestReteDTO.getCurRateA());

        p.setPreIntegralSubB(pickInterestReteDTO.getPreIntegralSubB());
        p.setCurIntegralSubB(pickInterestReteDTO.getCurIntegralSubB());
        p.setPreRateB(pickInterestReteDTO.getPreRateB());
        p.setCurRateB(pickInterestReteDTO.getCurRateB());

        p.setPreIntegralSubC(pickInterestReteDTO.getPreIntegralSubC());
        p.setCurIntegralSubC(pickInterestReteDTO.getCurIntegralSubC());
        p.setPreRateC(pickInterestReteDTO.getPreRateC());
        p.setCurRateC(pickInterestReteDTO.getCurRateC());

        p.setPreIntegralSubD(pickInterestReteDTO.getPreIntegralSubD());
        p.setCurIntegralSubD(pickInterestReteDTO.getCurIntegralSubD());
        p.setPreRateD(pickInterestReteDTO.getPreRateD());
        p.setCurRateD(pickInterestReteDTO.getCurRateD());

        p.setPreIntegralSubE(pickInterestReteDTO.getPreIntegralSubE());
        p.setCurIntegralSubE(pickInterestReteDTO.getCurIntegralSubE());
        p.setPreRateE(pickInterestReteDTO.getPreRateE());
        p.setCurRateE(pickInterestReteDTO.getCurRateE());

        p.setPreIntegralSubF(pickInterestReteDTO.getPreIntegralSubF());
        p.setCurIntegralSubF(pickInterestReteDTO.getCurIntegralSubF());
        p.setPreRateF(pickInterestReteDTO.getPreRateF());
        p.setCurRateF(pickInterestReteDTO.getCurRateF());

        p.setPreIntegralSubG(pickInterestReteDTO.getPreIntegralSubG());
        p.setCurIntegralSubG(pickInterestReteDTO.getCurIntegralSubG());
        p.setPreRateG(pickInterestReteDTO.getPreRateG());
        p.setCurRateG(pickInterestReteDTO.getCurRateG());

        p.setPreIntegralSubH(pickInterestReteDTO.getPreIntegralSubH());
        p.setCurIntegralSubH(pickInterestReteDTO.getCurIntegralSubH());
        p.setPreRateH(pickInterestReteDTO.getPreRateH());
        p.setCurRateH(pickInterestReteDTO.getCurRateH());

        p.setPreIntegralSubI(pickInterestReteDTO.getPreIntegralSubI());
        p.setCurIntegralSubI(pickInterestReteDTO.getCurIntegralSubI());
        p.setPreRateI(pickInterestReteDTO.getPreRateI());
        p.setCurRateI(pickInterestReteDTO.getCurRateI());

        p.setPreIntegralSubJ(pickInterestReteDTO.getPreIntegralSubJ());
        p.setCurIntegralSubJ(pickInterestReteDTO.getCurIntegralSubJ());
        p.setPreRateJ(pickInterestReteDTO.getPreRateJ());
        p.setCurRateJ(pickInterestReteDTO.getCurRateJ());

        p.setPreIntegralSubK(pickInterestReteDTO.getPreIntegralSubK());
        p.setCurIntegralSubK(pickInterestReteDTO.getCurIntegralSubK());
        p.setPreRateK(pickInterestReteDTO.getPreRateK());
        p.setCurRateK(pickInterestReteDTO.getCurRateK());

        p.setPreIntegralSubL(pickInterestReteDTO.getPreIntegralSubL());
        p.setCurIntegralSubL(pickInterestReteDTO.getCurIntegralSubL());
        p.setPreRateL(pickInterestReteDTO.getPreRateL());
        p.setCurRateL(pickInterestReteDTO.getCurRateL());
      }
      p.setSpecialPick(null);// ����ط�����Ϊnull
      tailDao.insert(p);
    }
    // 139��ȫһ��
    PickHeadImportDTO head1 = (PickHeadImportDTO) headList.get(0);
    String orgId1 = head1.getOrgId();
    if (orgId1 != null) {// ˵�����Ǵ���ȡ������ba003
      HafOperateLog haf = new HafOperateLog();
      haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// ס��������鼯ϵͳ
      haf.setOpModel(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO + "");// ����ģ��
      haf.setOpButton(BusiLogConst.BIZBLOG_ACTION_IMP + "");// ����
      haf.setOpBizId(new Integer(headId.toString()));// ȡͷ��ID
      haf.setOpIp(info.getUserIp());// IP��ַ
      haf.setOpTime(new Date(new java.util.Date().getTime()));// ʱ��-->ϵͳʱ��
      haf.setOperator(info.getUserName());
      haf.setOrgId(new Integer(head.getOrgId()));
      hafDao.insert(haf);
    }
    PickBizActivityLog biz = new PickBizActivityLog();
    biz.setBizid(new Integer(headId.toString()));// ͷ��id
    biz.setAction(new Integer(1));
    biz.setOpTime(new Date(new java.util.Date().getTime()));
    biz.setOperator(info.getUserName());// �������Ĳ���Ա...��Ȩ���й�ϵ
    pickBizDao.insert(biz);
    return head.getOrgId();
  }

  // �ж���ȡ�����Ƿ��ǺϷ�����ȡԭ��
  public boolean errorPickReason(String reason) {
    if (reason.equals("1"))
      return false;
    if (reason.equals("2"))
      return false;
    if (reason.equals("3"))
      return false;
    if (reason.equals("4"))
      return false;
    if (reason.equals("5"))
      return false;
    if (reason.equals("6"))
      return false;
    if (reason.equals("7"))
      return false;
    if (reason.equals("8"))
      return false;
    if (reason.equals("9"))
      return false;
    if (reason.equals("10"))
      return false;
    if (reason.equals("11"))
      return false;
    if (reason.equals("12"))
      return false;
    return true;
  }

  /**
   * ɾ��һ��Ա��
   */
  public void deleteEmployee(int headId, int empId, String last, String ip,
      String operator, SecurityInfo info) throws BusinessException {
    try {
      SpecialPick special = tailDao.is_findEmployeeBySpecial(headId, empId);
      if (special != null) {// ��Ա��������ȡ��ʱ��..�����޸Ĵ�Ա����״̬Ϊ1
        special.setIsPick(new BigDecimal(1));
        specialPickDao.updateByIsPick(special);// �޸�
      }
      PickHead head = headDao.queryById(new Integer(headId));// ���ݵõ���ͷ�������Ե�������orgID
      // �������ɾ��ͷ��ı��ǰ��,��Ȼ�ڲ�����־��ʱ��...�����nullPointException
      if (last.equals("ɾ��ͷβ��")) {// ֻʣ��һ����¼��..ɾ��������
        int isOrgEdition = info.getIsOrgEdition();
        if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// ��λ��
          boolean isNoPickUp = autoInfoPickDAODW.isNOPickUp(head.getOrg()
              .getId().toString(), head.getId().toString(),
              BusiConst.ORGBUSINESSTYPE_D);
          String stype = autoInfoPickDAODW
              .findStatus(head.getOrg().getId().toString(), head.getId()
                  .toString(), BusiConst.ORGBUSINESSTYPE_D);
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
              center_head_id, centerBizDate, head.getOrg().getId().toString(),
              head.getId().toString(), BusiConst.ORGBUSINESSTYPE_D);
        }
        // ������ɾ��β����ɾ��ͷ��...��Ȼ�������ִ���
        tailDao.deleteByEmpIdAndHeadId(empId, headId);
        headDao.deleteById(new Integer(headId));// ����ͷ��id��ɾ��ͷ��
        // ɾ��319����־
        PickBizActivityLog b = pickBizDao.queryByBizId(headId);
        pickBizDao.delete(b);
      } else {// û��ʣ�����һ����¼
        tailDao.deleteByEmpIdAndHeadId(empId, headId);
      }

      HafOperateLog haf = new HafOperateLog();
      haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// ס��������鼯ϵͳ
      haf.setOpModel(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO + "");// ����ģ��
      haf.setOpButton(BusiLogConst.BIZLOG_ACTION_DELETE + "");// ����
      haf.setOpBizId(new Integer(headId));// ȡͷ��ID
      haf.setOpIp(ip);// IP��ַ
      haf.setOpTime(new Date(new java.util.Date().getTime()));// ʱ��
      haf.setOperator(operator);
      haf.setOrgId(new Integer(head.getOrg().getId().toString()));// ��λ���
      hafDao.insert(haf);
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Serializable insertPickTail(PickTail pickTail) {
    return tailDao.insert(pickTail);
  }

  public List findDataByHeadId(int headId) {
    return tailDao.findDataByHeadId(headId);
  }

  /**
   * ȫ��ɾ���ķ���
   */
  public void allDelete(int headId, String ip, String operator,
      SecurityInfo info) throws BusinessException {
    try {
      PickHead head = headDao.queryById(new Integer(headId));// ���ݵõ���ͷ�������Ե�������orgID
      int isOrgEdition = info.getIsOrgEdition();
      if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// ��λ��
        boolean isNoPickUp = autoInfoPickDAODW.isNOPickUp(head.getOrg().getId()
            .toString(), head.getId().toString(), BusiConst.ORGBUSINESSTYPE_D);
        String stype = autoInfoPickDAODW.findStatus(head.getOrg().getId()
            .toString(), head.getId().toString(), BusiConst.ORGBUSINESSTYPE_D);
        if (stype.equals(BusiConst.OC_PICK_UP)) {
          throw new BusinessException("��������ȡ������ɾ��");
        }
        if (isNoPickUp) {
          throw new BusinessException("���ȳ����ύ���ݣ�");
        }
      } else {// ���İ�
        String center_head_id = "";
        String centerBizDate = "";
        autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP,
            center_head_id, centerBizDate, head.getOrg().getId().toString(),
            head.getId().toString(), BusiConst.ORGBUSINESSTYPE_D);
      }
      List specialPicklist = tailDao.findPickTailBySpecialPick(headId);
      if (specialPicklist != null && specialPicklist.size() > 0) {
        for (int i = 0; i < specialPicklist.size(); i++) {
          PickTail p = (PickTail) specialPicklist.get(i);
          SpecialPick special = tailDao.is_findEmployeeBySpecial(headId,
              Integer.parseInt(p.getEmpId().toString()));
          // ��Ա��������ȡ��ʱ��..�����޸Ĵ�Ա����״̬Ϊ1
          special.setIsPick(new BigDecimal(1));
          specialPickDao.updateByIsPick(special);// �޸�
        }
      }
      tailDao.deleteTail(new Integer(headId));// ɾ������

      // �����ȵ��� ..����ͷ��Ķ���...�������ܹ��õ�ͷ��Ե�orgId
      // ������ɾ��..���������null point Exception
      headDao.deleteById_LY(new Integer(headId));// ɾ��306
      PickBizActivityLog b = pickBizDao.queryByBizId(headId);
      pickBizDao.delete(b);
      // ������־
      HafOperateLog haf = new HafOperateLog();
      haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// ס��������鼯ϵͳ
      haf.setOpModel(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO + "");// ����ģ��
      haf.setOpButton(BusiLogConst.BIZLOG_ACTION_DELETEALL + "");// ����
      haf.setOpBizId(new Integer(headId));// ȡͷ��ID
      haf.setOpIp(ip);// IP��ַ
      haf.setOpTime(new Date(new java.util.Date().getTime()));// ʱ��
      haf.setOperator(operator);
      haf.setOrgId(new Integer(head.getOrg().getId().toString()));// ��λ���
      hafDao.insert(haf);
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * �����ȡ�ķ���
   */
  public boolean overPick(int orgId, String ip, String operator,
      String moneyDate, String noteNumber, SecurityInfo securityInfo)
      throws Exception {
    try {
      // ���β���Ψһ�ύ��¼..�˼�¼һ���Ǵ��ڵ�
      PickHead head = headDao.findByOrgId(orgId);// ����orgId��״̬Ϊ1�ļ�¼�ҳ���ͷ��
      Org org = orgDao.queryById(new Integer(orgId));
      String officeCode = "";
      String docNumDocument = collParaDAO.getDocNumDesignPara();
      if (docNumDocument.equals("1")) {
        officeCode = org.getOrgInfo().getOfficecode();
      } else {
        officeCode = org.getOrgInfo().getCollectionBankId();
      }
      // DocNumBS dn = new DocNumBS();
      String notenum = "";
      // //"..........����306..........."
      if (!head.getPickSatatus().equals("1")) {
        String docNumber = getDocNumber(officeCode, moneyDate.substring(0, 6),
            securityInfo);// ���ݵ�λ��Ϣ�ɻ�����İ��´�.��.�������������ƾ֤���
        // head.setDocNum(docNumber);
        // head.setSettDate(moneyDate);// ����������
        // head.setPickSatatus(new BigDecimal(3));
        if (noteNumber != null) {// ���Ʊ�ݱ��!-==null��ʱ��Ͳ���...��סҪ��Ϊ�˱�֤��ȡά���е���ȡ���..������Ʊ�ݱ�Ų��øı�
          notenum = noteNumber;
          // head.setNoteNum(noteNumber);
        } else {
          notenum = head.getNoteNum();
        }
        BizActivityLog bizActivityLog = bizActivityLogDAO
            .queryBizActivityLogWuht_(head.getId().toString(), "D", "1");// ��aa319�в��ҵǼ���
        String registrations = bizActivityLog.getOperator();
        headDao.overPickUp(new Integer(head.getId().toString()), new Integer(
            orgId), docNumber, moneyDate, notenum, operator, org.getOrgInfo()
            .getOfficecode(), org.getOrgInfo().getCollectionBankId(),
            registrations);

        // headDao.updatePickHead(head);
        // ........���� aa102..........
        // List list = tailDao.getAllEmpId(new Integer(head.getId().toString())
        // .intValue());// ��Ϊ���List һ�������..��Ϊ���������ʾ�����Ǿ��Ǵ���Ϊ����������
        // // ........���� aa101..........
        // Serializable aa102Id = saveOrgStream(new
        // Integer(org.getId().toString()).intValue(), docNumber, moneyDate,
        // new BigDecimal(head.getId().toString()), noteNumber,operator,list);
        // OrgHAFAccountFlowDrawing orgFlow = orgFlowDao.queryById(new Integer(
        // aa102Id.toString()));// ��øղ����ȥ��aa101�Ķ���
        // for (int i = 0; i < list.size(); i++) {
        // saveEmpStream(orgFlow, new Integer(head.getId().toString()), new
        // Integer(list.get(i)
        // .toString()),new Integer(org.getId().toString()));
        // }
        // ............���� aa319............
        PickBizActivityLog biz = new PickBizActivityLog();// ����aa319Log
        biz.setBizid(new Integer(head.getId().toString()));
        biz.setAction(new Integer(3));
        biz.setOpTime(new Date(new java.util.Date().getTime()));
        biz.setOperator(operator);
        pickBizDao.insert(biz);
        // ............���� ba003............
        HafOperateLog haf = new HafOperateLog();
        haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// ס��������鼯ϵͳ
        haf.setOpModel(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO + "");// ����ģ��
        haf.setOpButton(BusiLogConst.BIZLOG_ACTION_OPENING + "");// ����
        haf.setOpBizId(new Integer(head.getId().toString()));// ȡͷ��ID
        haf.setOpIp(ip);// IP��ַ
        haf.setOpTime(new Date(new java.util.Date().getTime()));// ʱ��
        haf.setOperator(operator);
        haf.setOrgId(new Integer(orgId));
        hafDao.insert(haf);
        return true;
      }
    } catch (Exception s) {
      return false;
    }
    return false;
  }

  /**
   * ��ȡά���������ȡ�ķ���
   */
  public boolean overPickVindicate(int orgId, String ip, String operator,
      String moneyDate, String noteNumber, SecurityInfo securityInfo)
      throws Exception {
    try {
      // ���β���Ψһ�ύ��¼..�˼�¼һ���Ǵ��ڵ�
      PickHead head = headDao.findByOrgId(orgId);// ����orgId��״̬Ϊ1�ļ�¼�ҳ���ͷ��
      Org org = orgDao.queryById(new Integer(orgId));
      String officeCode = "";
      String docNumDocument = collParaDAO.getDocNumDesignPara();
      if (docNumDocument.equals("1")) {
        officeCode = org.getOrgInfo().getOfficecode();
      } else {
        officeCode = org.getOrgInfo().getCollectionBankId();
      }
      // DocNumBS dn = new DocNumBS();
      String notenum = "";
      // //"..........����306..........."
      if (!head.getPickSatatus().equals("1")) {
        String docNumber = getDocNumber(officeCode, moneyDate.substring(0, 6),
            securityInfo);// ���ݵ�λ��Ϣ�ɻ�����İ��´�.��.�������������ƾ֤���
        // head.setDocNum(docNumber);
        // head.setSettDate(moneyDate);// ����������
        // head.setPickSatatus(new BigDecimal(3));

        if (noteNumber != null) {// ���Ʊ�ݱ��!-==null��ʱ��Ͳ���...��סҪ��Ϊ�˱�֤��ȡά���е���ȡ���..������Ʊ�ݱ�Ų��øı�
          notenum = noteNumber;
          // head.setNoteNum(noteNumber);
        } else {
          notenum = head.getNoteNum();
        }
        BizActivityLog bizActivityLog = bizActivityLogDAO
            .queryBizActivityLogWuht_(head.getId().toString(), "D", "1");// ��aa319�в��ҵǼ���
        String registrations = bizActivityLog.getOperator();
        headDao.overPickUp(new Integer(head.getId().toString()), new Integer(
            orgId), docNumber, moneyDate, notenum, operator, org.getOrgInfo()
            .getOfficecode(), org.getOrgInfo().getCollectionBankId(),
            registrations);
        // headDao.updatePickHead(head);
        // List list = tailDao.getAllEmpId(new Integer(head.getId().toString())
        // .intValue());// ��Ϊ���List һ�������..��Ϊ���������ʾ�����Ǿ��Ǵ���Ϊ����������
        // // ........���� aa101..........
        // Serializable aa102Id = saveOrgStream(new
        // Integer(org.getId().toString()).intValue(), docNumber, moneyDate,
        // new BigDecimal(head.getId().toString()), noteNumber,operator,list);
        // OrgHAFAccountFlowDrawing orgFlow = orgFlowDao.queryById(new Integer(
        // aa102Id.toString()));// ��øղ����ȥ��aa101�Ķ���
        // // ........���� aa102..........
        // for (int i = 0; i < list.size(); i++) {
        // saveEmpStream(orgFlow, new Integer(head.getId().toString()), new
        // Integer(list.get(i)
        // .toString()),new Integer(org.getId().toString()));
        // }
        // ............���� aa319............
        PickBizActivityLog biz = new PickBizActivityLog();// ����aa319Log
        biz.setBizid(new Integer(head.getId().toString()));
        biz.setAction(new Integer(3));
        biz.setOpTime(new Date(new java.util.Date().getTime()));
        biz.setOperator(operator);
        pickBizDao.insert(biz);
        // ............���� ba003............
        HafOperateLog haf = new HafOperateLog();
        haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// ס��������鼯ϵͳ
        haf.setOpModel(BusiLogConst.OP_MODE_DRAWING_DRAWING_MAINTAIN + "");// ����ģ��
        haf.setOpButton(BusiLogConst.BIZLOG_ACTION_OPENING + "");// ����
        haf.setOpBizId(new Integer(head.getId().toString()));// ȡͷ��ID
        haf.setOpIp(ip);// IP��ַ
        haf.setOpTime(new Date(new java.util.Date().getTime()));// ʱ��
        haf.setOperator(operator);
        haf.setOrgId(new Integer(orgId));
        hafDao.insert(haf);
        return true;
      }
    } catch (Exception s) {
      return false;
    }
    return false;
  }

  /**
   * ����ְ��ҵ����ˮ
   */
  public void saveEmpStream(OrgHAFAccountFlow orgFlow, Integer headId,
      Integer empId, Integer orgId) {
    // ....����AA102....ְ����ҵ����ˮ�� ���Գɹ�
    EmpHAFAccountFlow empStream = new EmpHAFAccountFlow();
    empStream.setEmpId(empId);
    empStream.setOrgHAFAccountFlow(orgFlow);// ��λ��ˮ��
    empStream.setCredit(new BigDecimal(0));
    empStream.setDebit(tailDao.getTheEmpBalance(headId.intValue(), orgId
        .intValue(), empId.intValue()));
    empStream.setInterest(tailDao.getTheEmpInterest(headId.intValue(), orgId
        .intValue(), empId.intValue()));
    empFlowDao.insert(empStream);
  }

  /**
   * ���浥λ��ˮ
   */
  public Serializable saveOrgStream(int orgId, String docNum, String moneyDate,
      BigDecimal headId, String noteNumber, String operator, List list) {
    // .......����AA101.....//��λ��ȡ��ҵ����ˮ��..�˱��Ӧ������abstract..���������������..��������ȡ������
    try {
      /** ����ͨ��OK** */
      Org org = orgDao.queryById(new Integer(orgId));// ���Org��
      OrgHAFAccountFlowDrawing orgStream = new OrgHAFAccountFlowDrawing();
      BigDecimal balance = tailDao.getDebit(new Integer(headId.intValue())
          .intValue());// ��ô˵�λҪ�����Ǯ..
      BigDecimal interest = tailDao.getInterest(new Integer(headId.intValue())
          .intValue());
      orgStream.setOrg(org);
      orgStream.setDocNum(docNum);
      orgStream.setSettDate(moneyDate);
      orgStream.setDebit(balance);
      orgStream.setCredit(new BigDecimal(0));
      orgStream.setInterest(interest);
      orgStream.setBizId(headId);
      orgStream.setIsFinance(new BigDecimal(1));
      orgStream.setBizStatus(new BigDecimal(3));
      orgStream.setNoteNum(noteNumber);
      orgStream.setReserveaA(operator);
      orgStream.setPersonTotal(new Integer(list.size()));
      // ����������ʼ
      orgStream.setOfficeCode(org.getOrgInfo().getOfficecode());
      orgStream.setMoneyBank(org.getOrgInfo().getCollectionBankId());
      // ����
      Serializable id = orgFlowDao.insert(orgStream);
      return id;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  /**
   * ����ͷ���id���ҳ�����λ��id
   */
  public String findOrdIdByHeadId(int headId) throws BusinessException {
    try {
      PickHead head = headDao.queryById(new Integer(headId));
      String orgId = head.getOrg().getId().toString();// ����ͷ���id����ȡorgid
      return orgId;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  /**
   * ���ݵ�λ��id�ҳ�����ͷ��״̬Ϊ1��id ��idһ���ܹ�����
   */
  public String findPickHeadSOneByOrgId(int orgId) throws BusinessException {
    String h = headDao.findPickHeadStatueOneByOrgId(orgId).toString();
    return h;// ���dao����һ�����᷵��null..
  }

  /**
   * ����ͷ���id��ɾ��ͷβ��ļ�¼
   */
  public void deleteHeadTailByHeadId(int headId, String ip, String operator,
      SecurityInfo info) throws BusinessException {
    try {
      PickHead head = headDao.queryById(new Integer(headId));
      int isOrgEdition = info.getIsOrgEdition();
      if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// ��λ��
        boolean isNoPickUp = autoInfoPickDAODW.isNOPickUp(head.getOrg().getId()
            .toString(), head.getId().toString(), BusiConst.ORGBUSINESSTYPE_D);
        String stype = autoInfoPickDAODW.findStatus(head.getOrg().getId()
            .toString(), head.getId().toString(), BusiConst.ORGBUSINESSTYPE_D);
        if (stype.equals(BusiConst.OC_PICK_UP)) {
          throw new BusinessException("��������ȡ������ɾ��");
        }
        if (isNoPickUp) {
          throw new BusinessException("���ȳ����ύ���ݣ�");
        }
      } else {// ���İ�
        String center_head_id = "";
        String centerBizDate = "";
        autoInfoPickDAO.deleteupdateAutoInfoPick(BusiConst.OC_NOT_PICK_UP,
            center_head_id, centerBizDate, head.getOrg().getId().toString(),
            head.getId().toString(), BusiConst.ORGBUSINESSTYPE_D);
      }
      List specialPicklist = tailDao.findPickTailBySpecialPick(headId);
      if (specialPicklist != null) {
        for (int i = 0; i < specialPicklist.size(); i++) {
          PickTail p = (PickTail) specialPicklist.get(i);
          SpecialPick special = tailDao.is_findEmployeeBySpecial(headId,
              Integer.parseInt(p.getEmpId().toString()));
          // ��Ա��������ȡ��ʱ��..�����޸Ĵ�Ա����״̬Ϊ1
          special.setIsPick(new BigDecimal(1));
          specialPickDao.updateByIsPick(special);// �޸�

        }
      }

      String orgId = head.getOrg().getId().toString();// ����ͷ���id����ȡorgid

      // ����ط��Ĳ�����Serializable ..�����㲻�ܴ�String�����ܴ���Serializable
      tailDao.deleteTail(new Integer(headId));
      headDao.deleteById_LY(new Integer(headId));
      PickBizActivityLog b = pickBizDao.queryByBizId(headId);
      pickBizDao.delete(b);// ɾ����־
      HafOperateLog haf = new HafOperateLog();// ����BA003����־
      haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// ס��������鼯ϵͳ
      haf.setOpModel(BusiLogConst.OP_MODE_DRAWING_DRAWING_MAINTAIN + "");// ����ģ��
      haf.setOpButton(BusiLogConst.BIZLOG_ACTION_DELETE + "");// ����
      haf.setOpBizId(new Integer(headId));// ȡͷ��ID
      haf.setOpIp(ip);// IP��ַ
      haf.setOpTime(new Date(new java.util.Date().getTime()));// ʱ��
      haf.setOperator(operator);
      haf.setOrgId(new Integer(orgId));
      hafDao.insert(haf);
    } catch (BusinessException be) {
      throw be;
    } catch (Exception s) {
      s.printStackTrace();
    }
  }

  /**
   * ����ƾ֤�ŵķ���.. ��ƾ֤����
   */
  public void insertDocNumCancel(String docNum, String officeCode,
      String bizYearmonth) throws Exception, BusinessException {
    try {// ��һ��������306�����ƾ֤��,�ڶ����ǰ��´����,��������306��sett_dateȡǰ6λ
      if (officeCode != null || bizYearmonth != null || docNum != null) {
        insertDao.insertDocNumCancel(docNum, officeCode, bizYearmonth);
      }
    } catch (Exception e) {
      throw new BusinessException("���ƾ֤��ʧ��!");
    }
  }

  /**
   * ���ƾ֤��
   */
  public void insert(String docNum, String officeCode, String bizYearmonth)
      throws Exception, BusinessException {
    try {// �����ݿ�����õ�......����ƾ֤��ŵķ���
      if (officeCode != null || bizYearmonth != null || docNum != null) {
        insertDao.insertDocNumCancel(docNum, officeCode, bizYearmonth);
      }
    } catch (Exception e) {
      throw new BusinessException("���ƾ֤��ʧ��!");
    }
  }

  /**
   * ����ƾ֤��
   */
  public String getDocNumber(String officeCode, String bizYearmonth,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String docNum = null;
    try {// ����ƾ֤��ŵķ��� ��һ���Ǵ˵�λ���ڵİ��´� �ڶ����ǻ������..�����ǻ������.���Ȼ����������λ
      docNum = maintainDao.getDocNumdocNum(officeCode, bizYearmonth);
      Map office = securityInfo.getOfficeInnerCodeMap();
      String officecode = office.get(officeCode).toString();
      if (officecode.length() == 1) {
        officecode = "0" + officecode;
      }
      docNum = officecode + bizYearmonth + docNum;
    } catch (Exception e) {
      throw new BusinessException("����ƾ֤��ʧ��!");
    }
    return docNum;
  }

  public void setInsertDao(DocNumCancelDAO insertDao) {
    this.insertDao = insertDao;
  }

  public void setMaintainDao(DocNumMaintainDAO maintainDao) {
    this.maintainDao = maintainDao;
  }

  public void setOrgFlowDao(OrgHAFAccountFlowDrawingDAO orgFlowDao) {
    this.orgFlowDao = orgFlowDao;
  }

  public void setEmpFlowDao(EmpHAFAccountFlowDAO empFlowDao) {
    this.empFlowDao = empFlowDao;
  }

  /**
   * ���ĺ� ��ѯְ����ȡ��Ϣ
   */
  public List getEmployeePickInfo(Pagination p) throws BusinessException {
    String empId = (String) p.getQueryCriterions().get("empId");
    String time = (String) p.getQueryCriterions().get("time");
    String reason = (String) p.getQueryCriterions().get("reason");
    String orderBy = p.getOrderBy();
    String order = p.getOrderother();
    int start = p.getFirstElementOnPage() - 1;
    int pageSize = p.getPageSize();
    List list = tailDao.getEmployeePickInfo(empId, time, reason, orderBy,
        order, start, pageSize);
    try {
      for (int i = 0; i < list.size(); i++) {
        PickTail tail = (PickTail) list.get(i);
        if (tail.getPickType().intValue() == 1) {// ������ȡ
          tail.setReason(BusiTools.getBusiValue(
              tail.getPickReason().intValue(), BusiConst.SOMEPICK));
        } else if (tail.getPickType().intValue() == 2) {
          tail.setReason(BusiTools.getBusiValue(
              tail.getPickReason().intValue(), BusiConst.DISTROYPICK));
        }
        tail.setTemp_PickType(BusiTools.getBusiValue(Integer.parseInt(tail
            .getPickType().toString()), BusiConst.PICKUPTYPE));
      }
      int count = tailDao.getEmployeePickInfoCount(empId, time, reason);
      p.setNrOfElements(count);
      return list;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return list;
  }

  public void update(int headId, int orgId, String ip, String operator)
      throws BusinessException {
    HafOperateLog haf = new HafOperateLog();// ����BA003����־
    haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// ס��������鼯ϵͳ
    haf.setOpModel(BusiLogConst.OP_MODE_DRAWING_DRAWING_MAINTAIN + "");// ����ģ��
    haf.setOpButton(BusiLogConst.BIZLOG_ACTION_MODIFY + "");// ����
    haf.setOpBizId(new Integer(headId));// ȡͷ��ID
    haf.setOpIp(ip);// IP��ַ
    haf.setOpTime(new Date(new java.util.Date().getTime()));// ʱ��
    haf.setOperator(operator);
    haf.setOrgId(new Integer(orgId));
    hafDao.insert(haf);
  }

  public NameAF findName(String orgID) throws BusinessException {
    Org org = orgDao.findOrgInfo(orgID);
    String payBank = "";
    String payBankNum = "";
    OrganizationUnit organizationUnit = organizationUnitDAO
        .queryOrganizationUnitListByCriterions(org.getOrgInfo().getOfficecode());
    CollBank collBank = collBankDAO.getCollBankByCollBankid(org.getOrgInfo()
        .getCollectionBankId());
    String organizatinUnitName = organizationUnit.getName();
    String centercollBankName = collBank.getCollBankName();
    String orgName = org.getOrgInfo().getName();

    if (org.getOrgInfo().getPayBank() == null) {
      payBank = " ";
    } else {
      payBank = org.getOrgInfo().getPayBank().getName();// ��н����
    }
    if (org.getOrgInfo().getPayBank() == null) {
      payBankNum = " ";
    } else {
      payBankNum = org.getOrgInfo().getPayBank().getAccountNum();// ��н�����ʺ�
    }
    payBankNum = collBank.getCollectionbankacc();
    NameAF nameAF = new NameAF();
    nameAF.setOrganizatinUnitName(organizatinUnitName);
    nameAF.setCentercollBankName(centercollBankName);
    nameAF.setOrgName(orgName);
    nameAF.setPayBank(payBank);
    nameAF.setPayBankNum(payBankNum);
    return nameAF;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public NameAF findpickTail(String id) throws BusinessException {
    List list = new ArrayList();
    NameAF nameAF = new NameAF();
    BigDecimal money = new BigDecimal(0);
    int idd = new Integer(id).intValue();
    list = tailDao.findDataByHeadId(idd);
    if (list.size() != 0) {
      for (int i = 0; i < list.size(); i++) {
        PickTail pickTail = (PickTail) list.get(i);
        BigDecimal pickPre = pickTail.getPickPreBalance();
        BigDecimal pickCur = pickTail.getPickCurBalance();
        BigDecimal preInt = pickTail.getPickPreInterest();
        BigDecimal curInt = pickTail.getPickCurInterest();
        if (pickPre != null || !pickPre.equals("")) {
          money = money.add(pickPre);
        }
        if (pickCur != null || !pickCur.equals("")) {
          money = money.add(pickCur);
        }
        if (preInt != null || !preInt.equals("")) {
          money = money.add(preInt);
        }
        if (curInt != null || !curInt.equals("")) {
          money = money.add(curInt);
        }
      }
    }
    nameAF.setMoney(money);
    return nameAF;
  }

  // ���ݵ�λid��314�������޴˵�λ
  public boolean check(String orgID, SecurityInfo info) {
    boolean b = false;
    List a = adjustWrongAccountHeadDAO.queryByIDGJP(orgID, info);
    if (a.size() > 0) {
      return true;
    }
    return b;
  }

  // ���ݵ�λid��309�������޴˵�λ
  public boolean checkTranOut(String orgID) {
    boolean b = false;
    List a = headDao.queryTranOutHeadByOrgid(orgID);
    if (a.size() > 0) {
      return true;
    }
    return b;
  }

  public void setPartPickupConditionDAO(
      PartPickupConditionDAO partPickupConditionDAO) {
    this.partPickupConditionDAO = partPickupConditionDAO;
  }

  public void referringData(String headId, SecurityInfo securityInfo,
      String temp_p) throws BusinessException {

    PickHead head = headDao.queryById(new Integer(headId));// ���ݵõ���ͷ�������Ե�������orgID
    boolean flag = autoInfoPickDAODW.isNOPickIn(head.getOrg().getId()
        .toString(), head.getId().toString(), BusiConst.ORGBUSINESSTYPE_D);
    try {
      if (flag) {
        throw new BusinessException("�ñ�ҵ�����ύ");
      } else {
        String ip = securityInfo.getUserInfo().getUserIp();
        String name = securityInfo.getUserInfo().getUsername();
        AutoInfoPick autoInfoPick = new AutoInfoPick();
        autoInfoPick.setOrgId(new Integer(head.getOrg().getId().toString()));
        autoInfoPick.setOrgHeadId(new Integer(head.getId().toString()));
        autoInfoPick.setType(BusiConst.ORGBUSINESSTYPE_D);
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
        if (temp_p.equals("1")) {
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO));// ����
        } else {
          hafOperateLog.setOpModel(Integer
              .toString(BusiLogConst.OP_MODE_DRAWING_DRAWING_MAINTAIN));// ά��
        }
        hafOperateLog.setOpButton(Integer
            .toString(BusiLogConst.BIZLOG_ACTION_REFERRINGDATE));
        hafOperateLog.setOpBizId(new Integer(head.getId().toString()));// AA306.ID
        hafOperateLog.setOperator(name);
        hafOperateLog.setOpIp(ip);
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOrgId(new Integer(head.getOrg().getId().toString()));
        hafOperateLogDAO.insert(hafOperateLog);
      }

    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }
  }

  public void pprovalData(String headId, SecurityInfo securityInfo,
      String temp_p) throws BusinessException {
    PickHead head = headDao.queryById(new Integer(headId));// ���ݵõ���ͷ�������Ե�������orgID
    // boolean flag=
    // autoInfoPickDAODW.isNOPickIn(head.getOrg().getId().toString(),head.getId().toString(),BusiConst.ORGBUSINESSTYPE_D);
    try {
      // if(!flag){
      String status = autoInfoPickDAODW.findStatus(head.getOrg().getId()
          .toString(), head.getId().toString(), BusiConst.ORGBUSINESSTYPE_D);
      if (status.equals(BusiConst.OC_PICK_UP)) {
        throw new BusinessException("��ҵ���ѱ�������ȡ�������Գ�����");
      }
      if (status.equals(BusiConst.OC_PICK_UP_OVER)) {
        throw new BusinessException("�ñ�ҵ��û���ύ�������Գ�����");
      }
      // }else{
      String ip = securityInfo.getUserInfo().getUserIp();
      String name = securityInfo.getUserInfo().getUsername();
      autoInfoPickDAODW.deleteAutoInfoPick(head.getOrg().getId().toString(),
          head.getId().toString(), BusiConst.ORGBUSINESSTYPE_D);
      // ����BA003��
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog
          .setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      if (temp_p.equals("1")) {
        hafOperateLog.setOpModel(Integer
            .toString(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO));// ����
      } else {
        hafOperateLog.setOpModel(Integer
            .toString(BusiLogConst.OP_MODE_DRAWING_DRAWING_MAINTAIN));// ά��
      }
      hafOperateLog.setOpButton(Integer
          .toString(BusiLogConst.BIZLOG_ACTION_PPROVALDATA));
      hafOperateLog.setOpBizId(new Integer(head.getId().toString()));// AA306.ID
      hafOperateLog.setOperator(name);
      hafOperateLog.setOpIp(ip);
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOrgId(new Integer(head.getOrg().getId().toString()));
      hafOperateLogDAO.insert(hafOperateLog);
      // }

    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }
  }

  public void pickUpData(String headId, SecurityInfo securityInfo)
      throws BusinessException {
    try {
      String minid = autoInfoPickDAO.findOrgHeadid(headId,
          BusiConst.ORGBUSINESSTYPE_D, BusiConst.OC_NOT_PICK_UP);// ��С��ͷ��id
      if (minid == null || "".equals(minid)) {
        throw new BusinessException("�õ�λ������δ��ȡ�ļ�¼");
      } else {
        PickHead head = pickupheadDaoDW.queryById(new Integer(minid));// ���ݵõ���ͷ�������Ե�������orgID
        List list = tailDaoDW.findPickTailList(minid);// �ڵ�λ�����ݿ��У�����ȡ����ORG_HEAD_ID��ѯAA306��AA307��ȡ���ļ�¼
        List headlist = new ArrayList();
        PickHeadImportDTO dtoo1 = new PickHeadImportDTO();
        PickHeadImportDTO dtoo2 = new PickHeadImportDTO();
        dtoo2.setOrgId(head.getOrg().getId().toString());
        dtoo2.setOrgNoteNumber(head.getNoteNum());
        headlist.add(dtoo1);
        headlist.add(dtoo2);
        this.getBatchErrorData(headlist, list, securityInfo, "", "");
        String bizdate = "1";
        autoInfoPickDAO.updateAutoInfoPick(BusiConst.OC_PICK_UP, temp_headid,
            bizdate, head.getOrg().getId().toString(), head.getId().toString(),
            BusiConst.ORGBUSINESSTYPE_D);
        // ����BA003��
        String ip = securityInfo.getUserInfo().getUserIp();
        String name = securityInfo.getUserInfo().getUsername();
        HafOperateLog hafOperateLog = new HafOperateLog();
        hafOperateLog.setOpSys(new Integer(
            BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
        hafOperateLog.setOpModel(Integer
            .toString(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO));// ����
        hafOperateLog.setOpButton(Integer
            .toString(BusiLogConst.BIZLOG_ACTION_PICKUPDATA));
        hafOperateLog.setOpBizId(new Integer(temp_headid));// AA306.ID
        hafOperateLog.setOperator(name);
        hafOperateLog.setOpIp(ip);
        hafOperateLog.setOpTime(new Date());
        hafOperateLog.setOrgId(new Integer(head.getOrg().getId().toString()));
        hafOperateLogDAO.insert(hafOperateLog);
      }
    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }
  }

  public void setEmpDAODW(EmpDAODW empDAODW) {
    this.empDAODW = empDAODW;
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
      org = orgDao.queryByCriterions(orgid, "2", null, securityInfo);
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

  public List getExportData(final int orgId, String ip, String operator,
      Pagination pagination) {
    // ���������־...
    List returnlist = new ArrayList();
    String orderArray[] = (String[]) pagination.getQueryCriterions().get(
        "orderArray");

    HafOperateLog haf = new HafOperateLog();
    // OPA_IDΪAA001�в����¼��ID
    // OP_TIMEΪϵͳʱ��
    // OPERATORΪ�������Ĳ���Ա
    haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// ס��������鼯ϵͳ
    haf.setOpModel(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO + "");// ����ģ��
    haf.setOpButton(BusiLogConst.BIZLOG_ACTION_EXP + "");// ����
    haf.setOpBizId(new Integer(0));// ȡͷ��ID
    haf.setOpIp(ip);// IP��ַ
    haf.setOpTime(new Date(new java.util.Date().getTime()));// ʱ��
    haf.setOperator(operator);
    haf.setOrgId(new Integer(orgId));
    hafDao.insert(haf);
    PartPickupConditionDTO partPickupConditionDTO = new PartPickupConditionDTO();
    try {
      partPickupConditionDTO = partPickupConditionDAO
          .queryPartPickupConditionInfo();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    List templist = empDao.getExportData(orgId, orderArray);
    for (int i = 0; i < templist.size(); i++) {
      Emp emp = (Emp) templist.get(i);
      emp.setMul(partPickupConditionDTO.getMultiple());
      returnlist.add(emp);
    }
    return returnlist;
  }

  public String findversionflag(Pagination pagination, PickHead p,
      SecurityInfo securityInfo) throws BusinessException {
    // TODO Auto-generated method stub
    String flag = "0";

    // �ж��Ƿ���ڵ�λ��
    int IsHaveOrgVersion = securityInfo.getIsHaveOrgVersion();
    if (IsHaveOrgVersion == Integer.parseInt(BusiConst.IS_HAVE)) {// ���ڵ�λ��
      int isCentEdition = securityInfo.getIsOrgEdition();
      // �ж��Ƿ�Ϊ���İ�
      if (isCentEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// ��λ��
        String orgid = (String) pagination.getQueryCriterions().get("idValue")
            .toString();
        flag = autoInfoPickDAODW.findStatus(orgid, p.getId().toString(),
            BusiConst.ORGBUSINESSTYPE_D);
      }
    }
    return flag;
  }

  public void setBizActivityLogDAO(BizActivityLogDAO bizActivityLogDAO) {
    this.bizActivityLogDAO = bizActivityLogDAO;
  }

  public CollParaDAO getCollParaDAO() {
    return collParaDAO;
  }

  public void setCollParaDAO(CollParaDAO collParaDAO) {
    this.collParaDAO = collParaDAO;
  }

  public boolean updatePickHead(String id, String empId, String path)
      throws Exception {
    boolean flag = false;
    try {
      PickTail tail = tailDao.findPickTailByHeadIdAndEmpId(new Integer(id)
          .intValue(), new Integer(empId).intValue());
      // PickHead pickhead = headDao.queryById(new Integer(id));// ��ѯ�����Ϣ
      if (tail != null) {
        tail.setPhotourl(path);
        flag = true;
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return flag;
  }

  public List checkpicker(String card_num, String card_num_two)
      throws Exception {
    List flaglist = new ArrayList();
    try {
      flaglist = tailDao.checkperson(card_num, card_num_two);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return flaglist;
  }

  public String checkpicker_yg(String card_num, String card_num_two)
      throws Exception {
    String count = "";
    try {
      count = tailDao.checkperson_yg(card_num, card_num_two);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  // ����� 2008-6-16//��ѯ����������
  public String queryCollectionBankNameById(String id, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    CollBank collBank = new CollBank();
    try {
      // ��λ�Ƿ����
      Org org = null;
      org = orgDao.queryByCriterions(id, null, null, securityInfo);

      if (org == null) {
        throw new BusinessException(" �����ڸõ�λ��λ����Ȩ�޷�Χ֮�ڣ���");
      }
      String collectionBankId = org.getOrgInfo().getCollectionBankId();
      if (collectionBankId != null && !collectionBankId.equals("")) {
        collBank = empDao.getCollBankByCollBankid(collectionBankId);
      }
      if (collBank == null) {
        collBank = new CollBank();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return collBank.getCollBankName();
  }

  // ����ͷ���id��ѯβ���ְ��
  public List querytailbyheadid(String id, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List returnlist = new ArrayList();
    try {
      returnlist = tailDao.getTailbyHeadid(new Integer(id).intValue());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return returnlist;

  }

  public String queryNoteNum() throws Exception, BusinessException {

    String o = "";
    try {
      o = orgDao.queryNoteNum();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return o;

  }

  public String findAA103_DayTime(final String collbankid) throws Exception,
      BusinessException {
    String o = "";
    try {
      o = orgDao.findAA103_DayTime(collbankid);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return o;

  }

  public String find_empid_card_num(final String orgid, final String empid)
      throws Exception, BusinessException {
    String o = "";
    try {
      o = orgDao.find_empid_card_num(orgid, empid);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return o;
  }

  public int getpickup_pl(final int orgId) throws Exception, BusinessException {
    int o = 0;
    try {
      o = tailDao.getpickup_pl(orgId);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return o;
  }

  public int getpickup_not_pl(final int orgId) throws Exception,
      BusinessException {
    int o = 0;
    try {
      o = tailDao.getpickup_not_pl(orgId);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return o;
  }

  public String getpickup_oper(final String id) throws Exception,
      BusinessException {
    String o = "";
    try {
      o = tailDao.getpickup_oper(id);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return o;
  }

  public String getpickup_check(final String id) throws Exception,
      BusinessException {
    String o = "";
    try {
      o = tailDao.getpickup_check(id);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return o;
  }

  public String find_user_realname(final String user) throws Exception,
      BusinessException {
    String o = "";
    try {
      o = orgDao.find_user_realname(user);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return o + "";
  }

  public void updateAA306_1(final String reason) throws Exception,
      BusinessException {
    try {
      empDao.deleteAA306_1();
      empDao.insertIntoAA306_1(reason);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getAA306_1() throws Exception, BusinessException {
    return empDao.getAA306_1();
  }

  public void deleteAA306_1() throws Exception, BusinessException {
    empDao.deleteAA306_1();
  }

  public List getpickup_not_aa306(final int orgId) throws Exception,
      BusinessException {
    List list = null;
    try {
      list = tailDao.getpickup_not_aa306(orgId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List getPickCheckList(Pagination pagination, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List list = null;
    String orgid = (String) pagination.getQueryCriterions().get("orgid");
    String orgname = (String) pagination.getQueryCriterions().get("orgname");
    String begdate = (String) pagination.getQueryCriterions().get("begdate");
    String enddate = (String) pagination.getQueryCriterions().get("enddate");
    String checkbegdate = (String) pagination.getQueryCriterions().get(
        "checkbegdate");
    String checkenddate = (String) pagination.getQueryCriterions().get(
        "checkenddate");
    String ischecked = (String) pagination.getQueryCriterions()
        .get("ischecked");
    try {
      list = tailDao.getPickCheckList(orgid, orgname, begdate, enddate,
          checkbegdate, checkenddate, ischecked);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public void setPickCheckStatus(String date, String type, String id)
      throws Exception, BusinessException {
    tailDao.setPickCheckStatus(date, type, id);
  }

}
