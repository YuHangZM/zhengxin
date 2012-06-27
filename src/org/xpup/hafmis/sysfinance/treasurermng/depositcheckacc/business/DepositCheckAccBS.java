package org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.common.dao.BankCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.TreasurerCredenceDAO;
import org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.bsinterface.IDepositCheckAccBS;
import org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.dto.DepositCheckAccBcaDTO;
import org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.dto.DepositCheckAccBdcDTO;
import org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.dto.DepositCheckAccWindowBaseDTO;
import org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.dto.DepositCheckAccWindowDTO;


public class DepositCheckAccBS implements IDepositCheckAccBS{
  private TreasurerCredenceDAO treasurerCredenceDAO=null; 
  private BankCredenceDAO bankCredenceDAO=null;
  private BookParameterDAO bookParameterDAO=null;
  /**
   * ���д����˵�
   * @author ���ƽ
   * 2007-10-30
   * ��ѯ�б���Ϣ
   */
  public Object[] findDepositCheckAccList(Pagination pagination,SecurityInfo securityInfo) throws Exception{
    Object[] obj=new Object[2];
    String settDateSt="";
    String settDateEnd="";
    String subjectCode="";
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
    try{
      if(pagination.getQueryCriterions().get("settDateSt")!=null){
        settDateSt=(String) pagination.getQueryCriterions().get("settDateSt");
      }
      if(pagination.getQueryCriterions().get("settDateEnd")!=null){
        settDateEnd=(String) pagination.getQueryCriterions().get("settDateEnd");
      }
      if(pagination.getQueryCriterions().get("subjectCode")!=null){
        subjectCode=(String) pagination.getQueryCriterions().get("subjectCode");
      }
      List bdcList=treasurerCredenceDAO.queryDepositCheckAccBdcList(settDateSt, settDateEnd, subjectCode, officeList1, securityInfo);
      List bcaList=bankCredenceDAO.queryDepositCheckAccBcaList(settDateSt, settDateEnd, subjectCode, officeList1, securityInfo);
      //�жϲ�ѯ��������list���ͬ��Ŀ���롢��ͬ����ŵļ�¼�Ƿ�FN210.DEBIT = FN211.CREDIT and FN210.CREDIT = FN211.DEBIT
      for(int i=0;i<bdcList.size();i++){
        DepositCheckAccBdcDTO depositCheckAccBdcDTO=(DepositCheckAccBdcDTO)bdcList.get(i);
        if(!depositCheckAccBdcDTO.getSettType().equals("")){
          depositCheckAccBdcDTO.setTemp_settType(bookParameterDAO.queryParamExplainByParaId(depositCheckAccBdcDTO.getSettType()));
        }
        if((!depositCheckAccBdcDTO.getCredenceCharacter().equals(""))&&(!depositCheckAccBdcDTO.getCredenceNum().equals(""))){
          depositCheckAccBdcDTO.setCredenceChaNum(bookParameterDAO.queryParamExplainByParaId(depositCheckAccBdcDTO.getCredenceCharacter())+"-"+
              depositCheckAccBdcDTO.getCredenceNum());
        }else if((!depositCheckAccBdcDTO.getCredenceNum().equals(""))&&depositCheckAccBdcDTO.getCredenceCharacter().equals("")){
          depositCheckAccBdcDTO.setCredenceChaNum(depositCheckAccBdcDTO.getCredenceNum());
        }
        for(int j=0;j<bcaList.size();j++){
          DepositCheckAccBcaDTO depositCheckAccBcaDTO=(DepositCheckAccBcaDTO)bcaList.get(j);
          if(!depositCheckAccBcaDTO.getSettType().equals("")){
            depositCheckAccBcaDTO.setTemp_settType(bookParameterDAO.queryParamExplainByParaId(depositCheckAccBcaDTO.getSettType()));
          }
          if(depositCheckAccBdcDTO.getSubjectCode().equals(depositCheckAccBcaDTO.getSubjectCode())&&
              depositCheckAccBdcDTO.getSettNum().equals(depositCheckAccBcaDTO.getSettNum())){
            if(depositCheckAccBdcDTO.getDebit().equals(depositCheckAccBcaDTO.getCredit())&&
                depositCheckAccBdcDTO.getCredit().equals(depositCheckAccBcaDTO.getDebit())){
              depositCheckAccBdcDTO.setType("1");
              depositCheckAccBcaDTO.setType("1");
            }
          }
        }
      }
      obj[0]=bdcList;
      obj[1]=bcaList;
    }catch(Exception e){
      e.printStackTrace();
    }
    return obj;
  }
  /**
   * ���д����˵�--���д�������ڱ�window
   * @author ���ƽ
   * 2007-11-1
   * ��ѯҳ����Ҫ��ʾ����Ϣ
   */
  public Object[] findDepositCheckAccWindowInfo(List bdcList,List bcaList,Pagination pagination,SecurityInfo securityInfo) throws Exception{
    Object[] obj=new Object[5];
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
    try{
      List bdcIdList=new ArrayList();
      List bcaIdList=new ArrayList();
      for(int i=0;i<bdcList.size();i++){
        DepositCheckAccBdcDTO depositCheckAccBdcDTO=(DepositCheckAccBdcDTO)bdcList.get(i);
        if(depositCheckAccBdcDTO.getType().equals("")){
          bdcIdList.add(depositCheckAccBdcDTO.getCredenceId());
        }
      }
      for(int i=0;i<bcaList.size();i++){
        DepositCheckAccBcaDTO depositCheckAccBcaDTO=(DepositCheckAccBcaDTO)bcaList.get(i);
        if(depositCheckAccBcaDTO.getType().equals("")){
          bcaIdList.add(depositCheckAccBcaDTO.getCredenceId());
        }
      }
      
      List bankList=new ArrayList();//������������δ��
      List officeList=new ArrayList();//������������δ��
      List bankOutList=new ArrayList();//�����Ѹ�����δ��
      List officeOutList=new ArrayList();//�����Ѹ�����δ��
      
      List baseList=treasurerCredenceDAO.queryDepositCheckAccWindowBaseList(bdcIdList, bcaIdList);
      if(baseList.size()>0){
        for(int i=0;i<baseList.size();i++){
          DepositCheckAccWindowBaseDTO depositCheckAccWindowBaseDTO=(DepositCheckAccWindowBaseDTO)baseList.get(i);
          if(depositCheckAccWindowBaseDTO.getType().equals("1")){
            officeList.add(depositCheckAccWindowBaseDTO);
          }else if(depositCheckAccWindowBaseDTO.getType().equals("2")){
            officeOutList.add(depositCheckAccWindowBaseDTO);
          }else if(depositCheckAccWindowBaseDTO.getType().equals("3")){
            bankOutList.add(depositCheckAccWindowBaseDTO);
          }else if(depositCheckAccWindowBaseDTO.getType().equals("4")){
            bankList.add(depositCheckAccWindowBaseDTO);
          }
        }
      }
      BigDecimal bdcBalanceSum=new BigDecimal(0.00);//�����ռ������
      BigDecimal bcaBalanceSum=new BigDecimal(0.00);//���ж��˵����
      BigDecimal bankMoneySum=new BigDecimal(0.00);//������������δ�ս���ܼ�
      BigDecimal officeMoneySum=new BigDecimal(0.00);//������������δ�ս���ܼ�
      BigDecimal bankOutMoneySum=new BigDecimal(0.00);//�����Ѹ�����δ������ܼ�
      BigDecimal officeOutMoneySum=new BigDecimal(0.00);//�����Ѹ�����δ������ܼ�
      BigDecimal adjustOfficeBalance=new BigDecimal(0.00);//���ں������ģ�
      BigDecimal adjustBankBalance=new BigDecimal(0.00);//���ں������У�
      
      for(int i=0;i<bankList.size();i++){
        DepositCheckAccWindowBaseDTO depositCheckAccWindowBaseDTO=(DepositCheckAccWindowBaseDTO)bankList.get(i);
        bankMoneySum=bankMoneySum.add(depositCheckAccWindowBaseDTO.getMoney());
      }
      for(int i=0;i<officeList.size();i++){
        DepositCheckAccWindowBaseDTO depositCheckAccWindowBaseDTO=(DepositCheckAccWindowBaseDTO)officeList.get(i);
        officeMoneySum=officeMoneySum.add(depositCheckAccWindowBaseDTO.getMoney());
      }
      for(int i=0;i<bankOutList.size();i++){
        DepositCheckAccWindowBaseDTO depositCheckAccWindowBaseDTO=(DepositCheckAccWindowBaseDTO)bankOutList.get(i);
        bankOutMoneySum=bankOutMoneySum.add(depositCheckAccWindowBaseDTO.getMoney());
      }
      for(int i=0;i<officeOutList.size();i++){
        DepositCheckAccWindowBaseDTO depositCheckAccWindowBaseDTO=(DepositCheckAccWindowBaseDTO)officeOutList.get(i);
        officeOutMoneySum=officeOutMoneySum.add(depositCheckAccWindowBaseDTO.getMoney());
      }
      DepositCheckAccWindowDTO depositCheckAccWindowDTO=new DepositCheckAccWindowDTO();
      depositCheckAccWindowDTO.setBankMoneySum(bankMoneySum);
      depositCheckAccWindowDTO.setOfficeMoneySum(officeMoneySum);
      depositCheckAccWindowDTO.setBankOutMoneySum(bankOutMoneySum);
      depositCheckAccWindowDTO.setOfficeOutMoneySum(officeOutMoneySum);
      bdcBalanceSum=officeMoneySum.subtract(officeOutMoneySum);
      bcaBalanceSum=bankMoneySum.subtract(bankOutMoneySum);
      depositCheckAccWindowDTO.setBdcBalanceSum(bdcBalanceSum);
      depositCheckAccWindowDTO.setBcaBalanceSum(bcaBalanceSum);
      adjustOfficeBalance=adjustOfficeBalance.add(bdcBalanceSum).add(bankMoneySum).subtract(bankOutMoneySum);
      adjustBankBalance=adjustBankBalance.add(bcaBalanceSum).add(officeMoneySum).subtract(officeOutMoneySum);
      depositCheckAccWindowDTO.setAdjustOfficeBalance(adjustOfficeBalance);
      depositCheckAccWindowDTO.setAdjustBankBalance(adjustBankBalance);
      obj[0]=bankList;
      obj[1]=officeList;
      obj[2]=bankOutList;
      obj[3]=officeOutList;
      obj[4]=depositCheckAccWindowDTO;
    }catch(Exception e){
      e.printStackTrace();
    }
    return obj;
  }
  public void setBankCredenceDAO(BankCredenceDAO bankCredenceDAO) {
    this.bankCredenceDAO = bankCredenceDAO;
  }
  public void setTreasurerCredenceDAO(TreasurerCredenceDAO treasurerCredenceDAO) {
    this.treasurerCredenceDAO = treasurerCredenceDAO;
  }
  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }
}
