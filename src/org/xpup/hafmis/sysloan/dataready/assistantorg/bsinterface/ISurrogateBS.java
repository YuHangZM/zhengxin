package org.xpup.hafmis.sysloan.dataready.assistantorg.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.assistantorg.dto.SurrogateAFDTO;

public interface ISurrogateBS {
  /**
   * name ����
   * ����˾ά��-��ʾ�б�
   */
  public List findSurrogateList(Pagination pagination) throws Exception, BusinessException;
/**
 * name ����
 * ����˾ά�� �����¼�¼��**���и�**��
 */
  public void insertSurrogateList(SurrogateAFDTO surrogateAFDTO,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * name ����
   * ����˾ά�� ͨ��id��ѯ���������޸�
   */
  public SurrogateAFDTO findSurrogateID(Integer id)throws Exception,BusinessException;
  /**
   * name ����
   * ����˾ά��,�޸����ݣ�**���и�**��
   */
  public void updateSurrogate(SurrogateAFDTO surrogateAFDTO,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * name ����
   * ����idɾ��PL007����
   */
  public String deleteSurrogate(Integer id,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * name ����
   *����id �ж��Ƿ��м�¼
   *true �д˼�¼
   *false �޴˼�¼
   */
  public boolean is_Surrogate_YM(Integer id)throws Exception,BusinessException;


}
