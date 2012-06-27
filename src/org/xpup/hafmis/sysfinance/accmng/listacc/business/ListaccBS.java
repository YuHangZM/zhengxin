package org.xpup.hafmis.sysfinance.accmng.listacc.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accmng.listacc.bsinterface.IListacctBS;
import org.xpup.hafmis.sysfinance.accmng.listacc.dto.ListaccDTO;
import org.xpup.hafmis.sysfinance.accmng.listacc.form.ListaccAF;
import org.xpup.hafmis.sysfinance.common.dao.AccountantCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.SubjectDAO;

public class ListaccBS implements IListacctBS {

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
   * ����������ѯ��ϸ���б�
   */
  public List querylistaccmngList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    return null;
  }

  /**
   * ��ѯ��ϸ���б�
   */
  public List queryDetailAccList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List resultList = new ArrayList();
    try {
      String year = (String) pagination.getQueryCriterions().get("bizyear");
      String starperiod = (String) pagination.getQueryCriterions().get(
          "starperiod");
      String endperiod = (String) pagination.getQueryCriterions().get(
          "endperiod");
      String subject = (String) pagination.getQueryCriterions().get(
          "starsubject");
      String office = (String) pagination.getQueryCriterions().get("office");
      if (office.equals("ȫ��")) {
        office = null;
      }

      String temp_day = "";
      String temp_month = "";
      String temp_dirc = "";
      String temp_credenceDate = "";
      // ��ѯƾ֤����С��ƾ֤����
      String minCredenceDate = this.getMinYearmonth_WL(securityInfo).substring(
          0, 6);
      // ��ѯ�����е�
      String startDate = year
          + (starperiod.length() == 1 ? "0" + starperiod : starperiod);
      String endDate = year
          + (endperiod.length() == 1 ? "0" + endperiod : endperiod);
      System.out.println("ƾ֤��С����====>" + minCredenceDate);
      System.out.println("startDate====>" + startDate);
      System.out.println("endDate====>" + endDate);
      if (Integer.parseInt(startDate) <= Integer.parseInt(minCredenceDate)) {
        if (Integer.parseInt(endDate) <= Integer.parseInt(minCredenceDate))
          return resultList;
        else
          startDate = minCredenceDate;
      }
      // �õ�����ѯ�Ŀ�Ŀ�ַ�������
      String subject_array[] = subjectDAO.getSubjectCodesInfo_WL(subject,
          subject, null, securityInfo);
      List list = null;
      String subjectCode = "";
      ListaccDTO dto = null;
      // ֮�����ı���,����,����ļ�¼
      ListaccDTO dto_begin = null;
      ListaccDTO dto_day = null;
      ListaccDTO dto_month = null;
      ListaccDTO dto_year = null;
      int pageCount = 0;
      int sum = 0;
      for (int i = 0; i < subject_array.length; i++) {
        // ���պϼ�
        sum = 0;
        BigDecimal daySum_debit = new BigDecimal(0.00);
        BigDecimal daySum_credit = new BigDecimal(0.00);
        // ���ºϼ�
        BigDecimal monthSum_debit = new BigDecimal(0.00);
        BigDecimal monthSum_credit = new BigDecimal(0.00);
        // �����ۼ�
        // ���ºϼ�
        BigDecimal yearSum_debit = new BigDecimal(0.00);
        BigDecimal yearSum_credit = new BigDecimal(0.00);

        BigDecimal balance = new BigDecimal(0.00);// ���

        subjectCode = subject_array[i];
        // ------�����ڳ����
        dto_begin = new ListaccDTO();
        // ��Ŀ����
        dto_begin.setSubjectCode(subjectCode);
        String subjectName = subjectDAO.querySubjectNameBySubjectCode(dto_begin
            .getSubjectCode(), securityInfo.getBookId());
        dto_begin.setSubjectname(subjectName);
        // ����
        dto_begin.setCredenceDate(startDate + "01");
        // ժҪ
        if (dto_begin.getCredenceDate().substring(4, 6).equals("01")) {
          dto_begin.setSummay(BusiTools.getBusiValue_WL(
              BusiConst.FNSUMMARY_LASTYEARCLEAR, BusiConst.FNSUMMARY));
        } else {
          dto_begin.setSummay(BusiTools.getBusiValue_WL(
              BusiConst.FNSUMMARY_BGNBLAN, BusiConst.FNSUMMARY));
        }

        int k = subjectDAO.checkSubjectCodeIsValid(securityInfo.getBookId(),
            subjectCode, startDate);
        if (k == 0)
          continue;
        // �ڳ�����ѯ����
        balance = accountantCredenceDAO.queryBgnblanceBySubjectcode_WS(
            subjectCode, startDate, office, securityInfo);
        // ��÷�������
        dto_begin.setDirtection(this.getDirtection(balance));
        temp_dirc = dto_begin.getDirtection();
        // ���
        dto_begin.setMoney(balance.abs());
        resultList.add(dto_begin);
        pageCount++;
        sum++;
        // ------------------�����ڳ�������-----------------------

        // -------------------��ѯ��ϸ�б�ʼ-----------------------
        list = accountantCredenceDAO.queryDetailAccList(securityInfo
            .getBookId(), subjectCode, year + "01", endDate, office);
        for (int j = 0; j < list.size(); j++) {
          dto = (ListaccDTO) list.get(j);
          dto.setSubjectCode(subjectCode);
          dto.setSubjectname(subjectName);
          String credenceDay = "";
          String credenceMonth = "";
          int month = Integer.parseInt(dto.getCredenceDate().substring(4, 6));
          if (j == 0) {
            temp_day = dto.getCredenceDate().substring(6, 8);
            temp_month = dto.getCredenceDate().substring(4, 6);
            temp_credenceDate = dto.getCredenceDate();
          } else {
            credenceDay = dto.getCredenceDate().substring(6, 8);
            credenceMonth = dto.getCredenceDate().substring(4, 6);
            if (!credenceDay.equals(temp_day)) {
              temp_day = credenceDay;
              // �������һ�������ӱ��պϼ�
              dto_day = new ListaccDTO();
              dto_day.setSubjectCode(subjectCode);
              dto_day.setSubjectname(subjectName);
              dto_day.setCredenceDate(temp_credenceDate);
              dto_day.setSummay(BusiTools.getBusiValue_WL(
                  BusiConst.FNSUMMARY_DAYSUM, BusiConst.FNSUMMARY));
              dto_day.setDebit(daySum_debit);
              dto_day.setCredit(daySum_credit);
              // ��÷�������
              dto_day.setDirtection(temp_dirc);
              // ���
              dto_day.setMoney(balance.abs());
              if (Integer.parseInt(temp_credenceDate.substring(4, 6)) >= Integer
                  .parseInt(startDate.substring(4, 6))
                  && month >= Integer.parseInt(startDate.substring(4, 6))) {
                resultList.add(dto_day);
                sum++;
              }
              daySum_debit = new BigDecimal(0.00);
              daySum_credit = new BigDecimal(0.00);

            }
            if (!credenceMonth.equals(temp_month)) {
              temp_month = credenceMonth;
              // �������һ���·������ӱ��ºϼ�
              dto_month = new ListaccDTO();
              dto_month.setSubjectCode(subjectCode);
              dto_month.setSubjectname(subjectName);
              dto_month.setCredenceDate(temp_credenceDate);
              dto_month.setSummay(BusiTools.getBusiValue_WL(
                  BusiConst.FNSUMMARY_TERMSUM, BusiConst.FNSUMMARY));
              dto_month.setDebit(monthSum_debit);
              dto_month.setCredit(monthSum_credit);
              // ��÷�������
              dto_month.setDirtection(this.getDirtection(balance));
              // ���
              dto_month.setMoney(balance.abs());
              monthSum_debit = new BigDecimal(0.00);
              monthSum_credit = new BigDecimal(0.00);
              if (Integer.parseInt(temp_credenceDate.substring(4, 6)) >= Integer
                  .parseInt(startDate.substring(4, 6))
                  && month >= Integer.parseInt(startDate.substring(4, 6))) {
                resultList.add(dto_month);
                sum++;
              }

              // ���ӱ����ۼ�
              dto_year = new ListaccDTO();
              dto_year.setSubjectCode(subjectCode);
              dto_year.setSubjectname(subjectName);
              dto_year.setCredenceDate(temp_credenceDate);
              dto_year.setSummay(BusiTools.getBusiValue_WL(
                  BusiConst.FNSUMMARY_YEARSUM, BusiConst.FNSUMMARY));
              dto_year.setDebit(yearSum_debit);
              dto_year.setCredit(yearSum_credit);
              // ��÷�������
              dto_year.setDirtection(this.getDirtection(balance));
              // ���
              dto_year.setMoney(balance.abs());
              if (Integer.parseInt(temp_credenceDate.substring(4, 6)) >= Integer
                  .parseInt(startDate.substring(4, 6))
                  && month >= Integer.parseInt(startDate.substring(4, 6))) {
                resultList.add(dto_year);
                sum++;
              }
            }
          }
          // ���ͷ���
          if (month >= Integer.parseInt(startDate.substring(4, 6))) {
            if (dto.getCredit().compareTo(new BigDecimal(0.00)) != 0) {// ����
              if (temp_dirc.equals("��")) {
                if (dto.getCredit().compareTo(new BigDecimal(0.00)) < 0
                    && dto.getCredit().abs().compareTo(balance.abs()) > 0) {
                  dto.setMoney(dto.getCredit().abs().add(balance.abs()));
                  balance = balance.subtract(dto.getCredit()).add(
                      dto.getDebit());
                } else if (dto.getCredit().compareTo(new BigDecimal(0.00)) > 0
                    && dto.getCredit().compareTo(balance.abs()) > 0
                    && balance.compareTo(new BigDecimal(0.00)) > 0) {
                  dto.setMoney(dto.getCredit().subtract(balance));
                  balance = balance.subtract(dto.getCredit()).add(
                      dto.getDebit());
                } else {
                  dto.setMoney(balance.subtract(dto.getCredit()).abs());
                  balance = balance.subtract(dto.getCredit()).add(
                      dto.getDebit());
                }
                dto.setDirtection(this.getDirtection(balance));
              } else {
                dto.setMoney(balance.abs().add(dto.getCredit()));
                balance = balance.subtract(dto.getCredit()).add(dto.getDebit());
                dto.setDirtection(this.getDirtection(balance));
              }
            } else {// �跽

              if (temp_dirc.equals("��")) {
                if (dto.getDebit().compareTo(new BigDecimal(0.00)) < 0
                    && dto.getDebit().abs().compareTo(balance.abs()) > 0) {
                  dto.setMoney(dto.getDebit().abs().add(balance.abs()));
                  balance = balance.subtract(dto.getDebit().abs());
                } else if (dto.getDebit().compareTo(new BigDecimal(0.00)) > 0
                    && dto.getDebit().compareTo(balance.abs()) > 0
                    && balance.compareTo(new BigDecimal(0.00)) < 0) {
                  dto.setMoney(dto.getDebit().subtract(balance.abs()));
                  // balance = balance.subtract(dto.getDebit());
                  balance = dto.getDebit().subtract(balance.abs());
                } else {
                  dto.setMoney(balance.abs().subtract(dto.getDebit()));
                  balance = balance.add(dto.getDebit());
                }
                dto.setDirtection(this.getDirtection(balance));
              } else {
                dto.setMoney(balance.add(dto.getDebit()));
                balance = balance.add(dto.getDebit());
                dto.setDirtection(this.getDirtection(balance));
              }
            }
            if (dto.getSummay() != null && !"".equals(dto.getSummay())) {
              dto.setSummay(bookParameterDAO.queryParamExplainInfo_WL(dto
                  .getSummay(), securityInfo, "4"));
            }

            resultList.add(dto);
            sum++;
          }
          temp_dirc = dto.getDirtection();
          // ����ǰһ�������
          temp_credenceDate = dto.getCredenceDate();
          // �ۼ�
          if (month >= Integer.parseInt(startDate.substring(4, 6))) {
            daySum_debit = daySum_debit.add(dto.getDebit());
            daySum_credit = daySum_credit.add(dto.getCredit());
            monthSum_debit = monthSum_debit.add(dto.getDebit());
            monthSum_credit = monthSum_credit.add(dto.getCredit());
          }
          yearSum_debit = yearSum_debit.add(dto.getDebit());
          yearSum_credit = yearSum_credit.add(dto.getCredit());
        }
        if (list != null && list.size() != 0) {
          // �����ѯ�����������������ӱ���,���ºͱ���ϼ�
          // ���ӱ��պϼ�
          dto_day = new ListaccDTO();
          dto_day.setSubjectCode(subjectCode);
          dto_day.setSubjectname(subjectName);
          dto_day.setCredenceDate(temp_credenceDate);
          dto_day.setSummay(BusiTools.getBusiValue_WL(
              BusiConst.FNSUMMARY_DAYSUM, BusiConst.FNSUMMARY));
          dto_day.setDebit(daySum_debit);
          dto_day.setCredit(daySum_credit);
          // ��÷�������
          dto_day.setDirtection(this.getDirtection(balance));
          // ���
          if (balance.compareTo(new BigDecimal(0.00)) < 0) {
            dto_day.setMoney(balance.abs());
          } else {
            dto_day.setMoney(balance);
          }
          resultList.add(dto_day);
          sum++;
          // ���ӱ��ºϼ�
          dto_month = new ListaccDTO();
          dto_month.setSubjectCode(subjectCode);
          dto_month.setSubjectname(subjectName);
          dto_month.setCredenceDate(temp_credenceDate);
          dto_month.setSummay(BusiTools.getBusiValue_WL(
              BusiConst.FNSUMMARY_TERMSUM, BusiConst.FNSUMMARY));
          dto_month.setDebit(monthSum_debit);
          dto_month.setCredit(monthSum_credit);
          // ��÷�������
          dto_month.setDirtection(this.getDirtection(balance));
          // ���
          if (balance.compareTo(new BigDecimal(0.00)) < 0) {
            dto_month.setMoney(balance.abs());
          } else {
            dto_month.setMoney(balance);
          }
          resultList.add(dto_month);
          sum++;
          // ���ӱ����ۼ�
          dto_year = new ListaccDTO();
          dto_year.setSubjectCode(subjectCode);
          dto_year.setSubjectname(subjectName);
          dto_year.setCredenceDate(temp_credenceDate);
          dto_year.setSummay(BusiTools.getBusiValue_WL(
              BusiConst.FNSUMMARY_YEARSUM, BusiConst.FNSUMMARY));
          dto_year.setDebit(yearSum_debit);
          dto_year.setCredit(yearSum_credit);
          // ��÷�������
          dto_year.setDirtection(this.getDirtection(balance));
          // ���
          if (balance.compareTo(new BigDecimal(0.00)) < 0) {
            dto_year.setMoney(balance.abs());
          } else {
            dto_year.setMoney(balance);
          }
          resultList.add(dto_year);
          sum++;
        }
        System.out.println(sum + "=================>");
        if (sum / 10 >= 1) {
          pageCount += sum % 10 == 0 ? sum / 10 - 1 : sum / 10;
        }

      }
      pagination.setPageCount(pageCount);
      pagination.setNrOfElements(resultList.size());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resultList;
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
   * ��öԷ���Ŀ
   * 
   * @param subjectCode
   * @param CredenceNum
   * @param flag
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public String getTheOtherDirtection(String credenceNum, String flag,
      SecurityInfo securityInfo, String office, String settdate)
      throws Exception, BusinessException {
    String subjectname = "";
    String subjectcode = "";
    String criterion = "";
    try {
      if (flag.equals("0")) {// ��0����ʾΪ�裬��ѯ c>0��
        criterion = " accountantCredence.credit <> 0 and ";
        subjectcode = accountantCredenceDAO.querySubjectCode_WL(credenceNum,
            criterion, securityInfo, office, settdate);
        subjectname = subjectDAO.querySubjectNameBySubjectCode(subjectcode,
            securityInfo.getBookId());
      } else {// ��1����ʾΪ������ѯ d>0��
        criterion = " accountantCredence.debit <> 0 and ";
        subjectcode = accountantCredenceDAO.querySubjectCode_WL(credenceNum,
            criterion, securityInfo, office, settdate);
        subjectname = subjectDAO.querySubjectNameBySubjectCode(subjectcode,
            securityInfo.getBookId());
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return subjectname;
  }

  /**
   * ���ҳ��¼��Ŀ�Ŀ�Ƿ��(�ڲ���Ϊһ����Ŀ,ȥ����)
   */
  public String checkSubjectcode(String subjectcode, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    String message = "";
    try {
      String flag = subjectDAO.getSubjectProperty(securityInfo.getBookId(),
          subjectcode);
      if (flag == null || flag.equals("")) {
        message = "¼��Ŀ�Ŀ����Ҫ�����Ѿ����ڵĿ�Ŀ����";
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return message;
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

  public List queryExpDetailAccList(ListaccAF af,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = null;
    try {
      String bookId = securityInfo.getBookId();
      String year = af.getBizyear();
      String month = af.getStarperiod();
      String yearMonth = year + (month.length() < 2 ? "0" + month : month);
      String subjectCode = af.getStarsubject();
      list = accountantCredenceDAO.queryExpDetailAccList(bookId, subjectCode,
          yearMonth);
      ListaccDTO dto = null;
      BigDecimal balance = accountantCredenceDAO.queryBgnblanceBySubjectcode_WS(
          subjectCode, yearMonth, null, securityInfo);
      // ��ý������
      String temp_dirc = getDirtection(balance);
      for (int i = 0; i < list.size(); i++) {
        dto = (ListaccDTO) list.get(i);
        // ���ͷ���
        if (dto.getCredit().compareTo(new BigDecimal(0.00)) != 0) {// ����
          if (temp_dirc.equals("��")) {
            if (dto.getCredit().compareTo(new BigDecimal(0.00)) < 0
                && dto.getCredit().abs().compareTo(balance.abs()) > 0) {
              dto.setMoney(dto.getCredit().abs().add(balance.abs()));
              balance = balance.subtract(dto.getCredit()).add(dto.getDebit());
            } else if (dto.getCredit().compareTo(new BigDecimal(0.00)) > 0
                && dto.getCredit().compareTo(balance.abs()) > 0
                && balance.compareTo(new BigDecimal(0.00)) > 0) {
              dto.setMoney(dto.getCredit().subtract(balance));
              balance = balance.subtract(dto.getCredit()).add(dto.getDebit());
            } else {
              dto.setMoney(balance.subtract(dto.getCredit()).abs());
              balance = balance.subtract(dto.getCredit()).add(dto.getDebit());
            }
//            dto.setDirtection(this.getDirtection(balance));
          } else {
            dto.setMoney(balance.abs().add(dto.getCredit()));
            balance = balance.subtract(dto.getCredit()).add(dto.getDebit());
//            dto.setDirtection(this.getDirtection(balance));
          }
        } else {// �跽

          if (temp_dirc.equals("��")) {
            if (dto.getDebit().compareTo(new BigDecimal(0.00)) < 0
                && dto.getDebit().abs().compareTo(balance.abs()) > 0) {
              dto.setMoney(dto.getDebit().abs().add(balance.abs()));
              balance = balance.subtract(dto.getDebit().abs());
            } else if (dto.getDebit().compareTo(new BigDecimal(0.00)) > 0
                && dto.getDebit().compareTo(balance.abs()) > 0
                && balance.compareTo(new BigDecimal(0.00)) < 0) {
              dto.setMoney(dto.getDebit().subtract(balance.abs()));
              // balance = balance.subtract(dto.getDebit());
              balance = dto.getDebit().subtract(balance.abs());
            } else {
              dto.setMoney(balance.abs().subtract(dto.getDebit()));
              balance = balance.add(dto.getDebit());
            }
//            dto.setDirtection(this.getDirtection(balance));
          } else {
            dto.setMoney(balance.add(dto.getDebit()));
            balance = balance.add(dto.getDebit());
//            dto.setDirtection(this.getDirtection(balance));
          }
        }
        if (dto.getSummay() != null && !"".equals(dto.getSummay())) {
          dto.setSummay(bookParameterDAO.queryParamExplainInfo_WL(dto
              .getSummay(), securityInfo, "4"));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
}
