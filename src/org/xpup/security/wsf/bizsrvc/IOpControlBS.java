package org.xpup.security.wsf.bizsrvc;

import java.io.Serializable;
import java.util.List;

import org.xpup.common.exception.BusinessException;

public interface IOpControlBS {

  /**
   * �ж��û��Ƿ�ӵ����Ӧ�Ĳ���Ȩ�ޡ������Ȩ�ޣ��������ء����û�У��׳��쳣��
   * 
   * @param username �û���¼��
   * @param opInnerName �������ڲ���
   * @throws BusinessException ����û�û����Ӧ��Ȩ�ޣ��׳����쳣��
   */
  public void decide(String username, String opInnerName)
      throws BusinessException;

  /**
   * Ϊָ����ɫ�������Ȩ�ޡ�
   * 
   * @param roleId ��ɫ��ID
   * @param operationIds ����ID��list
   */
  public void assignOperationsToRole(Serializable roleId, List operationIds)
      throws BusinessException;
  
  /**
   * Ϊָ���û��������Ȩ�ޡ�
   * 
   * @param userId ��ɫ��ID
   * @param operationIds ����ID��list
   */
  public void assignOperationsToUser(Serializable userId, List operationIds)
      throws BusinessException;

  public List findOperationsUnusedByUserId(Serializable userId);

  public List findOperationsByUserId(Serializable userId);

  public List findOperationsUnusedByRoleId(Serializable roleId);

  public List findOperationsByRoleId(Serializable roleId);
  public boolean checkIsOrg();

  public boolean checkIsCenter();
//�ж��Ƿ��е�λ��
  public boolean checkHaveOrg();
}
