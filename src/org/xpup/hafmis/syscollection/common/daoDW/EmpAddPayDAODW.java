/**
 * Copy Right Information   : Goldsoft 
 * Project                  : EmpAddPayDAODW
 * @version                 : 1.0
 * @author                  : wangy
 * ��������                   :2007-12-17
 **/
package org.xpup.hafmis.syscollection.common.daoDW;

import java.io.Serializable;
import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpAddPay;

/**
 * ���˲���_���İ�_������λ��
 * 
 * @author wangy 2007-12-17
 */
public class EmpAddPayDAODW extends HibernateDaoSupport{
  
  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public EmpAddPay queryById(Serializable id) {  
    Validate.notNull(id);
    return  (EmpAddPay) getHibernateTemplate().get(EmpAddPay.class,id);    
  }

}

