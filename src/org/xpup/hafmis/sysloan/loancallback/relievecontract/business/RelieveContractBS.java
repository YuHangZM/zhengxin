package org.xpup.hafmis.sysloan.loancallback.relievecontract.business;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.loancallback.relievecontract.bsinterface.IRelieveContractBS;
import org.xpup.hafmis.sysloan.loancallback.relievecontract.dto.RelieveContractPrintDTO;
import org.xpup.hafmis.sysloan.loancallback.relievecontract.dto.RelieveContractTaDTO;
import org.xpup.security.common.domain.Userslogincollbank;


public class RelieveContractBS implements IRelieveContractBS{
  private BorrowerAccDAO borrowerAccDAO=null;
  private PlOperateLogDAO plOperateLogDAO=null;
  /**
   * ��Ѻ��Ѻ�������
   * @author ���ƽ
   * 2007-9-21
   * ���ݴ����˺Ų�ѯ����ҳ�����������
   * ��ѯ������loadKouAcc
   */
  public RelieveContractTaDTO findRelieveContractTaInfo(String loadKouAcc,SecurityInfo securityInfo) throws Exception,BusinessException{
    // TODO Auto-generated method stub
    RelieveContractTaDTO relieveContractTaDTO=null;
    List loanbankList1 = null;
    try {
      // ȡ���û�Ȩ�޷ſ�����
      List loanbankList = securityInfo.getDkUserBankList();
      loanbankList1 = new ArrayList();
      Userslogincollbank bank = null;
      Iterator itr1 = loanbankList.iterator();
      while (itr1.hasNext()) {
        bank = (Userslogincollbank) itr1.next();
        loanbankList1.add(bank.getCollBankId());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    try{
      relieveContractTaDTO=borrowerAccDAO.queryRelieveContractTaInfo(loadKouAcc,loanbankList1);
      if(relieveContractTaDTO!=null){
        relieveContractTaDTO.setTemp_loanMode(BusiTools.getBusiValue(Integer.parseInt(relieveContractTaDTO.getLoanMode().toString()),BusiConst.PLRECOVERTYPE));
        relieveContractTaDTO.setTemp_cardKind(BusiTools.getBusiValue(Integer.parseInt(relieveContractTaDTO.getCardKind().toString()),BusiConst.DOCUMENTSSTATE));
      }else{
        throw new BusinessException("�ô����˺Ų����ڻ�״̬�����ѻ���");
      }
    }catch(BusinessException bex){
      throw bex;
    }catch(Exception e){
      e.printStackTrace();
    }
    return relieveContractTaDTO;
  }
  /**
   * ��Ѻ��Ѻ�������
   * @author ���ƽ
   * 2007-9-21
   * �����Ѻ��Ѻ���
   */
  public void saveRelieveContractTa(String contractId,SecurityInfo securityInfo) throws Exception{
    try{
      //���ȷ����ť��ʱ������Ѿ��ǽ��״̬�ģ��Ͳ����ٽ��
      int count=borrowerAccDAO.findRelieveContractByContractId(contractId);
      if(count>0){
        throw new BusinessException("�ô����˺��Ѿ�������������ٽ��");
      }
      //�޸�pl121
      borrowerAccDAO.updatePledgeContractStatus(contractId);
      //�޸�pl122
      borrowerAccDAO.updateImpawnContractStatus(contractId);
      // ������־PL021
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
      plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_LOANRECOVER_LIVING_DO));
      plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
      plOperateLog.setOpBizId(new BigDecimal(contractId));
      plOperateLog.setContractId(contractId);
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLogDAO.insert(plOperateLog);
    }catch(BusinessException bex){
      throw bex;
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  /**
   * ��Ѻ��Ѻ���ά��
   * @author ���ƽ
   * 2007-9-22
   * ��ѯ�б���Ϣ
   */
  public List findRelieveContractTbList(Pagination pagination,List loanbankList) throws Exception{
    List list=null;
    List countlist=null;
    String loanKouAcc="";
    String contractId="";
    String borrowerName="";
    String cardNum="";
    String loanBankId="";
    try{
      if(pagination.getQueryCriterions().get("loanKouAcc")!=null){
        loanKouAcc=(String) pagination.getQueryCriterions().get("loanKouAcc");
      }
      if(pagination.getQueryCriterions().get("contractId")!=null){
        contractId=(String) pagination.getQueryCriterions().get("contractId");
      }
      if(pagination.getQueryCriterions().get("borrowerName")!=null){
        borrowerName=(String) pagination.getQueryCriterions().get("borrowerName");
      }
      if(pagination.getQueryCriterions().get("cardNum")!=null){
        cardNum=(String) pagination.getQueryCriterions().get("cardNum");
      }
      if(pagination.getQueryCriterions().get("loanBankId")!=null){
        loanBankId=(String) pagination.getQueryCriterions().get("loanBankId");
      }
      String orderBy=(String) pagination.getOrderBy();;
      String order = (String) pagination.getOrderother(); 
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list=borrowerAccDAO.queryRelieveContractTbList(loanKouAcc, contractId, borrowerName, cardNum, loanBankId, orderBy, order, start, pageSize,page,loanbankList);
      countlist=borrowerAccDAO.queryRelieveContractTbListCount(loanKouAcc, contractId, borrowerName, cardNum, loanBankId,loanbankList);
      if(countlist.size()!=0){
        int count = countlist.size();
        pagination.setNrOfElements(count);
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return list;
  }
  /**
   * ��Ѻ��Ѻ���ά��
   * @author ���ƽ
   * 2007-9-22
   * ɾ����ť
   */
  public void deleteRelieveContractTb(String contractId,SecurityInfo securityInfo) throws Exception{
    try{
      //�޸�pl121
      borrowerAccDAO.updatePledgeContractStatusTb(contractId);
      //�޸�pl122
      borrowerAccDAO.updateImpawnContractStatusTb(contractId);
      // ������־PL021
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
      plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_LOANRECOVER_LIVING_MAINTAIN));
      plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
      plOperateLog.setOpBizId(new BigDecimal(contractId));
      plOperateLog.setContractId(contractId);
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLogDAO.insert(plOperateLog);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  /**
   * ��Ѻ��Ѻ���ά��
   * @author ���ƽ
   * 2007-9-24
   * ���ݺ�ͬ��Ų�ѯ����ӡ������
   * ��ѯ������contractId
   */
  public RelieveContractPrintDTO findRelieveContractTbPrintInfo(String contractId) throws Exception{
    // TODO Auto-generated method stub
    RelieveContractPrintDTO relieveContractPrintDTO=null;
    try{
      relieveContractPrintDTO=borrowerAccDAO.queryRelieveContractTbPrintInfo(contractId);
      if(relieveContractPrintDTO!=null){
        relieveContractPrintDTO.setTemp_loanMode(BusiTools.getBusiValue(Integer.parseInt(relieveContractPrintDTO.getLoanMode().toString()),BusiConst.PLRECOVERTYPE));
        relieveContractPrintDTO.setTemp_cardKind(BusiTools.getBusiValue(Integer.parseInt(relieveContractPrintDTO.getCardKind().toString()),BusiConst.DOCUMENTSSTATE));
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return relieveContractPrintDTO;
  }
  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }
  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }
}
