package org.xpup.hafmis.sysloan.common.loanconditionsset;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.CardMunChange;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentTailDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.sysloan.common.dao.AssistantBorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.HousesDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanConditionsSetDAO;
import org.xpup.hafmis.sysloan.common.dao.SpecialBorrowerDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantBorrower;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.common.domain.entity.Houses;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTbNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTdNewAF;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

public class LoanConditionsParamSetBS implements ILoanConditionsParamSetBS {
  // 1.�Ƿ��д����ʸ� ����boolean
  // 2.�ɴ������ ����BigDecimal
  private LoanConditionsSetDAO loanConditionsSetDAO = null;

  private EmpDAO empDAO = null;

  private ChgPaymentTailDAO chgPaymentTailDAO = null;

  private BorrowerDAO borrowerDAO = null;

  private HousesDAO housesDAO = null;

  private SpecialBorrowerDAO specialBorrowerDAO = null;

  private OrgDAO orgDAO = null;

  private AssistantBorrowerDAO assistantBorrowerDAO = null;

  public void setSpecialBorrowerDAO(SpecialBorrowerDAO specialBorrowerDAO) {
    this.specialBorrowerDAO = specialBorrowerDAO;
  }

  public void setHousesDAO(HousesDAO housesDAO) {
    this.housesDAO = housesDAO;
  }

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public void setChgPaymentTailDAO(ChgPaymentTailDAO chgPaymentTailDAO) {
    this.chgPaymentTailDAO = chgPaymentTailDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public void setLoanConditionsSetDAO(LoanConditionsSetDAO loanConditionsSetDAO) {
    this.loanConditionsSetDAO = loanConditionsSetDAO;
  }

  // 1.�Ƿ��д����ʸ� ����boolean
  public boolean isCanSysLoan(LoanapplyNewAF loanapplyaf,
      SecurityInfo securityInfo) throws BusinessException {
    String office = loanapplyaf.getBorrower().getOffice();
    String empid = loanapplyaf.getBorrower().getEmpId().toString();

    // ����ط����ϸ��ж���Ϊ��ְ��������ѡ����ְ�����ʱҲ�����������,����
    // loanapplyaf��borrower�е�empid������Բ�û�и�ֵ
    if (empid.equals("0")) {
      empid = loanapplyaf.getEmpid();
    }
    String orgid = loanapplyaf.getBorrower().getOrgId().toString();
    String empname = loanapplyaf.getBorrower().getBorrowerName().toString()
        .trim();
    String cardnum = loanapplyaf.getBorrower().getCardNum().toString().trim();
    String specialid = specialBorrowerDAO.findSpecialByBorrownameCardnum(
        empname, cardnum);// ���ݽ��������֤���Ų�ѯ�Ƿ����������˲�����δ����״̬
    int openMonth = 0;
    int qianJiao = 0;
    int lianxuJiao = 0;
    boolean ischeck = true;
    if (specialid == null) {
      try {
        // ְ��������״̬�Ƿ��������ò���
        String param_value1 = loanConditionsSetDAO
            .querySyscollectionState(office);
        // �����������������Ӧ����?����
        LoanConditionsParamSetDTO loanConditionsParamSetDTO2 = loanConditionsSetDAO
            .querySyscollectionMonth(office);
        LoanConditionsParamSetDTO loanConditionsParamSetDTO3 = loanConditionsSetDAO
            .querySyscollectionOpenAccMonth(office);
        List orgNaturelist = loanConditionsSetDAO.queryOrgNature(office);
        LoanConditionsParamSetDTO loanConditionsParamSetDTO5 = loanConditionsSetDAO
            .queryOweMonth(office);
        // ������Ƿ������ҪС��?����
        LoanConditionsParamSetDTO loanConditionsParamSetDTO6 = loanConditionsSetDAO
            .querySyscollectionOpenAccMonth_wsh(office);
        if ((!empid.equals("") && !empid.equals("0"))
            && ((!orgid.equals("") && !orgid.equals("0")))) {// ˵���Ǵӹ������������
          String payState = empDAO.findEmpInfoPayState(empid, orgid);// ��ְ�����˻�״̬
          // �������������֤�Ų�ѯ������Ϊ����˻��߸����������û��״̬�ǻ����еļ�¼
          String empEleven = loanConditionsSetDAO.queryempLoanIsEleven(empname,
              cardnum);
          String empElevena = loanConditionsSetDAO.queryempLoanIsElevena(
              empname, cardnum);

          String opendate = empDAO.findEmpInfoOpenDate_ws(empid, orgid);
          String orgPayMonth = empDAO.findEmpInfoOpenDate_wsh(empid, orgid);
          String empPayMonth = empDAO.findEmpInfoOpenDate_wsh_emp(empid, orgid);
          // String month="";
          // if(Integer.parseInt(orgPayMonth)-Integer.parseInt(empPayMonth)>0){
          // month=orgPayMonth;
          // }else{
          // month=empPayMonth;
          // }
          String month_1 = "";
          if (Integer.parseInt(orgPayMonth) - Integer.parseInt(empPayMonth) > 0) {
            month_1 = empPayMonth;
          } else {
            month_1 = orgPayMonth;
          }
          String plbizdate = securityInfo.getUserInfo().getPlbizDate();

          try {
            openMonth = BusiTools.monthInterval(opendate.substring(0, 6),
                plbizdate.substring(0, 6));// �������ٸ���
            qianJiao = BusiTools.monthInterval(month_1.substring(0, 6),
                plbizdate.substring(0, 6));// Ƿ�ɶ��ٸ���
            lianxuJiao = BusiTools.monthInterval(opendate.substring(0, 6),
                month_1);// Ƿ�ɶ��ٸ���
          } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          // ˵����ְ���䵱�˽���˻��߸�������˲��Һ�ͬ״̬Ϊ������
          if (Integer.parseInt(empEleven) > 0) {
            throw new BusinessException("��ְ�����ں�ͬ״̬Ϊ�����еĴ���");
          } else {
            if (Integer.parseInt(empElevena) > 0) {
              throw new BusinessException("��ְ��������ش���");
            }
            ischeck = true;
          }
          if (param_value1 != null) {// ˵����������Ч
            if (param_value1.equals("1")) {
              if (payState.equals("1")) {
                ischeck = true;
              } else {
                throw new BusinessException("��ְ���������˻�״̬����ȷ");
              }
            }

          }
          // ����ȥ������������������ƣ��ڹ���ʱ�ж�
          // if (loanConditionsParamSetDTO2 != null
          // && loanConditionsParamSetDTO2.getParamExplain() != null) {//
          // ˵����������Ч
          // int monthi2 = Integer.parseInt(loanConditionsParamSetDTO2
          // .getParamExplain());
          // // boolean flag
          // //
          // =loanConditionsSetDAO.queryMonthCounts(empid,orgid,loanConditionsParamSetDTO2.getParamExplain());
          // // //�����������,���صĶԴ�����Ƿ���ϲ�������
          // if (lianxuJiao > monthi2) {
          // ischeck = true;
          // } else {
          // throw new BusinessException("�����������������Ӧ����" + monthi2 + "��");
          // }
          // }
          // if (loanConditionsParamSetDTO3 != null
          // && loanConditionsParamSetDTO3.getParamExplain() != null) {//
          // ˵����������Ч
          // int monthi2 = Integer.parseInt(loanConditionsParamSetDTO3
          // .getParamExplain());
          // if (openMonth > monthi2) {
          // ischeck = true;
          // } else {
          // throw new BusinessException("�����𿪻�ʱ��Ӧ����" + monthi2 + "��");
          // }
          // }
          // if (loanConditionsParamSetDTO6 != null
          // && loanConditionsParamSetDTO6.getParamExplain() != null) {//
          // ˵����������Ч
          // int monthi2 = Integer.parseInt(loanConditionsParamSetDTO6
          // .getParamExplain());
          // if (qianJiao < monthi2) {
          // ischeck = true;
          // } else {
          // throw new BusinessException("������Ƿ������ҪС��" + monthi2 + "��");
          // }
          // }
        }
        if (loanConditionsParamSetDTO5 != null
            && loanConditionsParamSetDTO5.getParamExplain() != null) {// ˵����������Ч
          int monthi2 = Integer.parseInt(loanConditionsParamSetDTO5
              .getParamExplain());
          String id = "";
          if (cardnum.length() == 18) {
            id = CardMunChange.get15Id(cardnum);
          }
          if (cardnum.length() == 15) {
            id = CardMunChange.get18Id(cardnum);
          }
          List colist = borrowerDAO.findEmpinborrowByEmpid_wsh(empname,
              cardnum, id);
          for (int k = 0; k < colist.size(); k++) {
            String contractId = (String) colist.get(k);
            String owemonthai = chgPaymentTailDAO
                .countOverdueInfoOweMonth(contractId);
            int owemonthi = Integer.parseInt(owemonthai);
            if (owemonthi <= monthi2) {
              ischeck = true;
            } else {
              throw new BusinessException("�����ڲ��ܳ���" + monthi2 + "��");
            }
          }
        }
        // ���ݵ�λ�����ж�

        if (!empid.equals("") && !empid.equals("0")) {
          Org org = orgDAO.queryById(new Integer(orgid));
          for (int j = 0; j < orgNaturelist.size(); j++) {
            LoanConditionsParamSetDTO loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) orgNaturelist
                .get(j);
            String orgNature = loanConditionsParamSetDTO.getParamExplain();
            String paramNum = loanConditionsParamSetDTO.getParamNum();
            String count = "";
            if (orgNature != null
                && orgNature.equals(org.getOrgInfo().getCharacter())) {
              if (paramNum.equals("17")) {
                LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
                    .queryOverHousePriceS_wsh1(office);
                count = loanConditionsSetDAO
                    .querySyscollectionState_org_emp_count(orgid);
                if (loanConditionsParamSetDTO_1.getParamExplain() != null) {
                  if (Integer.parseInt(count) < Integer
                      .parseInt(loanConditionsParamSetDTO_1.getParamExplain())) {
                    throw new BusinessException("��λ������������ "
                        + loanConditionsParamSetDTO_1.getParamExplain() + "��");
                  } else {
                    ischeck = true;
                  }
                }

                LoanConditionsParamSetDTO loanConditionsParamSetDTO_2 = loanConditionsSetDAO
                    .queryOverHousePriceS_wsh2(office);
                // ȥ����������������ƣ��ڹ���ʱ����
                // if (loanConditionsParamSetDTO_2.getParamExplain() != null) {
                // // boolean flag =loanConditionsSetDAO.queryMonthCounts(empid
                // //
                // .toString(),orgid,loanConditionsParamSetDTO_2.getParamExplain());
                // // //�����������
                // // if(!flag){
                // // throw new BusinessException("��λ����������� " +
                // // loanConditionsParamSetDTO_2.getParamExplain()
                // // + "��");
                // // }else{
                // // ischeck=true;
                // // }
                // if (lianxuJiao > Integer.parseInt(loanConditionsParamSetDTO_2
                // .getParamExplain())) {
                // ischeck = true;
                // } else {
                // throw new BusinessException("�����������������Ӧ����"
                // + loanConditionsParamSetDTO_2.getParamExplain() + "��");
                // }
                // }

              }
              if (paramNum.equals("16")) {
                LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
                    .queryOverHousePriceS_wsh3(office);
                count = loanConditionsSetDAO
                    .querySyscollectionState_org_emp_count(orgid);
                if (loanConditionsParamSetDTO_1.getParamExplain() != null) {
                  if (Integer.parseInt(count) < Integer
                      .parseInt(loanConditionsParamSetDTO_1.getParamExplain())) {
                    throw new BusinessException("��λ������������ "
                        + loanConditionsParamSetDTO_1.getParamExplain() + "��");
                  } else {
                    ischeck = true;
                  }

                }
                LoanConditionsParamSetDTO loanConditionsParamSetDTO_2 = loanConditionsSetDAO
                    .queryOverHousePriceS_wsh4(office);
                if (loanConditionsParamSetDTO_2.getParamExplain() != null) {
                  // boolean flag =loanConditionsSetDAO.queryMonthCounts(empid
                  // .toString(),orgid,loanConditionsParamSetDTO_2.getParamExplain());
                  // //�����������
                  // if(!flag){
                  // throw new BusinessException("��λ����������� " +
                  // loanConditionsParamSetDTO_2.getParamExplain()
                  // + "��");
                  // }else{
                  // ischeck=true;
                  // }
                  if (lianxuJiao > Integer.parseInt(loanConditionsParamSetDTO_2
                      .getParamExplain())) {
                    ischeck = true;
                  } else {
                    throw new BusinessException("�����������������Ӧ����"
                        + loanConditionsParamSetDTO_2.getParamExplain() + "��");
                  }
                }
              }
              if (paramNum.equals("15")) {
                LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
                    .queryOverHousePriceS_wsh5(office);
                count = loanConditionsSetDAO
                    .querySyscollectionState_org_emp_count(orgid);
                if (loanConditionsParamSetDTO_1.getParamExplain() != null) {
                  if (Integer.parseInt(count) < Integer
                      .parseInt(loanConditionsParamSetDTO_1.getParamExplain())) {
                    throw new BusinessException("��λ������������ "
                        + loanConditionsParamSetDTO_1.getParamExplain() + "��");
                  } else {
                    ischeck = true;
                  }
                }
                LoanConditionsParamSetDTO loanConditionsParamSetDTO_2 = loanConditionsSetDAO
                    .queryOverHousePriceS_wsh6(office);
                if (loanConditionsParamSetDTO_2.getParamExplain() != null) {
                  // boolean flag =loanConditionsSetDAO.queryMonthCounts(empid
                  // .toString(),orgid,loanConditionsParamSetDTO_2.getParamExplain());
                  // //�����������
                  // if(!flag){
                  // throw new BusinessException("��λ����������� " +
                  // loanConditionsParamSetDTO_2.getParamExplain()
                  // + "��");
                  // }else{
                  // ischeck=true;
                  // }
                  if (lianxuJiao > Integer.parseInt(loanConditionsParamSetDTO_2
                      .getParamExplain())) {
                    ischeck = true;
                  } else {
                    throw new BusinessException("�����������������Ӧ����"
                        + loanConditionsParamSetDTO_2.getParamExplain() + "��");
                  }
                }
              }
            }
          }
        }
        // ���ݵ�λ�����ж�
      } catch (BusinessException be) {
        throw be;
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return ischeck;
  }

  // 1.�Ƿ��д����ʸ� ����boolean
  public boolean isCanSysLoanTb(LoanapplyTbNewAF loanapplyTbNewAF,
      SecurityInfo securityInfo) throws BusinessException {
    String contract_id = loanapplyTbNewAF.getContractId();
    // ���´��ý���˵�ȡ
    String office = borrowerDAO.findBorrrowInfoByContractid(contract_id)
        .getOffice();
    Borrower borrower = borrowerDAO.queryById(contract_id);
    String borrowerEmpId = null;
    if (borrower.getEmpId() != null) {
      borrowerEmpId = borrower.getEmpId().toString();
    }
    String empid = loanapplyTbNewAF.getEmpId();

    String orgid = loanapplyTbNewAF.getOrgId();
    String empname = loanapplyTbNewAF.getName();
    String cardnum = loanapplyTbNewAF.getCardNum();
    String specialid = specialBorrowerDAO.findSpecialByBorrownameCardnum(
        empname, cardnum);// ���ݽ��������֤���Ų�ѯ�Ƿ����������˲�����δ����״̬
    int openMonth = 0;
    int qianJiao = 0;
    int lianxuJiao = 0;
    boolean ischeck = true;
    if (specialid == null) {
      try {
        // ְ��������״̬�Ƿ��������ò���
        String param_value1 = loanConditionsSetDAO
            .querySyscollectionState(office);
        // �����������������Ӧ����?����
        LoanConditionsParamSetDTO loanConditionsParamSetDTO2 = loanConditionsSetDAO
            .querySyscollectionMonth(office);
        LoanConditionsParamSetDTO loanConditionsParamSetDTO3 = loanConditionsSetDAO
            .querySyscollectionOpenAccMonth(office);
        List orgNaturelist = loanConditionsSetDAO.queryOrgNature(office);
        LoanConditionsParamSetDTO loanConditionsParamSetDTO5 = loanConditionsSetDAO
            .queryOweMonth(office);
        // ������Ƿ������ҪС��?����
        LoanConditionsParamSetDTO loanConditionsParamSetDTO6 = loanConditionsSetDAO
            .querySyscollectionOpenAccMonth_wsh(office);
        if ((!empid.equals("") && !empid.equals("0"))
            && ((!orgid.equals("") && !orgid.equals("0")))) {// ˵���Ǵӹ������������
          String payState = empDAO.findEmpInfoPayState(empid, orgid);// ��ְ�����˻�״̬
          // �������������֤�Ų�ѯ������Ϊ����˻��߸����������û��״̬�ǻ����еļ�¼
          String empEleven = loanConditionsSetDAO.queryempLoanIsEleven(empname,
              cardnum);

          String opendate = empDAO.findEmpInfoOpenDate_ws(empid, orgid);
          String orgPayMonth = empDAO.findEmpInfoOpenDate_wsh(empid, orgid);
          String empPayMonth = empDAO.findEmpInfoOpenDate_wsh_emp(empid, orgid);
          // String month="";
          // if(Integer.parseInt(orgPayMonth)-Integer.parseInt(empPayMonth)>0){
          // month=orgPayMonth;
          // }else{
          // month=empPayMonth;
          // }
          String month_1 = "";
          if (Integer.parseInt(orgPayMonth) - Integer.parseInt(empPayMonth) > 0) {
            month_1 = empPayMonth;
          } else {
            month_1 = orgPayMonth;
          }
          String plbizdate = securityInfo.getUserInfo().getPlbizDate();

          try {
            openMonth = BusiTools.monthInterval(opendate.substring(0, 6),
                plbizdate.substring(0, 6));// �������ٸ���
            qianJiao = BusiTools.monthInterval(month_1.substring(0, 6),
                plbizdate.substring(0, 6));// Ƿ�ɶ��ٸ���
            lianxuJiao = BusiTools.monthInterval(opendate.substring(0, 6),
                month_1);// Ƿ�ɶ��ٸ���
          } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          // ˵����ְ���䵱�˽���˻��߸�������˲��Һ�ͬ״̬Ϊ������
          if (Integer.parseInt(empEleven) > 0) {
            throw new BusinessException("��ְ�����ں�ͬ״̬Ϊ�����еĴ���");
          } else {
            ischeck = true;
          }
          if (borrowerEmpId == null) {
            if (param_value1 != null) {// ˵����������Ч
              if (param_value1.equals("1")) {
                if (payState.equals("1")) {
                  ischeck = true;
                } else {
                  throw new BusinessException("��ְ���������˻�״̬����ȷ");
                }
              }

            }
            // if (loanConditionsParamSetDTO2 != null
            // && loanConditionsParamSetDTO2.getParamExplain() != null) {//
            // ˵����������Ч
            // int monthi2 = Integer.parseInt(loanConditionsParamSetDTO2
            // .getParamExplain());
            // // boolean flag
            // //
            // =loanConditionsSetDAO.queryMonthCounts(empid,orgid,loanConditionsParamSetDTO2.getParamExplain());
            // // //�����������,���صĶԴ�����Ƿ���ϲ�������
            // if (lianxuJiao > monthi2) {
            // ischeck = true;
            // } else {
            // throw new BusinessException("�����������������Ӧ����" + monthi2 + "��");
            // }
            // }
            // if (loanConditionsParamSetDTO3 != null
            // && loanConditionsParamSetDTO3.getParamExplain() != null) {//
            // ˵����������Ч
            // int monthi2 = Integer.parseInt(loanConditionsParamSetDTO3
            // .getParamExplain());
            // if (openMonth > monthi2) {
            // ischeck = true;
            // } else {
            // throw new BusinessException("�����𿪻�ʱ��Ӧ����" + monthi2 + "��");
            // }
            // }
            // if (loanConditionsParamSetDTO6 != null
            // && loanConditionsParamSetDTO6.getParamExplain() != null) {//
            // ˵����������Ч
            // int monthi2 = Integer.parseInt(loanConditionsParamSetDTO6
            // .getParamExplain());
            // if (qianJiao < monthi2) {
            // ischeck = true;
            // } else {
            // throw new BusinessException("������Ƿ������ҪС��" + monthi2 + "��");
            // }
            // }
            if (loanConditionsParamSetDTO5 != null
                && loanConditionsParamSetDTO5.getParamExplain() != null) {// ˵����������Ч
              int monthi2 = Integer.parseInt(loanConditionsParamSetDTO5
                  .getParamExplain());
              String id = "";
              if (cardnum.length() == 18) {
                id = CardMunChange.get15Id(cardnum);
              }
              if (cardnum.length() == 15) {
                id = CardMunChange.get18Id(cardnum);
              }
              List colist = borrowerDAO.findEmpinborrowByEmpid_wsh(empname,
                  cardnum, id);
              for (int k = 0; k < colist.size(); k++) {
                String contractId = (String) colist.get(k);
                String owemonthai = chgPaymentTailDAO
                    .countOverdueInfoOweMonth(contractId);
                int owemonthi = Integer.parseInt(owemonthai);
                if (owemonthi <= monthi2) {
                  ischeck = true;
                } else {
                  throw new BusinessException("�����ڲ��ܳ���" + monthi2 + "��");
                }
              }
            }
            // ���ݵ�λ�����ж�

            if (!empid.equals("") && !empid.equals("0")) {
              Org org = orgDAO.queryById(new Integer(orgid));
              for (int j = 0; j < orgNaturelist.size(); j++) {
                LoanConditionsParamSetDTO loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) orgNaturelist
                    .get(j);
                String orgNature = loanConditionsParamSetDTO.getParamExplain();
                String paramNum = loanConditionsParamSetDTO.getParamNum();
                String count = "";
                if (orgNature != null
                    && orgNature.equals(org.getOrgInfo().getCharacter())) {
                  if (paramNum.equals("17")) {
                    LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
                        .queryOverHousePriceS_wsh1(office);
                    count = loanConditionsSetDAO
                        .querySyscollectionState_org_emp_count(orgid);
                    if (loanConditionsParamSetDTO_1.getParamExplain() != null) {
                      if (Integer.parseInt(count) < Integer
                          .parseInt(loanConditionsParamSetDTO_1
                              .getParamExplain())) {
                        throw new BusinessException("��λ������������ "
                            + loanConditionsParamSetDTO_1.getParamExplain()
                            + "��");
                      } else {
                        ischeck = true;
                      }
                    }

                    LoanConditionsParamSetDTO loanConditionsParamSetDTO_2 = loanConditionsSetDAO
                        .queryOverHousePriceS_wsh2(office);
                    if (loanConditionsParamSetDTO_2.getParamExplain() != null) {
                      // boolean flag
                      // =loanConditionsSetDAO.queryMonthCounts(empid
                      // .toString(),orgid,loanConditionsParamSetDTO_2.getParamExplain());
                      // //�����������
                      // if(!flag){
                      // throw new BusinessException("��λ����������� " +
                      // loanConditionsParamSetDTO_2.getParamExplain()
                      // + "��");
                      // }else{
                      // ischeck=true;
                      // }
                      if (lianxuJiao > Integer
                          .parseInt(loanConditionsParamSetDTO_2
                              .getParamExplain())) {
                        ischeck = true;
                      } else {
                        throw new BusinessException("�����������������Ӧ����"
                            + loanConditionsParamSetDTO_2.getParamExplain()
                            + "��");
                      }
                    }

                  }
                  if (paramNum.equals("16")) {
                    LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
                        .queryOverHousePriceS_wsh3(office);
                    count = loanConditionsSetDAO
                        .querySyscollectionState_org_emp_count(orgid);
                    if (loanConditionsParamSetDTO_1.getParamExplain() != null) {
                      if (Integer.parseInt(count) < Integer
                          .parseInt(loanConditionsParamSetDTO_1
                              .getParamExplain())) {
                        throw new BusinessException("��λ������������ "
                            + loanConditionsParamSetDTO_1.getParamExplain()
                            + "��");
                      } else {
                        ischeck = true;
                      }

                    }
                    LoanConditionsParamSetDTO loanConditionsParamSetDTO_2 = loanConditionsSetDAO
                        .queryOverHousePriceS_wsh4(office);
                    if (loanConditionsParamSetDTO_2.getParamExplain() != null) {
                      // boolean flag
                      // =loanConditionsSetDAO.queryMonthCounts(empid
                      // .toString(),orgid,loanConditionsParamSetDTO_2.getParamExplain());
                      // //�����������
                      // if(!flag){
                      // throw new BusinessException("��λ����������� " +
                      // loanConditionsParamSetDTO_2.getParamExplain()
                      // + "��");
                      // }else{
                      // ischeck=true;
                      // }
                      if (lianxuJiao > Integer
                          .parseInt(loanConditionsParamSetDTO_2
                              .getParamExplain())) {
                        ischeck = true;
                      } else {
                        throw new BusinessException("�����������������Ӧ����"
                            + loanConditionsParamSetDTO_2.getParamExplain()
                            + "��");
                      }
                    }
                  }
                  if (paramNum.equals("15")) {
                    LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
                        .queryOverHousePriceS_wsh5(office);
                    count = loanConditionsSetDAO
                        .querySyscollectionState_org_emp_count(orgid);
                    if (loanConditionsParamSetDTO_1.getParamExplain() != null) {
                      if (Integer.parseInt(count) < Integer
                          .parseInt(loanConditionsParamSetDTO_1
                              .getParamExplain())) {
                        throw new BusinessException("��λ������������ "
                            + loanConditionsParamSetDTO_1.getParamExplain()
                            + "��");
                      } else {
                        ischeck = true;
                      }
                    }
                    LoanConditionsParamSetDTO loanConditionsParamSetDTO_2 = loanConditionsSetDAO
                        .queryOverHousePriceS_wsh6(office);
                    if (loanConditionsParamSetDTO_2.getParamExplain() != null) {
                      // boolean flag
                      // =loanConditionsSetDAO.queryMonthCounts(empid
                      // .toString(),orgid,loanConditionsParamSetDTO_2.getParamExplain());
                      // //�����������
                      // if(!flag){
                      // throw new BusinessException("��λ����������� " +
                      // loanConditionsParamSetDTO_2.getParamExplain()
                      // + "��");
                      // }else{
                      // ischeck=true;
                      // }
                      if (lianxuJiao > Integer
                          .parseInt(loanConditionsParamSetDTO_2
                              .getParamExplain())) {
                        ischeck = true;
                      } else {
                        throw new BusinessException("�����������������Ӧ����"
                            + loanConditionsParamSetDTO_2.getParamExplain()
                            + "��");
                      }
                    }
                  }
                }
              }
            }
          }

        }

        // ���ݵ�λ�����ж�
      } catch (BusinessException be) {
        throw be;
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return ischeck;
  }

  // 2.�ɴ������ ����BigDecimal
  public BigDecimal canSysLoanMoney(LoanapplyTdNewAF loanapplytdnewAF,
      SecurityInfo securityInfo) throws BusinessException {

    String specialid = specialBorrowerDAO.findSpecialByBorrownameCardnum(
        loanapplytdnewAF.getBorrowerName().trim(), loanapplytdnewAF
            .getCardNum().trim());// ���ݽ��������֤���Ų�ѯ�Ƿ����������˲�����δ����״̬

    try {
      if (specialid == null) {
        int loanlimit = Integer.parseInt(loanapplytdnewAF.getLoantimeLimit());
        int loanmoney = Integer.parseInt(loanapplytdnewAF.getLoanMoney());
        BigDecimal yueHuanBenXi = new BigDecimal(0.0);
        if (loanapplytdnewAF.getCorpusInterest() != null
            && !"".equals(loanapplytdnewAF.getCorpusInterest())) {
          yueHuanBenXi = new BigDecimal(loanapplytdnewAF.getCorpusInterest());
        }
        String contactid = loanapplytdnewAF.getContractId();
        Borrower borrower = borrowerDAO.queryById(contactid);
        String office = borrower.getOffice();
        List agelist = loanConditionsSetDAO.queryManAge(office);
        List orgNaturelist = loanConditionsSetDAO.queryOrgNature(office);
        String sex = borrower.getSex();
        int age = Integer.parseInt(borrower.getAge().toString());
        int tol = 0;
        int sy = loanlimit % 12;
        if (sy == 0) {
          tol = age + loanlimit / 12;
        } else {
          tol = age + loanlimit / 12 + 1;
        }
        for (int i = 0; i < agelist.size(); i++) {
          LoanConditionsParamSetDTO loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
              .get(i);
          String sexdto = loanConditionsParamSetDTO.getParamValue();
          int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
              .getParamExplain());
          if (sex.equals("1")) {// ��
            if (sexdto.equals("1")) {
              if (tol > paramExplain) {
                throw new BusinessException("���ܴ���,����Ӵ������޹���");
              }
            }

          }
          if (sex.equals("2")) {// Ů

            if (sexdto.equals("2")) {
              if (tol > paramExplain) {
                throw new BusinessException("���ܴ���,����Ӵ������޹���");
              }
            }
          }
        }

        List loanlimitlist = loanConditionsSetDAO.queryLoanLimit(office);
        for (int j = 0; j < loanlimitlist.size(); j++) {
          LoanConditionsParamSetDTO loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) loanlimitlist
              .get(j);
          String type = loanConditionsParamSetDTO.getParamValue();
          int loanlimitetime = Integer.parseInt(loanConditionsParamSetDTO
              .getParamExplain());
          if (type.equals("1")) {
            if (loanlimitetime > loanlimit) {
              throw new BusinessException("�������޲�����" + loanlimitetime + "��");
            }
          }
          if (type.equals("2")) {
            if (loanlimitetime < loanlimit) {
              throw new BusinessException("�������޲�����" + loanlimitetime + "��");
            }
          }
        }

        String num = borrowerDAO.countPeopleNum(contactid);
        if (num.equals("0")) {// ˵��û�и��������
          LoanConditionsParamSetDTO loanConditionsParamSetDTO = loanConditionsSetDAO
              .queryLoanMoney(office, "0");
          int loanm = Integer.parseInt(loanConditionsParamSetDTO
              .getParamExplain());
          if (loanConditionsParamSetDTO.getParamExplain() != null) {// ������
            if (loanmoney > loanm) {
              throw new BusinessException("����ӵ�й����������ܳ���" + loanm + "Ԫ");
            }
          }

        } else {
          LoanConditionsParamSetDTO loanConditionsParamSetDTO = loanConditionsSetDAO
              .queryLoanMoney(office, "1");
          int loanm = Integer.parseInt(loanConditionsParamSetDTO
              .getParamExplain());
          if (loanConditionsParamSetDTO.getParamExplain() != null) {// ������
            if (loanmoney > loanm) {
              throw new BusinessException("�и��������˵Ĵ�����ܳ���" + loanm + "Ԫ");
            }
          }

        }
        BigDecimal yuE = new BigDecimal(0.00);
        BigDecimal jiaoE = new BigDecimal(0.00);
        BigDecimal sum = new BigDecimal(0.00);
        // ������(���ó��������ͥ��Ա����������������ס������������)
        if (num.equals("0")) {// ˵��û�и��������
          LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
              .queryOverHousePriceS_1(office);
          LoanConditionsParamSetDTO loanConditionsParamSetDTO_14 = loanConditionsSetDAO
              .queryOverHousePriceS_14(office);
          LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
          borrower = borrowerDAO.queryById(contactid);
          if (borrower.getEmpId() == null && "".equals(borrower.getEmpId())) {
            throw new BusinessException("�����δ����ס��������");
          }
          if (borrower.getEmpId() != null || !"".equals(borrower.getEmpId())) {
            Emp emp = empDAO.queryByCriterions(borrower.getEmpId().toString(),
                borrower.getOrgId().toString());

            if (loanConditionsParamSetDTO_14.getParamExplain() != null) {// ������
              BigDecimal money = emp.getSalaryBase()
                  .multiply(
                      new BigDecimal(loanConditionsParamSetDTO_14
                          .getParamExplain())).multiply(
                      new BigDecimal(loanlimit));
              if (new BigDecimal(loanmoney).compareTo(money) > 0) {
                throw new BusinessException("����������뻹��������ó��� "
                    + loanConditionsParamSetDTO_14.getParamExplain() + "%");
              }
            }
            yuE = emp.getPreBalance().add(emp.getCurBalance());
            for (int i = 0; i < agelist.size(); i++) {
              loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                  .get(i);
              String sexdto = loanConditionsParamSetDTO.getParamValue();
              int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                  .getParamExplain());
              if (sex.equals("1")) {// ������Ա���
                if (sexdto.equals("1")) {
                  jiaoE = (emp.getOrgPay().add(emp.getEmpPay())).multiply(
                      new BigDecimal(12)).multiply(
                      new BigDecimal(paramExplain
                          - borrower.getAge().intValue()));
                }

              }
              if (sex.equals("2")) {// Ů

                if (sexdto.equals("2")) {
                  jiaoE = (emp.getOrgPay().add(emp.getEmpPay())).multiply(
                      new BigDecimal(12)).multiply(
                      new BigDecimal(paramExplain
                          - borrower.getAge().intValue()));
                }
              }
            }
            sum = yuE.add(jiaoE);
          }

          int loanm = Integer.parseInt(loanConditionsParamSetDTO_1
              .getParamExplain());
          sum = sum.multiply(new BigDecimal(loanm));
          if (loanConditionsParamSetDTO_1.getParamExplain() != null) {// ������
            if (new BigDecimal(loanmoney).compareTo(sum) > 0) {
              throw new BusinessException("���ó��������ͥ��Ա����������������ס������������ " + loanm
                  + "��");
            }
          }

        } else {
          LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
              .queryOverHousePriceS_1(office);
          LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
          LoanConditionsParamSetDTO loanConditionsParamSetDTO_14 = loanConditionsSetDAO
              .queryOverHousePriceS_14(office);
          String fuzhuEmpId = borrowerDAO.countPeopleNum_EmpId(contactid);
          String fuzhuOrgId = borrowerDAO.countPeopleNum_Id(contactid);
          String fuzhuSex = borrowerDAO.countPeopleNum_Sex(contactid);
          borrower = borrowerDAO.queryById(contactid);
          if ((borrower.getEmpId() == null || "".equals(borrower.getEmpId()))
              && (fuzhuEmpId == null || "".equals(fuzhuEmpId))) {
            throw new BusinessException("����˼���������˾�δ����ס��������");
          }
          if (borrower.getEmpId() != null && !"".equals(borrower.getEmpId())) {
            Emp emp = empDAO.queryByCriterions(borrower.getEmpId().toString(),
                borrower.getOrgId().toString());
            if (loanConditionsParamSetDTO_14.getParamExplain() != null) {// ������
              // �����
              BigDecimal money = emp.getSalaryBase()
                  .multiply(
                      new BigDecimal(loanConditionsParamSetDTO_14
                          .getParamExplain())).multiply(
                      new BigDecimal(loanlimit));
              // ���������
              Emp emp_fuzhu = empDAO.queryByCriterions(fuzhuEmpId.toString(),
                  fuzhuOrgId.toString());
              money = money.add(emp_fuzhu.getSalaryBase()
                  .multiply(
                      new BigDecimal(loanConditionsParamSetDTO_14
                          .getParamExplain())).multiply(
                      new BigDecimal(loanlimit)));
              if (new BigDecimal(loanmoney).compareTo(money) > 0) {
                throw new BusinessException("����������뻹��������ó��� "
                    + loanConditionsParamSetDTO_14.getParamExplain() + "%");
              }
            }
            yuE = emp.getPreBalance().add(emp.getCurBalance());// .add((emp.getOrgPay().add(emp.getEmpPay()).multiply(new
            // BigDecimal(12))));

            for (int i = 0; i < agelist.size(); i++) {
              loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                  .get(i);
              String sexdto = loanConditionsParamSetDTO.getParamValue();
              int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                  .getParamExplain());
              if (sex.equals("1")) {// ������Ա���
                if (sexdto.equals("1")) {
                  jiaoE = (emp.getOrgPay().add(emp.getEmpPay())).multiply(
                      new BigDecimal(12)).multiply(
                      new BigDecimal(paramExplain
                          - borrower.getAge().intValue()));
                }

              }
              if (sex.equals("2")) {// Ů

                if (sexdto.equals("2")) {
                  jiaoE = (emp.getOrgPay().add(emp.getEmpPay())).multiply(
                      new BigDecimal(12)).multiply(
                      new BigDecimal(paramExplain
                          - borrower.getAge().intValue()));
                }
              }
            }
            sum = yuE.add(jiaoE);
          }
          if (fuzhuEmpId != null && !"".equals(fuzhuEmpId)) {
            Emp emp = empDAO.queryByCriterions(fuzhuEmpId.toString(),
                fuzhuOrgId.toString());
            yuE = yuE.add(emp.getPreBalance().add(emp.getCurBalance()));// .add((emp.getOrgPay().add(emp.getEmpPay()).multiply(new
            // BigDecimal(12))));

            for (int i = 0; i < agelist.size(); i++) {
              loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                  .get(i);
              String sexdto = loanConditionsParamSetDTO.getParamValue();
              int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                  .getParamExplain());
              String fuZhuAge = "";
              fuZhuAge = borrowerDAO.countPeopleNum_Age(contactid);
              if (fuzhuSex.equals("1")) {// ������Ա���
                if (sexdto.equals("1")) {
                  jiaoE = jiaoE.add((emp.getOrgPay().add(emp.getEmpPay()))
                      .multiply(new BigDecimal(12)).multiply(
                          new BigDecimal(paramExplain
                              - Integer.valueOf(fuZhuAge).intValue())));
                }

              }
              if (fuzhuSex.equals("2")) {// Ů

                if (sexdto.equals("2")) {
                  jiaoE = jiaoE.add((emp.getOrgPay().add(emp.getEmpPay()))
                      .multiply(new BigDecimal(12)).multiply(
                          new BigDecimal(paramExplain
                              - Integer.valueOf(fuZhuAge).intValue())));
                }
              }
            }
            sum = yuE.add(jiaoE);
          }

          int loanm = Integer.parseInt(loanConditionsParamSetDTO_1
              .getParamExplain());
          sum = sum.multiply(new BigDecimal(loanm));
          if (loanConditionsParamSetDTO_1.getParamExplain() != null) {// ������
            if (new BigDecimal(loanmoney).compareTo(sum) > 0) {
              throw new BusinessException("���ó��������ͥ��Ա����������������ס������������ " + loanm
                  + "��");
            }
          }

        }
        // ������(���ó��������ͥ��Ա����������������ס������������)
        // ���ݵ�λ�����ж�
        borrower = borrowerDAO.queryById(contactid);
        if (borrower.getEmpId() != null) {
          Org org = orgDAO
              .queryById(new Integer(borrower.getOrgId().toString()));
          for (int j = 0; j < orgNaturelist.size(); j++) {
            LoanConditionsParamSetDTO loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) orgNaturelist
                .get(j);
            String orgNature = loanConditionsParamSetDTO.getParamExplain();
            String count = "";
            if (orgNature != null
                && orgNature.equals(org.getOrgInfo().getCharacter())) {
              if (j == 0) {
                LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
                    .queryOverHousePriceS_wsh1(office);
                count = loanConditionsSetDAO
                    .querySyscollectionState_org_emp_count(borrower.getOrgId()
                        .toString());
                if (loanConditionsParamSetDTO_1.getParamExplain() != null) {
                  if (Integer.parseInt(count) < Integer
                      .parseInt(loanConditionsParamSetDTO_1.getParamExplain())) {
                    throw new BusinessException("��λ������������ "
                        + loanConditionsParamSetDTO_1.getParamExplain() + "��");
                  }
                }

                LoanConditionsParamSetDTO loanConditionsParamSetDTO_2 = loanConditionsSetDAO
                    .queryOverHousePriceS_wsh2(office);
                if (loanConditionsParamSetDTO_2.getParamExplain() != null) {
                  boolean flag = loanConditionsSetDAO.queryMonthCounts(borrower
                      .getEmpId().toString(), borrower.getOrgId().toString(),
                      loanConditionsParamSetDTO_2.getParamExplain()); // �����������
                  if (!flag) {
                    throw new BusinessException("��λ����������� "
                        + loanConditionsParamSetDTO_2.getParamExplain() + "��");
                  }
                }

              }
              if (j == 1) {
                LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
                    .queryOverHousePriceS_wsh3(office);
                count = loanConditionsSetDAO
                    .querySyscollectionState_org_emp_count(borrower.getOrgId()
                        .toString());
                if (loanConditionsParamSetDTO_1.getParamExplain() != null) {
                  if (Integer.parseInt(count) < Integer
                      .parseInt(loanConditionsParamSetDTO_1.getParamExplain())) {
                    throw new BusinessException("��λ������������ "
                        + loanConditionsParamSetDTO_1.getParamExplain() + "��");
                  }
                }
                LoanConditionsParamSetDTO loanConditionsParamSetDTO_2 = loanConditionsSetDAO
                    .queryOverHousePriceS_wsh4(office);
                if (loanConditionsParamSetDTO_2.getParamExplain() != null) {
                  boolean flag = loanConditionsSetDAO.queryMonthCounts(borrower
                      .getEmpId().toString(), borrower.getOrgId().toString(),
                      loanConditionsParamSetDTO_2.getParamExplain()); // �����������
                  if (!flag) {
                    throw new BusinessException("��λ����������� "
                        + loanConditionsParamSetDTO_2.getParamExplain() + "��");
                  }
                }
              }
              if (j == 2) {
                LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
                    .queryOverHousePriceS_wsh5(office);
                count = loanConditionsSetDAO
                    .querySyscollectionState_org_emp_count(borrower.getOrgId()
                        .toString());
                if (loanConditionsParamSetDTO_1.getParamExplain() != null) {
                  if (Integer.parseInt(count) < Integer
                      .parseInt(loanConditionsParamSetDTO_1.getParamExplain())) {
                    throw new BusinessException("��λ������������ "
                        + loanConditionsParamSetDTO_1.getParamExplain() + "��");
                  }
                }
                LoanConditionsParamSetDTO loanConditionsParamSetDTO_2 = loanConditionsSetDAO
                    .queryOverHousePriceS_wsh6(office);
                if (loanConditionsParamSetDTO_2.getParamExplain() != null) {
                  boolean flag = loanConditionsSetDAO.queryMonthCounts(borrower
                      .getEmpId().toString(), borrower.getOrgId().toString(),
                      loanConditionsParamSetDTO_2.getParamExplain()); // �����������
                  if (!flag) {
                    throw new BusinessException("��λ����������� "
                        + loanConditionsParamSetDTO_2.getParamExplain() + "��");
                  }
                }
              }
            }
          }
        }
        // ���ݵ�λ�����ж�
        Houses houses = housesDAO.queryById(contactid);
        String housestype = houses.getHouseType();
        if (housestype.equals("01")) {// ��Ʒ��
          BigDecimal housetolprice = houses.getHouseTotlePrice();
          LoanConditionsParamSetDTO loanConditionsParamSetDTO = loanConditionsSetDAO
              .queryOverHousePriceS(office);
          if (loanConditionsParamSetDTO.getParamExplain() != null) {// ������
            BigDecimal ht = new BigDecimal(loanapplytdnewAF.getLoanMoney());
            String housesprice = loanConditionsParamSetDTO.getParamExplain();
            BigDecimal jg = housetolprice.multiply(new BigDecimal(housesprice))
                .divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP);

            if (ht.doubleValue() > jg.doubleValue()) {
              throw new BusinessException("���ܳ�����Ʒ���۵�" + housesprice + "%");
            }
          }
        }
        if (housestype.equals("02")) {// ���ַ�
          BigDecimal housetolprice = houses.getBargainorTotlePrice();
          if (houses.getBargainorHouseAge().intValue() >= 1
              && houses.getBargainorHouseAge().intValue() < 5) {
            LoanConditionsParamSetDTO loanConditionsParamSetDTO = loanConditionsSetDAO
                .queryOverHousePriceE(office);
            if (loanConditionsParamSetDTO.getParamExplain() != null) {// ������
              BigDecimal ht = new BigDecimal(loanapplytdnewAF.getLoanMoney());
              String housesprice = loanConditionsParamSetDTO.getParamExplain();
              BigDecimal jg = housetolprice.multiply(
                  new BigDecimal(housesprice)).divide(new BigDecimal(100), 4,
                  BigDecimal.ROUND_HALF_UP);

              if (ht.doubleValue() > jg.doubleValue()) {
                throw new BusinessException("���ܳ������ַ��۵�" + housesprice + "%");
              }
            }
          }
          if (houses.getBargainorHouseAge().intValue() >= 5
              && houses.getBargainorHouseAge().intValue() < 10) {
            LoanConditionsParamSetDTO loanConditionsParamSetDTO = loanConditionsSetDAO
                .queryOverHousePriceE_wsh(office);
            if (loanConditionsParamSetDTO.getParamExplain() != null) {// ������
              BigDecimal ht = new BigDecimal(loanapplytdnewAF.getLoanMoney());
              String housesprice = loanConditionsParamSetDTO.getParamExplain();
              BigDecimal jg = housetolprice.multiply(
                  new BigDecimal(housesprice)).divide(new BigDecimal(100), 4,
                  BigDecimal.ROUND_HALF_UP);

              if (ht.doubleValue() > jg.doubleValue()) {
                throw new BusinessException("���ܳ������ַ��۵�" + housesprice + "%");
              }
            }
          }
          if (houses.getBargainorHouseAge().intValue() >= 5
              && houses.getBargainorHouseAge().intValue() < 10) {
            LoanConditionsParamSetDTO loanConditionsParamSetDTO = loanConditionsSetDAO
                .queryOverHousePriceE_wsh_1(office);
            if (loanConditionsParamSetDTO.getParamExplain() != null) {// ������
              BigDecimal ht = new BigDecimal(loanapplytdnewAF.getLoanMoney());
              String housesprice = loanConditionsParamSetDTO.getParamExplain();
              BigDecimal jg = housetolprice.multiply(
                  new BigDecimal(housesprice)).divide(new BigDecimal(100), 4,
                  BigDecimal.ROUND_HALF_UP);

              if (ht.doubleValue() > jg.doubleValue()) {
                throw new BusinessException("���ܳ������ַ��۵�" + housesprice + "%");
              }
            }
          }

        }
        if (housestype.equals("01")) {// ��Ʒ��

          LoanConditionsParamSetDTO loanConditionsParamSetDTO = loanConditionsSetDAO
              .queryMaxLoanMoneyS(office);
          if (loanConditionsParamSetDTO.getParamExplain() != null) {// ������

            String housesprice = loanConditionsParamSetDTO.getParamExplain();
            int housesprices = Integer.parseInt(housesprice);
            if (loanmoney > housesprices) {
              throw new BusinessException("��Ʒ���۵Ĵ�����ܳ���" + housesprices);
            }
          }
        }
        if (housestype.equals("02")) {// ���ַ�
          LoanConditionsParamSetDTO loanConditionsParamSetDTO = loanConditionsSetDAO
              .queryMaxLoanMoneyE(office);
          if (loanConditionsParamSetDTO.getParamExplain() != null) {// ������

            String housesprice = loanConditionsParamSetDTO.getParamExplain();
            int housesprices = Integer.parseInt(housesprice);
            if (loanmoney > housesprices) {
              throw new BusinessException("���ַ��۵Ĵ�����ܳ���" + housesprices);
            }
          }
        }
        List loanlimitlist_wsh = loanConditionsSetDAO.queryLoanLimit(office);
        for (int j = 0; j < loanlimitlist_wsh.size(); j++) {
          LoanConditionsParamSetDTO loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) loanlimitlist_wsh
              .get(j);
          String type = loanConditionsParamSetDTO.getParamValue();
          int loanlimitetime = Integer.parseInt(loanConditionsParamSetDTO
              .getParamExplain());
          if (type.equals("1") && housestype.equals("01")) {

            if (loanlimitetime < loanlimit) {
              throw new BusinessException("��Ʒ���������޵ø���" + loanlimitetime + "��");
            }

          }
          if (type.equals("2") && housestype.equals("02")) {
            if (loanlimitetime < loanlimit) {
              throw new BusinessException("���ַ��������޲��ø���" + loanlimitetime + "��");
            }
          }
        }
        // ����������뻹�����
        if (yueHuanBenXi != null
            && yueHuanBenXi.compareTo(new BigDecimal(0.00)) > 0) {
          LoanConditionsParamSetDTO loanConditionsParamSetDTO = loanConditionsSetDAO
              .queryMaxLoanMoneyE_w(office);
          if (loanConditionsParamSetDTO.getParamExplain() != null) {// ������

            String salaryRate = loanConditionsParamSetDTO.getParamExplain();
            BigDecimal housesprices = new BigDecimal(salaryRate);
            Borrower borrower_1 = borrowerDAO.queryById(contactid);
            ;
            if (yueHuanBenXi.compareTo(borrower_1.getMonthSalary().multiply(
                new BigDecimal(salaryRate))) > 0) {
              throw new BusinessException("����˻���������ܳ����������" + housesprices);
            }
          }
        }
        // ����������뻹�����
      }
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return null;
  }

  // ��.�ɴ���� ��������
  public int canSysLoanlimit(LoanapplyTdNewAF loanapplytdnewAF,
      SecurityInfo securityInfo) throws BusinessException {

    String specialid = specialBorrowerDAO.findSpecialByBorrownameCardnum(
        loanapplytdnewAF.getBorrowerName().trim(), loanapplytdnewAF
            .getCardNum().trim());// ���ݽ��������֤���Ų�ѯ�Ƿ����������˲�����δ����״̬

    int limit_temp = 0;
    int limit_1 = 0;
    int limit_2 = 0;
    int limit_3 = 0;
    try {

      if (specialid == null) {

        String contactid = loanapplytdnewAF.getContractId();
        // ���ݵ�λ�����ж�
        Houses houses = housesDAO.queryById(contactid);
        String housestype = houses.getHouseType();
        Borrower borrower = borrowerDAO.queryById(contactid);
        String office = borrower.getOffice();
        List agelist = loanConditionsSetDAO.queryManAge(office);
        String sex = borrower.getSex();
        int age = Integer.parseInt(borrower.getAge().toString());
        // ///////////////////////////////////////////////////////////////////////////////////////
        // ����������ޣ���Ʒ��
        String paramExplain_13_1 = loanConditionsSetDAO
            .queryIsUseParamValue_Thirteen_One("13", office, "1");
        // ����������ޣ����ַ�
        String paramExplain_13_2 = loanConditionsSetDAO
            .queryIsUseParamValue_Thirteen_One("13", office, "2");
        // ������޲�����?��
        String paramExplain_5_2 = loanConditionsSetDAO
            .queryIsUseParamValue_Thirteen_One("5", office, "2");

        if (housestype.equals("01")) {// ��Ʒ��
          // ������ʵ������Ӵ������޲�����
          if (sex.equals("1")) {// ��
            for (int i = 0; i < agelist.size(); i++) {
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                  .get(i);
              String sexdto = loanConditionsParamSetDTO.getParamValue();
              int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                  .getParamExplain());
              if (sexdto.equals("1")) {
                limit_1 = (paramExplain - age) * 12;
              }
            }
            if (paramExplain_13_1 != null && !"".equals(paramExplain_13_1)) {
              limit_2 = Integer.parseInt(paramExplain_13_1) * 12;
            }
            if (paramExplain_5_2 != null && !"".equals(paramExplain_5_2)) {
              limit_3 = Integer.parseInt(paramExplain_5_2);
            }
            // ��С����
            if (limit_1 > limit_2 && limit_2 != 0) {
              limit_temp = limit_2;
            } else {
              limit_temp = limit_1;
            }
            if (limit_temp > limit_3 && limit_3 != 0) {
              limit_temp = limit_3;
            }
            if (limit_temp == 0) {
              limit_temp = 1000;
            }
            // ��С����
          }
          if (sex.equals("2")) {// Ů
            // ������ʵ������Ӵ������޲�����
            for (int i = 0; i < agelist.size(); i++) {
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                  .get(i);
              String sexdto = loanConditionsParamSetDTO.getParamValue();
              int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                  .getParamExplain());
              if (sexdto.equals("2")) {
                limit_1 = (paramExplain - age) * 12;

              }
            }
            if (paramExplain_13_1 != null && !"".equals(paramExplain_13_1)) {
              limit_2 = Integer.parseInt(paramExplain_13_1) * 12;
            }
            if (paramExplain_5_2 != null && !"".equals(paramExplain_5_2)) {
              limit_3 = Integer.parseInt(paramExplain_5_2);
            }
            // ��С����
            if (limit_1 > limit_2 && limit_2 != 0) {
              limit_temp = limit_2;
            } else {
              limit_temp = limit_1;
            }
            if (limit_temp > limit_3 && limit_3 != 0) {
              limit_temp = limit_3;
            }
            if (limit_temp == 0) {
              limit_temp = 1000;
            }
            // ��С����
          }
        }
        if (housestype.equals("02")) {// ���ַ�
          if (sex.equals("1")) {// ��
            for (int i = 0; i < agelist.size(); i++) {
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                  .get(i);
              String sexdto = loanConditionsParamSetDTO.getParamValue();
              int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                  .getParamExplain());
              if (sexdto.equals("1")) {
                limit_1 = (paramExplain - age) * 12;

              }
            }
            if (paramExplain_13_2 != null && !"".equals(paramExplain_13_2)) {
              limit_2 = Integer.parseInt(paramExplain_13_2) * 12;
            }
            if (paramExplain_5_2 != null && !"".equals(paramExplain_5_2)) {
              limit_3 = Integer.parseInt(paramExplain_5_2);
            }
            // ��С����
            if (limit_1 > limit_2 && limit_2 != 0) {
              limit_temp = limit_2;
            } else {
              limit_temp = limit_1;
            }
            if (limit_temp > limit_3 && limit_3 != 0) {
              limit_temp = limit_3;
            }
            if (limit_temp == 0) {
              limit_temp = 1000;
            }
            // ��С����
          }
          if (sex.equals("2")) {// Ů
            // ������ʵ������Ӵ������޲�����
            for (int i = 0; i < agelist.size(); i++) {
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                  .get(i);
              String sexdto = loanConditionsParamSetDTO.getParamValue();
              int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                  .getParamExplain());
              if (sexdto.equals("2")) {
                limit_1 = (paramExplain - age) * 12;

              }
            }
            if (paramExplain_13_2 != null && !"".equals(paramExplain_13_2)) {
              limit_2 = Integer.parseInt(paramExplain_13_2) * 12;
            }
            if (paramExplain_5_2 != null && !"".equals(paramExplain_5_2)) {
              limit_3 = Integer.parseInt(paramExplain_5_2);
            }
            // ��С����
            if (limit_1 > limit_2 && limit_2 != 0) {
              limit_temp = limit_2;
            } else {
              limit_temp = limit_1;
            }
            if (limit_temp > limit_3 && limit_3 != 0) {
              limit_temp = limit_3;
            }
            if (limit_temp == 0) {
              limit_temp = 1000;
            }
            // ��С����
          }
        }
        // ///////////////////////////////////////////////////////////////////////////////////////
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return limit_temp;
  }

  // ��.�ɴ���� ���ؽ��
  public BigDecimal canSysLoanlimit_1(LoanapplyTdNewAF loanapplytdnewAF,
      SecurityInfo securityInfo) throws BusinessException {

    String specialid = specialBorrowerDAO.findSpecialByBorrownameCardnum(
        loanapplytdnewAF.getBorrowerName().trim(), loanapplytdnewAF
            .getCardNum().trim());// ���ݽ��������֤���Ų�ѯ�Ƿ����������˲�����δ����״̬

    BigDecimal money_temp = new BigDecimal(0);
    BigDecimal money_1 = new BigDecimal(0);
    BigDecimal money_2 = new BigDecimal(0);
    BigDecimal money_3 = new BigDecimal(0);
    BigDecimal money_4 = new BigDecimal(0);
    BigDecimal money_5 = new BigDecimal(0);
    try {

      if (specialid == null) {

        String contactid = loanapplytdnewAF.getContractId();
        int loanmoney = Integer.parseInt(loanapplytdnewAF.getLoanMoney());
        int loanlimit = Integer.parseInt(loanapplytdnewAF.getLoantimeLimit());
        // ���ݵ�λ�����ж�
        Houses houses = housesDAO.queryById(contactid);
        String housestype = houses.getHouseType();
        Borrower borrower = borrowerDAO.queryById(contactid);
        AssistantBorrower assistantBorrower = assistantBorrowerDAO
            .findByContractId(contactid);
        String office = borrower.getOffice();
        List agelist = loanConditionsSetDAO.queryManAge(office);
        String sex = borrower.getSex();
        int age = Integer.parseInt(borrower.getAge().toString());
        String num = "0";
        if (borrower.getEmpId() != null && !"".equals(borrower.getEmpId())
            && assistantBorrower != null
            && assistantBorrower.getEmpId() != null
            && !"".equals(assistantBorrower.getEmpId())) {
          num = "1";
        }
        // ��ش���
        if (borrower.getLoanType() != null
            && borrower.getLoanType().equals("1")) {
          num = "2";
        }
        // String num=borrowerDAO.countPeopleNum(contactid);
        BigDecimal yuE = new BigDecimal(0.00);
        BigDecimal jiaoE = new BigDecimal(0.00);
        BigDecimal sum = new BigDecimal(0.00);
        // ///////////////////////////////////////////////////////////////////////////////////////
        // ����ӵ�й����𲻵ó�����Ԫ
        String paramExplain_7_1 = loanConditionsSetDAO
            .queryIsUseParamValue_Thirteen_One("7", office, "1");
        // �и��������˵Ĵ�����ܳ�����Ԫ
        String paramExplain_7_2 = loanConditionsSetDAO
            .queryIsUseParamValue_Thirteen_One("7", office, "2");
        // ������ܳ�����Ʒ���۵� %
        String paramExplain_8 = loanConditionsSetDAO.queryIsUseParamValue("8",
            office);
        // ��Ʒ��������߽��
        String paramExplain_10 = loanConditionsSetDAO.queryIsUseParamValue(
            "10", office);
        // ���ַ�������߽��
        String paramExplain_11 = loanConditionsSetDAO.queryIsUseParamValue(
            "11", office);
        // ���ó��������ͥ��Ա����������������ס������������ ��
        String paramExplain_12 = loanConditionsSetDAO.queryIsUseParamValue(
            "12", office);
        // ����������뻹����� %
        String paramExplain_14 = loanConditionsSetDAO.queryIsUseParamValue(
            "14", office);
        // ������ܳ������ַ��۵ģ���������1����5�꣩ %
        String paramExplain_9 = loanConditionsSetDAO.queryIsUseParamValue("9",
            office);
        // ����ӵ�й����𲻵ó�����Ԫ
        String paramExplain_9_1 = loanConditionsSetDAO
            .queryIsUseParamValue_Thirteen_One("9", office, "1");
        // �и��������˵Ĵ�����ܳ�����Ԫ
        String paramExplain_9_2 = loanConditionsSetDAO
            .queryIsUseParamValue_Thirteen_One("9", office, "2");

        if (housestype.equals("01")) {// ��Ʒ��
          // ������ʵ������Ӵ������޲�����
          if (num.equals("0") && borrower.getEmpId() != null
              && !"".equals(borrower.getEmpId())) {// ����ӵ�й�����,�����ӵ�й�����
            if (paramExplain_7_1 != null && !"".equals(paramExplain_7_1)) {
              money_1 = new BigDecimal(paramExplain_7_1);
            }
            if (paramExplain_8 != null && !"".equals(paramExplain_8)) {
              money_2 = (new BigDecimal(houses.getHouseTotlePrice().toString()))
                  .multiply(new BigDecimal(paramExplain_8)).divide(
                      new BigDecimal(100), 2);
            }
            if (paramExplain_10 != null && !"".equals(paramExplain_10)) {
              money_3 = new BigDecimal(paramExplain_10);
            }

            if (borrower.getEmpId() != null || !"".equals(borrower.getEmpId())) {
              Emp emp = empDAO.queryByCriterions(
                  borrower.getEmpId().toString(), borrower.getOrgId()
                      .toString());
              if (paramExplain_14 != null && !"".equals(paramExplain_14)) {
                money_5 = borrower.getMonthSalary().multiply(
                    new BigDecimal(paramExplain_14)).multiply(
                    new BigDecimal(loanlimit)).divide(new BigDecimal(100), 2);
              }

              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              yuE = borrower.getAccBlnce();
              for (int i = 0; i < agelist.size(); i++) {
                loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                    .get(i);
                String sexdto = loanConditionsParamSetDTO.getParamValue();
                int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                    .getParamExplain());
                if (sex.equals("1")) {// ������Ա���
                  if (sexdto.equals("1")) {
                    jiaoE = (borrower.getMonthPay()).multiply(
                        new BigDecimal(12)).multiply(
                        new BigDecimal(paramExplain
                            - borrower.getAge().intValue()));
                  }

                }
                if (sex.equals("2")) {// Ů

                  if (sexdto.equals("2")) {
                    jiaoE = (borrower.getMonthPay()).multiply(
                        new BigDecimal(12)).multiply(
                        new BigDecimal(paramExplain
                            - borrower.getAge().intValue()));
                  }
                }
              }
              sum = yuE.add(jiaoE);
            }
            int loanm;
            if (paramExplain_12 != null && !"".equals(paramExplain_12)) {
              loanm = Integer.parseInt(paramExplain_12);
              money_4 = sum.multiply(new BigDecimal(loanm));
            }

            // ��С���
            if (money_1.compareTo(money_2) > 0
                && money_2.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_2;
            } else {
              money_temp = money_1;
            }
            if (money_temp.compareTo(money_3) > 0
                && money_3.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_3;
            }
            if (money_temp.compareTo(money_4) > 0
                && money_4.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_4;
            }
            if (money_temp.compareTo(money_5) > 0
                && money_5.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_5;
            }
            // ��С���
            System.out.println("��Ʒ�������ӵ�й�����");
            System.out.println("1" + money_1);
            System.out.println("2" + money_2);
            System.out.println("3" + money_3);
            System.out.println("4" + money_4);
            System.out.println("5" + money_5);
            if (money_temp.compareTo(new BigDecimal(0)) == 0) {
              money_temp = new BigDecimal(100000000);
            }
          }
          if (num.equals("0") && assistantBorrower != null
              && assistantBorrower.getEmpId() != null
              && !"".equals(assistantBorrower.getEmpId())) {// ����ӵ�й�����,���������ӵ�й�����
            if (paramExplain_7_1 != null && !"".equals(paramExplain_7_1)) {
              money_1 = new BigDecimal(paramExplain_7_1);
            }
            if (paramExplain_8 != null && !"".equals(paramExplain_8)) {
              money_2 = (new BigDecimal(houses.getHouseTotlePrice().toString()))
                  .multiply(new BigDecimal(paramExplain_8)).divide(
                      new BigDecimal(100), 2);
            }
            if (paramExplain_10 != null && !"".equals(paramExplain_10)) {
              money_3 = new BigDecimal(paramExplain_10);
            }

            if (assistantBorrower.getEmpId() != null
                || !"".equals(assistantBorrower.getEmpId())) {
              Emp emp = empDAO.queryByCriterions(assistantBorrower.getEmpId()
                  .toString(), assistantBorrower.getOrgId().toString());
              if (paramExplain_14 != null && !"".equals(paramExplain_14)) {
                money_5 = assistantBorrower.getMonthSalary().multiply(
                    new BigDecimal(paramExplain_14)).multiply(
                    new BigDecimal(loanlimit)).divide(new BigDecimal(100), 2);
              }

              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              yuE = assistantBorrower.getAccBlnce();
              for (int i = 0; i < agelist.size(); i++) {
                loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                    .get(i);
                String sexdto = loanConditionsParamSetDTO.getParamValue();
                int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                    .getParamExplain());
                sex = assistantBorrower.getSex();
                if (sex.equals("1")) {// ������Ա���
                  if (sexdto.equals("1")) {
                    jiaoE = (assistantBorrower.getMonthPay()).multiply(
                        new BigDecimal(12)).multiply(
                        new BigDecimal(paramExplain
                            - assistantBorrower.getAge().intValue()));
                  }

                }
                if (sex.equals("2")) {// Ů

                  if (sexdto.equals("2")) {
                    jiaoE = (assistantBorrower.getMonthPay()).multiply(
                        new BigDecimal(12)).multiply(
                        new BigDecimal(paramExplain
                            - assistantBorrower.getAge().intValue()));
                  }
                }
              }
              sum = yuE.add(jiaoE);
            }
            int loanm;
            if (paramExplain_12 != null && !"".equals(paramExplain_12)) {
              loanm = Integer.parseInt(paramExplain_12);
              money_4 = sum.multiply(new BigDecimal(loanm));
            }

            // ��С���
            if (money_1.compareTo(money_2) > 0
                && money_2.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_2;
            } else {
              money_temp = money_1;
            }
            if (money_temp.compareTo(money_3) > 0
                && money_3.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_3;
            }
            if (money_temp.compareTo(money_4) > 0
                && money_4.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_4;
            }
            if (money_temp.compareTo(money_5) > 0
                && money_5.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_5;
            }
            // ��С���
            System.out.println("��Ʒ�����������ӵ�й�����");
            System.out.println("1" + money_1);
            System.out.println("2" + money_2);
            System.out.println("3" + money_3);
            System.out.println("4" + money_4);
            System.out.println("5" + money_5);
            if (money_temp.compareTo(new BigDecimal(0)) == 0) {
              money_temp = new BigDecimal(100000000);
            }
          }
          if (num.equals("1")) {// ����˺͸�������˶��й�����
            // ������ʵ������Ӵ������޲�����
            if (paramExplain_7_2 != null && !"".equals(paramExplain_7_2)) {
              money_1 = new BigDecimal(paramExplain_7_2);
            }
            if (paramExplain_8 != null && !"".equals(paramExplain_8)) {
              money_2 = (new BigDecimal(houses.getHouseTotlePrice().toString()))
                  .multiply(new BigDecimal(paramExplain_8)).divide(
                      new BigDecimal(100), 2);
            }
            if (paramExplain_10 != null && !"".equals(paramExplain_10)) {
              money_3 = new BigDecimal(paramExplain_10);
            }

            LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
                .queryOverHousePriceS_1(office);
            LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
            LoanConditionsParamSetDTO loanConditionsParamSetDTO_14 = loanConditionsSetDAO
                .queryOverHousePriceS_14(office);
            String fuzhuEmpId = borrowerDAO.countPeopleNum_EmpId(contactid);
            String fuzhuOrgId = borrowerDAO.countPeopleNum_Id(contactid);
            String fuzhuSex = borrowerDAO.countPeopleNum_Sex(contactid);
            borrower = borrowerDAO.queryById(contactid);

            // if ((borrower.getEmpId()== null ||
            // "".equals(borrower.getEmpId()))&&(fuzhuEmpId==null||"".equals(fuzhuEmpId))){
            // throw new BusinessException("����˼���������˾�δ����ס��������");
            // }
            if (borrower.getEmpId() != null && !"".equals(borrower.getEmpId())) {
              Emp emp = empDAO.queryByCriterions(
                  borrower.getEmpId().toString(), borrower.getOrgId()
                      .toString());
              if (paramExplain_14 != null && !"".equals(paramExplain_14)) {// ������
                // �����
                BigDecimal money = borrower.getMonthSalary().multiply(
                    new BigDecimal(paramExplain_14)).multiply(
                    new BigDecimal(loanlimit)).divide(new BigDecimal(100), 2);
                // ���������

                money_5 = money.add(assistantBorrower.getMonthSalary()
                    .multiply(new BigDecimal(paramExplain_14)).multiply(
                        new BigDecimal(loanlimit)).divide(new BigDecimal(100),
                        2));

              }
              yuE = borrower.getAccBlnce();// .add((emp.getOrgPay().add(emp.getEmpPay()).multiply(new
              // BigDecimal(12))));

              for (int i = 0; i < agelist.size(); i++) {
                loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                    .get(i);
                String sexdto = loanConditionsParamSetDTO.getParamValue();
                int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                    .getParamExplain());
                if (sex.equals("1")) {// ������Ա���
                  if (sexdto.equals("1")) {
                    jiaoE = (borrower.getMonthPay()).multiply(
                        new BigDecimal(12)).multiply(
                        new BigDecimal(paramExplain
                            - borrower.getAge().intValue()));
                  }

                }
                if (sex.equals("2")) {// Ů

                  if (sexdto.equals("2")) {
                    jiaoE = (borrower.getMonthPay()).multiply(
                        new BigDecimal(12)).multiply(
                        new BigDecimal(paramExplain
                            - borrower.getAge().intValue()));
                  }
                }
              }
              sum = yuE.add(jiaoE);
            }
            if (fuzhuEmpId != null && !"".equals(fuzhuEmpId)) {
              Emp emp = empDAO.queryByCriterions(fuzhuEmpId.toString(),
                  fuzhuOrgId.toString());
              yuE = yuE.add(assistantBorrower.getAccBlnce());// .add((emp.getOrgPay().add(emp.getEmpPay()).multiply(new
              // BigDecimal(12))));

              for (int i = 0; i < agelist.size(); i++) {
                loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                    .get(i);
                String sexdto = loanConditionsParamSetDTO.getParamValue();
                int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                    .getParamExplain());
                String fuZhuAge = "";
                fuZhuAge = borrowerDAO.countPeopleNum_Age(contactid);
                if (fuzhuSex.equals("1")) {// ������Ա���
                  if (sexdto.equals("1")) {
                    jiaoE = jiaoE.add((assistantBorrower.getMonthPay())
                        .multiply(new BigDecimal(12)).multiply(
                            new BigDecimal(paramExplain
                                - Integer.valueOf(
                                    assistantBorrower.getAge().toString())
                                    .intValue())));
                  }

                }
                if (fuzhuSex.equals("2")) {// Ů

                  if (sexdto.equals("2")) {
                    jiaoE = jiaoE.add((assistantBorrower.getMonthPay())
                        .multiply(new BigDecimal(12)).multiply(
                            new BigDecimal(paramExplain
                                - Integer.valueOf(
                                    assistantBorrower.getAge().toString())
                                    .intValue())));
                  }
                }
              }
              sum = yuE.add(jiaoE);
            }

            int loanm;
            if (paramExplain_12 != null && !"".equals(paramExplain_12)) {
              loanm = Integer.parseInt(paramExplain_12);
              money_4 = sum.multiply(new BigDecimal(loanm));
            }

            // ��С���
            if (money_1.compareTo(money_2) > 0
                && money_2.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_2;
            } else {
              money_temp = money_1;
            }
            if (money_temp.compareTo(money_3) > 0
                && money_3.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_3;
            }
            if (money_temp.compareTo(money_4) > 0
                && money_4.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_4;
            }
            if (money_temp.compareTo(money_5) > 0
                && money_5.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_5;
            }
            // ��С���
            System.out.println("��Ʒ��˫����ӵ�й�����");
            System.out.println("1" + money_1);
            System.out.println("2" + money_2);
            System.out.println("3" + money_3);
            System.out.println("4" + money_4);
            System.out.println("5" + money_5);
            if (money_temp.compareTo(new BigDecimal(0)) == 0) {
              money_temp = new BigDecimal(100000000);
            }
          }
          if (num.equals("2")) {// ����˺͸�������˶��й�����
            // ������ʵ������Ӵ������޲�����
            // if(assistantBorrower!=null){
            // if (paramExplain_7_2 != null && !"".equals(paramExplain_7_2)) {
            // money_1 = new BigDecimal(paramExplain_7_2);
            // }
            // }else{
            // if (paramExplain_7_1 != null && !"".equals(paramExplain_7_1)) {
            // money_1 = new BigDecimal(paramExplain_7_1);
            // }
            // }

            if (paramExplain_8 != null && !"".equals(paramExplain_8)) {
              money_2 = (new BigDecimal(houses.getHouseTotlePrice().toString()))
                  .multiply(new BigDecimal(paramExplain_8)).divide(
                      new BigDecimal(100), 2);
            }
            if (paramExplain_10 != null && !"".equals(paramExplain_10)) {
              money_3 = new BigDecimal(paramExplain_10);
            }

            // LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 =
            // loanConditionsSetDAO
            // .queryOverHousePriceS_1(office);
            // LoanConditionsParamSetDTO loanConditionsParamSetDTO = new
            // LoanConditionsParamSetDTO();
            // LoanConditionsParamSetDTO loanConditionsParamSetDTO_14 =
            // loanConditionsSetDAO
            // .queryOverHousePriceS_14(office);
            // String fuzhuEmpId = borrowerDAO.countPeopleNum_EmpId(contactid);
            // String fuzhuOrgId = borrowerDAO.countPeopleNum_Id(contactid);
            // String fuzhuSex = borrowerDAO.countPeopleNum_Sex(contactid);
            // borrower = borrowerDAO.queryById(contactid);
            // // if (borrower.getEmpId() != null &&
            // !"".equals(borrower.getEmpId())) {
            // if (paramExplain_14 != null && !"".equals(paramExplain_14)) {//
            // ������
            // // �����
            // BigDecimal money = borrower.getMonthSalary().multiply(
            // new BigDecimal(paramExplain_14)).multiply(
            // new BigDecimal(loanlimit)).divide(new BigDecimal(100), 2);
            // // ���������
            // if(assistantBorrower==null){
            // money_5 = money;
            // }else{
            // money_5 = money.add(assistantBorrower.getMonthSalary()
            // .multiply(new BigDecimal(paramExplain_14)).multiply(
            // new BigDecimal(loanlimit)).divide(new BigDecimal(100),
            // 2));
            // }
            //               
            //
            // }
            // yuE = borrower.getAccBlnce();
            // for (int i = 0; i < agelist.size(); i++) {
            // loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
            // .get(i);
            // String sexdto = loanConditionsParamSetDTO.getParamValue();
            // int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
            // .getParamExplain());
            // if (sex.equals("1")) {// ������Ա���
            // if (sexdto.equals("1")) {
            // jiaoE = (borrower.getMonthPay()).multiply(
            // new BigDecimal(12)).multiply(
            // new BigDecimal(paramExplain
            // - borrower.getAge().intValue()));
            // }
            //
            // }
            // if (sex.equals("2")) {// Ů
            //
            // if (sexdto.equals("2")) {
            // jiaoE = (borrower.getMonthPay()).multiply(
            // new BigDecimal(12)).multiply(
            // new BigDecimal(paramExplain
            // - borrower.getAge().intValue()));
            // }
            // }
            // }
            // sum = yuE.add(jiaoE);
            // // }
            // // if (fuzhuEmpId != null && !"".equals(fuzhuEmpId)) {
            // if(assistantBorrower!=null){
            // yuE = yuE.add(assistantBorrower.getAccBlnce());
            // // BigDecimal(12))));
            //
            // for (int i = 0; i < agelist.size(); i++) {
            // loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
            // .get(i);
            // String sexdto = loanConditionsParamSetDTO.getParamValue();
            // int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
            // .getParamExplain());
            // if (fuzhuSex.equals("1")) {// ������Ա���
            // if (sexdto.equals("1")) {
            // jiaoE = jiaoE.add((assistantBorrower.getMonthPay())
            // .multiply(new BigDecimal(12)).multiply(
            // new BigDecimal(paramExplain
            // - Integer.valueOf(
            // assistantBorrower.getAge().toString())
            // .intValue())));
            // }
            //
            // }
            // if (fuzhuSex.equals("2")) {// Ů
            //
            // if (sexdto.equals("2")) {
            // jiaoE = jiaoE.add((assistantBorrower.getMonthPay())
            // .multiply(new BigDecimal(12)).multiply(
            // new BigDecimal(paramExplain
            // - Integer.valueOf(
            // assistantBorrower.getAge().toString())
            // .intValue())));
            // }
            // }
            // }
            // sum = yuE.add(jiaoE);
            // }
            //              
            // // }
            //
            // int loanm;
            // if (paramExplain_12 != null && !"".equals(paramExplain_12)) {
            // loanm = Integer.parseInt(paramExplain_12);
            // money_4 = sum.multiply(new BigDecimal(loanm));
            // }

            // ��С���
            if (money_2.compareTo(money_3) > 0
                && money_3.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_3;
            } else {
              money_temp = money_2;
            }
            // if (money_temp.compareTo(money_3) > 0
            // && money_3.compareTo(new BigDecimal(0)) != 0) {
            // money_temp = money_3;
            // }
            // if (money_temp.compareTo(money_4) > 0
            // && money_4.compareTo(new BigDecimal(0)) != 0) {
            // money_temp = money_4;
            // }
            // if (money_temp.compareTo(money_5) > 0
            // && money_5.compareTo(new BigDecimal(0)) != 0) {
            // money_temp = money_5;
            // }
            // ��С���
            System.out.println("��Ʒ�������");
            System.out.println("1" + money_1);
            System.out.println("2" + money_2);
            System.out.println("3" + money_3);
            System.out.println("4" + money_4);
            System.out.println("5" + money_5);
            if (money_temp.compareTo(new BigDecimal(0)) == 0) {
              money_temp = new BigDecimal(100000000);
            }
          }
        }
        if (housestype.equals("02")) {// ���ַ�
          if (num.equals("0") && borrower.getEmpId() != null
              && !"".equals(borrower.getEmpId())) {// ����ӵ�й�����,�����ӵ�й�����
            if (paramExplain_7_1 != null && !"".equals(paramExplain_7_1)) {
              money_1 = new BigDecimal(paramExplain_7_1);
            }
            if (houses.getBargainorHouseAge().compareTo(new BigDecimal(1)) >= 0
                && houses.getBargainorHouseAge().compareTo(new BigDecimal(5)) <= 0) {
              if (paramExplain_9 != null && !"".equals(paramExplain_9)) {
                money_2 = (new BigDecimal(houses.getBargainorTotlePrice()
                    .toString())).multiply(new BigDecimal(paramExplain_9))
                    .divide(new BigDecimal(100), 2);
              }
            }
            if (houses.getBargainorHouseAge().compareTo(new BigDecimal(6)) >= 0
                && houses.getBargainorHouseAge().compareTo(new BigDecimal(10)) <= 0) {
              if (paramExplain_9_1 != null && !"".equals(paramExplain_9_1)) {
                money_2 = (new BigDecimal(houses.getBargainorTotlePrice()
                    .toString())).multiply(new BigDecimal(paramExplain_9_1))
                    .divide(new BigDecimal(100), 2);
              }
            }
            if (houses.getBargainorHouseAge().compareTo(new BigDecimal(11)) >= 0) {
              if (paramExplain_9_2 != null && !"".equals(paramExplain_9_2)) {
                money_2 = (new BigDecimal(houses.getBargainorTotlePrice()
                    .toString())).multiply(new BigDecimal(paramExplain_9_2))
                    .divide(new BigDecimal(100), 2);
              }
            }
            if (paramExplain_11 != null && !"".equals(paramExplain_11)) {
              money_3 = new BigDecimal(paramExplain_11);
            }
            if (borrower.getEmpId() != null || !"".equals(borrower.getEmpId())) {
              Emp emp = empDAO.queryByCriterions(
                  borrower.getEmpId().toString(), borrower.getOrgId()
                      .toString());
              if (paramExplain_14 != null && !"".equals(paramExplain_14)) {
                money_5 = borrower.getMonthSalary().multiply(
                    new BigDecimal(paramExplain_14)).multiply(
                    new BigDecimal(loanlimit)).divide(new BigDecimal(100), 2);
              }

              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              yuE = borrower.getAccBlnce();
              for (int i = 0; i < agelist.size(); i++) {
                loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                    .get(i);
                String sexdto = loanConditionsParamSetDTO.getParamValue();
                int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                    .getParamExplain());
                if (sex.equals("1")) {// ������Ա���
                  if (sexdto.equals("1")) {
                    jiaoE = (borrower.getMonthPay()).multiply(
                        new BigDecimal(12)).multiply(
                        new BigDecimal(paramExplain
                            - borrower.getAge().intValue()));
                  }

                }
                if (sex.equals("2")) {// Ů

                  if (sexdto.equals("2")) {
                    jiaoE = (borrower.getMonthPay()).multiply(
                        new BigDecimal(12)).multiply(
                        new BigDecimal(paramExplain
                            - borrower.getAge().intValue()));
                  }
                }
              }
              sum = yuE.add(jiaoE);
            }
            int loanm;
            if (paramExplain_12 != null && !"".equals(paramExplain_12)) {
              loanm = Integer.parseInt(paramExplain_12);
              money_4 = sum.multiply(new BigDecimal(loanm));
            }
            // ��С���
            if (money_1.compareTo(money_2) > 0
                && money_2.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_2;
            } else {
              money_temp = money_1;
            }
            if (money_temp.compareTo(money_3) > 0
                && money_3.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_3;
            }
            if (money_temp.compareTo(money_4) > 0
                && money_4.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_4;
            }
            if (money_temp.compareTo(money_5) > 0
                && money_5.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_5;
            }
            System.out.println("���ַ������ӵ�й�����");
            System.out.println("1" + money_1);
            System.out.println("2" + money_2);
            System.out.println("3" + money_3);
            System.out.println("4" + money_4);
            System.out.println("5" + money_5);
            if (money_temp.compareTo(new BigDecimal(0)) == 0) {
              money_temp = new BigDecimal(100000000);
            }
          }
          if (num.equals("0") && assistantBorrower != null
              && assistantBorrower.getEmpId() != null
              && !"".equals(assistantBorrower.getEmpId())) {// ����ӵ�й�����,���������ӵ�й�����
            if (paramExplain_7_1 != null && !"".equals(paramExplain_7_1)) {
              money_1 = new BigDecimal(paramExplain_7_1);
            }
            if (houses.getBargainorHouseAge().compareTo(new BigDecimal(1)) >= 0
                && houses.getBargainorHouseAge().compareTo(new BigDecimal(5)) <= 0) {
              if (paramExplain_9 != null && !"".equals(paramExplain_9)) {
                money_2 = (new BigDecimal(houses.getBargainorTotlePrice()
                    .toString())).multiply(new BigDecimal(paramExplain_9))
                    .divide(new BigDecimal(100), 2);
              }
            }
            if (houses.getBargainorHouseAge().compareTo(new BigDecimal(6)) >= 0
                && houses.getBargainorHouseAge().compareTo(new BigDecimal(10)) <= 0) {
              if (paramExplain_9_1 != null && !"".equals(paramExplain_9_1)) {
                money_2 = (new BigDecimal(houses.getBargainorTotlePrice()
                    .toString())).multiply(new BigDecimal(paramExplain_9_1))
                    .divide(new BigDecimal(100), 2);
              }
            }
            if (houses.getBargainorHouseAge().compareTo(new BigDecimal(11)) >= 0) {
              if (paramExplain_9_2 != null && !"".equals(paramExplain_9_2)) {
                money_2 = (new BigDecimal(houses.getBargainorTotlePrice()
                    .toString())).multiply(new BigDecimal(paramExplain_9_2))
                    .divide(new BigDecimal(100), 2);
              }
            }
            if (paramExplain_11 != null && !"".equals(paramExplain_11)) {
              money_3 = new BigDecimal(paramExplain_11);
            }
            if (assistantBorrower.getEmpId() != null
                || !"".equals(assistantBorrower.getEmpId())) {
              // Emp emp = empDAO.queryByCriterions(assistantBorrower.getEmpId()
              // .toString(),assistantBorrower.getOrgId().toString());
              if (paramExplain_14 != null && !"".equals(paramExplain_14)) {
                money_5 = assistantBorrower.getMonthSalary().multiply(
                    new BigDecimal(paramExplain_14)).multiply(
                    new BigDecimal(loanlimit)).divide(new BigDecimal(100), 2);
              }

              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              yuE = assistantBorrower.getAccBlnce();
              for (int i = 0; i < agelist.size(); i++) {
                loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                    .get(i);
                String sexdto = loanConditionsParamSetDTO.getParamValue();
                int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                    .getParamExplain());
                sex = assistantBorrower.getSex();
                if (sex.equals("1")) {// ������Ա���
                  if (sexdto.equals("1")) {
                    jiaoE = (assistantBorrower.getMonthPay()).multiply(
                        new BigDecimal(12)).multiply(
                        new BigDecimal(paramExplain
                            - assistantBorrower.getAge().intValue()));
                  }

                }
                if (sex.equals("2")) {// Ů

                  if (sexdto.equals("2")) {
                    jiaoE = (assistantBorrower.getMonthPay()).multiply(
                        new BigDecimal(12)).multiply(
                        new BigDecimal(paramExplain
                            - assistantBorrower.getAge().intValue()));
                  }
                }
              }
              sum = yuE.add(jiaoE);
            }
            int loanm;
            if (paramExplain_12 != null && !"".equals(paramExplain_12)) {
              loanm = Integer.parseInt(paramExplain_12);
              money_4 = sum.multiply(new BigDecimal(loanm));
            }
            // ��С���
            if (money_1.compareTo(money_2) > 0
                && money_2.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_2;
            } else {
              money_temp = money_1;
            }
            if (money_temp.compareTo(money_3) > 0
                && money_3.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_3;
            }
            if (money_temp.compareTo(money_4) > 0
                && money_4.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_4;
            }
            if (money_temp.compareTo(money_5) > 0
                && money_5.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_5;
            }
            System.out.println("���ַ����������ӵ�й�����");
            System.out.println("1" + money_1);
            System.out.println("2" + money_2);
            System.out.println("3" + money_3);
            System.out.println("4" + money_4);
            System.out.println("5" + money_5);
            if (money_temp.compareTo(new BigDecimal(0)) == 0) {
              money_temp = new BigDecimal(100000000);
            }
          }
          if (num.equals("1")) {// ����˺͸�������˶�ӵ�й�����
            // ������ʵ������Ӵ������޲�����
            if (paramExplain_7_2 != null && !"".equals(paramExplain_7_2)) {
              money_1 = new BigDecimal(paramExplain_7_2);
            }
            if (houses.getBargainorHouseAge().compareTo(new BigDecimal(1)) >= 0
                && houses.getBargainorHouseAge().compareTo(new BigDecimal(5)) <= 0) {
              if (paramExplain_9 != null && !"".equals(paramExplain_9)) {
                money_2 = (new BigDecimal(houses.getBargainorTotlePrice()
                    .toString())).multiply(new BigDecimal(paramExplain_9))
                    .divide(new BigDecimal(100), 2);
              }
            }
            if (houses.getBargainorHouseAge().compareTo(new BigDecimal(6)) >= 0
                && houses.getBargainorHouseAge().compareTo(new BigDecimal(10)) <= 0) {
              if (paramExplain_9_1 != null && !"".equals(paramExplain_9_1)) {
                money_2 = (new BigDecimal(houses.getBargainorTotlePrice()
                    .toString())).multiply(new BigDecimal(paramExplain_9_1))
                    .divide(new BigDecimal(100), 2);
              }
            }
            if (houses.getBargainorHouseAge().compareTo(new BigDecimal(11)) >= 0) {
              if (paramExplain_9_2 != null && !"".equals(paramExplain_9_2)) {
                money_2 = (new BigDecimal(houses.getBargainorTotlePrice()
                    .toString())).multiply(new BigDecimal(paramExplain_9_2))
                    .divide(new BigDecimal(100), BigDecimal.ROUND_CEILING);
              }
            }
            if (paramExplain_11 != null && !"".equals(paramExplain_11)) {
              money_3 = new BigDecimal(paramExplain_11);
            }
            LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
                .queryOverHousePriceS_1(office);
            LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
            LoanConditionsParamSetDTO loanConditionsParamSetDTO_14 = loanConditionsSetDAO
                .queryOverHousePriceS_14(office);
            String fuzhuEmpId = borrowerDAO.countPeopleNum_EmpId(contactid);
            String fuzhuOrgId = borrowerDAO.countPeopleNum_Id(contactid);
            String fuzhuSex = borrowerDAO.countPeopleNum_Sex(contactid);
            borrower = borrowerDAO.queryById(contactid);
            // if ((borrower.getEmpId()== null ||
            // "".equals(borrower.getEmpId()))&&(fuzhuEmpId==null||"".equals(fuzhuEmpId))){
            // throw new BusinessException("����˼���������˾�δ����ס��������");
            // }
            if (borrower.getEmpId() != null && !"".equals(borrower.getEmpId())) {
              Emp emp = empDAO.queryByCriterions(
                  borrower.getEmpId().toString(), borrower.getOrgId()
                      .toString());
              if (paramExplain_14 != null && !"".equals(paramExplain_14)) {// ������
                // �����
                BigDecimal money = borrower.getMonthSalary().multiply(
                    new BigDecimal(paramExplain_14)).multiply(
                    new BigDecimal(loanlimit)).divide(new BigDecimal(100), 2);
                // ���������
                Emp emp_fuzhu = empDAO.queryByCriterions(fuzhuEmpId.toString(),
                    fuzhuOrgId.toString());
                money_5 = money.add(assistantBorrower.getMonthSalary()
                    .multiply(new BigDecimal(paramExplain_14)).multiply(
                        new BigDecimal(loanlimit)).divide(new BigDecimal(100),
                        2));

              }
              yuE = borrower.getAccBlnce();// .add((emp.getOrgPay().add(emp.getEmpPay()).multiply(new
              // BigDecimal(12))));

              for (int i = 0; i < agelist.size(); i++) {
                loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                    .get(i);
                String sexdto = loanConditionsParamSetDTO.getParamValue();
                int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                    .getParamExplain());
                if (sex.equals("1")) {// ������Ա���
                  if (sexdto.equals("1")) {
                    jiaoE = (borrower.getMonthPay()).multiply(
                        new BigDecimal(12)).multiply(
                        new BigDecimal(paramExplain
                            - borrower.getAge().intValue()));
                  }

                }
                if (sex.equals("2")) {// Ů

                  if (sexdto.equals("2")) {
                    jiaoE = (borrower.getMonthPay()).multiply(
                        new BigDecimal(12)).multiply(
                        new BigDecimal(paramExplain
                            - borrower.getAge().intValue()));
                  }
                }
              }
              sum = yuE.add(jiaoE);
            }
            if (fuzhuEmpId != null && !"".equals(fuzhuEmpId)) {
              Emp emp = empDAO.queryByCriterions(fuzhuEmpId.toString(),
                  fuzhuOrgId.toString());
              yuE = yuE.add(assistantBorrower.getAccBlnce());// .add((emp.getOrgPay().add(emp.getEmpPay()).multiply(new
              // BigDecimal(12))));

              for (int i = 0; i < agelist.size(); i++) {
                loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                    .get(i);
                String sexdto = loanConditionsParamSetDTO.getParamValue();
                int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                    .getParamExplain());
                String fuZhuAge = "";
                fuZhuAge = borrowerDAO.countPeopleNum_Age(contactid);
                if (fuzhuSex.equals("1")) {// ������Ա���
                  if (sexdto.equals("1")) {
                    jiaoE = jiaoE.add((assistantBorrower.getMonthPay())
                        .multiply(new BigDecimal(12)).multiply(
                            new BigDecimal(paramExplain
                                - Integer.valueOf(
                                    assistantBorrower.getAge().toString())
                                    .intValue())));
                  }

                }
                if (fuzhuSex.equals("2")) {// Ů

                  if (sexdto.equals("2")) {
                    jiaoE = jiaoE.add((assistantBorrower.getMonthPay())
                        .multiply(new BigDecimal(12)).multiply(
                            new BigDecimal(paramExplain
                                - Integer.valueOf(
                                    assistantBorrower.getAge().toString())
                                    .intValue())));
                  }
                }
              }
              sum = yuE.add(jiaoE);
            }

            int loanm;
            if (paramExplain_12 != null && !"".equals(paramExplain_12)) {
              loanm = Integer.parseInt(paramExplain_12);
              money_4 = sum.multiply(new BigDecimal(loanm));
            }
            // ��С���
            if (money_1.compareTo(money_2) > 0
                && money_2.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_2;
            } else {
              money_temp = money_1;
            }
            if (money_temp.compareTo(money_3) > 0
                && money_3.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_3;
            }
            if (money_temp.compareTo(money_4) > 0
                && money_4.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_4;
            }
            if (money_temp.compareTo(money_5) > 0
                && money_5.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_5;
            }
            // ��С���
            System.out.println("���ַ�����˺͸�������˶�ӵ�й�����");
            System.out.println("1" + money_1);
            System.out.println("2" + money_2);
            System.out.println("3" + money_3);
            System.out.println("4" + money_4);
            System.out.println("5" + money_5);
            if (money_temp.compareTo(new BigDecimal(0)) == 0) {
              money_temp = new BigDecimal(100000000);
            }
          }
          if (num.equals("2")) {// ����˺͸�������˶�ӵ�й�����
            // ������ʵ������Ӵ������޲�����
            // if(assistantBorrower!=null){
            // if (paramExplain_7_2 != null && !"".equals(paramExplain_7_2)) {
            // money_1 = new BigDecimal(paramExplain_7_2);
            // }
            // }else{
            // if (paramExplain_7_1 != null && !"".equals(paramExplain_7_1)) {
            // money_1 = new BigDecimal(paramExplain_7_1);
            // }
            // }

            if (houses.getBargainorHouseAge().compareTo(new BigDecimal(1)) >= 0
                && houses.getBargainorHouseAge().compareTo(new BigDecimal(5)) <= 0) {
              if (paramExplain_9 != null && !"".equals(paramExplain_9)) {
                money_2 = (new BigDecimal(houses.getBargainorTotlePrice()
                    .toString())).multiply(new BigDecimal(paramExplain_9))
                    .divide(new BigDecimal(100), 2);
              }
            }
            if (houses.getBargainorHouseAge().compareTo(new BigDecimal(6)) >= 0
                && houses.getBargainorHouseAge().compareTo(new BigDecimal(10)) <= 0) {
              if (paramExplain_9_1 != null && !"".equals(paramExplain_9_1)) {
                money_2 = (new BigDecimal(houses.getBargainorTotlePrice()
                    .toString())).multiply(new BigDecimal(paramExplain_9_1))
                    .divide(new BigDecimal(100), 2);
              }
            }
            if (houses.getBargainorHouseAge().compareTo(new BigDecimal(11)) >= 0) {
              if (paramExplain_9_2 != null && !"".equals(paramExplain_9_2)) {
                money_2 = (new BigDecimal(houses.getBargainorTotlePrice()
                    .toString())).multiply(new BigDecimal(paramExplain_9_2))
                    .divide(new BigDecimal(100), BigDecimal.ROUND_CEILING);
              }
            }
            if (paramExplain_11 != null && !"".equals(paramExplain_11)) {
              money_3 = new BigDecimal(paramExplain_11);
            }
            // LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 =
            // loanConditionsSetDAO
            // .queryOverHousePriceS_1(office);
            // LoanConditionsParamSetDTO loanConditionsParamSetDTO = new
            // LoanConditionsParamSetDTO();
            // LoanConditionsParamSetDTO loanConditionsParamSetDTO_14 =
            // loanConditionsSetDAO
            // .queryOverHousePriceS_14(office);
            // String fuzhuEmpId = borrowerDAO.countPeopleNum_EmpId(contactid);
            // String fuzhuOrgId = borrowerDAO.countPeopleNum_Id(contactid);
            // String fuzhuSex = borrowerDAO.countPeopleNum_Sex(contactid);
            // borrower = borrowerDAO.queryById(contactid);
            // // if ((borrower.getEmpId()== null ||
            // //
            // "".equals(borrower.getEmpId()))&&(fuzhuEmpId==null||"".equals(fuzhuEmpId))){
            // // throw new BusinessException("����˼���������˾�δ����ס��������");
            // // }
            // // if (borrower.getEmpId() != null &&
            // !"".equals(borrower.getEmpId())) {
            // //
            // if (paramExplain_14 != null && !"".equals(paramExplain_14)) {//
            // ������
            // // �����
            // BigDecimal money = borrower.getMonthSalary().multiply(
            // new BigDecimal(paramExplain_14)).multiply(
            // new BigDecimal(loanlimit)).divide(new BigDecimal(100), 2);
            // // ���������
            //                
            // money_5 = money.add(assistantBorrower.getMonthSalary()
            // .multiply(new BigDecimal(paramExplain_14)).multiply(
            // new BigDecimal(loanlimit)).divide(new BigDecimal(100),
            // 2));
            //
            // }
            // yuE = borrower.getAccBlnce();//
            // .add((emp.getOrgPay().add(emp.getEmpPay()).multiply(new
            // // BigDecimal(12))));
            //
            // for (int i = 0; i < agelist.size(); i++) {
            // loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
            // .get(i);
            // String sexdto = loanConditionsParamSetDTO.getParamValue();
            // int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
            // .getParamExplain());
            // if (sex.equals("1")) {// ������Ա���
            // if (sexdto.equals("1")) {
            // jiaoE = (borrower.getMonthPay()).multiply(
            // new BigDecimal(12)).multiply(
            // new BigDecimal(paramExplain
            // - borrower.getAge().intValue()));
            // }
            //
            // }
            // if (sex.equals("2")) {// Ů
            //
            // if (sexdto.equals("2")) {
            // jiaoE = (borrower.getMonthPay()).multiply(
            // new BigDecimal(12)).multiply(
            // new BigDecimal(paramExplain
            // - borrower.getAge().intValue()));
            // }
            // }
            // }
            // sum = yuE.add(jiaoE);
            // // }
            // // if (fuzhuEmpId != null && !"".equals(fuzhuEmpId)) {
            // yuE = yuE.add(assistantBorrower.getAccBlnce());//
            // .add((emp.getOrgPay().add(emp.getEmpPay()).multiply(new
            // // BigDecimal(12))));
            //
            // for (int i = 0; i < agelist.size(); i++) {
            // loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
            // .get(i);
            // String sexdto = loanConditionsParamSetDTO.getParamValue();
            // int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
            // .getParamExplain());
            // if (fuzhuSex.equals("1")) {// ������Ա���
            // if (sexdto.equals("1")) {
            // jiaoE = jiaoE.add((assistantBorrower.getMonthPay())
            // .multiply(new BigDecimal(12)).multiply(
            // new BigDecimal(paramExplain
            // - Integer.valueOf(
            // assistantBorrower.getAge().toString())
            // .intValue())));
            // }
            //
            // }
            // if (fuzhuSex.equals("2")) {// Ů
            //
            // if (sexdto.equals("2")) {
            // jiaoE = jiaoE.add((assistantBorrower.getMonthPay())
            // .multiply(new BigDecimal(12)).multiply(
            // new BigDecimal(paramExplain
            // - Integer.valueOf(
            // assistantBorrower.getAge().toString())
            // .intValue())));
            // }
            // }
            // }
            // sum = yuE.add(jiaoE);
            // // }
            //
            // int loanm;
            // if (paramExplain_12 != null && !"".equals(paramExplain_12)) {
            // loanm = Integer.parseInt(paramExplain_12);
            // money_4 = sum.multiply(new BigDecimal(loanm));
            // }
            // ��С���
            if (money_2.compareTo(money_3) > 0
                && money_3.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_3;
            } else {
              money_temp = money_2;
            }
            // if (money_temp.compareTo(money_3) > 0
            // && money_3.compareTo(new BigDecimal(0)) != 0) {
            // money_temp = money_3;
            // }
            // if (money_temp.compareTo(money_4) > 0
            // && money_4.compareTo(new BigDecimal(0)) != 0) {
            // money_temp = money_4;
            // }
            // if (money_temp.compareTo(money_5) > 0
            // && money_5.compareTo(new BigDecimal(0)) != 0) {
            // money_temp = money_5;
            // }
            // ��С���
            System.out.println("���ַ�����˺͸�������˶�ӵ�й�����");
            System.out.println("1" + money_1);
            System.out.println("2" + money_2);
            System.out.println("3" + money_3);
            System.out.println("4" + money_4);
            System.out.println("5" + money_5);
            if (money_temp.compareTo(new BigDecimal(0)) == 0) {
              money_temp = new BigDecimal(100000000);
            }
          }
        }
        // ///////////////////////////////////////////////////////////////////////////////////////
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return money_temp;
  }

  public void canLoancallback_ws(LoancallbackTaAF loancallbackTaAF,
      SecurityInfo securityInfo) throws BusinessException {
    String office = loancallbackTaAF.getBorrowerInfoDTO().getOfficeCode();
    if (office != null) {
      String paramExplain = loanConditionsSetDAO.queryIsUseParamValue("14",
          office);
      if (paramExplain != null && !"".equals(paramExplain)) {
        String contractId = loancallbackTaAF.getBorrowerInfoDTO()
            .getContractId();
        BigDecimal monthSalary = borrowerDAO.findBorrrowInfoByContractid(
            contractId).getMonthSalary();
        BigDecimal corpusInterest = monthSalary.multiply(
            new BigDecimal(paramExplain)).divide(new BigDecimal(100), 4,
            BigDecimal.ROUND_HALF_UP);
        if (loancallbackTaAF.getCorpusInterest().compareTo(corpusInterest) > 0) {
          throw new BusinessException("��ǰ������»���Ϣ���õ��������뻹�������" + paramExplain
              + "%");
        }
      }

    }
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setAssistantBorrowerDAO(AssistantBorrowerDAO assistantBorrowerDAO) {
    this.assistantBorrowerDAO = assistantBorrowerDAO;
  }

}