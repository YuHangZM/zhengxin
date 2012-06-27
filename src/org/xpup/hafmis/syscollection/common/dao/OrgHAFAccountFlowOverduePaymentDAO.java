package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowOverduePayment;

public class OrgHAFAccountFlowOverduePaymentDAO extends HibernateDaoSupport{

  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public OrgHAFAccountFlowOverduePayment queryById(Integer id) {
    Validate.notNull(id);
    return  (OrgHAFAccountFlowOverduePayment) getHibernateTemplate().get(OrgHAFAccountFlowOverduePayment.class,id);    
  }
  /**
   * �����¼
   * @param OrgHAFAccountFlowOverduePayment
   * @return
   */
  public Serializable insert(OrgHAFAccountFlowOverduePayment orgHAFAccountFlowOverduePayment){
    Serializable id = null;
    try{    
    Validate.notNull(orgHAFAccountFlowOverduePayment);
    id = getHibernateTemplate().save(orgHAFAccountFlowOverduePayment); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}
