package org.xpup.hafmis.syscollection.common.daoDW;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonTail;

import java.util.*;

/**
 * @author ¬�� 2007-6-15
 */
public class ChgOrgRateDAODW extends HibernateDaoSupport {

  /**
   * ����������ѯ
   * 
   * @param id
   * @return
   */
  public ChgOrgRate queryById(Integer id) {
    Validate.notNull(id);
    return (ChgOrgRate) getHibernateTemplate().get(ChgOrgRate.class, id);
  }

  /**
   * �����¼
   * 
   * @param chgOrgRate
   * @return
   */
  public Serializable insert(ChgOrgRate chgOrgRate) {
    Serializable id = null;
    try {
      Validate.notNull(chgOrgRate);
      id = getHibernateTemplate().save(chgOrgRate);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * ���� ɾ��������¼
   * 
   * @param chgOrgRate
   */
  public void delete_WL(ChgOrgRate chgOrgRate) {
    Validate.notNull(chgOrgRate);
    getHibernateTemplate().delete(chgOrgRate);
  }

  /**
   * ���� �ж��Ƿ����δ���õĻ�ɱ�������
   * 
   * @param ��λID
   * @return boolean:false��δ���ã�true������
   */
  public boolean getChgStatus(final Integer orgId) {
    Validate.notNull(orgId);

    boolean chgStatus = false;
    Integer TEMP_status = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select chgOrgRate.chgStatus from ChgOrgRate chgOrgRate where chgOrgRate.chgStatus=1 and chgOrgRate.org.id=? ";

            Query query = session.createQuery(hql);
            query.setParameter(0, orgId);

            return query.uniqueResult();
          }
        });
    if (TEMP_status == null) {
      chgStatus = true;
    } else {
      if (TEMP_status.equals(new Integer(1))) {
        chgStatus = false;
      } else {
        chgStatus = true;
      }
    }

    return chgStatus;
  }

  /**
   * ��� ���ݵ�λ��źͽɴ�ID��ѯ ְ�����Ӽ��ٽ��
   * 
   * @param orgid
   * @param payheadId
   * @return
   */
  public BigDecimal queryEmpRateMoney(final Serializable orgid,
      final Serializable payheadId) {
    BigDecimal ratemoney = null;
    ratemoney = (BigDecimal) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select sum(chgOrgRate.empPay-chgOrgRate.oldEmpPay) "
                + "from ChgOrgRate chgOrgRate ";
            Vector parameters = new Vector();
            String criterion = "";

            if (orgid != null && !orgid.equals("")) {
              criterion += "chgOrgRate.org.id = ?  and ";
              parameters.add(new Integer(orgid.toString()));
            }

            if (payheadId != null && !payheadId.equals("")) {
              criterion += "chgOrgRate.paymentHead.id = ?  and ";
              parameters.add(new Integer(payheadId.toString()));
            } else {
              criterion += "chgOrgRate.paymentHead.id is null and ";
            }
            if (criterion.length() != 0)
              criterion = "where chgOrgRate.chgStatus=2 and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.uniqueResult();
          }
        });
    if (ratemoney == null) {
      ratemoney = new BigDecimal(0.00);
    }
    return ratemoney;
  }

  /**
   * ��� ���ݵ�λ��źͽɴ�ID��ѯ ��λ���Ӽ��ٽ��
   * 
   * @param orgid
   * @param payheadId
   * @return
   */
  public BigDecimal queryOrgRateMoney(final Serializable orgid,
      final Serializable payheadId) {
    BigDecimal ratemoney = null;
    ratemoney = (BigDecimal) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select sum(chgOrgRate.orgPay-chgOrgRate.oldOrgPay) "
                + "from ChgOrgRate chgOrgRate ";
            Vector parameters = new Vector();
            String criterion = "";

            if (orgid != null && !orgid.equals("")) {
              criterion += "chgOrgRate.org.id = ?  and ";
              parameters.add(new Integer(orgid.toString()));
            }

            if (payheadId != null && !payheadId.equals("")) {
              criterion += "chgOrgRate.paymentHead.id = ?  and ";
              parameters.add(new Integer(payheadId.toString()));
            } else {
              criterion += "chgOrgRate.paymentHead.id is null and ";
            }
            if (criterion.length() != 0)
              criterion = "where chgOrgRate.chgStatus=2 and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.uniqueResult();
          }
        });
    if (ratemoney == null) {
      ratemoney = new BigDecimal(0.00);
    }
    return ratemoney;
  }

  /**
   * ��� ���ݽɴ�ͷ��ID������״̬��ѯ
   * 
   * @param payHeadId
   * @param chgStatus
   * @return
   */
  public List queryChgOrgRateByPayHeadId(final Serializable orgid,
      final Serializable payHeadId, final Integer chgStatus) {
    List chgOrgRate = null;
    chgOrgRate = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from ChgOrgRate chgOrgRate ";
        Vector parameters = new Vector();
        String criterion = "";
        if (orgid != null&&!orgid.equals("")) {
          criterion += "chgOrgRate.org.id = ?  and ";
          parameters.add(new Integer(orgid.toString()));
        }
        if (payHeadId != null && !payHeadId.equals("")) {
          criterion += "chgOrgRate.paymentHead.id = ?  and ";
          parameters.add(new Integer(payHeadId.toString()));
        } else {
          criterion += "chgOrgRate.paymentHead.id is null and ";
        }

        if (chgStatus != null && !chgStatus.equals("")) {
          criterion += "chgOrgRate.chgStatus = ?  and ";
          parameters.add(chgStatus);
        }

        if (criterion.length() != 0)
          criterion = "where "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion;
        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        return query.list();
        }
      });

    return chgOrgRate;
  }

  /**
   * ���� ���Խ��л�ɱ�������ҵ��ģ�����ҳ����ʾ��Ϣ�Ĳ�ѯ
   */
  public ChgOrgRate getChgOrgRateMessage_WL(final String orgID)
      throws BusinessException {
    ChgOrgRate chgOrgRate = null;
    chgOrgRate = (ChgOrgRate) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from ChgOrgRate chgOrgRate ";
            Vector parameters = new Vector();
            String criterion = "";

            if (orgID != null && !orgID.equals("")) {
              criterion += "chgOrgRate.org.id = ?  and ";
              parameters.add(new Integer(orgID.toString()));
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
    return chgOrgRate;
  }

  /**
   * ���� ���ݵ�λID�����״̬ �õ�AA201����Ϣ
   */
  public ChgOrgRate getOrgRateMessage_WL(final String orgID, final String status)
      throws BusinessException {
    ChgOrgRate chgOrgRate = null;
    chgOrgRate = (ChgOrgRate) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from ChgOrgRate chgOrgRate ";
            Vector parameters = new Vector();
            String criterion = "";

            if (orgID != null && !orgID.equals("")) {
              criterion += "chgOrgRate.org.id = ?  and ";
              parameters.add(new Integer(orgID));
            }
            if (status != null && !status.equals("")) {
              criterion += "chgOrgRate.chgStatus = ?  and ";
              parameters.add(new Integer(status));
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
    return chgOrgRate;
  }

  /**
   * ���� ����������ѯ����ɱ�������ά���б� ��һ��
   */
  public List getChgOrgRateList_WL(final String orderBy, final String order,
      final int start, final int pageSize, final int page,
      final SecurityInfo securityInfo) {
    List chgOrgRateList = null;
    chgOrgRateList = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from ChgOrgRate chgOrgRate ";
            String criterion = " where chgOrgRate.org.id "
                + securityInfo.getGjjSecurityHqlSQL()
                + " and  chgOrgRate.paymentHead.id is null ";

            String ob = orderBy;
            if (ob == null)
              ob = " chgOrgRate.id  ";

            String od = order;
            if (od == null)
              od = "DESC";

            hql = hql + criterion + "  order by " + ob + " " + od;

            Query query = session.createQuery(hql);

            query.setFirstResult(start);
            if (pageSize > 0)
              query.setMaxResults(pageSize);

            List queryList = query.list();

            if (queryList == null || queryList.size() == 0) {
              query.setFirstResult(pageSize * (page - 2));
              if (pageSize > 0)
                query.setMaxResults(pageSize);
              queryList = query.list();
            }
            return queryList;
          }
        });
    return chgOrgRateList;
  }

  /**
   * ���� ����������ѯ����ɱ�������ά���б������ ��һ��
   */
  public int queryChgorgrateMaintainList(final SecurityInfo securityInfo) {

    int count = 0;
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from ChgOrgRate chgOrgRate ";
          String criterion = " where chgOrgRate.org.id "
              + securityInfo.getGjjSecurityHqlSQL()
              + " and  chgOrgRate.paymentHead.id is null ";

          hql = hql + criterion;

          session.clear();
          Query query = session.createQuery(hql);

          return query.list();
        }
      }

      );
      count = list.size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  /**
   * ���� ����������ѯ����ɱ�������ά���б�
   */
  public List getChgOrgRateListByCriterions_WL(final String orderBy,
      final String order, final int start, final int pageSize,
      final String orgID, final String orgName, final String chgDate,
      final int page, final SecurityInfo securityInfo) {
    List chgOrgRateList = null;
    chgOrgRateList = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from ChgOrgRate chgOrgRate ";
            Vector parameters = new Vector();
            String criterion = "";
            if (orgID != null && !orgID.equals("")) {
              criterion += "To_Char(chgOrgRate.org.id) like ? and ";
              parameters.add("%" + orgID + "%");
            }
            if (orgName != null && !orgName.equals("")) {
              criterion += " chgOrgRate.org.orgInfo.name like ? and ";
              parameters.add("%" + orgName + "%");
            }

            if (chgDate != null && !chgDate.equals("")) {
              criterion += "chgOrgRate.chgMonth = ?  and ";
              parameters.add(chgDate);
            }

            if (criterion.length() != 0) {
              criterion = "where chgOrgRate.org.id "
                  + securityInfo.getGjjSecurityHqlSQL() + " and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            } else {
              criterion = "where chgOrgRate.org.id "
                  + securityInfo.getGjjSecurityHqlSQL();
            }

            String ob = orderBy;
            if (ob == null)
              ob = " chgOrgRate.org.id  ";

            String od = order;
            if (od == null)
              od = "DESC";

            hql = hql + criterion + "  order by " + ob + " " + od;

            Query query = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }

            query.setFirstResult(start);
            if (pageSize > 0)
              query.setMaxResults(pageSize);

            List queryList = query.list();

            if (queryList == null || queryList.size() == 0) {
              query.setFirstResult(pageSize * (page - 2));
              if (pageSize > 0)
                query.setMaxResults(pageSize);
              queryList = query.list();
            }
            return queryList;
          }
        });
    return chgOrgRateList;
  }

  /**
   * ���� ����������ѯ����ɱ�������ά���б������
   */
  public int queryChgorgrateMaintainListByCriterions(final String orgID,
      final String orgName, final String chgDate,
      final SecurityInfo securityInfo) {

    int count = 0;
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from ChgOrgRate chgOrgRate ";
          Vector parameters = new Vector();
          String criterion = "";
          if (orgID != null && !orgID.equals("")) {
            criterion += "To_Char(chgOrgRate.org.id) like ? and ";
            parameters.add("%" + orgID + "%");
          }
          if (orgName != null && !orgName.equals("")) {
            criterion += " chgOrgRate.org.orgInfo.name like ? and ";
            parameters.add("%" + orgName + "%");
          }

          if (chgDate != null && !chgDate.equals("")) {
            criterion += "chgOrgRate.chgMonth = ?  and ";
            parameters.add(chgDate);
          }

          if (criterion.length() != 0) {
            criterion = "where chgOrgRate.org.id "
                + securityInfo.getGjjSecurityHqlSQL() + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            criterion = "where chgOrgRate.org.id "
                + securityInfo.getGjjSecurityHqlSQL();
          }

          hql = hql + criterion;

          session.clear();
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          return query.list();
        }
      }

      );
      count = list.size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  /**
   * ����ᣭ�������ݸ���������ѯ
   * 
   * @param officeCode
   * @param collectionBank
   * @param orgId
   * @param orgName
   * @param chgMonthStart
   * @param chgMonthEnd
   * @param chgDateStart
   * @param chgDateEnd
   * @param chgStatus
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @return
   */
  public List queryChgorgrateListByCriterions(final String officeCode,
      final String collectionBank, final String orgId, final String orgName,
      final String chgMonthStart, final String chgMonthEnd,
      final String chgDateStart, final String chgDateEnd,
      final Integer chgStatus, final int start, final int pageSize,
      final String orderBy, final String order, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from ChgOrgRate chgOrgRate";
          Vector parameters = new Vector();
          String criterion = "";
          if (officeCode != null && !officeCode.equals("")) {// ���´�
            criterion += "chgOrgRate.org.orgInfo.officecode = ? and ";
            parameters.add(officeCode);
          }
          if (collectionBank != null && !collectionBank.equals("")) {// �鼯����
            criterion += "chgOrgRate.org.orgInfo.collectionBankId = ? and ";
            parameters.add(collectionBank);
          }

          if (orgId != null && !orgId.equals("")) {// ��λ���
            criterion += "to_char(chgOrgRate.org.id) like ? and ";
            parameters.add("%" + orgId + "%");
          }

          if (orgName != null && !orgName.equals("")) {// ��λ����
            criterion += "chgOrgRate.org.orgInfo.name like ?  and ";
            parameters.add("%" + orgName + "%");
          }

          if (chgMonthStart != null && !chgMonthStart.equals("")
              && chgMonthEnd != null && !chgMonthEnd.equals("")) {// �п�ʼ���½�������
            criterion += "(chgOrgRate.chgMonth between ? and ? ) and ";
            parameters.add(chgMonthStart);
            parameters.add(chgMonthEnd);
          }

          if (chgMonthStart != null && !chgMonthStart.equals("")
              && (chgMonthEnd == null || chgMonthEnd.equals(""))) {// �п�ʼ�����޽�������
            criterion += "chgOrgRate.chgMonth = ? and ";
            parameters.add(chgMonthStart);
          }

          if (chgMonthEnd != null && !chgMonthEnd.equals("")
              && (chgMonthStart == null || chgMonthStart.equals(""))) {// �޿�ʼ�����н�������
            criterion += "chgOrgRate.chgMonth = ? and ";
            parameters.add(chgMonthEnd);
          }

          if (chgDateStart != null && !chgDateStart.equals("")
              && chgDateEnd != null && !chgDateEnd.equals("")) {// �п�ʼ���ڽ�������
            criterion += " (chgOrgRate.bizDate  between ? and ? ) and ";
            parameters.add(chgDateStart);
            parameters.add(chgDateEnd);
          }

          if (chgDateStart != null && !chgDateStart.equals("")
              && (chgDateEnd == null || chgDateEnd.equals(""))) {// �п�ʼ�����޽�������
            criterion += " chgOrgRate.bizDate = ? and ";
            parameters.add(chgDateStart);
          }

          if (chgDateEnd != null && !chgDateEnd.equals("")
              && (chgDateStart == null || chgDateStart.equals(""))) {// �޿�ʼ�����н�������
            criterion += " chgOrgRate.bizDate = ? and ";
            parameters.add(chgDateEnd);
          }

          if (chgStatus != null && !chgStatus.equals("")) {// ҵ��״̬
            criterion += " chgOrgRate.chgStatus = ? and ";
            parameters.add(chgStatus);
          }

          if (criterion.length() != 0) {
            criterion = " where chgOrgRate.org.id "
                + securityInfo.getGjjSecurityHqlSQL() + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          } else {
            hql = hql + " where chgOrgRate.org.id "
                + securityInfo.getGjjSecurityHqlSQL();
          }
          String ob = orderBy;
          if (ob == null)
            ob = " chgOrgRate.id ";

          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "  order by " + ob + " " + od;
          session.clear();
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          return query.list();
        }
      }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * ����� ����������ѯ������(��ҳ)
   * 
   * @param officeCode
   * @param collectionBank
   * @param orgId
   * @param orgName
   * @param chgMonthStart
   * @param chgMonthEnd
   * @param chgDateStart
   * @param chgDateEnd
   * @param chgStatus
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @return
   */
  public List queryChgorgrateListSizeByCriterions(final String officeCode,
      final String collectionBank, final String orgId, final String orgName,
      final String chgMonthStart, final String chgMonthEnd,
      final String chgDateStart, final String chgDateEnd,
      final Integer chgStatus, final int start, final int pageSize,
      final String orderBy, final String order, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from ChgOrgRate chgOrgRate";
          Vector parameters = new Vector();
          String criterion = "";
          if (officeCode != null && !officeCode.equals("")) {// ���´�
            criterion += "chgOrgRate.org.orgInfo.officecode = ? and ";
            parameters.add(officeCode);
          }
          if (collectionBank != null && !collectionBank.equals("")) {// �鼯����
            criterion += "chgOrgRate.org.orgInfo.collectionBankId = ? and ";
            parameters.add(collectionBank);
          }

          if (orgId != null && !orgId.equals("")) {// ��λ���
            criterion += "to_char(chgOrgRate.org.id) like ?  and ";
            parameters.add("%" + orgId + "%");
          }

          if (orgName != null && !orgName.equals("")) {// ��λ����
            criterion += "chgOrgRate.org.orgInfo.name like ?  and ";
            parameters.add("%" + orgName + "%");
          }

          if (chgMonthStart != null && !chgMonthStart.equals("")
              && chgMonthEnd != null && !chgMonthEnd.equals("")) {// �п�ʼ���½�������
            criterion += "(chgOrgRate.chgMonth between ? and ? ) and ";
            parameters.add(chgMonthStart);
            parameters.add(chgMonthEnd);
          }

          if (chgMonthStart != null && !chgMonthStart.equals("")
              && (chgMonthEnd == null || chgMonthEnd.equals(""))) {// �п�ʼ�����޽�������
            criterion += "chgOrgRate.chgMonth = ? and ";
            parameters.add(chgMonthStart);
          }

          if (chgMonthEnd != null && !chgMonthEnd.equals("")
              && (chgMonthStart == null || chgMonthStart.equals(""))) {// �޿�ʼ�����н�������
            criterion += "chgOrgRate.chgMonth = ? and ";
            parameters.add(chgMonthEnd);
          }

          if (chgDateStart != null && !chgDateStart.equals("")
              && chgDateEnd != null && !chgDateEnd.equals("")) {// �п�ʼ���ڽ�������
            criterion += " (chgOrgRate.bizDate  between ? and ? ) and ";
            parameters.add(chgDateStart);
            parameters.add(chgDateEnd);
          }

          if (chgDateStart != null && !chgDateStart.equals("")
              && (chgDateEnd == null || chgDateEnd.equals(""))) {// �п�ʼ�����޽�������
            criterion += " chgOrgRate.bizDate = ? and ";
            parameters.add(chgDateStart);
          }

          if (chgDateEnd != null && !chgDateEnd.equals("")
              && (chgDateStart == null || chgDateStart.equals(""))) {// �޿�ʼ�����н�������
            criterion += " chgOrgRate.bizDate = ? and ";
            parameters.add(chgDateEnd);
          }

          if (chgStatus != null && !chgStatus.equals("")) {// ҵ��״̬
            criterion += " chgOrgRate.chgStatus = ? and ";
            parameters.add(chgStatus);
          }

          if (criterion.length() != 0) {
            criterion = " where chgOrgRate.org.id "
                + securityInfo.getGjjSecurityHqlSQL() + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
          } else {
            hql = hql + " where chgOrgRate.org.id "
                + securityInfo.getGjjSecurityHqlSQL();
          }

          session.clear();
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          return query.list();
        }
      }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  /**
   * ����� ��������ͳ�Ƶ�λ�������ظ���
   * 
   * @param officeCode
   * @param collectionBank
   * @param orgId
   * @param orgName
   * @param chgMonthStart
   * @param chgMonthEnd
   * @param chgDateStart
   * @param chgDateEnd
   * @param chgStatus
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @return
   */
  public List queryChgorgrateOrgSizeByCriterions(final String officeCode,
      final String collectionBank, final String orgId, final String orgName,
      final String chgMonthStart, final String chgMonthEnd,
      final String chgDateStart, final String chgDateEnd,
      final Integer chgStatus, final int start, final int pageSize,
      final String orderBy, final String order, final SecurityInfo securityInfo) {

    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct chgOrgRate.org.id from ChgOrgRate chgOrgRate";
          Vector parameters = new Vector();
          String criterion = "";
          if (officeCode != null && !officeCode.equals("")) {// ���´�
            criterion += "chgOrgRate.org.orgInfo.officecode = ? and ";
            parameters.add(officeCode);
          }
          if (collectionBank != null && !collectionBank.equals("")) {// �鼯����
            criterion += "chgOrgRate.org.orgInfo.collectionBankId = ? and ";
            parameters.add(collectionBank);
          }

          if (orgId != null && !orgId.equals("")) {// ��λ���
            criterion += "to_char(chgOrgRate.org.id) like ?  and ";
            parameters.add("%" + orgId + "%");
          }

          if (orgName != null && !orgName.equals("")) {// ��λ����
            criterion += "chgOrgRate.org.orgInfo.name like ?  and ";
            parameters.add("%" + orgName + "%");
          }

          if (chgMonthStart != null && !chgMonthStart.equals("")
              && chgMonthEnd != null && !chgMonthEnd.equals("")) {// �п�ʼ���½�������
            criterion += "(chgOrgRate.chgMonth between ? and ? ) and ";
            parameters.add(chgMonthStart);
            parameters.add(chgMonthEnd);
          }

          if (chgMonthStart != null && !chgMonthStart.equals("")
              && (chgMonthEnd == null || chgMonthEnd.equals(""))) {// �п�ʼ�����޽�������
            criterion += "chgOrgRate.chgMonth = ? and ";
            parameters.add(chgMonthStart);
          }

          if (chgMonthEnd != null && !chgMonthEnd.equals("")
              && (chgMonthStart == null || chgMonthStart.equals(""))) {// �޿�ʼ�����н�������
            criterion += "chgOrgRate.chgMonth = ? and ";
            parameters.add(chgMonthEnd);
          }

          if (chgDateStart != null && !chgDateStart.equals("")
              && chgDateEnd != null && !chgDateEnd.equals("")) {// �п�ʼ���ڽ�������
            criterion += " (chgOrgRate.bizDate  between ? and ? ) and ";
            parameters.add(chgDateStart);
            parameters.add(chgDateEnd);
          }

          if (chgDateStart != null && !chgDateStart.equals("")
              && (chgDateEnd == null || chgDateEnd.equals(""))) {// �п�ʼ�����޽�������
            criterion += " chgOrgRate.bizDate = ? and ";
            parameters.add(chgDateStart);
          }

          if (chgDateEnd != null && !chgDateEnd.equals("")
              && (chgDateStart == null || chgDateStart.equals(""))) {// �޿�ʼ�����н�������
            criterion += " chgOrgRate.bizDate = ? and ";
            parameters.add(chgDateEnd);
          }

          if (chgStatus != null && !chgStatus.equals("")) {// ҵ��״̬
            criterion += " chgOrgRate.chgStatus = ? and ";
            parameters.add(chgStatus);
          }

          if (criterion.length() != 0) {
            criterion = " where chgOrgRate.org.id "
                + securityInfo.getGjjSecurityHqlSQL() + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
          } else {
            hql = hql + " where chgOrgRate.org.id "
                + securityInfo.getGjjSecurityHqlSQL();
          }

          session.clear();
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          return query.list();
        }
      }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }
  /**
   * ����� ��������ͳ�Ƶ�λ������������������ظ���
   * @param officeCode
   * @param collectionBank
   * @param orgId
   * @param orgName
   * @param chgMonthStart
   * @param chgMonthEnd
   * @param chgDateStart
   * @param chgDateEnd
   * @param chgStatus
   * @param start
   * @param pageSize
   * @param orderBy
   * @param order
   * @param securityInfo
   * @return
   */
  public List queryCountsByCriterions(final String officeCode,
      final String collectionBank, final String orgId, final String orgName,
      final String chgMonthStart, final String chgMonthEnd,
      final String chgDateStart, final String chgDateEnd,
      final Integer chgStatus, final int start, final int pageSize,
      final String orderBy, final String order, final SecurityInfo securityInfo) {

    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select chgOrgRate.org.id from ChgOrgRate chgOrgRate";
          Vector parameters = new Vector();
          String criterion = "";
          if (officeCode != null && !officeCode.equals("")) {// ���´�
            criterion += "chgOrgRate.org.orgInfo.officecode = ? and ";
            parameters.add(officeCode);
          }
          if (collectionBank != null && !collectionBank.equals("")) {// �鼯����
            criterion += "chgOrgRate.org.orgInfo.collectionBankId = ? and ";
            parameters.add(collectionBank);
          }

          if (orgId != null && !orgId.equals("")) {// ��λ���
            criterion += "to_char(chgOrgRate.org.id) like ?  and ";
            parameters.add("%" + orgId + "%");
          }

          if (orgName != null && !orgName.equals("")) {// ��λ����
            criterion += "chgOrgRate.org.orgInfo.name like ?  and ";
            parameters.add("%" + orgName + "%");
          }

          if (chgMonthStart != null && !chgMonthStart.equals("")
              && chgMonthEnd != null && !chgMonthEnd.equals("")) {// �п�ʼ���½�������
            criterion += "(chgOrgRate.chgMonth between ? and ? ) and ";
            parameters.add(chgMonthStart);
            parameters.add(chgMonthEnd);
          }

          if (chgMonthStart != null && !chgMonthStart.equals("")
              && (chgMonthEnd == null || chgMonthEnd.equals(""))) {// �п�ʼ�����޽�������
            criterion += "chgOrgRate.chgMonth = ? and ";
            parameters.add(chgMonthStart);
          }

          if (chgMonthEnd != null && !chgMonthEnd.equals("")
              && (chgMonthStart == null || chgMonthStart.equals(""))) {// �޿�ʼ�����н�������
            criterion += "chgOrgRate.chgMonth = ? and ";
            parameters.add(chgMonthEnd);
          }

          if (chgDateStart != null && !chgDateStart.equals("")
              && chgDateEnd != null && !chgDateEnd.equals("")) {// �п�ʼ���ڽ�������
            criterion += " (chgOrgRate.bizDate  between ? and ? ) and ";
            parameters.add(chgDateStart);
            parameters.add(chgDateEnd);
          }

          if (chgDateStart != null && !chgDateStart.equals("")
              && (chgDateEnd == null || chgDateEnd.equals(""))) {// �п�ʼ�����޽�������
            criterion += " chgOrgRate.bizDate = ? and ";
            parameters.add(chgDateStart);
          }

          if (chgDateEnd != null && !chgDateEnd.equals("")
              && (chgDateStart == null || chgDateStart.equals(""))) {// �޿�ʼ�����н�������
            criterion += " chgOrgRate.bizDate = ? and ";
            parameters.add(chgDateEnd);
          }

          if (chgStatus != null && !chgStatus.equals("")) {// ҵ��״̬
            criterion += " chgOrgRate.chgStatus = ? and ";
            parameters.add(chgStatus);
          }

          if (criterion.length() != 0) {
            criterion = " where chgOrgRate.org.id "
                + securityInfo.getGjjSecurityHqlSQL() + " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
          } else {
            hql = hql + " where chgOrgRate.org.id "
                + securityInfo.getGjjSecurityHqlSQL();
          }

          session.clear();
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          return query.list();
        }
      }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }


  /**
   * �����
   */
  public BigDecimal queryRate(final Integer id) {

    BigDecimal rate = new BigDecimal(0);
    try {
      rate = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = "select round(((a.org_pay+a.emp_pay)-(a.old_org_pay+a.old_emp_pay))/(a.old_org_pay+a.old_emp_pay),2) from aa201 a where a.id = "
                  + id;
              Query query = session.createSQLQuery(sql);
              return new BigDecimal(query.uniqueResult().toString());

              /**
               * Query query = session.createSQLQuery(sql); //
               * query.setParameter(0, parameters.get(0)); Object obj[] = null;
               * BigDecimal chgorgrate = new BigDecimal(0.00); Iterator iterate =
               * query.list().iterator(); while (iterate.hasNext()) { obj =
               * (Object[]) iterate.next(); chgorgrate=new
               * BigDecimal(obj[0].toString()); } return chgorgrate;
               */
            }
          }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }
    return rate;
  }
  /**
   * ����� COUNT 201
   * @param office
   * @param bank
   * @param orgCharacter
   * @param dept
   * @param ragion
   * @param startDate
   * @param endDate
   * @return
   */
  public int queryCount(final String office,final String bank,final String orgCharacter,
      final String dept,final String ragion,final String startDate,final String endDate){
    int count = 0;
    try{
      Integer countInteger = (Integer)getHibernateTemplate().execute(
        new HibernateCallback(){
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
     
        String hql = "select count(chgOrgRate.id) from ChgOrgRate chgOrgRate ";
        Vector parameters = new Vector();
        String criterion = "";
        if(office != null && !"".equals(office)){
          criterion += " chgOrgRate.org.orgInfo.officecode = ? and ";
          parameters.add(office);
        }
        if(bank != null && !"".equals(bank)){
          criterion += " chgOrgRate.org.orgInfo.collectionBankId = ? and ";
          parameters.add(bank);
        }
        if(orgCharacter != null && !"".equals(orgCharacter)){
          criterion += " chgOrgRate.org.orgInfo.character = ? and ";
          parameters.add(orgCharacter);
        }
        if(dept != null && !"".equals(dept)){
          criterion += " chgOrgRate.org.orgInfo.deptInCharge = ? and ";
          parameters.add(dept);
        }
        if(ragion != null && !"".equals(ragion)){
          criterion += " chgOrgRate.org.orgInfo.region = ? and ";
          parameters.add(ragion);
        }
        if(startDate != null && !"".equals(startDate) && endDate != null && !"".equals(endDate)){
          criterion += " (chgOrgRate.bizDate  between ?  and  ?)  and ";
          parameters.add(startDate);
          parameters.add(endDate);
        }
        if (criterion.length() != 0) 
//          criterion = "where (orgHAFAccountFlow.bizStatus = 3 or orgHAFAccountFlow.bizStatus = 4 or orgHAFAccountFlow.bizStatus = 5) and orgHAFAccountFlow.org.id  "
//              + securityInfo.getGjjSecurityHqlSQL()
//              + " and "
//              + criterion.substring(0, criterion.lastIndexOf("and"));
          criterion = " where chgOrgRate.chgStatus=2 and "
            + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion ;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Integer countTemp=new Integer(0);
          Iterator it=query.iterate();          
          if(it.hasNext()){
            countTemp=(Integer)it.next();
          }
          return countTemp;
      }
    });
      count = countInteger.intValue();
    }catch(Exception e){
      e.printStackTrace();
    }
    return count;
  }
  
  /**
   * ����� sum 
   * @param office
   * @param bank
   * @param orgCharacter
   * @param dept
   * @param ragion
   * @param startDate
   * @param endDate
   * @return
   */
  public int querySum(final String office,final String bank,final String orgCharacter,
      final String dept,final String ragion,final String startDate,final String endDate){
    int count = 0;
    try{
      Integer countInteger = (Integer)getHibernateTemplate().execute(
        new HibernateCallback(){
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
     
        String hql = "select sum((chgOrgRate.orgPay+chgOrgRate.empPay)-(chgOrgRate.oldOrgPay+chgOrgRate.oldEmpPay)) from ChgOrgRate chgOrgRate ";
        Vector parameters = new Vector();
        String criterion = "";
        if(office != null && !"".equals(office)){
          criterion += " chgOrgRate.org.orgInfo.officecode = ? and ";
          parameters.add(office);
        }
        if(bank != null && !"".equals(bank)){
          criterion += " chgOrgRate.org.orgInfo.collectionBankId = ? and ";
          parameters.add(bank);
        }
        if(orgCharacter != null && !"".equals(orgCharacter)){
          criterion += " chgOrgRate.org.orgInfo.character = ? and ";
          parameters.add(orgCharacter);
        }
        if(dept != null && !"".equals(dept)){
          criterion += " chgOrgRate.org.orgInfo.deptInCharge = ? and ";
          parameters.add(dept);
        }
        if(ragion != null && !"".equals(ragion)){
          criterion += " chgOrgRate.org.orgInfo.region = ? and ";
          parameters.add(ragion);
        }
        if(startDate != null && !"".equals(startDate) && endDate != null && !"".equals(endDate)){
          criterion += " (chgOrgRate.bizDate  between ?  and  ?)  and ";
          parameters.add(startDate);
          parameters.add(endDate);
        }
        if (criterion.length() != 0) 
//          criterion = "where (orgHAFAccountFlow.bizStatus = 3 or orgHAFAccountFlow.bizStatus = 4 or orgHAFAccountFlow.bizStatus = 5) and orgHAFAccountFlow.org.id  "
//              + securityInfo.getGjjSecurityHqlSQL()
//              + " and "
//              + criterion.substring(0, criterion.lastIndexOf("and"));
          criterion = " where  "
            + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion ;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          BigDecimal countTemp=new BigDecimal(0.00);
          Iterator it=query.iterate();          
          if(it.hasNext()){
            countTemp=(BigDecimal)it.next();
          }
          if(countTemp == null){
            return new Integer(0);
          }else{
          return new Integer(countTemp.intValue());
          }
      }
    });
      count = countInteger.intValue();
    }catch(Exception e){
      e.printStackTrace();
    }
    return count;
  }
  
}
