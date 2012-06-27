package org.xpup.hafmis.sysloan.dataready.bank.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanBank;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.dataready.bank.bsinterface.IBankBS;
import org.xpup.hafmis.sysloan.dataready.bank.dto.BankAFDTO;
import org.xpup.hafmis.sysloan.dataready.bank.dto.BankDTO;

public class BankBS implements IBankBS{
  private LoanBankDAO loanBankDAO=null;
  private CollBankDAO collBankDAO=null;
  private OrganizationUnitDAO organizationUnitDAO=null;
  private PlOperateLogDAO plOperateLogDAO=null;
  /**
   * name ����
   * ����׼��:����ά��--��ʾ�б�
   */
  public List findBankList(Pagination pagination)
  {
    List list=null;
    List listAF=new ArrayList();
    CollBank dto=null;
    OrganizationUnit organizationUnit=null;
    try{
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list=loanBankDAO.findLoanBank_YM(orderBy, order, start, pageSize,page);
      if(list.size()!=0||list!=null)
      {
        for(int i=0;i<list.size();i++)
        {
          BankAFDTO bankAF=new BankAFDTO();
        LoanBank loanBank=(LoanBank)list.get(i);
        bankAF.setId(loanBank.getId()); //id
        dto=collBankDAO.getCollBankByCollBankid(loanBank.getLoanBankId().toString());
        bankAF.setBankName(dto.getCollBankName());//��������  
        organizationUnit=
          organizationUnitDAO.queryOrganizationUnitListByCriterions(loanBank.getOffice());
        bankAF.setOffice(organizationUnit.getName());//���´�
        bankAF.setLoanAcc(loanBank.getLoanAcc());//����ί�д����˺�
        bankAF.setInterestAcc(loanBank.getInterestAcc());//������Ϣ�˺�
        bankAF.setBankPrisident(loanBank.getBankPrisident());//�����г�
        bankAF.setBankPrisidentTel(loanBank.getBankPrisidentTel());//�г��绰
        bankAF.setContactPrsn(loanBank.getContactPrsn());//��ϵ��
        bankAF.setContactTel(loanBank.getContactTel());//��ϵ�˵绰
        bankAF.setBusiness(loanBank.getBusiness());//��ϵ��ְ��
        String bankSt=BusiTools.getBusiValue(Integer.parseInt(loanBank.getLoanBnakSt()),
            BusiConst.APPSTATUS);
        bankAF.setLoanBankSt(bankSt);//״̬
        bankAF.setOutAccount(loanBank.getOutAccount());
        bankAF.setInAccount(loanBank.getInAccount());
        listAF.add(bankAF);
        }
      }
    
    int count = loanBankDAO.findBankCount_YM();
    pagination.setNrOfElements(count);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return listAF;
  }
  /**
   * name ����
   * �ж�������Ϣ�� �Ƿ��Ѿ����� ���´� ����ID��ͬ�ļ�¼  (**���и�**)
   * return boolean
   * true ����
   * false �ռ�¼
   */
  public boolean isCheckBank(BankAFDTO bankAFDTO){
    boolean is_bank=false;
    try {
      BankDTO bankDTO=new BankDTO();
      bankDTO.setBankName(new BigDecimal(bankAFDTO.getBankName()));
      bankDTO.setOffice(bankAFDTO.getOffice());
      is_bank=loanBankDAO.isCheckBank_YM(bankDTO);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return is_bank;
  }
  /**
   * name ����
   * ���������� ������Ϣ�� and ������־  (**���и�**)
   */
  public void insertBank(BankAFDTO bankAFDTO,SecurityInfo securityInfo)
  {
    try{
      LoanBank loanBank=new LoanBank(); //�������ж���
      loanBank.setOffice(bankAFDTO.getOffice()); //���´�
      loanBank.setLoanBankId(new BigDecimal(bankAFDTO.getBankName())); //���б��
      loanBank.setLoanAcc(bankAFDTO.getLoanAcc());//�����ʺ�
      loanBank.setInterestAcc(bankAFDTO.getInterestAcc());//��Ϣ�ʺ�
      loanBank.setBankPrisident(bankAFDTO.getBankPrisident());//�����г�
      loanBank.setBankPrisidentTel(bankAFDTO.getBankPrisidentTel());//�г��绰
      loanBank.setContactPrsn(bankAFDTO.getContactPrsn());//��ϵ��
      loanBank.setContactTel(bankAFDTO.getContactTel());//��ϵ�˵绰
      loanBank.setBusiness(bankAFDTO.getBusiness());//��ϵ��ְ��
      loanBank.setOutAccount(bankAFDTO.getOutAccount());
      loanBank.setInAccount(bankAFDTO.getInAccount());
      String yearClear = (Integer.parseInt(securityInfo.getUserInfo().getPlbizDate().substring(0, 4))-1)+"";//�������    
      loanBank.setYearClear(yearClear);//
      loanBank.setLoanBnakSt("0");
      String logid=loanBankDAO.insert(loanBank).toString();
      PlOperateLog plOperateLog=new PlOperateLog();//������־����     
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));//����ϵͳ
      plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_DATAPREPARATION_BANK));//����ά��
      plOperateLog.setOpButton("1");
      plOperateLog.setOpBizId(new BigDecimal(logid)); 
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLogDAO.insert(plOperateLog);

    }catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  /**
   * name ����
   * ͨ��������Ϣ�� ��������
   */
  public BankAFDTO queryBank(String id)
  {
    LoanBank loanBank=loanBankDAO.queryById(new Integer(id));
    BankAFDTO bankAF=new BankAFDTO();
    try{
    bankAF.setBankName(loanBank.getLoanBankId().toString());//��������  
    bankAF.setOffice(loanBank.getOffice());//���´�
    bankAF.setId(loanBank.getId());
    bankAF.setLoanAcc(loanBank.getLoanAcc());//����ί�д����˺�
    bankAF.setInterestAcc(loanBank.getInterestAcc());//������Ϣ�˺�
    bankAF.setBankPrisident(loanBank.getBankPrisident());//�����г�
    bankAF.setBankPrisidentTel(loanBank.getBankPrisidentTel());//�г��绰
    bankAF.setContactPrsn(loanBank.getContactPrsn());//��ϵ��
    bankAF.setContactTel(loanBank.getContactTel());//��ϵ�˵绰
    bankAF.setBusiness(loanBank.getBusiness());//��ϵ��ְ��
    bankAF.setOutAccount(loanBank.getOutAccount());
    bankAF.setInAccount(loanBank.getInAccount());
    }catch(Exception e)
    {
      e.printStackTrace();
    }
    return bankAF;
  }
  /**
   * name ����
   * ����idɾ��������Ϣ���¼
   */
  public String deleteBank_YM(Integer id,SecurityInfo securityInfo)
  {
    LoanBank loanBank=null;
    String err="�ü�¼����ɾ��";
    try{
      loanBank=loanBankDAO.queryById(id);
      if(loanBank==null)
      {
        err="�ü�¼�Ѿ�ɾ��!";
      }
      else  //���ڸü�¼
      {
        if(loanBank.getLoanBnakSt().equals("0"))
        {
          loanBankDAO.delete_YM(id);
          PlOperateLog plOperateLog=new PlOperateLog();//������־����     
          plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));//����ϵͳ
          plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_DATAPREPARATION_BANK));//����ά��
          plOperateLog.setOpButton("3");
          plOperateLog.setOpBizId(new BigDecimal(id.toString())); 
          plOperateLog.setOpIp(securityInfo.getUserIp());
          plOperateLog.setOpTime(new Date());
          plOperateLog.setOperator(securityInfo.getUserName());
          plOperateLogDAO.insert(plOperateLog);
          err="ɾ���ɹ�!";
        }
        else
        {
          err="�ü�¼����ɾ��";
        }
      }
    }catch(Exception e)
    {
      e.printStackTrace();
    }
    return err;
  }
  /**
   * name ����
   * ������Ϣ�� ���ù���
   * return boolean
   * ���óɹ� true
   * ����ʧ�� false
   */
  public String useBank_YM(Integer id,SecurityInfo securityInfo)
  {
    LoanBank loanBank=null;
    String use="������¼������!";
    try{
      loanBank=loanBankDAO.queryById(id);
      if(loanBank.getLoanBnakSt().equals("0"))
      {
        loanBank.setLoanBnakSt("1");
        loanBankDAO.update(loanBank);
        PlOperateLog plOperateLog=new PlOperateLog();//������־����     
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));//����ϵͳ
        plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_DATAPREPARATION_BANK));//����ά��
        plOperateLog.setOpButton("7");
        plOperateLog.setOpBizId(new BigDecimal(id.toString())); 
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLogDAO.insert(plOperateLog);
        use="���óɹ�!";
      }      
    }catch(Exception e)
    {
      e.printStackTrace();
    }
    return use;
  }
  /**
   * name ����
   * ���ݰ��´�,���� ��ô�����¼  (**���и�**)
   */
  public void updateBank_YM(BankAFDTO bankAFDTO,SecurityInfo securityInfo)
  {
    LoanBank bank=null;
    try{
      bank=loanBankDAO.queryById(bankAFDTO.getId());
      if(!bankAFDTO.getLoanAcc().equals(""))
        bank.setLoanAcc(bankAFDTO.getLoanAcc());//�����ʺ�
      if(bankAFDTO.getInterestAcc()!=null)
        bank.setInterestAcc(bankAFDTO.getInterestAcc().trim());
      if(bankAFDTO.getBankPrisident()!=null)
        bank.setBankPrisident(bankAFDTO.getBankPrisident());
      if(bankAFDTO.getBankPrisidentTel()!=null)
        bank.setBankPrisidentTel(bankAFDTO.getBankPrisidentTel());
      if(bankAFDTO.getContactPrsn()!=null)
        bank.setContactPrsn(bankAFDTO.getContactPrsn());
      if(bankAFDTO.getContactTel()!=null)
        bank.setContactTel(bankAFDTO.getContactTel());
      if(bankAFDTO.getOutAccount()!=null)
        bank.setOutAccount(bankAFDTO.getOutAccount());
      if(bankAFDTO.getInAccount()!=null)
        bank.setInAccount(bankAFDTO.getInAccount());
      if(bankAFDTO.getBusiness()!=null)
        bank.setBusiness(bankAFDTO.getBusiness());
        loanBankDAO.update(bank);
        PlOperateLog plOperateLog=new PlOperateLog();//������־����     
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));//����ϵͳ
        plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_DATAPREPARATION_BANK));//����ά��
        plOperateLog.setOpButton("2");
        plOperateLog.setOpBizId(new BigDecimal(bank.getId().toString())); 
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLogDAO.insert(plOperateLog);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    } 
  }
  /**
   * name ����
   *����id �ж��Ƿ��м�¼
   *true �д˼�¼
   *false �޴˼�¼
   */
  public boolean is_bank_YM(Integer id)
  {
    boolean is_bank=false;
    try {
      LoanBank loanBank=loanBankDAO.queryById(id);
      if(loanBank==null)
        is_bank=true;
    } catch (RuntimeException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return is_bank;
  }
  /**
   * �����������  ����
   * @return
   */
  public List getCollBankList(){
    List list = null;
    try {
      list = loanBankDAO.getCollBankList();
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list;
  }
  /**
   * �õ����´��µĹ鼯����
   * @param office
   * @return CollBankList
   */
  public List queryCollBank(String office){
    List collBankList = null;
    try {
      collBankList = collBankDAO.getOfficeCollBankList(office);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return collBankList;
  }
  public void setLoanBankDAO(LoanBankDAO loanBankDAO) {
    this.loanBankDAO = loanBankDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }
  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }
}
