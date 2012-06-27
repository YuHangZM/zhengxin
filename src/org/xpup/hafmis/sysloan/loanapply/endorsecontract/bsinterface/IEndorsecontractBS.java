package org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.dto.EndorsecontractTaDTO;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTaAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTaChangeLoanMonthRateAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTbAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTcAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTdAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTeAF;

public interface IEndorsecontractBS {

  public List queryBankList(String contractId,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * ҳ����ʾ�Ĳ�ѯ
   * @param id
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public EndorsecontractTaAF queryContractInfo(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request,String insert) throws Exception,BusinessException;
  public String queryPL121Contract(String contractid) throws Exception,BusinessException;
  //���ڴ�ӡ�͵�����
  public EndorsecontractTaAF queryContractInfo_(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  public EndorsecontractTaAF queryContractInfo_wy(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * ��ѯPL003��ParamValue�ֶ�
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public String queryParamValue(SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * �ַ�ȷ�ϰ�ť
   * @param form
   * @param securityInfo
   * @throws Exception
   * @throws BusinessException
   */
  public void endorsecontractTaMaitainSure(String loanassistantorgId,EndorsecontractTaAF endorsecontractTaAF,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * �ַ�ɨ�谴ť
   * @param endorsecontractTaAF
   * @param securityInfo
   * @throws Exception
   * @throws BusinessException
   */
  public void endorsecontractTaMaitainScan(EndorsecontractTaAF endorsecontractTaAF,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * ��ѯ��Ѻ��ͬ��Ϣ
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public EndorsecontractTbAF queryPledgeContractList(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  //���ڴ�ӡ
  public EndorsecontractTbAF queryPledgeContractList_(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  //��Ѻ��ͬ���ר��
  public EndorsecontractTbAF queryPledgeContractList_Chg(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * TbTOP�ַ�ȷ����ť
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public void sure(Integer id,String loanassistantorgId,SecurityInfo securityInfo,EndorsecontractTbAF endorsecontractTbAF) throws Exception,BusinessException;
  /**
   * ��ѯPL121ʵ��������set��AF
   * @param contractId
   * @param id(PL121Id)
   * @param debitter
   * @param office
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public EndorsecontractTbAF updatePledgeContract(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * �ַ�ɾ��Pl121
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public void deletePledgeContract(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * Tc��ѯ
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public EndorsecontractTcAF queryImpawnContractList(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
 //���ڴ�ӡ
  public EndorsecontractTcAF queryImpawnContractList_(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  //������Ѻ���ר��
  public EndorsecontractTcAF queryImpawnContractList_Chg(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * Tc �ж�Pl121
   * @param id
   * @param securityInfo
   * @param endorsecontractTcAF
   * @throws Exception
   * @throws BusinessException
   */
  public void sureTc(String id,String loanassistantorgId,SecurityInfo securityInfo,EndorsecontractTcAF endorsecontractTcAF) throws Exception,BusinessException;
  /**
   * TC �ַ� �޸İ�ť 
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public EndorsecontractTcAF updateImpawnContract(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * TC �ַ� ɾ����ť
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @throws Exception
   * @throws BusinessException
   */
  public void deleteImpawnContract(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * TD ��ѯ
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public EndorsecontractTdAF queryAssurerList(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  public EndorsecontractTdAF queryAssurerListByEmpId(String id,String orgId,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  //���ڴ�ӡ
  public EndorsecontractTdAF queryAssurerList_(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  public EndorsecontractTdAF queryAssurerList_Chg(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * Td �ַ� ȷ����ť
   * @param id
   * @param securityInfo
   * @param endorsecontractTcAF
   * @throws Exception
   * @throws BusinessException
   */
  public void sureTd(String id,SecurityInfo securityInfo,EndorsecontractTdAF endorsecontractTdAF) throws Exception,BusinessException;
  /**
   * Td �ַ� �޸İ�ť
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public EndorsecontractTdAF updateAssurer(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * Td �ַ� ɾ����ť
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @throws Exception
   * @throws BusinessException
   */
  public void deleteAssurer(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * Td ��֤�˵����� ͨ��ID��ѯPL123 empId
   * @param id
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public String selectAssurerEmpIdById(String id,SecurityInfo securityInfo) throws Exception,BusinessException;
  /**
   * Te ά����ѯ
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public EndorsecontractTeAF queryList(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  /**
   * Te ɾ��
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @throws Exception
   * @throws BusinessException
   */
  public void deleteContract(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * Te ǩ����ͬ
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @throws Exception
   * @throws BusinessException
   */
  public void sureContract(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * Te ����ǩ����ͬ
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @throws Exception
   * @throws BusinessException
   */
  public void delContract(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * ����ta�е�path
   * @param contractId
   * @param path
   * @throws Exception
   */
  public void updateScanTa_Yqf(String contractId, String path)throws Exception;
  /**
   * ����tb�е�path
   * @param contractId
   * @param path
   * @throws Exception
   */
  public void updateScanTb_Yqf(String contractId, String path)throws Exception;
  public void updateScanTe_Yqf(String contractId, String path)throws Exception;
  /**
   * ����tc�е�path
   * @param contractId
   * @param path
   * @throws Exception
   */
  public void updateScanTc_Yqf(String contractId, String path)throws Exception;
  /**
   * ����td�е�path
   * @param contractId
   * @param path
   * @throws Exception
   */
  public void updateScanTd_Yqf(String contractId, String path)throws Exception;
  /**
   *����������ȷ�Ƿ����������
   * ���ҳ����µ�����
   * @param bankId,term,loanMonthRate
   * @return loanMonthRate
   */
  public EndorsecontractTaChangeLoanMonthRateAF queryLoanMonthRate(String bankId,String term,String loanMonthRate,String loanMode,String loanMoney);
  
  /**
   * hanl
   * ���º�ͬ״̬
   * @param contractId
   */
  public void updateContractSt(String contractId)throws Exception;
}
