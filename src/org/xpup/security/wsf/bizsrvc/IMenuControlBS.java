package org.xpup.security.wsf.bizsrvc;

import java.io.Serializable;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.security.common.domain.MenuItem;

public interface IMenuControlBS {

  public List loadMenuItemTree();

  public List findAllMenuItemsByUserId(Serializable userId);

  public List findAllMenuItemsByRoleId(Serializable roleId);

  public MenuItem findMenuItem(Serializable id) throws BusinessException;

  /**
   * �����û���ӵ�е����и��˵���
   * 
   * @param username �û���¼��
   * @return ���˵��б�
   */
  public String getMenu(final String username, final Serializable parentId)throws Exception;  
  
  
  public List findAllRootMenu(String username);

  /**
   * ���ظ��˵����û���ӵ�е������Ӳ˵���
   * 
   * @param username �û���¼��
   * @param parentId ���˵�ID
   * @return �Ӳ˵��б�
   */
  
  public String getMenu(final String username,
      final Serializable parentId, String url)throws Exception;  
  public List findAllMenu(String username, String parentId);

  /**
   * ��ָ���Ĳ˵�������ָ����ɫ��
   * 
   * @param roleId ��ɫID
   * @param menuIds �˵���ID�б�
   */
  public void assignMenuItemsToRole(Serializable roleId, List menuItemIds)
      throws BusinessException;

  /**
   * ��ָ���Ĳ˵�������ָ���û���
   * 
   * @param roleId �û�ID
   * @param menuIds �˵���ID�б�
   */
  public void assignMenuItemsToUser(Serializable userId, List menuItemIds)
      throws BusinessException;

  
  public MenuItem queryMenuItem(final String username,final String parentId);
}