package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.business;

import java.io.Serializable;
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
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.dto.CredencecheckFindDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.dto.CredencecheckShowListDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.bsinterface.ICredenceFillinBS;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinIncomeExpenseDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTaShowDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTbFindDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTbListDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTcFindDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTcListDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.DelectCredenceInfoDTO;
import org.xpup.hafmis.sysfinance.common.biz.credencepop.dto.CredencePopInfoDTO;
import org.xpup.hafmis.sysfinance.common.biz.credencepop.dto.CredencePopListDTO;
import org.xpup.hafmis.sysfinance.common.dao.AccountantCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnBizActivityLogDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnDocNumCancelDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnDocNumMaintainDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnOperateLogDAO;
import org.xpup.hafmis.sysfinance.common.dao.SubjectDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.AccountantCredence;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnBizActivityLog;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnOperateLog;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.BookParameterDTO;

/**
 * Copy Right Information : ƾ֤¼��BS Goldsoft Project : CredenceFillinBS
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.10.17
 */
public class CredenceFillinBS implements ICredenceFillinBS {
  // FN201DAO
  protected AccountantCredenceDAO accountantCredenceDAO = null;

  protected SecurityDAO securityDAO = null;

  private BookParameterDAO bookParameterDAO = null;

  protected OrganizationUnitDAO organizationUnitDAO = null;

  protected FnBizActivityLogDAO fnBizActivityLogDAO = null;

  protected FnOperateLogDAO fnOperateLogDAO = null;

  protected FnDocNumCancelDAO fnDocNumCancelDAO = null;

  private FnDocNumMaintainDAO fnDocNumMaintainDAO = null;

  private SubjectDAO subjectDAO = null;

  private BookDAO bookDAO = null;

  public void setFnDocNumCancelDAO(FnDocNumCancelDAO fnDocNumCancelDAO) {
    this.fnDocNumCancelDAO = fnDocNumCancelDAO;
  }

  public void setFnDocNumMaintainDAO(FnDocNumMaintainDAO fnDocNumMaintainDAO) {
    this.fnDocNumMaintainDAO = fnDocNumMaintainDAO;
  }

  public void setSubjectDAO(SubjectDAO subjectDAO) {
    this.subjectDAO = subjectDAO;
  }

  public void setFnOperateLogDAO(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }

  public int findFnSettleType() throws Exception {
    return securityDAO.getFnSettleType();
  }

  public void setFnBizActivityLogDAO(FnBizActivityLogDAO fnBizActivityLogDAO) {
    this.fnBizActivityLogDAO = fnBizActivityLogDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public void setAccountantCredenceDAO(
      AccountantCredenceDAO accountantCredenceDAO) {
    this.accountantCredenceDAO = accountantCredenceDAO;
  }

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }

  public void setBookDAO(BookDAO bookDAO) {
    this.bookDAO = bookDAO;
  }

  public AccountantCredence queryById(Serializable credenceId) {
    return accountantCredenceDAO.queryById(credenceId);
  }

  // ----------------------------------------------------------------fuyf
  public Object[] findCredenceFillinTbList(Pagination pagination,
      List officeList, SecurityInfo securityInfo) throws Exception {
    Object[] obj = new Object[3];
    CredenceFillinTbFindDTO credenceFillinTbFindDTO = null;

    if (pagination.getQueryCriterions().get("credenceFillinTbFindDTO") != null) {
      credenceFillinTbFindDTO = (CredenceFillinTbFindDTO) pagination
          .getQueryCriterions().get("credenceFillinTbFindDTO");
    } else {
      credenceFillinTbFindDTO = new CredenceFillinTbFindDTO();
    }
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    List list = accountantCredenceDAO.queryAutoTransferInfoList(
        credenceFillinTbFindDTO, officeList, orderBy, order, start, pageSize,
        securityInfo.getBookId(), page);
    List lista = accountantCredenceDAO.queryAutoTransferInfoLista(
        credenceFillinTbFindDTO, officeList, orderBy, order, start, pageSize,
        securityInfo.getBookId(), page);
    for (int i = 0; i < list.size(); i++) {
      CredenceFillinTbListDTO credenceFillinTbListDTO = (CredenceFillinTbListDTO) list
          .get(i);
      credenceFillinTbListDTO.setBizType(BusiTools.getBusiValue(Integer
          .parseInt(credenceFillinTbListDTO.getNumBizType()),
          BusiConst.FNBUSINESSTYPE));
    }

    List countList = accountantCredenceDAO.queryAutoTransferInfoCount(
        credenceFillinTbFindDTO, officeList, securityInfo.getBookId());
    pagination.setNrOfElements(countList.size());
    String[] temp_strArray = new String[countList.size()];
    for (int i = 0; i < countList.size(); i++) {
      CredenceFillinTbListDTO credenceFillinTbCountDTO = (CredenceFillinTbListDTO) countList
          .get(i);
      String str = credenceFillinTbCountDTO.getSettNum() + ","
          + credenceFillinTbCountDTO.getBizType();
      temp_strArray[i] = str;
    }
    obj[0] = list;
    obj[1] = temp_strArray;
    obj[2] = lista;
    return obj;
  }

  // ----------------------------------------------------------------fuyf
  // ------------------------------------------------------���в���
  // ------------------------------------------------------���в���
  // -----------------------------------------------------------------liub
  /**
   * ������-ƾ֤¼��
   * 
   * @author ���� 2007-10-24 ����FN201ƾ֤��������
   */
  public void insertCredenceFillinTa(
      CredenceFillinTaShowDTO credenceFillinTaShowDTO,
      SecurityInfo securityInfo, String listAllContent) throws Exception,
      BusinessException {

    try {
      // �����������ڴ���bb201�����ڵ�һ���£���ʾ�����½�
      String temp_credenceDate1 = credenceFillinTaShowDTO.getChargeUpDate();
      String temp_credenceDate2 = securityInfo.getUserInfo().getFbizDate();
      String temp_yearMonth1 = temp_credenceDate1.substring(0, 6);
      String temp_yearMonth2 = temp_credenceDate2.substring(0, 6);
      if (Integer.parseInt(temp_yearMonth1) > Integer.parseInt(temp_yearMonth2)) {
        throw new BusinessException("�����½ᣡ");
      }

      String temp_credenceDate3 = bookParameterDAO.getFBizDate(securityInfo
          .getBookId(), securityInfo.getUserName());
      if (!temp_credenceDate3.equals("")) {
        if (Integer.parseInt(temp_credenceDate1) < Integer
            .parseInt(temp_credenceDate3)) {
          throw new BusinessException("��ƾ֤�����Ѽ���!");
        }
      }

    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    String office = "";
    int settleType = securityInfo.getFnSettleType();
    if (settleType == 0) {
      office = null;
    } else if (settleType == 1) {
      office = credenceFillinTaShowDTO.getOffice();
    }
    String credenceNum = fnDocNumMaintainDAO.getFnDocNumdocNum(office,
        credenceFillinTaShowDTO.getChargeUpDate().substring(0, 6), "0",
        securityInfo.getBookId());
    // ����fn201��
    String[] listContent = listAllContent.split(";");
    BigDecimal debit_sun = new BigDecimal(0.00);
    BigDecimal credit_sun = new BigDecimal(0.00);
    for (int i = 0; i < listContent.length; i++) {
      String[] listProperty = listContent[i].split(",");
      String paramId = bookParameterDAO.queryParamIdByParaExplain(
          listProperty[0], securityInfo);
      credenceFillinTaShowDTO.setSummay(paramId);
      credenceFillinTaShowDTO.setFreeSummay(listProperty[1]);
      credenceFillinTaShowDTO.setSubjectCode(listProperty[2]);
      credenceFillinTaShowDTO.setDebit(listProperty[3]);
      credenceFillinTaShowDTO.setCredit(listProperty[4]);
      debit_sun = debit_sun.add(new BigDecimal(listProperty[3]));
      credit_sun = credit_sun.add(new BigDecimal(listProperty[4]));
      credenceFillinTaShowDTO.setCredenceSt("0");
      credenceFillinTaShowDTO.setIncDecSt("0");
      credenceFillinTaShowDTO.setCashAccSt("0");
      credenceFillinTaShowDTO.setBankAccSt("0");
      AccountantCredence accountantCredence = new AccountantCredence();
      accountantCredence.setBookId(securityInfo.getBookId());
      accountantCredence.setCredenceNum(credenceNum);
      accountantCredence.setSummay(credenceFillinTaShowDTO.getSummay());
      accountantCredence.setFreeSummay(credenceFillinTaShowDTO.getFreeSummay());
      accountantCredence.setSubjectCode(credenceFillinTaShowDTO
          .getSubjectCode());
      accountantCredence.setOldCredenceNum(credenceFillinTaShowDTO
          .getOldCredenceNum());
      accountantCredence.setSettNum(credenceFillinTaShowDTO.getSettNum());
      accountantCredence.setDebit(new BigDecimal(credenceFillinTaShowDTO
          .getDebit()));
      accountantCredence.setCredit(new BigDecimal(credenceFillinTaShowDTO
          .getCredit()));
      accountantCredence.setCredenceDate(credenceFillinTaShowDTO
          .getChargeUpDate());
      accountantCredence.setCredenceCharacter(credenceFillinTaShowDTO
          .getCredenceCharacter());
      accountantCredence.setMakebill(credenceFillinTaShowDTO.getMakebill());
      accountantCredence.setAccountpsn(credenceFillinTaShowDTO.getAccountpsn());
      accountantCredence.setAccountCharge(credenceFillinTaShowDTO
          .getAccountCharge());
      accountantCredence.setOffice(credenceFillinTaShowDTO.getOffice());
      accountantCredence.setCredenceSt(credenceFillinTaShowDTO.getCredenceSt());
      accountantCredence.setIncDecSt(credenceFillinTaShowDTO.getIncDecSt());
      accountantCredence.setCashAccSt(credenceFillinTaShowDTO.getCashAccSt());
      accountantCredence.setBankAccSt(credenceFillinTaShowDTO.getBankAccSt());
      accountantCredence.setSettType(credenceFillinTaShowDTO.getSettType());
      accountantCredence.setSettDate(credenceFillinTaShowDTO.getSettDate());
      accountantCredenceDAO.insert(accountantCredence);
    }
    if(debit_sun.compareTo(credit_sun)!=0){
      throw new BusinessException("�����ƽ����������");
    }
    // ����fn310��
    FnBizActivityLog fnBizActivityLog = new FnBizActivityLog();
    fnBizActivityLog.setCredenceNum(credenceFillinTaShowDTO.getCredenceNum());
    fnBizActivityLog.setCredenceType("0");
    fnBizActivityLog.setCredenceDate(credenceFillinTaShowDTO.getChargeUpDate());
    fnBizActivityLog.setOffice(credenceFillinTaShowDTO.getOffice());
    fnBizActivityLog.setAction("0");
    fnBizActivityLog.setOpTime(new Date());
    fnBizActivityLog.setOperator(securityInfo.getUserName());
    fnBizActivityLog.setBookId(securityInfo.getBookId());
    fnBizActivityLogDAO.insert(fnBizActivityLog);
    // ����fn311��
    FnOperateLog fnOperateLog = new FnOperateLog();
    fnOperateLog.setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
    fnOperateLog.setOpModel(String
        .valueOf(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCEFILLIN));
    fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
    fnOperateLog.setOpIp(securityInfo.getUserIp());
    fnOperateLog.setOpTime(new Date());
    fnOperateLog.setOperator(securityInfo.getUserName());
    fnOperateLog.setBookId(securityInfo.getBookId());
    fnOperateLogDAO.insert(fnOperateLog);
  }

  /**
   * ������-ƾ֤¼��
   * 
   * @author ���� 2007-11-7 �޸�ƾ֤¼��
   */
  public void updateCredenceFillinTa(
      CredenceFillinTaShowDTO credenceFillinTaShowDTO,
      SecurityInfo securityInfo, String listAllContent) throws Exception {
    try {
      // �����������ڴ���bb201�����ڵ�һ���£���ʾ�����½�
      String temp_credenceDate1 = credenceFillinTaShowDTO.getChargeUpDate();
      String temp_credenceDate2 = securityInfo.getUserInfo().getBizDate();
      String temp_yearMonth1 = temp_credenceDate1.substring(0, 6);
      String temp_yearMonth2 = temp_credenceDate2.substring(0, 6);
      if (Integer.parseInt(temp_yearMonth1) > Integer.parseInt(temp_yearMonth2)) {
        throw new BusinessException("�����½ᣡ");
      }

      String temp_credenceDate3 = bookParameterDAO.getFBizDate(securityInfo
          .getBookId(), securityInfo.getUserName());
      if (!temp_credenceDate3.equals("")) {
        if (Integer.parseInt(temp_credenceDate1) < Integer
            .parseInt(temp_credenceDate3)) {
          throw new BusinessException("��ƾ֤�����Ѽ���!");
        }
      }
    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // ɾ��FN201��
    if (credenceFillinTaShowDTO.getReserveA().equals("2")) {
      // ���Ϊ�ڲ�ת��ת����ʱ�������ַ���ɾ��ƾ֤
      accountantCredenceDAO.delectCredenceInfo_reserveB_wsh(
          credenceFillinTaShowDTO.getModify_oldCredenceNum(),
          credenceFillinTaShowDTO.getOldOffice(), credenceFillinTaShowDTO
              .getChargeOldUpDate(), securityInfo.getBookId());
    } else if (credenceFillinTaShowDTO.getReserveA().equals("23")) {
      // ���Ϊ�����𻹴���ʱ�������ַ���ɾ��ƾ֤
      accountantCredenceDAO.delectCredenceInfo_reserveB_wsh(
          credenceFillinTaShowDTO.getModify_oldCredenceNum(), "23",
          credenceFillinTaShowDTO.getChargeOldUpDate(), securityInfo
              .getBookId());
    }else {
      accountantCredenceDAO.delectCredenceInfo_wsh(credenceFillinTaShowDTO
          .getModify_oldCredenceNum(), credenceFillinTaShowDTO.getOldOffice(),
          credenceFillinTaShowDTO.getChargeOldUpDate(),securityInfo.getBookId());
    }
    // ɾ��FN310��
    fnBizActivityLogDAO.deleteFnBizActivityLog_lb(credenceFillinTaShowDTO
        .getModify_oldCredenceNum(), credenceFillinTaShowDTO
        .getChargeOldUpDate(), credenceFillinTaShowDTO.getOldOffice(), "0",
        securityInfo);

    FnOperateLog fnOperateLog = new FnOperateLog();
    fnOperateLog.setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
    fnOperateLog.setOpModel(String
        .valueOf(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCEFILLIN));
    fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_DELETE));
    fnOperateLog.setOpIp(securityInfo.getUserIp());
    fnOperateLog.setOpTime(new Date());
    fnOperateLog.setOperator(securityInfo.getUserName());
    fnOperateLog.setBookId(securityInfo.getBookId());
    fnOperateLogDAO.insert(fnOperateLog);
    // �²���fn201��fn310��fn311������
    String[] listContent = listAllContent.split(";");
    BigDecimal debit_sun = new BigDecimal(0.00);
    BigDecimal credit_sun = new BigDecimal(0.00);
    for (int i = 0; i < listContent.length; i++) {
      String[] listProperty = listContent[i].split(",");
      String paramId = bookParameterDAO.queryParamIdByParaExplain(
          listProperty[0], securityInfo);
      credenceFillinTaShowDTO.setSummay(paramId);
      credenceFillinTaShowDTO.setFreeSummay(listProperty[1]);
      credenceFillinTaShowDTO.setSubjectCode(listProperty[2]);
      credenceFillinTaShowDTO.setDebit(listProperty[3]);
      credenceFillinTaShowDTO.setCredit(listProperty[4]);
      debit_sun = debit_sun.add(new BigDecimal(listProperty[3]));
      credit_sun = credit_sun.add(new BigDecimal(listProperty[4]));
      credenceFillinTaShowDTO.setCredenceSt("0");
      credenceFillinTaShowDTO.setIncDecSt("0");
      credenceFillinTaShowDTO.setCashAccSt("0");
      credenceFillinTaShowDTO.setBankAccSt("0");
      AccountantCredence accountantCredence = new AccountantCredence();
      accountantCredence.setBookId(securityInfo.getBookId());
      accountantCredence.setCredenceNum(credenceFillinTaShowDTO
          .getModify_oldCredenceNum());
      accountantCredence.setSummay(credenceFillinTaShowDTO.getSummay());
      accountantCredence.setFreeSummay(credenceFillinTaShowDTO.getFreeSummay());
      accountantCredence.setSubjectCode(credenceFillinTaShowDTO
          .getSubjectCode());
      accountantCredence.setOldCredenceNum(credenceFillinTaShowDTO
          .getOldCredenceNum());
      accountantCredence.setSettNum(credenceFillinTaShowDTO.getSettNum());
      accountantCredence.setDebit(new BigDecimal(credenceFillinTaShowDTO
          .getDebit()));
      accountantCredence.setCredit(new BigDecimal(credenceFillinTaShowDTO
          .getCredit()));
      accountantCredence.setCredenceDate(credenceFillinTaShowDTO
          .getChargeUpDate());
      accountantCredence.setCredenceCharacter(credenceFillinTaShowDTO
          .getCredenceCharacter());
      accountantCredence.setMakebill(credenceFillinTaShowDTO.getMakebill());
      accountantCredence.setAccountpsn(credenceFillinTaShowDTO.getAccountpsn());
      accountantCredence.setAccountCharge(credenceFillinTaShowDTO
          .getAccountCharge());
      accountantCredence.setOffice(credenceFillinTaShowDTO.getOffice());
      accountantCredence.setCredenceSt(credenceFillinTaShowDTO.getCredenceSt());
      accountantCredence.setIncDecSt(credenceFillinTaShowDTO.getIncDecSt());
      accountantCredence.setCashAccSt(credenceFillinTaShowDTO.getCashAccSt());
      accountantCredence.setBankAccSt(credenceFillinTaShowDTO.getBankAccSt());
      accountantCredence.setSettType(credenceFillinTaShowDTO.getSettType());
      accountantCredence.setSettDate(credenceFillinTaShowDTO.getSettDate());
      accountantCredence.setReserveA(credenceFillinTaShowDTO.getReserveA());
      accountantCredence.setReserveB(credenceFillinTaShowDTO.getReserveB());
      accountantCredence.setReserveC(credenceFillinTaShowDTO.getReserveC());
      //��Ҫ���ڿ���´�ת����޸ĺ�֤�����������´�
      if(credenceFillinTaShowDTO.getReserveA().equals("2")){//�������1˵��Ϊ����ҵ��,Ŀǰֻ������ת
        String officeCode = "";
        if(new BigDecimal(listProperty[3]).intValue()>new BigDecimal(listProperty[4]).intValue()){
          officeCode = accountantCredenceDAO.queryOfficeCode_Debit(
              credenceFillinTaShowDTO.getSettNum(), 
              credenceFillinTaShowDTO.getChargeOldUpDate());
        }else{
          officeCode = accountantCredenceDAO.queryOfficeCode_Credit(
              credenceFillinTaShowDTO.getSettNum(), 
              credenceFillinTaShowDTO.getChargeOldUpDate());
        }
        if(officeCode!=null){
          accountantCredence.setOffice(officeCode);
        }else{
          accountantCredence.setOffice(credenceFillinTaShowDTO.getOffice());
        }
      }else{
        accountantCredence.setOffice(credenceFillinTaShowDTO.getOffice());
      }
      accountantCredenceDAO.insert(accountantCredence);
    }
    if(debit_sun.compareTo(credit_sun)!=0){
      throw new BusinessException("�����ƽ����������");
    }
    // ����fn310��
    FnBizActivityLog fnBizActivityLog = new FnBizActivityLog();
    fnBizActivityLog.setCredenceNum(credenceFillinTaShowDTO.getCredenceNum());
    fnBizActivityLog.setCredenceType("0");
    fnBizActivityLog.setCredenceDate(credenceFillinTaShowDTO.getChargeUpDate());
    fnBizActivityLog.setOffice(credenceFillinTaShowDTO.getOffice());
    fnBizActivityLog.setAction("0");
    fnBizActivityLog.setOpTime(new Date());
    fnBizActivityLog.setOperator(securityInfo.getUserName());
    fnBizActivityLog.setBookId(securityInfo.getBookId());
    fnBizActivityLogDAO.insert(fnBizActivityLog);
    // ����fn311��
    fnOperateLog = new FnOperateLog();
    fnOperateLog.setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
    fnOperateLog.setOpModel(String
        .valueOf(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCEFILLIN));
    fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
    fnOperateLog.setOpIp(securityInfo.getUserIp());
    fnOperateLog.setOpTime(new Date());
    fnOperateLog.setOperator(securityInfo.getUserName());
    fnOperateLog.setBookId(securityInfo.getBookId());
    fnOperateLogDAO.insert(fnOperateLog);
  }

  /**
   * ȡ��ƾ֤¼��ժҪ�б���ժҪ���ƻ�������
   * 
   * @author ���� 2007-10-24 ��ѯfn102����paramExplain�ֶε����� ��ѯ������paramNum
   */
  public String findCredenceCharacterList(String search,
      SecurityInfo securityInfo) throws Exception {
    List summrayList = null;
    summrayList = bookParameterDAO.findParamExplainByChinese("4", " 10",
        search, securityInfo);
    StringBuffer summrayBuf = new StringBuffer();
    String summrayShow = "";
    for (int i = 0; i < summrayList.size(); i++) {
      BookParameterDTO bookParameterDTO = (BookParameterDTO) summrayList.get(i);
      String summray = bookParameterDTO.getBookParameterName();
      summrayBuf.append(summray + "\n");
    }
    summrayShow = summrayBuf.toString();
    return summrayShow;
  }

  /**
   * ȡ��ƾ֤¼��ժҪ�б�
   * 
   * @author ���� 2007-11-10 ��ѯfn102����paramExplain�ֶε�����
   */
  public List getSummayList(SecurityInfo securityInfo) throws Exception {
    List summrayList = null;
    summrayList = bookParameterDAO.getParamExplain_ice("4", "10", securityInfo);
    return summrayList;
  }

  // -----------------------------------------------------------------liub

  public void saveCredenceInfo(String[] settNum, String credenceDate,
      String oldCredenceNum, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    String temp_str = "";
    if (settNum != null) {
      for (int i = 0; i < settNum.length; i++) {
        String[] temp_array = settNum[i].split(",");
        // �õ���Ҫת��ҵ���Ʊ�ݱ��(�����)
        String noteNum = temp_array[0];
        // �õ���Ҫת��ҵ�������
        String bizType = temp_array[temp_array.length - 1];
        /*
         * ͨ��Ʊ�ݱ�����õ�AA101��PL202�е�ҵ��״̬������ʶ ���ҵ������С��20��ҵ���AA101�еõ�״̬���ʶ
         * ���ҵ�����ʹ���20��ҵ�����PL202�еõ�״̬���ʶ
         */
        int count = accountantCredenceDAO.queryBizTypeAndIsFinance(noteNum,
            bizType);
        // ��count>0ʱ��֤�����ڲ�����ת��������ҵ��
        if (count > 0) {
          throw new BusinessException("ҵ���н����Ϊ" + noteNum
              + "��ҵ�񲻷���ת������,���ܽ���ת�ˣ�");
        }
        // �ж��Ƿ����ظ��Ľ����
        if (!bizType.equals("4") && !bizType.equals("5")
            && !bizType.equals("6") && !bizType.equals("7")) {
          List list = accountantCredenceDAO.queryIsExistSettNum(noteNum,
              securityInfo.getBookId());
          if (list.size() != 0) {
            throw new BusinessException("�����" + noteNum + "�ظ�,���ܽ���ת�ˣ�");
          }
        }
        // �ж���Ʊ�ݱ���Ƿ��ڲ������ۻ��ǹ����𻹴���
        Object obj = accountantCredenceDAO.isSpecailType(noteNum);
        if (obj == null || obj.toString().equals("")) {
          // �ж�ͬһ������Ƿ���ڲ�ͬ�ĵ�λ��������,����Ϊ
          if (!bizType.equals("4") && !bizType.equals("5")
              && !bizType.equals("6") && !bizType.equals("7")
              && !bizType.equals("8") && !bizType.equals("11")) {
            List orgList = accountantCredenceDAO.queryOrgAndBank(noteNum,
                bizType);
            if (orgList.size() > 1) {
              throw new BusinessException("�����Ϊ" + noteNum + "��ҵ���ܽ���ת�ˣ�");
            }
          }
        }
        // �ж�ҵ���Ƿ��������ϵ
        Integer l = accountantCredenceDAO.queryIsCalculRela(noteNum, bizType,
            securityInfo.getBookId());
        if (l.intValue() <= 0) {
          throw new BusinessException("�����Ϊ" + noteNum
              + "��ҵ�����ѽ����ĺ����ϵ���Ҳ�����¼������ת�ˣ�");
        }
        // �����ύ���Ľ������ҵ��������������ַ���ʹ��'-'�ֿ����Ա����洢����
        temp_str = temp_str + settNum[i] + "-";
      }
      temp_str = temp_str.substring(0, temp_str.lastIndexOf("-"));
      // ʹ������ƾ֤�Ĵ洢����
      String bookId = securityInfo.getBookId();
      String userIP = securityInfo.getUserIp();
      String userName = securityInfo.getUserName();
      String bizDate = securityInfo.getUserInfo().getFbizDate();
      System.out.println(temp_str + "=============>");
      accountantCredenceDAO.insertCredenceInfo(temp_str, bookId, userIP,
          userName, credenceDate, oldCredenceNum);
    }

  }

  public Object[] findCredenceFillinTcList(Pagination pagination,
      List officeList, String bookId) throws Exception {

    // �õ������������ʱ��
    String useYearmonth = bookDAO.getUseYearmonth(bookId);

    Object[] obj = new Object[5];
    CredenceFillinTcFindDTO credenceFillinTcFindDTO = null;
    if (pagination.getQueryCriterions().get("credenceFillinTcFindDTO") != null) {
      credenceFillinTcFindDTO = (CredenceFillinTcFindDTO) pagination
          .getQueryCriterions().get("credenceFillinTcFindDTO");
    } else {
      credenceFillinTcFindDTO = new CredenceFillinTcFindDTO();
    }
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();

    List credenceFillinTcListDTOList = accountantCredenceDAO
        .querySettleIncAndDecInfoList(credenceFillinTcFindDTO, bookId,
            officeList, orderBy, order, start, pageSize, page, useYearmonth);
    for (int i = 0; i < credenceFillinTcListDTOList.size(); i++) {
      CredenceFillinTcListDTO credenceFillinTcListDTO = (CredenceFillinTcListDTO) credenceFillinTcListDTOList
          .get(i);
      // ת�����´�
      OrganizationUnit organizationUnit = organizationUnitDAO
          .queryOrganizationUnitListByCriterions(credenceFillinTcListDTO
              .getOffice());
      credenceFillinTcListDTO.setStrOffice(organizationUnit.getName());
      if (credenceFillinTcListDTO.getBalanceDirection().equals("0")) {
        credenceFillinTcListDTO.setDebit(credenceFillinTcListDTO.getSumDebit()
            .subtract(credenceFillinTcListDTO.getSumCredit()));
        credenceFillinTcListDTO.setCredit(new BigDecimal(0.00));
      } else if (credenceFillinTcListDTO.getBalanceDirection().equals("1")) {
        credenceFillinTcListDTO.setCredit(credenceFillinTcListDTO
            .getSumCredit().subtract(credenceFillinTcListDTO.getSumDebit()));
        credenceFillinTcListDTO.setDebit(new BigDecimal(0.00));
      }
    }
    List credenceFillinTcListDTOCount = accountantCredenceDAO
        .querySettleIncAndDecInfoCount(credenceFillinTcFindDTO, bookId,
            officeList, useYearmonth);
    BigDecimal sumCreditMoney = new BigDecimal(0.00);
    BigDecimal sumDebitMoney = new BigDecimal(0.00);
    // ����ѯ������������ַ�����������ȫ����ת
    String[] temp_strArray = new String[credenceFillinTcListDTOCount.size()];
    for (int i = 0; i < credenceFillinTcListDTOCount.size(); i++) {
      CredenceFillinTcListDTO credenceFillinTcListDTO = (CredenceFillinTcListDTO) credenceFillinTcListDTOCount
          .get(i);
      if (credenceFillinTcListDTO.getBalanceDirection().equals("0")) {
        credenceFillinTcListDTO.setDebit(credenceFillinTcListDTO.getSumDebit()
            .subtract(credenceFillinTcListDTO.getSumCredit()));
        credenceFillinTcListDTO.setCredit(new BigDecimal(0.00));
      } else if (credenceFillinTcListDTO.getBalanceDirection().equals("1")) {
        credenceFillinTcListDTO.setCredit(credenceFillinTcListDTO
            .getSumCredit().subtract(credenceFillinTcListDTO.getSumDebit()));
        credenceFillinTcListDTO.setDebit(new BigDecimal(0.00));
      }
      // ������ϼ�
      sumCreditMoney = sumCreditMoney.add(credenceFillinTcListDTO.getCredit());
      sumDebitMoney = sumDebitMoney.add(credenceFillinTcListDTO.getDebit());

      String str = credenceFillinTcListDTO.getSubjectcode() + ","
          + credenceFillinTcListDTO.getOffice() + ","
          + credenceFillinTcListDTO.getDebit() + ","
          + credenceFillinTcListDTO.getCredit() + ","
          + credenceFillinTcListDTO.getBySubjectDirection();
      temp_strArray[i] = str;
    }
    int count = credenceFillinTcListDTOCount.size();
    pagination.setNrOfElements(count);

    obj[0] = credenceFillinTcListDTOList;
    obj[1] = credenceFillinTcListDTOCount;
    obj[2] = sumCreditMoney;
    obj[3] = sumDebitMoney;
    obj[4] = temp_strArray;

    return obj;
  }

  public void saveSettleIncAndDecInfo(String[] SettleIncAndDecInfo,
      SecurityInfo securityInfo, String credenceDateStart,
      String credenceDateEnd) throws Exception, BusinessException {
    String bookid = securityInfo.getBookId();
    String temp_str = "";
    // �ж�ҳ����Ҫ��ת�Ŀ�Ŀ���뼰���´���FN201���е������ת״̬�Ƿ�=0
    for (int i = 0; i < SettleIncAndDecInfo.length; i++) {
      String[] temp_array = SettleIncAndDecInfo[i].split(",");
      int count = accountantCredenceDAO.isSettleIncAndDecInfo(bookid,
          temp_array[0], temp_array[1]);
      if (count > 0) {
        throw new BusinessException("��Ŀ����Ϊ" + temp_array[0] + "��ҵ���ܽ��н�ת��");
      }
      // �����ύ���Ľ������ҵ��������������ַ���ʹ��'#'�ֿ����Ա����洢����
      temp_str = temp_str + SettleIncAndDecInfo[i] + "#";
    }
    temp_str = temp_str.substring(0, temp_str.lastIndexOf("#"));

    String bookId = securityInfo.getBookId();
    String userIP = securityInfo.getUserIp();
    String userName = securityInfo.getUserName();
    String bizDate = securityInfo.getUserInfo().getFbizDate();
    // ���ý�ת�Ĵ洢����
    accountantCredenceDAO.insertSettleIncAndDecInfo(temp_str, bookId, userIP,
        userName, bizDate, credenceDateStart, credenceDateEnd);

  }

  public void delectCredenceInfo(String credenceId, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    String bookId = securityInfo.getBookId();
    // �õ������ϵ
    int settleType = securityInfo.getFnSettleType();
    // �õ���Ҫɾ����ƾ֤��Ϣ
    DelectCredenceInfoDTO delectCredenceInfoDTO = accountantCredenceDAO
        .queryDelectCredenceInfo(credenceId, bookId);
    // �ж�ƾ֤״̬�Ƿ�Ϊ0��ȷ��״̬
    if (!delectCredenceInfoDTO.getCredenceSt().equals("0")) {
      throw new BusinessException("�ü�¼�����ڻ�����ɾ����");
    }
    // �ж��Ƿ�Ϊ�����ת
    Object paraId = accountantCredenceDAO.isSettleIncAndDecInfo(bookId);
    if (delectCredenceInfoDTO.getSummay().equals(paraId.toString())) {
      // �жϳ���Ҫ���µĿ�Ŀ����
      List list = accountantCredenceDAO.isUpdateSettleIncAndDecInfo(bookId,
          delectCredenceInfoDTO.getDocNum());
      for (int i = 0; i < list.size(); i++) {
        String subject_code = (String) list.get(i);
        // ���summay���ڲ�ѯ�������ݣ�����ƾ֤�� FN201����������ͬ�Ŀ�Ŀ���롢���´�������״̬=1.�ѽ�תδ����Ϊ0�ļ�¼
        accountantCredenceDAO.updateSettleIncAndDecInfo(subject_code,
            delectCredenceInfoDTO.getOffice(), bookId);
      }
    } else {
      // �ж��Ƿ�Ϊ�ڲ�ת����ڲ�ת��
      if (delectCredenceInfoDTO.getBizType().equals("0")) {
        // ����AA101�ж�Ӧ״̬����Ϣ(�ڲ�ת��ʱʹ��)
        accountantCredenceDAO.updateOrgHAFAccountFlow_E(delectCredenceInfoDTO
            .getSettNum());
      } else if (delectCredenceInfoDTO.getBizType().equals("1")) {
        // ����AA101�ж�Ӧ״̬����Ϣ(�ڲ�ת��ʱʹ��)
        accountantCredenceDAO.updateOrgHAFAccountFlow_F(delectCredenceInfoDTO
            .getSettNum());
      } else if (delectCredenceInfoDTO.getReserveB().equals("4")) {
        accountantCredenceDAO.updateOrgHAFAccountFlow_E1(delectCredenceInfoDTO
            .getSettNum());
      } else if (delectCredenceInfoDTO.getReserveB().equals("5")) {
        accountantCredenceDAO.updateOrgHAFAccountFlow_F1(delectCredenceInfoDTO
            .getSettNum());
      } else if(delectCredenceInfoDTO.getBizType().equals("23")) {
        accountantCredenceDAO.updateLoanFlowHead_callback(delectCredenceInfoDTO
            .getSettNum());
      } else {
        if (delectCredenceInfoDTO.getReserveC() != null
            && !"".equals(delectCredenceInfoDTO)) {
          String reserveC = delectCredenceInfoDTO.getReserveC();
          String[] settNum = reserveC.split(",");
          for (int i = 0; i < settNum.length; i++) {
            // ����PL202
            accountantCredenceDAO.updateLoanFlowHead(settNum[i]);
            // ����AA101
            accountantCredenceDAO.updateOrgHAFAccountFlow(settNum[i], delectCredenceInfoDTO.getReserveB());
          }
        } else {
          // ����PL202
          accountantCredenceDAO.updateLoanFlowHead(delectCredenceInfoDTO
              .getSettNum());
          // ����AA101
          accountantCredenceDAO.updateOrgHAFAccountFlow(delectCredenceInfoDTO
              .getSettNum(),delectCredenceInfoDTO.getReserveB());
        }
      }
    }
    if (delectCredenceInfoDTO.getBizType().equals("2")) {
      String str = "";
      if (delectCredenceInfoDTO.getReserveC() != null
          && !"".equals(delectCredenceInfoDTO)) {
        String reserveC = delectCredenceInfoDTO.getReserveC();
        String[] settNum = reserveC.split(",");
        for (int i = 0; i < settNum.length; i++) {
          str += "'" + settNum[i] + "',";
        }
        str = str.substring(0, str.lastIndexOf(","));
      } else {
        str = delectCredenceInfoDTO.getSettNum();
      }
      // wangshuang add ͬʱɾ��fn201_1�е�����
      accountantCredenceDAO.deleteFn201_1(str, bookId);
      // ��ҵ������Ϊ�ڲ�ת��ת����ʹ�����·���ɾ��ƾ֤
      // ɾ��FN201
      accountantCredenceDAO.delectCredenceInfo_reserveB_wsh(
          delectCredenceInfoDTO.getDocNum(), delectCredenceInfoDTO
              .getReserveB(), delectCredenceInfoDTO.getCredenceDate(), bookId);
      // �жϽ��㷽ʽ,��ɾ������ƾ֤��
      if (settleType == 1) {
        fnDocNumCancelDAO.insertDocNumCancel(delectCredenceInfoDTO.getDocNum(),
            delectCredenceInfoDTO.getReserveB(), delectCredenceInfoDTO
                .getCredenceDate().substring(0, 6), "0", bookId);
      } else {
        fnDocNumCancelDAO.insertDocNumCancel(delectCredenceInfoDTO.getDocNum(),
            "", delectCredenceInfoDTO.getCredenceDate().substring(0, 6), "0",
            bookId);
      }
    } else if (delectCredenceInfoDTO.getBizType().equals("21")) {// �����
      String reserveC = delectCredenceInfoDTO.getReserveC();
      String str = "";
      // ���ҵ������Ϊ����������·���ɾ��fn201������
      String[] settNum = reserveC.split(",");
      for (int i = 0; i < settNum.length; i++) {
        str += "'" + settNum[i] + "',";
      }
      str = str.substring(0, str.lastIndexOf(","));
      accountantCredenceDAO.deleteFn201_1(str, bookId);
      System.out.println(delectCredenceInfoDTO.getCredenceDate()
          + "======================>");
      List list = accountantCredenceDAO.queryCredenceInfoByReserveC(bookId,
          reserveC, delectCredenceInfoDTO.getCredenceDate());
      for (int i = 0; i < list.size(); i++) {
        DelectCredenceInfoDTO dto = (DelectCredenceInfoDTO) list.get(i);
        accountantCredenceDAO.delectCredenceInfo_wsh(dto.getDocNum(),
            delectCredenceInfoDTO.getOffice(), delectCredenceInfoDTO
                .getCredenceDate(), bookId);
        // �жϽ��㷽ʽ,��ɾ������ƾ֤��
        if (settleType == 1) {
          fnDocNumCancelDAO.insertDocNumCancel(dto.getDocNum(),
              delectCredenceInfoDTO.getOffice(), delectCredenceInfoDTO
                  .getCredenceDate().substring(0, 6), "0", bookId);
        } else {
          fnDocNumCancelDAO.insertDocNumCancel(dto.getDocNum(), "",
              delectCredenceInfoDTO.getCredenceDate().substring(0, 6), "0",
              bookId);
        }
      }
    } else {
      // ɾ��FN201
      String str = "";
      if (delectCredenceInfoDTO.getReserveC() != null
          && !"".equals(delectCredenceInfoDTO)) {
        String reserveC = delectCredenceInfoDTO.getReserveC();
        String[] settNum = reserveC.split(",");
        for (int i = 0; i < settNum.length; i++) {
          str += "'" + settNum[i] + "',";
        }
        str = str.substring(0, str.lastIndexOf(","));
      } else {
        str = delectCredenceInfoDTO.getSettNum();
      }
      if (delectCredenceInfoDTO.getBizType().equals("23")) {
        System.out.println("�����𻹴�ɾ��=================>");
        accountantCredenceDAO.delectCredenceInfo_reserveB_wsh(
            delectCredenceInfoDTO.getDocNum(), "23", delectCredenceInfoDTO.getCredenceDate(), bookId);
      } else {
        accountantCredenceDAO.delectCredenceInfo_wsh(delectCredenceInfoDTO
            .getDocNum(), delectCredenceInfoDTO.getOffice(),
            delectCredenceInfoDTO.getCredenceDate(), bookId);
      }
      // wangshuang add ͬʱɾ��fn201_1�е�����
      accountantCredenceDAO.deleteFn201_1(str, bookId);

      // �жϽ��㷽ʽ,��ɾ������ƾ֤��
      if (settleType == 1) {
        fnDocNumCancelDAO.insertDocNumCancel(delectCredenceInfoDTO.getDocNum(),
            delectCredenceInfoDTO.getOffice(), delectCredenceInfoDTO
                .getCredenceDate().substring(0, 6), "0", bookId);
      } else {
        fnDocNumCancelDAO.insertDocNumCancel(delectCredenceInfoDTO.getDocNum(),
            "", delectCredenceInfoDTO.getCredenceDate().substring(0, 6), "0",
            bookId);
      }

    }
    // ɾ��FN310
    fnBizActivityLogDAO.deleteFnBizActivityLog_wsh(delectCredenceInfoDTO
        .getDocNum(), delectCredenceInfoDTO.getCredenceDate(),
        delectCredenceInfoDTO.getOffice(), securityInfo);

    // ����fn311��
    FnOperateLog fnOperateLog = new FnOperateLog();
    fnOperateLog.setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
    fnOperateLog.setOpModel(String
        .valueOf(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCEMAINTAIN));
    fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_DELETE));
    fnOperateLog.setOpIp(securityInfo.getUserIp());
    fnOperateLog.setOpTime(new Date());
    fnOperateLog.setOperator(securityInfo.getUserName());
    fnOperateLog.setBookId(securityInfo.getBookId());
    fnOperateLogDAO.insert(fnOperateLog);

  }

  public void delectAllCredenceInfo(List countList, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    String bookId = securityInfo.getBookId();
    int settleType = securityInfo.getFnSettleType();
    for (int i = 0; i < countList.size(); i++) {
      CredencecheckShowListDTO credencecheckShowListDTO = (CredencecheckShowListDTO) countList
          .get(i);
      DelectCredenceInfoDTO delectCredenceInfoDTO = accountantCredenceDAO
          .queryDelectCredenceInfo(credencecheckShowListDTO.getCredenceId(),
              bookId);
      if (delectCredenceInfoDTO == null) {
        continue;
      }
      // �ж�ƾ֤״̬�Ƿ�Ϊ0��ȷ��״̬
      if (!delectCredenceInfoDTO.getCredenceSt().equals("0")) {
        throw new BusinessException("��¼�����ڻ�����ɾ����");
      }
      // �ж��Ƿ�Ϊ�����ת
      Object paraId = accountantCredenceDAO.isSettleIncAndDecInfo(bookId);
      if (delectCredenceInfoDTO.getSummay().equals(paraId.toString())) {
        // �жϳ���Ҫ���µĿ�Ŀ����
        List list = accountantCredenceDAO.isUpdateSettleIncAndDecInfo(bookId,
            delectCredenceInfoDTO.getDocNum());
        for (int j = 0; j < list.size(); j++) {
          String subject_code = (String) list.get(j);
          // ���summay���ڲ�ѯ�������ݣ�����ƾ֤�� FN201����������ͬ�Ŀ�Ŀ���롢���´�������״̬=1.�ѽ�תδ����Ϊ0�ļ�¼
          accountantCredenceDAO.updateSettleIncAndDecInfo(subject_code,
              delectCredenceInfoDTO.getOffice(), bookId);
        }
      } else {
        if(delectCredenceInfoDTO.getBizType().equals("23")) {
          accountantCredenceDAO.updateLoanFlowHead_callback(delectCredenceInfoDTO
              .getSettNum());
        } else if (delectCredenceInfoDTO.getReserveC() != null
            && !"".equals(delectCredenceInfoDTO)) {
          String reserveC = delectCredenceInfoDTO.getReserveC();
          String[] settNum = reserveC.split(",");
          for (int j = 0; j < settNum.length; j++) {
            // ����PL202
            accountantCredenceDAO.updateLoanFlowHead(settNum[j]);
            // ����AA101
            accountantCredenceDAO.updateOrgHAFAccountFlow(settNum[j], delectCredenceInfoDTO.getReserveB());
          }
        } else {
          // ����PL202
          accountantCredenceDAO.updateLoanFlowHead(delectCredenceInfoDTO
              .getSettNum());
          // ����AA101
          accountantCredenceDAO.updateOrgHAFAccountFlow(delectCredenceInfoDTO
              .getSettNum(), delectCredenceInfoDTO.getReserveB());
        }
      }
      if (delectCredenceInfoDTO.getBizType().equals("2")) {
        String str = "";
        if (delectCredenceInfoDTO.getReserveC() != null
            && !"".equals(delectCredenceInfoDTO)) {
          String reserveC = delectCredenceInfoDTO.getReserveC();
          String[] settNum = reserveC.split(",");
          for (int k = 0; k < settNum.length; k++) {
            str += "'" + settNum[k] + "',";
          }
          str = str.substring(0, str.lastIndexOf(","));
        } else {
          str = delectCredenceInfoDTO.getSettNum();
        }
        // wangshuang add ͬʱɾ��fn201_1�е�����
        accountantCredenceDAO.deleteFn201_1(str, bookId);
        // ��ҵ������Ϊ�ڲ�ת��ת����ʹ�����·���ɾ��ƾ֤
        // ɾ��FN201
        accountantCredenceDAO
            .delectCredenceInfo_reserveB_wsh(delectCredenceInfoDTO.getDocNum(),
                delectCredenceInfoDTO.getReserveB(), delectCredenceInfoDTO
                    .getCredenceDate(), bookId);
        // �жϺ����ϵ��������ƾ֤��
        if (settleType == 1) {
          fnDocNumCancelDAO.insertDocNumCancel(delectCredenceInfoDTO
              .getDocNum(), delectCredenceInfoDTO.getReserveB(),
              delectCredenceInfoDTO.getCredenceDate().substring(0, 6), "0",
              bookId);
        } else {
          fnDocNumCancelDAO.insertDocNumCancel(delectCredenceInfoDTO
              .getDocNum(), "", delectCredenceInfoDTO.getCredenceDate()
              .substring(0, 6), "0", bookId);
        }

      } else if (delectCredenceInfoDTO.getBizType().equals("21")) {// �����
        String reserveC = delectCredenceInfoDTO.getReserveC();
        String str = "";
        // ���ҵ������Ϊ����������·���ɾ��fn201������
        String[] settNum = reserveC.split(",");
        for (int j = 0; j < settNum.length; j++) {
          str += "'" + settNum[j] + "',";
        }
        str = str.substring(0, str.lastIndexOf(","));
        accountantCredenceDAO.deleteFn201_1(str, bookId);
        List list = accountantCredenceDAO.queryCredenceInfoByReserveC(bookId,
            reserveC, delectCredenceInfoDTO.getCredenceDate());
        for (int j = 0; j < list.size(); j++) {
          DelectCredenceInfoDTO dto = (DelectCredenceInfoDTO) list.get(j);
          accountantCredenceDAO.delectCredenceInfo_wsh(dto.getDocNum(),
              delectCredenceInfoDTO.getOffice(), delectCredenceInfoDTO
                  .getCredenceDate(), bookId);
          // �жϽ��㷽ʽ,��ɾ������ƾ֤��
          if (settleType == 1) {
            fnDocNumCancelDAO.insertDocNumCancel(dto.getDocNum(),
                delectCredenceInfoDTO.getOffice(), delectCredenceInfoDTO
                    .getCredenceDate().substring(0, 6), "0", bookId);
          } else {
            fnDocNumCancelDAO.insertDocNumCancel(dto.getDocNum(), "",
                delectCredenceInfoDTO.getCredenceDate().substring(0, 6), "0",
                bookId);
          }
        }
      } else {
        // ɾ��FN201
        String str = "";
        if (delectCredenceInfoDTO.getReserveC() != null
            && !"".equals(delectCredenceInfoDTO)) {
          String reserveC = delectCredenceInfoDTO.getReserveC();
          String[] settNum = reserveC.split(",");
          for (int j = 0; j < settNum.length; j++) {
            str += "'" + settNum[j] + "',";
          }
          str = str.substring(0, str.lastIndexOf(","));
        } else {
          str = delectCredenceInfoDTO.getSettNum();
        }
        if (delectCredenceInfoDTO.getBizType().equals("23")) {
          System.out.println("�����𻹴�ɾ��=================>");
          accountantCredenceDAO.delectCredenceInfo_reserveB_wsh(
              delectCredenceInfoDTO.getDocNum(), "23", delectCredenceInfoDTO.getCredenceDate(), bookId);
        } else {
          accountantCredenceDAO.delectCredenceInfo_wsh(delectCredenceInfoDTO
              .getDocNum(), delectCredenceInfoDTO.getOffice(),
              delectCredenceInfoDTO.getCredenceDate(), bookId);
        }
        // wangshuang add ͬʱɾ��fn201_1�е�����
        accountantCredenceDAO.deleteFn201_1(str, bookId);
        // �жϺ����ϵ��������ƾ֤��
        if (settleType == 1) {
          fnDocNumCancelDAO.insertDocNumCancel(delectCredenceInfoDTO
              .getDocNum(), delectCredenceInfoDTO.getOffice(),
              delectCredenceInfoDTO.getCredenceDate().substring(0, 6), "0",
              bookId);
        } else {
          fnDocNumCancelDAO.insertDocNumCancel(delectCredenceInfoDTO
              .getDocNum(), "", delectCredenceInfoDTO.getCredenceDate()
              .substring(0, 6), "0", bookId);
        }

      }
      // ɾ��FN310
      fnBizActivityLogDAO.deleteFnBizActivityLog_wsh(delectCredenceInfoDTO
          .getDocNum(), delectCredenceInfoDTO.getCredenceDate(),
          delectCredenceInfoDTO.getOffice(), securityInfo);
    }

    // ����fn311��
    FnOperateLog fnOperateLog = new FnOperateLog();
    fnOperateLog.setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
    fnOperateLog.setOpModel(String
        .valueOf(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCEMAINTAIN));
    fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_DELETE));
    fnOperateLog.setOpIp(securityInfo.getUserIp());
    fnOperateLog.setOpTime(new Date());
    fnOperateLog.setOperator(securityInfo.getUserName());
    fnOperateLog.setBookId(securityInfo.getBookId());
    fnOperateLogDAO.insert(fnOperateLog);
  }

  public Object[] findAccountantCredence(String docNum,
      SecurityInfo securityInfo, String credenceDate, String office)
      throws Exception {
    Object[] obj = new Object[4];
    String bookId = securityInfo.getBookId();
    List list = accountantCredenceDAO.queryCredencePopList(docNum, bookId,
        credenceDate, office);

    BigDecimal sumDebit = new BigDecimal(0.00);
    BigDecimal sumCredit = new BigDecimal(0.00);
    List templist = new ArrayList();
    for (int i = 0; i < list.size(); i++) {
      CredencePopListDTO credencePopListDTO = (CredencePopListDTO) list.get(i);
      sumCredit = sumCredit.add(credencePopListDTO.getCredit());
      sumDebit = sumDebit.add(credencePopListDTO.getDebit());
      credencePopListDTO.setCredit(credencePopListDTO.getCredit().multiply(
          new BigDecimal(100)).setScale(0));
      credencePopListDTO.setDebit(credencePopListDTO.getDebit().multiply(
          new BigDecimal(100)).setScale(0));
      String summay = bookParameterDAO
          .queryParamExplainByParaId(credencePopListDTO.getSummay());
      credencePopListDTO.setSummay(summay);
      credencePopListDTO.setSubjectCode(credencePopListDTO.getSubjectCode()
          + " " + credencePopListDTO.getSubjectName());
      templist.add(credencePopListDTO);
    }
    CredencePopInfoDTO credencePopInfoDTO = accountantCredenceDAO
        .queryCredencePopInfo(docNum, bookId, credenceDate, office);
    obj[0] = templist;
    obj[1] = credencePopInfoDTO;
    obj[2] = sumDebit.multiply(new BigDecimal(100)).setScale(0);
    obj[3] = sumCredit.multiply(new BigDecimal(100)).setScale(0);
    return obj;
  }

  public List findContinuumPrintList(Pagination pagination, String type,
      SecurityInfo securityInfo) throws Exception {
    List resultList = new ArrayList();
    CredencecheckFindDTO credencecheckFindDTO = (CredencecheckFindDTO) pagination
        .getQueryCriterions().get("credencecheckFindDTO");
    if (credencecheckFindDTO == null) {
      credencecheckFindDTO = new CredencecheckFindDTO();
    }
    credencecheckFindDTO.setButtonPromise1("0");
    credencecheckFindDTO.setButtonPromise2("0");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    // ��ѯ�б��г��ֵ�ƾ֤��
    List list = accountantCredenceDAO.queryCredencecheckList_FYF(type,
        credencecheckFindDTO, securityInfo, orderBy, order, start, pageSize,
        page);
    for (int i = 0; i < list.size(); i++) {
      Object[] obj = new Object[2];
      Object[] temp_obj = (Object[]) list.get(i);
      String credenceNum = temp_obj[0].toString();
      String credenceDate = "";
      String office = "";
      if (temp_obj[1] != null) {
        credenceDate = temp_obj[1].toString();
      }
      if (temp_obj[2] != null) {
        office = temp_obj[2].toString();
      }
      List temp_list = accountantCredenceDAO.queryCredencePopList(credenceNum,
          securityInfo.getBookId(), credenceDate, office);
      for (int j = 0; j < temp_list.size(); j++) {
        CredencePopListDTO credencePopListDTO = (CredencePopListDTO) temp_list
            .get(j);
        String summay = bookParameterDAO
            .queryParamExplainByParaId(credencePopListDTO.getSummay());
        credencePopListDTO.setSummay(summay);
      }
      CredencePopInfoDTO credencePopInfoDTO = accountantCredenceDAO
          .queryCredencePopInfo(credenceNum, securityInfo.getBookId(),
              credenceDate, office);
      // ת�����㷽ʽ
      String settType = bookParameterDAO.queryParamExplainByParaId(securityInfo
          .getBookId(), credencePopInfoDTO.getSettType());
      credencePopInfoDTO.setSettType(settType);
      // ת������
      credencePopInfoDTO.setMakebill(securityDAO
          .queryByUserid(credencePopInfoDTO.getMakebill()));
      credencePopInfoDTO.setCheckpsn(securityDAO
          .queryByUserid(credencePopInfoDTO.getCheckpsn()));

      obj[0] = temp_list;
      obj[1] = credencePopInfoDTO;
      resultList.add(obj);
    }
    return resultList;
  }

  // ��ѯƾ֤��˵��˵��
  public String findParamExplain(String credenceCharacter,
      SecurityInfo securityInfo) throws Exception {
    Object obj = accountantCredenceDAO.queryParamExplain(credenceCharacter,
        securityInfo.getBookId());
    return obj.toString();
  }

  // ----------------------------------------------------------------fuyf
  // ------------------------------------------------------���в���
  // ------------------------------------------------------���в���
  // -----------------------------------------------------------------liub

  /**
   * ȡ��ƾ֤���б�
   * 
   * @author ���� 2007-10-31 ��ѯfn102����paramExplain�ֶε����� ��ѯ������paramNum
   */
  public List findCredenceCharacterList(SecurityInfo securityInfo)
      throws Exception {
    List credenceCharacterList = null;
    credenceCharacterList = bookParameterDAO.getParamExplain("2", "",
        securityInfo);
    return credenceCharacterList;
  }

  /**
   * ȡ�ý��㷽ʽ�б�
   * 
   * @author ���� 2007-11-1 ��ѯfn102����paramExplain�ֶε����� ��ѯ������paramNum
   */
  public List findSettTypeList(SecurityInfo securityInfo) throws Exception {
    List settTypeList = null;
    settTypeList = bookParameterDAO.getParamExplain("3", "", securityInfo);
    return settTypeList;
  }

  /**
   * ȡ��ƾ֤¼��ժҪ�б�
   * 
   * @author ���� 2007-10-24 ��ѯfn102����paramExplain�ֶε����� ��ѯ������paramNum
   */
  public String findSummayList(String search, SecurityInfo securityInfo)
      throws Exception {
    List summrayList = null;
    summrayList = bookParameterDAO.findParamExplainByChinese("4", " 10",
        search, securityInfo);
    StringBuffer summrayBuf = new StringBuffer();
    String summrayShow = "";
    for (int i = 0; i < summrayList.size(); i++) {
      BookParameterDTO bookParameterDTO = (BookParameterDTO) summrayList.get(i);
      String summray = bookParameterDTO.getBookParameterName();
      summrayBuf.append(summray + "\n");
    }
    summrayShow = summrayBuf.toString();
    return summrayShow;
  }

  /**
   * ƾ֤¼�������ѡ���´�ȡ��id�����ļ�������
   * 
   * @author ���� 2007-11-1
   */
  public String getCredenceDate(String officeCode, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    String credenceDate = "";
    String credenceYearMonth = "";
    String credenceYear = "";
    credenceYearMonth = bookDAO.getUseYearmonth(securityInfo.getBookId());
    if (credenceYearMonth != null) {
      credenceYear = credenceYearMonth.substring(0, 4);
    } else {
      credenceYear = BusiTools.dateToString(new Date(), "yyyy");
    }
    credenceDate = accountantCredenceDAO.queryMaxCredenceDateByOffice(
        officeCode, securityInfo.getBookId());
    if (credenceDate == null) {
      credenceDate = credenceYear + "0101";
    }
    String fnBizDate = securityInfo.getUserInfo().getFbizDate();
    if(fnBizDate.substring(0, 6).equals(credenceDate.substring(0, 6))) {
      return credenceDate;
    }else{
      return fnBizDate;
    }
    
  }

  /**
   * ƾ֤¼�������ѡ���´�ȡ�ö�Ӧ��ƾ֤��
   * 
   * @author ���� 2007-11-1
   */
  public String getCredenceNum(String officeCode, String bizYearmonth,
      String credenceNumType, String bookId) throws Exception,
      BusinessException {
    String credenceNum = fnDocNumMaintainDAO.getFnDocNumdocNum_WL(officeCode,
        bizYearmonth, "0", bookId);
    return credenceNum;
  }

  /**
   * �ж��Ƿ���ĩ����Ŀ����
   * 
   * @author ���� 2007-10-30
   */
  public Object[] isSubjectCodeEnd(String subjectCode, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    Object[] obj = new Object[3];
    String subjectId = "";
    String subjectName = "";
    Integer subjectCodeCount = new Integer(0);
    subjectId = subjectDAO.is_CodeIn_WL(subjectCode, "0", securityInfo);
    if (subjectId != null && !subjectId.equals("")) {
      subjectName = subjectDAO.querySubjectNameBySubjectCode(subjectCode,
          securityInfo.getBookId());
      subjectCodeCount = subjectDAO.findSubjectrelationParentId(subjectCode,
          securityInfo);
    } else {
      subjectId = "";
    }
    obj[0] = subjectId;
    obj[1] = subjectName;
    obj[2] = subjectCodeCount;
    return obj;
  }

  /**
   * ƾ֤¼��������������ж�FN201�����Ƿ���ڴ˽���ŵļ�¼
   * 
   * @author ���� 2007-11-5
   */
  public String queryIsExistSettNum(String settNum, String bookId)
      throws Exception, BusinessException {
    String cueAlert = "";
    List list = accountantCredenceDAO.queryIsExistSettNum(settNum, bookId);
    if (list.size() != 0) {
      cueAlert = "������Ѵ��ڣ����������룡";
    }
    return cueAlert;
  }

  /**
   * �ж����ݿ����Ƿ���ڴ�ժҪ
   * 
   * @author ���� 2007-11-9
   */
  public boolean isExistSummay(String summay, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    boolean cueAlert = true;
    String paramId = bookParameterDAO.queryParamIdByParaExplain(summay,
        securityInfo);
    if (paramId == null) {
      cueAlert = false;
    }
    return cueAlert;
  }

  /**
   * �õ�ƾ֤¼��Ļ�ƿ�Ŀ�����Ӧ�Ŀ�Ŀ������
   * 
   * @author ���� 2007-11-23
   */
  public Object[] getBalanceDir(String subjectcode, String office,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String direction = "";
    BigDecimal value = new BigDecimal(0.00);
    value = accountantCredenceDAO.queryBanlanceDiff(subjectcode, office,
        securityInfo);
    if (value.compareTo(new BigDecimal(0.00)) > 0) {
      direction = "1";
    } else if (value.compareTo(new BigDecimal(0.00)) == 0) {
      direction = "0";
    } else if (value.compareTo(new BigDecimal(0.00)) < 0) {
      direction = "-1";

    }
    Object[] obj = new Object[2];
    obj[0] = value.abs();
    obj[1] = direction;
    return obj;
  }

  /**
   * ƾ֤¼���ж�����״̬
   * 
   * @author ���� 2007-11-26 ����bookid��ѯ����״̬
   */
  public String findBookSt(SecurityInfo securityInfo) throws Exception {
    String bookSt = "";
    try {
      bookSt = bookDAO.getBookSt(securityInfo.getBookId());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bookSt;
  }

  public Object[] findDelRowList(List list) throws Exception {
    Object[] obj = new Object[3];

    BigDecimal sumDebit = new BigDecimal(0.00);
    BigDecimal sumCredit = new BigDecimal(0.00);
    for (int i = 0; i < list.size(); i++) {
      CredencePopListDTO credencePopListDTO = (CredencePopListDTO) list.get(i);
      // �ж��������
      if (credencePopListDTO.getSummay().equals("XXXXX")) {
        credencePopListDTO.setSummay("");
      }
      if (credencePopListDTO.getFreeSummay().equals("XXXXX")) {
        credencePopListDTO.setFreeSummay("");
      }
      if (credencePopListDTO.getSubjectCode().equals("XXXXX")) {
        credencePopListDTO.setSubjectCode("");
      }
      sumCredit = sumCredit.add(credencePopListDTO.getCredit());
      sumDebit = sumDebit.add(credencePopListDTO.getDebit());
      credencePopListDTO.setCredit(credencePopListDTO.getCredit().multiply(
          new BigDecimal(100)).setScale(0));
      credencePopListDTO.setDebit(credencePopListDTO.getDebit().multiply(
          new BigDecimal(100)).setScale(0));
    }
    obj[0] = list;
    obj[1] = sumDebit.multiply(new BigDecimal(100)).setScale(0);
    obj[2] = sumCredit.multiply(new BigDecimal(100)).setScale(0);
    return obj;
  }

  // -----------------------------------------------------------------liub
  public List getCollBank() {
    List list = accountantCredenceDAO.getCollBank();
    return list;
  }

  /**
   * ��ѯ�����б�
   * 
   * @author yangg
   * @param credenceFillinTbFindDTO
   * @return List(CredenceFillinIncomeExpenseDTO)
   * @throws Exception
   */
  public List getIncomeList(CredenceFillinTbFindDTO credenceFillinTbFindDTO)
      throws Exception {
    List incomeList = null;
    List returnList = new ArrayList();
    try {
      incomeList = accountantCredenceDAO.getIncomeList(credenceFillinTbFindDTO);
      Iterator iterator = incomeList.iterator();
      Object[] obj = null;
      while (iterator.hasNext()) {
        CredenceFillinIncomeExpenseDTO dto = new CredenceFillinIncomeExpenseDTO();
        obj = (Object[]) iterator.next();
        if (obj[0] != null) {
          dto.setOrgId(obj[0].toString());
        }
        if (obj[1] != null) {
          dto.setOrgName(obj[1].toString());
        }
        if (obj[2] != null) {
          dto.setMoneyBank(accountantCredenceDAO
              .findCollBanknameByOrgInfoCollectionBankId(obj[2].toString()));
        }
        if (obj[3] != null) {
          dto.setDebit(obj[3].toString());
        }
        if (obj[4] != null) {
          dto.setCredit(obj[4].toString());
        }
        if (obj[5] != null) {
          dto.setInterest(obj[5].toString());
        }
        if (obj[6] != null) {
          dto.setSett_date(obj[6].toString());
        }
        if (obj[7] != null) {
          dto.setNote_num(obj[7].toString());
        }
        if (obj[8] != null) {
          dto.setBiz_type(BusiTools.getBusiValue_WL(obj[8].toString(),
              BusiConst.CLEARACCOUNTBUSINESSTYPE));
        }
        returnList.add(dto);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return returnList;
  }

  /**
   * ��ѯ֧���б�
   * 
   * @author yangg
   * @param credenceFillinTbFindDTO
   * @return List(CredenceFillinIncomeExpenseDTO)
   * @throws Exception
   */
  public List getExpenseList(CredenceFillinTbFindDTO credenceFillinTbFindDTO)
      throws Exception {
    List expenseList = null;
    List returnList = new ArrayList();
    try {
      expenseList = accountantCredenceDAO
          .getExpenseList(credenceFillinTbFindDTO);
      Iterator iterator = expenseList.iterator();
      Object[] obj = null;
      while (iterator.hasNext()) {
        CredenceFillinIncomeExpenseDTO dto = new CredenceFillinIncomeExpenseDTO();
        obj = (Object[]) iterator.next();
        if (obj[0] != null) {
          dto.setOrgId(obj[0].toString());
        }
        if (obj[1] != null) {
          dto.setOrgName(obj[1].toString());
        }
        if (obj[2] != null) {
          dto.setMoneyBank(accountantCredenceDAO
              .findCollBanknameByOrgInfoCollectionBankId(obj[2].toString()));
        }
        if (obj[3] != null) {
          dto.setDebit(obj[3].toString());
        }
        if (obj[4] != null) {
          dto.setCredit(obj[4].toString());
        }
        if (obj[5] != null) {
          dto.setInterest(obj[5].toString());
        }
        if (obj[6] != null) {
          dto.setSett_date(obj[6].toString());
        }
        if (obj[7] != null) {
          dto.setNote_num(obj[7].toString());
        }
        if (obj[8] != null) {
          dto.setBiz_type(BusiTools.getBusiValue_WL(obj[8].toString(),
              BusiConst.CLEARACCOUNTBUSINESSTYPE));
        }
        returnList.add(dto);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return returnList;
  }
}
