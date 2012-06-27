package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgAgentDetail;

/**
 * Copy Right Information : ��λ�������� Goldsoft Project : OrgAgentDetailDAO
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.12.17
 */
public class OrgAgentDetailDAO extends HibernateDaoSupport {
  
  /**
   * ������Ϣ
   * 
   * @param agentDetail
   * @return
   * @author ���Ʒ�
   */
  public Serializable insert(OrgAgentDetail orgAgentDetail) {
    Serializable id = null;
    try {
      Validate.notNull(orgAgentDetail);
      id = getHibernateTemplate().save(orgAgentDetail);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
}
