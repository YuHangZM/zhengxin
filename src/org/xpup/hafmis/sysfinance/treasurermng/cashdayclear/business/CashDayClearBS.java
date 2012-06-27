package org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.business;




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
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.common.dao.AccountantCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnBizActivityLogDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnDocNumCancelDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnDocNumMaintainDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnOperateLogDAO;
import org.xpup.hafmis.sysfinance.common.dao.TreasurerCredenceDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnBizActivityLog;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnDocNumCancel;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnOperateLog;
import org.xpup.hafmis.sysfinance.common.domain.entity.TreasurerCredence;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.bsinterface.ICashDayClearBS;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTaDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTbFindDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTbShowListDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTcFindDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTcShowListDTO;




public class CashDayClearBS implements ICashDayClearBS{
  private BookParameterDAO bookParameterDAO=null;
  private TreasurerCredenceDAO treasurerCredenceDAO=null;
  private FnDocNumMaintainDAO fnDocNumMaintainDAO=null;
  private FnOperateLogDAO fnOperateLogDAO=null;
  private FnBizActivityLogDAO fnBizActivityLogDAO=null;
  private AccountantCredenceDAO accountantCredenceDAO=null;
  private FnDocNumCancelDAO fnDocNumCancelDAO=null;
  private BookDAO bookDAO=null;
  private SecurityDAO securityDAO=null;
  /**
   * �ֽ��ռ���
   * @author ���ƽ
   * 2007-10-12
   * ��ѯfn102����paramExplain�ֶε�����
   * ��ѯ������paramNum
   */
  public Object[] findCredenceCharacterList(SecurityInfo securityInfo,String temp){
    Object[] obj=new Object[3];
    List credenceCharacterList=null;
    List summrayList=null;
    List settTypeList=null;
    try{
      summrayList=bookParameterDAO.getParamExplain("4","10", securityInfo);
      if(temp.equals("")){
        credenceCharacterList=bookParameterDAO.getParamExplain("2","", securityInfo);
        settTypeList=bookParameterDAO.getParamExplain("3","", securityInfo);
      }
      obj[0]=credenceCharacterList;
      obj[1]=summrayList;
      obj[2]=settTypeList;
    }catch(Exception e){
      e.printStackTrace();
    }
    return obj;
  }
  /**
   * �ֽ��ռ���
   * @author ���ƽ
   * 2007-10-12
   * ����CREDENCE_TYPE����FN210���иð��´��£�ID����¼��CREDENCE_DATE
   * ��ѯ������office
   */
  public String findCredenceDateByOffice(String office,String credenceType,SecurityInfo securityInfo){
    String credenceDate="";
    try{
      credenceDate=treasurerCredenceDAO.queryCredenceDateByOffice(office,credenceType,securityInfo);
    }catch(Exception e){
      e.printStackTrace();
    }
    return credenceDate;
  }
  /**
   * �ֽ��ռ���
   * @author ���ƽ
   * 2007-10-13
   * ����ҳ���ȷ����ť
   */
  public void saveCashDayClearTa(CashDayClearTaDTO cashDayClearTaDTO,String credenceType,SecurityInfo securityInfo) throws Exception{
    try{
      
      //�����������ڴ���bb201�����ڵ�һ���£���ʾ�����½�
      String temp_credenceDate1 = cashDayClearTaDTO.getCredenceDate();
      String temp_credenceDate2 = securityInfo.getUserInfo().getBizDate();
      String temp_yearMonth1 = temp_credenceDate1.substring(0, 6);
      String temp_yearMonth2 = temp_credenceDate2.substring(0, 6);
      if(Integer.parseInt(temp_yearMonth1) > Integer.parseInt(temp_yearMonth2)){
        throw new BusinessException("�����½ᣡ");
      }
      
      String temp_credenceDate3 = treasurerCredenceDAO.getFBizDate(securityInfo.getBookId(),securityInfo.getUserName(),credenceType);
      if(!temp_credenceDate3.equals("")){
        if(Integer.parseInt(temp_credenceDate1) < Integer.parseInt(temp_credenceDate3)){
          throw new BusinessException("��ƾ֤�����Ѽ���!");
        }
      }
      
      
      
      String credenceNum="";
      //����Ľ������FN210.SETT_NUM�Ƿ����
      List list=treasurerCredenceDAO.queryTreasurerCredenceBySettNum(cashDayClearTaDTO.getSettNum().trim(),cashDayClearTaDTO.getSubjectCode().trim(),securityInfo,"");
      if(list.size()>0){
        throw new BusinessException("�ý�����Ѵ��ڣ�");
      }else{
        //����fn210��
        TreasurerCredence treasurerCredence=new TreasurerCredence();
        treasurerCredence.setBookId(securityInfo.getBookId());
        treasurerCredence.setSubjectCode(cashDayClearTaDTO.getSubjectCode().trim());
        treasurerCredence.setDebit(cashDayClearTaDTO.getDebit());
        treasurerCredence.setCredit(cashDayClearTaDTO.getCredit());
        //�ж�ͳһ������߶�������
        int settleType = securityInfo.getFnSettleType();
        if(settleType==0){
          credenceNum=fnDocNumMaintainDAO.getFnDocNumdocNum(null, cashDayClearTaDTO.getCredenceDate().substring(0,6), "1", securityInfo.getBookId());
        }else{
          credenceNum=fnDocNumMaintainDAO.getFnDocNumdocNum(cashDayClearTaDTO.getOffice().trim(), cashDayClearTaDTO.getCredenceDate().substring(0,6), "1", securityInfo.getBookId());
        }
        if(credenceNum!=null)
        {
          treasurerCredence.setCredenceNum(credenceNum);
        }
        treasurerCredence.setSettType(cashDayClearTaDTO.getSettType().trim());
        treasurerCredence.setSettNum(cashDayClearTaDTO.getSettNum().trim());
        treasurerCredence.setSummray(cashDayClearTaDTO.getSummray().trim());
        treasurerCredence.setCredenceCharacter(cashDayClearTaDTO.getCredenceCharacter().trim());
        treasurerCredence.setOffice(cashDayClearTaDTO.getOffice().trim());
        treasurerCredence.setCredenceType(credenceType);
        treasurerCredence.setCredenceDate(cashDayClearTaDTO.getCredenceDate());
        treasurerCredence.setSettDate(cashDayClearTaDTO.getSettDate());
        treasurerCredence.setDopsn(cashDayClearTaDTO.getDopsn().trim());
        treasurerCredence.setMakebill(securityInfo.getUserName().trim());
        treasurerCredence.setCredenceSt("0");
        treasurerCredenceDAO.insert(treasurerCredence);
        //����fn311��
        FnOperateLog fnOperateLog=new FnOperateLog();
        fnOperateLog.setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
        if(credenceType.equals("0")){
          fnOperateLog.setOpModel(String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_CASHDAYCLEAR_CASHDAYCLEAR));
        }
        if(credenceType.equals("1")){
          fnOperateLog.setOpModel(String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_CASHDAYCLEAR_CASHDAYCLEARMAINTAIN));
        }
        fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
        fnOperateLog.setOpIp(securityInfo.getUserIp());
        fnOperateLog.setOpTime(new Date());
        fnOperateLog.setOperator(securityInfo.getUserName());
        fnOperateLog.setBookId(securityInfo.getBookId());
        fnOperateLogDAO.insert(fnOperateLog);
        //����fn310��
        FnBizActivityLog fnBizActivityLog=new FnBizActivityLog();
        fnBizActivityLog.setCredenceNum(credenceNum);
        fnBizActivityLog.setCredenceType("1");
        fnBizActivityLog.setCredenceDate(cashDayClearTaDTO.getCredenceDate());
        fnBizActivityLog.setOffice(cashDayClearTaDTO.getOffice().trim());
        fnBizActivityLog.setAction("0");
        fnBizActivityLog.setOpTime(new Date());
        fnBizActivityLog.setOperator(securityInfo.getUserName());
        fnBizActivityLog.setBookId(securityInfo.getBookId());
        fnBizActivityLogDAO.insert(fnBizActivityLog);
      }
    }catch(BusinessException bex){
      throw bex;
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  /**
   * �ֽ��ռ���--�Զ�ת��ҳ��
   * @author ���ƽ
   * 2007-10-15
   * ��ѯҳ������ʾ������
   */
  public Object[] findCashDayClearTbList(Pagination pagination,String credenceType,SecurityInfo securityInfo) throws Exception{
    Object obj[]=new Object[3];
    List resultList=new ArrayList();
    try{
      CashDayClearTbFindDTO cashDayClearTbFindDTO=(CashDayClearTbFindDTO)pagination.getQueryCriterions().get("cashDayClearTbFindDTO");
      if (cashDayClearTbFindDTO==null) {
        cashDayClearTbFindDTO=new CashDayClearTbFindDTO();
      }
      String orderBy=(String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother(); 
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize(); 
      int page = pagination.getPage();
      List list=accountantCredenceDAO.queryCashDayClearTbList(cashDayClearTbFindDTO,credenceType,securityInfo,orderBy,order,start,pageSize,page);
      for(int i=0;i<list.size();i++){
        CashDayClearTbShowListDTO cashDayClearTbShowListDTO=(CashDayClearTbShowListDTO)list.get(i);
        if((!cashDayClearTbShowListDTO.getCredenceCharacter().equals(""))&&(!cashDayClearTbShowListDTO.getCredenceNum().equals(""))){
          cashDayClearTbShowListDTO.setCredenceChaNum(bookParameterDAO.queryParamExplainByParaId(cashDayClearTbShowListDTO.getCredenceCharacter())+"-"+
              cashDayClearTbShowListDTO.getCredenceNum());
        }else if((!cashDayClearTbShowListDTO.getCredenceNum().equals(""))&&cashDayClearTbShowListDTO.getCredenceCharacter().equals("")){
          cashDayClearTbShowListDTO.setCredenceChaNum(cashDayClearTbShowListDTO.getCredenceNum());
        }
        cashDayClearTbShowListDTO.setTemp_credenceChaNum(cashDayClearTbShowListDTO.getCredenceCharacter()+"-"+
            cashDayClearTbShowListDTO.getCredenceNum());
        if(!cashDayClearTbShowListDTO.getSummary().equals("")){
          cashDayClearTbShowListDTO.setTemp_summary(bookParameterDAO.queryParamExplainByParaId(cashDayClearTbShowListDTO.getSummary()));
        }
        resultList.add(cashDayClearTbShowListDTO);
      }
      List countList=accountantCredenceDAO.queryCashDayClearTbListCount(cashDayClearTbFindDTO,credenceType, securityInfo);
      BigDecimal debitSum=new BigDecimal(0.00);
      BigDecimal creditSum=new BigDecimal(0.00);
      if(countList.size()>0){
        for(int i=0;i<countList.size();i++){
          CashDayClearTbShowListDTO cashDayClearTbShowListDTO=(CashDayClearTbShowListDTO)countList.get(i);
          debitSum=debitSum.add(cashDayClearTbShowListDTO.getDebit());
          creditSum=creditSum.add(cashDayClearTbShowListDTO.getCredit());
        }
      }
      int count=countList.size();
      pagination.setNrOfElements(count);
      cashDayClearTbFindDTO.setDebitSum(debitSum);
      cashDayClearTbFindDTO.setCreditSum(creditSum);
      obj[0]=resultList;
      obj[1]=cashDayClearTbFindDTO;
      obj[2]=countList;
    }catch(Exception e){
      e.printStackTrace();
    }
    return obj;
  }
  /**
   * �ֽ��ռ���--�Զ�ת��ҳ��
   * @author ���ƽ
   * 2007-10-16
   * ת�˰�ť
   */
  public void cashDayClearTaTransfers(String[] rowArray,String credenceType,SecurityInfo securityInfo) throws Exception{
    try{
      String credenceId="";
      List credenceIdList=new ArrayList();
      for(int i=0;i<rowArray.length;i++){
        credenceId=credenceId+rowArray[i]+",";
        credenceIdList.add(rowArray[i]);
      }
      credenceId.lastIndexOf(",");
      //�ж���ѡ��¼�Ƿ�FN201.CASH_ACC_ST =0
      List list=accountantCredenceDAO.queryAccountantCredenceByCredenceId(credenceIdList);
      if(list.size()>0){
        throw new BusinessException("�м�¼��ת�ˣ�ת��ʧ��!");
      }
      //���ô洢����
      //credenceTypeΪ0ʱ��˵�����ֽ��ռ��˵�ת�ˣ�Ϊ1ʱ��˵�������д���ռ��˵�ת��
      String makeBill=securityInfo.getUserName();
      String opIp=securityInfo.getUserIp();
      String opModel="";
      if(credenceType.equals("0")){
        opModel=String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_CASHDAYCLEAR_AUTOCASHDAYCLEAR);
      }
      if(credenceType.equals("1")){
        opModel=String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_BANKDAYCLEAR_AUTOBANKDAYCLEAR);
      }
      treasurerCredenceDAO.transfers(credenceId,credenceType,makeBill,opIp,opModel);
    }catch(BusinessException bex){
      throw bex;
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("ת��ʧ��!");
    }
  }
  /**
   * �ֽ��ռ���--ά��ҳ��
   * @author ���ƽ
   * 2007-10-18
   * ��ʾҳ�������
   */
  public Object[] findCashDayClearTcList(String credenceType,Pagination pagination,SecurityInfo securityInfo) throws Exception{
    Object obj[]=new Object[3];
    try{
      CashDayClearTcFindDTO cashDayClearTcFindDTO=(CashDayClearTcFindDTO)pagination.getQueryCriterions().get("cashDayClearTcFindDTO");
      if (cashDayClearTcFindDTO==null) {
        cashDayClearTcFindDTO=new CashDayClearTcFindDTO();
      }
      List officeList1 = null;
      try {
        // ȡ���û�Ȩ�ް��´�
        List officeList = securityInfo.getOfficeList();
        officeList1 = new ArrayList();
        OfficeDto officedto = null;
        Iterator itr1 = officeList.iterator();
        while (itr1.hasNext()) {
          officedto = (OfficeDto) itr1.next();
          officeList1.add(officedto.getOfficeCode());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      String orderBy=(String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother(); 
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize(); 
      int page = pagination.getPage();
      List list=treasurerCredenceDAO.queryCashDayClearTcList(cashDayClearTcFindDTO, officeList1,credenceType, securityInfo, orderBy, order, start, pageSize, page);
      List resultList=new ArrayList();
      if(list.size()>0){
        for(int i=0;i<list.size();i++){
          CashDayClearTcShowListDTO cashDayClearTcShowListDTO=(CashDayClearTcShowListDTO)list.get(i);
          cashDayClearTcShowListDTO.setCredenceSt(BusiTools.getBusiValue(Integer.parseInt(cashDayClearTcShowListDTO.getCredenceSt()), BusiConst.CREDSTATE));
          if((!cashDayClearTcShowListDTO.getCredenceCharacter().equals(""))&&(!cashDayClearTcShowListDTO.getCredenceNum().equals(""))){
            cashDayClearTcShowListDTO.setCredenceChaNum(bookParameterDAO.queryParamExplainByParaId(cashDayClearTcShowListDTO.getCredenceCharacter())+"-"+
                cashDayClearTcShowListDTO.getCredenceNum());
          }else if((!cashDayClearTcShowListDTO.getCredenceNum().equals(""))&&cashDayClearTcShowListDTO.getCredenceCharacter().equals("")){
            cashDayClearTcShowListDTO.setCredenceChaNum(cashDayClearTcShowListDTO.getCredenceNum());
          }
          if(!cashDayClearTcShowListDTO.getAcredenceId().equals("")){
            Object[] object=accountantCredenceDAO.queryByCredenceId(cashDayClearTcShowListDTO.getAcredenceId());
            cashDayClearTcShowListDTO.setTemp_credenceChaNum(cashDayClearTcShowListDTO.getCredenceCharacter()+"-"+
                object[0].toString());
            cashDayClearTcShowListDTO.setOffice(object[1].toString());
          }
          if(!cashDayClearTcShowListDTO.getSummary().equals("")){
            cashDayClearTcShowListDTO.setTemp_summary(bookParameterDAO.queryParamExplainByParaId(cashDayClearTcShowListDTO.getSummary()));
          }
          if(!cashDayClearTcShowListDTO.getSettType().equals("")){
            cashDayClearTcShowListDTO.setTemp_settType(bookParameterDAO.queryParamExplainByParaId(cashDayClearTcShowListDTO.getSettType()));
          }
          resultList.add(cashDayClearTcShowListDTO);
        }
      }
      List countList=treasurerCredenceDAO.queryCashDayClearTcListCount(cashDayClearTcFindDTO, officeList1,credenceType, securityInfo);
      BigDecimal debitSum=new BigDecimal(0.00);
      BigDecimal creditSum=new BigDecimal(0.00);
      if(countList.size()>0){
        for(int i=0;i<countList.size();i++){
          CashDayClearTcShowListDTO cashDayClearTcShowListDTO=(CashDayClearTcShowListDTO)countList.get(i);
          debitSum=debitSum.add(cashDayClearTcShowListDTO.getDebit());
          creditSum=creditSum.add(cashDayClearTcShowListDTO.getCredit());
        }
      }
      int count=countList.size();
      pagination.setNrOfElements(count);
      cashDayClearTcFindDTO.setDebitSum(debitSum);
      cashDayClearTcFindDTO.setCreditSum(creditSum);
      obj[0]=resultList;
      obj[1]=cashDayClearTcFindDTO;
      obj[2]=countList;
    }catch(Exception e){
      e.printStackTrace();
    }
    return obj;
  }
  /**
   * �ֽ��ռ���--ά��ҳ��
   * @author ���ƽ
   * 2007-10-18
   * ɾ����ť
   */
  public void deleteCashDayClearTcList(String credenceId,String credenceType,SecurityInfo securityInfo) throws Exception{
    try{
      //��ѯ������¼��fn210�����Ƿ����
      Integer id=treasurerCredenceDAO.queryTreasurerCredenceById(new Integer(credenceId));
      if(id==null){
        throw new BusinessException("������¼�Ѿ������ڣ�������ɾ����");
      }
      //�ж��Ƿ�CREDENCE_ST=0
      List checkList=treasurerCredenceDAO.queryTreasurerCredenceByCredenceSt(credenceId);
      if(checkList.size()==0){
        throw new BusinessException("�ü�¼�Ѽ��ˣ�������ɾ����");
      }
      //�жϼ�¼FN210.ACREDENCE_ID�Ƿ�Ϊ��
      String acredenceId="";
      String credenceNum="";
      String credenceDate="";
      String office="";
      TreasurerCredence treasurerCredence1=treasurerCredenceDAO.queryAcredenceIdByCredenceId(credenceId);
      if(treasurerCredence1!=null){
        acredenceId=treasurerCredence1.getAcredenceId();
        credenceNum=treasurerCredence1.getCredenceNum();
        credenceDate=treasurerCredence1.getCredenceDate();
        office=treasurerCredence1.getOffice();
        if(acredenceId!=null){
          if(credenceType.equals("0")){
            //����fn201���е�cash_acc_st�ֶ�Ϊ0
            treasurerCredenceDAO.updateAccountantCredence(acredenceId,"0");
          }
          if(credenceType.equals("1")){
            //����fn201���е�bank_acc_st�ֶ�Ϊ0
            treasurerCredenceDAO.updateBankAccountantCredence(acredenceId,"0");
          }
        }
      }
      //ɾ��fn210��¼
      treasurerCredenceDAO.deleteTreasurerCredence(credenceId);
      //����fn302��:��ɾ��ҵ���ƾ֤�Ų��뵽����ƾ֤�ű���
      FnDocNumCancel fnDocNumCancel=new FnDocNumCancel();
      fnDocNumCancel.setBookId(securityInfo.getBookId());
      fnDocNumCancel.setCancelcredenceid(credenceNum);
      fnDocNumCancel.setBizYearmonth(credenceDate.substring(0,6));
      fnDocNumCancel.setCredenceNumType("1");
      if(securityInfo.getFnSettleType()==0){
        fnDocNumCancel.setOffice("");
      }else{
        fnDocNumCancel.setOffice(office);
      }
      fnDocNumCancelDAO.insert(fnDocNumCancel);
      //����fn311��
      FnOperateLog fnOperateLog=new FnOperateLog();
      fnOperateLog.setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
      if(credenceType.equals("0")){
        fnOperateLog.setOpModel(String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_CASHDAYCLEAR_CASHDAYCLEARMAINTAIN));
      }
      if(credenceType.equals("1")){
        fnOperateLog.setOpModel(String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_BANKDAYCLEAR_BANKDAYCLEARMAINTAIN));
      }
      fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_DELETE));
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLogDAO.insert(fnOperateLog);
      //ɾ��fn310���е����ݣ�CREDENCE_NUM=��ѡ��¼��ƾ֤�� and CREDENCE_DATE=ƾ֤���� and  OFFICE=�������´� and CREDENCE_TYPE=1��
      fnBizActivityLogDAO.deleteFnBizActivityLog(credenceNum, credenceDate, office,securityInfo);
    }catch(BusinessException bex){
      throw bex;
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("ɾ��ʧ��!");
    }
  }
  /**
   * �ֽ��ռ���--ά��ҳ��
   * @author ���ƽ
   * 2007-11-29
   * ȫ��ɾ����ť
   */
  public void deleteCashDayClearTcListAll(List list,SecurityInfo securityInfo,String credenceType) throws Exception{
    try{
      for(int i=0;i<list.size();i++){
        CashDayClearTcShowListDTO cashDayClearTcShowListDTO = (CashDayClearTcShowListDTO)list.get(i);
        String credenceId=cashDayClearTcShowListDTO.getCredenceId();
        //��ѯ������¼��fn210�����Ƿ����
        Integer id=treasurerCredenceDAO.queryTreasurerCredenceById(new Integer(credenceId));
        if(id==null){
          throw new BusinessException("������¼�Ѿ������ڣ�������ɾ����");
        }
        //�ж��Ƿ�CREDENCE_ST=0
        List checkList=treasurerCredenceDAO.queryTreasurerCredenceByCredenceSt(credenceId);
        if(checkList.size()==0){
          throw new BusinessException("�ü�¼�Ѽ��ˣ�������ɾ����");
        }
        //�жϼ�¼FN210.ACREDENCE_ID�Ƿ�Ϊ��
        String acredenceId="";
        String credenceNum="";
        String credenceDate="";
        String office="";
        TreasurerCredence treasurerCredence1=treasurerCredenceDAO.queryAcredenceIdByCredenceId(credenceId);
        if(treasurerCredence1!=null){
          acredenceId=treasurerCredence1.getAcredenceId();
          credenceNum=treasurerCredence1.getCredenceNum();
          credenceDate=treasurerCredence1.getCredenceDate();
          office=treasurerCredence1.getOffice();
          if(acredenceId!=null){
            if(credenceType.equals("0")){
              //����fn201���е�cash_acc_st�ֶ�Ϊ0
              treasurerCredenceDAO.updateAccountantCredence(acredenceId,"0");
            }
            if(credenceType.equals("1")){
              //����fn201���е�bank_acc_st�ֶ�Ϊ0
              treasurerCredenceDAO.updateBankAccountantCredence(acredenceId,"0");
            }
          }
        }
        //ɾ��fn210��¼
        treasurerCredenceDAO.deleteTreasurerCredence(credenceId);
        //����fn302��:��ɾ��ҵ���ƾ֤�Ų��뵽����ƾ֤�ű���
        FnDocNumCancel fnDocNumCancel=new FnDocNumCancel();
        fnDocNumCancel.setBookId(securityInfo.getBookId());
        fnDocNumCancel.setCancelcredenceid(credenceNum);
        fnDocNumCancel.setBizYearmonth(credenceDate.substring(0,6));
        fnDocNumCancel.setCredenceNumType("1");
        if(securityInfo.getFnSettleType()==0){
          fnDocNumCancel.setOffice("");
        }else{
          fnDocNumCancel.setOffice(office);
        }
        fnDocNumCancelDAO.insert(fnDocNumCancel);
        //ɾ��fn310���е����ݣ�CREDENCE_NUM=��ѡ��¼��ƾ֤�� and CREDENCE_DATE=ƾ֤���� and  OFFICE=�������´� and CREDENCE_TYPE=1��
        fnBizActivityLogDAO.deleteFnBizActivityLog(credenceNum, credenceDate, office,securityInfo);
      }
      //����fn311��
      FnOperateLog fnOperateLog=new FnOperateLog();
      fnOperateLog.setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
      if(credenceType.equals("0")){
        fnOperateLog.setOpModel(String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_CASHDAYCLEAR_CASHDAYCLEARMAINTAIN));
      }
      if(credenceType.equals("1")){
        fnOperateLog.setOpModel(String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_BANKDAYCLEAR_BANKDAYCLEARMAINTAIN));
      }
      fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_DELETEALL));
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLogDAO.insert(fnOperateLog);
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("ȫ��ɾ��ʧ��!");
    }
  }
  /**
   * �ֽ��ռ���--ά��ҳ��--�޸�
   * @author ���ƽ
   * 2007-10-19
   * ����fn210.CREDENCE_ID��ѯ��Ӧ������
   */
  public CashDayClearTaDTO findModifyInfo(String credenceId,SecurityInfo securityInfo) throws Exception{
    CashDayClearTaDTO cashDayClearTaDTO=null;
    try{
      //��ѯ������¼��fn210�����Ƿ����
      cashDayClearTaDTO=treasurerCredenceDAO.queryTreasurerCredenceByCredenceId(credenceId);
      if(cashDayClearTaDTO==null){
        throw new BusinessException("������¼�Ѿ������ڣ��������޸ģ�");
      }
      //�ж��Ƿ�CREDENCE_ST=0
      List checkList=treasurerCredenceDAO.queryTreasurerCredenceByCredenceSt(credenceId);
      if(checkList.size()==0){
        throw new BusinessException("�ü�¼�Ѽ��ˣ��������޸ģ�");
      }
    }catch(BusinessException bex){
      throw bex;
    }catch(Exception e){
      e.printStackTrace();
    }
    

    return cashDayClearTaDTO;
  }
  /**
   * �ֽ��ռ���--����ҳ��--�޸�
   * @author ���ƽ
   * 2007-10-19
   */
  public void modifyInfo(CashDayClearTaDTO newCashDayClearTaDTO,CashDayClearTaDTO oldCashDayClearTaDTO,String credenceType,SecurityInfo securityInfo)throws Exception{
    try{
      
      //�����������ڴ���bb201�����ڵ�һ���£���ʾ�����½�
      String temp_credenceDate1 = newCashDayClearTaDTO.getCredenceDate();
      String temp_credenceDate2 = securityInfo.getUserInfo().getBizDate();
      String temp_yearMonth1 = temp_credenceDate1.substring(0, 6);
      String temp_yearMonth2 = temp_credenceDate2.substring(0, 6);
      if(Integer.parseInt(temp_yearMonth1) > Integer.parseInt(temp_yearMonth2)){
        throw new BusinessException("�����½ᣡ");
      }
      
      String temp_credenceDate3 = treasurerCredenceDAO.getFBizDate(securityInfo.getBookId(),securityInfo.getUserName(),credenceType);
      if(!temp_credenceDate3.equals("")){
        if(Integer.parseInt(temp_credenceDate1) < Integer.parseInt(temp_credenceDate3)){
          throw new BusinessException("��ƾ֤�����Ѽ���!");
        }
      }
      
      String credenceNum="";
      //����Ľ������FN210.SETT_NUM�Ƿ����
      List list=treasurerCredenceDAO.queryTreasurerCredenceBySettNum(newCashDayClearTaDTO.getSettNum().trim(),newCashDayClearTaDTO.getSubjectCode().trim(),securityInfo,oldCashDayClearTaDTO.getCredenceId());
      if(list.size()>0){
        throw new BusinessException("�ý�����Ѵ��ڣ�");
      }
      //ɾ��fn210��¼
      treasurerCredenceDAO.deleteTreasurerCredence(oldCashDayClearTaDTO.getCredenceId());
      //ɾ��fn310���е����ݣ�CREDENCE_NUM=��ѡ��¼��ƾ֤�� and CREDENCE_DATE=ƾ֤���� and  OFFICE=�������´���
      fnBizActivityLogDAO.deleteFnBizActivityLog(oldCashDayClearTaDTO.getCredenceNum(), newCashDayClearTaDTO.getCredenceDate(), oldCashDayClearTaDTO.getOffice(),securityInfo);
      int settleType = securityInfo.getFnSettleType();
      if(settleType==1){
        fnDocNumCancelDAO.insertDocNumCancel(oldCashDayClearTaDTO.getCredenceNum(),
            oldCashDayClearTaDTO.getOffice(), oldCashDayClearTaDTO
                .getCredenceDate().substring(0, 6), "1",securityInfo.getBookId());
      }else{
        fnDocNumCancelDAO.insertDocNumCancel(oldCashDayClearTaDTO.getCredenceNum(),
            "", oldCashDayClearTaDTO
                .getCredenceDate().substring(0, 6), "1", securityInfo.getBookId()); 
      }
      
      //����fn210��
      TreasurerCredence treasurerCredence=new TreasurerCredence();
      treasurerCredence.setBookId(securityInfo.getBookId());
      treasurerCredence.setSubjectCode(newCashDayClearTaDTO.getSubjectCode().trim());
      treasurerCredence.setDebit(newCashDayClearTaDTO.getDebit());
      treasurerCredence.setCredit(newCashDayClearTaDTO.getCredit());
      credenceNum=fnDocNumMaintainDAO.getFnDocNumdocNum(oldCashDayClearTaDTO.getOffice(), newCashDayClearTaDTO.getCredenceDate().substring(0,6), "1", securityInfo.getBookId());
      if(credenceNum!=null)
      {
        treasurerCredence.setCredenceNum(credenceNum);
      }
      treasurerCredence.setSettType(newCashDayClearTaDTO.getSettType().trim());
      treasurerCredence.setSettNum(newCashDayClearTaDTO.getSettNum().trim());
      treasurerCredence.setSummray(newCashDayClearTaDTO.getSummray().trim());
      treasurerCredence.setCredenceCharacter(newCashDayClearTaDTO.getCredenceCharacter().trim());
      treasurerCredence.setOffice(oldCashDayClearTaDTO.getOffice());
      treasurerCredence.setCredenceType(credenceType);
      treasurerCredence.setCredenceDate(newCashDayClearTaDTO.getCredenceDate());
      treasurerCredence.setSettDate(newCashDayClearTaDTO.getSettDate());
      treasurerCredence.setDopsn(newCashDayClearTaDTO.getDopsn().trim());
      treasurerCredence.setMakebill(securityInfo.getUserName());
      treasurerCredence.setCredenceSt("0");
      treasurerCredence.setAcredenceId(oldCashDayClearTaDTO.getAcredenceId());
      treasurerCredenceDAO.insert(treasurerCredence);
      //����fn311��
      FnOperateLog fnOperateLog=new FnOperateLog();
      fnOperateLog.setOpSys(String.valueOf(BusiLogConst.OP_SYSTEM_TYPE_FINANCE));
      if(credenceType.equals("0")){
        fnOperateLog.setOpModel(String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_CASHDAYCLEAR_CASHDAYCLEAR));
      }
      if(credenceType.equals("1")){
        fnOperateLog.setOpModel(String.valueOf(BusiLogConst.FN_OP_TREASURERMNG_CASHDAYCLEAR_CASHDAYCLEARMAINTAIN));
      }
      fnOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
      fnOperateLog.setOpIp(securityInfo.getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserName());
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLogDAO.insert(fnOperateLog);
      //����fn310��
      FnBizActivityLog fnBizActivityLog=new FnBizActivityLog();
      fnBizActivityLog.setCredenceNum(credenceNum);
      fnBizActivityLog.setCredenceType("1");
      fnBizActivityLog.setCredenceDate(newCashDayClearTaDTO.getCredenceDate());
      fnBizActivityLog.setOffice(oldCashDayClearTaDTO.getOffice());
      fnBizActivityLog.setAction("0");
      fnBizActivityLog.setOpTime(new Date());
      fnBizActivityLog.setOperator(securityInfo.getUserName());
      fnBizActivityLog.setBookId(securityInfo.getBookId());
      fnBizActivityLogDAO.insert(fnBizActivityLog);
    }catch(BusinessException bex){
      throw bex;
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  /**
   * �ֽ��ռ���--ά��ҳ��--��ӡ
   * @author ���ƽ
   * 2007-10-19
   */
  public List findCashDayClearTcPrintList(Pagination pagination,String credenceType,SecurityInfo securityInfo) throws Exception{
    List resultList=new ArrayList();
    try{
      CashDayClearTcFindDTO cashDayClearTcFindDTO=(CashDayClearTcFindDTO)pagination.getQueryCriterions().get("cashDayClearTcFindDTO");
      if (cashDayClearTcFindDTO==null) {
        cashDayClearTcFindDTO=new CashDayClearTcFindDTO();
      }
      List officeList1 = null;
      try {
        // ȡ���û�Ȩ�ް��´�
        List officeList = securityInfo.getOfficeList();
        officeList1 = new ArrayList();
        OfficeDto officedto = null;
        Iterator itr1 = officeList.iterator();
        while (itr1.hasNext()) {
          officedto = (OfficeDto) itr1.next();
          officeList1.add(officedto.getOfficeCode());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      List list=treasurerCredenceDAO.queryCashDayClearTcListPrint(cashDayClearTcFindDTO, officeList1,credenceType,securityInfo);
      if(list.size()>0){
        for(int i=0;i<list.size();i++){
          CashDayClearTcShowListDTO cashDayClearTcShowListDTO=(CashDayClearTcShowListDTO)list.get(i);
          cashDayClearTcShowListDTO.setCredenceSt(BusiTools.getBusiValue(Integer.parseInt(cashDayClearTcShowListDTO.getCredenceSt()), BusiConst.CREDSTATE));
          if((!cashDayClearTcShowListDTO.getCredenceCharacter().equals(""))&&(!cashDayClearTcShowListDTO.getCredenceNum().equals(""))){
            cashDayClearTcShowListDTO.setCredenceChaNum(bookParameterDAO.queryParamExplainByParaId(cashDayClearTcShowListDTO.getCredenceCharacter())+
                cashDayClearTcShowListDTO.getCredenceNum());
          }else if((!cashDayClearTcShowListDTO.getCredenceNum().equals(""))&&cashDayClearTcShowListDTO.getCredenceCharacter().equals("")){
            cashDayClearTcShowListDTO.setCredenceChaNum(cashDayClearTcShowListDTO.getCredenceNum());
          }
          if(!cashDayClearTcShowListDTO.getSummary().equals("")){
            cashDayClearTcShowListDTO.setTemp_summary(bookParameterDAO.queryParamExplainByParaId(cashDayClearTcShowListDTO.getSummary()));
          }
          if(!cashDayClearTcShowListDTO.getSettType().equals("")){
            cashDayClearTcShowListDTO.setTemp_settType(bookParameterDAO.queryParamExplainByParaId(cashDayClearTcShowListDTO.getSettType()));
          }
          if(!cashDayClearTcShowListDTO.getMakebill().equals("")){
            cashDayClearTcShowListDTO.setMakebill(securityDAO.queryByUserid(cashDayClearTcShowListDTO.getMakebill()));
          }
          resultList.add(cashDayClearTcShowListDTO);
        }
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return resultList;
  }
  public List findCashDayClearTcPrintList_wsh(Pagination pagination,String credenceType,SecurityInfo securityInfo) throws Exception{
    List resultList=new ArrayList();
    try{
      CashDayClearTcFindDTO cashDayClearTcFindDTO=(CashDayClearTcFindDTO)pagination.getQueryCriterions().get("cashDayClearTcFindDTO");
      if (cashDayClearTcFindDTO==null) {
        cashDayClearTcFindDTO=new CashDayClearTcFindDTO();
      }
      List officeList1 = null;
      try {
        // ȡ���û�Ȩ�ް��´�
        List officeList = securityInfo.getOfficeList();
        officeList1 = new ArrayList();
        OfficeDto officedto = null;
        Iterator itr1 = officeList.iterator();
        while (itr1.hasNext()) {
          officedto = (OfficeDto) itr1.next();
          officeList1.add(officedto.getOfficeCode());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      List list=treasurerCredenceDAO.queryCashDayClearTcListPrint_wsh(cashDayClearTcFindDTO, officeList1,credenceType,securityInfo);
      if(list.size()>0){
        for(int i=0;i<list.size();i++){
          CashDayClearTcShowListDTO cashDayClearTcShowListDTO=(CashDayClearTcShowListDTO)list.get(i);
          cashDayClearTcShowListDTO.setCredenceSt(BusiTools.getBusiValue(Integer.parseInt(cashDayClearTcShowListDTO.getCredenceSt()), BusiConst.CREDSTATE));
          if((!cashDayClearTcShowListDTO.getCredenceCharacter().equals(""))&&(!cashDayClearTcShowListDTO.getCredenceNum().equals(""))){
            cashDayClearTcShowListDTO.setCredenceChaNum(bookParameterDAO.queryParamExplainByParaId(cashDayClearTcShowListDTO.getCredenceCharacter())+
                cashDayClearTcShowListDTO.getCredenceNum());
          }else if((!cashDayClearTcShowListDTO.getCredenceNum().equals(""))&&cashDayClearTcShowListDTO.getCredenceCharacter().equals("")){
            cashDayClearTcShowListDTO.setCredenceChaNum(cashDayClearTcShowListDTO.getCredenceNum());
          }
          if(!cashDayClearTcShowListDTO.getSummary().equals("")){
            cashDayClearTcShowListDTO.setTemp_summary(bookParameterDAO.queryParamExplainByParaId(cashDayClearTcShowListDTO.getSummary()));
          }
          if(!cashDayClearTcShowListDTO.getSettType().equals("")){
            cashDayClearTcShowListDTO.setTemp_settType(bookParameterDAO.queryParamExplainByParaId(cashDayClearTcShowListDTO.getSettType()));
          }
          if(!cashDayClearTcShowListDTO.getMakebill().equals("")){
            cashDayClearTcShowListDTO.setMakebill(securityDAO.queryByUserid(cashDayClearTcShowListDTO.getMakebill()));
          }
          resultList.add(cashDayClearTcShowListDTO);
        }
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return resultList;
  }
  /**
   * �ֽ��ռ���--����ҳ��
   * @author ���ƽ
   * 2007-11-26
   * ����bookid��ѯ����״̬
   */
  public String findBookSt(SecurityInfo securityInfo) throws Exception{
    String bookSt="";
    try{
      bookSt=bookDAO.getBookSt(securityInfo.getBookId());
    }catch(Exception e){
      e.printStackTrace();
    }
    return bookSt;
  }
  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }
  public void setTreasurerCredenceDAO(TreasurerCredenceDAO treasurerCredenceDAO) {
    this.treasurerCredenceDAO = treasurerCredenceDAO;
  }
  public void setFnDocNumMaintainDAO(FnDocNumMaintainDAO fnDocNumMaintainDAO) {
    this.fnDocNumMaintainDAO = fnDocNumMaintainDAO;
  }
  public void setFnOperateLogDAO(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }
  public void setFnBizActivityLogDAO(FnBizActivityLogDAO fnBizActivityLogDAO) {
    this.fnBizActivityLogDAO = fnBizActivityLogDAO;
  }
  public void setAccountantCredenceDAO(AccountantCredenceDAO accountantCredenceDAO) {
    this.accountantCredenceDAO = accountantCredenceDAO;
  }
  public void setFnDocNumCancelDAO(FnDocNumCancelDAO fnDocNumCancelDAO) {
    this.fnDocNumCancelDAO = fnDocNumCancelDAO;
  }
  public void setBookDAO(BookDAO bookDAO) {
    this.bookDAO = bookDAO;
  }
  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }
}
