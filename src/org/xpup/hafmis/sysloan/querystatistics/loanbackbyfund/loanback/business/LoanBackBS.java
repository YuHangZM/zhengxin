package org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanback.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanback.bsinterface.ILoanBackBS;
import org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanback.dto.LoanBackBankDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanback.dto.LoanBackDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanback.form.LoanBackAF;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.dto.LoanBusiFlowQueryClearDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.dto.BailClearInterestTbDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.form.BailClearInterestTbAF;

/**
 * @author ��Ұ 2007-10-15
 */
public class LoanBackBS implements ILoanBackBS {

  private LoanFlowHeadDAO loanFlowHeadDAO = null;// ��ˮͷ�� PL202

  private CollBankDAO collBankDAO = null;// ת����������

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  /**
   * Description ����ҵ����ˮͳ�Ʋ�ѯ�б�
   * 
   * @author wangy 2007-10-16
   * @param ����������ѯ�б�
   * @param pagination
   * @param securityInfo
   * @param ��LoanBusiFlowQueryShowAC����
   * @return LoanBusiFlowQueryAF
   * @throws Exception, BusinessException
   */
  public LoanBackAF queryLoanBusiFlowQueryListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    LoanBackAF loanBackAF = new LoanBackAF();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String office = (String) pagination.getQueryCriterions().get("office");
    String loanBank = (String) pagination.getQueryCriterions().get("loanBank");
    String beginBizDate = (String) pagination.getQueryCriterions().get(
        "beginBizDate");
    String endBizDate = (String) pagination.getQueryCriterions().get(
        "endBizDate");
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String borrowerCardNum = (String) pagination.getQueryCriterions().get(
        "borrowerCardNum");
    String docNum = (String) pagination.getQueryCriterions().get("docNum");
    String noteNum = (String) pagination.getQueryCriterions().get("noteNum");
    String orgId = (String) pagination.getQueryCriterions().get("orgId");
    String orgName = (String) pagination.getQueryCriterions().get("orgName");
    String empId = (String) pagination.getQueryCriterions().get("empId");
    List loanBusiFlowQueryList = new ArrayList();
    List printList = new ArrayList();// ��ӡ�б�
    List templist = new ArrayList();
    List temptotlelist = new ArrayList();
    BigDecimal data_1 = new BigDecimal("0.00");
    BigDecimal data_2 = new BigDecimal("0.00");
    BigDecimal data_3 = new BigDecimal("0.00");
    // �޸ļ�¼�����Ʒ����ϵͳ��Ҫ���Ӵ���ҵ����ˮ����Ҫͨ��Ʊ�ݺţ�����ţ����в�ѯ���ڲ����б����������һ��notNum 2007-11-10
    templist = loanFlowHeadDAO.queryLoanBackListByCriterions_wsh(start,
        orderBy, order, pageSize, page, securityInfo, office, loanBank,
        contractId, borrowerName, borrowerCardNum, docNum, beginBizDate,
        endBizDate, orgId, orgName, empId, noteNum);
    temptotlelist = loanFlowHeadDAO.queryLoanBackAllListByCriterions_wsh(
        securityInfo, office, loanBank, contractId, borrowerName,
        borrowerCardNum, docNum, beginBizDate, endBizDate, orgId, orgName,
        empId, noteNum);
//    Object dataObj[] = loanFlowHeadDAO
//        .queryLoanBackAllListByCriterions_data_1_wsh(securityInfo, office,
//            loanBank, contractId, borrowerName, borrowerCardNum, docNum,
//            beginBizDate, endBizDate, orgId, orgName, empId, noteNum);
    Object dateObj[] = loanFlowHeadDAO.queryLoanBackAllListByCriterions_date(
        securityInfo, office, loanBank, contractId, borrowerName,
        borrowerCardNum, docNum, beginBizDate, endBizDate, orgId, orgName,
        empId, noteNum);
    if(temptotlelist!=null && temptotlelist.size()!=0){
      Iterator its = temptotlelist.iterator();
      Object []obj = null;
      while (its.hasNext()) {
        obj = (Object[]) its.next();
        if(obj[5]!=null){
          data_1=data_1.add(new BigDecimal(obj[5].toString()));
        }
        if(obj[6]!=null){
          data_2=data_2.add(new BigDecimal(obj[6].toString()));
        }
        if(obj[7]!=null){
          data_3=data_3.add(new BigDecimal(obj[7].toString()));
        }
      }
    }
//  
    String date = "";
    if(dateObj!=null){
      if(dateObj[0]!=null){
        if(dateObj[0].toString().equals(dateObj[1].toString())){
          date = dateObj[0].toString();
        }else{
          date = dateObj[0].toString() + " �� " + dateObj[1].toString();
        }
      }
    }
    List rlist_yg = loanFlowHeadDAO.queryLoanBackBankListByCriterions_yg(
        securityInfo, office, loanBank, contractId, borrowerName,
        borrowerCardNum, docNum, beginBizDate, endBizDate, orgId, orgName,
        empId, noteNum);
    if(rlist_yg!=null && rlist_yg.size()!=0){
      LoanBackBankDTO loanBackBankDTO = (LoanBackBankDTO)rlist_yg.get(0);
      loanBackBankDTO.setCorpus(data_1);
      loanBackBankDTO.setInterest(data_2);
      loanBackBankDTO.setCorpusInterest(data_3);
      loanBackBankDTO.setAll(data_1.add(data_2).add(data_3));
      loanBackBankDTO.setCount(temptotlelist.size());
    }
    count = temptotlelist.size();
    // ����ҵ����ˮ��ѯ�б�
    Iterator iterate = templist.iterator();
    Object[] obj = null;
    while (iterate.hasNext()) {
      LoanBackDTO loanBackDTO = new LoanBackDTO();
      obj = (Object[]) iterate.next();
      if (obj[0] != null && !obj[0].equals("")) {
        loanBackDTO.setOrgId(BusiTools.convertTenNumber(obj[0].toString()));
      }
      if (obj[1] != null && !obj[1].equals("")) {
        loanBackDTO.setOrgName(obj[1].toString());
      }
      if (obj[2] != null && !obj[2].equals("")) {
        loanBackDTO.setContractId(obj[2].toString());
      }
      if (obj[3] != null && !obj[3].equals("")) {
        loanBackDTO.setBorrowerName(obj[3].toString());
      }
      if (obj[4] != null && !obj[4].equals("")) {
        loanBackDTO.setYearMonth(obj[4].toString());
      }
      if (obj[5] != null && !obj[5].equals("")) {
        loanBackDTO.setBenjin(obj[5].toString());
      }
      if (obj[6] != null && !obj[6].equals("")) {
        loanBackDTO.setInterest(obj[6].toString());
      }
      if (obj[7] != null && !obj[7].equals("")) {
        loanBackDTO.setPunishInterest(obj[7].toString());
      }
      if (obj[8] != null && !obj[8].equals("")) {
        loanBackDTO.setSum(obj[8].toString());
      }
      if (obj[9] != null && !obj[9].equals("")) {
        loanBackDTO.setBorrowerEmpId(BusiTools.convertSixNumber(String
            .valueOf(obj[9])));
      }
      if (obj[10] != null && !obj[10].equals("")) {
        loanBackDTO.setKouPersonName(obj[10].toString());
      }
      if (obj[11] != null && !obj[11].equals("")) {
        loanBackDTO.setBizDate(obj[11].toString());
      }
      if (obj[12] != null && !obj[12].equals("")) {
        loanBackDTO.setBatchNum(obj[12].toString());
      }
      loanBusiFlowQueryList.add(loanBackDTO);
    }
    loanBackAF.setList(loanBusiFlowQueryList);
    Iterator iteratetotle = temptotlelist.iterator();
    while (iteratetotle.hasNext()) {
      obj = (Object[]) iteratetotle.next();
      LoanBackDTO loanBackDTO = new LoanBackDTO();
      if (obj[0] != null && !obj[0].equals("")) {
        loanBackDTO.setOrgId(BusiTools.convertTenNumber(obj[0].toString()));
      }
      if (obj[1] != null && !obj[1].equals("")) {
        loanBackDTO.setOrgName(obj[1].toString());
      }
      if (obj[2] != null && !obj[2].equals("")) {
        loanBackDTO.setContractId(obj[2].toString());
      }
      if (obj[3] != null && !obj[3].equals("")) {
        loanBackDTO.setBorrowerName(obj[3].toString());
      }
      if (obj[4] != null && !obj[4].equals("")) {
        loanBackDTO.setYearMonth(obj[4].toString());
      }
      if (obj[5] != null && !obj[5].equals("")) {
        loanBackDTO.setBenjin(obj[5].toString());
      }
      if (obj[6] != null && !obj[6].equals("")) {
        loanBackDTO.setInterest(obj[6].toString());
      }
      if (obj[7] != null && !obj[7].equals("")) {
        loanBackDTO.setPunishInterest(obj[7].toString());
      }
      if (obj[8] != null && !obj[8].equals("")) {
        loanBackDTO.setSum(obj[8].toString());
      }
      if (obj[9] != null && !obj[9].equals("")) {
        loanBackDTO.setBorrowerEmpId(BusiTools.convertSixNumber(String
            .valueOf(obj[9])));
      }
      if (obj[10] != null && !obj[10].equals("")) {
        loanBackDTO.setKouPersonName(obj[10].toString());
      }
      if (obj[11] != null && !obj[11].equals("")) {
        loanBackDTO.setBizDate(obj[11].toString());
      }
      if (obj[12] != null && !obj[12].equals("")) {
        loanBackDTO.setBatchNum(obj[12].toString());
      }
      printList.add(loanBackDTO);
    }
    loanBackAF.setData_1(data_1);
    loanBackAF.setData_2(data_2);
    loanBackAF.setData_3(data_3);
    loanBackAF.setData_4(data_1.add(data_2).add(data_3));
    loanBackAF.setPrintDate(date);
    loanBackAF.setPrintList(printList);
    loanBackAF.setBankList_yg(rlist_yg);
    pagination.setNrOfElements(count);
    return loanBackAF;
  }

  /**
   * Description ����ҵ����ˮͳ�Ʋ�ѯ�б�
   * 
   * @author wangy 2007-10-18
   * @param ����PL202.FLOW_HEAD_ID��ѯ��ת�����Ϣ
   * @param flowHeadId
   * @param securityInfo
   * @param ��LoanBusiFlowQueryClearShowWindowAC����
   * @return LoanBusiFlowQueryClearDTO
   * @throws Exception, BusinessException
   */
  public LoanBusiFlowQueryClearDTO queryLoanBusiFlowQueryClearByFlowHeadId(
      String flowHeadId, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    LoanBusiFlowQueryClearDTO loanBusiFlowQueryClearDTO = new LoanBusiFlowQueryClearDTO();
    try {
      CollBank collBank = collBankDAO.getCollBankByCollBankid_(flowHeadId);
      loanBusiFlowQueryClearDTO.setLoanBankName(collBank.getCollBankName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanBusiFlowQueryClearDTO;
  }

  /**
   * Description ����ҵ����ˮͳ�Ʋ�ѯ�б�
   * 
   * @author wangy 2007-10-19
   * @param ����PL202.FLOW_HEAD_ID��ѯ��Ϣ��Ϣ
   * @param flowHeadId
   * @param securityInfo
   * @param ��LoanBusiFlowQueryClearShowWindowAC����
   * @return LoanBusiFlowQueryClearDTO
   * @throws Exception, BusinessException
   */
  public BailClearInterestTbAF queryLoanBusiFlowQueryBailClearListByFlowHeadId(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String flowHeadId = (String) pagination.getQueryCriterions().get(
        "flowHeadId");
    BigDecimal firstBalanceTotle = new BigDecimal(0.00);// ��Ϣǰ��֤��-�ܶ�
    BigDecimal occurMoneyTotle = new BigDecimal(0.00);// ��Ϣ��Ϣ-�ܶ�
    BigDecimal lastBalanceTotle = new BigDecimal(0.00);// ��Ϣ��֤��-�ܶ�
    BailClearInterestTbAF bailClearInterestTbAF = new BailClearInterestTbAF();
    List bailclearinterestTbList = new ArrayList();
    List templist = new ArrayList();
    List printList = new ArrayList();
    bailclearinterestTbList = loanFlowHeadDAO
        .queryBailClearInterestTbListByCriterions(flowHeadId, start, orderBy,
            order, pageSize, page);
    if (bailclearinterestTbList.size() > 0) {
      List countList = new ArrayList();
      countList = loanFlowHeadDAO
          .queryBailClearInterestTbCountByCriterions(flowHeadId);
      count = countList.size();
      Iterator iterate = bailclearinterestTbList.iterator();
      Object[] obj = null;
      while (iterate.hasNext()) {
        BailClearInterestTbDTO bailClearInterestTbDTO = new BailClearInterestTbDTO();
        obj = (Object[]) iterate.next();
        if (obj[0] != null && !obj[0].equals("")) {
          String bizDateTable = obj[0].toString();
          bailClearInterestTbDTO.setBizYear(bizDateTable.substring(0, 4));
        }
        String loanBankId = null;
        if (obj[1] != null && !obj[1].equals(""))
          loanBankId = obj[1].toString();
        if (obj[2] != null && !obj[2].equals(""))
          bailClearInterestTbDTO.setLoanKouAcc(obj[2].toString());
        if (obj[3] != null && !obj[3].equals(""))
          bailClearInterestTbDTO.setBorrowerName(obj[3].toString());
        String bailBalance = null;
        if (obj[4] != null && !obj[4].equals("")) {
          bailBalance = obj[4].toString();
        }
        String occurMoney = null;
        if (obj[5] != null && !obj[5].equals("")) {
          occurMoney = obj[5].toString();
          bailClearInterestTbDTO.setOccurMoney(occurMoney);
        }
        BigDecimal afterYearOccurMoney = new BigDecimal(0.00);// ��Ϣ���֮��Ľ�Ϣ��Ϣ��������Ϣ��ȣ�
        if (obj[6] != null && !obj[6].equals(""))
          afterYearOccurMoney = afterYearOccurMoney.add(new BigDecimal(obj[6]
              .toString()));

        BigDecimal firstBalance = new BigDecimal(0.00);// ��Ϣǰ��֤��
        firstBalance = new BigDecimal(bailBalance).add(afterYearOccurMoney
            .negate());
        bailClearInterestTbDTO.setBailBalance(firstBalance.toString());
        BigDecimal lastBalance = new BigDecimal(0.00);// ��Ϣ��֤��
        lastBalance = firstBalance.add(new BigDecimal(occurMoney));// ��Ϣǰ��֤��+��Ϣ��Ϣ
        bailClearInterestTbDTO.setLastBalance(lastBalance.toString());
        // ת����������
        try {
          CollBank dto = collBankDAO.getCollBankByCollBankid(loanBankId
              .toString());
          bailClearInterestTbDTO.setLoanBankName(dto.getCollBankName());
        } catch (Exception e) {
          e.printStackTrace();
        }
        templist.add(bailClearInterestTbDTO);
      }
      // ��ӡȫ���б�
      Iterator iter = countList.iterator();
      Object[] object = null;
      while (iter.hasNext()) {
        BailClearInterestTbDTO bailClearInterestTbDTO = new BailClearInterestTbDTO();
        object = (Object[]) iter.next();
        if (object[0] != null && !object[0].equals("")) {
          String bizDateTable = object[0].toString();
          bailClearInterestTbDTO.setBizYear(bizDateTable.substring(0, 4));
        }
        String loanBankId = null;
        if (object[1] != null && !object[1].equals(""))
          loanBankId = object[1].toString();
        if (object[2] != null && !object[2].equals(""))
          bailClearInterestTbDTO.setLoanKouAcc(object[2].toString());
        if (object[3] != null && !object[3].equals(""))
          bailClearInterestTbDTO.setBorrowerName(object[3].toString());
        String bailBalance = null;
        if (object[4] != null && !object[4].equals("")) {
          bailBalance = object[4].toString();
        }
        String occurMoney = null;
        if (object[5] != null && !object[5].equals("")) {
          occurMoney = object[5].toString();
          bailClearInterestTbDTO.setOccurMoney(occurMoney);
        }
        BigDecimal afterYearOccurMoney = new BigDecimal(0.00);// ��Ϣ���֮��Ľ�Ϣ��Ϣ��������Ϣ��ȣ�
        if (object[6] != null && !object[6].equals(""))
          afterYearOccurMoney = afterYearOccurMoney.add(new BigDecimal(
              object[6].toString()));

        BigDecimal firstBalance = new BigDecimal(0.00);// ��Ϣǰ��֤��
        firstBalance = new BigDecimal(bailBalance).add(afterYearOccurMoney
            .negate());
        bailClearInterestTbDTO.setBailBalance(firstBalance.toString());
        BigDecimal lastBalance = new BigDecimal(0.00);// ��Ϣ��֤��
        lastBalance = firstBalance.add(new BigDecimal(occurMoney));// ��Ϣǰ��֤��+��Ϣ��Ϣ
        bailClearInterestTbDTO.setLastBalance(lastBalance.toString());
        // �ϼ�
        firstBalanceTotle = firstBalanceTotle.add(firstBalance);// ��Ϣǰ��֤��-�ܶ�
        occurMoneyTotle = occurMoneyTotle.add(new BigDecimal(occurMoney));// ��Ϣ��Ϣ-�ܶ�
        lastBalanceTotle = lastBalanceTotle.add(lastBalance);// ��Ϣ��֤��-�ܶ�
        // ת����������
        try {
          CollBank dto = collBankDAO.getCollBankByCollBankid(loanBankId
              .toString());
          bailClearInterestTbDTO.setLoanBankName(dto.getCollBankName());
          bailClearInterestTbAF.setLoanBankName(dto.getCollBankName());
        } catch (Exception e) {
          e.printStackTrace();
        }
        printList.add(bailClearInterestTbDTO);
      }
    }
    pagination.setNrOfElements(count);
    if (firstBalanceTotle != null && !firstBalanceTotle.equals("")) {
      bailClearInterestTbAF.setBailBalanceTotle(firstBalanceTotle);
    }
    if (occurMoneyTotle != null && !occurMoneyTotle.equals("")) {
      bailClearInterestTbAF.setOccurMoneyTotle(occurMoneyTotle);
    }
    if (lastBalanceTotle != null && !lastBalanceTotle.equals("")) {
      bailClearInterestTbAF.setLastBalanceTotle(lastBalanceTotle);
    }
    bailClearInterestTbAF.setList(templist);
    bailClearInterestTbAF.setPrintList(printList);
    return bailClearInterestTbAF;
  }
}
