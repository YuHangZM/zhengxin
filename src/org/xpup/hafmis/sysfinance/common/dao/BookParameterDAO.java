package org.xpup.hafmis.sysfinance.common.dao;

import java.io.Serializable;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.credencechar.dto.CredencecharDTO;
import org.xpup.hafmis.sysfinance.bookmng.settlemodle.dto.SettlemodleDTO;
import org.xpup.hafmis.sysfinance.bookmng.summary.dto.SummaryDTO;
import org.xpup.hafmis.sysfinance.common.domain.entity.BookParameter;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.BookParameterDTO;


public class BookParameterDAO extends HibernateDaoSupport {

  /**
   * ����������ѯ
   * 
   * @param id
   * @return
   */
  public BookParameter queryById(Serializable id) {
    Validate.notNull(id);
    return (BookParameter) getHibernateTemplate().get(
        BookParameter.class, id);
  }

  /**
   * �����¼
   * 
   * @param BookParameter
   * @return
   */
  public Serializable insert(BookParameter bookParameter) {
    Serializable id = null;
    try {
      Validate.notNull(bookParameter);
      id = getHibernateTemplate().save(bookParameter);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  /**
   * ��ò���˵��
   * @param paramnum-�������
   * @param securityInfo-Ȩ��
   * @return
   */
  public List getParamExplain_WL(final String paramnum ,final SecurityInfo securityInfo) {
    List codeLength = null;
    codeLength = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select bookParameter.paramExplain from BookParameter bookParameter ";
            Vector parameters = new Vector();
            String criterion = "";
            
            criterion += "bookParameter.bookId = ?  and ";
            parameters.add(securityInfo.getBookId());
            
            if (paramnum != null && !paramnum.equals("")) {
              criterion += "bookParameter.paramNum = ?  and ";
              parameters.add(paramnum);
            }
            
            if (criterion.length() != 0)
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion+" order by bookParameter.paramValue ";
            Query query = session.createQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.list();
          }
        });
    return codeLength;
  }
  /**
   * �ֽ��ռ���
   * @author ���ƽ
   * 2007-10-12
   * ��ѯfn102����paramExplain�ֶκ�paraId�ֶε�����
   * ��ѯ������paramNum,paramValue
   * Ŀ�ģ��Ѳ�ѯ������list���������б���
   */
  public List getParamExplain(final String paramnum ,final String paramValue ,final SecurityInfo securityInfo) {
    List codeLength = null;
    codeLength = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select bookParameter.paraId,bookParameter.paramExplain from BookParameter bookParameter ";
            Vector parameters = new Vector();
            String criterion = "";
            
            criterion += "bookParameter.bookId = ?  and ";
            parameters.add(securityInfo.getBookId());
            
            if (paramnum != null && !paramnum.equals("")) {
              criterion += "bookParameter.paramNum = ?  and ";
              parameters.add(paramnum);
            }
            
            if (paramValue != null && !paramValue.equals("")) {
              criterion += "(to_number(bookParameter.paramValue) > ? or bookParameter.paramValue = 2 ) and ";
              parameters.add(new Integer(paramValue));
            }
            
            if (criterion.length() != 0)
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Iterator it = query.list().iterator();
            List temp_list = new ArrayList();
            Object obj[] = null;
            while (it.hasNext()) {
              obj = (Object[]) it.next();
              if (obj != null) {
                BookParameterDTO bookParameterDTO = new BookParameterDTO();
                bookParameterDTO.setBookParameterId(obj[0].toString());
                if (obj[1] != null) {
                  bookParameterDTO.setBookParameterName(obj[1].toString());
                }
                temp_list.add(bookParameterDTO);
              }
            }
            return temp_list;
          }
        });
    return codeLength;
  }
  /**
   * ƾ֤¼���ժҪ�б�
   * @author ����
   * 2007-10-12
   * ��ѯfn102����paramExplain�ֶκ�paraId�ֶε�����
   * ��ѯ������paramNum,paramValue
   * Ŀ�ģ��Ѳ�ѯ������list���������б���
   */
  public List getParamExplain_ice(final String paramnum ,final String paramValue ,final SecurityInfo securityInfo) {
    List codeLength = null;
    codeLength = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select bookParameter.paraId,bookParameter.paramExplain from BookParameter bookParameter ";
            Vector parameters = new Vector();
            String criterion = "";
            
            criterion += "bookParameter.bookId = ?  and ";
            parameters.add(securityInfo.getBookId());
            
            if (paramnum != null && !paramnum.equals("")) {
              criterion += "bookParameter.paramNum = ?  and ";
              parameters.add(paramnum);
            }
            
            if (paramValue != null && !paramValue.equals("")) {
              criterion += "to_number(bookParameter.paramValue) > ? and ";
              parameters.add(new Integer(paramValue));
            }
            
            if (criterion.length() != 0)
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Iterator it = query.list().iterator();
            List temp_list = new ArrayList();
            Object obj[] = null;
            while (it.hasNext()) {
              obj = (Object[]) it.next();
              if (obj != null) {
                BookParameterDTO bookParameterDTO = new BookParameterDTO();
                bookParameterDTO.setBookParameterId(obj[0].toString());
                if (obj[1] != null) {
                  bookParameterDTO.setBookParameterName(obj[1].toString());
                }
                temp_list.add(bookParameterDTO);
              }
            }
            return temp_list;
          }
        });
    return codeLength;
  }
  /**
   * �ֽ��ռ���
   * @author ���ƽ
   * 2007-10-15
   * ��ѯfn102����paramExplain�ֶ�
   * ��ѯ������paraId
   */
  public String queryParamExplainByParaId(final String paraId) throws Exception {
    String paramExplain="";
    try {
      paramExplain =(String)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            if(paraId != null && !"".equals(paraId)) {
              String hql = "select bookParameter.paramExplain " +
                  "from BookParameter bookParameter " +
                  "where bookParameter.paraId = ?";
              Query query = session.createQuery(hql);
              query.setParameter(0, new Integer(paraId));
              return query.uniqueResult();
            } else
              return "";
          }
        }
    );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramExplain;
  }
  /**
   * ƾ֤¼��
   * @author ����
   * 2007-11-9
   * ��ѯfn102����paraId�ֶ�
   * ��ѯ������paramExplain
   */
  public String queryParamIdByParaExplain(final String paramExplain,final SecurityInfo securityInfo) throws Exception {
      String  paraId =(String)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select bookParameter.paraId "
                + "from BookParameter bookParameter "
                + "where bookParameter.bookId = ? and bookParameter.paramExplain = ? and bookParameter.paramNum=4 and bookParameter.paramValue>10";
            Query query = session.createQuery(hql);
            query.setParameter(0, securityInfo.getBookId());
            query.setParameter(1, paramExplain);
            Integer paraId = (Integer) query.uniqueResult();
            if (paraId == null) {
              return paraId;
            } else {
              return paraId.toString();
            }
          }
        }
    );
    return paraId;
  }
  /**
   * ������-ƾ֤¼���������ժҪ���׺��ֲ�ѯ����Ӧ�ļ�¼
   * @author ����
   * 2007-10-24
   * ��ѯfn102����paramExplain�ֶκ�paraId�ֶε�����
   * ��ѯ������paramNum,paramValue��paramExplain
   * Ŀ�ģ��Ѳ�ѯ������list����divҪ��ʾ��������
   */
  public List findParamExplainByChinese(final String paramnum ,final String paramValue ,final String paramExplain ,final SecurityInfo securityInfo) {
    List codeLength = null;
    codeLength = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select bookParameter.paraId,bookParameter.paramExplain from BookParameter bookParameter where bookParameter.bookId = ?  and bookParameter.paramNum = ?  and bookParameter.paramValue > ?  and (bookParameter.paramExplain like ?  or bookParameter.paramExplainExplain like ?)";
            Vector parameters = new Vector();
            parameters.add(securityInfo.getBookId());
              parameters.add(paramnum);
              parameters.add(paramValue);
              parameters.add(paramExplain+"%");
              parameters.add(paramExplain+"%");
            Query query = session.createQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
              //System.out.print(parameters.get(i));
            }
            List list=query.list();
            Iterator it = list.iterator();
            List temp_list = new ArrayList();
            Object obj[] = null;
            while (it.hasNext()) {
              obj = (Object[]) it.next();
              if (obj != null) {
                BookParameterDTO bookParameterDTO = new BookParameterDTO();
                bookParameterDTO.setBookParameterId(obj[0].toString());
                if (obj[1] != null) {
                  bookParameterDTO.setBookParameterName(obj[1].toString());
                }
                temp_list.add(bookParameterDTO);
              }
            }
            return temp_list;
          }
        });
    return codeLength;
  }


  /**
   * author wsh ��Ŀ��ϵ����
   * ��ѯһ����Ŀ��Ӧ�Ŀ�Ŀ����
   * @param orgid ��λid
   * @2007-10-16
   * @return String
   */
  public String queryFirstSubjectCodeLength_wsh(final String bookId){
    String id= (String)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
           String id="";         
            String hql="select a.param_explain from fn102 a where a.param_num=1 and a.param_value=1 and a.book_id= ? ";                        
        Query query = session.createSQLQuery(hql);   
        query.setParameter(0, bookId);
        id = query.uniqueResult().toString();            
        return id;
      }
        });
    return id;
  }
  
  /**
   * ���� ������׿�Ŀ����
   * 
   * @param bookId
   * @return String
   */
  public String getParamValue(final String bookId) {
    Validate.notNull(bookId);
    String paramValue = "";
    try {
      paramValue = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(f102.para_id) from fn102 f102 " +
              "where f102.param_num='1' and f102.book_id=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramValue;
  }
  
  /**
   * ���� ������״���ṹ
   * 
   * @param bookId
   * @return String
   */
  public List getParamExplain(final String bookId) {
    Validate.notNull(bookId);
    List paramExplain = null;
    try {
      paramExplain = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select f102.param_explain from fn102 f102 " +
              "where f102.param_num=1 and f102.book_id=? " +
              "order by f102.param_value asc";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramExplain;
  }
  
  /**
   * ���� ���Summay�� idֵ
   * 
   * @param bookId
   * @return String
   */
  public String getSummay(final String bookId) {
    Validate.notNull(bookId);
    String paramValue = "";
    try {
      paramValue = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select ff.para_id from fn102 ff where " +
              "ff.book_id=? and ff.param_num='4' and ff.param_value='1'";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramValue;
  }
  
  /**
   * ���� ���Summay�� idֵ
   * 
   * @param bookId
   * @return String
   */
  public String getSummay5(final String bookId) {
    Validate.notNull(bookId);
    String paramValue = "";
    try {
      paramValue = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select ff.para_id from fn102 ff where " +
              "ff.book_id=? and ff.param_num='4' and ff.param_value='5'";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramValue;
  }
  
  /**
   * ���� ���Summay�� idֵ
   * 
   * @param bookId
   * @return String
   */
  public String getSummay4(final String bookId) {
    Validate.notNull(bookId);
    String paramValue = "";
    try {
      paramValue = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select ff.para_id from fn102 ff where " +
              "ff.book_id=? and ff.param_num='4' and ff.param_value='4'";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramValue;
  }
  
  /**
   * ƾ֤���б���ʾ  ����
   * @param bookId
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryCredencecharList(final String bookId , final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List credencecharList = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select f102.para_id,f102.param_explain,f102.param_explain_explain from fn102 f102 " +
                "where f102.param_num=2 and f102.book_id=?";
            String ob = orderBy;
            if (ob == null)
              ob = " f102.para_id ";
            String od = order;
            if (od == null)
              od = "DESC";
            hql = hql + " order by " + ob + " " + od;
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, bookId);
            query.setFirstResult(start);
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            List queryList = query.list();
            
            // ɾ������
            if (queryList == null || queryList.size() == 0) {
              query.setFirstResult(pageSize * (page - 2));
              queryList = query.list();
            }
            Object obj[] = null;
            List list = new ArrayList();
            Iterator iter = queryList.iterator();
            while (iter.hasNext()) {
              CredencecharDTO credencecharDTO = new CredencecharDTO();
              obj = (Object[]) iter.next();
              if(obj != null){
                if (obj[0] != null) {
                  credencecharDTO.setParaId(obj[0].toString());
                }
                if (obj[1] != null) {
                  credencecharDTO.setParamExplain(obj[1].toString());
                }
                if (obj[2] != null) {
                  credencecharDTO.setParamExplainExplain(obj[2].toString());
                }
                list.add(credencecharDTO);
              }
            }
            return list;
          }
        });
    return credencecharList;
  }
  
  /**
   * ���㷽ʽ�б���ʾ  ����
   * @param bookId
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List querySettlemodleList(final String bookId , final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List credencecharList = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select f102.para_id,f102.param_explain from fn102 f102 " +
                "where f102.param_num=3 and f102.book_id=?";
            String ob = orderBy;
            if (ob == null)
              ob = " f102.para_id ";
            String od = order;
            if (od == null)
              od = "DESC";
            hql = hql + " order by " + ob + " " + od;
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, bookId);
            query.setFirstResult(start);
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            List queryList = query.list();
            
            // ɾ������
            if (queryList == null || queryList.size() == 0) {
              query.setFirstResult(pageSize * (page - 2));
              queryList = query.list();
            }
            Object obj[] = null;
            List list = new ArrayList();
            Iterator iter = queryList.iterator();
            while (iter.hasNext()) {
              SettlemodleDTO settlemodleDTO = new SettlemodleDTO();
              obj = (Object[]) iter.next();
              if(obj != null){
                if (obj[0] != null) {
                  settlemodleDTO.setParaId(obj[0].toString());
                }
                if (obj[1] != null) {
                  settlemodleDTO.setParamExplain(obj[1].toString());
                }
                list.add(settlemodleDTO);
              }
            }
            return list;
          }
        });
    return credencecharList;
  }
  
  /**
   * ���㷽ʽ�б���ʾ  ����
   * @param bookId
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List querySummaryList(final String bookId , final String orderBy,
      final String order, final int start, final int pageSize, final int page) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List credencecharList = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select f102.para_id,f102.param_explain from fn102 f102 " +
                "where f102.param_num=4 and f102.book_id=? and f102.param_value>10";
            String ob = orderBy;
            if (ob == null)
              ob = " f102.para_id ";
            String od = order;
            if (od == null)
              od = "DESC";
            hql = hql + " order by " + ob + " " + od;
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, bookId);
            query.setFirstResult(start);
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            List queryList = query.list();
            
            // ɾ������
            if (queryList == null || queryList.size() == 0) {
              query.setFirstResult(pageSize * (page - 2));
              queryList = query.list();
            }
            Object obj[] = null;
            List list = new ArrayList();
            Iterator iter = queryList.iterator();
            while (iter.hasNext()) {
              SettlemodleDTO settlemodleDTO = new SettlemodleDTO();
              obj = (Object[]) iter.next();
              if(obj != null){
                if (obj[0] != null) {
                  settlemodleDTO.setParaId(obj[0].toString());
                }
                if (obj[1] != null) {
                  settlemodleDTO.setParamExplain(obj[1].toString());
                }
                list.add(settlemodleDTO);
              }
            }
            return list;
          }
        });
    return credencecharList;
  }
  
  /**
   * ���� ��ѯ��¼���� ƾ֤��
   * 
   * @param bookId
   * @return String
   */
  public String queryCredencecharCount(final String bookId) {
    Validate.notNull(bookId);
    String number = "";
    try {
      number = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(f102.para_id) from fn102 f102 " +
                "where f102.param_num=2 and f102.book_id=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return number;
  }
  
  /**
   * ���� ��ѯ��¼���� ���㷽ʽ
   * 
   * @param bookId
   * @return String
   */
  public String querySettlemodleCount(final String bookId) {
    Validate.notNull(bookId);
    String number = "";
    try {
      number = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(f102.para_id) from fn102 f102 " +
                "where f102.param_num=3 and f102.book_id=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return number;
  }
  
  /**
   * ���� ��ѯ��¼���� ����ժҪ
   * 
   * @param bookId
   * @return String
   */
  public String querySummaryCount(final String bookId) {
    Validate.notNull(bookId);
    String number = "";
    try {
      number = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(f102.para_id) from fn102 f102 " +
                "where f102.param_num=4 and f102.book_id=? and f102.param_value>10";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return number;
  }
  
  /**
   * ���� ƾ֤����FN102.PARAM_NUM=2�ļ�¼(������)
   *  PARAM_EXPLAIN ��ֵ
   * 
   * @param bookId
   * @return String
   */
  public String queryCredencecharParamExplainInsert(final String bookId,final String paramExplain) {
    Validate.notNull(bookId);
    String count = "";
    try {
      count = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(f102.para_id) from fn102 f102 " +
              "where f102.param_num = 2 and f102.book_id = ? and f102.param_explain =? ";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          query.setParameter(1, paramExplain);
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }
  
  /**
   * ���� ���㷽ʽ��FN102.PARAM_NUM=3�ļ�¼(������)
   *  PARAM_EXPLAIN ��ֵ
   * 
   * @param bookId
   * @return String
   */
  public String querySettlemodleParamExplainInsert(final String bookId,final String paramExplain) {
    Validate.notNull(bookId);
    String count = "";
    try {
      count = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(f102.para_id) from fn102 f102 " +
              "where f102.param_num = 3 and f102.book_id = ? and f102.param_explain =? ";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          query.setParameter(1, paramExplain);
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }
  
  /**
   * ���� ����ժҪ��FN102.PARAM_NUM=4�ļ�¼(������)
   *  PARAM_EXPLAIN ��ֵ
   * 
   * @param bookId
   * @return String
   */
  public String querySummaryParamExplainInsert(final String bookId,final String paramExplain) {
    Validate.notNull(bookId);
    String count = "";
    try {
      count = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(f102.para_id) from fn102 f102 " +
              "where f102.param_num = 4 and f102.book_id = ? and f102.param_explain =? ";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          query.setParameter(1, paramExplain);
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }
  public String querySummaryInFn201(final String bookId,final String paramExplain) {
    Validate.notNull(bookId);
    String count = "";
    try {
      count = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(fn201.credence_id) from fn201 where fn201.book_id =? and fn201.summay=? ";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          query.setParameter(1, paramExplain);
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }
  /**
   * ���� ƾ֤����FN102.PARAM_NUM=2�ļ�¼(�޸���)
   *  PARAM_EXPLAIN ��ֵ
   * 
   * @param bookId
   * @return String
   */
  public String queryCredencecharParamExplainUpdate(final String bookId,final String paramExplain,final String paraId) {
    Validate.notNull(bookId);
    String count = "";
    try {
      count = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(f102.para_id) from fn102 f102 " +
              "where f102.param_num = 2 and f102.book_id = ? and f102.param_explain =? " +
              "and  (f102.para_id < ? or f102.para_id > ?)";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          query.setParameter(1, paramExplain);
          query.setParameter(2, paraId);
          query.setParameter(3, paraId);
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }
  
  /**
   * ���� ���㷽ʽ��FN102.PARAM_NUM=3�ļ�¼(�޸���)
   *  PARAM_EXPLAIN ��ֵ
   * 
   * @param bookId
   * @return String
   */
  public String querySettlemodleParamExplainUpdate(final String bookId,final String paramExplain,final String paraId) {
    Validate.notNull(bookId);
    String count = "";
    try {
      count = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(f102.para_id) from fn102 f102 " +
              "where f102.param_num = 3 and f102.book_id = ? and f102.param_explain =? " +
              "and  (f102.para_id < ? or f102.para_id > ?)";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          query.setParameter(1, paramExplain);
          query.setParameter(2, paraId);
          query.setParameter(3, paraId);
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }
  
  /**
   * ���� ���㷽ʽ��FN102.PARAM_NUM=4�ļ�¼(�޸���)
   *  PARAM_EXPLAIN ��ֵ
   * 
   * @param bookId
   * @return String
   */
  public String querySummaryParamExplainUpdate(final String bookId,final String paramExplain,final String paraId) {
    Validate.notNull(bookId);
    String count = "";
    try {
      count = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(f102.para_id) from fn102 f102 " +
              "where f102.param_num = 4 and f102.book_id = ? and f102.param_explain =? " +
              "and  (f102.para_id < ? or f102.para_id > ?) and f102.param_value>10";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          query.setParameter(1, paramExplain);
          query.setParameter(2, paraId);
          query.setParameter(3, paraId);
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }
  
  /**
   * ���� ���fn102 ����param_value�����ֵ ƾ֤��
   * 
   * @param bookId
   * @return String
   */
  public List queryParamValueMax(final String bookId) {
    Validate.notNull(bookId);
    List list  = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select f11.param_value from fn102 f11 " +
              "where f11.book_id=? and f11.param_num=2 and f11.param_value<99 order by f11.param_value desc";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  /**
   * ���� ���fn102 ����param_value �����ֵ ���㷽ʽ
   * 
   * @param bookId
   * @return String
   */
  public List queryParamValue3Max(final String bookId) {
    Validate.notNull(bookId);
    List list  = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select f11.param_value from fn102 f11 " +
              "where f11.book_id=? and f11.param_num=3 and f11.param_value<99 order by f11.param_value desc";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  /**
   * ���� ���fn102 ����param_value �����ֵ ����ժҪ
   * 
   * @param bookId
   * @return String
   */
  public List queryParamValue4Max(final String bookId) {
    Validate.notNull(bookId);
    List list  = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select f11.param_value from fn102 f11 " +
              "where f11.book_id=? and f11.param_num=4 and f11.param_value>10 order by f11.param_value desc";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  /**
   * ����ID ��ѯ��ƾ֤�� �� ƾ֤������
   * @param paraId
   * @return
   * ����
   */
  public List queryCredencecharParamExplainInfo(final String paraId) {
    List paramExplain = null;
    paramExplain = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select f.param_explain,f.param_explain_explain from fn102 f where f.para_id=?";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, paraId);
            Iterator it = query.list().iterator();
            List temp_list = new ArrayList();
            Object obj[] = null;
            while (it.hasNext()) {
              obj = (Object[]) it.next();
              if (obj != null) {
                CredencecharDTO credencecharDTO = new CredencecharDTO();
                if(obj[0] != null){
                  credencecharDTO.setParamExplain(obj[0].toString());
                }
                if (obj[1] != null) {
                  credencecharDTO.setParamExplainExplain(obj[1].toString());
                }
                temp_list.add(credencecharDTO);
              }
            }
            return temp_list;
          }
        });
    return paramExplain;
  }
  
  /**
   * ����ID ��ѯ���㷽ʽ
   * @param paraId
   * @return
   * ����
   */
  public List querySettlemodleParamExplainInfo(final String paraId) {
    List paramExplain = null;
    paramExplain = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select f.param_explain,f.para_id from fn102 f where f.para_id=?";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, paraId);
            Iterator it = query.list().iterator();
            List temp_list = new ArrayList();
            Object obj[] = null;
            while (it.hasNext()) {
              obj = (Object[]) it.next();
              if (obj != null) {
                SettlemodleDTO settlemodleDTO = new SettlemodleDTO();
                if(obj[0] != null){
                  settlemodleDTO.setParamExplain(obj[0].toString());
                }
                if(obj[1] != null){
                  settlemodleDTO.setParaId(obj[1].toString());
                }
                temp_list.add(settlemodleDTO);
              }
            }
            return temp_list;
          }
        });
    return paramExplain;
  }
  
  /**
   * ����ID ��ѯ����ժҪ
   * @param paraId
   * @return
   * ����
   */
  public List querySummaryParamExplainInfo(final String paraId) {
    List paramExplain = null;
    paramExplain = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select f.param_explain,f.para_id from fn102 f where f.para_id=?";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, paraId);
            Iterator it = query.list().iterator();
            List temp_list = new ArrayList();
            Object obj[] = null;
            while (it.hasNext()) {
              obj = (Object[]) it.next();
              if (obj != null) {
                SummaryDTO summaryDTO = new SummaryDTO();
                if(obj[0] != null){
                  summaryDTO.setParamExplain(obj[0].toString());
                }
                if(obj[1] != null){
                  summaryDTO.setParaId(obj[1].toString());
                }
                temp_list.add(summaryDTO);
              }
            }
            return temp_list;
          }
        });
    return paramExplain;
  }
  
  /**
   * ����ID ��ѯ����˵��
   * @param paraId
   * @param securityInfo
   * @return
   */
  public String queryParamExplainInfo_WL(final String paraId,final SecurityInfo securityInfo,final String paranum ) {
    String paramExplain = null;
    paramExplain = (String)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select f.param_explain from fn102 f where f.para_id=? and f.BOOK_ID=? and f.PARAM_NUM=? ";
            Query query = session.createSQLQuery(hql);           
            query.setParameter(0, paraId);
            query.setParameter(1, securityInfo.getBookId());
            query.setParameter(2, paranum);
            return query.uniqueResult();
          }
        });
    return paramExplain;
  }
  
  /**
   * ���� FN102���е�paramExplain  paramExplainExplain ƾ֤��
   * @param paraId
   * @param paramExplain
   * @param paramExplainExplain
   * ����
   */
  public void updateCredencecharInfo(final String paraId,final String paramExplain,final String paramExplainExplain){
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "update BookParameter bp set bp.paramExplain=?,bp.paramExplainExplain=? "
              + "where bp.paraId=? ";
          Query query = session.createQuery(hql);
          query.setParameter(0, paramExplain);
          query.setParameter(1, paramExplainExplain);
          query.setParameter(2, new Integer(paraId));
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * ���� FN102���е�paramExplain ���㷽ʽ
   * @param paraId
   * @param paramExplain
   * ����
   */
  public void updateSettlemodleInfo(final String paraId,final String paramExplain){
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "update BookParameter bp set bp.paramExplain=? "
              + "where bp.paraId=? ";
          Query query = session.createQuery(hql);
          query.setParameter(0, paramExplain);
          query.setParameter(1, new Integer(paraId));
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * ���� FN102���е�paramExplain ����ժҪ
   * @param paraId
   * @param paramExplain
   * ����
   */
  public void updateSummaryInfo(final String paraId,final String paramExplain,final String paramExplainExplain){
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "update BookParameter bp set bp.paramExplain=?,bp.paramExplainExplain=? "
              + "where bp.paraId=? ";
          Query query = session.createQuery(hql);
          query.setParameter(0, paramExplain);
          query.setParameter(1, paramExplainExplain);
          query.setParameter(2, new Integer(paraId));
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * ɾ�� FN102���е� paraId ��¼
   * @param paraId
   * ����
   */
  public void deleteCredencecharInfo(final String paraId){
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "delete BookParameter bp where bp.paraId=? ";
          Query query = session.createQuery(hql);
          query.setParameter(0, new Integer(paraId));
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * ���� ����paraId �ж�FN102�����Ƿ��м�¼
   * 
   * @param bookId
   * @return String
   */
  public String isCredencecharById(final String paraId) {
    Validate.notNull(paraId);
    String number = "";
    try {
      number = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(f102.para_id) from fn102 f102 " +
                "where f102.para_id = ? ";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, paraId);
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return number;
  }
  
  /**
   * ���� �жϸü�¼��FN102.para_id��FN201.CREDENCE_CHARACTER or FN210.CREDENCE_CHARACTER���Ƿ����
   * 0����û�м�¼ 1�����м�¼
   * @param bookId
   * @return String
   */
  public String isCredencecharByParamValue(final String paraId,final String bookId) {
    Validate.notNull(paraId);
    String number = "";
    try {
      number = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(f201.credence_id) from fn201 f201 " +
              "where f201.credence_character =? and f201.book_id =?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, paraId);
          query.setParameter(1, bookId);
          String temp_query = query.uniqueResult().toString();
          
          if(temp_query.equals("0")){
            String sql1 = "select count(f210.credence_id) from fn210 f210 " +
                "where f210.credence_character =? and f210.book_id=?";
            Query query1 = session.createSQLQuery(sql1);
            query1.setParameter(0, paraId);
            query1.setParameter(1, bookId);
            String temp_query1 = query1.uniqueResult().toString();
            
            if(temp_query1.equals("0")){
              String sql2 = "select count(f102.para_id) from fn102 f102 " +
              "where f102.para_id = ? and f102.param_value >= 99 and f102.book_id = ?";
              Query query2 = session.createSQLQuery(sql2);
              query2.setParameter(0, paraId);
              query2.setParameter(1, bookId);
              String temp_query2 = query2.uniqueResult().toString();
              if(temp_query2.equals("0")){
                return "0";
              }else{
                return "1";
              }
            }else{
              return "1";
            }
            
          }
          return "1";
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return number;
  }
  
  /**
   * ���� �жϸü�¼��FN102.PARA_ID��FN201.SETT_TYPE or FN210.SETT_TYPE���Ƿ����
   * 0����û�м�¼ 1�����м�¼
   * @param bookId
   * @return String
   */
  public String isSettlemodleByParamValue(final String paraId,final String bookId) {
    Validate.notNull(paraId);
    String number = "";
    try {
      number = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(f201.credence_id) from fn201 f201 " +
              "where f201.sett_type =? and f201.book_id =?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, paraId);
          query.setParameter(1, bookId);
          String temp_query = query.uniqueResult().toString();
          
          if(temp_query.equals("0")){
            String sql1 = "select count(f210.credence_id) from fn210 f210 " +
                "where f210.sett_type =? and f210.book_id=?";
            Query query1 = session.createSQLQuery(sql1);
            query1.setParameter(0, paraId);
            query1.setParameter(1, bookId);
            String temp_query1 = query1.uniqueResult().toString();
            
            if(temp_query1.equals("0")){
              String sql2 = "select count(f102.para_id) from fn102 f102 " +
              "where f102.para_id = ? and f102.param_value >= 99 and f102.book_id = ?";
              Query query2 = session.createSQLQuery(sql2);
              query2.setParameter(0, paraId);
              query2.setParameter(1, bookId);
              String temp_query2 = query2.uniqueResult().toString();
              if(temp_query2.equals("0")){
                return "0";
              }else{
                return "1";
              }
            }else{
              return "1";
            }
            
          }
          return "1";
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return number;
  }
  
  /**
   * ���� �жϸü�¼��FN102.PARA_ID��FN201.SUMMAY or FN210.SUMMAY���Ƿ����
   * 0����û�м�¼ 1�����м�¼
   * @param bookId
   * @return String
   */
  public String isSummaryByParamValue(final String paraId,final String bookId) {
    Validate.notNull(paraId);
    String number = "";
    try {
      number = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(f201.credence_id) from fn201 f201 " +
              "where f201.summay =? and f201.book_id =?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, paraId);
          query.setParameter(1, bookId);
          String temp_query = query.uniqueResult().toString();
          
          if(temp_query.equals("0")){
            String sql1 = "select count(f210.credence_id) from fn210 f210 " +
                "where f210.summray =? and f210.book_id=?";
            Query query1 = session.createSQLQuery(sql1);
            query1.setParameter(0, paraId);
            query1.setParameter(1, bookId);
            String temp_query1 = query1.uniqueResult().toString();
            if(temp_query1.equals("0")){
              return "0";
            }else{
              return "1";
            }
          }
          return "1";
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return number;
  }

  /**
   * ����FN102�е�������ѯ��Ӧ�Ľ�������
   * @param bookId
   * @param paraId
   * @return
   */
  public String queryParamExplainByParaId(final String bookId, final String paraId){
    Validate.notNull(paraId);
    String paramExplain = "";
    try {
      paramExplain = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select f102.param_explain from fn102 f102 where f102.book_id=? and f102.param_num=3 and f102.para_id=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          query.setParameter(1, paraId);
          return query.uniqueResult() != null ? query.uniqueResult()
                  .toString() : "";
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramExplain;
  }
  
  public String getFBizDate(final String bookId,final String makebill) {
    String paramValue = "";
    try {
      paramValue = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select max(t.credence_date) from fn201 t " +
              "where t.book_id = ? and t.makebill = ? and t.credence_st = '2'";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bookId);
          query.setParameter(1, makebill);
          if(query.uniqueResult() != null && !query.uniqueResult().equals("")){
            return query.uniqueResult().toString();            
          }
          return "";
            }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramValue;
  }
}
