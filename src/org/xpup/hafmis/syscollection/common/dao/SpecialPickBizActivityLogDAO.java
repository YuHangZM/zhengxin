package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.SpecialPickBizActivityLog;

/**
 * ҵ����־-������ȡ
 * 
 *@author ���
 *2007-6-20
 */
public class SpecialPickBizActivityLogDAO extends HibernateDaoSupport{
  
  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public SpecialPickBizActivityLog queryById(Serializable id) {  
    Validate.notNull(id);
    return  (SpecialPickBizActivityLog) getHibernateTemplate().get(SpecialPickBizActivityLog.class,id);    
  }
  /**
   * �����¼
   * @param specialPickBizActivityLog
   * @return
   */
  public Serializable insert(SpecialPickBizActivityLog specialPickBizActivityLog){
    Serializable id = null;
    try{    
    Validate.notNull(specialPickBizActivityLog);
    id = getHibernateTemplate().save(specialPickBizActivityLog);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
}