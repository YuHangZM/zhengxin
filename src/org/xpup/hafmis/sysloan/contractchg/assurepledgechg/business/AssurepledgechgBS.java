package org.xpup.hafmis.sysloan.contractchg.assurepledgechg.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.AssistantOrgDAO;
import org.xpup.hafmis.sysloan.common.dao.AssurerDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.CongealInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.ContractChgDAO;
import org.xpup.hafmis.sysloan.common.dao.HousesDAO;
import org.xpup.hafmis.sysloan.common.dao.ImpawnContractDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PledgeContractDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantOrg;
import org.xpup.hafmis.sysloan.common.domain.entity.Assurer;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.CongealInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.ContractChg;
import org.xpup.hafmis.sysloan.common.domain.entity.Houses;
import org.xpup.hafmis.sysloan.common.domain.entity.ImpawnContract;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.common.domain.entity.PledgeContract;
import org.xpup.hafmis.sysloan.contractchg.assurepledgechg.bsinterface.IAssurepledgechgBS;
import org.xpup.hafmis.sysloan.contractchg.assurepledgechg.dto.AssurepledgechgTaDTO;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTbAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTcAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTdAF;
/**
 * 
 * @author yuqf
 *2007-10-08
 */
public class AssurepledgechgBS implements IAssurepledgechgBS{
  
  private BorrowerAccDAO borrowerAccDAO = null;// PL111
  
  private CollBankDAO collBankDAO = null;// BB105
  
  private PledgeContractDAO pledgeContractDAO = null;// pl121
  
  private HousesDAO housesDAO = null;// pl114 ������Ϣ

  private AssistantOrgDAO assistantOrgDAO = null;// pl007 ������˾����
  
  private PlOperateLogDAO plOperateLogDAO = null;//ҵ����־
  
  private ContractChgDAO contractChgDAO = null;//pl211 ��ͬ�����Ϣ��
  
  private ImpawnContractDAO impawnContractDAO = null;
  
  private AssurerDAO assurerDAO = null;// PL123
  
  private CongealInfoDAO congealInfoDAO = null;//pl210 �����
   
  public void setCongealInfoDAO(CongealInfoDAO congealInfoDAO) {
    this.congealInfoDAO = congealInfoDAO;
  }

  public void setAssurerDAO(AssurerDAO assurerDAO) {
    this.assurerDAO = assurerDAO;
  }

  public void setImpawnContractDAO(ImpawnContractDAO impawnContractDAO) {
    this.impawnContractDAO = impawnContractDAO;
  }

  public void setContractChgDAO(ContractChgDAO contractChgDAO) {
    this.contractChgDAO = contractChgDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setAssistantOrgDAO(AssistantOrgDAO assistantOrgDAO) {
    this.assistantOrgDAO = assistantOrgDAO;
  }

  public void setHousesDAO(HousesDAO housesDAO) {
    this.housesDAO = housesDAO;
  }

  public void setPledgeContractDAO(PledgeContractDAO pledgeContractDAO) {
    this.pledgeContractDAO = pledgeContractDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  /**
   * Ĭ�ϲ�ѯPL111 ��ѯ ��ͬ��� ���������  ����� �����ʼ����  �������  �����ֹ����  ���ÿ������  �»���Ϣ �ſ����� �鿴ɨ����Ϣ 
   */
  public List defaultQueryBorrowerAccList(Pagination pagination, SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;  
    int pageSize = pagination.getPageSize();
    int count=0;
    int term = 0;
    String contractId = (String)pagination.getQueryCriterions().get("contractId"); //��ͬID
    String debitter = (String)pagination.getQueryCriterions().get("debitter");//��������� PL110 
    String empId = (String)pagination.getQueryCriterions().get("empId");//ְ�����
    String cardNum = (String)pagination.getQueryCriterions().get("cardNum");//֤������
    String houseType = (String)pagination.getQueryCriterions().get("houseType");//��������
    try{
      list = borrowerAccDAO.queryBorrowerAccListByConditionYU(contractId, debitter, empId, cardNum, houseType, orderBy, order, start, pageSize,securityInfo);
      count = borrowerAccDAO.queryBorrowerAccListCountByConditionYU(contractId, debitter, empId, cardNum, houseType, orderBy, order, start, pageSize,securityInfo);
      if(list.size() != 0){
        for(int i=0;i<list.size();i++){
          AssurepledgechgTaDTO assurepledgechgTaDTO=new AssurepledgechgTaDTO();
          assurepledgechgTaDTO = (AssurepledgechgTaDTO)list.get(i);
          String tempBankId = assurepledgechgTaDTO.getBank();
          CollBank collBank = collBankDAO.getCollBankByCollBankid_(tempBankId);//ͨ������ID��ѯ�ſ���������
          assurepledgechgTaDTO.setBank(collBank.getCollBankName());
          //ͨ����ʼʱ��ͽ�����޼��������ʱ��
          String debitMoneyStaDate = assurepledgechgTaDTO.getStartDate();
          if(assurepledgechgTaDTO.getLoanTimeLimit()!=null && !"".equals(assurepledgechgTaDTO.getLoanTimeLimit())){
           term = new Integer(assurepledgechgTaDTO.getLoanTimeLimit()).intValue();
          }
          String endDate = BusiTools.addMonth(debitMoneyStaDate, term);
          assurepledgechgTaDTO.setEndDate(endDate+debitMoneyStaDate.substring(6, 8));
        }
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    pagination.setNrOfElements(count);
    return list;
  }

  /**
   * Tb ȷ����ť ����PL121 ����PL111 PL111.CONTRACT_ST=7
   */
  public void addPledgeContract(String pkId,SecurityInfo securityInfo, EndorsecontractTbAF endorsecontractTbAF,HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    
     String contractId = endorsecontractTbAF.getContractId();//��ͬID
     String debitter = endorsecontractTbAF.getDebitter();//��������� PL110 
     String pledgePerson = endorsecontractTbAF.getPledgePerson();//��Ѻ������
     String office = endorsecontractTbAF.getOffice();//��ѺȨ�ˣ����������ģ�
     String pledgeContractId = endorsecontractTbAF.getPledgeContractId();//��Ѻ��ͬ���
     String assistantOrgName = endorsecontractTbAF.getAssistantOrgName();//������˾����
     String pledgeMatterName = endorsecontractTbAF.getPledgeMatterName();//��Ѻ������
     String paperNum = endorsecontractTbAF.getPaperNum();//����Ȩ֤���
     String paperName = endorsecontractTbAF.getPaperName();//����Ȩ֤����
     String paperPersonName = endorsecontractTbAF.getPaperPersonName();//����Ȩ������
     String cardKind = endorsecontractTbAF.getCardKind();//����Ȩ��֤������
     String carNum = endorsecontractTbAF.getCarNum();//����Ȩ��֤������
     String tel = endorsecontractTbAF.getTel();//����Ȩ�˹̶��绰
     String mobile = endorsecontractTbAF.getMobile();//����Ȩ���ƶ��绰
     String pledgeAddr = endorsecontractTbAF.getPledgeAddr();//��Ѻ���ַ
     String area = endorsecontractTbAF.getArea();//�������
     String buyHouseContractId = endorsecontractTbAF.getBuyHouseContractId();//������ͬ���
     String pledgeValue = endorsecontractTbAF.getPledgeValue();//��Ѻֵ
     String evaluateValue = endorsecontractTbAF.getEvaluateValue();//����ֵ
     PledgeContract pledgeContract = null;
     String operator = securityInfo.getUserName();
     String opIp = securityInfo.getUserIp();
     ContractChg contractChg1 = null;
     ContractChg contractChg2 = null;
     ContractChg contractChg3 = null;
     if(pkId != null && !"".equals(pkId)){//
       pledgeContract = pledgeContractDAO.queryById(new Integer(pkId));// ����
       if (pledgeContract != null) {// ���ڣ�����
         //��ø���ǰ������
         EndorsecontractTbAF preEndorsecontractTbAF = (EndorsecontractTbAF)request.getSession().getAttribute("theEndorsecontractTbAF");//theEndorsecontractTbAF
         String prePaperNum = preEndorsecontractTbAF.getPaperNum();//����ǰ����Ȩ֤���
         if(prePaperNum==null){
           prePaperNum="";
         }
         String preTel = preEndorsecontractTbAF.getTel();//����ǰ�绰
         if(preTel==null){
           preTel="";
         }
         String preMobile = preEndorsecontractTbAF.getMobile();//����ǰ�ƶ��绰
         if(preMobile==null){
           preMobile="";
         }
//         if (buyHouseContractId != null && !"".equals(buyHouseContractId)) {
//           // ���¹�����ͬ���
//           Houses houses = housesDAO.queryById(contractId);
//           houses.setBuyHouseContractId(buyHouseContractId);
//         }
//         if (assistantOrgName != null && !"".equals(assistantOrgName)) {
//           // ���µ�����˾
//           String assistantOrgId = assistantOrgDAO.queryId(contractId);
//           if(assistantOrgId != null && !"".equals(assistantOrgId)){
//           AssistantOrg assistantOrg = assistantOrgDAO.queryById(new Integer(
//               assistantOrgId));
//           assistantOrg.setAssistantOrgName(assistantOrgName);
//           }
//         }
//         if (contractId != null && !"".equals(contractId)) {
//           pledgeContract.setContractId(contractId);
//         }
//         if (pledgeContractId != null && !"".equals(pledgeContractId)) {
//           pledgeContract.setPledgeContractId(pledgeContractId);
//         }
//         if (pledgeMatterName != null && !"".equals(pledgeMatterName)) {
//           pledgeContract.setPledgeMatterName(pledgeMatterName);
//         }
//         if (pledgeValue != null && !"".equals(pledgeValue)) {
//           pledgeContract.setPledgeValue(new BigDecimal(pledgeValue));
//         }
           pledgeContract.setPaperNum(paperNum);
           
           //����pl211 ��ͬ�����Ϣ��
           if(!paperNum.equals(prePaperNum)){
           contractChg1 = new ContractChg();
           contractChg1.setContractId(contractId);//��ͬ���
           contractChg1.setChgColumn("����Ȩ֤���");//�޸��ֶ�
           contractChg1.setChgBefInfo(prePaperNum);//�޸�ǰ��Ϣ
           contractChg1.setChgEndInfo(paperNum);//�޸ĺ���Ϣ
           contractChg1.setOpTime(new Date());//����ʱ��
           contractChg1.setOperator(operator);
           String contractType = BusiConst.PLPREPAYMENTFEES_PLEDGEINFO + "";
           contractChg1.setContractType(contractType);//�޸ĺ�ͬ���ͣ�4 ��Ѻ��ͬ��Ϣ
           contractChgDAO.insert(contractChg1);
           }
       
//         if (paperName != null && !"".equals(paperName)) {
//           pledgeContract.setPaperName(paperName);
//         }
//         if (paperPersonName != null && !"".equals(paperPersonName)) {
//           pledgeContract.setName(paperPersonName);
//         }
//         if (cardKind != null && !"".equals(cardKind)) {
//           pledgeContract.setCardKind(cardKind);
//         }
//         if (carNum != null && !"".equals(carNum)) {
//           pledgeContract.setCardNum(carNum);
//         }
     
           pledgeContract.setTel(tel);
           if(!tel.equals(preTel)){
           contractChg2 = new ContractChg();
           contractChg2.setContractId(contractId);//��ͬ���
           contractChg2.setChgColumn("����Ȩ�˹̶��绰");//�޸��ֶ�
           contractChg2.setChgBefInfo(preTel);//�޸�ǰ��
        
           contractChg2.setChgEndInfo(tel);//�޸ĺ���Ϣ

           contractChg2.setOpTime(new Date());//����ʱ��
           contractChg2.setOperator(operator);
           String contractType = BusiConst.PLPREPAYMENTFEES_PLEDGEINFO + "";
           contractChg2.setContractType(contractType);//�޸ĺ�ͬ���ͣ�4 ��Ѻ��ͬ��Ϣ
           contractChgDAO.insert(contractChg2);
           }
    
    
           pledgeContract.setMobile(mobile);
           if(!mobile.equals(preMobile)){
           contractChg3 = new ContractChg();
           contractChg3.setContractId(contractId);//��ͬ���
           contractChg3.setChgColumn("����Ȩ���ƶ��绰");//�޸��ֶ�
          
           contractChg3.setChgBefInfo(preMobile);//�޸�ǰ��Ϣ
         
             contractChg3.setChgEndInfo(mobile);//�޸ĺ���Ϣ
 
           contractChg3.setOpTime(new Date());//����ʱ��
           contractChg3.setOperator(operator);
           String contractType = BusiConst.PLPREPAYMENTFEES_PLEDGEINFO + "";
           contractChg3.setContractType(contractType);//�޸ĺ�ͬ���ͣ�4 ��Ѻ��ͬ��Ϣ
           contractChgDAO.insert(contractChg3);
           }
   
//         if (pledgeAddr != null && !"".equals(pledgeAddr)) {
//           pledgeContract.setPledgeAddr(pledgeAddr);
//         }
//         if (area != null && !"".equals(area)) {
//           pledgeContract.setArea(new BigDecimal(area));
//         }
//         if (evaluateValue != null && !"".equals(evaluateValue)) {
//           pledgeContract.setEvaluateValue(new BigDecimal(evaluateValue));
//         }
        
      
         String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
         String model = BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_PLEDGCONTRACT + "";
         String button = BusiLogConst.BIZLOG_ACTION_MODIFY + "";
         String bizId = pledgeContract.getId().toString();
         this.addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId,contractId);
       }
     }else{//�����ڣ�����һ��������
      if (buyHouseContractId != null && !"".equals(buyHouseContractId)) {
       // ���빺����ͬ���
       Houses houses = housesDAO.queryById(contractId);
       houses.setBuyHouseContractId(buyHouseContractId);
//       housesDAO.insert(houses);
     }
     // ���뵣����˾
     if (assistantOrgName != null && !"".equals(assistantOrgName)) {
       AssistantOrg assistantOrg = new AssistantOrg();
       assistantOrg.setAssistantOrgName(assistantOrgName);
       assistantOrgDAO.insert(assistantOrg);
     }
     pledgeContract = new PledgeContract();
     if (contractId != null && !"".equals(contractId)) {
       pledgeContract.setContractId(contractId);
     }
     if (pledgeContractId != null && !"".equals(pledgeContractId)) {
       pledgeContract.setPledgeContractId(pledgeContractId);
     }
     if (pledgeMatterName != null && !"".equals(pledgeMatterName)) {
       pledgeContract.setPledgeMatterName(pledgeMatterName);
     }
     if (pledgeValue != null && !"".equals(pledgeValue)) {
       pledgeContract.setPledgeValue(new BigDecimal(pledgeValue));
     }
     if (paperNum != null && !"".equals(paperNum)) {
       pledgeContract.setPaperNum(paperNum);
     }
     if (paperName != null && !"".equals(paperName)) {
       pledgeContract.setPaperName(paperName);
     }
     if (paperPersonName != null && !"".equals(paperPersonName)) {
       pledgeContract.setName(paperPersonName);
     }
     if (cardKind != null && !"".equals(cardKind)) {
       pledgeContract.setCardKind(cardKind);
     }
     if (carNum != null && !"".equals(carNum)) {
       pledgeContract.setCardNum(carNum);
     }
     if (tel != null && !"".equals(tel)) {
       pledgeContract.setTel(tel);
     }
     if (mobile != null && !"".equals(mobile)) {
       pledgeContract.setMobile(mobile);
     }
     if (pledgeAddr != null && !"".equals(pledgeAddr)) {
       pledgeContract.setPledgeAddr(pledgeAddr);
     }
     if (area != null && !"".equals(area)) {
       pledgeContract.setArea(new BigDecimal(area));
     }
     if (evaluateValue != null && !"".equals(evaluateValue)) {
       pledgeContract.setEvaluateValue(new BigDecimal(evaluateValue));
     }
     pledgeContract.setStatus("0");
     String pContractId = pledgeContractDAO.insert(pledgeContract).toString();
     request.getSession().setAttribute("pl121Id", pContractId);
     String button = BusiLogConst.BIZLOG_ACTION_ADD + "";
     String bizId = pContractId;
    
     String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
     String model = BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_PLEDGCONTRACT + "";
     
     this.updatePl111(contractId);
     
     this.addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId,contractId);
     }
  }
  //����PL111
  public void updatePl111(String id){
    BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(id);//����pl111 ��ͬ״̬��7;
    borrowerAcc.setContractSt(BusiConst.PLCONTRACTSTATUS_SECAUDIT+"");
  }
  /**
   * ���������־pl201
   * 
   * @param securityInfo
   *
   * @return
   */
  public void addPlOperateLog(String opSys, String model, String button,
      String bizId, String opIp, String operator, String opBizId,String contractId) {
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(opSys));
    plOperateLog.setOpModel(model);
    plOperateLog.setOpButton(button);
    if (opBizId != null && !"".equals(opBizId)) {
      plOperateLog.setOpBizId(new BigDecimal(opBizId));
    }
    plOperateLog.setOpIp(opIp);
    plOperateLog.setContractId(bizId);
    plOperateLog.setOpTime(new Date());
    plOperateLog.setOperator(operator);
    plOperateLog.setContractId(contractId);
    plOperateLogDAO.insert(plOperateLog);
  }
/**
 * TB �ַ����������ϰ�ť
 */
  public void deletePledgeContract(String id, Pagination pagination, SecurityInfo securityInfo, HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    PledgeContract pledgeContract = null;
    String operator = securityInfo.getUserName();
    String opIp = securityInfo.getUserIp();
    String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
    String model = BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_PLEDGCONTRACT + "";
    if (id != null && !"".equals(id)) {
      pledgeContract = pledgeContractDAO.queryById(new Integer(id));
      String bizId = pledgeContract.getId().toString();
      String contractId = pledgeContract.getContractId();
      String status = pledgeContract.getStatus();
      if (status.equals("0")) {
        pledgeContract.setStatus("1");//����״̬
        BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(pledgeContract.getContractId());//����pl111 ��ͬ״̬��7;
        borrowerAcc.setContractSt("7");
        String button = BusiLogConst.BIZLOG_ACTION_DELETE + "";
        this.addPlOperateLog(opSys, model, button, bizId, opIp, operator,
            bizId,contractId);
      } else {
        throw new BusinessException("�ü�¼�����ϣ�");
      }
    }
  }
/**
 * TC �ַ�--ȷ����ť
 */
public void addImpawnContract(String pkId, SecurityInfo securityInfo, EndorsecontractTcAF endorsecontractTcAF, HttpServletRequest request) throws Exception, BusinessException {
  // TODO Auto-generated method stub
//����ID��pl121Id������ѯPL121
  String operator = securityInfo.getUserName();
  String opIp = securityInfo.getUserIp();
  String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
  String model = BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_IMPAWNCONTRACT + "";

  String contractId = endorsecontractTcAF.getContractId();// ��ͬID
  String debitter = endorsecontractTcAF.getDebitter();// ��������� PL110
  String impawnContractId = endorsecontractTcAF.getImpawnContractId();// ��Ѻ��ͬ���
  String assistantOrgName = endorsecontractTcAF.getAssistantOrgName();// ������˾����
  String impawnPerson = endorsecontractTcAF.getImpawnPerson();// ��Ѻ��
  String office = endorsecontractTcAF.getOffice();// ��ѺȨ�ˣ����������ģ�
  String impawnMatterName = endorsecontractTcAF.getImpawnMatterName();// ��Ѻ������
  String impawnValue = endorsecontractTcAF.getImpawnValue();// ��Ѻ���ֵ
  String paperPersonName = endorsecontractTcAF.getPaperPersonName();// ����Ȩ������
  String cardKind = endorsecontractTcAF.getCardKind();// ����Ȩ��֤������
  String carNum = endorsecontractTcAF.getCarNum();// ����Ȩ��֤������
  String paperNum = endorsecontractTcAF.getPaperNum();// ����Ȩ֤���
  String paperName = endorsecontractTcAF.getPaperName();// ����Ȩ֤����
  String tel = endorsecontractTcAF.getTel();// ����Ȩ�˹̶��绰
  String mobile = endorsecontractTcAF.getMobile();// ����Ȩ���ƶ��绰

  ImpawnContract impawnContract = null;
  if (pkId != null && !"".equals(pkId)) {
    impawnContract = impawnContractDAO.queryById(new Integer(pkId));// ����
    if (impawnContract != null) {// ���ڣ�����
      EndorsecontractTcAF preEndorsecontractTcAF = (EndorsecontractTcAF)request.getSession().getAttribute("theEndorsecontractTcAF");
      String preContractId = preEndorsecontractTcAF.getContractId();// ��ͬID
      String preDebitter = preEndorsecontractTcAF.getDebitter();// ��������� PL110
      String preImpawnContractId = preEndorsecontractTcAF.getImpawnContractId();// ��Ѻ��ͬ���
      String preAssistantOrgName = preEndorsecontractTcAF.getAssistantOrgName();// ������˾����
      String preImpawnPerson = preEndorsecontractTcAF.getImpawnPerson();// ��Ѻ��
      String preOffice = preEndorsecontractTcAF.getOffice();// ��ѺȨ�ˣ����������ģ�
      String preImpawnMatterName = preEndorsecontractTcAF.getImpawnMatterName();// ��Ѻ������
      String preImpawnValue = preEndorsecontractTcAF.getImpawnValue();// ��Ѻ���ֵ
      String prePaperPersonName = preEndorsecontractTcAF.getPaperPersonName();// ����Ȩ������
      String preCardKind = preEndorsecontractTcAF.getCardKind();// ����Ȩ��֤������
      String preCarNum = preEndorsecontractTcAF.getCarNum();// ����Ȩ��֤������
      String prePaperNum = preEndorsecontractTcAF.getPaperNum();// ����Ȩ֤���
      String prePaperName = preEndorsecontractTcAF.getPaperName();// ����Ȩ֤����
      String preTel = preEndorsecontractTcAF.getTel();// ����Ȩ�˹̶��绰
      if(preTel == null){
        preTel="";
      }
      String preMobile = preEndorsecontractTcAF.getMobile();// ����Ȩ���ƶ��绰
      if(preMobile == null){
        preMobile="";
      }
      if(prePaperNum == null){
        prePaperNum = "";
      }
 
        impawnContract.setPaperNum(paperNum);
        if(!paperNum.equals(prePaperNum)){
        ContractChg contractChg1 = new ContractChg();
        contractChg1.setContractId(contractId);//��ͬ���
        contractChg1.setChgColumn("����Ȩ֤���");//�޸��ֶ�
        contractChg1.setChgBefInfo(prePaperNum);//�޸�ǰ��Ϣ
        contractChg1.setChgEndInfo(paperNum);//�޸ĺ���Ϣ
        contractChg1.setOpTime(new Date());//����ʱ��
        contractChg1.setOperator(operator);
        String contractType = BusiConst.PLPREPAYMENTFEES_IMPAWNINFO + "";
        contractChg1.setContractType(contractType);//�޸ĺ�ͬ���ͣ�4 ��Ѻ��ͬ��Ϣ
        contractChgDAO.insert(contractChg1);
        }
        
        impawnContract.setTel(tel);
        if(!tel.equals(preTel)){
        ContractChg contractChg2 = new ContractChg();
        contractChg2.setContractId(contractId);//��ͬ���
        contractChg2.setChgColumn("����Ȩ�˹̶��绰");//�޸��ֶ�
        contractChg2.setChgBefInfo(preTel);//�޸�ǰ��Ϣ
        contractChg2.setChgEndInfo(tel);//�޸ĺ���Ϣ
        contractChg2.setOpTime(new Date());//����ʱ��
        contractChg2.setOperator(operator);
        String contractType2 = BusiConst.PLPREPAYMENTFEES_IMPAWNINFO + "";
        contractChg2.setContractType(contractType2);//�޸ĺ�ͬ���ͣ�4 ��Ѻ��ͬ��Ϣ
        contractChgDAO.insert(contractChg2);
        }
        impawnContract.setMobile(mobile);
        if(!mobile.equals(preMobile)){
        ContractChg contractChg3 = new ContractChg();
        contractChg3.setContractId(contractId);//��ͬ���
        contractChg3.setChgColumn("����Ȩ���ƶ��绰");//�޸��ֶ�
        contractChg3.setChgBefInfo(preMobile);//�޸�ǰ��Ϣ
        contractChg3.setChgEndInfo(mobile);//�޸ĺ���Ϣ
        contractChg3.setOpTime(new Date());//����ʱ��
        contractChg3.setOperator(operator);
        String contractType3 = BusiConst.PLPREPAYMENTFEES_IMPAWNINFO + "";
        contractChg3.setContractType(contractType3);//�޸ĺ�ͬ���ͣ�4 ��Ѻ��ͬ��Ϣ
        contractChgDAO.insert(contractChg3);
        }
      String button = BusiLogConst.BIZLOG_ACTION_MODIFY + "";
      String bizId = impawnContract.getId().toString();
      this
          .addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId,contractId);
    }
  } else {// �����ڣ�����

    // ���뵣����˾
    if (assistantOrgName != null && !"".equals(assistantOrgName)) {
      AssistantOrg assistantOrg = new AssistantOrg();
      assistantOrg.setAssistantOrgName(assistantOrgName);
      assistantOrgDAO.insert(assistantOrg);
    }
    impawnContract = new ImpawnContract();
    if (contractId != null && !"".equals(contractId)) {
      impawnContract.setContractId(contractId);
    }
    if (impawnContractId != null && !"".equals(impawnContractId)) {
      impawnContract.setImpawnContractId(impawnContractId);
    }
    if (impawnMatterName != null && !"".equals(impawnMatterName)) {
      impawnContract.setImpawnMatterName(impawnMatterName);
    }
    if (impawnValue != null && !"".equals(impawnValue)) {
      impawnContract.setImpawnValue(new BigDecimal(impawnValue));
    }
    if (paperPersonName != null && !"".equals(paperPersonName)) {
      impawnContract.setName(paperPersonName);
    }
    if (cardKind != null && !"".equals(cardKind)) {
      impawnContract.setCardKind(cardKind);
    }
    if (carNum != null && !"".equals(carNum)) {
      impawnContract.setCardNum(carNum);
    }
    if (paperNum != null && !"".equals(paperNum)) {
      impawnContract.setPaperNum(paperNum);
    }
    if (paperName != null && !"".equals(paperName)) {
      impawnContract.setPaperName(paperName);
    }
    if (tel != null && !"".equals(tel)) {
      impawnContract.setTel(tel);
    }
    if (mobile != null && !"".equals(mobile)) {
      impawnContract.setMobile(mobile);
    }
    impawnContract.setStatus("0");
    String iContractId = impawnContractDAO.insert(impawnContract).toString();
    String button = BusiLogConst.BIZLOG_ACTION_ADD + "";
    String bizId = iContractId;
    this.updatePl111(contractId);
    this.addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId,contractId);
  }
}
/**
 * ����
 */
public void deleteImpawnContract(String id, Pagination pagination, SecurityInfo securityInfo, HttpServletRequest request) throws Exception, BusinessException {
  // TODO Auto-generated method stub
  ImpawnContract impawnContract = null;
  String operator = securityInfo.getUserName();
  String opIp = securityInfo.getUserIp();
  String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
  String model = BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_IMPAWNCONTRACT + "";
  if (id != null && !"".equals(id)) {
    impawnContract = impawnContractDAO.queryById(new Integer(id));
    String bizId = impawnContract.getId().toString();
    String contractId = impawnContract.getContractId();
    String status = impawnContract.getStatus();
    if (status.equals("0")) {
      impawnContract.setStatus("1");//����״̬
      BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(impawnContract.getContractId());//����pl111 ��ͬ״̬��7;
      borrowerAcc.setContractSt("7");
      String button = BusiLogConst.BIZLOG_ACTION_DELETE + "";
      this.addPlOperateLog(opSys, model, button, bizId, opIp, operator,
          bizId,contractId);
    } else {
      throw new BusinessException("�ü�¼�����ϣ�");
    }
  }
}
/**
 * Td ȷ��
 */
public void addAssurer(String pkId, SecurityInfo securityInfo, EndorsecontractTdAF endorsecontractTdAF, HttpServletRequest request) throws Exception, BusinessException {
  // TODO Auto-generated method stub
  String operator = securityInfo.getUserName();
  String opIp = securityInfo.getUserIp();
  String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
  String model = BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_ASSURER + "";

  String contractId = endorsecontractTdAF.getContractId();// ��ͬID
  String debitter = endorsecontractTdAF.getDebitter();// ��������� PL110
  String empId = endorsecontractTdAF.getEmpId();// ְ�����
  String empName = endorsecontractTdAF.getEmpName();// ְ������
  String cardKind = endorsecontractTdAF.getCardKind();// ֤������
  String cardNum = endorsecontractTdAF.getCardNum();// ֤������
  String sex = endorsecontractTdAF.getSex();// �Ա�
  String birthday = endorsecontractTdAF.getBirthday();// ��������
  String salary = endorsecontractTdAF.getSalary();// �¹��ʶ�
  String monthPay = endorsecontractTdAF.getMonthPay();// �½ɴ��
  String balance = endorsecontractTdAF.getBalance();// �˻����
  String empSt = endorsecontractTdAF.getEmpSt();// �˻�״̬
  String tel = endorsecontractTdAF.getTel();// �̶��绰
  String mobile = endorsecontractTdAF.getMobile();// �ж��绰
  String homeTel = endorsecontractTdAF.getHomeTel();// ��ͥ�绰
  String homeAddr = endorsecontractTdAF.getHomeAddr();// ��ͥסַ
  String homeMail = endorsecontractTdAF.getHomeMai();// ��ͥ�ʱ�
  String orgId = endorsecontractTdAF.getId();// ��λ���
  String orgName = endorsecontractTdAF.getOrgName();// ��λ����
  String orgAddr = endorsecontractTdAF.getOrgAddr();// ��λ��ַ
  String orgTel = endorsecontractTdAF.getOrgTel();// ��λ�绰
  String orgMail = endorsecontractTdAF.getOrgMail();// ��λ�������

 
  Assurer assurer = null;
  if (pkId != null && !"".equals(pkId)) {
    assurer = assurerDAO.queryById(new Integer(pkId));// ������ѯ
    if (assurer != null) {// ���ڣ�����
      EndorsecontractTdAF preEndorsecontractTdAF = (EndorsecontractTdAF)request.getSession().getAttribute("theEndorsecontractTdAF");
      String precontractId = preEndorsecontractTdAF.getContractId();//��ͬID
      String predebitter = preEndorsecontractTdAF.getDebitter();//��������� PL110 
      String preempId = preEndorsecontractTdAF.getEmpId();//ְ�����
      String preempName = preEndorsecontractTdAF.getEmpName();//ְ������
      String precardKind = preEndorsecontractTdAF.getCardKind();//֤������
      String precardNum = preEndorsecontractTdAF.getCardNum();//֤������
      String presex = preEndorsecontractTdAF.getSex();//�Ա�
      String prebirthday = preEndorsecontractTdAF.getBirthday();//��������
      String presalary = preEndorsecontractTdAF.getSalary();//�¹��ʶ�
      String premonthPay = preEndorsecontractTdAF.getMonthPay();//�½ɴ��
      String prebalance = preEndorsecontractTdAF.getBalance();//�˻����
      String preempSt = preEndorsecontractTdAF.getEmpSt();//�˻�״̬
      String pretel = preEndorsecontractTdAF.getTel();//�̶��绰
      if(pretel == null){
        pretel="";
      }
      String premobile = preEndorsecontractTdAF.getMobile();//�ж��绰
      if(premobile == null){
        premobile="";
      }
      String prehomeTel = preEndorsecontractTdAF.getHomeTel();//��ͥ�绰
      if(prehomeTel == null){
        prehomeTel="";
      }
      String prehomeAddr = preEndorsecontractTdAF.getHomeAddr();//��ͥסַ
      if(prehomeAddr == null){
        prehomeAddr="";
      }
      String prehomeMai = preEndorsecontractTdAF.getHomeMai();//��ͥ�ʱ�
      if(prehomeMai == null){
        prehomeMai="";
      }
      String preorgId = preEndorsecontractTdAF.getOrgId();//��λ���
      String preorgName = preEndorsecontractTdAF.getOrgName();//��λ����
      if(preorgName == null){
        preorgName="";
      }
      String preorgAddr = preEndorsecontractTdAF.getOrgAddr();//��λ��ַ
      if(preorgAddr == null){
        preorgAddr="";
      }
      String preorgTel = preEndorsecontractTdAF.getOrgTel();//��λ�绰
      if(preorgTel == null){
        preorgTel="";
      }
      String preorgMail = preEndorsecontractTdAF.getOrgMail();//��λ�������
      if(preorgMail == null){
        preorgMail="";
      }
      
//      �̶��绰���ƶ��绰����ͥ�绰����ͥ��ַ���������룬��λ���ƣ���λ��ַ����λ�������룬��λ�绰
//      ����ֻ��
     
        assurer.setTel(tel);
        if(!tel.equals(pretel)){
          ContractChg contractChg1 = new ContractChg();
          contractChg1.setContractId(contractId);//��ͬ���
          contractChg1.setChgColumn("�̶��绰");//�޸��ֶ�
          contractChg1.setChgBefInfo(pretel);//�޸�ǰ��Ϣ
          contractChg1.setChgEndInfo(tel);//�޸ĺ���Ϣ
          contractChg1.setOpTime(new Date());//����ʱ��
          contractChg1.setOperator(operator);
          String contractType = BusiConst.PLPREPAYMENTFEES_BIALINFO + "";
          contractChg1.setContractType(contractType);//�޸ĺ�ͬ���ͣ�6 ��֤����Ϣ
          contractChgDAO.insert(contractChg1);
          }
     
        assurer.setMobile(mobile);
        if(!mobile.equals(premobile)){
          ContractChg contractChg1 = new ContractChg();
          contractChg1.setContractId(contractId);//��ͬ���
          contractChg1.setChgColumn("�ƶ��绰");//�޸��ֶ�
          contractChg1.setChgBefInfo(premobile);//�޸�ǰ��Ϣ
          contractChg1.setChgEndInfo(mobile);//�޸ĺ���Ϣ
          contractChg1.setOpTime(new Date());//����ʱ��
          contractChg1.setOperator(operator);
          String contractType = BusiConst.PLPREPAYMENTFEES_BIALINFO + "";
          contractChg1.setContractType(contractType);//�޸ĺ�ͬ���ͣ�6 ��֤����Ϣ
          contractChgDAO.insert(contractChg1);
          }
      
        assurer.setHomeTel(homeTel);
        if(!homeTel.equals(prehomeTel)){
          ContractChg contractChg1 = new ContractChg();
          contractChg1.setContractId(contractId);//��ͬ���
          contractChg1.setChgColumn("��ͥ�绰");//�޸��ֶ�
          contractChg1.setChgBefInfo(prehomeTel);//�޸�ǰ��Ϣ
          contractChg1.setChgEndInfo(homeTel);//�޸ĺ���Ϣ
          contractChg1.setOpTime(new Date());//����ʱ��
          contractChg1.setOperator(operator);
          String contractType = BusiConst.PLPREPAYMENTFEES_BIALINFO + "";
          contractChg1.setContractType(contractType);//�޸ĺ�ͬ���ͣ�6 ��֤����Ϣ
          contractChgDAO.insert(contractChg1);
          }
      
        assurer.setHomeAddr(homeAddr);
        if(!homeAddr.equals(prehomeAddr)){
          ContractChg contractChg1 = new ContractChg();
          contractChg1.setContractId(contractId);//��ͬ���
          contractChg1.setChgColumn("��ͥ��ַ");//�޸��ֶ�
          contractChg1.setChgBefInfo(prehomeAddr);//�޸�ǰ��Ϣ
          contractChg1.setChgEndInfo(homeAddr);//�޸ĺ���Ϣ
          contractChg1.setOpTime(new Date());//����ʱ��
          contractChg1.setOperator(operator);
          String contractType = BusiConst.PLPREPAYMENTFEES_BIALINFO + "";
          contractChg1.setContractType(contractType);//�޸ĺ�ͬ���ͣ�6 ��֤����Ϣ
          contractChgDAO.insert(contractChg1);
          }
    
        assurer.setHomeMail(homeMail);
        if(!homeMail.equals(prehomeMai)){
          ContractChg contractChg1 = new ContractChg();
          contractChg1.setContractId(contractId);//��ͬ���
          contractChg1.setChgColumn("��ͥ��������");//�޸��ֶ�
          contractChg1.setChgBefInfo(prehomeMai);//�޸�ǰ��Ϣ
          contractChg1.setChgEndInfo(homeMail);//�޸ĺ���Ϣ
          contractChg1.setOpTime(new Date());//����ʱ��
          contractChg1.setOperator(operator);
          String contractType = BusiConst.PLPREPAYMENTFEES_BIALINFO + "";
          contractChg1.setContractType(contractType);//�޸ĺ�ͬ���ͣ�6 ��֤����Ϣ
          contractChgDAO.insert(contractChg1);
          }
     
        assurer.setOrgName(orgName);
        if(!orgName.equals(preorgName)){
          ContractChg contractChg1 = new ContractChg();
          contractChg1.setContractId(contractId);//��ͬ���
          contractChg1.setChgColumn("��λ����");//�޸��ֶ�
          contractChg1.setChgBefInfo(preorgName);//�޸�ǰ��Ϣ
          contractChg1.setChgEndInfo(orgName);//�޸ĺ���Ϣ
          contractChg1.setOpTime(new Date());//����ʱ��
          contractChg1.setOperator(operator);
          String contractType = BusiConst.PLPREPAYMENTFEES_BIALINFO + "";
          contractChg1.setContractType(contractType);//�޸ĺ�ͬ���ͣ�6 ��֤����Ϣ
          contractChgDAO.insert(contractChg1);
          }
     
        assurer.setOrgAddr(orgAddr);
        if(!orgAddr.equals(preorgAddr)){
          ContractChg contractChg1 = new ContractChg();
          contractChg1.setContractId(contractId);//��ͬ���
          contractChg1.setChgColumn("��λ��ַ");//�޸��ֶ�
          contractChg1.setChgBefInfo(preorgAddr);//�޸�ǰ��Ϣ
          contractChg1.setChgEndInfo(orgAddr);//�޸ĺ���Ϣ
          contractChg1.setOpTime(new Date());//����ʱ��
          contractChg1.setOperator(operator);
          String contractType = BusiConst.PLPREPAYMENTFEES_BIALINFO + "";
          contractChg1.setContractType(contractType);//�޸ĺ�ͬ���ͣ�6 ��֤����Ϣ
          contractChgDAO.insert(contractChg1);
          }
     
        assurer.setOrgTel(orgTel);
        if(!orgTel.equals(preorgTel)){
          ContractChg contractChg1 = new ContractChg();
          contractChg1.setContractId(contractId);//��ͬ���
          contractChg1.setChgColumn("��λ�绰");//�޸��ֶ�
          contractChg1.setChgBefInfo(preorgTel);//�޸�ǰ��Ϣ
          contractChg1.setChgEndInfo(orgTel);//�޸ĺ���Ϣ
          contractChg1.setOpTime(new Date());//����ʱ��
          contractChg1.setOperator(operator);
          String contractType = BusiConst.PLPREPAYMENTFEES_BIALINFO + "";
          contractChg1.setContractType(contractType);//�޸ĺ�ͬ���ͣ�6 ��֤����Ϣ
          contractChgDAO.insert(contractChg1);
          }
    
        assurer.setOrgMail(orgMail);
        if(!orgMail.equals(preorgMail)){
          ContractChg contractChg1 = new ContractChg();
          contractChg1.setContractId(contractId);//��ͬ���
          contractChg1.setChgColumn("��λ��������");//�޸��ֶ�
          contractChg1.setChgBefInfo(preorgMail);//�޸�ǰ��Ϣ
          contractChg1.setChgEndInfo(orgMail);//�޸ĺ���Ϣ
          contractChg1.setOpTime(new Date());//����ʱ��
          contractChg1.setOperator(operator);
          String contractType = BusiConst.PLPREPAYMENTFEES_BIALINFO + "";
          contractChg1.setContractType(contractType);//�޸ĺ�ͬ���ͣ�6 ��֤����Ϣ
          contractChgDAO.insert(contractChg1);
          }
      String button = BusiLogConst.BIZLOG_ACTION_MODIFY + "";
      String bizId = assurer.getId().toString();
      this.addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId,contractId);
    }
  } else {// �����ڣ�����
    String pl123Id = assurerDAO.queryIdYU(contractId, empName, cardNum);
    if(pl123Id != null){
      throw new BusinessException("��ְ���Ѿ�Ϊ��֤�ˣ�");
    }else{
    assurer = new Assurer();
    if (contractId != null && !"".equals(contractId)) {
      assurer.setContractId(contractId);
    }
    // if(debitter != null && !"".equals(debitter)){
    // Borrower borrower = new Borrower();
    // borrower.setContractId(contractId);
    // borrower.setBorrowerName(debitter);
    // borrowerDAO.insert(borrower);
    // }
    if (empId != null && !"".equals(empId)) {
      assurer.setEmpId(new BigDecimal(empId));
    }
    if (empName != null && !"".equals(empName)) {
      assurer.setEmpName(empName);
    }
    if (cardKind != null && !"".equals(cardKind)) {
      assurer.setCardKind(cardKind);
    }
    if (cardNum != null && !"".equals(cardNum)) {
      assurer.setCardNum(cardNum);
    }
    if (sex != null && !"".equals(sex)) {
      assurer.setSex(sex);
    }
    if (birthday != null && !"".equals(birthday)) {
      assurer.setBirthday(birthday);
    }
    if (salary != null && !"".equals(salary)) {
      assurer.setSalary(new BigDecimal(salary));
    }
    if (monthPay != null && !"".equals(monthPay)) {
      assurer.setMonthPay(new BigDecimal(monthPay));
    }
    if (balance != null && !"".equals(balance)) {
      assurer.setBalance(new BigDecimal(balance));
    }
    if (empSt != null && !"".equals(empSt)) {
      int tempEmpSt = BusiTools.getBusiKey(empSt, BusiConst.OLDPAYMENTSTATE);
      assurer.setEmpSt(new Integer(tempEmpSt).toString());
    }
    if (tel != null && !"".equals(tel)) {
      assurer.setTel(tel);
    }
    if (mobile != null && !"".equals(mobile)) {
      assurer.setMobile(mobile);
    }
    if (homeTel != null && !"".equals(homeTel)) {
      assurer.setHomeTel(homeTel);
    }
    if (homeAddr != null && !"".equals(homeAddr)) {
      assurer.setHomeAddr(homeAddr);
    }
    if (homeMail != null && !"".equals(homeMail)) {
      assurer.setHomeMail(homeMail);
    }
    if (orgId != null && !"".equals(orgId)) {
      assurer.setOrgId(new BigDecimal(orgId));
    }
    if (orgName != null && !"".equals(orgName)) {
      assurer.setOrgName(orgName);
    }
    if (orgAddr != null && !"".equals(orgAddr)) {
      assurer.setOrgAddr(orgAddr);
    }
    if (orgTel != null && !"".equals(orgTel)) {
      assurer.setOrgTel(orgTel);
    }
    if (orgMail != null && !"".equals(orgMail)) {
      assurer.setOrgMail(orgMail);
    }
    assurer.setStatus("0");// ��֤��״̬ 0����
   
    //����PL111
    this.updatePl111(contractId);
    String assurerId = assurerDAO.insert(assurer).toString();
    String button = BusiLogConst.BIZLOG_ACTION_ADD + "";
    String bizId = assurerId;
    
    //����PL210
    CongealInfo congealInfo = new CongealInfo();
    if(contractId != null && !"".equals(contractId)){
      congealInfo.setContractId(contractId);
    }
    if(orgId != null && !"".equals(orgId)){
      congealInfo.setOrgId(new BigDecimal(orgId));
    }
    if(empId != null && !"".equals(empId)){
      congealInfo.setEmpId(new BigDecimal(empId));
    }
    if(empName != null && !"".equals(empName)){
      congealInfo.setEmpName(empName);
    }
    if(cardKind != null && !"".equals(cardKind)){
      congealInfo.setCardKind(cardKind);
    }
    if(cardNum != null && !"".equals(cardNum)){
      congealInfo.setCardNum(cardNum);
    }
    congealInfo.setPersonId(assurerId);
    congealInfo.setStatus(BusiConst.PLPREPAYMENTFEES_CONGEALINFOGELATION+"");//0 ����
    congealInfo.setType(BusiConst.PLPREPAYMENTFEES_BIALTYPE+"");//3 ��֤��
    congealInfoDAO.insert(congealInfo);
    this.addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId,contractId);
    }
  }
}
/**
 * Td ����
 */
public void deleteAssurer(String id, Pagination pagination, SecurityInfo securityInfo, HttpServletRequest request) throws Exception, BusinessException {
  // TODO Auto-generated method stub
  Assurer assurer = null;
  String operator = securityInfo.getUserName();
  String opIp = securityInfo.getUserIp();
  String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
  String model = BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_ASSURER + "";
  if (id != null && !"".equals(id)) {
    assurer = assurerDAO.queryById(new Integer(id));
    String bizId = assurer.getId().toString();
    String contractId = assurer.getContractId();
    String status = assurer.getStatus();
    if (status.equals("0")) {
      assurer.setStatus("1");//����״̬
      BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);//����pl111 ��ͬ״̬��7;
      borrowerAcc.setContractSt("7");
      //����PL210
      String statu = BusiConst.PLPREPAYMENTFEES_CONGEALINFOTHAW+"";
      congealInfoDAO.updateCongealInfo(statu, id, contractId);
      String button = BusiLogConst.BIZLOG_ACTION_DELETE + "";
      this.addPlOperateLog(opSys, model, button, bizId, opIp, operator,
          bizId,contractId);
    } else {
      throw new BusinessException("�ü�¼�����ϣ�");
    }
  }
}
  
}
