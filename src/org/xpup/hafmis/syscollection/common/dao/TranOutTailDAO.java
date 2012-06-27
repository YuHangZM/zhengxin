package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail;
import org.xpup.hafmis.syscollection.tranmng.tranout.dto.TranoutiveFDTO;
import org.xpup.hafmis.sysloan.common.biz.contractpop.dto.ContractpopDTO;

/**
 * ת��β��
 * 
 * @author ��� 2007-6-19
 */
public class TranOutTailDAO extends HibernateDaoSupport {

  /**
   * ����������ѯ
   * 
   * @param id
   * @return
   */
  public TranOutTail queryById(Serializable id) {
    Validate.notNull(id);
    return (TranOutTail) getHibernateTemplate().get(TranOutTail.class, id);
  }

  /**
   * �����¼
   * 
   * @param empInfo
   * @return
   */
  public Serializable insert(TranOutTail tranOutTail) {
    Serializable id = null;
    try {
      Validate.notNull(tranOutTail);
      id = getHibernateTemplate().save(tranOutTail);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id.toString();
  }

  // ͨ��ת����λ��Ż�õ�λ����
  public List FindOutOrgName(final String orgoutid) {
    List list = (List) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = " select tot from TranOutHead toh,TranOutTail tot where toh.id=tot.tranOutHead.id and toh.tranOutOrg.id = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, orgoutid);
        return query.list();
      }
    });
    return list;
  }

  // ͨ��ת����λ��Ż�øõ�λ��Ա����Ϣ

  public List queryOutOrg(final String id, final String orderBy,
      final String order, final int start, final int pageSize,final int page)
      throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " from TranOutTail tot  ";
          Vector parameters = new Vector();
          String criterion = "";

          if (id != null && !id.equals("")) {
            criterion += "tot.tranOutHead.tranOutOrg.id = ?  and ";
            parameters.add(new Integer(id));
          }

          if (criterion.length() != 0)
            criterion = "where tot.tranOutHead.tranStatus = 1 and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " tot.tranOutHead.tranOutOrg.id ";
          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "order by " + ob + " " + od;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          
          List queryList =  query.list();
          
          if(queryList==null||queryList.size()==0){           
            query.setFirstResult(pageSize*(page-2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            queryList=query.list();
          }
          
          
          return query.list();
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  public List queryOutOrgCount(final String id) {
    int count = 0;
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " from TranOutTail tot  ";
          Vector parameters = new Vector();
          String criterion = "";

          if (id != null && !id.equals("")) {
            criterion += "tot.tranOutHead.tranOutOrg.id = ?  and ";
            parameters.add(new Integer(id));
          }

          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          session.clear();
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return query.list();
        }
      });
      // count = list.size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // test copy to HeadTable // ��ѯ��λ����=ת�뵥λ����
  public List queryInOrg(final Integer id) {
    List list = (List) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select tot.tranInOrg.orgInfo.name  "
            + "from TranInHead tot where tot.tranInOrg.id = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, id);
        return query.list();
      }
    });
    return list;
  }

  /*
   * ɾ��������¼
   */
  public void delete(TranOutTail tranOutTail) {
    try {
      Validate.notNull(tranOutTail);
      getHibernateTemplate().delete(tranOutTail);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * ɾ��list
   */
  public void deleteList(List list) {
    Validate.notNull(list);
    getHibernateTemplate().deleteAll(list);
  }

  // ͨ��������λ��Ż�õ�����λ����

  public List queryOutOrg(final String id) {
    List list = (List) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = " from TranOutTail tot where tot.tranOutHead.tranOutOrg.id =?";
        Query query = session.createQuery(hql);
        query.setParameter(0, new Integer(id));
        return query.list();
      }
    });
    return list;
  }

  // test copy to HeadTable // ��ѯ��λ����=ת�뵥λ����
  public List queryInOrg(final String id) {
    List list = (List) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select tot.tranInOrg.orgInfo.name  "
            + "from TranInHead tot where tot.tranInOrg.id = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, new Integer(id));

        return query.list();
      }
    });
    return list;
  }

  // sum��Ͳ���ͷ������
  public List queryTranOut_sy(final Integer id) throws NumberFormatException,
      Exception {
    List list = null;
    list = getHibernateTemplate().executeFind(

    new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = " select tranOutHead.id,sum(tranOutTail.preBalance + tranOutTail.curBalance+tranOutTail.preInterest + tranOutTail.curInterest) from TranOutTail tranOutTail,TranOutHead tranOutHead ";
        Vector parameters = new Vector();
        String criterion = "";
        criterion += "tranOutTail.tranOutHead.id= tranOutHead.id  and tranOutHead.id= ? group by tranOutHead.id";
        parameters.add(id);
        hql = hql + " where " + criterion;
        Query query = session.createQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        return query.list();
      }
    });
    return list;
  }
  public List queryTranOutTail_yg(final String tranOutHeadId) throws NumberFormatException,
  Exception {
    List list=getHibernateTemplate().executeFind(
        
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
            String hql = "select tranOutTail from TranOutTail tranOutTail where tranOutTail.tranOutHead.id = ?";
            
            Query query = session.createQuery(hql);
            query.setParameter(0, new Integer(tranOutHeadId));
            return query.list();
          }
        });
    return list;
  }

  // ����ͷ��id��ת�뵥λ���ƣ�ת����λ���ƣ�ת��id��ת��id��ת��Ʊ�ݱ�ţ�ת��Ʊ�ݱ�Ų��Ҷ�Ӧ�ļ�¼
  public List queryTranOutListByCriterionsAll_sy(final String id,
      final String inOrgId, final String outOrgId, final String outOrgName,
      final String inOrgName, final String noteNum, final String docNum,
      final String orderBy, final String order, final int start,
      final int pageSize) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(

      new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from TranOutTail tranOutTail ";
          Vector parameters = new Vector();
          String criterion = "";
          if (id != null && !id.equals("")) {
            criterion += "tranOutTail.tranOutHead.id= ?  and ";
            parameters.add(new Integer(id));
          }
          if (inOrgId != null && !inOrgId.equals("")) {
            criterion += "to_char(tranOutTail.tranOutHead.tranInOrg.id) like ?  and ";
            parameters.add("%"+new Integer(inOrgId)+"%");
          }
          if (outOrgId != null && !outOrgId.equals("")) {
            criterion += "to_char(tranOutTail.tranOutHead.tranOutOrg.id) like ?  and ";
            parameters.add("%"+new Integer(outOrgId)+"%");
          }
          if (outOrgName != null && !outOrgName.equals("")) {
            criterion += "tranOutTail.tranOutHead.tranOutOrg.orgInfo.name like ?  and ";
            parameters.add("%"+outOrgName+"%");
          }
          if (inOrgName != null && !inOrgName.equals("")) {
            criterion += "tranOutTail.tranOutHead.tranInOrg.orgInfo.name like ?  and ";
            parameters.add("%"+inOrgName+"%");
          }
          if (noteNum != null && !noteNum.equals("")) {
            criterion += "tranOutTail.tranOutHead.noteNum like ?  and ";
            parameters.add("%"+noteNum+"%");
          }
          if (docNum != null && !docNum.equals("")) {
            criterion += "tranOutTail.tranOutHead.docNum like ?  and ";
            parameters.add("%"+docNum+"%");
          }
//          criterion += " tranOutTail.tranOutHead.tranStatus>= 3  and ";
//          criterion += " tranOutTail.tranOutHead.tranInOrg.orgInfo.id is not null and ";
//          criterion += " tranOutTail.tranOutHead.id not in (select tranInHead.tranOutHeadId from TranInHead tranInHead where tranInHead.tranOutHeadId=tranOutTail.tranOutHead.id ) and";
          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String ob = orderBy;
          if (ob == null)
            ob = "tranOutTail.id";
          String od = order;
          if (od == null)
            od = "DESC";


          hql = hql + criterion + "order by " + ob + " " + order + " group by "
              + " tranOutTail.tranOutHead.id ";
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
//          query.setFirstResult(start);
//          if (pageSize > 0)
//            query.setMaxResults(pageSize);
          return query.list();
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  // ͳ�Ʒ�ҳͷ��id��ת���ţ�ת����ţ�ת��id��ת��id��ת��Ʊ�ݱ�ţ�ת��Ʊ�ݱ�Ų��Ҷ�Ӧ�ļ�¼
  public List countTranOutListByCriterionsAll_sy(final String id,
      final String inOrgId, final String outOrgId, final String outOrgName,
      final String inOrgName, final String noteNum, final String docNum)
      throws NumberFormatException, Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(

      new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from TranOutTail tranOutTail ";
          Vector parameters = new Vector();
          String criterion = "";
          if (id != null && !id.equals("")) {
            criterion += "tranOutTail.tranOutHead.id= ?  and ";
            parameters.add(new Integer(id));
          }
          if (inOrgId != null && !inOrgId.equals("")) {
            criterion += "to_char(tranOutTail.tranOutHead.tranInOrg.id) like ?  and ";
            parameters.add("%"+new Integer(inOrgId)+"%");
          }
          if (outOrgId != null && !outOrgId.equals("")) {
            criterion += "to_char(tranOutTail.tranOutHead.tranOutOrg.id) like ?  and ";
            parameters.add("%"+new Integer(outOrgId)+"%");
          }
          if (outOrgName != null && !outOrgName.equals("")) {
            criterion += "tranOutTail.tranOutHead.tranOutOrg.orgInfo.name like ?  and ";
            parameters.add("%"+outOrgName+"%");
          }
          if (inOrgName != null && !inOrgName.equals("")) {
            criterion += "tranOutTail.tranOutHead.tranInOrg.orgInfo.name like ?  and ";
            parameters.add("%"+inOrgName+"%");
          }
          if (noteNum != null && !noteNum.equals("")) {
            criterion += "tranOutTail.tranOutHead.noteNum like ?  and ";
            parameters.add("%"+noteNum+"%");
          }
          if (docNum != null && !docNum.equals("")) {
            criterion += "tranOutTail.tranOutHead.docNum like ?  and ";
            parameters.add("%"+docNum+"%");
          }
//          criterion += " tranOutTail.tranOutHead.tranStatus>= 3  and ";
//          criterion += " tranOutTail.tranOutHead.tranInOrg.orgInfo.id is not null and ";
//          criterion += " tranOutTail.tranOutHead.id not in (select tranInHead.tranOutHeadId from TranInHead tranInHead where tranInHead.tranOutHeadId=tranOutTail.tranOutHead.id ) and";
          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion + "";

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return query.list();
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  /*****************************************************************************
   * ͨ��ͷ��id���Ҷ�Ӧβ����� return
   */
  public List queryTranOutTail(final String tranOutHeadId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(

      new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from TranOutTail tranOutTail ";
          Vector parameters = new Vector();
          String criterion = "";
          criterion += "tranOutTail.tranOutHead.id= ? ";
          parameters.add(new Integer(tranOutHeadId));
          hql = hql + " where " + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // ͨ����������Pkid��ѯ β���List
  public List FindDelEmpInfo(final String HeadOutid) {
    List list = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from TranOutTail tot where tot.id = ?";
            Query query = session.createQuery(hql);
            query.setParameter(0, new Integer(HeadOutid));
            return query.list();
          }
        });
    return list;

  }

  // ת��ά�� ��ѯ���� wzq
  public List queryOrgInfoTBWZQ(final String id, final String inid,
      final String orderBy, final String order, final int start,
      final int pageSize) {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " from TranOutTail tot  ";
          Vector parameters = new Vector();
          String criterion = "";

          if (id != null && !id.equals("")) {
            criterion += "tot.tranOutHead.tranOutOrg.id = ?  and ";
            parameters.add(new Integer(id));
          }

          if (criterion.length() != 0)
            criterion = "where tot.tranOutHead.tranStatus = 1 and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " tot.tranOutHead.tranOutOrg.id ";
          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "order by " + ob + " " + od;

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

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  /*
   * ����ְ����Ų�ѯְ����Ϣ ,Ϊ���Ƿ��Ϣ
   */

  public List queryEmpInfoJxWZQ(final String empid) {
    List list = null;
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from TranOutTail tot where tot.empId= ? ";
        Query query = session.createQuery(hql);
        query.setParameter(0, new Integer(empid));
        return query.list();
      }
    });
    return list;
  }

  // ��ѯOfficeCode
  public List FindOfficeCode(final String pkid) {
    List list = null;
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = " from TranOutTail t where t.id = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, new Integer(pkid));
        return query.list();
      }
    });
    return list;
  }

  
  /*
   * Ĭ�ϲ�ѯaa309��payStatus = 1 or 3�ļ�¼ wzq @return list ת��ά����Ĭ�ϲ�ѯ
   */

  public List DefaultQueryTbWZQ(final String inid, final String orderBy,
      final String order, final int start, final int pageSize) {

    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from TranOutTail tot  ";
          Vector parameters = new Vector();
          String criterion = "";
          if (criterion.length() != 0)
            criterion = "where tot.tranOutHead.tranStatus = 1 or tot.tranOutHead.tranStatus = 3 "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String ob = orderBy;
          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + "order by " + ob + " " + od;

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
    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  // ת��ά��-- ɾ����λ��Ϣ
  public List queryTranOutHeadID(final Integer tranOutHeadID) {
    List list = null;

    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from TranOutTail tot where tot.tranOutHead.id= ? ";
        Query query = session.createQuery(hql);
        query.setParameter(0, tranOutHeadID);
        return query.list();
      }
    });
    return list;
  }

  // ����ת��--ͨ��310 pkid ��209 pkid
  public TranOutTail FindTranHeadPkid(final String pkid) {
    TranOutTail tranOutTail = null;
    tranOutTail = (TranOutTail) getHibernateTemplate().execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from TranOutTail tot where tot.id = ?";
            Query query = session.createQuery(hql);
            query.setParameter(0, new Integer(pkid));
            return query.list().get(0);
          }
        });
    return tranOutTail;

  }
  /*
   * ת��ά��--..��ѯԱ����Ϣ������ˮ102��
   */
    public List fIndTailEmpInfoWZQ(final String id) {
      List list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session)
        throws HibernateException, SQLException{
          String hql = " select tot from TranOutTail tot,TranOutOrg org where org.id = tot.tranOutHead.tranOutOrg.id and " +
              " tot.tranOutHead.id = ? " ;
          Query query = session.createQuery(hql);
           query.setParameter(0, new Integer(id));
          return query.list();
        }
      });
      return list;
    }  
  
    
    //����ת��--��� �ж�β���Ƿ����ظ���Ա����Ϣ
    public List queryTranOutEmp(final Integer tranOutHeadID) {
      List list = null;

      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from TranOutTail tot where tot.tranOutHead.id= ? ";
          Query query = session.createQuery(hql);
          query.setParameter(0, tranOutHeadID);
          return query.list();
        }
      });
      return list;
    }
 
    
    //��Ϣ��ʽ
    public int getDay(String moneyDate){
      java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
      String year = moneyDate.substring(0,4);
      String month = moneyDate.substring(4,6);
      String day = moneyDate.substring(6,8);
      Calendar convert = Calendar.getInstance();
      Calendar result = Calendar.getInstance();
      convert.set(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
      //����Ϊ�����6��30��
      result.set(Integer.parseInt(date.toString().substring(0,4)), 05,30);
      if(convert.getTime().getTime()> result.getTime().getTime()){//����Ϊ�����
        result.set(Integer.parseInt(date.toString().substring(0,4))+1, 05,30);
      }
      Timestamp one = new Timestamp(result.getTime().getTime());
      Timestamp two = new Timestamp(convert.getTime().getTime());
      int number = BusiTools.minusDate(one.toString().substring(0,10),two.toString().substring(0,10));
      return number;
    }
    
    
    
  /**
   * ���
   * ��ѯת�����
   * @param tranOutHeadid
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */  
  public List queryTranOutEmpList(final Serializable tranOutHeadid,
   final String orderBy, final String order, final int start,final int pageSize,final int page) 
  throws NumberFormatException, Exception {
     List list = null;
      try {
        Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
            || "DESC".equalsIgnoreCase(order));
        Validate.isTrue(start >= 0);

        list = getHibernateTemplate().executeFind(
            new HibernateCallback() {

              public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                String hql ="select tranOutTail from TranOutTail tranOutTail" ;
                Vector parameters = new Vector();
                String criterion = "";
                if (tranOutHeadid != null&&!tranOutHeadid.equals("")) {
                  criterion += " tranOutTail.tranOutHead.id = ?  and ";
                  parameters.add(new Integer(tranOutHeadid.toString()));
                }
                String ob = orderBy;
                if (ob == null)
                  ob = " tranOutTail.id ";

                String od = order;
                if (od == null)
                  od = "ASC";
                if (criterion.length() != 0)
                  criterion=" where tranOutTail.tranOutHead.tranStatus = 1 and "+
                  criterion.substring(0, criterion.lastIndexOf("and"));
                hql = hql + criterion + " order by " + ob + " " + od ;

                Query query = session.createQuery(hql);
                for (int i = 0; i < parameters.size(); i++) {
                  query.setParameter(i, parameters.get(i));
                }

                query.setFirstResult(start);
                if (pageSize > 0)
                  query.setMaxResults(pageSize);       
                
                List queryList=query.list();
                
                if(queryList==null||queryList.size()==0){           
                  query.setFirstResult(pageSize*(page-2));
                  if (pageSize > 0)
                    query.setMaxResults(pageSize);
                  queryList=query.list();
                }
                return queryList;
              }
            }
        );

      } catch (Exception e) {
        e.printStackTrace();
      }

      return list;
    }
  /**
   * ���
   * ��ѯת������¼��
   * @param tranOutHeadid
   * @return
   */
  public int queryCountTranOutEmpList(final Serializable tranOutHeadid) {
    int count=0;
    List list=new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql =" from TranOutTail tranOutTail " ;
              Vector parameters = new Vector();
              String criterion = "";
              if (tranOutHeadid != null&&!tranOutHeadid.equals("")) {
                criterion += " tranOutTail.tranOutHead.id = ?  and ";
                parameters.add(new Integer(tranOutHeadid.toString()));
              }
              
              if (criterion.length() != 0)
                criterion=" where  tranOutTail.tranOutHead.tranStatus = 1 and "+
                criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion ;

              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
    
              return query.list();
            }
          }

      );
      count=list.size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }
  /**
   * ���
   * �ѱ�AA309��ת����λ��ת��״̬��=5��ҵ��IDȡ����������AA310�����Ƿ�������ְ�������ͬ�ļ�¼
   * @param orgid
   * @param empid
   * @return
   */
  public List queryTranOutTailList(final String orgid,final String empid) {
    List list = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from TranOutTail tranOutTail ";
            Vector parameters = new Vector();
            String criterion = "";
            if (orgid != null&&!orgid.equals("")) {
              criterion += " tranOutTail.tranOutHead.tranOutOrg.id = ?  and ";
              parameters.add(new Integer(orgid));
            }
            if (empid != null&&!empid.equals("")) {
              criterion += " tranOutTail.empId = ?  and ";
              parameters.add(new Integer(empid));
            }
            
            if (criterion.length() != 0)
              criterion=" where tranOutTail.tranOutHead.tranStatus <> 5 and "+
              criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion ;

            Query query = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.list();
          }
        });
    return list;

  }
  /**
   * ����ֶ���Ϣ
   */
  public double getPreSub(final int orgId,final int empId){
      Object obj = getHibernateTemplate().execute(
          new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              Emp e = null;
              String hql="select e.preIntegralSubA*e.preRateA+e.preIntegralSubB*e.preRateB+e.preIntegralSubC*e.preRateC from Emp e where e.org.id=? and e.empId=?";
              Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1, empId).uniqueResult();
              return l;            
            }
          }
      );
      return Double.parseDouble(obj.toString());
  }
  /**
   * ����ֶ���Ϣ
   */
  public double getCurSub(final int orgId,final int empId){
    Object obj = new Object(); 
      obj = getHibernateTemplate().execute(
        new HibernateCallback(){
          public Object doInHibernate(Session session) throws HibernateException, SQLException {
            String hql="select e.curIntegralSubA*e.curRateA+e.curIntegralSubB*e.curRateB+e.curIntegralSubC*e.curRateC from Emp e where e.org.id=? and e.empId=?";
            Object l = session.createQuery(hql).setInteger(0, orgId).setInteger(1, empId).uniqueResult();
            return l;            
          }
        }
    );
    return Double.parseDouble(obj.toString());
  }
  
  /**
   * ���
   * ��ѯת�������ϸ
   * @param tranOutHeadid
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */  
  public List queryTranOutEmpListMX(final Serializable tranOutHeadid,
   final String orderBy, final String order, final int start,final int pageSize,final int page) 
  throws NumberFormatException, Exception {
     List list = null;
      try {
        Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
            || "DESC".equalsIgnoreCase(order));
        Validate.isTrue(start >= 0);

        list = getHibernateTemplate().executeFind(
            new HibernateCallback() {

              public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                String hql ="select tranOutTail from TranOutTail tranOutTail" ;
                Vector parameters = new Vector();
                String criterion = "";
                if (tranOutHeadid != null&&!tranOutHeadid.equals("")) {
                  criterion += " tranOutTail.tranOutHead.id = ?  and ";
                  parameters.add(new Integer(tranOutHeadid.toString()));
                }
                String ob = orderBy;
                if (ob == null)
                  ob = " tranOutTail.id ";

                String od = order;
                if (od == null)
                  od = "DESC";
                if (criterion.length() != 0)
                  criterion=" where "+
                  criterion.substring(0, criterion.lastIndexOf("and"));
                hql = hql + criterion + " order by " + ob + " " + od ;

                Query query = session.createQuery(hql);
                for (int i = 0; i < parameters.size(); i++) {
                  query.setParameter(i, parameters.get(i));
                }

                query.setFirstResult(start);
                if (pageSize > 0)
                  query.setMaxResults(pageSize);       
                
                List queryList=query.list();
                
                if(queryList==null||queryList.size()==0){           
                  query.setFirstResult(pageSize*(page-2));
                  if (pageSize > 0)
                    query.setMaxResults(pageSize);
                  queryList=query.list();
                }
                return queryList;
              }
            }
        );

      } catch (Exception e) {
        e.printStackTrace();
      }

      return list;
    }
  /**
   * ���
   * ��ѯת������¼����ϸ
   * @param tranOutHeadid
   * @return
   */
  public int queryCountTranOutEmpListMX(final Serializable tranOutHeadid) {
    int count=0;
    List list=new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql =" from TranOutTail tranOutTail " ;
              Vector parameters = new Vector();
              String criterion = "";
              if (tranOutHeadid != null&&!tranOutHeadid.equals("")) {
                criterion += " tranOutTail.tranOutHead.id = ?  and ";
                parameters.add(new Integer(tranOutHeadid.toString()));
              }
              
              if (criterion.length() != 0)
                criterion=" where "+
                criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion ;

              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
    
              return query.list();
            }
          }

      );
      count=list.size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }
 /*
  * ��ѯ�Ƿ����ת����Ӧ��ת���Ѽ��˵�ҵ��
  */
  public List queryTransInIsFiveList(final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // TODO Auto-generated method stub
          String hql = " select aa309.doc_num, ba001.name from aa309,aa311,aa001,ba001 " +
              "where aa311.tran_out_head_id=aa309.id " +
              "and aa001.orginfo_id=ba001.id  and aa309.tran_status!=5 and aa311.tran_status=5" +
              "and aa001.id=aa309.out_org_id and aa309.out_org_id "
              + securityInfo.getGjjSecuritySQL();
          Query query = session.createSQLQuery(hql);
          List templist = new ArrayList();
          Iterator iterate = query.list().iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            TranoutiveFDTO tranoutiveFDTO = new TranoutiveFDTO();
            obj = (Object[]) iterate.next();
            if (obj[0] != null) {
              tranoutiveFDTO.setNoteNum(obj[0].toString());
            }
            if (obj[1] != null) {
              tranoutiveFDTO.setOrgOutName(obj[1].toString());
            }
            templist.add(tranoutiveFDTO);
          }
          return templist;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
}





















