package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpAddPay;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail;

/**
 * ת��ͷ��
 * 
 *@author ���
 *2007-6-19
 */
public class TranInHeadDAO extends HibernateDaoSupport{
  public void updataTranInHead_sy(TranInHead tranInHead)throws NumberFormatException, Exception{
    TranInHead tranInHeadUpdate=(TranInHead)getHibernateTemplate().load(TranInHead.class, tranInHead.getId());
    tranInHeadUpdate.setDocNum(tranInHead.getDocNum());
    if(tranInHead.getNoteNum()!=null&&tranInHead.getNoteNum().equals("")){
    tranInHeadUpdate.setNoteNum(tranInHead.getNoteNum());
    }
    tranInHeadUpdate.setSettDate(tranInHead.getSettDate());
    tranInHeadUpdate.setTranStatus(tranInHead.getTranStatus());
    //getHibernateTemplate().update(tranInHead);

  }

  /**ת�뵥λ��ת����λ��ת��id��ת��id��ת��Ʊ�ݱ�ţ�ת��Ʊ�ݣ�ת��״̬��ת�����ڱ�Ų��Ҷ�Ӧ�ļ�¼
   * 
   * 
   */
    public List queryTranInListByCriterionsAll_sy(final String settDate,final String settDatea,final String tranStatus,
        final String inOrgId, final String outOrgId, final String outOrgName,
        final String inOrgName, final String noteNum, final String docNum,
        final String orderBy, final String order, final int start,
        final int pageSize,final SecurityInfo securityInfo,final int page,final String collBankId) throws NumberFormatException, Exception {
      List list = null;
//     tranStatusΪBigDecimal
      try {
        Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
            || "DESC".equalsIgnoreCase(order));
        Validate.isTrue(start >= 0);

        list = getHibernateTemplate().executeFind(

        new HibernateCallback() {
          public Object doInHibernate(Session session) throws HibernateException,
              SQLException {
            String hql = " from TranInHead tranInHead ";
            Vector parameters = new Vector();
            String criterion = "";
            if (tranStatus != null && !tranStatus.equals("")) {
              //��һ�ν���ת��ά��ʱ���״̬1��3
              if(tranStatus.equals("13")){
              criterion += "(tranInHead.tranStatus= 1  or tranInHead.tranStatus= 3  ) and ";
              }else{
              criterion += "tranInHead.tranStatus= ?  and ";
              parameters.add(new BigDecimal(tranStatus));
              }
             }
            if (settDate != null && !settDate.equals("")) {
              criterion += "tranInHead.settDate >= ?  and ";
              parameters.add(settDate);
            }
            if (settDatea != null && !settDatea.equals("")) {
              criterion += "tranInHead.settDate <= ?  and ";
              parameters.add(settDatea);
            }
            if (inOrgId != null && !inOrgId.equals("")) {
              criterion += "to_char(tranInHead.tranInOrg.id) like ?  and ";
              parameters.add("%"+new Integer(inOrgId)+"%");
            }
            if (outOrgId != null && !outOrgId.equals("")) {
              criterion += "tranInHead.tranOutOrgId like ?  and ";
              parameters.add("%"+outOrgId+"%");
            }
//            if (outOrgName != null && !outOrgName.equals("")) {
//              criterion += "tranInHead.tranOutOrg.orgInfo.name= ?  and ";
//              parameters.add(outOrgName);
//            }
            //ģ��
            if (outOrgName != null && !outOrgName.equals("")) {
              criterion += "tranInHead.id in (select tranInHead.id from TranOutOrg tranOutOrg,TranInHead tranInHead  where  tranInHead.tranOutOrgId=tranOutOrg.id and  tranOutOrg.orgInfo.name like ?) and ";
              parameters.add("%"+outOrgName+"%");
            }
//            if (inOrgName != null && !inOrgName.equals("")) {
//              criterion += "tranInHead.tranInOrg.orgInfo.name= ?  and ";
//              parameters.add(inOrgName);
//            }
            //ģ��
            if (inOrgName != null && !inOrgName.equals("")) {
              criterion += "tranInHead.tranInOrg.orgInfo.name like ?  and ";
              parameters.add("%"+inOrgName+"%");
            }
//            if (noteNum != null && !noteNum.equals("")) {
//              criterion += "tranInHead.noteNum= ?  and ";
//              parameters.add(noteNum);
//            }
            //ģ��
            if (noteNum != null && !noteNum.equals("")) {
              criterion += "tranInHead.noteNum like ?  and ";
              parameters.add("%"+noteNum+"%");
            }
//            if (docNum != null && !docNum.equals("")) {
//              criterion += "tranInHead.docNum = ?  and ";
//              parameters.add(docNum);
//            }
            //ģ��
            if (docNum != null && !docNum.equals("")) {
              criterion += "tranInHead.docNum like ?  and ";
              parameters.add("%"+docNum+"%");
            }
            if (collBankId != null&&!collBankId.equals("")) {
              criterion += " tranInHead.tranInOrg.orgInfo.collectionBankId = ? and ";
              parameters.add(collBankId);
            }
            if (criterion.length() != 0)
              criterion =  " where tranInHead.tranInOrg.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
            if(criterion.length() == 0){
              criterion =  " where tranInHead.tranInOrg.id "+securityInfo.getGjjSecurityHqlSQL();
//              +" and "
//              + criterion.substring(0, criterion.lastIndexOf("and"));
            }
            String ob = orderBy;
            if (ob == null)
              ob = "id";
            String od = order;
            if (od == null)
              od = "DESC";

            hql = hql + criterion + "order by " + ob + " " + order ;
            Query query = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            query.setFirstResult(start);
            session.clear();
            List queryList=query.list();
            
            if(queryList==null||queryList.size()==0){           
              query.setFirstResult(pageSize*(page-2));
              if (pageSize > 0)
                query.setMaxResults(pageSize);
              queryList=query.list();
            }
            return queryList;
          }
        });

      } catch (Exception e) {
        e.printStackTrace();
      }

      return list;
    }
    public List queryTranInListByCriterionsAll_sy_yg(final String settDate,final String settDatea,final String tranStatus,
        final String inOrgId, final String outOrgId, final String outOrgName,
        final String inOrgName, final String noteNum, final String docNum,
        final String orderBy, final String order, final int start,
        final int pageSize,final SecurityInfo securityInfo,final int page,final String collBankId) throws NumberFormatException, Exception {
      List list = null;
//     tranStatusΪBigDecimal
      try {
        Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
            || "DESC".equalsIgnoreCase(order));
        Validate.isTrue(start >= 0);
        
        list = getHibernateTemplate().executeFind(
            
            new HibernateCallback() {
              public Object doInHibernate(Session session) throws HibernateException,
              SQLException {
                String hql = " from TranInHead tranInHead ";
                Vector parameters = new Vector();
                String criterion = "";
                if (tranStatus != null && !tranStatus.equals("")) {
                  //��һ�ν���ת��ά��ʱ���״̬1��3
                  if(tranStatus.equals("13")){
                    criterion += "(tranInHead.tranStatus= 1  or tranInHead.tranStatus= 3  ) and ";
                  }else{
                    criterion += "tranInHead.tranStatus= ?  and ";
                    parameters.add(new BigDecimal(tranStatus));
                  }
                }
                if (settDate != null && !settDate.equals("")) {
                  criterion += "tranInHead.settDate >= ?  and ";
                  parameters.add(settDate);
                }
                if (settDatea != null && !settDatea.equals("")) {
                  criterion += "tranInHead.settDate <= ?  and ";
                  parameters.add(settDatea);
                }
                if (inOrgId != null && !inOrgId.equals("")) {
                  criterion += "to_char(tranInHead.tranInOrg.id) like ?  and ";
                  parameters.add("%"+new Integer(inOrgId)+"%");
                }
                if (outOrgId != null && !outOrgId.equals("")) {
                  criterion += "tranInHead.tranOutOrgId like ?  and ";
                  parameters.add("%"+outOrgId+"%");
                }
//            if (outOrgName != null && !outOrgName.equals("")) {
//              criterion += "tranInHead.tranOutOrg.orgInfo.name= ?  and ";
//              parameters.add(outOrgName);
//            }
                //ģ��
                if (outOrgName != null && !outOrgName.equals("")) {
                  criterion += "tranInHead.id in (select tranInHead.id from TranOutOrg tranOutOrg,TranInHead tranInHead  where  tranInHead.tranOutOrgId=tranOutOrg.id and  tranOutOrg.orgInfo.name like ?) and ";
                  parameters.add("%"+outOrgName+"%");
                }
//            if (inOrgName != null && !inOrgName.equals("")) {
//              criterion += "tranInHead.tranInOrg.orgInfo.name= ?  and ";
//              parameters.add(inOrgName);
//            }
                //ģ��
                if (inOrgName != null && !inOrgName.equals("")) {
                  criterion += "tranInHead.tranInOrg.orgInfo.name like ?  and ";
                  parameters.add("%"+inOrgName+"%");
                }
//            if (noteNum != null && !noteNum.equals("")) {
//              criterion += "tranInHead.noteNum= ?  and ";
//              parameters.add(noteNum);
//            }
                //ģ��
                if (noteNum != null && !noteNum.equals("")) {
                  criterion += "tranInHead.noteNum like ?  and ";
                  parameters.add("%"+noteNum+"%");
                }
//            if (docNum != null && !docNum.equals("")) {
//              criterion += "tranInHead.docNum = ?  and ";
//              parameters.add(docNum);
//            }
                //ģ��
                if (docNum != null && !docNum.equals("")) {
                  criterion += "tranInHead.docNum like ?  and ";
                  parameters.add("%"+docNum+"%");
                }
                if (collBankId != null&&!collBankId.equals("")) {
                  criterion += " tranInHead.tranInOrg.orgInfo.collectionBankId = ? and ";
                  parameters.add(collBankId);
                }
                if (criterion.length() != 0)
                  criterion =  " where tranInHead.tranInOrg.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
                if(criterion.length() == 0){
                  criterion =  " where tranInHead.tranInOrg.id "+securityInfo.getGjjSecurityHqlSQL();
//              +" and "
//              + criterion.substring(0, criterion.lastIndexOf("and"));
                }
                String ob = orderBy;
                if (ob == null)
                  ob = "id";
                String od = order;
                if (od == null)
                  od = "DESC";
                
                hql = hql + criterion + "order by " + ob + " " + order ;
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
    public List queryTranInListByCriterionsAll_sy(final String settDate,final String tranStatus,
        final String inOrgId, final String outOrgId, final String outOrgName,
        final String inOrgName, final String noteNum, final String docNum,
        final String orderBy, final String order, final int start,
        final int pageSize,final SecurityInfo securityInfo,final int page) throws NumberFormatException, Exception {
      List list = null;
//     tranStatusΪBigDecimal
      try {
        Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
            || "DESC".equalsIgnoreCase(order));
        Validate.isTrue(start >= 0);
        
        list = getHibernateTemplate().executeFind(
            
            new HibernateCallback() {
              public Object doInHibernate(Session session) throws HibernateException,
              SQLException {
                String hql = " from TranInHead tranInHead ";
                Vector parameters = new Vector();
                String criterion = "";
                if (tranStatus != null && !tranStatus.equals("")) {
                  //��һ�ν���ת��ά��ʱ���״̬1��3
                  if(tranStatus.equals("13")){
                    criterion += "(tranInHead.tranStatus= 1  or tranInHead.tranStatus= 3  ) and ";
                  }else{
                    criterion += "tranInHead.tranStatus= ?  and ";
                    parameters.add(new BigDecimal(tranStatus));
                  }
                }
                if (settDate != null && !settDate.equals("")) {
                  criterion += "tranInHead.settDate= ?  and ";
                  parameters.add(settDate);
                }
                if (inOrgId != null && !inOrgId.equals("")) {
                  criterion += "to_char(tranInHead.tranInOrg.id) like ?  and ";
                  parameters.add("%"+new Integer(inOrgId)+"%");
                }
                if (outOrgId != null && !outOrgId.equals("")) {
                  criterion += "tranInHead.tranOutOrgId like ?  and ";
                  parameters.add("%"+outOrgId+"%");
                }
//            if (outOrgName != null && !outOrgName.equals("")) {
//              criterion += "tranInHead.tranOutOrg.orgInfo.name= ?  and ";
//              parameters.add(outOrgName);
//            }
                //ģ��
                if (outOrgName != null && !outOrgName.equals("")) {
                  criterion += "tranInHead.id in (select tranInHead.id from TranOutOrg tranOutOrg,TranInHead tranInHead  where  tranInHead.tranOutOrgId=tranOutOrg.id and  tranOutOrg.orgInfo.name like ?) and ";
                  parameters.add("%"+outOrgName+"%");
                }
//            if (inOrgName != null && !inOrgName.equals("")) {
//              criterion += "tranInHead.tranInOrg.orgInfo.name= ?  and ";
//              parameters.add(inOrgName);
//            }
                //ģ��
                if (inOrgName != null && !inOrgName.equals("")) {
                  criterion += "tranInHead.tranInOrg.orgInfo.name like ?  and ";
                  parameters.add("%"+inOrgName+"%");
                }
//            if (noteNum != null && !noteNum.equals("")) {
//              criterion += "tranInHead.noteNum= ?  and ";
//              parameters.add(noteNum);
//            }
                //ģ��
                if (noteNum != null && !noteNum.equals("")) {
                  criterion += "tranInHead.noteNum like ?  and ";
                  parameters.add("%"+noteNum+"%");
                }
//            if (docNum != null && !docNum.equals("")) {
//              criterion += "tranInHead.docNum = ?  and ";
//              parameters.add(docNum);
//            }
                //ģ��
                if (docNum != null && !docNum.equals("")) {
                  criterion += "tranInHead.docNum like ?  and ";
                  parameters.add("%"+docNum+"%");
                }
                if (criterion.length() != 0)
                  criterion =  " where tranInHead.tranInOrg.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
                if(criterion.length() == 0){
                  criterion =  " where tranInHead.tranInOrg.id "+securityInfo.getGjjSecurityHqlSQL();
//              +" and "
//              + criterion.substring(0, criterion.lastIndexOf("and"));
                }
                String ob = orderBy;
                if (ob == null)
                  ob = "id";
                String od = order;
                if (od == null)
                  od = "DESC";
                
                hql = hql + criterion + "order by " + ob + " " + order ;
                Query query = session.createQuery(hql);
                for (int i = 0; i < parameters.size(); i++) {
                  query.setParameter(i, parameters.get(i));
                }
                if (pageSize > 0)
                  query.setMaxResults(pageSize);
                query.setFirstResult(start);
                session.clear();
                List queryList=query.list();
                
                if(queryList==null||queryList.size()==0){           
                  query.setFirstResult(pageSize*(page-2));
                  if (pageSize > 0)
                    query.setMaxResults(pageSize);
                  queryList=query.list();
                }
                return queryList;
              }
            });
        
      } catch (Exception e) {
        e.printStackTrace();
      }
      
      return list;
    }
    public List countTranInListByCriterionsAll_sy(final String settDate,final String tranStatus,
        final String inOrgId, final String outOrgId, final String outOrgName,
        final String inOrgName, final String noteNum, final String docNum,final SecurityInfo securityInfo
        ) throws NumberFormatException, Exception {
      List list = null;
//     tranStatusΪBigDecimal
      try {
       
        list = getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws HibernateException,
              SQLException {
            String hql = " from TranInHead tranInHead ";
            Vector parameters = new Vector();
            String criterion = "";
            if (tranStatus != null && !tranStatus.equals("")) {
              //��һ�ν���ת��ά��ʱ���״̬1��3
              if(tranStatus.equals("13")){
              criterion += "(tranInHead.tranStatus= 1  or tranInHead.tranStatus= 3 ) and ";
              }else{
              criterion += "tranInHead.tranStatus= ?  and ";
              parameters.add(new BigDecimal(tranStatus));
              }
             }
            if (settDate != null && !settDate.equals("")) {
              criterion += "tranInHead.settDate= ?  and ";
              parameters.add(settDate);
            }
            if (inOrgId != null && !inOrgId.equals("")) {
              criterion += "to_char(tranInHead.tranInOrg.id) like ?  and ";
              parameters.add("%"+new Integer(inOrgId)+"%");
            }
            if (outOrgId != null && !outOrgId.equals("")) {
              criterion += "tranInHead.tranOutOrgId like ?  and ";
              parameters.add("%"+outOrgId+"%");
            }
//            if (outOrgName != null && !outOrgName.equals("")) {
//              criterion += "tranInHead.tranOutOrg.orgInfo.name= ?  and ";
//              parameters.add(outOrgName);
//            }
            //ģ��
            if (outOrgName != null && !outOrgName.equals("")) {
              criterion += "tranInHead.id in (select tranInHead.id from TranOutOrg tranOutOrg,TranInHead tranInHead where  tranInHead.tranOutOrgId=tranOutOrg.id and  tranOutOrg.orgInfo.name like ?) and ";
              parameters.add("%"+outOrgName+"%");
            }
//            if (inOrgName != null && !inOrgName.equals("")) {
//              criterion += "tranInHead.tranInOrg.orgInfo.name= ?  and ";
//              parameters.add(inOrgName);
//            }
            //ģ��
            if (inOrgName != null && !inOrgName.equals("")) {
              criterion += "tranInHead.tranInOrg.orgInfo.name like ?  and ";
              parameters.add("%"+inOrgName+"%");
            }
//            if (noteNum != null && !noteNum.equals("")) {
//              criterion += "tranInHead.noteNum= ?  and ";
//              parameters.add(noteNum);
//            }
            //ģ��
            if (noteNum != null && !noteNum.equals("")) {
              criterion += "tranInHead.noteNum like ?  and ";
              parameters.add("%"+noteNum+"%");
            }
//            if (docNum != null && !docNum.equals("")) {
//              criterion += "tranInHead.docNum = ?  and ";
//              parameters.add(docNum);
//            }
            //ģ��
            if (docNum != null && !docNum.equals("")) {
              criterion += "tranInHead.docNum like ?  and ";
              parameters.add("%"+docNum+"%");
            }
            if (criterion.length() != 0)
              criterion =  " where tranInHead.tranInOrg.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
            if(criterion.length() == 0){
              criterion =  " where tranInHead.tranInOrg.id "+securityInfo.getGjjSecurityHqlSQL();
            }

            hql = hql + criterion + "" ;
            Query query = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
//            session.clear();
            return query.list();
          }
        });

      } catch (Exception e) {
        e.printStackTrace();
      }
      return list;
    }
  
    public List countTranInListByCriterionsAll_sy(final String settDate,final String settDatea,final String tranStatus,
        final String inOrgId, final String outOrgId, final String outOrgName,
        final String inOrgName, final String noteNum, final String docNum,final SecurityInfo securityInfo,final String collBankId
    ) throws NumberFormatException, Exception {
      List list = null;
//     tranStatusΪBigDecimal
      try {
        
        list = getHibernateTemplate().executeFind(
            new HibernateCallback() {
              public Object doInHibernate(Session session) throws HibernateException,
              SQLException {
                String hql = " from TranInHead tranInHead ";
                Vector parameters = new Vector();
                String criterion = "";
                if (tranStatus != null && !tranStatus.equals("")) {
                  //��һ�ν���ת��ά��ʱ���״̬1��3
                  if(tranStatus.equals("13")){
                    criterion += "(tranInHead.tranStatus= 1  or tranInHead.tranStatus= 3 ) and ";
                  }else{
                    criterion += "tranInHead.tranStatus= ?  and ";
                    parameters.add(new BigDecimal(tranStatus));
                  }
                }
                if (settDate != null && !settDate.equals("")) {
                  criterion += "tranInHead.settDate >= ?  and ";
                  parameters.add(settDate);
                }
                if (settDatea != null && !settDatea.equals("")) {
                  criterion += "tranInHead.settDate <= ?  and ";
                  parameters.add(settDatea);
                }
                if (inOrgId != null && !inOrgId.equals("")) {
                  criterion += "to_char(tranInHead.tranInOrg.id) like ?  and ";
                  parameters.add("%"+new Integer(inOrgId)+"%");
                }
                if (outOrgId != null && !outOrgId.equals("")) {
                  criterion += "tranInHead.tranOutOrgId like ?  and ";
                  parameters.add("%"+outOrgId+"%");
                }
//            if (outOrgName != null && !outOrgName.equals("")) {
//              criterion += "tranInHead.tranOutOrg.orgInfo.name= ?  and ";
//              parameters.add(outOrgName);
//            }
                //ģ��
                if (outOrgName != null && !outOrgName.equals("")) {
                  criterion += "tranInHead.id in (select tranInHead.id from TranOutOrg tranOutOrg,TranInHead tranInHead where  tranInHead.tranOutOrgId=tranOutOrg.id and  tranOutOrg.orgInfo.name like ?) and ";
                  parameters.add("%"+outOrgName+"%");
                }
//            if (inOrgName != null && !inOrgName.equals("")) {
//              criterion += "tranInHead.tranInOrg.orgInfo.name= ?  and ";
//              parameters.add(inOrgName);
//            }
                //ģ��
                if (inOrgName != null && !inOrgName.equals("")) {
                  criterion += "tranInHead.tranInOrg.orgInfo.name like ?  and ";
                  parameters.add("%"+inOrgName+"%");
                }
//            if (noteNum != null && !noteNum.equals("")) {
//              criterion += "tranInHead.noteNum= ?  and ";
//              parameters.add(noteNum);
//            }
                //ģ��
                if (noteNum != null && !noteNum.equals("")) {
                  criterion += "tranInHead.noteNum like ?  and ";
                  parameters.add("%"+noteNum+"%");
                }
//            if (docNum != null && !docNum.equals("")) {
//              criterion += "tranInHead.docNum = ?  and ";
//              parameters.add(docNum);
//            }
                //ģ��
                if (docNum != null && !docNum.equals("")) {
                  criterion += "tranInHead.docNum like ?  and ";
                  parameters.add("%"+docNum+"%");
                }
                if (collBankId != null&&!collBankId.equals("")) {
                  criterion += " tranInHead.tranInOrg.orgInfo.collectionBankId = ? and ";
                  parameters.add(collBankId);
                }
                if (criterion.length() != 0)
                  criterion =  " where tranInHead.tranInOrg.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
                if(criterion.length() == 0){
                  criterion =  " where tranInHead.tranInOrg.id "+securityInfo.getGjjSecurityHqlSQL();
                }
                
                hql = hql + criterion + "" ;
                Query query = session.createQuery(hql);
                for (int i = 0; i < parameters.size(); i++) {
                  query.setParameter(i, parameters.get(i));
                }
//            session.clear();
                return query.list();
              }
            });
        
      } catch (Exception e) {
        e.printStackTrace();
      }
      return list;
    }
    
  
 /**sum��Ͳ���ͷ������
 *return Object[]
 */
  public List queryTranIn_sy(final Integer id) throws NumberFormatException,
      Exception {
    List list = null;
    list =getHibernateTemplate().executeFind(
        
      new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = " select tranInHead.id,sum(tranInTail.preBalance + tranInTail.curBalance+tranInTail.preInterest + tranInTail.curInterest) from TranInTail tranInTail,TranInHead tranInHead ";
          Vector parameters = new Vector();
          String criterion = "";
          criterion += "tranInTail.tranInHead.id= tranInHead.id  and tranInHead.id= ? group by tranInHead.id";
          parameters.add(id);
         hql = hql + " where "+criterion;
         Query query = session.createQuery(hql);
         for (int i = 0; i < parameters.size(); i++) {
           query.setParameter(i, parameters.get(i));
         }
//        session.clear();
        return query.list();
      }
    }
    );
    return list;
  }
  /**sum��Ͳ���ͷ������
   *return Object[]
   */
    public List queryTranInSumBalance_sy(final Integer id) throws NumberFormatException,
        Exception {
      List list = null;
      list =getHibernateTemplate().executeFind(
          
        new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select tranInHead.id,sum(tranInTail.preBalance + tranInTail.curBalance) from TranInTail tranInTail,TranInHead tranInHead ";
            Vector parameters = new Vector();
            String criterion = "";
            criterion += "tranInTail.tranInHead.id= tranInHead.id  and tranInHead.id= ? group by tranInHead.id";
            parameters.add(id);
           hql = hql + " where "+criterion;
           Query query = session.createQuery(hql);
           for (int i = 0; i < parameters.size(); i++) {
             query.setParameter(i, parameters.get(i));
           }
//          session.clear();
          return query.list();
        }
      }
      );
      return list;
    }
  /**
   *�ж�TranOutHead�Ƿ��ж�ӦTranInTail������ 
   * 
   */
  public List queryTranOutHeadId_sy(final String id){
    List list = null;
    list = getHibernateTemplate().executeFind(
        new HibernateCallback() {

          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {

            String hql = " from TranInHead tranInHead  ";
            Vector parameters = new Vector();
            String criterion = "";
           
              criterion += " tranInHead.tranOutHeadId= ?  and ";
              parameters.add(new BigDecimal(id));
  
            if (criterion.length()!= 0)
              criterion = " where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            
            Query query = session.createQuery(hql); 
            
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            
            return query.list();
          }
        }
        );
    
    return list;
  }
  /**
   *ɾ��ת��ͷ����Ϣ 
   * 
   */
  public void deleteTranHead_sy(TranInHead tranInHead)throws NumberFormatException, Exception{
//    getHibernateTemplate().clear();
    getHibernateTemplate().delete(tranInHead);

  }
  /**
   *�������뵥λ��Ų���״̬ 
   */
  public List queryByInOrgId(final String id,final String tranStatus)throws NumberFormatException, Exception {
    List list = null;
  
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = " from TranInHead tranInHead  ";
              Vector parameters = new Vector();
              String criterion = "";

              if (id != null&&!id.equals("")) {
                criterion += " tranInHead.tranInOrg.id= ?  and ";
                parameters.add(new Integer(id));
              }
              if (tranStatus != null&&!tranStatus.equals("")) {
                if(tranStatus.equals("5")){
                  criterion += " tranInHead.tranStatus< ? and ";
                  parameters.add(new BigDecimal(tranStatus));
                }else{
                criterion += " tranInHead.tranStatus= ?  and ";
                parameters.add(new BigDecimal(tranStatus));
                }
              }
              if (criterion.length()!= 0)
                criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
     
              hql = hql + criterion+"";
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
   *ͨ�����뵥λid����������� 
   */
  public List queryTraninByInOrgId(final Integer id)throws NumberFormatException, Exception {  
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = " from TranInHead tranInHead ";
              Vector parameters = new Vector();
              String criterion = "";
              if (id != null&&!id.equals("")) {
                criterion += " tranInHead.tranInOrg.orgInfo.id= ? and";
                parameters.add(id);
              }
              if (criterion.length()!= 0)
                criterion = " where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion;
              
              Query query = session.createQuery(hql); 
              
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              
              return query.list();
            }
          }
      );
    }catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public TranInHead queryById(Serializable id) {    
    Validate.notNull(id);
    TranInHead tranInHead=null;
    try{
      tranInHead=(TranInHead) getHibernateTemplate().get(TranInHead.class,new Integer(id.toString())); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return tranInHead;
  }
  public TranInHead queryByIdWuht(Serializable id) {    
    Validate.notNull(id);
    TranInHead tranInHead=null;
    try{
      tranInHead=(TranInHead) getHibernateTemplate().get(TranInHead.class,new Integer(id.toString())); 

    }catch(Exception e){
      e.printStackTrace();
    }
    return tranInHead;
  }
  /**
   * �����¼
   * @param tranInHead
   * @return
   */
  public Serializable insert(TranInHead tranInHead){
    Serializable id = null;
    try{    
    Validate.notNull(tranInHead);
    id = getHibernateTemplate().save(tranInHead);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  

  /**����
   * ����ת����λ��ѯת�뵥λ
   * @param id ת����λid
   * @return BizActivityLog
   */
  public TranInHead queryTranInOrgIdByOutorgId(final String orgoutid) {
    TranInHead tranInHead=null;
    try{
      tranInHead=(TranInHead)getHibernateTemplate().execute(    
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = " from TranInHead tranInHead where  "+
                         "tranInHead.tranInOrg.id = ? ";
            Query query = session.createQuery(hql);
             query.setParameter(0,new Integer(orgoutid));
             return query.uniqueResult();
        }
        }); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return tranInHead;
  }
  
}
