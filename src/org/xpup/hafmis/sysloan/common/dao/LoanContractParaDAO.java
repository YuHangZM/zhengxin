package org.xpup.hafmis.sysloan.common.dao;

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
import org.xpup.hafmis.sysloan.common.domain.entity.LoanContractPara;

/**
 * ��ͬ�������PL004
 * 
 *@author ���
 *2007-9-13
 */
public class LoanContractParaDAO extends HibernateDaoSupport {
  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public LoanContractPara queryById(Serializable id) {  
    Validate.notNull(id);
    return  (LoanContractPara) getHibernateTemplate().get(LoanContractPara.class,id);    
  }
  /**
   * �����¼
   * 
   * @param loanContractPara
   * @return
   */
  public Serializable insert(LoanContractPara loanContractPara) {
    Serializable id = null;
    try {
      Validate.notNull(loanContractPara);
      id = getHibernateTemplate().save(loanContractPara);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  /**
   * ����������ѯ��ǰ�������ֵ
   * �������ѡ����ǰ����
   * jj
   * @param loanBankId
   * @param paramType
   * @param paramNum
   * @return
   */
  public String queryParamValue_LJ(final Integer loanBankId,final String paramType, final String paramNum,final String contractId){
    String paramValue = "";
    try {
      paramValue = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanContractPara.paramValue " +
              " from LoanContractPara loanContractPara " ;
             // " where loanBankPara.paramType='A' and loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanContractPara.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId.toString()));
          }
          if (paramType != null && !paramType.equals("")) {
            criterion += "loanContractPara.paramType = ?  and ";
            parameters.add(paramType);
          }
          if (paramNum != null && !paramNum.equals("")) {
            criterion += "loanContractPara.paramNum = ?  and ";
            parameters.add(paramNum);
          }
          if (contractId != null && !contractId.equals("")) {
            criterion += "loanContractPara.contractId = ?  and ";
            parameters.add(contractId);
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramValue;
  } 
  public String queryParamValue_wsh(final Integer loanBankId,final String paramType, final String paramNum,final String contractId){
    String paramValue = "";
    try {
      paramValue = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanBankPara.paramValue " +
              " from LoanBankPara loanBankPara " ;
             // " where loanBankPara.paramType='A' and loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanBankPara.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId.toString()));
          }
          if (paramType != null && !paramType.equals("")) {
            criterion += "loanBankPara.paramType = ?  and ";
            parameters.add(paramType);
          }
          if (paramNum != null && !paramNum.equals("")) {
            criterion += "loanBankPara.paramNum = ?  and ";
            parameters.add(paramNum);
          }
//          if (contractId != null && !contractId.equals("")) {
//            criterion += "LoanBankPara.contractId = ?  and ";
//            parameters.add(contractId);
//          }
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramValue;
  } 
  /**
   * ����������ѯ��ǰ�������˵��
   * �������ѡ����ǰ����
   * jj
   * @param loanBankId
   * @param paramType
   * @param paramNum
   * @return
   */
  public String queryParamExplain_LJ(final Integer loanBankId,final String paramType, final String paramNum,final String contractId){
    String paramExplain = "";
    try {
      paramExplain = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanContractPara.paramExplain " +
              " from LoanContractPara loanContractPara " ;
             // " where loanBankPara.paramType='A' and loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanContractPara.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId.toString()));
          }
          if (paramType != null && !paramType.equals("")) {
            criterion += "loanContractPara.paramType = ?  and ";
            parameters.add(paramType);
          }
          if (paramNum != null && !paramNum.equals("")) {
            criterion += "loanContractPara.paramNum = ?  and ";
            parameters.add(paramNum);
          }
          if (contractId != null && !contractId.equals("")) {
            criterion += "loanContractPara.contractId = ?  and ";
            parameters.add(contractId);
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramExplain;
  }
  public String queryParamExplain_wsh(final Integer loanBankId,final String paramType, final String paramNum,final String contractId){
    String paramExplain = "";
    try {
      paramExplain = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanBankPara.paramExplain " +
              " from LoanBankPara loanBankPara " ;
             // " where loanBankPara.paramType='A' and loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanBankPara.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId.toString()));
          }
          if (paramType != null && !paramType.equals("")) {
            criterion += "loanBankPara.paramType = ?  and ";
            parameters.add(paramType);
          }
          if (paramNum != null && !paramNum.equals("")) {
            criterion += "loanBankPara.paramNum = ?  and ";
            parameters.add(paramNum);
          }
//          if (contractId != null && !contractId.equals("")) {
//            criterion += "LoanBankPara.contractId = ?  and ";
//            parameters.add(contractId);
//          }
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramExplain;
  }
  public String queryParamExplain_wsh(final Integer loanBankId,final String paramType, final String paramNum,final String contractId,final String paranValue){
    String paramExplain = "";
    try {
      paramExplain = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanContractPara.paramExplain " +
              " from LoanContractPara loanContractPara " ;
             // " where loanBankPara.paramType='A' and loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
          Vector parameters = new Vector();
          String criterion = "";
          
          if (loanBankId != null && !loanBankId.equals("")) {
            criterion += "loanContractPara.loanBankId = ?  and ";
            parameters.add(new BigDecimal(loanBankId.toString()));
          }
          if (paramType != null && !paramType.equals("")) {
            criterion += "loanContractPara.paramType = ?  and ";
            parameters.add(paramType);
          }
          if (paramNum != null && !paramNum.equals("")) {
            criterion += "loanContractPara.paramNum = ?  and ";
            parameters.add(paramNum);
          }
//          if (contractId != null && !contractId.equals("")) {
//            criterion += "loanContractPara.contractId = ?  and ";
//            parameters.add(contractId);
//          }
          if (paranValue != null && !paranValue.equals("")) {
            criterion += "loanContractPara.paramValue = ?  and ";
            parameters.add(paranValue);
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramExplain;
  }
  /**
   * ��ǰ�������ά��
   * @author ���ƽ
   * 2007-10-04
   * ����loanBankId��paramTypeɾ��pl004������
   */
  public void deleteLoanBankPara(final String loanBankId,final String paramType){
    try{
      getHibernateTemplate().execute(
          new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
             String sql="delete LoanContractPara loanContractPara where loanContractPara.loanBankId=? and loanContractPara.paramType=? ";          
             session.createQuery(sql).setBigDecimal(0, new BigDecimal(loanBankId)).setString(1, paramType).executeUpdate();
              return null;
          }
      }  
  );
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  /**
   * ������Ϣ���
   * @author ���ƽ
   * 2007-10-06
   * ����contractId��paramTypeɾ��pl004������
   */
  public void deleteLoanBankParaByContractId(final String contractId,final String paramType){
    try{
      getHibernateTemplate().execute(
          new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
             String sql="delete LoanContractPara loanContractPara where loanContractPara.contractId=? and loanContractPara.paramType=? ";          
             session.createQuery(sql).setString(0, contractId).setString(1, paramType).executeUpdate();
              return null;
          }
      }  
  );
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  /**
   * ������Ϣ���
   * @author ���ƽ
   * 2007-10-05
   * ����contractId��paramType��ѯpl004������
   */
  public List queryParamByContractId(final String contractId,final String paramType) throws Exception {
    List list=null;
    try {
      list =(List)getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select "+"distinct "+
                                  "pl004.param_value,"+
                                  "pl004.param_num,"+
                                  "pl004.param_explain "+
                                  "from PL004 pl004 " +
                                  "where pl004.contract_id=? " +
                                  "and pl004.param_type=? ";
            Query query = session.createSQLQuery(hql);
            query.setString(0, contractId);
            query.setString(1, paramType);
            Iterator it = query.list().iterator();
            List temp_list = new ArrayList();
            Object obj[] = null;
            while (it.hasNext()) {
              LoanContractPara loanContractPara=new LoanContractPara();
              obj = (Object[]) it.next();
              if(obj[0]!=null){
                loanContractPara.setParamValue(obj[0].toString());
              }else{
                loanContractPara.setParamValue("");
              }
              loanContractPara.setParamNum(obj[1].toString());
              if(obj[2]!=null){
                loanContractPara.setParamExplain(obj[2].toString());
              }else{
                loanContractPara.setParamExplain("");
              }
              temp_list.add(loanContractPara);
            }
            return temp_list;                                      
          }
        }
    );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  public String queryParamExplain_wsh_1(final Integer loanBankId,final String paramType, final String paramNum,final String contractId,final String paranValue){
    String paramExplain = "";
    try {
      paramExplain = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanBankPara.paramExplain " +
          " from LoanBankPara loanBankPara " ;
         // " where loanBankPara.paramType='A' and loanBankPara.paramNum='1' and loanBankPara.loanBankId = ? ";
      Vector parameters = new Vector();
      String criterion = "";
      
      if (loanBankId != null && !loanBankId.equals("")) {
        criterion += "loanBankPara.loanBankId = ?  and ";
        parameters.add(new BigDecimal(loanBankId.toString()));
      }
      if (paramType != null && !paramType.equals("")) {
        criterion += "loanBankPara.paramType = ?  and ";
        parameters.add(paramType);
      }
      if (paramNum != null && !paramNum.equals("")) {
        criterion += "loanBankPara.paramNum = ?  and ";
        parameters.add(paramNum);
      }
      if (paranValue != null && !paranValue.equals("")) {
        criterion += "loanBankPara.paramValue = ?  and ";
        parameters.add(paranValue);
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramExplain;
  }
}
