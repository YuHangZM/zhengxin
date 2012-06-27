package org.xpup.hafmis.sysfinance.accmng.totleacc.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accmng.totleacc.bsinterface.ITotleaccBS;
import org.xpup.hafmis.sysfinance.accmng.totleacc.dto.TotleaccDTO;
import org.xpup.hafmis.sysfinance.common.dao.AccountantCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.SubjectDAO;

public class TotleaccBS implements ITotleaccBS {

  private SubjectDAO subjectDAO = null;

  private AccountantCredenceDAO accountantCredenceDAO = null;

  private BookParameterDAO bookParameterDAO = null;

  public void setSubjectDAO(SubjectDAO subjectDAO) {
    this.subjectDAO = subjectDAO;
  }

  public void setAccountantCredenceDAO(
      AccountantCredenceDAO accountantCredenceDAO) {
    this.accountantCredenceDAO = accountantCredenceDAO;
  }

  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }

  /**
   * ����������ѯ�����б�
   */
  public List querylistaccmngList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List returnlist = new ArrayList();
    try {
      String year = (String) pagination.getQueryCriterions().get("bizyear");
      String starperiod = (String) pagination.getQueryCriterions().get(
          "starperiod");
      String endperiod = (String) pagination.getQueryCriterions().get(
          "endperiod");
      String starsubject = (String) pagination.getQueryCriterions().get(
          "starsubject");
      String endsubject = starsubject;
      String level = "1";
      String office = (String) pagination.getQueryCriterions().get("office");
      if (office.equals("ȫ��")) {
        office = null;
      }
      // �жϻ���ڼ�
      String yearmonth = this.getMinYearmonth_WL(securityInfo);
      String yearBydate = yearmonth.substring(0, 4);
      String monthBydate = yearmonth.substring(4, 6);
      if (Integer.parseInt(year) < Integer.parseInt(yearBydate)) {
        return returnlist;
      } else if (Integer.parseInt(year) == Integer.parseInt(yearBydate)) {
        if ((Integer.parseInt(starperiod) <= Integer.parseInt(monthBydate))
            && (Integer.parseInt(endperiod) <= Integer.parseInt(monthBydate))) {
          return returnlist;
        } else if ((Integer.parseInt(starperiod) <= Integer
            .parseInt(monthBydate))
            && (Integer.parseInt(endperiod) > Integer.parseInt(monthBydate))) {
          starperiod = "" + (Integer.parseInt(monthBydate) + 1);
          if (starperiod.length() == 1) {
            starperiod = "0" + starperiod;
          }
        }
      }
      int number = 0;
      // �õ�����ѯ�Ŀ�Ŀ�ַ�������
      String subjectcode[] = subjectDAO.findSubjectCodesInfo(securityInfo
          .getBookId(), starsubject, endsubject, level);
      for (int i = 0; i < subjectcode.length; i++) {// ��ѯ��ϸ�б�
        BigDecimal value = new BigDecimal(0.00);
        BigDecimal x = new BigDecimal(0.00);
        TotleaccDTO listaccDTO = null;
        TotleaccDTO listaccDTO_bgn = null;
        TotleaccDTO listaccDTO_year = null;
        BigDecimal debit = new BigDecimal(0.00);// �跽���
        BigDecimal credit = new BigDecimal(0.00);// �������
        String termsum = "";// �����ںϼƵ���ʾ
        int size = returnlist.size();
        number = 0;
        if (true) {
          int count = 0;
          listaccDTO_bgn = new TotleaccDTO();
          // ��Ŀ����
          listaccDTO_bgn.setSubjectCode(subjectcode[i]);
          // ��Ŀ����
          listaccDTO_bgn.setSubjectname(subjectDAO
              .querySubjectNameBySubjectCode(subjectcode[i], securityInfo
                  .getBookId()));
          // ����
          listaccDTO_bgn.setCredenceDate(year.concat("-").concat(starperiod)
              .concat("-01"));
          // ժҪ
          if (starperiod.equals("01")) {
            listaccDTO_bgn.setSummay(BusiTools.getBusiValue_WL(
                BusiConst.FNSUMMARY_LASTYEARCLEAR, BusiConst.FNSUMMARY));
          } else {
            listaccDTO_bgn.setSummay(BusiTools.getBusiValue_WL(
                BusiConst.FNSUMMARY_BGNBLAN, BusiConst.FNSUMMARY));
          }
          // �ڳ�����ѯ����
          value = accountantCredenceDAO
              .queryBgnblanceBySubjectcode_Wsh(subjectcode[i], year,
                  starperiod, endperiod, office, securityInfo);
          x = value;
          // money = value;// ���
          // ��÷�������
          listaccDTO_bgn.setDirtection(this.getDirtection(value));
          // ���
          if (value.compareTo(new BigDecimal(0.00)) < 0) {
            listaccDTO_bgn.setMoney(value.abs());
          } else {
            listaccDTO_bgn.setMoney(value);
          }
          if (listaccDTO_bgn.getMoney().compareTo(new BigDecimal(0.00)) > 0) {
            number++;
          }
          returnlist.add(listaccDTO_bgn);
          count = Integer.parseInt(endperiod) - Integer.parseInt(starperiod);
          int months = count;
          int month = Integer.parseInt(starperiod) - 1;

          for (int j = 0; j < months + 1; j++) {
            listaccDTO = new TotleaccDTO();
            int number1 = 0;
            debit = new BigDecimal(0.00);// �跽���
            credit = new BigDecimal(0.00);// �������
            String date = "10";
            month = month + 1;
            if (month < 10) {
              date = String.valueOf(month);
              date = "0" + date;
            } else {
              date = String.valueOf(month);
            }
            if (true) {
              // ------�����ڳ����
              // ------����������Ϣ
              // ----------------------ת��--------------------------
              // ���ںϼ�
              // ��Ŀ����
              listaccDTO.setSubjectCode(subjectcode[i]);
              // ��Ŀ����
              listaccDTO.setSubjectname(subjectDAO
                  .querySubjectNameBySubjectCode(listaccDTO.getSubjectCode(),
                      securityInfo.getBookId()));
              // ����
              termsum = accountantCredenceDAO.findLastDay_WL(year.concat("-")
                  .concat(date).concat("-01"));
              listaccDTO.setCredenceDate(year.concat("-").concat(date).concat(
                  "-").concat(termsum));
              // ƾ֤�ֺ�,Ҫȡ�����ƾ֤�ź���Сƾ֤������ѯƾ֤����ϳ� "��1��20��ת3��40" ��ʽ
              // ȡ����fn201�е���Сƾ֤��
              String minNum = accountantCredenceDAO
                  .getDocumentNumberMinOrMax_wsh("min", office, date, date,
                      securityInfo.getBookId(), year, securityInfo,
                      subjectcode[i]);
              // ������Сƾ֤�ŵõ���Сƾ֤�ֵ�����

              if (minNum != null) {
                // String minCharacter = accountantCredenceDAO
                // .getDocumentCharacterrMinOrMax_wsh(minNum, office, date,
                // date, securityInfo.getBookId(), year, securityInfo,
                // subjectcode[i]);
                // if ((minCharacter != null) && (!minCharacter.equals(""))) {
                // listaccDTO
                // .setCredenceCharacter(bookParameterDAO
                // .queryParamExplainInfo_WL(minCharacter, securityInfo,
                // "2").concat(" - "));
                // listaccDTO.setCredcharnum(listaccDTO.getCredenceCharacter());
                // }
                listaccDTO.setCredcharnum(minNum);
              }

              // ȡ����fn201�е����ƾ֤��
              String maxNum = accountantCredenceDAO
                  .getDocumentNumberMinOrMax_wsh("max", office, date, date,
                      securityInfo.getBookId(), year, securityInfo,
                      subjectcode[i]);
              // ������Сƾ֤�ŵõ���Сƾ֤�ֵ�����

              if (maxNum != null) {
                // String maxCharacter = accountantCredenceDAO
                // .getDocumentCharacterrMinOrMax_wsh(maxNum, office, date,
                // date, securityInfo.getBookId(), year, securityInfo,
                // subjectcode[i]);
                // if ((maxCharacter != null) && (!maxCharacter.equals(""))) {
                // listaccDTO.setCredenceCharacter(","
                // .concat(bookParameterDAO.queryParamExplainInfo_WL(
                // maxCharacter, securityInfo, "2").concat(" - ")));
                // listaccDTO.setCredcharnum(listaccDTO.getCredcharnum().concat(
                // listaccDTO.getCredenceCharacter()));
                // }
                listaccDTO.setCredcharnum(listaccDTO.getCredcharnum()
                    .concat("").concat(maxNum));
              }

              // ժҪ
              listaccDTO.setSummay(BusiTools.getBusiValue_WL(
                  BusiConst.FNSUMMARY_TERMSUM, BusiConst.FNSUMMARY));
              // �跽
              debit = accountantCredenceDAO.queryTermlendmnt_WL(subjectcode[i],
                  year, date, date, office, securityInfo);

              if (debit == null) {
                debit = new BigDecimal(0.00);
              }
              if (debit.compareTo(new BigDecimal(0.00)) == 0) {
                number1++;
              }
              listaccDTO.setDebit(debit);
              // ����
              credit = accountantCredenceDAO.queryTermloanmnt_WL(
                  subjectcode[i], year, date, date, office, securityInfo);
              if (credit == null) {
                credit = new BigDecimal(0.00);
              }
              if (credit.compareTo(new BigDecimal(0.00)) == 0) {
                number1++;
              }
              // if (number1 == 2) {
              // continue;
              // }
              x = debit.subtract(credit).add(x);
              listaccDTO.setCredit(credit);
              // ����
              BigDecimal flag = new BigDecimal(0.00);
              if (listaccDTO_bgn.getDirtection() == "��") {
                flag = listaccDTO_bgn.getMoney().multiply(new BigDecimal(-1));
              } else {
                flag = listaccDTO_bgn.getMoney();
              }
              listaccDTO.setDirtection(this.getDirtection(debit
                  .subtract(credit).add(flag)));
              // ���
              listaccDTO.setMoney(debit.subtract(credit).add(flag));
              if (listaccDTO.getMoney().compareTo(new BigDecimal(0.00)) > 0) {
                number++;
              }
              returnlist.add(listaccDTO);
              // -------���汾���ۼ�
              listaccDTO_year = new TotleaccDTO();
              // ��Ŀ����
              listaccDTO_year.setSubjectCode(subjectcode[i]);
              // ��Ŀ����
              listaccDTO_year.setSubjectname(subjectDAO
                  .querySubjectNameBySubjectCode(subjectcode[i], securityInfo
                      .getBookId()));
              // ���� �������һ��
              termsum = accountantCredenceDAO.findLastDay_WL(year
                  + "-".concat(date).concat("-01"));
              listaccDTO_year.setCredenceDate(year + "-".concat(date)
                  + "-".concat(termsum));

              // ժҪ
              listaccDTO_year.setSummay(BusiTools.getBusiValue_WL(
                  BusiConst.FNSUMMARY_YEARSUM, BusiConst.FNSUMMARY));
              // �跽
              debit = accountantCredenceDAO.queryTermlendmnt_WL(subjectcode[i],
                  year, "01", date, office, securityInfo);
              if (debit == null) {
                debit = new BigDecimal(0.00);
              }
              listaccDTO_year.setDebit(debit);
              // ����
              credit = accountantCredenceDAO.queryTermloanmnt_WL(
                  subjectcode[i], year, "01", date, office, securityInfo);
              if (credit == null) {
                credit = new BigDecimal(0.00);
              }
              listaccDTO_year.setCredit(credit);
              // ����
              flag = new BigDecimal(0.00);
              if (listaccDTO_bgn.getDirtection() == "��") {

                flag = listaccDTO_bgn.getMoney().multiply(new BigDecimal(-1));
              } else {
                flag = listaccDTO_bgn.getMoney();
              }
              listaccDTO_year.setDirtection(this.getDirtection(x));
              // ����
              listaccDTO.setDirtection(this.getDirtection(x));

              // ���
              listaccDTO.setMoney(x.abs());
              listaccDTO_year.setMoney(x.abs());
              if (listaccDTO_year.getMoney().compareTo(new BigDecimal(0.00)) > 0) {
                number++;
              }
              returnlist.add(listaccDTO_year);
            }
          }

        }
        pagination.setNrOfElements(returnlist.size());
        // if (number == 0) {
        // int listCount = 0;
        // listCount = returnlist.size();
        // for (int m = listCount - 1; m >= size; m--) {
        // returnlist.remove(m);
        // }
        // }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return returnlist;
  }

  /**
   * ��÷�������
   * 
   * @param value
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public String getDirtection(BigDecimal value) throws Exception,
      BusinessException {
    String dirtection = "";
    try {
      if (value.compareTo(new BigDecimal(0.00)) == 0) {
        dirtection = BusiTools.getBusiValue_WL(BusiConst.BALANCEDIRECTION_AVE,
            BusiConst.BALANCEDIRECTION);
      } else if (value.compareTo(new BigDecimal(0.00)) > 0) {
        dirtection = BusiTools.getBusiValue_WL(
            BusiConst.BALANCEDIRECTION_DEBIT, BusiConst.BALANCEDIRECTION);
      } else {
        dirtection = BusiTools.getBusiValue_WL(
            BusiConst.BALANCEDIRECTION_CREDIT, BusiConst.BALANCEDIRECTION);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dirtection;
  }

  /**
   * ���ҳ��¼��Ŀ�Ŀ�Ƿ���ڲ���Ϊһ����Ŀ
   */
  public String checkSubjectcode(String subjectcode, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    String message = "";
    try {
      Integer flag = subjectDAO.findSubjectrelationFirstCode(subjectcode,
          securityInfo);
      if (flag.intValue() == 0) {
        message = "¼��Ŀ�Ŀ����Ҫ�����Ѿ����ڲ���Ϊһ���Ŀ�Ŀ����";
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return message;
  }

  /**
   * ������׿�Ŀ����
   */
  public String querySubjectdayreportParamValue(String bookId) throws Exception {
    try {
      return this.bookParameterDAO.getParamValue(bookId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "0";
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
}
