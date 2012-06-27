package org.xpup.hafmis.syscollection.common.daoDW;

import java.io.Serializable;

import org.apache.commons.lang.Validate;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.xpup.hafmis.syscollection.common.domain.entity.TranOutHead;


/**
 * ת��ͷ��
 * 
 * @author ��� 2007-6-19
 */
public class TranOutHeadDAODW extends HibernateDaoSupport {
  /**
   * ����������ѯ
   * 
   * @param id
   * @return
   */
  public TranOutHead queryById(Serializable id) {
   
    Validate.notNull(id);
  
   return (TranOutHead) getHibernateTemplate().get(TranOutHead.class, id);
  }
  /**
   * �����¼
   * 
   * @param empInfo
   * @return
   */
  public Serializable insert(TranOutHead tranOutHead) {
    Serializable id = null;
    try {
      Validate.notNull(tranOutHead);
      id = getHibernateTemplate().save(tranOutHead);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id.toString();
  }
  
  
}



