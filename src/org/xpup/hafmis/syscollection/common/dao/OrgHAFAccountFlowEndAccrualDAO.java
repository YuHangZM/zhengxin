package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowEndAccrual;

public class OrgHAFAccountFlowEndAccrualDAO extends HibernateDaoSupport{

  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public OrgHAFAccountFlowEndAccrual queryById(Integer id) {
    Validate.notNull(id);
    return  (OrgHAFAccountFlowEndAccrual) getHibernateTemplate().get(OrgHAFAccountFlowEndAccrual.class,id);    
  }
  /**
   * �����¼
   * @param OrgHAFAccountFlowEndAccrual
   * @return
   */
  public Serializable insert(OrgHAFAccountFlowEndAccrual orgHAFAccountFlowEndAccrual){
    Serializable id = null;
    try{    
    Validate.notNull(orgHAFAccountFlowEndAccrual);
    id = getHibernateTemplate().save(orgHAFAccountFlowEndAccrual); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}