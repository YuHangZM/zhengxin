package org.xpup.hafmis.orgstrct.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.enums.OrderEnum;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.exception.EntityNotFoundException;
import org.xpup.common.exception.IllegalDuplicationException;
import org.xpup.hafmis.orgstrct.domain.OrgUnitPropTemplate;

public class OrgUnitPropTemplateDAO extends HibernateDaoSupport {

  public OrgUnitPropTemplate queryById(Serializable id) {
    Validate.notNull(id, "����id����Ϊ��");

    return (OrgUnitPropTemplate) getHibernateTemplate().get(
        OrgUnitPropTemplate.class, id);
  }

  public List queryByCriterions(final String name, final String description,
      final String orderBy, final OrderEnum order, final int start,
      final int pageSize) {

    Validate.notNull(orderBy, "����orderBy����Ϊ�գ�");
    Validate.notNull(order, "����order����Ϊ�գ�");
    Validate.isTrue(orderBy.matches("oupt\\.(id|name|innerName|description)$"),
        "����orderBy(" + orderBy
            + ")������Ҫ����������oupt.id, oupt.name, oupt.innerName, oupt.description�е�һ����");

    List oupts = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from OrgUnitPropTemplate oupt ";
        Vector parameters = new Vector();
        String criterion = "";

        if (name != null) {
          criterion += "oupt.name like ? escape '/' and ";
          parameters.add("%" + name + "%");
        }

        if (description != null) {
          criterion += "oupt.description like ? escape '/' and ";
          parameters.add("%" + description + "%");
        }

        if (criterion.length() != 0)
          criterion = "where "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        hql = hql + criterion + "order by " + orderBy + " " + order.getName();

        Query query = session.createQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }

        query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize);

        return query.list();
      }
    });
    return oupts;
  }

  public int queryCountByCriterions(final String name, final String description) {

    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(oupt.id) from OrgUnitPropTemplate oupt ";
            Vector parameters = new Vector();
            String criterion = "";

            if (name != null) {
              criterion += "oupt.name like ? escape '/' and ";
              parameters.add("%" + name + "%");
            }

            if (description != null) {
              criterion += "oupt.description like ? escape '/' and ";
              parameters.add("%" + description + "%");
            }

            if (criterion.length() != 0)
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));

            hql = hql + criterion;

            Query query = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }

            return query.uniqueResult();
          }
        });
    return count.intValue();
  }

  public Serializable insert(OrgUnitPropTemplate orgUnitPropTemplate)
      throws BusinessException {
    Validate.notNull(orgUnitPropTemplate, "����orgUnitPropTemplate����Ϊ�գ�");
    Validate.notNull(orgUnitPropTemplate.getName(),
        "orgUnitPropTemplate.getName()����Ϊ�գ�");

    int count = getHibernateTemplate()
        .find(
            "from OrgUnitPropTemplate oupt where lower(oupt.name) = ? or lower(oupt.innerName) = ?",
            new Object[] { orgUnitPropTemplate.getName().toLowerCase().trim(),
                orgUnitPropTemplate.getInnerName().toLowerCase().trim() })
        .size();
    if (count != 0) {
      throw new IllegalDuplicationException("ģ�������ڲ����Ƴ����ظ�����ʹ���������ƣ�");
    }
    return getHibernateTemplate().save(orgUnitPropTemplate);
  }

  public void deleteById(Serializable id) throws BusinessException {
    Validate.notNull(id, "����id����Ϊ��");

    OrgUnitPropTemplate oupt = (OrgUnitPropTemplate) getHibernateTemplate()
        .get(OrgUnitPropTemplate.class, id);
    if (oupt == null) {
      throw new EntityNotFoundException("ģ�岻���ڣ����Ѿ���ɾ��!");
    }
    getHibernateTemplate().delete(oupt);
  }

  public void checkBeforeUpdate(OrgUnitPropTemplate oupt)
      throws BusinessException {
    int count = getHibernateTemplate()
        .find(
            "from OrgUnitPropTemplate oupt where (lower(oupt.name) = ? or lower(oupt.innerName) = ?) and oupt.id <> ?",
            new Object[] { oupt.getName().toLowerCase().trim(),
                oupt.getInnerName().toLowerCase().trim(), oupt.getId() })
        .size();
    if (count != 0) {
      throw new IllegalDuplicationException("ģ�������ڲ����Ƴ����ظ�����ʹ���������ƣ�");
    }
  }
}
