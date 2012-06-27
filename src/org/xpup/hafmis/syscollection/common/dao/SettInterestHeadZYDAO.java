package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.SettInterestHeadZY;

public class SettInterestHeadZYDAO extends HibernateDaoSupport{

  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public SettInterestHeadZY queryById(Integer id) {
    Validate.notNull(id);
    return  (SettInterestHeadZY) getHibernateTemplate().get(SettInterestHeadZY.class,id);    
  }
  /**
   * �����¼
   * @param chgPaymentHead
   * @return
   */
  public Serializable insert(SettInterestHeadZY settInterestHeadZY){
    Serializable id = null;
    try{    
    Validate.notNull(settInterestHeadZY);
    id = getHibernateTemplate().save(settInterestHeadZY);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}
