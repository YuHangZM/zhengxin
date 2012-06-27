/**
 * Copy Right Information   : Goldsoft 
 * Project                  : LoanCheckBS
 * @Version                 : 1.0
 * @author                  : wangy
 * ��������                   : 2007-09-22
 **/
package org.xpup.hafmis.sysloan.loanapply.loancheck.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.HousesDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.loanapply.loancheck.bsinterface.ILoanCheckBS;
import org.xpup.hafmis.sysloan.loanapply.loancheck.dto.LoanCheckDTO;
import org.xpup.hafmis.sysloan.loanapply.loancheck.form.LoanCheckShowAF;

public class LoanCheckBS implements ILoanCheckBS {

  BorrowerDAO borrowerDAO = null;

  BorrowerAccDAO borrowerAccDAO = null;

  HousesDAO housesDAO = null;

  LoanBankParaDAO loanBankParaDAO = null;

  CollBankDAO collBankDAO = null;

  PlOperateLogDAO plOperateLogDAO = null;

  OrganizationUnitDAO organizationUnitDAO = null;

  SecurityDAO securityDAO = null;

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setHousesDAO(HousesDAO housesDAO) {
    this.housesDAO = housesDAO;
  }

  public void setLoanBankParaDAO(LoanBankParaDAO loanBankParaDAO) {
    this.loanBankParaDAO = loanBankParaDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  /**
   * Description ��˴���
   * 
   * @author wangy 2007-09-22
   * @param �ж����д������PL003���У�����ֵ�Ƿ����AB
   * @param ��LoanCheckShowAC����
   * @return LoanCheckShowAF
   * @throws Exception, BusinessException
   */
  public LoanCheckShowAF queryBorrowerListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    LoanCheckShowAF loanCheckShowAF = new LoanCheckShowAF();
    String isContractWrite = null;
    try {
      String paramValue = loanBankParaDAO.queryParamvalueYU();
      if (paramValue != null) {
        if (paramValue.equalsIgnoreCase("AB")) {
          loanCheckShowAF = this.queryListByCriterions(pagination,
              securityInfo, isContractWrite);
        } else {
          isContractWrite = "1";// �Ƿ�ǩ����ͬΪ1(��ǩ��)
          loanCheckShowAF = this.queryListByCriterions(pagination,
              securityInfo, isContractWrite);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanCheckShowAF;
  }

  /**
   * Description ��˴���
   * 
   * @author wangy 2007-09-22
   * @param ��˴�����Ϣ�б�
   * @param ��this.queryBorrowerListByCriterions����
   * @return LoanCheckShowAF
   * @throws Exception, BusinessException
   */
  public LoanCheckShowAF queryListByCriterions(Pagination pagination,
      SecurityInfo securityInfo, String isContractWrite) throws Exception,
      BusinessException {

    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String officeCode = (String) pagination.getQueryCriterions().get(
        "officeCode");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    String orgName = (String) pagination.getQueryCriterions().get("orgName");
    String houseType = (String) pagination.getQueryCriterions()
        .get("houseType");
    String beginBizDate = (String) pagination.getQueryCriterions().get(
        "beginBizDate");
    String endBizDate = (String) pagination.getQueryCriterions().get(
        "endBizDate");
    String beginBackDate = (String) pagination.getQueryCriterions().get(
        "beginBackDate");
    String endBackDate = (String) pagination.getQueryCriterions().get(
        "endBackDate");
    String contractStFind = (String) pagination.getQueryCriterions().get(
        "contractStFind");
    // contractSt�������ж����ĸ��ڵ������Ȼ��������Ĭ�ϵĺ�ͬ״̬
    String contractStatus = (String) pagination.getQueryCriterions().get(
        "contractSt");
    String findType = (String) pagination.getQueryCriterions().get("findType");
    LoanCheckShowAF loanCheckShowAF = new LoanCheckShowAF();
    if (officeCode != null) {
      loanCheckShowAF.setOfficeCode(this.changeOffice(officeCode));
    } else {
      loanCheckShowAF.setOfficeCode("");
    }
    // ת�����´����ڴ�ӡ��ʾ
    try {

      List loancheckList = new ArrayList();
      List loancheckListAll = new ArrayList();// ��ӡ��
      List loancheckListSum = new ArrayList();// �����ۼƵ�
      List templist = new ArrayList();
      List templistAll = new ArrayList();
      loancheckList = borrowerDAO.queryBorrowerListByCriterions_wuht(
          contractId, officeCode, borrowerName, cardNum, loanBankName, orgName,
          houseType, beginBizDate, endBizDate, contractStFind, contractStatus,
          isContractWrite, findType, start, orderBy, order, pageSize, page,
          securityInfo, beginBackDate, endBackDate);

      count = borrowerDAO.queryBorrowerCountByCriterions_wsh(contractId,
          officeCode, borrowerName, cardNum, loanBankName, orgName, houseType,
          beginBizDate, endBizDate, contractStFind, contractStatus,
          isContractWrite, findType, securityInfo, beginBackDate, endBackDate);
      pagination.setNrOfElements(count);
      Iterator iterate = loancheckList.iterator();
      Object[] obj = null;
      BigDecimal loanTotleMoney = new BigDecimal(0.00);// �����-�ܶ�
      BigDecimal loanMoney = new BigDecimal(0.00);// �����
      BigDecimal totlePrice = new BigDecimal(0.00);// ����
      BigDecimal totlePriceAll = new BigDecimal(0.00);// �ϼƷ���
      BigDecimal houseArea = new BigDecimal(0.00);// �������
      BigDecimal houseAreaAll = new BigDecimal(0.00);// �ϼƽ������
      while (iterate.hasNext()) {
        LoanCheckDTO loanCheckDTO = new LoanCheckDTO();
        obj = (Object[]) iterate.next();
        if (obj[0] != null && !obj[0].equals(""))
          loanCheckDTO.setContractId(obj[0].toString());
        if (obj[1] != null && !obj[1].equals(""))
          loanCheckDTO.setBorrowerName(obj[1].toString());
        if (obj[2] != null && !obj[2].equals(""))
          loanCheckDTO.setCardNum(obj[2].toString());
        if (obj[3] != null && !obj[3].equals("")) {
          loanMoney = new BigDecimal(obj[3].toString());
          loanMoney = loanMoney.divide(new BigDecimal(10000), 1,
              BigDecimal.ROUND_HALF_UP);
          loanCheckDTO.setLoanMoney(loanMoney.toString());
        }
        if (obj[4] != null && !obj[4].equals(""))
          loanCheckDTO.setLoanTimeLimit(obj[4].toString());
        String loanBankId = "";
        if (obj[5] != null && !obj[5].equals(""))
          loanBankId = obj[5].toString();
        if (obj[6] != null && !obj[6].equals(""))
          loanCheckDTO.setOrgName(obj[6].toString());
        if (obj[7] != null && !obj[7].equals("")) {
          houseArea = new BigDecimal(obj[7].toString());
          houseArea = houseArea.divide(new BigDecimal(1), 2,
              BigDecimal.ROUND_HALF_UP);
          loanCheckDTO.setHouseArea(houseArea.toString());
        }
        String house_type = "";
        if (obj[8] != null && !obj[8].equals(""))
          house_type = obj[8].toString();
        String contractSt = null;
        if (obj[9] != null && !obj[9].equals(""))
          contractSt = obj[9].toString();
        if (obj[11] != null && !obj[11].equals("")) {
          totlePrice = new BigDecimal(obj[11].toString());
          totlePrice = totlePrice.divide(new BigDecimal(1), 2,
              BigDecimal.ROUND_HALF_UP);
          totlePriceAll = totlePriceAll.add(totlePrice);
          loanCheckDTO.setTotlePrice(totlePrice.toString());
        }
        if (obj[13] != null && !obj[13].equals(""))
          loanCheckDTO.setHouseAddr(obj[13].toString());

        if (obj[15] != null && !obj[15].equals(""))
          loanCheckDTO.setRemark(obj[15].toString());
        if (obj[16] != null && !obj[16].equals(""))
          loanCheckDTO.setAssistantborrowerName(obj[16].toString());
        if (obj[17] != null && !obj[17].equals("")) {
          loanCheckDTO.setOperator(securityDAO
              .queryByUserid(obj[17].toString()));
        }
        if (obj[18] != null && !obj[18].equals("")) {
          loanCheckDTO.setReDate(obj[18].toString());
        }
        if (obj[19] != null && !obj[19].equals("")) {
          loanCheckDTO.setPhotoUrl(obj[19].toString());
        }
        if (obj[20] != null && !obj[20].equals("")) {
          loanCheckDTO.setIsContract_write(obj[20].toString().equals("1") ? "��"
              : "��");
        }
        if (obj[21] != null && !obj[21].equals("")) {
          loanCheckDTO.setVipcheckDate(obj[21].toString());
        }
        // ת����������
        if (loanBankId != null && !loanBankId.equals("")) {
          try {
            CollBank dto = collBankDAO.getCollBankByCollBankid(loanBankId
                .toString());
            if (dto != null) {
              if (loanBankId.equals("0")) {
                loanCheckDTO.setLoanBankName("");
              } else {
                loanCheckDTO.setLoanBankName(dto.getCollBankName());
              }
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        // ö��ת����������
        try {
          loanCheckDTO.setHouseType(BusiTools.getBusiValue_WL(house_type,
              BusiConst.PLHOUSETYPE));
        } catch (Exception e) {
          e.printStackTrace();
        }
        // ö��ת����ͬ״̬
        try {
          loanCheckDTO.setContractSt(BusiTools.getBusiValue(Integer.parseInt(""
              + contractSt), BusiConst.PLCONTRACTSTATUS));
        } catch (Exception e) {
          e.printStackTrace();
        }
        templist.add(loanCheckDTO);
      }
      loanCheckShowAF.setList(templist);
      loanCheckShowAF.setCount(new Integer(count));
      totlePriceAll = new BigDecimal(0.00);
      // 1�����Ӵ�ӡ����2008�����ס����������ϸ��
      loancheckListAll = borrowerDAO.queryBorrowerListByCriterionsAll_wuht(
          contractId, officeCode, borrowerName, cardNum, loanBankName, orgName,
          houseType, beginBizDate, endBizDate, contractStFind, contractStatus,
          isContractWrite, findType, start, orderBy, order, pageSize, page,
          securityInfo, beginBackDate, endBackDate);
      if (loancheckListAll != null && loancheckListAll.size() > 0) {
        Iterator iteratePrint = loancheckListAll.iterator();
        Object[] objPrint = null;
        BigDecimal loanMoneyPrint = new BigDecimal(0.00);// �����
        BigDecimal totlePricePrint = new BigDecimal(0.00);// ����
        BigDecimal houseAreaPrint = new BigDecimal(0.00);// �������
        while (iteratePrint.hasNext()) {
          LoanCheckDTO loanCheckDTO = new LoanCheckDTO();
          objPrint = (Object[]) iteratePrint.next();
          if (objPrint[0] != null && !objPrint[0].equals(""))
            loanCheckDTO.setContractId(objPrint[0].toString());
          if (objPrint[1] != null && !objPrint[1].equals(""))
            loanCheckDTO.setBorrowerName(objPrint[1].toString());
          if (objPrint[2] != null && !objPrint[2].equals(""))
            loanCheckDTO.setCardNum(objPrint[2].toString());
          if (objPrint[3] != null && !objPrint[3].equals("")) {
            loanMoneyPrint = new BigDecimal(objPrint[3].toString());
            loanMoneyPrint = loanMoneyPrint.divide(new BigDecimal(10000), 1,
                BigDecimal.ROUND_HALF_UP);
            loanTotleMoney = loanTotleMoney.add(loanMoneyPrint);
            loanCheckDTO.setLoanMoney(loanMoneyPrint.toString());
          }
          if (objPrint[4] != null && !objPrint[4].equals(""))
            loanCheckDTO.setLoanTimeLimit(objPrint[4].toString());
          String loanBankId = "";
          if (objPrint[5] != null && !objPrint[5].equals(""))
            loanBankId = objPrint[5].toString();
          if (objPrint[6] != null && !objPrint[6].equals(""))
            loanCheckDTO.setOrgName(objPrint[6].toString());
          if (objPrint[7] != null && !objPrint[7].equals("")) {
            houseAreaPrint = new BigDecimal(objPrint[7].toString());
            houseAreaPrint = houseAreaPrint.divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_UP);
            houseAreaAll = houseAreaAll.add(houseAreaPrint);
            loanCheckDTO.setHouseArea(houseAreaPrint.toString());
          }
          if (objPrint[8] != null && !objPrint[8].equals(""))
            houseType = objPrint[8].toString();
          String contractSt = null;
          if (objPrint[9] != null && !objPrint[9].equals(""))
            contractSt = objPrint[9].toString();
          if (objPrint[11] != null && !objPrint[11].equals("")) {
            totlePricePrint = new BigDecimal(objPrint[11].toString());
            totlePricePrint = totlePricePrint.divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_UP);
            totlePriceAll = totlePriceAll.add(totlePricePrint);
            loanCheckDTO.setTotlePrice(totlePricePrint.toString());
          }
          if (objPrint[13] != null && !objPrint[13].equals(""))
            loanCheckDTO.setHouseAddr(objPrint[13].toString());
          // if (objPrint[14] != null && !objPrint[14].equals(""))
          // loanCheckDTO.setHouseAddr(objPrint[14].toString());
          if (objPrint[15] != null && !objPrint[15].equals(""))
            loanCheckDTO.setRemark(objPrint[15].toString());
          if (objPrint[16] != null && !objPrint[16].equals(""))
            loanCheckDTO.setDeveloperName(objPrint[16].toString());
          if (objPrint[17] != null && !objPrint[17].equals("")) {
            loanCheckDTO.setOperator(securityDAO.queryByUserid(objPrint[17]
                .toString()));
          }
          if (objPrint[18] != null && !objPrint[18].equals(""))
            loanCheckDTO.setOffice(objPrint[18].toString());
          // ת����������
          if (loanBankId != null && !loanBankId.equals("")) {
            try {
              CollBank dto = collBankDAO.getCollBankByCollBankid(loanBankId
                  .toString());
              if (dto != null) {
                if (loanBankId.equals("0")) {
                  loanCheckDTO.setLoanBankName("");
                } else {
                  loanCheckDTO.setLoanBankName(dto.getCollBankName());
                }
              }
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
          // ö��ת����������
          try {
            loanCheckDTO.setHouseType(BusiTools.getBusiValue_WL(houseType,
                BusiConst.PLHOUSETYPE));
          } catch (Exception e) {
            e.printStackTrace();
          }
          // ö��ת����ͬ״̬
          try {
            loanCheckDTO.setContractSt(BusiTools.getBusiValue(Integer
                .parseInt("" + contractSt), BusiConst.PLCONTRACTSTATUS));
          } catch (Exception e) {
            e.printStackTrace();
          }
          templistAll.add(loanCheckDTO);
        }
      }
      if (loanTotleMoney != null && !loanTotleMoney.equals("")) {
        loanCheckShowAF.setLoanTotleMoney(loanTotleMoney);
      }
      if (totlePriceAll != null && !totlePriceAll.equals("")) {
        loanCheckShowAF.setTotlePriceAll(totlePriceAll);
      }
      if (houseAreaAll != null && !houseAreaAll.equals("")) {
        loanCheckShowAF.setHouseAreaAll(houseAreaAll);
      }

      // �����ۼ�
      loancheckListSum = borrowerDAO.queryBorrowerListByCriterionsSum_wuht(
          officeCode, start, orderBy, order, pageSize, page, securityInfo);
      if (loancheckListSum != null && loancheckListSum.size() > 0) {
        // �ۼƻ���
        loanCheckShowAF.setCount(new Integer(loancheckListSum.size()));

        Iterator iterateSum = loancheckListSum.iterator();
        Object[] objPrint = null;
        BigDecimal loanMoneySum = new BigDecimal(0.00);
        BigDecimal totlePriceSum = new BigDecimal(0.00);
        BigDecimal houseAreaSum = new BigDecimal(0.00);
        BigDecimal loanMoneyAllSum = new BigDecimal(0.00);
        BigDecimal totlePriceAllSum = new BigDecimal(0.00);
        BigDecimal houseAreaAllSum = new BigDecimal(0.00);
        while (iterateSum.hasNext()) {
          objPrint = (Object[]) iterateSum.next();
          if (objPrint[3] != null && !objPrint[3].equals("")) {
            loanMoneySum = new BigDecimal(objPrint[3].toString());
            loanMoneySum = loanMoneySum.divide(new BigDecimal(10000), 1,
                BigDecimal.ROUND_HALF_UP);
            loanMoneyAllSum = loanMoneyAllSum.add(loanMoneySum);
          }

          if (objPrint[7] != null && !objPrint[7].equals("")) {
            houseAreaSum = new BigDecimal(objPrint[7].toString());
            houseAreaSum = houseAreaSum.divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_UP);
            houseAreaAllSum = houseAreaAllSum.add(houseAreaSum);

          }

          if (objPrint[10] != null && !objPrint[10].equals("")) {
            houseAreaSum = new BigDecimal(objPrint[10].toString());
            houseAreaSum = houseAreaSum.divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_UP);
            houseAreaAllSum = houseAreaAllSum.add(houseAreaSum);
          }
          // wuht
          if (objPrint[11] != null && !objPrint[11].equals("")) {
            totlePriceSum = new BigDecimal(objPrint[11].toString());
            totlePriceSum = totlePriceSum.divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_UP);
            totlePriceAllSum = totlePriceAllSum.add(totlePriceSum);

          }
          if (objPrint[12] != null && !objPrint[12].equals("")) {
            totlePriceSum = new BigDecimal(objPrint[12].toString());
            totlePriceSum = totlePriceSum.divide(new BigDecimal(1), 2,
                BigDecimal.ROUND_HALF_UP);
            totlePriceAllSum = totlePriceAllSum.add(totlePriceSum);
          }
        }
        loanCheckShowAF.setTotlePriceAllYearSum(totlePriceAllSum);
        loanCheckShowAF.setHouseAreaAllYearSum(houseAreaAllSum);
        loanCheckShowAF.setLoanTotleMoneyYearSum(loanMoneyAllSum);

      }
      loanCheckShowAF.setListAll(templistAll);

    } catch (Exception ex) {

      ex.printStackTrace();
    }

    return loanCheckShowAF;
  }

  /**
   * Description ��˴���
   * 
   * @author wangy 2007-09-22
   * @param ���ͨ��
   * @param ��LoanCheckMaintainAC����
   * @param contractId
   * @param securityInfo
   * @throws Exception, BusinessException
   */
  public void updateContractSTCheckPass(String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
    String contractSt = borrowerAcc.getContractSt();
    if (contractSt.equals("15")) {// �ύ���
      borrowerAcc.setContractSt("3");
    } else if (contractSt.equals("7")) { // �ٴ����
      borrowerAcc.setContractSt("8");// �ٴ�����
    } else {
      // ö��ת����ͬ״̬
      contractSt = BusiTools.getBusiValue(Integer.parseInt("" + contractSt),
          BusiConst.PLCONTRACTSTATUS);
      throw new BusinessException("�ü�¼״̬Ϊ" + contractSt + "���������ͨ����");
    }
    // ������־ pl021 PlOperateLog PlOperateLogDAO
    String operateName = securityInfo.getUserInfo().getUsername();// ����Ա
    String userIp = securityInfo.getUserInfo().getUserIp();// ����ԱIP
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ���� 2
    plOperateLog.setOpModel(new Integer(BusiLogConst.PL_OP_LOANAPPL_LOANAUDIT)
        .toString());// ��˴��� 28
    plOperateLog.setOpButton(new Integer(
        BusiLogConst.BIZLOG_ACTION_AUDITINGPASS).toString());// ���ͨ�� 15
    plOperateLog.setOpBizId(new BigDecimal(contractId));
    plOperateLog.setOpIp(userIp);
    plOperateLog.setContractId(contractId);
    plOperateLog.setOpTime(new Date());
    plOperateLog.setOperator(operateName);
    plOperateLogDAO.insert(plOperateLog);
  }

  /**
   * Description ��˴���
   * 
   * @author wangy 2007-09-22
   * @param ���δͨ�� update BorrowerAcc
   * @param ��LoanCheckReasonAC����
   * @param contractId
   * @param reasonA
   * @param securityInfo
   * @throws Exception, BusinessException
   */
  public void updateContractSTCheckNotPass(String contractId, String reasonA,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
    if (borrowerAcc.getContractSt().equals("15")) { // �ύ���
      borrowerAcc.setContractSt("5"); // ��˲�ͨ��
    } else if (borrowerAcc.getContractSt().equals("16")) { // �ύ���
      borrowerAcc.setContractSt("19"); // ��˲�ͨ��
    }
    borrowerAcc.setReasonA(reasonA);
  }

  /**
   * Description ��˴���
   * 
   * @author wangy 2007-09-22
   * @param ������� update BorrowerAcc
   * @param ��LoanCheckMaintainAC����
   * @param contractId
   * @param securityInfo
   * @throws Exception, BusinessException
   */
  public void updateContractSTCheckQuash(String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
    String contractSt = null;
    if (borrowerAcc.getContractSt().equals("3")) {// ���ͨ��
      borrowerAcc.setContractSt("15"); // �ύ���
    } else if (borrowerAcc.getContractSt().equals("8")) { // �ٴ�����
      borrowerAcc.setContractSt("7");// �ٴ����
    } else {
      // ��ʾ״̬ �������
      // ö��ת����ͬ״̬
      try {
        contractSt = BusiTools.getBusiValue(Integer.parseInt(""
            + borrowerAcc.getContractSt()), BusiConst.PLCONTRACTSTATUS);
      } catch (Exception e) {
        e.printStackTrace();
      }
      throw new BusinessException("�ü�¼״̬Ϊ" + contractSt + "�����ܳ�����ˣ�");
    }
    // ������־ pl021
    String operateName = securityInfo.getUserInfo().getUsername();// ����Ա
    String userIp = securityInfo.getUserInfo().getUserIp();// ����ԱIP
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ���� 2
    plOperateLog.setOpModel(new Integer(BusiLogConst.PL_OP_LOANAPPL_LOANAUDIT)
        .toString());// ��˴��� 28
    plOperateLog.setOpButton(new Integer(
        BusiLogConst.BIZLOG_ACTION_AUDITINGQUASH).toString());// ������� 17
    plOperateLog.setOpBizId(new BigDecimal(contractId));
    plOperateLog.setOpIp(userIp);
    plOperateLog.setContractId(contractId);
    plOperateLog.setOpTime(new Date());
    plOperateLog.setOperator(operateName);
    plOperateLogDAO.insert(plOperateLog);
  }

  /**
   * Description ��˴���
   * 
   * @author wangy 2007-09-22
   * @param ת��office
   * @param ��LoanCheckTaShowAC����
   * @param office
   * @return String
   * @throws Exception
   */
  public String changeOffice(String office) throws Exception {
    OrganizationUnit organizationUnit = organizationUnitDAO
        .queryOrganizationUnitListByCriterions(office);
    office = organizationUnit.getName();
    return office;
  }

  public void updateContractSTCheckPassrowArray(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    try {
      if (rowArray.length > 0) {
        String contractId;
        for (int i = 0; i < rowArray.length; i++) {
          contractId = rowArray[i];
          BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
          Borrower borrower = borrowerDAO.queryById(contractId);
          String contractSt = borrowerAcc.getContractSt();
          if (borrowerAcc.getIsContractWrite() != null
              && borrowerAcc.getIsContractWrite().equals("0")) {
            throw new BusinessException("��ͬ��Ϊ" + contractId + "δ�ύ���������ͨ����");
          }
          if (contractSt.equals("15")) {// �ύ���
            borrower.setChkPerson(securityInfo.getUserInfo().getUsername());
            borrowerAcc.setContractSt("3");
          } else if (contractSt.equals("7")) { // �ٴ����
            borrowerAcc.setContractSt("8");// �ٴ�����
          } else {
            // ö��ת����ͬ״̬
            contractSt = BusiTools.getBusiValue(Integer.parseInt(""
                + contractSt), BusiConst.PLCONTRACTSTATUS);
            throw new BusinessException("�ü�¼״̬Ϊ" + contractSt + "���������ͨ����");
          }
        }

      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  public void updateContractSTCheckNotPassrowArray(String[] rowArray,
      String reasonA, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    try {
      if (rowArray.length > 0) {
        String contractId;
        for (int i = 0; i < rowArray.length; i++) {
          contractId = rowArray[i];
          BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
          if (borrowerAcc.getContractSt().equals("15")) { // �ύ���
            borrowerAcc.setContractSt("5"); // ��˲�ͨ��
          } else if (borrowerAcc.getContractSt().equals("16")
              || borrowerAcc.getContractSt().equals("20")) { // �ύ���
            borrowerAcc.setContractSt("19"); // ��˲�ͨ��
          }
          borrowerAcc.setReasonA(reasonA);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  public void updateContractSTCheckQuashrowArray(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {

      if (rowArray.length > 0) {
        String contractId;
        for (int i = 0; i < rowArray.length; i++) {
          contractId = rowArray[i];
          BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
          String contractSt = null;
          if (borrowerAcc.getContractSt().equals("3")) {// ���ͨ��
            borrowerAcc.setContractSt("15"); // �ύ���
          } else if (borrowerAcc.getContractSt().equals("8")) { // �ٴ�����
            borrowerAcc.setContractSt("7");// �ٴ����
          } else {
            // ��ʾ״̬ �������
            // ö��ת����ͬ״̬
            try {
              contractSt = BusiTools.getBusiValue(Integer.parseInt(""
                  + borrowerAcc.getContractSt()), BusiConst.PLCONTRACTSTATUS);
            } catch (Exception e) {
              e.printStackTrace();
            }
            throw new BusinessException("�ü�¼״̬Ϊ" + contractSt + "�����ܳ�����ˣ�");
          }
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * �ؼ�ȷ��
   */
  public void updateContractStRedateSure(String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    try {
      BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
      String contractSt = borrowerAcc.getContractSt();
      if (contractSt.equals(BusiConst.PLCONTRACTSTATUS_THROUGHAPPROVAL + "")
          || contractSt.equals(BusiConst.PLCONTRACTSTATUS_CHKAGAIN_NOTPASS + "")) {// �ύ���
        Borrower borrower = borrowerDAO.queryById(contractId);
        borrower.setRedate(securityInfo.getUserInfo().getPlbizDate());// ���»ؼ�����
        borrowerAcc.setContractSt(BusiConst.PLCONTRACTSTATUS_REDATESURE + "");
        borrowerAcc.setRedatePerson(securityInfo.getUserInfo().getUsername());// ����Ӽ���
      } else {
        // ö��ת����ͬ״̬
        contractSt = BusiTools.getBusiValue(Integer.parseInt("" + contractSt),
            BusiConst.PLCONTRACTSTATUS);
        throw new BusinessException("�ü�¼״̬Ϊ" + contractSt + "�����ܻؼ�ȷ�ϣ�");
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }
  /**
   * �����ؼ�ȷ��
   */
  public void updateContractStRedateSureDel(String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    try {
      BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
      String contractSt = borrowerAcc.getContractSt();
      if (contractSt.equals(BusiConst.PLCONTRACTSTATUS_REDATESURE + "")) {
        Borrower borrower = borrowerDAO.queryById(contractId);
        borrower.setRedate("");// ���»ؼ�����
        borrowerAcc.setContractSt(BusiConst.PLCONTRACTSTATUS_THROUGHAPPROVAL + "");
        borrowerAcc.setRedatePerson("");// ����Ӽ���
      } else {
        // ö��ת����ͬ״̬
        contractSt = BusiTools.getBusiValue(Integer.parseInt("" + contractSt),
            BusiConst.PLCONTRACTSTATUS);
        throw new BusinessException("�ü�¼״̬Ϊ" + contractSt + "�����ܳ����ؼ�ȷ�ϣ�");
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * �ٴ����ͨ��
   */
  public void updateContractStChkAgainPass(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String contractId;
    try {
      for (int i = 0; i < rowArray.length; i++) {
        contractId = rowArray[i];
        BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
        String contractSt = borrowerAcc.getContractSt();
        if (contractSt.equals(BusiConst.PLCONTRACTSTATUS_REDATESURE + "")) {// �ؼ�ȷ��
          borrowerAcc.setContractSt(BusiConst.PLCONTRACTSTATUS_CHKAGAIN + "");
          borrowerAcc.setChkAgainPerson(securityInfo.getUserInfo()
              .getUsername());// �����ٴ������
        } else if (contractSt.equals(BusiConst.PLCONTRACTSTATUS_APPROVALAGAIN_NOTPASS + "")) {// �ؼ�ȷ��
          borrowerAcc.setContractSt(BusiConst.PLCONTRACTSTATUS_CHKAGAIN + "");
          borrowerAcc.setChkAgainPerson(securityInfo.getUserInfo()
              .getUsername());// �����ٴ������
        } else {
          // ö��ת����ͬ״̬
          contractSt = BusiTools.getBusiValue(
              Integer.parseInt("" + contractSt), BusiConst.PLCONTRACTSTATUS);
          throw new BusinessException("�ü�¼״̬Ϊ" + contractSt + "�������ٴ����ͨ����");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * �����ٴ����
   */
  public void updateContractStChkAgainQuash(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String contractId;
    try {
      for (int i = 0; i < rowArray.length; i++) {
        contractId = rowArray[i];
        BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
        String contractSt = borrowerAcc.getContractSt();
        if (contractSt.equals(BusiConst.PLCONTRACTSTATUS_CHKAGAIN + "")) {// �ύ���
          borrowerAcc.setContractSt(BusiConst.PLCONTRACTSTATUS_REDATESURE + "");
        } else {
          // ö��ת����ͬ״̬
          contractSt = BusiTools.getBusiValue(
              Integer.parseInt("" + contractSt), BusiConst.PLCONTRACTSTATUS);
          throw new BusinessException("�ü�¼״̬Ϊ" + contractSt + "�����ܳ����ٴ���ˣ�");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }
}
