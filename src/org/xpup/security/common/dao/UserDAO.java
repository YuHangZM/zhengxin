package org.xpup.security.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.exception.EntityNotFoundException;
import org.xpup.common.exception.IllegalDuplicationException;
import org.xpup.common.exception.SystemException;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.security.common.domain.User;

public class UserDAO extends HibernateDaoSupport {

  public User queryByUsername(final String username) {
    List users = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
            List users = session.createCriteria(User.class).add(
                Restrictions.eq("username", username)).list();
            return users;
          }
        });
    if (users.size() == 0) {
      return null;
    } else if (users.size() > 1) {
      throw new SystemException("�û�������Ψһ��");
    }
    return (User) users.get(0);
  }

  public User queryById(Serializable id) {
    return (User) getHibernateTemplate().get(User.class, id);
  }

  public Serializable insert(User user) throws BusinessException {
    Validate.notNull(user, "���� user ����Ϊ��");
    Validate.notNull(user.getUsername(), "user.getUsername()����Ϊ��");

    user.setUsername(user.getUsername().trim());

    int count = getHibernateTemplate().find(
        "from User user where lower(user.username) = ?",
        user.getUsername().toLowerCase()).size();
    if (count != 0) {
      throw new IllegalDuplicationException("�û����ƣ�" + user.getUsername()
          + "���Ѿ����ڣ��û����Ʊ���Ψһ!");
    }
    return getHibernateTemplate().save(user);
  }

  public void deleteById(Serializable id) throws BusinessException {
    Validate.notNull(id, "����id����Ϊ��");

    User user = (User) getHibernateTemplate().get(User.class, id);
    if (user == null) {
      throw new EntityNotFoundException("�û������ڣ����Ѿ���ɾ��!");
    }
    getHibernateTemplate().delete(user);
  }

  public void checkBeforeUpdate(User user) throws BusinessException {
    Validate.notNull(user, "���� user ����Ϊ��");
    Validate.notNull(user.getId(), "user.getId()����Ϊ��");

    int count = getHibernateTemplate().find(
        "from User user where lower(user.username) = ? and user.id <> ?",
        new Object[] { user.getUsername().toLowerCase(), user.getId() }).size();
    if (count != 0) {
      throw new IllegalDuplicationException("�û����ƣ�" + user.getUsername()
          + "���Ѿ����ڣ��û����Ʊ���Ψһ!");
    }
  }
  
  
  public boolean checkLogin(final String userName,final String userPassword) {   
        boolean flag=true;
        List list=(List)getHibernateTemplate().find( "from User user where user.username='"+userName+"' and user.password='"+userPassword+"'");
        if(list!=null&&list.size()>0){
          flag=false;
        }
        return flag;      
  }
  
  public List findAllUser(){
    List list=new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String sql = " select c101.username from ca101 c101 ";
        Query query = session.createSQLQuery(sql);
        return query.list();
      }
    });
    return list;
  }
}
