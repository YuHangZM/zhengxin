package org.xpup.hafmis.sysloan.loancallback.destoryback.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.destoryback.dto.DestoryBackTaDTO;
import org.xpup.hafmis.sysloan.loancallback.destoryback.form.DestoryBackTaAF;
import org.xpup.hafmis.sysloan.loancallback.destoryback.form.DestoryBackTbAF;

public interface IDestoryBackBS {
  
  /**
   *��������ջ�- ͨ�������˺Ŵ���ҳ����Ϣ
   * @param destoryBackTaDTO
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public DestoryBackTaAF queryContractInfo(String loanKouAcc,
      SecurityInfo securityInfo) throws Exception, BusinessException;

 
  /**
   * ��������ջ�-����ҳ����Ϣ
   * @param destoryBackTaDTO
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public void saveDestoryBack(DestoryBackTaDTO destoryBackTaDTO,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  /**
   * �����ջ�ά��-�����ջ�ά����Ϣ�б�
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public DestoryBackTbAF queryDestoryBackTbShowListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  /**
   * Tb �����ջ�ά��ɾ��
   * @param id
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public void deleteDestoryBackInfo(String flowHeadId,SecurityInfo securityInfo) throws Exception,BusinessException;

  /**
   * Tb �����ջ�ά���б��ӡ
   * @param pagination
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public List findDestoryBackTbPrint(Pagination pagination,SecurityInfo securityInfo) throws Exception;
  /**
   * Tb �����ջ�ά��������
   * @param pagination
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public DestoryBackTaDTO  queryDestoryBackTbWindowById(String flowHeadId,
      SecurityInfo securityInfo) throws Exception, BusinessException;
}
