package org.xpup.hafmis.sysloan.dataready.assistantorg.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.assistantorg.dto.InsuranceAFDTO;

public interface IInsuranceBS {
  /**
   * name ����
   * ���չ�˾ά��-��ʾ�б�
   */
  public List findInsuranceList(Pagination pagination) throws Exception, BusinessException;
/**
 * name ����
 * ���չ�˾ά�� �����¼�¼ ��**���и�**��
 */
  public void insertInsuranceList(InsuranceAFDTO insuranceAFDTO,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * name ����
   * ���չ�˾ά�� ͨ��id��ѯ���������޸�
   */
  public InsuranceAFDTO findInsuranceID(Integer id)throws Exception,BusinessException;
  /**
   * name ����
   * ���չ�˾ά��,�޸����ݣ�**���и�**��
   */
  public void updateInsurance(InsuranceAFDTO insuranceAFDTO,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * name ����
   * ����idɾ��PL007����
   */
  public String deleteInsurance(Integer id,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * name ����
   *����id �ж��Ƿ��м�¼
   *true �д˼�¼
   *false �޴˼�¼
   */
  public boolean is_Insurance_YM(Integer id)throws Exception,BusinessException;

}
