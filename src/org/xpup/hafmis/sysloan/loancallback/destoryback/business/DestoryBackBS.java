package org.xpup.hafmis.sysloan.loancallback.destoryback.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.CollParaDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.PlBizActiveLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumCancelDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumMaintainDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowHead;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowTail;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.loancallback.destoryback.bsinterface.IDestoryBackBS;
import org.xpup.hafmis.sysloan.loancallback.destoryback.dto.DestoryBackTaDTO;
import org.xpup.hafmis.sysloan.loancallback.destoryback.dto.DestoryBackTbTotleDTO;
import org.xpup.hafmis.sysloan.loancallback.destoryback.form.DestoryBackTaAF;
import org.xpup.hafmis.sysloan.loancallback.destoryback.form.DestoryBackTbAF;


public class DestoryBackBS implements IDestoryBackBS {
  private BorrowerAccDAO borrowerAccDAO = null;// ������˻��� PL111

  private LoanFlowHeadDAO loanFlowHeadDAO = null;// ��ˮͷ�� PL202

  private LoanFlowTailDAO loanFlowTailDAO = null;//β��ͷ�� PL203
  
  private PlBizActiveLogDAO plBizActiveLogDAO = null;// ҵ����־ PL020

  private PlOperateLogDAO plOperateLogDAO = null;// ������־ PL021
  
  private PlDocNumCancelDAO plDocNumCancelDAO=null;
 
  private CollBankDAO       collBankDAO=null;
  private CollParaDAO       collParaDAO=null;
  private  PlDocNumMaintainDAO plDocNumMaintainDAO=null;
  
  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setLoanFlowTailDAO(LoanFlowTailDAO loanFlowTailDAO) {
    this.loanFlowTailDAO = loanFlowTailDAO;
  }
  public void setPlBizActiveLogDAO(PlBizActiveLogDAO plBizActiveLogDAO) {
    this.plBizActiveLogDAO = plBizActiveLogDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }
  public void setPlDocNumCancelDAO(PlDocNumCancelDAO plDocNumCancelDAO) {
    this.plDocNumCancelDAO = plDocNumCancelDAO;
  }
  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setPlDocNumMaintainDAO(PlDocNumMaintainDAO plDocNumMaintainDAO) {
    this.plDocNumMaintainDAO = plDocNumMaintainDAO;
  }
  /**
   * ��������ջ�- ͨ�������˺Ŵ���ҳ����Ϣ
   * 
   * @author ��� 2007-10-16
   */
  public DestoryBackTaAF queryContractInfo(String loanKouAcc,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    DestoryBackTaAF destoryBackTaAF = new DestoryBackTaAF();
    // ���ݴ����˺���PL111�в�ѯ״̬Ϊ��������11��12��13�ļ�¼����
    List temp1 = borrowerAccDAO.queryDestoryBackNumByLoanKouAcc_WU(loanKouAcc,
        securityInfo);
    if (!temp1.isEmpty()) {
      // ���ݴ����˺���PL202�в�ѯ״̬Ϊ1�����������������ļ�¼
      List temp2 = loanFlowHeadDAO.queryDestoryBackByLoanKouAcc_WU(loanKouAcc,
          securityInfo);     
      if (temp2.isEmpty()) {
        // ���ݴ����˺���PL111��PL110�в�ѯ��¼
        DestoryBackTaDTO destoryBackTaDTO = borrowerAccDAO.queryDestoryBackByLoanKouAcc_WU(
            loanKouAcc, securityInfo);

        String contractId = destoryBackTaDTO.getContractId();// ��ͬ���
        
        String borrowerName = destoryBackTaDTO.getBorrowerName();// ���������

        String cardKind = destoryBackTaDTO.getCardKind(); // ֤������

        String cardKindName = destoryBackTaDTO.getCardKindName(); // ��ʾ֤�����Ͷ�Ӧ������

        String cardNum = destoryBackTaDTO.getCardNum(); // ֤������

        BigDecimal overplusLoanMoney = destoryBackTaDTO.getOverplusLoanMoney();// ʣ�౾��

        String loanMode = destoryBackTaDTO.getLoanMode();// ���ʽ

        String loanModeName = destoryBackTaDTO.getLoanModeName();// ���ʽ

        BigDecimal noBackMoney = destoryBackTaDTO.getNoBackMoney();// ����δ�ջؽ��

        destoryBackTaAF.setLoanKouAcc(loanKouAcc);
        destoryBackTaAF.setBorrowerName(borrowerName);
        destoryBackTaAF.setContractId(contractId);
        destoryBackTaAF.setCardKind(cardKind);
        destoryBackTaAF.setCardKindName(cardKindName);
        destoryBackTaAF.setCardNum(cardNum);
        destoryBackTaAF.setOverplusLoanMoney(overplusLoanMoney);
        destoryBackTaAF.setLoanMode(loanMode);
        destoryBackTaAF.setLoanModeName(loanModeName);
        destoryBackTaAF.setNoBackMoney(noBackMoney);

      } else {
        throw new BusinessException("�ô����˺��´���δ���˵�ҵ�񣬲��ܽ��к����ջ�!");
      }

    } else {
      throw new BusinessException("��ͬ״̬����ȷ��");
    }
    return destoryBackTaAF;
  }

  /**
   * ��������ջ�-��������ջ������Ϣ��ҳ����Ϣ��
   * 
   * @author ��� 2007-10-16
   */
  public void saveDestoryBack(DestoryBackTaDTO destoryBackTaDTO,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String operateName = securityInfo.getUserInfo().getUsername();// ����Ա
    String loanKouAcc = destoryBackTaDTO.getLoanKouAcc().trim();
    String contractId = destoryBackTaDTO.getContractId().trim();
    String backunit = destoryBackTaDTO.getBackUnit().trim();
    BigDecimal backMoney = destoryBackTaDTO.getBackMoney();
    String loanassistantorgId = destoryBackTaDTO.getLoanassistantorgId();
    BigDecimal loanBankId=borrowerAccDAO.queryById(contractId).getLoanBankId();
    // ���������ˮ��ͷ��PL202
    LoanFlowHead loanFlowHead = new LoanFlowHead();
     
    //ȡƾ֤��
     CollBank collBank = collBankDAO.getCollBankByCollBankid_(loanBankId.toString());
      String bizYearmonth = securityInfo.getUserInfo().getPlbizDate()
          .substring(0, 6);
      String officeId="";
      String loanDocNumDocument=collParaDAO.getLoanDocNumDesignPara();
      if(loanDocNumDocument.equals("1")){
        officeId=collBank.getOffice();
      }else{
        officeId=loanBankId.toString();
      }
      String docNum = plDocNumMaintainDAO.getDocNumdocNum(officeId,
          bizYearmonth);
    loanFlowHead.setDocNum(docNum);
    loanFlowHead.setBizDate(securityInfo.getUserInfo().getPlbizDate());//ҵ������
    if (backunit.equals("����")) {
      loanFlowHead.setBizType(new Integer(
          BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVERCENTER).toString());// ҵ������
    } else {
      loanFlowHead.setBizType(new Integer(
          BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVEROTHER).toString());// ҵ������
    }
    loanFlowHead.setShouldCount(new BigDecimal(0.00));//Ӧ������
    loanFlowHead.setShouldCorpus(new BigDecimal(0.00));//Ӧ����������
    loanFlowHead.setShouldInterest(new BigDecimal(0.00));//Ӧ����Ϣ
    loanFlowHead.setShouldOverdueCorpus(new BigDecimal(0.00));//Ӧ�����ڱ���
    loanFlowHead.setShouldOverdueInterest(new BigDecimal(0.00));//Ӧ��������Ϣ
    loanFlowHead.setShouldPunishInterest(new BigDecimal(0.00));//Ӧ����Ϣ
    loanFlowHead.setRealCount(new BigDecimal(0.00));// ʵ������
    loanFlowHead.setRealCorpus(new BigDecimal(0.00));//ʵ������
    loanFlowHead.setRealInterest(new BigDecimal(0.00));//ʵ����Ϣ
    loanFlowHead.setRealOverdueCorpus(new BigDecimal(0.00));//ʵ�����ڱ���
    loanFlowHead.setRealOverdueInterest(new BigDecimal(0.00));//ʵ��������Ϣ
    loanFlowHead.setRealPunishInterest(new BigDecimal(0.00));//ʵ����Ϣ
    loanFlowHead.setOccurMoney(backMoney);// �������
    loanFlowHead.setOtherInterest(new BigDecimal(0.00));//������Ϣ
    loanFlowHead.setLoanBankId(loanBankId);
    loanFlowHead.setBizSt(new Integer(BusiConst.BUSINESSSTATE_SURE).toString());// ҵ��״̬ 4��ȷ�ϣ�            
    loanFlowHead.setMakePerson(operateName);//�Ƶ���
    if (backunit.equals("������˾")) {
      loanFlowHead.setHedaiOrg(loanassistantorgId);
    }
    loanFlowHead.setIsFinance(new Integer(1));
    String flowHeadId = loanFlowHeadDAO.insert(loanFlowHead).toString();// ����PL202  ������flow_head_id
    //����Ʊ�ݺ�
    loanFlowHead.setNoteNum(flowHeadId);
    loanFlowHeadDAO.update(loanFlowHead);
    
    // ���������ˮ��β��PL203
    LoanFlowTail loanFlowTail = new LoanFlowTail();
    loanFlowTail.setFlowHeadId(new BigDecimal(flowHeadId));//ͷ����ˮ��
    loanFlowTail.setLoanKouAcc(loanKouAcc);//�����˺�
    loanFlowTail.setContractId(contractId);//��ͬ���
    loanFlowTail.setShouldCorpus(new BigDecimal(0.00));//Ӧ������
    loanFlowTail.setShouldInterest(new BigDecimal(0.00));//��Ӧ����Ϣ
    loanFlowTail.setShouldPunishInterest(new BigDecimal(0.00));//Ӧ����Ϣ
    loanFlowTail.setRealCorpus(new BigDecimal(0.00));//ʵ������
    loanFlowTail.setRealInterest(new BigDecimal(0.00));//ʵ����Ϣ
    loanFlowTail.setRealPunishInterest(new BigDecimal(0.00));//ʵ����Ϣ
    loanFlowTail.setOtherInterest(new BigDecimal(0.00));//������Ϣ
    loanFlowTail.setLoanType(new Integer(BusiConst.PLRECOVERTYPE_OTHER)
        .toString());// ��������
    loanFlowTail.setOccurMoney(backMoney);//�������
    loanFlowTailDAO.insert(loanFlowTail);
  }

  /**
   * �����ջ�ά��-�����ջ�ά����Ϣ�б�
   * 
   * @author ��� 2007-10-16
   */
  public DestoryBackTbAF queryDestoryBackTbShowListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    DestoryBackTbAF destoryBackTbAF = new DestoryBackTbAF();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String docNum = (String) pagination.getQueryCriterions().get("docNum");
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    String bizSt = (String) pagination.getQueryCriterions().get("bizSt");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    List destoryBackShowList = new ArrayList();
   
    destoryBackShowList = loanFlowHeadDAO.queryDestoryBackTbShowListByCriterions(start,
        orderBy, order, pageSize, page, securityInfo, loanKouAcc, contractId,
        borrowerName, cardNum, loanBankName, bizSt, docNum);
    if(!destoryBackShowList.isEmpty())
    destoryBackTbAF.setList(destoryBackShowList);
    
    // �ϼ���Ϣ
    BigDecimal reclaimCorpusTotle = new BigDecimal(0.00);// ���ս��-�ܶ�
    DestoryBackTbTotleDTO destoryBackTbTotleDTO = loanFlowHeadDAO
        .queryDestoryBackTbShowListTotleByCriterions(securityInfo, loanKouAcc,
            contractId, borrowerName, cardNum, loanBankName, bizSt, docNum);
    count=destoryBackTbTotleDTO.getCount();
    reclaimCorpusTotle=destoryBackTbTotleDTO.getReclaimCorpusTotle();
    destoryBackTbAF.setReclaimCorpusTotle(reclaimCorpusTotle);
    pagination.setNrOfElements(count);
    return destoryBackTbAF;
  }

  /**
   * �����ջ�ά��-�����ջ�ά��ɾ��
   * 
   * @author ��� 2007-10-16
   */
  public void deleteDestoryBackInfo(String flowHeadId,SecurityInfo securityInfo)
      throws Exception, BusinessException {
   
    try {
      
      //����flowHeadIdȡ��ͬ���
      String  contractId= loanFlowTailDAO.queryContractByHeadId_YU(flowHeadId);
        
      //ɾ��β��PL203��
      loanFlowTailDAO.deleteLoanFlowTailAll(flowHeadId);   
      
      //����flowHeadIDȡҵ��״̬
      LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(flowHeadId));
      String bizSt=loanFlowHead.getBizSt();    
      
      //ɾ��ͷ��PL202
      loanFlowHeadDAO.deleteLoanFlowHead(flowHeadId);
      
      // ����ƾ֤���PL221
      String officeCode = loanFlowHeadDAO.queryOfficeByBank(loanFlowHead.getLoanBankId().toString());
      String yearMonth = securityInfo.getUserInfo().getBizDate().substring(0, 6);// �鼯ҵ������
      String officeId="";
      String loanDocNumDocument=collParaDAO.getLoanDocNumDesignPara();
      if(loanDocNumDocument.equals("1")){
        officeId=officeCode;
      }else{
        officeId=loanFlowHead.getLoanBankId().toString();
      }
      plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum(), officeId,yearMonth);          
      
      //  ������־ pl021  
      String operateName = securityInfo.getUserInfo().getUsername();// ����Ա
      String userIp = securityInfo.getUserInfo().getUserIp();// ����ԱIP
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(new Integer(BusiLogConst.OP_SYSTEM_TYPE_LOAN).toString()));// ���� 2
      plOperateLog.setOpModel(new Integer(
          BusiLogConst.PL_OP_LOANRECOVER_CANRECOVER_MAINTAIN).toString());// �����ջ�-�����ջ�ά��

      plOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_DELETE)
              .toString());// ɾ�� 3
      plOperateLog
      .setOpBizId(new BigDecimal(flowHeadId));
      plOperateLog.setOpIp(userIp);
      plOperateLog.setContractId(contractId);
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(operateName);
      plOperateLogDAO.insert(plOperateLog); 
      //ɾ���ñ�ҵ������PL020
      plBizActiveLogDAO.deletePlBizActiveLogByFlowHeadId_WD(flowHeadId,bizSt);
    } catch (Exception e) {
      throw new BusinessException("ɾ��ʧ��!");
    }

  }

  /**
   * �����ջ�ά��-�����ջ�ά����ӡ
   * 
   * @author ��ϡ�2007-10-19
   */
  public List findDestoryBackTbPrint(Pagination pagination,SecurityInfo securityInfo) throws Exception {
    String docNum = (String) pagination.getQueryCriterions().get("docNum");
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    String bizSt = (String) pagination.getQueryCriterions().get("bizSt");
    
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    
    List resultList = new ArrayList();
    // �����ջ�ά����ӡ�б�
    resultList = loanFlowHeadDAO.queryDestoryBackTbShowListCountByCriterions(
        securityInfo, loanKouAcc, contractId, borrowerName, cardNum,
        loanBankName, bizSt, docNum);
   
    return  resultList;
  }
  /**
   *�����ջ�ά��-�����ջص�������ӡ
   * @param pagination
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public DestoryBackTaDTO queryDestoryBackTbWindowById(String flowHeadId, SecurityInfo securityInfo) throws Exception, BusinessException {

    DestoryBackTaDTO destoryBackTaDTO = new DestoryBackTaDTO();
    try {
      List list = loanFlowHeadDAO.querydestoryBackTbWindowById(flowHeadId, securityInfo);
      if (!list.isEmpty()) {
        destoryBackTaDTO = (DestoryBackTaDTO) list.get(0);
        // ֤�����Ͷ�Ӧ������
        destoryBackTaDTO.setCardKindName(BusiTools.getBusiValue(Integer
            .parseInt(destoryBackTaDTO.getCardKind()), BusiConst.DOCUMENTSSTATE));
        // ���ʽ
        destoryBackTaDTO.setLoanModeName(BusiTools.getBusiValue(Integer
            .parseInt(destoryBackTaDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));       
      } else {
        throw new BusinessException("�˺�ͬ�����ں����ջ���Ϣ");
      }
    } catch (BusinessException bx) {
      throw bx;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return destoryBackTaDTO;
  }

  public CollParaDAO getCollParaDAO() {
    return collParaDAO;
  }

  public void setCollParaDAO(CollParaDAO collParaDAO) {
    this.collParaDAO = collParaDAO;
  }
}

