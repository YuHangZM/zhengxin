package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowBalanceEndTransfer;

public class OrgHAFAccountFlowBalanceEndTransferDAO extends HibernateDaoSupport{

  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public OrgHAFAccountFlowBalanceEndTransfer queryById(Integer id) {
    Validate.notNull(id);
    return  (OrgHAFAccountFlowBalanceEndTransfer) getHibernateTemplate().get(OrgHAFAccountFlowBalanceEndTransfer.class,id);    
  }
  /**
   * �����¼
   * @param OrgHAFAccountFlowBalanceEndTransfer
   * @return
   */
  public Serializable insert(OrgHAFAccountFlowBalanceEndTransfer orgHAFAccountFlowBalanceEndTransfer){
    Serializable id = null;
    try{    
    Validate.notNull(orgHAFAccountFlowBalanceEndTransfer);
    id = getHibernateTemplate().save(orgHAFAccountFlowBalanceEndTransfer); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}
