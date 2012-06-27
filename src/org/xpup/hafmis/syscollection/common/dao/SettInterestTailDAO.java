package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.SettInterestTail;

/**
 * ��Ϣβ�� 
 * 
 *@author ���
 *2007-6-19
 */
public class SettInterestTailDAO extends HibernateDaoSupport{
  
  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public SettInterestTail queryById(Serializable id) {  
    Validate.notNull(id);
    return  (SettInterestTail) getHibernateTemplate().get(SettInterestTail.class,id);    
  }
  /**
   * �����¼
   * @param settInterestTail
   * @return
   */
  public Serializable insert(SettInterestTail settInterestTail){
    Serializable id = null;
    try{    
    Validate.notNull(settInterestTail);
    id = getHibernateTemplate().save(settInterestTail);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }

}
