package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutOrg;

/**
 * ת����λ
 * 
 *@author ���
 *2007-6-20
 */
public class TranOutOrgDAO extends HibernateDaoSupport{

  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public TranOutOrg queryById(Integer id) {
    Validate.notNull(id);
    return  (TranOutOrg) getHibernateTemplate().get(TranOutOrg.class,id);    
  }
  /**
   * �����¼
   * @param tranOutOrg
   * @return
   */
  public Serializable insert(TranOutOrg tranOutOrg){
    Serializable id = null;
    try{    
    Validate.notNull(tranOutOrg);
    id = getHibernateTemplate().save(tranOutOrg); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
  

}
