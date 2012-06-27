package org.xpup.hafmis.sysfinance.accmng.subjectmuster.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.CollParaDAO;
import org.xpup.hafmis.sysfinance.accmng.subjectmuster.bsinterface.ISubjectmusterBS;
import org.xpup.hafmis.sysfinance.accmng.subjectmuster.dto.SubjectmusterDTO;
import org.xpup.hafmis.sysfinance.common.dao.AccountantCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.SubjectDAO;

/**
 * Copy Right Information : ƾ֤���� Project : �ļ���
 * 
 * @Version : 1.0
 * @author : ���� �������� : 11-06-2007
 */
public class SubjectmusterBS implements ISubjectmusterBS {

  private BookParameterDAO bookParameterDAO = null;

  private SubjectDAO subjectDAO = null;

  private CollParaDAO collParaDAO = null;

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
   * �������1����Ŀ����
   * 
   * @param bookId
   * @return
   * @throws Exception
   */
  public String[] findSubjectCodesInfo(String bookId) throws Exception {
    try {
      return subjectDAO.findSubjectCodesInfo(bookId, "", "", "1");
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

  public String queryMaxCredenceNum(String office, String yearmonth,
      String bookid) throws Exception {
    try {
      return subjectDAO.queryMaxCredenceNum(yearmonth, bookid);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * ���ƾ֤����������Ϣ
   */
  public List findSubjectmusterInfo(String bookId, String credenceDateStart,
      String credenceDateEnd, String officeName, String credenceNumStart,
      String credenceNumEnd, String subjectLevel, SecurityInfo securityInfo)
      throws Exception {
    try {
      List list = new ArrayList();
      BigDecimal temp_debitSum = new BigDecimal("0.00");
      BigDecimal temp_creditSum = new BigDecimal("0.00");
      // ����������ѯ��Ŀ�������
      // int oneSubjectCodeLenght = Integer.parseInt(bookParameterDAO
      // .getParamExplain_WL("1", securityInfo).get(0).toString());
      // �жϻ���ڼ�
      String yearmonth = this.getMinYearmonth_WL(securityInfo);
      String yearBydate = yearmonth.substring(0, 4);
      String monthBydate = yearmonth.substring(4, 6);
      String years = credenceDateStart.substring(0, 4);
      String monthStart = credenceDateStart.substring(4, 6);
      String monthEnd = credenceDateEnd.substring(4, 6);

      if (Integer.parseInt(years) < Integer.parseInt(yearBydate)) {
        return list;
      } else if (Integer.parseInt(years) == Integer.parseInt(yearBydate)) {
        if ((Integer.parseInt(monthStart) <= Integer.parseInt(monthBydate))
            && (Integer.parseInt(monthEnd) <= Integer.parseInt(monthBydate))) {
          return list;
        } else if ((Integer.parseInt(monthStart) <= Integer
            .parseInt(monthBydate))
            && (Integer.parseInt(monthEnd) > Integer.parseInt(monthBydate))) {
          monthStart = "" + (Integer.parseInt(monthBydate) + 1);
          if (monthStart.length() == 1) {
            monthStart = "0" + monthStart;
          }
        }
      }
      credenceDateStart = years.concat(monthStart);
      // С��
      BigDecimal temp_debit = new BigDecimal("0.00");
      BigDecimal temp_credit = new BigDecimal("0.00");
      String state = "0";
      // �����ֿ���Ŀ����
      String[] temp_subjectCode = subjectDAO
          .findSubjectCodesInfoBySubjectSortCode(bookId, "", "", subjectLevel);
      // һ�����Ŀ�Ŀ����
      for (int j = 0; j < temp_subjectCode.length; j++) {
        SubjectmusterDTO subjcetmusterDTO = subjectDAO.findSubjectmusterInfo(
            temp_subjectCode[j], bookId, credenceDateStart, credenceDateEnd,
            officeName, credenceNumStart, credenceNumEnd);
        subjcetmusterDTO.setSubjectCode(temp_subjectCode[j]);
        // ����������ÿ�Ŀ����
        String subjectName = this.querySubjectNameBySubjectCode(bookId,
            temp_subjectCode[j]);
        subjcetmusterDTO.setSubjectName(subjectName);
        if (subjcetmusterDTO.getDebitSum().compareTo(new BigDecimal(0.00)) == 0
            && subjcetmusterDTO.getCreditSum().compareTo(new BigDecimal(0.00)) == 0) {
          if (j != temp_subjectCode.length - 1) {
            continue;
          }
        } else {
          list.add(subjcetmusterDTO);
          state = "1";
        }
        temp_debitSum = temp_debitSum.add(subjcetmusterDTO.getDebitSum());
        temp_creditSum = temp_creditSum.add(subjcetmusterDTO.getCreditSum());
        // temp_debit = temp_debit.add(subjcetmusterDTO.getDebitSum());
        // temp_credit = temp_credit.add(subjcetmusterDTO.getCreditSum());
        // SubjectmusterDTO subjcetmusterDTO2 = new SubjectmusterDTO();
        // subjcetmusterDTO2.setSubjectCode("�ϼ�");
        // subjcetmusterDTO2.setSubjectName("");
        // subjcetmusterDTO2.setDebitSum(temp_debit);
        // subjcetmusterDTO2.setCreditSum(temp_credit);
        // list.add(subjcetmusterDTO2);
      }
      if (!state.equals("0")) {
        SubjectmusterDTO subjcetmusterDTO1 = new SubjectmusterDTO();
        subjcetmusterDTO1.setSubjectCode("�ϼ�");
        subjcetmusterDTO1.setSubjectName("");
        subjcetmusterDTO1.setDebitSum(temp_debitSum);
        subjcetmusterDTO1.setCreditSum(temp_creditSum);
        list.add(subjcetmusterDTO1);
      }
      System.out.println(list.size()+"=====>");
      return list;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
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

  public String getNamePara() throws Exception {
    String name = "";
    name = collParaDAO.getNamePara();
    return name;
  }

  public AccountantCredenceDAO getAccountantCredenceDAO() {
    return accountantCredenceDAO;
  }

  public void setAccountantCredenceDAO(
      AccountantCredenceDAO accountantCredenceDAO) {
    this.accountantCredenceDAO = accountantCredenceDAO;
  }

  public void setCollParaDAO(CollParaDAO collParaDAO) {
    this.collParaDAO = collParaDAO;
  }
}
