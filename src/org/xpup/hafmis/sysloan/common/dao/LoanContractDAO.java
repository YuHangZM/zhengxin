package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanContract;
import org.xpup.hafmis.sysloan.common.domain.entity.PledgeContract;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.dto.EndorsecontractTaDTO;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTaAF;

public class LoanContractDAO extends HibernateDaoSupport {
  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public LoanContract queryById(Serializable id) {

    Validate.notNull(id);
    return  (LoanContract) getHibernateTemplate().get(LoanContract.class,id);
   
  }
  /**
   * ��������ɾ��
   * yuqf
   * @param id
   * @return
   */
  public void deleteById(String id) {
    try {
      Validate.notNull(id);
      // getHibernateTemplate().clear();
      LoanContract loanContract = queryById(id);

      getHibernateTemplate().delete(loanContract);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * ����������ѯ
   * yuqf
   * @param id
   * @return
   */
  public String queryByIdYU(final String id){
    Validate.notNull(id);
    String idd = "";
    try{
      idd = (String) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // TODO Auto-generated method stub
          
          String hql = "select t.contract_id from pl120 t where t.contract_id='"+id+"'";
         
          Query query = session.createSQLQuery(hql);
        
          return query.uniqueResult();
        }

      });
    }catch(Exception e){
      e.printStackTrace();
    }
    return idd;
  }
  /**
   * ����������ѯ
   * yuqf
   * @param id
   * @return
   */
  public String queryById_yg(final String id){
    Validate.notNull(id);
    String idd = "";
    try{
      idd = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
        SQLException {
          String hql = "select t.contract_id from pl121 t where t.status = '0' and t.contract_id='"+id+"'";
          Query query = session.createSQLQuery(hql);
          return query.uniqueResult()==null ? "":query.uniqueResult();
        }
      });
    }catch(Exception e){
      e.printStackTrace();
    }
    return idd;
  }
  /**
   * yuqf
   * 2007-10-29
   * ��ѯ������˾����
   * @param id
   * @return
   */
  public String queryAssOrgByIdYU(final String id){
    Validate.notNull(id);
    String idd = "";
    try{
      idd = (String) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // TODO Auto-generated method stub
          String hql = "select tt.assistant_org_name from pl120 t,pl007 tt where t.assistant_org_id=tt.assistant_org_id and t.contract_id='"+id+"'";
          Query query = session.createSQLQuery(hql);
     
          return query.uniqueResult();
        }

      });
    }catch(Exception e){
      e.printStackTrace();
    }
    return idd;
  }

  /**
   * �����¼
   * @param LoanContract
   * @return
   */
  public Serializable insert(LoanContract loanContract){
    Serializable id = null;
    try{    
    Validate.notNull(loanContract);
    id = getHibernateTemplate().save(loanContract);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
  /**
   * yuqf
   * ��ѯPL120�ֶΣ����ڽ���ͬ��Ϣҳ����ʾ
   * @param id
   * @return
   */
  public EndorsecontractTaDTO queryLoanContractInfoYU(final String id,final SecurityInfo securityInfo){
    EndorsecontractTaDTO endorsecontractTaDTO = null;
    try{
      endorsecontractTaDTO = (EndorsecontractTaDTO) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          // TODO Auto-generated method stub
          String hql = "select t.assurer,t.agreement_date,t.loan_start_date,t.assistant_org_id,t.photo_url2 from pl120 t where t.contract_id='"+id+"'";
          Query query = session.createSQLQuery(hql);
        
          EndorsecontractTaDTO endorsecontractTaDTO = new EndorsecontractTaDTO();
          Iterator iterate = query.list().iterator();
          Object[] obj = null;
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            if(obj[0] != null){
              endorsecontractTaDTO.setAssurer(obj[0].toString());//��֤��
            }
            if(obj[1] != null){
              endorsecontractTaDTO.setContractSureDate(obj[1].toString());//��ͬǩ������
            }
            if(obj[2] != null){
              endorsecontractTaDTO.setDebitMoneyStaDate(obj[2].toString());//�����ʼ����
            }
            if(obj[3]!= null){
              endorsecontractTaDTO.setAssistantOrgId(obj[3].toString());//������˾����
            }
            if(obj[4]!=null){
              endorsecontractTaDTO.setPhotoUrl(obj[4].toString());//·��
            }
          }
          /*********************3**************************/
//          if(query.uniqueResult() != null){
//            endorsecontractTaDTO.setAssurer(query.uniqueResult().toString());
//          }
          /*********************3**************************/
          return endorsecontractTaDTO;
        }

      });
    }catch(Exception e){
      e.printStackTrace();
    }
    return endorsecontractTaDTO;
  }
  /**
   * �´﷢��֪ͨ��_ȷ��
   * @author wsh
   * 2007-10-03
   * ����PL120�еĺ�ͬ����޸�PL120.LOAN_PAY_DATE
   * ��ѯ������contractId
   */
  public void updateLoanContract_wsh(final String loanPayDate,final String  contractId) throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "update LoanContract loanContract " +
              "set loanContract.loanPayDate = ? " +             
              "where loanContract.contractId = ? ";
          Query query=session.createQuery(hql);
          query.setString(0, loanPayDate);
          query.setString(1, contractId);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
public String findIssuenoticeBizDate(final String contractId)throws Exception{
        String bizDate = (String) getHibernateTemplate().execute(
            new HibernateCallback() {
              public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                String hql = "select t.loan_pay_date from pl120 t where t.contract_id= ? ";
                Vector parameters = new Vector();
                if (contractId != null) {
                  parameters.add(contractId);
                }
                Query query = session.createSQLQuery(hql);
                for (int i = 0; i < parameters.size(); i++) {
                  query.setParameter(i, parameters.get(i));
                }
                Object obj = null;
                obj = (Object) query.uniqueResult();
                String temp_bizDate = "";
                if (obj != null) {
                  temp_bizDate = obj.toString();
                }
                return temp_bizDate;
              }
            });
        return bizDate;
}
public String findloanBankInAccount(final String contractId)throws Exception{
  String bizDate = (String) getHibernateTemplate().execute(
      new HibernateCallback() {
        public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
          String hql = "select t.inAccount from pl002 t where t.loan_bank_id= ? ";
          Vector parameters = new Vector();
          if (contractId != null) {
            parameters.add(contractId);
          }
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Object obj = null;
          obj = (Object) query.uniqueResult();
          String temp_bizDate = "";
          if (obj != null) {
            temp_bizDate = obj.toString();
          }
          return temp_bizDate;
        }
      });
  return bizDate;
}
public String findloanBankOutAccount(final String contractId)throws Exception{
  String bizDate = (String) getHibernateTemplate().execute(
      new HibernateCallback() {
        public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
          String hql = "select t.outAccount from pl002 t where t.loan_bank_id= ? ";
          Vector parameters = new Vector();
          if (contractId != null) {
            parameters.add(contractId);
          }
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          Object obj = null;
          obj = (Object) query.uniqueResult();
          String temp_bizDate = "";
          if (obj != null) {
            temp_bizDate = obj.toString();
          }
          return temp_bizDate;
        }
      });
  return bizDate;
}
}
