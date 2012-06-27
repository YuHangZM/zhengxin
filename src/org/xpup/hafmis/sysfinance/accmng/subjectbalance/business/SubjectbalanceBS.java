package org.xpup.hafmis.sysfinance.accmng.subjectbalance.business;

import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accmng.subjectbalance.bsinterface.ISubjectbalanceBS;
import org.xpup.hafmis.sysfinance.common.dao.AccountantCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.SubjectDAO;

/**
 * Copy Right Information : ��Ŀ���� Project : �ļ���
 * 
 * @Version : 1.0
 * @author : ���� �������� : 11-02-2007
 */
public class SubjectbalanceBS implements ISubjectbalanceBS {

  private BookParameterDAO bookParameterDAO = null;

  private SubjectDAO subjectDAO = null;

  private AccountantCredenceDAO accountantCredenceDAO = null;

  public BookParameterDAO getBookParameterDAO() {
    return bookParameterDAO;
  }

  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }

  public SubjectDAO getSubjectDAO() {
    return subjectDAO;
  }

  public void setSubjectDAO(SubjectDAO subjectDAO) {
    this.subjectDAO = subjectDAO;
  }

  /**
   * �õ����ݿ�������ʼ������
   */
  public String getMinYearmonth_WL(SecurityInfo securityInfo) throws Exception,
      BusinessException {
    String yearmonth = "";
    try {
      yearmonth = accountantCredenceDAO.getMinYearmonth_WL(securityInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return yearmonth;
  }

  /**
   * ������׿�Ŀ����
   */
  public String querySubjectbalanceParamValue(String bookId) throws Exception {
    try {
      return this.bookParameterDAO.getParamValue(bookId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "0";
  }

  /**
   * �жϿ�Ŀ�����Ƿ���һ����Ŀ
   * 
   * @param bookId
   * @param subjectCode
   * @return
   */
  public boolean isSubjectCodeOneLevel(String bookId, String subjectCode)
      throws Exception {
    try {
      String temp_code = this.subjectDAO.getSubjectCodeOneLevle(bookId,
          subjectCode);
      int temp_num = Integer.parseInt(temp_code);
      if (temp_num > 0) {
        return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public String queryMakerPara() throws Exception {
    String name = "";
    name = subjectDAO.getNamePara();
    return name;

  }

  public String queryCountCredenceNum(String bookId, String yearMonth) {
    return subjectDAO.getCountCredenceNum(bookId, yearMonth);
  }

  /**
   * ���ݿ�Ŀ����ѯ ��Ŀ����
   * 
   * @param bookId
   * @param subjectCodeStart
   * @param subjectCodeEnd
   * @param subjectLevel
   * @param subjectSortCode
   * @return
   * @throws Exception
   */
  public String[] findSubjectCodesInfoBySubjectSortCode(String bookId,
      String subjectCodeStart, String subjectCodeEnd, String subjectLevel)
      throws Exception {
    try {
      return subjectDAO.findSubjectCodesInfoBySubjectSortCode(bookId,
          subjectCodeStart, subjectCodeEnd, subjectLevel);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * ���ݿ�Ŀ�����bookid ��ÿ�Ŀ����
   * 
   * @param bookId
   * @param subjectCode
   * @return
   * @throws Exception
   */
  public String querySubjectNameBySubjectCode(String bookId, String subjectCode)
      throws Exception {
    try {
      return subjectDAO.querySubjectNameBySubjectCode(subjectCode, bookId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * �������� ���ؿ�Ŀ������Ϣ
   * @param bookId
   * @param subjectCodeStart
   * @param subjectCodeEnd
   * @param subjectLevel
   * @param credenceDateStart
   * @param credenceDateEnd
   * @param officeName
   * @return
   * @throws Exception
   */
  public List findSubjectbalanceInfo(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    try {
      String year = (String) pagination.getQueryCriterions().get("year");
      String startMonth = (String) pagination.getQueryCriterions().get(
          "startMonth");
      String endMonth = startMonth;
      String startSubjectCode = (String) pagination.getQueryCriterions().get(
          "startSubjectCode");
      String endSubjectCode = (String) pagination.getQueryCriterions().get(
          "endSubjectCode");
      String subjectLevel = (String) pagination.getQueryCriterions().get(
          "subjectLevel");
      String office = (String) pagination.getQueryCriterions().get("office");
      if (office.equals("ȫ��")) {
        office = "";
      }
      List list = new ArrayList();
      // �ϼ�
      /*
       * BigDecimal temp_sumFirstDebit = new BigDecimal("0.00"); BigDecimal
       * temp_sumFirstCredit = new BigDecimal("0.00"); BigDecimal
       * temp_sumThisIssueDebit = new BigDecimal("0.00"); BigDecimal
       * temp_sumThisIssueCredit = new BigDecimal("0.00"); BigDecimal
       * temp_sumThisYearDebit = new BigDecimal("0.00"); BigDecimal
       * temp_sumThisYearCredit = new BigDecimal("0.00"); BigDecimal
       * temp_sumLastDebit = new BigDecimal("0.00"); BigDecimal
       * temp_sumLastCredit = new BigDecimal("0.00");
       */
      // int oneSubjectCodeLenght = Integer.parseInt(bookParameterDAO
      // .getParamExplain_WL("1", securityInfo).get(0).toString());
      // �жϻ���ڼ�
      String minCredenceDate = this.getMinYearmonth_WL(securityInfo).substring(
          0, 6);
      // ��ѯ�����е�
      String startDate = year
          + (startMonth.length() == 1 ? "0" + startMonth : startMonth);
      String endDate = year
          + (endMonth.length() == 1 ? "0" + endMonth : endMonth);
      if (Integer.parseInt(startDate) <= Integer.parseInt(minCredenceDate)) {
        if (Integer.parseInt(endDate) <= Integer.parseInt(minCredenceDate))
          return list;
        else
          startDate = minCredenceDate;
      }

      // BigDecimal temp_firstDebit = new BigDecimal("0.00");
      // BigDecimal temp_firstCredit = new BigDecimal("0.00");
      // BigDecimal temp_thisIssueDebit = new BigDecimal("0.00");
      // BigDecimal temp_thisIssueCredit = new BigDecimal("0.00");
      // BigDecimal temp_thisYearDebit = new BigDecimal("0.00");
      // BigDecimal temp_thisYearCredit = new BigDecimal("0.00");
      // BigDecimal temp_lastDebit = new BigDecimal("0.00");
      // BigDecimal temp_lastCredit = new BigDecimal("0.00");
      // �����ֿ���Ŀ����
      list = subjectDAO.findSubjectbalanceInfo(securityInfo.getBookId(),
          startSubjectCode, endSubjectCode, subjectLevel, startDate, endDate,
          office);
      // һ�����Ŀ�Ŀ����
      // for (int j = 0; j < temp_subjectCode.length; j++) {
      // SubjectbalanceDTO temp_subjectbalanceDTO = subjectDAO
      // .findSubjectbalanceInfo(temp_subjectCode[j], securityInfo
      // .getBookId(), startDate, endDate, office);
      //
      // temp_subjectbalanceDTO.setSubjectCode(temp_subjectCode[j]);
      // // ����������ÿ�Ŀ����
      // String subjectName = this.querySubjectNameBySubjectCode(securityInfo
      // .getBookId(), temp_subjectCode[j]);
      // temp_subjectbalanceDTO.setSubjectName(subjectName);
      // if (temp_subjectbalanceDTO.getFirstRemainingDebit().compareTo(
      // new BigDecimal(0.00)) == 0
      // && temp_subjectbalanceDTO.getFirstRemainingCredit().compareTo(
      // new BigDecimal(0.00)) == 0
      // && temp_subjectbalanceDTO.getThisIssueDebit().compareTo(
      // new BigDecimal(0.00)) == 0
      // && temp_subjectbalanceDTO.getThisIssueCredit().compareTo(
      // new BigDecimal(0.00)) == 0
      // && temp_subjectbalanceDTO.getThisYearDebit().compareTo(
      // new BigDecimal(0.00)) == 0
      // && temp_subjectbalanceDTO.getThisYearCredit().compareTo(
      // new BigDecimal(0.00)) == 0
      // && temp_subjectbalanceDTO.getLastRemainingDebit().compareTo(
      // new BigDecimal(0.00)) == 0
      // && temp_subjectbalanceDTO.getLastRemainingCredit().compareTo(
      // new BigDecimal(0.00)) == 0) {
      // if (j != temp_subjectCode.length - 1) {
      // continue;
      // }
      // } else {
      // list.add(temp_subjectbalanceDTO);
      // }
      // if (temp_subjectbalanceDTO.getSubjectCode().length() ==
      // oneSubjectCodeLenght) {
      // temp_firstDebit = temp_firstDebit.add(temp_subjectbalanceDTO
      // .getFirstRemainingDebit());
      // temp_firstCredit = temp_firstCredit.add(temp_subjectbalanceDTO
      // .getFirstRemainingCredit());
      // temp_thisIssueDebit = temp_thisIssueDebit.add(temp_subjectbalanceDTO
      // .getThisIssueDebit());
      // temp_thisIssueCredit = temp_thisIssueCredit
      // .add(temp_subjectbalanceDTO.getThisIssueCredit());
      // temp_thisYearDebit = temp_thisYearDebit.add(temp_subjectbalanceDTO
      // .getThisYearDebit());
      // temp_thisYearCredit = temp_thisYearCredit.add(temp_subjectbalanceDTO
      // .getThisYearCredit());
      // temp_lastDebit = temp_lastDebit.add(temp_subjectbalanceDTO
      // .getLastRemainingDebit());
      // temp_lastCredit = temp_lastCredit.add(temp_subjectbalanceDTO
      // .getLastRemainingCredit());
      // }
      /*
       * if (j == temp_subjectCode.length - 1 && state.equals("1")) {
       * SubjectbalanceDTO temp_subjectbalanceDTO2 = new SubjectbalanceDTO();
       * temp_subjectbalanceDTO2.setSubjectCode("С��");
       * temp_subjectbalanceDTO2.setSubjectName("");
       * temp_subjectbalanceDTO2.setFirstRemainingDebit(temp_firstDebit);
       * temp_subjectbalanceDTO2.setFirstRemainingCredit(temp_firstCredit);
       * temp_subjectbalanceDTO2.setThisIssueDebit(temp_thisIssueDebit);
       * temp_subjectbalanceDTO2.setThisIssueCredit(temp_thisIssueCredit);
       * temp_subjectbalanceDTO2.setThisYearDebit(temp_thisYearDebit);
       * temp_subjectbalanceDTO2.setThisYearCredit(temp_thisYearCredit);
       * temp_subjectbalanceDTO2.setLastRemainingDebit(temp_lastDebit);
       * temp_subjectbalanceDTO2.setLastRemainingCredit(temp_lastCredit);
       * list.add(temp_subjectbalanceDTO2); temp_sumFirstDebit =
       * temp_sumFirstDebit.add(temp_subjectbalanceDTO2
       * .getFirstRemainingDebit()); temp_sumFirstCredit =
       * temp_sumFirstCredit.add(temp_subjectbalanceDTO2
       * .getFirstRemainingCredit()); temp_sumThisIssueDebit =
       * temp_sumThisIssueDebit
       * .add(temp_subjectbalanceDTO2.getThisIssueDebit());
       * temp_sumThisIssueCredit = temp_sumThisIssueCredit
       * .add(temp_subjectbalanceDTO2.getThisIssueCredit());
       * temp_sumThisYearDebit = temp_sumThisYearDebit
       * .add(temp_subjectbalanceDTO2.getThisYearDebit());
       * temp_sumThisYearCredit = temp_sumThisYearCredit
       * .add(temp_subjectbalanceDTO2.getThisYearCredit()); temp_sumLastDebit =
       * temp_sumLastDebit.add(temp_subjectbalanceDTO2
       * .getLastRemainingDebit()); temp_sumLastCredit =
       * temp_sumLastCredit.add(temp_subjectbalanceDTO2
       * .getLastRemainingCredit()); }
       */
      // }
      /*
       * SubjectbalanceDTO subjectbalanceDTO = new SubjectbalanceDTO();
       * subjectbalanceDTO.setSubjectName("");
       * subjectbalanceDTO.setSubjectCode("�ϼ�");
       * subjectbalanceDTO.setFirstRemainingDebit(temp_sumFirstDebit);
       * subjectbalanceDTO.setFirstRemainingCredit(temp_sumFirstCredit);
       * subjectbalanceDTO.setThisIssueDebit(temp_sumThisIssueDebit);
       * subjectbalanceDTO.setThisIssueCredit(temp_sumThisIssueCredit);
       * subjectbalanceDTO.setThisYearDebit(temp_sumThisYearDebit);
       * subjectbalanceDTO.setThisYearCredit(temp_sumThisYearCredit);
       * subjectbalanceDTO.setLastRemainingDebit(temp_sumLastDebit);
       * subjectbalanceDTO.setLastRemainingCredit(temp_sumLastCredit);
       * list.add(subjectbalanceDTO);
       */
      pagination.setNrOfElements(list.size());
      return list;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return null;
    }
  }

  public AccountantCredenceDAO getAccountantCredenceDAO() {
    return accountantCredenceDAO;
  }

  public void setAccountantCredenceDAO(
      AccountantCredenceDAO accountantCredenceDAO) {
    this.accountantCredenceDAO = accountantCredenceDAO;
  }
}
