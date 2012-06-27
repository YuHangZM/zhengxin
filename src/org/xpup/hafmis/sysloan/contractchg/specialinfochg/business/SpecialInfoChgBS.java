package org.xpup.hafmis.sysloan.contractchg.specialinfochg.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerLoanInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanContractParaDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanContractPara;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.contractchg.specialinfochg.bsinterface.ISpecialInfoChgBS;
import org.xpup.hafmis.sysloan.contractchg.specialinfochg.dto.SpecialInfoChgDTO;
import org.xpup.security.common.domain.Userslogincollbank;


public class SpecialInfoChgBS implements ISpecialInfoChgBS{
  private BorrowerLoanInfoDAO borrowerLoanInfoDAO=null;
  private LoanContractParaDAO loanContractParaDAO=null;
  private PlOperateLogDAO plOperateLogDAO=null;
  /**
   * ������Ϣ���
   * @author ���ƽ
   * 2007-10-05
   * ���ݺ�ͬ��Ų�ѯҳ����������
   * ��ѯ������contractId
   */
  public SpecialInfoChgDTO findSpecialInfoChgInfo(String contractId,SecurityInfo securityInfo) throws Exception{
    SpecialInfoChgDTO specialInfoChgDTO=null;
    List list=null;
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
      specialInfoChgDTO=borrowerLoanInfoDAO.querySpecialInfoChgInfo(contractId,loanbankList1);
      if(specialInfoChgDTO!=null){
        specialInfoChgDTO.setTemp_loanMode(BusiTools.getBusiValue(Integer.parseInt(specialInfoChgDTO.getLoanMode().toString()),BusiConst.PLRECOVERTYPE));
      }else{
        throw new BusinessException("�ú�ͬ��Ų����ڻ���Ȩ���ڣ����������룡");
      }
      LoanContractPara loanContractPara=null;
      String paramType="B";
      list=loanContractParaDAO.queryParamByContractId(contractId, paramType);
      for (int i = 0; i < list.size(); i++) {
        loanContractPara=(LoanContractPara)list.get(i);
        char paramNum=loanContractPara.getParamNum().charAt(0);
        switch(paramNum){
          case '1':{
            specialInfoChgDTO.setAheadReturnAfter(loanContractPara.getParamValue());
            break;
          }
          case '2':{
            specialInfoChgDTO.setIsPartAheadReturn(loanContractPara.getParamValue());
            specialInfoChgDTO.setPartReturnMonthLT(loanContractPara.getParamExplain());
            break;
          }
          case '3':{
            specialInfoChgDTO.setIsAllReturn(loanContractPara.getParamValue());
            specialInfoChgDTO.setAllReturnMonthLT(loanContractPara.getParamExplain());
            break;
          }
          case '4':{
            specialInfoChgDTO.setLeastReturnMoney(new BigDecimal(loanContractPara.getParamExplain()));
            break;
          }
          case '5':{
            specialInfoChgDTO.setMostAheadReturnYearTimes(loanContractPara.getParamExplain());
            break;
          }
          case '6':{
            specialInfoChgDTO.setMostAheadReturnTimes(loanContractPara.getParamExplain());
            break;
          }
          case '7':{
            if(!loanContractPara.getParamValue().equals("3")){
              specialInfoChgDTO.setIsAccept("4");
              specialInfoChgDTO.setChargeMode(loanContractPara.getParamValue());
              if(loanContractPara.getParamValue().equals("1")){
                specialInfoChgDTO.setAheadReturnMoneyPercent(new BigDecimal(loanContractPara.getParamExplain()));
              }
              if(loanContractPara.getParamValue().equals("2")){
                specialInfoChgDTO.setMoney(new BigDecimal(loanContractPara.getParamExplain()));
              }
            }else{
              specialInfoChgDTO.setIsAccept("3");
            }
            break;
          }
        }
      }
    }catch(BusinessException bex){
      throw bex;
    }catch(Exception e){
      e.printStackTrace();
    }
    return specialInfoChgDTO;
  }
  /**
   * ������Ϣ���
   * @author ���ƽ
   * 2007-10-06
   * ȷ����ť
   */
  public void saveSpecialInfoChgInfo(SpecialInfoChgDTO specialInfoChgDTO,SecurityInfo securityInfo) throws Exception{
    String loanBankId=specialInfoChgDTO.getLoanBankId();
    String contractId=specialInfoChgDTO.getContractId();
    String paramType="B";
    //ɾ��pl004
    loanContractParaDAO.deleteLoanBankParaByContractId(contractId, paramType);
    //����pl004
    LoanContractPara loanContractPara=null;
    loanContractPara=new LoanContractPara();
    loanContractPara.setContractId(contractId);
    loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
    loanContractPara.setParamType("B");
    loanContractPara.setParamDescrip("1����ԭ���»����2����ʣ������3����ı�ʣ������");
    loanContractPara.setParamValue(specialInfoChgDTO.getAheadReturnAfter());
    loanContractPara.setParamNum("1");
    loanContractParaDAO.insert(loanContractPara);
    loanContractPara=new LoanContractPara();
    loanContractPara.setContractId(contractId);
    loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
    loanContractPara.setParamType("B");
    loanContractPara.setParamDescrip("������ǰ����1������2����");
    loanContractPara.setParamValue(specialInfoChgDTO.getIsPartAheadReturn());
    loanContractPara.setParamNum("2");
    if(specialInfoChgDTO.getIsPartAheadReturn().equals("1")){
      loanContractPara.setParamExplain(specialInfoChgDTO.getPartReturnMonthLT());
    }
    loanContractParaDAO.insert(loanContractPara);
    loanContractPara=new LoanContractPara();
    loanContractPara.setContractId(contractId);
    loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
    loanContractPara.setParamType("B");
    loanContractPara.setParamDescrip("һ���Խ��廹��1������2����");
    loanContractPara.setParamValue(specialInfoChgDTO.getIsAllReturn());
    loanContractPara.setParamNum("3");
    if(specialInfoChgDTO.getIsAllReturn().equals("1")){
      loanContractPara.setParamExplain(specialInfoChgDTO.getAllReturnMonthLT());
    }
    loanContractParaDAO.insert(loanContractPara);
    loanContractPara=new LoanContractPara();
    loanContractPara.setContractId(contractId);
    loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
    loanContractPara.setParamType("B");
    loanContractPara.setParamDescrip("1��ǰ������ͽ��");
    loanContractPara.setParamValue("1");
    loanContractPara.setParamNum("4");
    loanContractPara.setParamExplain(specialInfoChgDTO.getLeastReturnMoney().toString());
    loanContractParaDAO.insert(loanContractPara);
    loanContractPara=new LoanContractPara();
    loanContractPara.setContractId(contractId);
    loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
    loanContractPara.setParamType("B");
    loanContractPara.setParamDescrip("1��������������ǰ����");
    loanContractPara.setParamValue("1");
    loanContractPara.setParamNum("5");
    loanContractPara.setParamExplain(specialInfoChgDTO.getMostAheadReturnYearTimes());
    loanContractParaDAO.insert(loanContractPara);
    loanContractPara=new LoanContractPara();
    loanContractPara.setContractId(contractId);
    loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
    loanContractPara.setParamType("B");
    loanContractPara.setParamDescrip("1�������������������ǰ����");
    loanContractPara.setParamValue("1");
    loanContractPara.setParamNum("6");
    loanContractPara.setParamExplain(specialInfoChgDTO.getMostAheadReturnTimes());
    loanContractParaDAO.insert(loanContractPara);
    loanContractPara=new LoanContractPara();
    loanContractPara.setContractId(contractId);
    loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
    loanContractPara.setParamType("B");
    loanContractPara.setParamDescrip("1����ǰ�����2����3��");
    if(specialInfoChgDTO.getIsAccept().equals("3")){
      loanContractPara.setParamValue("3");
    }else{
      loanContractPara.setParamValue(specialInfoChgDTO.getChargeMode());
      if(specialInfoChgDTO.getChargeMode().equals("1")){
        loanContractPara.setParamExplain(specialInfoChgDTO.getAheadReturnMoneyPercent().toString());
      }
      if(specialInfoChgDTO.getChargeMode().equals("2")){
        loanContractPara.setParamExplain(specialInfoChgDTO.getMoney().toString());
      }
    }
    loanContractPara.setParamNum("7");
    loanContractParaDAO.insert(loanContractPara);
    //������־PL021
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_CONTRACTCHG_SPECIALINFOCHG));
    plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
    plOperateLog.setOpBizId(new BigDecimal(loanBankId));
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpTime(new Date());
    plOperateLog.setOperator(securityInfo.getUserName());
    plOperateLog.setContractId(contractId);
    plOperateLogDAO.insert(plOperateLog);
  }
  public void setBorrowerLoanInfoDAO(BorrowerLoanInfoDAO borrowerLoanInfoDAO) {
    this.borrowerLoanInfoDAO = borrowerLoanInfoDAO;
  }
  public void setLoanContractParaDAO(LoanContractParaDAO loanContractParaDAO) {
    this.loanContractParaDAO = loanContractParaDAO;
  }
  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }
}
