package org.xpup.hafmis.syscollection.pickupmng.specialpickup.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.AdjustWrongAccountHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.SpecialPickDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.SpecialPick;
import org.xpup.hafmis.syscollection.pickupmng.specialpickup.bsinterface.ISpePickBS;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;


public class SpePickBS implements ISpePickBS{
  protected OrgDAO orgDAO = null;
  protected EmpDAO empDAO = null;
  protected SpecialPickDAO specialPickDAO = null;
  protected HafOperateLogDAO hafOperateLogDAO = null;
  protected AdjustWrongAccountHeadDAO adjustWrongAccountHeadDAO=null; 
public void setSpecialPickDAO(SpecialPickDAO specialPickDAO) {
    this.specialPickDAO = specialPickDAO;
  }
public Org findOrgSpePickById (Integer id) throws BusinessException {
  Org org=orgDAO.queryById(id);
  return org;
  
}
/**
 * AJAXAction���õ���ѯ��λ
 */
public Org queryOrgById(Integer id,SecurityInfo securityInfo) throws Exception,
    BusinessException {
  try{
    Org org = null;
    org= orgDAO.queryByCriterions(id.toString(), null, null, securityInfo);

    if (org == null) {
      throw new BusinessException(" �����ڸõ�λ��λ����Ȩ�޷�Χ֮�ڣ���");
    }
    org = orgDAO.findById(id,securityInfo);

    return org;
  }catch(BusinessException be){
    throw be;
  }


}
public Emp findSpeEmp(String orgID,String empID)throws BusinessException{
  Emp emp = empDAO.getChgPersonEmp_WL(orgID, empID);
  if(emp!=null) {
    try {
      emp.getEmpInfo().setTEMP_cardKind(BusiTools.getBusiValue(Integer.parseInt(emp.getEmpInfo().getCardKind().toString()), BusiConst.DOCUMENTSSTATE));
    } catch (NumberFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  } 
  return emp;
  
}
public SpecialPick findSpePickMoney(String orgId,String empId)throws BusinessException{
  SpecialPick specialPick = specialPickDAO.querySpecialPick(Integer.parseInt(orgId), Integer.parseInt(empId));
  return specialPick;
}
//��ȡ״̬Ϊδʹ��ʱ
public void updateOrgOpenAccount(SpecialPick formSpecialPick, SecurityInfo securityInfo)throws BusinessException{
  String oper = securityInfo.getUserInfo().getUsername();
  String ip = securityInfo.getUserInfo().getUserIp();
  String bizDate=securityInfo.getUserInfo().getBizDate();
    String orgid=formSpecialPick.getOrg().getId().toString();
    String empid=formSpecialPick.getEmp().getEmpId().toString();
    SpecialPick specialPick=specialPickDAO.querySpecialPick(Integer.parseInt(orgid), Integer.parseInt(empid));
    specialPick.setPickCorpus(formSpecialPick.getPickCorpus());
    specialPick.setIsPick(new BigDecimal(1));
    specialPick.setOperator(oper);
    try {
      specialPick.setOperateTime(BusiTools.stringToUDate(bizDate, BusiConst.PUB_LONG_YMD_PATTERN));
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    HafOperateLog hafOperateLog =new HafOperateLog();
//  ����BA003
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(""+BusiLogConst.OP_MODE_DRAWING_SPECIALDRAWING_DO);
    hafOperateLog.setOpButton(""+BusiLogConst.BIZLOG_ACTION_MODIFY);
    hafOperateLog.setOpBizId(new Integer(specialPick.getId().toString()));
    hafOperateLog.setOpIp(ip);
    hafOperateLog.setOrgId(new Integer(formSpecialPick.getOrg().getId().toString()));
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOperator(oper);
    hafOperateLogDAO.insert(hafOperateLog);
}
//��ȡ״̬Ϊ��ʹ��
public void saveOrgOpenAccount(SpecialPick formSpecialPick, SecurityInfo securityInfo)throws BusinessException{
  String oper = securityInfo.getUserInfo().getUsername();
  String ip = securityInfo.getUserInfo().getUserIp();
  String bizDate=securityInfo.getUserInfo().getBizDate();
  SpecialPick specialPick =new SpecialPick();
    Org org = orgDAO.getOrg_WL(formSpecialPick.getOrg().getId().toString());
    specialPick.setOrg(org);
    specialPick.setEmpId(formSpecialPick.getEmp().getEmpId());  
    specialPick.setPickCorpus(formSpecialPick.getPickCorpus());
    specialPick.setIsPick(new BigDecimal(1));
    specialPick.setOperator(oper);
    try {
      specialPick.setOperateTime(BusiTools.stringToUDate(bizDate, BusiConst.PUB_LONG_YMD_PATTERN));
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Serializable id = specialPickDAO.insert(specialPick);
    HafOperateLog hafOperateLog =new HafOperateLog();
    //����BA003
    if(id!=null&&!id.equals("")){
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(""+BusiLogConst.OP_MODE_DRAWING_SPECIALDRAWING_DO);
    hafOperateLog.setOpButton(""+BusiLogConst.BIZLOG_ACTION_ADD);
    hafOperateLog.setOpBizId(new Integer(id.toString()));
    hafOperateLog.setOpIp(ip);
    hafOperateLog.setOrgId(new Integer(formSpecialPick.getOrg().getId().toString()));
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOperator(oper);
    hafOperateLogDAO.insert(hafOperateLog);

  }
  
}
//Ϊ��ҳ�������
public SpecialPick findEmpSpePick(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException{
  List list = null;
  double pcs=0.00;
  SpecialPick specialPick=new SpecialPick();
  String id = (String) pagination.getQueryCriterions().get("id");     
  String name = (String) pagination.getQueryCriterions().get("name");
  String officecode=(String) pagination.getQueryCriterions().get("officecode");
  String collectionBank=(String) pagination.getQueryCriterions().get("collectionBank");
  String operateTime1=(String) pagination.getQueryCriterions().get("operateTime1");
  String operateTime2=(String) pagination.getQueryCriterions().get("operateTime2");
  String orderBy = (String) pagination.getOrderBy();
  String order = (String) pagination.getOrderother();
  int start = pagination.getFirstElementOnPage() - 1;
  int pageSize = pagination.getPageSize();
  list = specialPickDAO.queryCountByCriterionsSL(id, name, officecode, collectionBank, operateTime1, operateTime2, orderBy, order, start, pageSize,securityInfo);  
  if(list!=null){
    for( int i=0;i<list.size();i++){
        specialPick = (SpecialPick)list.get(i);
        pcs=pcs+specialPick.getPickCorpusSum().doubleValue();
    }
  }
  specialPick.setPickCorpusSum(new BigDecimal(pcs));
  specialPick.setPickPeopleNum(new Integer(list.size()));
  return specialPick;
}

public List findEmpSpePickList(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException{
  List list = null;
  String id = (String) pagination.getQueryCriterions().get("id");     
  String name = (String) pagination.getQueryCriterions().get("name");
  String officecode=(String) pagination.getQueryCriterions().get("officecode");
  String collectionBank=(String) pagination.getQueryCriterions().get("collectionBank");
  String operateTime1=(String) pagination.getQueryCriterions().get("operateTime1");
  String operateTime2=(String) pagination.getQueryCriterions().get("operateTime2");
  String orderBy = (String) pagination.getOrderBy();
  String order = (String) pagination.getOrderother();
  int start = pagination.getFirstElementOnPage() - 1;
  int pageSize = pagination.getPageSize();
  list = specialPickDAO.queryByCriterionsSL(id, name, officecode, collectionBank, operateTime1, operateTime2, orderBy, order, start, pageSize,securityInfo);  
  List returnlist=new ArrayList();
  if(list!=null){
    for( int i=0;i<list.size();i++){
      SpecialPick specialPick = (SpecialPick)list.get(i);
      Emp e=empDAO.findEmpByOrdIdAndEmpId(new Integer(specialPick.getOrg().getId().toString()), specialPick.getEmpId());
      specialPick.setEmp(e);
      specialPick.getOrg().getOrgInfo().setTemp_officename(specialPickDAO.findOfficeNameByOrgInfoOfficeCode(specialPick.getOrg().getOrgInfo().getOfficecode()));
      specialPick.getOrg().getOrgInfo().setTemp_collectionBankname(specialPickDAO.findCollBanknameByOrgInfoCollectionBankId(specialPick.getOrg().getOrgInfo().getCollectionBankId()));
      specialPick.setTemp_operateTime(BusiTools.dateToString(specialPick.getOperateTime(), BusiConst.PUB_LONG_YMD_PATTERN));
      specialPick.setTemp_isPick(BusiTools.getBusiValue(Integer.parseInt(specialPick.getIsPick().toString()), BusiConst.PICKSTATUS));
      returnlist.add(specialPick);
    }
  }
  int count = specialPickDAO.queryCountByCriterionsSL(id, name, officecode, collectionBank, operateTime1, operateTime2, orderBy, order, start, pageSize,securityInfo).size();
  pagination.setNrOfElements(count);
  return returnlist;
}
/**
 * ��ѡɾ��
 */
public void deleteSpePick(Integer id, SecurityInfo securityInfo) throws BusinessException{
  String isPick="";
  try{
    SpecialPick specialPick=specialPickDAO.queryById(id);
    if(specialPick.getIsPick().toString().equals("2")){
      isPick="2";
      throw new BusinessException("��ȡ״̬Ϊ��ʹ�õ�������ȡ�޷�ɾ��!");
    }
    specialPickDAO.delete(specialPick);
    HafOperateLog hafOperateLog = new HafOperateLog();
    // ������־BA003
    String ip = securityInfo.getUserInfo().getUserIp();
    String oper = securityInfo.getUserInfo().getUsername();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_DRAWING_SPECIALDRAWING_MAINTAIN);
    hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_DELETE);
    hafOperateLog.setOpBizId(new Integer(id.toString()));
    hafOperateLog.setOpIp(ip);
    hafOperateLog.setOrgId(new Integer(specialPick.getOrg().getId().toString()));
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOperator(oper);
    hafOperateLogDAO.insert(hafOperateLog);
  }catch(Exception e){
    if(isPick.equals("2")){
      throw new BusinessException("��ȡ״̬Ϊ��ʹ�õ�������ȡ�޷�ɾ��!");
    }else{
    throw new BusinessException("ɾ��ʧ��!");
    }
  }
}
//���ݵ�λid��314�������޴˵�λ
public boolean check(String orgID,SecurityInfo info) {
  boolean b=false;
  List a=adjustWrongAccountHeadDAO.queryByIDGJP(orgID, info);
  if(a .size()>0){
    return true;
  }
  return b;
}
//���ݵ�λid��309�������޴˵�λ
public boolean checkTranOut(String orgID) {
  boolean b=false;
  List a=specialPickDAO.queryTranOutHeadByOrgid(orgID);
  if(a.size() > 0){
    return true;
  }
  return b;
}
//��ѯ��ְ���Ƿ����δ���˵���ȡҵ��
public List isClearAccount(String orgID,String empID) throws Exception {
  List list=new ArrayList();
  try{
    list=specialPickDAO.isClearAccount(orgID, empID);
    if(list.size()>0){
      throw new BusinessException("��ְ������δ���˵���ȡҵ��");
    }
  }catch(BusinessException bex){
    throw bex;
  }catch(Exception e){
    e.printStackTrace();
  }
  return list;
}
public void saveSpePick(){
  
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
public AdjustWrongAccountHeadDAO getAdjustWrongAccountHeadDAO() {
  return adjustWrongAccountHeadDAO;
}
public void setAdjustWrongAccountHeadDAO(
    AdjustWrongAccountHeadDAO adjustWrongAccountHeadDAO) {
  this.adjustWrongAccountHeadDAO = adjustWrongAccountHeadDAO;
}
}
