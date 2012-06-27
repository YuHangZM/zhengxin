package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTaInfoDTO;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTbListDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

public interface IAdjustAccountBS {
  /**
   * ��ѯ���ʵ���������List�ķ���
   * 
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public List findAdjustAccountPopList(Pagination pagination, List loanbankList)
      throws Exception;

  /**
   * �жϽ�Ҫ����ҵ���Ƿ���ϵ���Ҫ��(���ݴ���ƾ֤��Ų�ѯ)
   * 
   * @param flowHeadId PL202������
   * @param loanbankList
   * @throws Exception
   * @throws BusinessException
   */
  public AdjustAccountTaInfoDTO judgeLoanFlowHead(String flowHeadId,
      List loanbankList,SecurityInfo securityInfo) throws Exception, BusinessException;

  /**
   * �жϽ�Ҫ����ҵ���Ƿ���ϵ���Ҫ��(���ݴ����ʺŲ�ѯ)
   * 
   * @param loanKouAcc
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public AdjustAccountTaInfoDTO judgeLoanKouAcc(String loanKouAcc, List loanbankList, SecurityInfo securityInfo)
  throws Exception, BusinessException;

  /**
   * ��ѯ���ʵ�����ԭʼ��Ϣ
   * 
   * @param flowHeadId PL202������
   * @param loanKouAcc �����ʺ�
   * @param bizType ҵ��״̬
   * @param autoOverPay�Ƿ��Զ�����
   * @return ���ڴ�ӡƾ֤�ģ�ƾ֤��
   * @throws Exception
   */
  public String saveAdjustAccountInfo(String flowHeadId, String loanKouAcc,
      String bizType, String autoOverPay, SecurityInfo securityInfo,
      AdjustAccountTaInfoDTO adjustAccountTaInfoDTO) throws Exception;

  /**
   * ��ѯ���ʵ���ά���б�
   * 
   * @param pagination
   * @param loanbankList
   * @return
   * @throws Exception
   */
  public Object[] findAdjustAccountList(Pagination pagination, List loanbankList)
      throws Exception;

  /**
   * ɾ�����ʵ�����Ϣ�ķ���
   * 
   * @param flowHeadId PL202���id
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public void deleteAdjustAccountInfo(String flowHeadId,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  /**
   * ��ѯ��ӡ��Ϣ�ķ���
   * 
   * @param flowHeadId PL202���id
   * @return
   * @throws Exception
   */
  public AdjustAccountTbListDTO findPrintInfo(String flowHeadId)
      throws Exception;

  /**
   * ��ѯPL201�еı�������Ϣ���������ж�
   * @param yearMonth ��������
   * @param loanKouAcc �����ʺ�
   * @return obj[0]����obj[1]��Ϣ
   * @throws Exception
   * @throws BusinessException
   */
  public Object[] findCorpusAndInterest(String yearMonth, String loanKouAcc, int plLoanReturnType)
      throws Exception, BusinessException;
  
  /**
   * ��ӡ��ѯ����
   * @param headId ͷ��id
   * @return
   * @throws Exception
   */
  public LoancallbackTaAF findPrintCallbackInfo(String headId) throws Exception;
}
