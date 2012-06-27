package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowOrgAddPay;

/**
 * ��λס������ҵ����ˮ-��λ����M
 * 
 *@author ���
 *2007-6-25
 */
public class OrgHAFAccountFlowOrgAddPayDAO extends HibernateDaoSupport{
  
  public OrgHAFAccountFlowOrgAddPay queryById(Serializable id){
 
    Validate.notNull(id);
    return (OrgHAFAccountFlowOrgAddPay) getHibernateTemplate().get(OrgHAFAccountFlowOrgAddPay.class,id);
  }
  
  public Serializable insert(OrgHAFAccountFlowOrgAddPay orgHAFAccountFlowOrgAddPay){
    Serializable id = null;
    try{
      id = getHibernateTemplate().save(orgHAFAccountFlowOrgAddPay);
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}