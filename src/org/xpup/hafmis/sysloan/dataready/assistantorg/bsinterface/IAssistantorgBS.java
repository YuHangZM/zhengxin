package org.xpup.hafmis.sysloan.dataready.assistantorg.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.assistantorg.dto.AssistantorgAFDTO;

public interface IAssistantorgBS {
  /**
   * name ����
   * ������˾ά��-��ʾ�б�
   */
  public List findAssistantorgList(Pagination pagination) throws Exception, BusinessException;
/**
 * name ����
 * ������˾ά�� �����¼�¼��**���и�**��
 */
  public void insertAssistantorgList(AssistantorgAFDTO assistantorgDTO,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * name ����
   * ������˾ά�� ͨ��id��ѯ���������޸�
   */
  public AssistantorgAFDTO findAssistantorgID(Integer id)throws Exception,BusinessException;
  /**
   * name ����
   * ������˾ά��,�޸�����   ��**���и�**��
   */
  public void updateAssistantOrg(AssistantorgAFDTO assistantorgDTO,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * name ����
   * ����idɾ��PL007����
   */
  public String deleteAssistantOrg(Integer id,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * name ����
   *����id �ж��Ƿ��м�¼
   *true �д˼�¼
   *false �޴˼�¼
   */
  public boolean is_asistantOrg_YM(Integer id)throws Exception,BusinessException;

}
