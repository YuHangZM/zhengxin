package org.xpup.hafmis.sysloan.dataready.assistantorg.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.AssistantOrgDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantOrg;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.dataready.assistantorg.bsinterface.IAssistantorgBS;
import org.xpup.hafmis.sysloan.dataready.assistantorg.dto.AssistantOrgDTO;
import org.xpup.hafmis.sysloan.dataready.assistantorg.dto.AssistantorgAFDTO;

public class AssistantorgBS implements IAssistantorgBS {
  private AssistantOrgDAO assistantOrgDAO = null; // pl007

  private OrganizationUnitDAO organizationUnitDAO = null;

  private PlOperateLogDAO plOperateLogDAO = null;

  /**
   * name ���� ������˾ά��-��ʾ�б�
   */
  public List findAssistantorgList(Pagination pagination) {
    List list = null;
    List listAF = new ArrayList();
    try {
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list = assistantOrgDAO.findAssistantOrgs_YM(orderBy, order, start,
          pageSize, page);
      if (list.size() != 0 || list != null) {
        for (int i = 0; i < list.size(); i++) {
          AssistantorgAFDTO aAF = new AssistantorgAFDTO(); // ���� AF
          AssistantOrgDTO ao = (AssistantOrgDTO) list.get(i); // DAO ����ʵ��list
          aAF.setAssistantOrgName(ao.getAssistantOrgName()); // ��λ����
          aAF.setAssistantOrgAddr(ao.getAssistantOrgAddr()); // ��λ��ַ

          // �����޸� ��ͷ��
          if (ao.getArear() == null || ao.getArear().equals("")) {
            aAF.setArear(ao.getArear());
          } else {
            aAF.setArear(BusiTools.getBusiValue(
                Integer.parseInt(ao.getArear()), BusiConst.INAREA)); // ��������
          }
          // ���иģ�β��

          aAF.setPaybank(ao.getPaybank()); // ��������
          aAF.setContactPrsn(ao.getContactPrsn());// ��ϵ��
          aAF.setContactTel(ao.getContactTel()); // ��ϵ�绰
          aAF.setAssistantOrgId(ao.getId().toString()); // Э����λ���
          listAF.add(aAF);
        }
      }
    
    int count = assistantOrgDAO.findAssistantOrgTypeCountA_YM();
    pagination.setNrOfElements(count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return listAF;
  }

  /**
   * name ���� ������˾ά�� �����¼�¼  ��**���и�**��
   */
  public void insertAssistantorgList(AssistantorgAFDTO assistantorgDTO,
      SecurityInfo securityInfo) {
    try {
      AssistantOrg aog = new AssistantOrg(); // ������ʵ��
      aog.setAssistantOrgName(assistantorgDTO.getAssistantOrgName().trim());// ������˾����
      aog.setArtfclprsn(assistantorgDTO.getArtfclprsn().trim());// ���˴���
      aog.setCode(assistantorgDTO.getCode().trim());// ��֯����
      aog.setAssistantOrgAddr(assistantorgDTO.getAssistantOrgAddr().trim());// ������˾��ַ
      aog.setBasedDate(assistantorgDTO.getBasedDate().trim());// ��������
      aog.setArtfclprsnCardKind(assistantorgDTO.getArtfclprsnCardKind().trim());// ����֤������
      aog.setArtfclprsnCardNum(assistantorgDTO.getArtfclprsnCardNum().trim());// ����֤������
      aog.setAllowDept(assistantorgDTO.getAllowDept().trim());// ��׼����
      aog.setAllowId(assistantorgDTO.getAllowId().trim());// ��׼�ĺ�
      aog.setAgreementStartDate(assistantorgDTO.getAgreementStartDate().trim());// Э��ǩ������
      aog.setAgreementEndDate(assistantorgDTO.getAgreementEndDate().trim());// Э�鵽������
      aog.setPaybank(assistantorgDTO.getPaybank().trim());// ��������
      aog.setPayacc(assistantorgDTO.getPayacc().trim());// �������ʺ�
      aog.setContactPrsn(assistantorgDTO.getContactPrsn().trim());// ��ϵ��
      aog.setContactTel(assistantorgDTO.getContactTel().trim());// ��ϵ�绰
      aog.setArear(assistantorgDTO.getArear().trim());// ��������
      aog.setBusiness(assistantorgDTO.getBusiness());// ְ��
      aog.setRegisterFund(assistantorgDTO.getRegisterFund());// ע���ʽ�
      aog.setRemark(assistantorgDTO.getRemark());// ��ע
      aog.setAssistantOrgType("A");
      String id = assistantOrgDAO.insert(aog).toString();
      PlOperateLog plOperateLog = new PlOperateLog();// ������־����
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ����ϵͳ
      plOperateLog.setOpModel(String
          .valueOf(BusiLogConst.PL_OP_DATAPREPARATION_GUARANTEECORP));// ����ά��
      plOperateLog.setOpButton("1");
      plOperateLog.setOpBizId(new BigDecimal(id));
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLogDAO.insert(plOperateLog);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  /**
   * name ���� ������˾ά�� ͨ��id��ѯ���������޸�
   */
  public AssistantorgAFDTO findAssistantorgID(Integer id) {
    AssistantorgAFDTO AF = new AssistantorgAFDTO();
    try {
      AssistantOrg aog = assistantOrgDAO.queryById(id);
      AF.setAssistantOrgId(aog.getAssistantOrgId().toString());
      if (aog.getAssistantOrgName() != null)
        AF.setAssistantOrgName(aog.getAssistantOrgName());// ������˾����
      if (aog.getArtfclprsn() != null)
        AF.setArtfclprsn(aog.getArtfclprsn());// ���˴���
      if (aog.getCode() != null)
        AF.setCode(aog.getCode());// ��֯����
      if (aog.getAssistantOrgAddr() != null)
        AF.setAssistantOrgAddr(aog.getAssistantOrgAddr());// ������˾��ַ
      if (aog.getBasedDate() != null)
        AF.setBasedDate(aog.getBasedDate());// ��������
      if (aog.getArtfclprsnCardKind() != null)
        AF.setArtfclprsnCardKind(aog.getArtfclprsnCardKind());// ����֤������
      if (aog.getArtfclprsnCardNum() != null)
        AF.setArtfclprsnCardNum(aog.getArtfclprsnCardNum());// ����֤������
      if (aog.getAllowDept() != null)
        AF.setAllowDept(aog.getAllowDept());// ��׼����
      if (aog.getAllowId() != null)
        AF.setAllowId(aog.getAllowId());// ��׼�ĺ�
      if (aog.getAgreementStartDate() != null)
        AF.setAgreementStartDate(aog.getAgreementStartDate());// Э��ǩ������
      if (aog.getAgreementEndDate() != null)
        AF.setAgreementEndDate(aog.getAgreementEndDate());// Э�鵽������
      if (aog.getPaybank() != null)
        AF.setPaybank(aog.getPaybank());// ��������
      if (aog.getPayacc() != null)
        AF.setPayacc(aog.getPayacc());// �������ʺ�
      if (aog.getContactPrsn() != null)
        AF.setContactPrsn(aog.getContactPrsn());// ��ϵ��
      if (aog.getContactTel() != null)
        AF.setContactTel(aog.getContactTel());// ��ϵ�绰
      if (aog.getArear() != null)
        AF.setArear(aog.getArear());// ��������
      if (aog.getBusiness() != null)
        AF.setBusiness(aog.getBusiness());// ְ��
      if (aog.getRegisterFund() != null)
        AF.setRegisterFund(aog.getRegisterFund());// ע���ʽ�
      if (aog.getRemark() != null)
        AF.setRemark(aog.getRemark());// ��ע
      if (aog.getAssistantOrgType() != null)
        AF.setAssistantOrgType(aog.getAssistantOrgType());

    } catch (Exception e) {
      e.printStackTrace();
    }
    return AF;
  }

  /**
   * name ���� ������˾ά��,�޸�����   ��**���и�**��
   */
  public void updateAssistantOrg(AssistantorgAFDTO assistantorgDTO, SecurityInfo securityInfo) {
    AssistantOrg aog = null;
    try {
      aog = assistantOrgDAO.queryById(new Integer(assistantorgDTO.getAssistantOrgId()));
      aog.setAssistantOrgName(assistantorgDTO.getAssistantOrgName().trim());// ������˾����
      aog.setArtfclprsn(assistantorgDTO.getArtfclprsn().trim());// ���˴���
      aog.setCode(assistantorgDTO.getCode().trim());// ��֯����
      aog.setAssistantOrgAddr(assistantorgDTO.getAssistantOrgAddr().trim());// ������˾��ַ
      aog.setBasedDate(assistantorgDTO.getBasedDate().trim());// ��������
      aog.setArtfclprsnCardKind(assistantorgDTO.getArtfclprsnCardKind().trim());// ����֤������
      aog.setArtfclprsnCardNum(assistantorgDTO.getArtfclprsnCardNum().trim());// ����֤������
      aog.setAllowDept(assistantorgDTO.getAllowDept().trim());// ��׼����
      aog.setAllowId(assistantorgDTO.getAllowId().trim());// ��׼�ĺ�
      aog.setAgreementStartDate(assistantorgDTO.getAgreementStartDate().trim());// Э��ǩ������
      aog.setAgreementEndDate(assistantorgDTO.getAgreementEndDate().trim());// Э�鵽������
      aog.setPaybank(assistantorgDTO.getPaybank().trim());// ��������
      aog.setPayacc(assistantorgDTO.getPayacc().trim());// �������ʺ�
      aog.setContactPrsn(assistantorgDTO.getContactPrsn().trim());// ��ϵ��
      aog.setContactTel(assistantorgDTO.getContactTel().trim());// ��ϵ�绰
      aog.setArear(assistantorgDTO.getArear().trim());// ��������
      aog.setBusiness(assistantorgDTO.getBusiness());// ְ��
      aog.setRegisterFund(assistantorgDTO.getRegisterFund());// ע���ʽ�
      aog.setRemark(assistantorgDTO.getRemark());// ��ע
      aog.setAssistantOrgType(assistantorgDTO.getAssistantOrgType());
      String id = aog.getAssistantOrgId().toString();
      PlOperateLog plOperateLog = new PlOperateLog();// ������־����
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ����ϵͳ
      plOperateLog.setOpModel(String
          .valueOf(BusiLogConst.PL_OP_DATAPREPARATION_GUARANTEECORP));// ����ά��
      plOperateLog.setOpButton("2");
      plOperateLog.setOpBizId(new BigDecimal(id));
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLogDAO.insert(plOperateLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * name ���� ����idɾ��PL007����
   */
  public String deleteAssistantOrg(Integer id, SecurityInfo securityInfo) {
    String error = "ɾ���ɹ�!";
    try {
      assistantOrgDAO.delete_YM(id);
      PlOperateLog plOperateLog = new PlOperateLog();// ������־����
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ����ϵͳ
      plOperateLog.setOpModel(String
          .valueOf(BusiLogConst.PL_OP_DATAPREPARATION_GUARANTEECORP));// ����ά��
      plOperateLog.setOpButton("3");
      plOperateLog.setOpBizId(new BigDecimal(id.toString()));
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLogDAO.insert(plOperateLog);
    } catch (Exception e) {
      error = "ɾ��ʧ��!";
      e.printStackTrace();
    }
    return error;
  }

  /**
   * name ���� ����id �ж��Ƿ��м�¼ true �д˼�¼ false �޴˼�¼
   */
  public boolean is_asistantOrg_YM(Integer id) {
    boolean is_bank = false;
    try {
      AssistantOrg AssistantOrg = assistantOrgDAO.queryById(id);
      if (AssistantOrg == null)
        is_bank = true;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return is_bank;
  }

  public AssistantOrgDAO getAssistantOrgDAO() {
    return assistantOrgDAO;
  }

  public void setAssistantOrgDAO(AssistantOrgDAO assistantOrgDAO) {
    this.assistantOrgDAO = assistantOrgDAO;
  }

  public OrganizationUnitDAO getOrganizationUnitDAO() {
    return organizationUnitDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public PlOperateLogDAO getPlOperateLogDAO() {
    return plOperateLogDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

}
