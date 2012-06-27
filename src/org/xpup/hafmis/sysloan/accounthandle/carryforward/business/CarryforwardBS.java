package org.xpup.hafmis.sysloan.accounthandle.carryforward.business;

import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.carryforward.bsinterface.ICarryforwardBS;
import org.xpup.hafmis.sysloan.accounthandle.carryforward.dto.CarryforwardTbDTO;
import org.xpup.hafmis.sysloan.accounthandle.carryforward.form.CarryforwardShowAF;
import org.xpup.hafmis.sysloan.accounthandle.carryforward.form.CarryforwardTbShowAF;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
import org.xpup.hafmis.sysloan.common.dao.RestoreLoanDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanBank;

public class CarryforwardBS implements ICarryforwardBS {

  private RestoreLoanDAO restoreLoanDAO=null;//����ƻ���pl201
  
  private BorrowerAccDAO borrowerAccDAO=null;//pl111
  
  private CollBankDAO collBankDAO=null;     //ת����������
  
  private LoanBankDAO loanBankDAO=null;    //�����Ƿ����pl002 

  public void setRestoreLoanDAO(RestoreLoanDAO restoreLoanDAO) {
    this.restoreLoanDAO = restoreLoanDAO;
  }
  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }
  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }
  public void setLoanBankDAO(LoanBankDAO loanBankDAO) {
    this.loanBankDAO = loanBankDAO;
  }
  //������ʾ��Ҫ���ļ�¼
  public CarryforwardShowAF queryBorrowerAccList(Pagination pagination,
      SecurityInfo securityInfo)throws BusinessException{
    CarryforwardShowAF carryforwardShowAF=new CarryforwardShowAF();
    try{
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      String loanBankId=(String)pagination.getQueryCriterions().get("loanBankId");        //�ſ�����
      List list=new ArrayList();
      String contractSt="11";                     //ҵ��״̬�ǻ�����
      String bizdate=securityInfo.getUserInfo().getPlbizDate(); //ҵ������
      String tempbizdate=bizdate.substring(4, 6);
      String tempyear=bizdate.substring(0, 4);  //�鿴��ת����Ƿ����
      if(!tempbizdate.equals("12")){
        throw new BusinessException("���ʱ��ֻ��Ϊʮ���£����ʵ�������ڣ�");
      }  
      String yearClear="";
      yearClear=loanBankDAO.queryYearClearByBankId_sy(loanBankId);
      if(yearClear.equals(tempyear)){
       throw new BusinessException("�������Ѿ����ս�ת�������ٴβ�����");
       }
      String templastyear=new Integer(tempyear).intValue()-1+"";
      if(!templastyear.equals(yearClear)){
        throw new BusinessException("���ս�תʱ�䲻��ȷ��");
        }
      String loanKouYearmonth=bizdate.substring(0, 4)+"12"; //���ε�û����һ��Ļ����¼�ĺ�ͬ���
      CollBank  collBank=collBankDAO.getCollBankByCollBankid_(loanBankId);               //���ҳ���Ӧ��������������ǰ̨��ʾ
      list=borrowerAccDAO.queryBorrowerAccList_sy(contractSt, loanBankId, loanKouYearmonth, orderBy, order, start, pageSize, securityInfo,collBank.getCollBankName());
      carryforwardShowAF.setLoanBankId(loanBankId);
      carryforwardShowAF.setList(list);
//      String pl111id="";
      int count=0;
      List countlist=new ArrayList();
      countlist=borrowerAccDAO.countBorrowerAccList_sy(contractSt, loanBankId, loanKouYearmonth, securityInfo);
//      if(!countlist.isEmpty()){
//        for(int i=0;i<countlist.size();i++){
//          String temp_pl111id = "";
//          temp_pl111id = countlist.get(i) + "";
//          pl111id += temp_pl111id + ",";
//        }
//      }
//      pl111id=pl111id.substring(0, pl111id.lastIndexOf(","));
//      carryforwardShowAF.setPl111id(pl111id);
      if(!countlist.isEmpty())
      count=countlist.size();
      pagination.setNrOfElements(count);
    }catch(BusinessException bx){
      throw bx;
    }
    catch(Exception e){
      e.printStackTrace();
    }
    return carryforwardShowAF;
  }
  public String useCarryforward(String loanBankId,SecurityInfo securityInfo)throws BusinessException{
    String info="";
    try{
      String bizdate=securityInfo.getUserInfo().getPlbizDate(); //�������
      String ip=securityInfo.getUserInfo().getUserIp(); //ip
      String operson=securityInfo.getUserInfo().getUsername(); //����Ա
      restoreLoanDAO.useCarryforward_sy(loanBankId, bizdate, ip, operson);
      info="pass";
    }catch(Exception e){
      throw new BusinessException("���ս�תʧ�ܣ�");
    }
    return info;
  }
  //������Ϊ����ʱ�򣬽�����֤�Ĳ�ѯ
  public String queryCarrayforwardInfo(String loanBankId,
      SecurityInfo securityInfo)throws BusinessException{
    String massageinfo="";
    try{
      String bizdate=securityInfo.getUserInfo().getPlbizDate(); //ҵ������
      String tempbizdate=bizdate.substring(4, 6);
      String tempyear=bizdate.substring(0, 4);  //�鿴��ת����Ƿ����
      if(!tempbizdate.equals("12")){
        throw new BusinessException("���ʱ��ֻ��Ϊʮ���£����ʵ�������ڣ�");
      }
      String yearClear="";
      yearClear=loanBankDAO.queryYearClearByBankId_sy(loanBankId);
      if(yearClear.equals(tempyear)){
       throw new BusinessException("�������Ѿ����ս�ת�������ٴβ�����");
       }
      String templastyear=new Integer(tempyear).intValue()-1+"";
      if(!templastyear.equals(yearClear)){
        throw new BusinessException("���ս�תʱ�䲻��ȷ��");
        }
      massageinfo="pass";
    }catch(BusinessException bx){
      throw bx;
    }
    return massageinfo;
  }
  //���ս�ת������Ϊ����ʱ��ʹ�á�
  public String useBankCarryforward(String loanBankId,
      SecurityInfo securityInfo) throws BusinessException{
    String info="";
    try{
      String bizdate=securityInfo.getUserInfo().getPlbizDate().substring(0, 4); //�������
      restoreLoanDAO.useBankCarryforward_sy(loanBankId, bizdate);
      info="pass";
    }catch(Exception e){
      throw new BusinessException("���ս�תʧ�ܣ�");
    }
    return info;
  }
}
