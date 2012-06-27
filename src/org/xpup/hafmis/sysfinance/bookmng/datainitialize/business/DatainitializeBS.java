package org.xpup.hafmis.sysfinance.bookmng.datainitialize.business;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.datainitialize.bsinterface.IDatainitializeBS;
import org.xpup.hafmis.sysfinance.bookmng.datainitialize.dto.DatainitializeDTO;
import org.xpup.hafmis.sysfinance.common.dao.AccountantCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnOperateLogDAO;
import org.xpup.hafmis.sysfinance.common.dao.SubjectDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.AccountantCredence;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnOperateLog;

/**
 * Copy Right Information   : ��ʼ����
 * Project                  : �ļ���
 * @Version                 : 1.0
 * @author                  : ����
 * ��������                   : 10-16-2007
 */
public class DatainitializeBS implements IDatainitializeBS {
  
  private AccountantCredenceDAO accountantCredenceDAO = null;
  private FnOperateLogDAO fnOperateLogDAO = null;
  private BookParameterDAO bookParameterDAO = null;
  private SubjectDAO subjectDAO = null;
  private BookDAO bookDAO = null;
  
  public AccountantCredenceDAO getAccountantCredenceDAO() {
    return accountantCredenceDAO;
  }
  public void setAccountantCredenceDAO(AccountantCredenceDAO accountantCredenceDAO) {
    this.accountantCredenceDAO = accountantCredenceDAO;
  }

  public FnOperateLogDAO getFnOperateLogDAO() {
    return fnOperateLogDAO;
  }
  public void setFnOperateLogDAO(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }
  

  /**
   * ��ÿ�Ŀ����Ϳ�Ŀ����
   * ����
   */
  public List getDatainitialize(String bookId) throws Exception {
    List list = null;
    try {
      list = accountantCredenceDAO.getDatainitalizeInfo(bookId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * ����ۼƽ跽���ۼƴ���
   * ����
   */
  public List getLendsMoney(String bookId,String officeName) throws Exception {
    List list = null;
    List temp_list = new ArrayList();
    try {
      if(officeName.equals("")){
        list = this.getDatainitialize(bookId);
        return list;
      }
      //��ÿ�Ŀ����Ϳ�Ŀ����
      list = this.getDatainitialize(bookId);
      if(list == null){
        return list;
      }
      Iterator it = list.iterator();
      while(it.hasNext()){
        DatainitializeDTO temp_datainitializeDTO = (DatainitializeDTO)it.next();
        String subjectCode = temp_datainitializeDTO.getSubjectCode();
        DatainitializeDTO datainitializeDTO = accountantCredenceDAO.getLendsMoney(subjectCode, officeName,bookId);
        datainitializeDTO.setSubjectCode(temp_datainitializeDTO.getSubjectCode());
        datainitializeDTO.setSubjectName(temp_datainitializeDTO.getSubjectName());
        temp_list.add(datainitializeDTO);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return temp_list;
  }
  
  /**
   * * ɾ��FN201
   * FN201.SUMMAY=1 and FN201.OFFICE=��ѡ���´��ļ�¼
   * ����FN311
      OP_SYS=����ϵͳ
      OP_MODEL=��ʼ����
      OP_BUTTON=2
      OP_IP=����Աip
      OP_TIME=ϵͳʱ��
      OPERATOR=����Ա
   *����FN201:
      BOOK_ID=��������
      SUBJECT_CODE=��Ŀ����
      SUMMAY=1
      DEBIT=�ۼƽ跽
      CREDIT=�ۼƴ���
      MAKEBILL=����Ա
      OFFICE=��ѡ���´�
   * @param datainitializeDTO ����
   * @param securityInfo
   * @throws Exception
   */
  public void deleteSummaryOffice(List list,SecurityInfo securityInfo) throws Exception {
    try {
      DatainitializeDTO datainitializeDTO1 = (DatainitializeDTO)list.get(0);
      //ɾ�� FN201
      accountantCredenceDAO.delete_ZL(datainitializeDTO1.getBookId(), datainitializeDTO1.getOfficeName());
      accountantCredenceDAO.delete_ZL1(datainitializeDTO1.getBookId(), datainitializeDTO1.getOfficeName());
      accountantCredenceDAO.delete_ZL2(datainitializeDTO1.getBookId(), datainitializeDTO1.getOfficeName());
      //����FN311
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
      fnOperateLog.setOpModel(BusiLogConst.FN_OP_BOOKMNG_DATAINITIALIZE+"");
      fnOperateLog.setOpButton("2");
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setBookId(datainitializeDTO1.getBookId());
      fnOperateLogDAO.insert(fnOperateLog);
      
      Iterator it = list.iterator();
      while(it.hasNext()){
        DatainitializeDTO datainitializeDTO = (DatainitializeDTO)it.next();
//        if(datainitializeDTO.getDebit().equals("0") && datainitializeDTO.getCredit().equals("0")){
//          continue;
//        }else{
          //���� FN201
          AccountantCredence accountantCredence = new AccountantCredence();
          accountantCredence.setBookId(datainitializeDTO.getBookId());
          accountantCredence.setSubjectCode(datainitializeDTO.getSubjectCode());
          accountantCredence.setSummay(bookParameterDAO.getSummay(datainitializeDTO.getBookId()));

          accountantCredence.setDebit(new BigDecimal(datainitializeDTO.getDebit()));
          accountantCredence.setCredit(new BigDecimal(datainitializeDTO.getCredit()));
          
          accountantCredence.setMakebill(securityInfo.getUserName());
          accountantCredence.setOffice(datainitializeDTO.getOfficeName());
          accountantCredence.setCredenceSt("2");
          //��
          String date1 = bookDAO.getUseYearmonth(datainitializeDTO.getBookId())+"01";  
          DateFormat df = new SimpleDateFormat("yyyyMMdd");
          String year = "";
          try {  
            Date d1 = df.parse(date1);  
            //System.out.println("d1=="+df.format(d1));  
            Calendar  g = Calendar.getInstance();  
            g.setTime(d1);
            g.add(Calendar.DATE,-1);
            Date d2 = g.getTime();  
            //System.out.println("d2======="+df.format(d2)); 
            year = df.format(d2);
          } catch (ParseException exx) {              
            exx.printStackTrace();  
          }
          accountantCredence.setCredenceDate(year);
          accountantCredence.setSettDate(year);
          //�����(�������Ŀ)ֱ�Ӱ�FN201�е�(�����ת�ֶ���Ϊ2)
          if(subjectDAO.getSortcodeByCode_WL(datainitializeDTO.getSubjectCode(), securityInfo).equals("4")){
            accountantCredence.setIncDecSt("2");
          }
          if(subjectDAO.getProperyByCode_WL(datainitializeDTO.getSubjectCode(), securityInfo).equals("0")){
            //�����(�������ԵĿ�Ŀ)ֱ�Ӱ�(�����˽�ת״̬��Ϊ2)
            accountantCredence.setBankAccSt("2");
          }
          if (subjectDAO.getProperyByCode_WL(datainitializeDTO.getSubjectCode(), securityInfo).equals("1")){
            //�����(�ֽ����ԵĿ�Ŀ)ֱ�Ӱ�(�ֽ��˽�ת״̬��Ϊ2)
            accountantCredence.setCashAccSt("2");
          }
          accountantCredenceDAO.insert(accountantCredence);
//        }
      }
      Iterator it1 = list.iterator();
      while(it1.hasNext()){
        DatainitializeDTO datainitializeDTO11 = (DatainitializeDTO)it1.next();
//        if(datainitializeDTO11.getYesterdayRemainingSum().equals("0") || datainitializeDTO11.getBalaceDirection().equals("2")){
//          continue;
//        }
        AccountantCredence accountantCredence = new AccountantCredence();
        accountantCredence.setBookId(datainitializeDTO11.getBookId());
        accountantCredence.setSubjectCode(datainitializeDTO11.getSubjectCode());
        accountantCredence.setSummay(bookParameterDAO.getSummay4(datainitializeDTO11.getBookId()));
        if(datainitializeDTO11.getBalaceDirection().equals("0")){
          BigDecimal temp = new BigDecimal(datainitializeDTO11.getYesterdayRemainingSum()).subtract(new BigDecimal(datainitializeDTO11.getDebit())).add(new BigDecimal(datainitializeDTO11.getCredit())).subtract(new BigDecimal(datainitializeDTO11.getYesterdayDebit())).add(new BigDecimal(datainitializeDTO11.getYesterdayCredit()));
          accountantCredence.setDebit(temp);
          accountantCredence.setCredit(new BigDecimal("0.00"));
        }
        if(datainitializeDTO11.getBalaceDirection().equals("1")){
          BigDecimal temp = new BigDecimal(datainitializeDTO11.getYesterdayRemainingSum()).subtract(new BigDecimal(datainitializeDTO11.getCredit())).add(new BigDecimal(datainitializeDTO11.getDebit())).subtract(new BigDecimal(datainitializeDTO11.getYesterdayCredit())).add(new BigDecimal(datainitializeDTO11.getYesterdayDebit()));
          accountantCredence.setDebit(new BigDecimal("0.00"));
          accountantCredence.setCredit(temp);
        }
        
        if(datainitializeDTO11.getBalaceDirection().equals("2")){
          {
            BigDecimal temp_yeaterdayDebit = new BigDecimal(datainitializeDTO11.getYesterdayDebit());
            BigDecimal temp_debit = new BigDecimal(datainitializeDTO11.getDebit());
            BigDecimal temp_yeaterdayCredit = new BigDecimal(datainitializeDTO11.getYesterdayCredit());
            BigDecimal temp_credit = new BigDecimal(datainitializeDTO11.getCredit());
//          BigDecimal temp = temp_yeaterdayDebit.add(temp_debit).subtract(temp_yeaterdayCredit).subtract(temp_credit);
            BigDecimal temp = temp_yeaterdayCredit.add(temp_credit).subtract(temp_yeaterdayDebit).subtract(temp_debit);
            if(temp.compareTo(new BigDecimal(0)) > 0){
              accountantCredence.setDebit(temp.abs());
              accountantCredence.setCredit(new BigDecimal("0.00"));
            }
            if(temp.compareTo(new BigDecimal(0)) < 0){
              accountantCredence.setDebit(new BigDecimal("0.00"));
              accountantCredence.setCredit(temp.abs());
            }
            if(temp.compareTo(new BigDecimal(0)) == 0){
//              if(temp_yeaterdayDebit.compareTo(new BigDecimal(0))==0 && temp_debit.compareTo(new BigDecimal(0))==0 && temp_yeaterdayCredit.compareTo(new BigDecimal(0))==0 && temp_credit.compareTo(new BigDecimal(0))==0 ){
//                continue;
//              }else{
                accountantCredence.setDebit(new BigDecimal("0.00"));
                accountantCredence.setCredit(new BigDecimal("0.00"));
//              }
            }
          }
        }
        
        accountantCredence.setMakebill(securityInfo.getUserName());
        accountantCredence.setOffice(datainitializeDTO11.getOfficeName());
        accountantCredence.setCredenceSt("2");
        
        //��
        String year = (Integer.parseInt(bookDAO.getUseYearmonth(datainitializeDTO11.getBookId()).substring(0, 4))-2)+"1231";
        accountantCredence.setCredenceDate(year);
        accountantCredence.setSettDate(year);
        
        //�����(�������Ŀ)ֱ�Ӱ�FN201�е�(�����ת�ֶ���Ϊ2)
        if(subjectDAO.getSortcodeByCode_WL(datainitializeDTO11.getSubjectCode(), securityInfo).equals("4")){
          accountantCredence.setIncDecSt("2");
        }
        if(subjectDAO.getProperyByCode_WL(datainitializeDTO11.getSubjectCode(), securityInfo).equals("0")){
          //�����(�������ԵĿ�Ŀ)ֱ�Ӱ�(�����˽�ת״̬��Ϊ2)
          accountantCredence.setBankAccSt("2");
        }
        if (subjectDAO.getProperyByCode_WL(datainitializeDTO11.getSubjectCode(), securityInfo).equals("1")){
          //�����(�ֽ����ԵĿ�Ŀ)ֱ�Ӱ�(�ֽ��˽�ת״̬��Ϊ2)
          accountantCredence.setCashAccSt("2");
        }
        accountantCredenceDAO.insert(accountantCredence);
      }
      Iterator it2 = list.iterator();
      while(it2.hasNext()){
        DatainitializeDTO datainitializeDTO22 = (DatainitializeDTO)it2.next();
//        if(datainitializeDTO22.getYesterdayDebit().equals("0") && datainitializeDTO22.getYesterdayCredit().equals("0")){
//          continue;
//        }
        AccountantCredence accountantCredence = new AccountantCredence();
        accountantCredence.setBookId(datainitializeDTO22.getBookId());
        accountantCredence.setSubjectCode(datainitializeDTO22.getSubjectCode());
        accountantCredence.setSummay(bookParameterDAO.getSummay5(datainitializeDTO22.getBookId()));

        accountantCredence.setDebit(new BigDecimal(datainitializeDTO22.getYesterdayDebit()));
        accountantCredence.setCredit(new BigDecimal(datainitializeDTO22.getYesterdayCredit()));
        
        accountantCredence.setMakebill(securityInfo.getUserName());
        accountantCredence.setOffice(datainitializeDTO22.getOfficeName());
        accountantCredence.setCredenceSt("2");
        
        //��
        String year = (Integer.parseInt(bookDAO.getUseYearmonth(datainitializeDTO22.getBookId()).substring(0, 4))-1)+"1231";
        accountantCredence.setCredenceDate(year);
        accountantCredence.setSettDate(year);
        
        //�����(�������Ŀ)ֱ�Ӱ�FN201�е�(�����ת�ֶ���Ϊ2)
        if(subjectDAO.getSortcodeByCode_WL(datainitializeDTO22.getSubjectCode(), securityInfo).equals("4")){
          accountantCredence.setIncDecSt("2");
        }
        if(subjectDAO.getProperyByCode_WL(datainitializeDTO22.getSubjectCode(), securityInfo).equals("0")){
          //�����(�������ԵĿ�Ŀ)ֱ�Ӱ�(�����˽�ת״̬��Ϊ2)
          accountantCredence.setBankAccSt("2");
        }
        if (subjectDAO.getProperyByCode_WL(datainitializeDTO22.getSubjectCode(), securityInfo).equals("1")){
          //�����(�ֽ����ԵĿ�Ŀ)ֱ�Ӱ�(�ֽ��˽�ת״̬��Ϊ2)
          accountantCredence.setCashAccSt("2");
        }
        accountantCredenceDAO.insert(accountantCredence);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * * ����FN311
      OP_SYS=����ϵͳ
      OP_MODEL=��ʼ����
      OP_BUTTON=2
      OP_IP=����Աip
      OP_TIME=ϵͳʱ��
      OPERATOR=����Ա
   *����FN201:
      BOOK_ID=��������
      SUBJECT_CODE=��Ŀ����
      SUMMAY=1
      DEBIT=�ۼƽ跽
      CREDIT=�ۼƴ���
      MAKEBILL=����Ա
      OFFICE=��ѡ���´�
   * @param datainitializeDTO  ����
   * @param securityInfo
   * @throws Exception
   */
  public void insertSummaryOffice(List list,SecurityInfo securityInfo) throws Exception{
    try {
      if(list.size() != 0){
        DatainitializeDTO datainitializeDTO1 = (DatainitializeDTO)list.get(0);
        //����FN311
        FnOperateLog fnOperateLog = new FnOperateLog();
        fnOperateLog.setOpSys(BusiLogConst.OP_SYSTEM_TYPE_FINANCE+"");
        fnOperateLog.setOpModel(BusiLogConst.FN_OP_BOOKMNG_DATAINITIALIZE+"");
        fnOperateLog.setOpButton("1");
        fnOperateLog.setOpIp(securityInfo.getUserIp());
        fnOperateLog.setOpTime(new Date());
        fnOperateLog.setOperator(securityInfo.getUserName());
        fnOperateLog.setBookId(datainitializeDTO1.getBookId());
        fnOperateLogDAO.insert(fnOperateLog);
        Iterator it = list.iterator();
        while(it.hasNext()){
          DatainitializeDTO datainitializeDTO = (DatainitializeDTO)it.next();
//          if(datainitializeDTO.getDebit().equals("0") && datainitializeDTO.getCredit().equals("0")){
//            continue;
//          }else{
            //���� FN201
            AccountantCredence accountantCredence = new AccountantCredence();
            accountantCredence.setBookId(datainitializeDTO.getBookId());
            accountantCredence.setSubjectCode(datainitializeDTO.getSubjectCode());
            accountantCredence.setSummay(bookParameterDAO.getSummay(datainitializeDTO.getBookId()));

            accountantCredence.setDebit(new BigDecimal(datainitializeDTO.getDebit()));
            accountantCredence.setCredit(new BigDecimal(datainitializeDTO.getCredit()));
            
            accountantCredence.setMakebill(securityInfo.getUserName());
            accountantCredence.setOffice(datainitializeDTO.getOfficeName());
            accountantCredence.setCredenceSt("2");
            //��
            String date1 = bookDAO.getUseYearmonth(datainitializeDTO.getBookId())+"01";  
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            String year = "";
            try {
              Date d1 = df.parse(date1);  
              //System.out.println("d1=="+df.format(d1));  
              Calendar  g = Calendar.getInstance();  
              g.setTime(d1);  
              g.add(Calendar.DATE,-1);             
              Date d2 = g.getTime();  
              //System.out.println("d2======="+df.format(d2)); 
              year = df.format(d2);
            } catch (ParseException exx) {
              exx.printStackTrace();  
            }
            accountantCredence.setCredenceDate(year);
            accountantCredence.setSettDate(year);
            //�����(�������Ŀ)ֱ�Ӱ�FN201�е�(�����ת�ֶ���Ϊ2)
            if(subjectDAO.getSortcodeByCode_WL(datainitializeDTO.getSubjectCode(), securityInfo).equals("4")){
              accountantCredence.setIncDecSt("2");
            }
            if(subjectDAO.getProperyByCode_WL(datainitializeDTO.getSubjectCode(), securityInfo).equals("0")){
              //�����(�������ԵĿ�Ŀ)ֱ�Ӱ�(�����˽�ת״̬��Ϊ2)
              accountantCredence.setBankAccSt("2");
            }
            if (subjectDAO.getProperyByCode_WL(datainitializeDTO.getSubjectCode(), securityInfo).equals("1")){
              //�����(�ֽ����ԵĿ�Ŀ)ֱ�Ӱ�(�ֽ��˽�ת״̬��Ϊ2)
              accountantCredence.setCashAccSt("2");
            }
            accountantCredenceDAO.insert(accountantCredence);
//          }
        }
        Iterator it1 = list.iterator();
        while(it1.hasNext()){
          DatainitializeDTO datainitializeDTO11 = (DatainitializeDTO)it1.next();
//          if(datainitializeDTO11.getYesterdayRemainingSum().equals("0") || datainitializeDTO11.getBalaceDirection().equals("2")){
//            continue;
//          }
          AccountantCredence accountantCredence = new AccountantCredence();
          accountantCredence.setBookId(datainitializeDTO11.getBookId());
          accountantCredence.setSubjectCode(datainitializeDTO11.getSubjectCode());
          accountantCredence.setSummay(bookParameterDAO.getSummay4(datainitializeDTO11.getBookId()));
          if(datainitializeDTO11.getBalaceDirection().equals("0")){
            BigDecimal temp = new BigDecimal(datainitializeDTO11.getYesterdayRemainingSum()).subtract(new BigDecimal(datainitializeDTO11.getDebit())).add(new BigDecimal(datainitializeDTO11.getCredit())).subtract(new BigDecimal(datainitializeDTO11.getYesterdayDebit())).add(new BigDecimal(datainitializeDTO11.getYesterdayCredit()));
            accountantCredence.setDebit(temp);
            accountantCredence.setCredit(new BigDecimal("0.00"));
          }
          if(datainitializeDTO11.getBalaceDirection().equals("1")){
            BigDecimal temp = new BigDecimal(datainitializeDTO11.getYesterdayRemainingSum()).subtract(new BigDecimal(datainitializeDTO11.getCredit())).add(new BigDecimal(datainitializeDTO11.getDebit())).subtract(new BigDecimal(datainitializeDTO11.getYesterdayCredit())).add(new BigDecimal(datainitializeDTO11.getYesterdayDebit()));
            accountantCredence.setDebit(new BigDecimal("0.00"));
            accountantCredence.setCredit(temp);
          }
          if(datainitializeDTO11.getBalaceDirection().equals("2")){
            {
              BigDecimal temp_yeaterdayDebit = new BigDecimal(datainitializeDTO11.getYesterdayDebit());
              BigDecimal temp_debit = new BigDecimal(datainitializeDTO11.getDebit());
              BigDecimal temp_yeaterdayCredit = new BigDecimal(datainitializeDTO11.getYesterdayCredit());
              BigDecimal temp_credit = new BigDecimal(datainitializeDTO11.getCredit());
//            BigDecimal temp = temp_yeaterdayDebit.add(temp_debit).subtract(temp_yeaterdayCredit).subtract(temp_credit);
              BigDecimal temp = temp_yeaterdayCredit.add(temp_credit).subtract(temp_yeaterdayDebit).subtract(temp_debit);
              if(temp.compareTo(new BigDecimal(0)) > 0){
                accountantCredence.setDebit(temp.abs());
                accountantCredence.setCredit(new BigDecimal("0.00"));
              }
              if(temp.compareTo(new BigDecimal(0)) < 0){
                accountantCredence.setDebit(new BigDecimal("0.00"));
                accountantCredence.setCredit(temp.abs());
              }
              if(temp.compareTo(new BigDecimal(0)) == 0){
//                if(temp_yeaterdayDebit.compareTo(new BigDecimal(0))==0 && temp_debit.compareTo(new BigDecimal(0))==0 && temp_yeaterdayCredit.compareTo(new BigDecimal(0))==0 && temp_credit.compareTo(new BigDecimal(0))==0 ){
//                  continue;
//                }else{
                  accountantCredence.setDebit(new BigDecimal("0.00"));
                  accountantCredence.setCredit(new BigDecimal("0.00"));
//                }
              }
            }
          }
          
          accountantCredence.setMakebill(securityInfo.getUserName());
          accountantCredence.setOffice(datainitializeDTO11.getOfficeName());
          accountantCredence.setCredenceSt("2");
          
          //��
          String year = (Integer.parseInt(bookDAO.getUseYearmonth(datainitializeDTO11.getBookId()).substring(0, 4))-2)+"1231";
          accountantCredence.setCredenceDate(year);
          accountantCredence.setSettDate(year);
          
          //�����(�������Ŀ)ֱ�Ӱ�FN201�е�(�����ת�ֶ���Ϊ2)
          if(subjectDAO.getSortcodeByCode_WL(datainitializeDTO11.getSubjectCode(), securityInfo).equals("4")){
            accountantCredence.setIncDecSt("2");
          }
          if(subjectDAO.getProperyByCode_WL(datainitializeDTO11.getSubjectCode(), securityInfo).equals("0")){
            //�����(�������ԵĿ�Ŀ)ֱ�Ӱ�(�����˽�ת״̬��Ϊ2)
            accountantCredence.setBankAccSt("2");
          }
          if (subjectDAO.getProperyByCode_WL(datainitializeDTO11.getSubjectCode(), securityInfo).equals("1")){
            //�����(�ֽ����ԵĿ�Ŀ)ֱ�Ӱ�(�ֽ��˽�ת״̬��Ϊ2)
            accountantCredence.setCashAccSt("2");
          }
          accountantCredenceDAO.insert(accountantCredence);
        }
        Iterator it2 = list.iterator();
        while(it2.hasNext()){
          DatainitializeDTO datainitializeDTO2 = (DatainitializeDTO)it2.next();
//          if(datainitializeDTO2.getYesterdayDebit().equals("0") && datainitializeDTO2.getYesterdayCredit().equals("0")){
//            continue;
//          }
          AccountantCredence accountantCredence = new AccountantCredence();
          accountantCredence.setBookId(datainitializeDTO2.getBookId());
          accountantCredence.setSubjectCode(datainitializeDTO2.getSubjectCode());
          accountantCredence.setSummay(bookParameterDAO.getSummay5(datainitializeDTO2.getBookId()));

          accountantCredence.setDebit(new BigDecimal(datainitializeDTO2.getYesterdayDebit()));
          accountantCredence.setCredit(new BigDecimal(datainitializeDTO2.getYesterdayCredit()));
          
          accountantCredence.setMakebill(securityInfo.getUserName());
          accountantCredence.setOffice(datainitializeDTO2.getOfficeName());
          accountantCredence.setCredenceSt("2");
          
          //��
          String year = (Integer.parseInt(bookDAO.getUseYearmonth(datainitializeDTO2.getBookId()).substring(0, 4))-1)+"1231";
          accountantCredence.setCredenceDate(year);
          accountantCredence.setSettDate(year);
          
          //�����(�������Ŀ)ֱ�Ӱ�FN201�е�(�����ת�ֶ���Ϊ2)
          if(subjectDAO.getSortcodeByCode_WL(datainitializeDTO2.getSubjectCode(), securityInfo).equals("4")){
            accountantCredence.setIncDecSt("2");
          }
          if(subjectDAO.getProperyByCode_WL(datainitializeDTO2.getSubjectCode(), securityInfo).equals("0")){
            //�����(�������ԵĿ�Ŀ)ֱ�Ӱ�(�����˽�ת״̬��Ϊ2)
            accountantCredence.setBankAccSt("2");
          }
          if (subjectDAO.getProperyByCode_WL(datainitializeDTO2.getSubjectCode(), securityInfo).equals("1")){
            //�����(�ֽ����ԵĿ�Ŀ)ֱ�Ӱ�(�ֽ��˽�ת״̬��Ϊ2)
            accountantCredence.setCashAccSt("2");
          }
          accountantCredenceDAO.insert(accountantCredence);
        }
       }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * FN101 ����״̬ 0���ã�1Ϊ����
   * @param bookId
   * @return
   * @throws Exception  ����
   */
  public String getBookST(String bookId)throws Exception{
    String stutas = "";
    try {
      stutas = accountantCredenceDAO.getBookST(bookId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return stutas;
  }
  
  /**
   * �ж�FN201���Ƿ����SUMMAY=1 and OFFICE=��ѡ���´��ļ�¼
   * @param bookId
   * @param officeName
   * @return
   * @throws Exception ����
   */
  public List is_SummayOffice(final String bookId,final String officeName) throws Exception{
    return accountantCredenceDAO.is_SummayOffice(bookId, officeName);
  }
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
  public BookDAO getBookDAO() {
    return bookDAO;
  }
  public void setBookDAO(BookDAO bookDAO) {
    this.bookDAO = bookDAO;
  }
}
