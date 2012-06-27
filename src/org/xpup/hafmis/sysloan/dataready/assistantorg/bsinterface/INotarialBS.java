package org.xpup.hafmis.sysloan.dataready.assistantorg.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.assistantorg.dto.NotarialAFDTO;
public interface INotarialBS {
  /**
   * name ����
   * ��֤��ά��-��ʾ�б�
   */
  public List findNotarialList(Pagination pagination) throws Exception, BusinessException;
/**
 * name ����
 * ��֤��ά�� �����¼�¼
 */
  public void insertNotarialList(NotarialAFDTO notarialAFDTO,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * name ����
   * ��֤��ά�� ͨ��id��ѯ���������޸�
   */
  public NotarialAFDTO findNotarialID(Integer id)throws Exception,BusinessException;
  /**
   * name ����
   * ��֤��ά��,�޸�����
   */
  public void updateNotarial(NotarialAFDTO notarialAFDTO,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * name ����
   * ����idɾ��PL007����
   */
  public String deleteNotarial(Integer id,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * name ����
   *����id �ж��Ƿ��м�¼
   *true �д˼�¼
   *false �޴˼�¼
   */
  public boolean is_Notarial_YM(Integer id)throws Exception,BusinessException;



}
