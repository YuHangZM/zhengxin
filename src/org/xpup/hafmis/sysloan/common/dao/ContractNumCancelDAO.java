package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.sysloan.common.domain.entity.ContractNumCancel;

/**
 * ���Ϻ�ͬ��ű�PL102
 * 
 *@author ���
 *2007-9-13
 */
public class ContractNumCancelDAO extends HibernateDaoSupport {
  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public ContractNumCancel queryById(Serializable id) {  
    Validate.notNull(id);
    return  (ContractNumCancel) getHibernateTemplate().get(ContractNumCancel.class,id);    
  }
  /**
   * �����¼(����ƾ֤��)
   * 
   * @param contractNumCancel
   * @return
   */
  public Serializable insert(ContractNumCancel  contractNumCancel) {
    Serializable id = null;
    try {
      Validate.notNull(contractNumCancel);
      id = getHibernateTemplate().save(contractNumCancel);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
}
