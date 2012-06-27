package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgChgLogSeal;

public class OrgChgLogSealDAO extends HibernateDaoSupport{

  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public OrgChgLogSeal queryById(Integer id) {
    Validate.notNull(id);
    return  (OrgChgLogSeal) getHibernateTemplate().get(OrgChgLogSeal.class,id);    
  }
  /**
   * �����¼
   * @param orgChgLogSeal
   * @return
   */
  public Serializable insert(OrgChgLogSeal orgChgLogSeal){
    Serializable id = null;
    try{    
    Validate.notNull(orgChgLogSeal);
    id = getHibernateTemplate().save(orgChgLogSeal); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}