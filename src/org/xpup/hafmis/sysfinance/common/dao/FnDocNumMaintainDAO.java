package org.xpup.hafmis.sysfinance.common.dao;

import java.io.Serializable;
import java.sql.SQLException;

import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.syscollection.common.domain.entity.DocNumMaintain;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnDocNumCancel;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnDocNumMaintain;

public class FnDocNumMaintainDAO extends HibernateDaoSupport {

  /**
   * ����������ѯ
   * 
   * @param id
   * @return
   */
  public FnDocNumMaintain queryById(Serializable id) {
    Validate.notNull(id);
    return (FnDocNumMaintain) getHibernateTemplate().get(
        FnDocNumMaintain.class, id);
  }

  /**
   * �����¼
   * 
   * @param fnDocNumMaintain
   * @return
   */
  public Serializable insert(FnDocNumMaintain fnDocNumMaintain) {
    Serializable id = null;
    try {
      Validate.notNull(fnDocNumMaintain);
      id = getHibernateTemplate().save(fnDocNumMaintain);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * �����¼
   * 
   * @param docNumMaintain
   * @return
   */
  public Serializable insert(DocNumMaintain docNumMaintain) {
    Serializable id = null;
    try {
      Validate.notNull(docNumMaintain);
      id = getHibernateTemplate().save(docNumMaintain);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id.toString();
  }

  /**
   * @param officeCode ���´�
   * @param bizYearmonth ƾ֤����
   * @param credenceNumType ƾ֤����
   * @param bookId ƾ֤����
   * @return
   * @throws Exception
   * @throws BusinessException
   */

  public String getFnDocNumdocNum(String officeCode, String bizYearmonth,
      String credenceNumType, String bookId) throws Exception,
      BusinessException {
    String docNumdocNum = "";
    String docNumCanceldocNum = "";
    FnDocNumCancel fnDocNumCancel = null;
    FnDocNumMaintain fnDocNumMaintain = null;
    int docnumLen = 0;
    int docnumLen1 = 0;
    int cha = 0;
    String addstring = "";
    try {
      docNumCanceldocNum = this.getDocNumCanceldocNum(officeCode, bizYearmonth,
          credenceNumType, bookId);
      if (docNumCanceldocNum != null) {
        docNumdocNum = docNumCanceldocNum;
        fnDocNumCancel = this.getCancleDocument(officeCode, bizYearmonth,
            docNumdocNum, credenceNumType, bookId);
        this.getHibernateTemplate().delete(fnDocNumCancel);
      } else {
        FnDocNumMaintain temp_fnDocNumMaintain = this.getDocumentNumber(
            officeCode, bizYearmonth, credenceNumType, bookId);
        docNumdocNum = String.valueOf((Integer.parseInt(temp_fnDocNumMaintain
            .getCredenceNum())) + 1);
        docnumLen = temp_fnDocNumMaintain.getCredenceNum().length();
        docnumLen1 = docNumdocNum.length();
        cha = docnumLen - docnumLen1;
        fnDocNumMaintain = temp_fnDocNumMaintain;
        if (cha > 0) {
          for (int i = 0; i < cha; i++) {
            addstring = addstring + "0";
          }
          docNumdocNum = addstring + docNumdocNum;
        }
        fnDocNumMaintain.setCredenceNum(docNumdocNum);
        this.getHibernateTemplate().update(fnDocNumMaintain);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("����ҵ��ƾ֤�Ŵ���!");
    }
    return docNumdocNum;
  }

  /**
   * @param officeCode ���´�
   * @param bizYearmonth ƾ֤����
   * @param credenceNumType ƾ֤����
   * @param bookId ƾ֤����
   * @return
   * @throws Exception
   * @throws BusinessException
   */

  public String getFnDocNumdocNum_WL(String officeCode, String bizYearmonth,
      String credenceNumType, String bookId) throws Exception,
      BusinessException {
    String docNumdocNum = "";
    String docNumCanceldocNum = "";
    // FnDocNumCancel fnDocNumCancel = null;
    // FnDocNumMaintain fnDocNumMaintain = null;
    int docnumLen = 0;
    int docnumLen1 = 0;
    int cha = 0;
    String addstring = "";
    try {
      docNumCanceldocNum = this.getDocNumCanceldocNum(officeCode, bizYearmonth,
          credenceNumType, bookId);
      if (docNumCanceldocNum != null) {
        docNumdocNum = docNumCanceldocNum;
        // fnDocNumCancel = this.getCancleDocument(officeCode,
        // bizYearmonth,docNumdocNum,credenceNumType,bookId);
        // this.getHibernateTemplate().delete(fnDocNumCancel);
      } else {
        FnDocNumMaintain temp_fnDocNumMaintain = this.getDocumentNumber(
            officeCode, bizYearmonth, credenceNumType, bookId);
        docNumdocNum = String.valueOf((Integer.parseInt(temp_fnDocNumMaintain
            .getCredenceNum())) + 1);
        docnumLen = temp_fnDocNumMaintain.getCredenceNum().length();
        docnumLen1 = docNumdocNum.length();
        cha = docnumLen - docnumLen1;
        // fnDocNumMaintain=temp_fnDocNumMaintain;
        if (cha > 0) {
          for (int i = 0; i < cha; i++) {
            addstring = addstring + "0";
          }
          docNumdocNum = addstring + docNumdocNum;
        }
        // fnDocNumMaintain.setCredenceNum(docNumdocNum);
        // this.getHibernateTemplate().update(fnDocNumMaintain);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("����ҵ��ƾ֤�Ŵ���!");
    }
    return docNumdocNum;
  }

  /**
   * �������ϵ�ƾ֤�ŷ���String ���� officeCode ���´�,bizYearmonth ƾ֤����,credenceNumType
   * ƾ֤����,bookId ������ˮ��
   */
  public String getDocNumCanceldocNum(final String officeCode,
      final String bizYearmonth, final String credenceNumType,
      final String bookId) {
    String docNumCancel = "";
    docNumCancel = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String docNumCancel = "";
            String hql = "select min(fnDocNumCancel.cancelcredenceid) from FnDocNumCancel fnDocNumCancel  ";
            String criterion = "";
            if (officeCode != null) {
              criterion += " fnDocNumCancel.office = '" + officeCode + "' and ";
            } else {
              criterion += " fnDocNumCancel.office is null and ";
            }
            if (bizYearmonth != null) {
              criterion += " fnDocNumCancel.bizYearmonth = '" + bizYearmonth
                  + "' and ";
            }
            if (credenceNumType != null) {
              criterion += " fnDocNumCancel.credenceNumType = '"
                  + credenceNumType + "' and ";
            }
            if (bookId != null) {
              criterion += " fnDocNumCancel.bookId = '" + bookId + "' and ";
            }
            if (criterion.length() != 0)
              criterion = " where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query0 = session.createQuery(hql);
            docNumCancel = (String) query0.uniqueResult();
            return docNumCancel;
          }
        });
    return docNumCancel;
  }

  /**
   * �������ϵ�ƾ֤�ŷ��ض���һ����¼�� ���� officeCode ���´�,bizYearmonth ƾ֤����,credenceNumType
   * ƾ֤����,bookId ������ˮ��,cancelcredenceid ����ƾ֤��
   */

  public FnDocNumCancel getCancleDocument(final String officeCode,
      final String bizYearmonth, final String cancelcredenceid,
      final String credenceNumType, final String bookId) {
    FnDocNumCancel fnDocNumCancel = null;
    fnDocNumCancel = (FnDocNumCancel) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            FnDocNumCancel fnDocNumCancel = null;
            String hql = "select fnDocNumCancel from FnDocNumCancel fnDocNumCancel  ";
            String criterion = "";
            if (officeCode != null) {
              criterion += " fnDocNumCancel.office ='" + officeCode + "' and ";
            } else {
              criterion += " fnDocNumCancel.office is null and ";
            }
            if (bizYearmonth != null) {
              criterion += " fnDocNumCancel.bizYearmonth = '" + bizYearmonth
                  + "' and ";
            }
            if (cancelcredenceid != null) {
              criterion += " fnDocNumCancel.cancelcredenceid= '"
                  + cancelcredenceid + "' and ";
            }
            if (credenceNumType != null) {
              criterion += " fnDocNumCancel.credenceNumType= '"
                  + credenceNumType + "' and ";
            }
            if (bookId != null) {
              criterion += " fnDocNumCancel.bookId = '" + bookId + "' and ";
            }
            if (criterion.length() != 0)
              criterion = " where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query0 = session.createQuery(hql);
            fnDocNumCancel = (FnDocNumCancel) query0.uniqueResult();
            return fnDocNumCancel;
          }
        });
    return fnDocNumCancel;
  }

  /**
   * ����ƾ֤�ű���õ�ƾ֤�ŷ��ض���һ����¼�� ���� officeCode ���´�,bizYearmonth ƾ֤����,credenceNumType
   * ƾ֤����,bookId ������ˮ��
   */
  public FnDocNumMaintain getDocumentNumber(final String officeCode,
      final String bizYearmonth, final String credenceNumType,
      final String bookId) {
    FnDocNumMaintain fnDocNumMaintain = null;
    try {
      fnDocNumMaintain = (FnDocNumMaintain) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              FnDocNumMaintain temp_fnDocNumMaintain = null;
              String hql = "select fnDocNumMaintain from FnDocNumMaintain fnDocNumMaintain  ";
              String criterion = "";
              if (officeCode != null) {
                criterion += " fnDocNumMaintain.office ='" + officeCode
                    + "' and ";
              } else {
                criterion += " fnDocNumMaintain.office is null and ";
              }
              if (bizYearmonth != null) {
                criterion += " fnDocNumMaintain.bizYearmonth = '"
                    + bizYearmonth + "' and ";
              }
              if (credenceNumType != null) {
                criterion += " fnDocNumMaintain.credenceNumType = '"
                    + credenceNumType + "' and ";
              }
              if (bookId != null) {
                criterion += " fnDocNumMaintain.bookId = '" + bookId + "' and ";
              }
              if (criterion.length() != 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query0 = session.createQuery(hql);
              temp_fnDocNumMaintain = (FnDocNumMaintain) query0.uniqueResult();
              return temp_fnDocNumMaintain;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return fnDocNumMaintain;
  }

  /**
   * ƾ֤����-����
   * 
   * @author wsh ��ƾ֤�Ź���� FN301���������ƾ֤��
   * @param bookId ����
   * @return Integer
   */
  public Integer findCredenceclearMaxNum(final String office,
      final String type, final String bookId) {
    Integer minNun = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = "select max(t.credence_num) from fn301 t";
            String criterion = "";
            Integer temp_maxnun = new Integer(0);
            Vector parameters = new Vector();
            if (office != null && !"".equals(office)) {
              criterion += " t.office = ? and ";
              parameters.add(office);
            }
            if (type != null && !"".equals(type)) {
              criterion += " t.credence_num_type = ? and ";
              parameters.add(type);
            }
            if (bookId != null && !"".equals(bookId)) {
              criterion += " t.book_id = ? and ";
              parameters.add(bookId);
            }
            if (criterion.length() != 0)
              criterion = " where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            sql = sql + criterion;
            Query query = session.createSQLQuery(sql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            if (query.uniqueResult()!= null) {
              temp_maxnun = new Integer(query.uniqueResult().toString());
            }
            return temp_maxnun;
          }
        });
    return minNun;
  }

  /**
   * ��������--����
   * 
   * @author ���ƽ 2007-11-06 ͨ����ѯ�������fn301������ƾ֤��
   */
  public String getDocumentNumberMax(final String officeCode,
      final String bizYearmonth, final String credenceNumType,
      final String bookId) {
    String docNumMax = null;
    try {
      docNumMax = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select max(fnDocNumMaintain.credenceNum) from FnDocNumMaintain fnDocNumMaintain  ";
              String criterion = "";
              if (officeCode != null) {
                criterion += " fnDocNumMaintain.office ='" + officeCode
                    + "' and ";
              } else {
                criterion += " fnDocNumMaintain.office is null and ";
              }
              if (bizYearmonth != null) {
                criterion += " fnDocNumMaintain.bizYearmonth = '"
                    + bizYearmonth + "' and ";
              }
              if (credenceNumType != null) {
                criterion += " fnDocNumMaintain.credenceNumType = '"
                    + credenceNumType + "' and ";
              }
              if (bookId != null) {
                criterion += " fnDocNumMaintain.bookId = '" + bookId + "' and ";
              }
              if (criterion.length() != 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              Query query0 = session.createQuery(hql);
              String rs = (String) query0.uniqueResult();
              return rs;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return docNumMax;
  }

}
