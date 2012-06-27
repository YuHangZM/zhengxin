package org.xpup.security.common.dao;

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
import org.xpup.common.exception.EntityNotFoundException;
import org.xpup.common.exception.IllegalDuplicationException;
import org.xpup.security.common.domain.Operation;

public class OperationDAO extends HibernateDaoSupport {
  
  public List queryUnusedByUserId(final Serializable userId) {
    Validate.notNull(userId, "����userId����Ϊ�գ�");
    
    List unuseds = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        Query query = session.getNamedQuery("unusedByUserId");
        query.setParameter("userId", userId);
        return query.list();
      }
    });
    return unuseds;
  }
  
  public List queryUnusedByRoleId(final Serializable roleId) {
    Validate.notNull(roleId, "����roleId����Ϊ�գ�");
    
    List unuseds = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        Query query = session.getNamedQuery("unusedByRoleId");
        query.setParameter("roleId", roleId);
        return query.list();
      }
    });
    return unuseds;
  }

  public List queryByUserIdFromOU(final Serializable userId) {
    Validate.notNull(userId, "����userId����Ϊ�գ�");
    
    List operations = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select operation from Operation operation, OuRelation ouRelation inner join ouRelation.user user where operation.id = ouRelation.operation.id and user.id = :userId";
            return session.createQuery(hql).setParameter("userId", userId)
                .list();
          }
        });
    return operations;
  }

  public List queryByUserIdFromOR(final Serializable userId) {
    Validate.notNull(userId, "����userId����Ϊ�գ�");
    
    List operations = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select operation from Operation operation, OrRelation orRelation inner join orRelation.role role inner join role.users user where operation.id = orRelation.operation.id and user.id = :userId";
            return session.createQuery(hql).setParameter("userId", userId)
                .list();
          }
        });
    return operations;
  }

  public List queryByRoleIdFromOR(final Serializable roleId) {
    Validate.notNull(roleId, "����roleId����Ϊ�գ�");
    
    List operations = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select operation from Operation operation, OrRelation orRelation inner join orRelation.role role where operation.id = orRelation.operation.id and role.id = :roleId";
            return session.createQuery(hql).setParameter("roleId", roleId)
                .list();
          }
        });
    return operations;
  }

  public List queryByCriterionsFromOU(final String username,
      final String opInnerName) {
    List operations = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select operation from Operation operation, OuRelation ouRelation inner join ouRelation.user user where operation.id = ouRelation.operation.id and user.username=:username and operation.innerName=:opInnerName";
            return session.createQuery(hql).setString("username", username)
                .setString("opInnerName", opInnerName).list();
          }
        });
    return operations;
  }

  public List queryByCriterionsFromOR(final String username,
      final String opInnerName) {
    List operations = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select operation from Operation operation, OrRelation orRelation inner join orRelation.role role inner join role.users user where operation.id = orRelation.operation.id and user.username=:username and operation.innerName=:opInnerName";
            return session.createQuery(hql).setString("username", username)
                .setString("opInnerName", opInnerName).list();
          }
        });
    return operations;
  }

  public List queryByCriterions(final String name, final String innerName,
      final String description, final String group, final String orderBy,
      final OrderEnum order, final int start, final int pageSize) {

    Validate.notNull(orderBy, "����orderBy����Ϊ�գ�");
    Validate.notNull(order, "����order����Ϊ�գ�");
    Validate
        .isTrue(
            orderBy
                .matches("operation\\.(id|name|innerName|description|group)$"),
            "����orderBy("
                + orderBy
                + ")������Ҫ����������operation.id, operation.name, operation.innerName, operation.description, operation.group�е�һ����");

    List operations = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from Operation operation ";
            Vector parameters = new Vector();
            String criterion = "";

            if (name != null) {
              criterion += "operation.name like ? escape '/' and ";
              parameters.add("%" + name + "%");
            }

            if (innerName != null) {
              criterion += "operation.innerName like ? escape '/' and ";
              parameters.add("%" + innerName + "%");
            }

            if (description != null) {
              criterion += "operation.description like ? escape '/' and ";
              parameters.add("%" + description + "%");
            }

            if (group != null) {
              criterion += "operation.group like ? escape '/' and ";
              parameters.add("%" + group + "%");
            }

            if (criterion.length() != 0)
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));

            hql = hql + criterion + "order by " + orderBy + " "
                + order.getName();

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
    return operations;
  }

  public int queryCountByCriterions(final String name, final String innerName,
      final String description, final String group) {

    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(operation.id) from Operation operation ";
            Vector parameters = new Vector();
            String criterion = "";

            if (name != null) {
              criterion += "operation.name like ? escape '/' and ";
              parameters.add("%" + name + "%");
            }

            if (innerName != null) {
              criterion += "operation.innerName like ? escape '/' and ";
              parameters.add("%" + innerName + "%");
            }

            if (description != null) {
              criterion += "operation.description like ? escape '/' and ";
              parameters.add("%" + description + "%");
            }

            if (group != null) {
              criterion += "operation.group like ? escape '/' and ";
              parameters.add("%" + group + "%");
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

  public Operation queryById(Serializable id) {
    Validate.notNull(id, "����id����Ϊ�գ�");
    return (Operation) getHibernateTemplate().get(Operation.class, id);
  }

  public Serializable insert(Operation operation)
      throws IllegalDuplicationException {
    Validate.notNull(operation, "����operation����Ϊ�գ�");
    Validate.notNull(operation.getName(), "operation.getName()����Ϊ�գ�");
    Validate.notNull(operation.getInnerName(), "operation.getInnerName()����Ϊ�գ�");

    int count = getHibernateTemplate()
        .find(
            "from Operation operation where lower(operation.innerName) = ? or lower(operation.name) = ?",
            new Object[] { operation.getInnerName().toLowerCase(),
                operation.getName().toLowerCase().trim() }).size();
    if (count != 0) {
      throw new IllegalDuplicationException("��������������ϵͳ�������ظ�����ʹ���������ƣ�");
    }
    return getHibernateTemplate().save(operation);
  }

  public void checkBeforeUpdate(Operation operation)
      throws IllegalDuplicationException {
    Validate.notNull(operation, "����operation����Ϊ�գ�");
    Validate.notNull(operation, "operation.getId()����Ϊ�գ�");
    Validate.notNull(operation.getName(), "operation.getName()����Ϊ�գ�");
    Validate.notNull(operation.getInnerName(), "operation.getSysName()����Ϊ�գ�");

    int count = getHibernateTemplate()
        .find(
            "from Operation operation where (lower(operation.innerName) = ? or lower(operation.name) = ?) and operation.id <> ?",
            new Object[] { operation.getInnerName().toLowerCase(),
                operation.getName().trim(), operation.getId() }).size();
    if (count != 0) {
      throw new IllegalDuplicationException("��������������ϵͳ�������ظ�����ʹ���������ƣ�");
    }
  }

  public void deleteById(Serializable id) throws EntityNotFoundException {
    Validate.notNull(id, "����id����Ϊ�գ�");

    Operation operation = queryById(id);
    if (operation == null) {
      throw new EntityNotFoundException("�����������ڣ����Ѿ���ɾ����");
    }
    getHibernateTemplate().delete(operation);
  }

}
