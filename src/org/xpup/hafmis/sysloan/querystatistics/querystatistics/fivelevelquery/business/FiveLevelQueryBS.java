/**
 * Copy Right Information   : Goldsoft 
 * Project                  : FiveLevelQueryBS
 * @Version                 : 1.0
 * @author                  : wangy
 * ��������                   :2007-10-19
 **/
package org.xpup.hafmis.sysloan.querystatistics.querystatistics.fivelevelquery.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.AssistantOrgDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.DevelopProjectDAO;
import org.xpup.hafmis.sysloan.common.dao.DeveloperDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.PledgeContractDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantOrg;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.DeveloperDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.dto.FloorDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.fivelevelquery.bsinterface.IFiveLevelQueryBS;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.fivelevelquery.dto.FiveLevelQueryDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.fivelevelquery.form.FiveLevelQueryAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class FiveLevelQueryBS implements IFiveLevelQueryBS {

  private BorrowerAccDAO borrowerAccDAO = null;// PL111������˻���Ϣ��

  private CollBankDAO collBankDAO = null;// ת����������

  private OrganizationUnitDAO organizationUnitDAO = null;// ת�����´�

  private AssistantOrgDAO assistantOrgDAO = null;// PL007 ��õ�����˾������

  private PledgeContractDAO pledgeContractDAO = null;// ��Ѻ��ͬ��Ϣ�� PL121

  private DeveloperDAO developerDAO = null;// PL005 ��������Ϣ��

  private DevelopProjectDAO developProjectDAO = null;// PL006 ��������Ŀ ¥��
  
  private LoanBankParaDAO loanBankParaDAO = null;

  public void setLoanBankParaDAO(LoanBankParaDAO loanBankParaDAO) {
    this.loanBankParaDAO = loanBankParaDAO;
  }

  public void setPledgeContractDAO(PledgeContractDAO pledgeContractDAO) {
    this.pledgeContractDAO = pledgeContractDAO;
  }

  public void setAssistantOrgDAO(AssistantOrgDAO assistantOrgDAO) {
    this.assistantOrgDAO = assistantOrgDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setDeveloperDAO(DeveloperDAO developerDAO) {
    this.developerDAO = developerDAO;
  }

  public void setDevelopProjectDAO(DevelopProjectDAO developProjectDAO) {
    this.developProjectDAO = developProjectDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  /**
   * Description �弶����ͳ��
   * 
   * @author wangy 2007-10-20
   * @param ��ȡ������˾��Ϣ��������ʾ������
   * @param ��FiveLevelQueryShowAC����
   * @return FiveLevelQueryAF
   * @throws Exception, BusinessException
   */
  public List getAssistantOrgNameList() throws Exception {
    List list = null;
    try {
      list = assistantOrgDAO.queryAssistantOrgList();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * Description �弶����ͳ�Ʋ�ѯ�б�
   * 
   * @author wangy 2007-10-20
   * @param ����������ѯ�б�
   * @param pagination
   * @param securityInfo
   * @param ��FiveLevelQueryShowAC����
   * @return FiveLevelQueryAF
   * @throws Exception, BusinessException
   */
  public FiveLevelQueryAF queryFiveLevelQueryListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    String mode = (String) pagination.getQueryCriterions().get("mode");
    String office = (String) pagination.getQueryCriterions().get("office");
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    String developerName = (String) pagination.getQueryCriterions().get(
        "developerName");
    String developerId = (String) pagination.getQueryCriterions().get(
        "developerId");
    String floorid = (String) pagination.getQueryCriterions().get("floorid");
    String floorId = (String) pagination.getQueryCriterions().get("floorId");
    String assistantOrgName = (String) pagination.getQueryCriterions().get(
        "assistantOrgName");
    String assistantOrgId = (String) pagination.getQueryCriterions().get(
        "assistantOrgId");
    FiveLevelQueryAF fiveLevelQueryAF = new FiveLevelQueryAF();
    String type = (String) pagination.getQueryCriterions().get("type");
    // ��ȡPLҵ������
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    String bizYearMonth = bizDate.substring(0, 6);// �������
    String bizDay = bizDate.substring(6, 8);// �����
    // String bizDay = "05";
    List officeList = new ArrayList();
    String officeCode = null;
    List list1 = null;
    List list2 = null;
    List list3 = null;
    List list4 = null;
    List list5 = null;
    List loanBankList = new ArrayList();
    String loanBankId = null;
    List developerList = new ArrayList();
    List floorIdList = new ArrayList();
    List assistOrgList = new ArrayList();
    List returnList = new ArrayList();
    // ����ҳ����Ҫ��Ȩ�޵ģ���Ȩ����ȡ����������Ϊ������������Ϊ����
    // ���������1.����Ϊ��2.����Ϊ��
    System.out.println(loanBankName="=======>");
    int temp_plLoanReturnType = securityInfo.getPlLoanReturnType();
    if (type == null) {
      type = "1";
    }
    try {
      int count = loanBankParaDAO.queryFivelevelIsUse(loanBankName);
      if (!type.equals("0") && count == 0) {
        // ����Ȩ���еĻ��������ж�����Ϊ��
        if (temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_CENTER) {
          // ������Ϊ��
          if (mode.equals("1")) {
            if (office.equals("all")) {
              List tempOfficeList = securityInfo.getOfficeList();
              for (int i = 0; i < tempOfficeList.size(); i++) {
                OfficeDto officeDto = (OfficeDto) tempOfficeList.get(i);
                officeList.add(officeDto.getOfficeCode());
              }
            } else {
              officeList.add(office);
            }
            // begin
            for (int i = 0; i < officeList.size(); i++) {
              officeCode = (String) officeList.get(i);
              FiveLevelQueryDTO fiveLevelQueryDTO = new FiveLevelQueryDTO();
              OrganizationUnit organizationUnit = organizationUnitDAO
                  .queryOrganizationUnitListByCriterions(officeCode);
              String officeName = organizationUnit.getName();
              fiveLevelQueryDTO.setOffice(officeName);
              list1 = borrowerAccDAO.queryFiveLevelQueryListByCriterionsNormal(
                  bizYearMonth, bizDay, officeCode, loanBankId, developerId,
                  floorId, assistantOrgId, securityInfo);
              if (!list1.isEmpty()) {
                for (int j = 0; j < list1.size(); j++) {
                  FiveLevelQueryDTO dto1 = (FiveLevelQueryDTO) list1.get(j);
                  fiveLevelQueryDTO.setNormalCount(dto1.getNormalCount());// ��������
                  fiveLevelQueryDTO.setNormalValue(dto1.getNormalValue());// �������
                }
              }
              list2 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsAttention(bizYearMonth,
                      bizDay, officeCode, loanBankId, developerId, floorId,
                      assistantOrgId, securityInfo);
              if (!list2.isEmpty()) {
                for (int j = 0; j < list2.size(); j++) {
                  FiveLevelQueryDTO dto2 = (FiveLevelQueryDTO) list2.get(j);
                  fiveLevelQueryDTO.setAttentionCount(dto2.getAttentionCount());// ��ע����
                  fiveLevelQueryDTO.setAttentionValue(dto2.getAttentionValue());// ��ע���
                }
              }
              list3 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsSecondary(bizYearMonth,
                      bizDay, officeCode, loanBankId, developerId, floorId,
                      assistantOrgId, securityInfo);
              if (!list3.isEmpty()) {
                for (int j = 0; j < list3.size(); j++) {
                  FiveLevelQueryDTO dto3 = (FiveLevelQueryDTO) list3.get(j);
                  fiveLevelQueryDTO.setSecondaryCount(dto3.getSecondaryCount());// �μ�����
                  fiveLevelQueryDTO.setSecondaryValue(dto3.getSecondaryValue());// �μ����
                }
              }
              list4 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsShadiness(bizYearMonth,
                      bizDay, officeCode, loanBankId, developerId, floorId,
                      assistantOrgId, securityInfo);
              if (!list4.isEmpty()) {
                for (int j = 0; j < list4.size(); j++) {
                  FiveLevelQueryDTO dto4 = (FiveLevelQueryDTO) list4.get(j);
                  fiveLevelQueryDTO.setShadinessCount(dto4.getShadinessCount());// ���ɻ���
                  fiveLevelQueryDTO.setShadinessValue(dto4.getShadinessValue());// ���ɽ��
                }
              }
              list5 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsDamnify(bizYearMonth,
                      bizDay, officeCode, loanBankId, developerId, floorId,
                      assistantOrgId, securityInfo);
              if (!list5.isEmpty()) {
                for (int j = 0; j < list5.size(); j++) {
                  FiveLevelQueryDTO dto5 = (FiveLevelQueryDTO) list5.get(j);
                  fiveLevelQueryDTO.setDamnifyCount(dto5.getDamnifyCount());// ��ʧ����
                  fiveLevelQueryDTO.setDamnifyValue(dto5.getDamnifyValue());// ��ʧ���
                }
              }
              // �ϼ�
              // ����
              int totalCount = new Integer(fiveLevelQueryDTO.getNormalCount())
                  .intValue()
                  + new Integer(fiveLevelQueryDTO.getAttentionCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getSecondaryCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getShadinessCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getDamnifyCount()).intValue();
              // ���
              BigDecimal totalValue = fiveLevelQueryDTO.getNormalValue().add(
                  fiveLevelQueryDTO.getAttentionValue().add(
                      fiveLevelQueryDTO.getSecondaryValue().add(
                          fiveLevelQueryDTO.getShadinessValue().add(
                              fiveLevelQueryDTO.getDamnifyValue()))));
              fiveLevelQueryDTO.setTotalCount(totalCount + "");
              fiveLevelQueryDTO.setTotalValue(totalValue);
              // ������
              // ���� = ���μ�+ ����+ ��ʧ���Ļ���/�ܻ���
              if (totalCount != 0 && totalValue.intValue() != 0) {
                int badRateCount = new Integer(fiveLevelQueryDTO
                    .getSecondaryCount()).intValue()
                    + new Integer(fiveLevelQueryDTO.getShadinessCount())
                        .intValue()
                    + new Integer(fiveLevelQueryDTO.getDamnifyCount())
                        .intValue();
                // ��� = ���μ�+ ����+ ��ʧ���Ľ��/�ܽ��
                BigDecimal badRateValue = fiveLevelQueryDTO.getSecondaryValue()
                    .add(
                        fiveLevelQueryDTO.getShadinessValue().add(
                            fiveLevelQueryDTO.getDamnifyValue())).divide(
                        totalValue, 4, BigDecimal.ROUND_HALF_DOWN).multiply(
                        new BigDecimal(100)).divide(new BigDecimal(1), 2,
                        BigDecimal.ROUND_HALF_DOWN);
                fiveLevelQueryDTO.setBadRateCount(new BigDecimal(badRateCount)
                    .divide(new BigDecimal(totalCount), 4,
                        BigDecimal.ROUND_HALF_DOWN).multiply(
                        new BigDecimal(100)).divide(new BigDecimal(1), 2,
                        BigDecimal.ROUND_HALF_DOWN).toString());
                fiveLevelQueryDTO.setBadRateValue(badRateValue);
              }
              returnList.add(fiveLevelQueryDTO);
            }// if_for
            // end
          } else if (mode.equals("2")) {
            if (loanBankName.equals("all")) {
              // ��ȡȨ���е�����
              List bankList = securityInfo.getDkUserBankList();
              Userslogincollbank userslogincollbank = null;
              Iterator bank = bankList.iterator();
              while (bank.hasNext()) {
                userslogincollbank = (Userslogincollbank) bank.next();
                loanBankList.add(userslogincollbank.getCollBankId().toString());
              }
            } else {
              loanBankList.add(loanBankName);
            }
            // begin
            for (int i = 0; i < loanBankList.size(); i++) {
              loanBankId = (String) loanBankList.get(i);
              FiveLevelQueryDTO fiveLevelQueryDTO = new FiveLevelQueryDTO();
              String officeID = pledgeContractDAO
                  .queryOfficeIdByBankId(loanBankId);
              OrganizationUnit organizationUnit = organizationUnitDAO
                  .queryOrganizationUnitListByCriterions(officeID);
              String officeName = organizationUnit.getName();
              fiveLevelQueryDTO.setOffice(officeName);
              // ת����������
              CollBank dto = collBankDAO.getCollBankByCollBankid(loanBankId);
              fiveLevelQueryDTO.setLoanBankName(dto.getCollBankName());
              list1 = borrowerAccDAO.queryFiveLevelQueryListByCriterionsNormal(
                  bizYearMonth, bizDay, officeCode, loanBankId, developerId,
                  floorId, assistantOrgId, securityInfo);
              if (!list1.isEmpty()) {
                for (int j = 0; j < list1.size(); j++) {
                  FiveLevelQueryDTO dto1 = (FiveLevelQueryDTO) list1.get(j);
                  fiveLevelQueryDTO.setNormalCount(dto1.getNormalCount());// ��������
                  fiveLevelQueryDTO.setNormalValue(dto1.getNormalValue());// �������
                }
              }
              list2 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsAttention(bizYearMonth,
                      bizDay, officeCode, loanBankId, developerId, floorId,
                      assistantOrgId, securityInfo);
              if (!list2.isEmpty()) {
                for (int j = 0; j < list2.size(); j++) {
                  FiveLevelQueryDTO dto2 = (FiveLevelQueryDTO) list2.get(j);
                  fiveLevelQueryDTO.setAttentionCount(dto2.getAttentionCount());// ��ע����
                  fiveLevelQueryDTO.setAttentionValue(dto2.getAttentionValue());// ��ע���
                }
              }
              list3 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsSecondary(bizYearMonth,
                      bizDay, officeCode, loanBankId, developerId, floorId,
                      assistantOrgId, securityInfo);
              if (!list3.isEmpty()) {
                for (int j = 0; j < list3.size(); j++) {
                  FiveLevelQueryDTO dto3 = (FiveLevelQueryDTO) list3.get(j);
                  fiveLevelQueryDTO.setSecondaryCount(dto3.getSecondaryCount());// �μ�����
                  fiveLevelQueryDTO.setSecondaryValue(dto3.getSecondaryValue());// �μ����
                }
              }
              list4 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsShadiness(bizYearMonth,
                      bizDay, officeCode, loanBankId, developerId, floorId,
                      assistantOrgId, securityInfo);
              if (!list4.isEmpty()) {
                for (int j = 0; j < list4.size(); j++) {
                  FiveLevelQueryDTO dto4 = (FiveLevelQueryDTO) list4.get(j);
                  fiveLevelQueryDTO.setShadinessCount(dto4.getShadinessCount());// ���ɻ���
                  fiveLevelQueryDTO.setShadinessValue(dto4.getShadinessValue());// ���ɽ��
                }
              }
              list5 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsDamnify(bizYearMonth,
                      bizDay, officeCode, loanBankId, developerId, floorId,
                      assistantOrgId, securityInfo);
              if (!list5.isEmpty()) {
                for (int j = 0; j < list5.size(); j++) {
                  FiveLevelQueryDTO dto5 = (FiveLevelQueryDTO) list5.get(j);
                  fiveLevelQueryDTO.setDamnifyCount(dto5.getDamnifyCount());// ��ʧ����
                  fiveLevelQueryDTO.setDamnifyValue(dto5.getDamnifyValue());// ��ʧ���
                }
              }
              // �ϼ�
              // ����
              int totalCount = new Integer(fiveLevelQueryDTO.getNormalCount())
                  .intValue()
                  + new Integer(fiveLevelQueryDTO.getAttentionCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getSecondaryCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getShadinessCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getDamnifyCount()).intValue();
              // ���
              BigDecimal totalValue = fiveLevelQueryDTO.getNormalValue().add(
                  fiveLevelQueryDTO.getAttentionValue().add(
                      fiveLevelQueryDTO.getSecondaryValue().add(
                          fiveLevelQueryDTO.getShadinessValue().add(
                              fiveLevelQueryDTO.getDamnifyValue()))));
              fiveLevelQueryDTO.setTotalCount(totalCount + "");
              fiveLevelQueryDTO.setTotalValue(totalValue);
              // ������
              // ���� = ���μ�+ ����+ ��ʧ���Ļ���/�ܻ���
              if (totalCount != 0 && totalValue.intValue() != 0) {
                int badRateCount = new Integer(fiveLevelQueryDTO
                    .getSecondaryCount()).intValue()
                    + new Integer(fiveLevelQueryDTO.getShadinessCount())
                        .intValue()
                    + new Integer(fiveLevelQueryDTO.getDamnifyCount())
                        .intValue();
                // ��� = ���μ�+ ����+ ��ʧ���Ľ��/�ܽ��
                BigDecimal badRateValue = fiveLevelQueryDTO.getSecondaryValue()
                    .add(
                        fiveLevelQueryDTO.getShadinessValue().add(
                            fiveLevelQueryDTO.getDamnifyValue())).divide(
                        totalValue, 4, BigDecimal.ROUND_HALF_DOWN).multiply(
                        new BigDecimal(100)).divide(new BigDecimal(1), 2,
                        BigDecimal.ROUND_HALF_DOWN);
                fiveLevelQueryDTO.setBadRateCount(new BigDecimal(badRateCount)
                    .divide(new BigDecimal(totalCount), 4,
                        BigDecimal.ROUND_HALF_DOWN).multiply(
                        new BigDecimal(100)).divide(new BigDecimal(1), 2,
                        BigDecimal.ROUND_HALF_DOWN).toString());
                fiveLevelQueryDTO.setBadRateValue(badRateValue);
              }
              returnList.add(fiveLevelQueryDTO);
            }// end
          } else if (mode.equals("3")) {
            if (developerId != null && !developerId.equals("")) {
              developerList.add(developerId.trim());
            } else {
              // ��ȡȫ��������
              List developerDTOList = developerDAO
                  .queryAllDevelopIdAndDevelopName();
              DeveloperDTO developerDTO = null;
              Iterator developerIter = developerDTOList.iterator();
              while (developerIter.hasNext()) {
                developerDTO = (DeveloperDTO) developerIter.next();
                developerList.add(developerDTO.getDeveloperid());
              }
            }
            // begin
            for (int i = 0; i < developerList.size(); i++) {
              developerId = (String) developerList.get(i);
              // ͨ��������id��ÿ���������
              String developerNameTable = developerDAO
                  .queryDeveloperNameByDeveloperId(developerId);
              FiveLevelQueryDTO fiveLevelQueryDTO = new FiveLevelQueryDTO();
              fiveLevelQueryDTO.setDeveloperName(developerNameTable);
              list1 = borrowerAccDAO.queryFiveLevelQueryListByCriterionsNormal(
                  bizYearMonth, bizDay, officeCode, loanBankId, developerId,
                  floorId, assistantOrgId, securityInfo);
              if (!list1.isEmpty()) {
                for (int j = 0; j < list1.size(); j++) {
                  FiveLevelQueryDTO dto1 = (FiveLevelQueryDTO) list1.get(j);
                  fiveLevelQueryDTO.setNormalCount(dto1.getNormalCount());// ��������
                  fiveLevelQueryDTO.setNormalValue(dto1.getNormalValue());// �������
                }
              }
              list2 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsAttention(bizYearMonth,
                      bizDay, officeCode, loanBankId, developerId, floorId,
                      assistantOrgId, securityInfo);
              if (!list2.isEmpty()) {
                for (int j = 0; j < list2.size(); j++) {
                  FiveLevelQueryDTO dto2 = (FiveLevelQueryDTO) list2.get(j);
                  fiveLevelQueryDTO.setAttentionCount(dto2.getAttentionCount());// ��ע����
                  fiveLevelQueryDTO.setAttentionValue(dto2.getAttentionValue());// ��ע���
                }
              }
              list3 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsSecondary(bizYearMonth,
                      bizDay, officeCode, loanBankId, developerId, floorId,
                      assistantOrgId, securityInfo);
              if (!list3.isEmpty()) {
                for (int j = 0; j < list3.size(); j++) {
                  FiveLevelQueryDTO dto3 = (FiveLevelQueryDTO) list3.get(j);
                  fiveLevelQueryDTO.setSecondaryCount(dto3.getSecondaryCount());// �μ�����
                  fiveLevelQueryDTO.setSecondaryValue(dto3.getSecondaryValue());// �μ����
                }
              }
              list4 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsShadiness(bizYearMonth,
                      bizDay, officeCode, loanBankId, developerId, floorId,
                      assistantOrgId, securityInfo);
              if (!list4.isEmpty()) {
                for (int j = 0; j < list4.size(); j++) {
                  FiveLevelQueryDTO dto4 = (FiveLevelQueryDTO) list4.get(j);
                  fiveLevelQueryDTO.setShadinessCount(dto4.getShadinessCount());// ���ɻ���
                  fiveLevelQueryDTO.setShadinessValue(dto4.getShadinessValue());// ���ɽ��
                }
              }
              list5 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsDamnify(bizYearMonth,
                      bizDay, officeCode, loanBankId, developerId, floorId,
                      assistantOrgId, securityInfo);
              if (!list5.isEmpty()) {
                for (int j = 0; j < list5.size(); j++) {
                  FiveLevelQueryDTO dto5 = (FiveLevelQueryDTO) list5.get(j);
                  fiveLevelQueryDTO.setDamnifyCount(dto5.getDamnifyCount());// ��ʧ����
                  fiveLevelQueryDTO.setDamnifyValue(dto5.getDamnifyValue());// ��ʧ���
                }
              }
              // �ϼ�
              // ����
              int totalCount = new Integer(fiveLevelQueryDTO.getNormalCount())
                  .intValue()
                  + new Integer(fiveLevelQueryDTO.getAttentionCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getSecondaryCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getShadinessCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getDamnifyCount()).intValue();
              // ���
              BigDecimal totalValue = fiveLevelQueryDTO.getNormalValue().add(
                  fiveLevelQueryDTO.getAttentionValue().add(
                      fiveLevelQueryDTO.getSecondaryValue().add(
                          fiveLevelQueryDTO.getShadinessValue().add(
                              fiveLevelQueryDTO.getDamnifyValue()))));
              fiveLevelQueryDTO.setTotalCount(totalCount + "");
              fiveLevelQueryDTO.setTotalValue(totalValue);
              // ������
              // ���� = ���μ�+ ����+ ��ʧ���Ļ���/�ܻ���
              if (totalCount != 0 && totalValue.intValue() != 0) {
                int badRateCount = new Integer(fiveLevelQueryDTO
                    .getSecondaryCount()).intValue()
                    + new Integer(fiveLevelQueryDTO.getShadinessCount())
                        .intValue()
                    + new Integer(fiveLevelQueryDTO.getDamnifyCount())
                        .intValue();
                // ��� = ���μ�+ ����+ ��ʧ���Ľ��/�ܽ��
                BigDecimal badRateValue = fiveLevelQueryDTO.getSecondaryValue()
                    .add(
                        fiveLevelQueryDTO.getShadinessValue().add(
                            fiveLevelQueryDTO.getDamnifyValue())).divide(
                        totalValue, 4, BigDecimal.ROUND_HALF_DOWN).multiply(
                        new BigDecimal(100)).divide(new BigDecimal(1), 2,
                        BigDecimal.ROUND_HALF_DOWN);
                fiveLevelQueryDTO.setBadRateCount(new BigDecimal(badRateCount)
                    .divide(new BigDecimal(totalCount), 4,
                        BigDecimal.ROUND_HALF_DOWN).multiply(
                        new BigDecimal(100)).divide(new BigDecimal(1), 2,
                        BigDecimal.ROUND_HALF_DOWN).toString());
                fiveLevelQueryDTO.setBadRateValue(badRateValue);
              }
              returnList.add(fiveLevelQueryDTO);
              // end
            }
          } else if (mode.equals("4")) {
            if (floorid != null && !floorid.equals("")) {
              floorIdList.add(floorid.trim());
            } else {
              // ��ȡȫ��¥��
              List floorDTOList = developProjectDAO
                  .queryAllFloorIdAndFloorName();
              FloorDTO floorDTO = null;
              Iterator floorIter = floorDTOList.iterator();
              while (floorIter.hasNext()) {
                floorDTO = (FloorDTO) floorIter.next();
                floorIdList.add(floorDTO.getFloorId());
              }
            }
            // begin
            for (int i = 0; i < floorIdList.size(); i++) {
              floorId = (String) floorIdList.get(i);
              FiveLevelQueryDTO fiveLevelQueryDTO = new FiveLevelQueryDTO();
              // ͨ��¥��ID���¥�����ƺͿ�����ID
              List floorName = developProjectDAO
                  .queryFloorNameAndHeadIdByFloorId(floorId);
              FloorDTO floorDTO = null;
              Iterator floorIter = floorName.iterator();
              while (floorIter.hasNext()) {
                floorDTO = (FloorDTO) floorIter.next();
                fiveLevelQueryDTO.setFloorId(floorDTO.getFloorName());
                // ͨ��������id��ÿ���������
                String developerNameTable = developerDAO
                    .queryDeveloperNameByDeveloperId(floorDTO.getDeveloperId());
                fiveLevelQueryDTO.setDeveloperName(developerNameTable);
              }
              list1 = borrowerAccDAO.queryFiveLevelQueryListByCriterionsNormal(
                  bizYearMonth, bizDay, officeCode, loanBankId, developerId,
                  floorId, assistantOrgId, securityInfo);
              if (!list1.isEmpty()) {
                for (int j = 0; j < list1.size(); j++) {
                  FiveLevelQueryDTO dto1 = (FiveLevelQueryDTO) list1.get(j);
                  fiveLevelQueryDTO.setNormalCount(dto1.getNormalCount());// ��������
                  fiveLevelQueryDTO.setNormalValue(dto1.getNormalValue());// �������
                }
              }
              list2 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsAttention(bizYearMonth,
                      bizDay, officeCode, loanBankId, developerId, floorId,
                      assistantOrgId, securityInfo);
              if (!list2.isEmpty()) {
                for (int j = 0; j < list2.size(); j++) {
                  FiveLevelQueryDTO dto2 = (FiveLevelQueryDTO) list2.get(j);
                  fiveLevelQueryDTO.setAttentionCount(dto2.getAttentionCount());// ��ע����
                  fiveLevelQueryDTO.setAttentionValue(dto2.getAttentionValue());// ��ע���
                }
              }
              list3 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsSecondary(bizYearMonth,
                      bizDay, officeCode, loanBankId, developerId, floorId,
                      assistantOrgId, securityInfo);
              if (!list3.isEmpty()) {
                for (int j = 0; j < list3.size(); j++) {
                  FiveLevelQueryDTO dto3 = (FiveLevelQueryDTO) list3.get(j);
                  fiveLevelQueryDTO.setSecondaryCount(dto3.getSecondaryCount());// �μ�����
                  fiveLevelQueryDTO.setSecondaryValue(dto3.getSecondaryValue());// �μ����
                }
              }
              list4 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsShadiness(bizYearMonth,
                      bizDay, officeCode, loanBankId, developerId, floorId,
                      assistantOrgId, securityInfo);
              if (!list4.isEmpty()) {
                for (int j = 0; j < list4.size(); j++) {
                  FiveLevelQueryDTO dto4 = (FiveLevelQueryDTO) list4.get(j);
                  fiveLevelQueryDTO.setShadinessCount(dto4.getShadinessCount());// ���ɻ���
                  fiveLevelQueryDTO.setShadinessValue(dto4.getShadinessValue());// ���ɽ��
                }
              }
              list5 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsDamnify(bizYearMonth,
                      bizDay, officeCode, loanBankId, developerId, floorId,
                      assistantOrgId, securityInfo);
              if (!list5.isEmpty()) {
                for (int j = 0; j < list5.size(); j++) {
                  FiveLevelQueryDTO dto5 = (FiveLevelQueryDTO) list5.get(j);
                  fiveLevelQueryDTO.setDamnifyCount(dto5.getDamnifyCount());// ��ʧ����
                  fiveLevelQueryDTO.setDamnifyValue(dto5.getDamnifyValue());// ��ʧ���
                }
              }
              // �ϼ�
              // ����
              int totalCount = new Integer(fiveLevelQueryDTO.getNormalCount())
                  .intValue()
                  + new Integer(fiveLevelQueryDTO.getAttentionCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getSecondaryCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getShadinessCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getDamnifyCount()).intValue();
              // ���
              BigDecimal totalValue = fiveLevelQueryDTO.getNormalValue().add(
                  fiveLevelQueryDTO.getAttentionValue().add(
                      fiveLevelQueryDTO.getSecondaryValue().add(
                          fiveLevelQueryDTO.getShadinessValue().add(
                              fiveLevelQueryDTO.getDamnifyValue()))));
              fiveLevelQueryDTO.setTotalCount(totalCount + "");
              fiveLevelQueryDTO.setTotalValue(totalValue);
              // ������
              // ���� = ���μ�+ ����+ ��ʧ���Ļ���/�ܻ���
              if (totalCount != 0 && totalValue.intValue() != 0) {
                int badRateCount = new Integer(fiveLevelQueryDTO
                    .getSecondaryCount()).intValue()
                    + new Integer(fiveLevelQueryDTO.getShadinessCount())
                        .intValue()
                    + new Integer(fiveLevelQueryDTO.getDamnifyCount())
                        .intValue();
                // ��� = ���μ�+ ����+ ��ʧ���Ľ��/�ܽ��
                BigDecimal badRateValue = fiveLevelQueryDTO.getSecondaryValue()
                    .add(
                        fiveLevelQueryDTO.getShadinessValue().add(
                            fiveLevelQueryDTO.getDamnifyValue())).divide(
                        totalValue, 4, BigDecimal.ROUND_HALF_DOWN).multiply(
                        new BigDecimal(100)).divide(new BigDecimal(1), 2,
                        BigDecimal.ROUND_HALF_DOWN);
                fiveLevelQueryDTO.setBadRateCount(new BigDecimal(badRateCount)
                    .divide(new BigDecimal(totalCount), 4,
                        BigDecimal.ROUND_HALF_DOWN).multiply(
                        new BigDecimal(100)).divide(new BigDecimal(1), 2,
                        BigDecimal.ROUND_HALF_DOWN).toString());
                fiveLevelQueryDTO.setBadRateValue(badRateValue);
              }
              returnList.add(fiveLevelQueryDTO);
              // end
            }
          } else if (mode.equals("5")) {
            assistantOrgId = assistantOrgName;
            if (assistantOrgId.equals("all")) {
              // ��ȡȫ��������˾
              List assistantOrgList = this.getAssistantOrgNameList();
              AssistantOrg assistantOrg = null;
              Iterator assistantOrgIter = assistantOrgList.iterator();
              while (assistantOrgIter.hasNext()) {
                assistantOrg = (AssistantOrg) assistantOrgIter.next();
                assistOrgList.add(assistantOrg.getAssistantOrgId().toString());
              }
            } else {
              assistOrgList.add(assistantOrgId);
            }
            // begin
            for (int i = 0; i < assistOrgList.size(); i++) {
              assistantOrgId = (String) assistOrgList.get(i);
              FiveLevelQueryDTO fiveLevelQueryDTO = new FiveLevelQueryDTO();
              // ת��������˾����
              String assistantOrgNameTable = assistantOrgDAO
                  .queryAssistantOrgNameByAssistantOrgId(assistantOrgId);
              fiveLevelQueryDTO.setAssistantOrgName(assistantOrgNameTable);
              list1 = borrowerAccDAO.queryFiveLevelQueryListByCriterionsNormal(
                  bizYearMonth, bizDay, officeCode, loanBankId, developerId,
                  floorId, assistantOrgId, securityInfo);
              if (!list1.isEmpty()) {
                for (int j = 0; j < list1.size(); j++) {
                  FiveLevelQueryDTO dto1 = (FiveLevelQueryDTO) list1.get(j);
                  fiveLevelQueryDTO.setNormalCount(dto1.getNormalCount());// ��������
                  fiveLevelQueryDTO.setNormalValue(dto1.getNormalValue());// �������
                }
              }
              list2 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsAttention(bizYearMonth,
                      bizDay, officeCode, loanBankId, developerId, floorId,
                      assistantOrgId, securityInfo);
              if (!list2.isEmpty()) {
                for (int j = 0; j < list2.size(); j++) {
                  FiveLevelQueryDTO dto2 = (FiveLevelQueryDTO) list2.get(j);
                  fiveLevelQueryDTO.setAttentionCount(dto2.getAttentionCount());// ��ע����
                  fiveLevelQueryDTO.setAttentionValue(dto2.getAttentionValue());// ��ע���
                }
              }
              list3 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsSecondary(bizYearMonth,
                      bizDay, officeCode, loanBankId, developerId, floorId,
                      assistantOrgId, securityInfo);
              if (!list3.isEmpty()) {
                for (int j = 0; j < list3.size(); j++) {
                  FiveLevelQueryDTO dto3 = (FiveLevelQueryDTO) list3.get(j);
                  fiveLevelQueryDTO.setSecondaryCount(dto3.getSecondaryCount());// �μ�����
                  fiveLevelQueryDTO.setSecondaryValue(dto3.getSecondaryValue());// �μ����
                }
              }
              list4 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsShadiness(bizYearMonth,
                      bizDay, officeCode, loanBankId, developerId, floorId,
                      assistantOrgId, securityInfo);
              if (!list4.isEmpty()) {
                for (int j = 0; j < list4.size(); j++) {
                  FiveLevelQueryDTO dto4 = (FiveLevelQueryDTO) list4.get(j);
                  fiveLevelQueryDTO.setShadinessCount(dto4.getShadinessCount());// ���ɻ���
                  fiveLevelQueryDTO.setShadinessValue(dto4.getShadinessValue());// ���ɽ��
                }
              }
              list5 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsDamnify(bizYearMonth,
                      bizDay, officeCode, loanBankId, developerId, floorId,
                      assistantOrgId, securityInfo);
              if (!list5.isEmpty()) {
                for (int j = 0; j < list5.size(); j++) {
                  FiveLevelQueryDTO dto5 = (FiveLevelQueryDTO) list5.get(j);
                  fiveLevelQueryDTO.setDamnifyCount(dto5.getDamnifyCount());// ��ʧ����
                  fiveLevelQueryDTO.setDamnifyValue(dto5.getDamnifyValue());// ��ʧ���
                }
              }
              // �ϼ�
              // ����
              int totalCount = new Integer(fiveLevelQueryDTO.getNormalCount())
                  .intValue()
                  + new Integer(fiveLevelQueryDTO.getAttentionCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getSecondaryCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getShadinessCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getDamnifyCount()).intValue();
              // ���
              BigDecimal totalValue = fiveLevelQueryDTO.getNormalValue().add(
                  fiveLevelQueryDTO.getAttentionValue().add(
                      fiveLevelQueryDTO.getSecondaryValue().add(
                          fiveLevelQueryDTO.getShadinessValue().add(
                              fiveLevelQueryDTO.getDamnifyValue()))));
              fiveLevelQueryDTO.setTotalCount(totalCount + "");
              fiveLevelQueryDTO.setTotalValue(totalValue);
              // ������
              // ���� = ���μ�+ ����+ ��ʧ���Ļ���/�ܻ���
              if (totalCount != 0 && totalValue.intValue() != 0) {
                int badRateCount = new Integer(fiveLevelQueryDTO
                    .getSecondaryCount()).intValue()
                    + new Integer(fiveLevelQueryDTO.getShadinessCount())
                        .intValue()
                    + new Integer(fiveLevelQueryDTO.getDamnifyCount())
                        .intValue();
                // ��� = ���μ�+ ����+ ��ʧ���Ľ��/�ܽ��
                BigDecimal badRateValue = fiveLevelQueryDTO.getSecondaryValue()
                    .add(
                        fiveLevelQueryDTO.getShadinessValue().add(
                            fiveLevelQueryDTO.getDamnifyValue())).divide(
                        totalValue, 4, BigDecimal.ROUND_HALF_DOWN).multiply(
                        new BigDecimal(100)).divide(new BigDecimal(1), 2,
                        BigDecimal.ROUND_HALF_DOWN);
                fiveLevelQueryDTO.setBadRateCount(new BigDecimal(badRateCount)
                    .divide(new BigDecimal(totalCount), 4,
                        BigDecimal.ROUND_HALF_DOWN).multiply(
                        new BigDecimal(100)).divide(new BigDecimal(1), 2,
                        BigDecimal.ROUND_HALF_DOWN).toString());
                fiveLevelQueryDTO.setBadRateValue(badRateValue);
              }
              returnList.add(fiveLevelQueryDTO);
              // end
            }
          } // if_mode5
        } // if_����
        if (temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_BANK) {
          // ������Ϊ��
          if (mode.equals("1")) {
            if (office.equals("all")) {
              List tempOfficeList = securityInfo.getOfficeList();
              for (int i = 0; i < tempOfficeList.size(); i++) {
                OfficeDto officeDto = (OfficeDto) tempOfficeList.get(i);
                officeList.add(officeDto.getOfficeCode());
              }
            } else {
              officeList.add(office);
            }
            // begin
            for (int i = 0; i < officeList.size(); i++) {
              officeCode = (String) officeList.get(i);
              FiveLevelQueryDTO fiveLevelQueryDTO = new FiveLevelQueryDTO();
              OrganizationUnit organizationUnit = organizationUnitDAO
                  .queryOrganizationUnitListByCriterions(officeCode);
              String officeName = organizationUnit.getName();
              fiveLevelQueryDTO.setOffice(officeName);
              list1 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsNormalBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list1.isEmpty()) {
                for (int j = 0; j < list1.size(); j++) {
                  FiveLevelQueryDTO dto1 = (FiveLevelQueryDTO) list1.get(j);
                  fiveLevelQueryDTO.setNormalCount(dto1.getNormalCount());// ��������
                  fiveLevelQueryDTO.setNormalValue(dto1.getNormalValue());// �������
                }
              }
              list2 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsAttentionBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list2.isEmpty()) {
                for (int j = 0; j < list2.size(); j++) {
                  FiveLevelQueryDTO dto2 = (FiveLevelQueryDTO) list2.get(j);
                  fiveLevelQueryDTO.setAttentionCount(dto2.getAttentionCount());// ��ע����
                  fiveLevelQueryDTO.setAttentionValue(dto2.getAttentionValue());// ��ע���
                }
              }
              list3 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsSecondaryBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list3.isEmpty()) {
                for (int j = 0; j < list3.size(); j++) {
                  FiveLevelQueryDTO dto3 = (FiveLevelQueryDTO) list3.get(j);
                  fiveLevelQueryDTO.setSecondaryCount(dto3.getSecondaryCount());// �μ�����
                  fiveLevelQueryDTO.setSecondaryValue(dto3.getSecondaryValue());// �μ����
                }
              }
              list4 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsShadinessBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list4.isEmpty()) {
                for (int j = 0; j < list4.size(); j++) {
                  FiveLevelQueryDTO dto4 = (FiveLevelQueryDTO) list4.get(j);
                  fiveLevelQueryDTO.setShadinessCount(dto4.getShadinessCount());// ���ɻ���
                  fiveLevelQueryDTO.setShadinessValue(dto4.getShadinessValue());// ���ɽ��
                }
              }
              list5 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsDamnifyBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list5.isEmpty()) {
                for (int j = 0; j < list5.size(); j++) {
                  FiveLevelQueryDTO dto5 = (FiveLevelQueryDTO) list5.get(j);
                  fiveLevelQueryDTO.setDamnifyCount(dto5.getDamnifyCount());// ��ʧ����
                  fiveLevelQueryDTO.setDamnifyValue(dto5.getDamnifyValue());// ��ʧ���
                }
              }
              // �ϼ�
              // ����
              int totalCount = new Integer(fiveLevelQueryDTO.getNormalCount())
                  .intValue()
                  + new Integer(fiveLevelQueryDTO.getAttentionCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getSecondaryCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getShadinessCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getDamnifyCount()).intValue();
              // ���
              BigDecimal totalValue = fiveLevelQueryDTO.getNormalValue().add(
                  fiveLevelQueryDTO.getAttentionValue().add(
                      fiveLevelQueryDTO.getSecondaryValue().add(
                          fiveLevelQueryDTO.getShadinessValue().add(
                              fiveLevelQueryDTO.getDamnifyValue()))));
              fiveLevelQueryDTO.setTotalCount(totalCount + "");
              fiveLevelQueryDTO.setTotalValue(totalValue);
              // ������
              // ���� = ���μ�+ ����+ ��ʧ���Ļ���/�ܻ���
              if (totalCount != 0 && totalValue.intValue() != 0) {
                int badRateCount = new Integer(fiveLevelQueryDTO
                    .getSecondaryCount()).intValue()
                    + new Integer(fiveLevelQueryDTO.getShadinessCount())
                        .intValue()
                    + new Integer(fiveLevelQueryDTO.getDamnifyCount())
                        .intValue();
                // ��� = ���μ�+ ����+ ��ʧ���Ľ��/�ܽ��
                BigDecimal badRateValue = fiveLevelQueryDTO.getSecondaryValue()
                    .add(
                        fiveLevelQueryDTO.getShadinessValue().add(
                            fiveLevelQueryDTO.getDamnifyValue())).divide(
                        totalValue, 4, BigDecimal.ROUND_HALF_DOWN).multiply(
                        new BigDecimal(100)).divide(new BigDecimal(1), 2,
                        BigDecimal.ROUND_HALF_DOWN);
                fiveLevelQueryDTO.setBadRateCount(new BigDecimal(badRateCount)
                    .divide(new BigDecimal(totalCount), 4,
                        BigDecimal.ROUND_HALF_DOWN).multiply(
                        new BigDecimal(100)).divide(new BigDecimal(1), 2,
                        BigDecimal.ROUND_HALF_DOWN).toString());
                fiveLevelQueryDTO.setBadRateValue(badRateValue);
              }
              returnList.add(fiveLevelQueryDTO);
            }// if_for
            // end
          } else if (mode.equals("2")) {
            if (loanBankName.equals("all")) {
              // ��ȡȨ���е�����
              List bankList = securityInfo.getDkUserBankList();
              Userslogincollbank userslogincollbank = null;
              Iterator bank = bankList.iterator();
              while (bank.hasNext()) {
                userslogincollbank = (Userslogincollbank) bank.next();
                loanBankList.add(userslogincollbank.getCollBankId().toString());
              }
            } else {
              loanBankList.add(loanBankName);
            }
            // begin
            for (int i = 0; i < loanBankList.size(); i++) {
              loanBankId = (String) loanBankList.get(i);
              FiveLevelQueryDTO fiveLevelQueryDTO = new FiveLevelQueryDTO();
              String officeID = pledgeContractDAO
                  .queryOfficeIdByBankId(loanBankId);
              OrganizationUnit organizationUnit = organizationUnitDAO
                  .queryOrganizationUnitListByCriterions(officeID);
              String officeName = organizationUnit.getName();
              fiveLevelQueryDTO.setOffice(officeName);
              // ת����������
              CollBank dto = collBankDAO.getCollBankByCollBankid(loanBankId);
              fiveLevelQueryDTO.setLoanBankName(dto.getCollBankName());
              list1 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsNormalBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list1.isEmpty()) {
                for (int j = 0; j < list1.size(); j++) {
                  FiveLevelQueryDTO dto1 = (FiveLevelQueryDTO) list1.get(j);
                  fiveLevelQueryDTO.setNormalCount(dto1.getNormalCount());// ��������
                  fiveLevelQueryDTO.setNormalValue(dto1.getNormalValue());// �������
                }
              }
              list2 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsAttentionBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list2.isEmpty()) {
                for (int j = 0; j < list2.size(); j++) {
                  FiveLevelQueryDTO dto2 = (FiveLevelQueryDTO) list2.get(j);
                  fiveLevelQueryDTO.setAttentionCount(dto2.getAttentionCount());// ��ע����
                  fiveLevelQueryDTO.setAttentionValue(dto2.getAttentionValue());// ��ע���
                }
              }
              list3 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsSecondaryBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list3.isEmpty()) {
                for (int j = 0; j < list3.size(); j++) {
                  FiveLevelQueryDTO dto3 = (FiveLevelQueryDTO) list3.get(j);
                  fiveLevelQueryDTO.setSecondaryCount(dto3.getSecondaryCount());// �μ�����
                  fiveLevelQueryDTO.setSecondaryValue(dto3.getSecondaryValue());// �μ����
                }
              }
              list4 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsShadinessBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list4.isEmpty()) {
                for (int j = 0; j < list4.size(); j++) {
                  FiveLevelQueryDTO dto4 = (FiveLevelQueryDTO) list4.get(j);
                  fiveLevelQueryDTO.setShadinessCount(dto4.getShadinessCount());// ���ɻ���
                  fiveLevelQueryDTO.setShadinessValue(dto4.getShadinessValue());// ���ɽ��
                }
              }
              list5 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsDamnifyBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list5.isEmpty()) {
                for (int j = 0; j < list5.size(); j++) {
                  FiveLevelQueryDTO dto5 = (FiveLevelQueryDTO) list5.get(j);
                  fiveLevelQueryDTO.setDamnifyCount(dto5.getDamnifyCount());// ��ʧ����
                  fiveLevelQueryDTO.setDamnifyValue(dto5.getDamnifyValue());// ��ʧ���
                }
              }
              // �ϼ�
              // ����
              int totalCount = new Integer(fiveLevelQueryDTO.getNormalCount())
                  .intValue()
                  + new Integer(fiveLevelQueryDTO.getAttentionCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getSecondaryCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getShadinessCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getDamnifyCount()).intValue();
              // ���
              BigDecimal totalValue = fiveLevelQueryDTO.getNormalValue().add(
                  fiveLevelQueryDTO.getAttentionValue().add(
                      fiveLevelQueryDTO.getSecondaryValue().add(
                          fiveLevelQueryDTO.getShadinessValue().add(
                              fiveLevelQueryDTO.getDamnifyValue()))));
              fiveLevelQueryDTO.setTotalCount(totalCount + "");
              fiveLevelQueryDTO.setTotalValue(totalValue);
              // ������
              // ���� = ���μ�+ ����+ ��ʧ���Ļ���/�ܻ���
              if (totalCount != 0 && totalValue.intValue() != 0) {
                int badRateCount = new Integer(fiveLevelQueryDTO
                    .getSecondaryCount()).intValue()
                    + new Integer(fiveLevelQueryDTO.getShadinessCount())
                        .intValue()
                    + new Integer(fiveLevelQueryDTO.getDamnifyCount())
                        .intValue();
                // ��� = ���μ�+ ����+ ��ʧ���Ľ��/�ܽ��
                BigDecimal badRateValue = fiveLevelQueryDTO.getSecondaryValue()
                    .add(
                        fiveLevelQueryDTO.getShadinessValue().add(
                            fiveLevelQueryDTO.getDamnifyValue())).divide(
                        totalValue, 4, BigDecimal.ROUND_HALF_DOWN).multiply(
                        new BigDecimal(100)).divide(new BigDecimal(1), 2,
                        BigDecimal.ROUND_HALF_DOWN);
                fiveLevelQueryDTO.setBadRateCount(new BigDecimal(badRateCount)
                    .divide(new BigDecimal(totalCount), 4,
                        BigDecimal.ROUND_HALF_DOWN).multiply(
                        new BigDecimal(100)).divide(new BigDecimal(1), 2,
                        BigDecimal.ROUND_HALF_DOWN).toString());
                fiveLevelQueryDTO.setBadRateValue(badRateValue);
              }
              returnList.add(fiveLevelQueryDTO);
            }// end
          } else if (mode.equals("3")) {
            if (developerId != null && !developerId.equals("")) {
              developerList.add(developerId.trim());
            } else {
              // ��ȡȫ��������
              List developerDTOList = developerDAO
                  .queryAllDevelopIdAndDevelopName();
              DeveloperDTO developerDTO = null;
              Iterator developerIter = developerDTOList.iterator();
              while (developerIter.hasNext()) {
                developerDTO = (DeveloperDTO) developerIter.next();
                developerList.add(developerDTO.getDeveloperid());
              }
            }
            // begin
            for (int i = 0; i < developerList.size(); i++) {
              developerId = (String) developerList.get(i);
              // ͨ��������id��ÿ���������
              String developerNameTable = developerDAO
                  .queryDeveloperNameByDeveloperId(developerId);
              FiveLevelQueryDTO fiveLevelQueryDTO = new FiveLevelQueryDTO();
              fiveLevelQueryDTO.setDeveloperName(developerNameTable);
              list1 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsNormalBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list1.isEmpty()) {
                for (int j = 0; j < list1.size(); j++) {
                  FiveLevelQueryDTO dto1 = (FiveLevelQueryDTO) list1.get(j);
                  fiveLevelQueryDTO.setNormalCount(dto1.getNormalCount());// ��������
                  fiveLevelQueryDTO.setNormalValue(dto1.getNormalValue());// �������
                }
              }
              list2 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsAttentionBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list2.isEmpty()) {
                for (int j = 0; j < list2.size(); j++) {
                  FiveLevelQueryDTO dto2 = (FiveLevelQueryDTO) list2.get(j);
                  fiveLevelQueryDTO.setAttentionCount(dto2.getAttentionCount());// ��ע����
                  fiveLevelQueryDTO.setAttentionValue(dto2.getAttentionValue());// ��ע���
                }
              }
              list3 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsSecondaryBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list3.isEmpty()) {
                for (int j = 0; j < list3.size(); j++) {
                  FiveLevelQueryDTO dto3 = (FiveLevelQueryDTO) list3.get(j);
                  fiveLevelQueryDTO.setSecondaryCount(dto3.getSecondaryCount());// �μ�����
                  fiveLevelQueryDTO.setSecondaryValue(dto3.getSecondaryValue());// �μ����
                }
              }
              list4 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsShadinessBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list4.isEmpty()) {
                for (int j = 0; j < list4.size(); j++) {
                  FiveLevelQueryDTO dto4 = (FiveLevelQueryDTO) list4.get(j);
                  fiveLevelQueryDTO.setShadinessCount(dto4.getShadinessCount());// ���ɻ���
                  fiveLevelQueryDTO.setShadinessValue(dto4.getShadinessValue());// ���ɽ��
                }
              }
              list5 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsDamnifyBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list5.isEmpty()) {
                for (int j = 0; j < list5.size(); j++) {
                  FiveLevelQueryDTO dto5 = (FiveLevelQueryDTO) list5.get(j);
                  fiveLevelQueryDTO.setDamnifyCount(dto5.getDamnifyCount());// ��ʧ����
                  fiveLevelQueryDTO.setDamnifyValue(dto5.getDamnifyValue());// ��ʧ���
                }
              }
              // �ϼ�
              // ����
              int totalCount = new Integer(fiveLevelQueryDTO.getNormalCount())
                  .intValue()
                  + new Integer(fiveLevelQueryDTO.getAttentionCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getSecondaryCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getShadinessCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getDamnifyCount()).intValue();
              // ���
              BigDecimal totalValue = fiveLevelQueryDTO.getNormalValue().add(
                  fiveLevelQueryDTO.getAttentionValue().add(
                      fiveLevelQueryDTO.getSecondaryValue().add(
                          fiveLevelQueryDTO.getShadinessValue().add(
                              fiveLevelQueryDTO.getDamnifyValue()))));
              fiveLevelQueryDTO.setTotalCount(totalCount + "");
              fiveLevelQueryDTO.setTotalValue(totalValue);
              // ������
              // ���� = ���μ�+ ����+ ��ʧ���Ļ���/�ܻ���
              if (totalCount != 0 && totalValue.intValue() != 0) {
                int badRateCount = new Integer(fiveLevelQueryDTO
                    .getSecondaryCount()).intValue()
                    + new Integer(fiveLevelQueryDTO.getShadinessCount())
                        .intValue()
                    + new Integer(fiveLevelQueryDTO.getDamnifyCount())
                        .intValue();
                // ��� = ���μ�+ ����+ ��ʧ���Ľ��/�ܽ��
                BigDecimal badRateValue = fiveLevelQueryDTO.getSecondaryValue()
                    .add(
                        fiveLevelQueryDTO.getShadinessValue().add(
                            fiveLevelQueryDTO.getDamnifyValue())).divide(
                        totalValue, 4, BigDecimal.ROUND_HALF_DOWN).multiply(
                        new BigDecimal(100)).divide(new BigDecimal(1), 2,
                        BigDecimal.ROUND_HALF_DOWN);
                fiveLevelQueryDTO.setBadRateCount(new BigDecimal(badRateCount)
                    .divide(new BigDecimal(totalCount), 4,
                        BigDecimal.ROUND_HALF_DOWN).multiply(
                        new BigDecimal(100)).divide(new BigDecimal(1), 2,
                        BigDecimal.ROUND_HALF_DOWN).toString());
                fiveLevelQueryDTO.setBadRateValue(badRateValue);
              }
              returnList.add(fiveLevelQueryDTO);
              // end
            }
          } else if (mode.equals("4")) {
            if (floorid != null && !floorid.equals("")) {
              floorIdList.add(floorid.trim());
            } else {
              // ��ȡȫ��¥��
              List floorDTOList = developProjectDAO
                  .queryAllFloorIdAndFloorName();
              FloorDTO floorDTO = null;
              Iterator floorIter = floorDTOList.iterator();
              while (floorIter.hasNext()) {
                floorDTO = (FloorDTO) floorIter.next();
                floorIdList.add(floorDTO.getFloorId());
              }
            }
            // begin
            for (int i = 0; i < floorIdList.size(); i++) {
              floorId = (String) floorIdList.get(i);
              FiveLevelQueryDTO fiveLevelQueryDTO = new FiveLevelQueryDTO();
              // ͨ��¥��ID���¥�����ƺͿ�����ID
              List floorName = developProjectDAO
                  .queryFloorNameAndHeadIdByFloorId(floorId);
              FloorDTO floorDTO = null;
              Iterator floorIter = floorName.iterator();
              while (floorIter.hasNext()) {
                floorDTO = (FloorDTO) floorIter.next();
                fiveLevelQueryDTO.setFloorId(floorDTO.getFloorName());
                // ͨ��������id��ÿ���������
                String developerNameTable = developerDAO
                    .queryDeveloperNameByDeveloperId(floorDTO.getDeveloperId());
                fiveLevelQueryDTO.setDeveloperName(developerNameTable);
              }
              list1 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsNormalBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list1.isEmpty()) {
                for (int j = 0; j < list1.size(); j++) {
                  FiveLevelQueryDTO dto1 = (FiveLevelQueryDTO) list1.get(j);
                  fiveLevelQueryDTO.setNormalCount(dto1.getNormalCount());// ��������
                  fiveLevelQueryDTO.setNormalValue(dto1.getNormalValue());// �������
                }
              }
              list2 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsAttentionBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list2.isEmpty()) {
                for (int j = 0; j < list2.size(); j++) {
                  FiveLevelQueryDTO dto2 = (FiveLevelQueryDTO) list2.get(j);
                  fiveLevelQueryDTO.setAttentionCount(dto2.getAttentionCount());// ��ע����
                  fiveLevelQueryDTO.setAttentionValue(dto2.getAttentionValue());// ��ע���
                }
              }
              list3 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsSecondaryBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list3.isEmpty()) {
                for (int j = 0; j < list3.size(); j++) {
                  FiveLevelQueryDTO dto3 = (FiveLevelQueryDTO) list3.get(j);
                  fiveLevelQueryDTO.setSecondaryCount(dto3.getSecondaryCount());// �μ�����
                  fiveLevelQueryDTO.setSecondaryValue(dto3.getSecondaryValue());// �μ����
                }
              }
              list4 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsShadinessBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list4.isEmpty()) {
                for (int j = 0; j < list4.size(); j++) {
                  FiveLevelQueryDTO dto4 = (FiveLevelQueryDTO) list4.get(j);
                  fiveLevelQueryDTO.setShadinessCount(dto4.getShadinessCount());// ���ɻ���
                  fiveLevelQueryDTO.setShadinessValue(dto4.getShadinessValue());// ���ɽ��
                }
              }
              list5 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsDamnifyBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list5.isEmpty()) {
                for (int j = 0; j < list5.size(); j++) {
                  FiveLevelQueryDTO dto5 = (FiveLevelQueryDTO) list5.get(j);
                  fiveLevelQueryDTO.setDamnifyCount(dto5.getDamnifyCount());// ��ʧ����
                  fiveLevelQueryDTO.setDamnifyValue(dto5.getDamnifyValue());// ��ʧ���
                }
              }
              // �ϼ�
              // ����
              int totalCount = new Integer(fiveLevelQueryDTO.getNormalCount())
                  .intValue()
                  + new Integer(fiveLevelQueryDTO.getAttentionCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getSecondaryCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getShadinessCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getDamnifyCount()).intValue();
              // ���
              BigDecimal totalValue = fiveLevelQueryDTO.getNormalValue().add(
                  fiveLevelQueryDTO.getAttentionValue().add(
                      fiveLevelQueryDTO.getSecondaryValue().add(
                          fiveLevelQueryDTO.getShadinessValue().add(
                              fiveLevelQueryDTO.getDamnifyValue()))));
              fiveLevelQueryDTO.setTotalCount(totalCount + "");
              fiveLevelQueryDTO.setTotalValue(totalValue);
              // ������
              // ���� = ���μ�+ ����+ ��ʧ���Ļ���/�ܻ���
              if (totalCount != 0 && totalValue.intValue() != 0) {
                int badRateCount = new Integer(fiveLevelQueryDTO
                    .getSecondaryCount()).intValue()
                    + new Integer(fiveLevelQueryDTO.getShadinessCount())
                        .intValue()
                    + new Integer(fiveLevelQueryDTO.getDamnifyCount())
                        .intValue();
                // ��� = ���μ�+ ����+ ��ʧ���Ľ��/�ܽ��
                BigDecimal badRateValue = fiveLevelQueryDTO.getSecondaryValue()
                    .add(
                        fiveLevelQueryDTO.getShadinessValue().add(
                            fiveLevelQueryDTO.getDamnifyValue())).divide(
                        totalValue, 4, BigDecimal.ROUND_HALF_DOWN).multiply(
                        new BigDecimal(100)).divide(new BigDecimal(1), 2,
                        BigDecimal.ROUND_HALF_DOWN);
                fiveLevelQueryDTO.setBadRateCount(new BigDecimal(badRateCount)
                    .divide(new BigDecimal(totalCount), 4,
                        BigDecimal.ROUND_HALF_DOWN).multiply(
                        new BigDecimal(100)).divide(new BigDecimal(1), 2,
                        BigDecimal.ROUND_HALF_DOWN).toString());
                fiveLevelQueryDTO.setBadRateValue(badRateValue);
              }
              returnList.add(fiveLevelQueryDTO);
              // end
            }
          } else if (mode.equals("5")) {
            assistantOrgId = assistantOrgName;
            if (assistantOrgId.equals("all")) {
              // ��ȡȫ��������˾
              List assistantOrgList = this.getAssistantOrgNameList();
              AssistantOrg assistantOrg = null;
              Iterator assistantOrgIter = assistantOrgList.iterator();
              while (assistantOrgIter.hasNext()) {
                assistantOrg = (AssistantOrg) assistantOrgIter.next();
                assistOrgList.add(assistantOrg.getAssistantOrgId().toString());
              }
            } else {
              assistOrgList.add(assistantOrgId);
            }
            // begin
            for (int i = 0; i < assistOrgList.size(); i++) {
              assistantOrgId = (String) assistOrgList.get(i);
              FiveLevelQueryDTO fiveLevelQueryDTO = new FiveLevelQueryDTO();
              // ת��������˾����
              String assistantOrgNameTable = assistantOrgDAO
                  .queryAssistantOrgNameByAssistantOrgId(assistantOrgId);
              fiveLevelQueryDTO.setAssistantOrgName(assistantOrgNameTable);
              list1 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsNormalBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list1.isEmpty()) {
                for (int j = 0; j < list1.size(); j++) {
                  FiveLevelQueryDTO dto1 = (FiveLevelQueryDTO) list1.get(j);
                  fiveLevelQueryDTO.setNormalCount(dto1.getNormalCount());// ��������
                  fiveLevelQueryDTO.setNormalValue(dto1.getNormalValue());// �������
                }
              }
              list2 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsAttentionBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list2.isEmpty()) {
                for (int j = 0; j < list2.size(); j++) {
                  FiveLevelQueryDTO dto2 = (FiveLevelQueryDTO) list2.get(j);
                  fiveLevelQueryDTO.setAttentionCount(dto2.getAttentionCount());// ��ע����
                  fiveLevelQueryDTO.setAttentionValue(dto2.getAttentionValue());// ��ע���
                }
              }
              list3 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsSecondaryBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list3.isEmpty()) {
                for (int j = 0; j < list3.size(); j++) {
                  FiveLevelQueryDTO dto3 = (FiveLevelQueryDTO) list3.get(j);
                  fiveLevelQueryDTO.setSecondaryCount(dto3.getSecondaryCount());// �μ�����
                  fiveLevelQueryDTO.setSecondaryValue(dto3.getSecondaryValue());// �μ����
                }
              }
              list4 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsShadinessBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list4.isEmpty()) {
                for (int j = 0; j < list4.size(); j++) {
                  FiveLevelQueryDTO dto4 = (FiveLevelQueryDTO) list4.get(j);
                  fiveLevelQueryDTO.setShadinessCount(dto4.getShadinessCount());// ���ɻ���
                  fiveLevelQueryDTO.setShadinessValue(dto4.getShadinessValue());// ���ɽ��
                }
              }
              list5 = borrowerAccDAO
                  .queryFiveLevelQueryListByCriterionsDamnifyBank(officeCode,
                      loanBankId, developerId, floorId, assistantOrgId,
                      securityInfo);
              if (!list5.isEmpty()) {
                for (int j = 0; j < list5.size(); j++) {
                  FiveLevelQueryDTO dto5 = (FiveLevelQueryDTO) list5.get(j);
                  fiveLevelQueryDTO.setDamnifyCount(dto5.getDamnifyCount());// ��ʧ����
                  fiveLevelQueryDTO.setDamnifyValue(dto5.getDamnifyValue());// ��ʧ���
                }
              }
              // �ϼ�
              // ����
              int totalCount = new Integer(fiveLevelQueryDTO.getNormalCount())
                  .intValue()
                  + new Integer(fiveLevelQueryDTO.getAttentionCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getSecondaryCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getShadinessCount())
                      .intValue()
                  + new Integer(fiveLevelQueryDTO.getDamnifyCount()).intValue();
              // ���
              BigDecimal totalValue = fiveLevelQueryDTO.getNormalValue().add(
                  fiveLevelQueryDTO.getAttentionValue().add(
                      fiveLevelQueryDTO.getSecondaryValue().add(
                          fiveLevelQueryDTO.getShadinessValue().add(
                              fiveLevelQueryDTO.getDamnifyValue()))));
              fiveLevelQueryDTO.setTotalCount(totalCount + "");
              fiveLevelQueryDTO.setTotalValue(totalValue);
              // ������
              // ���� = ���μ�+ ����+ ��ʧ���Ļ���/�ܻ���
              if (totalCount != 0 && totalValue.intValue() != 0) {
                int badRateCount = new Integer(fiveLevelQueryDTO
                    .getSecondaryCount()).intValue()
                    + new Integer(fiveLevelQueryDTO.getShadinessCount())
                        .intValue()
                    + new Integer(fiveLevelQueryDTO.getDamnifyCount())
                        .intValue();
                // ��� = ���μ�+ ����+ ��ʧ���Ľ��/�ܽ��
                BigDecimal badRateValue = fiveLevelQueryDTO.getSecondaryValue()
                    .add(
                        fiveLevelQueryDTO.getShadinessValue().add(
                            fiveLevelQueryDTO.getDamnifyValue())).divide(
                        totalValue, 4, BigDecimal.ROUND_HALF_DOWN).multiply(
                        new BigDecimal(100)).divide(new BigDecimal(1), 2,
                        BigDecimal.ROUND_HALF_DOWN);
                fiveLevelQueryDTO.setBadRateCount(new BigDecimal(badRateCount)
                    .divide(new BigDecimal(totalCount), 4,
                        BigDecimal.ROUND_HALF_DOWN).multiply(
                        new BigDecimal(100)).divide(new BigDecimal(1), 2,
                        BigDecimal.ROUND_HALF_DOWN).toString());
                fiveLevelQueryDTO.setBadRateValue(badRateValue);
              }
              returnList.add(fiveLevelQueryDTO);
              // end
            }
          } // if_mode5
        } // if_����
      } // if_type
      fiveLevelQueryAF.setList(returnList);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return fiveLevelQueryAF;
  }
}