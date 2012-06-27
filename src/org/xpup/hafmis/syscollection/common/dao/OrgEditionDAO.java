package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgEdition;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantBorrower;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.AssistantBorrowerDTO;

public class OrgEditionDAO extends HibernateDaoSupport {
  /**
   * ����������ѯ
   * 
   * @param id
   * @return
   */
  public OrgEdition queryById(Serializable id) {
    Validate.notNull(id);
    return (OrgEdition) getHibernateTemplate().get(
        OrgEdition.class, id);
  }

  /**
   * �����¼
   * 
   * @param OrgEdition
   * @return
   */
  public Serializable insert(OrgEdition orgEdition) {
    Serializable id = null;
    try {
      Validate.notNull(orgEdition);
      id = getHibernateTemplate().save(orgEdition);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  public OrgEdition queryByOrgId_sy(final Integer orgid){
    OrgEdition orgEdition=null;
    orgEdition = (OrgEdition) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from OrgEdition orgEdition where orgEdition.orgId=? ";
        Query query = session.createQuery(hql);
        query.setParameter(0, orgid);
        Object obj = query.uniqueResult();
        return obj;
      }
    });
    return orgEdition;
  }
  /**
   * ɾ��da002
   * ��λ���
   */
  public void deleteOrgEdition_sy(OrgEdition orgEdition){
    try {
      getHibernateTemplate().delete(orgEdition);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * �ж��Ƿ���ڵ�λ��
   *  @author ��� 20071218
   * @param org_id����λ���
   * @return ����trueΪ���ڵ�λ��
   */
  public boolean findIsOrg(final String org_id) {
    boolean flag = true;
    String count = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String sql = "select count(*) from DA002 da002 where da002.is_org=1 and da002.org_id=?";
        Query query = session.createSQLQuery(sql);
        query.setParameter(0, new Integer(org_id));
        return query.uniqueResult().toString();
      }
    });
    if (new Integer(count).intValue()==0) {
      flag = false;
    } 
    return flag;
  }
}
