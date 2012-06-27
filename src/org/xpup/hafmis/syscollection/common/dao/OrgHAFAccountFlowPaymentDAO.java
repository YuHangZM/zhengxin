package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowPayment;

public class OrgHAFAccountFlowPaymentDAO extends HibernateDaoSupport{

  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public OrgHAFAccountFlowPayment queryById(Integer id) {
    Validate.notNull(id);
    return  (OrgHAFAccountFlowPayment) getHibernateTemplate().get(OrgHAFAccountFlowPayment.class,id);    
  }
  /**
   * �����¼
   * @param OrgHAFAccountFlowPayment
   * @return
   */
  public Serializable insert(OrgHAFAccountFlowPayment orgHAFAccountFlowPayment){
    Serializable id = null;
    try{    
    Validate.notNull(orgHAFAccountFlowPayment);
    id = getHibernateTemplate().save(orgHAFAccountFlowPayment); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}
